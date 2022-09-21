/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraisee.dao;

import com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO;
import com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.beehive.controls.api.bean.Control;
import com.defiance.ideal.qpd.appraisee.db.AppraiseeDB;
import com.defiance.ideal.shared.CommonConfigurations;
import com.defiance.ideal.shared.MailMessages;
import com.defiance.ideal.shared.MysqlDatabase;
import com.defiance.ideal.shared.SendMail;
import com.defiance.ideal.shared.SendMailDTO;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
@ControlImplementation(isTransient=true)
public class AppraiseeDAOImpl implements AppraiseeDAO{

    private transient final Logger logger = Logger.getLogger(this.getClass().getName());

    @Control
    private AppraiseeDB dbAppraisee;

    @Control
    private MysqlDatabase sharedDb;

    public AppraiseeDetailsDTO authenticateAppraisee(String empNum,int appraiseeQuarter,int appraiseeYear) throws Exception{
           AppraiseeDetailsDTO Test=null;
           try{
//               Test =dbAppraisee.authenAppraisee(empNum,appraiseeQuarter,appraiseeYear);
               Test =dbAppraisee.authenAppraisee(empNum,appraiseeYear);
           }
           catch(Exception e){
                logger.error("Exception @ AppraiseeDAOImpl @ authenticateAppraisee"+e.getMessage());
           }
           return Test;
    }
    public AppraiseeDetailsDTO getAppraiserName(int appraiserId) throws NullPointerException{
           AppraiseeDetailsDTO appraiserName=null;

           appraiserName=dbAppraisee.appraiserName(appraiserId);
           return appraiserName;
    }

    public AppraiseeDetailsDTO getReviewerName(int reviewerId) throws NullPointerException{
           AppraiseeDetailsDTO appraiserName=null;

           appraiserName=dbAppraisee.reviewerName(reviewerId);
           return appraiserName;
    }

    public AppraiseeDetailsDTO getAppraiseeSBU(int deptId) throws NullPointerException{
           AppraiseeDetailsDTO appraiserName=null;

           appraiserName=dbAppraisee.appraiseeSBU(deptId);
           return appraiserName;
    }

    public AppraiseeDetailsDTO checkAppraiseeComments(int appraiseeId) throws NullPointerException{
           AppraiseeDetailsDTO checkAppraiseeComments=null;

           checkAppraiseeComments=dbAppraisee.chkAppraiseeComments(appraiseeId);
           return checkAppraiseeComments;
    }

    public MyAppraisalFormDTO[] getQualAttributes(String kraQuality){
            MyAppraisalFormDTO[] qualAttributes=null;

            qualAttributes=dbAppraisee.qualAttribs(kraQuality);
            return qualAttributes;
    }

     public MyAppraisalFormDTO[] getCostAttributes(String kraCost){
            MyAppraisalFormDTO[] costAttributes=null;

            costAttributes=dbAppraisee.costAttribs(kraCost);
            return costAttributes;
    }

     public MyAppraisalFormDTO[] getCustomerAttributes(String kraCustomer){
            MyAppraisalFormDTO[] customerAttributes=null;

            customerAttributes=dbAppraisee.customerAttribs(kraCustomer);
            return customerAttributes;
    }

     public MyAppraisalFormDTO[] getDevlopAttributes(String kraDevlop){
         MyAppraisalFormDTO[] devlopAttributes=null;

            devlopAttributes=dbAppraisee.devlopAttribs(kraDevlop);
            return devlopAttributes;
    }


    public void insertAppraiseeComments(ArrayList<String> appraiseeComments,String empNum){
            //AppraiseeDetailsDTO insertAppraiseeComment=null;
            int appraiseeId=Integer.parseInt(empNum);
            Iterator itr=appraiseeComments.iterator();
            //String appraiseeComment[]=(String[]) appraiseeComments.toArray();
            String comment;
            while(itr.hasNext()){
                comment=(String) itr.next();
                dbAppraisee.appraiseeCommentsInsert(appraiseeId, comment);
            }
            //return insertAppraiseeComment;
    }

    public void updateAppraiseeComments(ArrayList<String> appraiseeComments,String empNum){
            //AppraiseeDetailsDTO insertAppraiseeComment=null;
            int kraQtrId=1;
            int appraiseeId=Integer.parseInt(empNum);
            Iterator itr=appraiseeComments.iterator();
            //String appraiseeComment[]=(String[]) appraiseeComments.toArray();
            String comment;
            while(itr.hasNext()){
                comment=(String) itr.next();
                dbAppraisee.appraiseeCommentsUpdate(appraiseeId, comment,kraQtrId);
            }
            //return insertAppraiseeComment;
    }

    public void updateAppraiseeStatus(int appraiseeId,int quarter,int quarterYear) throws NullPointerException{
          int submitStatus = CommonConfigurations.APPRAISEE_SUBMIT_STATUS;
          String dataString;
          String appraisalYear = quarterYear-1 + "-" +quarterYear;
//        System.out.println(appraiseeDetails.getAppraiseeEmail()+appraiseeDetails.getAppraiserEmail()+"appraisee mail;");
        try {
            SendMail mailObj = new SendMail();
            MailMessages messageObj = new MailMessages();
//            SendMailDTO appraiseeDetails  = sharedDb.getEmployeeEmailFromId(appraiseeId,quarter,quarterYear);
            SendMailDTO appraiseeDetails  = sharedDb.getEmployeeEmailFromId(appraiseeId,quarterYear);
            dataString=appraiseeDetails.getAppraiserNameMail()+"#~~#"+appraiseeDetails.getFirstName()+" "+appraiseeDetails.getLastName();
            logger.info("appraiseeDetails = " + appraiseeDetails.getEmailId()+"-"+messageObj.getSubject("APPRAISEE_QPDFORM_SUBMIT","")+"-"+messageObj.getMessage("APPRAISEE_QPDFORM_SUBMIT",appraiseeDetails,""));
//            dbAppraisee.updateAppraiseeStatus(appraiseeId,quarter,quarterYear,submitStatus);
            dbAppraisee.updateAppraiseeStatus(appraiseeId,quarterYear,submitStatus);

            mailObj.smtpMail(appraiseeDetails.getAppraiserEmailId(), messageObj.getSubject("APPRAISER_QPDFORM_SUBMIT",""), messageObj.getMessage("APPRAISER_QPDFORM_SUBMIT",dataString,appraisalYear),"");
            mailObj.smtpMail(appraiseeDetails.getEmailId(), messageObj.getSubject("APPRAISEE_QPDFORM_SUBMIT",""), messageObj.getMessage("APPRAISEE_QPDFORM_SUBMIT",dataString,appraisalYear),"");
            
            } catch (Exception e) {
                logger.error("Exception @ AppraiseeDAOImpl @ updateAppraiseeStatus"+e.getMessage());
            }

          

      }

    public MyAppraisalFormDTO[] getKraData(int bandId, int appraisalYear, int appraisalQuarter,String appraiseeId,int departmentId){
         MyAppraisalFormDTO[] kraData=null;
         int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
//         kraData=dbAppraisee.getKraData(bandId,appraisalYear,appraisalQuarter,appraiseeId,departmentId,triggerStatus);
         kraData=dbAppraisee.getKraData(bandId,appraisalYear,appraiseeId,departmentId,triggerStatus);
         return kraData;
    }

    public void insertOrUpdateAppraiseeData(MyAppraisalFormDTO formData,int appraiseeId) {
          int formLength = formData.getKraQtrIdHidden().length;
          int unDeletedStatus = 0;
          int deletedStatus = 1;
          System.out.println("formData.getTopAchievementsCount()"+formData.getTopAchievementsCount());
          System.out.println("formData.getDevelopmentNeedsCount()"+formData.getDevelopmentNeedsCount());
          System.out.println("formData.getGoalSheetCount()"+formData.getGoalSheetCount());



          for(int achievements=0;achievements<=formData.getTopAchievementsCount();achievements++){
              System.out.println(" KRA "+formData.getKeyResultAreas()[achievements]);
          // System.out.println(" Employee Remarks "+formData.getEmployeeRemarks()[achievements]);
              System.out.println(" ID "+formData.getTopAchievementsId()[achievements]);
              System.out.println(" Status "+formData.getTopAchievementsStatus()[achievements]);

              if(formData.getTopAchievementsStatus()[achievements].equals("undeleted")){

                  if(!(formData.getKeyResultAreas()[achievements].equals(""))){
                      dbAppraisee.insertOrUpdateAchievements(
                              formData.getTopAchievementsId()[achievements],
                              formData.getKeyResultAreas()[achievements],
                              formData.getAppraiseeYear(), appraiseeId, unDeletedStatus);
                  }
              }else if(formData.getTopAchievementsId()[achievements]!=0 && formData.getTopAchievementsStatus()[achievements].equals("deleted")){
                  dbAppraisee.setDeleteAchievements(formData.getTopAchievementsId()[achievements], deletedStatus);
              }
              
          }


        for(int devNeeds = 0; devNeeds <= formData.getDevelopmentNeedsCount(); devNeeds++){

              System.out.println(" DN Data "+formData.getDevNeedData()[devNeeds]);
              System.out.println(" DN ID "+formData.getDevelopmentNeedsId()[devNeeds]);
              System.out.println(" DN Status "+formData.getDevelopmentNeedsStatus()[devNeeds]);

              if(formData.getDevelopmentNeedsStatus()[devNeeds].equals("undeleted")){

                  if(!(formData.getDevNeedData()[devNeeds].equals(""))){
                    dbAppraisee.insertOrUpdateDevNeeds(formData.getDevelopmentNeedsId()[devNeeds],formData.getDevNeedData()[devNeeds],formData.getAppraiseeYear(),appraiseeId,unDeletedStatus);
                  }
                  
              }else if(formData.getDevelopmentNeedsId()[devNeeds]!=0 && formData.getDevelopmentNeedsStatus()[devNeeds].equals("deleted")){
                  
                  dbAppraisee.setDeleteDevNeeds(formData.getDevelopmentNeedsId()[devNeeds],deletedStatus);
                  
              }
              
        }

        for (int goal = 0; goal <= formData.getGoalSheetCount(); goal++){

              System.out.println(" Goal Data "+formData.getGoalData()[goal]);
              System.out.println(" Goal ID "+formData.getGoalId()[goal]);
              System.out.println(" Goal Status "+formData.getGoalSheetStatus()[goal]);

              if(formData.getGoalSheetStatus()[goal].equals("undeleted")){

                  if(!(formData.getGoalData()[goal].equals(""))){
                    dbAppraisee.insertOrUpdateGoals(formData.getGoalId()[goal],formData.getGoalData()[goal],formData.getAppraiseeYear(),appraiseeId,unDeletedStatus);
                  }
                  
              }else if(formData.getGoalId()[goal]!=0 && formData.getGoalSheetStatus()[goal].equals("deleted")){
                  dbAppraisee.setDeleteGoal(formData.getGoalId()[goal],deletedStatus);
              }

        }
          
          for (int i = 0; i < formLength; i++) {
                if(formData.getQpdKraIdHidden()[i]==0){
                    dbAppraisee.insertAppraiseeData(formData.getKraQtrIdHidden()[i],formData.getAppraiseeComments()[i],appraiseeId);
                }else{
                    dbAppraisee.updateAppraiseeData(formData.getKraQtrIdHidden()[i],formData.getAppraiseeComments()[i],appraiseeId,formData.getQpdKraIdHidden()[i]);
                }
          }

    }

    public MyAppraisalFormDTO[] getAchievementsData(int appraiseeId, int appraisalYear) {
        MyAppraisalFormDTO[] achievementsData=null;
         
//         kraData=dbAppraisee.getKraData(bandId,appraisalYear,appraisalQuarter,appraiseeId,departmentId,triggerStatus);
         achievementsData=dbAppraisee.getAchievementsData(appraiseeId,appraisalYear);
         return achievementsData;
    }

    public MyAppraisalFormDTO[] getDevNeedsData(int appraiseeId, int appraisalYear) {
        MyAppraisalFormDTO[] devNeedsData=null;

//         kraData=dbAppraisee.getKraData(bandId,appraisalYear,appraisalQuarter,appraiseeId,departmentId,triggerStatus);
         devNeedsData=dbAppraisee.getDevNeedsData(appraiseeId,appraisalYear);
         return devNeedsData;
    }

    public MyAppraisalFormDTO[] getGoalData(int appraiseeId, int appraisalYear) {
         MyAppraisalFormDTO[] goalData=null;
         goalData=dbAppraisee.getGoalData(appraiseeId,appraisalYear);
         return goalData;
    }
    
//    public int insertOrUpdateAppraiseeData(MyAppraisalFormDTO formData,int appraiseeId) {
//                 int formLength = formData.getKraQtrIdHidden().length;
//                 int lastInsertId = 0;
//          for (int i = 0; i < formLength; i++) {
//                if(formData.getQpdKraIdHidden()[i]==0){
//                    dbAppraisee.insertAppraiseeData(formData.getKraQtrIdHidden()[i],formData.getAppraiseeComments()[i],appraiseeId);
//                    lastInsertId = dbAppraisee.getLastInsertId();
//                    System.out.println("Last Insert Id "+lastInsertId);
//                }else{
//                    lastInsertId = formData.getQpdKraIdHidden()[i];
//                    dbAppraisee.updateAppraiseeData(formData.getKraQtrIdHidden()[i],formData.getAppraiseeComments()[i],appraiseeId,formData.getQpdKraIdHidden()[i]);
//                }
//          }
//
//                 return lastInsertId;
//    }
    
}
