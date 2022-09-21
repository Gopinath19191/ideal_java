/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.dao;

import com.htl.ideal.mom.dto.AuthenticationDto;

/**
 *
 * @author 16656
 */
public interface AuthenticationDao {
    public AuthenticationDto getUserDetails(String employee_id);
    public boolean authenticate(AuthenticationDto authenParams);
}
