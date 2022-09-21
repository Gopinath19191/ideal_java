/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.util;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 16113
 */
public class CommonConfig {
    public static final String EMP_ID= "EMP_ID";
    public static final String EMP_NAME = "EMP_NAME";
    public static final String PRACTICE_NAME = "PRACTICE_NAME";
    public static final String SUB_PRACTICE_NAME = "SUB_PRACTICE_NAME";
    public static final String EFFECTIVE_DATE = "EFFECTIVE_DATE";
    public static final String BAND = "BAND";
    public static final String DESIGNATION = "DESIGNATION";
    public static final String RM_ID = "RM_ID";
    public static final String RM_NAME = "RM_NAME";
    public static final String FILE_UPLOAD_PATH ="D:/bulkUpload/";
    public static final String F_NAME="FIRST_NAME";
    public static final String L_NAME="LAST_NAME";
    public static final String M_NAME="MIDDLE_NAME";
    public static final String DOB = "DATE_OF_BIRTH(DD-MM-YYYY)";
    public static final String PAN_NO= "PAN_NO";
    public static final String RRF_ID="RRF_ID";    
    public static final String P_CODE="POSITION_CODE";    
    public static final String C_ID="CANDIDATE_ID";
    public static final String PERSONAL_EMAIL="PERSONAL_EMAIL";
    public static final String ALT_EMAIL ="ALTERNATE_EMAIL";
    public static final String CURR_STATUS ="CURRENT_STATUS";
    public static final String EMP_STAT="EMPLOYMENT_STATUS";  //EMPLOYMENT STATUS
    public static final String DESI="DESIGNATION";  
    public static final String SRC_HIRE="SOURCE_OF_HIRE";  
    public static final String PASS_NO="PASSPORT_NO";  
    public static final String COMP_STRU="COMPANY_STRUCTURE"; 
    public static final String PRA_GRP="PRACTICE_GROUP"; 
    public static final String SPRA_GRP="SUB_PRACTICE_GROUP"; 
    //public static final String BAND="BAND"; 
    public static final String SUB_BAND="SUB_BAND"; 
    public static final String Employment="Permanent"; 
    public static final String RRFSTATUS ="10";
    public static final String BIll ="b";
    public static final String BUHID ="0";
    public static final String RRFMSTATUS ="0";
    public static final String RRFEMPID ="0";
    public static final String RCRTYPE ="e";
    
    
    
    public static String commonCodeForFileUpload(File dir, MultipartFile file) throws IOException {
             Random random = new Random();
            int ran = random.nextInt();
        if(ran<0){
            ran = -(ran);
        }   
        String temp =  file.getOriginalFilename().trim();
        String splitFile[] = temp.split("\\.");
        String tempfile = splitFile[0] + ran +"."+splitFile[1];
        File file1 = new File(dir.getAbsolutePath() + "/" + tempfile);
        file1.createNewFile();
        file.transferTo(file1);
        return tempfile;
    }
}
