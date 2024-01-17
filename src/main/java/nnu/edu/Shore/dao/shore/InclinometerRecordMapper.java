package nnu.edu.Shore.dao.shore;

import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.InclinometerRecord;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:14
 * @Description:
 */

@Repository
public interface InclinometerRecordMapper {
    void insertInclinometerRecord(InclinometerRecord inclinometerRecord);

    void updateInclinometerRecord(InclinometerRecord inclinometerRecord);

    void deleteInclinometerRecord(String machine_id_nnu);

    void getInclinometerRecords();
}
