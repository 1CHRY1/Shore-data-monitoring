package nnu.edu.Shore.service.impl;

import com.alibaba.fastjson2.JSONObject;
import nnu.edu.Shore.dao.shore.StationMapper;
import nnu.edu.Shore.pojo.Station;
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

    private Station dataProcess(JSONObject jsonObject){
        Station station = Station.builder()
                .station_id(jsonObject.getString("station_id"))
                .begin_time(jsonObject.getString("begin_time"))
                .end_time(jsonObject.getString("end_time"))
                .operate_time(jsonObject.getString("operate_time"))
                .operator(jsonObject.getString("operator"))
                .data_v(Integer.parseInt(jsonObject.getString("data_v")))
                .data_v_explain(jsonObject.getString("data_v_explain"))
                .build();
        return station;
    }

    @Override
    public String insertStation(JSONObject jsonObject){
        Station station = dataProcess(jsonObject);
        stationMapper.insertStation(station);
        return station.getStation_id();
    }

    @Override
    public String updateStation(Station station) {
        stationMapper.updateStation(station);
        return station.getStation_id();
    }

    @Override
    public String deleteStation(String station_id) {
        stationMapper.deleteStation(station_id);
        return station_id;
    }

    @Override
    public List<Station> getStations() {
        return stationMapper.getStations();
    }
}
