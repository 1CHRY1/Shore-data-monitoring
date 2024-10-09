package nnu.nari.bankdatamonitor.service.base;

import com.alibaba.fastjson2.JSONObject;
import nnu.nari.bankdatamonitor.model.base.MonitorInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/27 14:12
 * @Description:
 */
public interface IMonitorInfoService<T extends MonitorInfo> {

    T insertMonitorInfo(JSONObject jsonObject);

}
