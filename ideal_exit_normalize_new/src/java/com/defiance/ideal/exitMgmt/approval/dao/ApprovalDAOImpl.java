/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.exitMgmt.approval.dao;

import com.defiance.ideal.exitMgmt.approval.controller.ApprovalController;
import com.defiance.ideal.exitMgmt.approval.dto.ApprovalDTO;
import com.defiance.ideal.exitMgmt.employee.dto.EmployeeDTO;
import com.defiance.ideal.exitMgmt.utils.CommonConfigurations;
import com.defiance.ideal.exitMgmt.utils.CommonFunctions;
import com.defiance.ideal.exitMgmt.utils.MailMessages;
import com.defiance.ideal.exitMgmt.utils.SendMail;
import com.defiance.ideal.exitMgmt.utils.TrimSpace;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14583
 */
public class ApprovalDAOImpl extends SqlMapClientDaoSupport implements ApprovalDAO {

    public List<EmployeeDTO> getRegnEmpList(String employeeId, String moduleId, String approveType) {
        List<EmployeeDTO> employeeDetails = null;
        EmployeeDTO empFormData = new EmployeeDTO();
        try {
            empFormData.setEmpId(employeeId);
            empFormData.setModuleId(moduleId);
            empFormData.setApproveType(approveType);
            if (!moduleId.equals(Integer.toString(CommonConfigurations.EXIT_RM_APPROVAL_MODULE_ID))) {
                if (!moduleId.equals(Integer.toString(CommonConfigurations.EXIT_RM_APPROVAL_MODULE_ID)) && !moduleId.equals(Integer.toString(CommonConfigurations.EXIT_RM_CLERANCE_MODULE_ID))) {
                    empFormData.setEmpId(null);
                }
                empFormData.setSubmitStatus(Integer.toString(CommonConfigurations.RM_APPROVE_STATUS));
            }
            System.out.println("Here the problem is:::" + empFormData.getEmpId() + "-----" + empFormData.getModuleId() + "------" + empFormData.getSubmitStatus());
            employeeDetails = (List<EmployeeDTO>) getSqlMapClientTemplate().queryForList("ApprovalDetails.getRegnEmpList", empFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeDetails;
    }

    public void saveRmData(ApprovalDTO rmFormData) {
        EmployeeDTO empFormData = new EmployeeDTO();
        try {
            rmFormData.setReasonRejection(rmFormData.getReasonRejection());
            empFormData.setReasonRejection(rmFormData.getReasonRejection());// Needs to be remove (for  the time being)

            rmFormData.setExitEmpId(rmFormData.getExitEmpId());
            rmFormData.setModuleId(rmFormData.getModuleId());
            System.out.println("rmFormData empid ^^^^^^^^^^^ " + rmFormData.getResEmpId());
            System.out.println("::::::::::::::::" + rmFormData.getButtonName() + ";;;;;;;;");
            getSqlMapClientTemplate().update("ApprovalDetails.updateEmpRmData", rmFormData);
            if (rmFormData.getButtonName() != null && rmFormData.getButtonName().equals("Approve")) {
                rmFormData.setRmApprovedDate(new Date());
                rmFormData.setSubmitStatus(Integer.toString(CommonConfigurations.RM_APPROVE_STATUS));
                rmFormData.setDeleted(CommonConfigurations.RESIGN_UNDELETE);
                System.out.println("RM Comments : " + rmFormData.getRmComments());
                getSqlMapClientTemplate().insert("ApprovalDetails.insertRmData", rmFormData);
                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData", rmFormData);
                List rmDataList = new ArrayList();
                String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_ADMIN_APPROVAL_MODULE_ID),
                    Integer.toString(CommonConfigurations.EXIT_FINANCE_APPROVAL_MODULE_ID),
                    Integer.toString(CommonConfigurations.EXIT_NETWORK_APPROVAL_MODULE_ID),
                    Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
                triggerMail(rmDataList, toMailApprovalModules, empFormData, "rmApproveToEmp", "rmApproveToEmp", "rmApproveToEmp", CommonConfigurations.rmModuleName, rmFormData.getResEmpId(), null, null);
                System.out.println("Rm Data inserted for approve status");
            } else {
                List rmDataList = new ArrayList();
                String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
                triggerMail(rmDataList, toMailApprovalModules, empFormData, "rmRejectToEmp", "rmRejectToEmp", "rmRejectToEmp", CommonConfigurations.rmModuleName, rmFormData.getResEmpId(), null, null);
                rmFormData.setSubmitStatus(Integer.toString(CommonConfigurations.EMP_SAVE_STATUS));
                rmFormData.setDeleted(CommonConfigurations.RESIGN_DELETE);
                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData", rmFormData);
                System.out.println("Rm Data inserted for reject status");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ApprovalDTO getRmActionData(String exitEmpId) {
        ApprovalDTO rmActionData = null;
        try {
            ApprovalDTO rmFormData = new ApprovalDTO();
            rmFormData.setExitEmpId(exitEmpId);
            rmFormData.setCmpNoticePeriodInDays(CommonConfigurations.CMP_NOTICE_PERIOD_IN_DAYS);
            rmActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getRmActionData", rmFormData);
//            System.out.println("In DAOIMPL:::"+rmActionData.getLastWorkingDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rmActionData;
    }

    public void saveFinData(ApprovalDTO finFormData) {
        EmployeeDTO empFormData = new EmployeeDTO();
        System.out.println("::::In daoimpl:::" + finFormData.getModuleId() + "-----" + finFormData.getExitEmpId() + "----");
        empFormData.setModuleId(finFormData.getModuleId());
        empFormData.setAddressType(finFormData.getTotalAmount());
        try {
            TrimSpace.replaceNull(finFormData, CommonConfigurations.NULL_REPLACEMENT, CommonConfigurations.STR_NULL_REPLACEMENT, new boolean[]{true, true}, CommonConfigurations.FINANCE_NOT_NULL_FIELDS);
            empFormData.setExitEmpId(finFormData.getExitEmpId());
            System.out.println("::::In daoimpl:::" + empFormData.getModuleId() + "-----" + empFormData.getExitEmpId() + "----" + finFormData.getFinApprovalId() + ":::Testing" + finFormData.getButtonName() + "....");
            if (finFormData.getButtonName() != null && finFormData.getButtonName().equals("Submit")) {
                finFormData.setSubmitStatus(Integer.toString(CommonConfigurations.FIN_APPROVE_STATUS));
                if (CommonFunctions.checkData(finFormData, CommonConfigurations.surrenderYesValue, CommonConfigurations.FINANCE_NOT_NULL_FIELDS)) {
//                empFormData.setFinApprovedDate(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_INSERT));
                }
                finFormData.setFinApprovedDate(new Date());
                List finFormNoValues = TrimSpace.getDuePendingValues(finFormData, CommonConfigurations.NULL_REPLACEMENT, CommonConfigurations.STR_NULL_REPLACEMENT, new boolean[]{true, true}, CommonConfigurations.FINANCE_NOT_NULL_FIELDS, CommonConfigurations.FINANCE_DISPLAY_FIELDS, CommonConfigurations.surrenderYesValue);
                String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_FINANCE_APPROVAL_MODULE_ID), Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
                triggerMail(finFormNoValues, toMailApprovalModules, empFormData, "adminToEmp", "adminToEmpWithDue", "adminToEmpWithOutDue", CommonConfigurations.financeModuleName, finFormData.getResEmpId(), null, null);
                System.out.println("Fin Data Submitted and mail triggered");
//                 empFormData.setFinStatus("y");
            } else {
                finFormData.setSubmitStatus(Integer.toString(CommonConfigurations.RM_APPROVE_STATUS));
//                empFormData.setFinStatus("n");
            }
            finFormData.setDeleted(CommonConfigurations.RESIGN_UNDELETE);
            getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData", finFormData);
            if (finFormData.getFinApprovalId() != null && !finFormData.getFinApprovalId().equals("")) {
                getSqlMapClientTemplate().update("ApprovalDetails.updateFinData", finFormData);
//                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpStatus",empFormData);
                System.out.println("==========  The total amount in dao ========== " + finFormData.getTotalAmount());
//                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData",finFormData);
                System.out.println("Fin Data updated");
            } else {
                getSqlMapClientTemplate().insert("ApprovalDetails.insertFinData", finFormData);
//                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpStatus",empFormData);
//                getSqlMapClientTemplate().insert("ApprovalDetails.updateEmpData",finFormData);
                System.out.println("Fin Data inserted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveNsData(ApprovalDTO nsFormData) {
        EmployeeDTO empFormData = new EmployeeDTO();
        System.out.println("::::In daoimpl:::" + nsFormData.getModuleId() + "-----" + nsFormData.getExitEmpId() + "----");
        nsFormData.setModuleId(nsFormData.getModuleId());
        try {
            empFormData.setExitEmpId(nsFormData.getExitEmpId());
            System.out.println("::::In daoimpl:::" + empFormData.getModuleId() + "-----" + empFormData.getExitEmpId() + "----" + nsFormData.getNsApprovalId() + ":::Testing" + nsFormData.getButtonName() + "....");
            TrimSpace.replaceNull(nsFormData, CommonConfigurations.NULL_REPLACEMENT, CommonConfigurations.STR_NULL_REPLACEMENT, new boolean[]{true, true}, CommonConfigurations.NS_NOT_NULL_FIELDS);
            if (nsFormData.getButtonName() != null && nsFormData.getButtonName().equals("Submit")) {
                nsFormData.setSubmitStatus(Integer.toString(CommonConfigurations.NS_APPROVE_STATUS));
                if (CommonFunctions.checkData(nsFormData, CommonConfigurations.surrenderNoValue, CommonConfigurations.NS_NOT_NULL_FIELDS)) {
//                empFormData.setNsApprovedDate(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_INSERT));
                    nsFormData.setNsApprovedDate(new Date());
                }




//                empFormData.setNsStatus("y");
            } else {
                nsFormData.setSubmitStatus(Integer.toString(CommonConfigurations.RM_APPROVE_STATUS));
//                empFormData.setNsStatus("n");
            }
            nsFormData.setDeleted(CommonConfigurations.RESIGN_UNDELETE);
            getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData", nsFormData);
//            getSqlMapClientTemplate().update("ApprovalDetails.updateEmpStatus",empFormData);
            if (nsFormData.getNsApprovalId() != null && !nsFormData.getNsApprovalId().equals("")) {
                getSqlMapClientTemplate().update("ApprovalDetails.updateNsData", nsFormData);
//                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData",empFormData);
                System.out.println("Fin Data updated");
            } else {
                getSqlMapClientTemplate().insert("ApprovalDetails.insertNsData", nsFormData);
//                getSqlMapClientTemplate().insert("ApprovalDetails.updateEmpData",empFormData);
                System.out.println("Fin Data inserted");
            }
            if (nsFormData.getButtonName() != null && nsFormData.getButtonName().equals("Submit")) {
                List nsFormNoValues = TrimSpace.getDuePendingValues(nsFormData, CommonConfigurations.NULL_REPLACEMENT, CommonConfigurations.STR_NULL_REPLACEMENT, new boolean[]{true, true}, CommonConfigurations.NS_NOT_NULL_FIELDS, CommonConfigurations.NS_DISPLAY_FIELDS, CommonConfigurations.surrenderNoValue);
                String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_NETWORK_APPROVAL_MODULE_ID), Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
                triggerMail(nsFormNoValues, toMailApprovalModules, empFormData, "adminToEmp", "adminToEmpWithDue", "adminToEmpWithOutDue", CommonConfigurations.nsModuleName, nsFormData.getResEmpId(), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAdData(ApprovalDTO adFormData) {
        EmployeeDTO empFormData = new EmployeeDTO();
        System.out.println("::::In daoimpl:::" + adFormData.getModuleId() + "-----" + adFormData.getExitEmpId() + "----");
        empFormData.setModuleId(adFormData.getModuleId());
        try {
            adFormData.setExitEmpId(adFormData.getExitEmpId());
            System.out.println("::::In daoimpl:::" + empFormData.getModuleId() + "-----" + empFormData.getExitEmpId() + "----" + adFormData.getAdApprovalId() + ":::Testing" + adFormData.getButtonName() + "....");
            TrimSpace.replaceNull(adFormData, CommonConfigurations.NULL_REPLACEMENT, CommonConfigurations.STR_NULL_REPLACEMENT, new boolean[]{true, true}, CommonConfigurations.ADMIN_NOT_NULL_FIELDS);
            if (adFormData.getButtonName() != null && adFormData.getButtonName().equals("Submit")) {
                adFormData.setSubmitStatus(Integer.toString(CommonConfigurations.ADMIN_APPROVE_STATUS));
                if (CommonFunctions.checkData(adFormData, CommonConfigurations.surrenderNoValue, CommonConfigurations.ADMIN_NOT_NULL_FIELDS)) {
//                empFormData.setAdApprovedDate(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_INSERT));
                }
                adFormData.setAdApprovedDate(new Date());
                List adminFormNoValues = TrimSpace.getDuePendingValues(adFormData, CommonConfigurations.NULL_REPLACEMENT, CommonConfigurations.STR_NULL_REPLACEMENT, new boolean[]{true, true}, CommonConfigurations.ADMIN_NOT_NULL_FIELDS, CommonConfigurations.ADMIN_DISPLAY_FIELDS, CommonConfigurations.surrenderNoValue);
                String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_ADMIN_APPROVAL_MODULE_ID), Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
                //adminFormNoValues=null;
                triggerMail(adminFormNoValues, toMailApprovalModules, empFormData, "adminToEmp", "adminToEmpWithDue", "adminToEmpWithOutDue", CommonConfigurations.adminModuleName, adFormData.getResEmpId(), null, null);
                System.out.println("Admin Data Submitted and mail triggered");
//                empFormData.setAdminStatus("y");
            } else {
                adFormData.setSubmitStatus(Integer.toString(CommonConfigurations.RM_APPROVE_STATUS));
//                 empFormData.setAdminStatus("n");
            }
            adFormData.setDeleted(CommonConfigurations.RESIGN_UNDELETE);
            getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData", adFormData);
            if (adFormData.getAdApprovalId() != null && !adFormData.getAdApprovalId().equals("")) {
                getSqlMapClientTemplate().update("ApprovalDetails.updateAdData", adFormData);
//                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData",empFormData);
//                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpStatus",empFormData);
                System.out.println("Admin Data updated");
            } else {
                getSqlMapClientTemplate().insert("ApprovalDetails.insertAdData", adFormData);
//                getSqlMapClientTemplate().insert("ApprovalDetails.updateEmpData",empFormData);
//                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpStatus",empFormData);
                System.out.println("Admin Data inserted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveRmClrData(ApprovalDTO rmClrFormData) {
        EmployeeDTO empFormData = new EmployeeDTO();
        System.out.println("::::In daoimpl:::" + rmClrFormData.getModuleId() + "-----" + rmClrFormData.getExitEmpId() + "----");
        empFormData.setModuleId(rmClrFormData.getModuleId());
        try {
            empFormData.setExitEmpId(rmClrFormData.getExitEmpId());
            System.out.println("::::In daoimpl:::" + empFormData.getModuleId() + "-----" + empFormData.getExitEmpId() + "----" + rmClrFormData.getRmClrId() + ":::Testing" + rmClrFormData.getButtonName() + "....");
            TrimSpace.replaceNull(rmClrFormData, CommonConfigurations.NULL_REPLACEMENT, CommonConfigurations.STR_NULL_REPLACEMENT, new boolean[]{true, true}, CommonConfigurations.RMCLR_NOT_NULL_FIELDS);
            if (rmClrFormData.getButtonName() != null && rmClrFormData.getButtonName().equals("Submit")) {
                empFormData.setSubmitStatus(Integer.toString(CommonConfigurations.RMCLR_APPROVE_STATUS));
                if (CommonFunctions.checkData(rmClrFormData, CommonConfigurations.surrenderNoValue, CommonConfigurations.RMCLR_NOT_NULL_FIELDS)) {
                    rmClrFormData.setRmClrDate(new Date());
                }
                List rmClrFormNoValues = TrimSpace.getDuePendingValues(rmClrFormData, CommonConfigurations.NULL_REPLACEMENT, CommonConfigurations.STR_NULL_REPLACEMENT, new boolean[]{true, true}, CommonConfigurations.RMCLR_NOT_NULL_FIELDS, CommonConfigurations.RMCLR_DISPLAY_FIELDS, CommonConfigurations.surrenderNoValue);
                String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
                triggerMail(rmClrFormNoValues, toMailApprovalModules, empFormData, "adminToEmp", "adminToEmpWithDue", "adminToEmpWithOutDue", CommonConfigurations.rmModuleName, rmClrFormData.getResEmpId(), null, null);
                System.out.println("Admin Data Submitted and mail triggered");
//                 empFormData.setRmStatus("y");
            } else {
                empFormData.setSubmitStatus(Integer.toString(CommonConfigurations.RM_APPROVE_STATUS));
//                 empFormData.setRmStatus("n");
            }
            getSqlMapClientTemplate().update("ApprovalDetails.updateRmData", rmClrFormData);
//            getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData", empFormData);
//            getSqlMapClientTemplate().update("ApprovalDetails.updateEmpStatus", empFormData);

//            if(rmClrFormData.getRmClrId()!=null && !rmClrFormData.getRmClrId().equals("")){
//                getSqlMapClientTemplate().update("ApprovalDetails.updateRmData",rmClrFormData);
//                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData",empFormData);
//                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpStatus",empFormData);
//                System.out.println("Rm Clearance Data updated");
//            }
//            else{
////                getSqlMapClientTemplate().insert("ApprovalDetails.insertRmClrData",rmClrFormData);
//                getSqlMapClientTemplate().insert("ApprovalDetails.updateEmpData",empFormData);
//                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpStatus",empFormData);
//                System.out.println("Rm Clearance Data inserted");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveHrData(ApprovalDTO hrFormData, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("in save HR");
        EmployeeDTO empFormData = new EmployeeDTO();
        System.out.println("::::In daoimpl:::" + hrFormData.getModuleId() + "-----" + hrFormData.getExitEmpId() + "----");
        empFormData.setModuleId(hrFormData.getModuleId());
        try {
            hrFormData.setExitEmpId(hrFormData.getExitEmpId());
            System.out.println("::::In daoimpl:::" + empFormData.getModuleId() + "-----" + empFormData.getExitEmpId() + "----" + hrFormData.getHrApprovalId() + ":::Testing" + hrFormData.getButtonName() + "....");
            TrimSpace.replaceNull(hrFormData, CommonConfigurations.NULL_REPLACEMENT, CommonConfigurations.STR_NULL_REPLACEMENT, new boolean[]{true, true}, CommonConfigurations.HR_NOT_NULL_FIELDS);
            if (hrFormData.getButtonName() != null && hrFormData.getButtonName().equals("Submit")) {

                hrFormData.setSubmitStatus(Integer.toString(CommonConfigurations.HR_APPROVE_STATUS));
//                empFormData.setHrApprovedDate(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_INSERT));
                hrFormData.setHrApprovedDate(new Date());
                // Last Minute Change request
//                if(CommonFunctions.checkData(hrFormData,CommonConfigurations.surrenderNoValue,CommonConfigurations.HR_NOT_NULL_FIELDS)){
//                empFormData.setHrApprovedDate(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_INSERT));
//                }
//                List hrFormNoValues = TrimSpace.getDuePendingValues(hrFormData,CommonConfigurations.NULL_REPLACEMENT,CommonConfigurations.STR_NULL_REPLACEMENT,new boolean[]{true,true},CommonConfigurations.HR_NOT_NULL_FIELDS,CommonConfigurations.HR_DISPLAY_FIELDS,CommonConfigurations.surrenderNoValue);
//                Date today = new Date();
//                Timestamp timest = new Timestamp(today.getTime());
//                SimpleDateFormat sm = new SimpleDateFormat("ddmmyyhhmmss");
//                String modified_time = sm.format(timest);
//                hrFormData.setModified_time(modified_time);
//                hrFormData.setCreated_time(modified_time);
                Date date = new Date();
                DateFormat dfm1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String da1 = dfm1.format(date);
                long s = CommonFunctions.timeConversion(da1);
                hrFormData.setModified_time(s);
                hrFormData.setCreated_time(s);
                getSqlMapClientTemplate().insert("ApprovalDetails.insertRMChangedInfo", hrFormData);
                getSqlMapClientTemplate().insert("ApprovalDetails.insertEmpInfo", hrFormData);
                getSqlMapClientTemplate().update("ApprovalDetails.updateEmpStatus", hrFormData);
                getSqlMapClientTemplate().update("ApprovalDetails.updateManager", hrFormData);
                List hrFormNoValues = new ArrayList();
                String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
                triggerMail(hrFormNoValues, toMailApprovalModules, empFormData, "empToRmUpdate", "empToRmUpdate", "empToRmUpdate", CommonConfigurations.hrModuleName, hrFormData.getResEmpId(), request, response);
                if (hrFormData.getCheck().equals("true")) {
                    triggerMail(hrFormNoValues, toMailApprovalModules, empFormData, "pfRelatedNotes", "pfRelatedNotes", "pfRelatedNotes", CommonConfigurations.hrModuleName, hrFormData.getResEmpId(), request, response);
                } else {
                }
                if (hrFormData.getCheckService().equals("true") && !hrFormData.getEmploymentStatusId().equals("b")) {

                    triggerMail(hrFormNoValues, toMailApprovalModules, empFormData, "accpLetter", "accpLetter", "accpLetter", CommonConfigurations.hrModuleName, hrFormData.getResEmpId(), request, response);
                } else {
                }
                System.out.println("Hr Data Submitted and mail triggered");
            } else {
                hrFormData.setSubmitStatus(Integer.toString(CommonConfigurations.RM_APPROVE_STATUS));
            }
            hrFormData.setDeleted(CommonConfigurations.RESIGN_UNDELETE);
            getSqlMapClientTemplate().update("ApprovalDetails.updateEmpData", hrFormData);
            if (hrFormData.getHrApprovalId() != null && !hrFormData.getHrApprovalId().equals("")) {
                getSqlMapClientTemplate().update("ApprovalDetails.updateHrData", hrFormData);
            } else {
                getSqlMapClientTemplate().insert("ApprovalDetails.insertHrData", hrFormData);
                System.out.println("Hr Data inserted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ApprovalDTO getFinActionData(String exitEmpId) {
        ApprovalDTO finActionData = null;
        try {
            finActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getFinActionData", exitEmpId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finActionData;
    }

    public ApprovalDTO getNSActionData(String exitEmpId) {
        ApprovalDTO nsActionData = null;
        try {
            nsActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getNSActionData", exitEmpId);
//            System.out.println("In DAOIMPL:::"+rmActionData.getLastWorkingDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nsActionData;
    }

    public ApprovalDTO getAdminActionData(String exitEmpId) {
        ApprovalDTO adActionData = null;
        try {
            adActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getAdminActionData", exitEmpId);
//            System.out.println("In DAOIMPL:::"+rmActionData.getLastWorkingDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adActionData;
    }

//    public ApprovalDTO getRmClrData(String exitEmpId) {
//        ApprovalDTO rmClrData = null;
//        try {
//            rmClrData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getRmClrData",exitEmpId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rmClrData;
//    }
    public ApprovalDTO getHrActionData(String exitEmpId) {
        ApprovalDTO hrActionData = null;
        try {
            hrActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getHrActionData", exitEmpId);
//            System.out.println("In DAOIMPL:::"+rmActionData.getLastWorkingDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hrActionData;
    }

    public List<ApprovalDTO> getSurveyQuestions(String exitEmpId) {
        List<ApprovalDTO> surveyQuestions = null;
//        List<ApprovalDTO> checkSavedData=null;
        try {
//           checkSavedData=( List<ApprovalDTO>)getSqlMapClientTemplate().queryForList("ApprovalDetails.checkSavedData",exitEmpId);
//           if(checkSavedData.size()!=0)
            surveyQuestions = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("ApprovalDetails.getSurveyQuestionsAndAnswers", exitEmpId);
//           else
//            surveyQuestions=(List<ApprovalDTO>)  getSqlMapClientTemplate().queryForList("ApprovalDetails.getSurveyQuestions");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return surveyQuestions;
    }

    public List<ApprovalDTO> getSurveyAnswers() {
        List<ApprovalDTO> surveyAnswers = null;
        try {
            surveyAnswers = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("ApprovalDetails.getSurveyAnswers");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return surveyAnswers;
    }

    public void saveSurveyData(String[] questionId, String[] empAnswer, ApprovalDTO surveyData) throws Exception {
        List<ApprovalDTO> checkSavedData = null;
        try {
//            checkSavedData=( List<ApprovalDTO>)getSqlMapClientTemplate().queryForList("ApprovalDetails.checkSavedData",surveyData.getExitEmpId());
            checkSavedData = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("ApprovalDetails.checkSavedData", surveyData.getEmpId());
            getSqlMapClient().startTransaction();
            getSqlMapClient().startBatch();
            for (int i = 0; i < questionId.length; i++) {
                surveyData.setQuestionId(questionId[i]);
                surveyData.setEmpAnswer(empAnswer[i]);
                if (checkSavedData.size() == 0) {
                    getSqlMapClientTemplate().insert("ApprovalDetails.insertSurveyData", surveyData);
                } else {
                    getSqlMapClientTemplate().update("ApprovalDetails.updateSurveyData", surveyData);
                }
            }
            getSqlMapClient().executeBatch();
            getSqlMapClient().commitTransaction();
        } finally {
            getSqlMapClient().endTransaction();
        }
    }

    public void saveFinMultipleData(String[] primaryKey, String[] otherCategory, String[] other, String[] otherAmt, String[] otherRemarks, ApprovalDTO finFormData) throws Exception {
        List<ApprovalDTO> checkSavedData = null;
        ApprovalDTO finApprovalId = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.findFinApprovalId", finFormData.getExitEmpId());
        finFormData.setFinApprovalId(finApprovalId.getFinApprovalId());
        String deleteId;
        try {
            //   checkSavedData=( List<ApprovalDTO>)getSqlMapClientTemplate().queryForList("ApprovalDetails.checkFinanceSavedData",finFormData.getExitEmpId());
            getSqlMapClient().startTransaction();
            getSqlMapClient().startBatch();
            for (int i = 0; i < primaryKey.length; i++) {
                deleteId = primaryKey[i];
                getSqlMapClientTemplate().delete("ApprovalDetails.deleteFinLoan", deleteId);
            }
            for (int i = 0; i < other.length; i++) {
                System.out.println("=== The other category is == " + otherCategory[i]);
                System.out.println("=== The other loan is == " + other[i]);
                System.out.println("=== The other amount is == " + otherAmt[i]);
                System.out.println("=== The other remarks is == " + otherRemarks[i]);
                finFormData.setOtherCategory(otherCategory[i]);
                finFormData.setOther(other[i]);
                finFormData.setOtherAmt(otherAmt[i]);
                finFormData.setOtherRemarks(otherRemarks[i]);

                // if(checkSavedData.size()==0){
                getSqlMapClientTemplate().insert("ApprovalDetails.insertFinLoan", finFormData);
//                    System.out.println("inside insert");
//                }else{
//                getSqlMapClientTemplate().update("ApprovalDetails.updateFinLoan",finFormData);
//                    System.out.println("inside update***********");
//                }
            }
            getSqlMapClient().executeBatch();
            getSqlMapClient().commitTransaction();
        } finally {
            getSqlMapClient().endTransaction();
        }
    }

    public void deleteFinMultipleData(String[] otherCategory, String[] other, String[] otherAmt, String[] otherRemarks, ApprovalDTO finFormData) throws Exception {
        List<ApprovalDTO> checkSavedData = null;
        ApprovalDTO finApprovalId = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.findFinApprovalId", finFormData.getExitEmpId());
        finFormData.setFinApprovalId(finApprovalId.getFinApprovalId());
        try {
            checkSavedData = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("ApprovalDetails.checkFinanceSavedData", finFormData.getExitEmpId());
            getSqlMapClient().startTransaction();
            getSqlMapClient().startBatch();
            for (int i = 0; i < other.length; i++) {
                System.out.println("=== The other category is == " + otherCategory[i]);
                System.out.println("=== The other loan is == " + other[i]);
                System.out.println("=== The other amount is == " + otherAmt[i]);
                System.out.println("=== The other remarks is == " + otherRemarks[i]);
                finFormData.setOtherCategory(otherCategory[i]);
                finFormData.setOther(other[i]);
                finFormData.setOtherAmt(otherAmt[i]);
                finFormData.setOtherRemarks(otherRemarks[i]);

//                if(checkSavedData.size()==0){
//                getSqlMapClientTemplate().insert("ApprovalDetails.insertFinLoan", finFormData);
//                    System.out.println("inside insert");
//                }else{
                getSqlMapClientTemplate().delete("ApprovalDetails.deleteFinLoan", finFormData);
                System.out.println("inside delete***********");
            }

            getSqlMapClient().executeBatch();
            getSqlMapClient().commitTransaction();
        } finally {
            getSqlMapClient().endTransaction();
        }
    }

//    public void updateFinMultipleData(ApprovalDTO finFormData,String[] PrimaryotherCategory,String[] Primaryother,String[] PrimaryotherAmt,String[] PrimaryotherRemarks,String[] otherId) throws Exception {
//         ApprovalDTO finApprovalId= (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.findFinApprovalId", finFormData.getExitEmpId());
//         finFormData.setFinApprovalId(finApprovalId.getFinApprovalId());
//        try {
//            getSqlMapClient().startTransaction();
//            getSqlMapClient().startBatch();
//            for (int i = 0; i < Primaryother.length; i++) {
//
//                finFormData.setOtherCategory(PrimaryotherCategory[i]);
//                finFormData.setOther(Primaryother[i]);
//                finFormData.setOtherAmt(PrimaryotherAmt[i]);
//                finFormData.setOtherRemarks(PrimaryotherRemarks[i]);
//
//                getSqlMapClientTemplate().update("ApprovalDetails.updateFinLoan",finFormData);
//                    System.out.println("inside update1111");
//            }
//            getSqlMapClient().executeBatch();
//            getSqlMapClient().commitTransaction();
//        } finally {
//            getSqlMapClient().endTransaction();
//        }
//    }
    public List<ApprovalDTO> getfinMultipleData(String exitEmpId) {
        List<ApprovalDTO> FinanceSavedData = null;
        try {
            FinanceSavedData = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("ApprovalDetails.checkFinanceSavedData", exitEmpId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FinanceSavedData;
    }

    public void submitSurveyData(ApprovalDTO surveyFormData) {
        surveyFormData.setSession_empid(surveyFormData.getEmpId());
        List exitSurveyList = new ArrayList();
        EmployeeDTO empFormData = new EmployeeDTO();
        try {
            HttpServletRequest request = null;
            HttpServletResponse response = null;
            getSqlMapClientTemplate().update("ApprovalDetails.updateSurveyStatus", surveyFormData);
            String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
            triggerMail(exitSurveyList, toMailApprovalModules, empFormData, "exitSurveyToHr", "exitSurveyToHrMsg", "exitSurveyToHrMsg", CommonConfigurations.exitSurveyModuleName, surveyFormData.getEmpId(), null, null);
            System.out.println("Exit Survey Data Submitted and mail triggered");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void triggerMail(List formNoValues, String[] toMailApprovalModules, EmployeeDTO empFormData,
            String mailSubReason, String mailMessageWithDue, String mailMessageWithOutDue, String mailSubModule, String resEmpId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        MailMessages mailMsg = new MailMessages();
        String mailTo = null;
        String mailCc = null;
        String mailBcc = null;
        String mailSubject = null;
        String mailMessage = null;
        List mailToApprovalIds = new ArrayList();
        List mailCcApprovalIds = new ArrayList();
        Properties configFile = new Properties();

        try {
            configFile.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ApprovalDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            SendMail mailObj = new SendMail();
            EmployeeDTO getEmailAddress = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getEmailAddressNew", resEmpId);
            empFormData.setEmpStructure(getEmailAddress.getEmpStructure());
            List<EmployeeDTO> HrCCList = getSqlMapClientTemplate().queryForList("EmployeeDetails.getHRmailCC");
            for (int increment = 0; increment < toMailApprovalModules.length; increment++) {
                mailToApprovalIds.add(toMailApprovalModules[increment]);
            }
            EmployeeDTO dto = new EmployeeDTO();
            dto.setEmpId(resEmpId);
            List<EmployeeDTO> CCList = getSqlMapClientTemplate().queryForList("EmployeeDetails.getEmailAddress", resEmpId);
            empFormData.setApprovalMenuNames(mailToApprovalIds);
            EmployeeDTO getGUAccessEmail = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getGUAccessEmail", empFormData);

            if (mailSubModule.equals(CommonConfigurations.exitSurveyModuleName)) {
                System.out.println("inside exitSurveyModuleName");
                EmployeeDTO getExitSurveyDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getExitSurveyDL", empFormData);

                mailTo = getExitSurveyDL.getExitsurveydl();
                mailCc = getEmailAddress.getEmpWorkMail();
            } //added by gopinath
            else if (mailSubModule.equals(CommonConfigurations.adminModuleName)
                    || mailSubModule.equals(CommonConfigurations.financeModuleName)
                    || (mailSubModule.equals(CommonConfigurations.rmModuleName) && mailSubReason.equals("adminToEmp"))
                    || mailSubModule.equals(CommonConfigurations.nsModuleName)) {
                System.out.println("exit id admin/finance module ===========================>" + resEmpId);
                ApprovalDTO nsActionData = null;
                try {
                    nsActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getEmailStatus", resEmpId);
                    //            System.out.println("In DAOIMPL:::"+rmActionData.getLastWorkingDate());
                    System.out.println("email dectivation status query>>>>>>>>>>>>" + nsActionData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (nsActionData != null && mailSubModule.equals(CommonConfigurations.nsModuleName)) {
                    System.out.println("inside nsActionData IFFFF");
                    String emailStatus = nsActionData.getEmailDeactivation();
                    String desktopPasswordStatus = nsActionData.getDesktopPwd();
                    String salesCrmStatus = nsActionData.getSalesCrm();
                    String financeDaxStatus = nsActionData.getFinDax();
                    String nsOthersStatus = nsActionData.getNsOthers();
                    System.out.println("emailStatus>>>>>>>>>>>>>>>>>>>>" + emailStatus);
                    if (emailStatus.equals("2") || desktopPasswordStatus.equals("2")
                            || salesCrmStatus.equals("2") || financeDaxStatus.equals("2")
                            || nsOthersStatus.equals("2")) {
                        mailTo = getEmailAddress.getEmpWorkMail();
                        System.out.println("mail to work mail id >>>>>>>>>>>>>>>>>>>>" + mailTo);

                    } else {
                        mailTo = getEmailAddress.getPersonalEmail();
                        System.out.println("mail to personal mail id >>>>>>>>>>>>>>>>>>>>" + mailTo);
                    }
                    // mailCc = getGUAccessEmail.approvalWorkEmail;
                    EmployeeDTO getNSapprovalDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getNSapprovalDL", empFormData);
                    mailCc = getNSapprovalDL.getNsApprovaldl() + "," + getEmailAddress.getRmWorkMail();
                } else {
                    System.out.println("ELSE  clearance");
                    if (mailSubModule.equals(CommonConfigurations.financeModuleName)) {
                        System.out.println("ELSE  Finance clearance");
                        EmployeeDTO getFinanceapprovalDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getFinanceapprovalDL", empFormData);
                        mailTo = getEmailAddress.getEmpWorkMail();
                        mailCc = getFinanceapprovalDL.getFinApprovaldl() + "," + getEmailAddress.getRmWorkMail();
                    }
                    if (mailSubModule.equals(CommonConfigurations.adminModuleName)) {
                        System.out.println("ELSE  admin clearance");
                        EmployeeDTO getAdminapprovalDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getAdminapprovalDL", empFormData);
                        mailTo = getEmailAddress.getEmpWorkMail();
                        mailCc = getAdminapprovalDL.getAdminApprovaldl() + "," + getEmailAddress.getRmWorkMail();
                    }
                    if (mailSubModule.equals(CommonConfigurations.rmModuleName)) {
                        System.out.println("ELSE  rm clearance");
                        EmployeeDTO getRMclearanceDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getRMclearanceDL", empFormData);
                        mailTo = getEmailAddress.getPersonalEmail();
                        mailCc = getRMclearanceDL.getRmClearancedl() + "," + getEmailAddress.getRmWorkMail();
                        String pm_id = (String) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getPMMailIdForCC", resEmpId);
                        System.out.println("pm_id " + pm_id);
                        if(pm_id!=null){
                            mailCc = mailCc +","+pm_id;
                        }
                
                    }

                }

            } //add ends gopinath
            else if (mailSubModule.equals(CommonConfigurations.rmModuleName) && mailMessageWithOutDue.equals("rmApproveToEmp")) {
                System.out.println("inside rmModuleName 33 rmApproveToEmp");
//                String qualityHeadId = configFile.getProperty("rmApprovalAdditionalMailToQualityHead");
//                String qualityHeadMailId =  (String) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getQualityHeadMailId", qualityHeadId);
//                

                EmployeeDTO getRmapprovalDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getRmapprovalDL", empFormData);
                EmployeeDTO getBUHEmail = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getBUHEmail", empFormData);
                EmployeeDTO getRmapprovalDLTo = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getRmapprovalDLTo", empFormData);
                String pm_id = (String) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getPMMailIdForCC", resEmpId);
                System.out.println("pm_id " + pm_id);
                mailTo = getEmailAddress.getEmpWorkMail() + "," + getRmapprovalDLTo.getRmApprovaldlto();
                if ("2".equals(empFormData.getEmpStructure()) || "5".equals(empFormData.getEmpStructure())) {
                    mailCc = getRmapprovalDL.getRmApprovaldl() + "," + getBUHEmail.getBuhmail() + "," + getEmailAddress.getRmWorkMail();
                } else {
                    mailCc = getRmapprovalDL.getRmApprovaldl() + "," + getEmailAddress.getRmWorkMail();;
                }
                if (pm_id != null) {
                    mailCc = mailCc + "," + pm_id;
                }
            } else if (mailSubModule.equals(CommonConfigurations.rmModuleName) && (mailMessageWithOutDue.equals("adminToEmpWithDue") || (mailMessageWithOutDue.equals("adminToEmpWithOutDue")))) {
                System.out.println("inside rmModuleName ## adminToEmpWithDue");
                mailTo = getEmailAddress.getEmpWorkMail();
                mailCc = getGUAccessEmail.approvalWorkEmail + "," + getEmailAddress.getRmWorkMail();
            } else if (mailSubModule.equals(CommonConfigurations.rmModuleName) && mailMessageWithOutDue.equals("rmRejectToEmp")) {
                System.out.println("rmRejectToEmp");
                EmployeeDTO getResignationSubmissionEmail = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getResignationSubmissionEmail", empFormData);
                EmployeeDTO getBUHEmail = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getBUHEmail", empFormData);
                EmployeeDTO getRmapprovalDLTo = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getRmapprovalDLTo", empFormData);
                String pm_id = (String) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getPMMailIdForCC", resEmpId);
                System.out.println("pm_id " + pm_id);
                mailTo = getEmailAddress.getEmpWorkMail();
                mailCc = getEmailAddress.getRmWorkMail() + "," + getRmapprovalDLTo.getRmApprovaldlto() + "," + getBUHEmail.getBuhmail() + "," + getResignationSubmissionEmail.getDlmail();
                if (pm_id != null) {
                    mailCc = mailCc + "," + pm_id;
                }
            } else if (mailSubModule.equals(CommonConfigurations.hrModuleName) && mailMessageWithOutDue.equals("pfRelatedNotes")) {
                System.out.println("pfRelatedNotes");
                EmployeeDTO getRalPFDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getRalPFDL", empFormData);

                mailTo = getEmailAddress.getPersonalEmail();
                mailCc = getRalPFDL.getRalPFdl();
            } else if (mailSubModule.equals(CommonConfigurations.hrModuleName) && mailMessageWithOutDue.equals("empToRmUpdate")) {
                System.out.println("hr clearance @@@@@@@@@@@");

                EmployeeDTO getHRclearanceDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getHRclearanceDL", empFormData);
                EmployeeDTO getBUHEmail = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getBUHEmail", empFormData);

                String mailID = getHRclearanceDL.getHrApprovaldl();
                //add the isms mail cc here
                mailTo = getEmailAddress.getRmWorkMail();
                if ("2".equals(empFormData.getEmpStructure()) || "5".equals(empFormData.getEmpStructure())) {
                    if (getEmailAddress.getEmpStatus().equals("Terminated") || getEmailAddress.getEmpStatus().equals("Asked to resign") || getEmailAddress.getEmpStatus().equals("Absconding")) {
                        mailCc = mailID + "," + getBUHEmail.getBuhmail() + "," + getEmailAddress.getPersonalEmail() + "," + configFile.getProperty("ISMSMailId");
                    } else {
                        mailCc = mailID + "," + getBUHEmail.getBuhmail() + "," + getEmailAddress.getPersonalEmail();
                    }
                } else {
                    if (getEmailAddress.getEmpStatus().equals("Terminated") || getEmailAddress.getEmpStatus().equals("Asked to resign") || getEmailAddress.getEmpStatus().equals("Absconding")) {
                        mailCc = mailID + "," + getBUHEmail.getBuhmail() + "," + getEmailAddress.getPersonalEmail() + "," + configFile.getProperty("ISMSMailId");
                    } else {
                        mailCc = mailID + "," + getBUHEmail.getBuhmail() + "," + getEmailAddress.getPersonalEmail();
                    }
                }




            } else if (mailSubModule.equals(CommonConfigurations.hrModuleName) && mailMessageWithOutDue.equals("accpLetter")) {
                System.out.println("accpLetter !!!!!");

                //  mailCc = mailID + "," + configFile.getProperty("GroupMailOfHR");
                EmployeeDTO getRalPFDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getRalPFDL", empFormData);

                mailTo = getEmailAddress.getPersonalEmail();
                mailCc = getRalPFDL.getRalPFdl();
            } else if (mailSubModule.equals(CommonConfigurations.hrModuleName) && mailMessageWithOutDue.equals("serviceLetter")) {
                System.out.println("serviceLetter !!!!");
                EmployeeDTO getRalPFDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getRalPFDL", empFormData);

                mailTo = getEmailAddress.getPersonalEmail();
                mailCc = getRalPFDL.getRalPFdl();
            } else {
                mailTo = getEmailAddress.getEmpWorkMail();
                EmployeeDTO getRalPFDL = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getRalPFDL", empFormData);

                mailCc = getRalPFDL.getRalPFdl();
            }
            if (mailSubModule.equals(CommonConfigurations.hrModuleName)) {
                mailSubject = mailMsg.getSubject(mailSubReason, mailSubModule + "#~~#" + getEmailAddress.getEmployeeNumber());
            } else {
                mailSubject = mailMsg.getSubject(mailSubReason, mailSubModule + "#~~#" + getEmailAddress.getRmName());
            }
            if (formNoValues.size() != 0 && !mailSubModule.equals(CommonConfigurations.adminModuleName)) {
                mailMessage = mailMsg.getOthersMailMessage(mailMessageWithDue, getEmailAddress.getEmployeeName() + "#~~#" + mailSubModule + "#~~#" + empFormData.getAddressType(), formNoValues);
            } else {
                mailMessage = mailMsg.getOthersMailMessage(mailMessageWithOutDue, getEmailAddress.getEmployeeName() + "#~~#" + mailSubModule + "#~~#" + empFormData.getReasonRejection() + "#~~#" + getEmailAddress.getRmName() + "#~~#" + getEmailAddress.getBand() + "#~~#" + getEmailAddress.getResDate() + "#~~#" + getEmailAddress.getEmpStatus() + "#~~#" + getEmailAddress.getResDate(), formNoValues);
            }
            String fileName = "";
            String ressub = "Acceptance of Resignation Letter - " + getEmailAddress.getEmployeeNumber();
            String sersub = "Service Certificate and Relieving Letter - " + getEmailAddress.getEmployeeNumber();
            if (ressub.equals(mailSubject)) {
                System.out.println("comesssssssssssssssssssss");
                fileName = this.resLetter(request, response);
            } else if (sersub.equals(mailSubject)) {
                fileName = this.serLetter(request, response);
            }
            mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCc, mailBcc, getEmailAddress.getEmployeeNumber(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFileDb(String fileName, String contentType, String referenceName, int refId, String moduleName) {
        HashMap map = new HashMap();
        map.put("fileName", fileName);
        map.put("contentType", contentType);
        map.put("referenceName", referenceName);
        map.put("refId", refId);
        map.put("moduleName", moduleName);
        getSqlMapClientTemplate().insert("ApprovalDetails.addFileDb", map);
    }

    public ApprovalDTO getFile(int exitEmpId, String EXIT_MODULE_CODE) {
        ApprovalDTO dataObj = null;
        HashMap map = new HashMap();
        map.put("referenceId", exitEmpId);
        map.put("moduleName", EXIT_MODULE_CODE);
        dataObj = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getFile", map);
        return dataObj;
    }

    public void submitExitSurvey(ApprovalDTO formData, String session_empid) {
        try {
            formData.setSession_empid(session_empid);
            List<ApprovalDTO> checkSavedData = null;
            checkSavedData = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("ApprovalDetails.checkSavedData", formData.getEmpId());
            if (checkSavedData.size() == 0) {
                String d = null;
                String employee_experience = null;
                String[] q = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "14", "15",
                    "16", "17", "18", "19", "20", "21", "22", "23", "24", "26", "27", "28", "29", "30", "31", "32"};
                String[] a = new String[]{"3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "", "",
                    "", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3"};
                employee_experience = (String) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getWorkExperience", formData.getEmpId());
                int work_experience = Integer.parseInt(employee_experience);
                if (work_experience > 0 && 12 > work_experience) {
                    d = "1";
                } else if (work_experience > 12 && 24 > work_experience) {
                    d = "2";
                } else if (work_experience > 24 && 48 > work_experience) {
                    d = "3";
                } else if (work_experience > 48) {
                    d = "4";
                }

                for (int i = 0; i < q.length; i++) {
                    formData.setQuestionId(q[i]);
                    if (i == 0) {

                        formData.setEmpAnswer(d);
                    } else {
                        formData.setEmpAnswer(a[i]);
                    }
                    getSqlMapClientTemplate().insert("ApprovalDetails.insertSurveyData", formData);
                }
            }

            getSqlMapClientTemplate().update("ApprovalDetails.updateSurveyStatus", formData);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public List<ApprovalDTO> getExitEmployeeStatus() {
        List<ApprovalDTO> details = null;
        try {
            details = getSqlMapClientTemplate().queryForList("ApprovalDetails.getExitEmployeeStatus");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public void updateFileDb(String fileName, String contentType, String referenceName, int refId, String moduleName) {
        HashMap map = new HashMap();
        map.put("fileName", fileName);
        map.put("contentType", contentType);
        map.put("referenceName", referenceName);
        map.put("refId", refId);
        map.put("moduleName", moduleName);
        //getSqlMapClientTemplate().insert("ApprovalDetails.addFileDb", map);
        getSqlMapClientTemplate().update("ApprovalDetails.updateFileDb", map);
    }

    public List<ApprovalDTO> getEmploymentStatus() {
        List<ApprovalDTO> details = null;
        try {
            details = getSqlMapClientTemplate().queryForList("ApprovalDetails.getEmploymentStatus");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public String resLetter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String resEmpId = (String) request.getAttribute("resEmpId");
        int flag = 0;
        ApprovalDTO rmFormData = new ApprovalDTO();
        rmFormData.setEmpId(resEmpId);
        EmployeeDTO employeeDetails = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getEmployeeDetails", rmFormData);
        String fileName = employeeDetails.getEmployeeNumber() + "_RAL.pdf";

        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        document.open();
        PdfReader reader = new PdfReader("Resignation_Acceptance_Letter.pdf");
        document.add(new Paragraph(""));
        rmFormData.setExitEmpId(employeeDetails.getExitEmpId());
        rmFormData.setCmpNoticePeriodInDays(CommonConfigurations.CMP_NOTICE_PERIOD_IN_DAYS);
        ApprovalDTO rmActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getRmActionData", rmFormData);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + employeeDetails.getEmployeeNumber() + "_SRL.pdf\"");
        String filePath = "D:\\quailtyFileUpload\\";
        String file_name = employeeDetails.getEmployeeNumber() + "_SRL.pdf";
        File file = new File(filePath + file_name);
        if (file.delete()) {
            System.out.println("file deleted " + filePath + file_name);
        } else {
        }
        PdfWriter.getInstance(document, new FileOutputStream(new File(filePath + "//" + file_name)));
        document.open();

        Image headerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/ht_logo.png");
        headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
        document.add(headerImage);

        Image footerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/bonafied_footer.jpg");
        footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
        footerImage.setAbsolutePosition(0, 0);
        document.add(footerImage);
        
        if(employeeDetails.getEmpLeavingReason().equals("595")){
            Paragraph para6 = new Paragraph();
            para6.add(new Phrase("Acceptance of Retirement",new Font(Font.TIMES_ROMAN, 10, Font.BOLD|Font.UNDERLINE)));
            para6.setIndentationLeft(50f);
            para6.setIndentationRight(50f);
            para6.setAlignment(Element.ALIGN_CENTER);
            document.add(para6);

            Paragraph para = new Paragraph();
            para.add(new Phrase(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT),new Font(Font.TIMES_ROMAN, 9)));
            para.setIndentationRight(50f);
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);

            PdfPTable table = new PdfPTable(2);
            PdfPCell pfcell;
            table.setTotalWidth(490);
            table.setLockedWidth(true);
            table.setWidths(new float[]{0.55f, 2});
            pfcell = new PdfPCell(new Phrase("Employee Name",new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getEmployeeName(),new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);

            pfcell = new PdfPCell(new Phrase("Employee Id",new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getEmployeeNumber(),new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);

            pfcell = new PdfPCell(new Phrase("Band",new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getBand(),new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);

            pfcell = new PdfPCell(new Phrase("Sub Band",new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getSubBand(),new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);

            pfcell = new PdfPCell(new Phrase("Designation",new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getDesignation(),new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            document.add(table);

            Paragraph para3 = new Paragraph();
            para3.add(new Phrase("\nDear "+employeeDetails.getEmployeeName()+",\n\n",new Font(Font.TIMES_ROMAN, 10)));
            para3.add(new Phrase("On behalf of Hinduja Tech Ltd, we inform you that your retirement is effective on " +dateFormat.format(rmActionData.getLastWorkingDate())+", according to the policies of Hinduja Tech Ltd.",new Font(Font.TIMES_ROMAN, 10)));
            para3.setIndentationLeft(50f);
            para3.setIndentationRight(50f);
            para3.setLeading(11f);
            para3.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para3);  

            Paragraph para5 = new Paragraph();
            para5.add(new Phrase("\nWe have always cherished your association with us. We take this opportunity to "
                    + "place on record our appreciation of your dedicated service and the valuable contribution made by you "
                    + "during your career with us.\n\nOn behalf of the HT family, we wish you a very happy retired life.\n\n",new Font(Font.TIMES_ROMAN, 10)));
            para5.setIndentationLeft(50f);
            para5.setIndentationRight(50f);
            para5.setLeading(11f);
            para5.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para5);

            Paragraph para7 = new Paragraph();
            para7.add(new Phrase("Yours Truly,",new Font(Font.TIMES_ROMAN, 9)));
            para7.add(new Phrase("\nFor Hinduja Tech Ltd.",new Font(Font.TIMES_ROMAN, 9)));
            para7.setSpacingBefore(10f);
            para7.setIndentationLeft(50f);
            para7.setLeading(11f);
            para7.setAlignment(Element.ALIGN_LEFT);
            document.add(para7);

            Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/hr_signature.jpg");
            signImage.setAbsolutePosition(45, signImage.absoluteY());
            document.add(signImage);
//            
            Paragraph para8 = new Paragraph();
            para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(Font.TIMES_ROMAN, 9)));
            para8.add(new Phrase("\nGeneral Manager - HR",new Font(Font.TIMES_ROMAN, 9)));
            para8.setIndentationLeft(50f);
            para8.setLeading(11f);
            para8.setAlignment(Element.ALIGN_LEFT);
            document.add(para8);

        }else{
            Paragraph para6 = new Paragraph();
            para6.add(new Phrase("Acceptance of Resignation Letter", new Font(Font.TIMES_ROMAN, 10, Font.BOLD | Font.UNDERLINE)));
            para6.setIndentationLeft(50f);
            para6.setIndentationRight(50f);
            para6.setAlignment(Element.ALIGN_CENTER);
            document.add(para6);

            Paragraph para = new Paragraph();
            para.add(new Phrase(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT), new Font(Font.TIMES_ROMAN, 9)));
            para.setIndentationRight(50f);
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);

            PdfPTable table = new PdfPTable(2);
            PdfPCell pfcell;
            table.setTotalWidth(490);
            table.setLockedWidth(true);
            table.setWidths(new float[]{0.55f, 2});
            pfcell = new PdfPCell(new Phrase("Employee Name", new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": " + employeeDetails.getEmployeeName(), new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);

            pfcell = new PdfPCell(new Phrase("Employee Id", new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": " + employeeDetails.getEmployeeNumber(), new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);

            pfcell = new PdfPCell(new Phrase("Band", new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": " + employeeDetails.getBand(), new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);

            pfcell = new PdfPCell(new Phrase("Sub Band", new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": " + employeeDetails.getSubBand(), new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);

            pfcell = new PdfPCell(new Phrase("Designation", new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": " + employeeDetails.getDesignation(), new Font(Font.TIMES_ROMAN, 10)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            document.add(table);

            Paragraph para3 = new Paragraph();
            para3.add(new Phrase("\nDear " + employeeDetails.getEmployeeName() + ",\n\n", new Font(Font.TIMES_ROMAN, 10)));
            para3.add(new Phrase("This is with reference to your resignation letter " + dateFormat.format(rmActionData.getExitTriggerDate()) + "."
                    + "Your resignation has been accepted by the company, subject to your completion of necessary formalities including "
                    + "final settlement as per the company policy.On your returning of all the company assets if any and on completion "
                    + "of final settlement of accounts, you would be relieved from the services of the company, effective from " + dateFormat.format(rmActionData.getLastWorkingDate()) + ".", new Font(Font.TIMES_ROMAN, 10)));
            para3.setIndentationLeft(50f);
            para3.setIndentationRight(50f);
            para3.setLeading(11f);
            para3.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para3);

            Paragraph para4 = new Paragraph();
            para4.add(new Phrase("Relieving letter and service certificate will be forwarded to your address once the final "
                    + "settlement details are completed, including recoveries if any. Kindly write to "
                    + "exit.helpdesk@hindujatech.com for any clarifications.\n", new Font(Font.TIMES_ROMAN, 10)));
            para4.setSpacingBefore(10f);
            para4.setIndentationLeft(50f);
            para4.setIndentationRight(50f);
            para4.setLeading(11f);
            para4.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para4);

            Paragraph para5 = new Paragraph();
            para5.add(new Phrase("\nWe would like to bring to your attention certain provisions contained in the Employee "
                    + "Non-compete and Non-Disclosure Agreement (“Agreement”) dated " + dateFormat.format(employeeDetails.getEmpDoj()) + " entered between you and Hinduja Tech "
                    + "Limited (“HT”) at the time of your employment with us. \nPursuant to the said Agreement, you had "
                    + "undertaken that you shall abide by the following provision/restrictions\n\n"
                    + "\t1. Till the completion of one year post cessation of your employment with HT, you shall not (a) "
                    + "directly or indirectly, own, engage, participate, or be employed in any capacity related or similar to, "
                    + "or requiring knowledge of Confidential Information obtained from your employment with HT; (b) solicit or "
                    + "divert to any Competing Business any individual or entity which is a customer of HT or was a customer at any "
                    + "time during the preceding 12 months; or (c) induce, recruit or solicit any of HT’s employees to terminate their "
                    + "employment or enter into another employment agreement with a competitor to HT.\n\t2. You shall not disclose any "
                    + "Confidential Information without the prior written authorization of HT.\n\t3. You shall disclose in writing to HT, "
                    + "promptly and completely, any invention which you made during your employment with HT or in a period of one (1) "
                    + "year immediately following the end of your employment which relate to your prior work assignment at HT or to any "
                    + "Confidential Information of HT.\n\nThe capitalized words not defined herein shall have the meaning as set out in the "
                    + "Agreement.\n\nPlease note that as per the terms of the Agreement, in the event of breach of these conditions by you, "
                    + "HT shall be entitled to seek preliminary and permanent injunctive relief, without the necessity of proving actual "
                    + "damages, as well as an equitable accounting of all earnings, profits and other benefits arising from any violation of the "
                    + "above terms of the Agreement, which rights shall be in addition to any other rights or remedies to which HT may be "
                    + "entitled.\n\nWe wish to state that the terms of the Agreement will continue to be in force even after the cessation of your "
                    + "employment with HT for the period as stipulated in the Agreement, and any breach of the same will entitle HT to take "
                    + "necessary remedial action as it may deem fit, in order to protect its interests. Wish you success in your future endeavors", new Font(Font.TIMES_ROMAN, 10)));
            para5.setIndentationLeft(50f);
            para5.setIndentationRight(50f);
            para5.setLeading(11f);
            para5.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para5);

            Paragraph para7 = new Paragraph();
            para7.add(new Phrase("Yours Truly,", new Font(Font.TIMES_ROMAN, 9)));
            para7.add(new Phrase("\nFor Hinduja Tech Ltd.", new Font(Font.TIMES_ROMAN, 9)));
            para7.setSpacingBefore(10f);
            para7.setIndentationLeft(50f);
            para7.setLeading(11f);
            para7.setAlignment(Element.ALIGN_LEFT);
            document.add(para7);

            Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/hr_signature.jpg");
            signImage.setAbsolutePosition(45, signImage.absoluteY());
            document.add(signImage);
    //            
            Paragraph para8 = new Paragraph();
            para8.add(new Phrase("Jayawanthi Shankar Rao", new Font(Font.TIMES_ROMAN, 9)));
            para8.add(new Phrase("\nGeneral Manager - HR", new Font(Font.TIMES_ROMAN, 9)));
            para8.setIndentationLeft(50f);
            para8.setLeading(11f);
            para8.setAlignment(Element.ALIGN_LEFT);
            document.add(para8);
        }
        document.close();
        //
//      document.close();
        return fileName;
    }

    private void addResContent(Document document, HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String resEmpId = (String) request.getAttribute("resEmpId");
        ApprovalDTO rmFormData = new ApprovalDTO();
        rmFormData.setEmpId(resEmpId);
        EmployeeDTO employeeDetails = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getEmployeeDetails", rmFormData);
        rmFormData.setExitEmpId(employeeDetails.getExitEmpId());
        rmFormData.setCmpNoticePeriodInDays(CommonConfigurations.CMP_NOTICE_PERIOD_IN_DAYS);
        ApprovalDTO rmActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getRmActionData", rmFormData);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + employeeDetails.getEmployeeNumber() + "_RAL.pdf\"");
        String fileName = employeeDetails.getEmployeeNumber() + "_RAL.pdf";
        String hardPassword = "D 5BaIZQ@ CqAk+NQCW)7Dkgb@i&02ifu!2TMX*d 0TGK(j(Kq";
        byte[] hardPasswordByte = hardPassword.getBytes();
        PdfReader reader = new PdfReader("Resignation_Acceptance_Letter.pdf");
        PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(fileName));
        PdfContentByte content = pdfStamper.getOverContent(1);
        try {
            //  placeString(content, CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT), 403f, 740f, 10f, BaseFont.HELVETICA);
            placeString(content, CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT), 433f, 710f, 9f, BaseFont.HELVETICA);
            //Employee name
            //  placeString(content, employeeDetails.getEmployeeName(), 99f, 710f, 10f, BaseFont.HELVETICA);
            placeString(content, employeeDetails.getEmployeeName(), 118f, 719f, 9f, BaseFont.HELVETICA);
            //Employee Id
            //      placeString(content, employeeDetails.getEmployeeNumber(), 97f, 696f, 10f, BaseFont.HELVETICA);
            placeString(content, employeeDetails.getEmployeeNumber(), 118f, 706.5f, 9f, BaseFont.HELVETICA);
            //Employee Band
            //   placeString(content, employeeDetails.getBand(), 99f, 683f, 10f, BaseFont.HELVETICA);
            placeString(content, employeeDetails.getBand(), 119f, 693f, 9f, BaseFont.HELVETICA);
            //Employee Sub Band
            //  placeString(content, employeeDetails.getSubBand(), 99f, 670f, 10f, BaseFont.HELVETICA);
            placeString(content, employeeDetails.getSubBand(), 119f, 684f, 9f, BaseFont.HELVETICA);
            //Employee Designation
            placeString(content, employeeDetails.getDesignation(), 119f, 673f, 9f, BaseFont.HELVETICA);
            //Employee name
            placeString(content, employeeDetails.getEmployeeName() + ",", 71f, 650f, 9f, BaseFont.HELVETICA);
            //Employee Exit Trigger Date
            //  placeString(content, dateFormat.format(rmActionData.getExitTriggerDate()) + ".", 232.5f, 591, 9f, BaseFont.HELVETICA);
            placeString(content, dateFormat.format(rmActionData.getExitTriggerDate()) + ".", 234.5f, 627.5f, 9f, BaseFont.HELVETICA);
            //Employee Last Working Date
            placeString(content, dateFormat.format(rmActionData.getLastWorkingDate()) + ".", 239f, 593f, 9f, BaseFont.HELVETICA);
            // Employee Date of joining
            placeString(content, dateFormat.format(employeeDetails.getEmpDoj()), 227f, 524f, 9f, BaseFont.HELVETICA);
            pdfStamper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publishServLetter(ApprovalDTO hrFormData, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EmployeeDTO empFormData = new EmployeeDTO();
        System.out.println("::::In daoimpl:::" + hrFormData.getModuleId() + "-----" + hrFormData.getExitEmpId() + "----" + hrFormData.getResEmpId());
        empFormData.setModuleId(hrFormData.getModuleId());
        List hrFormNoValues = new ArrayList();
        String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
        triggerMail(hrFormNoValues, toMailApprovalModules, empFormData, "serviceLetter", "serviceLetter", "serviceLetter", CommonConfigurations.hrModuleName, hrFormData.getResEmpId(), request, response);
    }

    public String serLetter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String resEmpId = (String) request.getAttribute("resEmpId");
        System.out.println("resEmpId" + resEmpId);
        ApprovalDTO rmFormData = new ApprovalDTO();
        rmFormData.setEmpId(resEmpId);
        EmployeeDTO employeeDetails = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getEmployeeDetails", rmFormData);
        String relieved = "relieved";
        String relieving = "Relieving";
        if(employeeDetails.getEmpLeavingReason().equals("595")){
            relieved = "retired";
            relieving = "Retirement";
        }
        String fileName = employeeDetails.getEmployeeNumber() + "_SRL.pdf";
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        PdfReader reader = new PdfReader("Service_certificate_cum_releiving_letter.pdf");
        document.add(new Paragraph(""));
        DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
        // adding here
        rmFormData.setExitEmpId(employeeDetails.getExitEmpId());
        rmFormData.setCmpNoticePeriodInDays(CommonConfigurations.CMP_NOTICE_PERIOD_IN_DAYS);
        ApprovalDTO rmActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getRmActionData", rmFormData);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + employeeDetails.getEmployeeNumber() + "_SRL.pdf\"");
        String filePath = "D:\\quailtyFileUpload\\";
        String file_name = employeeDetails.getEmployeeNumber() + "_SRL.pdf";
        File file = new File(filePath + file_name);
        if (file.delete()) {
            System.out.println("file deleted " + filePath + file_name);
        } else {
        }
        PdfWriter.getInstance(document, new FileOutputStream(new File(filePath + "//" + file_name)));
        document.open();

        Image headerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/ht_logo.png");
        headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
        document.add(headerImage);

        Image footerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/bonafied_footer.jpg");
        footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
        footerImage.setAbsolutePosition(0, 0);
        document.add(footerImage);

        Paragraph para6 = new Paragraph();
        para6.add(new Phrase("Service Certificate and Relieving Letter", new Font(Font.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE)));
        para6.setIndentationLeft(50f);
        para6.setIndentationRight(50f);
        para6.setAlignment(Element.ALIGN_CENTER);
        document.add(para6);

        Paragraph para = new Paragraph();
        para.add(new Phrase(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT), new Font(Font.TIMES_ROMAN, 12)));
        para.setIndentationRight(50f);
        para.setAlignment(Element.ALIGN_RIGHT);
        document.add(para);

        Paragraph para3 = new Paragraph();
        para3.add(new Phrase("\nDear " + employeeDetails.getEmployeeName() + ",\n\n", new Font(Font.TIMES_ROMAN, 13)));
        para3.add(new Phrase("Consequent to the completion of Full and Final settlement process, we would like to "
                + "inform you that you have been "+relieved+" from the services of Hinduja Tech Limited on "
                + "close of business hours on " + dateFormat1.format(rmActionData.getLastWorkingDate()) + ".\n\n"
                + "Details of your service with the company is as furnished below\n\n ", new Font(Font.TIMES_ROMAN, 13)));
        para3.setIndentationLeft(50f);
        para3.setIndentationRight(50f);
        para3.setLeading(12f);
        para3.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(para3);

        PdfPTable table = new PdfPTable(2);
        PdfPCell pfcell;
        table.setTotalWidth(490);
        table.setLockedWidth(true);
        table.setWidths(new float[]{0.75f, 2});
        pfcell = new PdfPCell(new Phrase("Employee Name ", new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);
        pfcell = new PdfPCell(new Phrase(": " + employeeDetails.getEmployeeName(), new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);

        pfcell = new PdfPCell(new Phrase("Employee Id", new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);
        pfcell = new PdfPCell(new Phrase(": " + employeeDetails.getEmployeeNumber(), new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);

        pfcell = new PdfPCell(new Phrase("Band", new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);
        pfcell = new PdfPCell(new Phrase(": " + employeeDetails.getBand(), new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);

        pfcell = new PdfPCell(new Phrase("Sub Band", new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);
        pfcell = new PdfPCell(new Phrase(": " + employeeDetails.getSubBand(), new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);

        pfcell = new PdfPCell(new Phrase("Date of Joining", new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);
        pfcell = new PdfPCell(new Phrase(": " + dateFormat1.format(employeeDetails.getEmpDoj()), new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);

        pfcell = new PdfPCell(new Phrase("Date of "+relieving, new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);
        pfcell = new PdfPCell(new Phrase(": " + dateFormat1.format(rmActionData.getLastWorkingDate()), new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);

        pfcell = new PdfPCell(new Phrase("Designation at "+relieving, new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);
        pfcell = new PdfPCell(new Phrase(": " + employeeDetails.getDesignation(), new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);

        pfcell = new PdfPCell(new Phrase("Character & Conduct", new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);
        pfcell = new PdfPCell(new Phrase(": Good", new Font(Font.TIMES_ROMAN, 12)));
        pfcell.setBorder(0);
        table.addCell(pfcell);
        document.add(table);

        Paragraph para4 = new Paragraph();
        para4.add(new Phrase("Wish you the very best in all your future endeavours.\n", new Font(Font.TIMES_ROMAN, 12)));
        para4.setSpacingBefore(10f);
        para4.setIndentationLeft(50f);
        para4.setIndentationRight(50f);
        para4.setLeading(12f);
        para4.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(para4);

        Paragraph para7 = new Paragraph();
        para7.add(new Phrase("Yours Truly,", new Font(Font.TIMES_ROMAN, 12)));
        para7.add(new Phrase("\nFor Hinduja Tech Ltd.", new Font(Font.TIMES_ROMAN, 12)));
        para7.setSpacingBefore(10f);
        para7.setIndentationLeft(50f);
        para7.setLeading(12f);
        para7.setAlignment(Element.ALIGN_LEFT);
        document.add(para7);

        Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/hr_signature.jpg");
        signImage.setAbsolutePosition(45, signImage.absoluteY());
        document.add(signImage);
//            
        Paragraph para8 = new Paragraph();
        para8.add(new Phrase("Jayawanthi Shankar Rao", new Font(Font.TIMES_ROMAN, 12)));
        para8.add(new Phrase("\nGeneral Manager - HR", new Font(Font.TIMES_ROMAN, 12)));
        para8.setIndentationLeft(50f);
        para8.setLeading(12f);
        para8.setAlignment(Element.ALIGN_LEFT);
        document.add(para8);

        document.close();
        //bos.flush();
        //bos.close();
        //adding ends here
        //addContent(document, request, response);
        //      document.close();
        return fileName;

    }

//    private void addContent(Document document, HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
//        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//        DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
//        String resEmpId = (String) request.getAttribute("resEmpId");
//        ApprovalDTO rmFormData = new ApprovalDTO();
//        rmFormData.setEmpId(resEmpId);
//        EmployeeDTO employeeDetails = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getEmployeeDetails", rmFormData);
//        rmFormData.setExitEmpId(employeeDetails.getExitEmpId());
//        rmFormData.setCmpNoticePeriodInDays(CommonConfigurations.CMP_NOTICE_PERIOD_IN_DAYS);
//        ApprovalDTO rmActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getRmActionData", rmFormData);
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + employeeDetails.getEmployeeNumber() + "_SRL.pdf\"");
//        String fileName = employeeDetails.getEmployeeNumber() + "_SRL.pdf";
//        String hardPassword = "D 5BaIZQ@ CqAk+NQCW)7Dkgb@i&02ifu!2TMX*d 0TGK(j(Kq";
//        byte[] hardPasswordByte = hardPassword.getBytes();
//        PdfReader reader = new PdfReader("Service_certificate_cum_releiving_letter.pdf");
//        PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(fileName));
//        PdfContentByte content = pdfStamper.getOverContent(1);
//        try {
//            placeString(content, CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT), 455f, 714f, 10f, BaseFont.HELVETICA_BOLD);
//            //Employee Last Working date
//            placeString(content, dateFormat1.format(rmActionData.getLastWorkingDate()) + ".", 172f, 564.5f, 9f, BaseFont.HELVETICA_BOLD);
//            //Employee name
//            placeString(content, employeeDetails.getEmployeeName(), 190f, 482f, 10f, BaseFont.HELVETICA_BOLD);
//            //Employee Id
//            placeString(content, employeeDetails.getEmployeeNumber(), 190f, 464.5f, 10f, BaseFont.HELVETICA_BOLD);
//            //Employee Band
//            placeString(content, employeeDetails.getBand(), 190f, 448.5f, 10f, BaseFont.HELVETICA_BOLD);
//            //Employee Sub Band
//            placeString(content, employeeDetails.getSubBand(), 190f, 432f, 10f, BaseFont.HELVETICA_BOLD);
//            //Employee Designation
//            placeString(content, employeeDetails.getDesignation(), 190f, 383f, 10f, BaseFont.HELVETICA_BOLD);
//            //Employee name on top of pdf 
//            placeString(content, employeeDetails.getEmployeeName() + ",", 70f, 631f, 10f, BaseFont.HELVETICA_BOLD);
//            //Resignation submitted date
////            placeString(content, CommonFunctions.convertDate(employeeDetails.getResignedDate().toString(), CommonConfigurations.PDF_DATE_SELECT, CommonConfigurations.MYSQL_DATE_SELECT), 306f, 504f, 9f);
//            //placeString(content, dateFormat.format(rmActionData.getExitTriggerDate()), 195f, 431f, 9f);
//            placeString(content, dateFormat1.format(employeeDetails.getEmpDoj()), 190f, 415.5f, 10f, BaseFont.HELVETICA_BOLD);
////            placeString(content,CommonFunctions.convertDate(employeeDetails.getResignedDate(),CommonConfigurations.PDF_DATE_SELECT,CommonConfigurations.MYSQL_DATE_SELECT),304f,504f,9f);
////            placeString(content,CommonFunctions.convertDate(employeeDetails.getResignedDate(),CommonConfigurations.PDF_DATE_SELECT,CommonConfigurations.MYSQL_DATE_SELECT),304f,504f,9f);
//            //Releived date
////            placeString(content, CommonFunctions.convertDate(rmActionData.getLastWorkingDate().toString(), CommonConfigurations.PDF_DATE_SELECT, CommonConfigurations.MYSQL_DATE_SELECT + "."), 162f, 437f, 9f);
//            //LWD on top of pdf
//            placeString(content, dateFormat1.format(rmActionData.getLastWorkingDate()), 190f, 398f, 10f, BaseFont.HELVETICA_BOLD);
////            placeString(content, rmActionData.getLastWorkingDate() + ".", 162f, 437f, 9f);
////            placeString(content,CommonFunctions.convertDate(rmActionData.getLastWorkingDate(),CommonConfigurations.PDF_DATE_SELECT,CommonConfigurations.MYSQL_DATE_SELECT),162f,438f,9f);
////            placeString(content,CommonFunctions.convertDate(rmActionData.getLastWorkingDate(),CommonConfigurations.PDF_DATE_SELECT,CommonConfigurations.MYSQL_DATE_SELECT),162f,438f,9f);
//            pdfStamper.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    private void addContent(Document document, HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
        String resEmpId = (String) request.getAttribute("resEmpId");
        ApprovalDTO rmFormData = new ApprovalDTO();
        rmFormData.setEmpId(resEmpId);
        EmployeeDTO employeeDetails = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getEmployeeDetails", rmFormData);
        rmFormData.setExitEmpId(employeeDetails.getExitEmpId());
        rmFormData.setCmpNoticePeriodInDays(CommonConfigurations.CMP_NOTICE_PERIOD_IN_DAYS);
        ApprovalDTO rmActionData = (ApprovalDTO) getSqlMapClientTemplate().queryForObject("ApprovalDetails.getRmActionData", rmFormData);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + employeeDetails.getEmployeeNumber() + "_SRL.pdf\"");
        String fileName = employeeDetails.getEmployeeNumber() + "_SRL.pdf";
        String hardPassword = "D 5BaIZQ@ CqAk+NQCW)7Dkgb@i&02ifu!2TMX*d 0TGK(j(Kq";
        byte[] hardPasswordByte = hardPassword.getBytes();
        PdfReader reader = new PdfReader("Service_certificate_cum_releiving_letter.pdf");
        PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(fileName));
        PdfContentByte content = pdfStamper.getOverContent(1);
        try {
            //Date Value
            placeString(content, CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT), 441f, 689f, 10f, BaseFont.HELVETICA_BOLD);
            //Employee Last Working date
            placeString(content, dateFormat1.format(rmActionData.getLastWorkingDate()) + ".", 178f, 563.5f, 9f, BaseFont.HELVETICA_BOLD);
            //Employee name +7
            placeString(content, employeeDetails.getEmployeeName(), 191f, 507f, 10f, BaseFont.HELVETICA_BOLD);
            //Employee Id
            placeString(content, employeeDetails.getEmployeeNumber(), 191f, 492.5f, 10f, BaseFont.HELVETICA_BOLD);
            //Employee Band
            placeString(content, employeeDetails.getBand(), 191f, 478f, 10f, BaseFont.HELVETICA_BOLD);
            //Employee Sub Band
            placeString(content, employeeDetails.getSubBand(), 191f, 464.5f, 10f, BaseFont.HELVETICA_BOLD);
            //Employee Designation
            placeString(content, employeeDetails.getDesignation(), 191f, 421.5f, 10f, BaseFont.HELVETICA_BOLD);

            //Employee name on top of pdf 
            placeString(content, employeeDetails.getEmployeeName() + ",", 71f, 620f, 10f, BaseFont.HELVETICA_BOLD);
            //Resignation submitted date
//            placeString(content, CommonFunctions.convertDate(employeeDetails.getResignedDate().toString(), CommonConfigurations.PDF_DATE_SELECT, CommonConfigurations.MYSQL_DATE_SELECT), 306f, 504f, 9f);

            placeString(content, dateFormat1.format(employeeDetails.getEmpDoj()), 191f, 450.5f, 10f, BaseFont.HELVETICA_BOLD);

            //Releived date

            //LWD on top of pdf
            placeString(content, dateFormat1.format(rmActionData.getLastWorkingDate()), 191f, 435.5f, 10f, BaseFont.HELVETICA_BOLD);
            pdfStamper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PdfContentByte placeString(PdfContentByte content, String txtToPlace, float x, float y, float fontSize, String font) {

        try {
            BaseFont bf = BaseFont.createFont(font, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
            content.beginText();
            content.setTextMatrix(30, 30);
            content.setFontAndSize(bf, fontSize);
            content.moveText(x, y);
            content.showText(txtToPlace);
            content.endText();
        } catch (DocumentException ex) {
            Logger.getLogger(ApprovalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApprovalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
}
