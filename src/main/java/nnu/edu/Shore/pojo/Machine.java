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
 * @Date: 2024/1/15 17:11
 * @Description: 总设备类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Machine {

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MachineIdGroup implements Serializable {
        @NotNull
        String station_id; // 站码
        @NotNull
        String machine_id; //设备码
        @NotNull
        String machine_id_nnu; // 设备码别名
    }
    @EmbeddedId
    MachineIdGroup idGroup; // 复合主键
    @NotNull
    String machine_name; // 设备名称
    String begin_time; // 开始日期
    String end_time; // 结束时间
    @NotNull
    char type; // 设备类型，1=GNSS、2=测斜仪、3=孔隙水压力计、4=应力桩、5=预留扩展测量设备
    @NotNull
    Double longitude; // 经度
    @NotNull
    Double latitude; // 纬度
    @NotNull
    Double elevation; // 起始海拔高程
    String in_time; // 入库时间
    String operate_time; // 操作时间
    Integer data_v; // 数据版本
    String in_operator; // 录入人
    String notes; // 备注
}
