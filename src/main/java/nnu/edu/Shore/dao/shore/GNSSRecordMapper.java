package nnu.edu.Shore.dao.shore;

import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.Machine;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 22:23
 * @Description:
 */

@Repository
public interface GNSSRecordMapper {
    void insertGNSSRecord(GNSSRecord gnssRecord);

    List<GNSSRecord> getAllGNSSRecord();

    Integer getGNSSRecordCount();

    GNSSRecord getLatestRecord();

//    void updateGNSSRecord(Machine gnssRecord);
//
//    void deleteGNSSRecord(String machine_id_nnu);
//
//    void getGNSSRecords();
}
