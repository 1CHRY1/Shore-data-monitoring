package nnu.nari.bankdatamonitor.common.utils;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;
import nnu.nari.bankdatamonitor.model.info.Machine;
import nnu.nari.bankdatamonitor.model.record.*;
import nnu.nari.bankdatamonitor.service.info.MachineService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/11/3 11:31
 * @Description:
 */
public class DeviceDataProcess {

    @SneakyThrows
    public static GnssRecord dataProcess_gnss(JSONObject jsonObject){
        MachineService machineService = BeanUtil.getBean(MachineService.class);
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

    @SneakyThrows
    public static InclinometerRecord dataProcess_inclinometer(JSONObject jsonObject){
        MachineService machineService = BeanUtil.getBean(MachineService.class);
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

    @SneakyThrows
    public static ManometerRecord dataProccess_manometer(JSONObject jsonObject){
        MachineService machineService = BeanUtil.getBean(MachineService.class);
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

    @SneakyThrows
    public static StresspileRecord dataProcess_stresspile(JSONObject jsonObject){
        MachineService machineService = BeanUtil.getBean(MachineService.class);
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

    @SneakyThrows
    public static InclinometerOriginRecord dataProcess_inclinometerOrigin(JSONObject jsonObject) {
        MachineService machineService = BeanUtil.getBean(MachineService.class);
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
