/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.MonthlyLeaveDataDTO;
import com.defiance.ideal.reports.dto.MonthlyLeaveFilterDTO;
import com.defiance.ideal.reports.util.CommonConfigurations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14517
 */
public class MonthlyLeaveDaoImpl extends SqlMapClientDaoSupport implements MonthlyLeaveDao {

    private String sbuId;

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }

    public Map<String, String> getSbuList() {
        Map<String, String> sbuList = new LinkedHashMap<String, String>();
        try {
            sbuList = getSqlMapClientTemplate().queryForMap("MonthlyLeaveMap.getSbuList", sbuId, "sbuId", "sbuName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbuList;
    }

    public List<MonthlyLeaveFilterDTO> getStructureList() {
        List<MonthlyLeaveFilterDTO> dataObj = null;

        try {
            String structure = CommonConfigurations.SBU + ',' + CommonConfigurations.SSU;
            System.out.println("structure" + structure);
            dataObj = (List) getSqlMapClientTemplate().queryForList("MonthlyLeaveMap.getStructureList", structure);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataObj;
    }

    public List<MonthlyLeaveDataDTO> getLeaveRecords(MonthlyLeaveFilterDTO filterData) {
        System.out.println("filterData.getSbuFilter()" + filterData.getSbuFilter());
        System.out.println("get year"+filterData.getFilter_year());
        System.out.println("get month"+filterData.getFilter_month());
        if(filterData.getSbuFilter() == null){
            filterData.setSbuFilter("");
        }
        if(filterData.getEmployee_id() == null){
            filterData.setEmployee_id("");
        }
        if(filterData.getFrom_date() == null){
            filterData.setFrom_date("");
        }
        if(filterData.getTo_date() == null){
            filterData.setTo_date("");
        }
        if(filterData.getFilter_year()==null){
            filterData.setFilter_year("");
        }
        if(filterData.getFilter_month()==null){
            filterData.setFilter_month("");
        }
         String monthEndDate;
         int year=Integer.parseInt(filterData.getFilter_year()); 
        if ("01".equals(filterData.getFilter_month()) || "03".equals(filterData.getFilter_month()) || "05".equals(filterData.getFilter_month()) || "07".equals(filterData.getFilter_month()) || "08".equals(filterData.getFilter_month()) || "10".equals(filterData.getFilter_month()) || "12".equals(filterData.getFilter_month())) {
                monthEndDate = "31";
            } else if ("04".equals(filterData.getFilter_month()) || "06".equals(filterData.getFilter_month()) || "09".equals(filterData.getFilter_month()) || "11".equals(filterData.getFilter_month())) {
                monthEndDate = "30";
            } else if("02".equals(filterData.getFilter_month()) && year%4==0){
                monthEndDate = "29";
            }
            else{
                monthEndDate = "28";
            }
            System.out.println("monthEnd Date:::::" + monthEndDate);
            filterData.setStartYear(filterData.getFilter_year());
            filterData.setStartMonth(filterData.getFilter_month());
            filterData.setStartDate(filterData.getFilter_year() + "-" + filterData.getFilter_month() + "-" + "01");
            
            filterData.setEndDate(filterData.getFilter_year() + "-" + filterData.getFilter_month() + "-" + monthEndDate);
            System.out.println("  end date 11"+filterData.getEndDate());
        List<MonthlyLeaveDataDTO> template = null;
        try {

            template = getSqlMapClientTemplate().queryForList("MonthlyLeaveMap.getLeaveRecords", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;

    }

    public String getEmployeeName(String employee_id) {
        return (String)getSqlMapClientTemplate().queryForObject("MonthlyLeaveMap.getEmployeeName", employee_id);
    }

}
