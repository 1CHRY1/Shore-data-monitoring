package nnu.edu.Shore.pojo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/1/15 17:07
 * @Description: 测站类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Station {
    @NotNull
    String station_id; // 站码
    @NotNull
    String begin_time; // 开始日期
    @NotNull
    String end_time; // 结束时间
    String operate_time; // 操作时间
    String operator; // 操作人
    Integer data_v; // 数据版本
    String data_v_explain; // 数据版本说明
}
