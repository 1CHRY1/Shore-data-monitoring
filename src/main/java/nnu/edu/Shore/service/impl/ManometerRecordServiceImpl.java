package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import nnu.edu.Shore.common.utils.DatabaseUtil;
import nnu.edu.Shore.common.utils.TimeUtil;
import nnu.edu.Shore.dao.shore.ManometerRecordMapper;
import nnu.edu.Shore.pojo.ManometerRecord;
import nnu.edu.Shore.pojo.ManometerRecord.ManometerRecordIdGroup;
import nnu.edu.Shore.service.MachineService;
import nnu.edu.Shore.service.ManometerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:48
 * @Description:
 */

@Service
public class ManometerRecordServiceImpl implements ManometerRecordService {

    @Value("${spring.datasource.shore.jdbc-url}")
    String URL;

    @Value("${spring.datasource.shore.username}")
    String USER;

    @Value("${spring.datasource.shore.password}")
    String PASSWORD;

    @Autowired
    ManometerRecordMapper manometerRecordMapper;

    @Autowired
    MachineService machineService;

    @SneakyThrows
    private ManometerRecord dataProccess(JSONObject jsonObject){
        // 获取数据时间
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getString("read_date"));
        // 孔隙水压力计编号为3
        ManometerRecord manometerRecord;
        String machine_id = jsonObject.getString("device_id");
        JSONObject machineInfo = machineService.getMachineInfo(machine_id, '3');
        if (machineInfo == null) {
            return null;
        }
        String station_id = machineInfo.getString("station_id");
        String machine_id_nnu = machineInfo.getString("machine_id_nnu");
        // 判断请求格式和非空字段是否正确
        try {
            manometerRecord = ManometerRecord.builder()
                    .idGroup(ManometerRecordIdGroup.builder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .measure_time(measure_time)
                            .build())
                    .zx(jsonObject.getJSONObject("param_value").getDouble("zx"))
                    .wd(jsonObject.getJSONObject("param_value").getDouble("wd"))
                    .swgc(jsonObject.getJSONObject("param_value").getDouble("swgc"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return ManometerRecord.builder().build();
        }

        return manometerRecord;
    }

    @Override
    public String insertManometerRecord(JSONObject jsonObject) {
        ManometerRecord manometerRecord = dataProccess(jsonObject);
        if (manometerRecord == null) {
            return "设备id不存在";
        }
        try {
            manometerRecordMapper.insertManometerRecord(manometerRecord);
        } catch (DuplicateKeyException e) {
            return "重复的孔隙水压力计设备记录";
        }
        return manometerRecord.getIdGroup().getMachine_id();
    }

    @Override
    public List<ManometerRecord> getAllManometerRecord() {
        return manometerRecordMapper.getAllManometerRecord();
    }

    @Override
    public Integer getManometerRecordCount() {
        return manometerRecordMapper.getManometerRecordCount();
    }

    @Override
    public String getLatestTime() {
        ManometerRecord manometerRecord = manometerRecordMapper.getLatestRecord();
        if ( manometerRecord == null ) {
            return "当前无记录";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = manometerRecord.getIn_time().toLocalDateTime();
        return time.format(formatter);
    }

//    @Override
//    public String updateManometerRecord(ManometerRecord manometerRecord) {
//        return null;
//    }
//
//    @Override
//    public String deleteManometerRecord(String machine_id_nnu) {
//        return null;
//    }
//
//    @Override
//    public List<Machine> getManometerRecords() {
//        return null;
//    }
}
