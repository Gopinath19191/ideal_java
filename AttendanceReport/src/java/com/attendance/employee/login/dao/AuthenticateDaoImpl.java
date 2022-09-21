/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.login.dao;

import com.attendance.employee.login.dto.AuthenticateDto;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16047
 */
public class AuthenticateDaoImpl extends SqlMapClientDaoSupport implements AuthenticateDao{

    public AuthenticateDto getUserDetails(String empId) {
        AuthenticateDto userDetails = null;
        try{
            userDetails = (AuthenticateDto)getSqlMapClientTemplate().queryForObject("AuthenticateMap.getUserDetails", empId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return userDetails;
    }

    public boolean authenticate(AuthenticateDto authenParams) {
        List<AuthenticateDto> template = null;
        try {
            AuthenticateDto userAuthentication = null;
            AuthenticateDto groupAuthentication = null;
            AuthenticateDto loginAuthentication = null;
            loginAuthentication = (AuthenticateDto) getSqlMapClientTemplate().queryForObject("AuthenticateMap.loginCheck", authenParams);
            if (loginAuthentication == null) {
                return false;
            } else {
                userAuthentication = (AuthenticateDto) getSqlMapClientTemplate().queryForObject("AuthenticateMap.authenticateUser", authenParams);
                if (userAuthentication == null || userAuthentication.getuCreate() == null || userAuthentication.getuCreate().equals("-1")) {
                    groupAuthentication = (AuthenticateDto) getSqlMapClientTemplate().queryForObject("AuthenticateMap.authenticateGroup", authenParams);
                    if (groupAuthentication == null || groupAuthentication.getgCreate() == null || groupAuthentication.getgCreate().equals("-1")) {
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
