package nnu.nari.bankdatamonitor.controller.record;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.controller.base.MonitorRecordController;
import nnu.nari.bankdatamonitor.model.record.GnssRecord;
import nnu.nari.bankdatamonitor.model.record.InclinometerRecord;
import nnu.nari.bankdatamonitor.service.record.GnssRecordService;
import nnu.nari.bankdatamonitor.service.record.InclinometerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/27 10:04
 * @Description:
 */

@RestController
@RequestMapping("api/v1/inclinometer/record")
public class InclinometerRecordController extends MonitorRecordController<InclinometerRecord> {

    @Autowired
    InclinometerRecordService inclinometerRecordService;

    @Override
    @PostMapping("/insert")
    public ResponseEntity<String> insertMonitorRecord(JSONObject data) {
        return ResponseEntity.ok(inclinometerRecordService.insertMonitorRecord(data));
    }

}
