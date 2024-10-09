package nnu.nari.bankdatamonitor.model.info;

import jakarta.annotation.Nonnull;
import lombok.*;
import nnu.nari.bankdatamonitor.model.base.MonitorInfo;
import nnu.nari.bankdatamonitor.model.base.MonitorInfoIdGroup;

import java.security.MessageDigest;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/26 11:25
 * @Description:
 */

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Machine extends MonitorInfo {
    @NonNull
    String machine_name; // 设备名称
    @Nonnull
    char type; // 设备类型，1=GNSS、2=测斜仪、3=孔隙水压力计、4=应力桩、5=预留扩展测量设备
    @Nonnull
    Double longitude; // 经度
    @Nonnull
    Double latitude; // 纬度
    @Nonnull
    Double elevation; // 起始海拔高程
    Timestamp begin_time; // 开始日期
    Timestamp end_time; // 结束时间

    public Machine() {

    }

    @Builder(builderMethodName = "MachineBuilder")
    public Machine(
            MonitorInfoIdGroup idGroup, Timestamp in_time, Integer data_v, Timestamp operate_time, String in_operator, String notes,
            String machine_name, char type, Double longitude, Double latitude, Double elevation, Timestamp begin_time, Timestamp end_time
    ) {
        super(idGroup, in_time, data_v, operate_time, in_operator, notes);
        this.machine_name = machine_name;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
        this.begin_time = begin_time;
        this.end_time = end_time;
    }

    // 计算唯一标识码machine_id_nnu
    public void Onlyid(){
        generateUniqueCode();
    }

    // 生成唯一性编码并存入machine_id_nnu属性
    private void generateUniqueCode() {
        String type_string = String.valueOf(type);
        String longitude_string = String.valueOf(longitude);
        String latitude_string = String.valueOf(latitude);
        String combinedString = this.getIdGroup() + longitude_string + "_" + latitude_string + "_" + type_string;
//        String uniqueCode = hashString(combinedString);
        this.getIdGroup().setMachine_id_nnu(combinedString);
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
