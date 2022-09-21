/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.login.service;

import com.attendance.employee.login.dto.AuthenticateDto;

/**
 *
 * @author 16047
 */
public interface AuthenticateService {
    public AuthenticateDto getUserDetails(String empId);
    public boolean authenticate(AuthenticateDto authenParams);
}
