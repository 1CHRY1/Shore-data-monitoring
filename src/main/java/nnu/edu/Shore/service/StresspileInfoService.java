package nnu.edu.Shore.service;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.StresspileInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 12:00
 * @Description:
 */
public interface StresspileInfoService {
    String insertStresspileInfo(JSONObject jsonObject);

//    String updateStresspileInfo(StresspileInfo stresspileInfo);
//
//    String deleteStresspileInfo(String machine_id_nnu);
//
    List<StresspileInfo> getStresspileInfos();
}
