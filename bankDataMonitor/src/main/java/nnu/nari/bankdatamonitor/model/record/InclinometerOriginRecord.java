package nnu.nari.bankdatamonitor.model.record;

import lombok.*;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/10/9 11:37
 * @Description:
 */

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InclinometerOriginRecord extends MonitorRecord {
    Double angle; // 角度
    @Builder(builderMethodName = "InclinometerOriginRecordBuilder")
    public InclinometerOriginRecord(
            MonitorRecordIdGroup idGroup, Timestamp in_time, Double angle
    ) {
        super(idGroup, in_time);
        this.angle = angle;
    }
}
