package nnu.edu.Shore.pojo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
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
 * @Date: 2024/1/15 19:38
 * @Description: 应力桩设备记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StresspileRecord {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class StresspileRecordIdGroup implements Serializable {
        @NotNull
        String station_id; // 站码
        @NotNull
        String machine_id; //设备码
        @NotNull
        String machine_id_nnu; // 设备码别名
        @NotNull
        String measure_time; // 测量时间
    }
    @EmbeddedId
    StresspileRecordIdGroup idGroup; // 复合主键
    @NotNull
    Double horizontal1; // 水平应力角度1
    @NotNull
    Double horizontal_stress1; // 水平应力大小1
    @NotNull
    Double vertical_stress1; // 垂向应力大小1
    Double horizontal2; // 水平应力角度2
    Double horizontal_stress2; // 水平应力大小2
    Double vertical_stress2; // 垂向应力大小2
    Double horizontal3; // 水平应力角度3
    Double horizontal_stress3; // 水平应力大小3
    Double vertical_stress3; // 垂向应力大小3
    Double horizontal4; // 水平应力角度4
    Double horizontal_stress4; // 水平应力大小4
    Double vertical_stress4; // 垂向应力大小4
    Double horizonta15; // 水平应力角度5
    Double horizontal_stress5; // 水平应力大小5
    Double vertical_stress5; // 垂向应力大小5
    Double horizontal6; // 水平应力角度6
    Double horizontal_stress6; // 水平应力大小6
    Double vertical_stress6; // 垂向应力大小6
    String in_time; // 入库时间
}
