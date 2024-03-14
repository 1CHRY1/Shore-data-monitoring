package nnu.edu.Shore.dao.shore;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.pojo.Machine;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 16:09
 * @Description:
 */

@Repository
public interface MachineMapper {
    void insertMachine(Machine machine);

    JSONObject getMachineInfo(String machine_id, Character type);
//    void updateMachine(Machine machine);
//
//    void deleteMachine(String machine_id_nnu);
//
//    void getMachines();
}
