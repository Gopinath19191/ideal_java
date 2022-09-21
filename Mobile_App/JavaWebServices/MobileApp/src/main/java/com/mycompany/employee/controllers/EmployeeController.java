
package com.mycompany.employee.controllers;

import com.mycompany.employee.dao.EmployeeDao;
import com.mycompany.employee.dto.CalendarDto;
import com.mycompany.employee.dto.EmployeeDto;
import com.mycompany.employee.dto.LogsDto;
import com.mycompany.employee.dto.MasterDto;
import com.mycompany.employee.dto.TSNotificationListDto;
import com.mycompany.employee.dto.TimeSheetDto;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16363
 */
@Path("/employee")
public class EmployeeController extends MultiActionController {    
    @Autowired      
    private static final Logger log = Logger.getLogger(EmployeeController.class.getName());

    @Path("/valid")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getLoginDetails(@Context ServletContext config,  String details) throws Exception{
        JSONObject result = new JSONObject();        
        List<EmployeeDto> deviceList;
        JSONObject inputJson;
        JSONParser parser = new JSONParser();
        inputJson = (JSONObject)parser.parse(details);
        String userName = inputJson.get("id").toString();
        String password = inputJson.get("password").toString();
        String deviceId = inputJson.get("device_id").toString();
        String regId = inputJson.get("registration_id").toString();
        String platform = inputJson.get("platform").toString(); 
        Double version=0.00;
        Double app_version=0.00;
        app_version = Double.parseDouble(inputJson.get("version").toString());
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao registerDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        EmployeeDto regisDto = new EmployeeDto();
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(userName);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("getLoginDetails");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+userName+" "+" Password :"+password+" "+" DeviceId :"+deviceId+" "+" RegId :"+regId+" "+" platform :"+platform);
        registerDao.insertTxnLog(lDto);
        regisDto.setUserName(userName); 
        regisDto.setPassword(password);
        regisDto.setDeviceId(deviceId);
        regisDto.setRegId(regId);
        regisDto.setPlatform(platform); 
        regisDto.setVersion(app_version);

        int resultCnt = registerDao.getLoginDetails(regisDto); 
        if(resultCnt > 0)
        {
            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            String token = bytes.toString();
            regisDto.setTokenNo(token);   
            
            deviceList = registerDao.getDeviceDetails(regisDto);
            if(platform.equalsIgnoreCase("Android"))
            {
                version = registerDao.getDeviceAppVersionAndroid(regisDto);
            }
            else{
                version = registerDao.getDeviceAppVersionIOS(regisDto);            
            }
            
            int sessionCheck = registerDao.checkSessionExists(regisDto);
            if(sessionCheck > 0){
                registerDao.updateSessionToken(regisDto);
            }
            else{
                registerDao.insertSessionDetails(regisDto);       
            }
            
            if(deviceList.isEmpty())
            {            
                registerDao.insertDeviceDetails(regisDto);  
            }          
            else
            {     
                registerDao.updateDeviceRegIdDetails(regisDto);                 
            }
            
            result.put("authenticate", "true");
            result.put("token", token);             
            result.put("app_version", version);
            result.put("success", "1");
            result.put("err_code", "0");
            result.put("err_message", "");
            
        }
        else
        {
            result.put("authenticate", "false");
            result.put("success", "0");
            result.put("err_code", "522");
            result.put("err_message", "Invalid username or password");
            lDto.setError_code("522");
            lDto.setError_description("Invalid Username");
            registerDao.insertErrorLog(lDto);
        }
        return result;
    }        
    
    @Path("/notificationCount")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getNotificationCount(@Context ServletContext config,  String details) throws Exception{
        JSONObject result = new JSONObject();   
        JSONObject inputJson;
        JSONParser parser = new JSONParser();
        int count;
        int sessionRes; 
        inputJson = (JSONObject)parser.parse(details);
        String userName = inputJson.get("id").toString();
        String tokenNo = inputJson.get("tokenNo").toString();  
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao registerDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        EmployeeDto regisDto = new EmployeeDto(); 
        regisDto.setUserName(userName); 
        regisDto.setTokenNo(tokenNo); 
         LogsDto lDto = new LogsDto();
        lDto.setEmp_id(userName);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("getNotificationCount");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+userName+" "+" TokenNo :"+tokenNo);
        registerDao.insertTxnLog(lDto);
        int resultCnt = registerDao.isEmpValid(regisDto); 
        if(resultCnt > 0)
        {
            sessionRes = registerDao.isTokenValid(regisDto); 
            if(sessionRes == -1)        
            {
                result.put("success", "false");
                result.put("err_code", "523");             
                result.put("err_message", "Token Invalid");
                lDto.setError_code("523");
                lDto.setError_description("Token Invalid");
                registerDao.insertErrorLog(lDto);
            }    
            else
            {        
                if(sessionRes == 0)
                {
                    result.put("success", "false");
                    result.put("err_code", "521");             
                    result.put("err_message", "Session expired");  
                    lDto.setError_code("521");
                    lDto.setError_description("Session Expired");
                    registerDao.insertErrorLog(lDto);
                    registerDao.deleteToken(regisDto);
                }
                else
                {
                    count = registerDao.getNotificationCount(regisDto); 
                    result.put("success", "true");
                    result.put("err_code", "0");             
                    result.put("err_message", "");  
                    result.put("count", String.valueOf(count));     
                }
            }
        }
        else
        {
            result.put("success", "false");
             result.put("err_code", "522"); 
            result.put("err_message", "Invalid username");
            lDto.setError_code("522");
            lDto.setError_description("Invalid Username");
            registerDao.insertErrorLog(lDto);
        }
             
        return result;
    }
       
    public static String encodeImage(byte[] imageByteArray) throws UnsupportedEncodingException {       
         return Base64.encodeBase64URLSafeString(imageByteArray);
    }
    
    @Path("/profile")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getUserProfile(@Context ServletContext config,  String details) throws Exception{
        JSONObject result = new JSONObject();   
        JSONObject inputJson;
        JSONParser parser = new JSONParser();
        int sessionRes; 
        List<EmployeeDto> resultList;       
        inputJson = (JSONObject)parser.parse(details);
        String userName = inputJson.get("id").toString();
        String tokenNo = inputJson.get("tokenNo").toString();  
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao registerDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        EmployeeDto regisDto = new EmployeeDto();      
        String imageDataString = "";
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(userName);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("getUserProfile");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+userName+" "+" TokenNo :"+tokenNo);
        registerDao.insertTxnLog(lDto);
        regisDto.setUserName(userName); 
        regisDto.setTokenNo(tokenNo);  
        int resultCnt = registerDao.isEmpValid(regisDto); 
        if(resultCnt > 0)
        {
            sessionRes = registerDao.isTokenValid(regisDto);   
            if(sessionRes == -1)        
            {
                result.put("success", "false");                
                result.put("err_code", "523");
                result.put("err_message", "Token Invalid"); 
                lDto.setError_code("523");
                lDto.setError_description("Token Invalid");
                registerDao.insertErrorLog(lDto);
            }    
            else
            {       
                if(sessionRes == 0)
                {
                    result.put("success", "false");                
                    result.put("err_code", "521");
                    result.put("err_message", "Session expired");
                    lDto.setError_code("521");
                    lDto.setError_description("Session Expired");
                    registerDao.insertErrorLog(lDto);
                    registerDao.deleteToken(regisDto);
                }
                else
                {
                    resultList = registerDao.getUserProfile(regisDto);                  
                    if(!resultList.isEmpty())
                    {   
                        result.put("success", "true");                
                        result.put("err_code", "0");
                        result.put("err_message", "");
                        result.put("emp_id", userName);
                        result.put("emp_name", resultList.get(0).getEmpName());
                        result.put("gender", resultList.get(0).getGender());
                        result.put("designation", resultList.get(0).getDesignation());
                        result.put("band", resultList.get(0).getBand());
                        result.put("department", resultList.get(0).getDepartment());
                        result.put("employment_status", resultList.get(0).getEmploymentStatus());
                        result.put("DOB", resultList.get(0).getDob());
                        result.put("DOJ", resultList.get(0).getDoj());
                        result.put("location", resultList.get(0).getLocation());   
                        result.put("photo", resultList.get(0).getEmployeePhoto()); 
                            //Local
//                          File file = new File("D:\\Ponraj\\Projects\\iDeal Mobile App\\iDealProfileImages\\"+resultList.get(0).getEmployeePhoto());
                            //Production
//                          File file = new File("C:\\wampn\\www\\iDeal\\app\\webroot\\uploads\\employee_photos\\"+resultList.get(0).getEmployeePhoto());
                            //Pre-production
//                          File file = new File("C:\\wamp\\www\\idealuat\\app\\webroot\\uploads\\employee_photos\\"+resultList.get(0).getEmployeePhoto());
                            //Beta
                            File file = new File("C:\\wamp\\www\\idealbeta\\app\\webroot\\uploads\\employee_photos\\"+resultList.get(0).getEmployeePhoto());
                            try {
                                double fileSize = file.length();
                                System.out.println("PhotoSize::::::::"+fileSize);
                                
                                if(fileSize> 2000000)
                                {
                                    BufferedImage originalImage = ImageIO.read(file);
                                    int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                                    ByteArrayOutputStream baos=new ByteArrayOutputStream();

                                    BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                                    ImageIO.write(resizeImageJpg, "jpg", baos);
  
                                    baos.flush();
                                    byte[] imageInByte = baos.toByteArray();
                                    imageDataString = new String(Base64.encodeBase64(imageInByte), "UTF-8");
                                }
                                else
                                {
                                    FileInputStream imageInFile = new FileInputStream(file);
                                    byte imageData[] = new byte[(int) file.length()];
                                    imageInFile.read(imageData);
                                    imageDataString = new String(Base64.encodeBase64(imageData), "UTF-8");
                                }
                            } catch (FileNotFoundException e) {
                                System.out.println("Image not found" + e);
                            } catch (IOException ioe) {
                                System.out.println("Exception while reading the Image " + ioe);
                            }
                        result.put("image", imageDataString);
                    }
                    else
                    {
                        result.put("success", "false");                
                         result.put("err_code", "524");
                        result.put("err_message", "No Profile created for the Employee");
                        lDto.setError_code("524");
                        lDto.setError_description("No profile created");
                        registerDao.insertErrorLog(lDto);
                    }        
                }
            }
        }
        else
        {
            result.put("success", "false");                
            result.put("err_code", "522");
            result.put("err_message", "Invalid Username");
            lDto.setError_code("522");
            lDto.setError_description("Invalid Username");
            registerDao.insertErrorLog(lDto);
        }
        return result;
    }
    
    @Path("/getMasterData")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getMasterData(@Context ServletContext config,  String details) throws Exception{
        JSONObject result = new JSONObject();           
        JSONObject inputJson;
        JSONParser parser = new JSONParser();
        List<MasterDto> projectList;
        List<MasterDto> rolesList;
        List<EmployeeDto> wfhDetails; 
        List<EmployeeDto> wfhExcList; 
        List<EmployeeDto> wfhPolicy; 
        int sessionRes;    
        List<MasterDto> regularizationList;
        List<MasterDto> resultListPhases;
        List<MasterDto> resultListPhasesTask; 
        List<MasterDto> nonProjectActivities;
        List<MasterDto> nonProjectActivitiesTasks;
        inputJson = (JSONObject)parser.parse(details);
        String userName = inputJson.get("id").toString();
        String tokenNo = inputJson.get("tokenNo").toString();  
        String startDate = inputJson.get("start_date").toString();  
        String endDate = inputJson.get("end_date").toString(); 
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao employeeDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(userName);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("getMasterData");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+userName+" "+" TokenNo :"+tokenNo+" "+"StartDate :"+startDate+" "+" EndDate :"+endDate);
        employeeDao.insertTxnLog(lDto);
        EmployeeDto employeeDto = new EmployeeDto();      
        employeeDto.setUserName(userName); 
        employeeDto.setTokenNo(tokenNo);   
        employeeDto.setStartDate(startDate); 
        employeeDto.setEndDate(endDate); 
        int resultCnt = employeeDao.isEmpValid(employeeDto); 
        if(resultCnt > 0)
        {
            sessionRes = employeeDao.isTokenValid(employeeDto);   
            if(sessionRes == -1)        
            {
                result.put("success", "false");
                result.put("err_code", "523");                
                result.put("err_message", "Token Invalid"); 
                lDto.setError_code("523");
                lDto.setError_description("Token Invalid");
                employeeDao.insertErrorLog(lDto);
            }    
            else
            { 
                if(sessionRes == 0)
                {
                    result.put("success", "false");
                    result.put("err_code", "521");                
                    result.put("err_message", "Session expired");                   
                    lDto.setError_code("521");
                    lDto.setError_description("Session expired");
                    employeeDao.insertErrorLog(lDto);
                    employeeDao.deleteToken(employeeDto);
                } 
                else
                {
                    projectList = employeeDao.getMasterProjects(employeeDto);
                    nonProjectActivities = employeeDao.getNonProjectPhases();
                    JSONArray list = new JSONArray();   
                    JSONObject json=null;
                    if(!projectList.isEmpty() || !nonProjectActivities.isEmpty())
                    {
                        for(MasterDto obj:projectList) 
                        {
                                json = new JSONObject();
                                json.put("project_id", obj.getProjectID());
                                json.put("project_name", obj.getProjectName());
                                JSONArray roles = new JSONArray();
                                JSONArray phase = new JSONArray();
                                JSONObject phaseOthers = new JSONObject();
                                JSONObject taskOthers = new JSONObject();
                                JSONArray taskOthersNB = new JSONArray();
                                employeeDto.setProject_id(obj.getProjectID());
                                rolesList = employeeDao.getMasterRoles(employeeDto);
                                for(MasterDto subobj:rolesList)
                                { 
                                    JSONObject tempobj=new JSONObject();
                                    tempobj.put("roll_id", subobj.getRoleID());
                                    tempobj.put("roll_name", subobj.getRoleName());
                                    roles.add(tempobj);

                                    MasterDto mDto = new MasterDto();
                                    mDto.setProjectCode(subobj.getProjectCode());
                                    mDto.setStartDate(startDate); 
                                    mDto.setEndDate(endDate);
                                    resultListPhases = employeeDao.getPhaseDtls(mDto); 

                                    for(MasterDto phaseObj:resultListPhases)
                                    {
                                            JSONObject tempobj2=new JSONObject();      
                                            tempobj2.put("phase_id", phaseObj.getPhaseID());
                                            tempobj2.put("phase_name", phaseObj.getPhaseName());
                                            mDto.setPhasePrimarykey(phaseObj.getPhasePrimarykey());
                                            mDto.setStartDate(startDate); 
                                            mDto.setEndDate(endDate);
                                            resultListPhasesTask = employeeDao.getTaskDtls(mDto);
                                            JSONArray task = new JSONArray();
                                            for(MasterDto taskObj:resultListPhasesTask)
                                            {  
                                                JSONObject tempobj1=new JSONObject(); 
                                                tempobj1.put("task_id", taskObj.getTaskID());
                                                tempobj1.put("task_name", taskObj.getTaskName());  
                                                task.add(tempobj1); 
                                            }
                                        tempobj2.put("tasks", task);
                                        phase.add(tempobj2);
                                        json.put("is_role_enabled", subobj.getIs_role_enabled());
                                        json.put("start_date", subobj.getStartDate());                
                                        json.put("end_date", subobj.getEndDate());

                                    }

                                }
                                taskOthers.put("task_id","Non billable activity");
                                taskOthers.put("task_name","Non billable activity");
                                taskOthersNB.add(taskOthers);
                                phaseOthers.put("phase_id", "Non billable activity");
                                phaseOthers.put("phase_name", "Non billable activity");
                                phaseOthers.put("tasks",taskOthersNB);
                                phase.add(phaseOthers);
                                json.put("role", roles);   
                                json.put("project_phases", phase);   
                                list.add(json);
                                result.put("projects",list);   
  
                        }
                            nonProjectActivities = employeeDao.getNonProjectPhases();
                            if(!nonProjectActivities.isEmpty())
                            {
                                JSONArray tempNpa = new JSONArray();
                                JSONArray NProles = new JSONArray();
                                JSONObject nonProjObj = new JSONObject();
                                MasterDto masDto = new MasterDto();
                                for(MasterDto mobj:nonProjectActivities)
                                {
                                    JSONObject NpaObj = new JSONObject();
                                    NpaObj.put("phase_id", mobj.getNphaseId());
                                    NpaObj.put("phase_name", mobj.getNphaseName());
                                    masDto.setNphaseId(mobj.getNphaseId());
                                    JSONArray Nptlist = new JSONArray();
                                    nonProjectActivitiesTasks = employeeDao.getNonProjectTasks(masDto);
                                    for(MasterDto taskObj:nonProjectActivitiesTasks)
                                    {
                                        JSONObject Nptobj = new JSONObject();
                                        Nptobj.put("task_id", taskObj.getNtaskId());
                                        Nptobj.put("task_name", taskObj.getNtaskName());    
                                        Nptlist.add(Nptobj);
                                    }
                                    NpaObj.put("tasks", Nptlist);
                                    tempNpa.add(NpaObj);
                                }
                                nonProjObj.put("project_phases", tempNpa);
                                nonProjObj.put("project_id", "Non_Project_Activity");
                                nonProjObj.put("project_name", "Non Project Activity");
                                nonProjObj.put("start_date", "2008-01-01");
                                nonProjObj.put("end_date", "9999-12-31");
                                nonProjObj.put("is_role_enabled", "false");
                                nonProjObj.put("role", NProles);
                                list.add(nonProjObj);
                                result.put("projects", list);
                            }
                        result.put("success", "true");
                        result.put("err_code", "200");                
                        result.put("err_message", "");
                    }
                    
                    else
                    {   
                        result.put("success", "false");
                         result.put("err_code", 525);
                        result.put("err_message", "No Projects Available");
                        lDto.setError_code("525");
                        lDto.setError_description("No Projects Available");
                        employeeDao.insertErrorLog(lDto);
                    }
                        
                        int wfhEligibility = employeeDao.checkWFHEligibility(employeeDto);
                        wfhDetails = employeeDao.getEmpWfhDetailsForReasons(employeeDto);
                        wfhPolicy =  employeeDao.getWfhPolicy();
                        wfhExcList = employeeDao.getWfhExcList();
                        int emp_month_count = wfhDetails.get(0).getMonth_count();
                        int emp_year_count = wfhDetails.get(0).getYear_count();
                        int policy_month_count = wfhPolicy.get(0).getWHF_Per_Month();
                        int policy_year_count = wfhPolicy.get(0).getWHF_Per_Year();
                        String excUserId = wfhExcList.get(0).getEmp_id();
                        String is_allowed = "";
                    
                        regularizationList = employeeDao.getRegularizationDtls(); 
                        if(!regularizationList.isEmpty())
                        {
                            JSONArray tempRegularization = new JSONArray();
                            
                            for(MasterDto obj:regularizationList) 
                            { 
                                JSONObject tempobj=new JSONObject();

                                    if(obj.getParentId().equals("638") && obj.getConfigurationKey().equalsIgnoreCase("w") ){
                                        if(emp_month_count >= policy_month_count-1 && emp_year_count >= policy_year_count-1 && excUserId != null && !excUserId.equals(userName) || wfhEligibility==0){
                                          is_allowed = "0";
                                        }
                                    }
                                    else
                                    {
                                        is_allowed = "1";
                                    }
                                tempobj.put("is_allowed", is_allowed);    
                                tempobj.put("reason_id", obj.getParentId());
                                tempobj.put("reason_short_key", obj.getConfigurationKey());
                                tempobj.put("reason_text", obj.getConfigurationValue());
                                tempRegularization.add(tempobj);
                            }                               
                           result.put("reasons",tempRegularization);
                        }
                }
            }
        }
        return result;
    }
            
    @Path("/resetPassword")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject resetPassword(@Context ServletContext config,  String details) throws Exception{
        JSONObject result = new JSONObject();
        JSONObject inputJson;
        JSONParser parser = new JSONParser();
        inputJson = (JSONObject)parser.parse(details);
        String userName = inputJson.get("id").toString();
        String password = inputJson.get("old_password").toString();
        String newPassword = inputJson.get("new_password").toString();
        String confirmPassword = inputJson.get("confirm_password").toString(); 
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao registerDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        EmployeeDto regisDto = new EmployeeDto(); 
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(userName);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("resetPassword");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+userName+" "+" Password :"+password+" "+"NewPassword :"+newPassword+" "+" ConfirmPassword :"+confirmPassword);
        registerDao.insertTxnLog(lDto);
        regisDto.setUserName(userName); 
        regisDto.setPassword(password);
        regisDto.setNewPassword(newPassword);
        regisDto.setConfirmPassword(confirmPassword);                  
        int resultCnt = registerDao.getLoginDetails(regisDto); 
        if(resultCnt > 0)
        {
            if(!newPassword.equals(confirmPassword))
            {
                result.put("success", "false");
                result.put("err_code", "526");
                result.put("err_message", "new_password and confirm_password should be same");
                lDto.setError_code("526");
                lDto.setError_description("New password and confirm password should be same");
                registerDao.insertErrorLog(lDto);
            }
            else
            {
                registerDao.resettingPassword(regisDto);             
                result.put("success", "true");
                result.put("err_code", "0");             
                result.put("err_message", "");
                log.info("Username :"+userName+" reset password successfull");
            }             
        }
        else
        {
            result.put("success", "false");
            result.put("err_code", "522");
            result.put("err_message", "Invalid username or password");
            lDto.setError_code("522");
            lDto.setError_description("Invalid username and password");
            registerDao.insertErrorLog(lDto);
        } 
        return result;
    } 
    
    @Path("/logout")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject logout(@Context ServletContext config,  String details) throws Exception{
        JSONObject result = new JSONObject();
        JSONObject inputJson;
        JSONParser parser = new JSONParser();
        List<EmployeeDto> deviceList;
        int sessionRes;        
        inputJson = (JSONObject)parser.parse(details);
        String userName = inputJson.get("id").toString();
        String deviceId = inputJson.get("device_id").toString();
        String platform = inputJson.get("platform").toString();
        String token = inputJson.get("token").toString(); 
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao registerDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        EmployeeDto regisDto = new EmployeeDto();
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(userName);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("logout");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+userName+" "+" DeviceID :"+deviceId+" "+"Platform :"+platform+" "+" Token :"+token);
        registerDao.insertTxnLog(lDto);
        regisDto.setUserName(userName); 
        regisDto.setDeviceId(deviceId);
        regisDto.setPlatform(platform);
        regisDto.setTokenNo(token);
        
        int resultCnt = registerDao.isEmpValid(regisDto); 
        if(resultCnt > 0)
        {  
            deviceList = registerDao.getDeviceDetails(regisDto);
            if(!deviceList.isEmpty())
            {
                registerDao.deleteDeviceDtls(regisDto);
                sessionRes = registerDao.isTokenValid(regisDto);   
                if(sessionRes == -1)        
                {
                    result.put("success", "false");
                    result.put("err_code", "523");                
                    result.put("err_message", "Token Invalid");  
                    lDto.setError_code("523");
                    lDto.setError_description("Token Invalid");
                    registerDao.insertErrorLog(lDto);
                }    
                else
                {
                    result.put("success", "true");
                    result.put("err_code", "0");
                    result.put("err_message", "");              
                    registerDao.deleteToken(regisDto);
                }
            }
            else
            {
                result.put("success", "false");
                result.put("err_code", "538");
                result.put("err_message", "Invalid deviceId");
                lDto.setError_code("538");
                lDto.setError_description("Invalid deviceId");
                registerDao.insertErrorLog(lDto);
            }
        }
        else
        {
            result.put("success", "false");
            result.put("err_code", "522");
            result.put("err_message", "Invalid username");
            lDto.setError_code("522");
            lDto.setError_description("Invalid username");
            registerDao.insertErrorLog(lDto);
        } 
        return result;
    } 
    
    @Path("/tsEntries")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getTimesheetDetailsAndEntries(@Context ServletContext config, String details) throws Exception{
        JSONObject result = new JSONObject();
        JSONObject inputJson = new JSONObject();
        JSONParser parser = new JSONParser();
        int sessionRes; 
        List<TimeSheetDto> resultList; 
        inputJson = (JSONObject)parser.parse(details);
        String empNumber = inputJson.get("id").toString();
        String fromDate = inputJson.get("start_date").toString();
        String toDate = inputJson.get("end_date").toString();
        String tokenNo = inputJson.get("tokenNo").toString();  
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao employeeDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(empNumber);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("getTimesheetDetailsAndEntries");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+empNumber+" "+" FromDate :"+fromDate+" "+"ToDate :"+toDate+" "+" Token :"+tokenNo);
        employeeDao.insertTxnLog(lDto);
        TimeSheetDto timesheetDto = new TimeSheetDto();
        EmployeeDto employeeDto =  new EmployeeDto();
        employeeDto.setUserName(empNumber);
        employeeDto.setTokenNo(tokenNo);
        employeeDto.setStartDate(fromDate);
        employeeDto.setEndDate(toDate);
        int resultCnt = employeeDao.isEmpValid(employeeDto); 
        if(resultCnt > 0)
        {
            sessionRes = employeeDao.isTokenValid(employeeDto);   
            if(sessionRes == -1)        
            {
                result.put("success", "false");
                 result.put("err_code", "523");                
                result.put("err_message", "Token Invalid");
                lDto.setError_code("523");
                lDto.setError_description("Token Invalid");
                employeeDao.insertErrorLog(lDto);
            }    
            else
            {       
                if(sessionRes == 0)
                {
                    result.put("success", "false");
                    result.put("err_code", "521");                
                    result.put("err_message", "Session expired");                   
                    lDto.setError_code("521");
                    lDto.setError_description("Session expired");
                    employeeDao.insertErrorLog(lDto);
                    employeeDao.deleteToken(employeeDto);
                } 
                else
                {
                    resultList = employeeDao.getTimesheetDetailsAndEntries(employeeDto);                   
                    JSONArray list = new JSONArray();
                    if(resultList.isEmpty())
                    { 
                        result.put("success", "false");
                       result.put("err_code", "527");                
                        result.put("err_message", "No timesheet entries available");
                        lDto.setError_code("527");
                        lDto.setError_description("No timesheet entries available");
                        employeeDao.insertErrorLog(lDto);
                    }                    
                    else
                    {
                        JSONObject json=null;
                        String lastInserteddate="";
                        for(TimeSheetDto obj:resultList) 
                        {  
                            if(!lastInserteddate.equalsIgnoreCase(obj.getSelected_date()))
                            {
                                json=new JSONObject();
                                lastInserteddate=obj.getSelected_date();
                                json.put("date", obj.getSelected_date());
                                json.put("available_hours", obj.getAvailableHrs());
                                json.put("attendance_hours", obj.getAttendance_hours());
                                if(obj.getTotalHrs() == null){
                                    json.put("timesheet_availability", "0");
                                }
                                else{
                                    json.put("timesheet_availability", "1");
                                }
                                json.put("timesheet_hours", obj.getTotalHrs());
                                json.put("regularization_hours", obj.getRegularizationHrs());
                                json.put("is_holiday", obj.getIs_holiday());
                                JSONArray templist=new JSONArray();
                                for(TimeSheetDto subobj:resultList)
                                {
                                    JSONObject tempobj=new JSONObject();
                                    if((subobj.getSelected_date().equalsIgnoreCase(json.get("date").toString())) && subobj.getStatus() != null&& subobj.getStatus() != "")
                                    {
                                        tempobj.put("timesheet_id", subobj.getTimesheet_id());
                                        tempobj.put("project_id",subobj.getProject_id());
                                        tempobj.put("project_name", subobj.getProject_name());
                                        tempobj.put("project_status", subobj.getProject_status());
                                        tempobj.put("timesheet_hours",subobj.getTimesheet_hours());
                                        tempobj.put("shift_id",subobj.getShift_id());
                                        tempobj.put("role_id",subobj.getRole_id());
                                        tempobj.put("phase_id",subobj.getPhase_id());
                                        tempobj.put("task_id",subobj.getTask_id());
                                        tempobj.put("is_regularized",subobj.getIs_regularized());
                                        tempobj.put("reasons",subobj.getReasons());
                                        tempobj.put("remark",subobj.getRemark());
                                        tempobj.put("status", subobj.getStatus());
                                        tempobj.put("reasonCheck", subobj.getReasonCheck());
                                        templist.add(tempobj);                              
                                    }     
                                }
                                json.put("entries", templist);
                                
                                list.add(json);
                                result.put("data",list);
                                
                                result.put("success", "true");
                                result.put("err_code", "200");                
                                result.put("err_message", "");
                            }     
                        }
                    }   
                }
            }
        }
        else
        {
           result.put("success", "false");
           result.put("err_code", "522");                
           result.put("err_message", "Invalid Username");
           lDto.setError_code("522");
            lDto.setError_description("Invalid Username");
            employeeDao.insertErrorLog(lDto);
        }       
        return result;
    }
    
    @Path("/rejectedTsList")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getRejectedTsNotificationList(@Context ServletContext config, String details)throws Exception{
        JSONObject rjList = new JSONObject();
        JSONObject inputJson;
        JSONParser parser = new JSONParser();
        inputJson = (JSONObject)parser.parse(details);
        int sessionRes;
        List<TSNotificationListDto> resultList;
        String empNumber = inputJson.get("id").toString();
        String tokenNo = inputJson.get("tokenNo").toString();
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao registerDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(empNumber);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("getRejectedTsNotificationList");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+empNumber+" "+" Token :"+tokenNo);
        registerDao.insertTxnLog(lDto);
        EmployeeDto registerDto =  new EmployeeDto();
        TSNotificationListDto tSNotificationListDto = new TSNotificationListDto();       
        registerDto.setUserName(empNumber);
        registerDto.setTokenNo(tokenNo);         
        int resultCnt = registerDao.isEmpValid(registerDto); 
        if(resultCnt > 0)
        {
            sessionRes = registerDao.isTokenValid(registerDto);   
            if(sessionRes== -1)        
            {               
                rjList.put("success", "false");
                 rjList.put("err_code", "523");                
                rjList.put("err_message", "Token Invalid");
                lDto.setError_code("523");
                lDto.setError_description("Token Invalid");
                registerDao.insertErrorLog(lDto);
            }    
            else
            {      
                if(sessionRes== 0)
                {
                    rjList.put("success", "false");
                    rjList.put("err_code", "521");                
                    rjList.put("err_message", "Session expired");
                    lDto.setError_code("521");
                    lDto.setError_description("Session expired");
                    registerDao.insertErrorLog(lDto);
                    registerDao.deleteToken(registerDto);
                } 
                else
                {
                    resultList = registerDao.getTimesheetNL(registerDto);                   
                    if(!resultList.isEmpty())
                    {
                        rjList.put("success", "true");
                         rjList.put("err_code", "200");
                        rjList.put("err_message", "");
                        
                        for(int i=0;i<resultList.size();i++) {
                             rjList.put("list", resultList);
                        }        
                    }                       
                     else
                    {                   
                        rjList.put("success", "false");
                        rjList.put("err_code", "528");                     
                        rjList.put("err_message", "No sent back entries available"); 
                        lDto.setError_code("528");
                        lDto.setError_description("No sent back entries available");
                        registerDao.insertErrorLog(lDto);
                        
                    }
                }
            }
    }
         else
            {
                rjList.put("success", "false");
                rjList.put("err_code", "522");                
                rjList.put("err_message", "Invalid Username");                
                lDto.setError_code("522");
                lDto.setError_description("Invalid Username");
                registerDao.insertErrorLog(lDto);
            }
        return rjList;
    }
    
    
    @Path("/calendar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getCurrentCalendar(@Context ServletContext config, String details) throws Exception{
        JSONObject result = new JSONObject();
        JSONObject inputJson = new JSONObject();
        JSONParser parser = new JSONParser();
        inputJson = (JSONObject)parser.parse(details);
        int SessionRes;
        List<CalendarDto> resultList;
        String empNumber = inputJson.get("id").toString();
        String fromDate = inputJson.get("start_date").toString();
        String toDate = inputJson.get("end_date").toString();
        String tokenNo = inputJson.get("tokenNo").toString(); 
        final WebApplicationContext cxt = WebApplicationContextUtils.getRequiredWebApplicationContext(config);
        EmployeeDao employeeDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(empNumber);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("getCurrentCalendar");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+empNumber+" "+" FromDate :"+fromDate+" "+"ToDate :"+toDate+" "+" Token :"+tokenNo);
        employeeDao.insertTxnLog(lDto);
        EmployeeDto employeeDto =  new EmployeeDto();
        employeeDto.setUserName(empNumber);
        employeeDto.setTokenNo(tokenNo);
        employeeDto.setStartDate(fromDate);
        employeeDto.setEndDate(toDate);
        int resultCnt = employeeDao.isEmpValid(employeeDto);
        if(resultCnt >0)
        {
            SessionRes = employeeDao.isTokenValid(employeeDto);
            if(SessionRes== -1)        
            {
                result.put("success", "false");
                 result.put("err_code", "523");                
                result.put("err_message", "Token Invalid"); 
                lDto.setError_code("523");
                lDto.setError_description("Token Invalid");
                employeeDao.insertErrorLog(lDto);
            } 
            else
            {      
                if(SessionRes== 0)
                {
                    result.put("success", "false");
                    result.put("err_code", "521");                
                    result.put("err_message", "Session expired");
                    lDto.setError_code("521");
                    lDto.setError_description("Session Expired");
                    employeeDao.insertErrorLog(lDto);
                    employeeDao.deleteToken(employeeDto);
                }
                else
                {
                    resultList = employeeDao.getCurrentCalendar(employeeDto);
                    JSONArray list= new JSONArray();
                    if(resultList.isEmpty())
                    {
                        result.put("success", "false");
                        result.put("err_code", "529");                
                        result.put("err_message", "No calendar available");
                        lDto.setError_code("529");
                        lDto.setError_description("No Calendar Available");
                        employeeDao.insertErrorLog(lDto);
                    }
                    else
                    {
                        JSONObject json=null;
                        for(CalendarDto obj:resultList)
                        {
                            json = new JSONObject();
                            json.put("timesheet_date", obj.getTimesheet_date());
                            json.put("available_hours", obj.getAvailable_hours());
                            json.put("is_holiday", obj.getIs_holiday());
                            json.put("is_weekend", obj.getIs_weekend());
                            json.put("leave_status", obj.getLeave_status());
                            json.put("entries_status", obj.getEntries_status());
                            json.put("attendance_hours", obj.getAttendance_hours());
                            json.put("regularization_hours", obj.getRegularizationHrs());
                            list.add(json);
                            result.put("calendar", list);
                            result.put("success", "true");
                            result.put("err_code", "200");
                            result.put("err_message", "");
                        }
                    }
                }
            }
            
        }
        else
        {
            result.put("success", "false");
            result.put("err_code", "522");                
            result.put("err_message", "Invalid Username");
            lDto.setError_code("522");
            lDto.setError_description("Invalid Username");
            employeeDao.insertErrorLog(lDto);
        } 
        return result;
    }
    
    @Path("/submitTimeSheetEntries")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject submitTimeSheetEntry(@Context ServletContext config, String jsonStr) throws Exception{       
        JSONObject result = new JSONObject();       
        int sessionRes; 
        List<EmployeeDto> profile; 	
        List<EmployeeDto> wfhDetails; 
        List<EmployeeDto> wfhExcList; 
        List<EmployeeDto> wfhPolicy; 
        JSONObject jsonObject = new JSONObject();
        JSONParser parser = new JSONParser();      
        jsonObject = (JSONObject)parser.parse(jsonStr);   
        String userName = (String) jsonObject.get("id");
        String tokenNo = (String) jsonObject.get("tokenNo");         
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao employeeDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(userName);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("submitTimeSheetEntry");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+userName+" "+" Token :"+tokenNo);
        EmployeeDto employeeDto = new EmployeeDto();      
        employeeDto.setUserName(userName); 
        employeeDto.setTokenNo(tokenNo);         
        int resultCnt = employeeDao.isEmpValid(employeeDto); 
        if(resultCnt > 0)
        {
            sessionRes = employeeDao.isTokenValid(employeeDto);   
            if(sessionRes==-1)        
            {
                result.put("success", "false");
                 result.put("err_code", "523");                
                result.put("err_message", "Token Invalid"); 
                lDto.setError_code("523");
                lDto.setError_description("Token Invalid");
                employeeDao.insertErrorLog(lDto);
            }    
            else
            {  
                if(sessionRes==0)
                {
                    result.put("success", "false");
                    result.put("err_code", "521");                
                    result.put("err_message", "Session expired");
                    lDto.setError_code("521");
                    lDto.setError_description("Session Expired");
                    employeeDao.insertErrorLog(lDto);
                    employeeDao.deleteToken(employeeDto);
                } 
                else
                {            
                    profile = employeeDao.getUserProfile(employeeDto);
                    JSONArray slideContent = (JSONArray) jsonObject.get("dates");                
                    Iterator i = slideContent.iterator();     
                    String flag = "0";
                    while (i.hasNext()) {
                        JSONObject slide = (JSONObject) i.next();   
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date timesheetDate = sdf.parse((String)slide.get("date"));
                        String timeSheetDateStr =sdf.format(sdf.parse(slide.get("date").toString()));  
                        employeeDto.setTimeSheetDateStr(timeSheetDateStr);
                        employeeDto.setTimeSheetDate(timesheetDate);
                        String availableHours = slide.get("available_hours").toString();
                        employeeDto.setAvailable_hours(availableHours);
                        String att_hours = slide.get("attendance_hours").toString();
                        employeeDto.setAttendance_hours(att_hours);
                        String timesheetHours = slide.get("timesheet_hours").toString();
                        employeeDto.setTotalTimesheet_hours(timesheetHours);
                        String regularizationHours = slide.get("regularization_hours").toString();
                        employeeDto.setRegularization_hours(regularizationHours);  
                        String status = slide.get("status").toString();
                        employeeDto.setStatus(status);  
                        String is_holiday = slide.get("is_holiday").toString();
                        employeeDto.setIs_holiday(is_holiday); 
                        String is_HfLeave = slide.get("is_HfLeave").toString();
                        employeeDto.setIs_HfLeave(is_HfLeave);
                        String is_WeekEnd = slide.get("is_WeekEnd").toString();
                        employeeDto.setIs_WeekEnd(is_WeekEnd); 
                        int resCount1 = employeeDao.isEntry(employeeDto);
                        int resCount = employeeDao.isEntrySubmitted(employeeDto);
                        String isEmpLeave = employeeDao.isEmpLeaveApplied(employeeDto);


                         if(!(isEmpLeave.equalsIgnoreCase("FULL") && is_WeekEnd.equals("0") && is_holiday.equals("0")))
                        {
                            log.info("resCount"+resCount);
                            if(resCount == 0 && resCount1 != 0)
                            {
                                result.put("success", "false");
                                result.put("err_code", "530");                
                                result.put("err_message", "Submitted/Approved timesheet entries cannot be changed");  
                                lDto.setError_code("530");
                                lDto.setError_description("Submitted/Approved timesheet entries cannot be changed");
                                employeeDao.insertErrorLog(lDto);
                                log.info("Username :"+userName+" Submitted/Approved timesheet entries cannot be changed");
                            }  
                            else
                            { 
                                JSONArray slideContentEntries = (JSONArray) slide.get("entries");
                                if (slideContentEntries.isEmpty()) {
                                    result.put("success", "false");
                                    result.put("err_code", "531");
                                    result.put("err_message", "No Entries available for the date - " + timesheetDate);
                                    lDto.setError_code("531");
                                    lDto.setError_description("No Entries available for " + timesheetDate);
                                    employeeDao.insertErrorLog(lDto);
                                }
                                else
                                {
                                    Iterator it = slideContentEntries.iterator();
                                    Iterator it1 = slideContentEntries.iterator();
                                    int timeSheetHours = 0;
                                    int timeSheetMinutes = 0;   
                                    String reasonsExistChk = "";
                                    String totalTimesheetHrs = "";
                                    String totTimesheetHours1= "";
                                    String timesheet_hours1= "";
                                    JSONArray slideContentEntries1 = (JSONArray) slide.get("entries"); 
                                    Iterator itr = slideContentEntries1.iterator();
                                    while (itr.hasNext()) 
                                    {
                                        JSONObject slide2 = (JSONObject) itr.next();                                
                                        reasonsExistChk = reasonsExistChk + slide2.get("reasons").toString();
                                    }
//                                    while (it1.hasNext())
                                    for(int j=0; j<slideContentEntries.size(); j++)
                                    {
                                        JSONObject obj = (JSONObject)slideContentEntries.get(j);
                                        timesheet_hours1 = (String)obj.get("timesheet_hours");
                                        String temp[] = timesheet_hours1.split(":");
                                        int hours = Integer.parseInt(temp[0]);
                                        int minutes = Integer.parseInt(temp[1]);
                                        timeSheetHours = timeSheetHours + hours;
                                        timeSheetMinutes = timeSheetMinutes + minutes;

                                        if (timeSheetMinutes >= 60) {
                                          timeSheetHours ++;
                                          timeSheetMinutes = timeSheetMinutes % 60;
                                        }   
                                        totalTimesheetHrs = timeSheetHours+"."+timeSheetMinutes;
                                    }
                                        
                                    
                                    while (it.hasNext())
                                    {
                                        flag = "0";
                                        JSONObject slide1 = (JSONObject) it.next();
                                        String project_id = slide1.get("project_id").toString();
                                        employeeDto.setProject_id(project_id); 
                                        String timesheet_hours = (String)slide1.get("timesheet_hours");                                       
                                        employeeDto.setTimesheet_hours(timesheet_hours);
                                        String shift_id = slide1.get("shift_id").toString();
                                        employeeDto.setShift_id(shift_id);
                                        String role_id = (String)slide1.get("role_id");
                                        String phase_id = (String)slide1.get("phase_id");
                                        String task_id = (String)slide1.get("task_id");
                                        String timesheet_id = slide1.get("timesheet_id").toString();
                                        employeeDto.setRole_id((String)slide1.get("role_id"));
                                        employeeDto.setPhase_id((String)slide1.get("phase_id"));
                                        employeeDto.setTask_id((String)slide1.get("task_id"));								
                                        String is_regularized = slide1.get("is_regularized").toString();
                                        employeeDto.setIs_regularized(is_regularized);
                                        String reasons = slide1.get("reasons").toString();
                                        employeeDto.setReasons(reasons);
                                        
                                        if(phase_id == null || phase_id == "" || phase_id.isEmpty() ||task_id == null || task_id == "" || task_id.isEmpty())
                                        {
                                            LogsDto ElogDto = new LogsDto();
                                            ElogDto.setError_code("511");
                                            ElogDto.setError_description("NoPhaseIDorTaskID");
                                            ElogDto.setModule_name("Timesheet_entries");
                                            ElogDto.setFunction_name("submitTimeSheetEntry");
                                            ElogDto.setTxn_type_name("POST");
                                            ElogDto.setEmp_id(userName);
                                            ElogDto.setData("Username :"+userName+" "+" project_id :"+project_id+" "+" timesheet_id :"+timesheet_id+" "+" timeSheetDate :"+timeSheetDateStr+" "+" phase_id :"+phase_id+" "+" task_id :"+task_id+" "+" role_id :"+role_id+" "+" timesheet_hours :"+timesheet_hours+" "+" att_hours :"+att_hours+" "+" shift_id :"+shift_id);
                                            employeeDao.insertErrorLog(ElogDto);
                                        }
                                        if(role_id == null || role_id == "" || role_id.isEmpty())
                                        {
                                           LogsDto ElogDto = new LogsDto();
                                            ElogDto.setError_code("511");
                                            ElogDto.setError_description("NoRoleID");
                                            ElogDto.setModule_name("Timesheet_entries");
                                            ElogDto.setFunction_name("submitTimeSheetEntry");
                                            ElogDto.setTxn_type_name("POST");
                                            ElogDto.setEmp_id(userName);
                                            ElogDto.setData("Username :"+userName+" "+" project_id :"+project_id+" "+" timesheet_id :"+timesheet_id+" "+" timeSheetDate :"+timeSheetDateStr+" "+" role_id :"+role_id);
                                            employeeDao.insertErrorLog(ElogDto); 
                                        }
                                        if(status.equalsIgnoreCase("m") && Double.parseDouble(totalTimesheetHrs) > 23.59)
                                        {
                                            result.put("success", "false");
                                            result.put("err_code", "537");
                                            result.put("err_message", "The user cannot enter time greater than 23:59 hours for a Timesheet Entry.");
                                            lDto.setError_code("537");
                                            lDto.setError_description("User cannot enter time greater than 23:59 hours for a timesheet entry");
                                            employeeDao.insertErrorLog(lDto);
                                            flag = "1";
                                            break;
                                        }

                                        if(slide1.get("reasons").toString().trim().equals("Work From Home"))
                                        {                                    
                                            wfhDetails = employeeDao.getEmpWfhDetails(employeeDto);
                                            wfhPolicy = employeeDao.getWfhPolicy();
                                            wfhExcList = employeeDao.getWfhExcList();
                                            int emp_month_count = wfhDetails.get(0).getMonth_count();
                                            int emp_year_count = wfhDetails.get(0).getYear_count();
                                            int policy_month_count = wfhPolicy.get(0).getWHF_Per_Month();
                                            int policy_year_count = wfhPolicy.get(0).getWHF_Per_Year();
                                            String excUserId = wfhExcList.get(0).getEmp_id();

                                            if(emp_month_count >= policy_month_count-1 && emp_year_count >= policy_year_count-1 && excUserId != null && !excUserId.equals(userName))
                                            {
                                                result.put("success", "false");
                                                result.put("err_code", "533");                
                                                result.put("err_message", "You are exceeding 'Work from home' policy limit for the Month/Year. Please refer policy document.");
                                                lDto.setError_code("533");
                                                lDto.setError_description("Exceeded WFH Policy");
                                                employeeDao.insertErrorLog(lDto);
                                                flag = "1";
                                                break; 
                                            }
                                        }
                                        if(profile.get(0).getLocation().trim().equals("SKCL Towers - Triton Square(HTL - India - Chennai)") || profile.get(0).getLocation().trim().equals("HINDUJA TECH LTD. GIGA SPACE,DELTA-1, 3rd Floor,(HTL - India - Pune)"))          
                                        {
                                            if(status.equalsIgnoreCase("m") && att_hours.equals("00:00") && reasonsExistChk.equals(""))
                                            {
                                                result.put("success", "false");
                                                result.put("err_code", "534");                
                                                result.put("err_message", "Please select the regularization reason"); 
                                                lDto.setError_code("534");
                                                lDto.setError_description("Please select regularization reason");
                                                employeeDao.insertErrorLog(lDto);
                                                flag = "1";
                                                break;                                        
                                            }
                                            if(status.equalsIgnoreCase("m") && !att_hours.equals("00:00") && !att_hours.equals(timesheetHours) && reasonsExistChk.equals(""))  
                                            {
                                                result.put("success", "false");
                                                result.put("err_code", "534");                
                                                result.put("err_message", "Please select the regularization reason");  
                                                lDto.setError_code("534");
                                                lDto.setError_description("Please select regularization reason");
                                                employeeDao.insertErrorLog(lDto);
                                                flag = "1";
                                                break;
                                            }
                                            if(status.equalsIgnoreCase("m") && att_hours.equals(availableHours) && !att_hours.equals(timesheetHours) && reasonsExistChk.equals(""))  
                                            {
                                                result.put("success", "false");
                                                result.put("err_code", "534");                
                                                result.put("err_message", "Please select the regularization reason");  
                                                lDto.setError_code("534");
                                                lDto.setError_description("Please select regularization reason");
                                                employeeDao.insertErrorLog(lDto);
                                                flag = "1";
                                                break;
                                            }
                                            if(status.equalsIgnoreCase("m") && !att_hours.equals("00:00") && (reasonsExistChk.equals("")) && is_WeekEnd.equals("0") && is_holiday.equals("0") && is_HfLeave.equals("0"))  
                                            {
                                                    DateFormat df = new java.text.SimpleDateFormat("hh:mm");
                                                    Date available_hours = df.parse(slide.get("available_hours").toString());
                                                    Date attendance_hours = df.parse(slide.get("attendance_hours").toString());
                                                    long diff = attendance_hours.getTime() - available_hours.getTime();                                
                                                    long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
                                                    if(minutes < -15 || minutes > 60) 
                                                    {  
                                                        result.put("success", "false");
                                                        result.put("err_code", "534");                
                                                        result.put("err_message", "Please select the regularization reason");  
                                                        lDto.setError_code("534");
                                                        lDto.setError_description("Please select regularization reason");
                                                        employeeDao.insertErrorLog(lDto);
                                                        flag = "1";
                                                        break;
                                                    }
                                            }
                                            if(status.equalsIgnoreCase("m") && !att_hours.equals("00:00") && (reasonsExistChk.equals("")) && (is_WeekEnd.equals("1") || is_holiday.equals("1")))
                                            {
                                                String attendance_hrs = slide.get("attendance_hours").toString().replace(":", ".");
                                                String available_hrs = slide.get("available_hours").toString().replace(":", ".");

                                                if(Double.parseDouble(attendance_hrs) > Double.parseDouble(available_hrs))
                                                {  
                                                    result.put("success", "false");
                                                    result.put("err_code", "534");                
                                                    result.put("err_message", "Please select the regularization reason");
                                                    lDto.setError_code("534");
                                                    lDto.setError_description("Please select regularization reason");
                                                    employeeDao.insertErrorLog(lDto);
                                                    flag = "1";
                                                    break;
                                                }
                                            }
                                        }
                                        else
                                        {
                                            if(status.equalsIgnoreCase("m") && att_hours.equals("00:00"))
                                            {
                                                if((!timesheetHours.equals(availableHours)) && (reasonsExistChk.equals("")))
                                                {
                                                    result.put("success", "false");
                                                    result.put("err_code", "534");                
                                                    result.put("err_message", "Please select the regularization reason");  
                                                    lDto.setError_code("534");
                                                    lDto.setError_description("Please select regularization reason");
                                                    employeeDao.insertErrorLog(lDto);
                                                    flag = "1";
                                                    break;
                                                }
                                            }
                                            if(status.equalsIgnoreCase("m") && att_hours.equals(availableHours) && !att_hours.equals("00:00") && reasonsExistChk.equals(""))  
                                            {
                                                    DateFormat df = new java.text.SimpleDateFormat("hh:mm");
                                                    Date available_hours = df.parse(slide.get("available_hours").toString());
                                                    Date attendance_hours = df.parse(slide.get("attendance_hours").toString());
                                                    long diff = attendance_hours.getTime() - available_hours.getTime();                                
                                                    long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
                                                    if(minutes < -15 || minutes > 60) 
                                                    {  
                                                        result.put("success", "false");
                                                        result.put("err_code", "534");                
                                                        result.put("err_message", "Please select the regularization reason");  
                                                        lDto.setError_code("534");
                                                        lDto.setError_description("Please select regularization reason");
                                                        employeeDao.insertErrorLog(lDto);
                                                        flag = "1";
                                                        break;
                                                    }
                                            }
                                        }
                                        if(!reasons.equals("") && slide1.get("remark").equals(""))
                                        {
                                           result.put("success", "false");
                                           result.put("err_code", "535");                  
                                           result.put("err_message", "Remarks should not be empty while selecting the Regularization reason."); 
                                           lDto.setError_code("535");
                                            lDto.setError_description("Remarks should not be empty while selecting the regularization reason");
                                            employeeDao.insertErrorLog(lDto);
                                           flag = "1";
                                           break;
                                        }
                                        if(project_id.equals(""))
                                        {
                                            result.put("success", "false");
                                            result.put("err_code", "540");
                                            result.put("err_message", "Please select project");
                                            lDto.setError_code("540");
                                            lDto.setError_description("Please select project");
                                            employeeDao.insertErrorLog(lDto);
                                            break;
                                        }
                                        employeeDto.setRemark((String)slide1.get("remark"));
                                        employeeDto.setStatus((String)slide1.get("status"));
                                        employeeDto.setTimesheetId(slide1.get("timesheet_id").toString()); 
                                        if("00:00".equals(timesheet_hours) || "".equals(timesheet_hours) || timesheet_hours.equals("0:0") || timesheet_hours.equals("0:00") || timesheet_hours.equals("00:0") || timesheet_hours.equals("0")) 
                                        {           
                                           result.put("success", "false");
                                           result.put("err_code", "536");                  
                                           result.put("err_message", "Timesheet hours should not be empty.");
                                           lDto.setError_code("536");
                                            lDto.setError_description("Timesheet hours should not be empty");
                                            employeeDao.insertErrorLog(lDto);
                                           flag = "1";
                                           break;
                                        }
                                        else
                                        {
//                                            String temp[] = timesheet_hours.split(":");
//                                            int hours = Integer.parseInt(temp[0]);
//                                            int minutes = Integer.parseInt(temp[1]);
//                                            timeSheetHours = timeSheetHours + hours;
//                                            timeSheetMinutes = timeSheetMinutes + minutes;
//
//                                            if (timeSheetMinutes >= 60) {
//                                              timeSheetHours ++;
//                                              timeSheetMinutes = timeSheetMinutes % 60;
//                                            }   
//                                            String totTimesheetHours = timeSheetHours+"."+timeSheetMinutes;

//                                            if(Double.parseDouble(totTimesheetHours) > 23.59)
//                                            {
//                                                 result.put("success", "false");
//                                                 result.put("err_code", "537");                  
//                                                 result.put("err_message", "The user cannot select a time greater than 23:59 hours for a Timesheet Entry.");
//                                                 lDto.setError_code("537");
//                                                  lDto.setError_description("User cannot select a time greater than 23:59 hours for a timesheet entry");
//                                                  employeeDao.insertErrorLog(lDto);
//                                                 flag = "1";
//                                                 break;
//                                            }
                                            employeeDao.insertTimesheetEntries(employeeDto);
                                            lDto.setData("Username :"+userName+" "+" Token :"+tokenNo+" "+" project_id :"+project_id+" "+" timesheet_id :"+timesheet_id+" "+" timeSheetDate :"+timeSheetDateStr+" "+" phase_id :"+phase_id+" "+" task_id :"+task_id+" "+" role_id :"+role_id+" "+" timesheet_hours :"+timesheet_hours+" "+" att_hours :"+att_hours+" "+" shift_id :"+shift_id+" "+" status :"+status+" "+" availableHrs :"+availableHours+" "+" regularizationHrs :"+regularizationHours+" "+"reason :"+reasons+" remarks :"+slide1.get("remark"));
                                            employeeDao.insertTxnLog(lDto);
                                            result.put("success", "true");
                                            result.put("err_code", "0");                
                                            result.put("err_message", "");  
                                        }                               
                                    }  
                                }

                                if(flag.equals("1"))break;
                            }
                        }
                        else{
                            result.put("success", "false");
                            result.put("err_code", "539");
                            result.put("err_message", "Leave request has been submitted for the date "+timesheetDate);
                            lDto.setError_code("539");
                            lDto.setError_description("Leave request has been submitted for the date "+timesheetDate);
                            employeeDao.insertErrorLog(lDto);
                            break;
                        }
                    }                    
                }
            }
        }
        return result;        
    } 
	
    @Path("/deleteTimeSheetEntry")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject delete(@Context ServletContext config,  String details) throws Exception{
        JSONObject result = new JSONObject();
        JSONObject inputJson;       
        int sessionRes; 
        JSONParser parser = new JSONParser();            
        inputJson = (JSONObject)parser.parse(details);
        String userName = inputJson.get("id").toString();
        String tokenNo = inputJson.get("tokenNo").toString();
        String timesheetId = inputJson.get("timeSheet_id").toString();
       
        final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
        EmployeeDao employeeDao = (EmployeeDao)cxt.getBean("EmployeeDao");
        LogsDto lDto = new LogsDto();
        lDto.setEmp_id(userName);
        lDto.setModule_name("Timesheet_entries");
        lDto.setFunction_name("getCurrentCalendar");
        lDto.setTxn_type_name("POST");
        lDto.setData("Username :"+userName+" "+" TimesheetID :"+timesheetId+" "+" Token :"+tokenNo);
        employeeDao.insertTxnLog(lDto);
        EmployeeDto employeeDto = new EmployeeDto();  
        employeeDto.setUserName(userName); 
        employeeDto.setTokenNo(tokenNo);
        employeeDto.setTimesheetId(timesheetId);
        int resultCnt = employeeDao.isEmpValid(employeeDto); 
        if(resultCnt > 0)
        {
            sessionRes = employeeDao.isTokenValid(employeeDto);   
            if(sessionRes==-1)        
            {
                result.put("success", "false");
                result.put("err_code", "523");                
                result.put("err_message", "Token Invalid"); 
                lDto.setError_code("523");
                lDto.setError_description("Token Invalid");
                employeeDao.insertErrorLog(lDto);
            }    
            else
            {  
                if(sessionRes==0)
                {
                    result.put("success", "false");
                    result.put("err_code", "521");                
                    result.put("err_message", "Session expired");                   
                    lDto.setError_code("521");
                    lDto.setError_description("Session Expired");
                    employeeDao.insertErrorLog(lDto);
                    employeeDao.deleteToken(employeeDto);
                } 
                else
                {    
                    employeeDao.deleteUpdate(employeeDto);
                    result.put("success", "true");
                    result.put("err_code", "");                
                    result.put("err_message", "");
                }
            }    
        }
        else
        {
            result.put("success", "false");
            result.put("err_code", "522");                
            result.put("err_message", "Invalid Username");  
            lDto.setError_code("522");
            lDto.setError_description("Invalid Username");
            employeeDao.insertErrorLog(lDto);
        }
        return result;        
    }
    
                                    
        private static BufferedImage resizeImage(BufferedImage originalImage, int type){
            int IMG_WIDTH = 250;
            int IMG_HEIGHT = 400;
           BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
           Graphics2D g = resizedImage.createGraphics();
           g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
           g.dispose();

           return resizedImage;
       }
    
    
    
}
