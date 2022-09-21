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
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16365
 */
public class PmoRmgWrkLocAllocationDaoImpl extends SqlMapClientDaoSupport implements PmoRmgWrkLocAllocationDao {

    //For getting user details
    public PmoRmgWrkLocAllocationDto getUserDetails(String empId) {
        return (PmoRmgWrkLocAllocationDto) getSqlMapClientTemplate().queryForObject("AuthenticateMap.getUserDetails", empId);
    }

    //For authentication
    public boolean authenticate(PmoRmgWrkLocAllocationDto authenParams) {
        try {
            PmoRmgWrkLocAllocationDto userAuthentication = null;
            PmoRmgWrkLocAllocationDto groupAuthentication = null;
            PmoRmgWrkLocAllocationDto loginAuthentication = null;

            loginAuthentication = (PmoRmgWrkLocAllocationDto) getSqlMapClientTemplate().queryForObject("AuthenticateMap.loginCheck", authenParams);

            if (loginAuthentication == null) {
                System.out.println("Not Logged IN");
                return false;
            } else {
                userAuthentication = (PmoRmgWrkLocAllocationDto) getSqlMapClientTemplate().queryForObject("AuthenticateMap.authenticateUser", authenParams);
                if (userAuthentication == null || userAuthentication.getuCreate() == null || userAuthentication.getuCreate().equals("-1")) {
                    groupAuthentication = (PmoRmgWrkLocAllocationDto) getSqlMapClientTemplate().queryForObject("queryMap.authenticateGroup", authenParams);
                    if (groupAuthentication == null || groupAuthentication.getgCreate() == null || groupAuthentication.getgCreate().equals("-1")) {
                        System.out.println("Group Auth Failed");
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //To display employee list
    public List<PmoRmgWrkLocAllocationDto> getEmployeeList(PmoRmgWrkLocAllocationDto filterData) {
        List<PmoRmgWrkLocAllocationDto> empList = null;
        empList = (List<PmoRmgWrkLocAllocationDto>) getSqlMapClientTemplate().queryForList("PmoRmgWrkLocAllocation.getEmployeeList", filterData);
        return empList;
    }

    public List<PmoRmgWrkLocAllocationDto> getBaseLocationList() {
        List<PmoRmgWrkLocAllocationDto> baseLocationList = (List<PmoRmgWrkLocAllocationDto>) getSqlMapClientTemplate().queryForList("PmoRmgWrkLocAllocation.baseLocationList");
        return baseLocationList;
    }

    public List<PmoRmgWrkLocAllocationDto> getWorkLocationList() {
        List<PmoRmgWrkLocAllocationDto> workLocationList = (List<PmoRmgWrkLocAllocationDto>) getSqlMapClientTemplate().queryForList("PmoRmgWrkLocAllocation.workLocationList");
        return workLocationList;
    }

    public List<PmoRmgWrkLocAllocationDto> getCustomerLocationList(PmoRmgWrkLocAllocationDto filterData) {
        List<PmoRmgWrkLocAllocationDto> cusLocList = (List<PmoRmgWrkLocAllocationDto>) getSqlMapClientTemplate().queryForList("PmoRmgWrkLocAllocation.customerAddressList", filterData);
        return cusLocList;
    }

    public List<PmoRmgWrkLocAllocationDto> getCustomerList() {
        List<PmoRmgWrkLocAllocationDto> cusList = (List<PmoRmgWrkLocAllocationDto>) getSqlMapClientTemplate().queryForList("PmoRmgWrkLocAllocation.customerList");
        return cusList;
    }

    public List<PmoRmgWrkLocAllocationDto> getHTLBaseLocationList() {
        List<PmoRmgWrkLocAllocationDto> htlBaseLocationList = (List<PmoRmgWrkLocAllocationDto>) getSqlMapClientTemplate().queryForList("PmoRmgWrkLocAllocation.HtlbaseLocationList");
        return htlBaseLocationList;
    }

    public List<ReportingListDTO> getReportingList(SearchDTO searchObj) {
        List<ReportingListDTO> reportingList = (List<ReportingListDTO>) getSqlMapClientTemplate().queryForList("PmoRmgWrkLocAllocation.getReportingList", searchObj);
        return reportingList;
    }
    //For search

    public List<PmoRmgWrkLocAllocationDto> getSearchEmployeeList(SearchDTO searchObj) {
        searchObj.setEmployeeSearch("%" + searchObj.getEmployeeSearch() + "%");
        List<PmoRmgWrkLocAllocationDto> searchList = (List<PmoRmgWrkLocAllocationDto>) getSqlMapClientTemplate().queryForList("PmoRmgWrkLocAllocation.searchEmployeeList", searchObj);
        System.out.println("" + searchList.size());
        return searchList;
    }

    //To insert & maintain history
    public void insertLocation(PmoRmgWrkLocAllocationDto filterData) {
        getSqlMapClientTemplate().insert("PmoRmgWrkLocAllocation.insertLocation", filterData);
    }

    public void insertHistoryLocation(PmoRmgWrkLocAllocationDto filterData) {
        getSqlMapClientTemplate().insert("PmoRmgWrkLocAllocation.insertLocationHistory", filterData);
    }

    public PmoRmgWrkLocAllocationDto getSingleEmpDetails(PmoRmgWrkLocAllocationDto filterData) {
        PmoRmgWrkLocAllocationDto empDetails = (PmoRmgWrkLocAllocationDto) getSqlMapClientTemplate().queryForObject("PmoRmgWrkLocAllocation.getSingleEmployeeDetail", filterData);
        return empDetails;
    }

    //For customer calendar
    public List<CalendarDTO> getCalendarNameList(CalendarDTO searchObj) {
        List<CalendarDTO> calendarList = null;
        try {
            calendarList = getSqlMapClientTemplate().queryForList("PmoRmgWrkLocAllocation.getCalendarNameList", searchObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendarList;
    }

    public String getAvailableHrs(CalendarDTO searchObj) {
        String availableHrs = (String) getSqlMapClientTemplate().queryForObject("PmoRmgWrkLocAllocation.getAvailableHours", searchObj);
        return availableHrs;
    }
}
