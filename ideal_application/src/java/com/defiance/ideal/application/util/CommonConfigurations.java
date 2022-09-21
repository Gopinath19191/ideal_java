/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.util;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class CommonConfigurations {

    //public static HashMap customerCategory = Config.getChildElements(Config.getParentId("customer_category"));
    
    public static final String customerCategory = "208";
    public static final String attachmentType ="209";
    public static final String MAIL_DETAILS_PARENTID = "10";
    public static HashMap rating_values = Config.getChildElements(Config.getParentId("rating_values"));//385
    public static final String CUSTOMER_MODULE_CODE = "CUSTOMER";
    public static final String customerCode = "100001";
    
//    public static final String URL_TO_REDIRECT_FOR_OJF = "http://10.18.1.54/ideal2/";
    public static final String URL_TO_REDIRECT_FOR_OJF = "http://ideal.hindujatech.com/";
    //public static final String URL_TO_REDIRECT_FOR_OJF = "http://ideal.defiance-tech.com/"; // For Sever


   // public static final String[] approvalMailList = {"Satish.Ganta@hindujatech.com", "Karthik.Ramachandran@hindujatech.com", "Krishnan.Vaithiswaran@hindujatech.com"};
    //public static final String[] approvalMailList = {"revathi.chinnasamy@hindujatech.com","gopinath.elangovan@hindujatech.com"};

    public static final String[] qualityApprovalMailList = {"jegan.kasinathan@hindujatech.com","Jeffry.Stevenson@hindujatech.com"};    
   //public static final String[] qualityApprovalMailList = {"Thirumalai.Kailasam@defiance-tech.com","Jeffry.Stevenson@defiance-tech.com"};    
//     public static final String[] qualityApprovalMailList = {"Karthikeyan.Karunamoorthy@defiance-tech.com","Karthikeyan.Karunamoorthy@defiance-tech.com"};
//     public static final String[] qualityApprovalMailList = {"Mohamed.Musthaba@defiance-tech.com,malarvizhi.raja@defiance-tech.com"};

//    // public static final String URL_TO_REDIRECT_FOR_OJF = "http://10.17.1.232/ideal2_testing/";
//    public static final String URL_TO_REDIRECT_FOR_OJF = "http://ideal.defiance-tech.com/"; // For Sever
//    // public static final String[] approvalMailList = {"Mohamed.Musthaba@defiance-tech.com", "adaikkalam.p@defiance-tech.com"};
//    public static final String[] approvalMailList = {"Satish.Ganta@defiance-tech.com", "sureshkumar.ayyapitchai@defiance-tech.com"};
    //public static final String[] approvalMailList = {"Malarvizhi.raja@defiance-tech.com", "Mohamed.Musthaba@defiance-tech.com"};
    // public static final String[] qualityApprovalMailList = {"Mahendran.pa@defiance-tech.com,Suresh.Venkatachalam@defiance-tech.com,Sureshbabu.D@defiance-tech.com"};
    // public static final String[] qualityApprovalMailList = {"Suresh.Venkatachalam@defiance-tech.com"};
    // public static final String[] qualityApprovalMailList = {"14312","14058"};
//    public static final String[] qualityApprovalMailList = {"14029"};
    public static final int OJF_SURVEY_LIMIT_DAYS = 45;
    public static final int OJF_SURVEY_LIMIT_DAYS_FLOW = 7;
    public static final int CUSTOMER_LIST_MODULE_ID = 494;
    public static final int CUSTOMER_ADD_MODULE_ID = 496;
    public static final int CUSTOMER_EDIT_MODULE_ID = 495;
    public static final int CUSTOMER_APPROVE_MODULE_ID = 497;
    public static final int CUSTOMER_APPROVAL_LIST_MODULE_ID = 498;
    public static final String MYSQL_DATE_INSERT = "yyyy-MM-dd";
    public static final String MYSQL_DATE_SELECT = "dd-MMM-yyyy";
    public static final int EXIT_MODULES_NUMBER = 6;

   public static final String ExternalConfigFile = "D:\\propertyfiles\\applicationconfiguration.properties";
//   public static final String ExternalConfigFile = "E:\\propertyfiles\\applicationconfiguration.properties";

    public static final String CUSTOMER_SAVE_STATUS = "s";
    public static final String CUSTOMER_SUBMIT_STATUS = "m";
    public static final String CUSTOMER_AMENDED_STATUS = "d";
    public static final String CUSTOMER_APPROVED_STATUS = "a";
    public static final String CUSTOMER_REJECTED_STATUS = "r";
    public static final int RESIGN_UNDELETE = 0;
    public static final int RESIGN_DELETE = 1;
    public static final String[] statusList = {"0", "Resignation Form Saved", "Resignation Form Submitted", "RM Approved", "RM Rejected", "Finance Approved", "Finance Rejected", "Admin Approved", "Admin Rejected", "Network Approved", "Network Rejected", "HR Approved", "HR Rejected"};
    public static final String[] noticeWaiverValueList = {"Fully waived", "Partially waived", "No waiver"};
    public static final String[] surrenderValueList = {"Yes", "No", "NA"};
    public static final int SLIDER_MAX_VALUE = 5;
    public static final int SLIDER_MIN_VALUE = 1;
    public static final String SURVEY_ANSWER_RADIO = "R";
    public static final String SURVEY_ANSWER_SLIDER = "S";
    public static final String SURVEY_ANSWER_FREE_TEXT = "F";
    public static final String SURVEY_ANSWER_MULTIPLE = "M";
    public static final int SURVEY_SUBMIT_STATUS = 1;
//    public static final String[] ADMIN_NOT_NULL_FIELDS={"idCard","drawerKey","dataCard","mobilePhone","simCard","laptop"};
    public static final String[] RMCLR_NOT_NULL_FIELDS = {"CompDoc", "ProjDoc", "CustApproval", "EmpQpd", "RmOthers"};
//    public static final String[] ADMIN_NOT_NULL_FIELDS={"IdCard","DrawerKey","DataCard","MobilePhone","SimCard","Laptop"};
    public static final String[] ADMIN_NOT_NULL_FIELDS = {"IdCard", "DataCard", "BusinessCard", "CmpResPhone", "MobilePhone", "SimCard", "OutBillClr", "Laptop", "CdPenDrive", "CabinKey", "DrawerKey", "AdOther"};
    public static final String[] FINANCE_NOT_NULL_FIELDS = {"TravelAdvance", "SalaryAdvance", "Relocation", "JoiningBonus",
        "NoticeReimburse", "ProfessionalFee", "Loans", "OfficialExpenses", "Other"};
    public static final String[] NS_NOT_NULL_FIELDS = {"DesktopPwd", "EmailDeactivation", "NsOthers"};
    public static final String[] HR_NOT_NULL_FIELDS = {"ExitType", "IdealToolDeactivated", "LeaveBalance", "OverseasBond", "ExitInterview", "HrInsurance", "HrOthers"};
//    public static final String[] ADMIN_DISPLAY_FIELDS={"ID Card/Access Card","Drawer Key","Data Card","Mobile Phone handset","Mobile Phone SIM Card","Laptop"};
    public static final String[] ADMIN_DISPLAY_FIELDS = {"ID Card/Access Card", "Internet Data Card", "Business Cards", "Company Provided Residence Phone", "Mobile Phone handset", "Mobile Phone SIM Card", "Outstanding Mobile Bills Cleared", "Laptop", "All external mass storage devices (CD/pen drives)", "Cabin Keys", "Drawer/Storage Keys", "Others"};
    public static final String[] FINANCE_DISPLAY_FIELDS = {"Travel Advance", "Salary Advance", "Relocation", "Joining Bonus",
        "Notice Reimbursement", "Professional Fee", "Loans and Advances", "Outstanding Loans and official expenses cleared", "Others"};
    public static final String[] NS_DISPLAY_FIELDS = {"Desktop Password Disabled", "Email ID Deactivation", "Others"};
    public static final String[] HR_DISPLAY_FIELDS = {"Exit Type", "iDeal Tool Deactivated", "Leave Balance", "Overseas Bond/Training Bond", "Exit Interview", "Insurance", "Others"};
    public static final String[] RMCLR_DISPLAY_FIELDS = {"Company Manuals / Documents / Training Material", "Project / function related transition documents", "Customer informed and approval obtained", "QPD completed", "Others"};
    public static final int NULL_REPLACEMENT = 0;
    public static final String STR_NULL_REPLACEMENT = "0";
    public static final String adminModuleName = "Admin";
    public static final String financeModuleName = "Finance";
    public static final String nsModuleName = "Network & Support";
    public static final String rmModuleName = "Reporting Manager";
    public static final String hrModuleName = "Human Resource";
    public static final String exitSurveyModuleName = "Exit Survey";
    public static final String surrenderYesValue = "1";
    public static final String surrenderNoValue = "2";
    public static final String surrenderNAValue = "3";
    public static final int[] CUSTOMER_MENU_ID = {73, 75, 76, 77, 79};
    public static final String REQUEST_SAVE_STATUS = "s";
    public static final String REQUEST_SUBMIT_STATUS = "m";
    public static final String REQUEST_CLOSED_STATUS = "c";
    public static final String REQUEST_DROPPED_STATUS = "d";
    public static final String REQUEST_ONHOLD_STATUS = "h";
    public static final String[] REQUEST_DELIVARY_DISPLAY_FIELDS = {"Development Methodology", "Maintenance Methodology", "Production Support Methodology", "ERP Implementation Methodology", "Engineering Services Methodology"};
    public static final String[] REQUEST_PROJECT_FIELDS = {"Project Acquisition", "Initiation", "Estimation", "Planning & Tracking", "Reviews and Defect Management", "Metrics", "Configuration Management", "Project Delivery & Closure", "Professional Services", "Outsourcing"};
    public static final String[] REQUEST_SUPPORT_DISPLAY_FIELDS = {"Admin", "Quality", "Sales", "Business Planning", "Operations", "Finance", "HR", "N&S"};
    //Quality FeedBack Module ID
      public static final String QUALITY_REQUEST_MODULE_ID= "569";
      public static final String QUALITY_RESPONSE_MODULE_ID= "570";
      public static final String QUALITY_REPORT_MODULE_ID= "571";
    
      //Customer Mapping Module Id:
      public static final String CUSTOMER_MAPPING_MODULE_ID="615";
      public static final String DEBTORS_REPROT_AUTHENTICATORS_ID="523";
      public static final int DELETED_VALUE=1;
      public static final int UN_DELETED_VALUE=0;
      public static final String CUSTOMER_CONTACT_BUSINESS="bu";
      public static final String CUSTOMER_CONTACT_FINANCE="f";
      public static final String CUSTOMER_ADDRESS_BILLING="b";
      public static final String CUSTOMER_ADDRESS_WORKlOCATION="w";
      public static final String customerModuleName = "Customer Module";
      public static final String customerModuleReferenceId ="97630";

    //Configuration Values
    public static final String feedbackType = "423";
    public static final String improvementProcessType = "427";
    public static final String processAreaType = "431";
    public static final String focusAreaType = "435";
    public static final String evaluationStatus = "415";
    public static final String requestStatus = "419";
//    Local
//    public static final String feedbackType= "401";
//    public static final String improvementProcessType= "405";
//    public static final String processAreaType= "409";
//    public static final String focusAreaType= "413";
//    public static final String evaluationStatus= "437";
//    public static final String requestStatus= "441";
//    
    //Status Values
    public static final String[] status = {"Customer Submit", "Quality Admin Submit"};
    //File Upload Path
  public static final String fileUploadPath = "D:\\quailtyFileUpload\\";
//   public static final String fileUploadPath = "E:/quailtyFileUpload/";
  public static final String SBU = "1";
  public static final String bandId1 = "15";
  public static final String bandId2 = "35";
  public static final String bandId3 = "36";
  public static final String bandId4 = "2";
  public static final String bandId5 = "3";
  public static final String bandId = bandId1 + "," + bandId2 + "," + bandId3 + "," + bandId4 + "," + bandId5;
  
 
}

