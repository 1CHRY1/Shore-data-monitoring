package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import nnu.edu.Shore.common.utils.DatabaseUtil;
import nnu.edu.Shore.common.utils.TimeUtil;
import nnu.edu.Shore.dao.shore.InclinometerOriginRecordMapper;
import nnu.edu.Shore.pojo.InclinometerOriginRecord;
import nnu.edu.Shore.service.InclinometerOriginRecordService;
import nnu.edu.Shore.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/4/16 22:01
 * @Description:
 */
@Service
public class InclinometerOriginRecordServiceImpl implements InclinometerOriginRecordService {

    @Value("${spring.datasource.shore.jdbc-url}")
    String URL;

    @Value("${spring.datasource.shore.username}")
    String USER;

    @Value("${spring.datasource.shore.password}")
    String PASSWORD;

    @Autowired
    InclinometerOriginRecordMapper inclinometerOriginRecordMapper;

    @Autowired
    MachineService machineService;

    @SneakyThrows
    private InclinometerOriginRecord dataProcess(JSONObject jsonObject) {
        // 获取数据时间
        Timestamp measure_time = TimeUtil.String2Timestamp(jsonObject.getString("read_date"));
        // 测斜仪原始数据编号为5
        InclinometerOriginRecord inclinometerRecord;
        String machine_id = jsonObject.getString("device_id");
        JSONObject machineInfo = machineService.getMachineInfo(machine_id,'5');
        if (machineInfo == null) {
            return null;
        }
        String station_id = machineInfo.getString("station_id");
        String machine_id_nnu = machineInfo.getString("machine_id_nnu");
        // 判断请求格式和非空字段是否正确
        try {
            inclinometerRecord = InclinometerOriginRecord.builder()
                    .idGroup(InclinometerOriginRecord.InclinometerOriginRecordIdGroup.builder()
                            .station_id(station_id)
                            .machine_id(machine_id)
                            .machine_id_nnu(machine_id_nnu)
                            .measure_time(measure_time)
                            .build())
                    .angle(jsonObject.getJSONObject("param_value").getDouble("jd"))
                    .in_time(TimeUtil.String2Timestamp(LocalDateTime.now().toString()))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return InclinometerOriginRecord.builder().build();
        }

        return inclinometerRecord;
    }

    @Override
    public String insertInclinometerOriginRecord(JSONObject jsonObject) {
        InclinometerOriginRecord inclinometerOriginRecord = dataProcess(jsonObject);
        if (inclinometerOriginRecord == null) {
            return "设备id不存在";
        }
        try {
            inclinometerOriginRecordMapper.insertInclinometerOriginRecord(inclinometerOriginRecord);
        } catch (DuplicateKeyException e) {
            return "重复的测斜仪设备记录";
        }
        return inclinometerOriginRecord.getIdGroup().getMachine_id();
    }
}
