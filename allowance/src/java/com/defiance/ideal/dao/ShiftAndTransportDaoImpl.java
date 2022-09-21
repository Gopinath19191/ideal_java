/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dao;

import com.defiance.ideal.dto.SearchDto;
import com.defiance.ideal.dto.ShiftAndTransportDto;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 8000247
 */
public class ShiftAndTransportDaoImpl extends SqlMapClientDaoSupport implements ShiftAndTransportDao{

    public List<ShiftAndTransportDto> getCustomerList(SearchDto filterData) {
        List<ShiftAndTransportDto> dto= null;
         try {
            String monthEndDate;
            int year=Integer.parseInt(filterData.getYear());
            if ("1".equals(filterData.getMonth()) || "3".equals(filterData.getMonth()) || "5".equals(filterData.getMonth()) || "7".equals(filterData.getMonth()) || "8".equals(filterData.getMonth()) || "10".equals(filterData.getMonth()) || "12".equals(filterData.getMonth())) {
                monthEndDate = "31";
            } else if ("4".equals(filterData.getMonth()) || "6".equals(filterData.getMonth()) || "9".equals(filterData.getMonth()) || "11".equals(filterData.getMonth())) {
                monthEndDate = "30";
            } else if("2".equals(filterData.getMonth()) && year%4==0){
                monthEndDate = "29";
            }
            else{
                monthEndDate = "28";
            }
            String startDate = filterData.getYear()+"-"+filterData.getMonth()+"-"+"01";
            String endDate = filterData.getYear()+"-"+filterData.getMonth()+"-"+monthEndDate;
            filterData.setStartDate(startDate);
            filterData.setEndDate(endDate);
            dto = getSqlMapClientTemplate().queryForList("ShiftAndTransport.getCustomerList",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public List<ShiftAndTransportDto> getProjectList(SearchDto filterData) {
        List<ShiftAndTransportDto> dto= null;
         try {
             String monthEndDate;
            int year=Integer.parseInt(filterData.getYear());
            if ("1".equals(filterData.getMonth()) || "3".equals(filterData.getMonth()) || "5".equals(filterData.getMonth()) || "7".equals(filterData.getMonth()) || "8".equals(filterData.getMonth()) || "10".equals(filterData.getMonth()) || "12".equals(filterData.getMonth())) {
                monthEndDate = "31";
            } else if ("4".equals(filterData.getMonth()) || "6".equals(filterData.getMonth()) || "9".equals(filterData.getMonth()) || "11".equals(filterData.getMonth())) {
                monthEndDate = "30";
            } else if("2".equals(filterData.getMonth()) && year%4==0){
                monthEndDate = "29";
            }
            else{
                monthEndDate = "28";
            }
            String startDate = filterData.getYear()+"-"+filterData.getMonth()+"-"+"01";
            String endDate = filterData.getYear()+"-"+filterData.getMonth()+"-"+monthEndDate;
            filterData.setStartDate(startDate);
            filterData.setEndDate(endDate);
            dto = getSqlMapClientTemplate().queryForList("ShiftAndTransport.getProjectList",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public List getShiftDetails() {
        List<ShiftAndTransportDto> dto= null;
         try {
            dto = getSqlMapClientTemplate().queryForList("ShiftAndTransport.getShiftDetails");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public List<ShiftAndTransportDto> getEmployeeDetails(SearchDto filterData) {
        List<ShiftAndTransportDto> dto= null;
         try {
            String monthEndDate;
            int year=Integer.parseInt(filterData.getYear());
            if ("1".equals(filterData.getMonth()) || "3".equals(filterData.getMonth()) || "5".equals(filterData.getMonth()) || "7".equals(filterData.getMonth()) || "8".equals(filterData.getMonth()) || "10".equals(filterData.getMonth()) || "12".equals(filterData.getMonth())) {
                monthEndDate = "31";
            } else if ("4".equals(filterData.getMonth()) || "6".equals(filterData.getMonth()) || "9".equals(filterData.getMonth()) || "11".equals(filterData.getMonth())) {
                monthEndDate = "30";
            } else if("2".equals(filterData.getMonth()) && year%4==0){
                monthEndDate = "29";
            }
            else{
                monthEndDate = "28";
            }
            String startDate = filterData.getYear()+"-"+filterData.getMonth()+"-"+"01";
            String endDate = filterData.getYear()+"-"+filterData.getMonth()+"-"+monthEndDate;
            filterData.setStartDate(startDate);
            filterData.setEndDate(endDate);
            filterData.setStartYear(filterData.getYear());
            filterData.setStartMonth(filterData.getMonth());
            dto = getSqlMapClientTemplate().queryForList("ShiftAndTransport.getEmployeeDetails",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public void insertDetails(ShiftAndTransportDto filterData){
        getSqlMapClientTemplate().insert("ShiftAndTransport.insertDetails",filterData);
    }
    public List<ShiftAndTransportDto> getSavedDetails(SearchDto filterData) {
        System.out.println("in saved details");
        List<ShiftAndTransportDto> dto= null;
         try {
            dto = getSqlMapClientTemplate().queryForList("ShiftAndTransport.getSavedDetails",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public void updateDetails(ShiftAndTransportDto filterData){
        getSqlMapClientTemplate().update("ShiftAndTransport.updateDetails",filterData);
    }
    public ShiftAndTransportDto submittedCount(SearchDto filterData){
        ShiftAndTransportDto dto = null;
        try{
            dto =(ShiftAndTransportDto) getSqlMapClientTemplate().queryForObject("ShiftAndTransport.submittedCount",filterData);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public ShiftAndTransportDto detailsCount(SearchDto filterData){
        ShiftAndTransportDto dto = null;
        try{
            String monthEndDate;
            int year=Integer.parseInt(filterData.getYear());
            if ("1".equals(filterData.getMonth()) || "3".equals(filterData.getMonth()) || "5".equals(filterData.getMonth()) || "7".equals(filterData.getMonth()) || "8".equals(filterData.getMonth()) || "10".equals(filterData.getMonth()) || "12".equals(filterData.getMonth())) {
                monthEndDate = "31";
            } else if ("4".equals(filterData.getMonth()) || "6".equals(filterData.getMonth()) || "9".equals(filterData.getMonth()) || "11".equals(filterData.getMonth())) {
                monthEndDate = "30";
            } else if("2".equals(filterData.getMonth()) && year%4==0){
                monthEndDate = "29";
            }
            else{
                monthEndDate = "28";
            }
            String startDate = filterData.getYear()+"-"+filterData.getMonth()+"-"+"01";
            String endDate = filterData.getYear()+"-"+filterData.getMonth()+"-"+monthEndDate;
            filterData.setStartDate(startDate);
            filterData.setEndDate(endDate);
            filterData.setStartYear(filterData.getYear());
            filterData.setStartMonth(filterData.getMonth());
            dto =(ShiftAndTransportDto) getSqlMapClientTemplate().queryForObject("ShiftAndTransport.detailsCount",filterData);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public List<ShiftAndTransportDto> getSBU_List() {
        List<ShiftAndTransportDto> dto= null;
         try {
            dto = getSqlMapClientTemplate().queryForList("ShiftAndTransport.getSBU_List");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public List<ShiftAndTransportDto> getSBU_SUB_List(SearchDto filterData) {
        List<ShiftAndTransportDto> dto= null;
         try {
            dto = getSqlMapClientTemplate().queryForList("ShiftAndTransport.getSBU_SUB_List", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}
