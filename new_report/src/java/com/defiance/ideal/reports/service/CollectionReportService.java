/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.CollectionReportDataDTO;
import com.defiance.ideal.reports.dto.CollectionReportFilterDTO;
import com.defiance.ideal.reports.dto.ReimbursementsData;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public interface CollectionReportService {

    public List<CollectionReportDataDTO> fetchCollectionReport(CollectionReportFilterDTO filterData);

    public List<CollectionReportDataDTO> fetchLegalEntity(CollectionReportFilterDTO filterData);

    public List<CollectionReportDataDTO> fetchBusinessLeader(CollectionReportFilterDTO filterData);

    public List<CollectionReportDataDTO> fetchBdmName(CollectionReportFilterDTO filterData);

    public List<CollectionReportDataDTO> fetchCustomerName(CollectionReportFilterDTO filterData);
    
    public List<CollectionReportDataDTO> fetchBdmId(CollectionReportFilterDTO filterData);
    
    public List<CollectionReportDataDTO> fetchBdmNameList(CollectionReportFilterDTO filterData);
    
    public List<CollectionReportFilterDTO> getInvoiceDetails(CollectionReportFilterDTO filterData);
    
    public CollectionReportFilterDTO getInvoiceAverage(CollectionReportFilterDTO filterData);
    
    public List<ReimbursementsData> getReimbursementsList();
    
    public List<ReimbursementsData> getReimbursementReport(ReimbursementsData filteData);
    
}
