package nnu.nari.bankdatamonitor.repository.record;

import nnu.nari.bankdatamonitor.model.record.InclinometerRecord;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/27 15:19
 * @Description:
 */

@Repository
public interface InclinometerRecordRepo {

    void insertInclinometerRecord(InclinometerRecord inclinometerRecord) throws PSQLException;

}
