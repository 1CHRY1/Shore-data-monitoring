package nnu.nari.bankdatamonitor.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/10/29 20:49
 * @Description:
 */
public class TimeStringConverter {
    private static final List<DateTimeFormatter> formatters = new ArrayList<>();

    static {
        // 常见的日期时间格式
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        formatters.add(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static LocalDateTime convertStringToLocalDateTime(String timeString) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(timeString, formatter);
            } catch (DateTimeParseException e) {
                // 格式不匹配，尝试下一个格式
            }
        }
        throw new IllegalArgumentException("无法将字符串 " + timeString + " 转换为LocalDateTime，不匹配任何已知格式");
    }

    public static void main(String[] args) {
        String[] timeStrings = {
                "2024-05-18 00:20:53",
                "2024-05-18T00:20:53",
                "2024-05-18 00:20",
                "2024-05-18",
                "2024/05/18 00:20:53",
                "2024/05/18",
                "20240518 00:20:53",
                "20240518",
                "2024-05-18T00:20:53.123Z"
        };

        for (String timeString : timeStrings) {
            try {
                LocalDateTime dateTime = convertStringToLocalDateTime(timeString);
                System.out.println("转换成功: " + dateTime);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
