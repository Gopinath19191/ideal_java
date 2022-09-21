/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.dao;

import com.htl.ideal.mom.dto.AuthenticationDto;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16656
 */
public class AuthenticationDaoImpl extends SqlMapClientDaoSupport implements AuthenticationDao{
    public AuthenticationDto getUserDetails(String employee_id){
        return (AuthenticationDto) getSqlMapClientTemplate().queryForObject("AuthenticationMap.getUserDetails",employee_id);
    }
    
    public boolean authenticate(AuthenticationDto authenParams){
        List<AuthenticationDto> template = null;
        try {
            AuthenticationDto userAuthentication = null;
            AuthenticationDto groupAuthentication = null;
            AuthenticationDto loginAuthentication = null;
            loginAuthentication = (AuthenticationDto) getSqlMapClientTemplate().queryForObject("AuthenticationMap.loginCheck",authenParams);
            if(loginAuthentication==null){
                System.out.println("Not Logged IN");
                return false;
            }else{
                userAuthentication = (AuthenticationDto) getSqlMapClientTemplate().queryForObject("AuthenticationMap.authenticateUser", authenParams);
                System.out.println("userAuthentication == " + userAuthentication);
                if (userAuthentication == null || userAuthentication.getuCreate() == null || userAuthentication.getuCreate().equals("-1")) {
                    groupAuthentication = (AuthenticationDto) getSqlMapClientTemplate().queryForObject("AuthenticationMap.authenticateGroup", authenParams);
                    System.out.println("groupAuthentication == " + groupAuthentication);
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
}
