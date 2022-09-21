/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dto;

/**
 *
 * @author 16365
 */
public class SearchDTO {
 
    private String employeeSearch;
    private String managerId;
    private String loginEmpId;
    
    public String getEmployeeSearch() {
        return employeeSearch;
    }

    public void setEmployeeSearch(String employeeSearch) {
        this.employeeSearch = employeeSearch;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getLoginEmpId() {
        return loginEmpId;
    }

    public void setLoginEmpId(String loginEmpId) {
        this.loginEmpId = loginEmpId;
    }
       
    
}
