package nnu.nari.bankdatamonitor.service.record;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nnu.nari.bankdatamonitor.common.utils.TimeUtil;
import nnu.nari.bankdatamonitor.model.base.MonitorInfoIdGroup;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;
import nnu.nari.bankdatamonitor.model.info.Machine;
import nnu.nari.bankdatamonitor.model.record.GnssRecord;
import nnu.nari.bankdatamonitor.model.record.InclinometerRecord;
import nnu.nari.bankdatamonitor.repository.record.InclinometerRecordRepo;
import nnu.nari.bankdatamonitor.service.base.MonitorRecordService;
import nnu.nari.bankdatamonitor.service.info.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.crypto.Mac;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/27 10:25
 * @Description:
 */

@Service
@Slf4j
public class InclinometerRecordService extends MonitorRecordService<InclinometerRecord> {

    @Autowired
    MachineService machineService;

    @Autowired
    InclinometerRecordRepo inclinometerRecordRepo;

    @Override
    public String insertMonitorRecord(JSONObject data) {
        InclinometerRecord inclinometerRecord = dataProcess(data);
        if (inclinometerRecord == null) {
            return "Inclinometer设备id不存在";
        }
        if (inclinometerRecord.getIdGroup()==null) {
            return "Inclinometer设备记录推送格式不正确";
        }
        try {
            inclinometerRecordRepo.insertInclinometerRecord(inclinometerRecord);
        } catch (DuplicateKeyException e) {
            return "重复的Inclinometer设备记录";
        }
        String sucStr = "Inclinometer设备 "+inclinometerRecord.getIdGroup().getMachine_id()+" 于 "+inclinometerRecord.getIdGroup().getMeasure_time()+" 插入记录成功！";
        log.info(sucStr);
        return sucStr;
    }

    @SneakyThrows
    private InclinometerRecord dataProcess(JSONObject jsonObject){
        // 获取数据时间
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getString("read_date"));
        // 测斜仪编号为4
        InclinometerRecord inclinometerRecord;
        String machine_id = jsonObject.getString("device_id");
        Machine machine = machineService.getMachineInfo(machine_id,'4');
        if (machine.getIdGroup() == null) {
            return null;
        }
        String station_id = machine.getIdGroup().getStation_id();
        String machine_id_nnu = machine.getIdGroup().getMachine_id_nnu();
        // 判断请求格式和非空字段是否正确
        try {
            inclinometerRecord = InclinometerRecord.InclinometerRecordBuilder()
                    .idGroup(MonitorRecordIdGroup.MonitorRecordIdGroupBuilder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .measure_time(measure_time)
                            .build())
                    .top_move(jsonObject.getJSONObject("param_value").getDouble("ding_wy"))
                    .middle_move(jsonObject.getJSONObject("param_value").getDouble("zhong_wy"))
                    .bottom_move(jsonObject.getJSONObject("param_value").getDouble("di_wy"))
                    .top_move_24h(jsonObject.getJSONObject("param_value").getDouble("ding_wy_24h"))
                    .middle_move_24h(jsonObject.getJSONObject("param_value").getDouble("zhong_wy_24h"))
                    .bottom_move_24h(jsonObject.getJSONObject("param_value").getDouble("di_wy_24h"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return InclinometerRecord.InclinometerRecordBuilder().build();
        }

        return inclinometerRecord;
    }

}
