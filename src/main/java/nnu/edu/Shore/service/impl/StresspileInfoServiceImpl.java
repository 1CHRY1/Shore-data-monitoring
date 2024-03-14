package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.StresspileInfoMapper;
import nnu.edu.Shore.pojo.InclinometerInfo;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.StresspileInfoMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.StresspileInfo;
import nnu.edu.Shore.pojo.StresspileInfo.StresspileInfoIdGroup;
import nnu.edu.Shore.service.MachineService;
import nnu.edu.Shore.service.StresspileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 12:01
 * @Description:
 */

@Service
public class StresspileInfoServiceImpl implements StresspileInfoService {

    @Autowired
    StresspileInfoMapper stresspileInfoMapper;

    @Autowired
    MachineService machineService;

    private StresspileInfo dataProcess(JSONObject jsonObject){
        // 应力桩编号为2
        StresspileInfo stresspileInfo;
        String machine_id = jsonObject.getJSONObject("idGroup").getString("machine_id");
        JSONObject machineInfo = machineService.getMachineInfo(machine_id,'2');
        if (machineInfo == null) {
            return null;
        }
        String station_id = machineInfo.getString("station_id");
        String machine_id_nnu = machineInfo.getString("machine_id_nnu");
        // 判断请求格式和非空字段是否正确
        try {
            stresspileInfo = StresspileInfo.builder()
                    .idGroup(StresspileInfoIdGroup.builder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .build())
                    .point_num(jsonObject.getInteger("point_num"))
                    .point1_depth(jsonObject.getDouble("point1_depth"))
                    .in_time(machineInfo.getString("in_time"))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return StresspileInfo.builder().build();
        }
        // 判断其他字段
        Number point2_depth = (Number) jsonObject.getOrDefault("point2_depth",null);
        Number point3_depth = (Number) jsonObject.getOrDefault("point3_depth",null);
        Number point4_depth = (Number) jsonObject.getOrDefault("point4_depth",null);
        Number point5_depth = (Number) jsonObject.getOrDefault("point5_depth",null);
        Number point6_depth = (Number) jsonObject.getOrDefault("point6_depth",null);

        if (point2_depth != null) { stresspileInfo.setPoint2_depth(point2_depth.doubleValue()); } else { stresspileInfo.setPoint2_depth(null);}
        if (point3_depth != null) { stresspileInfo.setPoint3_depth(point3_depth.doubleValue()); } else { stresspileInfo.setPoint3_depth(null);}
        if (point4_depth != null) { stresspileInfo.setPoint4_depth(point4_depth.doubleValue()); } else { stresspileInfo.setPoint4_depth(null);}
        if (point5_depth != null) { stresspileInfo.setPoint5_depth(point5_depth.doubleValue()); } else { stresspileInfo.setPoint5_depth(null);}
        if (point6_depth != null) { stresspileInfo.setPoint6_depth(point6_depth.doubleValue()); } else { stresspileInfo.setPoint6_depth(null);}

        // 其他信息
        stresspileInfo.setData_v(1);
        stresspileInfo.setIn_operator("Admin");
        stresspileInfo.setNotes("");
        return stresspileInfo;
    }

    @Override
    public String insertStresspileInfo(JSONObject jsonObject) {
        StresspileInfo stresspileInfo = dataProcess(jsonObject);
        if (stresspileInfo == null) {
            return "设备id不存在";
        }
        stresspileInfoMapper.insertStresspileInfo(stresspileInfo);
        return stresspileInfo.getIdGroup().getMachine_id();
    }

//    @Override
//    public String updateStresspileInfo(StresspileInfo stresspileInfo) {
//        return null;
//    }
//
//    @Override
//    public String deleteStresspileInfo(String machine_id_nnu) {
//        return null;
//    }
//
//    @Override
//    public List<Machine> getStresspileInfos() {
//        return null;
//    }
}
