package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.InclinometerInfoMapper;
import nnu.edu.Shore.dao.shore.InclinometerRecordMapper;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.InclinometerRecord;
import nnu.edu.Shore.pojo.InclinometerRecord.InclinometerRecordIdGroup;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.service.InclinometerInfoService;
import nnu.edu.Shore.service.InclinometerRecordService;
import nnu.edu.Shore.service.MachineService;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
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

    @Autowired
    InclinometerRecordMapper inclinometerRecordMapper;

    @Autowired
    MachineService machineService;

    private InclinometerRecord dataProcess(JSONObject jsonObject){
        InclinometerRecord inclinometerRecord;
        String machine_id = jsonObject.getJSONObject("idGroup").getString("machine_id");
        JSONObject machineInfo = machineService.getMachineInfo(machine_id);
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
                            .measure_time(jsonObject.getJSONObject("idGroup").getString("measure_time"))
                            .build())
                    .x_move1(jsonObject.getDouble("x_move1"))
                    .y_move1(jsonObject.getDouble("y_move1"))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return InclinometerRecord.builder().build();
        }
        // 判断其他字段
        Number x_move2 = (Number) jsonObject.getOrDefault("x_move2",null);
        Number y_move2 = (Number) jsonObject.getOrDefault("y_move2",null);
        Number x_move3 = (Number) jsonObject.getOrDefault("x_move3",null);
        Number y_move3 = (Number) jsonObject.getOrDefault("y_move3",null);
        Number x_move4 = (Number) jsonObject.getOrDefault("x_move4",null);
        Number y_move4 = (Number) jsonObject.getOrDefault("y_move4",null);
        Number x_move5 = (Number) jsonObject.getOrDefault("x_move5",null);
        Number y_move5 = (Number) jsonObject.getOrDefault("y_move5",null);
        Number x_move6 = (Number) jsonObject.getOrDefault("x_move6",null);
        Number y_move6 = (Number) jsonObject.getOrDefault("y_move6",null);
        if (x_move2 != null) {inclinometerRecord.setX_move2(x_move2.doubleValue());} else {inclinometerRecord.setX_move2(null);}
        if (y_move2 != null) {inclinometerRecord.setY_move2(y_move2.doubleValue());} else {inclinometerRecord.setY_move2(null);}
        if (x_move3 != null) {inclinometerRecord.setX_move2(x_move3.doubleValue());} else {inclinometerRecord.setX_move3(null);}
        if (y_move3 != null) {inclinometerRecord.setY_move2(y_move3.doubleValue());} else {inclinometerRecord.setY_move3(null);}
        if (x_move4 != null) {inclinometerRecord.setX_move2(x_move4.doubleValue());} else {inclinometerRecord.setX_move4(null);}
        if (y_move4 != null) {inclinometerRecord.setY_move2(y_move4.doubleValue());} else {inclinometerRecord.setY_move4(null);}
        if (x_move5 != null) {inclinometerRecord.setX_move2(x_move5.doubleValue());} else {inclinometerRecord.setX_move5(null);}
        if (y_move5 != null) {inclinometerRecord.setY_move2(y_move5.doubleValue());} else {inclinometerRecord.setY_move5(null);}
        if (x_move6 != null) {inclinometerRecord.setX_move2(x_move6.doubleValue());} else {inclinometerRecord.setX_move6(null);}
        if (y_move6 != null) {inclinometerRecord.setY_move2(y_move6.doubleValue());} else {inclinometerRecord.setY_move6(null);}
        String in_time = LocalDateTime.now().toString();
        inclinometerRecord.setIn_time(in_time);
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
