package nnu.edu.Shore.pojo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/15 19:36
 * @Description: 应力桩设备信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StresspileInfo {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @Builder
    public static class StresspileInfoIdGroup implements Serializable {
        @NotNull
        String station_id; // 站码
        @NotNull
        String machine_id; //设备码
        @NotNull
        String machine_id_nnu; // 设备码别名
    }
    @EmbeddedId
    StresspileInfoIdGroup idGroup; //复合主键
    @NotNull
    Integer point_num; // 传感器个数
    @NotNull
    Double point1_depth; // 传感器1深度
    Double point2_depth; // 传感器2深度
    Double point3_depth; // 传感器3深度
    Double point4_depth; // 传感器4深度
    Double point5_depth; // 传感器5深度
    Double point6_depth; // 传感器6深度
    String in_time; // 入库时间
    Integer data_v; // 数据版本
    String in_operator; // 录入人
    String notes; // 备注
}
