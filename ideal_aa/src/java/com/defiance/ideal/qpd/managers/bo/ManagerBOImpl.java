 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.managers.bo;

import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import javax.servlet.http.HttpServletRequest;
import com.defiance.ideal.qpd.managers.dto.ManagerDTO;
import com.defiance.ideal.qpd.managers.dao.ManagerDAO;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.log4j.Logger;
/**
 *
 * @author Administrator
 */
@ControlImplementation(isTransient = true)
public class ManagerBOImpl implements ManagerBO{

    private transient final Logger logger = Logger.getLogger(this.getClass().getName());

    @Control
    private  ManagerDAO controlObj;

    public ManagerDTO[] getAppraiseesByReviewer(HttpServletRequest request,String reviewerId, int appraisalQuarter, int appraisalYear) {
         //HttpServletRequest request = null;
         ManagerDTO[] appraiseesbyReviewer=null;
         String selectedAppraiserId=(String)request.getParameter("myappraiserId");
         logger.info("selectedAppraiserId"+selectedAppraiserId);
         try{
         if(selectedAppraiserId!=null && !"".equals(selectedAppraiserId)){
               appraiseesbyReviewer=controlObj.getAppraiseesByReviewerAndAppraiser(selectedAppraiserId,reviewerId,appraisalQuarter,appraisalYear);
         }else{
               appraiseesbyReviewer=controlObj.getAppraiseesByReviewer(reviewerId,appraisalQuarter,appraisalYear);
         }
         
         request.setAttribute("appraiserList", controlObj.getAppraisersByReviewer(reviewerId,appraisalQuarter,appraisalYear));

        }
         catch(Exception e){
             logger.error("Exception at Manager@BOImpl @ getAppraiseesByReviewer "+e.getMessage());
         }
         return appraiseesbyReviewer;
    }

    public ManagerDTO[] getAppraiseesByHr(HttpServletRequest request, String hrId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] appraiseesbyHr=null;
        String selectedNormalizerId=(String)request.getParameter("mynormalizerId");
        String selectedSBUDepartmentId=(String)request.getParameter("appraiseeSBUDepartmentId");
         try{
//             if((selectedNormalizerId!=null && !"".equals(selectedNormalizerId)) && (selectedSBUDepartmentId==null && "".equals(selectedSBUDepartmentId))){
//                appraiseesbyHr=controlObj.getAppraiseesByHrAndNormalizer(hrId,appraisalQuarter,appraisalYear,selectedNormalizerId);
//             }else if((selectedSBUDepartmentId!=null && !"".equals(selectedSBUDepartmentId)) && (selectedNormalizerId==null && "".equals(selectedNormalizerId))){
//                appraiseesbyHr=controlObj.getAppraiseesByHrAndSBU(hrId,appraisalQuarter,appraisalYear,selectedSBUDepartmentId);
//             }else if((selectedNormalizerId!=null && !"".equals(selectedNormalizerId)) && (selectedSBUDepartmentId!=null && !"".equals(selectedSBUDepartmentId))){
//                appraiseesbyHr=controlObj.getAppraiseesByHrAndNormalizerAndSBU(hrId,appraisalQuarter,appraisalYear,selectedNormalizerId,selectedSBUDepartmentId);
//             }else{
//                logger.info("no normalizer selected");
//                appraiseesbyHr=controlObj.getAppraiseesByHr(hrId,appraisalQuarter,appraisalYear);
//             }


             
             String whereCondition = "";

             if(selectedNormalizerId !=null && !"".equals(selectedNormalizerId)){
                 whereCondition += " AND qpd.normalizerId='"+selectedNormalizerId+"'";
             }
             
             if(selectedSBUDepartmentId !=null && !"".equals(selectedSBUDepartmentId)){
                 whereCondition += " AND qpd.departmentId='"+selectedSBUDepartmentId+"'";
             }

                appraiseesbyHr=controlObj.getAppraiseesByHr(hrId,appraisalQuarter,appraisalYear,whereCondition);

              request.setAttribute("normalizerList", controlObj.getNormalizersByHr(hrId,appraisalQuarter,appraisalYear));
              request.setAttribute("organizationalList", controlObj.getCompanyStructureBrHr(hrId,appraisalQuarter,appraisalYear));
         }
         catch(Exception e){
             logger.error("Exception at Manager@BOImpl @ getAppraiseesByHr "+e.getMessage());
         }
        return appraiseesbyHr;
    }

    public void updateReviewerRating(String reviewerId, int appraisalQuarter, int appraisalYear, ManagerDTO formData,String button) {
        try{
             controlObj.updateReviewerRating(reviewerId,appraisalQuarter,appraisalYear,formData,button);
         }
         catch(Exception e){
             logger.error("Exception at Manager@BOImpl @ updateReviewerRating "+e.getMessage());
         }
    }

    public void updateFinalStatus(String hrId, int appraisalQuarter, int appraisalYear, ManagerDTO appraiseesbyHr, String button) {
        try{
             controlObj.updateFinalStatus(hrId,appraisalQuarter,appraisalYear,appraiseesbyHr,button);
         }
         catch(Exception e){
             logger.error("Exception at Manager@BOImpl @ updateFinalStatus "+e.getMessage());
         }
    }

    public ManagerDTO[] getAppraiseesByFinance(HttpServletRequest request, String financeId, int appraisalQuarter, int appraisalYear) {
         ManagerDTO[] appraiseesbyFinance=null;

         try{
             appraiseesbyFinance=controlObj.getAppraiseesByFinance(financeId,appraisalQuarter,appraisalYear);
         }
         catch(Exception e){
             logger.error("Exception at Manager@BOImpl @ getAppraiseesByFinance "+e.getMessage());
         }
        return appraiseesbyFinance;
    }

    public ManagerDTO[] getKraData(int bandId, int appraisalQuarter, int appraisalYear, int appraiseeId,int departmentId) {
            ManagerDTO[] kraDataObj=null;
            kraDataObj=controlObj.getKraData(bandId,appraisalYear,appraisalQuarter,appraiseeId,departmentId);
            return kraDataObj;
    }

    public ManagerDTO[] getAppraiseesByNormalizer(HttpServletRequest request, String normalizerId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] appraiseesbyNormalizer=null;
         String selectedAppraiserId=(String)request.getParameter("myappraiserId");
         String selectedReviewerId=(String)request.getParameter("myreviewerId");
         logger.info("selectedAppraiserId"+selectedAppraiserId);
         try{
//         if((selectedAppraiserId!=null && !"".equals(selectedAppraiserId)) && (selectedReviewerId==null && "".equals(selectedReviewerId))){
//                    logger.info("In appraiser alone");
//               appraiseesbyNormalizer=controlObj.getAppraiseesByNormalizerAndAppraiser(selectedAppraiserId,normalizerId,appraisalQuarter,appraisalYear);
//         }else if((selectedReviewerId!=null && !"".equals(selectedReviewerId)) && (selectedAppraiserId==null && "".equals(selectedAppraiserId))){
//                    logger.info("In Reviewer alone");
//               appraiseesbyNormalizer=controlObj.getAppraiseesByNormalizerAndReviewer(selectedReviewerId,normalizerId,appraisalQuarter,appraisalYear);
//         }else if((selectedAppraiserId!=null && !"".equals(selectedAppraiserId)) && (selectedReviewerId!=null && !"".equals(selectedReviewerId))){
//                    logger.info("In Both");
//               appraiseesbyNormalizer=controlObj.getAppraiseesByNormalizerAndAppraiserReviewer(selectedAppraiserId,selectedReviewerId,normalizerId,appraisalQuarter,appraisalYear);
//         }else {
//                    logger.info("In None");
//               appraiseesbyNormalizer=controlObj.getAppraiseesByNormalizer(normalizerId,appraisalQuarter,appraisalYear);
//         }

             String whereCondition = "";

             if(selectedAppraiserId !=null && !"".equals(selectedAppraiserId)){
                 whereCondition += " AND qpd.appraiserId='"+selectedAppraiserId+"'";
             }
             
             if(selectedReviewerId !=null && !"".equals(selectedReviewerId)){
                 whereCondition += " AND qpd.reviewerId='"+selectedReviewerId+"'";
             }

         appraiseesbyNormalizer = controlObj.getAppraiseesByNormalizer(normalizerId,appraisalQuarter,appraisalYear,whereCondition);


         request.setAttribute("appraiserList", controlObj.getAppraisersByNormalizer(normalizerId,appraisalQuarter,appraisalYear));
         request.setAttribute("reviewerList", controlObj.getReviewersByNormalizer(normalizerId,appraisalQuarter,appraisalYear));

        }
         catch(Exception e){
             logger.error("Exception at Manager@BOImpl @ getAppraiseesByNormalizer "+e.getMessage());
         }
         return appraiseesbyNormalizer;
    }
    
    public ManagerDTO[] getAppraiseesByBuh(HttpServletRequest request, String buhId, int appraisalQuarter, int appraisalYear) {
        ManagerDTO[] appraiseesbyBuh=null;
         String selectedAppraiserId=(String)request.getParameter("myappraiserId");
         String selectedReviewerId=(String)request.getParameter("myreviewerId");
         String selectedNormalizerId=(String)request.getParameter("mynormalizerId");
         logger.info("selectedAppraiserId"+selectedAppraiserId);
         try{
//         if((selectedAppraiserId!=null && !"".equals(selectedAppraiserId)) && (selectedReviewerId==null && "".equals(selectedReviewerId))){
//                    logger.info("In appraiser alone");
//               appraiseesbyNormalizer=controlObj.getAppraiseesByNormalizerAndAppraiser(selectedAppraiserId,normalizerId,appraisalQuarter,appraisalYear);
//         }else if((selectedReviewerId!=null && !"".equals(selectedReviewerId)) && (selectedAppraiserId==null && "".equals(selectedAppraiserId))){
//                    logger.info("In Reviewer alone");
//               appraiseesbyNormalizer=controlObj.getAppraiseesByNormalizerAndReviewer(selectedReviewerId,normalizerId,appraisalQuarter,appraisalYear);
//         }else if((selectedAppraiserId!=null && !"".equals(selectedAppraiserId)) && (selectedReviewerId!=null && !"".equals(selectedReviewerId))){
//                    logger.info("In Both");
//               appraiseesbyNormalizer=controlObj.getAppraiseesByNormalizerAndAppraiserReviewer(selectedAppraiserId,selectedReviewerId,normalizerId,appraisalQuarter,appraisalYear);
//         }else {
//                    logger.info("In None");
//               appraiseesbyNormalizer=controlObj.getAppraiseesByNormalizer(normalizerId,appraisalQuarter,appraisalYear);
//         }

             String whereCondition = "";

             if(selectedAppraiserId !=null && !"".equals(selectedAppraiserId)){
                 whereCondition += " AND qpd.appraiserId='"+selectedAppraiserId+"'";
             }
             
             if(selectedReviewerId !=null && !"".equals(selectedReviewerId)){
                 whereCondition += " AND qpd.reviewerId='"+selectedReviewerId+"'";
             }
             
             if(selectedNormalizerId !=null && !"".equals(selectedNormalizerId)){
                 whereCondition += " AND qpd.normalizerId='"+selectedNormalizerId+"'";
             }

         appraiseesbyBuh = controlObj.getAppraiseesByBuh(buhId,appraisalQuarter,appraisalYear,whereCondition);


         request.setAttribute("appraiserList", controlObj.getAppraisersByBuh(buhId,appraisalQuarter,appraisalYear));
         request.setAttribute("reviewerList", controlObj.getReviewersByBuh(buhId,appraisalQuarter,appraisalYear));
         request.setAttribute("normalizerList", controlObj.getNormalizersByBuh(buhId,appraisalQuarter,appraisalYear));

        }
         catch(Exception e){
             logger.error("Exception at Manager@BOImpl @ getAppraiseesByNormalizer "+e.getMessage());
         }
         return appraiseesbyBuh;
    }

    public void updateNormalizerRating(String normalizerId, int appraisalQuarter, int appraisalYear, ManagerDTO formData, String button) {
        try{
             controlObj.updateNormalizerRating(normalizerId,appraisalQuarter,appraisalYear,formData,button);
         }
         catch(Exception e){
             logger.error("Exception at Manager@BOImpl @ updateNormalizerRating "+e.getMessage());
         }
    }

    public ManagerDTO getEmployeesCount(String reviewerId, int appraisalQuarter, int appraisalYear,String referenceType) {
         ManagerDTO appraiseeCount = null;
         try{
            appraiseeCount = controlObj.getAppraiseeCount(reviewerId, appraisalQuarter,appraisalYear,referenceType);
         }catch(Exception e){
            logger.error("Exception at Manager@BOImpl @ getEmployeesCount "+e.getMessage());
         }
         return appraiseeCount;
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

}
