/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.RMNonComplianceDataDTO;
import java.util.List;

/**
 *
 * @author 16221
 */
public interface RMNoncomplianceDAO {

    public List<RMNonComplianceDataDTO> timesheetReport(RMNonComplianceDataDTO filterData);
    public int leaveDetails(RMNonComplianceDataDTO filterData);
    public List<RMNonComplianceDataDTO> getEmployeeList(RMNonComplianceDataDTO filterData);
    public List<RMNonComplianceDataDTO> getEmployeeDetails(RMNonComplianceDataDTO filterData);
    public int getCalendarId(RMNonComplianceDataDTO filterData);
    public String getHolidayDetails(RMNonComplianceDataDTO filterData);
    public String getWeekEndDetails(String calendar_id);
    public List<RMNonComplianceDataDTO> getCompanyStructure(RMNonComplianceDataDTO filterData);
    public String getRMId(String EmployeeNumber);
    public List<RMNonComplianceDataDTO> getProjectList(RMNonComplianceDataDTO filterData);
    public List<RMNonComplianceDataDTO> getProjectEmployeeList(RMNonComplianceDataDTO filterData);
    public String getUnitWiseAccess(String getRMId);
    public String getFileName(RMNonComplianceDataDTO filterData);
    public List<RMNonComplianceDataDTO> timesheetReportUnitWise(RMNonComplianceDataDTO filterData);
     public List<RMNonComplianceDataDTO> getMultiProjectEmployeeList(RMNonComplianceDataDTO filterData);
}
