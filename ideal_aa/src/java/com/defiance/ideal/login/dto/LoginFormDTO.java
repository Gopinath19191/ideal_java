/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.login.dto;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
/**
 *
 * @author Admin
 */
@Jpf.FormBean()
public class LoginFormDTO implements java.io.Serializable{
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public String username;
public String password;
public String groupId;
public String empId;
public String userAccountId;

public String gCreate;
public String gRead;
public String gUpdate;
public String gDelete;

public String uCreate;
public String uRead;
public String uUpdate;
public String uDelete;
public int userCount;
public String managerSurvey;

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public String getManagerSurvey() {
        return managerSurvey;
    }

    public void setManagerSurvey(String managerSurvey) {
        this.managerSurvey = managerSurvey;
    }

}
