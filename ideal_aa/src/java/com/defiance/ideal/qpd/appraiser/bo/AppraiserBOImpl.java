package com.defiance.ideal.qpd.appraiser.bo;

/**
 *
 * @author Administrator
 */

import com.defiance.ideal.qpd.appraiser.dao.AppraiserDAO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserRatingFormDTO;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.log4j.Logger;

@ControlImplementation(isTransient=true)
public class AppraiserBOImpl implements AppraiserBO {

        private transient final Logger logger = Logger.getLogger(this.getClass().getName());

        @Control
        private AppraiserDAO controlObj;

     public AppraiseeListDTO[] getAppraiseeList(int employeeId, int appraisalYear, int appraisalQuarter) {
            AppraiseeListDTO[] dataObj=null;
            try{
                dataObj=controlObj.getAppraiseeList(employeeId,appraisalYear,appraisalQuarter);
            }
            catch(Exception e){
                 logger.error("Exception @ Appraiser BOIMPL @ getAppraiseeList "+e.getMessage());
            }
            return dataObj;
    }

    public AppraiserFormDTO[] getKraData(int bandIdForm, int appraisalQuarterForm, int appraisalYearForm, int appraiseeIdForm,int departmentId) {
         AppraiserFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getKraData(bandIdForm,appraisalQuarterForm,appraisalYearForm,appraiseeIdForm,departmentId);
            }
            catch(Exception e){
                 logger.error("Exception @ Appraiser BOIMPL @ getKraData "+e.getMessage());
            }
            return dataObj;
    }

    public void updateAppraiseeData(AppraiserRatingFormDTO formData) {
            try{
             controlObj.updateAppraiseeData(formData);
            }
            catch(Exception e){
                 logger.error("Exception @ Appraiser BOIMPL @ updateAppraiseeData "+e.getMessage());
            }
    }

    public void updateAppraiseeStatus(String button,int appraiseeId, int appraiseeQuarter, int appraiseeYear,int sendBackStatus,String reasonAppraiser) {
            try{
                    controlObj.updateAppraiseeStatus(button,appraiseeId,appraiseeQuarter,appraiseeYear,sendBackStatus,reasonAppraiser);
                }
                catch(Exception e){
                 logger.error("Exception @ Appraiser BOIMPL @ updateAppraiseeStatus "+e.getMessage());
            }
    }

    public AppraiseeListDTO getSubmitStatus(int appraiseeId, int appraisalQuarter, int appraisalYear) {
        AppraiseeListDTO dataObj=null;
        try{
           dataObj=controlObj.getSubmitStatus(appraiseeId,appraisalQuarter,appraisalYear);
        }
        catch(Exception e){
                 logger.error("Exception @ Appraiser BOIMPL @ getSubmitStatus "+e.getMessage());
            }

        return dataObj;
    }

    public AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId) {
          AppraiserFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getAchievementsData(appraisalYear,appraiseeId);
            }
            catch(Exception e){
                 logger.error("Exception @ Appraiser BOIMPL @ getAchievementsData "+e.getMessage());
            }
            return dataObj;
    }

    public AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId) {
         AppraiserFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getDevelopmentData(appraisalYear,appraiseeId);
            }
            catch(Exception e){
                 logger.error("Exception @ Appraiser BOIMPL @ getAchievementsData "+e.getMessage());
            }
            return dataObj;
    }

    public AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId) {
         AppraiserFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getGoalData(appraisalYear,appraiseeId);
            }
            catch(Exception e){
                 logger.error("Exception @ Appraiser BOIMPL @ getAchievementsData "+e.getMessage());
            }
            return dataObj;
    }

    public AppraiserRatingFormDTO[] getLastFourQPDRating(int appraiseeId) {
        AppraiserRatingFormDTO[] qpdRating = null;
        try {
            qpdRating = controlObj.getLastFourQPDRating(appraiseeId);
        } catch (Exception e) {
            logger.error("Exception @ Appraiser BOIMPL @ getLastThreeQPDRating "+e.getMessage());
        }
        return qpdRating;
    }

}
