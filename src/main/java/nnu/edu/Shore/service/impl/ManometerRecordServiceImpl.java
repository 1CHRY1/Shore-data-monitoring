package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.ManometerRecordMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerRecord;
import nnu.edu.Shore.pojo.ManometerRecord.ManometerRecordIdGroup;
import nnu.edu.Shore.service.ManometerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    ManometerRecordMapper manometerRecordMapper;

    private ManometerRecord dataProccess(JSONObject jsonObject){
        ManometerRecord manometerRecord = ManometerRecord.builder()
                .idGroup(ManometerRecordIdGroup.builder()
                        .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                        .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                        .machine_id_nnu(jsonObject.getJSONObject("idGroup").getString("machine_id_nnu"))
                        .measure_time(jsonObject.getJSONObject("idGroup").getString("measure_time"))
                        .build())
                .pressure1(Double.parseDouble(jsonObject.getString("pressure1")))
                .pressure2(Double.parseDouble(jsonObject.getString("pressure2")))
                .pressure3(Double.parseDouble(jsonObject.getString("pressure3")))
                .pressure4(Double.parseDouble(jsonObject.getString("pressure4")))
                .pressure5(Double.parseDouble(jsonObject.getString("pressure5")))
                .pressure6(Double.parseDouble(jsonObject.getString("pressure6")))
                .in_time(jsonObject.getString("in_time"))
                .build();
        return manometerRecord;
    }

    @Override
    public String insertManometerRecord(JSONObject jsonObject) {
        ManometerRecord manometerRecord = dataProccess(jsonObject);
        manometerRecordMapper.insertManometerRecord(manometerRecord);
        return manometerRecord.getIdGroup().getMachine_id();
    }

    @Override
    public String updateManometerRecord(ManometerRecord manometerRecord) {
        return null;
    }

    @Override
    public String deleteManometerRecord(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getManometerRecords() {
        return null;
    }
}
