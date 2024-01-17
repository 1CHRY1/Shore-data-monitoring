package nnu.edu.Shore.service;

import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:34
 * @Description:
 */
public interface ManometerInfoService {
    String insertManometerInfo(ManometerInfo manometerInfo);

    String updateManometerInfo(ManometerInfo manometerInfo);

    String deleteManometerInfo(String machine_id_nnu);

    List<Machine> getManometerInfos();
}
