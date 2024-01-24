package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.StresspileInfoMapper;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.StresspileInfo;
import nnu.edu.Shore.pojo.StresspileInfo.StresspileInfoIdGroup;
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

    private StresspileInfo dataProcess(JSONObject jsonObject){
        StresspileInfo stresspileInfo;
        // 判断请求格式和非空字段是否正确
        try {
            stresspileInfo = StresspileInfo.builder()
                    .idGroup(StresspileInfoIdGroup.builder()
                            .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                            .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                            .machine_id_nnu(jsonObject.getJSONObject("idGroup").getString("machine_id_nnu"))
                            .build())
                    .point_num(Integer.parseInt(jsonObject.getString("point_num")))
                    .point1_depth(Double.parseDouble(jsonObject.getString("point1_depth")))
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
        String in_time = (String) jsonObject.getOrDefault("in_time",null);
        Integer data_v = (Integer) jsonObject.getOrDefault("data_v",null);
        String in_operator = (String) jsonObject.getOrDefault("in_operator",null);
        String notes = (String) jsonObject.getOrDefault("notes",null);
        if (point2_depth != null) { stresspileInfo.setPoint2_depth(point2_depth.doubleValue()); } else { stresspileInfo.setPoint2_depth(null);}
        if (point3_depth != null) { stresspileInfo.setPoint3_depth(point3_depth.doubleValue()); } else { stresspileInfo.setPoint3_depth(null);}
        if (point4_depth != null) { stresspileInfo.setPoint4_depth(point4_depth.doubleValue()); } else { stresspileInfo.setPoint4_depth(null);}
        if (point5_depth != null) { stresspileInfo.setPoint5_depth(point5_depth.doubleValue()); } else { stresspileInfo.setPoint5_depth(null);}
        if (point6_depth != null) { stresspileInfo.setPoint6_depth(point6_depth.doubleValue()); } else { stresspileInfo.setPoint6_depth(null);}
        stresspileInfo.setIn_time(in_time);
        stresspileInfo.setData_v(data_v);
        stresspileInfo.setIn_operator(in_operator);
        stresspileInfo.setNotes(notes);

        return stresspileInfo;
    }

    @Override
    public String insertStresspileInfo(JSONObject jsonObject) {
        StresspileInfo stresspileInfo = dataProcess(jsonObject);
        stresspileInfoMapper.insertStresspileInfo(stresspileInfo);
        return stresspileInfo.getIdGroup().getMachine_id();
    }

    @Override
    public String updateStresspileInfo(StresspileInfo stresspileInfo) {
        return null;
    }

    @Override
    public String deleteStresspileInfo(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getStresspileInfos() {
        return null;
    }
}
