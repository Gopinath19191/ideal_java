/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.login.services;

import com.defiance.ideal.exitMgmt.login.dao.LoginDAOImpl;
import com.defiance.ideal.exitMgmt.login.dto.LoginDTO;
import java.util.List;

/**
 *
 * @author 14583
 */
public class LoginServiceImpl implements LoginService {

    private LoginDAOImpl loginImplObj;

    public LoginDAOImpl getLoginImplObj() {
        return loginImplObj;
    }

    public void setLoginImplObj(LoginDAOImpl loginImplObj) {
        this.loginImplObj = loginImplObj;
    }

    public LoginDTO getLoginDetails(LoginDTO loginFormData) {
         LoginDTO loginDetails = null;
        try {
            loginDetails = getLoginImplObj().getLoginDetails(loginFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginDetails;
    }

    public LoginDTO getIdealUserName(String idealToken) {
        LoginDTO idealUserName = null;
        try {
            idealUserName = getLoginImplObj().getIdealUserName(idealToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idealUserName;
    }

    public List<LoginDTO> authenticateUser(String empId, List menuNames) {
        List<LoginDTO> authenticateUser = null;
        try {
            authenticateUser = (List<LoginDTO>) getLoginImplObj().authenticateUser(empId,menuNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticateUser;
    }

    public List<LoginDTO> authenticateGroup(String groupId, List menuNames) {
        List<LoginDTO> authenticateGroup = null;
        try {
            authenticateGroup = getLoginImplObj().authenticateGroup(groupId,menuNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticateGroup;
    }



}
