/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.AssociateTimesheetDao;
import com.defiance.ideal.reports.dto.AssociateTimesheetDataDTO;
import com.defiance.ideal.reports.dto.AssociateTimesheetFilterDTO;
import java.util.List;
import java.util.Map;


/**
 *
 * @author 14053
 */
public class AssociateTimesheetServiceImpl implements AssociateTimesheetService {

    public AssociateTimesheetDao associateTimesheetDao;

    public AssociateTimesheetDao getAssociateTimesheetDao() {
        return associateTimesheetDao;
    }

    public void setAssociateTimesheetDao(AssociateTimesheetDao associateTimesheetDao) {
        this.associateTimesheetDao = associateTimesheetDao;
    }

    

//    public List<ReportsDataDTO> fetchReport(ReportsFilterDTO filterData) {
//        List<ReportsDataDTO> associateData = null;
//        return associateData;
//    }

    public Map<String, String> getSbuList() {
        return associateTimesheetDao.getSbuList();
           }
    
    public List<AssociateTimesheetDataDTO> getReportList(AssociateTimesheetDataDTO filterData) {
        return associateTimesheetDao.getReportList(filterData);
           }


    public List<AssociateTimesheetDataDTO> fetchAccrualData(AssociateTimesheetDataDTO filterData) {
        return associateTimesheetDao.fetchAccrualData(filterData);
    }
    public AssociateTimesheetDataDTO getSummation(AssociateTimesheetDataDTO filterData) {
        return associateTimesheetDao.getSummation(filterData);
    }
  
}
