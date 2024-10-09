package nnu.nari.bankdatamonitor.model.info;

import lombok.*;
import nnu.nari.bankdatamonitor.model.base.MonitorInfo;
import nnu.nari.bankdatamonitor.model.base.MonitorInfoIdGroup;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 14:46
 * @Description:
 */

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GNSSInfo extends MonitorInfo {
    @Builder(builderMethodName = "GNSSInfoBuilder")
    public GNSSInfo(
            MonitorInfoIdGroup idGroup, Timestamp in_time, Integer data_v, Timestamp operate_time, String in_operator, String notes
    ) {
        super(idGroup, in_time, data_v, operate_time, in_operator, notes);
    }
}
