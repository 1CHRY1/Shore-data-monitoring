package nnu.edu.Shore.service;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.InclinometerRecord;
import nnu.edu.Shore.pojo.Machine;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:16
 * @Description:
 */
public interface InclinometerRecordService {
    String insertInclinometerRecord(JSONObject jsonObject);

//    String updateInclinometerRecord(InclinometerRecord inclinometerRecord);
//
//    String deleteInclinometerRecord(String machine_id_nnu);
//
//    List<Machine> getInclinometerRecords();
}
