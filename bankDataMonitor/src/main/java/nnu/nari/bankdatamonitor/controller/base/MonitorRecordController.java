package nnu.nari.bankdatamonitor.controller.base;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.record.GnssRecord;
import nnu.nari.bankdatamonitor.service.base.MonitorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 16:49
 * @Description:
 */

@RestController
public class MonitorRecordController<T extends MonitorRecord> {

    @Autowired
    MonitorRecordService<T> monitorRecordService;

    public ResponseEntity<String> insertMonitorRecord(@RequestBody JSONObject data) {
        return ResponseEntity.ok(monitorRecordService.insertMonitorRecord(data));
    }

}
