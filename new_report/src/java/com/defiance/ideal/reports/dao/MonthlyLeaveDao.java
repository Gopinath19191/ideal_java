/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.MonthlyLeaveDataDTO;
import com.defiance.ideal.reports.dto.MonthlyLeaveFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public interface MonthlyLeaveDao {
    public Map<String, String> getSbuList();
    public List<MonthlyLeaveFilterDTO> getStructureList();
    public List<MonthlyLeaveDataDTO> getLeaveRecords(MonthlyLeaveFilterDTO filterData);

    public String getEmployeeName(String employee_id);

}
