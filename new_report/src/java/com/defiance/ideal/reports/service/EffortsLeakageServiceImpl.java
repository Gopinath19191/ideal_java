/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.EffortsLeakageDao;
import com.defiance.ideal.reports.dto.EffortsLeakageDataDTO;
import com.defiance.ideal.reports.dto.SearchDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 16221
 */
public class EffortsLeakageServiceImpl implements EffortsLeakageService{
    public EffortsLeakageDao dao;

    public EffortsLeakageDao getDao() {
        return dao;
    }

    public void setDao(EffortsLeakageDao dao) {
        this.dao = dao;
    }
    public List<EffortsLeakageDataDTO> getData(EffortsLeakageDataDTO filterData){
        List<EffortsLeakageDataDTO> data=dao.getData(filterData);
        return data;
    }
    public List<EffortsLeakageDataDTO>getProjectUnbilledDetails(EffortsLeakageDataDTO filterData){
        List<EffortsLeakageDataDTO> data=dao.getProjectUnbilledDetails(filterData);
        return data;
    }
    public List<EffortsLeakageDataDTO>getRevenueLeakageReport(SearchDTO filterData){
        List<EffortsLeakageDataDTO> data=dao.getRevenueLeakageReport(filterData);
        return data;
    }
    public List<SearchDTO> getCustomerList(){
        List<SearchDTO> customer_list = dao.getCustomerList();
        return customer_list;
    }
    public String getParent_id(){
        String parent_id = dao.getParent_id();
        return parent_id;
    }
    public List<SearchDTO> getUnit(){
        List<SearchDTO> unit_list = dao.getUnit();
        return unit_list;
    }
    public List<SearchDTO> getSubUnit(String parent_id){
        List<SearchDTO> sub_unit_list = dao.getSubUnit(parent_id);
        return sub_unit_list;
    }
}
