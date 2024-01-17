package nnu.edu.Shore.dao.shore;

import nnu.edu.Shore.pojo.Station;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/16 9:43
 * @Description:
 */

@Repository
public interface StationMapper {
    void insertStation(Station station);

    void updateStation(Station station);

    void deleteStation(String stationId);

    List<Station> getStations();
}
