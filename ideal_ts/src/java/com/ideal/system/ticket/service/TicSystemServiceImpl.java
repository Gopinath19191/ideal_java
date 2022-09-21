/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.system.ticket.service;

import com.ideal.system.ticket.dao.TicSystemDaoImpl;
import com.ideal.system.ticket.dto.TicSystemDataDTO;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author 8000246
 */
public class TicSystemServiceImpl {

    final static Logger logger = Logger.getLogger(TicSystemServiceImpl.class);
    TicSystemDaoImpl daoImpl = new TicSystemDaoImpl();

    public TicSystemDaoImpl getDaoImpl() {
        return daoImpl;
    }

    public void setDaoImpl(TicSystemDaoImpl daoImpl) {
        this.daoImpl = daoImpl;
    }

    public List<TicSystemDataDTO> fetchTicRequest(TicSystemDataDTO dto) {
        List<TicSystemDataDTO> details = getDaoImpl().fetchTicRequest(dto);
        return details;
    }

    public int fetchTicRequestListCount(TicSystemDataDTO dto) {
        return getDaoImpl().fetchTicRequestListCount(dto);

    }

    public List<TicSystemDataDTO> search_feedback_user(TicSystemDataDTO dto) {
        List<TicSystemDataDTO> details = getDaoImpl().search_feedback_user(dto);
        return details;
    }

    public List<TicSystemDataDTO> getRefeenceDetails(String refVal) {
        List<TicSystemDataDTO> refIds = null;
        refIds = getDaoImpl().getRefeenceDetails(refVal);
        return refIds;
    }

    public List<TicSystemDataDTO> getTicRequestByRef(TicSystemDataDTO dto) {
        List<TicSystemDataDTO> details = getDaoImpl().getTicRequestByRef(dto);
        return details;
    }

    public TicSystemDataDTO getFreqC(TicSystemDataDTO statVal) {
        return getDaoImpl().getFreqC(statVal);
    }

    public TicSystemDataDTO insertRequest(TicSystemDataDTO dto) {
        TicSystemDataDTO details = getDaoImpl().insertRequest(dto);
        return details;
    }

    public TicSystemDataDTO insertResponse(TicSystemDataDTO dto) {
        TicSystemDataDTO details = getDaoImpl().insertResponse(dto);
        return details;
    }

    public TicSystemDataDTO insertResponseEdit(TicSystemDataDTO dto) {
        TicSystemDataDTO details = getDaoImpl().insertResponseEdit(dto);
        return details;
    }

    public int getHighestRefNo(TicSystemDataDTO hr) {
        int highest_ref = getDaoImpl().getHighestRefNo(hr);
        return highest_ref;
    }

    public String getHighestReqId(TicSystemDataDTO dto) {
        String i = getDaoImpl().getHighestReqId(dto);
        return i;
    }

    public TicSystemDataDTO updateRequest(TicSystemDataDTO dto) {
        TicSystemDataDTO details = getDaoImpl().updateRequest(dto);
        return details;
    }

    public TicSystemDataDTO updateRequestWithOutStatus(TicSystemDataDTO dto) {
        TicSystemDataDTO details = getDaoImpl().updateRequestWithOutStatus(dto);
        return details;
    }

    public TicSystemDataDTO updateRequestInResponseTbl(TicSystemDataDTO dto) {
        TicSystemDataDTO details = getDaoImpl().updateRequestInResponseTbl(dto);
        return details;
    }

    public List<TicSystemDataDTO> getStatusList(TicSystemDataDTO dto) {
        List<TicSystemDataDTO> issueType = getDaoImpl().getStatusList(dto);
        return issueType;
    }

    public List<TicSystemDataDTO> getSystemList() {
        List<TicSystemDataDTO> issueType = getDaoImpl().getSystemList();
        return issueType;
    }

    public List<TicSystemDataDTO> getAppAreaList() {
        List<TicSystemDataDTO> issueType = getDaoImpl().getAppAreaList();
        return issueType;
    }

    public List<TicSystemDataDTO> getAdminList() {
        List<TicSystemDataDTO> issueType = getDaoImpl().getAdminList();
        return issueType;
    }

    public List<TicSystemDataDTO> getIssueTypeList() {
        List<TicSystemDataDTO> issueType = getDaoImpl().getIssueTypeList();
        return issueType;
    }

    public List<TicSystemDataDTO> getSupportTypeList() {
        List<TicSystemDataDTO> supportType = getDaoImpl().getSupportTypeList();
        return supportType;
    }

    public List<TicSystemDataDTO> getEmployeeDetailsFromId() {
        List<TicSystemDataDTO> allEmp = getDaoImpl().getEmployeeDetailsFromId();
        return allEmp;
    }

    public List<TicSystemDataDTO> getEmpDetails(String refVal) {
        List<TicSystemDataDTO> refEmp = null;
        if (refVal != null) {
            refEmp = getDaoImpl().getEmpDetails(refVal);
        }
        return refEmp;
    }

    public String getEmpMail(String refVal) {
        String refEmp = null;
        refEmp = getDaoImpl().getEmpMail(refVal);
        return refEmp;
    }

    public String getCcMail(String refVal) {
        String refEmp = null;
        refEmp = getDaoImpl().getCcMailDao(refVal);
        return refEmp;
    }

    public List<TicSystemDataDTO> getVerifedCcMailNames(String refVal) {
        List<TicSystemDataDTO> refEmp = null;
        refEmp = getDaoImpl().getVerifedCcMailNamesDao(refVal);
        return refEmp;
    }

    public String getVerifedCcMailNamesList(String refVal) {
        String refEmp = null;
        refEmp = getDaoImpl().getVerifedCcMailNamesDaoList(refVal);
        return refEmp;
    }

    public String getEmpName(TicSystemDataDTO dto) {
        String i = getDaoImpl().getEmpName(dto);
        return i;
    }

    public void removeFile(String refVal) {
        String refEmp = null;
        getDaoImpl().removeFile(refVal);
    }

    public void fileDownload(String fileName, String fileNameNew, String filePath, String fileType, HttpServletResponse response) throws IOException {
        response.setContentType(fileType);
        response.setHeader("Content-disposition", "attachment; filename=\"" + fileNameNew + "\"");
        File file = new File(filePath + "\\" + fileName);
        FileInputStream fileIn = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        try {
            byte[] outputByte = new byte[4096];
            while (fileIn.read(outputByte, 0, 4096) != -1) {
                out.write(outputByte, 0, 4096);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileIn.close();
            out.flush();
            out.close();
        }

    }

    public List<TicSystemDataDTO> getResponseStatusList(String statusValue) {
        List<TicSystemDataDTO> responseStatusList = null;
        try {
            responseStatusList = getDaoImpl().getResponseStatusList(statusValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseStatusList;
    }

    public String getOpenStatus(String isssue_type) {
        String openStatus = null;
        try {
            openStatus = getDaoImpl().getOpenStatus(isssue_type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openStatus;
    }

    public List<TicSystemDataDTO> getSubUnitList(String id) {
        List<TicSystemDataDTO> subUnitTypeList = null;
        try {
            subUnitTypeList = getDaoImpl().getSubUnitList(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subUnitTypeList;
    }

    public List<TicSystemDataDTO> getRequestTypes(String support_unit_id) {
        List<TicSystemDataDTO> requsetTypeList = null;
        try {
            requsetTypeList = getDaoImpl().getRequestTypes(support_unit_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requsetTypeList;
    }

    public List<TicSystemDataDTO> getRequestAreas(String support_unit_id, String issue_type) {
        List<TicSystemDataDTO> requestAreaList = null;
        try {
            requestAreaList = getDaoImpl().getRequestAreas(support_unit_id, issue_type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestAreaList;
    }

    public String getTomailAddress(String sub_unit_id) {
        String tomail = null;
        try {
            tomail = getDaoImpl().getTomailAddress(sub_unit_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tomail;
    }

    public List<TicSystemDataDTO> getStatus(String status) {
        List<TicSystemDataDTO> issueType = getDaoImpl().getStatus(status);
        return issueType;
    }

    public ArrayList<TicSystemDataDTO> getMailDetails() {
        ArrayList<TicSystemDataDTO> mailDetails = getDaoImpl().getMailDetails();
        return mailDetails;
    }

    public List<TicSystemDataDTO> getNextStatusList(String ticket_id) {
        List<TicSystemDataDTO> next_status = getDaoImpl().getNextStatusList(ticket_id);
        return next_status;
    }
}
