/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.CustagingReportDaoImpl;
import com.defiance.ideal.reports.dto.CustagingReportDataDTO;
import com.defiance.ideal.reports.dto.CustagingReportFilterDTO;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 14517
 */
public class CustagingReportServiceImpl implements CustagingReportService {

    public CustagingReportDaoImpl custagingReportDaoImpl;

    public CustagingReportDaoImpl getCustagingReportDaoImpl() {
        return custagingReportDaoImpl;
    }

    public void setCustagingReportDaoImpl(CustagingReportDaoImpl custagingReportDaoImpl) {
        this.custagingReportDaoImpl = custagingReportDaoImpl;
    }

    public List<CustagingReportDataDTO> fetchCustagingReport(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> projectList = custagingReportDaoImpl.fetchCustagingReport(filterData);
        //System.out.println("Inside service size"+projectList.size());
        return projectList;
    }

    public HashMap<String, String> fetchExchangeRate(CustagingReportFilterDTO filterData) {
        HashMap<String, String> map = new HashMap();
        map = custagingReportDaoImpl.fetchExchangeRate(filterData);
        System.out.println("Inside service size" + map.size());
        return map;
    }

    public List<CustagingReportDataDTO> fetchLegalEntity(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> projectList = custagingReportDaoImpl.fetchLegalEntity(filterData);
        System.out.println("Inside service size" + projectList.size());
        return projectList;
    }

    public List<CustagingReportDataDTO> fetchBusinessLeader(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> projectList = custagingReportDaoImpl.fetchBusinessLeader(filterData);
        System.out.println("Inside service size" + projectList.size());
        return projectList;
    }

    public List<CustagingReportDataDTO> fetchBdmName(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> projectList = custagingReportDaoImpl.fetchBdmName(filterData);
        System.out.println("Inside service size" + projectList.size());
        return projectList;
    }

    public List<CustagingReportDataDTO> fetchBdmId(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> projectList = custagingReportDaoImpl.fetchBdmId(filterData);
        System.out.println("Inside service size" + projectList.size());
        return projectList;
    }

    public List<CustagingReportDataDTO> fetchBdmNameList(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> projectList = custagingReportDaoImpl.fetchBdmNameList(filterData);
        System.out.println("Inside service size" + projectList.size());
        return projectList;
    }

    public List<CustagingReportDataDTO> fetchCustomerName(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> projectList = null;
        try {
            projectList = custagingReportDaoImpl.fetchCustomerName(filterData);
        } catch (ParseException ex) {
            Logger.getLogger(CustagingReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return projectList;
    }

    public void updateInvoiceDates(CustagingReportFilterDTO formData) {
        custagingReportDaoImpl.updateInvoiceDates(formData);
    }

    public List<CustagingReportDataDTO> fetchReportDates(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> fetchreportDates = custagingReportDaoImpl.fetchReportDates(filterData);
        return fetchreportDates;

    }

    public String maxDateOfReport() {
        String maxDate = custagingReportDaoImpl.maxDateOfReport();
        return maxDate;
    }
}
