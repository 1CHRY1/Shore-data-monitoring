package nnu.edu.Shore.controller;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.service.GNSSRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 10:41
 * @Description:
 */

@RestController
@RequestMapping("/GNSSRecord")
public class GNSSRecordController {

    @Autowired
    GNSSRecordService gnssRecordService;

    @RequestMapping(value="/insertGNSSRecord", method = RequestMethod.POST)
    public JsonResult insertGNSSRecord(@RequestBody JSONObject jsonObject){
        // 插入GNSS设备记录数据
        return ResultUtils.success(gnssRecordService.insertGNSSRecord(jsonObject));
    }
}
