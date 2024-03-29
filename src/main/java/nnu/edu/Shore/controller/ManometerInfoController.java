package nnu.edu.Shore.controller;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.ManometerInfo;
import nnu.edu.Shore.service.GNSSRecordService;
import nnu.edu.Shore.service.ManometerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:37
 * @Description:
 */

@RestController
@RequestMapping("api/v1/manometer/info")
public class ManometerInfoController {

    @Autowired
    ManometerInfoService manometerInfoService;

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public JsonResult insertManometerInfo(@RequestBody JSONObject jsonObject){
        // 插入压力计设备信息数据
        return ResultUtils.success(manometerInfoService.insertManometerInfo(jsonObject));
    }
}
