package nnu.nari.bankdatamonitor.common.config;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import nnu.nari.bankdatamonitor.jobs.QuartzSchedulerManager;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @projectName: backEnd
 * @package: com.johnny.bank.config
 * @className: QuartzListener
 * @author: Johnny Tang
 * @description: Quartz 设置自动启动
 * @date: 2024/1/27 23:28
 * @version: 1.0
 */
@Configuration
@Slf4j
public class QuartzListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private QuartzSchedulerManager quartzSchedulerManager;

    // 初始启动quartz
    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        try {
            quartzSchedulerManager.startJob();
            log.info("任务已经启动...");
        } catch (SchedulerException e) {
            log.info(e.getMessage(), e.toString());
        }
//        System.out.println("not start quartz");
    }

    // 初始注入scheduler
    @Bean
    public Scheduler scheduler() throws SchedulerException {
//        return StdSchedulerFactory.getDefaultScheduler();
        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
        return schedulerFactoryBean.getScheduler();
    }
}
