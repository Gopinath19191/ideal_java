package com.defiance.ideal.login.bo;

import com.defiance.ideal.login.dao.LoginDAO;
import com.defiance.ideal.login.dto.LoginFormDTO;
import javax.servlet.http.HttpServletRequest;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.log4j.Logger;

@ControlImplementation(isTransient = true)
public class LoginBOImpl implements LoginBO {

    @Control
    private LoginDAO controlObj;
    private transient final Logger logger = Logger.getLogger(this.getClass().getName());

    public LoginFormDTO authenticateLoginDetails(HttpServletRequest request) {
        LoginFormDTO dataObj = null;
        try {
            dataObj = controlObj.authenticateLoginDetails(request);
        } catch (Exception ex) {
            logger.error("Exception @ authenticateLoginDetails in LoginBOImpl" + ex.getMessage());
        }
        return dataObj;
    }

    public LoginFormDTO authenticateUser(String userAccountId, String MODULE_ID) {
        LoginFormDTO dataObj = null;
        try {
            dataObj = controlObj.authenticateUser(userAccountId, MODULE_ID);
        } catch (Exception ex) {
            logger.error("Exception @ authenticateUser in LoginBOImpl" + ex.getMessage());
        }
        return dataObj;
    }

    public LoginFormDTO authenticateGroup(String groupId, String MODULE_ID) {
        LoginFormDTO dataObj = null;
        try {
            dataObj = controlObj.authenticateGroup(groupId, MODULE_ID);
        } catch (Exception ex) {
            logger.error("Exception @ authenticateGroup in LoginBOImpl" + ex.getMessage());
        }
        return dataObj;
    }

    public LoginFormDTO menuCheck(String referenceName, int currentYear, int employeeId) {
        LoginFormDTO dataObj = null;
        try {
            dataObj = controlObj.menuCheck(referenceName, currentYear, employeeId);
        } catch (Exception ex) {
            logger.error("Exception @ menuCheck in LoginBOImpl" + ex.getMessage());
        }
        return dataObj;
    }

    public void updateManagerSurvey(int currentYear, int currentQuarter, int employeeId, int surveyStatus) {

        try {
            controlObj.updateManagerSurvey(currentYear, currentQuarter, employeeId, surveyStatus);
        } catch (Exception ex) {
            logger.error("Exception @ updateManagerSurvey in LoginBOImpl" + ex.getMessage());
        }

    }

    public LoginFormDTO managerSurvey(int currentYear, int employeeId) {

        LoginFormDTO dataObj = null;

        try {
            dataObj = controlObj.managerSurvey(currentYear, employeeId);
        } catch (Exception ex) {
            logger.error("Exception @ managerSurvey in LoginBOImpl" + ex.getMessage());
        }
        return dataObj;
    }

    public String userNameString(String userToken, String empId) {
        String userNameString = null;
        try {
            userNameString = controlObj.userNameString(userToken, empId);
        } catch (Exception e) {
            logger.error("Exception @ userNameString in LoginBOImpl" + e.getMessage());
        }
        return userNameString;
    }
}
