package nnu.nari.bankdatamonitor.model.base;

import jakarta.annotation.Nonnull;
import lombok.*;

import javax.persistence.Embeddable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 10:36
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder(builderMethodName = "MonitorInfoIdGroupBuilder")
public class MonitorInfoIdGroup {
    @NonNull
    String station_id; // 站码
    @Nonnull
    String machine_id; //设备码
    @Nonnull
    String machine_id_nnu; // 设备码别名
}
