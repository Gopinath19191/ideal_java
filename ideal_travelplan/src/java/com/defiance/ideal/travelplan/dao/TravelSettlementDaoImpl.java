/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dao;

import com.defiance.ideal.travelplan.dto.CommonDto;
import com.defiance.ideal.travelplan.dto.DomesticTravelDto;
import com.defiance.ideal.travelplan.dto.TravelSettlementDto;
import com.defiance.ideal.travelplan.utils.CommonConfigurations;
import com.defiance.ideal.travelplan.utils.MailMessages;
import com.defiance.ideal.travelplan.utils.SendMail;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 16113
 */
public class TravelSettlementDaoImpl extends SqlMapClientDaoSupport implements TravelSettlementDao {

    public List<TravelSettlementDto> travelSettlementList(TravelSettlementDto filterdto) {
        return null;
    }

    public TravelSettlementDto travelDetails(String ticketId) {
        TravelSettlementDto travelDetails = null;
        try {
            travelDetails = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.travelDetails", ticketId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelDetails;
    }

    public List<TravelSettlementDto> getCategories() {
        List<TravelSettlementDto> travelDetails = null;
        try {
            travelDetails = getSqlMapClientTemplate().queryForList("TravelSettlement.getCategories");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelDetails;
    }

    public List<TravelSettlementDto> getDocumentType() {
        List<TravelSettlementDto> docType = null;
        try {
            docType = getSqlMapClientTemplate().queryForList("TravelSettlement.getDocumentType");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return docType;
    }

    public List<TravelSettlementDto> eligibilityList(String empId) {
        List<TravelSettlementDto> travelDetails = null;
        try {
            travelDetails = getSqlMapClientTemplate().queryForList("TravelSettlement.eligibilityList", empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelDetails;
    }

    public List<TravelSettlementDto> adminSettlemntTicDetails(String masterId) {
        List<TravelSettlementDto> travelDetails = null;
        try {
            travelDetails = getSqlMapClientTemplate().queryForList("TravelSettlement.ticketDetails", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelDetails;
    }

    public List<TravelSettlementDto> editAdminTicExpense(String masterId) {
        List<TravelSettlementDto> travelDetails = null;
        try {
            travelDetails = getSqlMapClientTemplate().queryForList("TravelSettlement.editAdminTicExpense", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelDetails;
    }

    public List<TravelSettlementDto> editAdminHotExpense(String masterId) {
        List<TravelSettlementDto> travelDetails = null;
        try {
            travelDetails = getSqlMapClientTemplate().queryForList("TravelSettlement.editAdminHotExpense", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelDetails;
    }

    public List<TravelSettlementDto> adminSettlemntHotelDetails(String masterId) {
        List<TravelSettlementDto> travelDetails = null;
        try {
            travelDetails = getSqlMapClientTemplate().queryForList("TravelSettlement.hotelDetails", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelDetails;
    }

    public List<TravelSettlementDto> adminSettlemntConvDetails(String masterId) {
        List<TravelSettlementDto> travelDetails = null;
        try {
            travelDetails = getSqlMapClientTemplate().queryForList("TravelSettlement.convenienceDetails", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelDetails;
    }

    public TravelSettlementDto checkSettlementMaster(String masterId) {
        TravelSettlementDto travelDetails = null;
        try {
            travelDetails = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.checkSettlementMaster", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelDetails;
    }

    public String addAdminSettlement(TravelSettlementDto masterdto) {
        String lastInsertId = null;
        try {
            lastInsertId = (String) getSqlMapClientTemplate().insert("TravelSettlement.addAdminSettlement", masterdto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastInsertId;
    }

    public String addAdminExpency(TravelSettlementDto masterdto) {
        String lastInsertId = null;
        try {
            lastInsertId = (String) getSqlMapClientTemplate().insert("TravelSettlement.addAdminExpency", masterdto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastInsertId;
    }

    public String addConvExpenseDetails(TravelSettlementDto masterdto) {
        String lastInsertId = null;
        try {
            lastInsertId = (String) getSqlMapClientTemplate().insert("TravelSettlement.addConvExpenseDetails", masterdto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastInsertId;
    }

    public void updateAdminSettlement(TravelSettlementDto masterdto) {
        getSqlMapClientTemplate().update("TravelSettlement.updateAdminSettlement", masterdto);
    }

    public String uploadFile(HttpServletRequest request, MultipartFile file, String masterId, String ticketRefId) throws IOException {
        String lastInsertId = null;
        HashMap uploadData = new HashMap();
        Date createdDate = new Date();
        if (!file.getOriginalFilename().equals("") && file.getSize() > 0) {
            String file_name = ticketRefId + "-" + file.getOriginalFilename();
            uploadData.put("file_name", file_name);
            uploadData.put("created_date", createdDate);
            uploadData.put("master_id", masterId);
            File path = new File(CommonConfigurations.travelDocumentsPath + file_name);
            file.transferTo(path);
            lastInsertId = (String) getSqlMapClientTemplate().insert("TravelSettlement.saveAttachment", uploadData);
        }
        return lastInsertId;
    }

    public String uploadAttachment(HttpServletRequest request, MultipartFile file, String masterId, String category, String ticketRefId) throws IOException {
        String lastInsertId = null;
        HashMap uploadData = new HashMap();
        Date createdDate = new Date();
        if (!file.getOriginalFilename().equals("") && file.getSize() > 0) {
            String file_name = ticketRefId + "-" + file.getOriginalFilename();
            uploadData.put("file_name", file_name);
            uploadData.put("created_date", createdDate);
            uploadData.put("master_id", masterId);
            uploadData.put("category", category);
            File path = new File(CommonConfigurations.travelDocumentsPath + file_name);
            file.transferTo(path);
            lastInsertId = (String) getSqlMapClientTemplate().insert("TravelSettlement.uploadAttachment", uploadData);
        }
        return lastInsertId;
    }

    public void updateFile(HttpServletRequest request, String file_name, String masterId, String category, String id, String status) throws IOException {
        HashMap uploadData = new HashMap();
        Date createdDate = new Date();
        uploadData.put("file_name", file_name);
        uploadData.put("created_date", createdDate);
        uploadData.put("master_id", masterId);
        uploadData.put("category", category);
        uploadData.put("id", id);
        uploadData.put("status", status);
        getSqlMapClientTemplate().update("TravelSettlement.updateAttachment", uploadData);
    }

    public String updateAttachment(HttpServletRequest request, MultipartFile file, String id, String masterId, String category, String status, String ticketRefId) throws IOException {
        String lastInsertId = null;
        HashMap uploadData = new HashMap();
        Date createdDate = new Date();
        if (!file.getOriginalFilename().equals("") && file.getSize() > 0) {
            String file_name = ticketRefId + "-" + file.getOriginalFilename();
            uploadData.put("file_name", file_name);
            uploadData.put("created_date", createdDate);
            uploadData.put("master_id", masterId);
            uploadData.put("category", category);
            uploadData.put("id", id);
            uploadData.put("status", status);
            File path = new File(CommonConfigurations.travelDocumentsPath + file_name);
            file.transferTo(path);
            getSqlMapClientTemplate().update("TravelSettlement.updateAttachment", uploadData);
        }
        return lastInsertId;
    }

    public static void fileDownload(String fileName, HttpServletResponse response) {
        try {
            //response.setContentType(fileType);
            System.out.println("file name>>>>>" + fileName);
            response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
            File file = new File(CommonConfigurations.fileDownloadPath + "\\" + fileName);
            //prepare input stream
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            //prepare output stream
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            //start reading and writing data
            byte[] buf = new byte[4 * 1024];
            int bytesRead;
            while ((bytesRead = in.read(buf)) != -1) {
                out.write(buf, 0, bytesRead);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<TravelSettlementDto> advanceDetails(String masterId) {
        List<TravelSettlementDto> depositedDetails = null;
        try {
            depositedDetails = getSqlMapClientTemplate().queryForList("TravelSettlement.advanceDetails", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return depositedDetails;
    }

    public String totalAdvance(String masterId) {
        String totalAdvance = null;
        try {
            totalAdvance = (String) getSqlMapClientTemplate().queryForObject("TravelSettlement.totalAdvance", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalAdvance;
    }

    public String comSettlementTotal(String masterId, String category) {
        String toal = null;
        Map map = new HashMap();
        map.put("categoryType", category);
        map.put("masterId", masterId);
        try {
            toal = (String) getSqlMapClientTemplate().queryForObject("TravelSettlement.comSettlementTotal", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toal;
    }

    public List<TravelSettlementDto> getAutocomplete(String val, String type) {
        List<TravelSettlementDto> result = null;
        val = '%' + val + '%';
        String country_id = type;
        Map<String, String> m = new HashMap<String, String>();
        m.put("m", val);
        m.put("c", country_id);
        result = (List<TravelSettlementDto>) getSqlMapClientTemplate().queryForList("TravelSettlement.getAutoCity", m);
        return result;
    }

    public String addEmployeeSettlement(TravelSettlementDto masterdto) {
        String lastInsertId = null;
        try {
            lastInsertId = (String) getSqlMapClientTemplate().insert("TravelSettlement.addEmployeeSettlement", masterdto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastInsertId;
    }

    public void updateEmployeeSettlement(TravelSettlementDto masterdto) {
        getSqlMapClientTemplate().update("TravelSettlement.updateEmployeeSettlement", masterdto);
    }

    public void updateAdminTM(TravelSettlementDto masterdto) {
        getSqlMapClientTemplate().update("TravelSettlement.updateAdminTM", masterdto);
    }

    public void updateTicDetails(TravelSettlementDto masterdto) {
        getSqlMapClientTemplate().update("TravelSettlement.updateTicDetails", masterdto);
    }

    public void updateHotDetails(TravelSettlementDto masterdto) {
        getSqlMapClientTemplate().update("TravelSettlement.updateHotDetails", masterdto);
    }

    public void updateConDetails(TravelSettlementDto masterdto) {
        getSqlMapClientTemplate().update("TravelSettlement.updateConDetails", masterdto);
    }

    public String getEligibileAmount(TravelSettlementDto masterdto) {
        String amount = null;
        try {
            amount = (String) getSqlMapClientTemplate().queryForObject("TravelSettlement.getEligibileAmount", masterdto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }

    public String getMisEligibileAmount(TravelSettlementDto masterdto) {
        String amount = null;
        try {
            amount = (String) getSqlMapClientTemplate().queryForObject("TravelSettlement.getMisEligibileAmount", masterdto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }

    public List<String> citylist(TravelSettlementDto masterdto) {
        List<String> citylist = null;
        try {
            citylist = getSqlMapClientTemplate().queryForList("TravelSettlement.citylist", masterdto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citylist;
    }

    public TravelSettlementDto viewDetails(String masterId) {
        TravelSettlementDto adminList = null;
        try {
            adminList = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.viewDetails", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public TravelSettlementDto getDetails(String masterId) {
        TravelSettlementDto adminList = null;
        try {
            adminList = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.getDetails", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public TravelSettlementDto adminExpenseTotals(String masterId) {
        TravelSettlementDto expenseTotals = null;
        try {
            expenseTotals = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.adminExpenseTotals", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenseTotals;
    }

    public List<TravelSettlementDto> allExpenses(String masterId) {
        List<TravelSettlementDto> companyExpancyDetails = null;
        try {
            companyExpancyDetails = getSqlMapClientTemplate().queryForList("TravelSettlement.allExpenses", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyExpancyDetails;
    }
    
    public List<TravelSettlementDto> getCityList(String countryId) {
        List<TravelSettlementDto> getCityList = null;
        try {
            getCityList = getSqlMapClientTemplate().queryForList("TravelSettlement.getCityList", countryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getCityList;
    }
    public TravelSettlementDto AllexpenceTotals(String masterId) {
        TravelSettlementDto expenses = null;
        try {
            expenses = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.AllexpenceTotals", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public TravelSettlementDto ViewAllexpenceTotals(String masterId) {
        TravelSettlementDto expenses = null;
        try {
            expenses = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.ViewAllexpenceTotals", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public List<TravelSettlementDto> adminList(TravelSettlementDto masterdto) {
        List<TravelSettlementDto> adminList = null;
        if (masterdto.getList_type().equals("pending")) {
            try {
                adminList = getSqlMapClientTemplate().queryForList("TravelSettlement.adminPendingList", masterdto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                adminList = getSqlMapClientTemplate().queryForList("TravelSettlement.adminProcessedList", masterdto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminList;
    }

    public List<TravelSettlementDto> getRMList(TravelSettlementDto dto) {
        List<TravelSettlementDto> rmlist = null;
        if (dto.getList_type().equals("pending")) {
            try {
                rmlist = getSqlMapClientTemplate().queryForList("TravelSettlement.getRMPendingList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                rmlist = getSqlMapClientTemplate().queryForList("TravelSettlement.getRMProcessedList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rmlist;
    }

    public List<TravelSettlementDto> getBUHList(TravelSettlementDto dto) {
        List<TravelSettlementDto> buhlist = null;
        if (dto.getList_type().equals("pending")) {
            try {
                buhlist = getSqlMapClientTemplate().queryForList("TravelSettlement.getBUHPendingList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                buhlist = getSqlMapClientTemplate().queryForList("TravelSettlement.getBUHProcessedList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buhlist;
    }

    public List<TravelSettlementDto> getFinanceList(TravelSettlementDto dto) {
        List<TravelSettlementDto> financelist = null;
        if (dto.getList_type().equals("pending")) {
            try {
                financelist = getSqlMapClientTemplate().queryForList("TravelSettlement.getFinancePendingList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                financelist = getSqlMapClientTemplate().queryForList("TravelSettlement.getFinanceProcessedList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return financelist;
    }

    public List<TravelSettlementDto> getCFOList(TravelSettlementDto dto) {
        List<TravelSettlementDto> financelist = null;
        if (dto.getList_type().equals("pending")) {
            try {
                financelist = getSqlMapClientTemplate().queryForList("TravelSettlement.getCFOPendingList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                financelist = getSqlMapClientTemplate().queryForList("TravelSettlement.getCFOProcessedList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return financelist;
    }

    public List<TravelSettlementDto> getTreasuryList(TravelSettlementDto dto) {
        List<TravelSettlementDto> financelist = null;
        if (dto.getList_type().equals("pending")) {
            try {
                financelist = getSqlMapClientTemplate().queryForList("TravelSettlement.getTreasuryPendingList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                financelist = getSqlMapClientTemplate().queryForList("TravelSettlement.getTreasuryProcessedList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return financelist;
    }

    public List<TravelSettlementDto> getPayrollList(TravelSettlementDto dto) {
        List<TravelSettlementDto> payrolllist = null;
        if (dto.getList_type().equals("pending")) {
            try {
                payrolllist = getSqlMapClientTemplate().queryForList("TravelSettlement.getPayrollPendingList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                payrolllist = getSqlMapClientTemplate().queryForList("TravelSettlement.getPayrollProcessedList", dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return payrolllist;
    }

    public void updateDeviation(TravelSettlementDto masterdto) {
        getSqlMapClientTemplate().update("TravelSettlement.updateDeviation", masterdto);
    }

    public void updateDeviationEmp(TravelSettlementDto masterdto) {
        getSqlMapClientTemplate().update("TravelSettlement.updateDeviationEmp", masterdto);
    }

    public TravelSettlementDto approverDetails(String ticketId) {
        TravelSettlementDto approverDetails = null;
        try {
            approverDetails = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.approverDetails", ticketId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return approverDetails;
    }

    public void updateRmApproval(TravelSettlementDto dto) {
        try {
            getSqlMapClientTemplate().update("TravelSettlement.updateRmApproval", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBuhApproval(TravelSettlementDto dto) {
        try {
            getSqlMapClientTemplate().update("TravelSettlement.updateBuhApproval", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateFinanceApproval(TravelSettlementDto dto) {
        try {
            getSqlMapClientTemplate().update("TravelSettlement.updateFinanceApproval", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAdminApproval(TravelSettlementDto dto) {
        try {
            getSqlMapClientTemplate().update("TravelSettlement.updateAdminApproval", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTreasuryApproval(TravelSettlementDto dto) {
        try {
            getSqlMapClientTemplate().update("TravelSettlement.updateTreasuryApproval", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePayrollApproval(TravelSettlementDto dto) {
        try {
            getSqlMapClientTemplate().update("TravelSettlement.updatePayrollApproval", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TravelSettlementDto balCaliculation(String masterId) {
        TravelSettlementDto balcal = null;
        try {
            balcal = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.balCaliculation", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balcal;
    }

    public void updateCfoApproval(TravelSettlementDto dto) {
        try {
            getSqlMapClientTemplate().update("TravelSettlement.updateCfoApproval", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TravelSettlementDto> adminExpenseList(String masterId) {
        List<TravelSettlementDto> adminExpenseList = null;
        try {
            adminExpenseList = getSqlMapClientTemplate().queryForList("TravelSettlement.adminExpenseList", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminExpenseList;
    }

    public List<TravelSettlementDto> empExpenseList(String masterId) {
        List<TravelSettlementDto> empExpenseList = null;
        try {
            empExpenseList = getSqlMapClientTemplate().queryForList("TravelSettlement.empExpenseList", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empExpenseList;
    }

    public TravelSettlementDto approvelStatus(String masterId) {
        TravelSettlementDto approvelStatus = null;
        try {
            approvelStatus = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.approvelStatus", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvelStatus;
    }

    public TravelSettlementDto totalForApprovals(String masterId) {
        TravelSettlementDto approvelStatus = null;
        try {
            approvelStatus = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.totalForApprovals", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvelStatus;
    }

    public String getBuh(String structure_id) {
        String buh_id = null;
        try {
            buh_id = (String) getSqlMapClientTemplate().queryForObject("TravelSettlement.getBuh", structure_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buh_id;
    }

    public String getCfo() {
        String cfo_id = null;
        try {
            cfo_id = (String) getSqlMapClientTemplate().queryForObject("TravelSettlement.getCfo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cfo_id;
    }

    public void updateExpenses(TravelSettlementDto masterdto) {
        getSqlMapClientTemplate().update("TravelSettlement.updateExpenses", masterdto);
    }

    public List<TravelSettlementDto> getAttachments(String masterId) {
        List<TravelSettlementDto> attlist = null;
        try {
            attlist = getSqlMapClientTemplate().queryForList("TravelSettlement.getAttachments", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attlist;
    }

    public List<TravelSettlementDto> isSettlementAdded(String masterId) {
        List<TravelSettlementDto> attlist = null;
        try {
            attlist = getSqlMapClientTemplate().queryForList("TravelSettlement.isEmpSettlementAdded", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attlist;
    }

    public void sendSettlementMail(TravelSettlementDto dto, String status) throws Exception {
        ArrayList<TravelSettlementDto> connectionRes;
        connectionRes = getMailDetails();
        String con_username = connectionRes.get(0).getConfigValue();
        String con_password = connectionRes.get(1).getConfigValue();
        String con_host = connectionRes.get(2).getConfigValue();
        String con_port = connectionRes.get(3).getConfigValue();
        SendMail mailObj = null;
        try {
            mailObj = new SendMail(con_username, con_password, con_host, con_port);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        MailMessages mailMessageObj = new MailMessages();
        try {
            String travel_id = dto.getTicketRefId();
            HttpServletRequest requestObj = null;
            String mailSubject = "";
            String mailTo = "";
            String mailCC = "";
            String mailMessage = "";
            if (status.equals("11")) {
                mailSubject = travel_id + " - Admin Input on Travel Expenses Completed";
                TravelSettlementDto admin_mail = getEmployeeMailId(dto.getAdminId());
                mailCC = admin_mail.getMail_id();
                TravelSettlementDto employee_mail = getEmployeeMailId(dto.getEmp_id());
                mailTo = employee_mail.getMail_id();
                mailMessage += "Dear " + employee_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "This is to inform you that Admin has updated the Travel expenses relating to captioned Travel plan, request you to update your expenses and submit for Settlement.<br><br>"
                        + "Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            } else if ((status.equals("12") && dto.getDeviation().equals("N")) || (status.equals("13") && dto.getSettlementType().equals("c"))) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement is waiting for Approval";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                mailCC = employee_mail.getMail_id();
                String finance_mail = getFinanceMailId(travelDetails.getPracticeGroupId());
                mailTo = finance_mail;
                mailMessage += "Dear Finance in charge,<br><br>";
                mailMessage += "Travel Settlement submitted by " + employee_mail.getEmployee_name() + " is waiting for your approval in iDEAL, kindly login to iDEAL for review and approval.<br><br>"
                        + "Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
                
                // mail trigger action too submition of proofs
                if(travelDetails.getCountryId().equals("113")){
                    mailMessage="";
                    mailSubject = "Travel " + travelDetails.getTicketRefId() + " settlement-proofs to be couriered to treasury at Gateway Office-Chennai";
                    mailTo = employee_mail.getMail_id();
                    mailCC = employee_mail.getMail_id();
                    mailMessage += "Dear "+employee_mail.getEmployee_name() +",<br><br>";
                    mailMessage += "You travel settlement is referred to treasury for validation, please arrange to send the print of Travel settlement summary page along with bills to Treasury at Hinduja Tech limited, A6 8th Floor, Gateway Office Parks, 16 GST Road, Perungalathur, Chennai-600 063.<br><br>"
                            + "Please login to iDeal to view the details.<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
                    mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
                }else{
                    mailMessage="";
                    mailSubject = "Travel " + travelDetails.getTicketRefId() + " settlement-submitted to treasury for validation";
                    mailTo = getPayrollMailId();
                    mailCC = employee_mail.getMail_id();
                    mailMessage += "Dear Payroll,<br><br>";
                    mailMessage += "Travel Settlement submitted by "+employee_mail.getEmployeeName()+" is waiting for your validation in iDEAL, kindly login to iDEAL for review and approval. <br><br>"
                                + "Please login to iDeal to view the details.<br><br>"
                                + "Regards,<br>"
                                + "iDeal Admin<br>"
                                + "http://ideal.hindujatech.com";
                    mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
                }
            } else if (status.equals("12") && dto.getDeviation().equals("Y")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement is waiting for Approval";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                mailCC = employee_mail.getMail_id();
                TravelSettlementDto rm_mail = getEmployeeMailId(travelDetails.getManagerId());
                mailTo = rm_mail.getMail_id();
                mailMessage += "Dear " + rm_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "Travel Settlement submitted by " + employee_mail.getEmployee_name() + " is waiting for your approval in iDEAL, kindly login to iDEAL for review and approval.<br><br>"
                        + "Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            } else if (status.equals("13") && dto.getSettlementType().equals("h")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement is waiting for Approval";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                mailCC = employee_mail.getMail_id();
                TravelSettlementDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                mailTo = buh_mail.getMail_id();
                TravelSettlementDto manager_mail = getEmployeeMailId(travelDetails.getManagerId());
                mailCC = mailCC + "," + manager_mail.getMail_id();
                mailMessage += "Dear " + buh_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "Travel Settlement submitted by " + employee_mail.getEmployee_name() + " and approved by " + manager_mail.getEmployee_name() + " is waiting for your approval in iDEAL,  kindly login to iDEAL for review and approval .<br><br>"
                        + "Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            } else if (status.equals("15") && dto.getCfo_action().equals("N")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement is waiting for Approval";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                TravelSettlementDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                mailCC = employee_mail.getMail_id() + "," + buh_mail.getMail_id();
                String finance_mail = getFinanceMailId(travelDetails.getPracticeGroupId());
                mailTo = finance_mail;
                mailMessage += "Dear Finance in charge,<br><br>";
                mailMessage += "Settlement submitted by " + employee_mail.getEmployee_name() + " and approved by " + buh_mail.getEmployee_name() + " is waiting for your action to approve Settlement<br><br>"
                        + "Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
                
                // proof submittion mail
                if(travelDetails.getCountryId().equals("113")){
                    mailMessage="";
                    mailSubject = "Travel " + travelDetails.getTicketRefId() + " settlement-proofs to be couriered to Treasury at Gateway Office-Chennai";
                    mailTo = employee_mail.getMail_id();
                    mailCC = employee_mail.getMail_id();
                    mailMessage += "Dear "+employee_mail.getEmployee_name() +",<br><br>";
                    mailMessage += "You travel settlement is referred to treasury for validation, please arrange to send the print of Travel settlement summary page along with bills to Treasury at Hinduja Tech limited, A6 8th Floor, Gateway Office Parks, 16 GST Road, Perungalathur, Chennai-600 063.<br><br>"
                            + "Please login to iDeal to view the details.<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
                    mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
                }else{
                    mailMessage="";
                    mailSubject = "Travel " + travelDetails.getTicketRefId() + " settlement-submitted to Treasury for validation";
                    mailTo = getPayrollMailId();
                    mailCC = employee_mail.getMail_id();
                    mailMessage += "Dear Payroll,<br><br>";
                    mailMessage += "Travel Settlement submitted by "+employee_mail.getEmployeeName()+" is waiting for your validation in iDEAL, kindly login to iDEAL for review and approval. <br><br>"
                                + "Please login to iDeal to view the details.<br><br>"
                                + "Regards,<br>"
                                + "iDeal Admin<br>"
                                + "http://ideal.hindujatech.com";
                    mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
                }
            } else if (status.equals("15") && dto.getCfo_action().equals("Y")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement is waiting for Approval";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                TravelSettlementDto manager_mail = getEmployeeMailId(travelDetails.getManagerId());
                TravelSettlementDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                String cfo_id = getCfo();
                TravelSettlementDto cfo_mail = getEmployeeMailId(cfo_id);
                mailCC=employee_mail.getMail_id()+","+manager_mail.getMail_id()+","+buh_mail.getMail_id();
                mailTo = cfo_mail.getMail_id();
                mailMessage += "Dear "+cfo_mail.getEmployee_name()+",<br><br>";
                mailMessage += "Settlement submitted by " + employee_mail.getEmployee_name() + " is approved by "+buh_mail.getEmployee_name()+" is waiting for your approval in iDEAL,  kindly login to iDEAL for review and approval<br><br>"
                        + "Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            } else if (status.equals("17")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement is waiting for Approval";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                String cfo_id = getCfo();
                TravelSettlementDto cfo_mail = getEmployeeMailId(cfo_id);
                mailCC = employee_mail.getMail_id()+","+cfo_mail.getMail_id();
                String finance_mail = getFinanceMailId(travelDetails.getPracticeGroupId());
                mailTo = finance_mail;
                mailMessage += "Dear Finance in charge,<br><br>";
                mailMessage += "Settlement submitted by " + employee_mail.getEmployee_name() + " is waiting for your approval in iDEAL, kindly login to iDEAL for review and approval.<br><br>"
                        + "Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
                // proof submittion mail
                if(travelDetails.getCountryId().equals("113")){
                    mailMessage="";
                    mailSubject = "Travel " + travelDetails.getTicketRefId() + " settlement-proofs to be couriered to Treasury at Gateway Office-Chennai";
                    mailTo = employee_mail.getMail_id();
                    mailCC = employee_mail.getMail_id();
                    mailMessage += "Dear "+employee_mail.getEmployee_name() +",<br><br>";
                    mailMessage += "You travel settlement is referred to treasury for validation, please arrange to send the print of Travel settlement summary page along with bills to Treasury at Hinduja Tech limited, A6 8th Floor, Gateway Office Parks, 16 GST Road, Perungalathur, Chennai-600 063.<br><br>"
                            + "Please login to iDeal to view the details.<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
                    mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
                }else{
                    mailMessage="";
                    mailSubject = "Travel " + travelDetails.getTicketRefId() + " settlement-submitted to Treasury for validation";
                    mailTo = getPayrollMailId();
                    mailCC = employee_mail.getMail_id();
                    mailMessage += "Dear Payroll,<br><br>";
                    mailMessage += "Travel Settlement submitted by "+employee_mail.getEmployeeName()+" is waiting for your validation in iDEAL, kindly login to iDEAL for review and approval. <br><br>"
                                + "Please login to iDeal to view the details.<br><br>"
                                + "Regards,<br>"
                                + "iDeal Admin<br>"
                                + "http://ideal.hindujatech.com";
                    mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
                }
            } else if (status.equals("19")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                TravelSettlementDto financeDetails = travelFinanceDetails(dto.getMasterId());
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                mailCC = employee_mail.getMail_id();
                String finance_mail = getFinanceMailId(travelDetails.getPracticeGroupId());;
                if (Integer.parseInt(financeDetails.getTobeRecovered()) > 0) {
                    mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement Waiting For Money Recovery";
                    mailTo = getPayrollMailId();
                    mailCC = finance_mail + "," + mailCC;
                    mailMessage += "Dear Treasury in charge,<br><br>";
                    mailMessage += "Above captioned Travel settlement being reviewed and approved by Finance in Charge, and waiting for your action on recovering the amount from Employee's Account. Update the iDEAL, once the amount is recovered in Employee's account.<br><br>"
                            + "Please login to iDeal to view the details.<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
                } else if (Integer.parseInt(financeDetails.getTobeDeposited()) > 0) {
                    mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement Waiting For Money Deposit";
                    mailTo = getTreasuryMailId();
                    mailCC = finance_mail + "," + mailCC;
                    mailMessage += "Dear Treasury in charge,<br><br>";
                    mailMessage += "Above captioned Travel settlement being review and approved by Finance in Charge, and waiting for your action on depositing the amount in Employee's Account. Update the iDEAL, once the amount is deposited in Employee's account.<br><br>"
                            + "Please login to iDeal to view the details.<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
                }
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            } else if (dto.getStatus().equals("21")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement Money Deposited";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                mailMessage += "Dear " + employee_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "Above captioned Travel settlement is completed and remaining amount is deposited to your account.<br><br>"
                        + "Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailCC = getTreasuryMailId();
                mailTo = employee_mail.getMail_id();
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            } else if (dto.getStatus().equals("22")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement Money Recovered";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                mailMessage += "Dear " + employee_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "Above captioned Travel settlement is completed and remaining amount is recovered from your account.<br><br>"
                        + "Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailCC = getPayrollMailId();
                mailTo = employee_mail.getMail_id();
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if (dto.getStatus().equals("14")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement is sent back";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                TravelSettlementDto rm_mail = getEmployeeMailId(travelDetails.getManagerId());
                mailMessage += "Dear " + employee_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "Travel Settlement submitted by you is being sent back by "+rm_mail.getEmployee_name()+" with below remarks. Request you to review and make necessary change, if any.<br><br>"
                        + "<b>Cancel Reason: </b>" + dto.getRm_remarks()
                        + "<br><br>Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailTo = employee_mail.getMail_id();
                mailCC = rm_mail.getMail_id();
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            } else if (dto.getStatus().equals("16")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement is sent back";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                TravelSettlementDto rm_mail = getEmployeeMailId(travelDetails.getManagerId());
                TravelSettlementDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                mailMessage += "Dear " + employee_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "Travel Settlement submitted by you is being sent back by  " + buh_mail.getEmployee_name() + ", with below remarks. Request you to review and make necessary change, if any.<br><br>"
                        + "<b>Cancel Reason: </b>" + dto.getBuh_remarks()
                        + "<br><br>Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailTo = employee_mail.getMail_id();
                mailCC = rm_mail.getMail_id() + "," + buh_mail.getMail_id();
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            } else if (dto.getStatus().equals("18")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Request Sent Back by BUH";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                TravelSettlementDto rm_mail = getEmployeeMailId(travelDetails.getManagerId());
                TravelSettlementDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                String cfo_id = getCfo();
                TravelSettlementDto cfo_mail = getEmployeeMailId(cfo_id);
                mailMessage += "Dear " + employee_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "Travel Settlement submitted by you is being sent back by  " + cfo_mail.getEmployee_name() + ", please find the reason below.<br><br>"
                        + "<b>Cancel Reason: </b>" + dto.getCfo_remarks()
                        + "<br><br>Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailTo = employee_mail.getMail_id();
                mailCC = rm_mail.getMail_id() + "," + buh_mail.getMail_id() + "," + cfo_mail.getMail_id();
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            } else if (dto.getStatus().equals("20")) {
                TravelSettlementDto travelDetails = travelDetails(dto.getMasterId());
                mailSubject = travelDetails.getTicketRefId() + " - Travel Settlement is sent back";
                TravelSettlementDto employee_mail = getEmployeeMailId(travelDetails.getEmpId());
                TravelSettlementDto rm_mail = getEmployeeMailId(travelDetails.getManagerId());
                mailMessage += "Dear " + employee_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "Travel Settlement submitted by you is being sent back by finance with below remarks. Request you to review and make necessary change, if any.<br><br>"
                        + "<b>Cancel Reason: </b>" + dto.getFinance_remarks()
                        + "<br><br>Please login to iDeal to view the details.<br><br>"
                        + "Regards,<br>"
                        + "iDeal Admin<br>"
                        + "http://ideal.hindujatech.com";
                mailTo = employee_mail.getMail_id();
                String finance_mail = getFinanceMailId(travelDetails.getPracticeGroupId());
                if (travelDetails.getDeviation().equals("Y") && dto.getCfo_action().equals("Y")) {
                    TravelSettlementDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                    mailCC = rm_mail.getMail_id() + "," + buh_mail.getMail_id() + "," + finance_mail;
                } else {
                    mailCC = rm_mail.getMail_id() + "," + finance_mail;
                }
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }
            // && dto.getDeviation().equals("N")
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TravelSettlementDto> getMailDetails() {
        ArrayList<TravelSettlementDto> list = null;
        try {
            list = (ArrayList) getSqlMapClientTemplate().queryForList("TravelSettlement.getConfigValueData");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<TravelSettlementDto> vendorList() {
        ArrayList<TravelSettlementDto> list = null;
        try {
            list = (ArrayList) getSqlMapClientTemplate().queryForList("TravelSettlement.vendorList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public TravelSettlementDto getEmployeeMailId(String emp_id) {
        TravelSettlementDto mail_id = null;
        try {
            mail_id = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.getEmployeeMailId", emp_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mail_id;
    }

    public TravelSettlementDto travelFinanceDetails(String master_id) {
        TravelSettlementDto mail_id = null;
        try {
            mail_id = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.getTravelFinanceDetails", master_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mail_id;
    }

    public String getFinanceMailId(String practiceGroupId) {
        String mail_id = null;
        try {
            mail_id = (String) getSqlMapClientTemplate().queryForObject("TravelSettlement.getFinanceMailId", practiceGroupId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mail_id;
    }

    public String getTreasuryMailId() {
        String mail_id = null;
        try {
            mail_id = (String) getSqlMapClientTemplate().queryForObject("TravelSettlement.getTreasuryMailId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mail_id;
    }

    public String getPayrollMailId() {
        String mail_id = null;
        try {
            mail_id = (String) getSqlMapClientTemplate().queryForObject("TravelSettlement.getPayrollMailId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mail_id;
    }

    public TravelSettlementDto travelCities(String masterId) {
        TravelSettlementDto citiDto = null;
        try {
            citiDto = (TravelSettlementDto) getSqlMapClientTemplate().queryForObject("TravelSettlement.travelCities", masterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citiDto;
    }
    public void updateBillDetails(TravelSettlementDto masterdto) {
        if (!masterdto.getBillReceived().equals("No")){
            System.out.println("not applicable");
            getSqlMapClientTemplate().update("TravelSettlement.updateBillDetails", masterdto);
        }
        ArrayList<TravelSettlementDto> connectionRes;
        connectionRes = getMailDetails();
        String con_username = connectionRes.get(0).getConfigValue();
        String con_password = connectionRes.get(1).getConfigValue();
        String con_host = connectionRes.get(2).getConfigValue();
        String con_port = connectionRes.get(3).getConfigValue();
        SendMail mailObj = null;
        try {
            mailObj = new SendMail(con_username, con_password, con_host, con_port);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        MailMessages mailMessageObj = new MailMessages();
        try {
            String travel_id = masterdto.getTicketRefId();
            HttpServletRequest requestObj = null;
            String mailSubject = "";
            String mailTo = "";
            String mailCC = "";
            String mailMessage = "";
            if (masterdto.getBillReceived().equals("Yes")) {
                mailSubject = travel_id + " - Bills Received";
                TravelSettlementDto admin_mail = getEmployeeMailId(masterdto.getFinance_id());
                mailCC = admin_mail.getMail_id();
                TravelSettlementDto employee_mail = getEmployeeMailId(masterdto.getEmpId());
                mailTo = employee_mail.getMail_id();
                mailMessage += "Dear " + employee_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "The documents submitted against the above Travel Plan has been received by Finance Team and the claim is under process."
                        + "<br><br>Regards,<br>"
                        + "Finance Team.<br>";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if (masterdto.getBillReceived().equals("No")) {
                mailSubject = travel_id + " - Bills Not Received";
                TravelSettlementDto admin_mail = getEmployeeMailId(masterdto.getFinance_id());
                mailCC = admin_mail.getMail_id();
                TravelSettlementDto employee_mail = getEmployeeMailId(masterdto.getEmpId());
                mailTo = employee_mail.getMail_id();
                mailMessage += "Dear " + employee_mail.getEmployee_name() + ",<br><br>";
                mailMessage += "Please send the original bills/documents against the above Travel Plan to the Finance team to proceed further."
                        + "<br><br>Regards,<br>"
                        + "Finance Team<br>";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }
        }catch(Exception e){
        }
    }
}
