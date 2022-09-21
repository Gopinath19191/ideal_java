/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.RMNonComplianceDataDTO;
import java.util.List;

/**
 *
 * @author 16221
 */
public interface RMNonComplianceService {
    public List<RMNonComplianceDataDTO> timesheetReport(RMNonComplianceDataDTO filterData);
}
