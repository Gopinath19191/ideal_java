/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.dao;

import com.defiance.ideal.timesheet.approve.dto.ApprovalDTO;
import com.defiance.ideal.timesheet.approve.dto.CompanyHolidayDTO;
import com.defiance.ideal.timesheet.approve.dto.ConfigurationDTO;
import com.defiance.ideal.timesheet.approve.dto.EmployeeDTO;
import com.defiance.ideal.timesheet.approve.dto.EmployeeLeaveDTO;
import com.defiance.ideal.timesheet.approve.dto.PhaseDTO;
import com.defiance.ideal.timesheet.approve.dto.ProjectDTO;
import com.defiance.ideal.timesheet.approve.dto.RoleDTO;
import com.defiance.ideal.timesheet.approve.dto.RoleWorkLocationDTO;
import com.defiance.ideal.timesheet.approve.dto.SearchCriteriaDTO;
import com.defiance.ideal.timesheet.approve.dto.TaskDTO;
import com.defiance.ideal.timesheet.approve.dto.TimesheetDTO;
import com.defiance.ideal.timesheet.approve.dto.TimesheetEntryDTO;
import com.defiance.ideal.timesheet.approve.dto.WFHpolicyIntegrationDTO;
import com.defiance.ideal.timesheet.approve.dto.WeekEndDetails;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 14578
 */
public interface ApprovalDAO {

    public List<ApprovalDTO> getProjectList(ApprovalDTO empDet);
    public Map getYearList(TimesheetEntryDTO formData);
    public String getJoinedDate(String val);
    public List<TimesheetDTO> getProjects(TimesheetDTO formData);
    public List<TimesheetDTO> getConfigurationList(String name);
    public List<TimesheetDTO> getPhaseList(String project);
    public List<TimesheetDTO> getTaskList(TimesheetDTO formData);
    public List<TimesheetDTO> getProjectDetail(String id);
    public List<TimesheetDTO> getTimesheetEntries(TimesheetDTO formData);
    public List<TimesheetDTO> getProjectsGlobal(TimesheetDTO formData);
    public List<TimesheetDTO> getProjectsGlobalNew(TimesheetDTO formData);
    public List<TimesheetDTO> getProjectDetailGlobal(TimesheetDTO formData);
    public List<TimesheetDTO> getPhases(TimesheetDTO formData);
    public List<TimesheetDTO> getNonPhases(TimesheetDTO formData);
    public List<TimesheetDTO> getTasks(TimesheetDTO formData);
    public List<TimesheetDTO> getNonTasks(TimesheetDTO formData);
    public String getPhaseId(TimesheetDTO formData);
    public List<TimesheetDTO> getRoles(TimesheetDTO formData);
    public List<ApprovalDTO> getEmployeeList(ApprovalDTO appDTO);
    public List<ApprovalDTO> getPrjDetails(ApprovalDTO appDTO);
    
    public List<ApprovalDTO> filterDataList(ApprovalDTO appDTO);
    public void approverSubmit(ApprovalDTO appDTO);
    public TimesheetDTO getAvailableHours(TimesheetDTO formData);
    public void saveTimesheet(TimesheetDTO data);
    public List<TimesheetDTO> getEmployeeInfo(String employee_id);
    public List<TimesheetDTO> getHolidays(TimesheetDTO formData);
    public List<TimesheetDTO> getLeaveDetails(TimesheetDTO formData);
    public String getEmployeeJoinedDate(String employee_id);
    public TimesheetDTO getAttendanceHours(TimesheetDTO formData);
    public List<ApprovalDTO> getAttendanceHoursForApproval(ApprovalDTO formData);
    
    
    public List<TimesheetEntryDTO> getTimesheetEntries_New(SearchCriteriaDTO objSearch);
    public EmployeeDTO getEmployeeInfo_New(String employee_id);
    public List<CompanyHolidayDTO> getCompanyHolidays(SearchCriteriaDTO objSearch);
    public List<EmployeeLeaveDTO> getEmployeeLeaveDetails(SearchCriteriaDTO objSearch);
    public List<PhaseDTO> getPhases_New(SearchCriteriaDTO objSearch);
    public List<TaskDTO> getTasks_New(SearchCriteriaDTO objSearch);
    public List<RoleDTO> getRoles_New(SearchCriteriaDTO objSearch);
    public List<ProjectDTO> getProjects_New1(SearchCriteriaDTO objSearch);
    public ProjectDTO getProjectDetail_New(SearchCriteriaDTO objSearch);

    public PhaseDTO getPhaseDetail(SearchCriteriaDTO objSearch);
    //public String getPhaseDetail(SearchCriteriaDTO objSearch);
    public List<ConfigurationDTO> getConfiguration(String key);
    public String getAttendanceHours_New(SearchCriteriaDTO objSearch);
    public String getAvailableHours_New(SearchCriteriaDTO objSearch);
    public void saveTimesheet(TimesheetEntryDTO data);
    public String getWorkLocationOfEmployee(SearchCriteriaDTO objSearch);
    //For Status filter
    
    public List<TimesheetDTO> getStatusList();
    
    public List<RoleWorkLocationDTO> getRoleAndLocation(SearchCriteriaDTO objSearch);

    public int savedDataRemove(String timeSheetId);

    public TaskDTO getTask(SearchCriteriaDTO objSearch);
    
    public List<WFHpolicyIntegrationDTO> getCountOfRegularizationWFH(SearchCriteriaDTO objSearch);
    
    public List<WFHpolicyIntegrationDTO> getPolicyConfigurationValue();
    
    public List<WFHpolicyIntegrationDTO> getExceptionList();
    
    public List<WeekEndDetails> getWeekEndDetails(SearchCriteriaDTO objSearch);
    
    public String get_WFH_Eligible(SearchCriteriaDTO searchObj);
}
