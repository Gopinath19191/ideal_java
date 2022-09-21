/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.login.bo;

/**
 *
 * @author HARIHARAN.C
 */
import com.defiance.ideal.login.dto.LoginFormDTO;
import javax.servlet.http.HttpServletRequest;
import org.apache.beehive.controls.api.bean.ControlInterface;

@ControlInterface
public interface LoginBO {

    public LoginFormDTO authenticateLoginDetails(HttpServletRequest request);

    public LoginFormDTO authenticateUser(String userAccountId, String MODULE_ID);

    public LoginFormDTO authenticateGroup(String groupId, String MODULE_ID);

//    public LoginFormDTO menuCheck(String referenceName, int currentYear, int currentQuarter,int employeeId);
    public LoginFormDTO menuCheck(String referenceName, int currentYear, int employeeId);

//    public LoginFormDTO managerSurvey(int currentYear,int currentQuarter,int employeeId);
    public LoginFormDTO managerSurvey(int currentYear, int employeeId);

    public void updateManagerSurvey(int currentYear, int currentQuarter, int employeeId, int surveyStatus);

    public String userNameString(String userToken, String empId);

    

}
