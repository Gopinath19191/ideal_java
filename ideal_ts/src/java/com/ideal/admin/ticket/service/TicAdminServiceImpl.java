package com.ideal.admin.ticket.service;

import com.ideal.admin.ticket.dao.TicAdminDaoImpl;
import com.ideal.admin.ticket.dto.TicAdminDataDTO;
import com.ideal.system.ticket.dto.TicSystemDataDTO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class TicAdminServiceImpl {

    final static Logger logger = Logger.getLogger(TicAdminServiceImpl.class);
    TicAdminDaoImpl daoImpl = new TicAdminDaoImpl();

    public TicAdminDaoImpl getDaoImpl() {
        return daoImpl;
    }

    public void setDaoImpl(TicAdminDaoImpl daoImpl) {
        this.daoImpl = daoImpl;
    }

    public List<TicAdminDataDTO> fetchTicRequest(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> details = getDaoImpl().fetchTicRequest(dto);
        return details;
    }

    public TicAdminDataDTO insertRequest(TicAdminDataDTO dto) {
        TicAdminDataDTO details = getDaoImpl().insertRequest(dto);
        return details;
    }

    public TicAdminDataDTO insertResponse(TicAdminDataDTO dto) {
        TicAdminDataDTO details = getDaoImpl().insertResponse(dto);
        return details;
    }

    public TicAdminDataDTO insertRepliedDate(TicAdminDataDTO dto) {
        TicAdminDataDTO details = getDaoImpl().insertRepliedDate(dto);
        return details;
    }

    public TicAdminDataDTO updateStatusInRequests(TicAdminDataDTO dto) {
        TicAdminDataDTO details = getDaoImpl().updateStatusInRequests(dto);
        return details;
    }

    public TicAdminDataDTO updateAssignEngineerId(TicAdminDataDTO dto) {
        TicAdminDataDTO details = getDaoImpl().updateAssignEngineerId(dto);
        return details;
    }

    public TicAdminDataDTO updateClosedDate(TicAdminDataDTO dto) {
        TicAdminDataDTO details = getDaoImpl().updateClosedDate(dto);
        return details;
    }

    public List<TicAdminDataDTO> selectEmpByRef(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> details = getDaoImpl().selectEmpByRef(dto);
        return details;
    }

    public String getEmpName(TicAdminDataDTO dto) {
        String i = getDaoImpl().getEmpName(dto);
        return i;
    }

    public int getAssignEngineerId(String number) {
        int i = getDaoImpl().getAssignEngineerId(number);
        return i;
    }

    public List<TicAdminDataDTO> getConversationService(String convdt) {
        List<TicAdminDataDTO> details = null;
        details = getDaoImpl().getConversation(convdt);
        return details;
    }

    public List<TicAdminDataDTO> search_feedback(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> details = getDaoImpl().search_feedback(dto);
        return details;
    }

    public int fetchTicRequestListCount(TicAdminDataDTO dto) {
        return getDaoImpl().fetchTicRequestListCount(dto);
    }

    public TicAdminDataDTO getFreqC(TicAdminDataDTO statVal) {
        return getDaoImpl().getFreqC(statVal);
    }

    public List<TicAdminDataDTO> getRefeenceDetails(String refVal) {
        List<TicAdminDataDTO> refIds = null;
        refIds = getDaoImpl().getRefeenceDetails(refVal);
        return refIds;
    }

    public List<TicAdminDataDTO> getEmpDetails(String refVal) {
        List<TicAdminDataDTO> refEmp = null;
        refEmp = getDaoImpl().getEmpDetails(refVal);
        return refEmp;
    }

    public List<TicAdminDataDTO> getAssignEngineerDetails(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> refEmp = null;
        refEmp = getDaoImpl().getAssignEngineerDetails(dto);
        return refEmp;
    }

    public String getstructure(String number) {
        String structure = null;
        structure = getDaoImpl().getstructure(number);
        return structure;
    }

    public String getCcMail(String refVal) {
        String refEmp = null;
        refEmp = getDaoImpl().getCcMailDao(refVal);
        return refEmp;
    }

    public List<TicAdminDataDTO> getVerifedCcMailNames(String refVal) {
        List<TicAdminDataDTO> refEmp = null;
        refEmp = getDaoImpl().getVerifedCcMailNamesDao(refVal);
        return refEmp;
    }

    public List<TicAdminDataDTO> getAssignEmpNameList(String refVal) {
        List<TicAdminDataDTO> refEmp = null;
        refEmp = getDaoImpl().getAssignEmpNameList(refVal);
        return refEmp;
    }

    public String getVerifedCcMailNamesList(String refVal) {
        String refEmp = null;
        refEmp = getDaoImpl().getVerifedCcMailNamesDaoList(refVal);
        return refEmp;
    }

    public String getAssignEmpName(String refVal) {
        String refEmp = null;
        refEmp = getDaoImpl().getAssignEmpName(refVal);
        return refEmp;
    }

    public List<TicAdminDataDTO> getStatusList() {
        List<TicAdminDataDTO> issueType = getDaoImpl().getStatusList();
        return issueType;
    }

    public List<TicAdminDataDTO> getNextStatusList(TicAdminDataDTO details) {
        List<TicAdminDataDTO> issueType = getDaoImpl().getNextStatusList(details);
        return issueType;
    }

    public List<TicAdminDataDTO> getSupportTypeList() {
        List<TicAdminDataDTO> issueType = getDaoImpl().getSupportTypeList();
        return issueType;
    }

    public List<TicAdminDataDTO> getSubUnitList(String supporId) {
        List<TicAdminDataDTO> sunUnitType = getDaoImpl().getSubUnitList(supporId);
        return sunUnitType;
    }

    public List<TicAdminDataDTO> getIssueTypeList(String lastSupportType) {
        List<TicAdminDataDTO> issueType = getDaoImpl().getIssueTypeList(lastSupportType);
        return issueType;
    }

    public List<TicAdminDataDTO> getApplicationList(String unit_id, String sub_unit_id) {
        List<TicAdminDataDTO> issueType = getDaoImpl().getApplicationList(unit_id, sub_unit_id);
        return issueType;
    }
    public List<TicAdminDataDTO> getSupportEngineerList(String unit_id, String sub_unit_id){
        List<TicAdminDataDTO> engineerList = getDaoImpl().getSupportEngineerList(unit_id, sub_unit_id);
        return engineerList;
    }
    public List<TicAdminDataDTO> getAssignEngineerList(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> assignEngineerList = getDaoImpl().getAssignEngineerList(dto);
        return assignEngineerList;
    }

    public void fileDownload(String fileName, String originalName, String filePath, String fileType, HttpServletResponse response) {
        try {
            response.setContentType(fileType);
            response.setHeader("Content-disposition", "attachment; filename=\"" + originalName + "\"");
            File file = new File(filePath + "\\" + fileName);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            byte[] buf = new byte[4 * 1024];
            int bytesRead;
            while ((bytesRead = in.read(buf)) != -1) {
                out.write(buf, 0, bytesRead);
            }
            in.close();
            out.close();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<TicAdminDataDTO> getSelectedStatus(TicAdminDataDTO dto) {
        List<TicAdminDataDTO> selStaus = getDaoImpl().getSelectedStatus(dto);
        return selStaus;
    }

    public String getLatestResponseStatus(String status) {
        String latestResponseStatus = "";
        try {
            latestResponseStatus = getDaoImpl().getLatestResponseStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latestResponseStatus;
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

    public List<String> getempList(String emp) {
        List<String> details = getDaoImpl().getempList(emp);
        return details;
    }

    public List<TicAdminDataDTO> getStatus(String status) {
        List<TicAdminDataDTO> issueType = getDaoImpl().getStatus(status);
        return issueType;
    }

    public TicAdminDataDTO checkUserAdmin(String employee) {
        return getDaoImpl().checkUserAdmin(employee);
    }

    public ArrayList<TicSystemDataDTO> getMailDetails() {
        ArrayList<TicSystemDataDTO> mailDetails = getDaoImpl().getMailDetails();
        return mailDetails;
    }
}
