/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.BillingInvoiceReportDataDTO;
import com.defiance.ideal.reports.dto.BillingInvoiceReportFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 16047
 */
public interface BillingInvoiceReportService {
    public List<BillingInvoiceReportDataDTO> getBillingInvoiceList(BillingInvoiceReportFilterDTO formData);
    public List<BillingInvoiceReportFilterDTO> getSearchList(String empVal);
    public List<BillingInvoiceReportFilterDTO> getProjectSearchList(String prjVal);
    public List<BillingInvoiceReportFilterDTO> getCustomerSearchList(String custVal);
    public Map<String, String> getPrjSbuList();
    public Map<String, String> getEmpSbuList();
    public List<BillingInvoiceReportDataDTO> getBillingYearList();
    public List<BillingInvoiceReportDataDTO> getBillingMonthList();
    public List<BillingInvoiceReportDataDTO> getBilingAdviceFilterList();
    public List<BillingInvoiceReportDataDTO> getAccrualSnoList();
    public String getEmployeeName(String employee_id);
    public String getProjectName(String projectId);
    public String getCustomerName(String customerId);
    public int getBillingInvoiceListCount(BillingInvoiceReportFilterDTO formData);
}
