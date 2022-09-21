package com.ideal.admin.ticket.dao;

import com.ideal.admin.ticket.dto.LoginDTO;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public class AuthenticatorDaoImpl extends SqlMapClientDaoSupport {
final static Logger logger = Logger.getLogger(AuthenticatorDaoImpl.class);
    public boolean authenticate(LoginDTO authenParams) {
        List<LoginDTO> template = null;
        try {
            LoginDTO userAuthentication = null;
            LoginDTO groupAuthentication = null;
            LoginDTO loginAuthentication = null;
            loginAuthentication = (LoginDTO) getSqlMapClientTemplate().queryForObject("AuthenticationMap.loginCheck", authenParams);
            if (loginAuthentication == null) {
                return false;
            } else {
                userAuthentication = (LoginDTO) getSqlMapClientTemplate().queryForObject("AuthenticationMap.authenticateUser", authenParams);
                if (userAuthentication == null || userAuthentication.getuCreate() == null || userAuthentication.getuCreate().equals("-1")) {
                    groupAuthentication = (LoginDTO) getSqlMapClientTemplate().queryForObject("AuthenticationMap.authenticateGroup", authenParams);
                    if (groupAuthentication == null || groupAuthentication.getgCreate() == null || groupAuthentication.getgCreate().equals("-1")) {
                        return false;
                    } else {
                        return true;
                    }

                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public LoginDTO getUserDetails(String empId) {
        logger.info(" authent daoempId "+empId);
        return (LoginDTO) getSqlMapClientTemplate().queryForObject("AuthenticationMap.getUserDetails", empId);
    }
}
