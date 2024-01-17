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
 * @Date: 2024/1/15 19:34
 * @Description: 孔隙水压力计设备记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManometerRecord {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class ManometerRecordIdGroup implements Serializable{
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
    ManometerRecordIdGroup idGroup;
    @NotNull
    Double pressure1; // 第1传感器数值
    Double pressure2; // 第2传感器数值
    Double pressure3; // 第3传感器数值
    Double pressure4; // 第4传感器数值
    Double pressure5; // 第5传感器数值
    Double pressure6; // 第6传感器数值
    String in_time; // 入库时间
}
