package nnu.edu.Shore.controller;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.common.result.JsonResult;
import nnu.edu.Shore.common.result.ResultUtils;
import nnu.edu.Shore.pojo.Station;
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
 * @Date: 2024/1/16 10:11
 * @Description:
 */

@RestController
@RequestMapping("/Station")
public class StationController {
    @Autowired
    StationService stationService;

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public JsonResult insertStation(@RequestBody JSONObject jsonObject){
        // 插入测站信息数据
        return ResultUtils.success(stationService.insertStation(jsonObject));
    }

    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public JsonResult updateStation(@RequestBody Station station){
        // 更新测站信息数据
        return ResultUtils.success(stationService.updateStation(station));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public JsonResult deleteStation(@RequestBody String station_id){
        // 删除测站信息数据
        return ResultUtils.success(stationService.deleteStation(station_id));
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public JsonResult getStations(){
        // 获取所有站点信息数据
        return ResultUtils.success(stationService.getStations());
    }
}
