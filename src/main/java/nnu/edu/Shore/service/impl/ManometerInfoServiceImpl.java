package nnu.edu.Shore.service.impl;

import nnu.edu.Shore.dao.shore.ManometerInfoMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.ManometerInfo;
import nnu.edu.Shore.service.ManometerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 11:36
 * @Description:
 */

@Service
public class ManometerInfoServiceImpl implements ManometerInfoService {

    @Autowired
    ManometerInfoMapper manometerInfoMapper;

    @Override
    public String insertManometerInfo(ManometerInfo manometerInfo) {
        manometerInfoMapper.insertManometerInfo(manometerInfo);
        return manometerInfo.getIdGroup().getMachine_id();
    }

    @Override
    public String updateManometerInfo(ManometerInfo manometerInfo) {
        return null;
    }

    @Override
    public String deleteManometerInfo(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getManometerInfos() {
        return null;
    }
}
