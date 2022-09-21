/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.BillableHoursDataDTO;
import com.defiance.ideal.reports.dto.BillableHoursFilterDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14517
 */
public class BillableHoursDaoImpl extends SqlMapClientDaoSupport implements BillableHoursDao  {
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
            sbuList = getSqlMapClientTemplate().queryForMap("BillableHoursMap.getSbuList",sbuId,"sbuId", "sbuName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbuList;
    }
      public Map<String, String> getCustomerList() {
        Map<String, String> customerList = new LinkedHashMap<String, String>();
        try {
            customerList = getSqlMapClientTemplate().queryForMap("BillableHoursMap.getCustomerList","","custId", "custName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }
       public List getProjectList(String sbuId,String subSbuId) {
        List<BillableHoursFilterDTO> projectList = new ArrayList();
        Map<String, String> map = new HashMap<String, String>();
        map.put("sbuId",sbuId);
        map.put("subSbuId",subSbuId);
        try {
            projectList = getSqlMapClientTemplate().queryForList("BillableHoursMap.getProjectList",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public List<BillableHoursDataDTO> fetchBillableHoursData(BillableHoursFilterDTO filterData) {
          List<BillableHoursDataDTO> template = null;
        try {
            String monthEndDate;
            int year=Integer.parseInt(filterData.getYearFilter());
            if ("01".equals(filterData.getMonthFilter()) || "03".equals(filterData.getMonthFilter()) || "05".equals(filterData.getMonthFilter()) || "07".equals(filterData.getMonthFilter()) || "08".equals(filterData.getMonthFilter()) || "10".equals(filterData.getMonthFilter()) || "12".equals(filterData.getMonthFilter())) {
                monthEndDate = "31";
            } else if ("04".equals(filterData.getMonthFilter()) || "06".equals(filterData.getMonthFilter()) || "09".equals(filterData.getMonthFilter()) || "11".equals(filterData.getMonthFilter())) {
                monthEndDate = "30";
            } else if("02".equals(filterData.getMonthFilter()) && year%4==0){
                monthEndDate = "29";
            }
            else{
                monthEndDate = "28";
            }
            String startDate = filterData.getYearFilter()+"-"+filterData.getMonthFilter()+"-"+"01";
            String endDate = filterData.getYearFilter()+"-"+filterData.getMonthFilter()+"-"+monthEndDate;
            filterData.setStartDate(startDate);
            filterData.setEndDate(endDate);
            filterData.setStartYear(filterData.getYearFilter());
            filterData.setStartMonth(filterData.getMonthFilter());
            template = getSqlMapClientTemplate().queryForList("BillableHoursMap.billableData",filterData);
           // System.out.println(": TEMPLATE :"+template+":FILTER:"+filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

}
