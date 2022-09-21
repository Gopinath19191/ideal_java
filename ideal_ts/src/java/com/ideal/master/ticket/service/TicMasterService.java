/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.master.ticket.service;

import com.ideal.master.ticket.dao.TicMasterDaoImpl;
import com.ideal.master.ticket.dto.FeedbackMasterDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 16113
 */
public class TicMasterService {
     TicMasterDaoImpl daoImpl = new TicMasterDaoImpl();

    public TicMasterDaoImpl getDaoImpl() {
        return daoImpl;
    }

    public void setDaoImpl(TicMasterDaoImpl daoImpl) {
        this.daoImpl = daoImpl;
    }
     
    public List<FeedbackMasterDto> showFeedback(){       
        List<FeedbackMasterDto> itype = daoImpl.showFeedback();
        return itype;
    }
    
    public void addIssue(String issueName, String status[]){
        
        daoImpl.addIssue(issueName, status);
    }
    
    public FeedbackMasterDto editIssue(String issueId){
        
       return daoImpl.editIssue(issueId);
    }
    
    public List<FeedbackMasterDto> getStatusList(String parent_id){
        
        return daoImpl.getStatusList(parent_id);
    }
    
    public void deleteIssue(String id){
         daoImpl.deleteIssue(id);
    }
    
    public void updateIssue(FeedbackMasterDto dto){
         
         daoImpl.updateIssue(dto);
    }
    
    public void updateStaus(List<FeedbackMasterDto> list){
         daoImpl.updateStatus(list);
         
    }
    
    public void insertStatus(List<FeedbackMasterDto> list){
         daoImpl.insertStatus(list);
    }
    
}
