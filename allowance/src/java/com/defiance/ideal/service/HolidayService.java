/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.service;

import com.defiance.ideal.dto.HolidayDto;
import com.defiance.ideal.dto.DetailsDto;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public interface HolidayService {

    public List getProjectList(HolidayDto formData);

    public List getHolidayList(HolidayDto formData);

    public void updateHolidayAllowance(HolidayDto formData);

    public List getAllowanceDetails(HolidayDto formData);

    public String getRecordCount(HolidayDto formData);

    public Object getRequestData(String allowanceId);

    //For RM Approval
    public List getRMApprovalDetails(HolidayDto formData);
    public List<HolidayDto> getEmployeeList(String filterData);
    public List<HolidayDto> getProjectName(HolidayDto formData);
//    public void updatePmStatus(HolidayDto formData);
    public String getPmRecordCount(HolidayDto formData);
    public String getPMTotalRecordCount(HolidayDto formData);
   public List getEmployeeSearchList(String val);
   public String getEmployeeName(String val);
   public List getTotalAllowanceDetails(HolidayDto formData);
   public String getTotalRecordCount(HolidayDto formData);
   public List getWorkedDaysList(HolidayDto formData);
   public List<HolidayDto> getConfigValues(String parentId);
 

}
