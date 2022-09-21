/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.TravelReportDetails;
import com.defiance.ideal.reports.dto.TravelReportFilterDTO;
import java.util.List;

/**
 *
 * @author 15261
 */
public interface TravelReportDao {

    public List<TravelReportDetails> getProjectDetailsList();
    public List<TravelReportDetails> getTravelReportList(TravelReportFilterDTO dto);
    public List<TravelReportDetails> getSbuList(TravelReportFilterDTO dto);
    public List<TravelReportDetails> getSBUDescription(TravelReportFilterDTO dto);
    public List<TravelReportDetails> getSubSBUDescription(TravelReportFilterDTO dto);
    
}
