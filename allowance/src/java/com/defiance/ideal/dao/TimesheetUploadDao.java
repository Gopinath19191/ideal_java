/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dao;

import com.defiance.ideal.dto.TimesheetDto;
import java.util.List;

/**
 *
 * @author 16221
 */
public interface TimesheetUploadDao {
    public String validateEmployeeId(TimesheetDto searchDto);
    public TimesheetDto getProjectId(TimesheetDto searchDto);
    public int insertTimesheet(TimesheetDto searchDto);
    public int insertTimesheetTemp(TimesheetDto searchDto);
    public void deleteTimesheetTemp();
    public List<TimesheetDto> getErrorEmployeeList();
    public void updateErrorData(TimesheetDto error_data);
    public List<TimesheetDto> getTimesheetDetails();
    public void updateTimesheetStatus(TimesheetDto error_data);
    public List<TimesheetDto> getUploadStatus();
}
