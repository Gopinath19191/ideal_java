/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.service;

import com.defiance.ideal.dao.FullAllowanceDao;
import com.defiance.ideal.dto.FullAllowanceDto;
import com.defiance.ideal.dto.SearchDto;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author 16221
 */
public class FullAllowanceServiceImpl implements FullAllowanceService{
    
    FullAllowanceDao dao;

    public FullAllowanceDao getDao() {
        return dao;
    }

    public void setDao(FullAllowanceDao dao) {
        this.dao = dao;
    }
        
    @Override
    public String getCutOffDate(){
        String cut_off_date = null;
        cut_off_date = dao.getCutOffDate();
        return cut_off_date;        
    }
    
    public List<FullAllowanceDto> getCustomerList(SearchDto filterData) {
        
        List<FullAllowanceDto> customerList = dao.getCustomerList(filterData);
        return customerList;
    }
            
    public List<FullAllowanceDto> getProjectList(SearchDto filterData) {
        
        List<FullAllowanceDto> projectList = dao.getProjectList(filterData);
        return projectList;
    }
         
    public List<FullAllowanceDto> getSubSbu(SearchDto filterData) {
        
        List<FullAllowanceDto> subSbuList = dao.getSubSbu(filterData);
        return subSbuList;
    }
    
    public List<FullAllowanceDto> getEmployeeDetails(SearchDto filterData) {
        
        List<FullAllowanceDto> employeeList = dao.getEmployeeDetails(filterData);
        List<FullAllowanceDto> shiftAmount = dao.getShiftAmount();
//        ListIterator<FullAllowanceDto> itr = employeeList.listIterator();
        for(int i=0;i<employeeList.size();i++){
            double general=0, shift_one=0,shift_two=0,shift_three=0,holiday=0,general_value,shift_one_value,shift_two_value,shift_three_value,holiday_value,maximum_hardship_amount;
            general = Double.parseDouble(employeeList.get(i).getCust_general());
            shift_one = Double.parseDouble(employeeList.get(i).getCompany_shift_I()) + Double.parseDouble(employeeList.get(i).getCust_shift_I());
            shift_two = Double.parseDouble(employeeList.get(i).getCompany_shift_II())+ Double.parseDouble(employeeList.get(i).getCust_shift_II());
            shift_three = Double.parseDouble(employeeList.get(i).getCompany_shift_III())+ Double.parseDouble(employeeList.get(i).getCust_shift_III());
            holiday = Double.parseDouble(employeeList.get(i).getWeekend_holidays_entered());
            
            general_value = Double.parseDouble(shiftAmount.get(0).getAmount());
            shift_one_value = Double.parseDouble(shiftAmount.get(1).getAmount());
            shift_two_value = Double.parseDouble(shiftAmount.get(2).getAmount());
            shift_three_value = Double.parseDouble(shiftAmount.get(3).getAmount());
            holiday_value = Double.parseDouble(shiftAmount.get(5).getAmount());
            maximum_hardship_amount = Double.parseDouble(shiftAmount.get(6).getAmount());
            double hardship_amount, shift_one_amount, shift_two_amount, shift_three_amount, shift_amount,transport_amount, holiday_amount, total_amount;
            hardship_amount = general*general_value;
            shift_one_amount = shift_one*shift_one_value;
            shift_two_amount = shift_two*shift_two_value;
            shift_three_amount = shift_three*shift_three_value;
            shift_amount = shift_one_amount+shift_two_amount+shift_three_amount;
            transport_amount=0;
            holiday_amount = holiday*holiday_value;
            if(hardship_amount>maximum_hardship_amount){
                hardship_amount = maximum_hardship_amount;
            }
            total_amount = hardship_amount+shift_amount+holiday_amount+transport_amount;
            employeeList.get(i).setHardship_amount(Double.toString(hardship_amount));
            employeeList.get(i).setShift_amount(Double.toString(shift_amount));
            employeeList.get(i).setHoliday_amount(Double.toString(holiday_amount));
            employeeList.get(i).setTransport_amount(Double.toString(transport_amount));
            employeeList.get(i).setTotal_amount(Double.toString(total_amount));
            employeeList.get(i).setOne_way("0");
            employeeList.get(i).setTwo_way("0");
            employeeList.get(i).setNo_cab("0");
        }
        return employeeList;
    }
            
    public List<FullAllowanceDto> getShiftDetails() {
        
        List<FullAllowanceDto> shiftList = dao.getShiftAmount();
        return shiftList;
    }
            
    public void insertAllowanceDetails(FullAllowanceDto filterData) {
        
        dao.insertAllowanceDetails(filterData);
    }
    
    public List<FullAllowanceDto> getProcessedList(SearchDto filterData) {
        
        List<FullAllowanceDto> processedList = dao.getProcessedList(filterData);
        return processedList;
    }
    
    public List<FullAllowanceDto> getEmployeeAllowanceReport(SearchDto filterData) {
        
        List<FullAllowanceDto> employeeAllowanceList = dao.getEmployeeAllowanceReport(filterData);
        return employeeAllowanceList;
    }
    
    public List<FullAllowanceDto> getSbuList() {
        
        List<FullAllowanceDto> sbuList = dao.getSbuList();
        return sbuList;
    }
    
    public String getProjectName(int projectId){
    
        String projectName = dao.getProjectName(projectId);
        return projectName;
    }
            
}
