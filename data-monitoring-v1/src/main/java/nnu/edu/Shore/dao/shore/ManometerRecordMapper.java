package nnu.edu.Shore.dao.shore;

import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.ManometerInfo;
import nnu.edu.Shore.pojo.ManometerRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:46
 * @Description:
 */

@Repository
public interface ManometerRecordMapper {
    void insertManometerRecord(ManometerRecord manometerRecord);

    List<ManometerRecord> getAllManometerRecord();

    Integer getManometerRecordCount();

    ManometerRecord getLatestRecord();

//    void updateManometerRecord(ManometerRecord manometerRecord);
//
//    void deleteManometerRecord(String machine_id_nnu);
//
//    void getManometerRecords();
}
