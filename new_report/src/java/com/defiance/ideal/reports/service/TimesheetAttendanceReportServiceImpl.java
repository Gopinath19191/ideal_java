/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.TimesheetAttendanceReportDao;
import com.defiance.ideal.reports.dto.TimesheetAttendanceReportDTO;
import java.util.List;

/**
 *
 * @author 16656
 */
public class TimesheetAttendanceReportServiceImpl implements TimesheetAttendanceReportService{
    
    public TimesheetAttendanceReportDao timesheetAttendanceReportDao;

    public TimesheetAttendanceReportDao getTimesheetAttendanceReportDao() {
        return timesheetAttendanceReportDao;
    }

    public void setTimesheetAttendanceReportDao(TimesheetAttendanceReportDao timesheetAttendanceReportDao) {
        this.timesheetAttendanceReportDao = timesheetAttendanceReportDao;
    }
    
    public List<TimesheetAttendanceReportDTO> getTimesheetAttendanceReport(TimesheetAttendanceReportDTO dto) {
        List<TimesheetAttendanceReportDTO> lstAttendanceRpt = null;
        try{
            lstAttendanceRpt = timesheetAttendanceReportDao.getTimesheetAttendanceReport(dto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return lstAttendanceRpt;
    }
    
    public TimesheetAttendanceReportDTO getEmployeeName(TimesheetAttendanceReportDTO dto) {
        TimesheetAttendanceReportDTO lstAttendanceRpt = null;
        try{
            lstAttendanceRpt = timesheetAttendanceReportDao.getEmployeeName(dto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return lstAttendanceRpt;
    }
    
    public List<TimesheetAttendanceReportDTO> getEmployeesList(String id) {
        List<TimesheetAttendanceReportDTO> searchList = null;
        try {
            searchList = timesheetAttendanceReportDao.getEmployeesList(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }
}
