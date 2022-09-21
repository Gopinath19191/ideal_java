/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.BenchReportDataDTO;
import com.defiance.ideal.reports.dto.BenchReportFilterDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14619
 */
public class BenchReportDaoImpl extends SqlMapClientDaoSupport implements BenchReportDao {
private String sbuId;
private String bandId;

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }
 /**
     * @return the sbuId
     */
    public String getSbuId() {
        return sbuId;
    }

    /**
     * @param sbuId the sbuId to set
     */
    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }
public List<BenchReportDataDTO> fetchBenchDaysReport(BenchReportFilterDTO filterData){
         // System.out.println("Inside Dao");
             List<BenchReportDataDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("BenchDaysMap.getBenchDaysList",filterData);
           // System.out.println("template size"+template.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }
public Map<String, String> getSbuList(){
     //System.out.print("inside sbu list");
           Map<String, String> sbuList = new LinkedHashMap<String, String>();
        try {
            sbuList = getSqlMapClientTemplate().queryForMap("BenchDaysMap.getSbuList", getSbuId(),"sbuId", "sbuName");
          //  System.out.print("sbuList"+sbuList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbuList;
      }

   public Map<String, String> getBandList(){
    // System.out.print("inside band list");
           Map<String, String> bandList = new LinkedHashMap<String, String>();
        try {
            bandList = getSqlMapClientTemplate().queryForMap("BenchDaysMap.getBandList", getBandId(),"bandId", "bandName");
        //    System.out.print("bandList"+bandList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bandList;
      }
}
