/*
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

$Header:$
 */

import com.defiance.ideal.login.bo.LoginBO;
import com.defiance.ideal.login.dto.LoginFormDTO;
import com.defiance.ideal.shared.CommonConfigurations;
import com.defiance.ideal.shared.CommonFunctions;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.log4j.Logger;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

/**
 * <p>
 * A controller class that contains logic, exception handlers, and state for the current
 * web directory path. When a request is received for the page flow (/Controller.jpf), an
 * action (/begin.do), or a page (/index.jsp), an instance of this class becomes the
 * <em>current page flow</em>. By default, it is stored in the session while its actions
 * and pages are being accessed, and is removed when another page flow is requested.
 * </p>
 * <p>
 * Properties in the current page flow may be accessed through the <code>pageFlow</code>
 * databinding context in pages and in expression-aware annotations. For example, if this
 * class contains a <code>getSomeProperty</code> method, it can be accessed through the
 * expression <code>${pageFlow.someProperty}</code>.
 * </p>
 * <p>
 * There may be only one page flow in any package.
 * </p>
 */
@Jpf.Controller(simpleActions = {
    @Jpf.SimpleAction(name = "begin", path = "index.jsp")
})
public class Controller extends PageFlowController {

    private transient final Logger logger = Logger.getLogger(this.getClass().getName());
    @Control
    private LoginBO controlObj;

    /**
     * Method that is invoked when this controller instance is created.
     */
    @Override
    protected void onCreate() {
    }

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "success", path = "index.jsp"),
        @Jpf.Forward(name = "unauthorisedAccess", path = "unauthorisedaccess.jsp"),
        @Jpf.Forward(name = "redirectHome", path = "http://ideal.defiance-tech.com/")
    })
    public Forward validateUserName() throws Exception {
        Forward fwd = null;
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        try {
            String userToken = request.getParameter("userToken");
            String empId = request.getParameter("empId");
            if ((empId != null && userToken != null) && (!empId.equals("") && !userToken.equals(""))) {
                String userName = controlObj.userNameString(userToken, empId);
                if (userName != null) {
                    session.setAttribute("userEmpCode", userName);
                    fwd = new Forward("success");
                } else {
                    fwd = new Forward("redirectHome");
                }
            } else {
                fwd = new Forward("redirectHome");
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fwd;
    }

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "success", path = "processDuration.do"),
        @Jpf.Forward(name = "unauthorisedAccess", path = "unauthorisedaccess.jsp"),
        @Jpf.Forward(name = "failure", path = "index.jsp")
    })
    public Forward validate() throws Exception {
        LoginFormDTO authentication = null;
        LoginFormDTO menuCheck = null;
        HttpServletRequest request = getRequest();
        HttpSession session = getSession();
        Forward fwd = null;
        String authenticatedFlag = null;
        request.setAttribute("userEmpCode", request.getParameter("userName"));
        try {
            CommonFunctions.printRequest(request);
            authentication = controlObj.authenticateLoginDetails(request);
            if (authentication == null) {
                logger.info("Authentication Failure");
                String errorMessage = "Invalid Login Details";
                request.setAttribute("invalid_login", errorMessage);
                fwd = new Forward("failure");
            } else {
                logger.info("Authentication Success");
                LoginFormDTO userAuthentication = controlObj.authenticateUser(authentication.getUserAccountId(), CommonConfigurations.AA_MODULE_ID);
                /*
                System.out.println("userAuthentication = " + userAuthentication.getuCreate());
                System.out.println("userAuthentication = " + userAuthentication.getuRead());
                System.out.println("userAuthentication = " + userAuthentication.getuUpdate());
                System.out.println("userAuthentication = " + userAuthentication.getuDelete());
                 */
                if (userAuthentication == null) {
                    LoginFormDTO groupAuthentication = controlObj.authenticateGroup(authentication.getGroupId(), CommonConfigurations.AA_MODULE_ID);
                    /*
                    System.out.println("groupAuthentication = " + groupAuthentication.getgCreate());
                    System.out.println("groupAuthentication = " + groupAuthentication.getgRead());
                    System.out.println("groupAuthentication = " + groupAuthentication.getgUpdate());
                    System.out.println("groupAuthentication = " + groupAuthentication.getgDelete());
                     */
                    if (groupAuthentication == null) {
                        authenticatedFlag = "0";
                    } else if ((groupAuthentication.getgCreate().trim().equals("1")) && (groupAuthentication.getgRead().trim().equals("1")) && (groupAuthentication.getgUpdate().trim().equals("1")) && (groupAuthentication.getgDelete().trim().equals("1"))) {
                        authenticatedFlag = "1";
                    } else {
                        authenticatedFlag = "0";
                    }
                } else if ((userAuthentication.getuCreate().trim().equals("1")) && (userAuthentication.getuRead().trim().equals("1")) && (userAuthentication.getuUpdate().trim().equals("1")) && (userAuthentication.getuDelete().trim().equals("1"))) {
                    authenticatedFlag = "1";
                } else {
                    authenticatedFlag = "0";
                }
                logger.info("Permission Granted Allowing User - Authenticated Flag = " + authenticatedFlag);
                if (authenticatedFlag.equals("1")) {
                    session.setAttribute("loginId", authentication.getUsername());
                    session.setAttribute("groupId", authentication.getGroupId());
                    session.setAttribute("employeeId", authentication.getEmpId());

                    int previousYear = (CommonFunctions.getAppraisalYear() - 1);
                    ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                    myArrrayList.add(CommonFunctions.getAppraisalYear());
                    myArrrayList.add(previousYear);
                    request.setAttribute("yearData", myArrrayList);
                    fwd = new Forward("success");
                } else {
                    logger.info("Permission Denied Declined User - " + authentication.getUserAccountId());
                    fwd = new Forward("unauthorisedAccess");
                }
            }
        } catch (Exception e) {
            logger.error("Error @ validate in Controller.java" + e.getMessage());
        }
        return fwd;
    }

    /**
     * Method that is invoked when this controller instance is destroyed.
     */
    @Override
    protected void onDestroy(HttpSession session) {
    }

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "success", path = "index.jsp")
    })
    public Forward logout() throws Exception {
        HttpSession session = this.getRequest().getSession();
        HttpServletRequest requestObj = this.getRequest();
        requestObj.setAttribute("empid", session.getAttribute("loginId"));
        requestObj.setAttribute("userEmpCode", session.getAttribute("loginId"));
        session.invalidate();
        Forward fwd = new Forward("success");
        return fwd;
    }

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "success", path = "index.jsp")
    })
    public Forward login() throws Exception {
        Forward fwd = new Forward("success");
        return fwd;
    }

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "processDuration", path = "home.jsp"),
        @Jpf.Forward(name = "qpdForm", path = "/com/defiance/ideal/qpd/appraisee/begin.do")
    })
    public Forward processDuration() throws Exception {


        HttpServletRequest requestObj = this.getRequest();
        HttpSession sessionObj = this.getRequest().getSession();
        LoginFormDTO appraiseeCheck = null;
        LoginFormDTO appraiserCheck = null;
        LoginFormDTO reviewerCheck = null;
        LoginFormDTO normalizerCheck = null;
        LoginFormDTO buhCheck = null;
        LoginFormDTO financeCheck = null;
        LoginFormDTO surveyCheck = null;
        int currentQuarter;
        int currentYear;
        Properties configFile = new Properties();
        configFile.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));

        Forward fwd = null;
        if (requestObj.getParameter("qpage") != null) {
            fwd = new Forward("qpdForm");
        } else {
            fwd = new Forward("processDuration");
        }

        if (requestObj.getParameter("appraisalQuarter") == null) {

//            CommonConfigurations quarter =  new CommonConfigurations();
//            currentQuarter =  quarter.QPD_QUARTER;

            currentQuarter = Integer.parseInt(configFile.getProperty("QPD_QUARTER"));
        } else {
            currentQuarter = Integer.parseInt(requestObj.getParameter("appraisalQuarter").toString());
        }

        if (requestObj.getParameter("appraisalYear") == null) {

//            CommonConfigurations year =  new CommonConfigurations();
//            currentYear =  year.QPD_YEAR;

            currentYear = Integer.parseInt(configFile.getProperty("QPD_YEAR"));
        } else {
            currentYear = Integer.parseInt(requestObj.getParameter("appraisalYear").toString());
        }


        int previousYear = (CommonFunctions.getAppraisalYear() - 1);
        ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
        myArrrayList.add(CommonFunctions.getAppraisalYear());
        myArrrayList.add(previousYear);
        requestObj.setAttribute("yearData", myArrrayList);

        int employeeId = Integer.parseInt(sessionObj.getAttribute("employeeId").toString());

        logger.info("Manager Dialog  " + requestObj.getParameter("managerDialog"));

        if (requestObj.getParameter("managerDialog") != null && !("").equals(requestObj.getParameter("managerDialog").toString())) {
            controlObj.updateManagerSurvey(currentYear, currentQuarter, employeeId, Integer.parseInt(requestObj.getParameter("managerDialog").toString()));
        }


//         appraiseeCheck = controlObj.menuCheck("appraiseeId",currentYear,currentQuarter,employeeId);
        appraiseeCheck = controlObj.menuCheck("appraiseeId", currentYear, employeeId);
//         appraiserCheck = controlObj.menuCheck("appraiserId",currentYear,currentQuarter,employeeId);
        appraiserCheck = controlObj.menuCheck("appraiserId", currentYear, employeeId);
//         reviewerCheck = controlObj.menuCheck("reviewerId",currentYear,currentQuarter,employeeId);
        reviewerCheck = controlObj.menuCheck("reviewerId", currentYear, employeeId);
//         normalizerCheck = controlObj.menuCheck("normalizerId",currentYear,currentQuarter,employeeId);
        normalizerCheck = controlObj.menuCheck("normalizerId", currentYear, employeeId);
        buhCheck = controlObj.menuCheck("buhId", currentYear, employeeId);
//         financeCheck = controlObj.menuCheck("financeid",currentYear,currentQuarter,employeeId);
        financeCheck = controlObj.menuCheck("financeid", currentYear, employeeId);

//         surveyCheck = controlObj.managerSurvey(currentYear,currentQuarter,employeeId);
        surveyCheck = controlObj.managerSurvey(currentYear, employeeId);

        requestObj.setAttribute("appraisalQuarter", currentQuarter);
        requestObj.setAttribute("appraisalYear", currentYear);

        if (surveyCheck == null) {
        } else {
            requestObj.setAttribute("surveyCheck", surveyCheck.getManagerSurvey());
        }



        sessionObj.setAttribute("appraisalQuarterSession", currentQuarter);
        sessionObj.setAttribute("appraisalYearSession", currentYear);

        System.out.println("appraiseeCheck.getUserCount()::::" + appraiseeCheck.getUserCount());
        System.out.println("reviewerCheck.getUserCount()::::" + reviewerCheck.getUserCount());

        if (appraiseeCheck.getUserCount() > 0) {
            sessionObj.setAttribute("isAppraisee", 1);
        } else {
            sessionObj.setAttribute("isAppraisee", 0);
        }
        if (appraiserCheck.getUserCount() > 0) {
            sessionObj.setAttribute("isAppraiser", 1);
        } else {
            sessionObj.setAttribute("isAppraiser", 0);
        }
        if (reviewerCheck.getUserCount() > 0) {
            sessionObj.setAttribute("isReviewer", 1);
        } else {
            sessionObj.setAttribute("isReviewer", 0);
        }
        if (normalizerCheck.getUserCount() > 0) {
            sessionObj.setAttribute("isNormaliser", 1);
        } else {
            sessionObj.setAttribute("isNormaliser", 0);
        }
        if (buhCheck.getUserCount() > 0) {
            sessionObj.setAttribute("isBuh", 1);
        } else {
            sessionObj.setAttribute("isBuh", 0);
        }
        if (financeCheck.getUserCount() > 0) {
            sessionObj.setAttribute("isFinance", 1);
        } else {
            sessionObj.setAttribute("isFinance", 0);
        }
        if (employeeId == CommonConfigurations.HR_FOR_APPRAISAL) {
            sessionObj.setAttribute("isHr", 1);
        } else {
            sessionObj.setAttribute("isHr", 0);
        }

//        fwd.setRedirect(true);
        return fwd;
    }
}
