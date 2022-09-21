/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.managers.bo;
import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import com.defiance.ideal.qpd.managers.dto.ManagerDTO;
import org.apache.beehive.controls.api.bean.ControlInterface;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author Administrator
 */
@ControlInterface
public interface ManagerBO {
    
    public ManagerDTO[] getAppraiseesByReviewer(HttpServletRequest request,String reviewerId, int appraisalQuarter, int appraisalYear);

    public ManagerDTO[] getAppraiseesByHr(HttpServletRequest request, String hrId, int appraisalQuarter, int appraisalYear);

    public void updateReviewerRating(String reviewerId, int appraisalQuarter, int appraisalYear, ManagerDTO formData,String button);

    public void updateFinalStatus(String hrId, int appraisalQuarter, int appraisalYear, ManagerDTO appraiseesbyHr, String button);

    public ManagerDTO[] getAppraiseesByFinance(HttpServletRequest request, String financeId, int appraisalQuarter, int appraisalYear);

    public ManagerDTO[] getKraData(int bandId, int appraisalQuarter, int appraisalYear, int appraiseeId,int departmentId);

    public ManagerDTO[] getAppraiseesByNormalizer(HttpServletRequest request, String normalizerId, int appraisalQuarter, int appraisalYear);
    
    public ManagerDTO[] getAppraiseesByBuh(HttpServletRequest request, String buhId, int appraisalQuarter, int appraisalYear);

    public void updateNormalizerRating(String normalizerId, int appraisalQuarter, int appraisalYear, ManagerDTO formData, String button);

    public ManagerDTO getEmployeesCount(String reviewerId, int appraisalQuarter, int appraisalYear,String referenceType);

    public AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId);

    public AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId);

    public AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId);

    public AppraiseeListDTO getSubmitStatus(int appraiseeId, int appraisalQuarter, int appraisalYear);

}
