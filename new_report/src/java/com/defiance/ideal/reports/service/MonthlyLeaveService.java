/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.MonthlyLeaveFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public interface MonthlyLeaveService {
    public Map<String,String> getSbuList();
    public List<MonthlyLeaveFilterDTO> getStructureList();
    public List getLeaveRecord(MonthlyLeaveFilterDTO filterData);

    public String getEmployeeName(String employee_id);

}
