/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.LoginDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14053
 */
public class AuthenticatorDaoImpl extends SqlMapClientDaoSupport {


    public boolean authenticate(LoginDTO authenParams) {

        List<LoginDTO> template = null;

        try {
                LoginDTO userAuthentication = null;
                LoginDTO groupAuthentication = null;
                LoginDTO loginAuthentication = null;

                    loginAuthentication = (LoginDTO) getSqlMapClientTemplate().queryForObject("AuthenticationMap.loginCheck",authenParams);

                    System.out.println("loginAuthentication = " + loginAuthentication);
                    
                    if(loginAuthentication==null){
                        System.out.println("Not Logged IN");
                        return false;

                    }else{
                        userAuthentication = (LoginDTO) getSqlMapClientTemplate().queryForObject("AuthenticationMap.authenticateUser", authenParams);

                        System.out.println("userAuthentication == " + userAuthentication);

                        if (userAuthentication == null || userAuthentication.getuCreate() == null || userAuthentication.getuCreate().equals("-1")) {

                            groupAuthentication = (LoginDTO) getSqlMapClientTemplate().queryForObject("AuthenticationMap.authenticateGroup", authenParams);

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

    public LoginDTO getUserDetails(String empId) {

        return (LoginDTO) getSqlMapClientTemplate().queryForObject("AuthenticationMap.getUserDetails",empId);
        
    }

    public List<LoginDTO> fetchConfigList(LoginDTO filterData) {
        List<LoginDTO> template = null;

        try {
            template = getSqlMapClientTemplate().queryForList("AuthenticationMap.fetchConfigList",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }
                
                
                




}
