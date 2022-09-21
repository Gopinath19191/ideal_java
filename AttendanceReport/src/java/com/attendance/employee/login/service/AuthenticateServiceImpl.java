/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.login.service;

import com.attendance.employee.login.dao.AuthenticateDaoImpl;
import com.attendance.employee.login.dto.AuthenticateDto;

/**
 *
 * @author 16047
 */
public class AuthenticateServiceImpl implements AuthenticateService{
     private AuthenticateDaoImpl authenticateDao;

    public AuthenticateDaoImpl getAuthenticateDao() {
        return authenticateDao;
    }

    public void setAuthenticateDao(AuthenticateDaoImpl authenticateDao) {
        this.authenticateDao = authenticateDao;
    }
     
    public AuthenticateDto getUserDetails(String empId) {
        AuthenticateDto userDetails = null;
        try{
            userDetails = authenticateDao.getUserDetails(empId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return userDetails;
    }

    public boolean authenticate(AuthenticateDto authenParams) {
        return authenticateDao.authenticate(authenParams);
    }
    
}
