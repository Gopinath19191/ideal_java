/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dto;

import java.io.Serializable;

/**
 *
 * @author 16047
 */
public class AttendanceDto implements Serializable{
    private String empId;
    private String employee_id;
    private String employeeNumber;
    private String empName;
    private int id;
    private String siteCode;
    private String department;
    private String attendanceDate;
    private String firstIn;
    private String lastOut;
    private String location;
    private String timeDuration;
    private String moduleId;
    private String fromDate;
    private String toDate;
    private String attendance_status;
    private String minDate;
    private String maxDate;
    private int start_page;
    private int end_page;
    private String unitId;
    private String unitName;
    private String unit;
    private String locationId;
    private String locationName;
    private String rm_id;
    private String reasonValue;
    private String reasonName;
    private String changeTime;
    private String reason;
    private String empRemarks;
    private String revisedStatus;
    private String rmRemarks;
    private String directReportee;
    private String directReporteeId;
    private String directReporteeName;
    private String workLocation;
    private String rmName;

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }
    
    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }
    
    public String getDirectReportee() {
        return directReportee;
    }

    public void setDirectReportee(String directReportee) {
        this.directReportee = directReportee;
    }

    public String getDirectReporteeId() {
        return directReporteeId;
    }

    public void setDirectReporteeId(String directReporteeId) {
        this.directReporteeId = directReporteeId;
    }

    public String getDirectReporteeName() {
        return directReporteeName;
    }

    public void setDirectReporteeName(String directReporteeName) {
        this.directReporteeName = directReporteeName;
    }

    public String getRmRemarks() {
        return rmRemarks;
    }

    public void setRmRemarks(String rmRemarks) {
        this.rmRemarks = rmRemarks;
    }

    public String getRevisedStatus() {
        return revisedStatus;
    }

    public void setRevisedStatus(String revisedStatus) {
        this.revisedStatus = revisedStatus;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEmpRemarks() {
        return empRemarks;
    }

    public void setEmpRemarks(String empRemarks) {
        this.empRemarks = empRemarks;
    }

    public String getReasonValue() {
        return reasonValue;
    }

    public void setReasonValue(String reasonValue) {
        this.reasonValue = reasonValue;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public String getRm_id() {
        return rm_id;
    }

    public void setRm_id(String rm_id) {
        this.rm_id = rm_id;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public String getAttendance_status() {
        return attendance_status;
    }

    public void setAttendance_status(String attendance_status) {
        this.attendance_status = attendance_status;
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

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getFirstIn() {
        return firstIn;
    }

    public void setFirstIn(String firstIn) {
        this.firstIn = firstIn;
    }

    public String getLastOut() {
        return lastOut;
    }

    public void setLastOut(String lastOut) {
        this.lastOut = lastOut;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
}
