package nnu.edu.Shore.service.impl;

import nnu.edu.Shore.dao.shore.StresspileInfoMapper;
import nnu.edu.Shore.pojo.Machine;
import nnu.edu.Shore.pojo.StresspileInfo;
import nnu.edu.Shore.service.StresspileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/17 12:01
 * @Description:
 */

@Service
public class StresspileServiceInfoImpl implements StresspileInfoService {

    @Autowired
    StresspileInfoMapper stresspileInfoMapper;

    @Override
    public String insertStresspileInfo(StresspileInfo stresspileInfo) {
        stresspileInfoMapper.insertStresspileInfo(stresspileInfo);
        return stresspileInfo.getIdGroup().getMachine_id();
    }

    @Override
    public String updateStresspileInfo(StresspileInfo stresspileInfo) {
        return null;
    }

    @Override
    public String deleteStresspileInfo(String machine_id_nnu) {
        return null;
    }

    @Override
    public List<Machine> getStresspileInfos() {
        return null;
    }
}
