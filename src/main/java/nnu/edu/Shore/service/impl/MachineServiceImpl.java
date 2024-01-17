package nnu.edu.Shore.service.impl;

import nnu.edu.Shore.dao.shore.MachineMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Override
    public String insertMachine(Machine machine) {
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
