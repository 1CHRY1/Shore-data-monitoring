package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.StresspileRecordMapper;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerRecord;
import nnu.edu.Shore.pojo.StresspileRecord;
import nnu.edu.Shore.pojo.StresspileRecord.StresspileRecordIdGroup;
import nnu.edu.Shore.service.MachineService;
import nnu.edu.Shore.service.StresspileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    MachineService machineService;

    private StresspileRecord dataProcess(JSONObject jsonObject){
        // 应力桩编号为2
        StresspileRecord stresspileRecord;
        String machine_id = jsonObject.getJSONObject("idGroup").getString("machine_id");
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
                            .measure_time(jsonObject.getJSONObject("idGroup").getString("measure_time"))
                            .build())
                    .horizontal1(jsonObject.getDouble("horizontal1"))
                    .horizontal_stress1(jsonObject.getDouble("horizontal_stress1"))
                    .vertical_stress1(jsonObject.getDouble("vertical_stress1"))
                    .in_time(LocalDateTime.now().toString())
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return StresspileRecord.builder().build();
        }
        // 判断其他字段
        Number horizontal2 = (Number) jsonObject.getOrDefault("horizontal2",null);
        Number horizontal_stress2 = (Number) jsonObject.getOrDefault("horizontal_stress2",null);
        Number vertical_stress2 = (Number) jsonObject.getOrDefault("vertical_stress2",null);
        Number horizontal3 = (Number) jsonObject.getOrDefault("horizontal3",null);
        Number horizontal_stress3 = (Number) jsonObject.getOrDefault("horizontal_stress3",null);
        Number vertical_stress3 = (Number) jsonObject.getOrDefault("vertical_stress3",null);
        Number horizontal4 = (Number) jsonObject.getOrDefault("horizontal4",null);
        Number horizontal_stress4 = (Number) jsonObject.getOrDefault("horizontal_stress4",null);
        Number vertical_stress4 = (Number) jsonObject.getOrDefault("vertical_stress4",null);
        Number horizontal5 = (Number) jsonObject.getOrDefault("horizontal5",null);
        Number horizontal_stress5 = (Number) jsonObject.getOrDefault("horizontal_stress5",null);
        Number vertical_stress5 = (Number) jsonObject.getOrDefault("vertical_stress5",null);
        Number horizontal6 = (Number) jsonObject.getOrDefault("horizontal6",null);
        Number horizontal_stress6 = (Number) jsonObject.getOrDefault("horizontal_stress6",null);
        Number vertical_stress6 = (Number) jsonObject.getOrDefault("vertical_stress6",null);
        if (horizontal2 != null) { stresspileRecord.setHorizontal2(horizontal2.doubleValue()); } else { stresspileRecord.setHorizontal2(null);}
        if (horizontal_stress2 != null) { stresspileRecord.setHorizontal_stress2(horizontal_stress2.doubleValue()); } else { stresspileRecord.setHorizontal2(null);}
        if (vertical_stress2 != null) { stresspileRecord.setVertical_stress2(vertical_stress2.doubleValue()); } else { stresspileRecord.setVertical_stress2(null);}
        if (horizontal3 != null) { stresspileRecord.setHorizontal3(horizontal3.doubleValue()); } else { stresspileRecord.setHorizontal3(null);}
        if (horizontal_stress3 != null) { stresspileRecord.setHorizontal_stress3(horizontal_stress3.doubleValue()); } else { stresspileRecord.setHorizontal3(null);}
        if (vertical_stress3 != null) { stresspileRecord.setVertical_stress3(vertical_stress3.doubleValue()); } else { stresspileRecord.setVertical_stress3(null);}
        if (horizontal4 != null) { stresspileRecord.setHorizontal4(horizontal4.doubleValue()); } else { stresspileRecord.setHorizontal4(null);}
        if (horizontal_stress4 != null) { stresspileRecord.setHorizontal_stress4(horizontal_stress4.doubleValue()); } else { stresspileRecord.setHorizontal4(null);}
        if (vertical_stress4 != null) { stresspileRecord.setVertical_stress4(vertical_stress4.doubleValue()); } else { stresspileRecord.setVertical_stress4(null);}
        if (horizontal5 != null) { stresspileRecord.setHorizontal5(horizontal5.doubleValue()); } else { stresspileRecord.setHorizontal5(null);}
        if (horizontal_stress5 != null) { stresspileRecord.setHorizontal_stress5(horizontal_stress5.doubleValue()); } else { stresspileRecord.setHorizontal5(null);}
        if (vertical_stress5 != null) { stresspileRecord.setVertical_stress5(vertical_stress5.doubleValue()); } else { stresspileRecord.setVertical_stress5(null);}
        if (horizontal6 != null) { stresspileRecord.setHorizontal6(horizontal6.doubleValue()); } else { stresspileRecord.setHorizontal6(null);}
        if (horizontal_stress6 != null) { stresspileRecord.setHorizontal_stress6(horizontal_stress6.doubleValue()); } else { stresspileRecord.setHorizontal6(null);}
        if (vertical_stress6 != null) { stresspileRecord.setVertical_stress6(vertical_stress6.doubleValue()); } else { stresspileRecord.setVertical_stress6(null);}

        return stresspileRecord;
    }

    @Override
    public String insertStresspileRecord(JSONObject jsonObject) {
        StresspileRecord stresspileRecord = dataProcess(jsonObject);
        if (stresspileRecord == null) {
            return "设备id不存在";
        }
        stresspileRecordMapper.insertStresspileRecord(stresspileRecord);
        return stresspileRecord.getIdGroup().getMachine_id();
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
