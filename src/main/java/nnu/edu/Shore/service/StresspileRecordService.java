package nnu.edu.Shore.service;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.pojo.StresspileRecord;
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
    String insertStresspileRecord(JSONObject jsonObject);

    List<StresspileRecord> getAllStresspileRecord();

    Integer getStresspileRecordCount();

    String getLatestTime();

//    String updateStresspileRecord(StresspileRecord stresspileRecord);
//
//    String deleteStresspileRecord(String machine_id_nnu);
//
//    List<Machine> getStresspileRecords();
}
