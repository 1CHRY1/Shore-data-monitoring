package nnu.nari.bankdatamonitor.service.record;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import nnu.nari.bankdatamonitor.common.utils.DeviceDataProcess;
import nnu.nari.bankdatamonitor.common.utils.TimeStringConverter;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.record.*;
import nnu.nari.bankdatamonitor.repository.record.*;
import nnu.nari.bankdatamonitor.service.base.IMonitorRecordService;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 20:35
 * @Description:
 */

@Service
@Slf4j
public class MonitorRecordService<T extends MonitorRecord> implements IMonitorRecordService<T> {

    @Autowired
    MonitorRecordRepo monitorRecordRepo;

    @Override
    public String insertMonitorRecord(String device, JSONObject data) {
        T deviceRecord = null;
        switch (device) {
            case ("gnss") -> { deviceRecord = (T) DeviceDataProcess.dataProcess_gnss(data); }
            case ("inclinometer") -> { deviceRecord = (T) DeviceDataProcess.dataProcess_inclinometer(data); }
            case ("manometer") -> { deviceRecord = (T) DeviceDataProcess.dataProccess_manometer(data); }
            case ("stresspile") -> { deviceRecord = (T) DeviceDataProcess.dataProcess_stresspile(data); }
            case ("inclinometer_o") -> {deviceRecord = (T) DeviceDataProcess.dataProcess_inclinometerOrigin(data); }
        }
        if (deviceRecord == null) {
            return device + "设备id不存在";
        }
        if (deviceRecord.getIdGroup() == null) {
            return device + "Record设备记录推送格式不正确";
        }
        try {
            switch (device) {
                case ("gnss") -> { monitorRecordRepo.insertGnssRecord((GnssRecord) deviceRecord); }
                case ("inclinometer") -> { monitorRecordRepo.insertInclinometerRecord((InclinometerRecord) deviceRecord); }
                case ("manometer") -> { monitorRecordRepo.insertManometerRecord((ManometerRecord) deviceRecord); }
                case ("stresspile") -> { monitorRecordRepo.insertStresspileRecord((StresspileRecord) deviceRecord); }
                case ("inclinometer_o") -> { monitorRecordRepo.insertInclinometerOriginRecord((InclinometerOriginRecord) deviceRecord); }
            }
        } catch (DuplicateKeyException | PSQLException e) {
            log.info("重复的"+device+"设备记录");
            return "重复的"+device+"设备记录";
        }
        String sucStr = device + "设备 "+deviceRecord.getIdGroup().getMachine_id()+" 于 "+deviceRecord.getIdGroup().getMeasure_time()+" 插入记录成功！";
        log.info(sucStr);
        return sucStr;
    }

    @Override
    public List<T> getMonitorRecordByTime(String device, String start, String end) {
        try {
            LocalDateTime startTime = TimeStringConverter.convertStringToLocalDateTime(start);
            LocalDateTime endTime = TimeStringConverter.convertStringToLocalDateTime(end);
            switch (device) {
                case ("gnss") -> { return (List<T>) monitorRecordRepo.getGnssRecord(startTime, endTime); }
                case ("inclinometer") -> { return (List<T>) monitorRecordRepo.getInclinometerRecord(startTime, endTime); }
                case ("manometer") -> { return (List<T>) monitorRecordRepo.getManometerRecord(startTime, endTime); }
                case ("stresspile") -> { return (List<T>) monitorRecordRepo.getStresspileRecord(startTime, endTime); }
                case ("inclinometer_o") -> { return (List<T>) monitorRecordRepo.getInclinometerOriginRecord(startTime, endTime); }
                default -> { return null; }
            }
        } catch (DateTimeException e) {
            return null;
        }
    }

    public List<T> getMonitorRecordBystartTimeAndLimit(String device, String start, String limit) {
        try {
            LocalDateTime startTime = TimeStringConverter.convertStringToLocalDateTime(start);
            Integer limitNum = Integer.parseInt(limit);
            switch (device) {
                case ("gnss") -> { return (List<T>) monitorRecordRepo.getGnssRecordByLimit(startTime, limitNum); }
                case ("inclinometer") -> { return (List<T>) monitorRecordRepo.getInclinometerRecordByLimit(startTime, limitNum); }
                case ("manometer") -> { return (List<T>) monitorRecordRepo.getManometerRecordByLimit(startTime, limitNum); }
                case ("stresspile") -> { return (List<T>) monitorRecordRepo.getStresspileRecordByLimit(startTime, limitNum); }
                case ("inclinometer_o") -> { return (List<T>) monitorRecordRepo.getInclinometerOriginRecordByLimit(startTime, limitNum); }
                default -> { return null; }
            }
        } catch (DateTimeException e) {
            return null;
        }
    }
}
