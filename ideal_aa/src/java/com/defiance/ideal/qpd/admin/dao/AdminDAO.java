/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.admin.dao;
import com.defiance.ideal.qpd.admin.dto.AdminDTO;
import org.apache.beehive.controls.api.bean.ControlInterface;

/**
 *
 * @author Hariharan.C
 */
@ControlInterface
public interface AdminDAO {

    public AdminDTO[] getStructureDetails();

    public void triggerAppraisal(AdminDTO formData,int empId);

    public AdminDTO[] filterEmployeeData(String appraiserData,String concatenatedBand,String dojCheck, String concatenatedStatus, String concatenatedStructure, int appraisalYear, int appraisalPeriod);
    

    public AdminDTO[] getEmployeeName(String searchString);

    public AdminDTO[] filterEmployeeDataChangeReq(String appraiserData,String concatentedBand,String dojCheck, String concatenatedStatus, String concatenatedStructure, int appraisalYear, int appraisalPeriod);
    

    public void updateAppraisal(AdminDTO formData, int empId);

    public AdminDTO[] getBandData();



}