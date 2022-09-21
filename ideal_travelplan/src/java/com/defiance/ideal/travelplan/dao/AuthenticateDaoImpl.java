/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dao;

import com.defiance.ideal.travelplan.dto.AuthenticateDto;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14578
 */
public class AuthenticateDaoImpl extends SqlMapClientDaoSupport implements AuthenticateDao {

    public boolean authenticate(AuthenticateDto authenParams) {
        try {
            AuthenticateDto userAuthentication = null;
            AuthenticateDto groupAuthentication = null;
            AuthenticateDto loginAuthentication = null;

            loginAuthentication = (AuthenticateDto) getSqlMapClientTemplate().queryForObject("Authenticate.loginCheck",authenParams);

            if(loginAuthentication==null){
                System.out.println("Not Logged IN");
                return false;
            } else {
                userAuthentication = (AuthenticateDto) getSqlMapClientTemplate().queryForObject("Authenticate.authenticateUser", authenParams);
                if (userAuthentication == null || userAuthentication.getuCreate() == null || userAuthentication.getuCreate().equals("-1")) {
                    groupAuthentication = (AuthenticateDto) getSqlMapClientTemplate().queryForObject("Authenticate.authenticateGroup", authenParams);
                    if (groupAuthentication==null || groupAuthentication.getgCreate() == null || groupAuthentication.getgCreate().equals("-1")) {
                        System.out.println("Group Auth Failed");
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public AuthenticateDto getUserDetails(String empId) {
        System.out.println("Empppp"+empId);
        return (AuthenticateDto) getSqlMapClientTemplate().queryForObject("Authenticate.getUserDetails",empId);
    }
    
    public AuthenticateDto getTpRequestorEmpStatus(String empId) {
        System.out.println("Empppp"+empId);
        return (AuthenticateDto) getSqlMapClientTemplate().queryForObject("Authenticate.getTpRequestorEmpStatus",empId);
    }

    public String checkEmpIsGerman(String empId) {
        return (String) getSqlMapClientTemplate().queryForObject("Authenticate.checkEmpIsGerman",empId);
    }

}
