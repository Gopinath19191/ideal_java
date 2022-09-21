/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.defiance.ideal.reports.dto.AssociateTimesheetDataDTO;
import com.defiance.ideal.reports.util.CommonConfigurations;

/**
 *
 * @author 14053
 */
public class AssociateTimesheetDaoImpl extends SqlMapClientDaoSupport implements AssociateTimesheetDao {

    private SimpleJdbcTemplate simpleJdbcTemplate;
    private SimpleJdbcCall funcGetActorName;
    private String sbuId;

    public Map<String, String> getSbuList() {
        Map<String, String> sbuList = new LinkedHashMap<String, String>();
        try {
           // System.out.println("------sbuId" + sbuId);
            sbuList = getSqlMapClientTemplate().queryForMap("AssociateTimesheetMap.getSbuList", sbuId, "sbuId", "sbuName");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sbuList;
    }

    public List<AssociateTimesheetDataDTO> getReportList(AssociateTimesheetDataDTO filterData) {
        List<AssociateTimesheetDataDTO> reportList = null;
        try {
           // System.out.println("--Report Name---");
            reportList = getSqlMapClientTemplate().queryForList("AssociateTimesheetMap.getReportNameList", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("filterData"+filterData.getReportConfigValue());
        return reportList;
    }

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }

    public List<AssociateTimesheetDataDTO> fetchAccrualData(AssociateTimesheetDataDTO filterData) {
        List<AssociateTimesheetDataDTO> template = null;

        try {
            HashMap params = new java.util.HashMap();
            String monthEndDate;
            String reportType = filterData.getReportFilter();
            reportType="cwh";
            int year=Integer.parseInt(filterData.getYearFilter()); 
           // System.out.println("--------------" + filterData.getMonthFilter());
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
            System.out.println("monthEnd Date:::::" + monthEndDate);
            params.put("StartDate", filterData.getYearFilter() + "-" + filterData.getMonthFilter() + "-" + "01");
            params.put("EndDate", filterData.getYearFilter() + "-" + filterData.getMonthFilter() + "-" + monthEndDate);
            params.put("EmpId", filterData.getEmployeeId());
            params.put("StartYear", filterData.getYearFilter());
            params.put("StartMonth", filterData.getMonthFilter());
            filterData.setStartYear(filterData.getYearFilter());
            filterData.setStartMonth(filterData.getMonthFilter());
            filterData.setStartDate(filterData.getYearFilter() + "-" + filterData.getMonthFilter() + "-" + "01");
            filterData.setEndDate(filterData.getYearFilter() + "-" + filterData.getMonthFilter() + "-" + monthEndDate);
//            filterData.setEndDate(filterData.getYearFilter() + "-" + filterData.getMonthFilter() + "-" + "31");
            // filterData.setEndDate("2016-03-31");
            System.out.println("  end date 11"+filterData.getEndDate());
            String PES = CommonConfigurations.PES;
            String TS = CommonConfigurations.TS;
            String parentId = PES + ',' + TS;
            if (filterData.getSbuFilter() != null) {
                if ("All".equals(filterData.getSbuFilter())) {
                    // filterData.setSbu("9,10,11");
                   // filterData.setSbu("2,5");
                   filterData.setSbu(parentId); // Instead of changing sbu id (2,5)
                } else {
                    filterData.setSbu(filterData.getSbuFilter());
                }
            } else {
               // filterData.setSbu("9,10,11");
               // filterData.setSbu("2,5");
                    filterData.setSbu(parentId);
            }
            //System.out.println("-----Sbu Data----" + filterData.getSbu() + ":::::Report Type:::" + filterData.getReportFilter() + ":::report Type::::" + reportType);
            if (reportType != null) {
                if (reportType.equals("cwh")) {
                   System.out.println("start year"+filterData.getStartYear());
                   System.out.println("start month"+filterData.getStartMonth());
                   System.out.println("start date"+filterData.getStartDate());
                   System.out.println("end date"+filterData.getEndDate());
                   System.out.println("employee id"+filterData.getEnd_date());
                    System.out.println("worked hrs"+filterData.getWorked_hours());
                    System.out.println("hhhh"+filterData.getWorkedhours());
                            
                    System.out.println("Here comes the getAssociateWorkedHrsReport query");
                   // getSqlMapClientTemplate().queryForObject("AssociateTimesheetMap.getWeekdaydifference", params);
                    getSqlMapClientTemplate().update("AssociateTimesheetMap.getAccuredHrs", filterData);
                    template = getSqlMapClientTemplate().queryForList("AssociateTimesheetMap.getAssociateWorkedHrsReport", filterData);
                    System.out.println("ffffffffffffff..........."+template.toString());
                } else if (reportType.equals("ptwh")) {
                    System.out.println("Here comes the getAssociateWorkedHrsReport query");
                    //System.out.println("Second report---");
                    getSqlMapClientTemplate().update("AssociateTimesheetMap.getTotWorkedHrsView");
                    template = getSqlMapClientTemplate().queryForList("AssociateTimesheetMap.getTotWorkedHrsReport", filterData);

                } else if (reportType.equals("pmwh")) {
                    System.out.println("Here comes the getAssociateWorkedHrsReport query");
                   // System.out.println("Third report----");
                    getSqlMapClientTemplate().queryForObject("AssociateTimesheetMap.getWeekdaydifference", params);
                    template = getSqlMapClientTemplate().queryForList("AssociateTimesheetMap.getMonthlyWorkedHrsReport", filterData);
                } else if (reportType.equals("npwh")) {
                    //System.out.println("Fourth report-----");
                    getSqlMapClientTemplate().queryForObject("AssociateTimesheetMap.getWeekdaydifference", params);
                    template = getSqlMapClientTemplate().queryForList("AssociateTimesheetMap.getNonPrjWorkedHrsReport", filterData);
                }

            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    public AssociateTimesheetDataDTO getSummation(AssociateTimesheetDataDTO filterData) {
        AssociateTimesheetDataDTO dto= null;
        try {
        	
        	dto = (AssociateTimesheetDataDTO) getSqlMapClientTemplate().queryForObject("AssociateTimesheetMap.getSummation", filterData);
        	
        }catch(Exception e){
        	e.printStackTrace();
        }
    
    
        return dto;
    }
}
