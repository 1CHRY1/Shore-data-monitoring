package nnu.edu.Shore.common.config;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import nnu.edu.Shore.common.utils.RequestUtil;
import nnu.edu.Shore.pojo.StresspileInfo;
import nnu.edu.Shore.service.StresspileInfoService;
import nnu.edu.Shore.service.StresspileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/02/07/17:00
 * @Description:
 */
@Component
@Slf4j
public class TimeTask {

    @Autowired
    StresspileInfoService stresspileInfoService;

    @Autowired
    StresspileRecordService stresspileRecordService;

//    @Scheduled(cron = "0/1 * * * * ?")
    public void getStresspileData() {
        // 定时获取应力桩数据
        List<StresspileInfo> stresspileInfoList = stresspileInfoService.getStresspileInfos();
        for (StresspileInfo stresspileInfo : stresspileInfoList) {
            String url = "http://58.222.103.178:7009/webapi/api/Login/Login";
            // 获取设备id
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("Username", "ylz");
            requestBody.put("Password", "ylz@123");
            JSONObject result = RequestUtil.doPost(url, requestBody);
            stresspileRecordService.insertStresspileRecord(result);
        }
    }
}
