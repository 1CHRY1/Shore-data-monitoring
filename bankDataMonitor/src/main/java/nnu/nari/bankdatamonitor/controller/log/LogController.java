package nnu.nari.bankdatamonitor.controller.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/7/17 16:23
 * @Description:
 */

@RestController
@RequestMapping("api/v1/log")
@Slf4j
public class LogController {

    @Value("${logpath}")
    String logpath;

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public ResponseEntity<String> insertLogInfo(@RequestBody String logInfo){
        // 插入日志数据
        String filePath = logpath;
        File file = new File(filePath);
        if (file.exists()) {
            try (FileWriter writer = new FileWriter(file)) { // true 表示以追加模式打开
                writer.write(URLDecoder.decode(logInfo,"UTF-8") + System.lineSeparator());
                return ResponseEntity.ok("日志推送成功！");
            } catch (IOException e) {
                log.info(e.getMessage());
//                throw new RuntimeException(e);
                return ResponseEntity.ok("日志推送失败！");
            }
        } else {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(URLDecoder.decode(logInfo,"UTF-8") + System.lineSeparator());
                return ResponseEntity.ok("日志推送成功！");
            } catch (IOException e) {
                log.info(e.getMessage());
//                throw new RuntimeException(e);
                return ResponseEntity.ok("日志推送失败！");
            }
        }
    }
}
