package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.StresspileRecordMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerRecord;
import nnu.edu.Shore.pojo.StresspileRecord;
import nnu.edu.Shore.pojo.StresspileRecord.StresspileRecordIdGroup;
import nnu.edu.Shore.service.StresspileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    StresspileRecordMapper stresspileRecordMapper;

    private StresspileRecord dataProcess(JSONObject jsonObject){
        StresspileRecord stresspileRecord = StresspileRecord.builder()
                .idGroup(StresspileRecordIdGroup.builder()
                        .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                        .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                        .machine_id_nnu(jsonObject.getJSONObject("idGroup").getString("machine_id_nnu"))
                        .measure_time(jsonObject.getJSONObject("idGroup").getString("measure_time"))
                        .build())
                .horizontal1(jsonObject.getDouble("horizontal1"))
                .horizontal_stress1(jsonObject.getDouble("horizontal_stress1"))
                .vertical_stress1(jsonObject.getDouble("vertical_stress1"))
                .horizontal2(jsonObject.getDouble("horizontal2"))
                .horizontal_stress2(jsonObject.getDouble("horizontal_stress2"))
                .vertical_stress2(jsonObject.getDouble("vertical_stress2"))
                .horizontal3(jsonObject.getDouble("horizontal3"))
                .horizontal_stress3(jsonObject.getDouble("horizontal_stress3"))
                .vertical_stress3(jsonObject.getDouble("vertical_stress3"))
                .horizontal4(jsonObject.getDouble("horizontal4"))
                .horizontal_stress4(jsonObject.getDouble("horizontal_stress4"))
                .vertical_stress4(jsonObject.getDouble("vertical_stress4"))
                .horizonta15(jsonObject.getDouble("horizontal5"))
                .horizontal_stress5(jsonObject.getDouble("horizontal_stress5"))
                .vertical_stress5(jsonObject.getDouble("vertical_stress5"))
                .horizontal6(jsonObject.getDouble("horizontal6"))
                .horizontal_stress6(jsonObject.getDouble("horizontal_stress6"))
                .vertical_stress6(jsonObject.getDouble("vertical_stress6"))
                .in_time(jsonObject.getString("in_time"))
                .build();
        return stresspileRecord;
    }

    @Override
    public String insertStresspileRecord(JSONObject jsonObject) {
        StresspileRecord stresspileRecord = dataProcess(jsonObject);
        stresspileRecordMapper.insertStresspileRecord(stresspileRecord);
        return stresspileRecord.getIdGroup().getMachine_id();
    }

    @Override
    public String updateStresspileRecord(StresspileRecord stresspileRecord) {
        return null;
    }

    @Override
    public String deleteStresspileRecord(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getStresspileRecords() {
        return null;
    }
}
