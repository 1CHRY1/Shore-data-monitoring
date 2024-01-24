package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.StresspileInfoMapper;
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
        StresspileInfo stresspileInfo = StresspileInfo.builder()
                .idGroup(StresspileInfoIdGroup.builder()
                        .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                        .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                        .machine_id_nnu(jsonObject.getJSONObject("idGroup").getString("machine_id_nnu"))
                        .build())
                .point_num(Integer.parseInt(jsonObject.getString("point_num")))
                .point1_depth(Double.parseDouble(jsonObject.getString("point1_depth")))
                .point2_depth(Double.parseDouble(jsonObject.getString("point2_depth")))
                .point3_depth(Double.parseDouble(jsonObject.getString("point3_depth")))
                .point4_depth(Double.parseDouble(jsonObject.getString("point4_depth")))
                .point5_depth(Double.parseDouble(jsonObject.getString("point5_depth")))
                .point6_depth(Double.parseDouble(jsonObject.getString("point6_depth")))
                .in_time(jsonObject.getString("in_time"))
                .data_v(Integer.parseInt(jsonObject.getString("data_v")))
                .in_operator(jsonObject.getString("in_operator"))
                .notes(jsonObject.getString("notes"))
                .build();
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
