/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.RMNonComplianceDataDTO;
import java.util.List;

/**
 *
 * @author 16221
 */
public interface RMNonComplianceDao {
    public List<RMNonComplianceDataDTO> timesheetReport(RMNonComplianceDataDTO filterData);
    public int leaveDetails(RMNonComplianceDataDTO filterData);
}
