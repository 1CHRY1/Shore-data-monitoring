package nnu.edu.Shore.dao.shore;

import nnu.edu.Shore.pojo.StresspileInfo;
import nnu.edu.Shore.pojo.StresspileRecord;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 15:39
 * @Description:
 */
@Repository
public interface StresspileRecordMapper {
    void insertStresspileRecord(StresspileRecord stresspileRecord);

//    void updateStresspileRecord(StresspileRecord stresspileRecord);
//
//    void deleteStresspileRecord(String machine_id_nnu);
//
//    void getStresspileRecords();
}
