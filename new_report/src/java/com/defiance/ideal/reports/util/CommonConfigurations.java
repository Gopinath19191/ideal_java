/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.util;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class CommonConfigurations {

   // public static HashMap customerCategory = Config.getChildElements(Config.getParentId("customer_category"));
    
    public static final String CUSTOMER_MODULE_CODE = "CUSTOMER";

    public static final int CUSTOMER_LIST_MODULE_ID = 494;
    public static final int CUSTOMER_ADD_MODULE_ID = 496;
    public static final int CUSTOMER_EDIT_MODULE_ID = 495;
    public static final int CUSTOMER_APPROVE_MODULE_ID = 497;
    public static final int CUSTOMER_APPROVAL_LIST_MODULE_ID = 498;
    public static final String REPORTNAME_CONFIG_VALUE = "490";
    
    public static final String MYSQL_DATE_INSERT = "yyyy-MM-dd";
    public static final String MYSQL_DATE_SELECT = "dd-MMM-yyyy";
    public static final int EXIT_MODULES_NUMBER = 6;

    public static final String ExternalConfigFile = "D:\\propertyfiles\\reportsconfiguration.properties";

    public static final String CUSTOMER_SAVE_STATUS = "s";
    public static final String CUSTOMER_SUBMIT_STATUS = "m";
    public static final String CUSTOMER_AMENDED_STATUS = "d";
    public static final String CUSTOMER_APPROVED_STATUS = "a";
    public static final String CUSTOMER_REJECTED_STATUS = "r";
        
    public static final int RESIGN_UNDELETE = 0;
    public static final int RESIGN_DELETE = 1;

    public static final String[] statusList = {"0","Resignation Form Saved","Resignation Form Submitted","RM Approved","RM Rejected","Finance Approved","Finance Rejected","Admin Approved","Admin Rejected","Network Approved","Network Rejected","HR Approved","HR Rejected"};
    public static final String[] noticeWaiverValueList = {"Fully waived","Partially waived","No waiver"};
    public static final String[] surrenderValueList = {"Yes","No","NA"};
    public static final String[] approvalMailList = {"Mohamed.Musthaba@defiance-tech.com", "Adaikkalam.p@defiance-tech.com"};
    
    public static final int SLIDER_MAX_VALUE=5;
    public static final int SLIDER_MIN_VALUE=1;
    
    public static final String SURVEY_ANSWER_RADIO="R";
    public static final String SURVEY_ANSWER_SLIDER="S";
    public static final String SURVEY_ANSWER_FREE_TEXT="F";
    public static final String SURVEY_ANSWER_MULTIPLE="M";
    
    public static final int SURVEY_SUBMIT_STATUS=1;
    
//    public static final String[] ADMIN_NOT_NULL_FIELDS={"idCard","drawerKey","dataCard","mobilePhone","simCard","laptop"};
    public static final String[] RMCLR_NOT_NULL_FIELDS={"CompDoc","ProjDoc","CustApproval","EmpQpd","RmOthers"};
//    public static final String[] ADMIN_NOT_NULL_FIELDS={"IdCard","DrawerKey","DataCard","MobilePhone","SimCard","Laptop"};
    public static final String[] ADMIN_NOT_NULL_FIELDS={"IdCard","DataCard","BusinessCard","CmpResPhone","MobilePhone","SimCard","OutBillClr","Laptop","CdPenDrive","CabinKey","DrawerKey","AdOther"};
    public static final String[] FINANCE_NOT_NULL_FIELDS={"TravelAdvance","SalaryAdvance","Relocation","JoiningBonus",
                                                            "NoticeReimburse","ProfessionalFee","Loans","OfficialExpenses","Other"};
     public static final String[] NS_NOT_NULL_FIELDS={"DesktopPwd","EmailDeactivation","NsOthers"};
     public static final String[] HR_NOT_NULL_FIELDS={"ExitType","IdealToolDeactivated","LeaveBalance","OverseasBond","ExitInterview","HrInsurance","HrOthers"};
     
//    public static final String[] ADMIN_DISPLAY_FIELDS={"ID Card/Access Card","Drawer Key","Data Card","Mobile Phone handset","Mobile Phone SIM Card","Laptop"};
    public static final String[] ADMIN_DISPLAY_FIELDS={"ID Card/Access Card","Internet Data Card","Business Cards","Company Provided Residence Phone","Mobile Phone handset","Mobile Phone SIM Card","Outstanding Mobile Bills Cleared","Laptop","All external mass storage devices (CD/pen drives)","Cabin Keys","Drawer/Storage Keys","Others"};
    public static final String[] FINANCE_DISPLAY_FIELDS={"Travel Advance","Salary Advance","Relocation","Joining Bonus",
                                                            "Notice Reimbursement","Professional Fee","Loans and Advances","Outstanding Loans and official expenses cleared","Others"};
     public static final String[] NS_DISPLAY_FIELDS={"Desktop Password Disabled","Email ID Deactivation","Others"};
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
    public static final int[] CUSTOMER_MENU_ID ={73, 75, 76, 77, 79};
    public static final int[] BUSINESS_LEADER_BAND_IDS ={15,35,36,2,3};

    
    public static final String TrainingPhaseValue = "6";
    public static final String ProjectManagementPhaseValue = "1";
    public static final String PreSalesPhaseValue = "5";
    
    public static final String PES = "2";
      public static final String delivery = "1";
      public static final String enabling = "12";
        public static final String sales = "52";
        public static final String total=delivery+','+enabling+','+sales ;
    public static  final String TS = "5";
    public static  final String Operations = "15";
    public static  final String Finance = "19";
    public static  final String Corporate = "21";
    public static  final String Sales = "23";
    public static final String SBU = "1";
    public static final String HR = "13";
    public static final String SSU= "12";
    public static final String structure = SBU + ',' +SSU;    
    public static int pageCount = 15;
    public static final int PMO_FOR_TS = 2360;
    public static final int[] PMO_FOR_PES = {67,1558};
    public static final String BUH_FOR_TS = "2605";
    public static final String BUH_FOR_PES = "33";
    //public static final int[] PMO_FOR_ERP = {2649,1692};
    public static final String CRMG = "29";

}
