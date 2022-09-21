/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.AccrualDao;
import com.defiance.ideal.reports.dto.AccrualDataDTO;
import com.defiance.ideal.reports.dto.AccrualFilterDTO;
import java.util.List;
import java.util.Map;


/**
 *
 * @author 14053
 */
public class AccrualServiceImpl implements AccrualService {

    public AccrualDao accrualDao;

    public AccrualDao getAccrualDao() {
        return accrualDao;
    }

    public void setAccrualDao(AccrualDao accrualDao) {
        this.accrualDao = accrualDao;
    }

//    public List<ReportsDataDTO> fetchReport(ReportsFilterDTO filterData) {
//        List<ReportsDataDTO> associateData = null;
//        return associateData;
//    }

    public Map<String, String> getSbuList() {
        return accrualDao.getSbuList();
    }

    public List<AccrualDataDTO> fetchAccrualData(AccrualFilterDTO filterData) {
        return accrualDao.fetchAccrualData(filterData);
    }
}
