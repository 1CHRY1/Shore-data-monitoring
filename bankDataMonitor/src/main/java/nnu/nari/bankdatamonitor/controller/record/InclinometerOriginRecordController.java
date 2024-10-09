package nnu.nari.bankdatamonitor.controller.record;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.controller.base.MonitorRecordController;
import nnu.nari.bankdatamonitor.model.record.GnssRecord;
import nnu.nari.bankdatamonitor.model.record.InclinometerOriginRecord;
import nnu.nari.bankdatamonitor.service.record.InclinometerOriginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/10/9 11:33
 * @Description:
 */

@RestController
@RequestMapping("api/v1/inclinometer/origin/record")
public class InclinometerOriginRecordController extends MonitorRecordController<InclinometerOriginRecord> {

    @Autowired
    InclinometerOriginRecordService inclinometerOriginRecordService;

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public ResponseEntity<String> insertInclinometerOriginRecord(@RequestBody JSONObject jsonObject){
        // 插入测斜仪设备记录数据
        return ResponseEntity.ok(inclinometerOriginRecordService.insertMonitorRecord(jsonObject));
    }
}
