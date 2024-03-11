package nnu.edu.Shore.controller;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.pojo.StresspileInfo;
import nnu.edu.Shore.pojo.StresspileRecord;
import nnu.edu.Shore.service.StresspileInfoService;
import nnu.edu.Shore.service.StresspileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 15:42
 * @Description:
 */

@RestController
@RequestMapping("api/v1/stresspile/record")
public class StresspileRecordController {
    @Autowired
    StresspileRecordService stresspileRecordService;

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public JsonResult insertStresspileRecord(@RequestBody JSONObject jsonObject){
        // 插入应力桩设备信记录数据
        return ResultUtils.success(stresspileRecordService.insertStresspileRecord(jsonObject));
    }
}
