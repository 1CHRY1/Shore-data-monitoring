package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import nnu.edu.Shore.common.utils.DatabaseUtil;
import nnu.edu.Shore.common.utils.TimeUtil;
import nnu.edu.Shore.dao.shore.ManometerInfoMapper;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerInfo;
import nnu.edu.Shore.pojo.ManometerInfo.ManometerInfoIdGroup;
import nnu.edu.Shore.service.MachineService;
import nnu.edu.Shore.service.ManometerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:36
 * @Description:
 */

@Service
public class ManometerInfoServiceImpl implements ManometerInfoService {

    @Autowired
    ManometerInfoMapper manometerInfoMapper;

    @Autowired
    MachineService machineService;

    private ManometerInfo dataProcess(JSONObject jsonObject){
        // 孔隙水压力计编号为3
        ManometerInfo manometerInfo;
        String machine_id = jsonObject.getJSONObject("idGroup").getString("machine_id");
        JSONObject machineInfo = machineService.getMachineInfo(machine_id,'2');
        if (machineInfo == null) {
            return null;
        }
        String station_id = machineInfo.getString("station_id");
        String machine_id_nnu = machineInfo.getString("machine_id_nnu");
        // 判断请求格式和非空字段是否正确
        try {
            manometerInfo = ManometerInfo.builder()
                    .idGroup(ManometerInfoIdGroup.builder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .build())
                    .point_num(jsonObject.getInteger("point_num"))
                    .point1_depth(jsonObject.getDouble("point1_depth"))
                    .in_time(machineInfo.getString("in_time"))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return ManometerInfo.builder().build();
        }

        // 判断其他字段
        Number point2_depth = (Number) jsonObject.getOrDefault("point2_depth",null);
        Number point3_depth = (Number) jsonObject.getOrDefault("point3_depth",null);
        Number point4_depth = (Number) jsonObject.getOrDefault("point4_depth",null);
        Number point5_depth = (Number) jsonObject.getOrDefault("point5_depth",null);
        Number point6_depth = (Number) jsonObject.getOrDefault("point6_depth",null);

        if (point2_depth != null) { manometerInfo.setPoint2_depth(point2_depth.doubleValue()); } else { manometerInfo.setPoint6_depth(null);}
        if (point3_depth != null) { manometerInfo.setPoint3_depth(point3_depth.doubleValue()); } else { manometerInfo.setPoint6_depth(null);}
        if (point4_depth != null) { manometerInfo.setPoint4_depth(point4_depth.doubleValue()); } else { manometerInfo.setPoint6_depth(null);}
        if (point5_depth != null) { manometerInfo.setPoint5_depth(point5_depth.doubleValue()); } else { manometerInfo.setPoint6_depth(null);}
        if (point6_depth != null) { manometerInfo.setPoint6_depth(point6_depth.doubleValue()); } else { manometerInfo.setPoint6_depth(null);}

        // 其他信息
        manometerInfo.setData_v(1);
        manometerInfo.setIn_operator("Admin");
        manometerInfo.setNotes("notes");

        return manometerInfo;
    }

    @Override
    public String insertManometerInfo(JSONObject jsonObject) {
        ManometerInfo manometerInfo = dataProcess(jsonObject);
        if (manometerInfo == null) {
            return "设备id不存在";
        }
        manometerInfoMapper.insertManometerInfo(manometerInfo);
        return manometerInfo.getIdGroup().getMachine_id();
    }

//    @Override
//    public String updateManometerInfo(ManometerInfo manometerInfo) {
//        return null;
//    }
//
//    @Override
//    public String deleteManometerInfo(String machine_id_nnu) {
//        return null;
//    }
//
//    @Override
//    public List<Machine> getManometerInfos() {
//        return null;
//    }
}
