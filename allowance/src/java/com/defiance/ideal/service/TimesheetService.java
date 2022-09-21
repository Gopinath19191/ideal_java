/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.service;

import com.defiance.ideal.dto.TimesheetDto;
import java.util.List;

/**
 *
 * @author 16221
 */
public interface TimesheetService {
    public String validateEmployeeId(TimesheetDto searchDto);
    public TimesheetDto getProjectId(TimesheetDto searchDto);
    public int insertTimesheet(TimesheetDto searchDto);
    public int insertTimesheetTemp(TimesheetDto searchDto);
    public void deleteTimesheetTemp();
    public void uploadTimesheet();
    public List<TimesheetDto> getUploadStatus();
}
