/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.VendorContractExportDto;
import java.util.List;

/**
 *
 * @author 16221
 */
public interface VendorContractExportDao{
    public List<VendorContractExportDto> getExportEmployeeTimesheet(VendorContractExportDto Dto);
    public List<VendorContractExportDto> getEmployeeDetails(VendorContractExportDto Dto);
    public List<VendorContractExportDto> getEmployeeList(VendorContractExportDto Dto);
    public List<VendorContractExportDto> getConsolidateEmployeeList(VendorContractExportDto Dto);
    public List<VendorContractExportDto> getVendorList();
    public String getEmployeeListCount(VendorContractExportDto Dto);
    public List<VendorContractExportDto> getSearchEmployeeList(String employee_search);
}
