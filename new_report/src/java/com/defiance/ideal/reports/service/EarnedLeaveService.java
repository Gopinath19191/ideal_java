/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.EarnedLeaveDataDTO;
import com.defiance.ideal.reports.dto.EarnedLeaveFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public interface EarnedLeaveService {
    public Map<String,String> getSbuList();
    public List getLeaveRecord(EarnedLeaveFilterDTO filterData);
    public List getEncashRecord(EarnedLeaveFilterDTO filterData);
    public List getLopRecord(EarnedLeaveFilterDTO filterData);
//    public Map<String,String> getSearchList(String empval);
    public List<EarnedLeaveDataDTO> getSearchList(String empval);

}
