/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.TravelReportDaoImpl;
import com.defiance.ideal.reports.dto.TravelReportDetails;
import com.defiance.ideal.reports.dto.TravelReportFilterDTO;
import java.util.List;

/**
 *
 * @author 15261
 */
public class TravelReportServiceImpl implements TravelReportService {

    public TravelReportDaoImpl travelReportDaoImpl;

    public TravelReportDaoImpl getTravelReportDaoImpl() {
        return travelReportDaoImpl;
    }

    public void setTravelReportDaoImpl(TravelReportDaoImpl travelReportDaoImpl) {
        this.travelReportDaoImpl = travelReportDaoImpl;
    }


    public List<TravelReportDetails> getProjectDetails(){
        return travelReportDaoImpl.getProjectDetailsList();
    }

    public List<TravelReportDetails> getTravelReportList(TravelReportFilterDTO dto){
        return travelReportDaoImpl.getTravelReportList(dto);
    }
    public List<TravelReportDetails> getSbuList(TravelReportFilterDTO dto){
        return travelReportDaoImpl.getSbuList(dto);
    }

    public List<TravelReportDetails> getSBUDescription(TravelReportFilterDTO dto){
        return travelReportDaoImpl.getSBUDescription(dto);
    }
    public List<TravelReportDetails> getSubSBUDescription(TravelReportFilterDTO dto){
        return travelReportDaoImpl.getSubSBUDescription(dto);
    }
}
