package nnu.nari.bankdatamonitor.service.record;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nnu.nari.bankdatamonitor.common.utils.TimeUtil;
import nnu.nari.bankdatamonitor.model.base.MonitorInfoIdGroup;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;
import nnu.nari.bankdatamonitor.model.info.Machine;
import nnu.nari.bankdatamonitor.model.record.GnssRecord;
import nnu.nari.bankdatamonitor.repository.record.GnssRecordRepo;
import nnu.nari.bankdatamonitor.service.base.MonitorRecordService;
import nnu.nari.bankdatamonitor.service.info.MachineService;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 20:58
 * @Description:
 */

@Service
@Slf4j
public class GnssRecordService extends MonitorRecordService<GnssRecord> {

    @Autowired
    MachineService machineService;

    @Autowired
    GnssRecordRepo gnssRecordRepo;

    @Override
    public String insertMonitorRecord(JSONObject data) {
        GnssRecord gnssRecord = dataProcess(data);
        if (gnssRecord == null) {
            return "Gnss设备id不存在";
        }
        if (gnssRecord.getIdGroup() == null) {
            return "GnssRecord设备记录推送格式不正确";
        }
        try {
            gnssRecordRepo.insertGnssRecord(gnssRecord);
        } catch (DuplicateKeyException | PSQLException e) {
            log.info("重复的Gnss设备记录");
            return "重复的Gnss设备记录";
        }
        String sucStr = "Gnss设备 "+gnssRecord.getIdGroup().getMachine_id()+" 于 "+gnssRecord.getIdGroup().getMeasure_time()+" 插入记录成功！";
        log.info(sucStr);
        return sucStr;
    }

    @SneakyThrows
    private GnssRecord dataProcess(JSONObject jsonObject){
        // 获取数据时间
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getString("read_date"));
        // GNSS编号为1
        GnssRecord gnssRecord;
        String machine_id = jsonObject.getString("device_id");
        Machine machine = machineService.getMachineInfo(machine_id,'1');
        if (machine.getIdGroup() == null) {
            return null;
        }
        String station_id = machine.getIdGroup().getStation_id();
        String machine_id_nnu = machine.getIdGroup().getMachine_id_nnu();
        // 判断请求格式和非空字段是否正确
        try {
            gnssRecord = GnssRecord.GnssRecordBuilder()
                    .idGroup(
                            MonitorRecordIdGroup.MonitorRecordIdGroupBuilder()
                                    .station_id(station_id)
                                    .machine_id(machine_id)
                                    .machine_id_nnu(machine_id_nnu)
                                    .measure_time(measure_time)
                                    .build())
                    .x_move(jsonObject.getJSONObject("param_value").getDouble("x"))
                    .y_move(jsonObject.getJSONObject("param_value").getDouble("y"))
                    .z_move(jsonObject.getJSONObject("param_value").getDouble("z"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        }
        catch (JSONException | NumberFormatException | NullPointerException | ParseException e) {
            return GnssRecord.GnssRecordBuilder().build();
        }
        Number threeD = jsonObject.getJSONObject("param_value").getDouble("3d");
        Number threeDF = jsonObject.getJSONObject("param_value").getDouble("3d_5h");
        if (threeD != null) {gnssRecord.setThreeD(threeD.doubleValue());} else {gnssRecord.setThreeD(null);}
        if (threeDF != null) {gnssRecord.setThreeDF(threeDF.doubleValue());} else {gnssRecord.setThreeDF(null);}
        return gnssRecord;
    }

}
