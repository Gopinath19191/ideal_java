/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dao;

import com.defiance.ideal.travelplan.dto.AuthenticateDto;

/**
 *
 * @author 14578
 */
public interface AuthenticateDao {

    public boolean authenticate(AuthenticateDto authenParams);
    public AuthenticateDto getUserDetails(String empId);
}
