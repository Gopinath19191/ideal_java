/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.admin.ticket.dao;

import com.ideal.admin.ticket.dto.TicAdminDataDTO;
import com.ideal.master.ticket.dto.FeedbackMasterDto;
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
public class TicAdminDaoImpl extends SqlMapClientDaoSupport {

    final static Logger logger = Logger.getLogger(TicAdminDaoImpl.class);

    public List<TicAdminDataDTO> fetchTicRequest(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> details = null;
        try {
            if ("".equals(dto.getRef_no())) {
                dto.setRef_no(null);
            }
            if ("".equals(dto.getStatus())) {
                dto.setStatus(null);
            }
            if ("".equals(dto.getIssue_type())) {
                dto.setIssue_type(null);
            }
            if ("Search by Employee Number or First/Last name".equals(dto.getEmpName())) {
                dto.setEmpName(null);
            }
            details = (List<TicAdminDataDTO>) getSqlMapClientTemplate().queryForList("TicAdminMap.getTicRequest", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return details;
    }

    public TicAdminDataDTO insertRequest(TicAdminDataDTO dto) {
        TicAdminDataDTO details = new TicAdminDataDTO();
        try {
            details = (TicAdminDataDTO) getSqlMapClientTemplate().insert("TicAdminMap.insertRequest", dto);
        } catch (Exception e) {
        }
        return details;
    }

    public TicAdminDataDTO insertResponse(TicAdminDataDTO dto) {
        TicAdminDataDTO details = new TicAdminDataDTO();
         String lastFileInsertId = null;
        try {
        lastFileInsertId = (String) getSqlMapClientTemplate().insert("TicAdminMap.insertResponse", dto);
        details.setLastInsertId(lastFileInsertId);
         } catch (Exception e) {
        }
        return details;
    }

    public TicAdminDataDTO insertRepliedDate(TicAdminDataDTO dto) {
        TicAdminDataDTO details = new TicAdminDataDTO();
        try {
            details = (TicAdminDataDTO) getSqlMapClientTemplate().insert("TicAdminMap.insertRepliedDate", dto);
        } catch (Exception e) {
        }
        return details;
    }

    public TicAdminDataDTO updateStatusInRequests(TicAdminDataDTO dto) {
        TicAdminDataDTO details = new TicAdminDataDTO();
        try {
            details = (TicAdminDataDTO) getSqlMapClientTemplate().insert("TicAdminMap.updateStatusInRequests", dto);
        } catch (Exception e) {
        }
        return details;
    }

    public TicAdminDataDTO updateAssignEngineerId(TicAdminDataDTO dto) {
        TicAdminDataDTO details = new TicAdminDataDTO();
        try {
            details = (TicAdminDataDTO) getSqlMapClientTemplate().insert("TicAdminMap.updateAssignEngineerId", dto);
        } catch (Exception e) {
        }
        return details;
    }

    public TicAdminDataDTO updateClosedDate(TicAdminDataDTO dto) {
        TicAdminDataDTO details = new TicAdminDataDTO();
        try {
            details = (TicAdminDataDTO) getSqlMapClientTemplate().insert("TicAdminMap.updateClosedDate", dto);
        } catch (Exception e) {
        }
        return details;
    }

    public String getCcMailDao(String empIds) {
        String mailCC = null;
        try {
            mailCC = (String) getSqlMapClientTemplate().queryForObject("TicAdminMap.getCcMail", empIds);
        } catch (Exception e) {
        }
        return mailCC;
    }

    public List<TicAdminDataDTO> getVerifedCcMailNamesDao(String empIds) {
        List<TicAdminDataDTO> ccMailName = null;
        try {
            ccMailName = (List<TicAdminDataDTO>) getSqlMapClientTemplate().queryForList("TicAdminMap.getVerifedCcMailNames", empIds);
        } catch (Exception e) {
        }
        return ccMailName;
    }

    public List<TicAdminDataDTO> getAssignEmpNameList(String empIds) {
        List<TicAdminDataDTO> assignEmployee = null;
        try {
            assignEmployee = (List<TicAdminDataDTO>) getSqlMapClientTemplate().queryForList("TicAdminMap.getVerifedAssignEmployeeNamesList", empIds);
        } catch (Exception e) {
        }
        return assignEmployee;
    }

    public String getVerifedCcMailNamesDaoList(String empIds) {
        String ccMail = null;
        try {
            ccMail = (String) getSqlMapClientTemplate().queryForObject("TicAdminMap.getVerifedCcMailNamesList", empIds);
        } catch (Exception e) {
        }
        return ccMail;
    }

    public String getAssignEmpName(String empIds) {
        String employee_name = null;
        try {
            employee_name = (String) getSqlMapClientTemplate().queryForObject("TicAdminMap.getVerifedAssignEmployeeNames", empIds);
        } catch (Exception e) {
        }
        return employee_name;
    }

    public List<TicAdminDataDTO> selectEmpByRef(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> details = null;
        try {
            details = (List<TicAdminDataDTO>) getSqlMapClientTemplate().queryForList("TicAdminMap.getTicRequestByRef", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public String getEmpName(TicAdminDataDTO dto) {
        String getEmpName = null;
        try {
            getEmpName = (String) getSqlMapClient().queryForObject("TicAdminMap.getEmpName", dto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return getEmpName;
    }

    public int getAssignEngineerId(String number) {
        int empId = 0;
        try {
            empId = (Integer) getSqlMapClient().queryForObject("TicAdminMap.getAssignEngineerId", number);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return empId;
    }

    public String getstructure(String number) {
        String struct = null;
        try {
            struct = (String) getSqlMapClient().queryForObject("TicAdminMap.getstructureId", number);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return struct;
    }

    public List<TicAdminDataDTO> getConversation(String convdt) {
        List<TicAdminDataDTO> details = null;
        try {
            details = (List<TicAdminDataDTO>) getSqlMapClientTemplate().queryForList("TicAdminMap.getConversationMap", convdt);
        } catch (Exception e) {
        }
        return details;
    }

    public List<TicAdminDataDTO> search_feedback(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> details = null;
        try {
            if ("".equals(dto.getRef_no())) {
                dto.setRef_no(null);
            }
            if ("".equals(dto.getStatus())) {
                dto.setStatus(null);
            }
            if ("".equals(dto.getIssue_type())) {
                dto.setIssue_type(null);
            }
            if ("Search by Employee Number or First/Last name".equals(dto.getEmpName())) {

                dto.setEmpName(null);
            }
            details = (List<TicAdminDataDTO>) getSqlMapClientTemplate().queryForList("TicAdminMap.searchFeedback", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public int fetchTicRequestListCount(TicAdminDataDTO dto) {
        int TicRequestCount = 0;
        try {
            TicRequestCount = (Integer) getSqlMapClientTemplate().queryForObject("TicAdminMap.getTicRequestListCount", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TicRequestCount;
    }

    public TicAdminDataDTO getFreqC(TicAdminDataDTO statVal) {
        TicAdminDataDTO stat = new TicAdminDataDTO();
        try {
            stat = (TicAdminDataDTO) getSqlMapClientTemplate().queryForObject("TicAdminMap.getFreqC", statVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stat;
    }

    public List<TicAdminDataDTO> getRefeenceDetails(String refVal) {
        List<TicAdminDataDTO> refIds = null;
        refVal = "%" + refVal + "%";
        try {
            refIds = getSqlMapClientTemplate().queryForList("TicAdminMap.getRefIds", refVal);
        } catch (Exception e) {
        }
        return refIds;
    }

    public List<TicAdminDataDTO> getEmpDetails(String refVal) {
        List<TicAdminDataDTO> refEmp = null;
        refVal = "'%" + refVal + "%'";
        try {
            refEmp = getSqlMapClientTemplate().queryForList("TicAdminMap.getEmpIdsAdmin", refVal);
        } catch (Exception e) {
        }
        return refEmp;
    }

    public List<TicAdminDataDTO> getAssignEngineerDetails(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> refEmp = null;
        String refVal = dto.getCriteria();
        refVal = "'%" + refVal + "%'";
        dto.setRefVal(refVal);
        try {
            refEmp = getSqlMapClientTemplate().queryForList("TicAdminMap.getAssignEngineer", dto);
        } catch (Exception e) {
        }
        return refEmp;
    }

    public List<TicAdminDataDTO> getSupportTypeList() {
        List<TicAdminDataDTO> support_list = null;
        try {
            support_list = getSqlMapClientTemplate().queryForList("TicSystemMap.getSupportTypeList");
        } catch (Exception e) {
        }
        return support_list;
    }

    public List<TicAdminDataDTO> getSubUnitList(String supporId) {
        List<TicAdminDataDTO> sub_unit_list = null;
        try {
            sub_unit_list = getSqlMapClientTemplate().queryForList("TicSystemMap.getSubUnitList", supporId);
        } catch (Exception e) {
        }
        return sub_unit_list;
    }

    public List<TicAdminDataDTO> getStatusList() {
        List<TicAdminDataDTO> next_status = null;
        try {
            next_status = getSqlMapClientTemplate().queryForList("TicAdminMap.getStatusList");
        } catch (Exception e) {
        }
        return next_status;
    }

    public List<TicAdminDataDTO> getNextStatusList(TicAdminDataDTO details) {
        String nxt_status = null;
        List<TicAdminDataDTO> next_status = null;
        try {
            nxt_status = (String) getSqlMapClientTemplate().queryForObject("TicAdminMap.getStatusKey", details);
        } catch (Exception e) {
        }
        
        if(nxt_status != null && nxt_status.length() > 0) {
            try {
                next_status = getSqlMapClientTemplate().queryForList("TicAdminMap.getNextStatusList", nxt_status);
            } catch (Exception e) {
            }
        }else{
            nxt_status = "'a','p','w'";
            try {
                next_status = getSqlMapClientTemplate().queryForList("TicAdminMap.getNextStatusList", nxt_status);
            } catch (Exception e) {
            }
        }
        return next_status;
    }

    public List<TicAdminDataDTO> getIssueTypeList(String support_unit_id) {
        List<TicAdminDataDTO> issue_list = null;
        try {
            issue_list = getSqlMapClientTemplate().queryForList("TicAdminMap.getIssueTypeList",support_unit_id);
        } catch (Exception e) {
        }
        return issue_list;
    }

    public List<TicAdminDataDTO> getApplicationList(String unit_id, String sub_unit_id) {
        TicAdminDataDTO dto = new TicAdminDataDTO();
        dto.setSub_unit_id(sub_unit_id);
        dto.setSupport_type(unit_id);
        List<TicAdminDataDTO> application_list = null;
        try{
            application_list = (List<TicAdminDataDTO>) getSqlMapClientTemplate().queryForList("TicAdminMap.getApplicationList", dto);
        }catch(Exception e){}
        return application_list;
    }
    
    public List<TicAdminDataDTO> getSupportEngineerList(String unit_id, String sub_unit_id) {
        TicAdminDataDTO dto = new TicAdminDataDTO();
        dto.setSub_unit_id(sub_unit_id);
        dto.setSupport_type(unit_id);
        List<TicAdminDataDTO> support_engineer_list=null;
        try{
            support_engineer_list = (List<TicAdminDataDTO>) getSqlMapClientTemplate().queryForList("TicAdminMap.getSupportEngineerList", dto);
        }catch(Exception e){}
        return support_engineer_list;
    }

    public List<TicAdminDataDTO> getAssignEngineerList(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> assign_engineer_list = null;
        if (dto != null && dto.getSub_unit_id().equals("0")) {
            dto.setSub_unit_id(null);
            try {
                assign_engineer_list = getSqlMapClientTemplate().queryForList("TicAdminMap.getAssignEngineerList", dto);
            } catch (Exception e) {
            }
        }else{
            dto = new TicAdminDataDTO();
            dto.setUnit_id("1");
            dto.setSub_unit_id("1");
            assign_engineer_list = getSqlMapClientTemplate().queryForList("TicAdminMap.getAssignEngineerList", dto);
        }
        
        return assign_engineer_list;
    }

    public List<TicAdminDataDTO> getSelectedStatus(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> status_details = null;
        try {
            status_details = getSqlMapClientTemplate().queryForList("TicAdminMap.getselStatus", dto);
        } catch (Exception e) {
        }
        return status_details;
    }

    public String getLatestResponseStatus(String status) {
        String latestResponseStatus = "";
        try {
            latestResponseStatus = (String) getSqlMapClientTemplate().queryForObject("TicAdminMap.getLatestResponseStatus", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latestResponseStatus;
    }

    public String getTomailAddress(String sub_unit_id) {
        String tomail = null;
        try {
            tomail = (String) getSqlMapClientTemplate().queryForObject("TicAdminMap.getTomailAddress", sub_unit_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tomail;
    }

    public List<String> getempList(String emp) {
        List<String> details = null;
        try {
            details = getSqlMapClientTemplate().queryForList("TicAdminMap.getempList", emp);
        } catch (Exception e) {
        }
        return details;
    }

    public List<TicAdminDataDTO> getStatus(String status) {
        List<TicAdminDataDTO> status_details = null;
        try {
            status_details = getSqlMapClientTemplate().queryForList("TicAdminMap.getStatus", status);
        } catch (Exception e) {
        }
        return status_details;
    }

    public TicAdminDataDTO checkUserAdmin(String employee) {
        TicAdminDataDTO statusList = null;
        try {
            statusList = (TicAdminDataDTO) getSqlMapClient().queryForObject("TicAdminMap.checkAdmin", employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusList;
    }

    public ArrayList<TicSystemDataDTO> getMailDetails() {
        ArrayList<TicSystemDataDTO> mail_details = null;
        try {
            mail_details = (ArrayList<TicSystemDataDTO>) getSqlMapClientTemplate().queryForList("TicSystemMap.getMailDetails");
        } catch (Exception e) {
        }
        return mail_details;
    }
}
