/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AssociateDataDTO;
import com.defiance.ideal.reports.dto.EmployeeBasicReportDTO;
import java.util.List;

/**
 *
 * @author 16113
 */
public interface EmployeeBasicReportDAO {
     public List<EmployeeBasicReportDTO> fetchAssociate(AssociateDataDTO dto);
    public int fetchAssociateListCount(AssociateDataDTO dto);
}
