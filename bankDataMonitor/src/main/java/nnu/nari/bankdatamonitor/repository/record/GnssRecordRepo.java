package nnu.nari.bankdatamonitor.repository.record;

import nnu.nari.bankdatamonitor.model.record.GnssRecord;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/27 15:18
 * @Description:
 */

@Repository("GnssRecordRepo")
public interface GnssRecordRepo {

    void insertGnssRecord(GnssRecord gnssRecord);

}
