/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.TimesheetAttendanceReportDTO;
import java.util.List;

/**
 *
 * @author 16656
 */
public interface TimesheetAttendanceReportService {
    
    public List<TimesheetAttendanceReportDTO> getTimesheetAttendanceReport(TimesheetAttendanceReportDTO dto);
    public TimesheetAttendanceReportDTO getEmployeeName(TimesheetAttendanceReportDTO dto);
    public List<TimesheetAttendanceReportDTO> getEmployeesList(String id);
}
