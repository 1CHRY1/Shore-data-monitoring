package nnu.edu.Shore.service.impl;

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

    @Override
    public String insertStation(Station station){
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
