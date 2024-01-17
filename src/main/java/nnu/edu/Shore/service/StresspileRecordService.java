package nnu.edu.Shore.service;

import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.StresspileInfo;
import nnu.edu.Shore.pojo.StresspileRecord;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 15:40
 * @Description:
 */
public interface StresspileRecordService {
    String insertStresspileRecord(StresspileRecord stresspileRecord);

    String updateStresspileRecord(StresspileRecord stresspileRecord);

    String deleteStresspileRecord(String machine_id_nnu);

    List<Machine> getStresspileRecords();
}
