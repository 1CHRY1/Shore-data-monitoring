package nnu.edu.Shore.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/3/15 17:02
 * @Description:
 */
public class TimeUtil {
    public static Timestamp String2Timestamp(String timeString) throws ParseException {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date parsedDate;
        try {
            parsedDate = dateFormat1.parse(timeString);
        } catch (ParseException e1) {
            // Try the second date format
            parsedDate = dateFormat2.parse(timeString);
        }
        Timestamp Time = new Timestamp(parsedDate.getTime());
        return Time;
    }
}
