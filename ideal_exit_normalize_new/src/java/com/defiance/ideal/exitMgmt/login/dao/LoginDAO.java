/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.login.dao;

import com.defiance.ideal.exitMgmt.login.dto.LoginDTO;

/**
 *
 * @author 14583
 */
public interface LoginDAO {

    public  LoginDTO getLoginDetails(LoginDTO formData);

}
