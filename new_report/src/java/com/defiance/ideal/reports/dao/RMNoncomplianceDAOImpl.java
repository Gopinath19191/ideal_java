/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import com.defiance.ideal.reports.dto.RMNonComplianceDataDTO;
import com.defiance.ideal.reports.service.RMNonComplianceService;
import com.defiance.ideal.reports.service.RMNoncomplianceDAO;
import java.util.List;

/**
 *
 * @author 16221
 */
public class RMNoncomplianceDAOImpl extends SqlMapClientDaoSupport implements RMNoncomplianceDAO{
    
    public List<RMNonComplianceDataDTO> timesheetReport(RMNonComplianceDataDTO filterData) {
        List<RMNonComplianceDataDTO> reportList = null;
        try {
            System.out.println("emp id, "+filterData.getEmployee_id()+" date "+filterData.getTimesheet_date());
            reportList = getSqlMapClientTemplate().queryForList("timesheetNonCompliance.gettimesheetDetails", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }
            
    public List<RMNonComplianceDataDTO> timesheetReportUnitWise(RMNonComplianceDataDTO filterData) {
        List<RMNonComplianceDataDTO> reportList = null;
        try {
            System.out.println("emp id, "+filterData.getEmployee_id()+" date "+filterData.getTimesheet_date());
            reportList = getSqlMapClientTemplate().queryForList("timesheetNonCompliance.getTimesheetReportUnitWise", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }
            
    public String getFileName(RMNonComplianceDataDTO filterData) {
        String FileName = null;
        try {
            System.out.println("emp id, "+filterData.getEmployee_id()+" date "+filterData.getTimesheet_date());
            FileName = (String) getSqlMapClientTemplate().queryForObject("timesheetNonCompliance.getfileName", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("report output "+FileName);
        return FileName;
    }
            
    public int leaveDetails(RMNonComplianceDataDTO filterData) {
        int leaveList = 0;
        try {
            leaveList = (Integer) getSqlMapClientTemplate().queryForObject("timesheetNonCompliance.getLeaveDetails", filterData);
            System.out.println("leave list "+leaveList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaveList;
    }        
    
    public List<RMNonComplianceDataDTO> getEmployeeList(RMNonComplianceDataDTO filterData) {
        List<RMNonComplianceDataDTO> reportList = null;
        try {
            reportList = getSqlMapClientTemplate().queryForList("timesheetNonCompliance.getEmployeeList", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }
            
    public List<RMNonComplianceDataDTO> getEmployeeDetails(RMNonComplianceDataDTO filterData) {
        List<RMNonComplianceDataDTO> reportList = null;
        try {
            reportList = getSqlMapClientTemplate().queryForList("timesheetNonCompliance.getEmployeeDetails", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }
            
    public int getCalendarId(RMNonComplianceDataDTO filterData) {
        int calendar_id = 0;
        try {
            calendar_id = (Integer) getSqlMapClientTemplate().queryForObject("timesheetNonCompliance.getCalendarId", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar_id;
    }
            
    public String getHolidayDetails(RMNonComplianceDataDTO filterData) {
        String holidayDate = null;
        try {
            holidayDate = (String) getSqlMapClientTemplate().queryForObject("timesheetNonCompliance.getHolidayDetails", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holidayDate;
    }
            
    public String getWeekEndDetails(String Calendat_id) {
        String holidayDate = null;
        try {
            holidayDate = (String) getSqlMapClientTemplate().queryForObject("timesheetNonCompliance.getWeekEndDetails", Calendat_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holidayDate;
    }
            
    public List<RMNonComplianceDataDTO> getCompanyStructure(RMNonComplianceDataDTO filterData) {
        List<RMNonComplianceDataDTO> reportList = null;
        try {
            reportList = getSqlMapClientTemplate().queryForList("timesheetNonCompliance.getCompanyStructure", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }
        
    public String getRMId(String employeeNumber) {
        String empId = null;
        try {
            empId = (String) getSqlMapClientTemplate().queryForObject("timesheetNonCompliance.getRMId", employeeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empId;
    }
            
    public List<RMNonComplianceDataDTO> getProjectList(RMNonComplianceDataDTO filterData) {
        List<RMNonComplianceDataDTO> projectList = null;
        try {
            projectList = getSqlMapClientTemplate().queryForList("timesheetNonCompliance.getProjectList", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
            
    public List<RMNonComplianceDataDTO> getProjectEmployeeList(RMNonComplianceDataDTO filterData) {
        List<RMNonComplianceDataDTO> projectList = null;
        try {
            projectList = getSqlMapClientTemplate().queryForList("timesheetNonCompliance.getProjectEmployeeList", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
    public List<RMNonComplianceDataDTO> getMultiProjectEmployeeList(RMNonComplianceDataDTO filterData) {
        List<RMNonComplianceDataDTO> projectList = null;
        try {
            projectList = getSqlMapClientTemplate().queryForList("timesheetNonCompliance.getMultiProjectEmployeeList", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }            
    public String getUnitWiseAccess(String getRMId) {
        String unitAccess = null;
        try {
            unitAccess = (String) getSqlMapClientTemplate().queryForObject("timesheetNonCompliance.getUnitWiseAccess", getRMId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unitAccess;
    }
}
