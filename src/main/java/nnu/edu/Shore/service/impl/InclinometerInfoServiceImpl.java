package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.InclinometerInfoMapper;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.InclinometerInfo.InclinometerInfoIdGroup;
import nnu.edu.Shore.pojo.Machine;
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
        InclinometerInfo inclinometerInfo = InclinometerInfo.builder()
                .idGroup(InclinometerInfoIdGroup.builder()
                        .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                        .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                        .machine_id_nnu(jsonObject.getJSONObject("idGroup").getString("machine_id_nnu"))
                        .build())
                .point_num(Integer.parseInt(jsonObject.getString("point_num")))
                .point1_depth(Double.parseDouble(jsonObject.getString("point1_depth")))
                .point2_depth(Double.parseDouble(jsonObject.getString("point2_depth")))
                .point3_depth(Double.parseDouble(jsonObject.getString("point3_depth")))
                .in_time(jsonObject.getString("in_time"))
                .data_v(Integer.parseInt(jsonObject.getString("data_v")))
                .in_operator(jsonObject.getString("in_operator"))
                .notes(jsonObject.getString("notes"))
                .build();
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
