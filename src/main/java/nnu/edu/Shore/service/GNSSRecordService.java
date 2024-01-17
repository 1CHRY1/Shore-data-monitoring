package nnu.edu.Shore.service;

import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.Machine;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 10:38
 * @Description:
 */
public interface GNSSRecordService {
    String insertGNSSRecord(GNSSRecord gnssRecord);

    String updateGNSSRecord(GNSSRecord gnssRecord);

    String deleteGNSSRecord(String gnssRecord);

    List<GNSSRecord> getGNSSRecords();
}
