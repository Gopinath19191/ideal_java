/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.TravelReportDetails;
import com.defiance.ideal.reports.dto.TravelReportFilterDTO;
import java.util.List;

/**
 *
 * @author 15261
 */
public interface TravelReportService {

    public List<TravelReportDetails> getProjectDetails();
    public List<TravelReportDetails> getTravelReportList(TravelReportFilterDTO dto);
    public List<TravelReportDetails> getSbuList(TravelReportFilterDTO dto);
    public List<TravelReportDetails> getSBUDescription(TravelReportFilterDTO dto);
    public List<TravelReportDetails> getSubSBUDescription(TravelReportFilterDTO dto);

}
