/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dao;

import com.defiance.ideal.travelplan.dto.CommonDto;
import java.util.List;

/**
 *
 * @author 14583
 */
public interface CommonDao {
    public CommonDto getEmployeeDetails(String employee_id);
    public List<CommonDto> getCustomerDetails();
    public List<CommonDto>getBUHApproverList(String buh_id);
    public List<CommonDto> getTravelHotelData(String master_id);
    public List<CommonDto> getTravelTicketData(String master_id);
    public List<CommonDto> getTravelConveyanceData(String master_id);
    public List<CommonDto> getNationalityList();
    public void insertEmpGenericDetails(CommonDto genericDto);
    public CommonDto getEmpGenericDetails(String empId);
      public CommonDto getGenericDetails(String empId);

}
