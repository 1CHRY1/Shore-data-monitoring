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
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "MonitorRecordBuilder")
public class MonitorRecord {
    @EmbeddedId
    MonitorRecordIdGroup idGroup;
    Timestamp in_time; // 入库时间
}
