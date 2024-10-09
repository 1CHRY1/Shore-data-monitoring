package nnu.nari.bankdatamonitor.common.utils;

import java.sql.*;
import java.time.LocalDate;
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
    private static boolean checkPartitionExists(Connection connection, String partitionName) throws SQLException {
        boolean exists = false;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, partitionName, null);
            exists = resultSet.next();
            resultSet.close();
            return exists;
        } catch (SQLException e) {
            exists = true; // 返回存在则不做任何操作
        }
        return exists;
    }

    // 数据库按月自动分区
    public static void DBPartition(String url, String user, String password, LocalDate date, String name) {
        String tableName = name + "_record";
        String partitionName = name + "_record_" + date.format(DateTimeFormatter.ofPattern("yyyyMM"));

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            boolean partitionExists = checkPartitionExists(connection, partitionName);
            if (!partitionExists){
                String createPartitionSQL = "CREATE TABLE " + partitionName + " PARTITION OF " + tableName +
                        " FOR VALUES FROM ('" + date + "') TO ('" + date.plusMonths(1).minusDays(1) + "')";
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
