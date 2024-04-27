package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import nnu.edu.Shore.common.utils.DatabaseUtil;
import nnu.edu.Shore.common.utils.TimeUtil;
import nnu.edu.Shore.dao.shore.StresspileRecordMapper;
import nnu.edu.Shore.pojo.*;
import nnu.edu.Shore.pojo.StresspileRecord.StresspileRecordIdGroup;
import nnu.edu.Shore.service.MachineService;
import nnu.edu.Shore.service.StresspileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
 * @Date: 2024/1/17 15:41
 * @Description:
 */

@Service
public class StresspileRecordServiceImpl implements StresspileRecordService {

    @Value("${spring.datasource.shore.jdbc-url}")
    String URL;

    @Value("${spring.datasource.shore.username}")
    String USER;

    @Value("${spring.datasource.shore.password}")
    String PASSWORD;

    @Autowired
    StresspileRecordMapper stresspileRecordMapper;

    @Autowired
    MachineService machineService;

    @SneakyThrows
    private StresspileRecord dataProcess(JSONObject jsonObject){
        // 若是一个月的第一分钟，则新建表分区进行存储
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getString("read_date"));
        DatabaseUtil.DBPartition(URL, USER, PASSWORD, measure_time, "stresspile");
        // 应力桩编号为2
        StresspileRecord stresspileRecord;
        String machine_id = jsonObject.getString("device_id");
        JSONObject machineInfo = machineService.getMachineInfo(machine_id,'2');
        if (machineInfo == null) {
            return null;
        }
        String station_id = machineInfo.getString("station_id");
        String machine_id_nnu = machineInfo.getString("machine_id_nnu");
        // 判断请求格式和非空字段是否正确
        try {
            stresspileRecord = StresspileRecord.builder()
                    .idGroup(StresspileRecordIdGroup.builder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .measure_time(measure_time)
                            .build())
                    .top_angle(jsonObject.getJSONObject("param_value").getDouble("top_angle"))
                    .middle_angle(jsonObject.getJSONObject("param_value").getDouble("middle_angle"))
                    .bottom_angle(jsonObject.getJSONObject("param_value").getDouble("bottom_angle"))
                    .top_power(jsonObject.getJSONObject("param_value").getDouble("top_power"))
                    .middle_power(jsonObject.getJSONObject("param_value").getDouble("middle_power"))
                    .bottom_power(jsonObject.getJSONObject("param_value").getDouble("bottom_power"))
                    .top_change(jsonObject.getJSONObject("param_value").getDouble("top_change"))
                    .middle_change(jsonObject.getJSONObject("param_value").getDouble("middle_change"))
                    .bottom_change(jsonObject.getJSONObject("param_value").getDouble("bottom_change"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return StresspileRecord.builder().build();
        }

        return stresspileRecord;
    }

    @Override
    public String insertStresspileRecord(JSONObject jsonObject) {
        StresspileRecord stresspileRecord = dataProcess(jsonObject);
        if (stresspileRecord == null) {
            return "设备id不存在";
        }
        try {
            stresspileRecordMapper.insertStresspileRecord(stresspileRecord);
        } catch (DuplicateKeyException e) {
            return "重复的应力桩设备记录";
        }
        return stresspileRecord.getIdGroup().getMachine_id();
    }

    @Override
    public List<StresspileRecord> getAllStresspileRecord() {
        // 获取所有Stresspile记录数据
        return stresspileRecordMapper.getAllStresspileRecord();
    }

    @Override
    public Integer getStresspileRecordCount() {
        return stresspileRecordMapper.getStresspileRecordCount();
    }

    @Override
    public String getLatestTime() {
        StresspileRecord stresspileRecord = stresspileRecordMapper.getLatestRecord();
        if ( stresspileRecord == null ) {
            return "当前无记录";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = stresspileRecord.getIn_time().toLocalDateTime();
        return time.format(formatter);
    }

//    @Override
//    public String updateStresspileRecord(StresspileRecord stresspileRecord) {
//        return null;
//    }
//
//    @Override
//    public String deleteStresspileRecord(String machine_id_nnu) {
//        return null;
//    }
//
//    @Override
//    public List<Machine> getStresspileRecords() {
//        return null;
//    }
}
