package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.GNSSRecordMapper;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.GNSSRecord.GNSSRecordIdGroup;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.service.GNSSRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    private GNSSRecord dataProcess(JSONObject jsonObject){
        GNSSRecord gnssRecord;
        // 判断请求格式和非空字段是否正确
        try {
            gnssRecord = GNSSRecord.builder()
                    .idGroup(
                            GNSSRecordIdGroup.builder()
                                    .station_id(jsonObject.getJSONObject("idGroup").getString("station_id"))
                                    .machine_id(jsonObject.getJSONObject("idGroup").getString("machine_id"))
                                    .machine_id_nnu(jsonObject.getJSONObject("idGroup").getString("machine_id_nnu"))
                                    .measure_time(jsonObject.getJSONObject("idGroup").getString("measure_time"))
                                    .build())
                    .x_move(jsonObject.getDouble("x_move"))
                    .y_move(jsonObject.getDouble("y_move"))
                    .z_move(jsonObject.getDouble("z_move"))
                    .build();
        }
        catch (JSONException | NumberFormatException | NullPointerException e) {
            return GNSSRecord.builder().build();
        }
        // 判断其他字段
        String in_time = (String) jsonObject.getOrDefault("in_time", null);
        gnssRecord.setIn_time(in_time);
        return gnssRecord;
    }

    @Override
    public String insertGNSSRecord(JSONObject jsonObject) {
        GNSSRecord gnssRecord = dataProcess(jsonObject);
        gnssRecordMapper.insertGNSSRecord(gnssRecord);
        return gnssRecord.getIdGroup().getMachine_id();
    }

    @Override
    public String updateGNSSRecord(GNSSRecord gnssRecord) {
        return null;
    }

    @Override
    public String deleteGNSSRecord(String gnssRecord) {
        return null;
    }

    @Override
    public List<GNSSRecord> getGNSSRecords() {
        return null;
    }
}
