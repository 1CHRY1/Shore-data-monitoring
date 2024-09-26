package nnu.edu.Shore.controller;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.service.GNSSRecordService;
import nnu.edu.Shore.service.InclinometerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:03
 * @Description:
 */

@RestController
@RequestMapping("api/v1/inclinometer/info")
public class InclinometerInfoController {

    @Autowired
    InclinometerInfoService inclinometerInfoService;

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public JsonResult insertInclinometerInfo(@RequestBody JSONObject jsonObject){
        // 插入测斜仪设备信息数据
        return ResultUtils.success(inclinometerInfoService.insertInclinometerInfo(jsonObject));
    }
}
