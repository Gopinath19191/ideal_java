/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.BillingInvoiceReportDataDTO;
import com.defiance.ideal.reports.dto.BillingInvoiceReportFilterDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16047
 */
public class BillingInvoiceReportDaoImpl extends SqlMapClientDaoSupport implements BillingInvoiceReportDao{
    private String sbuId;

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }
    
    public List<BillingInvoiceReportDataDTO> getBillingInvoiceList(BillingInvoiceReportFilterDTO formData){
        List<BillingInvoiceReportDataDTO> billingInvoiceList = null;
        try{
            billingInvoiceList = getSqlMapClientTemplate().queryForList("billingInvoiceReportMap.getBillingInvoiceList", formData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return billingInvoiceList;
    }
    
    public List<BillingInvoiceReportFilterDTO> getSearchList(String empVal){
        List<BillingInvoiceReportFilterDTO> getSearchList = null;
        String key = "%"+empVal+"%";
        try{
            getSearchList = getSqlMapClientTemplate().queryForList("billingInvoiceReportMap.getSearchList", key);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getSearchList;
    }
    
    public List<BillingInvoiceReportFilterDTO> getProjectSearchList(String prjVal){
        List<BillingInvoiceReportFilterDTO> getProjectSearchList = null;
        String key = "%"+prjVal+"%";
        try{
            getProjectSearchList = getSqlMapClientTemplate().queryForList("billingInvoiceReportMap.getProjectSearchList", key);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getProjectSearchList;
    }
    
    public List<BillingInvoiceReportFilterDTO> getCustomerSearchList(String custVal){
        List<BillingInvoiceReportFilterDTO> getCustomerSearchList = null;
        String key = "%"+custVal+"%";
        try{
            getCustomerSearchList = getSqlMapClientTemplate().queryForList("billingInvoiceReportMap.getCustomerSearchList", key);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getCustomerSearchList;
    }
    
    public Map<String, String> getPrjSbuList(){
        Map<String, String> getPrjSbuList = new LinkedHashMap<String, String>();
        try {
            getPrjSbuList = getSqlMapClientTemplate().queryForMap("billingInvoiceReportMap.getPrjSbuList",sbuId,"projectSbuId", "projectSbuName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getPrjSbuList;
    }
    
    public Map<String, String> getEmpSbuList(){
         Map<String, String> getEmpSbuList = new LinkedHashMap<String, String>();
        try {
            getEmpSbuList = getSqlMapClientTemplate().queryForMap("billingInvoiceReportMap.getEmpSbuList",sbuId,"employeeSbuId", "employeeSbuName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getEmpSbuList;
    }
    
    public List<BillingInvoiceReportDataDTO> getBillingYearList(){
        List<BillingInvoiceReportDataDTO> getBillingYearList = null;
        try {
            //getBillingYearList = getSqlMapClientTemplate().queryForMap("billingInvoiceReportMap.getBillingYearList","","yearId", "yearName");
            getBillingYearList = getSqlMapClientTemplate().queryForList("billingInvoiceReportMap.getBillingYearList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getBillingYearList;
    }
    
    public List<BillingInvoiceReportDataDTO> getBillingMonthList(){
        List<BillingInvoiceReportDataDTO> getBillingMonthList = null;
        try {
            getBillingMonthList = getSqlMapClientTemplate().queryForList("billingInvoiceReportMap.getBillingMonthList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getBillingMonthList;
    }
    
    public List<BillingInvoiceReportDataDTO> getBilingAdviceFilterList(){
        List<BillingInvoiceReportDataDTO> getBilingAdviceFilterList = null;
        try {
            getBilingAdviceFilterList = getSqlMapClientTemplate().queryForList("billingInvoiceReportMap.getBilingAdviceFilterList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getBilingAdviceFilterList;
    }
    
    public List<BillingInvoiceReportDataDTO> getAccrualSnoList(){
        List<BillingInvoiceReportDataDTO> getAccrualSnoList = null;
        try {
            getAccrualSnoList = getSqlMapClientTemplate().queryForList("billingInvoiceReportMap.getAccrualSnoList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getAccrualSnoList;
    }
    
    public String getEmployeeName(String employee_id){
        String getEmployeeName = "";
        try{
            getEmployeeName = (String)getSqlMapClientTemplate().queryForObject("billingInvoiceReportMap.getEmployeeName", employee_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getEmployeeName;
    }
    
    public String getProjectName(String projectId){
        String getProjectName = "";
        try{
            getProjectName = (String)getSqlMapClientTemplate().queryForObject("billingInvoiceReportMap.getProjectName", projectId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getProjectName;
    }
    
    public String getCustomerName(String customerId){
        String getCustomerName = "";
        try{
            getCustomerName = (String)getSqlMapClientTemplate().queryForObject("billingInvoiceReportMap.getCustomerName", customerId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getCustomerName;
    }
    
    public int getBillingInvoiceListCount(BillingInvoiceReportFilterDTO formData){
        int getBillingInvoiceListCount = 0;
        try{
            getBillingInvoiceListCount = (Integer)getSqlMapClientTemplate().queryForObject("billingInvoiceReportMap.getBillingInvoiceListCount", formData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getBillingInvoiceListCount;
    }
}
