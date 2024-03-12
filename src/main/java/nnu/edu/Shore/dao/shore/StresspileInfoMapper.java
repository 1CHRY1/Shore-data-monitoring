package nnu.edu.Shore.dao.shore;

import nnu.edu.Shore.pojo.StresspileInfo;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:59
 * @Description:
 */

@Repository
public interface StresspileInfoMapper {
    void insertStresspileInfo(StresspileInfo stresspileInfo);

//    void updateStresspileInfo(StresspileInfo stresspileInfo);
//
//    void deleteStresspileInfo(String machine_id_nnu);
//
//    void getStresspileInfos();
}
