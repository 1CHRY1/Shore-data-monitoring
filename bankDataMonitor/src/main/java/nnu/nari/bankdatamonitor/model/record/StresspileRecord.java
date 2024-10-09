package nnu.nari.bankdatamonitor.model.record;

import lombok.*;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 16:42
 * @Description:
 */

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StresspileRecord extends MonitorRecord {

    Double top_angle; // 上夹角
    Double middle_angle; // 中夹角
    Double bottom_angle; // 下夹角
    Double top_power; // 上最大主应力
    Double middle_power; // 中最大主应力
    Double bottom_power; // 下最大主应力
    Double top_change; // 上最大主应变
    Double middle_change; // 中最大主应变
    Double bottom_change; // 下最大主应变

    @Builder(builderMethodName = "StresspileRecordBuilder")
    public StresspileRecord(
            MonitorRecordIdGroup idGroup, Timestamp in_time,
            Double top_angle, Double middle_angle, Double bottom_angle, Double top_power, Double middle_power,
            Double bottom_power, Double top_change, Double middle_change, Double bottom_change
            ) {
        super(idGroup, in_time);
        this.top_angle = top_angle;
        this.middle_angle = middle_angle;
        this.bottom_angle = bottom_angle;
        this.top_power = top_power;
        this.middle_power = middle_power;
        this.bottom_power = bottom_power;
        this.top_change = top_change;
        this.middle_change = middle_change;
        this.bottom_change = bottom_change;
    }

}
