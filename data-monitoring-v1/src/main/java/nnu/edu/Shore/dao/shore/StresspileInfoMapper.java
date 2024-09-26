package nnu.edu.Shore.dao.shore;

import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.StresspileInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    List<StresspileInfo> getStresspileInfos();

//    void updateStresspileInfo(StresspileInfo stresspileInfo);
//
//    void deleteStresspileInfo(String machine_id_nnu);
//
}
