package nnu.edu.Shore.controller;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.service.InclinometerOriginRecordService;
import nnu.edu.Shore.service.InclinometerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/4/16 22:41
 * @Description:
 */

@RestController
@RequestMapping("api/v1/inclinometer/origin/record")
public class InclinometerOriginRecordController {

    @Autowired
    InclinometerOriginRecordService inclinometerOriginRecordService;

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public JsonResult insertInclinometerRecord(@RequestBody JSONObject jsonObject){
        // 插入测斜仪设备记录数据
        return ResultUtils.success(inclinometerOriginRecordService.insertInclinometerOriginRecord(jsonObject));
    }
}
