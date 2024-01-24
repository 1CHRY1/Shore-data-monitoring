package nnu.edu.Shore.service;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.Station;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 16:08
 * @Description:
 */
public interface MachineService {
    String insertMachine(JSONObject jsonObject);

    String updateMachine(Machine machine);

    String deleteMachine(String machine_id_nnu);

    List<Machine> getMachines();
}
