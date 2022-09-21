package com.defiance.ideal.qpd.appraisee.bo;

/**
 *
 * @author Administrator
 */
import com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO;
import com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO;
import com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO;
import java.util.logging.Level;
//import java.util.logging.Logger;
import org.apache.log4j.Logger;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import java.util.ArrayList;

@ControlImplementation(isTransient=true)
public class AppraiseeBOImpl implements AppraiseeBO {

        private transient final Logger logger = Logger.getLogger(this.getClass().getName());

        @Control
        private AppraiseeDAO controlObj;

        public AppraiseeDetailsDTO authenticateAppraisee(String empNum,int appraiseeQuarter,int appraiseeYear){

            AppraiseeDetailsDTO dataObj=null;
            try{
                dataObj=controlObj.authenticateAppraisee(empNum,appraiseeQuarter,appraiseeYear);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ authenticateAppraisee "+e.getMessage());
            }
            return dataObj;
        }

        public AppraiseeDetailsDTO getAppraiserName(int appraiserId){
            AppraiseeDetailsDTO dataObj=null;
            try{
                dataObj=controlObj.getAppraiserName(appraiserId);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getAppraiserName "+e.getMessage());
            }
            return dataObj;
        }

        public AppraiseeDetailsDTO getReviewerName(int reviewerId){
            AppraiseeDetailsDTO dataObj=null;
            try{
                dataObj=controlObj.getReviewerName(reviewerId);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getReviewerName "+e.getMessage());
            }
            return dataObj;
        }

        public AppraiseeDetailsDTO getAppraiseeSBU(int deptId){
            AppraiseeDetailsDTO dataObj=null;
            try{
                dataObj=controlObj.getAppraiseeSBU(deptId);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getAppraiseeSBU "+e.getMessage());
            }
            return dataObj;
        }
        public AppraiseeDetailsDTO checkAppraiseeComments(int appraiseeId){
            AppraiseeDetailsDTO dataObj=null;
            try{
                dataObj=controlObj.checkAppraiseeComments(appraiseeId);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ checkAppraiseeComments "+e.getMessage());
            }
            return dataObj;
        }

        public MyAppraisalFormDTO[] getQualAttributes(String kraQuality){
            MyAppraisalFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getQualAttributes(kraQuality);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getQualAttributes "+e.getMessage());
            }
            return dataObj;
        }

        public MyAppraisalFormDTO[] getCostAttributes(String kraCost){
            MyAppraisalFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getQualAttributes(kraCost);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getCostAttributes "+e.getMessage());
            }
            return dataObj;
        }

        public MyAppraisalFormDTO[] getCustomerAttributes(String kraCustomer){
            MyAppraisalFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getQualAttributes(kraCustomer);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getCustomerAttributes "+e.getMessage());
            }
            return dataObj;
        }

        public MyAppraisalFormDTO[] getDevlopAttributes(String kraDevlop){
            MyAppraisalFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getQualAttributes(kraDevlop);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getDevlopAttributes "+e.getMessage());
            }
            return dataObj;
        }

        public void insertAppraiseeComments(ArrayList<String> appraiseeComments,String empNum){
                //AppraiseeDetailsDTO dataObj=null;
                try{
                    controlObj.insertAppraiseeComments(appraiseeComments, empNum);
                }
                catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ insertAppraiseeComments "+e.getMessage());
            }
                //return dataObj;
        }

        public void updateAppraiseeComments(ArrayList<String> appraiseeComments,String empNum){
                //AppraiseeDetailsDTO dataObj=null;
                try{
                    controlObj.updateAppraiseeComments(appraiseeComments, empNum);
                }
                catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ updateAppraiseeComments "+e.getMessage());
            }
                //return dataObj;
        }

        public void updateAppraiseeStatus(int appraiseeId,int quarter,int quarterYear){
                //AppraiseeDetailsDTO dataObj=null;
                try{
                    controlObj.updateAppraiseeStatus(appraiseeId,quarter,quarterYear);
                }
                catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ updateAppraiseeStatus "+e.getMessage());
            }
                //return dataObj;
        }

    
    public MyAppraisalFormDTO[] getKraData(int bandId, int appraisalYear, int appraisalQuarter,String appraiseeId,int departmentId) {
          MyAppraisalFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getKraData(bandId,appraisalYear,appraisalQuarter,appraiseeId,departmentId);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getKraData "+e.getMessage());
            }
            return dataObj;
    }

    public void insertOrUpdateAppraiseeData(MyAppraisalFormDTO formData,int appraiseeId){

        try{
            controlObj.insertOrUpdateAppraiseeData(formData,appraiseeId);
        }
        catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ insertOrUpdateAppraiseeData "+e.getMessage());
                 e.printStackTrace();
        }
    }

    public MyAppraisalFormDTO[] getAchievementsData(int appraiseeId, int appraisalYear) {
         MyAppraisalFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getAchievementsData(appraiseeId,appraisalYear);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getAchievementsData "+e.getMessage());
            }
            return dataObj;
    }

    public MyAppraisalFormDTO[] getDevNeedsData(int appraiseeId, int appraisalYear) {
            MyAppraisalFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getDevNeedsData(appraiseeId,appraisalYear);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getDevNeedsData "+e.getMessage());
            }
            return dataObj;
    }

    public MyAppraisalFormDTO[] getGoalData(int appraiseeId, int appraisalYear) {
         MyAppraisalFormDTO[] dataObj=null;
            try{
                dataObj=controlObj.getGoalData(appraiseeId,appraisalYear);
            }
            catch(Exception e){
                 logger.error("Exception @ AppraiseeBOIMPL @ getGoalData "+e.getMessage());
            }
            return dataObj;
    }

//    public int insertOrUpdateAppraiseeData(MyAppraisalFormDTO formData,int appraiseeId){
//        int lastInsertId = 0;
//        try{
//            lastInsertId = controlObj.insertOrUpdateAppraiseeData(formData,appraiseeId);
//        }
//        catch(Exception e){
//                 logger.error("Exception @ AppraiseeBOIMPL @ insertOrUpdateAppraiseeData "+e.getMessage());
//            }
//        return lastInsertId;
//    }


}
