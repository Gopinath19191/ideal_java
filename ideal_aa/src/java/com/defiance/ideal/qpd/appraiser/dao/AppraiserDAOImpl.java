/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraiser.dao;

import com.defiance.ideal.qpd.appraiser.db.AppraiserDB;
import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserRatingFormDTO;
import com.defiance.ideal.qpd.managers.db.ManagerDB;
import com.defiance.ideal.qpd.managers.dto.ManagerDTO;
import com.defiance.ideal.shared.CommonConfigurations;
import com.defiance.ideal.shared.CommonFunctions;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.beehive.controls.api.bean.Control;
import com.defiance.ideal.shared.MailMessages;
import com.defiance.ideal.shared.MysqlDatabase;
import com.defiance.ideal.shared.SendMail;
import com.defiance.ideal.shared.SendMailDTO;
import org.apache.log4j.Logger;
/**
 *
 * @author Administrator
 */
@ControlImplementation(isTransient=true)
public class AppraiserDAOImpl implements AppraiserDAO{

    private transient final Logger logger = Logger.getLogger(this.getClass().getName());

    @Control
    private AppraiserDB dbAppraiser;
    
    @Control
    private ManagerDB dbManager;

    @Control
    private MysqlDatabase sharedDb;


    public AppraiseeListDTO[] getAppraiseeList(int employeeId, int appraisalYear, int appraisalQuarter) {
       AppraiseeListDTO[] appraiseeData=null;
       int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
               try{
//                   appraiseeData =dbAppraiser.getAppraiseeList(employeeId,appraisalYear,appraisalQuarter,triggerStatus);
                   appraiseeData =dbAppraiser.getAppraiseeList(employeeId,appraisalYear,triggerStatus);
               }
               catch(Exception e){
                e.printStackTrace();
               }
               return appraiseeData;
    }

    public AppraiserFormDTO[] getKraData(int bandIdForm, int appraisalQuarterForm, int appraisalYearForm, int appraiseeIdForm,int departmentId) {
         AppraiserFormDTO[] appraiseeData=null;
               try{
//                   appraiseeData =dbAppraiser.getKraData(bandIdForm,appraisalQuarterForm,appraisalYearForm,appraiseeIdForm,departmentId);
                   appraiseeData =dbAppraiser.getKraData(bandIdForm,appraisalYearForm,appraiseeIdForm,departmentId);
               }
               catch(Exception e){
                e.printStackTrace();
               }
               return appraiseeData;
    }

    public void updateAppraiseeData(AppraiserRatingFormDTO formData) {
                
        System.out.println("tttt"+formData.getDatePicker());
       
            try{
                int commentsLength = formData.getAppraiseeComments().length;
                  for (int i = 0; i < commentsLength; i++) {
                    dbAppraiser.updateAppraiseeData(formData.getAppraiseeId(),formData.getKraQtrIdHidden()[i],formData.getQpdKraIdHidden()[i],formData.getAppraiseeComments()[i],formData.getAppraiserCommentsRating()[i],formData.getAppraiserCommentsNew()[i]);
                  }

                if(formData.getAchievementId()!=null){
                    int achievementsLength = formData.getAchievementId().length;

                    for (int achievement = 0; achievement < achievementsLength; achievement++) {
                        System.out.println(" Achievement Id " + formData.getAchievementId()[achievement]);
                        System.out.println(" Achievement Data " + formData.getAppraiserRemarks()[achievement]);
                        dbAppraiser.updateAchievementsData(formData.getAchievementId()[achievement], formData.getAppraiserRemarks()[achievement]);
                    }
                }

                if(formData.getDevelopmentNeedsId()!=null){
                    int developmentLength = formData.getDevelopmentNeedsId().length;

                    for (int devel = 0; devel < developmentLength; devel++) {
                        System.out.println(" Development Id " + formData.getDevelopmentNeedsId()[devel]);
                        System.out.println(" Devel Remarks " + formData.getApprDevelopmentRemarks()[devel]);
                        dbAppraiser.updateDevelopmentData(formData.getDevelopmentNeedsId()[devel], formData.getApprDevelopmentRemarks()[devel]);
                    }
                }

                if(formData.getGoalsId()!=null){
                    int goalLength = formData.getGoalsId().length;

                    for (int goal = 0; goal < goalLength; goal++) {
                        System.out.println(" Goal Id " + formData.getGoalsId()[goal]);
                        System.out.println(" Devel Remarks " + formData.getApprGoalRemarks()[goal]);
                        dbAppraiser.updateGoalData(formData.getGoalsId()[goal], formData.getApprGoalRemarks()[goal]);
                    }
                }
                    
//                    dbAppraiser.updateAppraiserRating(formData.getFinalRating(),formData.getAppraiseeId(),formData.getAppraiseeQuarter(),formData.getAppraiseeYear(),formData.getJustifyRatingAppraiser(),formData.getAreasOfImprovement(),formData.getTechnologyTraining(),formData.getSoftskillTraining());
                    System.out.println("APP Prom Recom:::"+formData.getAppraiserPromotionRecommeded()+"-----------------"+formData.getButtonAction());
                    if (formData.getButtonAction() != null && !formData.getButtonAction().equals("") && formData.getButtonAction().equals("Submit")) {
                    int levelSkip = CommonFunctions.checkLevelSkip(formData.getAppraiseeId(), formData.getAppraiseeYear(), sharedDb, "appraiser");
                    if (levelSkip == 0) {
                        System.out.println("inside appraiser");  
                        dbAppraiser.updateAppraiserrrRating(formData.getFinalRating(), formData.getAppraiseeId(), formData.getAppraiseeYear(), formData.getJustifyRatingAppraiser(), formData.getAreasOfImprovement(), formData.getTechnologyTraining(), formData.getSoftskillTraining(), formData.getAppraiserPromotionRecommeded(), formData.getDatePicker());
                    } else if (levelSkip == 1) {
                        dbAppraiser.updateAppraiserRating(formData.getFinalRating(), formData.getAppraiseeId(), formData.getAppraiseeYear(), formData.getJustifyRatingAppraiser(), formData.getAreasOfImprovement(), formData.getTechnologyTraining(), formData.getSoftskillTraining(), formData.getAppraiserPromotionRecommeded(),formData.getDatePicker());
                        dbManager.updateSubmitStatus(formData.getAppraiserId(), formData.getAppraiseeYear(), formData.getAppraiseeId(), formData.getFinalRating(), CommonConfigurations.REVIEWER_SUBMIT_STATUS, " ", formData.getAppraiserPromotionRecommeded());
                    } else if (levelSkip == 2) {
                        dbAppraiser.updateAppraiserRating(formData.getFinalRating(), formData.getAppraiseeId(), formData.getAppraiseeYear(), formData.getJustifyRatingAppraiser(), formData.getAreasOfImprovement(), formData.getTechnologyTraining(), formData.getSoftskillTraining(), formData.getAppraiserPromotionRecommeded(),formData.getDatePicker());
                        dbManager.updateSubmitStatus(formData.getAppraiserId(), formData.getAppraiseeYear(), formData.getAppraiseeId(), formData.getFinalRating(), CommonConfigurations.REVIEWER_SUBMIT_STATUS, " ", formData.getAppraiserPromotionRecommeded());
                        dbManager.updateSubmitStatusNormalizer(formData.getAppraiserId(), formData.getAppraiseeYear(), formData.getAppraiseeId(), formData.getFinalRating(), CommonConfigurations.NORMALIZER_SUBMIT_STATUS, " ", formData.getAppraiserPromotionRecommeded());
                    }
                }else{
                        dbAppraiser.updateAppraiserRating(formData.getFinalRating(), formData.getAppraiseeId(), formData.getAppraiseeYear(), formData.getJustifyRatingAppraiser(), formData.getAreasOfImprovement(), formData.getTechnologyTraining(), formData.getSoftskillTraining(), formData.getAppraiserPromotionRecommeded(),formData.getDatePicker());
                }
            }
            catch(Exception e){
                e.printStackTrace();
                 logger.error("Exception @ AppraiserDAOImpl @ updateAppraiseeData "+e.getMessage());
            }
    }

    public void updateAppraiseeStatus(String button,int appraiseeId, int appraiseeQuarter, int appraiseeYear,int sendBackStatus,String reasonAppraiser){
        String appraisalYear = null;
        if(sendBackStatus == 1){
                int submitStatus = CommonConfigurations.APPRAISER_SEND_BACK;
        try {
        SendMail mailObj = new SendMail();
        MailMessages messageObj = new MailMessages();
        appraisalYear = appraiseeYear-1 +"-"+appraiseeYear;
        SendMailDTO appraiseeEmailData = sharedDb.getEmployeeEmailFromId(appraiseeId,appraiseeYear);
        String dataString=appraiseeEmailData.getFirstName()+" "+appraiseeEmailData.getLastName()+"#~~#"+reasonAppraiser;
        mailObj.smtpMail(appraiseeEmailData.getEmailId(), messageObj.getSubject("APPRAISER_SEND_BACK",""), messageObj.getMessage("APPRAISER_SEND_BACK",dataString,appraisalYear),appraiseeEmailData.getAppraiserEmailId());
        } catch (Exception e) {
            logger.error("Exception @ AppraiserDAOImpl @ updateAppraiseeStatus @ if condition "+e.getMessage());
        }
        dbAppraiser.updateAppraiseeStatus(appraiseeId,appraiseeYear,submitStatus);
        }else{
        int submitStatus = 0;
        try {
            appraisalYear = appraiseeYear-1 +"-"+appraiseeYear;
            int levelSkip = CommonFunctions.checkLevelSkip(appraiseeId,appraiseeYear,sharedDb,"appraiser");
            
            SendMail mailObj = new SendMail();
            MailMessages messageObj = new MailMessages();
            SendMailDTO appraiseeEmailData = sharedDb.getEmployeeEmailFromId(appraiseeId,appraiseeYear);

            String dataString=null;
            String dataString1=null;

            if(button!=null && !button.equals("") && button.equals("Submit")){
             if(levelSkip==0){
                submitStatus = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
                dataString=appraiseeEmailData.getReviewerNameMail()+"#~~#"+appraiseeEmailData.getAppraiserNameMail()+"#~~#"+appraiseeEmailData.getFirstName()+" "+appraiseeEmailData.getLastName();
                mailObj.smtpMail(appraiseeEmailData.getReviewerEmailId(), messageObj.getSubject("APPRAISER_SUBMIT_REVIEWER",""), messageObj.getMessage("APPRAISER_SUBMIT_REVIEWER",dataString,appraisalYear),"");
                mailObj.smtpMail(appraiseeEmailData.getAppraiserEmailId(), messageObj.getSubject("APPRAISER_SUBMIT",""), messageObj.getMessage("APPRAISER_SUBMIT",dataString,appraisalYear),"");
            }else if(levelSkip==1){
                submitStatus = CommonConfigurations.REVIEWER_SUBMIT_STATUS;
                dataString=appraiseeEmailData.getNormaliserNameMail()+"#~~#"+appraiseeEmailData.getFirstName()+" "+appraiseeEmailData.getLastName()+"#~~#"+appraiseeEmailData.getAppraiserNameMail();
                dataString1=appraiseeEmailData.getAppraiserNameMail()+"#~~#"+appraiseeEmailData.getFirstName()+" "+appraiseeEmailData.getLastName()+"#~~#"+appraiseeEmailData.getFirstName()+" "+appraiseeEmailData.getLastName();
                mailObj.smtpMail(appraiseeEmailData.getNormaliserEmailId(), messageObj.getSubject("REVIEWER_SUBMIT_FINAL_REVIEW",""), messageObj.getMessage("REVIEWER_SUBMIT_FINAL_REVIEW",dataString,appraisalYear),"");
                mailObj.smtpMail(appraiseeEmailData.getAppraiserEmailId(), messageObj.getSubject("REVIEWER_SUBMIT",""), messageObj.getMessage("REVIEWER_SUBMIT",dataString1,appraisalYear),"");
            }else if(levelSkip==2){
                submitStatus = CommonConfigurations.NORMALIZER_SUBMIT_STATUS;
                dataString=appraiseeEmailData.getAppraiserNameMail()+"#~~#"+appraiseeEmailData.getFirstName()+" "+appraiseeEmailData.getLastName()+"#~~#"+appraiseeEmailData.getAppraiserNameMail();
                dataString1=appraiseeEmailData.getHrNameMail()+"#~~#"+appraiseeEmailData.getAppraiserNameMail()+"#~~#"+appraiseeEmailData.getFirstName()+" "+appraiseeEmailData.getLastName();
                mailObj.smtpMail(appraiseeEmailData.getHrEmailId(), messageObj.getSubject("NORMALIZER_SUBMIT_HR",""), messageObj.getMessage("NORMALIZER_SUBMIT_HR",dataString1,appraisalYear),"");
                mailObj.smtpMail(appraiseeEmailData.getAppraiserEmailId(), messageObj.getSubject("NORMALIZER_SUBMIT",""), messageObj.getMessage("NORMALIZER_SUBMIT",dataString,appraisalYear),"");
            }
            }else{
                submitStatus = 2;
            }
           
            
//            String dataString=appraiseeEmailData.getReviewerNameMail()+"#~~#"+appraiseeEmailData.getAppraiserNameMail()+"#~~#"+appraiseeEmailData.getFirstName()+" "+appraiseeEmailData.getLastName();
//
//            mailObj.smtpMail(appraiseeEmailData.getReviewerEmailId(), messageObj.getSubject("APPRAISER_SUBMIT_REVIEWER",""), messageObj.getMessage("APPRAISER_SUBMIT_REVIEWER",dataString),"");

//            mailObj.smtpMail(appraiseeEmailData.getAppraiserEmailId(), messageObj.getSubject("APPRAISER_SUBMIT",""), messageObj.getMessage("APPRAISER_SUBMIT",dataString),"");

            } catch (Exception e) {
                logger.error("Exception @ AppraiserDAOImpl @ updateAppraiseeStatus @ else condition "+e.getMessage());
            }
        dbAppraiser.updateAppraiseeStatus(appraiseeId,appraiseeYear,submitStatus);
        }
        
    }

    public AppraiseeListDTO getSubmitStatus(int appraiseeId, int appraisalQuarter, int appraisalYear) {
        AppraiseeListDTO getSelectedAppraiseeDetails=null;
        try{
//           getSelectedAppraiseeDetails=dbAppraiser.getSelectedAppraiseeDetails(appraiseeId,appraisalQuarter,appraisalYear);
           getSelectedAppraiseeDetails=dbAppraiser.getSelectedAppraiseeDetails(appraiseeId,appraisalYear);
        }
        catch(Exception e){
                 logger.error("Exception @ AppraiserDAOImpl @ getSubmitStatus "+e.getMessage());
            }
        return getSelectedAppraiseeDetails;
    }

    public AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId) {
        AppraiserFormDTO[] achievementsData=null;
               try{
                   achievementsData =dbAppraiser.getAchievementsData(appraisalYear,appraiseeId);
               }
               catch(Exception e){
                e.printStackTrace();
               }
               return achievementsData;
    }

    public AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId) {
         AppraiserFormDTO[] developmentData=null;
               try{
                developmentData=dbAppraiser.getDevelopmentData(appraisalYear,appraiseeId);
               }
               catch(Exception e){
                e.printStackTrace();
               }
               return developmentData;
    }

    public AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId) {
               AppraiserFormDTO[] developmentData=null;
               
               try{
                developmentData=dbAppraiser.getGoalData(appraisalYear,appraiseeId);
               }catch(Exception e){
                e.printStackTrace();
               }
               return developmentData;
    }

    public AppraiserRatingFormDTO[] getLastFourQPDRating(int appraiseeId) {
        AppraiserRatingFormDTO[] qpdRating = null;
        try {
            qpdRating = dbAppraiser.getLastFourQPDRating(appraiseeId);
        } catch (Exception e) {
            logger.error("Exception @ Appraiser BOIMPL @ getLastThreeQPDRating " + e.getMessage());
        }
        return qpdRating;
    }

   
}
