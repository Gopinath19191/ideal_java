/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.employee.dao;

import com.defiance.ideal.exitMgmt.login.dto.LoginDTO;
import java.util.List;

/**
 *
 * @author 14583
 */
public interface ExitEmployeeDAO {

    public  List<LoginDTO> getEmployeeList(LoginDTO formData);

}
