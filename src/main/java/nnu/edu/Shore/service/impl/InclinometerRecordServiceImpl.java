package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.InclinometerInfoMapper;
import nnu.edu.Shore.dao.shore.InclinometerRecordMapper;
import nnu.edu.Shore.pojo.InclinometerRecord;
import nnu.edu.Shore.pojo.InclinometerRecord.InclinometerRecordIdGroup;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.service.InclinometerInfoService;
import nnu.edu.Shore.service.InclinometerRecordService;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private InclinometerRecord dataProcess(JSONObject jsonObject){
        InclinometerRecord inclinometerRecord = InclinometerRecord.builder()
                .idGroup(InclinometerRecordIdGroup.builder()
                        .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                        .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                        .machine_id_nnu(jsonObject.getJSONObject("idGroup").getString("machine_id_nnu"))
                        .measure_time(jsonObject.getJSONObject("idGroup").getString("measure_time"))
                        .build())
                .x_move1(Double.parseDouble(jsonObject.getString("x_move1")))
                .y_move1(Double.parseDouble(jsonObject.getString("y_move1")))
                .x_move2(Double.parseDouble(jsonObject.getString("x_move2")))
                .y_move2(Double.parseDouble(jsonObject.getString("y_move2")))
                .x_move3(Double.parseDouble(jsonObject.getString("x_move3")))
                .y_move3(Double.parseDouble(jsonObject.getString("y_move3")))
                .x_move4(Double.parseDouble(jsonObject.getString("x_move4")))
                .y_move4(Double.parseDouble(jsonObject.getString("y_move4")))
                .x_move5(Double.parseDouble(jsonObject.getString("x_move5")))
                .y_move5(Double.parseDouble(jsonObject.getString("y_move5")))
                .x_move6(Double.parseDouble(jsonObject.getString("x_move6")))
                .y_move6(Double.parseDouble(jsonObject.getString("y_move6")))
                .in_time(jsonObject.getString("in_time"))
                .build();
        return inclinometerRecord;
    }
    @Override
    public String insertInclinometerRecord(JSONObject jsonObject) {
        InclinometerRecord inclinometerRecord = dataProcess(jsonObject);
        inclinometerRecordMapper.insertInclinometerRecord(inclinometerRecord);
        return inclinometerRecord.getIdGroup().getMachine_id();
    }

    @Override
    public String updateInclinometerRecord(InclinometerRecord inclinometerRecord) {
        return null;
    }

    @Override
    public String deleteInclinometerRecord(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getInclinometerRecords() {
        return null;
    }
}
