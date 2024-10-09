package nnu.nari.bankdatamonitor.service.info;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import nnu.nari.bankdatamonitor.common.utils.TimeUtil;
import nnu.nari.bankdatamonitor.model.base.MonitorInfoIdGroup;
import nnu.nari.bankdatamonitor.model.info.Machine;
import nnu.nari.bankdatamonitor.repository.info.MachineInfoRepo;
import nnu.nari.bankdatamonitor.service.base.MonitorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/27 14:10
 * @Description:
 */

@Service
public class MachineService extends MonitorInfoService<Machine> {

    @Autowired
    MachineInfoRepo machineInfoRepo;

    public Machine getMachineInfo(String machine_id, Character type){
        return machineInfoRepo.getMachineInfo(machine_id, type);
    }

    public String insertMachine(JSONObject data) {
        Machine machine = dataProcess(data);

        return machine.getIdGroup().getMachine_id();
    }

    @SneakyThrows
    private Machine dataProcess(JSONObject jsonObject){
        Machine machine;
        // 判断请求格式和非空字段是否正确
        try {
            machine = Machine.MachineBuilder()
                    .idGroup(MonitorInfoIdGroup.MonitorInfoIdGroupBuilder()
                            .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                            .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                            .build()
                    )
                    .machine_name(jsonObject.getString("machine_name"))
                    .type(jsonObject.getString("type").toString().charAt(0))
                    .longitude(jsonObject.getDouble("longitude"))
                    .latitude(jsonObject.getDouble("latitude"))
                    .elevation(jsonObject.getDouble("elevation"))
                    .build();
        }
        catch (JSONException | NumberFormatException | NullPointerException e) {
            return Machine.MachineBuilder().build();
        }
        // 判断其他字段
        String begin_timeStr = (String) jsonObject.getOrDefault("begin_time",null);
        String end_timeStr = (String) jsonObject.getOrDefault("end_time", null);
        machine.setBegin_time(TimeUtil.String2Timestamp(begin_timeStr));
        machine.setEnd_time(TimeUtil.String2Timestamp(end_timeStr));

        String in_timeStr = LocalDateTime.now().toString();
        machine.setOperate_time(TimeUtil.String2Timestamp(in_timeStr));
        machine.setIn_time(TimeUtil.String2Timestamp(in_timeStr));

        //其他信息
        machine.setData_v(1);
        machine.setIn_operator("Admin");
        machine.setNotes("");

        //生成唯一id
        machine.Onlyid();
        return machine;
    }
}
