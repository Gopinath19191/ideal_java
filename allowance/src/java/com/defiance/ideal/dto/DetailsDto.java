/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.dto;
import java.io.Serializable;

/**
 *
 * @author 14053
 */
public class DetailsDto implements Serializable {
    
    private String projectName;
    private String managerName;
    private String projectId;
    private String holiday;
    private String empName;
    private String empId;
    private String workedDay;

    public String getWorkedDay() {
        return workedDay;
    }

    public void setWorkedDay(String workedDay) {
        this.workedDay = workedDay;
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

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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
    
}
