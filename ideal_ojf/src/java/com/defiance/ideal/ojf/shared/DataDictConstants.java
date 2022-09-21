package com.defiance.ideal.ojf.shared;

import java.util.Properties;

public class DataDictConstants {

    public static final String JNDINAME = "java:comp/env/intra";
    public static final String EMAIL_SERVER_NAME = "smtp.mail.apac.microsoftonline.com";//"smtp.gmail.com";
    public static final String MAIL_PORT_VALUE = "587";//"465";
//    public static final String EMAILID = "dhr@defiance-tech.com";
    public static final String EMAILID = "dhr@hindujatech.com";
    public static final String EMAIL_PASSWORD = "Welcome456";
    public static final Properties emailProps;
  public static final String dbUrl = "jdbc:mysql://192.168.3.8:3306/ideal_rmstesting";//mydb";//intranet_uat";
// public static final String dbUrl = "jdbc:mysql://192.168.3.250:3306/ideal2_travelplan";
    public static final String driver = "com.mysql.jdbc.Driver";
 public static final String password = "";
  //  public static final String password = "passw0rd@123";//admin";//passw0rd@123";
    public static final String userName = "root";
    // public static final String[] category = {"DataBase", "Desktop"};
    // public static final String[] type = {"Software", "Booting Issue", "PowerSave"};
    public static final String[] type = {"Support", "New Procurement", "Query"};
    public static final String[] category = {"Hardware", "Software"};
    public static final String[] itemS = {"SQL Server", "Oracle", "Sybase"};
    public static final String[] itemH = {"Desktop", "Laptop", "Server"};
    public static final String[] loanType = {"Personal Loan", "Vehicle Loan"};
    // public static final String[] payRequestType = {"Clarification", "Salary Certificate", "Change Request"};
    public static final String[] payRequestType = {"Clarification", "Change Request"};
    public static final String[] loanYears = {"1 Year", "2 Years", "3 Years", "4 Years", "5 Years"};
    public static final String[] financeStatus = {"Completed", "Not Feasible"};
    public static final String[] finStatus = {"Pending", "Completed", "Not Feasible"};
    public static final String[] ApprovalStatus = {"Pending", "Processed", "Rejected"};
    public static final String[] appStatus = {"Pending", "Approve", "Reject"};
//    public static final String[] reqReason = {"New", "Damaged", "Lost"};
    public static final String[] reqReason = {"Damaged", "Lost"};
    public static final String[] mobileRequest = {"Sim Card", "Data card", "Black Berry"};
    public static final String[] confRoomType = {"ASV tower", "Khivraj Building"};
    public static final String[] confRoomFor = {"Self", "Guest"};
    public static final String[] reimbursementFor = {"Client Entertainment", "Team Lunch", "Telephone", "Others"};
    public static final String[] month = {"January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static final String[] requiredFor = {"Project", "Function"};
    public static final String[] yesNo = {"No", "Yes"};
    public static final String[] hrRequestType = {"Address Proof", "Salary Certificate", "Proof of Employment"};
    //  public static final String fileUploadPath = "c://RMSUploads/";
  public static final String fileUploadPath = "D://ideal_rms/infraUploads/";
    public static final String corpUploadPath = "D://ideal_rms/corpUploads/";
    public static final String payrollUploadPath = "D://ideal_rms/payrollUploads/";
    public static final String hrUploadPath = "D://ideal_rms/hrUploads/";
    public static final String reimburseUploadPath = "D://ideal_rms/reimburseUploads/";
    public static final String UploadPath = "C:/Uploads/";
    public static final String sendMailStatus = "true";
  //   public static final String traingRoomAdminId = "111";
     public static final String[] traingRoomAdminId = {"111"};
      public static final String[] traingRoomAdminEmpId = {"14242"};
      public static final String unitHeadGp = "8";
      public static final String ITHeadGp = "11";

      public static final String[] hours = {"01", "02","03","04","05","06","07","08","09","10",
      "11","12","13","14","15","16","17","18","19","20","21", "22","23"};
      public static final String[] minutes = {"00", "15","30","45"};
   /* public static final String fileUploadPath = "C://RMSUploads/infraUploads/";
    public static final String corpUploadPath = "C://RMSUploads/corpUploads/";
    public static final String payrollUploadPath = "C://RMSUploads/payrollUploads/";
    public static final String hrUploadPath = "C://RMSUploads/hrUploads/";
    public static final String reimburseUploadPath = "C://RMSUploads/reimburseUploads/";
    public static final String UploadPath = "E://ideal_rms/Uploads/";*/

    static {
        emailProps = new Properties();
        emailProps.put("mail.transport.protocol", "smtp");
        emailProps.put("mail.smtps.host", "smtp.mail.apac.microsoftonline.com");
        emailProps.put("mail.smtps.auth", "true");
        emailProps.put("mail.smtps.quitwait", "false");
        emailProps.put("mail.smtp.starttls.enable", "true");
        emailProps.put("mail.smtp.port", MAIL_PORT_VALUE);

    }
}
