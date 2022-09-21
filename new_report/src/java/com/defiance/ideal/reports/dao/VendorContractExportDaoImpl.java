/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.VendorContractExportDto;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16221
 */
public class VendorContractExportDaoImpl extends SqlMapClientDaoSupport implements VendorContractExportDao {
    
    public List<VendorContractExportDto> getExportEmployeeTimesheet(VendorContractExportDto Dto){
        List<VendorContractExportDto> timesheet_details = null;
        try{
            timesheet_details = getSqlMapClientTemplate().queryForList("vendorContractTimesheet.getTimesheetDetails", Dto);
        }catch(Exception e){
        }
        return timesheet_details;
    }
    
    public List<VendorContractExportDto> getEmployeeDetails(VendorContractExportDto Dto){
        List<VendorContractExportDto> employee_details = null;
        try{
            employee_details = getSqlMapClientTemplate().queryForList("vendorContractTimesheet.getEmployeeDetails", Dto);
        }catch(Exception e){
        }
        return employee_details;
    }
    
    public List<VendorContractExportDto> getEmployeeList(VendorContractExportDto Dto){
        List<VendorContractExportDto> employee_list = null;
        try{
            employee_list = getSqlMapClientTemplate().queryForList("vendorContractTimesheet.getEmployeeList", Dto);
        }catch(Exception e){
        }
        return employee_list;
    }
    
    public List<VendorContractExportDto> getConsolidateEmployeeList(VendorContractExportDto Dto){
        List<VendorContractExportDto> consolidate_employee_list = null;
        try{
            consolidate_employee_list = getSqlMapClientTemplate().queryForList("vendorContractTimesheet.getConsolidateEmployeeList", Dto);
        }catch(Exception e){
        }
        return consolidate_employee_list;
    }
    
    public List<VendorContractExportDto> getVendorList(){
        List<VendorContractExportDto> vendor_list = null;
        try{
            vendor_list = getSqlMapClientTemplate().queryForList("vendorContractTimesheet.getVendorList");
        }catch(Exception e){
        }
        return vendor_list;
    }
    
    public String getEmployeeListCount(VendorContractExportDto Dto){
        String record_count = null;
        try{
            record_count = (String)getSqlMapClientTemplate().queryForObject("vendorContractTimesheet.getEmployeeListCount", Dto);
        }catch(Exception e){
        }
        return record_count;
    }
    
    public List<VendorContractExportDto> getSearchEmployeeList(String employee_search){
        List<VendorContractExportDto> employee_list = null;
        String key = employee_search+"%";
        try{
            employee_list = getSqlMapClientTemplate().queryForList("vendorContractTimesheet.getSearchEmployeeList", key);
        }catch(Exception e){
        }
        return employee_list;
    }
}
