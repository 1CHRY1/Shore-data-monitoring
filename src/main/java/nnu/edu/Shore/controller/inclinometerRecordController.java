package nnu.edu.Shore.controller;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.InclinometerRecord;
import nnu.edu.Shore.service.InclinometerInfoService;
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
 * @Date: 2024/1/17 11:20
 * @Description:
 */
@RestController
@RequestMapping("/InclinometerRecord")
public class inclinometerRecordController {

    @Autowired
    InclinometerRecordService inclinometerRecordService;

    @RequestMapping(value="/insertInclinometerRecord", method = RequestMethod.POST)
    public JsonResult insertInclinometerRecord(@RequestBody JSONObject jsonObject){
        // 插入测斜仪设备记录数据
        return ResultUtils.success(inclinometerRecordService.insertInclinometerRecord(jsonObject));
    }
}
