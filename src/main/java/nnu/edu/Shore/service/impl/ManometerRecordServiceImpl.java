package nnu.edu.Shore.service.impl;

import nnu.edu.Shore.dao.shore.ManometerRecordMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerRecord;
import nnu.edu.Shore.service.ManometerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:48
 * @Description:
 */

@Service
public class ManometerRecordServiceImpl implements ManometerRecordService {

    @Autowired
    ManometerRecordMapper manometerRecordMapper;

    @Override
    public String insertManometerRecord(ManometerRecord manometerRecord) {
        manometerRecordMapper.insertManometerRecord(manometerRecord);
        return manometerRecord.getIdGroup().getMachine_id();
    }

    @Override
    public String updateManometerRecord(ManometerRecord manometerRecord) {
        return null;
    }

    @Override
    public String deleteManometerRecord(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getManometerRecords() {
        return null;
    }
}
