package nnu.nari.bankdatamonitor.controller.record;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.controller.base.MonitorRecordController;
import nnu.nari.bankdatamonitor.model.record.ManometerRecord;
import nnu.nari.bankdatamonitor.model.record.StresspileRecord;
import nnu.nari.bankdatamonitor.service.record.ManometerRecordService;
import nnu.nari.bankdatamonitor.service.record.StresspileRecordService;
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
@RequestMapping("api/v1/stresspile/record")
public class StresspileRecordController extends MonitorRecordController<StresspileRecord> {

    @Autowired
    StresspileRecordService stresspileRecordService;

    @Override
    @PostMapping("/insert")
    public ResponseEntity<String> insertMonitorRecord(JSONObject data) {
        return ResponseEntity.ok(stresspileRecordService.insertMonitorRecord(data));
    }

}
