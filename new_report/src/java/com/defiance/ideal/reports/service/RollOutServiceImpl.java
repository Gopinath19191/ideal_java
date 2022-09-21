/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.RollOutDaoImpl;
import com.defiance.ideal.reports.dto.RollOutDTO;
import java.util.List;

/**
 *
 * @author 14578
 */
public class RollOutServiceImpl implements RollOutService {

    public RollOutDaoImpl rollOutDaoImpl;

    public RollOutDaoImpl getRollOutDaoImpl() {
        return rollOutDaoImpl;
    }

    public void setRollOutDaoImpl(RollOutDaoImpl rollOutDaoImpl) {
        this.rollOutDaoImpl = rollOutDaoImpl;
    }

    public List<RollOutDTO> getRollOutreport(RollOutDTO formData) {
        return rollOutDaoImpl.getRollOutreport(formData);
    }

    public List<RollOutDTO> getprojectList() {
        return rollOutDaoImpl.getprojectList();
    }

    public List<RollOutDTO> getSbuList(RollOutDTO sbuId) {
        return rollOutDaoImpl.getSbuList(sbuId);
    }
    public List<RollOutDTO> getPmNameList(){
        return rollOutDaoImpl.getPmNameList();
    }
    public List<RollOutDTO> getsubSbuList(String sbuId){
        return rollOutDaoImpl.getsubSbuList(sbuId);
    }
}
