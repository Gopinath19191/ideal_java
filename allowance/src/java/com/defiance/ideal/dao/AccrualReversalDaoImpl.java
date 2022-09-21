/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dao;

import com.defiance.ideal.dto.AccrualDto;
import com.defiance.ideal.dto.SearchDto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16221
 */
public class AccrualReversalDaoImpl extends SqlMapClientDaoSupport implements AccrualReversalDao{
    public List<SearchDto> getCustomerList(String created_by){
        List<SearchDto> customerList = null;
        try{
            customerList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getCustomerList", created_by);
        }catch(Exception e){
            e.printStackTrace();
        }
        return customerList;
    }
    public List<SearchDto> getProjectList(SearchDto filter_data){
        List<SearchDto> projectList = null;
        try{
            projectList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getProjectList", filter_data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return projectList;
    }
    public List<String> getAccrualNo(String project_id){
        List<String> accrualNo = null;
        try{
            accrualNo = getSqlMapClientTemplate().queryForList("accrualReversalMap.getAccrualNo", project_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return accrualNo;
    }
    public String getMaxAccrualNo(){
        String max_accrual_no = null;
        try{
            max_accrual_no = (String)getSqlMapClientTemplate().queryForObject("accrualReversalMap.getMaxAccrualNo");
        }catch(Exception e){
            e.printStackTrace();
        }
        return max_accrual_no;
    }
    public List<AccrualDto> getEmployeeAccrualDetails(SearchDto objSearch){
        List<AccrualDto> accrualList = null;
        try{
            accrualList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getEmployeeAccrualDetails", objSearch);
        }catch(Exception e){
            e.printStackTrace();
        }
        return accrualList;
    }
    public void submitAccrualReversal(AccrualDto data){
        try{
            String last_id = (String)getSqlMapClientTemplate().insert("accrualReversalMap.submitAccrualReversal", data);
            data.setLast_id(last_id);
            getSqlMapClientTemplate().insert("accrualReversalMap.insertReversalRemarks", data);
            getSqlMapClientTemplate().update("accrualReversalMap.updateAccrualConsolidationStatus", data.getAccrual_id());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getTimesheetDate(AccrualDto data){
        String timesheet_date = null;
        try{
            timesheet_date = (String)getSqlMapClientTemplate().queryForObject("accrualReversalMap.getTimesheetDate", data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return timesheet_date;
    }
    public void updateAccrualTms(AccrualDto data){
        try{
            getSqlMapClientTemplate().update("accrualReversalMap.updateAccrualTms", data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void reverseTimesheetAccrual(AccrualDto data){
        try{
            getSqlMapClientTemplate().update("accrualReversalMap.reverseTimesheetAccrual", data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<AccrualDto> getEmployeeTimesheetDetails(SearchDto filter_data){
        List<AccrualDto> accrualList = null;
        try{
            accrualList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getEmployeeTimesheetDetails", filter_data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return accrualList;
    }
    public List<SearchDto> getEmployeeProjects(SearchDto filter_data){
        List<SearchDto> projectList = null;
        try{
            projectList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getEmployeeProjects", filter_data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return projectList;
    }
    public List<SearchDto> getEmployeeRole(SearchDto filter_data){
        List<SearchDto> roleList = null;
        try{
            roleList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getEmployeeRoles", filter_data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return roleList;
    }
    public void updateTimesheet(SearchDto data){
        try{
            getSqlMapClientTemplate().update("accrualReversalMap.updateTimesheet", data);
            getSqlMapClientTemplate().update("accrualReversalMap.insertTimesheetUpdate", data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<SearchDto> getProjectByManager(String manager_id){
        List<SearchDto> projectList = null;
        try{
            projectList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getProjectByManager", manager_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return projectList;
    }
    public List<SearchDto> getReporteesList(String manager_id){
        List<SearchDto> employeeList = null;
        try{
            employeeList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getReporteesList", manager_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeList;
    }
    public List<SearchDto> getEmployeeList(String project_id){
        List<SearchDto> employeeList = null;
        try{
            employeeList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getEmployeeList", project_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeList;
    }
    public List<AccrualDto> getTimesheetDetails(SearchDto filter_data){
        List<AccrualDto> timesheetList = null;
        try{
            if(filter_data.getProject_id().equals("Non_Project_Activity")){
                timesheetList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getNonProjectTimesheet", filter_data);
            }else{
                timesheetList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getProjectTimesheet", filter_data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return timesheetList;
    }
    public void rejectTimesheet(String timesheet_id){
        try{
            getSqlMapClientTemplate().update("accrualReversalMap.rejectTimesheet", timesheet_id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<SearchDto> getOfficeTimingList(){
        List<SearchDto> officeList = null;
        try{
            officeList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getOfficeTimingList");
        }catch(Exception e){
            e.printStackTrace();
        }
        return officeList;
    }
    public void updateOfficeTiming(SearchDto filterData){
        try{
            getSqlMapClientTemplate().update("accrualReversalMap.updateOfficeTiming", filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<SearchDto> getEmployessList(String manager_id){
        List<SearchDto> employeeList = null;
        try{
            employeeList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getEmployessList", manager_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeList;
    }
    public void approveOfficeTiming(SearchDto filter_data){
        try{
            getSqlMapClientTemplate().update("accrualReversalMap.approveOfficeTiming", filter_data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<SearchDto> getSelectedList(String employee_id){
        List<SearchDto> selectedList = null;
        try{
            selectedList = getSqlMapClientTemplate().queryForList("accrualReversalMap.getSelectedList", employee_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return selectedList;
    }
    public SearchDto getEmployeeDetails(SearchDto filter_data){
        SearchDto employeeDetails = null;
        try{
            employeeDetails = (SearchDto)getSqlMapClientTemplate().queryForObject("accrualReversalMap.getEmployeeDetails", filter_data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeDetails;
    }
    public List<SearchDto> getConnectionDetails(){
        List<SearchDto> connectionDetails = null;
        try{
            connectionDetails = getSqlMapClientTemplate().queryForList("accrualReversalMap.getConfigValueData","10");
        }catch(Exception e){
            e.printStackTrace();
        }
        return connectionDetails;
    }
    public SearchDto getoffTimingDetails(String timing_id){
        SearchDto timingDetails = null;
        try{
            timingDetails = (SearchDto)getSqlMapClientTemplate().queryForObject("accrualReversalMap.getoffTimingDetails", timing_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return timingDetails;
    }
    public List<SearchDto> getAccrualDetails(String year){
        List<SearchDto> accrualDetails = null;
        try{
            accrualDetails = getSqlMapClientTemplate().queryForList("accrualReversalMap.getAccrualDetails", year);
        }catch(Exception e){
            e.printStackTrace();
        }
        return accrualDetails;
    }
    public String updateAccrualClose(SearchDto filter_data){
        String accrualDetails = null;
        try{
            accrualDetails = (String)getSqlMapClientTemplate().queryForObject("accrualReversalMap.checkAccrualNumber", filter_data);
            if(accrualDetails!=null && accrualDetails!=""){
                getSqlMapClientTemplate().update("accrualReversalMap.updateAaccrualClose", filter_data);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(filter_data.getStartYear()),Integer.parseInt(filter_data.getStartMonth()),1);
                System.out.println("calendar "+calendar.getTime());
                int mnth = calendar.get(Calendar.MONTH) + 1;
                int yr  = calendar.get(Calendar.YEAR);
                System.out.println("calendar "+calendar.getTime());
                String month="";
                String year="";
                if(mnth<10){
                    month = Integer.toString(mnth);
                    while(month.length()<2)
                        month = "0"+month;
                }else{
                    month = Integer.toString(mnth);
                }
                year = Integer.toString(yr);
                System.out.println("year "+year+" month "+month);
                int new_accrual_no = Integer.parseInt(filter_data.getEndDate())+1;
                filter_data.setAccrual_no(Integer.toString(new_accrual_no));
                filter_data.setMonth(month);
                filter_data.setYear(year);
                getSqlMapClientTemplate().update("accrualReversalMap.insertNextAccountingMonth", filter_data);
                accrualDetails="Accrual Closed for Current Month";
            }else{
                accrualDetails="Accrual Number not available";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accrualDetails;
    }
}
