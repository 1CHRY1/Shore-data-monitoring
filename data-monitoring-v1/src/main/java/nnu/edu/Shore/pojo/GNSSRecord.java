package nnu.edu.Shore.pojo;

//import com.sun.istack.internal.NotNull;
import javax.annotation.Nonnull;

import lombok.*;
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
 * @Date: 2024/1/15 17:21
 * @Description: GNSS设备记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GNSSRecord {
    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GNSSRecordIdGroup implements Serializable {
        @Nonnull
        String station_id; // 站码
        @Nonnull
        String machine_id; //设备码
        @Nonnull
        String machine_id_nnu; // 设备码别名
        @Nonnull
        Timestamp measure_time; // 测量日期
    }
    @EmbeddedId
    GNSSRecordIdGroup idGroup; // 复合主键
    @Nonnull
    Double x_move; // x东偏移量
    @Nonnull
    Double y_move; // y北偏移量
    @Nonnull
    Double z_move; // z上偏移量
    Double threeD; // 3D累计位移
    Double threeDF; // 5小时3D相对变化量
    Timestamp in_time; // 入库时间
}
