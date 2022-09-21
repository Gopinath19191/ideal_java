/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.login.dao;

import com.defiance.ideal.exitMgmt.login.dto.LoginDTO;
import java.security.MessageDigest;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14583
 */
public class LoginDAOImpl extends SqlMapClientDaoSupport implements LoginDAO{

    public LoginDTO getLoginDetails(LoginDTO loginFormData) {
        LoginDTO loginDetails = null;
        try {
             if(loginFormData.getIdealUserName()!=null){
                 loginDetails =(LoginDTO) getSqlMapClientTemplate().queryForObject("LoginDetails.getLoginDetailsWOPassword",loginFormData);
            }else{
//                 String toEnc = loginFormData.getPassword();
//            MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
//            mdEnc.reset();
//            mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
//            byte messageDigest[] = mdEnc.digest();
//            StringBuffer sb=new StringBuffer();
//            for (int i = 0; i < messageDigest.length; i++) {
//                    String hex=Integer.toHexString(0xFF & messageDigest[i]);
//                    if(hex.length()==1){sb.append('0');}
//                    sb.append(hex);
//            }
//            String userPassword = sb.toString(); // Encrypted string
           String toEnc = loginFormData.getPassword();
      MessageDigest mdEnc = MessageDigest.getInstance("MD5");
      mdEnc.reset();
      mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
      byte[] messageDigest = mdEnc.digest();
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < messageDigest.length; i++) {
        String hex = Integer.toHexString(0xFF & messageDigest[i]);
        if (hex.length() == 1) {
          sb.append('0');
        }
        sb.append(hex);
      }
      loginFormData.setPassword(sb.toString());
            //loginFormData.setPassword(toEnc);
            loginDetails =(LoginDTO) getSqlMapClientTemplate().queryForObject("LoginDetails.getLoginDetails",loginFormData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginDetails;
    }

    public LoginDTO getIdealUserName(String idealToken) {
        LoginDTO idealUserName = null;
        try {
            idealUserName = (LoginDTO) getSqlMapClientTemplate().queryForObject("LoginDetails.getIdealUserName",idealToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idealUserName;
    }

    public List<LoginDTO> authenticateUser(String userAccountId,List menuNames) {
        List<LoginDTO> authenticateUser = null;
        try {
            LoginDTO formData = new LoginDTO();
            formData.setUserAccountId(userAccountId);
            formData.setModuleName(menuNames);
            System.out.println("Module Id:::"+formData.getModuleId());
            authenticateUser = (List<LoginDTO>) getSqlMapClientTemplate().queryForList("LoginDetails.getAuthenticateUser",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticateUser;
    }

    public List<LoginDTO> authenticateGroup(String groupId, List menuNames) {
        List<LoginDTO> authenticateGroup = null;
        try {
            LoginDTO formData = new LoginDTO();
            formData.setGroupId(groupId);
            formData.setModuleName(menuNames);
            System.out.println("Module Id:::"+formData.getModuleId());
            authenticateGroup = (List<LoginDTO>) getSqlMapClientTemplate().queryForList("LoginDetails.getAuthenticateGroup",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticateGroup;
    }


}
