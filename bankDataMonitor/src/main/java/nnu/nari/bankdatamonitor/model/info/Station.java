package nnu.nari.bankdatamonitor.model.info;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 11:24
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "stationBuilder")
public class Station {
    @Nonnull
    String station_id; // 站码
    @Nonnull
    String begin_time; // 开始日期
    @Nonnull
    String end_time; // 结束时间
    String operate_time; // 操作时间
    String operator; // 操作人
    Integer data_v; // 数据版本
    String data_v_explain; // 数据版本说明
}
