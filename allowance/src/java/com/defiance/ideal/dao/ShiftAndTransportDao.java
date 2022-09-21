/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dao;

import com.defiance.ideal.dto.SearchDto;
import com.defiance.ideal.dto.ShiftAndTransportDto;
import java.util.List;

/**
 *
 * @author 8000247
 */
public interface ShiftAndTransportDao {
    
    public List<ShiftAndTransportDto> getCustomerList(SearchDto filterData);
    public List<ShiftAndTransportDto> getProjectList(SearchDto filterData);
    public List getShiftDetails();
    public List<ShiftAndTransportDto> getEmployeeDetails(SearchDto filterData);
    public void insertDetails(ShiftAndTransportDto filterData);
    public List<ShiftAndTransportDto> getSavedDetails(SearchDto filterData);
    public void updateDetails(ShiftAndTransportDto filterData);
    public ShiftAndTransportDto submittedCount(SearchDto filterData);
    public List<ShiftAndTransportDto> getSBU_List();
    public List<ShiftAndTransportDto> getSBU_SUB_List(SearchDto filterData);

    public ShiftAndTransportDto detailsCount(SearchDto filterData);
}
