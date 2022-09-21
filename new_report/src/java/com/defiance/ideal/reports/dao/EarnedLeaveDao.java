/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.EarnedLeaveDataDTO;
import com.defiance.ideal.reports.dto.EarnedLeaveFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public interface EarnedLeaveDao {
    public Map<String, String> getSbuList();
    public List<EarnedLeaveDataDTO> getLeaveRecords(EarnedLeaveFilterDTO filterData);
    public List<EarnedLeaveDataDTO> getEncashRecords(EarnedLeaveFilterDTO filterData);
    public List<EarnedLeaveDataDTO> getLopRecords(EarnedLeaveFilterDTO filterData);
//    public Map<String, String> getSearchList(String empval);
    public List<EarnedLeaveDataDTO> getSearchList(String empval);

}
