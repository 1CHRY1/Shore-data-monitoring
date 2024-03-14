package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.InclinometerInfoMapper;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.InclinometerInfo.InclinometerInfoIdGroup;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.Station;
import nnu.edu.Shore.service.InclinometerInfoService;
import nnu.edu.Shore.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:01
 * @Description:
 */

@Service
public class InclinometerInfoServiceImpl implements InclinometerInfoService {

    @Autowired
    InclinometerInfoMapper inclinometerInfoMapper;

    @Autowired
    MachineService machineService;

    private InclinometerInfo dataProcess(JSONObject jsonObject){
        // 测斜仪编号为4
        InclinometerInfo inclinometerInfo;
        String machine_id = jsonObject.getJSONObject("idGroup").getString("machine_id");
        JSONObject machineInfo = machineService.getMachineInfo(machine_id,'4');
        if (machineInfo == null) {
            return null;
        }
        String station_id = machineInfo.getString("station_id");
        String machine_id_nnu = machineInfo.getString("machine_id_nnu");
        // 判断请求格式和非空字段是否正确
        try {
            inclinometerInfo = InclinometerInfo.builder()
                    .idGroup(InclinometerInfoIdGroup.builder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .build())
                    .point_num(jsonObject.getInteger("point_num"))
                    .point1_depth(jsonObject.getDouble("point1_depth"))
                    .in_time(machineInfo.getString("in_time"))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return InclinometerInfo.builder().build();
        }
        // 判断其他字段
        Number point2_depth = (Number) jsonObject.getOrDefault("point2_depth",null);
        Number point3_depth = (Number) jsonObject.getOrDefault("point3_depth",null);
        Number point4_depth = (Number) jsonObject.getOrDefault("point4_depth",null);
        Number point5_depth = (Number) jsonObject.getOrDefault("point5_depth",null);
        Number point6_depth = (Number) jsonObject.getOrDefault("point6_depth",null);

        if (point2_depth != null) { inclinometerInfo.setPoint2_depth(point2_depth.doubleValue()); } else { inclinometerInfo.setPoint6_depth(null);}
        if (point3_depth != null) { inclinometerInfo.setPoint3_depth(point3_depth.doubleValue()); } else { inclinometerInfo.setPoint6_depth(null);}
        if (point4_depth != null) { inclinometerInfo.setPoint4_depth(point4_depth.doubleValue()); } else { inclinometerInfo.setPoint6_depth(null);}
        if (point5_depth != null) { inclinometerInfo.setPoint5_depth(point5_depth.doubleValue()); } else { inclinometerInfo.setPoint6_depth(null);}
        if (point6_depth != null) { inclinometerInfo.setPoint6_depth(point6_depth.doubleValue()); } else { inclinometerInfo.setPoint6_depth(null);}

        // 其他信息
        inclinometerInfo.setData_v(1);
        inclinometerInfo.setIn_operator("Admin");
        inclinometerInfo.setNotes("");

        return inclinometerInfo;
    }

    @Override
    public String insertInclinometerInfo(JSONObject jsonObject) {
        InclinometerInfo inclinometerInfo = dataProcess(jsonObject);
        if (inclinometerInfo == null) {
            return "设备id不存在";
        }
        inclinometerInfoMapper.insertInclinometerInfo(inclinometerInfo);
        return inclinometerInfo.getIdGroup().getMachine_id();
    }

//    @Override
//    public String updateInclinometerInfo(InclinometerInfo inclinometerInfo) {
//        return null;
//    }
//
//    @Override
//    public String deleteInclinometerInfo(String machine_id_nnu) {
//        return null;
//    }
//
//    @Override
//    public List<Machine> getInclinometerInfos() {
//        return null;
//    }
}
