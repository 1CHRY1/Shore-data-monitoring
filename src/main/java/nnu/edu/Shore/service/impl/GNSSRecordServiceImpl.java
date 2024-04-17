package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.utils.DatabaseUtil;
import nnu.edu.Shore.common.utils.TimeUtil;
import nnu.edu.Shore.dao.shore.GNSSRecordMapper;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.GNSSRecord.GNSSRecordIdGroup;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.service.GNSSRecordService;
import nnu.edu.Shore.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 10:39
 * @Description:
 */

@Repository
public class GNSSRecordServiceImpl implements GNSSRecordService {

    @Value("${spring.datasource.shore.jdbc-url}")
    String URL;

    @Value("${spring.datasource.shore.username}")
    String USER;

    @Value("${spring.datasource.shore.password}")
    String PASSWORD;

    @Autowired
    GNSSRecordMapper gnssRecordMapper;

    @Autowired
    MachineService machineService;

    @SneakyThrows
    private GNSSRecord dataProcess(JSONObject jsonObject){
        // 若是一个月的第一分钟，则新建表分区进行存储
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getString("read_date"));
        DatabaseUtil.DBPartition(URL, USER, PASSWORD, measure_time, "gnss");
        // GNSS编号为1
        GNSSRecord gnssRecord;
        String machine_id = jsonObject.getString("device_id");
        JSONObject machineInfo = machineService.getMachineInfo(machine_id,'1');
        if (machineInfo == null) {
            return null;
        }
        String station_id = machineInfo.getString("station_id");
        String machine_id_nnu = machineInfo.getString("machine_id_nnu");
        // 判断请求格式和非空字段是否正确
        try {
            gnssRecord = GNSSRecord.builder()
                    .idGroup(
                            GNSSRecordIdGroup.builder()
                                    .station_id(station_id)
                                    .machine_id(machine_id)
                                    .machine_id_nnu(machine_id_nnu)
                                    .measure_time(measure_time)
                                    .build())
                    .x_move(jsonObject.getJSONObject("param_value").getDouble("x"))
                    .y_move(jsonObject.getJSONObject("param_value").getDouble("y"))
                    .z_move(jsonObject.getJSONObject("param_value").getDouble("z"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        }
        catch (JSONException | NumberFormatException | NullPointerException | ParseException e) {
            return GNSSRecord.builder().build();
        }
        Number threeD = jsonObject.getJSONObject("param_value").getDouble("3d");
        Number threeDF = jsonObject.getJSONObject("param_value").getDouble("3d_5h");
        if (threeD != null) {gnssRecord.setThreeD(threeD.doubleValue());} else {gnssRecord.setThreeD(null);}
        if (threeDF != null) {gnssRecord.setThreeDF(threeDF.doubleValue());} else {gnssRecord.setThreeDF(null);}
        return gnssRecord;
    }

    @Override
    public String insertGNSSRecord(JSONObject jsonObject) {

        // 数据处理
        GNSSRecord gnssRecord = dataProcess(jsonObject);
        if (gnssRecord == null) {
            return "设备id不存在";
        }
        gnssRecordMapper.insertGNSSRecord(gnssRecord);
        return gnssRecord.getIdGroup().getMachine_id();
    }

    @Override
    public List<GNSSRecord> getAllGNSSRecord() {
        // 获取所有GNSS记录数据
        return gnssRecordMapper.getAllGNSSRecord();
    }

    @Override
    public Integer getGNSSRecordCount() {
        return gnssRecordMapper.getGNSSRecordCount();
    }

    @Override
    public String getLatestTime() {
        GNSSRecord gnssRecord = gnssRecordMapper.getLatestRecord();
        if ( gnssRecord == null ) {
            return "当前无记录";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = gnssRecord.getIn_time().toLocalDateTime();
        return time.format(formatter);
    }

//    @Override
//    public String updateGNSSRecord(GNSSRecord gnssRecord) {
//        return null;
//    }
//
//    @Override
//    public String deleteGNSSRecord(String gnssRecord) {
//        return null;
//    }
//
//    @Override
//    public List<GNSSRecord> getGNSSRecords() {
//        return null;
//    }
}
