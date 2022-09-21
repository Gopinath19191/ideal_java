/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.service;

import com.ideal.hrMaster.dao.IdCardDao;
import com.ideal.hrMaster.dto.EmployeeDto;
import com.ideal.hrMaster.dto.IdCardDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16221
 */
public class IdCardServiceImpl extends MultiActionController implements IdCardService{

    IdCardDao dao;

    public IdCardDao getDao() {
        return dao;
    }

    public void setDao(IdCardDao dao) {
        this.dao = dao;
    }
    
    @Override
    public List<EmployeeDto> getEmployeeList() {
        List<EmployeeDto> employee_list = dao.getEmployeeList();
        return employee_list;
    }
    
    public IdCardDto getEmployeeDetails(String employee_id){
        IdCardDto employee_details = dao.getEmployeeDetails(employee_id);
        return employee_details;
    }
    
    public void updateEmployeeDetails(IdCardDto employee_details){
        dao.updateEmployeeDetails(employee_details);
    }
    public void updateFileDetails(HashMap details){
        dao.updateFileDetails(details);
    }
    
    public ArrayList getBloodGroup(){
        ArrayList blood_group = dao.getBloodGroup();
        return blood_group;
    }
    
    public ArrayList<IdCardDto> getMailDetails(){
        ArrayList<IdCardDto> mail_details = dao.getMailDetails();
        return mail_details;
    }
    
    public String getFacilityMailDetails(String id){
        String mail_details = dao.getFacilityMailDetails(id);
        return mail_details;
    }
    public String getEmployeePersonalMailId(String id){
        String mail_details = dao.getEmployeePersonalMailId(id);
        return mail_details;
    }
    public void updateDwnloadDetails(IdCardDto employee_details){
        dao.updateDwnloadDetails(employee_details);
    }
    public IdCardDto getEmployeeBonafiedDetails(String employee_id){
        IdCardDto employee_details = dao.getEmployeeBonafiedDetails(employee_id);
        return employee_details;
    }
    public IdCardDto getEmployeeDetailsForDeputation(String employee_id){
        IdCardDto employee_details = dao.getEmployeeDetailsForDeputation(employee_id);
        return employee_details;
    }
    public IdCardDto getEmployeeMailId(String employee_id){
        IdCardDto employee_details = dao.getEmployeeMailId(employee_id);
        return employee_details;
    }
    public IdCardDto getEmployeePersonalDetails(String employee_id){
        IdCardDto employee_details = dao.getEmployeePersonalDetails(employee_id);
        return employee_details;
    }
    public List<IdCardDto> getReport(IdCardDto type){
        List<IdCardDto> report_list = dao.getReport(type);
        return report_list;
    }
    public List<IdCardDto> getReportTypes(){
        List<IdCardDto> report_type = dao.getReportTypes();
        return report_type;
    }
    public List<IdCardDto> getCustomerDetails(){
        List<IdCardDto> customer_list = dao.getCustomerDetails();
        return customer_list;
    }
    public List<IdCardDto> getCustomerHrName(){
        List<IdCardDto> customer_name = dao.getCustomerHrName();
        return customer_name;
    }
    public List<IdCardDto> getCustomerDepartments(){
        List<IdCardDto> departments = dao.getCustomerDepartments();
        return departments;
    }
    public List<IdCardDto> getHtRepresentative(){
        List<IdCardDto> ht_representative = dao.getHtRepresentative();
        return ht_representative;
    }
    public List<IdCardDto> getCustomerLocations(){
        List<IdCardDto> customer_locations = dao.getCustomerLocations();
        return customer_locations;
    }
    public List<EmployeeDto> getDcEmployeeList(String type){
        List<EmployeeDto> employee_list = dao.getDcEmployeeList(type);
        return employee_list;
    }
    public IdCardDto getDcEmployeeDetails(String employee_id){
        IdCardDto employee_details = dao.getDcEmployeeDetails(employee_id);
        return employee_details;
    }
    public void updateContractDate(String employee_id, String to_date){
        dao.updateContractDate(employee_id, to_date);
    }
    public List<EmployeeDto> getActiveEmployeeList(){
        List<EmployeeDto> employee_list = dao.getActiveEmployeeList();
        return employee_list;
    }
}
