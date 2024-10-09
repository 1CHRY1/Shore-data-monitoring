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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "MonitorRecordIdGroupBuilder")
public class MonitorRecordIdGroup {
    @NonNull
    String station_id; // 站码
    @Nonnull
    String machine_id; //设备码
    @Nonnull
    String machine_id_nnu; // 设备码别名
    @NonNull
    Timestamp measure_time; // 设备测量时间
}
