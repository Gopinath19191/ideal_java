/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.TeamAllocationReportDaoImpl;
import com.defiance.ideal.reports.dto.TeamAllocationReportDTO;
import java.util.List;


/**
 *
 * @author 15332
 */
public class TeamAllocationReportServiceImpl implements TeamAllocationReportService {
    
    public TeamAllocationReportDaoImpl  teamAllocationReportDaoImpl;

    public TeamAllocationReportDaoImpl getTeamAllocationReportDaoImpl() {
        return teamAllocationReportDaoImpl;
    }

    public void setTeamAllocationReportDaoImpl(TeamAllocationReportDaoImpl teamAllocationReportDaoImpl) {
        this.teamAllocationReportDaoImpl = teamAllocationReportDaoImpl;
    }

    public List<TeamAllocationReportDTO> getRollOutreport(TeamAllocationReportDTO formData) {
        return teamAllocationReportDaoImpl.getRollOutreport(formData);
    }

    public List<TeamAllocationReportDTO> getprojectList(TeamAllocationReportDTO formData) {
        return teamAllocationReportDaoImpl.getprojectList(formData);
    }
    
    public List<TeamAllocationReportDTO> getcustomerList(TeamAllocationReportDTO formData) {
        return teamAllocationReportDaoImpl.getcustomerList(formData);
    }

    public List<TeamAllocationReportDTO> getSbuList(TeamAllocationReportDTO formData) {
        return teamAllocationReportDaoImpl.getSbuList(formData);
    }
    public List<TeamAllocationReportDTO> getPmNameList(){
        return teamAllocationReportDaoImpl.getPmNameList();
    }
    public List<TeamAllocationReportDTO> getsubSbuList(String sbuId){
        return teamAllocationReportDaoImpl.getsubSbuList(sbuId);
    }
    
    
}
