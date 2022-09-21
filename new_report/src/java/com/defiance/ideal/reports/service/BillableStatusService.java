/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.BillableStatusDataDTO;
import com.defiance.ideal.reports.dto.BillableStatusFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public interface BillableStatusService {

    public Map<String, String> getSbuList();

    public List<BillableStatusDataDTO> fetchBillableAssociateData(BillableStatusFilterDTO filterData);

//  public List<AccrualDataDTO> fetchAccrualData(AccrualFilterDTO filterData);

}

