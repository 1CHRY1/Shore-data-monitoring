package nnu.edu.Shore.service.impl;

import nnu.edu.Shore.dao.shore.StresspileRecordMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.StresspileRecord;
import nnu.edu.Shore.service.StresspileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 15:41
 * @Description:
 */

@Service
public class StresspileRecordServiceImpl implements StresspileRecordService {

    @Autowired
    StresspileRecordMapper stresspileRecordMapper;
    @Override
    public String insertStresspileRecord(StresspileRecord stresspileRecord) {
        stresspileRecordMapper.insertStresspileRecord(stresspileRecord);
        return stresspileRecord.getIdGroup().getMachine_id();
    }

    @Override
    public String updateStresspileRecord(StresspileRecord stresspileRecord) {
        return null;
    }

    @Override
    public String deleteStresspileRecord(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getStresspileRecords() {
        return null;
    }
}
