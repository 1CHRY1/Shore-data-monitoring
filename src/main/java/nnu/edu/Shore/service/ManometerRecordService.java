package nnu.edu.Shore.service;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerInfo;
import nnu.edu.Shore.pojo.ManometerRecord;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:47
 * @Description:
 */

public interface ManometerRecordService {
    String insertManometerRecord(JSONObject jsonObject);

    List<ManometerRecord> getAllManometerRecord();

    Integer getManometerRecordCount();

    String getLatestTime();

//    String updateManometerRecord(ManometerRecord manometerRecord);
//
//    String deleteManometerRecord(String machine_id_nnu);
//
//    List<Machine> getManometerRecords();
}
