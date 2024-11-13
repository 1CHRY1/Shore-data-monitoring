package nnu.nari.bankdatamonitor.controller.record;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.service.record.MonitorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 16:49
 * @Description:
 */

@RestController
@RequestMapping("api/v1")
public class MonitorRecordController<T extends MonitorRecord> {

    @Autowired
    MonitorRecordService<T> monitorRecordService;

    @PostMapping("/{device}/record/insert")
    public ResponseEntity<String> insertMonitorRecord(@PathVariable("device") String device, @RequestBody JSONObject data) {
        return ResponseEntity.ok(monitorRecordService.insertMonitorRecord(device, data));
    }

    @PostMapping("/inclinometer/origin/record/insert")
    public ResponseEntity<String> insertInclinometerOriginRecord(@RequestBody JSONObject data) {
        return ResponseEntity.ok(monitorRecordService.insertMonitorRecord("inclinometer_o", data));
    }

    @GetMapping("/{device}/record/{start}/{end}")
    public ResponseEntity<List<T>> getMonitorRecordByTime(@PathVariable("device") String device, @PathVariable String start, @PathVariable String end) {
        return ResponseEntity.ok(monitorRecordService.getMonitorRecordByTime(device, start, end));
    }

    @GetMapping("/{device}/record/limit/{start}/{limit}")
    public ResponseEntity<List<T>> getMonitorRecordByStartTimeAndLimit(@PathVariable("device") String device, @PathVariable String start, @PathVariable String limit) {
        return ResponseEntity.ok(monitorRecordService.getMonitorRecordBystartTimeAndLimit(device, start, limit));
    }

}
