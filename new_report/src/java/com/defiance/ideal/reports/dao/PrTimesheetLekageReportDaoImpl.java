package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.OJFSurveyReportDTO;
import com.defiance.ideal.reports.dto.PrLastBilledReportDTO;
import com.defiance.ideal.reports.dto.PrTimesheetLekageReportDTO;
import com.defiance.ideal.reports.dto.UtilizationReportDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class PrTimesheetLekageReportDaoImpl extends SqlMapClientDaoSupport implements PrTimesheetLekageReportDao {

    public List<PrTimesheetLekageReportDTO> getCustomerList() {
        List<PrTimesheetLekageReportDTO> customerList = null;
        try {
            customerList = getSqlMapClientTemplate().queryForList("PrTimesheetLekageReport.getCustomerList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public List<PrTimesheetLekageReportDTO> getProjectList(PrTimesheetLekageReportDTO filterData) {

        List<PrTimesheetLekageReportDTO> projectList = null;
       
        try {
            String customer_id=filterData.getCust_name();
            System.out.println("customer_id: " + customer_id);
            projectList = getSqlMapClientTemplate().queryForList("PrTimesheetLekageReport.getProjectList", customer_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public List<PrTimesheetLekageReportDTO> getPrTimesheetLekageReport(PrTimesheetLekageReportDTO filterData) {
        List<PrTimesheetLekageReportDTO> timesheetLekageReport = null;
        try {
            timesheetLekageReport = getSqlMapClientTemplate().queryForList("PrTimesheetLekageReport.getPrTimesheetLekageReport", filterData);
            System.out.println("count   " + timesheetLekageReport.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timesheetLekageReport;
    }
    
    public List<UtilizationReportDTO> getUtilizationReport(UtilizationReportDTO filterData) {
        List<UtilizationReportDTO> UtilizationReport = null;
        try {
            UtilizationReport = getSqlMapClientTemplate().queryForList("PrTimesheetLekageReport.getUtilizationReport", filterData);
            System.out.println("count   " + UtilizationReport.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UtilizationReport;
    }
    
    public List<OJFSurveyReportDTO> getOJFSurveyReport(OJFSurveyReportDTO filterData){
        List<OJFSurveyReportDTO> OJFSurveyReport = null;
        try {
            OJFSurveyReport = getSqlMapClientTemplate().queryForList("PrTimesheetLekageReport.getOJFSurveyReport", filterData);
            System.out.println("count   " + OJFSurveyReport.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OJFSurveyReport;
    
    }
    
     public int getCustomerListCount(OJFSurveyReportDTO filterData) {
        int customerListcount = 0;
        try {
            customerListcount = (Integer)getSqlMapClientTemplate().queryForObject("PrTimesheetLekageReport.getCustomerListCount",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerListcount;
    }
        
      public List<OJFSurveyReportDTO> getCustomerListLimit(OJFSurveyReportDTO filterData) {
        List<OJFSurveyReportDTO> customerListLimit = null;
        
        try {
            customerListLimit = getSqlMapClientTemplate().queryForList("PrTimesheetLekageReport.getCustomerListLimit",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerListLimit;
    }
      
     public List<PrTimesheetLekageReportDTO> getInvoiceAnnexureReport(PrTimesheetLekageReportDTO filterData){
          List<PrTimesheetLekageReportDTO> invoiceAnnexureReport = null;
        try {
            invoiceAnnexureReport = getSqlMapClientTemplate().queryForList("PrTimesheetLekageReport.getInvoiceAnnexureReport", filterData);
            System.out.println("count   " + invoiceAnnexureReport.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceAnnexureReport;        
     }
     
     public List<PrTimesheetLekageReportDTO> getWorkLocationReport(PrTimesheetLekageReportDTO filterData) {
        List<PrTimesheetLekageReportDTO> workstationList = null;
        try {
            workstationList = getSqlMapClientTemplate().queryForList("PrTimesheetLekageReport.getWorkLocationReport");
//            workstationList = getSqlMapClientTemplate().queryForList("PrTimesheetLekageReport.getLimitedWorkLocationReport");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workstationList;
    }
     
    public List<PrLastBilledReportDTO> getPrLastBilledReport(PrLastBilledReportDTO filterData) {
         List<PrLastBilledReportDTO> prLastBilledReport = null;
        try {
            prLastBilledReport = getSqlMapClientTemplate().queryForList("PrTimesheetLekageReport.getPrLastBilledReport", filterData);
            System.out.println("count   " + prLastBilledReport.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prLastBilledReport;
    } 
}
