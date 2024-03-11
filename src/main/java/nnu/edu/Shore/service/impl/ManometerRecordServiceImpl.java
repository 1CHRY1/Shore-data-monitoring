package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.ManometerRecordMapper;
import nnu.edu.Shore.pojo.InclinometerInfo;
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
        ManometerRecord manometerRecord;
        // 判断请求格式和非空字段是否正确
        try {
            manometerRecord = ManometerRecord.builder()
                    .idGroup(ManometerRecordIdGroup.builder()
                            .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                            .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                            .machine_id_nnu(jsonObject.getJSONObject("idGroup").getString("machine_id_nnu"))
                            .measure_time(jsonObject.getJSONObject("idGroup").getString("measure_time"))
                            .build())
                    .pressure1(jsonObject.getDouble("pressure1"))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return ManometerRecord.builder().build();
        }
        // 判断其他字段
        Number pressure2 = (Number) jsonObject.getOrDefault("pressure2",null);
        Number pressure3 = (Number) jsonObject.getOrDefault("pressure3",null);
        Number pressure4 = (Number) jsonObject.getOrDefault("pressure4",null);
        Number pressure5 = (Number) jsonObject.getOrDefault("pressure5",null);
        Number pressure6 = (Number) jsonObject.getOrDefault("pressure6",null);
        String in_time = (String) jsonObject.getOrDefault("in_time",null);
        if (pressure2 != null) { manometerRecord.setPressure2(pressure2.doubleValue()); } else { manometerRecord.setPressure2(null);}
        if (pressure3 != null) { manometerRecord.setPressure3(pressure3.doubleValue()); } else { manometerRecord.setPressure3(null);}
        if (pressure4 != null) { manometerRecord.setPressure4(pressure4.doubleValue()); } else { manometerRecord.setPressure4(null);}
        if (pressure5 != null) { manometerRecord.setPressure5(pressure5.doubleValue()); } else { manometerRecord.setPressure5(null);}
        if (pressure6 != null) { manometerRecord.setPressure6(pressure6.doubleValue()); } else { manometerRecord.setPressure6(null);}
        manometerRecord.setIn_time(in_time);
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
