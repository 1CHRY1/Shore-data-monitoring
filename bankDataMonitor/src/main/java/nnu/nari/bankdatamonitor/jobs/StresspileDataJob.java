package nnu.nari.bankdatamonitor.jobs;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import nnu.nari.bankdatamonitor.common.utils.BeanUtil;
import nnu.nari.bankdatamonitor.common.utils.InternetUtil;
import nnu.nari.bankdatamonitor.service.record.MonitorRecordService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/9/30 22:32
 * @Description:
 */

@Slf4j
@Component
public class StresspileDataJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String STRESS_PILE_SERVER = dataMap.getString("stressPileServer");
        String STRESS_PILE_USER = dataMap.getString("stressPileUser");
        String STRESS_PILE_PASSWORD = dataMap.getString("stressPilePassword");
        getStresspileData(STRESS_PILE_SERVER, STRESS_PILE_USER, STRESS_PILE_PASSWORD);
    }

    public void getStresspileData(String url, String user, String password) {
        var monitorRecordService = BeanUtil.getBean(MonitorRecordService.class);
        // 定时推送应力桩数据给数据库
        // 登陆
        String loginUrl = url + "/Login/Login";
        JSONObject loginRequestBody = new JSONObject().fluentPut("Username", user).fluentPut("Password", password);
        Map<String, String> CommonHeader = new HashMap<String, String>() {{put("Content-Type", "application/json; charset=utf-8");}};
        JSONObject loginResult = InternetUtil.doPost(loginUrl, CommonHeader ,loginRequestBody).getJSONObject("Result");
        String AccessToken = "Bearer " + loginResult.getString("AccessToken");
        String RefreshToken = loginResult.getString("RefreshToken");
        String UserToken = loginResult.getString("Token");

        // 获取设备树
        Map<String, String> EmptyBody = new HashMap<>();
        Map<String, String> bearerHeader = new HashMap<String, String>() {{put("Content-Type", "application/json; charset=utf-8"); put("UserToken",UserToken);put("Authorization",AccessToken);}};
        String deviceTreeUrl = url + "/Tree/tree";
        JSONArray deviceTreeResult = InternetUtil.doGet(deviceTreeUrl, bearerHeader, EmptyBody).getJSONArray("Result");
        List<String> deviceIdList = Arrays.asList("H231001020", "H231001044", "H231001032", "H231001017", "H231001003", "H231001043", "H231001009");

        // 获取时间
        LocalDateTime start_time = LocalDateTime.now().withSecond(0).withNano(0).minusMinutes(5);
        LocalDateTime end_time = start_time.plusMinutes(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String start_time_str = start_time.format(formatter);
        String end_time_str = end_time.format(formatter);

        // 获取各个设备的所有测点
        for( int i=0; i < 7; i++ ) {
            String deviceId = deviceIdList.get(i);
            String Path = deviceTreeResult.getJSONObject(i+1).getString("Path");
            String deviceNodeUrl = url + "/Component/points/" + Path;
            JSONArray deviceNodeResult = InternetUtil.doGet(deviceNodeUrl,bearerHeader,EmptyBody).getJSONArray("Result");
            JSONArray Points = new JSONArray();
            for (Object deviceNodeObj : deviceNodeResult) {
                JSONObject deviceNode = (JSONObject) deviceNodeObj;
                String id = deviceNode.getString("Id");
                List<Integer> types = Arrays.asList(5);
                JSONObject Point = new JSONObject().fluentPut("PointId",id).fluentPut("EigenTypes",types);
                Points.add(Point);
            }

            // 查询历史数据
            String HistoryDataUrl = url + "/DataManage/datas-v2";
            JSONObject HistoryRequestdata = new JSONObject().fluentPut("DataType",0).fluentPut("StartTime",start_time_str).fluentPut("EndTime",end_time_str).fluentPut("Points",Points);
            JSONArray HistoryDataResult = InternetUtil.doPost(HistoryDataUrl, bearerHeader, HistoryRequestdata).getJSONArray("Result");
            Map<String,String> nameDict = new HashMap<String,String>(){{
                put("-上_Angle","top_angle"); put("-中_Angle","middle_angle"); put("-下_Angle","bottom_angle"); put("-上_E1(εp.max)","top_change"); put("-中_E1(εp.max)","middle_change"); put("-下_E1(εp.max)","bottom_change"); put("-上_Q1(σ.max)","top_power"); put("-中_Q1(σ.max)","middle_power"); put("-下_Q1(σ.max)","bottom_power");
            }};
            List<String> nameList = Arrays.asList("-上_Angle","-中_Angle","-下_Angle","-上_E1(εp.max)","-中_E1(εp.max)","-下_E1(εp.max)","-上_Q1(σ.max)","-中_Q1(σ.max)","-下_Q1(σ.max)");
            JSONObject result = new JSONObject();
            for (Object historyDataObj : HistoryDataResult) {
                JSONObject HistoryData = (JSONObject) historyDataObj;
                String name = HistoryData.getJSONObject("Param").getString("Name");
                if ( nameList.contains(name.substring(1))) {
                    JSONArray dataList = HistoryData.getJSONObject("Datas").getJSONArray("Datas");
                    Integer dataCount = HistoryData.getJSONObject("Datas").getInteger("DataCount");
                    // 若请求的数据为空
                    if (dataCount.equals(0)) {
                        result.put(nameDict.get(name.substring(1)),null);
                        continue;
                    }
                    Double sum = 0.0;
                    for (Object dataObj : dataList) {
                        JSONObject data = (JSONObject) dataObj;
                        sum += data.getDouble("Y");
                    }
                    Double average = sum/dataCount;
                    result.put(nameDict.get(name.substring(1)),average);
                }
            }
            JSONObject insertData = new JSONObject().fluentPut("device_id",deviceId).fluentPut("read_date",start_time_str).fluentPut("param_value",result);

            // 将数据发送数据库
            // String dataUrl = "http://119.45.198.54:9999/api/v1/stresspile/record/insert";
            // JSONObject response = RequestUtil.doPost(dataUrl, CommonHeader, requestData);

            // 直接插入数据库
            monitorRecordService.insertMonitorRecord("stresspile",insertData);
        }
    }
}
