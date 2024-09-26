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
 * @Date: 2024/1/15 19:34
 * @Description: 孔隙水压力计设备记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManometerRecord {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @Builder
    public static class ManometerRecordIdGroup implements Serializable{
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
    ManometerRecordIdGroup idGroup;
    Double zx; // 频率
    Double wd; // 温度
    Double swgc; // 水位高程
    Timestamp in_time; // 入库时间
}
