package nnu.edu.Shore.controller;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.Station;
import nnu.edu.Shore.service.MachineService;
import nnu.edu.Shore.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 16:18
 * @Description:
 */

@RestController
@RequestMapping("/Machine")
public class MachineController {
    @Autowired
    MachineService machineService;

    @RequestMapping(value="/insertMachine", method = RequestMethod.POST)
    public JsonResult insertMachine(@RequestBody JSONObject jsonObject){
        // 插入设备信息数据
        return ResultUtils.success(machineService.insertMachine(jsonObject));
    }
}
