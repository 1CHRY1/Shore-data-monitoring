package nnu.edu.Shore.service;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:34
 * @Description:
 */
public interface ManometerInfoService {
    String insertManometerInfo(JSONObject jsonObject);

    String updateManometerInfo(ManometerInfo manometerInfo);

    String deleteManometerInfo(String machine_id_nnu);

    List<Machine> getManometerInfos();
}
