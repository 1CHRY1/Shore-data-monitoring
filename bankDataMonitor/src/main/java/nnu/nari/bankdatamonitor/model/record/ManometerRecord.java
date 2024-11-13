package nnu.nari.bankdatamonitor.model.record;

import lombok.*;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 16:39
 * @Description:
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ManometerRecord extends MonitorRecord {

    Double zx; // 频率
    Double wd; // 温度
    Double swgc; // 水位高程
    Timestamp in_time; // 入库时间

    @Builder(builderMethodName = "ManometerRecordBuilder")
    public ManometerRecord(
            MonitorRecordIdGroup idGroup, Timestamp in_time,
            Double zx, Double wd, Double swgc
    ) {
        super(idGroup, in_time);
        this.zx = zx;
        this.wd = wd;
        this.swgc = swgc;
    }
}
