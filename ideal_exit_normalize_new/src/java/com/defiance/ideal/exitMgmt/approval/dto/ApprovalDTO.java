/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.approval.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 14583
 */
public class ApprovalDTO {

    // Variable used for RM Approval Module
    private String rmApprovalId;
    private String exitEmpId;
    private String rmId;
    private Date lastWorkingDate;
    private String daysServed;
    private String balNoticePeriod;
    private String noticeWaiverValue;
    private String buttonName;
    private String resEmpId;
    private String name;
    private String answerKey;
    private String answerValue;
    private String questionId;
    private String answerType;
    private String questionDescription;
    private String parentId;
    private String empAnswer;
    private String reasonRejection;
    private String submitStatus;
    private String deleted;
    private Integer cmpNoticePeriodInDays;

    //Variables needed for RM Clearance Module
    private String rmClrId;
    private String compDoc;
    private String projDoc;
    private String custApproval;
    private String empQpd;
    private String rmOthers;
    private String compDocRemarks;
    private String projDocRemarks;
    private String custApprovalRemarks;
    private String empQpdRemarks;
    private String rmOthersRemarks;
    private String rmClrOverAllComments;
    private Date rmApprovedDate;
    private Date rmClrDate;
    private Date adApprovedDate;
    private Date finApprovedDate;
    private Date nsApprovedDate;
    private Date hrApprovedDate;

    //Common Variables needed
    private String empId;
    private String moduleId;

   
    
    // Variable used for Finance Approval Module
    private String Id;
    private String finApprovalId;
    private String finApproverId;
    private String lastPaidSalary;
    private String travelAdvance;
    private String salaryAdvance;
    private String relocation;
    private String joiningBonus;
    private String noticeReimburse;
    private String professionalFee;
    private String loans;
    private String officialExpenses;
    private String other;
    private String lastPaidSalaryRemarks;
    private String travelAdvanceRemarks;
    private String salaryAdvanceRemarks;
    private String relocationRemarks;
    private String joiningBonusRemarks;
    private String noticeReimburseRemarks;
    private String professionalFeeRemarks;
    private String loansRemarks;

    private String otherId;

   
    private String officialExpensesRemarks;
    private String otherRemarks;
    private String finOverAllComments;
    private String otherLoans;
    private String otherLoanRemarks;
    private String otherCategory;
    private String otherAmt;

   

   
    private String otherLoanAmt;
    private String totalAmount;
    private String lastPaidSalaryAmt;
    private String travelAdvAmt;
    private String salAdvAmt;
    private String relocationAmt;
     private String joiningBonusAmt;
    private String noticeReimbAmt;
    private String professionalfeeAmt;
    private String loansAmt;
    private String officialExpAmt;


   

    
    //Variable used for Network Approval Module
    private String nsApprovalId;
    private String desktopPwd;
    private String emailDeactivation;
    private String salesCrm;
    private String finDax;
    private String nsOthers;
    private String desktopPwdRemarks;
    private String emailDeactivationRemarks;
    private String salesCrmRemarks;
    private String finDaxRemarks;
    private String nsOtherRemarks;
    private String nsOverAllComments;
    private String nsApproverId;

    //Variables used for Admin Approval Module
    private String adApprovalId;
    private String idCard;
    private String drawerKey;
    private String dataCard;
    private String mobilePhone;
    private String simCard;
    private String laptop;
    private String businessCard;
    private String cmpResPhone;
    private String outBillClr;
    private String cdPenDrive;
    private String cabinKey;
    private String adOther;
    private String idCardRemarks;
    private String drawerKeyRemarks;
    private String dataCardRemarks;
    private String mobilePhoneRemarks;
    private String simCardRemarks;
    private String laptopRemarks;
    private String businessCardRemarks;
    private String cmpResPhoneRemarks;
    private String outBillClrRemarks;
    private String cdPenDriveRemarks;
    private String cabinKeyRemarks;
    private String adOtherRemarks;
    private String adOverAllComments;
    private String adApproverId;

    // Variables used for HR Approval Module
    private String hrApprovalId;
    private String exitType;
    private String idealToolDeactivated;
    private String leaveBalance;
    private String overseasBond;
    private String exitInterview;
    private String hrInsurance;
    private String hrOthers;
    private String exitTypeRemarks;
    private String idealToolDeactivatedRemarks;
    private String leaveBalanceRemarks;
    private String overseasBondRemarks;
    private String exitInterviewRemarks;
    private String hrInsuranceRemarks;
    private String hrOthersRemarks;
    private String hrOverAllComments;
    private String hrApproverId;
    private Date exitTriggerDate;
    private String rmComments;
    private MultipartFile file;
    private int fileId;
    private String fileName;
    private String fileType;
    private String referenceName;
    private int referenceId;
    private String moduleName;
    private String survey_status;
    private String session_empid;
    private String exitKey;
    private String exitValues;
    private String employmentStatus;
    private String employmentStatusId;
    private String remarks;
    private String empStatus;
    private long modified_time;
    private long created_time;
    private String check;
    private String checkService;
    private String manager;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    
    public String getCheckService() {
        return checkService;
    }

    public void setCheckService(String checkService) {
        this.checkService = checkService;
    }
    
    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
    
    public String getExitKey() {
        return exitKey;
    }

    public void setExitKey(String exitKey) {
        this.exitKey = exitKey;
    }

    public String getExitValues() {
        return exitValues;
    }

    public void setExitValues(String exitValues) {
        this.exitValues = exitValues;
    }

    public long getModified_time() {
        return modified_time;
    }

    public void setModified_time(long modified_time) {
        this.modified_time = modified_time;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }
    
    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }
    
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getEmploymentStatusId() {
        return employmentStatusId;
    }

    public void setEmploymentStatusId(String employmentStatusId) {
        this.employmentStatusId = employmentStatusId;
    }
    
   

	public String getSession_empid() {
		return session_empid;
	}

	public void setSession_empid(String session_empid) {
		this.session_empid = session_empid;
	}

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    

    public String getRmComments() {
        return rmComments;
    }

    public void setRmComments(String rmComments) {
        this.rmComments = rmComments;
    }
    
    
        private HashMap getMonthList(){
        HashMap monthList = new HashMap();
            monthList.put(1, "January");
            monthList.put(2, "Feburary");
            monthList.put(3, "March");
            monthList.put(4, "April");
            monthList.put(5, "May");
            monthList.put(6, "June");
            monthList.put(7, "July");
            monthList.put(8, "August");
            monthList.put(9, "September");
            monthList.put(10, "October");
            monthList.put(11, "November");
            monthList.put(12, "December");
            return monthList;
    }



    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getAdApprovalId() {
        return adApprovalId;
    }

    public void setAdApprovalId(String adApprovalId) {
        this.adApprovalId = adApprovalId;
    }

    public Date getAdApprovedDate() {
        return adApprovedDate;
    }

    public void setAdApprovedDate(Date adApprovedDate) {
        this.adApprovedDate = adApprovedDate;
    }

    public String getAdApproverId() {
        return adApproverId;
    }

    public void setAdApproverId(String adApproverId) {
        this.adApproverId = adApproverId;
    }

    public String getAdOther() {
        return adOther;
    }

    public void setAdOther(String adOther) {
        this.adOther = adOther;
    }

    public String getAdOtherRemarks() {
        return adOtherRemarks;
    }

    public void setAdOtherRemarks(String adOtherRemarks) {
        this.adOtherRemarks = adOtherRemarks;
    }

    public String getAdOverAllComments() {
        return adOverAllComments;
    }

    public void setAdOverAllComments(String adOverAllComments) {
        this.adOverAllComments = adOverAllComments;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(String answerKey) {
        this.answerKey = answerKey;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    public String getBalNoticePeriod() {
        return balNoticePeriod;
    }

    public void setBalNoticePeriod(String balNoticePeriod) {
        this.balNoticePeriod = balNoticePeriod;
    }

    public String getBusinessCard() {
        return businessCard;
    }

    public void setBusinessCard(String businessCard) {
        this.businessCard = businessCard;
    }

    public String getBusinessCardRemarks() {
        return businessCardRemarks;
    }

    public void setBusinessCardRemarks(String businessCardRemarks) {
        this.businessCardRemarks = businessCardRemarks;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getCabinKey() {
        return cabinKey;
    }

    public void setCabinKey(String cabinKey) {
        this.cabinKey = cabinKey;
    }

    public String getCabinKeyRemarks() {
        return cabinKeyRemarks;
    }

    public void setCabinKeyRemarks(String cabinKeyRemarks) {
        this.cabinKeyRemarks = cabinKeyRemarks;
    }

    public String getCdPenDrive() {
        return cdPenDrive;
    }

    public void setCdPenDrive(String cdPenDrive) {
        this.cdPenDrive = cdPenDrive;
    }

    public String getCdPenDriveRemarks() {
        return cdPenDriveRemarks;
    }

    public void setCdPenDriveRemarks(String cdPenDriveRemarks) {
        this.cdPenDriveRemarks = cdPenDriveRemarks;
    }

    public Integer getCmpNoticePeriodInDays() {
        return cmpNoticePeriodInDays;
    }

    public void setCmpNoticePeriodInDays(Integer cmpNoticePeriodInDays) {
        this.cmpNoticePeriodInDays = cmpNoticePeriodInDays;
    }

    public String getCmpResPhone() {
        return cmpResPhone;
    }

    public void setCmpResPhone(String cmpResPhone) {
        this.cmpResPhone = cmpResPhone;
    }

    public String getCmpResPhoneRemarks() {
        return cmpResPhoneRemarks;
    }

    public void setCmpResPhoneRemarks(String cmpResPhoneRemarks) {
        this.cmpResPhoneRemarks = cmpResPhoneRemarks;
    }

    public String getCompDoc() {
        return compDoc;
    }

    public void setCompDoc(String compDoc) {
        this.compDoc = compDoc;
    }

    public String getCompDocRemarks() {
        return compDocRemarks;
    }

    public void setCompDocRemarks(String compDocRemarks) {
        this.compDocRemarks = compDocRemarks;
    }

    public String getCustApproval() {
        return custApproval;
    }

    public void setCustApproval(String custApproval) {
        this.custApproval = custApproval;
    }

    public String getCustApprovalRemarks() {
        return custApprovalRemarks;
    }

    public void setCustApprovalRemarks(String custApprovalRemarks) {
        this.custApprovalRemarks = custApprovalRemarks;
    }

    public String getDataCard() {
        return dataCard;
    }

    public void setDataCard(String dataCard) {
        this.dataCard = dataCard;
    }

    public String getDataCardRemarks() {
        return dataCardRemarks;
    }

    public void setDataCardRemarks(String dataCardRemarks) {
        this.dataCardRemarks = dataCardRemarks;
    }

    public String getDaysServed() {
        return daysServed;
    }

    public void setDaysServed(String daysServed) {
        this.daysServed = daysServed;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getDesktopPwd() {
        return desktopPwd;
    }

    public void setDesktopPwd(String desktopPwd) {
        this.desktopPwd = desktopPwd;
    }

    public String getDesktopPwdRemarks() {
        return desktopPwdRemarks;
    }

    public void setDesktopPwdRemarks(String desktopPwdRemarks) {
        this.desktopPwdRemarks = desktopPwdRemarks;
    }

    public String getDrawerKey() {
        return drawerKey;
    }

    public void setDrawerKey(String drawerKey) {
        this.drawerKey = drawerKey;
    }

    public String getDrawerKeyRemarks() {
        return drawerKeyRemarks;
    }

    public void setDrawerKeyRemarks(String drawerKeyRemarks) {
        this.drawerKeyRemarks = drawerKeyRemarks;
    }

    public String getEmailDeactivation() {
        return emailDeactivation;
    }

    public void setEmailDeactivation(String emailDeactivation) {
        this.emailDeactivation = emailDeactivation;
    }

    public String getEmailDeactivationRemarks() {
        return emailDeactivationRemarks;
    }

    public void setEmailDeactivationRemarks(String emailDeactivationRemarks) {
        this.emailDeactivationRemarks = emailDeactivationRemarks;
    }

    public String getEmpAnswer() {
        return empAnswer;
    }

    public void setEmpAnswer(String empAnswer) {
        this.empAnswer = empAnswer;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpQpd() {
        return empQpd;
    }

    public void setEmpQpd(String empQpd) {
        this.empQpd = empQpd;
    }

    public String getEmpQpdRemarks() {
        return empQpdRemarks;
    }

    public void setEmpQpdRemarks(String empQpdRemarks) {
        this.empQpdRemarks = empQpdRemarks;
    }

    public String getExitEmpId() {
        return exitEmpId;
    }

    public void setExitEmpId(String exitEmpId) {
        this.exitEmpId = exitEmpId;
    }

    public String getExitInterview() {
        return exitInterview;
    }

    public void setExitInterview(String exitInterview) {
        this.exitInterview = exitInterview;
    }

    public String getExitInterviewRemarks() {
        return exitInterviewRemarks;
    }

    public void setExitInterviewRemarks(String exitInterviewRemarks) {
        this.exitInterviewRemarks = exitInterviewRemarks;
    }

    public Date getExitTriggerDate() {
        return exitTriggerDate;
    }

    public void setExitTriggerDate(Date exitTriggerDate) {
        this.exitTriggerDate = exitTriggerDate;
    }

    public String getExitType() {
        return exitType;
    }

    public void setExitType(String exitType) {
        this.exitType = exitType;
    }

    public String getExitTypeRemarks() {
        return exitTypeRemarks;
    }

    public void setExitTypeRemarks(String exitTypeRemarks) {
        this.exitTypeRemarks = exitTypeRemarks;
    }

    public String getFinApprovalId() {
        return finApprovalId;
    }

    public void setFinApprovalId(String finApprovalId) {
        this.finApprovalId = finApprovalId;
    }

    public Date getFinApprovedDate() {
        return finApprovedDate;
    }

    public void setFinApprovedDate(Date finApprovedDate) {
        this.finApprovedDate = finApprovedDate;
    }

    public String getFinApproverId() {
        return finApproverId;
    }

    public void setFinApproverId(String finApproverId) {
        this.finApproverId = finApproverId;
    }

    public String getFinDax() {
        return finDax;
    }

    public void setFinDax(String finDax) {
        this.finDax = finDax;
    }

    public String getFinDaxRemarks() {
        return finDaxRemarks;
    }

    public void setFinDaxRemarks(String finDaxRemarks) {
        this.finDaxRemarks = finDaxRemarks;
    }

    public String getFinOverAllComments() {
        return finOverAllComments;
    }

    public void setFinOverAllComments(String finOverAllComments) {
        this.finOverAllComments = finOverAllComments;
    }

    public String getHrApprovalId() {
        return hrApprovalId;
    }

    public void setHrApprovalId(String hrApprovalId) {
        this.hrApprovalId = hrApprovalId;
    }

    public Date getHrApprovedDate() {
        return hrApprovedDate;
    }

    public void setHrApprovedDate(Date hrApprovedDate) {
        this.hrApprovedDate = hrApprovedDate;
    }

    public String getHrApproverId() {
        return hrApproverId;
    }

    public void setHrApproverId(String hrApproverId) {
        this.hrApproverId = hrApproverId;
    }

    public String getHrInsurance() {
        return hrInsurance;
    }

    public void setHrInsurance(String hrInsurance) {
        this.hrInsurance = hrInsurance;
    }

    public String getHrInsuranceRemarks() {
        return hrInsuranceRemarks;
    }

    public void setHrInsuranceRemarks(String hrInsuranceRemarks) {
        this.hrInsuranceRemarks = hrInsuranceRemarks;
    }

    public String getHrOthers() {
        return hrOthers;
    }

    public void setHrOthers(String hrOthers) {
        this.hrOthers = hrOthers;
    }

    public String getHrOthersRemarks() {
        return hrOthersRemarks;
    }

    public void setHrOthersRemarks(String hrOthersRemarks) {
        this.hrOthersRemarks = hrOthersRemarks;
    }

    public String getHrOverAllComments() {
        return hrOverAllComments;
    }

    public void setHrOverAllComments(String hrOverAllComments) {
        this.hrOverAllComments = hrOverAllComments;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardRemarks() {
        return idCardRemarks;
    }

    public void setIdCardRemarks(String idCardRemarks) {
        this.idCardRemarks = idCardRemarks;
    }

    public String getIdealToolDeactivated() {
        return idealToolDeactivated;
    }

    public void setIdealToolDeactivated(String idealToolDeactivated) {
        this.idealToolDeactivated = idealToolDeactivated;
    }

    public String getIdealToolDeactivatedRemarks() {
        return idealToolDeactivatedRemarks;
    }

    public void setIdealToolDeactivatedRemarks(String idealToolDeactivatedRemarks) {
        this.idealToolDeactivatedRemarks = idealToolDeactivatedRemarks;
    }

    public String getJoiningBonus() {
        return joiningBonus;
    }

    public void setJoiningBonus(String joiningBonus) {
        this.joiningBonus = joiningBonus;
    }

    public String getJoiningBonusAmt() {
        return joiningBonusAmt;
    }

    public void setJoiningBonusAmt(String joiningBonusAmt) {
        this.joiningBonusAmt = joiningBonusAmt;
    }

    public String getJoiningBonusRemarks() {
        return joiningBonusRemarks;
    }

    public void setJoiningBonusRemarks(String joiningBonusRemarks) {
        this.joiningBonusRemarks = joiningBonusRemarks;
    }

    public String getLaptop() {
        return laptop;
    }

    public void setLaptop(String laptop) {
        this.laptop = laptop;
    }

    public String getLaptopRemarks() {
        return laptopRemarks;
    }

    public void setLaptopRemarks(String laptopRemarks) {
        this.laptopRemarks = laptopRemarks;
    }

    public String getLastPaidSalary() {
        return lastPaidSalary;
    }

    public void setLastPaidSalary(String lastPaidSalary) {
        this.lastPaidSalary = lastPaidSalary;
    }

    public String getLastPaidSalaryAmt() {
        return lastPaidSalaryAmt;
    }

    public void setLastPaidSalaryAmt(String lastPaidSalaryAmt) {
        this.lastPaidSalaryAmt = lastPaidSalaryAmt;
    }

    public String getLastPaidSalaryRemarks() {
        return lastPaidSalaryRemarks;
    }

    public void setLastPaidSalaryRemarks(String lastPaidSalaryRemarks) {
        this.lastPaidSalaryRemarks = lastPaidSalaryRemarks;
    }

    public Date getLastWorkingDate() {
        return lastWorkingDate;
    }

    public void setLastWorkingDate(Date lastWorkingDate) {
        this.lastWorkingDate = lastWorkingDate;
    }

    public String getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(String leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public String getLeaveBalanceRemarks() {
        return leaveBalanceRemarks;
    }

    public void setLeaveBalanceRemarks(String leaveBalanceRemarks) {
        this.leaveBalanceRemarks = leaveBalanceRemarks;
    }

    public String getLoans() {
        return loans;
    }

    public void setLoans(String loans) {
        this.loans = loans;
    }

    public String getLoansAmt() {
        return loansAmt;
    }

    public void setLoansAmt(String loansAmt) {
        this.loansAmt = loansAmt;
    }

    public String getLoansRemarks() {
        return loansRemarks;
    }

    public void setLoansRemarks(String loansRemarks) {
        this.loansRemarks = loansRemarks;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhoneRemarks() {
        return mobilePhoneRemarks;
    }

    public void setMobilePhoneRemarks(String mobilePhoneRemarks) {
        this.mobilePhoneRemarks = mobilePhoneRemarks;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoticeReimbAmt() {
        return noticeReimbAmt;
    }

    public void setNoticeReimbAmt(String noticeReimbAmt) {
        this.noticeReimbAmt = noticeReimbAmt;
    }

    public String getNoticeReimburse() {
        return noticeReimburse;
    }

    public void setNoticeReimburse(String noticeReimburse) {
        this.noticeReimburse = noticeReimburse;
    }

    public String getNoticeReimburseRemarks() {
        return noticeReimburseRemarks;
    }

    public void setNoticeReimburseRemarks(String noticeReimburseRemarks) {
        this.noticeReimburseRemarks = noticeReimburseRemarks;
    }

    public String getNoticeWaiverValue() {
        return noticeWaiverValue;
    }

    public void setNoticeWaiverValue(String noticeWaiverValue) {
        this.noticeWaiverValue = noticeWaiverValue;
    }

    public String getNsApprovalId() {
        return nsApprovalId;
    }

    public void setNsApprovalId(String nsApprovalId) {
        this.nsApprovalId = nsApprovalId;
    }

    public Date getNsApprovedDate() {
        return nsApprovedDate;
    }

    public void setNsApprovedDate(Date nsApprovedDate) {
        this.nsApprovedDate = nsApprovedDate;
    }

    public String getNsApproverId() {
        return nsApproverId;
    }

    public void setNsApproverId(String nsApproverId) {
        this.nsApproverId = nsApproverId;
    }

    public String getNsOtherRemarks() {
        return nsOtherRemarks;
    }

    public void setNsOtherRemarks(String nsOtherRemarks) {
        this.nsOtherRemarks = nsOtherRemarks;
    }

    public String getNsOthers() {
        return nsOthers;
    }

    public void setNsOthers(String nsOthers) {
        this.nsOthers = nsOthers;
    }

    public String getNsOverAllComments() {
        return nsOverAllComments;
    }

    public void setNsOverAllComments(String nsOverAllComments) {
        this.nsOverAllComments = nsOverAllComments;
    }

    public String getOfficialExpAmt() {
        return officialExpAmt;
    }

    public void setOfficialExpAmt(String officialExpAmt) {
        this.officialExpAmt = officialExpAmt;
    }

    public String getOfficialExpenses() {
        return officialExpenses;
    }

    public void setOfficialExpenses(String officialExpenses) {
        this.officialExpenses = officialExpenses;
    }

    public String getOfficialExpensesRemarks() {
        return officialExpensesRemarks;
    }

    public void setOfficialExpensesRemarks(String officialExpensesRemarks) {
        this.officialExpensesRemarks = officialExpensesRemarks;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOtherAmt() {
        return otherAmt;
    }

    public void setOtherAmt(String otherAmt) {
        this.otherAmt = otherAmt;
    }

    public String getOtherCategory() {
        return otherCategory;
    }

    public void setOtherCategory(String otherCategory) {
        this.otherCategory = otherCategory;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getOtherLoanAmt() {
        return otherLoanAmt;
    }

    public void setOtherLoanAmt(String otherLoanAmt) {
        this.otherLoanAmt = otherLoanAmt;
    }

    public String getOtherLoanRemarks() {
        return otherLoanRemarks;
    }

    public void setOtherLoanRemarks(String otherLoanRemarks) {
        this.otherLoanRemarks = otherLoanRemarks;
    }

    public String getOtherLoans() {
        return otherLoans;
    }

    public void setOtherLoans(String otherLoans) {
        this.otherLoans = otherLoans;
    }

    public String getOtherRemarks() {
        return otherRemarks;
    }

    public void setOtherRemarks(String otherRemarks) {
        this.otherRemarks = otherRemarks;
    }

    public String getOutBillClr() {
        return outBillClr;
    }

    public void setOutBillClr(String outBillClr) {
        this.outBillClr = outBillClr;
    }

    public String getOutBillClrRemarks() {
        return outBillClrRemarks;
    }

    public void setOutBillClrRemarks(String outBillClrRemarks) {
        this.outBillClrRemarks = outBillClrRemarks;
    }

    public String getOverseasBond() {
        return overseasBond;
    }

    public void setOverseasBond(String overseasBond) {
        this.overseasBond = overseasBond;
    }

    public String getOverseasBondRemarks() {
        return overseasBondRemarks;
    }

    public void setOverseasBondRemarks(String overseasBondRemarks) {
        this.overseasBondRemarks = overseasBondRemarks;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProfessionalFee() {
        return professionalFee;
    }

    public void setProfessionalFee(String professionalFee) {
        this.professionalFee = professionalFee;
    }

    public String getProfessionalFeeRemarks() {
        return professionalFeeRemarks;
    }

    public void setProfessionalFeeRemarks(String professionalFeeRemarks) {
        this.professionalFeeRemarks = professionalFeeRemarks;
    }

    public String getProfessionalfeeAmt() {
        return professionalfeeAmt;
    }

    public void setProfessionalfeeAmt(String professionalfeeAmt) {
        this.professionalfeeAmt = professionalfeeAmt;
    }

    public String getProjDoc() {
        return projDoc;
    }

    public void setProjDoc(String projDoc) {
        this.projDoc = projDoc;
    }

    public String getProjDocRemarks() {
        return projDocRemarks;
    }

    public void setProjDocRemarks(String projDocRemarks) {
        this.projDocRemarks = projDocRemarks;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getReasonRejection() {
        return reasonRejection;
    }

    public void setReasonRejection(String reasonRejection) {
        this.reasonRejection = reasonRejection;
    }

    public String getRelocation() {
        return relocation;
    }

    public void setRelocation(String relocation) {
        this.relocation = relocation;
    }

    public String getRelocationAmt() {
        return relocationAmt;
    }

    public void setRelocationAmt(String relocationAmt) {
        this.relocationAmt = relocationAmt;
    }

    public String getRelocationRemarks() {
        return relocationRemarks;
    }

    public void setRelocationRemarks(String relocationRemarks) {
        this.relocationRemarks = relocationRemarks;
    }

    public String getResEmpId() {
        return resEmpId;
    }

    public void setResEmpId(String resEmpId) {
        this.resEmpId = resEmpId;
    }

    public String getRmApprovalId() {
        return rmApprovalId;
    }

    public void setRmApprovalId(String rmApprovalId) {
        this.rmApprovalId = rmApprovalId;
    }

    public Date getRmApprovedDate() {
        return rmApprovedDate;
    }

    public void setRmApprovedDate(Date rmApprovedDate) {
        this.rmApprovedDate = rmApprovedDate;
    }

    public Date getRmClrDate() {
        return rmClrDate;
    }

    public void setRmClrDate(Date rmClrDate) {
        this.rmClrDate = rmClrDate;
    }

    public String getRmClrId() {
        return rmClrId;
    }

    public void setRmClrId(String rmClrId) {
        this.rmClrId = rmClrId;
    }

    public String getRmClrOverAllComments() {
        return rmClrOverAllComments;
    }

    public void setRmClrOverAllComments(String rmClrOverAllComments) {
        this.rmClrOverAllComments = rmClrOverAllComments;
    }

    public String getRmId() {
        return rmId;
    }

    public void setRmId(String rmId) {
        this.rmId = rmId;
    }

    public String getRmOthers() {
        return rmOthers;
    }

    public void setRmOthers(String rmOthers) {
        this.rmOthers = rmOthers;
    }

    public String getRmOthersRemarks() {
        return rmOthersRemarks;
    }

    public void setRmOthersRemarks(String rmOthersRemarks) {
        this.rmOthersRemarks = rmOthersRemarks;
    }

    public String getSalAdvAmt() {
        return salAdvAmt;
    }

    public void setSalAdvAmt(String salAdvAmt) {
        this.salAdvAmt = salAdvAmt;
    }

    public String getSalaryAdvance() {
        return salaryAdvance;
    }

    public void setSalaryAdvance(String salaryAdvance) {
        this.salaryAdvance = salaryAdvance;
    }

    public String getSalaryAdvanceRemarks() {
        return salaryAdvanceRemarks;
    }

    public void setSalaryAdvanceRemarks(String salaryAdvanceRemarks) {
        this.salaryAdvanceRemarks = salaryAdvanceRemarks;
    }

    public String getSalesCrm() {
        return salesCrm;
    }

    public void setSalesCrm(String salesCrm) {
        this.salesCrm = salesCrm;
    }

    public String getSalesCrmRemarks() {
        return salesCrmRemarks;
    }

    public void setSalesCrmRemarks(String salesCrmRemarks) {
        this.salesCrmRemarks = salesCrmRemarks;
    }

    public String getSimCard() {
        return simCard;
    }

    public void setSimCard(String simCard) {
        this.simCard = simCard;
    }

    public String getSimCardRemarks() {
        return simCardRemarks;
    }

    public void setSimCardRemarks(String simCardRemarks) {
        this.simCardRemarks = simCardRemarks;
    }

    public String getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTravelAdvAmt() {
        return travelAdvAmt;
    }

    public void setTravelAdvAmt(String travelAdvAmt) {
        this.travelAdvAmt = travelAdvAmt;
    }

    public String getTravelAdvance() {
        return travelAdvance;
    }

    public void setTravelAdvance(String travelAdvance) {
        this.travelAdvance = travelAdvance;
    }

    public String getTravelAdvanceRemarks() {
        return travelAdvanceRemarks;
    }

    public void setTravelAdvanceRemarks(String travelAdvanceRemarks) {
        this.travelAdvanceRemarks = travelAdvanceRemarks;
    }

    	public String getSurvey_status() {
		return survey_status;
	}

	public void setSurvey_status(String survey_status) {
		this.survey_status = survey_status;
	}

}