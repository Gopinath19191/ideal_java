/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.BillableHoursDataDTO;
import com.defiance.ideal.reports.dto.BillableHoursFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public interface  BillableHoursService {
    public Map<String, String> getSbuList();
    public Map<String, String> getCustomerList();
    public List getProjectList(String sbuId,String subSbuId);
    public List<BillableHoursDataDTO> fetchBillableHoursData(BillableHoursFilterDTO filterData);
}
