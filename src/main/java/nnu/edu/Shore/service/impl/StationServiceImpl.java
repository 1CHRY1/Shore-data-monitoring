package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.StationMapper;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.pojo.Station;
import nnu.edu.Shore.pojo.StresspileInfo;
import nnu.edu.Shore.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 9:59
 * @Description:
 */

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    StationMapper stationMapper;

    private Station dataProcess(JSONObject jsonObject) {
        Station station;
        // 判断请求格式和非空字段是否正确
        try {
            station = Station.builder()
                    .station_id(jsonObject.getString("station_id"))
                    .begin_time(jsonObject.getString("begin_time"))
                    .end_time(jsonObject.getString("end_time"))
                    .build();
        } catch (JSONException | NumberFormatException | NullPointerException e) {
            return Station.builder().build();
        }
        // 判断其他字段
        String operate_time = (String) jsonObject.getOrDefault("operate_time",null);
        String operator = (String) jsonObject.getOrDefault("operator",null);
        Integer data_v = (Integer) jsonObject.getOrDefault("data_v",null);
        String data_v_explain = (String) jsonObject.getOrDefault("data_v_explain",null);

        station.setOperate_time(operate_time);
        station.setOperator(operator);
        station.setData_v(data_v);
        station.setData_v_explain(data_v_explain);

        return station;
    }

    @Override
    public String insertStation(JSONObject jsonObject){
        Station station = dataProcess(jsonObject);
        stationMapper.insertStation(station);
        return station.getStation_id();
    }

//    @Override
//    public String updateStation(Station station) {
//        stationMapper.updateStation(station);
//        return station.getStation_id();
//    }
//
//    @Override
//    public String deleteStation(String station_id) {
//        stationMapper.deleteStation(station_id);
//        return station_id;
//    }
//
//    @Override
//    public List<Station> getStations() {
//        return stationMapper.getStations();
//    }
}
