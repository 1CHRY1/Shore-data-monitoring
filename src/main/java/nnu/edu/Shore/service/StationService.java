package nnu.edu.Shore.service;

import nnu.edu.Shore.pojo.Station;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 9:57
 * @Description:
 */
public interface StationService {
    String insertStation(Station station);

    String updateStation(Station station);

    String deleteStation(String station_id);

    List<Station> getStations();
}
