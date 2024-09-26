package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.MachineMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.Machine.MachineIdGroup;
import nnu.edu.Shore.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

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
        Machine machine;
        // 判断请求格式和非空字段是否正确
        try {
            machine = Machine.builder()
                    .idGroup(MachineIdGroup.builder()
                            .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                            .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                            .build()
                    )
                    .machine_name(jsonObject.getString("machine_name"))
                    .type(jsonObject.getString("type").toString().charAt(0))
                    .longitude(jsonObject.getDouble("longitude"))
                    .latitude(jsonObject.getDouble("latitude"))
                    .elevation(jsonObject.getDouble("elevation"))
                    .build();
        }
        catch (JSONException | NumberFormatException | NullPointerException e) {
            return Machine.builder().build();
        }
        // 判断其他字段
        String begin_time = (String) jsonObject.getOrDefault("begin_time",null);
        String end_time = (String) jsonObject.getOrDefault("end_time", null);
        machine.setBegin_time(begin_time);
        machine.setEnd_time(end_time);

        String in_time = LocalDateTime.now().toString();
        machine.setOperate_time(in_time);
        machine.setIn_time(in_time);

        //其他信息
        machine.setData_v(1);
        machine.setIn_operator("Admin");
        machine.setNotes("");

        //生成唯一id
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
    public JSONObject getMachineInfo(String machine_id, Character type){
        return machineMapper.getMachineInfo(machine_id, type);
    }

    @Override
    public Integer getMachineCount(Character type) {
        return machineMapper.getMachineCount(type);
    }

//    @Override
//    public String updateMachine(Machine machine) {
//        return null;
//    }
//
//    @Override
//    public String deleteMachine(String machine_id) {
//        return null;
//    }
//
//    @Override
//    public List<Machine> getMachines() {
//        return null;
//    }
}
