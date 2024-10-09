package nnu.nari.bankdatamonitor.repository.info;

import nnu.nari.bankdatamonitor.model.info.Machine;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/28 18:04
 * @Description:
 */
public interface MachineInfoRepo {

    Machine getMachineInfo(String machine_id, Character type);

}
