package nnu.nari.bankdatamonitor.jobs;

import lombok.extern.slf4j.Slf4j;
import nnu.nari.bankdatamonitor.common.utils.DatabaseUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/10/9 10:51
 * @Description:
 */

@Slf4j
@Component
public class CreateDBPartitionJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String PG_URL = dataMap.getString("url");
        String PG_USER = dataMap.getString("user");
        String PG_PASS = dataMap.getString("password");
        LocalDate MonthTime = LocalDate.now().withDayOfMonth(1);
        List<String> tableNames = new ArrayList<>();
        tableNames.add("gnss"); tableNames.add("manometer"); tableNames.add("inclinometer"); tableNames.add("inclinometer_o"); tableNames.add("stresspile");
        for (String tableName : tableNames) {
            DatabaseUtil.DBPartition(PG_URL, PG_USER, PG_PASS, MonthTime, tableName);
        }
    }

}