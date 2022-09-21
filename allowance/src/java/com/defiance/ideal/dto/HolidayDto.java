/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 14053
 */
public class HolidayDto implements Serializable {

    private String userName;
    private String password;
    private String idealUserName;
    private String idealLoginTime;
    public String empId;
    private String groupId;
    private String userAccountId;
    private String moduleId;
    private String gCreate;
    private String gRead;
    private String gUpdate;
    private String gDelete;
    private String uCreate;
    private String uRead;
    private String uUpdate;
    private String uDelete;
    private String menuName;
    private String menuId;
    private String moduleName;
    private String employeeId;
    //Apply
    private String allowance_date;
    private String selHr;
    private String selMin;
    private String project_id;
    private String reason;
    private String status;
    private String created;
    private String modified;
    private String requestId;
    private String total_hours;
    private String project_name;
    private String approved_hours;
    private String allowanceId;
    private int start_page;
    private int end_page;
    private String statusVal;
    private String employeeName;
    private String month_filter;
    private String year_filter;
    private String start_date;
    private String end_date;
    private String excelbuttonName;
    private String created_date;
    private String approved_date;
    private String managerName;
    private String managerEmail;
    private String employeeEmail;
    private String employeeNumber;
    private String startFilter;
    private String endFilter;
    private String saveEnable;
    //Approve
    private String holidayDate;
    private String projectId;
    private String prajectName;
    private String totalHours;
    private String approvedHours;
    private String empName;
    private String requestStatus;
    private String projectName;
    private String requestList;
    private String list;
    private String rmSubmit;
    private String employee_id;
    private String id;
    private String[] chk_box;
    private String[] totalApprovedHrs;
    private String accessParameter;
    private String emploId;
    private String configKey;
    private String configValue;
    private String rejectReason;
    private String emploNo;
    private String selMon;
    private String selYear;
    private String designation;
    private String timesheetEnteredHours;
    private String timesheetApprovedHours;

    public String getTimesheetEnteredHours() {
        return timesheetEnteredHours;
    }

    public void setTimesheetEnteredHours(String timesheetEnteredHours) {
        this.timesheetEnteredHours = timesheetEnteredHours;
    }

    public String getTimesheetApprovedHours() {
        return timesheetApprovedHours;
    }

    public void setTimesheetApprovedHours(String timesheetApprovedHours) {
        this.timesheetApprovedHours = timesheetApprovedHours;
    }
      
    public String getSaveEnable() {
        return saveEnable;
    }

    public void setSaveEnable(String saveEnable) {
        this.saveEnable = saveEnable;
    }
    
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public String getSelYear() {
        return selYear;
    }

    public void setSelYear(String selYear) {
        this.selYear = selYear;
    }
    public String getSelMon() {
        return selMon;
    }

    public void setSelMon(String selMon) {
        this.selMon = selMon;
    }

    public String getEmploNo() {
        return emploNo;
    }

    public void setEmploNo(String emploNo) {
        this.emploNo = emploNo;
    }

    public String getEndFilter() {
        return endFilter;
    }

    public String getStartFilter() {
        return startFilter;
    }

    public void setEndFilter(String endFilter) {
        this.endFilter = endFilter;
    }

    public void setStartFilter(String startFilter) {
        this.startFilter = startFilter;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getApproved_date() {
        return approved_date;
    }

    public void setApproved_date(String approved_date) {
        this.approved_date = approved_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getExcelbuttonName() {
        return excelbuttonName;
    }

    public void setExcelbuttonName(String excelbuttonName) {
        this.excelbuttonName = excelbuttonName;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getMonth_filter() {
        return month_filter;
    }

    public void setMonth_filter(String month_filter) {
        this.month_filter = month_filter;
    }

    public String getYear_filter() {
        return year_filter;
    }

    public void setYear_filter(String year_filter) {
        this.year_filter = year_filter;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStatusVal() {
        return statusVal;
    }

    public void setStatusVal(String statusVal) {
        this.statusVal = statusVal;
    }

    public String getAllowanceId() {
        return allowanceId;
    }

    public void setAllowanceId(String allowanceId) {
        this.allowanceId = allowanceId;
    }

    public int getEnd_page() {
        return end_page;
    }

    public void setEnd_page(int end_page) {
        this.end_page = end_page;
    }

    public int getStart_page() {
        return start_page;
    }

    public void setStart_page(int start_page) {
        this.start_page = start_page;
    }

    public String getApproved_hours() {
        return approved_hours;
    }

    public void setApproved_hours(String approved_hours) {
        this.approved_hours = approved_hours;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getSelHr() {
        return selHr;
    }

    public void setSelHr(String selHr) {
        this.selHr = selHr;
    }

    public String getSelMin() {
        return selMin;
    }

    public void setSelMin(String selMin) {
        this.selMin = selMin;
    }

    public String getTotal_hours() {
        return total_hours;
    }

    public void setTotal_hours(String total_hours) {
        this.total_hours = total_hours;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAllowance_date() {
        return allowance_date;
    }

    public void setAllowance_date(String allowance_date) {
        this.allowance_date = allowance_date;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getgCreate() {
        return gCreate;
    }

    public void setgCreate(String gCreate) {
        this.gCreate = gCreate;
    }

    public String getgDelete() {
        return gDelete;
    }

    public void setgDelete(String gDelete) {
        this.gDelete = gDelete;
    }

    public String getgRead() {
        return gRead;
    }

    public void setgRead(String gRead) {
        this.gRead = gRead;
    }

    public String getgUpdate() {
        return gUpdate;
    }

    public void setgUpdate(String gUpdate) {
        this.gUpdate = gUpdate;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getIdealLoginTime() {
        return idealLoginTime;
    }

    public void setIdealLoginTime(String idealLoginTime) {
        this.idealLoginTime = idealLoginTime;
    }

    public String getIdealUserName() {
        return idealUserName;
    }

    public void setIdealUserName(String idealUserName) {
        this.idealUserName = idealUserName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getuCreate() {
        return uCreate;
    }

    public void setuCreate(String uCreate) {
        this.uCreate = uCreate;
    }

    public String getuDelete() {
        return uDelete;
    }

    public void setuDelete(String uDelete) {
        this.uDelete = uDelete;
    }

    public String getuRead() {
        return uRead;
    }

    public void setuRead(String uRead) {
        this.uRead = uRead;
    }

    public String getuUpdate() {
        return uUpdate;
    }

    public void setuUpdate(String uUpdate) {
        this.uUpdate = uUpdate;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApprovedHours() {
        return approvedHours;
    }

    public void setApprovedHours(String approvedHours) {
        this.approvedHours = approvedHours;
    }

    public String getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getPrajectName() {
        return prajectName;
    }

    public void setPrajectName(String prajectName) {
        this.prajectName = prajectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRequestList() {
        return requestList;
    }

    public void setRequestList(String requestList) {
        this.requestList = requestList;
    }

    public String getRmSubmit() {
        return rmSubmit;
    }

    public void setRmSubmit(String rmSubmit) {
        this.rmSubmit = rmSubmit;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getChk_box() {
        return chk_box;
    }

    public void setChk_box(String[] chk_box) {
        this.chk_box = chk_box;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getAccessParameter() {
        return accessParameter;
    }

    public void setAccessParameter(String accessParameter) {
        this.accessParameter = accessParameter;
    }

    public String getEmploId() {
        return emploId;
    }

    public void setEmploId(String emploId) {
        this.emploId = emploId;
    }

    public String[] getTotalApprovedHrs() {
        return totalApprovedHrs;
    }

    public void setTotalApprovedHrs(String[] totalApprovedHrs) {
        this.totalApprovedHrs = totalApprovedHrs;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

}
