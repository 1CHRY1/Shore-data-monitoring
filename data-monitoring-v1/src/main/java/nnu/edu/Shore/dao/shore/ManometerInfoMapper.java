package nnu.edu.Shore.dao.shore;

import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerInfo;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:32
 * @Description:
 */

@Repository
public interface ManometerInfoMapper {
    void insertManometerInfo(ManometerInfo manometerInfo);

//    void updateManometerInfo(ManometerInfo manometerInfo);
//
//    void deleteManometerInfo(String machine_id_nnu);
//
//    void getManometerInfos();
}
