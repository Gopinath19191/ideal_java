/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.dao;

import com.defiance.ideal.ojf.dto.LoginDTO;
import com.defiance.ideal.ojf.joiningForm.dto.JoinerFormDTO;
import com.defiance.ideal.ojf.joiningForm.dto.NationalityDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 15850
 */
public class LoginDAOImpl extends SqlMapClientDaoSupport implements LoginDAO  {
     public LoginDTO authenticateLoginDetails(LoginDTO formData) throws Exception{
         
      
         
      formData = (LoginDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getLoginDTO",
                    formData);
         System.out.println("%%%%%%%%%%%%%%"+ formData);
         return formData;
     }
     
    public LoginDTO authenticateUser(LoginDTO userAuthentication) throws NullPointerException{
        

        userAuthentication = (LoginDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getauthenticateUser",
                  userAuthentication );


         return userAuthentication;
    }    
    public LoginDTO authenticateGroup(LoginDTO groupAuthentication) throws NullPointerException{
       
       groupAuthentication = (LoginDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getGroupAuthentication",
                   groupAuthentication);
       return groupAuthentication;
    }
    
    public LoginDTO authenticateToken(String tokenRcvd){
        LoginDTO tokenAuthentication = null;
        if(tokenRcvd != null){
                tokenAuthentication = (LoginDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getTokenAuthentication",
                   tokenRcvd);
           }
       return tokenAuthentication;
        
        }

     public List<JoinerFormDTO> getRRFDetails(JoinerFormDTO formData){
         return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getRRFDetails",formData);

}
     

   
}