/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dto;

/**
 *
 * @author 16363
 */
public class AttendancePmReportFilterDto {
    private String project_id;
    private String emp_id;
    private String username;
    private String project_name;
    private int in_time;
    private int out_time;
    private String availableHours;
    private String time_duration;
    private String work_location;
    private String swipe_location;
    private String emp_name;
    private String unit;
    private String from_date;
    private String to_date;
    private String attendance_hours;
    private String attendance_date;
    private String projectId;
    private String projectName;
    private String projectCode;
    private String TimesheetEntryProject;
    private String employeeNumber;
    private String empName;
     private String employeeId;
    private String empId;
    private String fromDate;
    private String toDate;
    private String value;
    private int start_page;
    private int end_page;
    private String filterDataList;
    private String recordCount;
    private String loggedInempId;
    private String aFrom;
    private String aTo;
    private int startIndex;
    private int endIndex;
    private int totalRecords;
    private int role;
    private String moduleId;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
    
    public enum Role {
       EMP, RM, ADMIN, PM;
    };

    public int getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role.ordinal();
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getaFrom() {
        return aFrom;
    }

    public void setaFrom(String aFrom) {
        this.aFrom = aFrom;
    }

    public String getaTo() {
        return aTo;
    }

    public void setaTo(String aTo) {
        this.aTo = aTo;
    }

    public String getLoggedInempId() {
        return loggedInempId;
    }

    public void setLoggedInempId(String loggedInempId) {
        this.loggedInempId = loggedInempId;
    }
    public String getFilterDataList() {
        return filterDataList;
    }

    public void setFilterDataList(String filterDataList) {
        this.filterDataList = filterDataList;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public int getStart_page() {
        return start_page;
    }

    public void setStart_page(int start_page) {
        this.start_page = start_page;
    }

    public int getEnd_page() {
        return end_page;
    }

    public void setEnd_page(int end_page) {
        this.end_page = end_page;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getIn_time() {
        return in_time;
    }

    public void setIn_time(int in_time) {
        this.in_time = in_time;
    }

    public int getOut_time() {
        return out_time;
    }

    public void setOut_time(int out_time) {
        this.out_time = out_time;
    }

    public String getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }

    public String getTime_duration() {
        return time_duration;
    }

    public void setTime_duration(String time_duration) {
        this.time_duration = time_duration;
    }

    public String getWork_location() {
        return work_location;
    }

    public void setWork_location(String work_location) {
        this.work_location = work_location;
    }

    public String getSwipe_location() {
        return swipe_location;
    }

    public void setSwipe_location(String swipe_location) {
        this.swipe_location = swipe_location;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getAttendance_hours() {
        return attendance_hours;
    }

    public void setAttendance_hours(String attendance_hours) {
        this.attendance_hours = attendance_hours;
    }

    public String getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(String attendance_date) {
        this.attendance_date = attendance_date;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getTimesheetEntryProject() {
        return TimesheetEntryProject;
    }

    public void setTimesheetEntryProject(String TimesheetEntryProject) {
        this.TimesheetEntryProject = TimesheetEntryProject;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }
    
}
