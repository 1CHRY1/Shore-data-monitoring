package nnu.edu.Shore.pojo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.security.MessageDigest;

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
@Builder
public class Machine {

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MachineIdGroup implements Serializable {
        @NotNull
        String station_id; // 站码
        @NotNull
        String machine_id; //设备码
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

    // 计算唯一标识码machine_id_nnu
    public void Onlyid(){
        generateUniqueCode();
    }

    // 生成唯一性编码并存入machine_id_nnu属性
    private void generateUniqueCode() {
        String type_string = String.valueOf(type);
        String longitude_string = String.valueOf(longitude);
        String latitude_string = String.valueOf(latitude);
        String combinedString = idGroup.station_id + longitude_string + "_" + latitude_string + "_" + type_string;
//        String uniqueCode = hashString(combinedString);
        idGroup.machine_id_nnu = combinedString;
    }

    // 使用SHA-256哈希算法处理字符串
    private String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hashBytes = digest.digest(input.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
    }

}
