package nnu.edu.Shore.controller;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.Station;
import nnu.edu.Shore.service.MachineService;
import nnu.edu.Shore.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 16:18
 * @Description:
 */

@RestController
@RequestMapping("api/v1/machine/info")
public class MachineController {
    @Autowired
    MachineService machineService;

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public JsonResult insertMachine(@RequestBody JSONObject jsonObject){
        // 插入设备信息数据
        return ResultUtils.success(machineService.insertMachine(jsonObject));
    }

    @GetMapping(value="/get")
    public JsonResult getMachineInfo(@RequestParam String machine_id, @RequestParam Character type) {
        // 通过设备标识码获取设备信息
        return ResultUtils.success(machineService.getMachineInfo(machine_id, type));
    }
}
