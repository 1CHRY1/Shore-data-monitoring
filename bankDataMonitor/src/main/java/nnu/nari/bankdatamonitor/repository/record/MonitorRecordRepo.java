package nnu.nari.bankdatamonitor.repository.record;

import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.record.*;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/11/11 21:57
 * @Description:
 */
@Repository("MonitorRecordRepo")
public interface MonitorRecordRepo {

    void insertGnssRecord(GnssRecord gnssRecord) throws PSQLException;

    void insertInclinometerRecord(InclinometerRecord inclinometerRecord) throws PSQLException;

    void insertManometerRecord(ManometerRecord manometerRecord) throws PSQLException;

    void insertStresspileRecord(StresspileRecord stresspileRecord) throws PSQLException;

    void insertInclinometerOriginRecord(InclinometerOriginRecord inclinometerOriginRecord) throws PSQLException;

    List<MonitorRecord> getGnssRecord(LocalDateTime startTime, LocalDateTime endTime);
    List<MonitorRecord> getGnssRecordByLimit(LocalDateTime startTime, Integer limit);

    List<MonitorRecord> getInclinometerRecord(LocalDateTime startTime, LocalDateTime endTime);
    List<MonitorRecord> getInclinometerRecordByLimit(LocalDateTime startTime, Integer limit);

    List<MonitorRecord> getManometerRecord(LocalDateTime startTime, LocalDateTime endTime);
    List<MonitorRecord> getManometerRecordByLimit(LocalDateTime startTime, Integer limit);

    List<MonitorRecord> getStresspileRecord(LocalDateTime startTime, LocalDateTime endTime);
    List<MonitorRecord> getStresspileRecordByLimit(LocalDateTime startTime, Integer limit);

    List<MonitorRecord> getInclinometerOriginRecord(LocalDateTime startTime, LocalDateTime endTime);
    List<MonitorRecord> getInclinometerOriginRecordByLimit(LocalDateTime startTime, Integer limit);

}