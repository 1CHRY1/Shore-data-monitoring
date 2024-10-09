package nnu.nari.bankdatamonitor.model.info;

import jakarta.annotation.Nonnull;
import lombok.*;
import nnu.nari.bankdatamonitor.model.base.MonitorInfo;
import nnu.nari.bankdatamonitor.model.base.MonitorInfoIdGroup;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 15:01
 * @Description:
 */

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ManometerInfo extends MonitorInfo {
    @Nonnull
    Integer point_num; // 传感器个数
    @Nonnull
    Double point1_depth; // 传感器1深度
    Double point2_depth; // 传感器2深度
    Double point3_depth; // 传感器3深度
    Double point4_depth; // 传感器4深度
    Double point5_depth; // 传感器5深度
    Double point6_depth; // 传感器6深度

    @Builder(builderMethodName = "ManometerInfoBuilder")
    public ManometerInfo(
            MonitorInfoIdGroup idGroup, Timestamp in_time, Integer data_v, Timestamp operate_time, String in_operator, String notes,
            Integer point_num, Double point1_depth, Double point2_depth, Double point3_depth, Double point4_depth, Double point5_depth, Double point6_depth
    ) {
        super(idGroup, in_time, data_v, operate_time, in_operator, notes);
        this.point_num = point_num;
        this.point1_depth = point1_depth;
        this.point2_depth = point2_depth;
        this.point3_depth = point3_depth;
        this.point4_depth = point4_depth;
        this.point5_depth = point5_depth;
        this.point6_depth = point6_depth;
    }
}
