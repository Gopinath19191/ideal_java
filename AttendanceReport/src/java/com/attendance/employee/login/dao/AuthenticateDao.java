/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.login.dao;

import com.attendance.employee.login.dto.AuthenticateDto;

/**
 *
 * @author 16047
 */
public interface AuthenticateDao {
    public AuthenticateDto getUserDetails(String empId);
    public boolean authenticate(AuthenticateDto authenParams);
}
