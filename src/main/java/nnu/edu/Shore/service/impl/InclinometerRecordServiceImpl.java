package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import nnu.edu.Shore.common.utils.DatabaseUtil;
import nnu.edu.Shore.common.utils.TimeUtil;
import nnu.edu.Shore.dao.shore.InclinometerInfoMapper;
import nnu.edu.Shore.dao.shore.InclinometerRecordMapper;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.InclinometerRecord;
import nnu.edu.Shore.pojo.InclinometerRecord.InclinometerRecordIdGroup;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.service.InclinometerInfoService;
import nnu.edu.Shore.service.InclinometerRecordService;
import nnu.edu.Shore.service.MachineService;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:17
 * @Description:
 */

@Service
public class InclinometerRecordServiceImpl implements InclinometerRecordService {

    @Value("${spring.datasource.shore.jdbc-url}")
    String URL;

    @Value("${spring.datasource.shore.username}")
    String USER;

    @Value("${spring.datasource.shore.password}")
    String PASSWORD;

    @Autowired
    InclinometerRecordMapper inclinometerRecordMapper;

    @Autowired
    MachineService machineService;

    @SneakyThrows
    private InclinometerRecord dataProcess(JSONObject jsonObject){
        // 若是一个月的第一分钟，则新建表分区进行存储
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getString("read_date"));
        DatabaseUtil.DBPartition(URL, USER, PASSWORD, measure_time, "inclinometer");
        // 测斜仪编号为4
        InclinometerRecord inclinometerRecord;
        String machine_id = jsonObject.getString("device_id");
        JSONObject machineInfo = machineService.getMachineInfo(machine_id,'4');
        if (machineInfo == null) {
            return null;
        }
        String station_id = machineInfo.getString("station_id");
        String machine_id_nnu = machineInfo.getString("machine_id_nnu");
        // 判断请求格式和非空字段是否正确
        try {
            inclinometerRecord = InclinometerRecord.builder()
                    .idGroup(InclinometerRecordIdGroup.builder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .measure_time(measure_time)
                            .build())
                    .top_move(jsonObject.getJSONObject("param_value").getDouble("ding_wy"))
                    .middle_move(jsonObject.getJSONObject("param_value").getDouble("zhong_wy"))
                    .bottom_move(jsonObject.getJSONObject("param_value").getDouble("di_wy"))
                    .top_move_24h(jsonObject.getJSONObject("param_value").getDouble("ding_wy_24h"))
                    .middle_move_24h(jsonObject.getJSONObject("param_value").getDouble("zhong_wy_24h"))
                    .bottom_move_24h(jsonObject.getJSONObject("param_value").getDouble("di_wy_24h"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return InclinometerRecord.builder().build();
        }

        return inclinometerRecord;
    }
    @Override
    public String insertInclinometerRecord(JSONObject jsonObject) {
        InclinometerRecord inclinometerRecord = dataProcess(jsonObject);
        if (inclinometerRecord == null) {
            return "设备id不存在";
        }
        inclinometerRecordMapper.insertInclinometerRecord(inclinometerRecord);
        return inclinometerRecord.getIdGroup().getMachine_id();
    }

    @Override
    public List<InclinometerRecord> getAllInclinometerRecord() {
        // 获取所有测斜记录数据
        return inclinometerRecordMapper.getAllInclinometerRecord();
    }

    @Override
    public Integer getInclinometerRecordCount() {
        return inclinometerRecordMapper.getInclinometerRecordCount();
    }

    @Override
    public String getLatestTime() {
        InclinometerRecord inclinometerRecord = inclinometerRecordMapper.getLatestRecord();
        if ( inclinometerRecord == null ) {
            return "当前无记录";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = inclinometerRecord.getIn_time().toLocalDateTime();
        return time.format(formatter);
    }
//    @Override
//    public String updateInclinometerRecord(InclinometerRecord inclinometerRecord) {
//        return null;
//    }
//
//    @Override
//    public String deleteInclinometerRecord(String machine_id_nnu) {
//        return null;
//    }
//
//    @Override
//    public List<Machine> getInclinometerRecords() {
//        return null;
//    }
}
