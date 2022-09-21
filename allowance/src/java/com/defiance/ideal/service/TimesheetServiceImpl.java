/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.service;

import com.defiance.ideal.dao.TimesheetUploadDao;
import com.defiance.ideal.dto.TimesheetDto;
import java.util.List;

/**
 *
 * @author 16221
 */
public class TimesheetServiceImpl implements TimesheetService{
    TimesheetUploadDao dao;

    public TimesheetUploadDao getDao() {
        return dao;
    }

    public void setDao(TimesheetUploadDao dao) {
        this.dao = dao;
    }

    public String validateEmployeeId(TimesheetDto searchDto){
        String employee_id = dao.validateEmployeeId(searchDto);
        return employee_id;
    }
    
    public TimesheetDto getProjectId(TimesheetDto searchDto){
        TimesheetDto project_search = dao.getProjectId(searchDto);
        return project_search;
    }
    
    public int insertTimesheet(TimesheetDto searchDto){
        int last_insert_id = dao.insertTimesheet(searchDto);
        return last_insert_id;
    }
    
    public int insertTimesheetTemp(TimesheetDto searchDto){
        int last_insert_id = dao.insertTimesheetTemp(searchDto);
        return last_insert_id;
    }
    
    public void deleteTimesheetTemp(){
        dao.deleteTimesheetTemp();
    }
    public void uploadTimesheet(){
        List<TimesheetDto> get_employee_list = dao.getErrorEmployeeList();
        System.out.println("list size "+get_employee_list.size());
        if(get_employee_list.size() > 0){
            for(int i=0; i<get_employee_list.size(); i++){
                TimesheetDto error_data = new TimesheetDto();
                error_data.setEmployee_number(get_employee_list.get(i).getEmployee_number());
                error_data.setReason(get_employee_list.get(i).getReason());
                dao.updateErrorData(error_data);
            }
        }
        List<TimesheetDto> get_timesheet_details = dao.getTimesheetDetails();
        if(get_timesheet_details.size() > 0){
            for(int i=0; i<get_timesheet_details.size(); i++){
                String error = "";
                TimesheetDto timesheet_date = new TimesheetDto();
                timesheet_date.setEmployee_id(get_timesheet_details.get(i).getEmployee_id());
                timesheet_date.setDate(get_timesheet_details.get(i).getDate());
                timesheet_date.setProject_id(get_timesheet_details.get(i).getProject_id());
                timesheet_date.setRole_id(get_timesheet_details.get(i).getRole_id());
                timesheet_date.setTimesheet_hours(get_timesheet_details.get(i).getTimesheet_hours());
                int last_insert_id = dao.insertTimesheet(timesheet_date);
                if(last_insert_id==1){
                    error="Success";
                }else{
                    error="Timesheet Already Uploaded";
                }
                timesheet_date.setRow_id(get_timesheet_details.get(i).getRow_id());
                timesheet_date.setReason(error);
                dao.updateTimesheetStatus(timesheet_date);
            }
        }
    }
    
    public List<TimesheetDto> getUploadStatus(){
        List<TimesheetDto> timesheet_status = dao.getUploadStatus();
        return timesheet_status;
    }
}   
