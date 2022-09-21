/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dao;

import com.defiance.ideal.dto.TimesheetDto;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16221
 */
public class TimesheetUploadDaoImpl extends SqlMapClientDaoSupport implements TimesheetUploadDao{
    
    @Override
    public String validateEmployeeId(TimesheetDto searchDto){
        String employee_id = null;
        try{
            employee_id = (String)getSqlMapClientTemplate().queryForObject("timesheetUploadMap.validateEmployeeId", searchDto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employee_id;
    }
    
    @Override
    public TimesheetDto getProjectId(TimesheetDto searchDto){
        TimesheetDto project_search = null;
        try{
            project_search = (TimesheetDto)getSqlMapClientTemplate().queryForObject("timesheetUploadMap.getProjectId", searchDto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return project_search;
    }
    
    @Override
    public int insertTimesheet(TimesheetDto searchDto){
        String last_id = "";
        try{
            last_id = (String) getSqlMapClientTemplate().queryForObject("timesheetUploadMap.insertTimesheet", searchDto);
        }catch(Exception e){
            e.printStackTrace();
        }     
        return Integer.valueOf(last_id);
    }
    
    @Override
    public void deleteTimesheetTemp(){
        try{
            getSqlMapClientTemplate().delete("timesheetUploadMap.deleteTimesheet");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public int insertTimesheetTemp(TimesheetDto searchDto){
        String last_id = "";
        try{
            last_id = (String) getSqlMapClientTemplate().insert("timesheetUploadMap.insertTimesheetTemp", searchDto);
        }catch(Exception e){
            e.printStackTrace();
        }     
        return Integer.valueOf("1");
    }
    
    @Override
    public List<TimesheetDto> getErrorEmployeeList(){
        List<TimesheetDto> error_employee = null;
        try{
            error_employee = getSqlMapClientTemplate().queryForList("timesheetUploadMap.getErrorEmployee");
        }catch(Exception e){
            e.printStackTrace();
        }
        return error_employee;
    }
    
    @Override
    public void updateErrorData(TimesheetDto error_data){
        try{
            getSqlMapClientTemplate().update("timesheetUploadMap.updateErrorData", error_data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public List<TimesheetDto> getTimesheetDetails(){
        List<TimesheetDto> timesheet_details = null;
        try{
            timesheet_details = getSqlMapClientTemplate().queryForList("timesheetUploadMap.getTimesheetDetails");
        }catch(Exception e){
            e.printStackTrace();
        }
        return timesheet_details;
    }
    
    @Override
    public void updateTimesheetStatus(TimesheetDto error_data){
        try{
            getSqlMapClientTemplate().update("timesheetUploadMap.updateTimesheetStatus", error_data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public List<TimesheetDto> getUploadStatus(){
        List<TimesheetDto> timesheet_details = null;
        try{
            timesheet_details = getSqlMapClientTemplate().queryForList("timesheetUploadMap.getUploadStatus");
        }catch(Exception e){
            e.printStackTrace();
        }
        return timesheet_details;
    }
}
