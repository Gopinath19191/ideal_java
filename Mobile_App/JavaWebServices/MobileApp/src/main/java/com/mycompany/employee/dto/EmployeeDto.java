
package com.mycompany.employee.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class EmployeeDto implements Serializable {
    private int id;
    private Double version;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNo;   
    private String city;
    private String resultVal;
    private String deviceId;
    private double appVersion;  
    private String tokenNo;
    private String empName;
    private String gender;
    private String designation;
    private String band;
    private String department;
    private String employmentStatus;
    private String dob;
    private String doj;    
    private String location;
    private String platform;    
    private String regId;
    private Timestamp sesStartTime;
    private Timestamp currentTime;
    private String startDate;
    private String endDate;
    private String newPassword;
    private String confirmPassword;
    private Timestamp timesheet_date;
    private int  projectID;
    private String project_name;
    private String timesheet_hours;
    private String rejection_remarks;
    private String success;
    private String err_code;
    private String err_message;
    private String date;
    private String available_hours;
    private String attendance_hours;   
    private String regularization_hours;
    private String status;
    private String is_holiday;
    private String project_id;                   
    private String shift_id;
    private String role_id;
    private String phase_id;
    private String task_id;
    private String is_regularized;
    private String reasons;
    private String remark;
    private Date timeSheetDate;
    private int isDeleted;
    private String timesheetId;   
    private String totalTimesheet_hours;   
    private String is_WeekEnd; 
    private String is_HfLeave;     
    private Date monthStartdate;
    private Date yearStartdate;
    private int month_count;
    private int year_count;
    private int WHF_Per_Month;
    private int WHF_Per_Year;
    private String Emp_id;
    private String timeSheetDateStr;  
    private String employeePhoto; 
    private String leaveReqID;
    private String leave_from_date;
    private String leave_to_date;
    
    
    public String getLeave_from_date() {
        return leave_from_date;
    }

    public void setLeave_from_date(String leave_from_date) {
        this.leave_from_date = leave_from_date;
    }

    public String getLeave_to_date() {
        return leave_to_date;
    }

    public void setLeave_to_date(String leave_to_date) {
        this.leave_to_date = leave_to_date;
    }
    
    public String getLeaveReqID() {
        return leaveReqID;
    }

    public void setLeaveReqID(String leaveReqID) {
        this.leaveReqID = leaveReqID;
    }
    
    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getResultVal() {
        return resultVal;
    }

    public void setResultVal(String resultVal) {
        this.resultVal = resultVal;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }   

    public double getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(double appVersion) {
        this.appVersion = appVersion;
    }    

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Timestamp getSesStartTime() {
        return sesStartTime;
    }

    public void setSesStartTime(Timestamp sesStartTime) {
        this.sesStartTime = sesStartTime;
    }

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Timestamp getTimesheet_date() {
        return timesheet_date;
    }

    public void setTimesheet_date(Timestamp timesheet_date) {
        this.timesheet_date = timesheet_date;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTimesheet_hours() {
        return timesheet_hours;
    }

    public void setTimesheet_hours(String timesheet_hours) {
        this.timesheet_hours = timesheet_hours;
    }

    public String getRejection_remarks() {
        return rejection_remarks;
    }

    public void setRejection_remarks(String rejection_remarks) {
        this.rejection_remarks = rejection_remarks;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_message() {
        return err_message;
    }

    public void setErr_message(String err_message) {
        this.err_message = err_message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvailable_hours() {
        return available_hours;
    }

    public void setAvailable_hours(String available_hours) {
        this.available_hours = available_hours;
    }

    public String getAttendance_hours() {
        return attendance_hours;
    }

    public void setAttendance_hours(String attendance_hours) {
        this.attendance_hours = attendance_hours;
    }

    public String getRegularization_hours() {
        return regularization_hours;
    }

    public void setRegularization_hours(String regularization_hours) {
        this.regularization_hours = regularization_hours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_holiday() {
        return is_holiday;
    }

    public void setIs_holiday(String is_holiday) {
        this.is_holiday = is_holiday;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getShift_id() {
        return shift_id;
    }

    public void setShift_id(String shift_id) {
        this.shift_id = shift_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getPhase_id() {
        return phase_id;
    }

    public void setPhase_id(String phase_id) {
        this.phase_id = phase_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getIs_regularized() {
        return is_regularized;
    }

    public void setIs_regularized(String is_regularized) {
        this.is_regularized = is_regularized;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }     

    public Date getTimeSheetDate() {
        return timeSheetDate;
    }

    public void setTimeSheetDate(Date timeSheetDate) {
        this.timeSheetDate = timeSheetDate;
    }   

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(String timesheetId) {
        this.timesheetId = timesheetId;
    }

    public String getTotalTimesheet_hours() {
        return totalTimesheet_hours;
    }

    public void setTotalTimesheet_hours(String totalTimesheet_hours) {
        this.totalTimesheet_hours = totalTimesheet_hours;
    }

    public String getIs_WeekEnd() {
        return is_WeekEnd;
    }

    public void setIs_WeekEnd(String is_WeekEnd) {
        this.is_WeekEnd = is_WeekEnd;
    }    

    public String getIs_HfLeave() {
        return is_HfLeave;
    }

    public void setIs_HfLeave(String is_HfLeave) {
        this.is_HfLeave = is_HfLeave;
    }

    public Date getMonthStartdate() {
        return monthStartdate;
    }

    public void setMonthStartdate(Date monthStartdate) {
        this.monthStartdate = monthStartdate;
    }

    public Date getYearStartdate() {
        return yearStartdate;
    }

    public void setYearStartdate(Date yearStartdate) {
        this.yearStartdate = yearStartdate;
    }

    public int getMonth_count() {
        return month_count;
    }

    public void setMonth_count(int month_count) {
        this.month_count = month_count;
    }

    public int getYear_count() {
        return year_count;
    }

    public void setYear_count(int year_count) {
        this.year_count = year_count;
    }

    public int getWHF_Per_Month() {
        return WHF_Per_Month;
    }

    public void setWHF_Per_Month(int WHF_Per_Month) {
        this.WHF_Per_Month = WHF_Per_Month;
    }

    public int getWHF_Per_Year() {
        return WHF_Per_Year;
    }

    public void setWHF_Per_Year(int WHF_Per_Year) {
        this.WHF_Per_Year = WHF_Per_Year;
    }

    public String getEmp_id() {
        return Emp_id;
    }

    public void setEmp_id(String Emp_id) {
        this.Emp_id = Emp_id;
    }

    public String getTimeSheetDateStr() {
        return timeSheetDateStr;
    }

    public void setTimeSheetDateStr(String timeSheetDateStr) {
        this.timeSheetDateStr = timeSheetDateStr;
    }     

    public String getEmployeePhoto() {
        return employeePhoto;
    }

    public void setEmployeePhoto(String employeePhoto) {
        this.employeePhoto = employeePhoto;
    }  
        
}
