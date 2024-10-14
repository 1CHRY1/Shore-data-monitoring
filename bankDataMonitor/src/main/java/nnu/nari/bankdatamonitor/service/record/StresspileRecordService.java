package nnu.nari.bankdatamonitor.service.record;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nnu.nari.bankdatamonitor.common.utils.TimeUtil;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;
import nnu.nari.bankdatamonitor.model.info.Machine;
import nnu.nari.bankdatamonitor.model.record.ManometerRecord;
import nnu.nari.bankdatamonitor.model.record.StresspileRecord;
import nnu.nari.bankdatamonitor.repository.record.StresspileRecordRepo;
import nnu.nari.bankdatamonitor.service.base.MonitorRecordService;
import nnu.nari.bankdatamonitor.service.info.MachineService;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

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
public class StresspileRecordService extends MonitorRecordService<StresspileRecord> {

    @Autowired
    MachineService machineService;

    @Autowired
    StresspileRecordRepo stresspileRecordRepo;

    @Override
    public String insertMonitorRecord(JSONObject data) {
        StresspileRecord stresspileRecord = dataProcess(data);
        if (stresspileRecord == null) {
            return "Stresspile设备id不存在";
        }
        if (stresspileRecord.getIdGroup() == null) {
            return "StresspileRecord设备记录推送格式不正确";
        }
        try {
            stresspileRecordRepo.insertStresspileRecord(stresspileRecord);
        } catch (DuplicateKeyException | PSQLException e) {
            log.info("重复的Stresspile设备记录");
            return "重复的Stresspile设备记录";
        }
        String sucStr = "Stresspile设备 " + stresspileRecord.getIdGroup().getMachine_id() + " 于 " + stresspileRecord.getIdGroup().getMeasure_time() + " 插入记录成功！";
        log.info(sucStr);
        return sucStr;
    }

    @SneakyThrows
    private StresspileRecord dataProcess(JSONObject jsonObject){
        // 获取数据时间
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getString("read_date"));
        // 应力桩编号为2
        StresspileRecord stresspileRecord;
        String machine_id = jsonObject.getString("device_id");
        Machine machine = machineService.getMachineInfo(machine_id,'2');
        if (machine.getIdGroup() == null) {
            return null;
        }
        String station_id = machine.getIdGroup().getStation_id();
        String machine_id_nnu = machine.getIdGroup().getMachine_id_nnu();
        // 判断请求格式和非空字段是否正确
        try {
            stresspileRecord = StresspileRecord.StresspileRecordBuilder()
                    .idGroup(MonitorRecordIdGroup.MonitorRecordIdGroupBuilder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .measure_time(measure_time)
                            .build())
                    .top_angle(jsonObject.getJSONObject("param_value").getDouble("top_angle"))
                    .middle_angle(jsonObject.getJSONObject("param_value").getDouble("middle_angle"))
                    .bottom_angle(jsonObject.getJSONObject("param_value").getDouble("bottom_angle"))
                    .top_power(jsonObject.getJSONObject("param_value").getDouble("top_power"))
                    .middle_power(jsonObject.getJSONObject("param_value").getDouble("middle_power"))
                    .bottom_power(jsonObject.getJSONObject("param_value").getDouble("bottom_power"))
                    .top_change(jsonObject.getJSONObject("param_value").getDouble("top_change"))
                    .middle_change(jsonObject.getJSONObject("param_value").getDouble("middle_change"))
                    .bottom_change(jsonObject.getJSONObject("param_value").getDouble("bottom_change"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return StresspileRecord.StresspileRecordBuilder().build();
        }

        return stresspileRecord;
    }

}
