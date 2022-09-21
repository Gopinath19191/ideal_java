
package com.mycompany.employee.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class MasterDto implements Serializable {
    private String projectID;
    private String projectCode;
    private String projectName;
    private String roleID;
    private String roleName;
    private String phaseID;
    private String phaseName;
    private String taskID;
    private String taskName;
    private String is_role_enabled;
    private String reasonText;
    private String startDate;
    private String endDate;
    private String parentId;
    private String configurationKey;
    private String configurationValue;
    private String dbProjectID;
    private String NphaseId;
    private String NphaseName;
    private String NtaskId;
    private String NtaskName;
    private String projectPrimaryKey;
    private String phasePrimarykey;
    private String taskPrimarykey;

    public String getPhasePrimarykey() {
        return phasePrimarykey;
    }

    public void setPhasePrimarykey(String phasePrimarykey) {
        this.phasePrimarykey = phasePrimarykey;
    }

    public String getTaskPrimarykey() {
        return taskPrimarykey;
    }

    public void setTaskPrimarykey(String taskPrimarykey) {
        this.taskPrimarykey = taskPrimarykey;
    }
    
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
    
    public String getNphaseId() {
        if(this.NphaseId == null) return "Others";
        return NphaseId;
    }

    public void setNphaseId(String NphaseId) {
        this.NphaseId = NphaseId;
    }

    public String getNphaseName() {
        if(this.NphaseName == null) return "Others";
        return NphaseName;
    }

    public void setNphaseName(String NphaseName) {
        this.NphaseName = NphaseName;
    }

    public String getNtaskId() {
        if(this.NtaskId == null) return "Others";
        return NtaskId;
    }

    public void setNtaskId(String NtaskId) {
        this.NtaskId = NtaskId;
    }

    public String getNtaskName() {
        if(this.NtaskName == null) return "Others";
        return NtaskName;
    }

    public void setNtaskName(String NtaskName) {
        this.NtaskName = NtaskName;
    }
    
    public String getProjectID() {
        if(this.projectID == null) return "";
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRoleID() {
        if(this.roleID==null) return "";
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        if(this.roleName == null) return "";
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhaseID() {
        if(this.phaseID== null) return "Others";
        return phaseID;
    }

    public void setPhaseID(String phaseID) {
        this.phaseID = phaseID;
    }

    public String getPhaseName() {
        if(this.phaseName== null) return "Others";
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getTaskID() {
        if(this.taskID == null) return "Others";
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        if(this.taskID== null) return "Others";
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getIs_role_enabled() {
        return is_role_enabled;
    }

    public void setIs_role_enabled(String is_role_enabled) {
        this.is_role_enabled = is_role_enabled;
    }

    public String getReasonText() {
        if(this.reasonText==null) return "";
        return reasonText;
    }

    public void setReasonText(String reasonText) {
        this.reasonText = reasonText;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getConfigurationKey() {
        return configurationKey;
    }

    public void setConfigurationKey(String configurationKey) {
        this.configurationKey = configurationKey;
    }

    public String getConfigurationValue() {
        return configurationValue;
    }

    public void setConfigurationValue(String configurationValue) {
        this.configurationValue = configurationValue;
    }

    public String getDbProjectID() {
        return dbProjectID;
    }

    public void setDbProjectID(String dbProjectID) {
        this.dbProjectID = dbProjectID;
    }
	public String getProjectPrimaryKey() {
        return projectPrimaryKey;
    }

    public void setProjectPrimaryKey(String projectPrimaryKey) {
        this.projectPrimaryKey = projectPrimaryKey;
    }
         
}
