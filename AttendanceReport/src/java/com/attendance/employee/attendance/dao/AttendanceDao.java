/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dao;

import com.attendance.employee.attendance.dto.AttendanceDto;
import com.attendance.employee.attendance.dto.AttendancePmProjectReport;
import com.attendance.employee.attendance.dto.AttendancePmReportFilterDto;
import java.util.List;

/**
 *
 * @author 16047
 */
public interface AttendanceDao {
    public List<AttendanceDto> getSearchList(String empVal,String modId);
    public List<AttendanceDto> getAttendanceDetails(AttendanceDto filterData);
    public String getEmployeeName(String employee_id);
    public List<AttendanceDto> getEmployeeDetails(AttendanceDto filterData);
    public AttendanceDto getSwipeDetails(String empId, String from_date);
    public List<AttendancePmProjectReport> getPmProjectAttendanceReport(AttendancePmReportFilterDto pmAttReport);
}
