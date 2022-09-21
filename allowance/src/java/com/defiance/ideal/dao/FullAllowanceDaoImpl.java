/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.defiance.ideal.dto.FullAllowanceDto;
import com.defiance.ideal.dto.SearchDto;
import com.defiance.ideal.util.MailMessages;
import com.defiance.ideal.util.SendMail;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 16221
 */
public class FullAllowanceDaoImpl  extends SqlMapClientDaoSupport implements FullAllowanceDao{
    public String getCutOffDate(){
        String cut_off_date = null;
        try {
            cut_off_date = (String) getSqlMapClientTemplate().queryForObject("allowanceMap.getCutOffDate",758);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cut_off_date;
    }
    
    public List<FullAllowanceDto> getCustomerList(SearchDto filterData){
        List<FullAllowanceDto> customerList = null;
        try{
            customerList = getSqlMapClientTemplate().queryForList("allowanceMap.getCustomerList",filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return customerList;
    }
            
    public List<FullAllowanceDto> getProjectList(SearchDto filterData){
        List<FullAllowanceDto> projectList = null;
        try{
            projectList = getSqlMapClientTemplate().queryForList("allowanceMap.getProjectList",filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return projectList;
    }
    
    public List<FullAllowanceDto> getSubSbu(SearchDto filterData){
        List<FullAllowanceDto> subSbuList = null;
        try{
            subSbuList = getSqlMapClientTemplate().queryForList("allowanceMap.getSubSbuList",filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return subSbuList;
    }
            
    public List<FullAllowanceDto> getEmployeeDetails(SearchDto filterData){
        List<FullAllowanceDto> employeeList = null;
        try{
            String procedure = (String)getSqlMapClientTemplate().queryForObject("allowanceMap.getEmployeeDetails",filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            employeeList = getSqlMapClientTemplate().queryForList("allowanceMap.getEmployeeList",filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeList;
    }
            
    public List<FullAllowanceDto> getShiftAmount(){
        List<FullAllowanceDto> shift_amount = null;
        try{
            shift_amount = getSqlMapClientTemplate().queryForList("allowanceMap.getShiftAmount");
        }catch(Exception e){
            e.printStackTrace();
        }
        return shift_amount;
    }
            
    public void insertAllowanceDetails(FullAllowanceDto filterData){
        HttpServletRequest requestObj = null;
        
        try{
            getSqlMapClientTemplate().insert("allowanceMap.insertAllowanceDetails",filterData);
            try{
                getSqlMapClientTemplate().update("allowanceMap.updateTimesheetDetails", filterData);
            }catch(Exception e){
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        ArrayList<FullAllowanceDto> connectionRes;
        connectionRes =  (ArrayList<FullAllowanceDto>) getSqlMapClientTemplate().queryForList("allowanceMap.getConfigValueData","10");
        String con_username = connectionRes.get(0).getConfigValue();
        String con_password = connectionRes.get(1).getConfigValue();
        String con_host = connectionRes.get(2).getConfigValue();
        String con_port = connectionRes.get(3).getConfigValue();
        
        SendMail mailObj = null;
        FullAllowanceDto employeeName = null;
        FullAllowanceDto ProjectManager = null;
        try {
            mailObj = new SendMail(con_username,con_password,con_host,con_port);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HolidayDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HolidayDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        MailMessages mailMessageObj = new MailMessages();
        try{
            String[] months = new DateFormatSymbols().getMonths();
            employeeName = (FullAllowanceDto) getSqlMapClientTemplate().queryForObject("allowanceMap.getEmployeeName", filterData.getEmployee_id());
            ProjectManager = (FullAllowanceDto) getSqlMapClientTemplate().queryForObject("allowanceMap.getManagerName", filterData.getCreated_by());
            int shift_count = Integer.parseInt(filterData.getCompany_shift_I())+Integer.parseInt(filterData.getCompany_shift_II())+Integer.parseInt(filterData.getCompany_shift_III())+
                                Integer.parseInt(filterData.getCust_shift_I())+Integer.parseInt(filterData.getCust_shift_II())+Integer.parseInt(filterData.getCompany_shift_III());
            String allowance_details = "";
            allowance_details += "<br>Dear <b>"+employeeName.getEmployee_name()+"</b>,<br><br>";
            allowance_details += "Please be informed that your project manager <b>"+ProjectManager.getEmployee_name()+" </b>has successfully submitted allowance inputs for "+months[Integer.parseInt(filterData.getMonth())-1]+" - "+filterData.getYear()+".<br>"; 
            allowance_details += "The same will be processed in current month payroll.<br><br>";
            allowance_details += "<table style='border: 1px solid grey;border-collapse: collapse;'>";
            allowance_details += "<tr><th style='border: 1px solid grey;text-align: center;padding: 4px;'>Particulars</th><th style='border: 1px solid grey;text-align: center;padding: 4px;'>Hardship Allowance</th><th style='border: 1px solid grey;text-align: center;padding: 4px;'>Shift Allowance</th>";
            allowance_details += "<th style='border: 1px solid grey;text-align: center;padding: 4px;'>Holiday Allowance</th><th style='border: 1px solid grey;text-align: center;padding: 4px;'>Transport One Way</th><th style='border: 1px solid grey;text-align: center;padding: 4px;'>No Cab</th><th style='border: 1px solid grey;text-align: center;padding: 4px;'>Total Amount</th>";
            allowance_details += "<tr><td style='border: 1px solid grey;text-align: center;padding: 4px;'>  No of Days</td><td style='border: 1px solid grey;text-align: center;padding: 4px;'>"+filterData.getCust_general()+"</td><td style='border: 1px solid grey;text-align: center;padding: 4px;'>"+shift_count+"</td><td style='border: 1px solid grey;text-align: center;padding: 4px;'>"+filterData.getWeekend_holidays_entered()+"</td>";
            allowance_details += "<td style='border: 1px solid grey;text-align: center;padding: 4px;'>"+filterData.getOne_way()+"</td><td style='border: 1px solid grey;text-align: center;padding: 4px;'>"+filterData.getNo_cab()+"</td><td rowspan='2' style='border: 1px solid grey;text-align: center;padding: 4px;'>"+filterData.getTotal_amount()+"</td></tr>";
            allowance_details += "<tr><td style='border: 1px solid grey;text-align: center;padding: 4px;'> Amount(INR)</td><td style='border: 1px solid grey;text-align: center;padding: 4px;'>"+filterData.getHardship_amount()+"</td><td style='border: 1px solid grey;text-align: center;padding: 4px;'>"+filterData.getShift_amount()+"</td><td style='border: 1px solid grey;text-align: center;padding: 4px;'>"+filterData.getHoliday_amount()+"</td><td colspan='2' style='border: 1px solid grey;text-align: center;padding: 4px;'>"+filterData.getTransport_amount()+"</td></tr>";
            allowance_details += "</table>";
            allowance_details += "<br><br>Project Manager Remarks : "+filterData.getRemarks()+"<br><br>";
            allowance_details += "Kindly reach your project manager for clarifications if any.<br><br><br>";
            allowance_details += "- Regards,<br>";
            allowance_details += "Ideal Admin";
            String mailSubject = "Allowance Input Submitted for "+employeeName.getEmployee_id()+"-"+employeeName.getEmployee_name()+" - "+months[Integer.parseInt(filterData.getMonth())-1]+"-"+filterData.getYear();
            String mailMessage = mailMessageObj.getMessage(requestObj, allowance_details, employeeName.getEmployee_name(), ProjectManager.getEmployee_name(), filterData.getStatus(), "ALL");
            mailObj.smtpMail(employeeName.getEmployee_mail_id(), mailSubject, mailMessage, ProjectManager.getEmployee_mail_id());
        }catch (Exception e) {
            e.printStackTrace();
        }
       
    }
            
    public List<FullAllowanceDto> getProcessedList(SearchDto filterData){
        List<FullAllowanceDto> processedList = null;
        try{
            processedList = getSqlMapClientTemplate().queryForList("allowanceMap.getProcessedList",filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return processedList;
    }
            
    public List<FullAllowanceDto> getEmployeeAllowanceReport(SearchDto filterData){
        List<FullAllowanceDto> processedList = null;
        try{
            processedList = getSqlMapClientTemplate().queryForList("allowanceMap.getEmployeeAllowanceList",filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return processedList;
    }
            
    public List<FullAllowanceDto> getSbuList(){
        List<FullAllowanceDto> sbuList = null;
        try{
            sbuList = getSqlMapClientTemplate().queryForList("allowanceMap.getSbuList");
        }catch(Exception e){
            e.printStackTrace();
        }
        return sbuList;
    }
    
    public String getProjectName(int projectId){
        String projectName = null;
        try{
            projectName = (String)getSqlMapClientTemplate().queryForObject("allowanceMap.getprojectName", projectId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return projectName;
    }
}
