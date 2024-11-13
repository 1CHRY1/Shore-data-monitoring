package nnu.nari.bankdatamonitor.model.record;

import jakarta.annotation.Nonnull;
import lombok.*;
import nnu.nari.bankdatamonitor.model.base.MonitorRecord;
import nnu.nari.bankdatamonitor.model.base.MonitorRecordIdGroup;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 15:05
 * @Description:
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GnssRecord extends MonitorRecord {

    @Nonnull
    Double x_move; // x东偏移量
    @Nonnull
    Double y_move; // y北偏移量
    @Nonnull
    Double z_move; // z上偏移量
    Double threeD; // 3D累计位移
    Double threeDF; // 5小时3D相对变化量

    @Builder(builderMethodName = "GnssRecordBuilder")
    public GnssRecord(
            MonitorRecordIdGroup idGroup, Timestamp in_time,
            Double x_move, Double y_move, Double z_move, Double threeD, Double threeDF
    ) {
        super(idGroup, in_time);
        this.x_move = x_move;
        this.y_move = y_move;
        this.z_move = z_move;
        this.threeD = threeD;
        this.threeDF = threeDF;
    }
}
