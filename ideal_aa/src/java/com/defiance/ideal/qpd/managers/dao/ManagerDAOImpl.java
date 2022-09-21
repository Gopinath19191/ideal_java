/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.managers.dao;
import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import com.defiance.ideal.qpd.managers.db.ManagerDB;
import com.defiance.ideal.qpd.managers.dto.ManagerDTO;
import com.defiance.ideal.shared.CommonConfigurations;
import com.defiance.ideal.shared.CommonFunctions;
import com.defiance.ideal.shared.MailMessages;
import com.defiance.ideal.shared.MysqlDatabase;
import com.defiance.ideal.shared.SendMail;
import com.defiance.ideal.shared.SendMailDTO;
import java.util.ArrayList;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.log4j.Logger;
/**
 *
 * @author Administrator
 */
@ControlImplementation(isTransient = true)
public class ManagerDAOImpl implements ManagerDAO{

    private transient final Logger logger = Logger.getLogger(this.getClass().getName());

    @Control
    private ManagerDB dbCTRL;


    @Control
    private MysqlDatabase sharedDb;

    public ManagerDTO[] getAppraiseesByReviewer(String reviewerId, int appraisalQuarter, int appraisalYear) {

        ManagerDTO[] appraiseesbyReviewer=null;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        try{
            logger.info("ManagerDTO[] getAppraiseesByReviewer");
//        appraiseesbyReviewer=dbCTRL.getAppraiseesByReviewer(reviewerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
        appraiseesbyReviewer=dbCTRL.getAppraiseesByReviewer(reviewerId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception at Manager@DAOImpl @getAppraiseesByReviewer"+e.getMessage());
        }
        return appraiseesbyReviewer;
    }

    public ManagerDTO[] getAppraisersByReviewer(String reviewerId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] appraisersbyReviewer=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
//        appraisersbyReviewer=dbCTRL.getAppraisersByReviewer(reviewerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
        appraisersbyReviewer=dbCTRL.getAppraisersByReviewer(reviewerId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception at Manager@DAOImpl @getAppraisersByReviewer"+e.getMessage());
        }
        return appraisersbyReviewer;
    }

    public ManagerDTO[] getAppraiseesByReviewerAndAppraiser(String selectedAppraiserId, String reviewerId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] getAppraiseesByReviewerAndAppraiser=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
//        getAppraiseesByReviewerAndAppraiser=dbCTRL.getAppraiseesByReviewerAndAppraiser(selectedAppraiserId,reviewerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
        getAppraiseesByReviewerAndAppraiser=dbCTRL.getAppraiseesByReviewerAndAppraiser(selectedAppraiserId,reviewerId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception at Manager@DAOImpl @getAppraiseesByReviewerAndAppraiser"+e.getMessage());
        }
        return getAppraiseesByReviewerAndAppraiser;
    }

    public ManagerDTO[] getAppraiseesByHr(String hrId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] appraiseesbyHr=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.REVIEWER_SUBMIT_STATUS;
        try{
//        appraiseesbyHr=dbCTRL.getAppraiseesByHr(hrId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
        appraiseesbyHr=dbCTRL.getAppraiseesByHr(hrId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception at Manager@DAOImpl @getAppraiseesByHr"+e.getMessage());
        }
        return appraiseesbyHr;
    }

    public ManagerDTO[] getAppraiseesByHr(String hrId, int appraisalQuarter, int appraisalYear,String whereCondition) {
        ManagerDTO[] appraiseesbyHr=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.REVIEWER_SUBMIT_STATUS;
        try{
//        appraiseesbyHr=dbCTRL.getAppraiseesByHr(hrId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus,whereCondition);
        appraiseesbyHr=dbCTRL.getAppraiseesByHr(hrId,appraisalYear,submitStatusCheck,triggerStatus,whereCondition);
        }catch(Exception e){
            logger.error("Exception at Manager@DAOImpl @getAppraiseesByHr"+e.getMessage());
        }
        return appraiseesbyHr;
    }

    public void updateReviewerRating(String reviewerId, int appraisalQuarter, int appraisalYear, ManagerDTO formData,String button) {
            int submitStatus=CommonConfigurations.REVIEWER_SUBMIT_STATUS;
//            int submitStatusSendback=CommonConfigurations.REVIEWER_SEND_BACK;
            int length=formData.getReviewerRatingForm().length;

            String appraisalYearForMail = appraisalYear-1 +"-"+appraisalYear;

            

            String[] appraiseeNames = new String[length];
            String[] normaliserIds = new String[length];
            String[] normaliserNames = new String[length];
            
            String appraiseeNamesMailString = "";
           
            
            SendMailDTO appraiseeDetails=null;
            ArrayList distinctNormaliserList = new ArrayList();
            
        
        try{
            SendMail mailObj = new SendMail();
            MailMessages messageObj = new MailMessages();
            if(("Save").equals(button) || ("Export to Excel").equals(button)){
            for(int i=0;i<length;i++){
                logger.info("appraisee Id Form "+formData.getAppraiseeIdForm()[i]);
                logger.info("reviewer rate Form "+formData.getReviewerRatingForm()[i]);

                if(formData.getSubmitStatusForm()[i] == 5){
                    dbCTRL.updateNormalisedReviewerRating(reviewerId,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],formData.getJustifyRatingReviewer()[i]);
                }else{
                    dbCTRL.updateReviewerRating(reviewerId,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],formData.getJustifyRatingReviewer()[i],formData.getReviewerPromotionRecommended()[i]);
                    logger.info("reviewer rating justifi "+formData.getAppraiseeIdForm()[i]+formData.getJustifyRatingReviewer()[i]);
                }
                

                }
            }else if(formData.getSubmitToNormalizer()!=null){
                    for(int i=0;i<length;i++){
                        int levelSkip = CommonFunctions.checkLevelSkip(formData.getAppraiseeIdForm()[i],appraisalYear,sharedDb,"reviewer");
                        if(levelSkip==0){
                        dbCTRL.updateSubmitStatus(reviewerId,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],CommonConfigurations.REVIEWER_SUBMIT_STATUS,formData.getJustifyRatingReviewer()[i],formData.getReviewerPromotionRecommended()[i]);
                        }else if(levelSkip==2){
                        dbCTRL.updateSubmitStatus(reviewerId,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],CommonConfigurations.REVIEWER_SUBMIT_STATUS,formData.getJustifyRatingReviewer()[i],formData.getReviewerPromotionRecommended()[i]);
                        dbCTRL.updateSubmitStatusNormalizer(reviewerId,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],CommonConfigurations.NORMALIZER_SUBMIT_STATUS,formData.getJustifyRatingReviewer()[i],formData.getReviewerPromotionRecommended()[i]);
                        }
//                        dbCTRL.updateSubmitStatus(reviewerId,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],submitStatus,formData.getJustifyRatingReviewer()[i],formData.getReviewerPromotionRecommended()[i]);
                        appraiseeDetails  = sharedDb.getEmployeeEmailFromId(formData.getAppraiseeIdForm()[i],appraisalYear);
                            appraiseeNamesMailString += appraiseeDetails.getFirstName()+" "+appraiseeDetails.getLastName()+"<br>";
                            System.out.println("appraiseeNamesMailString==="+appraiseeNamesMailString);
                            appraiseeNames[i] = appraiseeDetails.getFirstName()+" "+appraiseeDetails.getLastName();
                            normaliserNames[i] = appraiseeDetails.getNormaliserNameMail();
                            normaliserIds[i]= appraiseeDetails.getNormaliserEmailId();

                               if(distinctNormaliserList.size()!=0){
                                    if(!distinctNormaliserList.contains(appraiseeDetails.getNormaliserEmailId())){
                                        distinctNormaliserList.add(appraiseeDetails.getNormaliserEmailId());
                                    }
                                }else{
                                        distinctNormaliserList.add(appraiseeDetails.getNormaliserEmailId());
                                }
                        }
                    int levelSkip =0;
                        for (int normaliserCount = 0; normaliserCount < distinctNormaliserList.size(); normaliserCount++) {

                            String appraiseeNamemailString = "";
                            String normaliserName = "";
                            String hrName = "";
                            
                                    for (int mailCount = 0; mailCount < appraiseeNames.length; mailCount++) {
                                        if(distinctNormaliserList.get(normaliserCount).equals(normaliserIds[mailCount])){
                                            appraiseeNamemailString += appraiseeNames[mailCount]+"<br>";
                                            normaliserName=normaliserNames[mailCount];
                                        }
                                        levelSkip = CommonFunctions.checkLevelSkip(formData.getAppraiseeIdForm()[mailCount],appraisalYear,sharedDb,"reviewer");
                                        System.out.println("::::::: for checking..."+levelSkip+"...."+appraiseeNames.length+"-----"+mailCount+"...."+appraiseeDetails.getHrEmailId());
                                    }
                            if (levelSkip == 0) {
                                mailObj.smtpMail(distinctNormaliserList.get(normaliserCount).toString(), messageObj.getSubject("REVIEWER_SUBMIT_FINAL_REVIEW",""), messageObj.getMessage("REVIEWER_SUBMIT_FINAL_REVIEW",normaliserName+"#~~#"+appraiseeNamemailString,appraisalYearForMail),"");
                            } else if (levelSkip==2) {
                                System.out.println(":::Here the problem..."+messageObj.getSubject("NORMALIZER_SUBMIT_HR", ""));
                                System.out.println(":::Here the problem..."+messageObj.getMessage("NORMALIZER_SUBMIT_HR", appraiseeDetails.getHrNameMail() + "#~~#" + normaliserName+"#~~#"+appraiseeNamemailString,appraisalYearForMail));
                                 mailObj.smtpMail(appraiseeDetails.getHrEmailId(), messageObj.getSubject("NORMALIZER_SUBMIT_HR", ""), messageObj.getMessage("NORMALIZER_SUBMIT_HR", appraiseeDetails.getHrNameMail() + "#~~#" + normaliserName+"#~~#"+appraiseeNamemailString,appraisalYearForMail), "");
                            }
                            System.out.println("normalizerId = " + reviewerId);
//                        mailObj.smtpMail(distinctNormaliserList.get(normaliserCount).toString(), messageObj.getSubject("REVIEWER_SUBMIT_FINAL_REVIEW",""), messageObj.getMessage("REVIEWER_SUBMIT_FINAL_REVIEW",normaliserName+"#~~#"+appraiseeNamemailString),"");
                        }
                    if(levelSkip == 0){
                        mailObj.smtpMail(appraiseeDetails.getReviewerEmailId(), messageObj.getSubject("REVIEWER_SUBMIT",""), messageObj.getMessage("REVIEWER_SUBMIT",appraiseeDetails.getReviewerNameMail()+"#~~#"+appraiseeNamesMailString,appraisalYearForMail),"");
                    } else if (levelSkip==2) {
                        mailObj.smtpMail(appraiseeDetails.getReviewerEmailId(), messageObj.getSubject("NORMALIZER_SUBMIT",""), messageObj.getMessage("NORMALIZER_SUBMIT",appraiseeDetails.getReviewerNameMail()+"#~~#"+appraiseeNamesMailString,appraisalYearForMail),"");
                    }
                        
                }
//            else{
//                System.out.println("send back to Appraiser");
//                for(int i=0;i<length;i++){
//                System.out.println(""+formData.getAppraiseeIdForm()[i]);
//                System.out.println(""+formData.getReviewerRatingForm()[i]);
//                dbCTRL.updateSubmitStatus(reviewerId,appraisalQuarter,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],submitStatusSendback,formData.getJustifyRatingReviewer()[i]);

//                        SendMailDTO appraiseeDetails  = sharedDb.getEmployeeEmailFromId(Integer.parseInt(reviewerId));
//                        System.out.println("appraiseeDetails = " + appraiseeDetails.getEmailId()+"-"+messageObj.getSubject("REVIEWER_SEND_BACK","")+"-"+messageObj.getMessage("REVIEWER_SEND_BACK",appraiseeDetails,""));
//                        mailObj.smtpMail(appraiseeDetails.getEmailId(), messageObj.getSubject("REVIEWER_SEND_BACK",""), messageObj.getMessage("REVIEWER_SEND_BACK",appraiseeDetails,""),"");
//                }
//            }
        }catch(Exception e){
            logger.error("Exception at Manager@DAOImpl @updateReviewerRating"+e.getMessage());
        }
    }

    public void updateFinalStatus(String hrId, int appraisalQuarter, int appraisalYear, ManagerDTO formData, String button) {
            int submitStatus=CommonConfigurations.HR_SUBMIT_STATUS;
            int submitStatusSendback=CommonConfigurations.HR_SEND_BACK;
            
            int financeId=CommonConfigurations.FINANCE_FOR_APPRAISAL;
            SendMailDTO appraiseesList=null;

            String appraisalYearForMail = appraisalYear-1+"-"+appraisalYear;

        try {

                            SendMail mailObj = new SendMail();
                            MailMessages messageObj = new MailMessages();

            if("Submit To Finance".equals(button)){
                    logger.info("Submit to Finance Button Clicked");
                    String employeeNamesString="";
                    for(int i=0;i<formData.getAppraiseeIdForm().length;i++){

//                      dbCTRL.updateFinalSubmitStatus(hrId,appraisalQuarter,appraisalYear,formData.getAppraiseeIdForm()[i],submitStatus);
                      dbCTRL.updateFinalSubmitStatus(hrId,appraisalYear,formData.getAppraiseeIdForm()[i],submitStatus);
                      
//                      appraiseesList  = sharedDb.getEmployeeEmailFromId(formData.getAppraiseeIdForm()[i],appraisalQuarter,appraisalYear);
                      appraiseesList  = sharedDb.getEmployeeEmailFromId(formData.getAppraiseeIdForm()[i],appraisalYear);
                      employeeNamesString +=appraiseesList.getEmployeeFullName()+"<br>";
                     
                    }

                    SendMailDTO hrDetails       = sharedDb.getEmailFromId(Integer.parseInt(hrId));
                    SendMailDTO financeDetails  = sharedDb.getEmailFromId(financeId);
                    
                     if(!("").equals(employeeNamesString)){
                        mailObj.smtpMail(financeDetails.getEmailId(), messageObj.getSubject("HR_SUBMIT_FINANCE",""), messageObj.getMessage("HR_SUBMIT_FINANCE",financeDetails.getFullName()+"#~~#"+hrDetails.getFullName()+"#~~#"+employeeNamesString,appraisalYearForMail),"");
                        mailObj.smtpMail(hrDetails.getEmailId(), messageObj.getSubject("HR_SUBMIT",""), messageObj.getMessage("HR_SUBMIT",hrDetails.getFullName()+"#~~#"+employeeNamesString,appraisalYearForMail),"");
                     }
                    
                                
                }else if(("Export to Excel").equals(button)){
                    logger.info("export to excel @ DAO Impl");
                }else{
                System.out.println("Appraisee Data Length"+formData.getAppraiseeIdForm().length);
                
                for(int i=0;i<formData.getAppraiseeIdForm().length;i++){
                    int sendbackStatus = 1;
                    String dataString=formData.getNormalizerNameForm()[i]+"#~~#"+formData.getReasonSendbackHr();
//                    dbCTRL.updateSendbackStatus(hrId,appraisalQuarter,appraisalYear,formData.getAppraiseeIdForm()[i],submitStatusSendback,sendbackStatus);
                    dbCTRL.updateSendbackStatus(hrId,appraisalYear,formData.getAppraiseeIdForm()[i],submitStatusSendback,sendbackStatus);
//                    SendMailDTO appraiseeDetails  = sharedDb.getEmployeeEmailFromId(Integer.parseInt(hrId),appraisalQuarter,appraisalYear);
                    SendMailDTO appraiseeDetails  = sharedDb.getHrEmailFromId(Integer.parseInt(hrId),appraisalYear);
                    System.out.println(""+appraiseeDetails.getEmailId());
                    System.out.println(""+messageObj.getSubject("HR_SEND_BACK",""));
                    System.out.println(messageObj.getMessage("HR_SEND_BACK",dataString,appraisalYearForMail));
                    System.out.println(formData.getNormalizerEmailForm()[i]);
                    mailObj.smtpMail(appraiseeDetails.getEmailId(), messageObj.getSubject("HR_SEND_BACK",""), messageObj.getMessage("HR_SEND_BACK",dataString,appraisalYearForMail),formData.getNormalizerEmailForm()[i]);
                    }
                 
               }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception@ManagerDAOImlp@updateFinalStatus()"+e.getMessage());
        }

    }


     public ManagerDTO[] getAppraiseesByFinance(String financeId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] appraiseesbyFinance=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.HR_SUBMIT_STATUS;
        try{
//            appraiseesbyFinance=dbCTRL.getAppraiseesByFinance(financeId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
            appraiseesbyFinance=dbCTRL.getAppraiseesByFinance(financeId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseesByFinance()"+e.getMessage());
        }
        return appraiseesbyFinance;
    }

    public ManagerDTO[] getKraData(int bandId, int appraisalYear, int appraisalQuarter, int appraiseeId,int departmentId) {
        ManagerDTO[] kraDataObj=null;
//        kraDataObj=dbCTRL.getKraData(bandId,appraisalYear,appraisalQuarter,appraiseeId,departmentId);;
        kraDataObj=dbCTRL.getKraData(bandId,appraisalYear,appraiseeId,departmentId);
        return kraDataObj;
    }

    public ManagerDTO[] getAppraiseesByNormalizer(String normalizerId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] appraiseesbyNormalizer=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
//        appraiseesbyNormalizer=dbCTRL.getAppraiseesByNormalizer(normalizerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
        appraiseesbyNormalizer=dbCTRL.getAppraiseesByNormalizer(normalizerId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseesByNormalizer()"+e.getMessage());
        }
        return appraiseesbyNormalizer;
    }

    public ManagerDTO[] getAppraiseesByNormalizer(String normalizerId, int appraisalQuarter, int appraisalYear,String whereCondition) {
        ManagerDTO[] appraiseesbyNormalizer=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
//        appraiseesbyNormalizer=dbCTRL.getAppraiseesByNormalizer(normalizerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus,whereCondition);
        appraiseesbyNormalizer=dbCTRL.getAppraiseesByNormalizer(normalizerId,appraisalYear,submitStatusCheck,triggerStatus,whereCondition);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseesByNormalizer()"+e.getMessage());
        }
        return appraiseesbyNormalizer;
    }
    
    public ManagerDTO[] getAppraiseesByBuh(String buhId, int appraisalQuarter, int appraisalYear,String whereCondition) {
        ManagerDTO[] appraiseesbyBuh=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
//        appraiseesbyNormalizer=dbCTRL.getAppraiseesByNormalizer(normalizerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus,whereCondition);
        appraiseesbyBuh=dbCTRL.getAppraiseesByBuh(buhId,appraisalYear,submitStatusCheck,triggerStatus,whereCondition);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseesByNormalizer()"+e.getMessage());
        }
        return appraiseesbyBuh;
    }

    public ManagerDTO[] getAppraiseesByNormalizerAndAppraiser(String selectedAppraiserId, String normalizerId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] getAppraiseesByNormalizerAndAppraiser=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
//        getAppraiseesByNormalizerAndAppraiser=dbCTRL.getAppraiseesByNormalizerAndAppraiser(selectedAppraiserId,normalizerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
        getAppraiseesByNormalizerAndAppraiser=dbCTRL.getAppraiseesByNormalizerAndAppraiser(selectedAppraiserId,normalizerId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseesByNormalizerAndAppraiser()"+e.getMessage());
        }
        return getAppraiseesByNormalizerAndAppraiser;
    }

    public Object getAppraisersByNormalizer(String normalizerId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] appraisersByNormalizer=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
//        appraisersByNormalizer=dbCTRL.getAppraisersByNormalizer(normalizerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
        appraisersByNormalizer=dbCTRL.getAppraisersByNormalizer(normalizerId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraisersByNormalizer()"+e.getMessage());
        }
        return appraisersByNormalizer;
    }

    public Object getReviewersByNormalizer(String normalizerId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] reviewersByNormalizer=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
//        reviewersByNormalizer=dbCTRL.getReviewersByNormalizer(normalizerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
        reviewersByNormalizer=dbCTRL.getReviewersByNormalizer(normalizerId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getReviewersByNormalizer()"+e.getMessage());
        }
        return reviewersByNormalizer;
    }
    
    public Object getAppraisersByBuh(String buhId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] appraisersByBuh=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
        appraisersByBuh=dbCTRL.getAppraisersByBuh(buhId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraisersByBuh()"+e.getMessage());
        }
        return appraisersByBuh;
    }

    public Object getReviewersByBuh(String buhId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] reviewersByBuh=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
        reviewersByBuh=dbCTRL.getReviewersByBuh(buhId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getReviewersByBuh()"+e.getMessage());
        }
        return reviewersByBuh;
    }
    
    public Object getNormalizersByBuh(String buhId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] normalizersByBuh=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
        normalizersByBuh=dbCTRL.getNormalizersByBuh(buhId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getNormalizersByBuh()"+e.getMessage());
        }
        return normalizersByBuh;
    }

    public ManagerDTO[] getAppraiseesByNormalizerAndReviewer(String selectedReviewerId, String normalizerId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] getAppraiseesByNormalizerAndReviewer=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
//        getAppraiseesByNormalizerAndReviewer=dbCTRL.getAppraiseesByNormalizerAndReviewer(selectedReviewerId,normalizerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
        getAppraiseesByNormalizerAndReviewer=dbCTRL.getAppraiseesByNormalizerAndReviewer(selectedReviewerId,normalizerId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseesByNormalizerAndReviewer()"+e.getMessage());
        }
        return getAppraiseesByNormalizerAndReviewer;
    }

    public ManagerDTO[] getAppraiseesByNormalizerAndAppraiserReviewer(String selectedAppraiserId, String selectedReviewerId, String normalizerId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] getAppraiseesByNormalizerAndAppraiserReviewer=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        int submitStatusCheck = CommonConfigurations.APPRAISER_SUBMIT_STATUS;
        try{
//        getAppraiseesByNormalizerAndAppraiserReviewer=dbCTRL.getAppraiseesByNormalizerAndAppraiserReviewer(selectedAppraiserId,selectedReviewerId,normalizerId,appraisalQuarter,appraisalYear,submitStatusCheck,triggerStatus);
        getAppraiseesByNormalizerAndAppraiserReviewer=dbCTRL.getAppraiseesByNormalizerAndAppraiserReviewer(selectedAppraiserId,selectedReviewerId,normalizerId,appraisalYear,submitStatusCheck,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseesByNormalizerAndAppraiserReviewer()"+e.getMessage());
        }
        return getAppraiseesByNormalizerAndAppraiserReviewer;
    }

    public void updateNormalizerRating(String normalizerId, int appraisalQuarter, int appraisalYear, ManagerDTO formData, String button) {
            int submitStatus=CommonConfigurations.NORMALIZER_SUBMIT_STATUS;
            int submitStatusSendback=0;
            int length=formData.getReviewerRatingForm().length;
            SendMailDTO appraiseeDetails=null;

            String appraisalYearForMail = appraisalYear-1+"-"+appraisalYear;

        try{
            SendMail mailObj = new SendMail();
            MailMessages messageObj = new MailMessages();
            if(("Save").equals(button) || ("Export to Excel").equals(button)){
                
            for(int i=0;i<length;i++){
                if(formData.getSendbackbyhrForm()[i] == 0){
                    dbCTRL.updateNormalizerRating(normalizerId,appraisalQuarter,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],formData.getJustifyRatingNormalizer()[i],formData.getNormalizerPromotionRecommended()[i]);
                }else if(formData.getSendbackbyhrForm()[i] == 1){
//                    dbCTRL.updateCorrectedNormalizerRating(normalizerId,appraisalQuarter,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i]);
                    dbCTRL.updateCorrectedNormalizerRating(normalizerId,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],formData.getJustifyRatingNormalizer()[i],formData.getNormalizerPromotionRecommended()[i]);
                    //System.out.println("reviewer rating justifi "+normalizerId+appraisalQuarter+appraisalYear+formData.getAppraiseeIdForm()[i]+formData.getReviewerRatingForm()[i]+formData.getJustifyRatingNormalizer()[i]);
                }


                }
            }
            else if("Submit To HR".equals(button)){
                String dataString="";
                String hrEmail="";
                String employeeNamesString="";
                    for(int i=0;i<length;i++){
                        
                        if(formData.getSendbackbyhrForm()[i] == 0){
//                          dbCTRL.updateSubmitStatusNormalizer(normalizerId,appraisalQuarter,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],submitStatus,formData.getJustifyRatingNormalizer()[i]);
                          dbCTRL.updateSubmitStatusNormalizer(normalizerId,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],submitStatus,formData.getJustifyRatingNormalizer()[i],formData.getNormalizerPromotionRecommended()[i]);
                          //dbCTRL.updateNormalisedSubmitStatus(normalizerId,appraisalQuarter,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],submitStatus,formData.getJustifyRatingReviewer()[i]);
                        }else{
//                          dbCTRL.updateSubmitStatusCorrectedNormalizerRating(normalizerId,appraisalQuarter,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],submitStatus);
                          dbCTRL.updateSubmitStatusCorrectedNormalizerRating(normalizerId,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],submitStatus,formData.getJustifyRatingNormalizer()[i],formData.getNormalizerPromotionRecommended()[i]);
                        }
                        dataString=formData.getHrNameForm()[i]+"#~~#"+formData.getNormalizerNameForm()[i];
                        hrEmail=formData.getHrEmailForm()[i];
//                        appraiseeDetails  = sharedDb.getEmployeeEmailFromId(formData.getAppraiseeIdForm()[i],appraisalQuarter,appraisalYear);
                        appraiseeDetails  = sharedDb.getEmployeeEmailFromId(formData.getAppraiseeIdForm()[i],appraisalYear);
                        employeeNamesString +=appraiseeDetails.getEmployeeFullName()+"<br>";
                        System.out.println("employeeNamesString==="+employeeNamesString);
                        //dbCTRL.updateSubmitStatusNormalizer(normalizerId,appraisalQuarter,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],submitStatus,formData.getJustifyRatingNormalizer()[i]);
                        
                        //System.out.println("appraiseeDetails = " + appraiseeDetails.getEmailId()+"-"+messageObj.getSubject("REVIEWER_SUBMIT")+"-"+messageObj.getMessage("REVIEWER_SUBMIT",appraiseeDetails));
                    }

                if(!("").equals(employeeNamesString)){
                        mailObj.smtpMail(hrEmail, messageObj.getSubject("NORMALIZER_SUBMIT_HR",""), messageObj.getMessage("NORMALIZER_SUBMIT_HR",dataString+"#~~#"+employeeNamesString,appraisalYearForMail),"");
                        System.out.println("normalizerId = " + normalizerId);
                        SendMailDTO normaliserDetails = sharedDb.getEmailFromId(Integer.parseInt(normalizerId));
                        mailObj.smtpMail(normaliserDetails.getEmailId(), messageObj.getSubject("NORMALIZER_SUBMIT",""),messageObj.getMessage("NORMALIZER_SUBMIT",normaliserDetails.getFullName()+"#~~#"+employeeNamesString,appraisalYearForMail),"");
                    }
                }
            /*else{
                System.out.println("send back to Appraiser");
                for(int i=0;i<length;i++){
                System.out.println(""+formData.getAppraiseeIdForm()[i]);
                System.out.println(""+formData.getReviewerRatingForm()[i]);
                dbCTRL.updateSubmitStatus(normalizerId,appraisalQuarter,appraisalYear,formData.getAppraiseeIdForm()[i],formData.getReviewerRatingForm()[i],submitStatusSendback,formData.getJustifyRatingReviewer()[i]);



                        SendMailDTO appraiseeDetails  = sharedDb.getEmployeeEmailFromId(Integer.parseInt(normalizerId));
                        System.out.println("appraiseeDetails = " + appraiseeDetails.getEmailId()+"-"+messageObj.getSubject("REVIEWER_SEND_BACK")+"-"+messageObj.getMessage("REVIEWER_SEND_BACK",appraiseeDetails));
                        mailObj.smtpMail(appraiseeDetails.getEmailId(), messageObj.getSubject("REVIEWER_SEND_BACK"), messageObj.getMessage("REVIEWER_SEND_BACK",appraiseeDetails),"");


                }
            }*/
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@updateNormalizerRating()"+e.getMessage());
        }
    }

    public ManagerDTO[] getAppraiseesByHrAndNormalizer(String hrId, int appraisalQuarter, int appraisalYear, String selectedNormalizerId) {
        ManagerDTO[] appraiseesbyHr=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        //int submitStatusCheck = CommonConfigurations.REVIEWER_SUBMIT_STATUS;
        try{
//        appraiseesbyHr=dbCTRL.getAppraiseesByHrAndNormalizer(hrId,appraisalQuarter,appraisalYear,selectedNormalizerId,triggerStatus);
        appraiseesbyHr=dbCTRL.getAppraiseesByHrAndNormalizer(hrId,appraisalYear,selectedNormalizerId,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseesByHrAndNormalizer()"+e.getMessage());
        }
        return appraiseesbyHr;
    }

    public Object getNormalizersByHr(String hrId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] appraiseesbyHr=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        //int submitStatusCheck = CommonConfigurations.REVIEWER_SUBMIT_STATUS;
        try{
//            appraiseesbyHr=dbCTRL.getNormalizersByHr(hrId,appraisalQuarter,appraisalYear,triggerStatus);
            appraiseesbyHr=dbCTRL.getNormalizersByHr(hrId,appraisalYear,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getNormalizersByHr()"+e.getMessage());
        }
        return appraiseesbyHr;
    }

    public Object getCompanyStructureBrHr(String hrId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] companyStructureBrHr=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        //int submitStatusCheck = CommonConfigurations.REVIEWER_SUBMIT_STATUS;
        try{
            companyStructureBrHr=dbCTRL.getCompanyStructureBrHr(hrId,appraisalQuarter,appraisalYear,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getCompanyStructureBrHr()"+e.getMessage());
        }
        return companyStructureBrHr;
    }

    public ManagerDTO[] getAppraiseesByHrAndSBU(String hrId, int appraisalQuarter, int appraisalYear, String selectedSBUDepartmentId) {
        ManagerDTO[] appraiseesbyHr=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        //int submitStatusCheck = CommonConfigurations.REVIEWER_SUBMIT_STATUS;
        try{
//        appraiseesbyHr=dbCTRL.getAppraiseesByHrAndSBU(hrId,appraisalQuarter,appraisalYear,selectedSBUDepartmentId,triggerStatus);
        appraiseesbyHr=dbCTRL.getAppraiseesByHrAndSBU(hrId,appraisalYear,selectedSBUDepartmentId,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseesByHrAndSBU()"+e.getMessage());
        }
        return appraiseesbyHr;
    }

    public ManagerDTO[] getAppraiseesByHrAndNormalizerAndSBU(String hrId, int appraisalQuarter, int appraisalYear, String selectedNormalizerId, String selectedSBUDepartmentId) {
        ManagerDTO[] appraiseesbyHr=null;
        int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
        //int submitStatusCheck = CommonConfigurations.REVIEWER_SUBMIT_STATUS;
        try{
//        appraiseesbyHr=dbCTRL.getAppraiseesByHrAndNormalizerAndSBU(hrId,appraisalQuarter,appraisalYear,selectedNormalizerId,selectedSBUDepartmentId,triggerStatus);
        appraiseesbyHr=dbCTRL.getAppraiseesByHrAndNormalizerAndSBU(hrId,appraisalYear,selectedNormalizerId,selectedSBUDepartmentId,triggerStatus);
        }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseesByHrAndNormalizerAndSBU()"+e.getMessage());
        }
        return appraiseesbyHr;
    }


    public ManagerDTO getAppraiseeCount(String reviewerId, int appraisalQuarter, int appraisalYear,String referenceType) {
         ManagerDTO appraiseeCount = null;
         int triggerStatus = CommonConfigurations.TRIGGER_STATUS;
         try{
//            appraiseeCount = dbCTRL.getAppraiseeCount(reviewerId, appraisalQuarter,appraisalYear,referenceType,triggerStatus);
            appraiseeCount = dbCTRL.getAppraiseeCount(reviewerId, appraisalYear,referenceType,triggerStatus);
         }catch(Exception e){
            logger.error("Exception@ManagerDAOImlp@getAppraiseeCount()"+e.getMessage());
         }
         return appraiseeCount;
    }

    public AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId) {
         AppraiserFormDTO[] achievementsData=null;
               try{
                   achievementsData =dbCTRL.getAchievementsData(appraisalYear,appraiseeId);
               }
               catch(Exception e){
                e.printStackTrace();
               }
               return achievementsData;
    }

    public AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId) {
        AppraiserFormDTO[] developmentData=null;
               try{
                developmentData=dbCTRL.getDevelopmentData(appraisalYear,appraiseeId);
               }
               catch(Exception e){
                e.printStackTrace();
               }
               return developmentData;
    }

    public AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId) {
         AppraiserFormDTO[] developmentData=null;

               try{
                developmentData=dbCTRL.getGoalData(appraisalYear,appraiseeId);
               }catch(Exception e){
                e.printStackTrace();
               }
               return developmentData;
    }

    public AppraiseeListDTO getSubmitStatus(int appraiseeId, int appraisalQuarter, int appraisalYear) {
        AppraiseeListDTO getSelectedAppraiseeDetails=null;
        try{
           getSelectedAppraiseeDetails=dbCTRL.getSelectedAppraiseeDetails(appraiseeId,appraisalYear);
        }
        catch(Exception e){
                 logger.error("Exception @ AppraiserDAOImpl @ getSubmitStatus "+e.getMessage());
            }
        return getSelectedAppraiseeDetails;
    }
    
}
