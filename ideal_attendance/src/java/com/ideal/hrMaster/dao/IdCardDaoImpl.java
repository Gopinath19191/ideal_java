/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dao;

import com.ideal.hrMaster.dto.EmployeeDto;
import com.ideal.hrMaster.dto.IdCardDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16221
 */
public class IdCardDaoImpl extends SqlMapClientDaoSupport implements IdCardDao {

    @Override
    public List<EmployeeDto>  getEmployeeList() {
        List<EmployeeDto> employee_list = null;
        try{
            employee_list = getSqlMapClientTemplate().queryForList("IdCard.getEmployeeList");
        }catch(Exception e){
            System.out.println(e);
        }
        return employee_list;
    }
    
    public IdCardDto getEmployeeDetails(String employee_id){
        IdCardDto employee_details = null;
        try{
            employee_details = (IdCardDto)getSqlMapClientTemplate().queryForObject("IdCard.getEmployeeDetails",employee_id);
        }catch(Exception e){
            System.out.println(e);
        }
        return employee_details;    
    }
    
    public void updateEmployeeDetails(IdCardDto employee_details){
        try{
            getSqlMapClientTemplate().insert("IdCard.updateEmployeeDetails",employee_details);
            if(!employee_details.getConfigKey().equals("") && !employee_details.getConfigKey().equals(null)){
                getSqlMapClientTemplate().insert("IdCard.updateEmergencyDetails",employee_details);
            }else{
                getSqlMapClientTemplate().insert("IdCard.insertEmergencyDetails",employee_details);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    public void updateFileDetails(HashMap details){
        try{
            getSqlMapClientTemplate().delete("IdCard.deleteFileDetails", details);
            String lastInsertId = (String) getSqlMapClientTemplate().insert("IdCard.insertFileDetails", details);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public ArrayList getBloodGroup(){
        ArrayList blood_group = null;
        try{
            blood_group = (ArrayList)getSqlMapClientTemplate().queryForList("IdCard.getBloodGroup");
        }catch(Exception e){
            System.out.println(e);
        }
        return blood_group;
    }
    
    public ArrayList<IdCardDto> getMailDetails(){
        ArrayList<IdCardDto> mail_details = null;
        try{
            mail_details = (ArrayList<IdCardDto>) getSqlMapClientTemplate().queryForList("IdCard.getConfigValueData","10");
        }catch(Exception e){
            System.out.println(e);
        }
        return mail_details;
    }
    
    public String getFacilityMailDetails(String id){
        String mail_details = null;
        try{
            mail_details = (String) getSqlMapClientTemplate().queryForObject("IdCard.getFacilityMailDetails",id);
        }catch(Exception e){
            System.out.println(e);
        }
        return mail_details;
    }
    
    public String getEmployeePersonalMailId(String id){
        String mail_details = null;
        try{
            mail_details = (String) getSqlMapClientTemplate().queryForObject("IdCard.getEmployeePersonalMailId",id);
        }catch(Exception e){
            System.out.println(e);
        }
        return mail_details;
    }
    public void updateDwnloadDetails(IdCardDto employee_details){
        try{
            getSqlMapClientTemplate().insert("IdCard.insertCertificateIssuedDetails",employee_details);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public IdCardDto getEmployeeBonafiedDetails(String employee_id){
        IdCardDto employee_details = null;
        try{
            employee_details = (IdCardDto)getSqlMapClientTemplate().queryForObject("IdCard.getEmployeeBonafiedDetails",employee_id);
        }catch(Exception e){
            System.out.println(e);
        }
        return employee_details; 
    }
    public IdCardDto getEmployeeMailId(String employee_id){
        IdCardDto employee_details = null;
        try{
            employee_details = (IdCardDto)getSqlMapClientTemplate().queryForObject("IdCard.getEmployeeMailId",employee_id);
        }catch(Exception e){
            System.out.println(e);
        }
        return employee_details; 
    }
    public IdCardDto getEmployeePersonalDetails(String employee_id){
        IdCardDto employee_details = null;
        try{
            employee_details = (IdCardDto)getSqlMapClientTemplate().queryForObject("IdCard.getEmployeePersonalDetails",employee_id);
        }catch(Exception e){
            System.out.println(e);
        }
        return employee_details; 
    }
    public List<IdCardDto> getReport(IdCardDto type){
        List<IdCardDto> report_list = null;
        try{
            report_list = (List<IdCardDto>) getSqlMapClientTemplate().queryForList("IdCard.getReportList",type);
        }catch(Exception e){
            System.out.println(e);
        }
        return report_list;
    }
    public List<IdCardDto> getReportTypes(){
        List<IdCardDto> report_type = null;
        try{
            report_type = (List<IdCardDto>) getSqlMapClientTemplate().queryForList("IdCard.getReportType");
        }catch(Exception e){
            System.out.println(e);
        }
        return report_type;
    }
    public IdCardDto getEmployeeDetailsForDeputation(String employee_id){
        IdCardDto employee_details = null;
        try{
            employee_details = (IdCardDto)getSqlMapClientTemplate().queryForObject("IdCard.getEmployeeDetailsForDeputation",employee_id);
        }catch(Exception e){
            System.out.println(e);
        }
        return employee_details; 
    }
    @Override
    public List<IdCardDto> getCustomerDetails(){
        List<IdCardDto> customer_list = null;
        try{
            customer_list = (List<IdCardDto>) getSqlMapClientTemplate().queryForList("IdCard.getCustomerDetails");
        }catch(Exception e){
            System.out.println(e);
        }
        return customer_list;
    }
    
    @Override
    public List<IdCardDto> getCustomerHrName(){
        List<IdCardDto> customer_name = null;
        try{
            customer_name = (List<IdCardDto>) getSqlMapClientTemplate().queryForList("IdCard.getCustomerHrName");
        }catch(Exception e){
            System.out.println(e);
        }
        return customer_name;
    }
    
    @Override
    public List<IdCardDto> getCustomerDepartments(){
        List<IdCardDto> departments = null;
        try{
            departments = (List<IdCardDto>) getSqlMapClientTemplate().queryForList("IdCard.getCustomerDepartments");
        }catch(Exception e){
            System.out.println(e);
        }
        return departments;
    }
    
    @Override
    public List<IdCardDto> getHtRepresentative(){
        List<IdCardDto> ht_representative = null;
        try{
            ht_representative = (List<IdCardDto>) getSqlMapClientTemplate().queryForList("IdCard.getHtRepresentative");
        }catch(Exception e){
            System.out.println(e);
        }
        return ht_representative;
    }
    
    @Override
    public List<IdCardDto> getCustomerLocations(){
        List<IdCardDto> customer_locations = null;
        try{
            customer_locations = (List<IdCardDto>) getSqlMapClientTemplate().queryForList("IdCard.getcustomerLocations");
        }catch(Exception e){
            System.out.println(e);
        }
        return customer_locations;
    }
    
    @Override
    public List<EmployeeDto> getDcEmployeeList(String type){
        List<EmployeeDto> employee_list = null;
        String value = "d";
        if(type.equals("e")){
            value="'d'";
        }else{
            value="'r','t','b','q','o','y'";
        }
        try{
            employee_list = getSqlMapClientTemplate().queryForList("IdCard.getDCEmployeeList", value);
        }catch(Exception e){
            System.out.println(e);
        }
        return employee_list;
    }
    
    @Override
    public IdCardDto getDcEmployeeDetails(String employee_id){
        IdCardDto employee_details = null;
        try{
            employee_details = (IdCardDto)getSqlMapClientTemplate().queryForObject("IdCard.getDcEmployeeDetails",employee_id);
        }catch(Exception e){
            System.out.println(e);
        }
        return employee_details;
    }
    
    public void updateContractDate(String employee_id, String to_date){
        IdCardDto det = new IdCardDto();
        det.setEmployee_id(employee_id);
        det.setToDate(to_date);
        try{
            getSqlMapClientTemplate().insert("IdCard.updateContractDate",det);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public List<EmployeeDto> getActiveEmployeeList(){
        List<EmployeeDto> employee_list = null;
        try{
            employee_list = getSqlMapClientTemplate().queryForList("IdCard.getActiveEmployeeList");
        }catch(Exception e){
            System.out.println(e);
        }
        return employee_list;
    }
}
