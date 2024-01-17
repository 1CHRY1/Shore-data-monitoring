package nnu.edu.Shore.service.impl;

import nnu.edu.Shore.dao.shore.InclinometerInfoMapper;
import nnu.edu.Shore.pojo.InclinometerInfo;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.service.InclinometerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:01
 * @Description:
 */

@Service
public class InclinometerInfoServiceImpl implements InclinometerInfoService {

    @Autowired
    InclinometerInfoMapper inclinometerInfoMapper;

    @Override
    public String insertInclinometerInfo(InclinometerInfo inclinometerInfo) {
        inclinometerInfoMapper.insertInclinometerInfo(inclinometerInfo);
        return inclinometerInfo.getIdGroup().getMachine_id();
    }

    @Override
    public String updateInclinometerInfo(InclinometerInfo inclinometerInfo) {
        return null;
    }

    @Override
    public String deleteInclinometerInfo(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getInclinometerInfos() {
        return null;
    }
}
