/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.RMNonComplianceDataDTO;
import com.defiance.ideal.reports.dao.RMNonComplianceDao;
import com.defiance.ideal.reports.dao.RMNoncomplianceDAOImpl;
import java.util.List;

/**
 *
 * @author 16221
 */
public class RMNoncomplianceServiceImpl implements RMNonComplianceService{
    
    public RMNoncomplianceDAO RMNonComplianceDao;

    public RMNoncomplianceDAO getRMNonComplianceDao() {
        return RMNonComplianceDao;
    }

    public void setRMNonComplianceDao(RMNoncomplianceDAO RMNonComplianceDao) {
        this.RMNonComplianceDao = RMNonComplianceDao;
    }
    
    public List<RMNonComplianceDataDTO> timesheetReport(RMNonComplianceDataDTO filterData) {
        return RMNonComplianceDao.timesheetReport(filterData);
    }
    
    public List<RMNonComplianceDataDTO> timesheetReportUnitWise(RMNonComplianceDataDTO filterData) {
        return RMNonComplianceDao.timesheetReportUnitWise(filterData);
    }
    
    public String getFileName(RMNonComplianceDataDTO filterData) {
        return RMNonComplianceDao.getFileName(filterData);
    }
            
    public int leaveDetails(RMNonComplianceDataDTO filterData) {
        return RMNonComplianceDao.leaveDetails(filterData);
    }
    
    public List<RMNonComplianceDataDTO> getEmployeeList(RMNonComplianceDataDTO filterData){
        return RMNonComplianceDao.getEmployeeList(filterData);
    }
            
    public List<RMNonComplianceDataDTO> getEmployeeDetails(RMNonComplianceDataDTO filterData){
        return RMNonComplianceDao.getEmployeeDetails(filterData);
    }
            
    public int getCalendarId(RMNonComplianceDataDTO filterData) {
        return RMNonComplianceDao.getCalendarId(filterData);
    }
    
    public String getHolidayDetails(RMNonComplianceDataDTO filterData) {
        return RMNonComplianceDao.getHolidayDetails(filterData);
    }
    
    public String getWeekEndDetails(String calendar_id) {
        return RMNonComplianceDao.getWeekEndDetails(calendar_id);
    }
            
    public List<RMNonComplianceDataDTO> getCompanyStructure(RMNonComplianceDataDTO filterData){
        return RMNonComplianceDao.getCompanyStructure(filterData);
    }
    
    public String getRMId(String employeeNumber){
        return RMNonComplianceDao.getRMId(employeeNumber);
    }
            
    public List<RMNonComplianceDataDTO> getProjectList(RMNonComplianceDataDTO filterData){
        return RMNonComplianceDao.getProjectList(filterData);
    }
            
    public List<RMNonComplianceDataDTO> getProjectEmployeeList(RMNonComplianceDataDTO filterData){
        return RMNonComplianceDao.getProjectEmployeeList(filterData);
    }  
    public String getUnitWiseAccess(String getRMId) {
        return RMNonComplianceDao.getUnitWiseAccess(getRMId);
    }
     public List<RMNonComplianceDataDTO> getMultiProjectEmployeeList(RMNonComplianceDataDTO filterData){
        return RMNonComplianceDao.getMultiProjectEmployeeList(filterData);
    }
}
