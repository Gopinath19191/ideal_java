/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dao;

import com.attendance.employee.attendance.dto.CalendarDTO;
import com.attendance.employee.attendance.dto.PmoRmgWrkLocAllocationDto;
import com.attendance.employee.attendance.dto.ReportingListDTO;
import com.attendance.employee.attendance.dto.SearchDTO;
import java.util.List;

/**
 *
 * @author 16365
 */
public interface PmoRmgWrkLocAllocationDao {
    
    //For Authentication
    
    public PmoRmgWrkLocAllocationDto getUserDetails(String empId);
    
    public boolean authenticate(PmoRmgWrkLocAllocationDto authenParams);
    
    //To display employee list
    
    public List<PmoRmgWrkLocAllocationDto> getEmployeeList(PmoRmgWrkLocAllocationDto filterData);
    
    public List<PmoRmgWrkLocAllocationDto> getBaseLocationList();
    
    public List<PmoRmgWrkLocAllocationDto> getWorkLocationList();
    
    public List<PmoRmgWrkLocAllocationDto> getCustomerLocationList(PmoRmgWrkLocAllocationDto filterData);
    
    public List<PmoRmgWrkLocAllocationDto> getCustomerList();
    
    public List<PmoRmgWrkLocAllocationDto> getHTLBaseLocationList();
    
    public List<ReportingListDTO> getReportingList(SearchDTO searchObj);
    
    //For search
    
    public List<PmoRmgWrkLocAllocationDto> getSearchEmployeeList(SearchDTO searchObj);
    
    //To insert & maintain history
    
    public void insertLocation(PmoRmgWrkLocAllocationDto filterData);
    
    public void insertHistoryLocation(PmoRmgWrkLocAllocationDto filterData);
    
    public PmoRmgWrkLocAllocationDto getSingleEmpDetails(PmoRmgWrkLocAllocationDto filterData);
    
     //For customer calendar
    
    public List<CalendarDTO> getCalendarNameList(CalendarDTO searchObj);
    
    public String getAvailableHrs(CalendarDTO searchObj);
}
