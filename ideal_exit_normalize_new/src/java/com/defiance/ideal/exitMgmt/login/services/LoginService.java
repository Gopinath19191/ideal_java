/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.login.services;

import com.defiance.ideal.exitMgmt.login.dto.LoginDTO;

/**
 *
 * @author 14583
 */
public interface LoginService {

    public  LoginDTO getLoginDetails(LoginDTO formData);

}
