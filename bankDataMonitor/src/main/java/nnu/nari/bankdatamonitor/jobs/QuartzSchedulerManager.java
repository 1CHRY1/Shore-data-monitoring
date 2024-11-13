package nnu.nari.bankdatamonitor.jobs;

import lombok.extern.slf4j.Slf4j;
import nnu.nari.bankdatamonitor.common.utils.DatabaseUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/30 22:23
 * @Description:
 */

@Component("QuartzManager")
@Slf4j
public class QuartzSchedulerManager {

    @Value("${stresspileServer.url}")
    String STRESS_PILE_SERVER;

    @Value("${stresspileServer.user}")
    String STRESS_PILE_USER;

    @Value("${stresspileServer.password}")
    String STRESS_PILE_PASSWORD;

    @Value("${spring.datasource.bank.jdbc-url}")
    String PG_URL;

    @Value("${spring.datasource.bank.username}")
    String PG_USER;

    @Value("${spring.datasource.bank.password}")
    String PG_PASS;

    @Autowired
    @Lazy
    private Scheduler scheduler;

    // 开始执行定时器
    public void startJob() throws SchedulerException {
        log.info("start job here");
//        startStresspileDataJob();
        startCreateDBPartitionJob();
        scheduler.start();
    }

    public void startStresspileDataJob() throws SchedulerException {
        log.info("start StresspileDataJob here!");
        stresspileDataJob(scheduler);
        scheduler.start();
    }

    public void startCreateDBPartitionJob() throws SchedulerException {
        log.info("start createDBPartitionJob here!");
        createDBPartitionJob(scheduler);
        scheduler.start();
    }

    private void stresspileDataJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(StresspileDataJob.class)
                .withIdentity("StresspileDataGroup")
                .build();
        jobDetail.getJobDataMap().put("stressPileServer", STRESS_PILE_SERVER);
        jobDetail.getJobDataMap().put("stressPileUser", STRESS_PILE_USER);
        jobDetail.getJobDataMap().put("stressPilePassword", STRESS_PILE_PASSWORD);
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("StresspileData"+"_trigger","StresspileDataTriggerGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).repeatForever()).build();
        scheduler.scheduleJob(jobDetail, simpleTrigger);
    }

    private void createDBPartitionJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(CreateDBPartitionJob.class)
                .withIdentity("DBDataGroup")
                .build();
        jobDetail.getJobDataMap().put("url", PG_URL);
        jobDetail.getJobDataMap().put("user", PG_USER);
        jobDetail.getJobDataMap().put("password", PG_PASS);
        // 基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 1 * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("DBData"+"_trigger","DBDataTriggerGroup")
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
//        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("DBData"+"_trigger","DBDataTriggerGroup")
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).repeatForever()).build();
//        scheduler.scheduleJob(jobDetail, simpleTrigger);
    }
}
