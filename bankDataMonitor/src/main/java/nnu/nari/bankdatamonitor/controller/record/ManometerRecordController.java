package nnu.nari.bankdatamonitor.controller.record;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.controller.base.MonitorRecordController;
import nnu.nari.bankdatamonitor.model.record.InclinometerRecord;
import nnu.nari.bankdatamonitor.model.record.ManometerRecord;
import nnu.nari.bankdatamonitor.service.record.InclinometerRecordService;
import nnu.nari.bankdatamonitor.service.record.ManometerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("api/v1/manometer/record")
public class ManometerRecordController extends MonitorRecordController<ManometerRecord> {

    @Autowired
    ManometerRecordService manometerRecordService;

    @Override
    @PostMapping("/insert")
    public ResponseEntity<String> insertMonitorRecord(JSONObject data) {
        return ResponseEntity.ok(manometerRecordService.insertMonitorRecord(data));
    }

}
