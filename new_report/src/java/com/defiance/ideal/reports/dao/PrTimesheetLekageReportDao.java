package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.OJFSurveyReportDTO;
import com.defiance.ideal.reports.dto.PrLastBilledReportDTO;
import com.defiance.ideal.reports.dto.PrTimesheetLekageReportDTO;
import com.defiance.ideal.reports.dto.UtilizationReportDTO;
import java.util.List;

public interface PrTimesheetLekageReportDao {
    
    public List<PrTimesheetLekageReportDTO> getCustomerList();
    public List<PrTimesheetLekageReportDTO> getProjectList(PrTimesheetLekageReportDTO filterData);
    public List<PrTimesheetLekageReportDTO> getPrTimesheetLekageReport(PrTimesheetLekageReportDTO filterData);
    public List<UtilizationReportDTO> getUtilizationReport(UtilizationReportDTO filterData);
    public List<OJFSurveyReportDTO> getOJFSurveyReport(OJFSurveyReportDTO filterData);
    public int getCustomerListCount(OJFSurveyReportDTO filterData);
    public List<OJFSurveyReportDTO> getCustomerListLimit(OJFSurveyReportDTO filterData);
    public List<PrTimesheetLekageReportDTO> getInvoiceAnnexureReport(PrTimesheetLekageReportDTO filterData);
    public List<PrTimesheetLekageReportDTO> getWorkLocationReport(PrTimesheetLekageReportDTO filterData);
    public List<PrLastBilledReportDTO> getPrLastBilledReport(PrLastBilledReportDTO filterData);
}
