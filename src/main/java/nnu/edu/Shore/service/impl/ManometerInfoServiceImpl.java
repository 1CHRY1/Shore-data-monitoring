package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.ManometerInfoMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerInfo;
import nnu.edu.Shore.pojo.ManometerInfo.ManometerInfoIdGroup;
import nnu.edu.Shore.service.ManometerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private ManometerInfo dataProcess(JSONObject jsonObject){
        ManometerInfo manometerInfo = ManometerInfo.builder()
                .idGroup(ManometerInfoIdGroup.builder()
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
        return manometerInfo;
    }



    @Override
    public String insertManometerInfo(JSONObject jsonObject) {
        ManometerInfo manometerInfo = dataProcess(jsonObject);
        manometerInfoMapper.insertManometerInfo(manometerInfo);
        return manometerInfo.getIdGroup().getMachine_id();
    }

    @Override
    public String updateManometerInfo(ManometerInfo manometerInfo) {
        return null;
    }

    @Override
    public String deleteManometerInfo(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getManometerInfos() {
        return null;
    }
}
