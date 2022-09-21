/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.ojf.shared;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Admin
 */
public class CommonConfigurations {
    
     Properties configFile = new Properties();

       
//    public static final String dbUrl = "jdbc:mysql://localhost:3306/ideal2";
//    public static final String userName = "root";
//    public static final String password = "";

    public static final String dbUrl = "jdbc:mysql://localhost:3306/ideal_aa";
    public static final String userName = "ideal2";
    public static final String password = "passw0rd@123";
    
    public static final String driver = "com.mysql.jdbc.Driver";

    public static final String DATE_FORMAT_NOW = "dd-MMM-yyyy";

    public static final String[] sourceOfResume = {"Direct","Jobsite","Referral","Partner","Others"};
    public static final String[] noticeBuyoutReq = {"Yes","No"};
    public static final String CandidateRefPrefix = "HTL";
    public static final String CandidateRefDateFormat = "MMMyy";
    //0 - "Shortlisted"
    //1 - "Declined"
    //2 - "Onhold"
    //2 - "Initiated Joining Formalities Not in Config But manually maintained"
    //3 - "Joining Formalities Completed Not in Config But manually maintained"
    public static final String[] candidateStatus    = {"Shortlisted","Declined","Onhold"};
    public static final String[] offerStatus        = {"Accepted","Rejected"};
    public static final String[] maritalStatus      = {"Married","Unmarried","Widow/Widower","Divorced"};
    public static final String[] relationShips      = {"Father","Mother","Spouse","Son","Daughter"};
    public static final String[] bankAccType        = {"Existing","New"};
    public static final String[] bankNames          = {"YES","AXIS","CITI","HDFC","ICICI","INDUSIND"};
    public static final String[] jfStatus          = {"Trigger Mail To Candidate","Joining Formalities Initiated","Joining Formalities Completed check details","Data Verified","JF Data Added to iDeal"};
    public static final String initialCandidateRefNo = "0";
    public static final String JF_MODULE_CODE = "JF";
    public static final String JF_X_PROOFNAME = "xProof";
    public static final String JF_P2_PROOFNAME = "p2Proof";
    public static final String JF_GRAD_PROOFNAME = "gradProof";
    public static final String JF_PGRAD_PROOFNAME = "pgradProof";
    public static final String JF_OTHERS_PROOFNAME = "otherProof";
    public static final String JF_BANK_PROOFNAME = "bankProof";
    public static final String JF_DL_PROOFNAME = "driveLicProof";
    public static final String JF_PASSPORT_PROOFNAME = "passportProof";
    public static final String JF_JOINERPHOTO_PROOFNAME = "joinerPhotoProof";
    public static final String JF_JOINERSIGNATURE_NAME = "joinerSignatureProof";
    public static final String JF_JOINERADDRESS_PROOFNAME = "joinerAddressProof";
    public static final String JF_JOINERADHAR_PROOFNAME = "joinerAdharProof";
    public static final String JF_JOINERUAN_PROOFNAME = "joinerUanProof";
    public static final String JF_Education = "Education";
    public static final String JF_Certification = "Certification";
    public static final String JF_Experience = "Experience";
    // Got from acos table in iDeal
//    public static final String JF_MODULE_ID = "110";// For Local Machine
    public static final String JF_MODULE_ID = "455"; // For Production machine
    //public static final String JF_MODULE_ID = "395";

//    public static final String mailUrlPath = "http://ideal.defiance-tech.com:9000/ideal_ojf/com/defiance/ideal/joiningForm/joinerDetails.do?trackNumber=";
    public static final String mailUrlPath = "http://localhost:8080/ideal_ojf/com/defiance/ideal/joiningForm/joinerDetails.do?trackNumber=";
    //public static final String mailUrlPath = "http://localhost:8091/ideal_jf";
    //public static final String mailUrlPath = "http://10.18.1.147:8092/ideal_ojf/com/defiance/ideal/joiningForm/joinerDetails.do?trackNumber=";

    public static final String fileUploadPath="D:/OJFProofUpload";
    public static final String fileUploadPathLive="D:/OJFProofUpload";
    
   // public static final String fileUploadPathLive="C:/wamp/www/iDeal_demo/app/webroot/uploads/employee_photos/";
    //public static final String fileUploadPathLive="C:/wampn/www/iDeal/app/webroot/uploads/employee_photos/";// For Production server
    //ACO ID's
    public static final String NEW_EMPLOYEE_ACO_ID="11";
    //ARO ID's
    public static final String JOINING_FORMALITY_ARO_ID="";
    public static final String JOINING_FORMALITY_NEWCANDIDATE_EMPLOYEEID="0";
    public static final String JOINING_FORMALITY_NEWCANDIDATE_GROUPID="18";// (New Employee -  OJF) in Groups table
    public static final String JOINING_FORMALITY_IDEAL_GROUPID="2";
//    public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://192.168.1.219/iDeal_demo/employees/joining_formality_account_creation/";//For Local Machine
//    public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://220.226.3.80/ideal2_enhancements/employees/joining_formality_account_creation/";// For 220.226.4.18 Machine
//    public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://192.168.1.219/iDeal_demo/employees/joining_formality_account_creation/";// For 220.226.4.18 Machine
    //public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://10.18.1.147/ideal2/employees/joining_formality_account_creation/";// For 220.226.4.18 Machine
    //public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://10.18.1.36/ideal2/employees/joining_formality_account_creation/";// For Local Machine
//    public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://220.226.4.80/ideal2_test_ojf/employees/joining_formality_account_creation/";// For Production server demo
    //public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://ideal.defiance-tech.com/employees/joining_formality_account_creation/";// For Production server live
    //public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://ideal.hindujatech.com/employees/joining_formality_account_creation/";// For local Machine
    //public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://ideal.hindujatech.com/employees/joining_formality_account_creation/";
     // public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://localhost/ideal_php/ideal/employees/joining_formality_account_creation/";
	//  public static final String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL="http://10.18.1.45/ideal/employees/joining_formality_account_creation/";
    // OJF Status Id Details

    public static final int JF_TRIGGER_MAIL_CANDIDATE = 0;
    public static final int JF_INITIATED = 1;
    public static final int JF_JOINER_SAVEDATA = 2;
    public static final int JF_COMPLETED_CHECK_DETAILS = 3;
    public static final int JF_SENDBACKTOJOINER = 4;
    public static final int JF_DATA_VERIFIED_BY_HR = 5;
    public static final int JF_ADDED_TO_IDEAL = 6;

    public static final String propertyPath ="D:/OJF_Properties/OJFProperties.txt";

    public static final String employementTypeConfigId ="140";
    public static final String empBillableConfigId ="52";
    public static final String recruitment_team_dl = "recruitment.team@hindujatech.com";
    
//public static final String ojfGroupMail="humanresources@hindujatech.com,tech.support@hindujatech.com";
  // public static final String ojfGroupMail="revathi.chinnasamy@hindujatech.com,thara.gopinath@hindujatech.com"; // For Testing purpose
    // my code
    public static  String id;




}
