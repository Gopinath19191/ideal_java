/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.employee.dao;

import com.mycompany.employee.dto.CalendarDto;
import com.mycompany.employee.dto.EmployeeDto;
import com.mycompany.employee.dto.LogsDto;
import com.mycompany.employee.dto.MasterDto;
import com.mycompany.employee.dto.PushNotificationDto;
import com.mycompany.employee.dto.TSNotificationListDto;
import com.mycompany.employee.dto.TimeSheetDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16363
 */
public class EmployeeDaoImpl extends SqlMapClientDaoSupport implements EmployeeDao {
        
    @Override
    public int getLoginDetails(EmployeeDto regisDto) throws Exception{        
        int result = (int)getSqlMapClientTemplate().queryForObject("registerMap.getLoginDetails",regisDto);
        return result;
    }
    
    @Override
    public List<EmployeeDto> getDeviceDetails(EmployeeDto regisDto) throws Exception{
        List<EmployeeDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.getDeviceDetails",regisDto);
        return result;
    }    
              
    @Override
    public int insertDeviceDetails(EmployeeDto regisDto) throws Exception{
//        int id =0;
        int result = (int)getSqlMapClientTemplate().insert("registerMap.insertDeviceId", regisDto);
        return result;
    }
    
//    @Override
//    public void updateDeviceDetails(EmployeeDto regisDto) throws Exception{
////        int id =0;
//        getSqlMapClientTemplate().update("registerMap.updateDeviceId", regisDto);       
//    }
    
    @Override
    public int getNotificationCount(EmployeeDto regisDto) throws Exception{        
        int result = (int) getSqlMapClientTemplate().queryForObject("registerMap.getNotificationCount",regisDto);
        return result;
    }
            
    @Override
    public List<EmployeeDto> getUserProfile(EmployeeDto regisDto) throws Exception{       
        List<EmployeeDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.getUserProfile",regisDto);
        return result;
    }
    
    @Override
    public void updateDeviceRegIdDetails(EmployeeDto regisDto) throws Exception{
//        int id =0;
        getSqlMapClientTemplate().update("registerMap.updateDeviceRegId", regisDto);       
    }
    
    @Override
    public void insertSessionDetails(EmployeeDto regisDto) throws Exception{
//        int id =0;
        getSqlMapClientTemplate().insert("registerMap.insertSessionDetails", regisDto);       
    }
    
    @Override
    public void updateSessionToken(EmployeeDto regisDto) throws Exception{
//        int id =0;
        getSqlMapClientTemplate().update("registerMap.updateSessionToken", regisDto);       
    }
    
    @Override
    public int checkSessionExists(EmployeeDto regisDto) throws Exception{        
        int result = (int)getSqlMapClientTemplate().queryForObject("registerMap.checkSessionExists",regisDto);
        return result;
    }
    
    @Override
    public double getDeviceAppVersionAndroid(EmployeeDto regisDto) throws Exception{
        double result = (double) getSqlMapClientTemplate().queryForObject("registerMap.getDeviceAppVersionAndroid",regisDto);
        return result;
    }
    
     @Override
    public double getDeviceAppVersionIOS(EmployeeDto regisDto) throws Exception{
        double result = (double) getSqlMapClientTemplate().queryForObject("registerMap.getDeviceAppVersionIOS",regisDto);
        return result;
    }
    
    @Override
    public int isTokenValid(EmployeeDto regisDto) throws Exception{
        int sessionDuration = 20*60*1000;
        List<EmployeeDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.isTokenValid",regisDto);

        if (result.isEmpty())
        {
            return -1;
        }
        else if((result.get(0).getCurrentTime().getTime() - result.get(0).getSesStartTime().getTime()) > sessionDuration)
        {
            return 0;
        }
        else
        {
            getSqlMapClientTemplate().update("registerMap.updateSessTime", regisDto);
            return 1;
        }
    }    
//    
//     @Override
//    public List<EmployeeDto> isTokenValid(EmployeeDto regisDto) throws Exception{
//        List<EmployeeDto> result = new ArrayList<>(); 
//        result = getSqlMapClientTemplate().queryForList("registerMap.isTokenValid",regisDto);
//
//        if (result.isEmpty())
//        {
//            return -1;
//        }
//        else if((result.get(0).getCurrentTime().getTime() - result.get(0).getSesStartTime().getTime()) > 1200000)
//        {
//            // Update session start time
//            return 0;
//        }
//        else
//        {
//            return 1;
//        }
//    }  
    
    @Override
    public int isEmpValid(EmployeeDto regisDto) throws Exception{        
        int result = (int)getSqlMapClientTemplate().queryForObject("registerMap.isEmpValid",regisDto);
        return result;
    }
    
    @Override
    public void resettingPassword(EmployeeDto regisDto) throws Exception{       
       getSqlMapClientTemplate().update("registerMap.resettingPassword", regisDto);    
    }
    
    @Override
    public void deleteDeviceDtls(EmployeeDto regisDto) throws Exception{       
       getSqlMapClientTemplate().delete("registerMap.deleteDeviceDtls", regisDto);    
    }
    
    @Override
    public void deleteToken(EmployeeDto regisDto) throws Exception{       
       getSqlMapClientTemplate().delete("registerMap.deleteToken", regisDto);    
    }
    
    @Override
    public List<TSNotificationListDto> getTimesheetNL(EmployeeDto registerDto) throws Exception{
        List<TSNotificationListDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.getTimesheetNL",registerDto);
        return result;
    }  
    
    @Override
    public List<TimeSheetDto> getTimesheetDetailsAndEntries(EmployeeDto employeeDto) throws Exception{
        List<TimeSheetDto> tmResult = new ArrayList<>();
        tmResult = getSqlMapClientTemplate().queryForList("registerMap.getTimesheetDetailsAndEntries",employeeDto);
        return tmResult;
    }
    
    
    @Override
    public List<CalendarDto> getCurrentCalendar(EmployeeDto employeeDto) throws Exception
    {
        List<CalendarDto> calResult = new ArrayList<>();
        calResult = getSqlMapClientTemplate().queryForList("registerMap.getCurrentCalendar",employeeDto);
        return calResult;
    }
	
    @Override
    public List<MasterDto> getMasterData(EmployeeDto regisDto) throws Exception{
        List<MasterDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.getMasterData",regisDto);
        return result;
    } 
    
    @Override
    public List<MasterDto> getMasterProjects(EmployeeDto regisDto) throws Exception{
        List<MasterDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.getMasterProjects",regisDto);
        return result;
    }
    
    @Override
    public List<MasterDto> getMasterRoles(EmployeeDto regisDto) throws Exception{
        List<MasterDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.getMasterRoles",regisDto);
        return result;
    }    
    
    @Override
    public List<MasterDto> getMasterDataRoles(EmployeeDto regisDto) throws Exception{
        List<MasterDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.getMasterDataRoles",regisDto);
        return result;
    } 
    
    @Override
    public List<MasterDto> getRegularizationDtls() throws Exception{
        List<MasterDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.getRegularizationDtls");
        return result;
    }  
	
    @Override
    public List<MasterDto> getPhaseDtls(MasterDto employeeDto) throws Exception{
        List<MasterDto> tmResult = new ArrayList<>();
        tmResult = getSqlMapClientTemplate().queryForList("registerMap.getPhaseDtls",employeeDto);
        System.out.println("tmResult="+tmResult.size());
        return tmResult;
    }
    
    @Override
    public List<MasterDto> getTaskDtls(MasterDto employeeDto) throws Exception{
        List<MasterDto> tmResult = new ArrayList<>();
        tmResult = getSqlMapClientTemplate().queryForList("registerMap.getTaskDtls",employeeDto);
        return tmResult;
    }
    
//    @Override
//    public List<MasterDto> getNonProjectDetails() throws Exception{
//        List<MasterDto> result= new ArrayList<>();
//        result = getSqlMapClientTemplate().queryForList("registerMap.getNonProjectActivities");
//        return result;
//    }
    
    @Override
    public List<MasterDto> getNonProjectPhases() throws Exception{
        List<MasterDto> result= new ArrayList<>();
        result = getSqlMapClientTemplate().queryForList("registerMap.getNonProjectActivitiesPhases");
        return result;
    }
    
    @Override
    public List<MasterDto> getNonProjectTasks(MasterDto masDto) throws Exception{
        List<MasterDto> result= new ArrayList<>();
        result = getSqlMapClientTemplate().queryForList("registerMap.getNonProjectActivitiesTasks", masDto);
        return result;
    }
        
    @Override
    public void insertTimesheetEntries(EmployeeDto regisDto) throws Exception{
        getSqlMapClientTemplate().insert("registerMap.insertTimesheetEntries", regisDto);       
    }
	
	@Override
    public void deleteUpdate(EmployeeDto regisDto) throws Exception{
        getSqlMapClientTemplate().update("registerMap.deleteUpdate", regisDto);        
    }
	
	@Override
    public List<EmployeeDto> getEmpWfhDetails(EmployeeDto employeeDto) throws Exception{
        List<EmployeeDto> result= new ArrayList<>();
        result = getSqlMapClientTemplate().queryForList("registerMap.getEmpWfhDetails",employeeDto);
        return result;
    }
    
    @Override
    public List<EmployeeDto> getWfhPolicy() throws Exception{
        List<EmployeeDto> result= new ArrayList<>();
        result = getSqlMapClientTemplate().queryForList("registerMap.getWfhPolicy");
        return result;
    }
        
    @Override
    public List<EmployeeDto> getWfhExcList() throws Exception{
        List<EmployeeDto> result= new ArrayList<>();
        result = getSqlMapClientTemplate().queryForList("registerMap.getWfhExcList");
        return result;
    }
	    
    @Override
    public void insertTxnLog(LogsDto lDto) throws Exception{
        getSqlMapClientTemplate().insert("registerMap.insertTxnLog", lDto);
    }
    
     @Override
    public void insertErrorLog(LogsDto lDto) throws Exception{
        getSqlMapClientTemplate().insert("registerMap.insertErrorLog", lDto);
    }
	
    @Override
    public int isEntrySubmitted(EmployeeDto regisDto) throws Exception{
        int result = (int)getSqlMapClientTemplate().queryForObject("registerMap.isEntrySubmitted", regisDto);
        return result;
    }
    
    @Override
    public int isEntry(EmployeeDto regisDto) throws Exception{
        int result = (int)getSqlMapClientTemplate().queryForObject("registerMap.isEntry", regisDto);
        return result;
    }
    
    @Override
    public String isEmpLeaveApplied(EmployeeDto regisDto) throws Exception{
     String result = (String)getSqlMapClientTemplate().queryForObject("registerMap.isEmpLeave", regisDto);
        System.out.println("Result ::::::" +result);
        return result;
    }
    
    @Override
    public List<EmployeeDto> getEmpWfhDetailsForReasons(EmployeeDto employeeDto) throws Exception{
        List<EmployeeDto> result= new ArrayList<>();
        result = getSqlMapClientTemplate().queryForList("registerMap.getEmpWfhDetailsForReasons",employeeDto);
        return result;
    }
    
    @Override
    public int checkWFHEligibility(EmployeeDto regisDto) throws Exception{
        int result = (int)getSqlMapClientTemplate().queryForObject("registerMap.checkWFHEligible", regisDto);
        return result;
    }
    
    @Override
    public List<PushNotificationDto> getDeviceRegKeyForPN(PushNotificationDto pnDto) throws Exception{
        List<PushNotificationDto> result = new ArrayList<>();
        result = getSqlMapClientTemplate().queryForList("registerMap.selectRegKeyPN", pnDto);
        return result;
    }
    
    @Override
    public List<PushNotificationDto> getDeviceRegKeyForSentBackPN(PushNotificationDto pnDto) throws Exception{
        List<PushNotificationDto> result = new ArrayList<>();
        result = getSqlMapClientTemplate().queryForList("registerMap.selectRegKeySentBackPN", pnDto);
        return result;
    }
    
    @Override
    public List<EmployeeDto> getleaveDaysPN(EmployeeDto employeeDto) throws Exception{
        List<EmployeeDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.leaveDaysPN",employeeDto);
        return result;
    }
    
    @Override
    public List<PushNotificationDto> getltimesheetDatePN(PushNotificationDto pushDto) throws Exception{
        List<PushNotificationDto> result = new ArrayList<>(); 
        result = getSqlMapClientTemplate().queryForList("registerMap.timesheetDatePN",pushDto);
        return result;
    }
    
    @Override
    public int nodeviceFound(PushNotificationDto pushDto) throws Exception{
       int result = (int)getSqlMapClientTemplate().queryForObject("registerMap.nodeviceFound",pushDto);
        return result;
    }
}
