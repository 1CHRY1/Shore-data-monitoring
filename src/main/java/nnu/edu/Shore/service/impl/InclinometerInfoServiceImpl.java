package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.InclinometerInfoMapper;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.InclinometerInfo.InclinometerInfoIdGroup;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.Station;
import nnu.edu.Shore.service.InclinometerInfoService;
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

    private InclinometerInfo dataProcess(JSONObject jsonObject){
        InclinometerInfo inclinometerInfo;
        // 判断请求格式和非空字段是否正确
        try {
            inclinometerInfo = InclinometerInfo.builder()
                    .idGroup(InclinometerInfoIdGroup.builder()
                            .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                            .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                            .machine_id_nnu(jsonObject.getJSONObject("idGroup").getString("machine_id_nnu"))
                            .build())
                    .point_num(Integer.parseInt(jsonObject.getString("point_num")))
                    .point1_depth(Double.parseDouble(jsonObject.getString("point1_depth")))
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
        String in_time = (String) jsonObject.getOrDefault("in_time",null);
        Integer data_v = (Integer) jsonObject.getOrDefault("data_v",null);
        String in_operator = (String) jsonObject.getOrDefault("in_operator",null);
        String notes = (String) jsonObject.getOrDefault("notes",null);
        if (point2_depth != null) { inclinometerInfo.setPoint2_depth(point2_depth.doubleValue()); } else { inclinometerInfo.setPoint6_depth(null);}
        if (point3_depth != null) { inclinometerInfo.setPoint2_depth(point3_depth.doubleValue()); } else { inclinometerInfo.setPoint6_depth(null);}
        if (point4_depth != null) { inclinometerInfo.setPoint2_depth(point4_depth.doubleValue()); } else { inclinometerInfo.setPoint6_depth(null);}
        if (point5_depth != null) { inclinometerInfo.setPoint2_depth(point5_depth.doubleValue()); } else { inclinometerInfo.setPoint6_depth(null);}
        if (point6_depth != null) { inclinometerInfo.setPoint2_depth(point6_depth.doubleValue()); } else { inclinometerInfo.setPoint6_depth(null);}
        inclinometerInfo.setIn_time(in_time);
        inclinometerInfo.setData_v(data_v);
        inclinometerInfo.setIn_operator(in_operator);
        inclinometerInfo.setNotes(notes);

        return inclinometerInfo;
    }

    @Override
    public String insertInclinometerInfo(JSONObject jsonObject) {
        InclinometerInfo inclinometerInfo = dataProcess(jsonObject);
        inclinometerInfoMapper.insertInclinometerInfo(inclinometerInfo);
        return inclinometerInfo.getIdGroup().getMachine_id();
    }

    @Override
    public String updateInclinometerInfo(InclinometerInfo inclinometerInfo) {
        return null;
    }

    @Override
    public String deleteInclinometerInfo(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getInclinometerInfos() {
        return null;
    }
}
