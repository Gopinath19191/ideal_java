/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dao;

import com.defiance.ideal.travelplan.dto.LocalConveyanceDto;
import java.util.List;

/**
 *
 * @author 14578
 */
public interface LocalConveyanceDao {

    public LocalConveyanceDto getEmpDetails(String empId);

    public List<LocalConveyanceDto> getConfigValues(String parentId);

    public List<LocalConveyanceDto> getProjectList(String empId);

    public List<LocalConveyanceDto> getCityList(String countryId);

    public List<LocalConveyanceDto> getTravelPoints(String countryId);

    public LocalConveyanceDto getLastInsertLCId();

    public String insertLocalConveyance(LocalConveyanceDto formDtoObj);

    public void insertHistories(String LCUniqueId, LocalConveyanceDto historyDto);

    public LocalConveyanceDto getLCViewDetails(String tpUniqueId);

    public LocalConveyanceDto getReferenceId(String uniqueId);

    public List<LocalConveyanceDto> getHistoryDetails(String tpUniqueId);

    public String getLastInsertId();
//    public void updateLocalConveyance(String LCUniqueId, LocalConveyanceDto updateDto);
}
