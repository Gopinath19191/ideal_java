/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.exitMgmt.employee.dao;

import com.defiance.ideal.exitMgmt.approval.dto.ApprovalDTO;
import com.defiance.ideal.exitMgmt.employee.dto.EmployeeDTO;
import com.defiance.ideal.exitMgmt.login.dto.LoginDTO;
import com.defiance.ideal.exitMgmt.utils.CommonConfigurations;
import com.defiance.ideal.exitMgmt.utils.MailMessages;
import com.defiance.ideal.exitMgmt.utils.SendMail;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14583
 */
public class ExitEmployeeDAOImpl extends SqlMapClientDaoSupport implements ExitEmployeeDAO {

    public List<LoginDTO> getEmployeeList(LoginDTO formData) {
        List<LoginDTO> employeeList = null;
        try {
            employeeList = getSqlMapClientTemplate().queryForList("ExitEmployee.getEmployeeLists", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public EmployeeDTO getEmployeeDetails(String employeeId) {
        EmployeeDTO employeeDetails = null;
        ApprovalDTO rmFormData = new ApprovalDTO();
        rmFormData.setCmpNoticePeriodInDays(CommonConfigurations.CMP_NOTICE_PERIOD_IN_DAYS);
        rmFormData.setEmpId(employeeId);
        try {
            employeeDetails = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getEmployeeDetails", rmFormData);
            employeeDetails.setEmpId(employeeId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeDetails;
    }

    public String getEmployeeReporteesCount(String employeeId) {
        String reportees_count = null;
        try {
            reportees_count = (String) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getReporteesCount", employeeId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportees_count;
    }

    public List<String> getCompanyHolidays(EmployeeDTO employeeDetails) {
        List<String> cmpHoliday = null;
        try {
            cmpHoliday = (List) getSqlMapClientTemplate().queryForList("EmployeeDetails.getEmpHolidays", employeeDetails);
            //System.out.println("employeeDetails.getCmpHolidays====>"+employeeDetails.getCmpHoliday());
            //System.out.println("ExitEmployeeId **************"+employeeDetails.getExitEmpId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmpHoliday;
    }

    public EmployeeDTO getEmployeeAddress(EmployeeDTO formData) {
        EmployeeDTO empAddressDetails = null;
        try {
            empAddressDetails = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getEmployeeAddress", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empAddressDetails;
    }

    public String getEmpName(String employeeId) {
        String empName = null;
        try {
            empName = (String) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getEmpName", employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empName;
    }

    public void insertExitEmpDetails(EmployeeDTO employeeFormData) {
        try {
            employeeFormData.setDeleted(CommonConfigurations.RESIGN_UNDELETE);
//            System.out.println(":::"+employeeFormData.getResignedDate()+"----"+employeeFormData.getButtonName());
            employeeFormData.setResignedDate(new Date());
            if (employeeFormData.getButtonName().equals("Save")) {
                employeeFormData.setSubmitStatus(Integer.toString(CommonConfigurations.EMP_SAVE_STATUS));
            } else if (employeeFormData.getButtonName().equals("Submit")) {
//                employeeFormData.setSubmitDate(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_INSERT));
                employeeFormData.setSubmitStatus(Integer.toString(CommonConfigurations.EMP_SUBMIT_STATUS));
            }// To check the new record or existing
            getSqlMapClientTemplate().update("EmployeeDetails.updateEmpRecords", employeeFormData);
            if (employeeFormData.getExitEmpId() == null || employeeFormData.getExitEmpId().equals("")) {
                getSqlMapClientTemplate().insert("EmployeeDetails.insertExitEmpDetails", employeeFormData);
            } else {
                getSqlMapClientTemplate().update("EmployeeDetails.updateExitEmpDetails", employeeFormData);
            }// Condtion to check the mail part
            if (employeeFormData.getButtonName().equals("Submit")) {
                // Here Mail Code has to come
                List rmModuleList = new ArrayList();
                String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
                this.triggerMail(rmModuleList, toMailApprovalModules, employeeFormData, "empToRM", "empToRM", "empToRM", CommonConfigurations.rmModuleName, employeeFormData.getEmpId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EmployeeDTO> getEmpAddressDetails(String empId) {
        List<EmployeeDTO> empAddress = null;
        try {
            empAddress = (List<EmployeeDTO>) getSqlMapClientTemplate().queryForList("EmployeeDetails.getEmpAddressDetails", empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empAddress;
    }

    public List<EmployeeDTO> getCountryList() {
        List<EmployeeDTO> countryList = null;
        try {
            countryList = (List<EmployeeDTO>) getSqlMapClientTemplate().queryForList("EmployeeDetails.getCountryList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }

    public List<EmployeeDTO> getRegnEmpList(String employeeId) {
        List<EmployeeDTO> employeeDetails = null;
        try {
            employeeDetails = (List<EmployeeDTO>) getSqlMapClientTemplate().queryForList("EmployeeDetails.getRegnEmpList", employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeDetails;
    }

    public void triggerMail(List formNoValues, String[] toMailApprovalModules, EmployeeDTO empFormData,
            String mailSubReason, String mailMessageWithDue, String mailMessageWithOutDue, String mailSubModule, String resEmpId) throws IOException {
        Properties configFile = new Properties();
        configFile.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));
        String addtionalMailIds = null;
        MailMessages mailMsg = new MailMessages();
        String mailTo = null;
        String mailCc = null;
        String mailBcc = null;
        String mailSubject = null;
        String mailMessage = null;
        List mailToApprovalIds = new ArrayList();
        List mailCcApprovalIds = new ArrayList();
        try {
            SendMail mailObj = new SendMail();
            EmployeeDTO getEmailAddress = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getEmailAddress", resEmpId);
            EmployeeDTO getAddtionalMailIds = null;
            EmployeeDTO getPmMailIds = null;
            EmployeeDTO getBccMailIds = null;


            if (resEmpId != null) {
                empFormData.setResEmpId(resEmpId);
                empFormData.setCurrentDate(new Date());
                getPmMailIds = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getPmMailIds", empFormData);
            }

            empFormData.setEmpStructure(getEmailAddress.getEmpStructure());


            System.out.println("empFormData.getEmpStructure()  " + empFormData.getEmpStructure());


            EmployeeDTO getGUAccessEmail = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getGUAccessEmail", empFormData);
            EmployeeDTO getRMGEmail = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getRMGEmail", empFormData);

            // for resignation submission
            EmployeeDTO getResignationSubmissionEmail = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getResignationSubmissionEmail", empFormData);
            EmployeeDTO getBUHEmail = (EmployeeDTO) getSqlMapClientTemplate().queryForObject("EmployeeDetails.getBUHEmail", empFormData);
            if (mailSubModule.equals(CommonConfigurations.exitSurveyModuleName)) {


                mailTo = getEmailAddress.getRmWorkMail();
                mailCc = getEmailAddress.getEmpWorkMail() + "," + getResignationSubmissionEmail.getDlmail() + "," + getBUHEmail.getBuhmail();

            } else if (mailSubModule.equals(CommonConfigurations.rmModuleName)) {
                System.out.println("inside employee submit res");
                System.out.println("1.getEmailAddress  for RM mail" + getEmailAddress.getRmWorkMail());
                System.out.println("2. getEmailAddress employee mail" + getEmailAddress.getEmpWorkMail());
                System.out.println("3. getResignationSubmissionEmail " + getResignationSubmissionEmail.getDlmail());
                System.out.println("4. mailTo getBUHEmail " + getBUHEmail.getBuhmail());

                mailTo = getEmailAddress.getRmWorkMail();
                if ("2".equals(empFormData.getEmpStructure()) || "5".equals(empFormData.getEmpStructure())) {
                    mailCc = getEmailAddress.getEmpWorkMail() + "," + getResignationSubmissionEmail.getDlmail() + "," + getBUHEmail.getBuhmail();
                } else {
                    mailCc = getEmailAddress.getEmpWorkMail() + "," + getResignationSubmissionEmail.getDlmail();
                }
                if(getPmMailIds.getPmWorkEmail()!=null){
                    mailCc = mailCc + ","+ getPmMailIds.getPmWorkEmail();
                }
            } else {
                System.out.println("test else last ");
                mailTo = getEmailAddress.getEmpWorkMail();
                mailCc = getRMGEmail.rmgWorkEmail + "," + getGUAccessEmail.approvalWorkEmail;
            }
            mailSubject = mailMsg.getSubject(mailSubReason, mailSubModule);
            if (formNoValues.size() != 0) {
                mailMessage = mailMsg.getOthersMailMessage(mailMessageWithDue, getEmailAddress.getEmployeeName() + "#~~#" + mailSubModule + "#~~#" + getEmailAddress.getRmName(), formNoValues);
            } else {
                System.out.println("Check :::" + mailMessageWithOutDue);
                mailMessage = mailMsg.getOthersMailMessage(mailMessageWithOutDue, getEmailAddress.getEmployeeName() + "#~~#" + mailSubModule + "#~~#" + getEmailAddress.getRmName(), formNoValues);
            }
            System.out.println("maill c cc" + mailCc);
            mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCc, mailBcc, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
