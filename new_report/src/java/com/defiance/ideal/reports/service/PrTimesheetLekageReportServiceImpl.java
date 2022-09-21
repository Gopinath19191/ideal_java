package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.PrTimesheetLekageReportDao;
import com.defiance.ideal.reports.dto.OJFSurveyReportDTO;
import com.defiance.ideal.reports.dto.PrLastBilledReportDTO;
import com.defiance.ideal.reports.dto.PrTimesheetLekageReportDTO;
import com.defiance.ideal.reports.dto.UtilizationReportDTO;
import java.util.List;

public class PrTimesheetLekageReportServiceImpl implements PrTimesheetLekageReportService {

    PrTimesheetLekageReportDao dao;

    public PrTimesheetLekageReportDao getDao() {
        return dao;
    }

    public void setDao(PrTimesheetLekageReportDao dao) {
        this.dao = dao;
    }

    public List<PrTimesheetLekageReportDTO> getCustomerList() {
        List<PrTimesheetLekageReportDTO> customerList = dao.getCustomerList();
        return customerList;
    }

    public List<PrTimesheetLekageReportDTO> getProjectList(PrTimesheetLekageReportDTO filterData) {
        List<PrTimesheetLekageReportDTO> projectList = dao.getProjectList(filterData);
        return projectList;
    }

    public List<PrTimesheetLekageReportDTO> getPrTimesheetLekageReport(PrTimesheetLekageReportDTO filterData) {
        List<PrTimesheetLekageReportDTO> timesheetLekageReport = dao.getPrTimesheetLekageReport(filterData);
        return timesheetLekageReport;
    }
    
    public List<UtilizationReportDTO> getUtilizationReport(UtilizationReportDTO filterData) {
        List<UtilizationReportDTO> UtilizationReport = dao.getUtilizationReport(filterData);
        return UtilizationReport;
    }
     
     public List<OJFSurveyReportDTO> getOJFSurveyReport(OJFSurveyReportDTO filterData){
         List<OJFSurveyReportDTO> OJFSurveyReport = dao.getOJFSurveyReport(filterData);
        return OJFSurveyReport;
     }
     
     public int getCustomerListCount(OJFSurveyReportDTO filterData) {      
        return dao.getCustomerListCount(filterData);
    }
     
     public List<OJFSurveyReportDTO> getCustomerListLimit(OJFSurveyReportDTO filterData) {
        List<OJFSurveyReportDTO> customerListLimit = dao.getCustomerListLimit(filterData);
        return customerListLimit;
    }
     
     public List<PrTimesheetLekageReportDTO> getInvoiceAnnexureReport(PrTimesheetLekageReportDTO filterData){
          List<PrTimesheetLekageReportDTO> invoiceAnnexureReport = dao.getInvoiceAnnexureReport(filterData);
        return invoiceAnnexureReport;        
     }
     
      public List<PrTimesheetLekageReportDTO> getWorkLocationReport(PrTimesheetLekageReportDTO filterData) {
        List<PrTimesheetLekageReportDTO> workLocationList = dao.getWorkLocationReport(filterData);
        return workLocationList;
    }
      public List<PrLastBilledReportDTO> getPrLastBilledReport(PrLastBilledReportDTO filterData) {
        List<PrLastBilledReportDTO> prLastBilledReport = dao.getPrLastBilledReport(filterData);
        return prLastBilledReport;
    }
}
