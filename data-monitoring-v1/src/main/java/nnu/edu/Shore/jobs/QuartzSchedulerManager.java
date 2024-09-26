//package nnu.edu.Shore.jobs;
//
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * @projectName: backEnd
// * @package: com.johnny.bank.jobs
// * @className: QuartzSchedulerManager
// * @author: Johnny Tang
// * @description: Quartz Job的控制逻辑
// * @date: 2024/1/27 23:25
// * @version: 1.0
// */
//@Component("QuartzManager")
//@Slf4j
//public class QuartzSchedulerManager {
//
//    @Value("${modelServer.url}")
//    String MODEL_SERVER_URL;
//
//    @Value("${modelServer.storageLimit}")
//    Integer STORAGE_LIMIT;
//
//    @Value("${modelServer.caseLimit}")
//    String CASE_LIMIT;
//
//    @Value("${staticData.waterConditionPath}")
//    String WATER_CONDITION_PATH;
//
//    @Value("${waterCondition.url}")
//    String WATER_CONDITION_URL;
//
//    @Autowired
//    @Lazy
//    private Scheduler scheduler;
//
//    public boolean isJobExist(String name, String group) throws SchedulerException {
//        JobKey jobKey = new JobKey(name, group);
//        return scheduler.checkExists(jobKey);
//    }
//
//    // 开始执行定时器
//    public void startJob() throws SchedulerException {
//        log.info("start job here");
////        startTestJob(scheduler);
////        startTestRunningJob(scheduler);
////        startGnssWarningJob(scheduler);
//        startModelCaseDeletePreparingJob();
//        scheduler.start();
//    }
//
//    // 执行modelCase删除准备过程
//    public void startModelCaseDeletePreparingJob() throws SchedulerException {
//        JobDetail jobDetail = JobBuilder.newJob(modelServerShakingHandJob.class)
//                .withIdentity("modelServerHandShake", "modelServerGroup")
//                .build();
//        jobDetail.getJobDataMap().put("modelServerUrl", MODEL_SERVER_URL);
//        jobDetail.getJobDataMap().put("storageLimit", STORAGE_LIMIT);
//        jobDetail.getJobDataMap().put("caseLimit", CASE_LIMIT);
//        // 基于表达式构建触发器
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0/7 * * ?");
//        // CronTrigger表达式触发器 继承于Trigger。TriggerBuilder 用于构建触发器实例
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("modelServerHandShake_trigger", "modelServerTriggerGroup")
//                .withSchedule(cronScheduleBuilder).build();
//        scheduler.scheduleJob(jobDetail, cronTrigger);
//    }
//
//    // 获取Job信息
//    public String getJobInfo(String name, String group) throws SchedulerException {
//        TriggerKey triggerKey = new TriggerKey(name, group);
//        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
//                scheduler.getTriggerState(triggerKey).name());
//    }
//
//    // 修改某个任务的执行时间
//    public boolean modifyJob(String name, String group, String time) throws SchedulerException {
//        Date date = null;
//        TriggerKey triggerKey = new TriggerKey(name, group);
//        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//        String oldTime = cronTrigger.getCronExpression();
//        if (!oldTime.equalsIgnoreCase(time)) {
//            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
//            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
//                    .withSchedule(cronScheduleBuilder).build();
//            date = scheduler.rescheduleJob(triggerKey, trigger);
//        }
//        return date != null;
//    }
//
//    // 暂停所有任务
//    public void pauseAllJob() throws SchedulerException {
//        scheduler.pauseAll();
//    }
//
//    // 暂停某个任务
//    public void pauseJob(String name, String group) throws SchedulerException {
//        JobKey jobKey = new JobKey(name, group);
//        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//        if (jobDetail == null)
//            return;
//        scheduler.pauseJob(jobKey);
//    }
//
//    // 恢复所有任务
//    public void resumeAllJob() throws SchedulerException {
//        scheduler.resumeAll();
//    }
//
//    // 恢复某个任务
//    public void resumeJob(String name, String group) throws SchedulerException {
//        JobKey jobKey = new JobKey(name, group);
//        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//        if (jobDetail == null)
//            return;
//        scheduler.resumeJob(jobKey);
//    }
//
//    // 删除某个任务
//    public void deleteJob(String name, String group) throws SchedulerException {
//        JobKey jobKey = new JobKey(name, group);
//        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//        if (jobDetail == null)
//            return;
//        scheduler.deleteJob(jobKey);
//    }
//
//    // 启动任务1
//    private void startTestJob(Scheduler scheduler) throws SchedulerException {
//        // 通过JobBuilder构建JobDetail实例，JobDetail规定其job只能是实现Job接口的实例
//        JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("testJob", "group1").build();
//        // 基于表达式构建触发器
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/55 * * * ?");
//        // CronTrigger表达式触发器 继承于Trigger。TriggerBuilder 用于构建触发器实例
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("testJob", "tesGroup")
//                .withSchedule(cronScheduleBuilder).build();
//
//        scheduler.scheduleJob(jobDetail, cronTrigger);
//    }
//}
