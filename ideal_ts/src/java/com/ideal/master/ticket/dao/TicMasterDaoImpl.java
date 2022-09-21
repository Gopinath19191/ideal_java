/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.master.ticket.dao;

import com.ideal.master.ticket.dto.FeedbackMasterDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16113
 */
public class TicMasterDaoImpl extends SqlMapClientDaoSupport {

    public List<FeedbackMasterDto> showFeedback() {
        List<FeedbackMasterDto> itype = null;
        try {
            itype = (List<FeedbackMasterDto>) getSqlMapClient().queryForList("TicMasterMap.getTikmaster");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itype;
    }
    public void addIssue(String issueName, String status[]) {
        String confKey = issueName.substring(0, 1);
        String issueId = null;
        FeedbackMasterDto dto = new FeedbackMasterDto();
        dto.setParent_id("0");
        dto.setConfiguration_key(confKey);
        dto.setConfiguration_value(issueName);
        try {
            issueId = (String) getSqlMapClient().insert("TicMasterMap.insertissue", dto);
            for (int i = 0; i < status.length; i++) {
                String statusval = status[i];
                if (statusval != "" || statusval != null) {
                    String statusKey = statusval.substring(0, 2);
                    dto.setConfiguration_key(statusKey);
                }
                dto.setParent_id(issueId);

                dto.setConfiguration_value(statusval);
                if (status[i] != null || status[i] != "") {
                    getSqlMapClient().insert("TicMasterMap.insertStatus", dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public FeedbackMasterDto editIssue(String id) {
        FeedbackMasterDto singleDto = null;
        try {
            singleDto = (FeedbackMasterDto) getSqlMapClient().queryForObject("TicMasterMap.getSingleIssue", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return singleDto;
    }
    public List<FeedbackMasterDto> getStatusList(String parent_id) {
        List<FeedbackMasterDto> statusList = null;
        try {
            statusList = (List<FeedbackMasterDto>) getSqlMapClient().queryForList("TicMasterMap.getStatusList", parent_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusList;
    }
    public void updateIssue(FeedbackMasterDto dto) {
        List<FeedbackMasterDto> itype = null;
        try {
            getSqlMapClient().update("TicMasterMap.updateIssue", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateStatus(List<FeedbackMasterDto> list) {
        for (FeedbackMasterDto dto : list) {
            try {
                getSqlMapClient().update("TicMasterMap.updateStatus", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void insertStatus(List<FeedbackMasterDto> list) {
        try {
            for (FeedbackMasterDto dto : list) {
                getSqlMapClient().insert("TicMasterMap.insertStatus", dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteIssue(String id) {
        try {
            getSqlMapClient().delete("TicMasterMap.deleteIssue", id);
            getSqlMapClient().delete("TicMasterMap.deleteStatus", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }     
}
