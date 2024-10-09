package nnu.nari.bankdatamonitor.controller.record;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.controller.base.MonitorRecordController;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.record.GnssRecord;
import nnu.nari.bankdatamonitor.service.record.GnssRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 20:55
 * @Description:
 */

@RestController
@RequestMapping("api/v1/gnss/record")
public class GnssRecordController extends MonitorRecordController<GnssRecord> {

    @Autowired
    GnssRecordService gnssRecordService;

    @Override
    @PostMapping("/insert")
    public ResponseEntity<String> insertMonitorRecord(JSONObject data) {
        return ResponseEntity.ok(gnssRecordService.insertMonitorRecord(data));
    }

}
