/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dao;

import com.defiance.ideal.travelplan.dto.TravelSettlementDto;
import java.util.List;

/**
 *
 * @author 16113
 */
public interface TravelSettlementDao {
    public TravelSettlementDto travelDetails(String ticketRef);
    public List<TravelSettlementDto> getCategories();
    public List<TravelSettlementDto> eligibilityList(String empId);
    public List<TravelSettlementDto> adminSettlemntHotelDetails(String masterId);
    public List<TravelSettlementDto> adminSettlemntTicDetails(String masterId);
    public List<TravelSettlementDto> adminSettlemntConvDetails(String masterId);
}
