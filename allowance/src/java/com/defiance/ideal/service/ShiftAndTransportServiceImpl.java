/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.service;

import com.defiance.ideal.dao.ShiftAndTransportDao;
import com.defiance.ideal.dto.SearchDto;
import com.defiance.ideal.dto.ShiftAndTransportDto;
import java.util.List;

/**
 *
 * @author 8000247
 */
public class ShiftAndTransportServiceImpl implements ShiftAndTransportService{
    
    ShiftAndTransportDao dao;

    public ShiftAndTransportDao getDao() {
        return dao;
    }

    public void setDao(ShiftAndTransportDao dao) {
        this.dao = dao;
    }
    
    
    public List<ShiftAndTransportDto> getCustomerList(SearchDto filterData)
    {       
        List<ShiftAndTransportDto> list=dao.getCustomerList(filterData);
        return list;
    }
    public List<ShiftAndTransportDto> getProjectList(SearchDto filterData)
    {
        List<ShiftAndTransportDto> list=dao.getProjectList(filterData);
        return list;
    }
    public List getShiftDetails(){
        List list=dao.getShiftDetails();
        return list;
    }
    public List<ShiftAndTransportDto> getEmployeeDetails(SearchDto filterData)
    {
        List<ShiftAndTransportDto> list=dao.getEmployeeDetails(filterData);
        return list;
    }
    public void insertDetails(ShiftAndTransportDto filterData){
        dao.insertDetails(filterData);
    }
    public List<ShiftAndTransportDto> getSavedDetails(SearchDto filterData)
    {
        List<ShiftAndTransportDto> list=dao.getSavedDetails(filterData);
        return list;
    }
    public void updateDetails(ShiftAndTransportDto filterData){
        dao.updateDetails(filterData);
    }
    public ShiftAndTransportDto submittedCount(SearchDto filterData){
        ShiftAndTransportDto dto = dao.submittedCount(filterData);
        return dto;
    }
    public ShiftAndTransportDto detailsCount(SearchDto filterData){
        ShiftAndTransportDto dto = dao.detailsCount(filterData);
        return dto;
    }
    public List<ShiftAndTransportDto> getSBU_List()
    {
        List<ShiftAndTransportDto> list=dao.getSBU_List();
        return list;
    }
    public List<ShiftAndTransportDto> getSBU_SUB_List(SearchDto filterData)
    {
        List<ShiftAndTransportDto> list=dao.getSBU_SUB_List(filterData);
        return list;
    }
}
