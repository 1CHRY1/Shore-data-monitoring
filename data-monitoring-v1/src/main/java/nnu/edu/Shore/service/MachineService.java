package nnu.edu.Shore.service;

import com.alibaba.fastjson2.JSONObject;
import org.aopalliance.intercept.Interceptor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 16:08
 * @Description:
 */
public interface MachineService {
    String insertMachine(JSONObject jsonObject);

    JSONObject getMachineInfo(String machine_id, Character type);

    Integer getMachineCount(Character type);
//    String updateMachine(Machine machine);
//
//    String deleteMachine(String machine_id_nnu);
//
//    List<Machine> getMachines();
}
