/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.shared;

import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author Admin
 */
public class CommonConfigurations {

  static Properties configFile = new Properties();
    
    public static HashMap employeeStatus = Config.getChildElements(Config.getParentId("employment_status_others"));
    
    public static final String AA_MODULE_ID = "389";
    //public static final int HR_FOR_APPRAISAL = 347;
    public static final int HR_FOR_APPRAISAL = 2862;
    public static final int FINANCE_FOR_APPRAISAL = 259;//Finance Emp Id -14003 (Sathish Ganta
    public static final int APPRAISER_INTIAL_STATUS = 0;
    public static final int APPRAISER_SEND_BACK = 1;
    public static final int APPRAISEE_SUBMIT_STATUS = 2;
    public static final int REVIEWER_SEND_BACK = 3;
    public static final int APPRAISER_SUBMIT_STATUS = 4;
    public static final int NORMALIZER_SEND_BACK = 5;
    public static final int REVIEWER_SUBMIT_STATUS = 6;
    public static final int HR_SEND_BACK = 7;
    public static final int NORMALIZER_SUBMIT_STATUS = 8;
    public static final int HR_SUBMIT_STATUS = 9;
    public static final int ONE = 1;
    public static final int TRIGGER_STATUS = 1;
    public static final int TRIGGER_DISABLED = 0;

    public static final boolean SEND_MAILS=true;
    
//    public static final String fileUploadPath="E:\\appraiseeUploads\\";
//    public static final String APPRAISEE_DOCUMENT_UPLOAD_PATH="E:\\appraiseeUploads\\";
//    public static final String APPRAISEE_FILE_UPLOAD_CODE="AA_AP";
//    public static final String ExternalConfigFile = "E:\\propertyfiles\\aaconfiguration.properties";
    public static final String fileUploadPath="D:\\appraiseeUploads\\";
    public static final String APPRAISEE_DOCUMENT_UPLOAD_PATH="D:\\appraiseeUploads\\";
    public static final String APPRAISEE_FILE_UPLOAD_CODE="AA_AP";
    public static final String ExternalConfigFile = "D:\\propertyfiles\\aaconfiguration.properties";
    
}
