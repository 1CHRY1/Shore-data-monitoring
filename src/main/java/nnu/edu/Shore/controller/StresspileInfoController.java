package nnu.edu.Shore.controller;

import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.StresspileInfo;
import nnu.edu.Shore.service.InclinometerInfoService;
import nnu.edu.Shore.service.StresspileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 12:03
 * @Description:
 */

@RestController
@RequestMapping("/StresspileInfo")
public class StresspileInfoController {
    @Autowired
    StresspileInfoService stresspileInfoService;

    @RequestMapping(value="/insertStresspileInfo", method = RequestMethod.POST)
    public JsonResult insertStresspileInfo(@RequestBody StresspileInfo stresspileInfo){
        // 插入应力桩备信息数据
        return ResultUtils.success(stresspileInfoService.insertStresspileInfo(stresspileInfo));
    }
}
