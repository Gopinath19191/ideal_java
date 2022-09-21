/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.CustagingReportDao;
import com.defiance.ideal.reports.dto.CustagingReportDataDTO;
import com.defiance.ideal.reports.dto.CustagingReportFilterDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public interface CustagingReportService {

    public List<CustagingReportDataDTO> fetchCustagingReport(CustagingReportFilterDTO filterData);
    public HashMap<String,String> fetchExchangeRate(CustagingReportFilterDTO filterData);
    


    public List<CustagingReportDataDTO> fetchLegalEntity(CustagingReportFilterDTO filterData);
    


    public List<CustagingReportDataDTO> fetchBusinessLeader(CustagingReportFilterDTO filterData);

    public List<CustagingReportDataDTO> fetchBdmName(CustagingReportFilterDTO filterData);
    
    public List<CustagingReportDataDTO> fetchBdmId(CustagingReportFilterDTO filterData);
    
    public List<CustagingReportDataDTO> fetchBdmNameList(CustagingReportFilterDTO filterData);

    public List<CustagingReportDataDTO> fetchCustomerName(CustagingReportFilterDTO filterData);
    
    public void updateInvoiceDates(CustagingReportFilterDTO formData);
    
    public List<CustagingReportDataDTO> fetchReportDates(CustagingReportFilterDTO filterData);
    public String maxDateOfReport();
}
