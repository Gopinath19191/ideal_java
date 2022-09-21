/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.BenchReportDao;
import com.defiance.ideal.reports.dto.BenchReportDataDTO;
import com.defiance.ideal.reports.dto.BenchReportFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14619
 */
public class BenchReportServiceImpl implements BenchReportService{
     public BenchReportDao benchReportDao;

    public BenchReportDao getBenchReportDao() {
        return benchReportDao;
    }

    public void setBenchReportDao(BenchReportDao benchReportDao) {
        this.benchReportDao = benchReportDao;
    }
 public Map<String, String> getSbuList() {
        return benchReportDao.getSbuList();
    }
 public Map<String, String> getBandList() {
        return benchReportDao.getBandList();
    }
     public List<BenchReportDataDTO> fetchBenchList(BenchReportFilterDTO filterData){
       List<BenchReportDataDTO> projectList=benchReportDao.fetchBenchDaysReport(filterData);
       System.out.println("Inside service size"+projectList.size());
       return projectList;

   }


}
