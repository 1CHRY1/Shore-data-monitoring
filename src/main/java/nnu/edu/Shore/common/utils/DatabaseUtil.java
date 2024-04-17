package nnu.edu.Shore.common.utils;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/3/15 11:02
 * @Description:
 */
public class DatabaseUtil {

    // 判断数据库分区是否存在
    private static boolean checkPartitionExists(Connection connection, String tableName, String partitionName) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT 1 FROM pg_partition WHERE tablename = '" + tableName + "' AND partitionname = '" + partitionName + "'");
            return resultSet.next();
        } catch (SQLException e) {
//            System.out.println(e);
            return false;
        }
    }

    // 数据库按月自动分区
    public static void DBPartition(String url, String user, String password, Timestamp timestamp, String name) {
        // 判断时间是否为一个月的第一分钟
        LocalDate Date = timestamp.toLocalDateTime().toLocalDate();
        LocalTime Time = timestamp.toLocalDateTime().toLocalTime();
        if (Date.getDayOfMonth() != 1 || Time.getHour() != 0 || Time.getMinute() != 0 ) {
//        if (Date.getDayOfMonth() == 18 ) {
            return;
        }
        // 若是，则为该时间创造一个分区
        LocalDate firstDayOfMonth = Date.withDayOfMonth(1);
        String tableName = name + "_record";
        String partitionName = name + "_" + firstDayOfMonth.format(DateTimeFormatter.ofPattern("yyyyMM"));

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            boolean partitionExists = checkPartitionExists(connection, tableName, partitionName);
            if (!partitionExists){
                String createPartitionSQL = "CREATE TABLE " + partitionName + " PARTITION OF " + tableName +
                        " FOR VALUES FROM ('" + firstDayOfMonth + "') TO ('" + firstDayOfMonth.plusMonths(1).minusDays(1) + "')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(createPartitionSQL);
                System.out.println("Partition created successfully: " + partitionName);
            } else {
                System.out.println("Partition already exists: " + partitionName);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
