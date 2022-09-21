/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraiser.bo;

/**
 *
 * @author Administrator
 */
import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserRatingFormDTO;
import org.apache.beehive.controls.api.bean.ControlInterface;

@ControlInterface
public interface AppraiserBO {

    public AppraiseeListDTO[] getAppraiseeList(int employeeId, int appraisalYear, int appraisalQuarter);
    public AppraiserFormDTO[] getKraData(int bandIdForm, int appraisalQuarterForm, int appraisalYearForm, int appraiseeIdForm,int departmentId);
    public void updateAppraiseeData(AppraiserRatingFormDTO formData);
    public void updateAppraiseeStatus(String button,int appraiseeId, int appraiseeQuarter, int appraiseeYear,int sendBackStatus,String reasonAppraiser);

    public AppraiseeListDTO getSubmitStatus(int appraiseeId, int appraisalQuarter, int appraisalYear);

    public AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId);

    public AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId);

    public AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId);

    public AppraiserRatingFormDTO[] getLastFourQPDRating(int appraiseeId);

}
