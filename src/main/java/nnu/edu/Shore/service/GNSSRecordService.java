package nnu.edu.Shore.service;

import com.alibaba.fastjson2.JSONObject;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.Machine;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 10:38
 * @Description:
 */
public interface GNSSRecordService {
    String insertGNSSRecord(JSONObject jsonObject);

    List<GNSSRecord> getAllGNSSRecord();

    Integer getGNSSRecordCount();

    String getLatestTime();

//    String updateGNSSRecord(GNSSRecord gnssRecord);
//
//    String deleteGNSSRecord(String gnssRecord);
//
//    List<GNSSRecord> getGNSSRecords();
}
