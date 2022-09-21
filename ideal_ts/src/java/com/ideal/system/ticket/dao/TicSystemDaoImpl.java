/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.system.ticket.dao;

import com.ideal.admin.ticket.dto.TicAdminDataDTO;
import com.ideal.system.ticket.dto.TicSystemDataDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 8000246
 */
public class TicSystemDaoImpl extends SqlMapClientDaoSupport implements TicSystemDao {

    final static Logger logger = Logger.getLogger(TicSystemDaoImpl.class);

    public List<TicSystemDataDTO> fetchTicRequest(TicSystemDataDTO dto) {
        List<TicSystemDataDTO> details = null;
        try {
            if ("".equals(dto.getRef_no())) {
                dto.setRef_no(null);
            }
            if ("".equals(dto.getStatus())) {
                dto.setStatus(null);
            }
            details = (List<TicSystemDataDTO>) getSqlMapClient().queryForList("TicSystemMap.getTicRequest", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;

    }

    public int fetchTicRequestListCount(TicSystemDataDTO dto) {
        int TicRequestCount = 0;
        try {
            TicRequestCount = (Integer) getSqlMapClientTemplate().queryForObject("TicSystemMap.fetchTicRequestListCount", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TicRequestCount;
    }

    public List<TicSystemDataDTO> search_feedback_user(TicSystemDataDTO dto) {
        List<TicSystemDataDTO> details = null;
        try {
            if ("".equals(dto.getRef_no())) {
                dto.setRef_no(null);
            }
            if ("".equals(dto.getStatus())) {
                dto.setStatus(null);
            }

            details = (List<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.searchFeedback_user", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public List<TicSystemDataDTO> getRefeenceDetails(String refVal) {
        List<TicSystemDataDTO> refIds = null;
        refVal = "%" + refVal + "%";
        try {
            refIds = getSqlMapClientTemplate().queryForList("TicSystemMap.getRefIds", refVal);
        } catch (Exception e) {
        }
        return refIds;
    }

    public List<TicSystemDataDTO> getEmpDetails(String refVal) {
        List<TicSystemDataDTO> refEmp = null;
        int len = refVal.split("-").length;
        if (len > 1) {
            refVal = refVal.split("-")[1].trim();
        } else {
            refVal = "'%" + refVal + "%'";
        }
        try {
            refEmp = getSqlMapClientTemplate().queryForList("TicSystemMap.getEmpIds", refVal);
        } catch (Exception e) {
        }
        return refEmp;
    }

    public String getEmpMail(String refVal) {
        String refEmp = null;
        try {
            refEmp = (String) getSqlMapClientTemplate().queryForObject("TicSystemMap.getEmpMail", refVal);
        } catch (Exception e) {
        }

        return refEmp;
    }

    public void removeFile(String refVal) {
        try {
            getSqlMapClientTemplate().update("TicSystemMap.removeFile", refVal);
        } catch (Exception e) {
        }
    }

    public List<TicSystemDataDTO> getTicRequestByRef(TicSystemDataDTO dto) {
        List<TicSystemDataDTO> details = null;
        try {
            details = (List<TicSystemDataDTO>) getSqlMapClient().queryForList("TicSystemMap.getTicRequestByRef", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;

    }

    public TicSystemDataDTO insertRequest(TicSystemDataDTO dto) {
        TicSystemDataDTO details = new TicSystemDataDTO();
         String lastFileInsertId = null;
        try {
            lastFileInsertId = (String) getSqlMapClientTemplate().insert("TicSystemMap.insertRequest", dto);
            details.setLastInsertId(lastFileInsertId);
        } catch (Exception e) {
        }
        return details;
    }

    public TicSystemDataDTO insertResponse(TicSystemDataDTO dto) {
        TicSystemDataDTO details = new TicSystemDataDTO();
        try {
            details = (TicSystemDataDTO) getSqlMapClientTemplate().insert("TicSystemMap.insertResponse", dto);
        } catch (Exception e) {
        }
        return details;
    }

    public TicSystemDataDTO insertResponseEdit(TicSystemDataDTO dto) {
        TicSystemDataDTO details = new TicSystemDataDTO();
         String lastFileInsertId = null;
        try {
            lastFileInsertId = (String) getSqlMapClientTemplate().insert("TicSystemMap.insertResponseEdit", dto);
            details.setLastInsertId(lastFileInsertId);
        } catch (Exception e) {
        }
        return details;
    }

    public int getHighestRefNo(TicSystemDataDTO hr) {
        int highest_ref = 0;
        try {
            highest_ref = (Integer) getSqlMapClient().queryForObject("TicSystemMap.getHighestRefNo", hr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return highest_ref;
    }

    public String getHighestReqId(TicSystemDataDTO dto) {
        String highest_id = null;
        try {
            highest_id = (String) getSqlMapClient().queryForObject("TicSystemMap.getHighestReqId", dto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return highest_id;
    }

    public TicSystemDataDTO updateRequest(TicSystemDataDTO dto) {
        TicSystemDataDTO ulist = new TicSystemDataDTO();
        try {
            ulist = (TicSystemDataDTO) getSqlMapClientTemplate().insert("TicSystemMap.updateRequest", dto);
        } catch (Exception e) {
        }
        return ulist;
    }

    public TicSystemDataDTO updateRequestWithOutStatus(TicSystemDataDTO dto) {
        TicSystemDataDTO ulist = new TicSystemDataDTO();
        try {
            ulist = (TicSystemDataDTO) getSqlMapClientTemplate().insert("TicSystemMap.updateRequestWithOutStatus", dto);
        } catch (Exception e) {
        }
        return ulist;
    }

    public TicSystemDataDTO updateRequestInResponseTbl(TicSystemDataDTO dto) {
        TicSystemDataDTO urlist = new TicSystemDataDTO();
        try {
            urlist = (TicSystemDataDTO) getSqlMapClientTemplate().insert("TicSystemMap.updateRequestInResponseTbl", dto);
        } catch (Exception e) {
        }
        return urlist;
    }

    public List<TicSystemDataDTO> getStatusList(TicSystemDataDTO dto) {
        List<TicSystemDataDTO> statusList = null;
        try {
            statusList = (List<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getStatusList", dto);
        } catch (Exception e) {
        }
        return statusList;
    }

    public List<TicSystemDataDTO> getSystemList() {
        List<TicSystemDataDTO> system_list = null;
        try {
            system_list = (List<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getSystemList");
        } catch (Exception e) {
        }
        return system_list;
    }

    public List<TicSystemDataDTO> getAppAreaList() {
        List<TicSystemDataDTO> area_list = null;
        try {
            area_list = (List<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getAppAreaList");
        } catch (Exception e) {
        }
        return area_list;
    }

    public List<TicSystemDataDTO> getIssueTypeList() {
        List<TicSystemDataDTO> issue_list = null;
        try {
            issue_list = (List<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getIssueTypeList");
        } catch (Exception e) {
        }
        return issue_list;
    }

    public List<TicSystemDataDTO> getSupportTypeList() {
        List<TicSystemDataDTO> request_type = null;
        try {
            request_type = (List<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getSupportTypeList");
        } catch (Exception e) {
        }
        return request_type;
    }

    public List<TicSystemDataDTO> getEmployeeDetailsFromId() {
        List<TicSystemDataDTO> employee_details = null;
        try {
            employee_details = (List<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getEmployeeDetailsFromId");
        } catch (Exception e) {
        }
        return employee_details;
    }

    public List<TicSystemDataDTO> getAdminList() {
        List<TicSystemDataDTO> admin_list = null;
        try {
            admin_list = (List<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getAdminList");
        } catch (Exception e) {
        }
        return admin_list;
    }

    public TicSystemDataDTO getFreqC(TicSystemDataDTO statVal) {
        TicSystemDataDTO stat = new TicSystemDataDTO();;
        try {
            stat = (TicSystemDataDTO) getSqlMapClientTemplate().queryForObject("TicSystemMap.getFreqC", statVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stat;
    }

    public String getEmpName(TicSystemDataDTO dto) {
        String getEmpName = null;
        try {
            getEmpName = (String) getSqlMapClient().queryForObject("TicSystemMap.getEmpName", dto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return getEmpName;
    }

    public String getCcMailDao(String empIds) {
        String cc_mail = null;
        try {
            cc_mail = (String) getSqlMapClientTemplate().queryForObject("TicSystemMap.getCcMail", empIds);
        } catch (Exception e) {
        }
        return cc_mail;
    }

    public List<TicSystemDataDTO> getVerifedCcMailNamesDao(String empIds) {
        List<TicSystemDataDTO> cc_mail_name = null;
        try {
            cc_mail_name = (List<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getVerifedCcMailNames", empIds);
        } catch (Exception e) {
        }
        return cc_mail_name;
    }

    public String getVerifedCcMailNamesDaoList(String empIds) {
        String cc_mail_list = null;
        try {
            cc_mail_list = (String) getSqlMapClientTemplate().queryForObject("TicSystemMap.getVerifedCcMailNamesList", empIds);
        } catch (Exception e) {
        }
        return cc_mail_list;
    }

    public List<TicSystemDataDTO> getResponseStatusList(String statusValue) {
        List<TicSystemDataDTO> responseStatusList = null;
        try {
            responseStatusList = getSqlMapClientTemplate().queryForList("TicSystemMap.getResponseStatusList", statusValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseStatusList;
    }

    public String getOpenStatus(String issue_type) {
        String openStatus = null;
        try {
            openStatus = (String) getSqlMapClientTemplate().queryForObject("TicSystemMap.getOpenStatus", issue_type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openStatus;
    }

    public List<TicSystemDataDTO> getSubUnitList(String id) {
        List<TicSystemDataDTO> subUnitList = null;
        try {
            subUnitList = getSqlMapClientTemplate().queryForList("TicSystemMap.getSubUnitList", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subUnitList;
    }

    public List<TicSystemDataDTO> getRequestTypes(String support_unit_id) {
        List<TicSystemDataDTO> responseStatusList = null;
        try {
            responseStatusList = getSqlMapClientTemplate().queryForList("TicSystemMap.getRequestTypes", support_unit_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseStatusList;
    }

    public List<TicSystemDataDTO> getRequestAreas(String sub_unit_id, String issue_type) {
        List<TicSystemDataDTO> requestAreaList = null;
        TicSystemDataDTO details = new TicSystemDataDTO();
        details.setSubUnitId(sub_unit_id);
        details.setIssue_type(issue_type);
        try {
            requestAreaList = getSqlMapClientTemplate().queryForList("TicSystemMap.getRequestAreas", details);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestAreaList;
    }

    public String getTomailAddress(String sub_unit_id) {
        String tomail = null;
        try {
            tomail = (String) getSqlMapClientTemplate().queryForObject("TicSystemMap.getTomailAddress", sub_unit_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tomail;
    }

    public List<TicSystemDataDTO> getStatus(String status) {
        List<TicSystemDataDTO> status_list = null;
        try {
            status_list = (List<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getStatus", status);
        } catch (Exception e) {
        }
        return status_list;
    }

    public ArrayList<TicSystemDataDTO> getMailDetails() {
        ArrayList<TicSystemDataDTO> mail_details = null;
        try {
            mail_details = (ArrayList<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getMailDetails");
        } catch (Exception e) {
        }
        return mail_details;
    }

    public List<TicSystemDataDTO> getNextStatusList(String ticket_id) {
        String nxt_status = null;
        List<TicSystemDataDTO> next_status = null;
        List<TicSystemDataDTO> ticket_details = null;
        try {
            ticket_details = getSqlMapClientTemplate().queryForList("TicSystemMap.getTicketDetails", ticket_id);
        } catch (Exception e) {
        }
        try {
            TicSystemDataDTO details = new TicSystemDataDTO();
            details.setStatus(ticket_details.get(0).getStatus());
            details.setIssue_type(ticket_details.get(0).getIssue_type());
            nxt_status = (String) getSqlMapClientTemplate().queryForObject("TicSystemMap.getStatusKey", details);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (nxt_status.length() > 0) {
            try {
                next_status = getSqlMapClientTemplate().queryForList("TicSystemMap.getNextStatusList", nxt_status);
                if(!ticket_details.get(0).getIssue_type().equals("2")){
                    nxt_status = "'o'"+","+"'c'";
                    next_status = getSqlMapClientTemplate().queryForList("TicSystemMap.getNextStatusList", nxt_status);
                }
            } catch (Exception e) {
            }
        }
        return next_status;
    }
}
