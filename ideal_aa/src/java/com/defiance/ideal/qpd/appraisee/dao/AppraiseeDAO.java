/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraisee.dao;

/**
 *
 * @author Administrator
 */

import com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO;
import com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO;
import org.apache.beehive.controls.api.bean.ControlInterface;
import java.util.ArrayList;

@ControlInterface
public interface AppraiseeDAO {

    public AppraiseeDetailsDTO authenticateAppraisee(String empNum,int appraiseeQuarter,int appraiseeYear) throws Exception;
    public AppraiseeDetailsDTO getAppraiserName(int appraiserId);
    public AppraiseeDetailsDTO getReviewerName(int reviewerId);
    public AppraiseeDetailsDTO getAppraiseeSBU(int deptId);

    public AppraiseeDetailsDTO checkAppraiseeComments(int appraiseeId);

    public MyAppraisalFormDTO[] getQualAttributes(String kraQuality);
    public MyAppraisalFormDTO[] getCostAttributes(String kraCost);
    public MyAppraisalFormDTO[] getCustomerAttributes(String kraCustomer);
    public MyAppraisalFormDTO[] getDevlopAttributes(String kraDevlop);

  public void insertOrUpdateAppraiseeData(MyAppraisalFormDTO formData,int appraiseeId);
//    public int insertOrUpdateAppraiseeData(MyAppraisalFormDTO formData,int appraiseeId);
    
    public void updateAppraiseeStatus(int appraiseeId,int quarter,int quarterYear);

    public void insertAppraiseeComments(ArrayList<String> appraiseeComments,String empNum);
    public void updateAppraiseeComments(ArrayList<String> appraiseeComments,String empNum);
    
    public MyAppraisalFormDTO[] getKraData(int bandId, int appraisalYear, int appraisalQuarter,String appraiseeId,int departmentId);
    
    public MyAppraisalFormDTO[] getAchievementsData(int appraiseeId, int appraisalYear);
    public MyAppraisalFormDTO[] getDevNeedsData(int appraiseeId, int appraisalYear);
    public MyAppraisalFormDTO[] getGoalData(int appraiseeId, int appraisalYear);

}
