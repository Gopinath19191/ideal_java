/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.SubordinateLeaveDataDTO;
import com.defiance.ideal.reports.dto.SubordinateLeaveFilterDTO;
import java.util.List;

/**
 *
 * @author 16047
 */
public interface SubordinateLeaveDao {

    public List<SubordinateLeaveDataDTO> getSubordinateLeaveRecord(SubordinateLeaveFilterDTO filterData);

    public List<SubordinateLeaveDataDTO> getSearchList(String empVal,String rmId);

    public int getSubordinateLeaveRecordCount(SubordinateLeaveFilterDTO filterData);
    
}
