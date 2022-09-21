/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.managers.dao;

import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import com.defiance.ideal.qpd.managers.dto.ManagerDTO;
import org.apache.beehive.controls.api.bean.ControlInterface;
/**
 *
 * @author Administrator
 */
@ControlInterface
public interface ManagerDAO {

    public ManagerDTO[] getAppraiseesByReviewer(String reviewerId, int appraisalQuarter, int appraisalYear);

    public ManagerDTO[] getAppraisersByReviewer(String reviewerId, int appraisalQuarter, int appraisalYear);

    public ManagerDTO[] getAppraiseesByReviewerAndAppraiser(String selectedAppraiserId, String reviewerId, int appraisalQuarter, int appraisalYear);

    public ManagerDTO[] getAppraiseesByHr(String hrId, int appraisalQuarter, int appraisalYear);
    public ManagerDTO[] getAppraiseesByHr(String hrId, int appraisalQuarter, int appraisalYear,String whereCondition);

    public void updateReviewerRating(String reviewerId, int appraisalQuarter, int appraisalYear, ManagerDTO formData,String button);

    public void updateFinalStatus(String hrId, int appraisalQuarter, int appraisalYear, ManagerDTO appraiseesbyHr, String button);

    public ManagerDTO[] getAppraiseesByFinance(String financeId, int appraisalQuarter, int appraisalYear);

    public ManagerDTO[] getKraData(int bandId, int appraisalYear, int appraisalQuarter, int appraiseeId,int departmentId);

    public ManagerDTO[] getAppraiseesByNormalizer(String normalizerId, int appraisalQuarter, int appraisalYear);
    public ManagerDTO[] getAppraiseesByNormalizer(String normalizerId, int appraisalQuarter, int appraisalYear,String whereCondition);
    public ManagerDTO[] getAppraiseesByBuh(String buhId, int appraisalQuarter, int appraisalYear,String whereCondition);

    public ManagerDTO[] getAppraiseesByNormalizerAndAppraiser(String selectedAppraiserId, String normalizerId, int appraisalQuarter, int appraisalYear);

    public Object getAppraisersByNormalizer(String normalizerId, int appraisalQuarter, int appraisalYear);

    public Object getReviewersByNormalizer(String normalizerId, int appraisalQuarter, int appraisalYear);
    
    public Object getAppraisersByBuh(String buhId, int appraisalQuarter, int appraisalYear);

    public Object getNormalizersByBuh(String buhId, int appraisalQuarter, int appraisalYear);
    
    public Object getReviewersByBuh(String buhId, int appraisalQuarter, int appraisalYear);

    public ManagerDTO[] getAppraiseesByNormalizerAndReviewer(String selectedReviewerId, String normalizerId, int appraisalQuarter, int appraisalYear);

    public ManagerDTO[] getAppraiseesByNormalizerAndAppraiserReviewer(String selectedAppraiserId, String selectedReviewerId, String normalizerId, int appraisalQuarter, int appraisalYear);

    public void updateNormalizerRating(String normalizerId, int appraisalQuarter, int appraisalYear, ManagerDTO formData, String button);

    public ManagerDTO[] getAppraiseesByHrAndNormalizer(String hrId, int appraisalQuarter, int appraisalYear, String selectedNormalizerId);

    public Object getNormalizersByHr(String hrId, int appraisalQuarter, int appraisalYear);

    public Object getCompanyStructureBrHr(String hrId, int appraisalQuarter, int appraisalYear);

    public ManagerDTO[] getAppraiseesByHrAndSBU(String hrId, int appraisalQuarter, int appraisalYear, String selectedSBUDepartmentId);

    public ManagerDTO[] getAppraiseesByHrAndNormalizerAndSBU(String hrId, int appraisalQuarter, int appraisalYear, String selectedNormalizerId, String selectedSBUDepartmentId);

    public ManagerDTO getAppraiseeCount(String reviewerId, int appraisalQuarter, int appraisalYear,String referenceType);

    public AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId);

    public AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId);

    public AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId);

    public AppraiseeListDTO getSubmitStatus(int appraiseeId, int appraisalQuarter, int appraisalYear);

}
