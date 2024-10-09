package nnu.nari.bankdatamonitor.model.record;

import lombok.*;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 16:01
 * @Description:
 */

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InclinometerRecord extends MonitorRecord {

    Double top_move; // 顶部累计位移
    Double middle_move; // 中部累计位移
    Double bottom_move; // 底部累计位移
    Double top_move_24h; // 顶部24小时累计位移
    Double middle_move_24h; // 中部24小时累计位移
    Double bottom_move_24h; // 底部24小时累计位移

    @Builder(builderMethodName = "InclinometerRecordBuilder")
    public InclinometerRecord(
            MonitorRecordIdGroup idGroup, Timestamp in_time,
            Double top_move, Double middle_move, Double bottom_move, Double top_move_24h, Double middle_move_24h, Double bottom_move_24h
    ) {
        super(idGroup, in_time);
        this.top_move = top_move;
        this.middle_move = middle_move;
        this.bottom_move = bottom_move;
        this.top_move_24h = top_move_24h;
        this.middle_move_24h = middle_move_24h;
        this.bottom_move_24h = bottom_move_24h;
    }
}
