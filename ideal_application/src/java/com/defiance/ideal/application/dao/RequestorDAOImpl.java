/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.util.CommonConfigurations;
import com.defiance.ideal.application.dto.RequestorDTO;
import com.defiance.ideal.application.util.CommonMethods;
import com.defiance.ideal.application.util.MailMessages;
import com.defiance.ideal.application.util.SendMail;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.orm.toplink.SessionFactory;

/**
 *
 * @author 14355
 */
public class RequestorDAOImpl extends SqlMapClientDaoSupport {

    private SessionFactory sessionFactory;

    public List<RequestorDTO> getdashBoardList(String empId, String moduleId, String selectedValue, String list) {
        RequestorDTO reqDto = new RequestorDTO();
        List<RequestorDTO> getDashBoardList = null;
        try {
            reqDto.setEmpId(empId);
            reqDto.setModuleId(moduleId);
            reqDto.setSelectedValue(selectedValue);
            reqDto.setList(list);
            getDashBoardList = getSqlMapClientTemplate().queryForList("Requestor.dashBoardList", reqDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getDashBoardList;
    }

    public void insertNewRequest(RequestorDTO requestformData) {
        try {
            int reqYear = CommonMethods.getCurrentYear();
            Calendar cal = Calendar.getInstance();
            String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String month = null;
//            if (cal.get(Calendar.MONTH) == 0) {
//                month = months[(cal.get(Calendar.MONTH))];
//            } else {
            month = months[(cal.get(Calendar.MONTH))];
//            }
            String refDate = reqYear + month;
            requestformData.setReferenceId(refDate);
            getSqlMapClientTemplate().insert("Requestor.insertRequestDetails", requestformData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RequestorDTO getEmpDetails(String empId) {
        RequestorDTO defaultValues = new RequestorDTO();
        RequestorDTO empDetails = null;
        List<RequestorDTO> feedbackValues = null;
        List<RequestorDTO> improvementValues = null;
        List<RequestorDTO> processValues = null;
        List<RequestorDTO> focusValues = null;
        List<RequestorDTO> evalStatusValues = null;
        List<RequestorDTO> reqStatusValues = null;
        try {
            String feedbackId = CommonConfigurations.feedbackType;
            String improvementProcessId = CommonConfigurations.improvementProcessType;
            String processId = CommonConfigurations.processAreaType;
            String focusId = CommonConfigurations.focusAreaType;
            String evalStatusId = CommonConfigurations.evaluationStatus;
            String reqStatusId = CommonConfigurations.requestStatus;
            empDetails = (RequestorDTO) getSqlMapClientTemplate().queryForObject("Requestor.empDetails", empId);
            feedbackValues = getSqlMapClientTemplate().queryForList("Requestor.configValues", feedbackId);
            improvementValues = getSqlMapClientTemplate().queryForList("Requestor.configValues", improvementProcessId);
            processValues = getSqlMapClientTemplate().queryForList("Requestor.configValues", processId);
            focusValues = getSqlMapClientTemplate().queryForList("Requestor.configValues", focusId);
            evalStatusValues = getSqlMapClientTemplate().queryForList("Requestor.configValues", evalStatusId);
            reqStatusValues = getSqlMapClientTemplate().queryForList("Requestor.configValues", reqStatusId);
            defaultValues.setEmpInfo(empDetails);
            defaultValues.setFeedbackValues(feedbackValues);
            defaultValues.setProcessValues(processValues);
            defaultValues.setImprovementvalues(improvementValues);
            defaultValues.setFocusValues(focusValues);
            defaultValues.setEvalStatusValues(evalStatusValues);
            defaultValues.setReqStatusValues(reqStatusValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValues;
    }

    public List<RequestorDTO> getFocusArea(String empId) {
        List<RequestorDTO> focusValues = null;
        try {
            System.out.println("Focus DAO IMPL--");
            String focusId = empId;
            focusValues = getSqlMapClientTemplate().queryForList("Requestor.configValues", focusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return focusValues;
    }

    public void insertFile(RequestorDTO fileData) {
        try {
            getSqlMapClientTemplate().insert("Requestor.insertFile", fileData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RequestorDTO getRequestDetails(String refId) {
        RequestorDTO individualRequestDetails = null;
        try {
            individualRequestDetails = (RequestorDTO) getSqlMapClientTemplate().queryForObject("Requestor.getRequestDetails", refId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return individualRequestDetails;
    }

    public int getLastInsert() {
        Integer lastInsertId = null;
        try {
            lastInsertId = (Integer) getSqlMapClientTemplate().queryForObject("Requestor.getLastInsert");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastInsertId;
    }

    public RequestorDTO getFiledownload(String refId) {
        RequestorDTO fileDetails = null;
        try {
            fileDetails = (RequestorDTO) getSqlMapClientTemplate().queryForObject("Requestor.getFileName", refId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileDetails;
    }

    public void updateRequest(RequestorDTO formData) {
        try {
            getSqlMapClientTemplate().update("Requestor.updateQualityData", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<RequestorDTO> getEmployeeList(String empVal) {
        List<RequestorDTO> employeeList = null;
        try {
            String key = "%" + empVal + "%";
            System.out.println("in daoimpl::::" + key);
            employeeList = (List<RequestorDTO>) getSqlMapClientTemplate().queryForList("Requestor.getEmployeeList", key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void updateReferenceId(String refId, int id) {
        try {
            RequestorDTO reqDto = new RequestorDTO();
            reqDto.setReferenceId(refId);
            reqDto.setRefId(id);
            getSqlMapClientTemplate().update("Requestor.updateReferenceId", reqDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RequestorDTO qualityInformation(String empId) {
        RequestorDTO qualityInformation = null;
        try {
            qualityInformation = (RequestorDTO) getSqlMapClientTemplate().queryForObject("Requestor.qualityAdminDetails", empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qualityInformation;
    }

    public void triggerMailAction(RequestorDTO formData, String updatedRefId) throws Exception {
        try {
            
            String[] toMailApprovalMails = CommonConfigurations.qualityApprovalMailList;
            
            //Fix Ticket ID RefNo ITS000602
            /*String toMailApprovalMailsIds = "";
            String[] toMailApprovalWorkMailIds = null;            
            if (toMailApprovalMails.length > 0) {
                for (int i = 0; i < toMailApprovalMails.length; i++) {
                    RequestorDTO getEmployeeDetails = (RequestorDTO) getSqlMapClientTemplate().queryForObject("Requestor.getApproverEmailAddress", toMailApprovalMails[i]);
                    toMailApprovalMailsIds = toMailApprovalMailsIds + "," + getEmployeeDetails.getEmpWorkMail();
                }
                toMailApprovalWorkMailIds = toMailApprovalMailsIds.split(", ");
            }
             this.triggerMail(toMailApprovalWorkMailIds, formData, "RequestorSubmit", "Request", formData.getEmployeeId());
             this.triggerMail(toMailApprovalWorkMailIds, formData, "QualityAdminSubmit", "Response", qualId);
             */
            System.out.println("formData.getSubmitButton():"+formData.getSubmitButton());
            if (formData.getSubmitButton().intValue() == 1) {
                System.out.println("Inside Submit Action");
                formData.setReferenceId(updatedRefId);               
                this.triggerMail(toMailApprovalMails, formData, "RequestorSubmit", "Request", formData.getEmployeeId());
            } else if (formData.getSubmitButton().intValue() == 2) {
                System.out.println("Inside Quality Submit");
                String qualId = formData.getQualityId().toString();  
                
                System.out.println("qualId:"+qualId);    
                System.out.println("requestor EmployeeId:"+formData.getRequestorEmployeeID());
                String requestorEmployeeID=formData.getRequestorEmployeeID();
                this.triggerMail(toMailApprovalMails, formData, "QualityAdminSubmit", "Response", requestorEmployeeID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void triggerMail(String[] toMailApprovalModules, RequestorDTO requestorData, String mailSubReason, String mailProcessReason, String resEmpId) {
        System.out.println("INside Trigger Mail");
        String parentId = CommonConfigurations.MAIL_DETAILS_PARENTID;
        MailMessages mailMsg = new MailMessages();
        String mailTo = "";
        String mailCc = "";
        String mailSubject = "";
        String mailMessage = "";
        List mailToApprovalIds = new ArrayList();
        List mailCcApprovalIds = new ArrayList();
        String feedBackType = null;
        String otherData = null;
        SendMail mailObj = null;
        List<RequestorDTO> connectionRes = new ArrayList();
        try {
            mailObj = new SendMail();
            connectionRes = getSqlMapClientTemplate().queryForList("Requestor.configValues", parentId);
            String con_username = connectionRes.get(0).getConfigValue();
            String con_password = connectionRes.get(1).getConfigValue();
            String con_host = connectionRes.get(2).getConfigValue();
            String con_port = connectionRes.get(3).getConfigValue();

//            mailObj = new SendMail(con_username, con_password, con_host, con_port);
            RequestorDTO getEmployeeDetails = (RequestorDTO) getSqlMapClientTemplate().queryForObject("Requestor.getEmailAddress", resEmpId);
            if (mailProcessReason.equals("Request")) {
                System.out.println("Empl WOrkMail Addrtess====" + getEmployeeDetails.getEmpWorkMail());
                mailToApprovalIds.addAll(Arrays.asList(toMailApprovalModules));
                for (Object o : mailToApprovalIds) {
                    mailTo += o.toString();
                    mailTo += ",";
                }
                mailCc = getEmployeeDetails.getEmpWorkMail();
            } else if (mailProcessReason.equals("Response")) {
                System.out.println("Empl Quality Admin====" + getEmployeeDetails.getEmpWorkMail());
                mailTo = getEmployeeDetails.getEmpWorkMail();
                mailCcApprovalIds.addAll(Arrays.asList(toMailApprovalModules));
                for (Object o : mailCcApprovalIds) {
                    mailCc += o.toString();
                    mailCc += ",";
                }
            }
            if (requestorData.getFeedbackType() != null) {
                if (requestorData.getFeedbackType().equalsIgnoreCase("c")) {
                    feedBackType = "QMS Complaint";
                } else if (requestorData.getFeedbackType().equalsIgnoreCase("p")) {
                    feedBackType = "Process improvement feedback";
                } else if (requestorData.getFeedbackType().equalsIgnoreCase("q")) {
                    feedBackType = "QMS Query";
                } else {
                    feedBackType = " ";
                }
            }
            if (feedBackType != "Improvement Process Type") {
                otherData = feedBackType + "#~~#";
            } else {
                otherData = "Process improvement feedback" + "#~~#";
            }
            mailSubject = mailMsg.getSubject(mailSubReason, otherData);

            if (mailProcessReason.equals("Request")) {

                mailMessage = mailMsg.getOthersMailMessage(mailSubReason, requestorData.getReferenceId() + "#~~#" + feedBackType + "#~~#" + requestorData.getDescription() + "#~~#" + requestorData.getEmpId());
            } else if (mailProcessReason.equals("Response")) {
                System.out.println("Response  FeedBackType====" + feedBackType);
                String evalStatus = null;
                if (requestorData.getReqStatus() != null) {
                    if (requestorData.getEvalStatus().equalsIgnoreCase("a")) {
                        evalStatus = "Accepted";
                    } else if (requestorData.getEvalStatus().equalsIgnoreCase("r")) {
                        evalStatus = "Rejected";
                    } else if (requestorData.getEvalStatus().equalsIgnoreCase("d")) {
                        evalStatus = "Deffered";
                    } else {
                        evalStatus = " ";
                    }
                } else if (requestorData.getEvalStatus() == null) {
                    evalStatus = "---";
                }
                mailMessage = mailMsg.getOthersMailMessage(mailSubReason, requestorData.getReferenceId() + "#~~#" + feedBackType + "#~~#" + requestorData.getDescription() + "#~~#" + requestorData.getEmployeeName() + "#~~#" + requestorData.getResposerName() + "#~~#" + evalStatus + "#~~#" + requestorData.getMailName() + "#~~#" + requestorData.getMailSubName() + "#~~#" + requestorData.getQualtyRemarks());
            }
            mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
