package nnu.edu.Shore.common.config;

import lombok.extern.slf4j.Slf4j;
import nnu.edu.Shore.common.utils.ProcessUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2023/02/07/17:00
 * @Description:
 */
@Component
@Slf4j
public class TimeTask {

    @Value("${python}")
    String python;

    @Value("${DataProcess}")
    String DataProcess;

    @Value("${DataProcessLog}")
    String logPath;

//    @Scheduled(cron = "0/5 * * * * *")
    @Scheduled(cron = "0 0 12 * * ?")
    public void executePythonJiangsu() {
        try {
            /**
             * @Description:定时获取文件夹数据入库
             * @Author: chry
             * @Date: 2024/1/10
             */
            List<String> commands = new ArrayList<>();
            commands.add(python);
            commands.add(DataProcess + "DataProcess.py");
            Process start = ProcessUtil.exeProcess(commands);
            ProcessUtil.readProcessOutput(start.getInputStream(), System.out);
            start.waitFor();
            // 添加日志输出
            log.info("Python script execution scheduled at: {}", LocalDateTime.now());
            // 创建日志文件
            try (PrintWriter writer = new PrintWriter(new FileWriter(logPath, true))) {
                writer.println("Log message: Python script execution scheduled at " + LocalDateTime.now());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
