/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.ExitReportDao;
import com.defiance.ideal.reports.dao.ExitReportDaoImpl;
import com.defiance.ideal.reports.dto.ExitReportDTO;
import java.util.List;

/**
 *
 * @author 14578
 */
public class ExitReportServiceImpl {

    public ExitReportDaoImpl exitreportDaoImpl;

    public ExitReportDaoImpl getExitreportDaoImpl() {
        return exitreportDaoImpl;
    }

    public void setExitreportDaoImpl(ExitReportDaoImpl exitreportDaoImpl) {
        this.exitreportDaoImpl = exitreportDaoImpl;
    }

    public List<ExitReportDTO> getExitreport(String exitTriggerDate) {
        return exitreportDaoImpl.getExitReport(exitTriggerDate);
    }
}
