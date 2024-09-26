package nnu.edu.Shore.pojo;

//import com.sun.istack.internal.NotNull;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/15 19:38
 * @Description: 应力桩设备记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StresspileRecord {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @Builder
    public static class StresspileRecordIdGroup implements Serializable {
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
    StresspileRecordIdGroup idGroup; // 复合主键
    Double top_angle; // 上夹角
    Double middle_angle; // 中夹角
    Double bottom_angle; // 下夹角
    Double top_power; // 上最大主应力
    Double middle_power; // 中最大主应力
    Double bottom_power; // 下最大主应力
    Double top_change; // 上最大主应变
    Double middle_change; // 中最大主应变
    Double bottom_change; // 下最大主应变
    Timestamp in_time; // 入库时间
}
