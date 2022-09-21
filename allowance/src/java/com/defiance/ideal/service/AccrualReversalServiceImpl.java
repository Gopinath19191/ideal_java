/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.service;

import com.defiance.ideal.dao.AccrualReversalDao;
import com.defiance.ideal.dto.AccrualDto;
import com.defiance.ideal.dto.SearchDto;
import com.defiance.ideal.util.MailMessages;
import com.defiance.ideal.util.SendMail;
import com.lowagie.text.Phrase;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 16221
 */
public class AccrualReversalServiceImpl implements AccrualReversalService{
    AccrualReversalDao dao;

    public AccrualReversalDao getDao() {
        return dao;
    }

    public void setDao(AccrualReversalDao dao) {
        this.dao = dao;
    }
    public List<SearchDto> getCustomerList(String created_by){
        List<SearchDto> customerList = dao.getCustomerList(created_by);
        return customerList;
    }
    public List<SearchDto> getProjectList(SearchDto filter_data){
        List<SearchDto> projectList = dao.getProjectList(filter_data);
        return projectList;
    }
    public List<String> getAccrualNo(String project_id){
        List<String> accrualNo = dao.getAccrualNo(project_id);
        return accrualNo;
    }
    public List<AccrualDto> getEmployeeAccrualDetails(SearchDto objSearch){
        List<AccrualDto> accrualList = dao.getEmployeeAccrualDetails(objSearch);
        return accrualList;
    }
    public void submitAccrualReversal(AccrualDto data){
        String max_accrual_no = dao.getMaxAccrualNo();
        if(max_accrual_no != null){
            int max_id = Integer.parseInt(max_accrual_no)+1;
            data.setNew_accrual_no(Integer.toString(max_id));
            dao.submitAccrualReversal(data);
            String timesheet_date = dao.getTimesheetDate(data);
            if(timesheet_date!=null){
                data.setSubmitted_date(timesheet_date);
                dao.updateAccrualTms(data);
                dao.reverseTimesheetAccrual(data);

            }
        }else{
        
        }
        
    }
    public List<AccrualDto> getEmployeeTimesheetDetails(SearchDto filter_data){
        List<AccrualDto> accrualList = dao.getEmployeeTimesheetDetails(filter_data);
        return accrualList;
    }
    public List<SearchDto> getEmployeeProjects(SearchDto filter_data){
        List<SearchDto> projectList = dao.getEmployeeProjects(filter_data);
        return projectList;
    
    }
    public List<SearchDto> getEmployeeRole(SearchDto filter_data){
        List<SearchDto> roleList = dao.getEmployeeRole(filter_data);
        return roleList;
    }
    public void updateTimesheet(SearchDto data){
        dao.updateTimesheet(data);
    }
    public List<SearchDto> getProjectByManager(String manager_id){
        List<SearchDto> projectList = dao.getProjectByManager(manager_id);
        return projectList;
    }
    public List<SearchDto> getReporteesList(String manager_id){
        List<SearchDto> employeeList = dao.getReporteesList(manager_id);
        return employeeList;
    }
    public List<SearchDto> getEmployeeList(String project_id){
        List<SearchDto> employeeList = dao.getEmployeeList(project_id);
        return employeeList;
    }
    public List<AccrualDto> getTimesheetDetails(SearchDto filter_data){
         List<AccrualDto> timesheetList = dao.getTimesheetDetails(filter_data);
        return timesheetList;
    }
    public void rejectTimesheet(String timesheet_id){
        dao.rejectTimesheet(timesheet_id);
    }
    public List<SearchDto> getOfficeTimingList(){
        List<SearchDto> officeList = dao.getOfficeTimingList();
        return officeList;
    }
    public void updateOfficeTiming(SearchDto filterData){
        dao.updateOfficeTiming(filterData);
        if(filterData.getRole_id().equals("e")){
            
        }else{
            SearchDto mailDetails = dao.getEmployeeDetails(filterData);
            String mailSubject = "Special office timing - Request";
            String mailMessage = "Dear "+mailDetails.getManager_name()+",<br><br>"
                                +mailDetails.getEmployee_name()+" reporting to you in "+mailDetails.getRole_name()
                                +" has raised a request in iDeal for special office timing - "+mailDetails.getStartDate()
                                +"<br><br>Kindly logon to iDeal and provide your decision on approval or rejection<br><br>"
                                +"Regards,<br>Ideal Admin.";
            List<SearchDto> connectionRes;
            connectionRes =  dao.getConnectionDetails();
            String con_username = connectionRes.get(0).getConfigValue();
            String con_password = connectionRes.get(1).getConfigValue();
            String con_host = connectionRes.get(2).getConfigValue();
            String con_port = connectionRes.get(3).getConfigValue();

            SendMail mailObj = null;
            try {
                mailObj = new SendMail(con_username,con_password,con_host,con_port);
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(HolidayDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(HolidayDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
//            MailMessages mailMessageObj = new MailMessages();
            try{
                mailObj.smtpMail(mailDetails.getEmployee_mail_id(), mailSubject, mailMessage, mailDetails.getManager_mail_id());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public List<SearchDto> getEmployessList(String manager_id){
        List<SearchDto> employeeList = dao.getEmployessList(manager_id);
        return employeeList;
    }
    public void approveOfficeTiming(SearchDto filter_data){
        dao.approveOfficeTiming(filter_data);
        String status="";
        if(filter_data.getStatus().equals("a")){
            status="approved";
        }else{
            status="rejected";
        }
        SearchDto details = dao.getoffTimingDetails(filter_data.getTimesheet_id());
        filter_data.setCreated_by(details.getEmployee_id());
        filter_data.setRole_id(details.getRole_id());
        SearchDto mailDetails = dao.getEmployeeDetails(filter_data);
        String mailSubject = "Office timing change request - "+status;
        String mailMessage = "Dear "+mailDetails.getEmployee_name()+",<br><br>"
                            +mailDetails.getManager_name()+" has "+status
                            +" your special office timing request - "+mailDetails.getStartDate()
                            +"<br><br>Kindly logon to iDeal to check the details<br><br>"
                            +"Regards,<br>Ideal Admin.";
        List<SearchDto> connectionRes;
        connectionRes =  dao.getConnectionDetails();
        String con_username = connectionRes.get(0).getConfigValue();
        String con_password = connectionRes.get(1).getConfigValue();
        String con_host = connectionRes.get(2).getConfigValue();
        String con_port = connectionRes.get(3).getConfigValue();

        SendMail mailObj = null;
        try {
            mailObj = new SendMail(con_username,con_password,con_host,con_port);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(HolidayDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(HolidayDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
//            MailMessages mailMessageObj = new MailMessages();
        try{
            mailObj.smtpMail(mailDetails.getEmployee_mail_id(), mailSubject, mailMessage, mailDetails.getManager_mail_id());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<SearchDto> getSelectedList(String employee_id){
        List<SearchDto> selectedList = dao.getSelectedList(employee_id);
        return selectedList;
    }
    public List<SearchDto> getAccrualDetails(String year){
        List<SearchDto> accrualDetails = dao.getAccrualDetails(year);
        return accrualDetails;
    }
    public String updateAccrualClose(SearchDto filter_data){
        String status = dao.updateAccrualClose(filter_data);
        return status;
    }
}
