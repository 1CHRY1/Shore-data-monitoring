package nnu.edu.Shore.service;

import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.Machine;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 10:59
 * @Description:
 */
public interface InclinometerInfoService {
    String insertInclinometerInfo(InclinometerInfo inclinometerInfo);

    String updateInclinometerInfo(InclinometerInfo inclinometerInfo);

    String deleteInclinometerInfo(String machine_id_nnu);

    List<Machine> getInclinometerInfos();
}
