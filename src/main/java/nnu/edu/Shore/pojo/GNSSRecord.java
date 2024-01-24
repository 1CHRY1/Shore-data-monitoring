package nnu.edu.Shore.pojo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.io.Serializable;

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
        @NotNull
        String station_id; // 站码
        @NotNull
        String machine_id; //设备码
        @NotNull
        String machine_id_nnu; // 设备码别名
        @NotNull
        String measure_time; // 测量日期
    }
    @EmbeddedId
    GNSSRecordIdGroup idGroup; // 复合主键
    @NotNull
    Double x_move; // x东偏移量
    @NotNull
    Double y_move; // y北偏移量
    @NotNull
    Double z_move; // z上偏移量
    String in_time; // 入库时间
}
