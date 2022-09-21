/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.admin.dao;

import com.defiance.ideal.qpd.admin.db.AdminDBCTL;
import com.defiance.ideal.qpd.admin.dto.AdminDTO;
import com.defiance.ideal.shared.CommonConfigurations;
import com.defiance.ideal.shared.MailMessages;
import com.defiance.ideal.shared.MysqlDatabase;
import com.defiance.ideal.shared.SendMail;
import com.defiance.ideal.shared.SendMailDTO;
import java.util.ArrayList;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.beehive.controls.api.bean.Control;
/**
 *
 * @author Hariharan.C
 */
@ControlImplementation(isTransient = true)
public class AdminDAOImpl implements AdminDAO{


    @Control
    private AdminDBCTL dbCTRL;

    @Control
    private MysqlDatabase sharedDbCTRL;

    public AdminDTO[] getStructureDetails() {
          AdminDTO[] masterDataBeans=null;
        try {
             masterDataBeans=dbCTRL.getStructureDetails();
            } catch (Exception e) {
                e.printStackTrace();
            }
           return  masterDataBeans;
        }

    public AdminDTO[] filterEmployeeData(String appraiserName,String concatenatedBand,String dojCheck, String concatenatedStatus, String concatenatedStructure,int appraisalYear,int appraisalPeriod) {
     
        AdminDTO[] appraiserData = null;
        String whereCondition="";
        String whereCondition2="";
        try{

            if(("").equals(concatenatedStructure)){
                concatenatedStructure = "''";
            }else{
                whereCondition += " AND emp.structure_name IN("+concatenatedStructure+") ";
                whereCondition2 += " AND emp.structure_name IN("+concatenatedStructure+") ";
            }
            if(("").equals(concatenatedStatus)){
                concatenatedStatus = "''";
            }else{
                whereCondition += " AND emp.employment_status IN("+concatenatedStatus+") ";
                whereCondition2 += " AND emp.employment_status IN("+concatenatedStatus+") ";
            }

            if(("").equals(concatenatedBand)){
                concatenatedBand = "''";
            }else{
                whereCondition += " AND subbnd.parent_id IN("+concatenatedBand+") ";
                whereCondition2 += " AND bnd.parent_id IN("+concatenatedBand+") ";
            }
            if((null==appraiserName) || ("0").equals(appraiserName)){
                appraiserName = "''";
            }else{
                whereCondition += " AND appr.id="+appraiserName;
                whereCondition2 += " AND appr.id="+appraiserName;
            }
            System.out.println("where conditon =============>"+whereCondition);
              System.out.println("where conditon2 =============>"+whereCondition2);
            System.out.println("Appraisal Year ========== > "+appraisalYear);
            if(!"".equals(whereCondition) || !"".equals(whereCondition2)){
                appraiserData = dbCTRL.filterEmployeeData(dojCheck,appraisalYear,whereCondition,whereCondition2);
            }
        }catch(Exception e) {
        e.printStackTrace();
        }
        return appraiserData;

    }
   
    public void triggerAppraisal(AdminDTO formData,int empId) {
        try{
            int dataCount = formData.getTriggerCheckForm().length;
            int hrId = CommonConfigurations.HR_FOR_APPRAISAL;
            int financeId = CommonConfigurations.FINANCE_FOR_APPRAISAL;
            int submitStatus = CommonConfigurations.APPRAISER_INTIAL_STATUS;

            String appraisalYear = formData.getAppraisalYearFinal()-1 +"-"+formData.getAppraisalYearFinal();

            String qpdId;
            String subjectString="";
            String dataString;
            String dataString2;
            ArrayList distinctAppraiserList = new ArrayList();



            String[] appraiseeNames = new String[dataCount];
            String[] appraiserIds = new String[dataCount];
            String[] appraiserNames = new String[dataCount];

            SendMail mailObj = new SendMail();
            SendMailDTO employeeData = new SendMailDTO();
            SendMailDTO appraiserData = new SendMailDTO();
            MailMessages messageObj = new MailMessages();
            
            
            for (int i = 0; i < dataCount; i++) {
                
                                if(formData.getQpdIdForm()[i]==0){
                                qpdId = null;
                                }else{
                                qpdId = Integer.toString(formData.getQpdIdForm()[i]);
                                }
            
//          mailMessageObj.sendMessage(formData.getAppraiseeIdForm()[i],"TRIGGER_APPRAISAL",null,empId,sharedDbCTRL);
                        if(formData.getSubmitAppraisal()!=null){
                                   

                                    try {
                                        employeeData = sharedDbCTRL.getEmailFromId(formData.getAppraiseeIdForm()[i]);
                                        appraiserData = sharedDbCTRL.getEmailFromId(formData.getAppraiserIdForm()[i]);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        System.out.println("exception at sendMessage@MailMessages = " + e);
                                    }
                                    
                            dbCTRL.triggerAppraisal(formData.getAppraisalPeriodFinal(),formData.getAppraisalYearFinal(),formData.getBandIdForm()[i],formData.getDepIdForm()[i],formData.getAppraiseeIdForm()[i],formData.getAppraiserIdForm()[i],formData.getReviewerIdForm()[i],formData.getNormalizerIdForm()[i],hrId,financeId,submitStatus,qpdId,CommonConfigurations.TRIGGER_STATUS);

//                            subjectString = "Q"+formData.getAppraisalPeriodFinal()+"-"+formData.getAppraisalYearFinal();

                            

                            subjectString = " "+formData.getAppraisalYearFinal();
                            dataString = employeeData.getFullName()+"#~~#"+subjectString;
                            dataString2 = appraiserData.getFullName()+"#~~#"+subjectString;
                                    

                            mailObj.smtpMail(employeeData.getEmailId(),messageObj.getSubject("TRIGGER_APPRAISAL_APPRAISEE",subjectString),messageObj.getMessage("TRIGGER_APPRAISAL_APPRAISEE",dataString,appraisalYear),"");


                            appraiseeNames[i] = employeeData.getFullName();
                            appraiserNames[i] = appraiserData.getFullName();
                            appraiserIds[i]= appraiserData.getEmailId();

                             if(distinctAppraiserList.size()!=0){
                                    if(!distinctAppraiserList.contains(appraiserData.getEmailId())){
                                        distinctAppraiserList.add(appraiserData.getEmailId());
                                    }
                                }else{
                                        distinctAppraiserList.add(appraiserData.getEmailId());
                                }


                            //mailObj.smtpMail(appraiserData.getEmailId(),messageObj.getSubject("TRIGGER_APPRAISAL_APPRAISER",subjectString),messageObj.getMessage("TRIGGER_APPRAISAL_APPRAISER",dataString2),"");
                            
                        }else if(formData.getSaveAppraisal()!=null || formData.getExcelExport()!=null){
                            
                            dbCTRL.triggerAppraisal(formData.getAppraisalPeriodFinal(),formData.getAppraisalYearFinal(),formData.getBandIdForm()[i],formData.getDepIdForm()[i],formData.getAppraiseeIdForm()[i],formData.getAppraiserIdForm()[i],formData.getReviewerIdForm()[i],formData.getNormalizerIdForm()[i],hrId,financeId,submitStatus,qpdId,CommonConfigurations.TRIGGER_DISABLED);
                            
                        }
                        
                }

                      for (int appraiserCount = 0; appraiserCount < distinctAppraiserList.size(); appraiserCount++) {

                            String appraiseeNamemailString = "";
                            String appraiserName = "";

                                    for (int mailCount = 0; mailCount < appraiseeNames.length; mailCount++) {
                                        if(distinctAppraiserList.get(appraiserCount).equals(appraiserIds[mailCount])){
                                            appraiseeNamemailString += appraiseeNames[mailCount]+"<br>";
                                            appraiserName=appraiserNames[mailCount];
                                        }
                                    }
                        mailObj.smtpMail(distinctAppraiserList.get(appraiserCount).toString(), messageObj.getSubject("TRIGGER_APPRAISAL_APPRAISER",subjectString), messageObj.getMessage("TRIGGER_APPRAISAL_APPRAISER",appraiserName+"#~~#"+subjectString+"#~~#"+appraiseeNamemailString,appraisalYear),"");
                        }



        }catch(Exception e) {
           e.printStackTrace();
        }
    }


    public AdminDTO[] getEmployeeName(String searchString) {
    AdminDTO[] masterData=null;
    try {
        System.out.println("searchString = " + searchString);
         masterData=dbCTRL.getEmployeeName(searchString);
         System.out.println("masterData = " + masterData);
    } catch (Exception e) {
         e.printStackTrace();
    }
       return  masterData;
    }

    public AdminDTO[] filterEmployeeDataChangeReq(String appraiserName,String concatenatedBand,String dojCheck, String concatenatedStatus, String concatenatedStructure, int appraisalYear, int appraisalPeriod) {
        AdminDTO[] appraiserData = null;
        String whereCondition="";
        try{

            if(("").equals(concatenatedStructure)){
                concatenatedStructure = "''";
            }else{
                whereCondition += " AND ea.departmentId IN("+concatenatedStructure+") ";
            }
            if(("").equals(concatenatedStatus)){
                concatenatedStatus = "''";
            }else{
                whereCondition += " AND emp.employment_status IN("+concatenatedStatus+") ";
            }
            if(("").equals(concatenatedBand)){
                concatenatedBand = "''";
            }else{
                whereCondition += " AND bnd.parent_id IN("+concatenatedBand+") ";
            }
            if((null==appraiserName) || ("").equals(appraiserName)){
                appraiserName = "''";
            }else{
                whereCondition += " AND appr.id="+appraiserName;                
            }

//        appraiserData = dbCTRL.filterEmployeeDataChangeRequest(dojCheck,appraisalYear,appraisalPeriod,CommonConfigurations.TRIGGER_STATUS,whereCondition);
        appraiserData = dbCTRL.filterEmployeeDataChangeRequest(dojCheck,appraisalYear,CommonConfigurations.TRIGGER_STATUS,whereCondition);

        }catch(Exception e) {
        e.printStackTrace();
        }
        return appraiserData;
    }
   

    public void updateAppraisal(AdminDTO formData, int empId) {
        try{
            int dataCount = formData.getTriggerCheckForm().length;
            int hrId = CommonConfigurations.HR_FOR_APPRAISAL;
            int financeId = CommonConfigurations.FINANCE_FOR_APPRAISAL;
//            int submitStatus = 0;

            

            for (int i = 0; i < dataCount; i++) {
//            dbCTRL.updateAppraisal(formData.getAppraisalPeriodFinal(),formData.getAppraisalYearFinal(),formData.getBandIdForm()[i],formData.getDepIdForm()[i],formData.getAppraiseeIdForm()[i],formData.getAppraiserIdForm()[i],formData.getReviewerIdForm()[i],formData.getNormalizerIdForm()[i],hrId,financeId,submitStatus,formData.getQpdIdForm()[i]);
            dbCTRL.updateAppraisal(formData.getAppraisalPeriodFinal(),formData.getAppraisalYearFinal(),formData.getBandIdForm()[i],formData.getDepIdForm()[i],formData.getAppraiseeIdForm()[i],formData.getAppraiserIdForm()[i],formData.getReviewerIdForm()[i],formData.getNormalizerIdForm()[i],hrId,financeId,formData.getQpdIdForm()[i]);
//            mailMessageObj.sendMessage(formData.getAppraiseeIdForm()[i],"UPDATE_APPRAISAL",null,empId,sharedDbCTRL);

                              SendMail mailObj = new SendMail();
                              SendMailDTO employeeData = new SendMailDTO();
                              SendMailDTO CCData = new SendMailDTO();
                              MailMessages messageObj = new MailMessages();
                              try {
                                  employeeData = sharedDbCTRL.getEmailFromId(formData.getAppraiseeIdForm()[i]);
                                  CCData = sharedDbCTRL.getEmailFromId(empId);
                              } catch (Exception e) {
                                  e.printStackTrace();
                                  System.out.println("exception at sendMessage@MailMessages = " + e);
                              }
                              /*
                               * Line Commented for Stopping mails upon updation of employee data on HR - Varun request by Hariharan.C
                               * 
                                mailObj.smtpMail(employeeData.getEmailId(),messageObj.getSubject("UPDATE_APPRAISAL",""),messageObj.getMessage("UPDATE_APPRAISAL",employeeData.getFullName()),CCData.getEmailId());
                               *
                               *
                               */


            }
        }catch(Exception e) {
        e.printStackTrace();
        }
    }

    public AdminDTO[] getBandData() {
           AdminDTO[] bandData=null;
        try {
             bandData=dbCTRL.getBandData();
            } catch (Exception e) {
                e.printStackTrace();
            }
           return bandData;
    }


}
