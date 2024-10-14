package nnu.nari.bankdatamonitor.service.record;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nnu.nari.bankdatamonitor.common.utils.TimeUtil;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;
import nnu.nari.bankdatamonitor.model.info.Machine;
import nnu.nari.bankdatamonitor.model.record.GnssRecord;
import nnu.nari.bankdatamonitor.model.record.InclinometerRecord;
import nnu.nari.bankdatamonitor.model.record.ManometerRecord;
import nnu.nari.bankdatamonitor.repository.record.ManometerRecordRepo;
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
public class ManometerRecordService extends MonitorRecordService<ManometerRecord> {

    @Autowired
    MachineService machineService;

    @Autowired
    ManometerRecordRepo manometerRecordRepo;

    @Override
    public String insertMonitorRecord(JSONObject data) {
        ManometerRecord manometerRecord = dataProccess(data);
        if (manometerRecord == null) {
            return "Manometer设备id不存在";
        }
        if (manometerRecord.getIdGroup()==null) {
            return "ManometerRecord设备记录推送格式不正确";
        }
        try {
            manometerRecordRepo.insertManometerRecord(manometerRecord);
        } catch (DuplicateKeyException | PSQLException e) {
            log.info("重复的Manometer设备记录");
            return "重复的Manometer设备记录";
        }
        String sucStr = "Manometer设备 "+manometerRecord.getIdGroup().getMachine_id()+" 于 "+manometerRecord.getIdGroup().getMeasure_time()+" 插入记录成功！";
        log.info(sucStr);
        return sucStr;
    }

    @SneakyThrows
    private ManometerRecord dataProccess(JSONObject jsonObject){
        // 获取数据时间
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getString("read_date"));
        // 孔隙水压力计编号为3
        ManometerRecord manometerRecord;
        String machine_id = jsonObject.getString("device_id");
        Machine machine = machineService.getMachineInfo(machine_id,'3');
        if (machine.getIdGroup() == null) {
            return null;
        }
        String station_id = machine.getIdGroup().getStation_id();
        String machine_id_nnu = machine.getIdGroup().getMachine_id_nnu();
        // 判断请求格式和非空字段是否正确
        try {
            manometerRecord = ManometerRecord.ManometerRecordBuilder()
                    .idGroup(MonitorRecordIdGroup.MonitorRecordIdGroupBuilder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .measure_time(measure_time)
                            .build())
                    .zx(jsonObject.getJSONObject("param_value").getDouble("zx"))
                    .wd(jsonObject.getJSONObject("param_value").getDouble("wd"))
                    .swgc(jsonObject.getJSONObject("param_value").getDouble("swgc"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return ManometerRecord.ManometerRecordBuilder().build();
        }

        return manometerRecord;
    }

}
