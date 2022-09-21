/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.ojf.dao;
import com.defiance.ideal.ojf.dto.LoginDTO;
import com.defiance.ideal.ojf.joiningForm.dto.JoinerFormDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author 15850
 */

public interface LoginDAO {

    public LoginDTO authenticateLoginDetails(LoginDTO formData) throws Exception;
    public LoginDTO authenticateUser(LoginDTO logindto);
    public LoginDTO authenticateGroup(LoginDTO loginDTO);
    public LoginDTO authenticateToken(String toString);
    public List<JoinerFormDTO> getRRFDetails(JoinerFormDTO formData);
  

    
}