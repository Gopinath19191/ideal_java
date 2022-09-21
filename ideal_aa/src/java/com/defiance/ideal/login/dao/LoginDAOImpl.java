/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.login.dao;

import com.defiance.ideal.login.dto.LoginFormDTO;
import com.defiance.ideal.shared.CommonConfigurations;
import com.defiance.ideal.shared.CommonFunctions;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import javax.servlet.http.HttpServletRequest;
import org.apache.beehive.controls.api.bean.Control;
import com.defiance.ideal.shared.MysqlDatabase;
import java.math.*;
import java.security.*;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@ControlImplementation(isTransient = true)
public class LoginDAOImpl implements LoginDAO {

    @Control
    private MysqlDatabase dbCTRL;
    private transient final Logger logger = Logger.getLogger(this.getClass().getName());

    public LoginFormDTO authenticateLoginDetails(HttpServletRequest request) throws Exception {
        LoginFormDTO Test = null;
        try {
            String userName = request.getParameter("userName");
            String toEnc = request.getParameter("userPassword");
            MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm

            mdEnc.reset();
            mdEnc.update(toEnc.getBytes(), 0, toEnc.length());

            byte messageDigest[] = mdEnc.digest();

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xff & messageDigest[i]);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }

//            String userPassword = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
            String userPassword = sb.toString(); // Encrypted string

            logger.info("Encrypted user Password -" + userPassword + "--User name--" + userName);

            Test = dbCTRL.AuthenticateUser(userName, userPassword);
        } catch (Exception e) {
            logger.error("error @ authenticateLoginDetails in LoginDAOImpl - " + e.getMessage());
        }
        return Test;
    }

    public LoginFormDTO authenticateUser(String empId, String moduleId) throws NullPointerException {
        LoginFormDTO userAuthentication = null;

        userAuthentication = dbCTRL.authenticateUser(empId, moduleId);
        return userAuthentication;
    }

    public LoginFormDTO authenticateGroup(String groupId, String moduleId) throws NullPointerException {
        LoginFormDTO groupAuthentication = null;
        groupAuthentication = dbCTRL.authenticateGroup(groupId, moduleId);
        return groupAuthentication;
    }

    public LoginFormDTO menuCheck(String referenceName, int currentYear, int employeeId) throws NullPointerException {
        LoginFormDTO groupAuthentication = null;
//       groupAuthentication = dbCTRL.menuAuthentication(referenceName,currentQuarter,currentYear,employeeId,CommonConfigurations.TRIGGER_STATUS);
        groupAuthentication = dbCTRL.menuAuthentication(referenceName, currentYear, employeeId, CommonConfigurations.TRIGGER_STATUS);
        return groupAuthentication;
    }

    public void updateManagerSurvey(int currentYear, int currentQuarter, int employeeId, int surveyStatus) {
        try {
//            dbCTRL.updateManagerSurvey(currentYear,currentQuarter,employeeId,surveyStatus);
            dbCTRL.updateManagerSurvey(currentYear, employeeId, surveyStatus);
        } catch (Exception e) {
            logger.info("Exception @ LoginDAOImpl - updateManagerSurvey " + e.getLocalizedMessage());
        }

    }

    public LoginFormDTO managerSurvey(int currentYear, int employeeId) {
        LoginFormDTO managerSurveyStatus = null;
//        managerSurveyStatus = dbCTRL.managerSurvey(currentYear,currentQuarter,employeeId);
        managerSurveyStatus = dbCTRL.managerSurvey(currentYear, employeeId);
        return managerSurveyStatus;
    }

    public String userNameString(String userToken, String empId) {
        String userNameString = null;
        userNameString = dbCTRL.userNameString(userToken, empId);
        return userNameString;
    }
}
