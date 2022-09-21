/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.exitMgmt.login.dto;

import java.util.List;

/**
 *
 * @author 14583
 */
public class LoginDTO {

    private String userName;
    private String password;
    private String idealUserName;
    private String idealLoginTime;
    private String groupId;
    private String empId;
    private String userAccountId;
    private String moduleId;
    private int manager;
    
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

    private int resignation_form = 0;
    private int rm_approval = 0;
    private int fin_approval = 0;
    private int network_approval = 0;
    private int admin_approval = 0;
    private int hr_approval = 0;
    private int rm_clearance = 0;

    private int rm_approval_view = 0;
    private int rm_clearance_view = 0;
    private int fin_approval_view = 0;
    private int admin_approval_view = 0;
    private int network_approval_view = 0;
    private int hr_approval_view = 0;
    private int exit_survey = 0;
    private int view_status = 0;
    
    private int rm_approvalId;
    private int rm_clearanceId;
    private int fin_approvalId;
    private int admin_approvalId;
    private int network_approvalId;
    private int hr_approvalId;


    List moduleName;

    public int getAdmin_approval() {
        return admin_approval;
    }

    public void setAdmin_approval(int admin_approval) {
        this.admin_approval = admin_approval;
    }

    public int getAdmin_approvalId() {
        return admin_approvalId;
    }

    public void setAdmin_approvalId(int admin_approvalId) {
        this.admin_approvalId = admin_approvalId;
    }

    public int getAdmin_approval_view() {
        return admin_approval_view;
    }

    public void setAdmin_approval_view(int admin_approval_view) {
        this.admin_approval_view = admin_approval_view;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public int getExit_survey() {
        return exit_survey;
    }

    public void setExit_survey(int exit_survey) {
        this.exit_survey = exit_survey;
    }

    public int getFin_approval() {
        return fin_approval;
    }

    public void setFin_approval(int fin_approval) {
        this.fin_approval = fin_approval;
    }

    public int getFin_approvalId() {
        return fin_approvalId;
    }

    public void setFin_approvalId(int fin_approvalId) {
        this.fin_approvalId = fin_approvalId;
    }

    public int getFin_approval_view() {
        return fin_approval_view;
    }

    public void setFin_approval_view(int fin_approval_view) {
        this.fin_approval_view = fin_approval_view;
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

    public int getHr_approval() {
        return hr_approval;
    }

    public void setHr_approval(int hr_approval) {
        this.hr_approval = hr_approval;
    }

    public int getHr_approvalId() {
        return hr_approvalId;
    }

    public void setHr_approvalId(int hr_approvalId) {
        this.hr_approvalId = hr_approvalId;
    }

    public int getHr_approval_view() {
        return hr_approval_view;
    }

    public void setHr_approval_view(int hr_approval_view) {
        this.hr_approval_view = hr_approval_view;
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

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
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

    public List getModuleName() {
        return moduleName;
    }

    public void setModuleName(List moduleName) {
        this.moduleName = moduleName;
    }

    public int getNetwork_approval() {
        return network_approval;
    }

    public void setNetwork_approval(int network_approval) {
        this.network_approval = network_approval;
    }

    public int getNetwork_approvalId() {
        return network_approvalId;
    }

    public void setNetwork_approvalId(int network_approvalId) {
        this.network_approvalId = network_approvalId;
    }

    public int getNetwork_approval_view() {
        return network_approval_view;
    }

    public void setNetwork_approval_view(int network_approval_view) {
        this.network_approval_view = network_approval_view;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getResignation_form() {
        return resignation_form;
    }

    public void setResignation_form(int resignation_form) {
        this.resignation_form = resignation_form;
    }

    public int getRm_approval() {
        return rm_approval;
    }

    public void setRm_approval(int rm_approval) {
        this.rm_approval = rm_approval;
    }

    public int getRm_approvalId() {
        return rm_approvalId;
    }

    public void setRm_approvalId(int rm_approvalId) {
        this.rm_approvalId = rm_approvalId;
    }

    public int getRm_approval_view() {
        return rm_approval_view;
    }

    public void setRm_approval_view(int rm_approval_view) {
        this.rm_approval_view = rm_approval_view;
    }

    public int getRm_clearance() {
        return rm_clearance;
    }

    public void setRm_clearance(int rm_clearance) {
        this.rm_clearance = rm_clearance;
    }

    public int getRm_clearanceId() {
        return rm_clearanceId;
    }

    public void setRm_clearanceId(int rm_clearanceId) {
        this.rm_clearanceId = rm_clearanceId;
    }

    public int getRm_clearance_view() {
        return rm_clearance_view;
    }

    public void setRm_clearance_view(int rm_clearance_view) {
        this.rm_clearance_view = rm_clearance_view;
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

    public int getView_status() {
        return view_status;
    }

    public void setView_status(int view_status) {
        this.view_status = view_status;
    }

  }
