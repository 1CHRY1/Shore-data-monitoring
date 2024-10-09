package nnu.nari.bankdatamonitor.model.base;

import lombok.*;

import javax.persistence.EmbeddedId;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 10:41
 * @Description:
 */

@Data
@Getter
@Setter
@Builder(builderMethodName = "baseMonitorInfoBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class MonitorInfo {
    @EmbeddedId
    MonitorInfoIdGroup idGroup;

    Timestamp in_time; // 入库时间
    Integer data_v; // 数据版本
    Timestamp operate_time; // 操作时间
    String in_operator; // 录入人
    String notes; // 备注
}
