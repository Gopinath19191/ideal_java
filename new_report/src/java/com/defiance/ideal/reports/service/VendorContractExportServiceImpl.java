/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.VendorContractExportDao;
import com.defiance.ideal.reports.dto.VendorContractExportDto;
import com.defiance.ideal.reports.util.CommonFunctions;
import java.util.List;

/**
 *
 * @author 16221
 */
public class VendorContractExportServiceImpl implements VendorContractExportService{
    public VendorContractExportDao dao;

    public VendorContractExportDao getDao() {
        return dao;
    }

    public void setDao(VendorContractExportDao dao) {
        this.dao = dao;
    }
        
    public List<VendorContractExportDto> getExportEmployeeTimesheet(VendorContractExportDto Dto){
        List<VendorContractExportDto> list = dao.getExportEmployeeTimesheet(Dto);
        return list;
    }
    public List<VendorContractExportDto> getEmployeeDetails(VendorContractExportDto Dto){
        List<VendorContractExportDto> list = dao.getEmployeeDetails(Dto);
        return list;
    }
    public List<VendorContractExportDto> getEmployeeList(VendorContractExportDto Dto){
        List<VendorContractExportDto> list = dao.getEmployeeList(Dto);
        return list;
    }
    public List<VendorContractExportDto> getConsolidateEmployeeList(VendorContractExportDto Dto){
        List<VendorContractExportDto> list = dao.getConsolidateEmployeeList(Dto);
        return list;
    }
    public List<VendorContractExportDto> getVendorList(){
        List<VendorContractExportDto> list = dao.getVendorList();
        return list;
    }
    public String getEmployeeListCount(VendorContractExportDto Dto){
        String record_count = dao.getEmployeeListCount(Dto);
        return record_count;
    }
    public List<VendorContractExportDto> getSearchEmployeeList(String employee_search){
        List<VendorContractExportDto> list = dao.getSearchEmployeeList(employee_search);
        return list;
    }
}
