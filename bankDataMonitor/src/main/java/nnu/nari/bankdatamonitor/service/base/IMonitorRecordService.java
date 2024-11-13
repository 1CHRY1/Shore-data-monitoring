package nnu.nari.bankdatamonitor.service.base;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 20:37
 * @Description:
 */
public interface IMonitorRecordService<T extends MonitorRecord> {

    String insertMonitorRecord(String device, JSONObject data);

    List<T> getMonitorRecordByTime(String device, String start, String end);

}
