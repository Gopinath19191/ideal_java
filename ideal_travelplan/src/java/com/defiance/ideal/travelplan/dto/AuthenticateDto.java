/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.travelplan.dto;
import java.io.Serializable;

/**
 *
 * @author 15065
 */
public class AuthenticateDto implements Serializable {
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
    private String employeeName;
    private String employment_status;
    private String transferred_country_id;

    public String getEmployment_status() {
        return employment_status;
    }

    public void setEmployment_status(String employment_status) {
        this.employment_status = employment_status;
    }

    public String getTransferred_country_id() {
        return transferred_country_id;
    }

    public void setTransferred_country_id(String transferred_country_id) {
        this.transferred_country_id = transferred_country_id;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
}
