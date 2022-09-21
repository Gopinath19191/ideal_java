/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.service;

import com.attendance.employee.attendance.dao.PmoRmgWrkLocAllocationDao;
import com.attendance.employee.attendance.dto.CalendarDTO;
import com.attendance.employee.attendance.dto.PmoRmgWrkLocAllocationDto;
import com.attendance.employee.attendance.dto.ReportingListDTO;
import com.attendance.employee.attendance.dto.SearchDTO;
import java.util.List;

/**
 *
 * @author 16365
 */
public class PmoRmgWrkLocAllocationServiceImpl implements PmoRmgWrkLocAllocationService {
    
    PmoRmgWrkLocAllocationDao dao;
    
    public PmoRmgWrkLocAllocationDao getDao() {
        return dao;
    }

    public void setDao(PmoRmgWrkLocAllocationDao dao) {
        this.dao = dao;
    }
    
    //For Authentication
    
    public PmoRmgWrkLocAllocationDto getUserDetails(String empId){
        PmoRmgWrkLocAllocationDto userDetails = dao.getUserDetails(empId);
        return userDetails;
    }
    
    public boolean authenticate(PmoRmgWrkLocAllocationDto authenParams){
        boolean authenticate = dao.authenticate(authenParams);
        return authenticate;
    }
    
    //To display employee list
    
    public List<PmoRmgWrkLocAllocationDto> getEmployeeList(PmoRmgWrkLocAllocationDto filterData){
        List<PmoRmgWrkLocAllocationDto> empList = dao.getEmployeeList(filterData);
        return empList;
    }
    
    public List<PmoRmgWrkLocAllocationDto> getBaseLocationList(){
        List<PmoRmgWrkLocAllocationDto> baseLocationList = dao.getBaseLocationList();
        return baseLocationList;
    }
    
    public List<PmoRmgWrkLocAllocationDto> getWorkLocationList(){
        List<PmoRmgWrkLocAllocationDto> workLocationList = dao.getWorkLocationList();
        return workLocationList;
    }
    
     public List<PmoRmgWrkLocAllocationDto> getCustomerLocationList(PmoRmgWrkLocAllocationDto filterData){
        List<PmoRmgWrkLocAllocationDto> cusLocList = dao.getCustomerLocationList(filterData);
        return cusLocList;
    }
     
    public List<PmoRmgWrkLocAllocationDto> getCustomerList(){
        List<PmoRmgWrkLocAllocationDto> cusList = dao.getCustomerList();
        return cusList;
    }
    
    public List<PmoRmgWrkLocAllocationDto> getHTLBaseLocationList(){
        List<PmoRmgWrkLocAllocationDto> HtlBaseLocationList = dao.getHTLBaseLocationList();
        return HtlBaseLocationList;
    }
    
    public List<ReportingListDTO> getReportingList(SearchDTO searchObj){
        List<ReportingListDTO> reportingList = dao.getReportingList(searchObj);
        return reportingList;
    }
    
    //For Search
    
    public List<PmoRmgWrkLocAllocationDto> getSearchEmployeeList(SearchDTO searchObj){
        List<PmoRmgWrkLocAllocationDto> searchList = dao.getSearchEmployeeList(searchObj);
        return searchList;
    }
    
    //To insert & maintain history
    
    public void insertLocation(PmoRmgWrkLocAllocationDto filterData){
        dao.insertLocation(filterData);
    }
    
    public void insertHistoryLocation(PmoRmgWrkLocAllocationDto filterData){
        dao.insertHistoryLocation(filterData);
    }
    
    public PmoRmgWrkLocAllocationDto getSingleEmpDetails(PmoRmgWrkLocAllocationDto filterData){
        PmoRmgWrkLocAllocationDto empDetails = dao.getSingleEmpDetails(filterData);
        return empDetails;
    }
    
    //For customer calendar
    
    public List<CalendarDTO> getCalendarNameList(CalendarDTO searchObj){
        List<CalendarDTO> calendarList = dao.getCalendarNameList(searchObj);
        return calendarList;
    }
    
    public String getAvailableHrs(CalendarDTO searchObj){
        String availableHrs = dao.getAvailableHrs(searchObj);
        return availableHrs;
    }
}
