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
 * @Date: 2024/1/15 17:27
 * @Description: 测斜仪设备记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InclinometerRecord {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class InclinometerRecordIdGroup implements Serializable {
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
    InclinometerRecordIdGroup idGroup;
    @NotNull
    Double x_move1; // 第1传感器x向偏移量
    @NotNull
    Double y_move1; // 第1传感器y向偏移量
    Double x_move2; // 第2传感器x向偏移量
    Double y_move2; // 第2传感器y向偏移量
    Double x_move3; // 第3传感器x向偏移量
    Double y_move3; // 第3传感器y向偏移量
    Double x_move4; // 第4传感器x向偏移量
    Double y_move4; // 第4传感器y向偏移量
    Double x_move5; // 第5传感器x向偏移量
    Double y_move5; // 第5传感器y向偏移量
    Double x_move6; // 第6传感器x向偏移量
    Double y_move6; // 第6传感器y向偏移量
    String in_time; // 入库时间
}
