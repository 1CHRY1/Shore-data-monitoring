package nnu.nari.bankdatamonitor.service.base;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 20:35
 * @Description:
 */

@Service
public class MonitorRecordService<T extends MonitorRecord> implements IMonitorRecordService<T> {

    @Override
    public String insertMonitorRecord(JSONObject data) {
        return null;
    }

    private T dataProcess(JSONObject data) {
        return null;
    }
}
