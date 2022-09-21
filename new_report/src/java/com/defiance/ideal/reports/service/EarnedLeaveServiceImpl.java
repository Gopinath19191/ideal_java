/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.EarnedLeaveDao;
import com.defiance.ideal.reports.dto.EarnedLeaveDataDTO;
import com.defiance.ideal.reports.dto.EarnedLeaveFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public class EarnedLeaveServiceImpl implements EarnedLeaveService{
    public EarnedLeaveDao earnedLeaveDao;

    public EarnedLeaveDao getEarnedLeaveDao() {
        return earnedLeaveDao;
    }

    public void setEarnedLeaveDao(EarnedLeaveDao earnedLeaveDao) {
        this.earnedLeaveDao = earnedLeaveDao;
    }
    public Map<String,String> getSbuList(){
        return earnedLeaveDao.getSbuList();
    }
    
    public List<EarnedLeaveDataDTO> getLeaveRecord(EarnedLeaveFilterDTO filterData){
        List<EarnedLeaveDataDTO> dataDto=earnedLeaveDao.getLeaveRecords(filterData);
        return dataDto;
    }

    public List<EarnedLeaveDataDTO> getEncashRecord(EarnedLeaveFilterDTO filterData){
        List<EarnedLeaveDataDTO> dataDto=earnedLeaveDao.getEncashRecords(filterData);
        return dataDto;
    }

    public List<EarnedLeaveDataDTO> getLopRecord(EarnedLeaveFilterDTO filterData){
        List<EarnedLeaveDataDTO> dataDto=earnedLeaveDao.getLopRecords(filterData);
        return dataDto;
    }

    public List<EarnedLeaveDataDTO> getSearchList(String empval){
        return earnedLeaveDao.getSearchList(empval);
    }
//    public Map<String,String> getSearchList(String empval){
//        return earnedLeaveDao.getSearchList(empval);
//    }
}
