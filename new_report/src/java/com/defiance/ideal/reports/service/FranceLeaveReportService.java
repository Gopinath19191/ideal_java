/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.FranceLeaveReportDTO;
import java.util.List;

/**
 *
 * @author 16656
 */
public interface FranceLeaveReportService {

    public List<FranceLeaveReportDTO> getFranceLeaveDetails(FranceLeaveReportDTO dto);

    public List<FranceLeaveReportDTO> getSearchList(String empVal);

    public int getLeaveCount(FranceLeaveReportDTO dto);
}
