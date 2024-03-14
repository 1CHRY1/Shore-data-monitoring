package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.dao.shore.GNSSRecordMapper;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.GNSSRecord.GNSSRecordIdGroup;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.service.GNSSRecordService;
import nnu.edu.Shore.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 10:39
 * @Description:
 */

@Repository
public class GNSSRecordServiceImpl implements GNSSRecordService {

    @Autowired
    GNSSRecordMapper gnssRecordMapper;

    @Autowired
    MachineService machineService;

    private GNSSRecord dataProcess(JSONObject jsonObject){
        // GNSS编号为1
        GNSSRecord gnssRecord;
        String machine_id = jsonObject.getJSONObject("idGroup").getString("machine_id");
        JSONObject machineInfo = machineService.getMachineInfo(machine_id,'1');
        if (machineInfo == null) {
            return null;
        }
        String station_id = machineInfo.getString("station_id");
        String machine_id_nnu = machineInfo.getString("machine_id_nnu");
        // 判断请求格式和非空字段是否正确
        try {
            gnssRecord = GNSSRecord.builder()
                    .idGroup(
                            GNSSRecordIdGroup.builder()
                                    .station_id(station_id)
                                    .machine_id(machine_id)
                                    .machine_id_nnu(machine_id_nnu)
                                    .measure_time(jsonObject.getJSONObject("idGroup").getString("measure_time"))
                                    .build())
                    .x_move(jsonObject.getDouble("x_move"))
                    .y_move(jsonObject.getDouble("y_move"))
                    .z_move(jsonObject.getDouble("z_move"))
                    .in_time(LocalDateTime.now().toString())
                    .build();
        }
        catch (JSONException | NumberFormatException | NullPointerException e) {
            return GNSSRecord.builder().build();
        }

        return gnssRecord;
    }

    @Override
    public String insertGNSSRecord(JSONObject jsonObject) {
        GNSSRecord gnssRecord = dataProcess(jsonObject);
        if (gnssRecord == null) {
            return "设备id不存在";
        }
        gnssRecordMapper.insertGNSSRecord(gnssRecord);
        return gnssRecord.getIdGroup().getMachine_id();
    }

//    @Override
//    public String updateGNSSRecord(GNSSRecord gnssRecord) {
//        return null;
//    }
//
//    @Override
//    public String deleteGNSSRecord(String gnssRecord) {
//        return null;
//    }
//
//    @Override
//    public List<GNSSRecord> getGNSSRecords() {
//        return null;
//    }
}
