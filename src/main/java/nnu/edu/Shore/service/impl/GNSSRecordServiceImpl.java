package nnu.edu.Shore.service.impl;

import nnu.edu.Shore.dao.shore.GNSSRecordMapper;
import nnu.edu.Shore.pojo.GNSSRecord;
import nnu.edu.Shore.service.GNSSRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 10:39
 * @Description:
 */

@Repository
public class GNSSRecordServiceImpl implements GNSSRecordService {

    @Autowired
    GNSSRecordMapper gnssRecordMapper;

    @Override
    public String insertGNSSRecord(GNSSRecord gnssRecord) {
        gnssRecordMapper.insertGNSSRecord(gnssRecord);
        return gnssRecord.getIdGroup().getMachine_id();
    }

    @Override
    public String updateGNSSRecord(GNSSRecord gnssRecord) {
        return null;
    }

    @Override
    public String deleteGNSSRecord(String gnssRecord) {
        return null;
    }

    @Override
    public List<GNSSRecord> getGNSSRecords() {
        return null;
    }
}
