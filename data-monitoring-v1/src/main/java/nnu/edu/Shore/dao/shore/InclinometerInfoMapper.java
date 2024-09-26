package nnu.edu.Shore.dao.shore;

import nnu.edu.Shore.pojo.InclinometerInfo;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 10:53
 * @Description:
 */

@Repository
public interface InclinometerInfoMapper {
    void insertInclinometerInfo(InclinometerInfo inclinometerInfo);

//    void updateInclinometerInfo(InclinometerInfo inclinometerInfo);
//
//    void deleteInclinometerInfo(String machine_id_nnu);
//
//    void getInclinometerInfos();
}
