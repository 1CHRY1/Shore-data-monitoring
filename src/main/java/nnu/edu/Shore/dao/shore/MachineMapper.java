package nnu.edu.Shore.dao.shore;

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

    void updateMachine(Machine machine);

    void deleteMachine(String machine_id_nnu);

    void getMachines();
}
