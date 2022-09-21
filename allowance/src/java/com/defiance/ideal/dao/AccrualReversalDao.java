/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dao;

import com.defiance.ideal.dto.AccrualDto;
import com.defiance.ideal.dto.SearchDto;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 16221
 */
public interface AccrualReversalDao {
    public List<SearchDto> getCustomerList(String created_by);
    public List<SearchDto> getProjectList(SearchDto filter_data);
    public List<String> getAccrualNo(String project_id);
    public List<AccrualDto> getEmployeeAccrualDetails(SearchDto objSearch);
    public String getMaxAccrualNo();
    public void submitAccrualReversal(AccrualDto data);
    public String getTimesheetDate(AccrualDto data);
    public void updateAccrualTms(AccrualDto data);
    public void reverseTimesheetAccrual(AccrualDto data);
    public List<AccrualDto> getEmployeeTimesheetDetails(SearchDto filter_data);
    public List<SearchDto> getEmployeeProjects(SearchDto filter_data);
    public List<SearchDto> getEmployeeRole(SearchDto filter_data);
    public void updateTimesheet(SearchDto data);
    public List<SearchDto> getProjectByManager(String manager_id);
    public List<SearchDto> getReporteesList(String manager_id);
    public List<SearchDto> getEmployeeList(String project_id);
    public List<AccrualDto> getTimesheetDetails(SearchDto filter_data);
    public void rejectTimesheet(String timesheet_id);
    public List<SearchDto> getOfficeTimingList();
    public void updateOfficeTiming(SearchDto filterData);
    public List<SearchDto> getEmployessList(String manager_id);
    public void approveOfficeTiming(SearchDto filter_data);
    public List<SearchDto> getSelectedList(String employee_id);
    public SearchDto getEmployeeDetails(SearchDto filter_data);
    public List<SearchDto> getConnectionDetails();
    public SearchDto getoffTimingDetails(String timing_id);
    public List<SearchDto> getAccrualDetails(String year);
    public String updateAccrualClose(SearchDto filter_data);
}
