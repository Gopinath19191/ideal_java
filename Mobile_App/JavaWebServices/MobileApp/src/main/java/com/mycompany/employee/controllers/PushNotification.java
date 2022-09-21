package com.mycompany.employee.controllers;

import com.mycompany.employee.dao.EmployeeDao;
import com.mycompany.employee.dto.EmployeeDto;
import com.mycompany.employee.dto.LogsDto;
import com.mycompany.employee.dto.PushNotificationDto;
import com.mycompany.employee.dto.TSNotificationListDto;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.swing.text.html.HTMLDocument;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import org.apache.commons.httpclient.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http2.client.HTTP2Client;
import org.eclipse.jetty.http2.client.http.HttpClientTransportOverHTTP2;
import org.eclipse.jetty.io.ssl.SslConnection;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16363
 */
@Path("/notification")
public class PushNotification extends MultiActionController {

//    public final static String AUTH_KEY_FCM = "AIzaSyBpAL40KWKEKrkHqemvZ3nEpnxnxCyq60E";
    public final static String AUTH_KEY_FCM = "AAAA4-nkmak:APA91bFO0Ylyag11m5OCoIddQ_P5pahAvusZA9rLg_x4e4opQOrIYmiPaQr-g7QmPxz9MR3UL3b78s8cJODZUa5hvaVHdtxe0ARTWWyVlqv0m9HypUXUkbxjfLuRb2Y_hP5mX9tHQADT";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
    private static final String HOST = "gateway.sandbox.push.apple.com";
    private static final int PORT = 2195;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static JSONObject pushNotification(@Context ServletContext config,  String details) throws Exception {

        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;
        
//        JSONObject result = new JSONObject();
        JSONObject json = new JSONObject();
        List<PushNotificationDto> resultList;
        List<PushNotificationDto> resultListSentBack;
        JSONObject inputJson;
        JSONParser parser = new JSONParser();
        inputJson = (JSONObject)parser.parse(details);
        String username = inputJson.get("id").toString();
//        String leaveReqID = inputJson.get("leaveReqID").toString();
        String message = inputJson.get("message").toString();
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao employeeDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(username);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("pushNotifications");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+username+" "+" FromDate :"+message);
        employeeDao.insertTxnLog(lDto);
        
        PushNotificationDto pDto = new PushNotificationDto();
        pDto.setUserName(username);
//        pDto.setDeviceId(device_id);
        resultList = employeeDao.getDeviceRegKeyForPN(pDto);
        resultListSentBack = employeeDao.getDeviceRegKeyForSentBackPN(pDto);
        int devicesCount = employeeDao.nodeviceFound(pDto);
        
        if(resultList.isEmpty() && !message.equalsIgnoreCase("TIMESHEET_SENT_BACK"))
        {
            json.put("success", "false");
            json.put("err_code", "541");                
            json.put("err_message", "There is no device id for the user "+username);
            lDto.setError_code("541");
            lDto.setError_description("There is no device id for the user "+username);
            employeeDao.insertErrorLog(lDto);
        }
        if(devicesCount==0)
        {
            json.put("success", "false");
            json.put("err_code", "541");                
            json.put("err_message", "There is no device id for the user "+username);
            lDto.setError_code("541");
            lDto.setError_description("There is no device id for the user "+username);
            employeeDao.insertErrorLog(lDto);
        }
        else
        {
            String platform="";
            String register_key="";
            if(!message.equalsIgnoreCase("TIMESHEET_SENT_BACK"))
            {
                platform = resultList.get(0).getPlatform();
                register_key = resultList.get(0).getRegister_key();
            }
            else
            {
               platform = resultListSentBack.get(0).getPlatform();
               register_key = resultListSentBack.get(0).getRegister_key(); 
            }
            

    //        @WebService(serviceName = "notification")
            if(platform.equalsIgnoreCase("Android") || message.equalsIgnoreCase("TIMESHEET_SENT_BACK"))
            {
                URL url = new URL(FMCurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "key=" + authKey);
                conn.setRequestProperty("Content-Type", "application/json");

                if(message.equalsIgnoreCase("TIMESHEET_SENT_BACK"))
                {
                    EmployeeDto empDto = new EmployeeDto();
                    JSONObject info = new JSONObject();
                    JSONObject info1 = new JSONObject();
                    List<TSNotificationListDto> sentBackdates;
                    empDto.setUserName(username);
                    int sentBackCount = employeeDao.getNotificationCount(empDto);
                    sentBackdates = employeeDao.getTimesheetNL(empDto);
                    JSONArray sentBackDates = new JSONArray();
                    String lastdate = "";
                    for(TSNotificationListDto resultDate: sentBackdates)
                    {
                        if(!lastdate.equals(resultDate.getTimesheet_date()))
                        {
                            lastdate = resultDate.getTimesheet_date();
                            sentBackDates.add(resultDate.getTimesheet_date());
                        }
                    }
                    
                    //To send the notification to non-active users-seperate query is used
                    //taking the users from the device details - send the notification to users with that device id
                    for(PushNotificationDto regkey: resultListSentBack)
                    {
//                        json.put("to", register_key);
                        if(regkey.getPlatform().equalsIgnoreCase("ios"))
                        continue;
                        json.put("to", regkey.getRegister_key());
                        info1.put("dates", sentBackDates);
                        info.put("tag", String.valueOf(sentBackCount));
                        info.put("title", "iDeal");
                        info.put("body", message);
                        info.put("badge", String.valueOf(sentBackCount));
                        info.put("sound", "false");
//                        info1.put("badge", String.valueOf(sentBackCount));
                        json.put("data", info1);
                        json.put("notification", info);
                        json.put("success", "true");
                        json.put("err_code", "542");                
                        json.put("err_message", "");

                        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                        wr.write(json.toString());
    //                    wr.write(result);
                        wr.flush();
                        conn.getInputStream();
                    }
                }
                if(message.equalsIgnoreCase("LEAVE_APPLIED"))
                {
                    String leaveReqID = inputJson.get("leaveReqID").toString();
                    EmployeeDto empDto = new EmployeeDto();
                    JSONObject info = new JSONObject();
                    JSONObject info1 = new JSONObject();
                    JSONArray dates = new JSONArray();
                    List<EmployeeDto> leaveDates;
                    empDto.setUserName(username);
                    empDto.setLeaveReqID(leaveReqID);
                    leaveDates = employeeDao.getleaveDaysPN(empDto);
                    for(EmployeeDto leaveDate: leaveDates)
                    {
                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("from_date", leaveDate.getLeave_from_date());
                        jsonObj.put("to_date", leaveDate.getLeave_to_date());
                        dates.add(jsonObj);
                    }
                    json.put("to", register_key);
                    info1.put("dates", dates);
                    info.put("tag", "0");
                    info.put("title", "iDeal");
                    info.put("body", message);
                    info.put("sound", "false");
                    json.put("notification", info);
                    json.put("data", info1);
                    json.put("success", "true");
                    json.put("err_code", "542");                
                    json.put("err_message", "");

                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(json.toString());
//                    wr.write(result.toString());
                    wr.flush();
                    conn.getInputStream();
                }
                if(message.equalsIgnoreCase("TIMESHEET_SAVED") || message.equalsIgnoreCase("TIMESHEET_SUBMITTED"))
                {  
                    JSONObject jsonObject = new JSONObject();
                    JSONParser jsonParser = new JSONParser();
                    JSONObject info = new JSONObject();
                    JSONObject info1 = new JSONObject();
                    jsonObject = (JSONObject)jsonParser.parse(details); 
                    JSONArray Tsdates = (JSONArray)jsonObject.get("timesheetDate");
                    JSONArray dates = new JSONArray();
                    for(Object value: Tsdates)
                    {
                        dates.add(value);
                    }
                    json.put("to", register_key);
                    info1.put("dates", dates);
                    info.put("tag", "0");
                    info.put("title", "iDeal");
                    info.put("body", message);
                    info.put("sound", "false");
                    json.put("notification", info);
                    json.put("data", info1);
                    json.put("success", "true");
                    json.put("err_code", "542");                
                    json.put("err_message", "");

                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(json.toString());
//                    wr.write(result.toString());
                    wr.flush();
                    conn.getInputStream();
                }
            }
            if(platform.equalsIgnoreCase("ios") || message.equalsIgnoreCase("TIMESHEET_SENT_BACK"))
            {
                //Local    
//              String certPath ="D:\\Ponraj\\Projects\\iDeal Mobile App\\APNSDevelopmentKey.p12";
                //Beta
              String certPath ="D:\\Tomcat\\apache-tomcat-7.0.72\\Apple_license\\APNSDevelopmentKey.p12";
                //Production
//                String certPath ="C:\\\\Program Files (x86)\\\\Apache Software Foundation\\\\apache-tomcat-8.5.11\\\\apple_certificate\\\\APNSDevelopmentKey.p12";
                String password = "ideal";
                 ApnsService service =
                    APNS.newService()
                    .withCert(certPath, password)
                    .withSandboxDestination()
                    .build();
                if(message.equalsIgnoreCase("TIMESHEET_SENT_BACK"))
                {
                    JSONObject aps = new JSONObject();
                    JSONObject data = new JSONObject();
                    JSONArray date = new JSONArray();
                    JSONObject alert = new JSONObject();
                    EmployeeDto empDto = new EmployeeDto();
                    List<TSNotificationListDto> sentBackdates;
                    empDto.setUserName(username);
                    int sentBackCount = employeeDao.getNotificationCount(empDto);
                    sentBackdates = employeeDao.getTimesheetNL(empDto);
                    JSONArray sentBackDates = new JSONArray();
                    String lastdate = "";
                    for(TSNotificationListDto resultDate: sentBackdates)
                    {
                        if(!lastdate.equals(resultDate.getTimesheet_date()))
                        {
                            lastdate = resultDate.getTimesheet_date();
                            sentBackDates.add(resultDate.getTimesheet_date());
                        }
                    }
                    alert.put("alertTitle", "Entry Sent Back"); 
                    alert.put("alertBody", "A timesheet entry has been sent back");
                    aps.put("alert", alert);
                    aps.put("badge", String.valueOf(sentBackCount));
                    data.put("message", "TIMESHEET_SENT_BACK");
                    data.put("dates", sentBackDates);
                    json.put("aps", aps);
                    json.put("data", data);

                    String payload = APNS.newPayload().badge(sentBackCount).alertBody("Entry Sent Back").sound("default").customField("data", data).build();
                     
                    //To send the notification to non-active users-seperate query is used
                    //taking the users from the device details - send the notification to users with that device id
                    for(PushNotificationDto regkey: resultListSentBack)
                    {
//                      String token = register_key; 
                        if(regkey.getPlatform().equalsIgnoreCase("Android")){
                            continue;
                        }
                        String token = regkey.getRegister_key();
                        service.push(token, payload);
                    }
                }
                if(message.equalsIgnoreCase("LEAVE_APPLIED"))
                {
                    JSONObject aps = new JSONObject();
                    JSONObject data = new JSONObject();
                    JSONObject alert = new JSONObject();
                    JSONArray date = new JSONArray();
                    String leaveReqID = inputJson.get("leaveReqID").toString();
                    EmployeeDto empDto = new EmployeeDto();
                    List<EmployeeDto> leaveDates;
                    empDto.setUserName(username);
                    empDto.setLeaveReqID(leaveReqID);
                    int sentBackCount = employeeDao.getNotificationCount(empDto);
                    leaveDates = employeeDao.getleaveDaysPN(empDto);
                    for(EmployeeDto leaveDate: leaveDates)
                    {
                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("start_date", leaveDate.getLeave_from_date());
                        jsonObj.put("end_date", leaveDate.getLeave_to_date());
                        date.add(jsonObj);
                    }
                    alert.put("alertTitle", "Leave Applied"); 
                    alert.put("alertBody", "A leave has been applied.");
                    aps.put("alert", alert);
                    aps.put("badge", String.valueOf(sentBackCount));
                    data.put("message", "LEAVE_APPLIED");
                    data.put("dates", date);
                    json.put("aps", aps);
                    json.put("data", data);

//                    String payload = APNS.newPayload().alertBody(json.toString()).sound("default").build();
                    String payload = APNS.newPayload().badge(sentBackCount).alertBody("LEAVE_APPLIED").sound("default").customField("data", data).build();
                    String token = register_key;
                    service.push(token, payload);
                }
                if(message.equalsIgnoreCase("TIMESHEET_SAVED") || message.equalsIgnoreCase("TIMESHEET_SUBMITTED"))
                {
                    JSONObject jsonObject = new JSONObject();
                    JSONParser jsonParser = new JSONParser();
                     JSONObject alert = new JSONObject();
                    JSONObject aps = new JSONObject();
                    JSONObject data = new JSONObject();
                    EmployeeDto empDto = new EmployeeDto();
                    empDto.setUserName(username);
                    int sentBackCount = employeeDao.getNotificationCount(empDto);
                    jsonObject = (JSONObject)jsonParser.parse(details); 
                    JSONArray Tsdates = (JSONArray)jsonObject.get("timesheetDate");
                    JSONArray dates = new JSONArray();
                    for(Object value: Tsdates)
                    {
                        dates.add(value);
                    }
//                    json.put("aps", aps);

//                    String payload = APNS.newPayload().alertBody(json.toString()).sound("default").build();
                    
                    String token = register_key;
                    if(message.equalsIgnoreCase("TIMESHEET_SAVED"))
                    {
                        alert.put("alertTitle", "Entry Saved"); 
                        alert.put("alertBody", "A timesheet entry has been saved.");
                        data.put("dates", dates);
                        data.put("message", "TIMESHEET_SAVED");
                        aps.put("badge", String.valueOf(sentBackCount));
                        aps.put("alert", alert);
                        json.put("aps", aps);
                        json.put("data", data);
                        String payload = APNS.newPayload().badge(sentBackCount).alertBody("TIMESHEET_SAVED").sound("default").customField("data", data).build();
                        service.push(token, payload);
                    }
                    if(message.equalsIgnoreCase("TIMESHEET_SUBMITTED"))
                    {
                        alert.put("alertTitle", "Entry Submitted"); 
                        alert.put("alertBody", "A timesheet entry has been submitted.");
                        data.put("dates", dates);
                        data.put("message", "TIMESHEET_SUBMITTED");
                        aps.put("badge", String.valueOf(sentBackCount));
                        aps.put("alert", alert);
                        json.put("aps", aps);
                        json.put("data", data);
                        String payload = APNS.newPayload().badge(sentBackCount).alertBody("TIMESHEET_SUBMITTED").sound("default").customField("data", data).build();
                        service.push(token, payload);
                    }
//                    service.push(token, payload);
                }
            }
        }
            return json;
    }
}
