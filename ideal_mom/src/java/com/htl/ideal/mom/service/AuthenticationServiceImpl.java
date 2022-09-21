/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.service;

import com.htl.ideal.mom.dao.AuthenticationDao;
import com.htl.ideal.mom.dto.AuthenticationDto;

/**
 *
 * @author 16656
 */
public class AuthenticationServiceImpl implements AuthenticationService{
    public AuthenticationDao dao;

    public AuthenticationDao getDao() {
        return dao;
    }

    public void setDao(AuthenticationDao dao) {
        this.dao = dao;
    }
    
        
    public AuthenticationDto getUserDetails(String employee_id){
        return (AuthenticationDto) dao.getUserDetails(employee_id);
    }
    
    public boolean authenticate(AuthenticationDto authenParams){
        return dao.authenticate(authenParams);
    }
}
