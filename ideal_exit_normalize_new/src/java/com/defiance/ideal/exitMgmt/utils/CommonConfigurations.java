/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.utils;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class CommonConfigurations {

    public static final String EXIT_MODULE_CODE = "EXIT_MGMT";

//    public static final String EXIT_SURVEY_FILE = "D:\\propertyfiles\\exitMgmt\\exitSurvey.properties";
    
    public static final int EXIT_EMPLOYEE_MODULE_ID = 494;
    public static final int EXIT_RM_APPROVAL_MODULE_ID = 496;
    public static final int EXIT_RM_CLERANCE_MODULE_ID = 495;
    public static final int EXIT_FINANCE_APPROVAL_MODULE_ID = 497;
    public static final int EXIT_NETWORK_APPROVAL_MODULE_ID = 498;
    public static final int EXIT_ADMIN_APPROVAL_MODULE_ID = 499;
    public static final int EXIT_HR_APPROVAL_MODULE_ID = 500;
    
    public static final int EXIT_RM_APPROVAL_VIEW_MODULE_ID = 501;
    public static final int EXIT_RM_CLERANCE_VIEW_MODULE_ID = 502;
    public static final int EXIT_FINANCE_APPROVAL_VIEW_MODULE_ID = 503;
    public static final int EXIT_ADMIN_APPROVAL_VIEW_MODULE_ID = 504;
    public static final int EXIT_NETWORK_APPROVAL_VIEW_MODULE_ID = 505;
    public static final int EXIT_HR_APPROVAL_VIEW_MODULE_ID = 506;
    public static final int EXIT_SURVEY_MODULE_ID = 507;
    public static final int EXIT_TRIGGER_MODULE_ID = 555;
//    public static final int EXIT_EMPLOYEE_MODULE_ID = 477;
//    public static final int EXIT_RM_MODULE_ID = 478;
//    public static final int EXIT_FINANCE_MODULE_ID = 479;
//    public static final int EXIT_NETWORK_MODULE_ID = 480;
//    public static final int EXIT_ADMIN_MODULE_ID = 481;
//    public static final int EXIT_HR_MODULE_ID = 482;
//    public static final int EXIT_RM_CLERANCE_MODULE_ID = 483;
//    public static final int EXIT_FINANCE_CLERANCE_MODULE_ID = 484;
//    public static final int EXIT_ADMIN_CLERANCE_MODULE_ID = 485;
//    public static final int EXIT_NETWORK_CLERANCE_MODULE_ID = 486;
//    public static final int EXIT_HR_CLERANCE_MODULE_ID = 487;
//    public static final int EXIT_SURVEY_MODULE_ID = 488;

    public static final int[] EXIT_MENU_ID ={494,495,496,497,498,499,500,501,502,503,504,505,506,507};
    public static final int[] EXIT_VIEW_STATUS_ID ={501,502,503,504,505,506,507};
//    public static final int[] EXIT_MENU_ID ={477,478,479,480,481,482,483,484,485,486,487,488};
//    public static final int[] EXIT_VIEW_STATUS_ID ={483,484,485,486,487,488};

//    public static final int[] EXIT_APPROVALS_ID ={479,480,481,482};
    public static final int[] EXIT_APPROVALS_ID ={497,498,499,500};
    
    public static final String MYSQL_DATE_INSERT = "yyyy-MM-dd";
    public static final String MYSQL_DATE_SELECT = "dd-MMM-yyyy";
    public static final String PDF_DATE_SELECT = "dd-MM-yyyy";
    public static final int EXIT_MODULES_NUMBER = 6;

    public static final String ExternalConfigFile = "D:\\propertyfiles\\exitconfiguration.properties";
    public static final String ExternalExitFile = "D:\\propertyfiles\\exitMgmt\\exitSurvey.properties";
    public static final String ExternalConfigMailFile = "D:\\propertyfiles\\exitMgmt\\exitMailMessages.properties";
//    public static final String ExternalConfigFile = "E:\\propertyfiles\\exitconfiguration.properties";
//    public static final String ExternalExitFile = "E:\\propertyfiles\\exitMgmt\\exitSurvey.properties";
//    public static final String ExternalConfigMailFile = "E:\\propertyfiles\\exitMgmt\\exitMailMessages.properties";
    
    public static final String SAVE_STATUS = "n";
    public static final String SUBMIT_STATUS = "y";

    public static final int EMP_SAVE_STATUS = 1;
    public static final int EMP_SUBMIT_STATUS = 2;
    public static final int RM_APPROVE_STATUS = 3;
    public static final int RM_REJECT_STATUS = 4;
    public static final int FIN_APPROVE_STATUS = 5;
    public static final int FIN_REJECT_STATUS = 6;
    public static final int ADMIN_APPROVE_STATUS = 7;
    public static final int ADMIN_REJECT_STATUS = 8;
    public static final int NS_APPROVE_STATUS = 9;
    public static final int NS_REJECT_STATUS = 10;
    public static final int HR_APPROVE_STATUS = 11;
    public static final int HR_REJECT_STATUS = 12;
    public static final int EXIT_SURVEY_SAVE_STATUS = 13;
    public static final int EXIT_SURVEY_SUBMIT_STATUS = 14;
    public static final int RMCLR_APPROVE_STATUS = 15;
    
    public static final String RESIGN_UNDELETE = "N";
    public static final String RESIGN_DELETE = "Y";

    public static final String[] statusList = {"0","Resignation Form Saved","Resignation Form Submitted","RM Approved","RM Rejected","Finance Approved","Finance Rejected","Admin Approved","Admin Rejected","Network Approved","Network Rejected","HR Approved","HR Rejected"};
    public static final String[] noticeWaiverValueList = {"Fully waived","Partially waived","No waiver","Not Applicable"};  
    public static final String[] surrenderValueList = {"Yes","No","NA"};
    
    public static final int SLIDER_MAX_VALUE=5;
    public static final int SLIDER_MIN_VALUE=1;
    public static final int CMP_NOTICE_PERIOD_IN_DAYS=90;
    
    public static final String SURVEY_ANSWER_RADIO="R";
    public static final String SURVEY_ANSWER_SLIDER="S";
    public static final String SURVEY_ANSWER_FREE_TEXT="F";
    public static final String SURVEY_ANSWER_MULTIPLE="M";
    
    public static final String SURVEY_SUBMIT_STATUS="Y";
    
//    public static final String[] ADMIN_NOT_NULL_FIELDS={"idCard","drawerKey","dataCard","mobilePhone","simCard","laptop"};
    public static final String[] RMCLR_NOT_NULL_FIELDS={"CompDoc","ProjDoc","CustApproval","EmpQpd","RmOthers"};
//    public static final String[] ADMIN_NOT_NULL_FIELDS={"IdCard","DrawerKey","DataCard","MobilePhone","SimCard","Laptop"};
    public static final String[] ADMIN_NOT_NULL_FIELDS={"IdCard","DataCard","BusinessCard","CmpResPhone","MobilePhone","SimCard","OutBillClr","CdPenDrive","CabinKey","DrawerKey","AdOther"};
    public static final String[] FINANCE_NOT_NULL_FIELDS={"TravelAdvance","SalaryAdvance","Relocation","JoiningBonus",
                                                            "NoticeReimburse","ProfessionalFee","Loans","OfficialExpenses","Other"};
     public static final String[] NS_NOT_NULL_FIELDS={"DesktopPwd","EmailDeactivation","SalesCrm","FinDax","Laptop","NsOthers"};
     public static final String[] HR_NOT_NULL_FIELDS={"ExitType","IdealToolDeactivated","LeaveBalance","OverseasBond","ExitInterview","HrInsurance","HrOthers"};
     
//    public static final String[] ADMIN_DISPLAY_FIELDS={"ID Card/Access Card","Drawer Key","Data Card","Mobile Phone handset","Mobile Phone SIM Card","Laptop"};
    public static final String[] ADMIN_DISPLAY_FIELDS={"ID Card/Access Card","Internet Data Card","Business Cards","Company Provided Residence Phone","Mobile Phone handset","Mobile Phone SIM Card","Outstanding Mobile Bills Cleared","All external mass storage devices (CD/pen drives)","Cabin Keys","Drawer/Storage Keys","Others"};
    public static final String[] FINANCE_DISPLAY_FIELDS={"Travel Advance settlements","Salary Advance settlements","Relocation","Joining Bonus",
                                                            "Notice Reimbursement","Professional Fee","Loans and Advances","Outstanding Loans and official expenses cleared","Others"};
     public static final String[] NS_DISPLAY_FIELDS={"Desktop Password Disabled","Email ID Deactivation","Sales CRM tool deactivated","Finance DaX tool deactivated","Laptop","Others"};
     public static final String[] HR_DISPLAY_FIELDS={"Exit Type","iDeal Tool Deactivated","Leave Balance","Overseas Bond/Training Bond","Exit Interview","Insurance","Others"};
     public static final String[] RMCLR_DISPLAY_FIELDS={"Company Manuals / Documents / Training Material","Project / function related transition documents","Customer informed and approval obtained","QPD completed","Others"};
    
    public static final int NULL_REPLACEMENT=0;
    public static final String STR_NULL_REPLACEMENT="0";

    public static final String adminModuleName = "Admin";
    public static final String financeModuleName = "Finance";
    public static final String nsModuleName = "Network & Support";
    public static final String rmModuleName = "Reporting Manager";
    public static final String hrModuleName = "Human Resource";
    public static final String exitSurveyModuleName = "Exit Survey";

    public static final String surrenderYesValue = "1";
    public static final String surrenderNoValue = "2";
    public static final String surrenderNAValue = "3";

    public static final String[] exitType = {"Resignation","Termination","Absconding"};
    public static final String[] monthList = {"January","February","March","April","May","June"};
    public static final String fileUploadPath="D:\\fileUploads\\";
    
    public static final String PES_COMPANY_STRUCTURE = "2";
    public static final String TS_COMPANY_STRUCTURE = "5";
    public static final String PF_Guideliness="D:/propertyfiles/exitMgmt/PF Transfer - ImportantGuidelinesformember.pdf";
    public static final String PF_ProcessFlow="D:/propertyfiles/exitMgmt/PF Transfer ProcessFlowforMembers.pdf";
}
