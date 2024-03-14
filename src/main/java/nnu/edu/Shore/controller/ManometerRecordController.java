package nnu.edu.Shore.controller;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.pojo.ManometerInfo;
import nnu.edu.Shore.pojo.ManometerRecord;
import nnu.edu.Shore.service.ManometerInfoService;
import nnu.edu.Shore.service.ManometerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:50
 * @Description:
 */

@RestController
@RequestMapping("api/v1/manometer/record")
public class ManometerRecordController {
    @Autowired
    ManometerRecordService manometerRecordService;

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public JsonResult insertManometerRecord(@RequestBody JSONObject jsonObject){
        // 插入压力计设备记录数据
        return ResultUtils.success(manometerRecordService.insertManometerRecord(jsonObject));
    }
}
