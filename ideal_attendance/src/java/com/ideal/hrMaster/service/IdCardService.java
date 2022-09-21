/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.service;

import com.ideal.hrMaster.dto.EmployeeDto;
import com.ideal.hrMaster.dto.IdCardDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author 16221
 */
public interface IdCardService {
    public List<EmployeeDto> getEmployeeList();
    public IdCardDto getEmployeeDetails(String employee_id);
    public void updateEmployeeDetails(IdCardDto employee_details);
    public void updateFileDetails(HashMap details);
    public ArrayList getBloodGroup();
    public ArrayList<IdCardDto> getMailDetails();
    public String getFacilityMailDetails(String id);
    public String getEmployeePersonalMailId(String id);
    public void updateDwnloadDetails(IdCardDto employee_details);
    public IdCardDto getEmployeeBonafiedDetails(String employee_id);
    public IdCardDto getEmployeeDetailsForDeputation(String employee_id);
    public IdCardDto getEmployeeMailId(String employee_id);
    public IdCardDto getEmployeePersonalDetails(String employee_id);
    public List<IdCardDto> getReport(IdCardDto type);
    public List<IdCardDto> getReportTypes();
    public List<IdCardDto> getCustomerDetails();
    public List<IdCardDto> getCustomerHrName();
    public List<IdCardDto> getCustomerDepartments();
    public List<IdCardDto> getHtRepresentative();
    public List<IdCardDto> getCustomerLocations();
    public List<EmployeeDto> getDcEmployeeList(String type);
    public IdCardDto getDcEmployeeDetails(String employee_id);
    public void updateContractDate(String employee_id, String to_date);
    public List<EmployeeDto> getActiveEmployeeList();
}
