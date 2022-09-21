/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dto;

import java.util.Date;

/**
 *
 * @author 16365
 */
public class AttendanceReportDto {
    private String employeeNumber;
    private String empName;
    private String RM_Name;
    private String attendanceDate;
    private Date firstIn;
    private Date lastOut;
    private String timeDuration;
    private String Worklocation;
    private String location;
    //private String crossOverDate;
    private String attendance_status;
    private String unit;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRM_Name() {
        return RM_Name;
    }

    public void setRM_Name(String RM_Name) {
        this.RM_Name = RM_Name;
    }

    public Date getFirstIn() {
        return firstIn;
    }

    public void setFirstIn(Date firstIn) {
        this.firstIn = firstIn;
    }

    public Date getLastOut() {
        return lastOut;
    }

    public void setLastOut(Date lastOut) {
        this.lastOut = lastOut;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getWorklocation() {
        return Worklocation;
    }

    public void setWorklocation(String Worklocation) {
        this.Worklocation = Worklocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttendance_status() {
        return attendance_status;
    }

    public void setAttendance_status(String attendance_status) {
        this.attendance_status = attendance_status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

//    public String getCrossOverDate() {
//        return crossOverDate;
//    }
//
//    public void setCrossOverDate(String crossOverDate) {
//        this.crossOverDate = crossOverDate;
//    }    
}
