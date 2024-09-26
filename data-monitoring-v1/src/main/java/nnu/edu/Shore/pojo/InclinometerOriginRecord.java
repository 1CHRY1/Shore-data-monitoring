package nnu.edu.Shore.pojo;

//import com.sun.istack.internal.NotNull;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/15 17:27
 * @Description: 测斜仪设备记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InclinometerOriginRecord {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @Builder
    public static class InclinometerOriginRecordIdGroup implements Serializable {
        @Nonnull
        String station_id; // 站码
        @Nonnull
        String machine_id; //设备码
        @Nonnull
        String machine_id_nnu; // 设备码别名
        @Nonnull
        Timestamp measure_time; // 测量时间
    }
    @EmbeddedId
    InclinometerOriginRecordIdGroup idGroup;
    Double angle; // 角度
    Timestamp in_time; // 入库时间
}