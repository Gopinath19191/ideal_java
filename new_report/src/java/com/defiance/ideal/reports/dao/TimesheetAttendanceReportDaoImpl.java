/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.TimesheetAttendanceReportDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16656
 */
public class TimesheetAttendanceReportDaoImpl extends SqlMapClientDaoSupport implements TimesheetAttendanceReportDao{
    
    public List<TimesheetAttendanceReportDTO> getTimesheetAttendanceReport(TimesheetAttendanceReportDTO dto) {
        List<TimesheetAttendanceReportDTO> lstAttendanceRpt = null;
        try {
            lstAttendanceRpt = getSqlMapClientTemplate().queryForList("AttendanceReportMap.getTimesheetAttendanceReport", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstAttendanceRpt;
    }
    
    public TimesheetAttendanceReportDTO getEmployeeName(TimesheetAttendanceReportDTO dto) {
        TimesheetAttendanceReportDTO lstAttendanceRpt = null;
        try {
            lstAttendanceRpt = (TimesheetAttendanceReportDTO)getSqlMapClientTemplate().queryForObject("AttendanceReportMap.getEmpName", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstAttendanceRpt;
    }
    
    public List<TimesheetAttendanceReportDTO> getEmployeesList(String id) {
        List<TimesheetAttendanceReportDTO> searchList = null;
        try {
            searchList = getSqlMapClientTemplate().queryForList("AttendanceReportMap.getEmployeesList", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }
}
