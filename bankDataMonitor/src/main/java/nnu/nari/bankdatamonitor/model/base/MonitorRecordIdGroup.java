package nnu.nari.bankdatamonitor.model.base;

import jakarta.annotation.Nonnull;
import lombok.*;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 11:22
 * @Description:
 */


@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class MonitorRecordIdGroup {
    @NonNull
    String station_id; // 站码
    @Nonnull
    String machine_id; //设备码
    @Nonnull
    String machine_id_nnu; // 设备码别名
    @NonNull
    Timestamp measure_time; // 设备测量时间

    @Builder(builderMethodName = "MonitorRecordIdGroupBuilder")
    public MonitorRecordIdGroup(
            String station_id, String machine_id, String machine_id_nnu, Timestamp measure_time
    ) {
        this.station_id = station_id;
        this.machine_id = machine_id;
        this.machine_id_nnu = machine_id_nnu;
        this.measure_time = measure_time;
    }

}
