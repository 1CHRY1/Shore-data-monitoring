package nnu.edu.Shore.service.impl;

import nnu.edu.Shore.dao.shore.InclinometerInfoMapper;
import nnu.edu.Shore.dao.shore.InclinometerRecordMapper;
import nnu.edu.Shore.pojo.InclinometerRecord;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.service.InclinometerInfoService;
import nnu.edu.Shore.service.InclinometerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:17
 * @Description:
 */

@Service
public class InclinometerRecordServiceImpl implements InclinometerRecordService {

    @Autowired
    InclinometerRecordMapper inclinometerRecordMapper;

    @Override
    public String insertInclinometerRecord(InclinometerRecord inclinometerRecord) {
        inclinometerRecordMapper.insertInclinometerRecord(inclinometerRecord);
        return inclinometerRecord.getIdGroup().getMachine_id();
    }

    @Override
    public String updateInclinometerRecord(InclinometerRecord inclinometerRecord) {
        return null;
    }

    @Override
    public String deleteInclinometerRecord(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getInclinometerRecords() {
        return null;
    }
}
