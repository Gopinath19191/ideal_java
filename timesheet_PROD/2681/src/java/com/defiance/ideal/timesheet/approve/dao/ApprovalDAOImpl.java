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
import com.defiance.ideal.timesheet.approve.dto.TimesheetExportDTO;
import com.defiance.ideal.timesheet.approve.dto.WFHpolicyIntegrationDTO;
import com.defiance.ideal.timesheet.approve.dto.WeekEndDetails;
import com.defiance.ideal.timesheet.util.CommonFunctions;
import com.lowagie.text.Phrase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14578
 */
public class ApprovalDAOImpl extends SqlMapClientDaoSupport implements ApprovalDAO {
final static Logger logger = Logger.getLogger(ApprovalDAOImpl.class);

    public List<ApprovalDTO> getProjectList(ApprovalDTO empDet) {
        List<ApprovalDTO> approverList = null;
        try {
            approverList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getProjectList", empDet);
        } catch (Exception e) {
        }
        return approverList;
    }
    public String getprojectManager(ApprovalDTO empDet) {
        String managerId = null;
        try {
            managerId = (String) getSqlMapClientTemplate().queryForObject("queryMap.getprojectManager", empDet);
        } catch (Exception e) {
        }
        return managerId;
    }
    
    public List<ApprovalDTO> getWorkTypeList(ApprovalDTO empDet) {
        List<ApprovalDTO> workTypeList = null;
        try {
            workTypeList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getWorkTypeList", empDet);
        } catch (Exception e) {
        }
        return workTypeList;
    }

    public List<ApprovalDTO> getEmployeeList(ApprovalDTO appDTO) {
        List<ApprovalDTO> employeeList = null;
        List<ApprovalDTO> temp = new ArrayList<ApprovalDTO>();
        boolean contains;
        String managerId = null;
        try {
            managerId = (String) getSqlMapClientTemplate().queryForObject("queryMap.getprojectManager", appDTO);
        } catch (Exception e) {
        }
        System.out.println("managerId @@@@ in employees"+managerId);
        if(managerId!=null){
            appDTO.setManagerId("YES");
        }else{
            appDTO.setManagerId("NO");
        }
        System.out.println("appDTO.GETMANAGER in employees"+appDTO.getManagerId());
        try {
            employeeList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getEmployeeList", appDTO);
            int i = 0;
            String resignedStatus[] = new String[]{"r", "t", "b", "q", "o"};
            Iterator itr = employeeList.iterator();
            while (itr.hasNext()) {
                ApprovalDTO dto = (ApprovalDTO) itr.next();
                contains = Arrays.asList(resignedStatus).contains(dto.getEmploymentStatus());
                if (!contains) {
                    temp.add(employeeList.get(i));
                }
                i++;
            }
            employeeList.clear();

        } catch (Exception e) {
        }
        return temp;
    }

    public List<ApprovalDTO> getPrjDetails(ApprovalDTO appDTO) {
        List<ApprovalDTO> employeeList = null;
        try {
            employeeList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getPrjDetails", appDTO);
        } catch (Exception e) {
        }
        return employeeList;
    }

    public List<ApprovalDTO> filterDataList(ApprovalDTO appDTO) {
        List<ApprovalDTO> filterDataList = null;
        try {
            logger.info("selectedValue-------" + appDTO.getValue());
            String managerId = null;
            try {
                managerId = (String) getSqlMapClientTemplate().queryForObject("queryMap.getprojectManager", appDTO);
            } catch (Exception e) {
            }
            System.out.println("managerId @@@@"+managerId);
            if(managerId!=null){
                appDTO.setManagerId("YES");
            }else{
                appDTO.setManagerId("NO");
            }
            System.out.println("appDTO.GETMANAGER "+appDTO.getManagerId());
            if (!appDTO.getProjectId().equals("Non_Project_Activity")) {
                filterDataList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getTimesheetEntryProjects", appDTO);
            } else {
                filterDataList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getNonProjectTimesheetEntry", appDTO);
            }

        } catch (Exception e) {
        }
        return filterDataList;
    }

    public List<ApprovalDTO> getAttendanceHoursForApproval(ApprovalDTO formData) {
        List<ApprovalDTO> attendance_hours = null;
        try {
            attendance_hours = getSqlMapClientTemplate().queryForList("queryMap.getAttendanceHoursForApproval", formData);
            Iterator itr = attendance_hours.iterator();
            while (itr.hasNext()) {
                TimesheetDTO dto = (TimesheetDTO) itr.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attendance_hours;

    }

    public void approverSubmit(ApprovalDTO appDTO) {
        logger.info("appdto valesi "+appDTO.getActionValue()+" hrs "+appDTO.getApproveType());
        if(appDTO.getActionValue().equalsIgnoreCase("r")){
            appDTO.setApprovedHrs(null);
            appDTO.setApprovedMins(null);
        }
        try {
            getSqlMapClientTemplate().update("queryMap.updateTimesheetEntryStatus", appDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String recordCount(ApprovalDTO appDTO) {
        String recordCount = null;
        try {
            recordCount = (String) getSqlMapClientTemplate().queryForObject("queryMap.getTimesheetEntryCount", appDTO);

        } catch (Exception e) {
        }
        return recordCount;
    }

    public boolean authenticate(ApprovalDTO authenParams) {
        try {
            ApprovalDTO userAuthentication = null;
            ApprovalDTO groupAuthentication = null;
            ApprovalDTO loginAuthentication = null;

            loginAuthentication = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("queryMap.loginCheck", authenParams);

            if (loginAuthentication == null) {
                logger.info("Not Logged IN");
                return false;
            } else {
                userAuthentication = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("queryMap.authenticateUser", authenParams);
                if (userAuthentication == null || userAuthentication.getuCreate() == null || userAuthentication.getuCreate().equals("-1")) {
                    groupAuthentication = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("queryMap.authenticateGroup", authenParams);
                    if (groupAuthentication == null || groupAuthentication.getgCreate() == null || groupAuthentication.getgCreate().equals("-1")) {
                        logger.info("Group Auth Failed");
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ApprovalDTO getUserDetails(String empId) {
        return (ApprovalDTO) getSqlMapClientTemplate().queryForObject("queryMap.getUserDetails", empId);
    }

    public Map getYearList(TimesheetEntryDTO formData) {
        Map val = new HashMap();
        String joined_year = (String) getSqlMapClientTemplate().queryForObject("queryMap.getJoinedYear", formData);
        if (Integer.parseInt(joined_year) < 2015) {
            joined_year = "2015";
        }
        int currentYear = CommonFunctions.getCurrentYear();
        for (int i = currentYear; i >= Integer.parseInt(joined_year); i--) {
            val.put(i, i);
        }
        return val;
    }
    
    public int getNonComplianceCount(String employee_id){
        int count=0;
        try{
            String data = (String) getSqlMapClientTemplate().queryForObject("queryMap.getNonComplianceCount", employee_id);
            count = Integer.parseInt(data);
        }catch(Exception e){
        }
        return count;
    }

    public String getJoinedDate(String val) {
        String joined_date = (String) getSqlMapClientTemplate().queryForObject("queryMap.getJoinedDate", val);
        return joined_date;
    }

    public List<TimesheetDTO> getProjects(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getProjects", formData);
        return data;
    }

    public List<TimesheetDTO> excelProjectList(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.excelProjectList", formData);
        return data;
    }

    public List<TimesheetDTO> getConfigurationList(String name) {
        List<TimesheetDTO> result = null;
        result = getSqlMapClientTemplate().queryForList("queryMap.getConfigurationList", name);
        return result;
    }

    public List<TimesheetDTO> getPhaseList(String project) {
        List<TimesheetDTO> result = null;
        result = getSqlMapClientTemplate().queryForList("queryMap.getPhaseList", project);
        return result;
    }

    public List<TimesheetDTO> getTaskList(TimesheetDTO formData) {
        List<TimesheetDTO> result = null;
        result = getSqlMapClientTemplate().queryForList("queryMap.getTaskList", formData);
        return result;
    }

    public List<TimesheetDTO> getProjectDetail(String id) {
        List<TimesheetDTO> result = null;
        result = getSqlMapClientTemplate().queryForList("queryMap.getProjectDetail", id);
        return result;
    }

    public List<TimesheetDTO> getTimesheetEntries(TimesheetDTO formData) {
        List<TimesheetDTO> result = null;
        result = getSqlMapClientTemplate().queryForList("queryMap.getTimesheetEntries", formData);
        return result;
    }

    public List<TimesheetDTO> getProjectsGlobal(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getProjectsGlobal", formData);
        return data;
    }

    public List<TimesheetDTO> getProjectsGlobalNew(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getProjectsGlobalNew", formData);
        return data;
    }

    public List<TimesheetDTO> getProjectDetailGlobal(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getProjectDetailGlobal", formData);
        return data;
    }

    public List<TimesheetDTO> getPhases(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getPhases", formData);
        return data;
    }

    public List<TimesheetDTO> getNonPhases(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getNonPhases", formData);
        return data;
    }

    public List<TimesheetDTO> getTasks(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getTasks", formData);
        return data;
    }

    public List<TimesheetDTO> getNonTasks(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getNonTasks", formData);
        return data;
    }

    public String getPhaseId(TimesheetDTO formData) {
        String data = null;
        data = (String) getSqlMapClientTemplate().queryForObject("queryMap.getPhaseId", formData);
        return data;
    }

    public List<TimesheetDTO> getRoles(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getRoles", formData);
        return data;
    }
    
    public List<TimesheetDTO> excelRoleList(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.excelRoleList", formData);
        return data;
    }
     
    public List<TimesheetDTO> excelPhaseList(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.excelPhaseList", formData);
        return data;
    }
    
    public void saveTimesheet(TimesheetDTO data) {
        try {
            if (data.getDb_autoId().equals("")) {
                String last_id = (String) getSqlMapClientTemplate().insert("queryMap.insertTimesheetEntry", data);
                data.setDb_timesheet_entry_id(last_id);
                getSqlMapClientTemplate().insert("queryMap.insertTimesheetEntryProject", data);
            } else {
                getSqlMapClientTemplate().update("queryMap.updateTimesheetEntry", data);
                String last_id = (String) getSqlMapClientTemplate().queryForObject("queryMap.getTimesheetEntryProjectId", data.getDb_autoId());
                data.setDb_autoId(last_id);
                getSqlMapClientTemplate().update("queryMap.updateTimesheetEntryProject", data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TimesheetDTO> getEmployeeInfo(String employee_id) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeInfo", employee_id);
        return data;
    }

    public TimesheetDTO getAvailableHours(TimesheetDTO formData) {
        TimesheetDTO available_hours = null;
        available_hours = (TimesheetDTO) getSqlMapClientTemplate().queryForObject("queryMap.getAvailableHours", formData);
        return available_hours;

    }
    
    public TimesheetDTO getAttendanceHours(TimesheetDTO formData) {
        TimesheetDTO attendance_hours = null;
        try {
            attendance_hours = (TimesheetDTO) getSqlMapClientTemplate().queryForObject("queryMap.getAttendanceHours", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendance_hours;

    }
    
    public List<TimesheetDTO> getHolidays(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getHolidays", formData);
        return data;
    }

    public List<TimesheetDTO> getLeaveDetails(TimesheetDTO formData) {
        List data = null;
        data = getSqlMapClientTemplate().queryForList("queryMap.getLeaveDetails", formData);
        return data;
    }

    public ApprovalDTO getProjectDetails(ApprovalDTO appDTO) {
        ApprovalDTO prjDetails = null;
        try {
            prjDetails = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("queryMap.getProjectDetails", appDTO);
        } catch (Exception e) {
        }
        return prjDetails;
    }

    public ApprovalDTO getTimeSheetDetails(ApprovalDTO appDTO) {
        ApprovalDTO timesheetDetails = null;
        try {
            timesheetDetails = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("queryMap.getTimeSheetDetails", appDTO);
        } catch (Exception e) {
        }
        return timesheetDetails;
    }

    public ApprovalDTO getBillingDetails(ApprovalDTO appDTO) {
        ApprovalDTO billingDetails = null;
        try {
            billingDetails = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("queryMap.getBillingDetails", appDTO);
        } catch (Exception e) {
        }
        return billingDetails;
    }

    public ApprovalDTO getBillingAdviceDetails(ApprovalDTO appDTO) {
        ApprovalDTO billingAdviceDetails = null;
        try {
            if(appDTO.getWorkingPlace().equals("company_locations")){
                appDTO.setWorkingPlace("s");
            }else if(appDTO.getWorkingPlace().equals("customer_work_locations")){
                appDTO.setWorkingPlace("o");
            }
            billingAdviceDetails = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("queryMap.getBillingAdviceDetails", appDTO);
        } catch (Exception e) {
        }
        return billingAdviceDetails;
    }

    public void updateBillingDetails(ApprovalDTO appDTO) {
        try {
            getSqlMapClientTemplate().update("queryMap.updateBillingAdviceDetails", appDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertBillingDetails(ApprovalDTO appDTO) {
        try {
            getSqlMapClientTemplate().update("queryMap.insertBillingDetails", appDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEmployeeJoinedDate(String employee_id) {
        String data = "";
        try {
            data = (String) getSqlMapClientTemplate().queryForObject("queryMap.getEmployeeJoinedDate", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
     
    public ApprovalDTO getEmployeeStructure(ApprovalDTO checkRmg) {
        ApprovalDTO data = null;
        try {
            data = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("queryMap.getEmployeeStructure", checkRmg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public List<TimesheetDTO> getStatusList(){
        List<TimesheetDTO> list = null;
        try{
            list = getSqlMapClientTemplate().queryForList("queryMap.getStatusList");
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public EmployeeDTO getEmployeeInfo_New(String employee_id) {
        EmployeeDTO empData = null;
        try{
            empData = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("queryMap.getEmployeeInfo_New", employee_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return empData;
    }
    public String getOfficeStartTime(String employee_id){
        String office_time = null;
        try{
            office_time = (String) getSqlMapClientTemplate().queryForObject("queryMap.getOfficeStartTime", employee_id);
            if(office_time==null){
                office_time = "e";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("office_time "+office_time);
        return office_time;
    }
    public List<TimesheetExportDTO> getExportEmployeeTimesheet(SearchCriteriaDTO searchObj){
        List<TimesheetExportDTO> timesheet_details = null;
        try{
            timesheet_details = getSqlMapClientTemplate().queryForList("queryMap.getTimesheetDetails", searchObj);
        }catch(Exception e){
            e.printStackTrace();
        }
        return timesheet_details;
    }
    public List<TimesheetExportDTO> getEmployeeDetails(SearchCriteriaDTO searchObj){
        List<TimesheetExportDTO> emp_details = null;
        try{
            emp_details = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeDetails", searchObj);
        }catch(Exception e){
            e.printStackTrace();
        }
        return emp_details;
    }
    public List<CompanyHolidayDTO> getCompanyHolidays(SearchCriteriaDTO objSearch) {
        List<CompanyHolidayDTO> data = null;
        try{
            data = (List<CompanyHolidayDTO>) getSqlMapClientTemplate().queryForList("queryMap.getCompanyHolidays", objSearch);
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    public List<CompanyHolidayDTO> getCompanyExceptionalWorkingDays(SearchCriteriaDTO objSearch) {
        List<CompanyHolidayDTO> data = null;
        try{
            data = (List<CompanyHolidayDTO>) getSqlMapClientTemplate().queryForList("queryMap.getCompanyExceptionalWorkingDays", objSearch);
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    public List<EmployeeLeaveDTO> getEmployeeLeaveDetails(SearchCriteriaDTO objSearch) {
        List<EmployeeLeaveDTO> data = null;
        try{
            data = (List<EmployeeLeaveDTO>) getSqlMapClientTemplate().queryForList("queryMap.getEmployeeLeaveDetails", objSearch);
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    
    public List<PhaseDTO> getPhases_New(SearchCriteriaDTO objSearch) {
        List<PhaseDTO> data = null;
        try{
            data = (List<PhaseDTO>) getSqlMapClientTemplate().queryForList("queryMap.getPhases_New", objSearch);
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
     
    public List<TaskDTO> getTasks_New(SearchCriteriaDTO objSearch) {
        List<TaskDTO> data = null;
        try{
            data = (List<TaskDTO>) getSqlMapClientTemplate().queryForList("queryMap.getTasks_New", objSearch);
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    
    public List<RoleDTO> getRoles_New(SearchCriteriaDTO objSearch) {
        List<RoleDTO> data = null;
        try{
            data = (List<RoleDTO>) getSqlMapClientTemplate().queryForList("queryMap.getRoles_New", objSearch);
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    
    public List<TimesheetEntryDTO> getTimesheetEntries_New(SearchCriteriaDTO objSearch) {
        List<TimesheetEntryDTO> result = null;
        try{
            result = (List<TimesheetEntryDTO>) getSqlMapClientTemplate().queryForList("queryMap.getTimesheetEntries_New", objSearch); 
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    public List<ProjectDTO> getProjects_New1(SearchCriteriaDTO objSearch) {
        List<ProjectDTO> result = null;
        try{
            result = (List<ProjectDTO>)getSqlMapClientTemplate().queryForList("queryMap.getProjects_New1", objSearch); 
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }    
    
    public ProjectDTO getProjectDetail_New(SearchCriteriaDTO objSearch) {
        ProjectDTO projectData = null;
        try{
            projectData = (ProjectDTO) getSqlMapClientTemplate().queryForObject("queryMap.getProjectDetail_New", objSearch);
        }catch(Exception e){
            e.printStackTrace();
        }
        return projectData;
    }
    
    public PhaseDTO getPhaseDetail(SearchCriteriaDTO objSearch) {
        PhaseDTO phaseData = null;
        try{
            phaseData = (PhaseDTO) getSqlMapClientTemplate().queryForObject("queryMap.getPhaseDetail", objSearch);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(phaseData == null){
            phaseData = new PhaseDTO();
            phaseData.setPhase_id("Others");
            phaseData.setPhase_name("Others");
        }
        return phaseData;
    }
    
    public List<ConfigurationDTO> getConfiguration(String key) {
        List<ConfigurationDTO> resultConfig = null;
        try{
            resultConfig = getSqlMapClientTemplate().queryForList("queryMap.getConfiguration", key);
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultConfig;
    }     
     
    public String getAttendanceHours_New(SearchCriteriaDTO objSearch) {
        String attendance_hours = null;
        try {
            attendance_hours = (String) getSqlMapClientTemplate().queryForObject("queryMap.getAttendanceHours_New", objSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendance_hours;
    }
    public String getAttendanceHoursInOut(SearchCriteriaDTO objSearch) {
        String attendance_hours = null;
        try {
            attendance_hours = (String) getSqlMapClientTemplate().queryForObject("queryMap.getAttendanceHoursInOut", objSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendance_hours;
    }
    public String getWorkLocationOfEmployee(SearchCriteriaDTO objSearch) {
        String wrkLocation = null;
        try {
            wrkLocation = (String) getSqlMapClientTemplate().queryForObject("queryMap.getWorkLocationOfEmployee", objSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wrkLocation;
    }
    
    public String getAvailableHours_New(SearchCriteriaDTO objSearch) {
        String available_hours = null;
        try {
            available_hours = (String) getSqlMapClientTemplate().queryForObject("queryMap.getAvailableHours_New", objSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return available_hours;
    }
    public String getAvailableOfficeHours_New(SearchCriteriaDTO objSearch) {
        String available_hours = null;
        try {
            available_hours = (String) getSqlMapClientTemplate().queryForObject("queryMap.getAvailableOfficeHours_New", objSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return available_hours;
    }
    public String getOfficeHours_New(SearchCriteriaDTO objSearch) {
        String office_hours = null;
        try {
            office_hours = (String) getSqlMapClientTemplate().queryForObject("queryMap.getOfficeHours_New", objSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return office_hours;
    }
    
    public void saveTimesheet(TimesheetEntryDTO oTimeSheetEntry) {
        try {
            String timesheet_id = "";
            timesheet_id = (String) getSqlMapClientTemplate().queryForObject("queryMap.saveTimesheetEntry", oTimeSheetEntry);
            if (timesheet_id != null && !timesheet_id.equals("")) {
                oTimeSheetEntry.setTime_sheet_id(timesheet_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getProjectRole(TimesheetEntryDTO oTimeSheetEntry){
        String role_id = "";
        try {
            role_id = (String) getSqlMapClientTemplate().queryForObject("queryMap.getProjectRole", oTimeSheetEntry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role_id;
    }
    
    public List<RoleWorkLocationDTO> getRoleAndLocation(SearchCriteriaDTO objSearch) {
        List<RoleWorkLocationDTO> lstRoleWorkLocation = null;
        try{
            lstRoleWorkLocation = (List<RoleWorkLocationDTO>) getSqlMapClientTemplate().queryForList("queryMap.getRoleAndLocation", objSearch);
        }catch(Exception e){
            e.printStackTrace();
        }
        return lstRoleWorkLocation;
    }

    public int savedDataRemove(String timeSheetId) {
        String i = "";
        try {
            i = (String) getSqlMapClientTemplate().queryForObject("queryMap.removedSavedEntryProcedure", timeSheetId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.parseInt(i);
    }
    
    public TaskDTO getTask(SearchCriteriaDTO oSearch){
        TaskDTO dto = new TaskDTO();
        try{
            dto = (TaskDTO) getSqlMapClientTemplate().queryForObject("queryMap.getTaskDetail", oSearch);
        }catch(Exception e) {
            e.printStackTrace();
        }
        if(dto == null){
            dto = new TaskDTO();
            dto.setTask_id("Others");
            dto.setTask_name("Others");
        }
        return dto;
    }
    
    public List<WFHpolicyIntegrationDTO> getCountOfRegularizationWFH(SearchCriteriaDTO objSearch){
        List<WFHpolicyIntegrationDTO> countWFH  = null;
        try{
            countWFH = (List<WFHpolicyIntegrationDTO>) getSqlMapClientTemplate().queryForList("queryMap.getWFHcount", objSearch);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countWFH;
    }
    
    public List<WFHpolicyIntegrationDTO> getPolicyConfigurationValue(){
        List<WFHpolicyIntegrationDTO> policyValues  = null;
        try{
            policyValues = (List<WFHpolicyIntegrationDTO>) getSqlMapClientTemplate().queryForList("queryMap.getPolicyValue");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return policyValues;
    }
    
    public List<WFHpolicyIntegrationDTO> getExceptionList(){
        List<WFHpolicyIntegrationDTO> exceptionList  = null;
        try{
            exceptionList = (List<WFHpolicyIntegrationDTO>) getSqlMapClientTemplate().queryForList("queryMap.getExceptionList");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return exceptionList;
    }
    
    public List<WeekEndDetails> getWeekEndDetails(SearchCriteriaDTO objSearch){
        List<WeekEndDetails> weekEndList = null;
        try{
            weekEndList = (List<WeekEndDetails>) getSqlMapClientTemplate().queryForList("queryMap.getEmployee_WeekEnd",objSearch);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return weekEndList;
    }
    
    public String get_WFH_Eligible(SearchCriteriaDTO searchObj){
        String eligible = "";
        try{
            eligible = (String) getSqlMapClientTemplate().queryForObject("queryMap.get_WFH_Eligible",searchObj);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return eligible;
    }
    
    public int getTimesheetBlockDate(){
        int block_date=0;
        try{
            String date = (String) getSqlMapClientTemplate().queryForObject("queryMap.getTimesheetBlockDate");
            block_date = Integer.parseInt(date);
        }catch(Exception e){
            e.printStackTrace();
        }
        return block_date;
    }

}
