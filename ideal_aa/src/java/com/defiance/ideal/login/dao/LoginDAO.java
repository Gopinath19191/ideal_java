/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.login.dao;
import com.defiance.ideal.login.dto.LoginFormDTO;
import javax.servlet.http.HttpServletRequest;
import org.apache.beehive.controls.api.bean.ControlInterface;

/**
 *
 * @author Admin
 */
@ControlInterface
public interface LoginDAO {

    public LoginFormDTO authenticateLoginDetails(HttpServletRequest request) throws Exception;
    public LoginFormDTO authenticateUser(String empId, String MODULE_ID);
    public LoginFormDTO authenticateGroup(String groupId, String MODULE_ID);
    public LoginFormDTO menuCheck(String referenceName,int currentYear,int employeeId);
    public void  updateManagerSurvey(int currentYear, int currentQuarter, int employeeId, int surveyStatus);
    public LoginFormDTO managerSurvey(int currentYear, int employeeId);
    public String userNameString(String userToken, String empId);
}