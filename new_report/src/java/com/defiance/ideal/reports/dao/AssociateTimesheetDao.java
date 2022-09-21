/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AssociateTimesheetDataDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public interface AssociateTimesheetDao {

    public Map<String, String> getSbuList();
    public List<AssociateTimesheetDataDTO> getReportList(AssociateTimesheetDataDTO filterData);
    public List<AssociateTimesheetDataDTO> fetchAccrualData(AssociateTimesheetDataDTO filterData);
    public AssociateTimesheetDataDTO getSummation(AssociateTimesheetDataDTO filterData);
 

}
