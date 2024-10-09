package nnu.nari.bankdatamonitor.service.record;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nnu.nari.bankdatamonitor.common.utils.TimeUtil;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;
import nnu.nari.bankdatamonitor.model.info.Machine;
import nnu.nari.bankdatamonitor.model.record.InclinometerOriginRecord;
import nnu.nari.bankdatamonitor.model.record.InclinometerRecord;
import nnu.nari.bankdatamonitor.repository.record.InclinometerOriginRecordRepo;
import nnu.nari.bankdatamonitor.repository.record.InclinometerRecordRepo;
import nnu.nari.bankdatamonitor.service.base.MonitorRecordService;
import nnu.nari.bankdatamonitor.service.info.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/10/9 14:23
 * @Description:
 */
@Service
@Slf4j
public class InclinometerOriginRecordService extends MonitorRecordService<InclinometerOriginRecord> {

    @Autowired
    MachineService machineService;

    @Autowired
    InclinometerOriginRecordRepo inclinometerOriginRecordRepo;

    @Override
    public String insertMonitorRecord(JSONObject data) {
        InclinometerOriginRecord inclinometerOriginRecord = dataProcess(data);
        if (inclinometerOriginRecord == null) {
            return "Inclinometer_o设备id不存在";
        }
        if (inclinometerOriginRecord.getIdGroup()==null) {
            return "Inclinometer_o设备记录推送格式不正确";
        }
        try {
            inclinometerOriginRecordRepo.insertInclinometerOriginRecord(inclinometerOriginRecord);
        } catch (DuplicateKeyException e) {
            return "重复的Inclinometer_o设备记录";
        }
        String sucStr = "Inclinometer_o 设备 "+inclinometerOriginRecord.getIdGroup().getMachine_id()+" 于 "+inclinometerOriginRecord.getIdGroup().getMeasure_time()+" 插入记录成功！";
        log.info(sucStr);
        return sucStr;
    }

    @SneakyThrows
    private InclinometerOriginRecord dataProcess(JSONObject jsonObject) {
        // 获取数据时间
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getString("read_date"));
        // 测斜仪原始数据编号为5
        InclinometerOriginRecord inclinometerRecord;
        String machine_id = jsonObject.getString("device_id");
        Machine machine = machineService.getMachineInfo(machine_id,'5');
        if (machine.getIdGroup() == null) {
            return null;
        }
        String station_id = machine.getIdGroup().getStation_id();
        String machine_id_nnu = machine.getIdGroup().getMachine_id_nnu();
        // 判断请求格式和非空字段是否正确
        try {
            inclinometerRecord = InclinometerOriginRecord.InclinometerOriginRecordBuilder()
                    .idGroup(MonitorRecordIdGroup.MonitorRecordIdGroupBuilder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .measure_time(measure_time)
                            .build())
                    .angle(jsonObject.getJSONObject("param_value").getDouble("jd"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return InclinometerOriginRecord.InclinometerOriginRecordBuilder().build();
        }

        return inclinometerRecord;
    }

}
