/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.AssociateTimesheetDataDTO;
import com.defiance.ideal.reports.dto.AssociateTimesheetFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public interface AssociateTimesheetService {

    public Map<String, String> getSbuList();

    public List<AssociateTimesheetDataDTO> fetchAccrualData(AssociateTimesheetDataDTO filterData);
    
    public AssociateTimesheetDataDTO getSummation(AssociateTimesheetDataDTO filterData); 

}