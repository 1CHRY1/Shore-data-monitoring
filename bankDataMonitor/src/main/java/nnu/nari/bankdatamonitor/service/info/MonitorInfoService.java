package nnu.nari.bankdatamonitor.service.info;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.model.base.MonitorInfo;
import nnu.nari.bankdatamonitor.service.base.IMonitorInfoService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/27 14:12
 * @Description:
 */

@Service
public class MonitorInfoService<T extends MonitorInfo> implements IMonitorInfoService<T> {

    @Override
    public T insertMonitorInfo(JSONObject jsonObject) {
        return null;
    }

}
