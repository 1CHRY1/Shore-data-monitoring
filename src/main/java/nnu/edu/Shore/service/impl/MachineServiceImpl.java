package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.MachineMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.Machine.MachineIdGroup;
import nnu.edu.Shore.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 16:17
 * @Description:
 */

@Repository
public class MachineServiceImpl implements MachineService {

    @Autowired
    MachineMapper machineMapper;

    private Machine dataProcess(JSONObject jsonObject){
        // 数据处理过程
        Machine machine = Machine.builder()
                .idGroup(MachineIdGroup.builder()
                        .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                        .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                        .build()
                )
                .machine_name(jsonObject.getJSONObject("idGroup").getString("machine_name"))
                .begin_time(jsonObject.getString("begin_time"))
                .end_time(jsonObject.getString("end_time"))
                .type(jsonObject.getString("type").toString().charAt(0))
                .longitude(Double.parseDouble(jsonObject.getString("longitude")))
                .latitude(Double.parseDouble(jsonObject.getString("latitude")))
                .elevation(Double.parseDouble(jsonObject.getString("elevation")))
                .in_time(jsonObject.getString("in_time"))
                .operate_time(jsonObject.getString("operate_time"))
                .data_v(Integer.parseInt(jsonObject.getString("data_v")))
                .in_operator(jsonObject.getString("in_operator"))
                .notes(jsonObject.getString("notes"))
                .build();
        machine.Onlyid();
        return machine;
    }

    @Override
    public String insertMachine(JSONObject jsonObject) {
        Machine machine = dataProcess(jsonObject);
        machineMapper.insertMachine(machine);
        return machine.getIdGroup().getMachine_id();
    }

    @Override
    public String updateMachine(Machine machine) {
        return null;
    }

    @Override
    public String deleteMachine(String machine_id) {
        return null;
    }

    @Override
    public List<Machine> getMachines() {
        return null;
    }
}
