/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.service;

import com.defiance.ideal.dto.SearchDto;
import com.defiance.ideal.dto.FullAllowanceDto;
import java.util.List;
/**
 *
 * @author 16221
 */
public interface FullAllowanceService {
    
    public String getCutOffDate();
    public List<FullAllowanceDto> getCustomerList(SearchDto filterData);
    public List<FullAllowanceDto> getProjectList(SearchDto filterData);
    public List<FullAllowanceDto> getSubSbu(SearchDto filterData);
    public List<FullAllowanceDto> getEmployeeDetails(SearchDto filterData);
    public List<FullAllowanceDto> getShiftDetails();
    public void insertAllowanceDetails(FullAllowanceDto filterData);
    public List<FullAllowanceDto> getProcessedList(SearchDto filterData);
    public List<FullAllowanceDto> getEmployeeAllowanceReport(SearchDto filterData);
    public List<FullAllowanceDto> getSbuList();
    public String getProjectName(int projectId);
}
