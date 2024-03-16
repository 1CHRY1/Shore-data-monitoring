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
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getJSONObject("idGroup").getString("measure_time"));
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        if (currentDate.getDayOfMonth() == 1 && currentTime.getHour() == 0 && currentTime.getMinute() == 0 ) {
//        if (currentDate.getDayOfMonth() == 16 ) {
            DatabaseUtil.DBPartition(URL, USER, PASSWORD, measure_time, "gnss");
        }
        // GNSS编号为1
        GNSSRecord gnssRecord;
        String machine_id = jsonObject.getJSONObject("idGroup").getString("machine_id");
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
                    .x_move(jsonObject.getDouble("x_move"))
                    .y_move(jsonObject.getDouble("y_move"))
                    .z_move(jsonObject.getDouble("z_move"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        }
        catch (JSONException | NumberFormatException | NullPointerException | ParseException e) {
            return GNSSRecord.builder().build();
        }

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
