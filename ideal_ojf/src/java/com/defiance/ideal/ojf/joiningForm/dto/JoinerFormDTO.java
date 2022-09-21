/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.joiningForm.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 15858
 */
public class JoinerFormDTO {

    //////////
    private String xYearOfPassing;
    private String xInstitution;
    private String xPercentage;
    private String p2Qualification;
    private String p2YearOfPassing;
    private String p2Institution;
    private String p2Percentage;
    private String gradQualification;
    private String gradYearOfPassing;
    private String gradInstitution;
    private String gradPercentage;
    private String pGradQualification;
    private String pGradYearOfPassing;
    private String pGradInstitution;
    private String pGradPercentage;
    private int educationalQualificationCount;
    private String fileId;
    private String fileType;
    private String referenceName;
    private String referenceId;
    private String moduleName;
    private MultipartFile[] otherProofDetails;
    private String cityName;
    private String hrName;
    private String jfStatus;
    private String dataVerifiedById;
    private String dataApprovedById;
    private String empId1;
    public String joinerEmpId;
    public String employeeLocation;
    public String joinedDate;
    public String refFrmEarNameDb;
    public String refFrmEarCmpNameDb;
    public String refFrmEarAddressAndTelDb;
//     private String[] refFrmEarDesignation;
//     public String refFrmEarDesignationDb;
//     private String[] refFrmEarNoOfYearsKnown;
//     public String refFrmEarNoOfYearsKnownDb;
    //  private String[] refFrmEarlierCompanyStatus;
    public int refFrmEarlierCompanyCount;
    public int refFrmEarlierCompDeleted;
    public String nameOfPersonAtComDb;
    private String[] nameOfPersonAtCom;
    public String companyAndDepDb;
    private String[] companyAndDep;
    public String pacRelationDb;
    private String[] pacRelation;
    private String[] personsAtCompanyStatus;
    private String[] refFrmEarDesignation;
    public String refFrmEarDesignationDb;
    private String[] refFrmEarNoOfYearsKnown;
    private String[] refFrmEarEmailId;
    public String refFrmEarNoOfYearsKnownDb;
    public String refFrmEarEmailIdDb;
    private String[] refFrmEarlierCompanyStatus;
    public String country;
    public String region;
    public String city;
    public String zip_code;
    public int emp_id;
    public String address_type;
    public int nonappraiseestatus;
    private int CER_TR_deletedX;
    public String employeeName;
    public String employeeNumber;
    public String employeeCcEmail;
    public String employeeToEmail;
    private String employeeRemarks;
    private String closedDate;
    private String practiceGroupIdd;

    public String[] getRefFrmEarEmailId() {
        return refFrmEarEmailId;
    }

    public void setRefFrmEarEmailId(String[] refFrmEarEmailId) {
        this.refFrmEarEmailId = refFrmEarEmailId;
    }

    public String getRefFrmEarEmailIdDb() {
        return refFrmEarEmailIdDb;
    }

    public void setRefFrmEarEmailIdDb(String refFrmEarEmailIdDb) {
        this.refFrmEarEmailIdDb = refFrmEarEmailIdDb;
    }
    
    public String getPracticeGroupIdd() {
        return practiceGroupIdd;
    }

    public void setPracticeGroupIdd(String practiceGroupIdd) {
        this.practiceGroupIdd = practiceGroupIdd;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getEmployeeRemarks() {
        return employeeRemarks;
    }

    public void setEmployeeRemarks(String employeeRemarks) {
        this.employeeRemarks = employeeRemarks;
    }

    public String getEmployeeToEmail() {
        return employeeToEmail;
    }

    public void setEmployeeToEmail(String employeeToEmail) {
        this.employeeToEmail = employeeToEmail;
    }
    public int eID;

    public int geteID() {
        return eID;
    }

    public void seteID(int eID) {
        this.eID = eID;
    }

    public String getEmployeeCcEmail() {
        return employeeCcEmail;
    }

    public void setEmployeeCcEmail(String employeeCcEmail) {
        this.employeeCcEmail = employeeCcEmail;
    }
    public String employeeId;
    public int experience;
    public int expYear;
    public int expMonth;
    //private String ifsc;
    public String adharNo;
    public String masterBillable;

    public String getAdharNo() {
        return adharNo;
    }

    public void setAdharNo(String adharNo) {
        this.adharNo = adharNo;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getCER_TR_deletedX() {
        return CER_TR_deletedX;
    }

    public void setCER_TR_deletedX(int CER_TR_deletedX) {
        this.CER_TR_deletedX = CER_TR_deletedX;
    }

    public int getNonappraiseestatus() {
        return nonappraiseestatus;
    }

    public void setNonappraiseestatus(int nonappraiseestatus) {
        this.nonappraiseestatus = nonappraiseestatus;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNameOfPersonAtComDb() {
        return nameOfPersonAtComDb;
    }

    public void setNameOfPersonAtComDb(String nameOfPersonAtComDb) {
        this.nameOfPersonAtComDb = nameOfPersonAtComDb;
    }

    public String[] getNameOfPersonAtCom() {
        return nameOfPersonAtCom;
    }

    public void setNameOfPersonAtCom(String[] nameOfPersonAtCom) {
        this.nameOfPersonAtCom = nameOfPersonAtCom;
    }

    public String getCompanyAndDepDb() {
        return companyAndDepDb;
    }

    public void setCompanyAndDepDb(String companyAndDepDb) {
        this.companyAndDepDb = companyAndDepDb;
    }

    public String[] getCompanyAndDep() {
        return companyAndDep;
    }

    public void setCompanyAndDep(String[] companyAndDep) {
        this.companyAndDep = companyAndDep;
    }

    public String getPacRelationDb() {
        return pacRelationDb;
    }

    public void setPacRelationDb(String pacRelationDb) {
        this.pacRelationDb = pacRelationDb;
    }

    public String[] getPacRelation() {
        return pacRelation;
    }

    public void setPacRelation(String[] pacRelation) {
        this.pacRelation = pacRelation;
    }

    public String[] getPersonsAtCompanyStatus() {
        return personsAtCompanyStatus;
    }

    public void setPersonsAtCompanyStatus(String[] personsAtCompanyStatus) {
        this.personsAtCompanyStatus = personsAtCompanyStatus;
    }

    public String[] getRefFrmEarDesignation() {
        return refFrmEarDesignation;
    }

    public void setRefFrmEarDesignation(String[] refFrmEarDesignation) {
        this.refFrmEarDesignation = refFrmEarDesignation;
    }

    public String getRefFrmEarDesignationDb() {
        return refFrmEarDesignationDb;
    }

    public void setRefFrmEarDesignationDb(String refFrmEarDesignationDb) {
        this.refFrmEarDesignationDb = refFrmEarDesignationDb;
    }

    public String[] getRefFrmEarNoOfYearsKnown() {
        return refFrmEarNoOfYearsKnown;
    }

    public void setRefFrmEarNoOfYearsKnown(String[] refFrmEarNoOfYearsKnown) {
        this.refFrmEarNoOfYearsKnown = refFrmEarNoOfYearsKnown;
    }

    public String getRefFrmEarNoOfYearsKnownDb() {
        return refFrmEarNoOfYearsKnownDb;
    }

    public void setRefFrmEarNoOfYearsKnownDb(String refFrmEarNoOfYearsKnownDb) {
        this.refFrmEarNoOfYearsKnownDb = refFrmEarNoOfYearsKnownDb;
    }

    public String[] getRefFrmEarlierCompanyStatus() {
        return refFrmEarlierCompanyStatus;
    }

    public void setRefFrmEarlierCompanyStatus(String[] refFrmEarlierCompanyStatus) {
        this.refFrmEarlierCompanyStatus = refFrmEarlierCompanyStatus;
    }
    public int personsAtCompanyCount;
    public int personsAtCompanyDeleted;

    public String getEmployeeLocation() {
        return employeeLocation;
    }

    public void setEmployeeLocation(String employeeLocation) {
        this.employeeLocation = employeeLocation;
    }

    public int getRefFrmEarlierCompanyCount() {
        return refFrmEarlierCompanyCount;
    }

    public void setRefFrmEarlierCompanyCount(int refFrmEarlierCompanyCount) {
        this.refFrmEarlierCompanyCount = refFrmEarlierCompanyCount;
    }

    public List getFormFiles() {
        return formFiles;
    }

    public void setFormFiles(List formFiles) {
        this.formFiles = formFiles;
    }
    //////////
    // Varibales needed for recruiter screen
    private String userEmpId;
    private String userName;
    private String workEmailId;
    private String contractemployeeId;
    private String sendBackReason;
    private String date_of_expiry;
    private String date_of_issue;
    private String phone;
    private String type;
    private String address;
    private int age;
    private int employeeStatusX;
    private int empId;
    public String[] refFrmEarName;
    //  public String refFrmEarNameDb;
    public String[] refFrmEarCmpName;
    //public String refFrmEarCmpNameDb;
    public String[] refFrmEarAddressAndTel;
    // public String refFrmEarAddressAndTelDb;

    public String[] getRefFrmEarName() {
        return refFrmEarName;
    }

    public void setRefFrmEarName(String[] refFrmEarName) {
        this.refFrmEarName = refFrmEarName;
    }

    public String getRefFrmEarNameDb() {
        return refFrmEarNameDb;
    }

    public void setRefFrmEarNameDb(String refFrmEarNameDb) {
        this.refFrmEarNameDb = refFrmEarNameDb;
    }

    public String[] getRefFrmEarCmpName() {
        return refFrmEarCmpName;
    }

    public void setRefFrmEarCmpName(String[] refFrmEarCmpName) {
        this.refFrmEarCmpName = refFrmEarCmpName;
    }

    public String getRefFrmEarCmpNameDb() {
        return refFrmEarCmpNameDb;
    }

    public void setRefFrmEarCmpNameDb(String refFrmEarCmpNameDb) {
        this.refFrmEarCmpNameDb = refFrmEarCmpNameDb;
    }

    public String[] getRefFrmEarAddressAndTel() {
        return refFrmEarAddressAndTel;
    }

    public void setRefFrmEarAddressAndTel(String[] refFrmEarAddressAndTel) {
        this.refFrmEarAddressAndTel = refFrmEarAddressAndTel;
    }

    public String getRefFrmEarAddressAndTelDb() {
        return refFrmEarAddressAndTelDb;
    }

    public void setRefFrmEarAddressAndTelDb(String refFrmEarAddressAndTelDb) {
        this.refFrmEarAddressAndTelDb = refFrmEarAddressAndTelDb;
    }

//    public int getRefFrmEarlierCompanyCount() {
//        return refFrmEarlierCompanyCount;
//    }
//
//    public void setRefFrmEarlierCompanyCount(int refFrmEarlierCompanyCount) {
//        this.refFrmEarlierCompanyCount = refFrmEarlierCompanyCount;
//    }
    public int getRefFrmEarlierCompDeleted() {
        return refFrmEarlierCompDeleted;
    }

    public void setRefFrmEarlierCompDeleted(int refFrmEarlierCompDeleted) {
        this.refFrmEarlierCompDeleted = refFrmEarlierCompDeleted;
    }
//    public String[] refFrmEarDesignation;
//    public String refFrmEarDesignationDb;
//    public String[] refFrmEarNoOfYearsKnown;
//    public String refFrmEarNoOfYearsKnownDb;
//    public String[] refFrmEarlierCompanyStatus;
//    //public int refFrmEarlierCompanyCount;
//    public int refFrmEarlierCompDeleted;
    public int jfPerCompIdDb;
    public int[] jfPerCompId;
    public int jfRefEarCompIdDb;
    public int[] jfRefEarCompId;
    private String dataVerifiedBy;
    private String checkJoinerWorkEmail;
    private String joinerWorkEmail;
    private MultipartFile passAttachment_1;
    private MultipartFile passAttachment_2;
    private MultipartFile passAttachment_3;
    private MultipartFile passAttachment_4;
    private MultipartFile passAttachment_5;
    private MultipartFile passAttachment_6;
    private MultipartFile passAttachment_7;
    private MultipartFile passAttachment_8;
    private MultipartFile passAttachment_9;
    private MultipartFile passAttachment_10;
    private String[] visaCountry;
    private String visaCountryDb;
    private String[] visaType;
    private String visaTypeDb;
    private String[] visaDateOfIssue;
    private String visaDateOfIssueDb;
    private String[] visaDateOfExpiry;
    private String visaDateOfExpiryDb;
    private String[] jfVisaId;
    private String jfVisaIdDb;
    private String[] visaDetailsStatus;
    private int visaDetailsCount;
    private int visaDetailsDeleted;
    private int jfFamIdDb;
    public int[] VISA_TR_deleted;
    private int VISA_TR_deletedX;
    private String[] occupationOfRel;
    private String occupationOfRelDb;
    private int[] lifeInsurance;
    private int lifeInsuranceDb;
    private int[] medicalInsurance;
    private int medicalInsuranceDb;
    private String[] nameOfRelation;
    private String nameOfRelationDb;
    private String[] relationShip;
    private String relationShipDb;
    private String[] dobRelation;
    private String dobRelationDb;
    private String[] jfFamId;
    private String dataVerifiedMailCc;
    private String dataApprovedBy;
    private MultipartFile dlProof;
    private String dlDateOfExpiry;
    private String dlDateOfIssue;
    private String dlPlaceOfIssue;
    private String jfPreEmpIdDb;
    private String nameAddPrevEmpDb;
    private String dateOfJoinDb;
    private String desigOnJoinDb;
    private String salaryOnJoinDb;
    private String dateOfRelDb;
    private String desigOnRelDb;
    private String salOnRelDb;
    private String[] PRF_TR_deleted;
    private String documentType;
    private String documentName;
    private String documentSubmitionStatus;
    private String[] docs;
    private String[] docsTemp;
    private String documentId;
    private String appStatus;

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String[] getDocs() {
        return docs;
    }

    public void setDocs(String[] docs) {
        this.docs = docs;
    }

    public String[] getDocsTemp() {
        return docsTemp;
    }

    public void setDocsTemp(String[] docsTemp) {
        this.docsTemp = docsTemp;
    }

    public String getDocumentSubmitionStatus() {
        return documentSubmitionStatus;
    }

    public void setDocumentSubmitionStatus(String documentSubmitionStatus) {
        this.documentSubmitionStatus = documentSubmitionStatus;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getMasterBillable() {
        return masterBillable;
    }

    public void setMasterBillable(String masterBillable) {
        this.masterBillable = masterBillable;
    }

    public String[] getPRF_TR_deleted() {
        return PRF_TR_deleted;
    }

    public void setPRF_TR_deleted(String[] PRF_TR_deleted) {
        this.PRF_TR_deleted = PRF_TR_deleted;
    }
    private String PRF_TR_deletedX;

    public String getPRF_TR_deletedX() {
        return PRF_TR_deletedX;
    }

    public void setPRF_TR_deletedX(String PRF_TR_deletedX) {
        this.PRF_TR_deletedX = PRF_TR_deletedX;
    }
    private String[] REL_TR_deleted;
    private String REL_TR_deletedX;

    public String[] getREL_TR_deleted() {
        return REL_TR_deleted;
    }

    public void setREL_TR_deleted(String[] REL_TR_deleted) {
        this.REL_TR_deleted = REL_TR_deleted;
    }

    public String getREL_TR_deletedX() {
        return REL_TR_deletedX;
    }

    public void setREL_TR_deletedX(String REL_TR_deletedX) {
        this.REL_TR_deletedX = REL_TR_deletedX;
    }

    public String getDlFileName() {
        return dlFileName;
    }

    public void setDlFileName(String dlFileName) {
        this.dlFileName = dlFileName;
    }

    public String getDlFileType() {
        return dlFileType;
    }

    public void setDlFileType(String dlFileType) {
        this.dlFileType = dlFileType;
    }

    public String getDlFileId() {
        return dlFileId;
    }

    public void setDlFileId(String dlFileId) {
        this.dlFileId = dlFileId;
    }
    public String dlFileName;
    public String dlFileType;
    public String dlFileId;

    public String getDlPlaceOfIssue() {
        return dlPlaceOfIssue;
    }

    public void setDlPlaceOfIssue(String dlPlaceOfIssue) {
        this.dlPlaceOfIssue = dlPlaceOfIssue;
    }
    private String dlNumber;
    public String[] educationalQualificationFileAttachmentStatus;
    private List formFiles = new ArrayList();
    private String[] dateOfRel;
    private String[] salOnRel;
    private String[] desigOnRel;
    private String[] salaryOnJoin;
    private String[] desigOnJoin;
    private String[] dateOfJoin;
    private String[] nameAddPrevEmp;
    private String[] jfPreEmpId;
    private String jfPreEmpIdX;
    private String dateOfRelX;
    private String salOnRelX;
    private String desigOnRelX;
    private String salaryOnJoinX;
    private String desigOnJoinX;
    private String dateOfJoinX;
    private String nameAddPrevEmpX;
    private String jfEduProofIdDb;
    private String workMailid;
    private String employeementType;
    private String joiningLocation;
    private String currentLocation;
    private String empBillable;
    // public String joinerEmpId;
    private String empNumber;
    // private String joinedDate;
    private String fileName;
    private String previousPfNumber;
    private String uanNo;
    private MultipartFile CanChequeLeaf;
    private MultipartFile CanChequeLeafYes;
    private MultipartFile EmpPhotoUpload;
    private MultipartFile EmpAddProof;
    private MultipartFile empAdharProof;
    private MultipartFile empUanProof;
    private MultipartFile attachment_1;
    private MultipartFile attachment_2;
    private MultipartFile attachment_3;
    private MultipartFile attachment_4;
    private MultipartFile attachment_5;
    private MultipartFile attachment_6;
    private MultipartFile attachment_7;
    private MultipartFile attachment_8;
    private MultipartFile attachment_9;
    private MultipartFile attachment_10;
    private MultipartFile cert_attachment_1;
    private MultipartFile cert_attachment_2;
    private MultipartFile cert_attachment_3;
    private MultipartFile cert_attachment_4;
    private MultipartFile cert_attachment_5;
    private MultipartFile cert_attachment_6;
    private MultipartFile cert_attachment_7;
    private MultipartFile cert_attachment_8;
    private MultipartFile cert_attachment_9;
    private MultipartFile cert_attachment_10;
    private MultipartFile exp_attachment_1;
    private MultipartFile exp_attachment_2;
    private MultipartFile exp_attachment_3;
    private MultipartFile exp_attachment_4;
    private MultipartFile exp_attachment_5;
    private MultipartFile exp_attachment_6;
    private MultipartFile exp_attachment_7;
    private MultipartFile exp_attachment_8;
    private MultipartFile exp_attachment_9;
    private MultipartFile exp_attachment_10;
    private MultipartFile otherProof0;
    private MultipartFile otherProof1;
    private MultipartFile otherProof2;
    private MultipartFile otherProof3;
    private MultipartFile otherProof4;
    private MultipartFile otherProof5;
    private MultipartFile otherProof6;
    private MultipartFile otherProof7;
    private MultipartFile otherProof8;
    private MultipartFile otherProof9;
    private MultipartFile otherProof10;

    public MultipartFile getCanChequeLeafYes() {
        return CanChequeLeafYes;
    }

    public void setCanChequeLeafYes(MultipartFile CanChequeLeafYes) {
        this.CanChequeLeafYes = CanChequeLeafYes;
    }
    private String doj;
    private String joinerDoj;
    private String empref;
    private String jfId;
    private String emloyeeNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String structureName;
    private String practiceGroup;
    private String subPracticeGroup;
    private String band;
    private String subBand;
    private String designation;
    private String designationId;
    private String subBandName;
    private String bandName;
    private String desigName;
    private String personalEmailId1;
    private String personalEmailId2;
    private String reportingManager;
    private String rmName;
    public String dateOfBirth;
    private String panNo;
    private String refnumber;
    public String employeeType;
    private String status;
    private String trackNumber;
    private String structureId;
    private String practiceGroupId;
    private String subPracticeGroupId;
    private String subBandId;
    private String bandId;
    private String desigId;
    private String mailTo;
    private String mailCc;
    private String mailTriggeredBy;
    private String buttonName;
    private String buttonType;
    private String ContentType;
    private String FileData;
    private String rmMail;
    private String ojfHrName;
    private String currentStatus;
    private String id;

    public String getDesignationId() {
        return designationId;
    }

    public void setDesignationId(String designationId) {
        this.designationId = designationId;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getId() {
        return id;
    }

    public String getOjfHrName() {
        return ojfHrName;
    }

    public void setOjfHrName(String ojfHrName) {
        this.ojfHrName = ojfHrName;
    }

    public String getRmMail() {
        return rmMail;
    }

    public void setRmMail(String rmMail) {
        this.rmMail = rmMail;
    }

    public String getFileData() {
        return FileData;
    }

    public void setFileData(String FileData) {
        this.FileData = FileData;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String ContentType) {
        this.ContentType = ContentType;
    }
    private String subBandNameX;
    private String bandNameX;
    private String desigNameX;
    private String appId;
    private String empContract;
    private String contract_type;
    private String contractEmployeeId;
    private String contract_employee_id;

    public String getContract_employee_id() {
        return contract_employee_id;
    }

    public void setContract_employee_id(String contract_employee_id) {
        this.contract_employee_id = contract_employee_id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    private String employeeCategory;
    private String empCategory;
    private String createdDate;
    private Object candidateNameSearch;//new
    private Object candidateRefNumberSearch;
    private Object structureNameSearch;
    private Object practiceGroupSearch;
    private Object statusSearch;
    public String rrfRes;
    private int dataCount;
    private String bankCity;
    private String bankCountry;
    private String bankRegion;
    private String gender;
    private String fatherName;
    private String maritalStatus;
    private String dateOfMarriage;
    private String spouseName;
    private String placeOfBirth;
    private String religion;
    private String motherTongue;
    private String nationality;
    private String presentAddress;
    private String permanentAddress;
    private String phoneNumberPresent;
    private String phoneNumberPermanent;
    private String empFileName;
    private String empFileType;
    private String empStatus;
    private String workEmail;

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getEmpFileType() {
        return empFileType;
    }

    public void setEmpFileType(String empFileType) {
        this.empFileType = empFileType;
    }
    private String empAddPfFileName;
    private String empAddPfFileType;
    private String empAddPfFileId;
    private String empAdharFileName;
    private String empAdharFileType;
    private String empAdharFileId;
    private String empUanPfFileName;
    private String empUanPfFileType;
    private String empUanPfFileId;
    private String bloodGroup;
    private String bankAccType;
    private String nameInBankRecords;
    private String bankName;
    private String accountNumber;
    private String branch;
    private String bankFileName;
    private String bankFileType;
    private String empSignatureFileName;
    private String empSignatureFileType;
    private String empSignatureFileid;
    private MultipartFile empSignatureFile;

    public String getEmpSignatureFileName() {
        return empSignatureFileName;
    }

    public void setEmpSignatureFileName(String empSignatureFileName) {
        this.empSignatureFileName = empSignatureFileName;
    }

    public String getEmpSignatureFileType() {
        return empSignatureFileType;
    }

    public void setEmpSignatureFileType(String empSignatureFileType) {
        this.empSignatureFileType = empSignatureFileType;
    }

    public String getEmpSignatureFileid() {
        return empSignatureFileid;
    }

    public void setEmpSignatureFileid(String empSignatureFileid) {
        this.empSignatureFileid = empSignatureFileid;
    }

    public MultipartFile getEmpSignatureFile() {
        return empSignatureFile;
    }

    public void setEmpSignatureFile(MultipartFile empSignatureFile) {
        this.empSignatureFile = empSignatureFile;
    }
    
    public String getEmpAdharFileName() {
        return empAdharFileName;
    }

    public void setEmpAdharFileName(String empAdharFileName) {
        this.empAdharFileName = empAdharFileName;
    }

    public String getEmpAdharFileType() {
        return empAdharFileType;
    }

    public void setEmpAdharFileType(String empAdharFileType) {
        this.empAdharFileType = empAdharFileType;
    }

    public String getEmpAdharFileId() {
        return empAdharFileId;
    }

    public void setEmpAdharFileId(String empAdharFileId) {
        this.empAdharFileId = empAdharFileId;
    }

    public String getBankFileType() {
        return bankFileType;
    }

    public void setBankFileType(String bankFileType) {
        this.bankFileType = bankFileType;
    }
    public String CandidateRefPrefix = "DTL";
    private String jobporname;
    private String vendorname;
    private int statusToInt;
    private String sourceofhire;
    //new 
    public static final int JF_INITIATED = 1;
    public long password;
    public static final String JOINING_FORMALITY_NEWCANDIDATE_GROUPID = "18";// (New Employee -  OJF) in Groups table
    public static final String JF_MODULE_CODE = "JF";
    public static final String JF_X_PROOFNAME = "xProof";
    public static final String JF_P2_PROOFNAME = "p2Proof";
    public static final String JF_GRAD_PROOFNAME = "gradProof";
    public static final String JF_PGRAD_PROOFNAME = "pgradProof";
    public static final String JF_OTHERS_PROOFNAME = "otherProof";
    public static final String JF_BANK_PROOFNAME = "bankProof";
    public static final String JF_DL_PROOFNAME = "driveLicProof";
    public static final String JF_PASSPORT_PROOFNAME = "passportProof";
    public static final String JF_JOINERPHOTO_PROOFNAME = "joinerPhotoProof";
    public static final String JF_JOINERADDRESS_PROOFNAME = "joinerAddressProof";
    public static final String JF_JOINERADHAR_PROOFNAME = "joinerAdharProof";
    public static final String JF_JOINERUAN_PROOFNAME = "joinerUanProof";
    public static final String JF_JOINERSIGNATURE_NAME = "joinerSignatureProof";
    public static final String JF_Education = "Education";
    public static final String JF_Certification = "Certification";

    public MultipartFile getCanChequeLeaf() {
        return CanChequeLeaf;
    }

    public void setCanChequeLeaf(MultipartFile CanChequeLeaf) {
        this.CanChequeLeaf = CanChequeLeaf;
    }

    public MultipartFile getEmpPhotoUpload() {
        return EmpPhotoUpload;
    }

    public void setEmpPhotoUpload(MultipartFile EmpPhotoUpload) {
        this.EmpPhotoUpload = EmpPhotoUpload;
    }

    public MultipartFile getEmpAddProof() {
        return EmpAddProof;
    }

    public void setEmpAddProof(MultipartFile EmpAddProof) {
        this.EmpAddProof = EmpAddProof;
    }

    public String getPreviousPfNumber() {
        return previousPfNumber;
    }

    public void setPreviousPfNumber(String previousPfNumber) {
        this.previousPfNumber = previousPfNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getJoinerEmpId() {
        return joinerEmpId;
    }

    public void setJoinerEmpId(String joinerEmpId) {
        this.joinerEmpId = joinerEmpId;
    }

    public String getEmpBillable() {
        return empBillable;
    }

    public void setEmpBillable(String empBillable) {
        this.empBillable = empBillable;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getJoiningLocation() {
        return joiningLocation;
    }

    public void setJoiningLocation(String joiningLocation) {
        this.joiningLocation = joiningLocation;
    }

    public String getEmployeementType() {
        return employeementType;
    }

    public void setEmployeementType(String employeementType) {
        this.employeementType = employeementType;
    }

    public String getWorkMailid() {
        return workMailid;
    }

    public void setWorkMailid(String workMailid) {
        this.workMailid = workMailid;
    }

    public String getCandidateRefPrefix() {
        return CandidateRefPrefix;
    }

    public void setCandidateRefPrefix(String CandidateRefPrefix) {
        this.CandidateRefPrefix = CandidateRefPrefix;
    }

    //new
    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public String getSourceofhire() {
        return sourceofhire;
    }

    public void setSourceofhire(String sourceofhire) {
        this.sourceofhire = sourceofhire;
    }

    public int getStatusToInt() {
        return statusToInt;
    }

    public void setStatusToInt(int statusToInt) {
        this.statusToInt = statusToInt;
    }

    public String getJobporname() {
        return jobporname;
    }

    public void setJobporname(String jobporname) {
        this.jobporname = jobporname;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    /**
     * @return the jfId
     */
    public String getJfId() {
        return jfId;
    }

    /**
     * @param jfId the jfId to set
     */
    public void setJfId(String jfId) {
        this.jfId = jfId;
    }

    /**
     * @return the emloyeeNumber
     */
    public String getEmloyeeNumber() {
        return emloyeeNumber;
    }

    /**
     * @param emloyeeNumber the emloyeeNumber to set
     */
    public void setEmloyeeNumber(String emloyeeNumber) {
        this.emloyeeNumber = emloyeeNumber;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the structureName
     */
    public String getStructureName() {
        return structureName;
    }

    /**
     * @param structureName the structureName to set
     */
    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    /**
     * @return the practiceGroup
     */
    public String getPracticeGroup() {
        return practiceGroup;
    }

    /**
     * @param practiceGroup the practiceGroup to set
     */
    public void setPracticeGroup(String practiceGroup) {
        this.practiceGroup = practiceGroup;
    }

    /**
     * @return the subPracticeGroup
     */
    public String getSubPracticeGroup() {
        return subPracticeGroup;
    }

    /**
     * @param subPracticeGroup the subPracticeGroup to set
     */
    public void setSubPracticeGroup(String subPracticeGroup) {
        this.subPracticeGroup = subPracticeGroup;
    }

    /**
     * @return the band
     */
    public String getBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(String band) {
        this.band = band;
    }

    /**
     * @return the subBand
     */
    public String getSubBand() {
        return subBand;
    }

    /**
     * @param subBand the subBand to set
     */
    public void setSubBand(String subBand) {
        this.subBand = subBand;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the subBandName
     */
    public String getSubBandName() {
        return subBandName;
    }

    /**
     * @param subBandName the subBandName to set
     */
    public void setSubBandName(String subBandName) {
        this.subBandName = subBandName;
    }

    /**
     * @return the bandName
     */
    public String getBandName() {
        return bandName;
    }

    /**
     * @param bandName the bandName to set
     */
    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    /**
     * @return the desigName
     */
    public String getDesigName() {
        return desigName;
    }

    /**
     * @param desigName the desigName to set
     */
    public void setDesigName(String desigName) {
        this.desigName = desigName;
    }

    /**
     * @return the personalEmailId1
     */
    public String getPersonalEmailId1() {
        return personalEmailId1;
    }

    /**
     * @param personalEmailId1 the personalEmailId1 to set
     */
    public void setPersonalEmailId1(String personalEmailId1) {
        this.personalEmailId1 = personalEmailId1;
    }

    /**
     * @return the personalEmailId2
     */
    public String getPersonalEmailId2() {
        return personalEmailId2;
    }

    /**
     * @param personalEmailId2 the personalEmailId2 to set
     */
    public void setPersonalEmailId2(String personalEmailId2) {
        this.personalEmailId2 = personalEmailId2;
    }

    /**
     * @return the reportingManager
     */
    public String getReportingManager() {
        return reportingManager;
    }

    /**
     * @param reportingManager the reportingManager to set
     */
    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the panNo
     */
    public String getPanNo() {
        return panNo;
    }

    /**
     * @param panNo the panNo to set
     */
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    /**
     * @return the refnumber
     */
    public String getRefnumber() {
        return refnumber;
    }

    /**
     * @param refnumber the refnumber to set
     */
    public void setRefnumber(String refnumber) {
        this.refnumber = refnumber;
    }

    /**
     * @return the employeeType
     */
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * @param employeeType the employeeType to set
     */
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the trackNumber
     */
    public String getTrackNumber() {
        return trackNumber;
    }

    /**
     * @param trackNumber the trackNumber to set
     */
    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     * @return the structureId
     */
    public String getStructureId() {
        return structureId;
    }

    /**
     * @param structureId the structureId to set
     */
    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    /**
     * @return the practiceGroupId
     */
    public String getPracticeGroupId() {
        return practiceGroupId;
    }

    /**
     * @param practiceGroupId the practiceGroupId to set
     */
    public void setPracticeGroupId(String practiceGroupId) {
        this.practiceGroupId = practiceGroupId;
    }

    /**
     * @return the subPracticeGroupId
     */
    public String getSubPracticeGroupId() {
        return subPracticeGroupId;
    }

    /**
     * @param subPracticeGroupId the subPracticeGroupId to set
     */
    public void setSubPracticeGroupId(String subPracticeGroupId) {
        this.subPracticeGroupId = subPracticeGroupId;
    }

    /**
     * @return the subBandId
     */
    public String getSubBandId() {
        return subBandId;
    }

    /**
     * @param subBandId the subBandId to set
     */
    public void setSubBandId(String subBandId) {
        this.subBandId = subBandId;
    }

    /**
     * @return the bandId
     */
    public String getBandId() {
        return bandId;
    }

    /**
     * @param bandId the bandId to set
     */
    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    /**
     * @return the desigId
     */
    public String getDesigId() {
        return desigId;
    }

    /**
     * @param desigId the desigId to set
     */
    public void setDesigId(String desigId) {
        this.desigId = desigId;
    }

    /**
     * @return the mailTo
     */
    public String getMailTo() {
        return mailTo;
    }

    /**
     * @param mailTo the mailTo to set
     */
    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    /**
     * @return the mailCc
     */
    public String getMailCc() {
        return mailCc;
    }

    /**
     * @param mailCc the mailCc to set
     */
    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    /**
     * @return the mailTriggeredBy
     */
    public String getMailTriggeredBy() {
        return mailTriggeredBy;
    }

    /**
     * @param mailTriggeredBy the mailTriggeredBy to set
     */
    public void setMailTriggeredBy(String mailTriggeredBy) {
        this.mailTriggeredBy = mailTriggeredBy;
    }

    /**
     * @return the buttonName
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * @param buttonName the buttonName to set
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    /**
     * @return the buttonType
     */
    public String getButtonType() {
        return buttonType;
    }

    /**
     * @param buttonType the buttonType to set
     */
    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }

    /**
     * @return the subBandNameX
     */
    public String getSubBandNameX() {
        return subBandNameX;
    }

    /**
     * @param subBandNameX the subBandNameX to set
     */
    public void setSubBandNameX(String subBandNameX) {
        this.subBandNameX = subBandNameX;
    }

    /**
     * @return the bandNameX
     */
    public String getBandNameX() {
        return bandNameX;
    }

    /**
     * @param bandNameX the bandNameX to set
     */
    public void setBandNameX(String bandNameX) {
        this.bandNameX = bandNameX;
    }

    /**
     * @return the desigNameX
     */
    public String getDesigNameX() {
        return desigNameX;
    }

    /**
     * @param desigNameX the desigNameX to set
     */
    public void setDesigNameX(String desigNameX) {
        this.desigNameX = desigNameX;
    }

    /**
     * @return the empContract
     */
    public String getEmpContract() {
        return empContract;
    }

    /**
     * @param empContract the empContract to set
     */
    public void setEmpContract(String empContract) {
        this.empContract = empContract;
    }

    /**
     * @return the contract_type
     */
    public String getContract_type() {
        return contract_type;
    }

    /**
     * @param contract_type the contract_type to set
     */
    public void setContract_type(String contract_type) {
        this.contract_type = contract_type;
    }

    /**
     * @return the contractEmployeeId
     */
    public String getContractEmployeeId() {
        return contractEmployeeId;
    }

    /**
     * @param contractEmployeeId the contractEmployeeId to set
     */
    public void setContractEmployeeId(String contractEmployeeId) {
        this.contractEmployeeId = contractEmployeeId;
    }

    /**
     * @return the employeeCategory
     */
    public String getEmployeeCategory() {
        return employeeCategory;
    }

    /**
     * @param employeeCategory the employeeCategory to set
     */
    public void setEmployeeCategory(String employeeCategory) {
        this.employeeCategory = employeeCategory;
    }

    /**
     * @return the empCategory
     */
    public String getEmpCategory() {
        return empCategory;
    }

    /**
     * @param empCategory the empCategory to set
     */
    public void setEmpCategory(String empCategory) {
        this.empCategory = empCategory;
    }

    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setCandidateNameSearch(String candidateName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.candidateNameSearch = candidateName;
    }

    public void setCandidateRefNumberSearch(String candidateRefNumber) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        candidateRefNumberSearch = candidateRefNumber;
    }

    public void setStructureNameSearch(String companyStructure) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        structureNameSearch = companyStructure;
    }

    public void setPracticeGroupSearch(String practiceGroup) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        practiceGroupSearch = practiceGroup;
    }

    public void setStatusSearch(String status) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        statusSearch = status;
    }

    /**
     * @return the candidateNameSearch
     */
    public Object getCandidateNameSearch() {
        return candidateNameSearch;
    }

    /**
     * @param candidateNameSearch the candidateNameSearch to set
     */
    public void setCandidateNameSearch(Object candidateNameSearch) {
        this.candidateNameSearch = candidateNameSearch;
    }

    /**
     * @return the candidateRefNumberSearch
     */
    public Object getCandidateRefNumberSearch() {
        return candidateRefNumberSearch;
    }

    /**
     * @param candidateRefNumberSearch the candidateRefNumberSearch to set
     */
    public void setCandidateRefNumberSearch(Object candidateRefNumberSearch) {
        this.candidateRefNumberSearch = candidateRefNumberSearch;
    }

    /**
     * @return the structureNameSearch
     */
    public Object getStructureNameSearch() {
        return structureNameSearch;
    }

    /**
     * @param structureNameSearch the structureNameSearch to set
     */
    public void setStructureNameSearch(Object structureNameSearch) {
        this.structureNameSearch = structureNameSearch;
    }

    /**
     * @return the practiceGroupSearch
     */
    public Object getPracticeGroupSearch() {
        return practiceGroupSearch;
    }

    /**
     * @param practiceGroupSearch the practiceGroupSearch to set
     */
    public void setPracticeGroupSearch(Object practiceGroupSearch) {
        this.practiceGroupSearch = practiceGroupSearch;
    }

    /**
     * @return the statusSearch
     */
    public Object getStatusSearch() {
        return statusSearch;
    }

    /**
     * @param statusSearch the statusSearch to set
     */
    public void setStatusSearch(Object statusSearch) {
        this.statusSearch = statusSearch;
    }

    /**
     * @return the rrfRes
     */
    public Object getRrfRes() {
        return rrfRes;
    }

    /**
     * @param rrfRes the rrfRes to set
     */
    public void setRrfRes(String rrfRes) {
        this.rrfRes = rrfRes;
    }

    /**
     * @return the dataCount
     */
    public int getDataCount() {
        return dataCount;
    }

    /**
     * @param dataCount the dataCount to set
     */
    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    /**
     * @return the bankCity
     */
    public String getBankCity() {
        return bankCity;
    }

    /**
     * @param bankCity the bankCity to set
     */
    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    /**
     * @return the bankCountry
     */
    public String getBankCountry() {
        return bankCountry;
    }
//
//    /**
//     * @param bankCountry the bankCountry to set
//     */

    public void setBankCountry(String bankCountry) {
        this.bankCountry = bankCountry;
    }
//
//    /**
//     * @return the bankRegion
//     */

    public String getBankRegion() {
        return bankRegion;
    }
//
//    /**
//     * @param bankRegion the bankRegion to set
//     */

    public void setBankRegion(String bankRegion) {
        this.bankRegion = bankRegion;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the fatherName
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * @param fatherName the fatherName to set
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    /**
     * @return the maritalStatus
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus the maritalStatus to set
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return the dateOfMarriage
     */
    public String getDateOfMarriage() {
        return dateOfMarriage;
    }

    /**
     * @param dateOfMarriage the dateOfMarriage to set
     */
    public void setDateOfMarriage(String dateOfMarriage) {
        this.dateOfMarriage = dateOfMarriage;
    }

    /**
     * @return the spouseName
     */
    public String getSpouseName() {
        return spouseName;
    }

    /**
     * @param spouseName the spouseName to set
     */
    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    /**
     * @return the placeOfBirth
     */
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * @param placeOfBirth the placeOfBirth to set
     */
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return the motherTongue
     */
    public String getMotherTongue() {
        return motherTongue;
    }

    /**
     * @param motherTongue the motherTongue to set
     */
    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the presentAddress
     */
    public String getPresentAddress() {
        return presentAddress;
    }

    /**
     * @param presentAddress the presentAddress to set
     */
    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    /**
     * @return the permanentAddress
     */
    public String getPermanentAddress() {
        return permanentAddress;
    }

    /**
     * @param permanentAddress the permanentAddress to set
     */
    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    /**
     * @return the phoneNumberPresent
     */
    public String getPhoneNumberPresent() {
        return phoneNumberPresent;
    }

    /**
     * @param phoneNumberPresent the phoneNumberPresent to set
     */
    public void setPhoneNumberPresent(String phoneNumberPresent) {
        this.phoneNumberPresent = phoneNumberPresent;
    }

    /**
     * @return the phoneNumberPermanent
     */
    public String getPhoneNumberPermanent() {
        return phoneNumberPermanent;
    }

    /**
     * @param phoneNumberPermanent the phoneNumberPermanent to set
     */
    public void setPhoneNumberPermanent(String phoneNumberPermanent) {
        this.phoneNumberPermanent = phoneNumberPermanent;
    }

    /**
     * @return the empFileName
     */
    public String getEmpFileName() {
        return empFileName;
    }

    /**
     * @param empFileName the empFileName to set
     */
    public void setEmpFileName(String empFileName) {
        this.empFileName = empFileName;
    }

    /**
     * @return the empAddPfFileName
     */
    public String getEmpAddPfFileName() {
        return empAddPfFileName;
    }

    /**
     * @param empAddPfFileName the empAddPfFileName to set
     */
    public void setEmpAddPfFileName(String empAddPfFileName) {
        this.empAddPfFileName = empAddPfFileName;
    }

    /**
     * @return the empAddPfFileType
     */
    public String getEmpAddPfFileType() {
        return empAddPfFileType;
    }

    /**
     * @param empAddPfFileType the empAddPfFileType to set
     */
    public void setEmpAddPfFileType(String empAddPfFileType) {
        this.empAddPfFileType = empAddPfFileType;
    }

    /**
     * @return the empAddPfFileId
     */
    public String getEmpAddPfFileId() {
        return empAddPfFileId;
    }

    /**
     * @param empAddPfFileId the empAddPfFileId to set
     */
    public void setEmpAddPfFileId(String empAddPfFileId) {
        this.empAddPfFileId = empAddPfFileId;
    }

    /**
     * @return the bloodGroup
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * @param bloodGroup the bloodGroup to set
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * @return the bankAccType
     */
    public String getBankAccType() {
        return bankAccType;
    }

    /**
     * @param bankAccType the bankAccType to set
     */
    public void setBankAccType(String bankAccType) {
        this.bankAccType = bankAccType;
    }

    /**
     * @return the nameInBankRecords
     */
    public String getNameInBankRecords() {
        return nameInBankRecords;
    }

    /**
     * @param nameInBankRecords the nameInBankRecords to set
     */
    public void setNameInBankRecords(String nameInBankRecords) {
        this.nameInBankRecords = nameInBankRecords;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * @return the bankFileName
     */
    public String getBankFileName() {
        return bankFileName;
    }

    /**
     * @param bankFileName the bankFileName to set
     */
    public void setBankFileName(String bankFileName) {
        this.bankFileName = bankFileName;
    }
    //new properties
    private String[] degree;
    private String degreeX;
    private String[] qualification;
    private String[] qualification1;
    private String qualificationX;
    private String[] institution;
    private String[] institution1;
    private String institutionX;
    private String[] university;
    private String[] university1;
    private String universityX;
    private String[] percentage;
    private String otherInstitute;
    private String otheruniversity;
    private String percentageX;
    private String[] remarks;
    private String remarksX;
    private String attachmentX;
    private String[] attachmentXY;
    private String[] educationId;
    private String educationIdX;
    private String[] year_of_passing;
    private String year_of_passingX;
//    public FormFile attachment_1;
//    public FormFile attachment_2;
//    public FormFile attachment_3;
//    public FormFile attachment_4;
//    public FormFile attachment_5;
//    public FormFile attachment_6;
//    public FormFile attachment_7;
//    public FormFile attachment_8;
//    public FormFile attachment_9;
//    public FormFile attachment_10;
    private String universityName;
    private String instituteName;
    private String degreeVal;
    private String qualificationName;
    private String[] cert_qualification;
    private String cert_qualificationX;
    private String[] cert_institution;
    private String cert_institutionX;
    private String[] cert_percentage;
    private String cert_percentageX;
    private String[] cert_remarks;
    private String cert_remarksX;
    private String cert_attachmentX;
    private String exp_attachmentX;
    private String[] cert_attachmentXY;
    private String[] exp_attachmentXY;
    private String[] certificationId;
    private String certificationIdX;
    private String[] cert_year_of_passing;
    private String cert_year_of_passingX;
    private String[] educationalQualificationFileAttachmentCount;
//    public MultipartFile cert_attachment_1;
//    public MultipartFile cert_attachment_2;
//    public MultipartFile cert_attachment_3;
//    public MultipartFile cert_attachment_4;
//    public MultipartFile cert_attachment_5;
//    public MultipartFile cert_attachment_6;
//    public FormFile cert_attachment_7;
//    public FormFile cert_attachment_8;
//    public FormFile cert_attachment_9;
//    public FormFile cert_attachment_10;
    private String[] skill_category;
    private String[] skill_category1;
    private String[] stream;
    private String[] skill;
    private String[] skill_type;
    private String[] skill_year;
    private String[] skill_month;
    private String[] skillId;
    private String skill_categoryX;
    private String streamX;
    private String skillX;
    private String skill_typeX;
    private String skill_yearX;
    private String skill_monthX;
    private String skillIdX;
    private String skillName;
    private String streamName;
    private String[] namePrevEmp;
    private String namePrevEmpDb;
// modified================== CER_TR_deleted property
    private int[] CER_TR_deleted = new int[10];
    private int[] EDU_TR_deleted;
    public int EDU_TR_deletedX;
    private int[] SK_TR_deleted;
    private int SK_TR_deletedX;
    private int[] EMP_TR_deleted;
    private int EMP_TR_deletedX;
    private int[] DEP_TR_deleted;
    private int[] PASS_TR_deleted;
    private int[] EMERGENCY_TR_deleted;
    private int EMERGENCY_TR_deletedX;
    private String passportProofID;
    private String dlProofID;
    private String[] referenceNumber;
    private String[] placeOfIssue;
    private String[] entries;
    private String referenceNumberDb;
    private String placeOfIssueDb;
    private String entriesDb;
    private String[] passportNumber;
    private String[] passDateOfIssue;
    private String[] passPlaceOfIssue;
    private String[] passDateOfExpiry;
    private String[] passportId;
    private String passportNumberX;
    private String passDateOfIssueX;
    private String passPlaceOfIssueX;
    private String passDateOfExpiryX;
    private String passportIdX;
    private String passAttachmentX;
    private String[] passAttachmentXY;
//    public FormFile passAttachment_1;
//    public FormFile passAttachment_2;
//    public FormFile passAttachment_3;
//    public FormFile passAttachment_4;
//    public FormFile passAttachment_5;
//    public FormFile passAttachment_6;
//    public FormFile passAttachment_7;
//    public FormFile passAttachment_8;
//    public FormFile passAttachment_9;
//    public FormFile passAttachment_10;
    private String passport_number;
    private String passport_id;
    private String rrfId;
    private String rrfName;
    private String rrf_id;
//    private String rrf;
//
//    public String getRrf() {
//        return rrf;
//    }
//
//    public void setRrf(String rrf) {
//        this.rrf = rrf;
//    }
    private String[] emergencyId;
    private String[] relationship;
    private String[] home_phone_number;
    private String[] mobile_number;
    private String[] work_phone_number;
    private String[] name;
    private String emergencyIdX;
    private String relationshipX;
    private String home_phone_numberX;
    private String mobile_numberX;
    private String work_phone_numberX;
    private String nameX;

    public String getOtherInstitute() {
        return otherInstitute;
    }

    public void setOtherInstitute(String otherInstitute) {
        this.otherInstitute = otherInstitute;
    }

    public String getOtheruniversity() {
        return otheruniversity;
    }

    public void setOtheruniversity(String otheruniversity) {
        this.otheruniversity = otheruniversity;
    }

    /**
     * @return the degree
     */
    public String getJfEduProofIdDb() {
        return jfEduProofIdDb;
    }

    public void setJfEduProofIdDb(String jfEduProofIdDb) {
        this.jfEduProofIdDb = jfEduProofIdDb;
    }

    public String[] getDegree() {
        return degree;
    }

    /**
     * @param degree the degree to set
     */
    public void setDegree(String[] degree) {
        this.degree = degree;
    }

    /**
     * @return the degreeX
     */
    public String getDegreeX() {
        return degreeX;
    }

    /**
     * @param degreeX the degreeX to set
     */
    public void setDegreeX(String degreeX) {
        this.degreeX = degreeX;
    }

    /**
     * @return the qualification
     */
    public String[] getQualification() {
        return qualification;
    }

    /**
     * @param qualification the qualification to set
     */
    public void setQualification(String[] qualification) {
        this.qualification = qualification;
    }

    /**
     * @return the qualification1
     */
    public String[] getQualification1() {
        return qualification1;
    }

    /**
     * @param qualification1 the qualification1 to set
     */
    public void setQualification1(String[] qualification1) {
        this.qualification1 = qualification1;
    }

    /**
     * @return the qualificationX
     */
    public String getQualificationX() {
        return qualificationX;
    }

    /**
     * @param qualificationX the qualificationX to set
     */
    public void setQualificationX(String qualificationX) {
        this.qualificationX = qualificationX;
    }

    /**
     * @return the institution
     */
    public String[] getInstitution() {
        return institution;
    }

    /**
     * @param institution the institution to set
     */
    public void setInstitution(String[] institution) {
        this.institution = institution;
    }

    /**
     * @return the institution1
     */
    public String[] getInstitution1() {
        return institution1;
    }

    /**
     * @param institution1 the institution1 to set
     */
    public void setInstitution1(String[] institution1) {
        this.institution1 = institution1;
    }

    /**
     * @return the institutionX
     */
    public String getInstitutionX() {
        return institutionX;
    }

    /**
     * @param institutionX the institutionX to set
     */
    public void setInstitutionX(String institutionX) {
        this.institutionX = institutionX;
    }

    /**
     * @return the university
     */
    public String[] getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(String[] university) {
        this.university = university;
    }

    /**
     * @return the university1
     */
    public String[] getUniversity1() {
        return university1;
    }

    /**
     * @param university1 the university1 to set
     */
    public void setUniversity1(String[] university1) {
        this.university1 = university1;
    }

    /**
     * @return the universityX
     */
    public String getUniversityX() {
        return universityX;
    }

    /**
     * @param universityX the universityX to set
     */
    public void setUniversityX(String universityX) {
        this.universityX = universityX;
    }

    /**
     * @return the percentage
     */
    public String[] getPercentage() {
        return percentage;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(String[] percentage) {
        this.percentage = percentage;
    }

    /**
     * @return the percentageX
     */
    public String getPercentageX() {
        return percentageX;
    }

    /**
     * @param percentageX the percentageX to set
     */
    public void setPercentageX(String percentageX) {
        this.percentageX = percentageX;
    }

    /**
     * @return the remarks
     */
    public String[] getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String[] remarks) {
        this.remarks = remarks;
    }

    /**
     * @return the remarksX
     */
    public String getRemarksX() {
        return remarksX;
    }

    /**
     * @param remarksX the remarksX to set
     */
    public void setRemarksX(String remarksX) {
        this.remarksX = remarksX;
    }

    /**
     * @return the attachmentX
     */
    public String getAttachmentX() {
        return attachmentX;
    }

    /**
     * @param attachmentX the attachmentX to set
     */
    public void setAttachmentX(String attachmentX) {
        this.attachmentX = attachmentX;
    }

    /**
     * @return the attachmentXY
     */
    public String[] getAttachmentXY() {
        return attachmentXY;
    }

    /**
     * @param attachmentXY the attachmentXY to set
     */
    public void setAttachmentXY(String[] attachmentXY) {
        this.attachmentXY = attachmentXY;
    }

    /**
     * @return the educationId
     */
    public String[] getEducationId() {
        return educationId;
    }

    /**
     * @param educationId the educationId to set
     */
    public void setEducationId(String[] educationId) {
        this.educationId = educationId;
    }

    /**
     * @return the educationIdX
     */
    public String getEducationIdX() {
        return educationIdX;
    }

    /**
     * @param educationIdX the educationIdX to set
     */
    public void setEducationIdX(String educationIdX) {
        this.educationIdX = educationIdX;
    }

    /**
     * @return the year_of_passing
     */
    public String[] getYear_of_passing() {
        return year_of_passing;
    }

    /**
     * @param year_of_passing the year_of_passing to set
     */
    public void setYear_of_passing(String[] year_of_passing) {
        this.year_of_passing = year_of_passing;
    }

    /**
     * @return the year_of_passingX
     */
    public String getYear_of_passingX() {
        return year_of_passingX;
    }

    /**
     * @param year_of_passingX the year_of_passingX to set
     */
    public void setYear_of_passingX(String year_of_passingX) {
        this.year_of_passingX = year_of_passingX;
    }

    /**
     * @return the universityName
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * @param universityName the universityName to set
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * @return the instituteName
     */
    public String getInstituteName() {
        return instituteName;
    }

    /**
     * @param instituteName the instituteName to set
     */
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    /**
     * @return the degreeVal
     */
    public String getDegreeVal() {
        return degreeVal;
    }

    /**
     * @param degreeVal the degreeVal to set
     */
    public void setDegreeVal(String degreeVal) {
        this.degreeVal = degreeVal;
    }

    /**
     * @return the qualificationName
     */
    public String getQualificationName() {
        return qualificationName;
    }

    /**
     * @param qualificationName the qualificationName to set
     */
    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    /**
     * @return the cert_qualification
     */
    public String[] getCert_qualification() {
        return cert_qualification;
    }

    /**
     * @param cert_qualification the cert_qualification to set
     */
    public void setCert_qualification(String[] cert_qualification) {
        this.cert_qualification = cert_qualification;
    }

    /**
     * @return the cert_qualificationX
     */
    public String getCert_qualificationX() {
        return cert_qualificationX;
    }

    /**
     * @param cert_qualificationX the cert_qualificationX to set
     */
    public void setCert_qualificationX(String cert_qualificationX) {
        this.cert_qualificationX = cert_qualificationX;
    }

    /**
     * @return the cert_institution
     */
    public String[] getCert_institution() {
        return cert_institution;
    }

    /**
     * @param cert_institution the cert_institution to set
     */
    public void setCert_institution(String[] cert_institution) {
        this.cert_institution = cert_institution;
    }

    /**
     * @return the cert_institutionX
     */
    public String getCert_institutionX() {
        return cert_institutionX;
    }

    /**
     * @param cert_institutionX the cert_institutionX to set
     */
    public void setCert_institutionX(String cert_institutionX) {
        this.cert_institutionX = cert_institutionX;
    }

    /**
     * @return the cert_percentage
     */
    public String[] getCert_percentage() {
        return cert_percentage;
    }

    /**
     * @param cert_percentage the cert_percentage to set
     */
    public void setCert_percentage(String[] cert_percentage) {
        this.cert_percentage = cert_percentage;
    }

    /**
     * @return the cert_percentageX
     */
    public String getCert_percentageX() {
        return cert_percentageX;
    }

    /**
     * @param cert_percentageX the cert_percentageX to set
     */
    public void setCert_percentageX(String cert_percentageX) {
        this.cert_percentageX = cert_percentageX;
    }

    /**
     * @return the cert_remarks
     */
    public String[] getCert_remarks() {
        return cert_remarks;
    }

    /**
     * @param cert_remarks the cert_remarks to set
     */
    public void setCert_remarks(String[] cert_remarks) {
        this.cert_remarks = cert_remarks;
    }

    /**
     * @return the cert_remarksX
     */
    public String getCert_remarksX() {
        return cert_remarksX;
    }

    /**
     * @param cert_remarksX the cert_remarksX to set
     */
    public void setCert_remarksX(String cert_remarksX) {
        this.cert_remarksX = cert_remarksX;
    }

    /**
     * @return the cert_attachmentX
     */
    public String getCert_attachmentX() {
        return cert_attachmentX;
    }

    /**
     * @param cert_attachmentX the cert_attachmentX to set
     */
    public void setCert_attachmentX(String cert_attachmentX) {
        this.cert_attachmentX = cert_attachmentX;
    }

    /**
     * @return the cert_attachmentXY
     */
    public String[] getCert_attachmentXY() {
        return cert_attachmentXY;
    }

    /**
     * @param cert_attachmentXY the cert_attachmentXY to set
     */
    public void setCert_attachmentXY(String[] cert_attachmentXY) {
        this.cert_attachmentXY = cert_attachmentXY;
    }

    /**
     * @return the certificationId
     */
    public String[] getCertificationId() {
        return certificationId;
    }

    /**
     * @param certificationId the certificationId to set
     */
    public void setCertificationId(String[] certificationId) {
        this.certificationId = certificationId;
    }

    /**
     * @return the certificationIdX
     */
    public String getCertificationIdX() {
        return certificationIdX;
    }

    /**
     * @param certificationIdX the certificationIdX to set
     */
    public void setCertificationIdX(String certificationIdX) {
        this.certificationIdX = certificationIdX;
    }

    /**
     * @return the cert_year_of_passing
     */
    public String[] getCert_year_of_passing() {
        return cert_year_of_passing;
    }

    /**
     * @param cert_year_of_passing the cert_year_of_passing to set
     */
    public void setCert_year_of_passing(String[] cert_year_of_passing) {
        this.cert_year_of_passing = cert_year_of_passing;
    }

    /**
     * @return the cert_year_of_passingX
     */
    public String getCert_year_of_passingX() {
        return cert_year_of_passingX;
    }

    /**
     * @param cert_year_of_passingX the cert_year_of_passingX to set
     */
    public void setCert_year_of_passingX(String cert_year_of_passingX) {
        this.cert_year_of_passingX = cert_year_of_passingX;
    }

    /**
     * @return the skill_category
     */
    public String[] getSkill_category() {
        return skill_category;
    }

    /**
     * @param skill_category the skill_category to set
     */
    public void setSkill_category(String[] skill_category) {
        this.skill_category = skill_category;
    }

    /**
     * @return the stream
     */
    public String[] getStream() {
        return stream;
    }

    /**
     * @param stream the stream to set
     */
    public void setStream(String[] stream) {
        this.stream = stream;
    }

    /**
     * @return the skill
     */
    public String[] getSkill() {
        return skill;
    }

    /**
     * @param skill the skill to set
     */
    public void setSkill(String[] skill) {
        this.skill = skill;
    }

    /**
     * @return the skill_type
     */
    public String[] getSkill_type() {
        return skill_type;
    }

    /**
     * @param skill_type the skill_type to set
     */
    public void setSkill_type(String[] skill_type) {
        this.skill_type = skill_type;
    }

    /**
     * @return the skill_year
     */
    public String[] getSkill_year() {
        return skill_year;
    }

    /**
     * @param skill_year the skill_year to set
     */
    public void setSkill_year(String[] skill_year) {
        this.skill_year = skill_year;
    }

    /**
     * @return the skill_month
     */
    public String[] getSkill_month() {
        return skill_month;
    }

    /**
     * @param skill_month the skill_month to set
     */
    public void setSkill_month(String[] skill_month) {
        this.skill_month = skill_month;
    }

    /**
     * @return the skillId
     */
    public String[] getSkillId() {
        return skillId;
    }

    /**
     * @param skillId the skillId to set
     */
    public void setSkillId(String[] skillId) {
        this.skillId = skillId;
    }

    /**
     * @return the skill_categoryX
     */
    public String getSkill_categoryX() {
        return skill_categoryX;
    }

    /**
     * @param skill_categoryX the skill_categoryX to set
     */
    public void setSkill_categoryX(String skill_categoryX) {
        this.skill_categoryX = skill_categoryX;
    }

    /**
     * @return the streamX
     */
    public String getStreamX() {
        return streamX;
    }

    /**
     * @param streamX the streamX to set
     */
    public void setStreamX(String streamX) {
        this.streamX = streamX;
    }

    /**
     * @return the skillX
     */
    public String getSkillX() {
        return skillX;
    }

    /**
     * @param skillX the skillX to set
     */
    public void setSkillX(String skillX) {
        this.skillX = skillX;
    }

    /**
     * @return the skill_typeX
     */
    public String getSkill_typeX() {
        return skill_typeX;
    }

    /**
     * @param skill_typeX the skill_typeX to set
     */
    public void setSkill_typeX(String skill_typeX) {
        this.skill_typeX = skill_typeX;
    }

    /**
     * @return the skill_yearX
     */
    public String getSkill_yearX() {
        return skill_yearX;
    }

    /**
     * @param skill_yearX the skill_yearX to set
     */
    public void setSkill_yearX(String skill_yearX) {
        this.skill_yearX = skill_yearX;
    }

    /**
     * @return the skill_monthX
     */
    public String getSkill_monthX() {
        return skill_monthX;
    }

    /**
     * @param skill_monthX the skill_monthX to set
     */
    public void setSkill_monthX(String skill_monthX) {
        this.skill_monthX = skill_monthX;
    }

    /**
     * @return the skillIdX
     */
    public String getSkillIdX() {
        return skillIdX;
    }

    /**
     * @param skillIdX the skillIdX to set
     */
    public void setSkillIdX(String skillIdX) {
        this.skillIdX = skillIdX;
    }

    /**
     * @return the skillName
     */
    public String getSkillName() {
        return skillName;
    }

    /**
     * @param skillName the skillName to set
     */
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    /**
     * @return the streamName
     */
    public String getStreamName() {
        return streamName;
    }

    /**
     * @param streamName the streamName to set
     */
    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    /**
     * @return the namePrevEmp
     */
    public String[] getNamePrevEmp() {
        return namePrevEmp;
    }

    /**
     * @param namePrevEmp the namePrevEmp to set
     */
    public void setNamePrevEmp(String[] namePrevEmp) {
        this.namePrevEmp = namePrevEmp;
    }

    /**
     * @return the namePrevEmpDb
     */
    public String getNamePrevEmpDb() {
        return namePrevEmpDb;
    }

    /**
     * @param namePrevEmpDb the namePrevEmpDb to set
     */
    public void setNamePrevEmpDb(String namePrevEmpDb) {
        this.namePrevEmpDb = namePrevEmpDb;
    }

    /**
     * @return the CER_TR_deleted
     */
    public int[] getCER_TR_deleted() {
        return CER_TR_deleted;
    }

    /**
     * @param CER_TR_deleted the CER_TR_deleted to set
     */
    public void setCER_TR_deleted(int[] CER_TR_deleted) {
        this.CER_TR_deleted = CER_TR_deleted;
    }

    /**
     * @return the EDU_TR_deleted
     */
    public int[] getEDU_TR_deleted() {
        return EDU_TR_deleted;
    }

    /**
     * @param EDU_TR_deleted the EDU_TR_deleted to set
     */
    public void setEDU_TR_deleted(int[] EDU_TR_deleted) {
        this.EDU_TR_deleted = EDU_TR_deleted;
    }

    /**
     * @return the SK_TR_deleted
     */
    public int[] getSK_TR_deleted() {
        return SK_TR_deleted;
    }

    /**
     * @param SK_TR_deleted the SK_TR_deleted to set
     */
    public void setSK_TR_deleted(int[] SK_TR_deleted) {
        this.SK_TR_deleted = SK_TR_deleted;
    }

    /**
     * @return the EMP_TR_deleted
     */
    public int[] getEMP_TR_deleted() {
        return EMP_TR_deleted;
    }

    /**
     * @param EMP_TR_deleted the EMP_TR_deleted to set
     */
    public void setEMP_TR_deleted(int[] EMP_TR_deleted) {
        this.EMP_TR_deleted = EMP_TR_deleted;
    }

    /**
     * @return the DEP_TR_deleted
     */
    public int[] getDEP_TR_deleted() {
        return DEP_TR_deleted;
    }

    /**
     * @param DEP_TR_deleted the DEP_TR_deleted to set
     */
    public void setDEP_TR_deleted(int[] DEP_TR_deleted) {
        this.DEP_TR_deleted = DEP_TR_deleted;
    }

    /**
     * @return the VISA_TR_deleted
     */
    public int[] getVISA_TR_deleted() {
        return VISA_TR_deleted;
    }

    /**
     * @param VISA_TR_deleted the VISA_TR_deleted to set
     */
    public void setVISA_TR_deleted(int[] VISA_TR_deleted) {
        this.VISA_TR_deleted = VISA_TR_deleted;
    }

    /**
     * @return the PASS_TR_deleted
     */
    public int[] getPASS_TR_deleted() {
        return PASS_TR_deleted;
    }

    /**
     * @param PASS_TR_deleted the PASS_TR_deleted to set
     */
    public void setPASS_TR_deleted(int[] PASS_TR_deleted) {
        this.PASS_TR_deleted = PASS_TR_deleted;
    }

    /**
     * @return the EMERGENCY_TR_deleted
     */
    public int[] getEMERGENCY_TR_deleted() {
        return EMERGENCY_TR_deleted;
    }

    /**
     * @param EMERGENCY_TR_deleted the EMERGENCY_TR_deleted to set
     */
    public void setEMERGENCY_TR_deleted(int[] EMERGENCY_TR_deleted) {
        this.EMERGENCY_TR_deleted = EMERGENCY_TR_deleted;
    }

    /**
     * @return the passportProofID
     */
    public String getPassportProofID() {
        return passportProofID;
    }

    /**
     * @param passportProofID the passportProofID to set
     */
    public void setPassportProofID(String passportProofID) {
        this.passportProofID = passportProofID;
    }

    /**
     * @return the dlProofID
     */
    public String getDlProofID() {
        return dlProofID;
    }

    /**
     * @param dlProofID the dlProofID to set
     */
    public void setDlProofID(String dlProofID) {
        this.dlProofID = dlProofID;
    }

    /**
     * @return the referenceNumber
     */
    public String[] getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * @param referenceNumber the referenceNumber to set
     */
    public void setReferenceNumber(String[] referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    /**
     * @return the placeOfIssue
     */
    public String[] getPlaceOfIssue() {
        return placeOfIssue;
    }

    /**
     * @param placeOfIssue the placeOfIssue to set
     */
    public void setPlaceOfIssue(String[] placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    /**
     * @return the entries
     */
    public String[] getEntries() {
        return entries;
    }

    /**
     * @param entries the entries to set
     */
    public void setEntries(String[] entries) {
        this.entries = entries;
    }

    /**
     * @return the referenceNumberDb
     */
    public String getReferenceNumberDb() {
        return referenceNumberDb;
    }

    /**
     * @param referenceNumberDb the referenceNumberDb to set
     */
    public void setReferenceNumberDb(String referenceNumberDb) {
        this.referenceNumberDb = referenceNumberDb;
    }

    /**
     * @return the placeOfIssueDb
     */
    public String getPlaceOfIssueDb() {
        return placeOfIssueDb;
    }

    /**
     * @param placeOfIssueDb the placeOfIssueDb to set
     */
    public void setPlaceOfIssueDb(String placeOfIssueDb) {
        this.placeOfIssueDb = placeOfIssueDb;
    }

    /**
     * @return the entriesDb
     */
    public String getEntriesDb() {
        return entriesDb;
    }

    /**
     * @param entriesDb the entriesDb to set
     */
    public void setEntriesDb(String entriesDb) {
        this.entriesDb = entriesDb;
    }

    /**
     * @return the passportNumber
     */
    public String[] getPassportNumber() {
        return passportNumber;
    }

    /**
     * @param passportNumber the passportNumber to set
     */
    public void setPassportNumber(String[] passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * @return the passDateOfIssue
     */
    public String[] getPassDateOfIssue() {
        return passDateOfIssue;
    }

    /**
     * @param passDateOfIssue the passDateOfIssue to set
     */
    public void setPassDateOfIssue(String[] passDateOfIssue) {
        this.passDateOfIssue = passDateOfIssue;
    }

    /**
     * @return the passPlaceOfIssue
     */
    public String[] getPassPlaceOfIssue() {
        return passPlaceOfIssue;
    }

    /**
     * @param passPlaceOfIssue the passPlaceOfIssue to set
     */
    public void setPassPlaceOfIssue(String[] passPlaceOfIssue) {
        this.passPlaceOfIssue = passPlaceOfIssue;
    }

    /**
     * @return the passDateOfExpiry
     */
    public String[] getPassDateOfExpiry() {
        return passDateOfExpiry;
    }

    /**
     * @param passDateOfExpiry the passDateOfExpiry to set
     */
    public void setPassDateOfExpiry(String[] passDateOfExpiry) {
        this.passDateOfExpiry = passDateOfExpiry;
    }

    /**
     * @return the passportId
     */
    public String[] getPassportId() {
        return passportId;
    }

    /**
     * @param passportId the passportId to set
     */
    public void setPassportId(String[] passportId) {
        this.passportId = passportId;
    }

    /**
     * @return the passportNumberX
     */
    public String getPassportNumberX() {
        return passportNumberX;
    }

    /**
     * @param passportNumberX the passportNumberX to set
     */
    public void setPassportNumberX(String passportNumberX) {
        this.passportNumberX = passportNumberX;
    }

    /**
     * @return the passDateOfIssueX
     */
    public String getPassDateOfIssueX() {
        return passDateOfIssueX;
    }

    /**
     * @param passDateOfIssueX the passDateOfIssueX to set
     */
    public void setPassDateOfIssueX(String passDateOfIssueX) {
        this.passDateOfIssueX = passDateOfIssueX;
    }

    /**
     * @return the passPlaceOfIssueX
     */
    public String getPassPlaceOfIssueX() {
        return passPlaceOfIssueX;
    }

    /**
     * @param passPlaceOfIssueX the passPlaceOfIssueX to set
     */
    public void setPassPlaceOfIssueX(String passPlaceOfIssueX) {
        this.passPlaceOfIssueX = passPlaceOfIssueX;
    }

    /**
     * @return the passDateOfExpiryX
     */
    public String getPassDateOfExpiryX() {
        return passDateOfExpiryX;
    }

    /**
     * @param passDateOfExpiryX the passDateOfExpiryX to set
     */
    public void setPassDateOfExpiryX(String passDateOfExpiryX) {
        this.passDateOfExpiryX = passDateOfExpiryX;
    }

    /**
     * @return the passportIdX
     */
    public String getPassportIdX() {
        return passportIdX;
    }

    /**
     * @param passportIdX the passportIdX to set
     */
    public void setPassportIdX(String passportIdX) {
        this.passportIdX = passportIdX;
    }

    /**
     * @return the passAttachmentX
     */
    public String getPassAttachmentX() {
        return passAttachmentX;
    }

    /**
     * @param passAttachmentX the passAttachmentX to set
     */
    public void setPassAttachmentX(String passAttachmentX) {
        this.passAttachmentX = passAttachmentX;
    }

    /**
     * @return the passAttachmentXY
     */
    public String[] getPassAttachmentXY() {
        return passAttachmentXY;
    }

    /**
     * @param passAttachmentXY the passAttachmentXY to set
     */
    public void setPassAttachmentXY(String[] passAttachmentXY) {
        this.passAttachmentXY = passAttachmentXY;
    }

    /**
     * @return the passport_number
     */
    public String getPassport_number() {
        return passport_number;
    }

    /**
     * @param passport_number the passport_number to set
     */
    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    /**
     * @return the passport_id
     */
    public String getPassport_id() {
        return passport_id;
    }

    /**
     * @param passport_id the passport_id to set
     */
    public void setPassport_id(String passport_id) {
        this.passport_id = passport_id;
    }

    /**
     * @return the rrfId
     */
    public String getRrfId() {
        return rrfId;
    }

    /**
     * @param rrfId the rrfId to set
     */
    public void setRrfId(String rrfId) {
        this.rrfId = rrfId;
    }

    /**
     * @return the rrfName
     */
    public String getRrfName() {
        return rrfName;
    }

    /**
     * @param rrfName the rrfName to set
     */
    public void setRrfName(String rrfName) {
        this.rrfName = rrfName;
    }

    /**
     * @return the rrf_id
     */
    public String getRrf_id() {
        return rrf_id;
    }

    /**
     * @param rrf_id the rrf_id to set
     */
    public void setRrf_id(String rrf_id) {
        this.rrf_id = rrf_id;
    }

    /**
     * @return the emergencyId
     */
    public String[] getEmergencyId() {
        return emergencyId;
    }

    /**
     * @param emergencyId the emergencyId to set
     */
    public void setEmergencyId(String[] emergencyId) {
        this.emergencyId = emergencyId;
    }

    /**
     * @return the relationship
     */
    public String[] getRelationship() {
        return relationship;
    }

    /**
     * @param relationship the relationship to set
     */
    public void setRelationship(String[] relationship) {
        this.relationship = relationship;
    }

    /**
     * @return the home_phone_number
     */
    public String[] getHome_phone_number() {
        return home_phone_number;
    }

    /**
     * @param home_phone_number the home_phone_number to set
     */
    public void setHome_phone_number(String[] home_phone_number) {
        this.home_phone_number = home_phone_number;
    }

    /**
     * @return the mobile_number
     */
    public String[] getMobile_number() {
        return mobile_number;
    }

    /**
     * @param mobile_number the mobile_number to set
     */
    public void setMobile_number(String[] mobile_number) {
        this.mobile_number = mobile_number;
    }

    /**
     * @return the work_phone_number
     */
    public String[] getWork_phone_number() {
        return work_phone_number;
    }

    /**
     * @param work_phone_number the work_phone_number to set
     */
    public void setWork_phone_number(String[] work_phone_number) {
        this.work_phone_number = work_phone_number;
    }

    /**
     * @return the name
     */
    public String[] getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String[] name) {
        this.name = name;
    }

    /**
     * @return the emergencyIdX
     */
    public String getEmergencyIdX() {
        return emergencyIdX;
    }

    /**
     * @param emergencyIdX the emergencyIdX to set
     */
    public void setEmergencyIdX(String emergencyIdX) {
        this.emergencyIdX = emergencyIdX;
    }

    /**
     * @return the relationshipX
     */
    public String getRelationshipX() {
        return relationshipX;
    }

    /**
     * @param relationshipX the relationshipX to set
     */
    public void setRelationshipX(String relationshipX) {
        this.relationshipX = relationshipX;
    }

    /**
     * @return the home_phone_numberX
     */
    public String getHome_phone_numberX() {
        return home_phone_numberX;
    }

    /**
     * @param home_phone_numberX the home_phone_numberX to set
     */
    public void setHome_phone_numberX(String home_phone_numberX) {
        this.home_phone_numberX = home_phone_numberX;
    }

    /**
     * @return the mobile_numberX
     */
    public String getMobile_numberX() {
        return mobile_numberX;
    }

    /**
     * @param mobile_numberX the mobile_numberX to set
     */
    public void setMobile_numberX(String mobile_numberX) {
        this.mobile_numberX = mobile_numberX;
    }

    /**
     * @return the work_phone_numberX
     */
    public String getWork_phone_numberX() {
        return work_phone_numberX;
    }

    /**
     * @param work_phone_numberX the work_phone_numberX to set
     */
    public void setWork_phone_numberX(String work_phone_numberX) {
        this.work_phone_numberX = work_phone_numberX;
    }

    /**
     * @return the nameX
     */
    public String getNameX() {
        return nameX;
    }

    /**
     * @param nameX the nameX to set
     */
    public void setNameX(String nameX) {
        this.nameX = nameX;
    }

    /**
     * @return the empref
     */
    public String getEmpref() {
        return empref;
    }

    /**
     * @param empref the empref to set
     */
    public void setEmpref(String empref) {
        this.empref = empref;
    }

    /**
     * @return the attachment_1
     */
    public MultipartFile getAttachment_1() {
        return attachment_1;
    }

    /**
     * @param attachment_1 the attachment_1 to set
     */
    public void setAttachment_1(MultipartFile attachment_1) {
        this.attachment_1 = attachment_1;
    }

    /**
     * @return the attachment_2
     */
    public MultipartFile getAttachment_2() {
        return attachment_2;
    }

    /**
     * @param attachment_2 the attachment_2 to set
     */
    public void setAttachment_2(MultipartFile attachment_2) {
        this.attachment_2 = attachment_2;
    }

    /**
     * @return the attachment_3
     */
    public MultipartFile getAttachment_3() {
        return attachment_3;
    }

    /**
     * @param attachment_3 the attachment_3 to set
     */
    public void setAttachment_3(MultipartFile attachment_3) {
        this.attachment_3 = attachment_3;
    }

    /**
     * @return the attachment_4
     */
    public MultipartFile getAttachment_4() {
        return attachment_4;
    }

    /**
     * @param attachment_4 the attachment_4 to set
     */
    public void setAttachment_4(MultipartFile attachment_4) {
        this.attachment_4 = attachment_4;
    }

    /**
     * @return the attachment_5
     */
    public MultipartFile getAttachment_5() {
        return attachment_5;
    }

    /**
     * @param attachment_5 the attachment_5 to set
     */
    public void setAttachment_5(MultipartFile attachment_5) {
        this.attachment_5 = attachment_5;
    }

    /**
     * @return the attachment_6
     */
    public MultipartFile getAttachment_6() {
        return attachment_6;
    }

    /**
     * @param attachment_6 the attachment_6 to set
     */
    public void setAttachment_6(MultipartFile attachment_6) {
        this.attachment_6 = attachment_6;
    }

    /**
     * @return the attachment_7
     */
    public MultipartFile getAttachment_7() {
        return attachment_7;
    }

    /**
     * @param attachment_7 the attachment_7 to set
     */
    public void setAttachment_7(MultipartFile attachment_7) {
        this.attachment_7 = attachment_7;
    }

    /**
     * @return the attachment_8
     */
    public MultipartFile getAttachment_8() {
        return attachment_8;
    }

    /**
     * @param attachment_8 the attachment_8 to set
     */
    public void setAttachment_8(MultipartFile attachment_8) {
        this.attachment_8 = attachment_8;
    }

    /**
     * @return the attachment_9
     */
    public MultipartFile getAttachment_9() {
        return attachment_9;
    }

    /**
     * @param attachment_9 the attachment_9 to set
     */
    public void setAttachment_9(MultipartFile attachment_9) {
        this.attachment_9 = attachment_9;
    }

    /**
     * @return the attachment_10
     */
    public MultipartFile getAttachment_10() {
        return attachment_10;
    }

    /**
     * @param attachment_10 the attachment_10 to set
     */
    public void setAttachment_10(MultipartFile attachment_10) {
        this.attachment_10 = attachment_10;
    }

    /**
     * @return the EDU_TR_deletedX
     */
    public int getEDU_TR_deletedX() {
        return EDU_TR_deletedX;
    }

    /**
     * @param EDU_TR_deletedX the EDU_TR_deletedX to set
     */
    public void setEDU_TR_deletedX(int EDU_TR_deletedX) {
        this.EDU_TR_deletedX = EDU_TR_deletedX;
    }

    /**
     * @return the cert_attachment_1
     */
    public MultipartFile getCert_attachment_1() {
        return cert_attachment_1;
    }

    /**
     * @param cert_attachment_1 the cert_attachment_1 to set
     */
    public void setCert_attachment_1(MultipartFile cert_attachment_1) {
        this.cert_attachment_1 = cert_attachment_1;
    }

    /**
     * @return the cert_attachment_2
     */
    public MultipartFile getCert_attachment_2() {
        return cert_attachment_2;
    }

    /**
     * @param cert_attachment_2 the cert_attachment_2 to set
     */
    public void setCert_attachment_2(MultipartFile cert_attachment_2) {
        this.cert_attachment_2 = cert_attachment_2;
    }

    /**
     * @return the cert_attachment_3
     */
    public MultipartFile getCert_attachment_3() {
        return cert_attachment_3;
    }

    /**
     * @param cert_attachment_3 the cert_attachment_3 to set
     */
    public void setCert_attachment_3(MultipartFile cert_attachment_3) {
        this.cert_attachment_3 = cert_attachment_3;
    }

    /**
     * @return the cert_attachment_4
     */
    public MultipartFile getCert_attachment_4() {
        return cert_attachment_4;
    }

    /**
     * @param cert_attachment_4 the cert_attachment_4 to set
     */
    public void setCert_attachment_4(MultipartFile cert_attachment_4) {
        this.cert_attachment_4 = cert_attachment_4;
    }

    /**
     * @return the cert_attachment_5
     */
    public MultipartFile getCert_attachment_5() {
        return cert_attachment_5;
    }

    /**
     * @param cert_attachment_5 the cert_attachment_5 to set
     */
    public void setCert_attachment_5(MultipartFile cert_attachment_5) {
        this.cert_attachment_5 = cert_attachment_5;
    }

    /**
     * @return the cert_attachment_6
     */
    public MultipartFile getCert_attachment_6() {
        return cert_attachment_6;
    }

    /**
     * @param cert_attachment_6 the cert_attachment_6 to set
     */
    public void setCert_attachment_6(MultipartFile cert_attachment_6) {
        this.cert_attachment_6 = cert_attachment_6;
    }

    /**
     * @return the cert_attachment_7
     */
    public MultipartFile getCert_attachment_7() {
        return cert_attachment_7;
    }

    /**
     * @param cert_attachment_7 the cert_attachment_7 to set
     */
    public void setCert_attachment_7(MultipartFile cert_attachment_7) {
        this.cert_attachment_7 = cert_attachment_7;
    }

    /**
     * @return the cert_attachment_8
     */
    public MultipartFile getCert_attachment_8() {
        return cert_attachment_8;
    }

    /**
     * @param cert_attachment_8 the cert_attachment_8 to set
     */
    public void setCert_attachment_8(MultipartFile cert_attachment_8) {
        this.cert_attachment_8 = cert_attachment_8;
    }

    /**
     * @return the cert_attachment_9
     */
    public MultipartFile getCert_attachment_9() {
        return cert_attachment_9;
    }

    /**
     * @param cert_attachment_9 the cert_attachment_9 to set
     */
    public void setCert_attachment_9(MultipartFile cert_attachment_9) {
        this.cert_attachment_9 = cert_attachment_9;
    }

    /**
     * @return the cert_attachment_10
     */
    public MultipartFile getCert_attachment_10() {
        return cert_attachment_10;
    }

    /**
     * @param cert_attachment_10 the cert_attachment_10 to set
     */
    public void setCert_attachment_10(MultipartFile cert_attachment_10) {
        this.cert_attachment_10 = cert_attachment_10;
    }

    /**
     * @return the SK_TR_deletedX
     */
    public int getSK_TR_deletedX() {
        return SK_TR_deletedX;
    }

    /**
     * @param SK_TR_deletedX the SK_TR_deletedX to set
     */
    public void setSK_TR_deletedX(int SK_TR_deletedX) {
        this.SK_TR_deletedX = SK_TR_deletedX;
    }

    /**
     * @return the jfPreEmpId
     */
    public String[] getJfPreEmpId() {
        return jfPreEmpId;
    }

    /**
     * @param jfPreEmpId the jfPreEmpId to set
     */
    public void setJfPreEmpId(String[] jfPreEmpId) {
        this.jfPreEmpId = jfPreEmpId;
    }

    /**
     * @return the nameAddPrevEmp
     */
    public String[] getNameAddPrevEmp() {
        return nameAddPrevEmp;
    }

    /**
     * @param nameAddPrevEmp the nameAddPrevEmp to set
     */
    public void setNameAddPrevEmp(String[] nameAddPrevEmp) {
        this.nameAddPrevEmp = nameAddPrevEmp;
    }

    /**
     * @return the dateOfJoin
     */
    public String[] getDateOfJoin() {
        return dateOfJoin;
    }

    /**
     * @param dateOfJoin the dateOfJoin to set
     */
    public void setDateOfJoin(String[] dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    /**
     * @return the desigOnJoin
     */
    public String[] getDesigOnJoin() {
        return desigOnJoin;
    }

    /**
     * @param desigOnJoin the desigOnJoin to set
     */
    public void setDesigOnJoin(String[] desigOnJoin) {
        this.desigOnJoin = desigOnJoin;
    }

    /**
     * @return the salaryOnJoin
     */
    public String[] getSalaryOnJoin() {
        return salaryOnJoin;
    }

    /**
     * @param salaryOnJoin the salaryOnJoin to set
     */
    public void setSalaryOnJoin(String[] salaryOnJoin) {
        this.salaryOnJoin = salaryOnJoin;
    }

    /**
     * @return the desigOnRel
     */
    public String[] getDesigOnRel() {
        return desigOnRel;
    }

    /**
     * @param desigOnRel the desigOnRel to set
     */
    public void setDesigOnRel(String[] desigOnRel) {
        this.desigOnRel = desigOnRel;
    }

    /**
     * @return the salOnRel
     */
    public String[] getSalOnRel() {
        return salOnRel;
    }

    /**
     * @param salOnRel the salOnRel to set
     */
    public void setSalOnRel(String[] salOnRel) {
        this.salOnRel = salOnRel;
    }

    /**
     * @return the dateOfRel
     */
    public String[] getDateOfRel() {
        return dateOfRel;
    }

    /**
     * @param dateOfRel the dateOfRel to set
     */
    public void setDateOfRel(String[] dateOfRel) {
        this.dateOfRel = dateOfRel;
    }

    public List getOtherProof() {
        return this.formFiles;
    }

    public void setOtherProof(int iIndex, MultipartFile formFile) {
        this.formFiles.add(formFile);
    }

    /**
     * @return the educationalQualificationFileAttachmentStatus
     */
    public String[] getEducationalQualificationFileAttachmentStatus() {
        return educationalQualificationFileAttachmentStatus;
    }

    /**
     * @param educationalQualificationFileAttachmentStatus the
     * educationalQualificationFileAttachmentStatus to set
     */
    public void setEducationalQualificationFileAttachmentStatus(String[] educationalQualificationFileAttachmentStatus) {
        this.educationalQualificationFileAttachmentStatus = educationalQualificationFileAttachmentStatus;
    }

    /**
     * @return the jfPreEmpIdX
     */
    public String getJfPreEmpIdX() {
        return jfPreEmpIdX;
    }

    /**
     * @param jfPreEmpIdX the jfPreEmpIdX to set
     */
    public void setJfPreEmpIdX(String jfPreEmpIdX) {
        this.jfPreEmpIdX = jfPreEmpIdX;
    }

    /**
     * @return the dateOfRelX
     */
    public String getDateOfRelX() {
        return dateOfRelX;
    }

    /**
     * @param dateOfRelX the dateOfRelX to set
     */
    public void setDateOfRelX(String dateOfRelX) {
        this.dateOfRelX = dateOfRelX;
    }

    /**
     * @return the salOnRelX
     */
    public String getSalOnRelX() {
        return salOnRelX;
    }

    /**
     * @param salOnRelX the salOnRelX to set
     */
    public void setSalOnRelX(String salOnRelX) {
        this.salOnRelX = salOnRelX;
    }

    /**
     * @return the desigOnRelX
     */
    public String getDesigOnRelX() {
        return desigOnRelX;
    }

    /**
     * @param desigOnRelX the desigOnRelX to set
     */
    public void setDesigOnRelX(String desigOnRelX) {
        this.desigOnRelX = desigOnRelX;
    }

    /**
     * @return the salaryOnJoinX
     */
    public String getSalaryOnJoinX() {
        return salaryOnJoinX;
    }

    /**
     * @param salaryOnJoinX the salaryOnJoinX to set
     */
    public void setSalaryOnJoinX(String salaryOnJoinX) {
        this.salaryOnJoinX = salaryOnJoinX;
    }

    /**
     * @return the desigOnJoinX
     */
    public String getDesigOnJoinX() {
        return desigOnJoinX;
    }

    /**
     * @param desigOnJoinX the desigOnJoinX to set
     */
    public void setDesigOnJoinX(String desigOnJoinX) {
        this.desigOnJoinX = desigOnJoinX;
    }

    /**
     * @return the dateOfJoinX
     */
    public String getDateOfJoinX() {
        return dateOfJoinX;
    }

    /**
     * @param dateOfJoinX the dateOfJoinX to set
     */
    public void setDateOfJoinX(String dateOfJoinX) {
        this.dateOfJoinX = dateOfJoinX;
    }

    public String getNameAddPrevEmpX() {
        return nameAddPrevEmpX;
    }

    /**
     * @param nameAddPrevEmpX the nameAddPrevEmpX to set
     */
    public void setNameAddPrevEmpX(String nameAddPrevEmpX) {
        this.nameAddPrevEmpX = nameAddPrevEmpX;
    }

    /**
     * @return the EMP_TR_deletedX
     */
    public int getEMP_TR_deletedX() {
        return EMP_TR_deletedX;
    }

    /**
     * @param EMP_TR_deletedX the EMP_TR_deletedX to set
     */
    public void setEMP_TR_deletedX(int EMP_TR_deletedX) {
        this.EMP_TR_deletedX = EMP_TR_deletedX;
    }

    /**
     * @return the dlNumber
     */
    public String getDlNumber() {
        return dlNumber;
    }

    /**
     * @param dlNumber the dlNumber to set
     */
    public void setDlNumber(String dlNumber) {
        this.dlNumber = dlNumber;
    }

    /**
     * @return the dlDateOfIssue
     */
    public String getDlDateOfIssue() {
        return dlDateOfIssue;
    }

    /**
     * @param dlDateOfIssue the dlDateOfIssue to set
     */
    public void setDlDateOfIssue(String dlDateOfIssue) {
        this.dlDateOfIssue = dlDateOfIssue;
    }

    /**
     * @return the dlDateOfExpiry
     */
    public String getDlDateOfExpiry() {
        return dlDateOfExpiry;
    }

    /**
     * @param dlDateOfExpiry the dlDateOfExpiry to set
     */
    public void setDlDateOfExpiry(String dlDateOfExpiry) {
        this.dlDateOfExpiry = dlDateOfExpiry;
    }

    /**
     * @return the jfPreEmpIdDb
     */
    public String getJfPreEmpIdDb() {
        return jfPreEmpIdDb;
    }

    /**
     * @param jfPreEmpIdDb the jfPreEmpIdDb to set
     */
    public void setJfPreEmpIdDb(String jfPreEmpIdDb) {
        this.jfPreEmpIdDb = jfPreEmpIdDb;
    }

    /**
     * @return the nameAddPrevEmpDb
     */
    public String getNameAddPrevEmpDb() {
        return nameAddPrevEmpDb;
    }

    /**
     * @param nameAddPrevEmpDb the nameAddPrevEmpDb to set
     */
    public void setNameAddPrevEmpDb(String nameAddPrevEmpDb) {
        this.nameAddPrevEmpDb = nameAddPrevEmpDb;
    }

    /**
     * @return the dateOfJoinDb
     */
    public String getDateOfJoinDb() {
        return dateOfJoinDb;
    }

    /**
     * @param dateOfJoinDb the dateOfJoinDb to set
     */
    public void setDateOfJoinDb(String dateOfJoinDb) {
        this.dateOfJoinDb = dateOfJoinDb;
    }

    /**
     * @return the desigOnJoinDb
     */
    public String getDesigOnJoinDb() {
        return desigOnJoinDb;
    }

    /**
     * @param desigOnJoinDb the desigOnJoinDb to set
     */
    public void setDesigOnJoinDb(String desigOnJoinDb) {
        this.desigOnJoinDb = desigOnJoinDb;
    }

    /**
     * @return the salaryOnJoinDb
     */
    public String getSalaryOnJoinDb() {
        return salaryOnJoinDb;
    }

    /**
     * @param salaryOnJoinDb the salaryOnJoinDb to set
     */
    public void setSalaryOnJoinDb(String salaryOnJoinDb) {
        this.salaryOnJoinDb = salaryOnJoinDb;
    }

    /**
     * @return the dateOfRelDb
     */
    public String getDateOfRelDb() {
        return dateOfRelDb;
    }

    /**
     * @param dateOfRelDb the dateOfRelDb to set
     */
    public void setDateOfRelDb(String dateOfRelDb) {
        this.dateOfRelDb = dateOfRelDb;
    }

    /**
     * @return the desigOnRelDb
     */
    public String getDesigOnRelDb() {
        return desigOnRelDb;
    }

    /**
     * @param desigOnRelDb the desigOnRelDb to set
     */
    public void setDesigOnRelDb(String desigOnRelDb) {
        this.desigOnRelDb = desigOnRelDb;
    }

    /**
     * @return the salOnRelDb
     */
    public String getSalOnRelDb() {
        return salOnRelDb;
    }

    /**
     * @param salOnRelDb the salOnRelDb to set
     */
    public void setSalOnRelDb(String salOnRelDb) {
        this.salOnRelDb = salOnRelDb;
    }

    /**
     * @return the skill_category1
     */
    public String[] getSkill_category1() {
        return skill_category1;
    }

    /**
     * @param skill_category1 the skill_category1 to set
     */
    public void setSkill_category1(String[] skill_category1) {
        this.skill_category1 = skill_category1;
    }

    /**
     * @return the otherProof0
     */
    public MultipartFile getOtherProof0() {
        return otherProof0;
    }

    /**
     * @param otherProof0 the otherProof0 to set
     */
    public void setOtherProof0(MultipartFile otherProof0) {
        this.otherProof0 = otherProof0;
    }

    /**
     * @return the otherProof1
     */
    public MultipartFile getOtherProof1() {
        return otherProof1;
    }

    /**
     * @param otherProof1 the otherProof1 to set
     */
    public void setOtherProof1(MultipartFile otherProof1) {
        this.otherProof1 = otherProof1;
    }

    /**
     * @return the otherProof2
     */
    public MultipartFile getOtherProof2() {
        return otherProof2;
    }

    /**
     * @param otherProof2 the otherProof2 to set
     */
    public void setOtherProof2(MultipartFile otherProof2) {
        this.otherProof2 = otherProof2;
    }

    /**
     * @return the otherProof3
     */
    public MultipartFile getOtherProof3() {
        return otherProof3;
    }

    /**
     * @param otherProof3 the otherProof3 to set
     */
    public void setOtherProof3(MultipartFile otherProof3) {
        this.otherProof3 = otherProof3;
    }

    /**
     * @return the otherProof4
     */
    public MultipartFile getOtherProof4() {
        return otherProof4;
    }

    /**
     * @param otherProof4 the otherProof4 to set
     */
    public void setOtherProof4(MultipartFile otherProof4) {
        this.otherProof4 = otherProof4;
    }

    /**
     * @return the otherProof5
     */
    public MultipartFile getOtherProof5() {
        return otherProof5;
    }

    /**
     * @param otherProof5 the otherProof5 to set
     */
    public void setOtherProof5(MultipartFile otherProof5) {
        this.otherProof5 = otherProof5;
    }

    /**
     * @return the otherProof6
     */
    public MultipartFile getOtherProof6() {
        return otherProof6;
    }

    /**
     * @param otherProof6 the otherProof6 to set
     */
    public void setOtherProof6(MultipartFile otherProof6) {
        this.otherProof6 = otherProof6;
    }

    /**
     * @return the otherProof7
     */
    public MultipartFile getOtherProof7() {
        return otherProof7;
    }

    /**
     * @param otherProof7 the otherProof7 to set
     */
    public void setOtherProof7(MultipartFile otherProof7) {
        this.otherProof7 = otherProof7;
    }

    /**
     * @return the otherProof8
     */
    public MultipartFile getOtherProof8() {
        return otherProof8;
    }

    /**
     * @param otherProof8 the otherProof8 to set
     */
    public void setOtherProof8(MultipartFile otherProof8) {
        this.otherProof8 = otherProof8;
    }

    /**
     * @return the otherProof9
     */
    public MultipartFile getOtherProof9() {
        return otherProof9;
    }

    /**
     * @param otherProof9 the otherProof9 to set
     */
    public void setOtherProof9(MultipartFile otherProof9) {
        this.otherProof9 = otherProof9;
    }

    /**
     * @return the otherProof10
     */
    public MultipartFile getOtherProof10() {
        return otherProof10;
    }

    /**
     * @param otherProof10 the otherProof10 to set
     */
    public void setOtherProof10(MultipartFile otherProof10) {
        this.otherProof10 = otherProof10;
    }

    /**
     * @return the dataApprovedBy
     */
    public String getDataApprovedBy() {
        return dataApprovedBy;
    }

    /**
     * @param dataApprovedBy the dataApprovedBy to set
     */
    public void setDataApprovedBy(String dataApprovedBy) {
        this.dataApprovedBy = dataApprovedBy;
    }

    /**
     * @return the dataVerifiedMailCc
     */
    public String getDataVerifiedMailCc() {
        return dataVerifiedMailCc;
    }

    /**
     * @param dataVerifiedMailCc the dataVerifiedMailCc to set
     */
    public void setDataVerifiedMailCc(String dataVerifiedMailCc) {
        this.dataVerifiedMailCc = dataVerifiedMailCc;
    }

    /**
     * @return the dlProof
     */
    public MultipartFile getDlProof() {
        return dlProof;
    }

    /**
     * @param dlProof the dlProof to set
     */
    public void setDlProof(MultipartFile dlProof) {
        this.dlProof = dlProof;
    }

    /**
     * @return the jfFamId
     */
    public String[] getJfFamId() {
        return jfFamId;
    }

    /**
     * @param jfFamId the jfFamId to set
     */
    public void setJfFamId(String[] jfFamId) {
        this.jfFamId = jfFamId;
    }

    /**
     * @return the nameOfRelation
     */
    public String[] getNameOfRelation() {
        return nameOfRelation;
    }

    /**
     * @param nameOfRelation the nameOfRelation to set
     */
    public void setNameOfRelation(String[] nameOfRelation) {
        this.nameOfRelation = nameOfRelation;
    }

    /**
     * @return the nameOfRelationDb
     */
    public String getNameOfRelationDb() {
        return nameOfRelationDb;
    }

    /**
     * @param nameOfRelationDb the nameOfRelationDb to set
     */
    public void setNameOfRelationDb(String nameOfRelationDb) {
        this.nameOfRelationDb = nameOfRelationDb;
    }

    /**
     * @return the relationShip
     */
    public String[] getRelationShip() {
        return relationShip;
    }

    /**
     * @param relationShip the relationShip to set
     */
    public void setRelationShip(String[] relationShip) {
        this.relationShip = relationShip;
    }

    /**
     * @return the relationShipDb
     */
    public String getRelationShipDb() {
        return relationShipDb;
    }

    /**
     * @param relationShipDb the relationShipDb to set
     */
    public void setRelationShipDb(String relationShipDb) {
        this.relationShipDb = relationShipDb;
    }

    /**
     * @return the dobRelation
     */
    public String[] getDobRelation() {
        return dobRelation;
    }

    /**
     * @param dobRelation the dobRelation to set
     */
    public void setDobRelation(String[] dobRelation) {
        this.dobRelation = dobRelation;
    }

    /**
     * @return the dobRelationDb
     */
    public String getDobRelationDb() {
        return dobRelationDb;
    }

    /**
     * @param dobRelationDb the dobRelationDb to set
     */
    public void setDobRelationDb(String dobRelationDb) {
        this.dobRelationDb = dobRelationDb;
    }

    /**
     * @return the occupationOfRel
     */
    public String[] getOccupationOfRel() {
        return occupationOfRel;
    }

    /**
     * @param occupationOfRel the occupationOfRel to set
     */
    public void setOccupationOfRel(String[] occupationOfRel) {
        this.occupationOfRel = occupationOfRel;
    }

    /**
     * @return the occupationOfRelDb
     */
    public String getOccupationOfRelDb() {
        return occupationOfRelDb;
    }

    /**
     * @param occupationOfRelDb the occupationOfRelDb to set
     */
    public void setOccupationOfRelDb(String occupationOfRelDb) {
        this.occupationOfRelDb = occupationOfRelDb;
    }

    /**
     * @return the lifeInsurance
     */
    public int[] getLifeInsurance() {
        return lifeInsurance;
    }

    /**
     * @param lifeInsurance the lifeInsurance to set
     */
    public void setLifeInsurance(int[] lifeInsurance) {
        this.lifeInsurance = lifeInsurance;
    }

    /**
     * @return the lifeInsuranceDb
     */
    public int getLifeInsuranceDb() {
        return lifeInsuranceDb;
    }

    /**
     * @param lifeInsuranceDb the lifeInsuranceDb to set
     */
    public void setLifeInsuranceDb(int lifeInsuranceDb) {
        this.lifeInsuranceDb = lifeInsuranceDb;
    }

    /**
     * @return the medicalInsurance
     */
    public int[] getMedicalInsurance() {
        return medicalInsurance;
    }

    /**
     * @param medicalInsurance the medicalInsurance to set
     */
    public void setMedicalInsurance(int[] medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    /**
     * @return the medicalInsuranceDb
     */
    public int getMedicalInsuranceDb() {
        return medicalInsuranceDb;
    }

    /**
     * @param medicalInsuranceDb the medicalInsuranceDb to set
     */
    public void setMedicalInsuranceDb(int medicalInsuranceDb) {
        this.medicalInsuranceDb = medicalInsuranceDb;
    }

    /**
     * @return the passAttachment_1
     */
    public MultipartFile getPassAttachment_1() {
        return passAttachment_1;
    }

    /**
     * @param passAttachment_1 the passAttachment_1 to set
     */
    public void setPassAttachment_1(MultipartFile passAttachment_1) {
        this.passAttachment_1 = passAttachment_1;
    }

    /**
     * @return the passAttachment_2
     */
    public MultipartFile getPassAttachment_2() {
        return passAttachment_2;
    }

    /**
     * @param passAttachment_2 the passAttachment_2 to set
     */
    public void setPassAttachment_2(MultipartFile passAttachment_2) {
        this.passAttachment_2 = passAttachment_2;
    }

    /**
     * @return the passAttachment_3
     */
    public MultipartFile getPassAttachment_3() {
        return passAttachment_3;
    }

    /**
     * @param passAttachment_3 the passAttachment_3 to set
     */
    public void setPassAttachment_3(MultipartFile passAttachment_3) {
        this.passAttachment_3 = passAttachment_3;
    }

    /**
     * @return the passAttachment_4
     */
    public MultipartFile getPassAttachment_4() {
        return passAttachment_4;
    }

    /**
     * @param passAttachment_4 the passAttachment_4 to set
     */
    public void setPassAttachment_4(MultipartFile passAttachment_4) {
        this.passAttachment_4 = passAttachment_4;
    }

    /**
     * @return the passAttachment_5
     */
    public MultipartFile getPassAttachment_5() {
        return passAttachment_5;
    }

    /**
     * @param passAttachment_5 the passAttachment_5 to set
     */
    public void setPassAttachment_5(MultipartFile passAttachment_5) {
        this.passAttachment_5 = passAttachment_5;
    }

    /**
     * @return the passAttachment_6
     */
    public MultipartFile getPassAttachment_6() {
        return passAttachment_6;
    }

    /**
     * @param passAttachment_6 the passAttachment_6 to set
     */
    public void setPassAttachment_6(MultipartFile passAttachment_6) {
        this.passAttachment_6 = passAttachment_6;
    }

    /**
     * @return the passAttachment_7
     */
    public MultipartFile getPassAttachment_7() {
        return passAttachment_7;
    }

    /**
     * @param passAttachment_7 the passAttachment_7 to set
     */
    public void setPassAttachment_7(MultipartFile passAttachment_7) {
        this.passAttachment_7 = passAttachment_7;
    }

    /**
     * @return the passAttachment_8
     */
    public MultipartFile getPassAttachment_8() {
        return passAttachment_8;
    }

    /**
     * @param passAttachment_8 the passAttachment_8 to set
     */
    public void setPassAttachment_8(MultipartFile passAttachment_8) {
        this.passAttachment_8 = passAttachment_8;
    }

    /**
     * @return the passAttachment_9
     */
    public MultipartFile getPassAttachment_9() {
        return passAttachment_9;
    }

    /**
     * @param passAttachment_9 the passAttachment_9 to set
     */
    public void setPassAttachment_9(MultipartFile passAttachment_9) {
        this.passAttachment_9 = passAttachment_9;
    }

    /**
     * @return the passAttachment_10
     */
    public MultipartFile getPassAttachment_10() {
        return passAttachment_10;
    }

    /**
     * @param passAttachment_10 the passAttachment_10 to set
     */
    public void setPassAttachment_10(MultipartFile passAttachment_10) {
        this.passAttachment_10 = passAttachment_10;
    }

    /**
     * @return the visaCountry
     */
    public String[] getVisaCountry() {
        return visaCountry;
    }

    /**
     * @param visaCountry the visaCountry to set
     */
    public void setVisaCountry(String[] visaCountry) {
        this.visaCountry = visaCountry;
    }

    /**
     * @return the visaCountryDb
     */
    public String getVisaCountryDb() {
        return visaCountryDb;
    }

    /**
     * @param visaCountryDb the visaCountryDb to set
     */
    public void setVisaCountryDb(String visaCountryDb) {
        this.visaCountryDb = visaCountryDb;
    }

    /**
     * @return the visaType
     */
    public String[] getVisaType() {
        return visaType;
    }

    /**
     * @param visaType the visaType to set
     */
    public void setVisaType(String[] visaType) {
        this.visaType = visaType;
    }

    /**
     * @return the visaTypeDb
     */
    public String getVisaTypeDb() {
        return visaTypeDb;
    }

    /**
     * @param visaTypeDb the visaTypeDb to set
     */
    public void setVisaTypeDb(String visaTypeDb) {
        this.visaTypeDb = visaTypeDb;
    }

    /**
     * @return the visaDateOfIssue
     */
    public String[] getVisaDateOfIssue() {
        return visaDateOfIssue;
    }

    /**
     * @param visaDateOfIssue the visaDateOfIssue to set
     */
    public void setVisaDateOfIssue(String[] visaDateOfIssue) {
        this.visaDateOfIssue = visaDateOfIssue;
    }

    /**
     * @return the visaDateOfIssueDb
     */
    public String getVisaDateOfIssueDb() {
        return visaDateOfIssueDb;
    }

    /**
     * @param visaDateOfIssueDb the visaDateOfIssueDb to set
     */
    public void setVisaDateOfIssueDb(String visaDateOfIssueDb) {
        this.visaDateOfIssueDb = visaDateOfIssueDb;
    }

    /**
     * @return the visaDateOfExpiry
     */
    public String[] getVisaDateOfExpiry() {
        return visaDateOfExpiry;
    }

    /**
     * @param visaDateOfExpiry the visaDateOfExpiry to set
     */
    public void setVisaDateOfExpiry(String[] visaDateOfExpiry) {
        this.visaDateOfExpiry = visaDateOfExpiry;
    }

    /**
     * @return the visaDateOfExpiryDb
     */
    public String getVisaDateOfExpiryDb() {
        return visaDateOfExpiryDb;
    }

    /**
     * @param visaDateOfExpiryDb the visaDateOfExpiryDb to set
     */
    public void setVisaDateOfExpiryDb(String visaDateOfExpiryDb) {
        this.visaDateOfExpiryDb = visaDateOfExpiryDb;
    }

    /**
     * @return the jfVisaId
     */
    public String[] getJfVisaId() {
        return jfVisaId;
    }

    /**
     * @param jfVisaId the jfVisaId to set
     */
    public void setJfVisaId(String[] jfVisaId) {
        this.jfVisaId = jfVisaId;
    }

    /**
     * @return the jfVisaIdDb
     */
    public String getJfVisaIdDb() {
        return jfVisaIdDb;
    }

    /**
     * @param jfVisaIdDb the jfVisaIdDb to set
     */
    public void setJfVisaIdDb(String jfVisaIdDb) {
        this.jfVisaIdDb = jfVisaIdDb;
    }

    /**
     * @return the visaDetailsStatus
     */
    public String[] getVisaDetailsStatus() {
        return visaDetailsStatus;
    }

    /**
     * @param visaDetailsStatus the visaDetailsStatus to set
     */
    public void setVisaDetailsStatus(String[] visaDetailsStatus) {
        this.visaDetailsStatus = visaDetailsStatus;
    }

    /**
     * @return the visaDetailsCount
     */
    public int getVisaDetailsCount() {
        return visaDetailsCount;
    }

    /**
     * @param visaDetailsCount the visaDetailsCount to set
     */
    public void setVisaDetailsCount(int visaDetailsCount) {
        this.visaDetailsCount = visaDetailsCount;
    }

    /**
     * @return the visaDetailsDeleted
     */
    public int getVisaDetailsDeleted() {
        return visaDetailsDeleted;
    }

    /**
     * @param visaDetailsDeleted the visaDetailsDeleted to set
     */
    public void setVisaDetailsDeleted(int visaDetailsDeleted) {
        this.visaDetailsDeleted = visaDetailsDeleted;
    }

    /**
     * @return the jfFamIdDb
     */
    public int getJfFamIdDb() {
        return jfFamIdDb;
    }

    /**
     * @param jfFamIdDb the jfFamIdDb to set
     */
    public void setJfFamIdDb(int jfFamIdDb) {
        this.jfFamIdDb = jfFamIdDb;
    }

    /**
     * @return the VISA_TR_deletedX
     */
    public int getVISA_TR_deletedX() {
        return VISA_TR_deletedX;
    }

    /**
     * @param VISA_TR_deletedX the VISA_TR_deletedX to set
     */
    public void setVISA_TR_deletedX(int VISA_TR_deletedX) {
        this.VISA_TR_deletedX = VISA_TR_deletedX;
    }

    /**
     * @return the EMERGENCY_TR_deletedX
     */
    public int getEMERGENCY_TR_deletedX() {
        return EMERGENCY_TR_deletedX;
    }

    /**
     * @param EMERGENCY_TR_deletedX the EMERGENCY_TR_deletedX to set
     */
    public void setEMERGENCY_TR_deletedX(int EMERGENCY_TR_deletedX) {
        this.EMERGENCY_TR_deletedX = EMERGENCY_TR_deletedX;
    }

    /**
     * @return the dataVerifiedBy
     */
    public String getDataVerifiedBy() {
        return dataVerifiedBy;
    }

    /**
     * @param dataVerifiedBy the dataVerifiedBy to set
     */
    public void setDataVerifiedBy(String dataVerifiedBy) {
        this.dataVerifiedBy = dataVerifiedBy;
    }

    /**
     * @return the checkJoinerWorkEmail
     */
    public String getCheckJoinerWorkEmail() {
        return checkJoinerWorkEmail;
    }

    /**
     * @param checkJoinerWorkEmail the checkJoinerWorkEmail to set
     */
    public void setCheckJoinerWorkEmail(String checkJoinerWorkEmail) {
        this.checkJoinerWorkEmail = checkJoinerWorkEmail;
    }

    /**
     * @return the jfPerCompIdDb
     */
    public int getJfPerCompIdDb() {
        return jfPerCompIdDb;
    }

    /**
     * @param jfPerCompIdDb the jfPerCompIdDb to set
     */
    public void setJfPerCompIdDb(int jfPerCompIdDb) {
        this.jfPerCompIdDb = jfPerCompIdDb;
    }

    /**
     * @return the jfPerCompId
     */
    public int[] getJfPerCompId() {
        return jfPerCompId;
    }

    /**
     * @param jfPerCompId the jfPerCompId to set
     */
    public void setJfPerCompId(int[] jfPerCompId) {
        this.jfPerCompId = jfPerCompId;
    }

    /**
     * @return the jfRefEarCompIdDb
     */
    public int getJfRefEarCompIdDb() {
        return jfRefEarCompIdDb;
    }

    /**
     * @param jfRefEarCompIdDb the jfRefEarCompIdDb to set
     */
    public void setJfRefEarCompIdDb(int jfRefEarCompIdDb) {
        this.jfRefEarCompIdDb = jfRefEarCompIdDb;
    }

    /**
     * @return the jfRefEarCompId
     */
    public int[] getJfRefEarCompId() {
        return jfRefEarCompId;
    }

    /**
     * @param jfRefEarCompId the jfRefEarCompId to set
     */
    public void setJfRefEarCompId(int[] jfRefEarCompId) {
        this.jfRefEarCompId = jfRefEarCompId;
    }

    /**
     *
     *
     *
     *
     *
     * /
     *
     **
     * @return the personsAtCompanyCount
     */
    public int getPersonsAtCompanyCount() {
        return personsAtCompanyCount;
    }

    /**
     * @param personsAtCompanyCount the personsAtCompanyCount to set
     */
    public void setPersonsAtCompanyCount(int personsAtCompanyCount) {
        this.personsAtCompanyCount = personsAtCompanyCount;
    }

    /**
     * @return the personsAtCompanyDeleted
     */
    public int getPersonsAtCompanyDeleted() {
        return personsAtCompanyDeleted;
    }

    /**
     * @param personsAtCompanyDeleted the personsAtCompanyDeleted to set
     */
    public void setPersonsAtCompanyDeleted(int personsAtCompanyDeleted) {
        this.personsAtCompanyDeleted = personsAtCompanyDeleted;
    }

    /**
     * @return the doj
     */
    public String getDoj() {
        return doj;
    }

    /**
     * @param doj the doj to set
     */
    public void setDoj(String doj) {
        this.doj = doj;
    }

    /**
     * @return the joinerDoj
     */
    public String getJoinerDoj() {
        return joinerDoj;
    }

    /**
     * @param joinerDoj the joinerDoj to set
     */
    public void setJoinerDoj(String joinerDoj) {
        this.joinerDoj = joinerDoj;
    }

    /**
     * @return the joinerWorkEmail
     */
    public String getJoinerWorkEmail() {
        return joinerWorkEmail;
    }

    /**
     * @param joinerWorkEmail the joinerWorkEmail to set
     */
    public void setJoinerWorkEmail(String joinerWorkEmail) {
        this.joinerWorkEmail = joinerWorkEmail;
    }

    /**
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }
    public String eIdd;

    public String geteIdd() {
        return eIdd;
    }

    public void seteIdd(String eIdd) {
        this.eIdd = eIdd;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * @return the employeeStatusX
     */
    public int getEmployeeStatusX() {
        return employeeStatusX;
    }

    /**
     * @param employeeStatusX the employeeStatusX to set
     */
    public void setEmployeeStatusX(int employeeStatusX) {
        this.employeeStatusX = employeeStatusX;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the date_of_issue
     */
    public String getDate_of_issue() {
        return date_of_issue;
    }

    /**
     * @param date_of_issue the date_of_issue to set
     */
    public void setDate_of_issue(String date_of_issue) {
        this.date_of_issue = date_of_issue;
    }

    /**
     * @return the date_of_expiry
     */
    public String getDate_of_expiry() {
        return date_of_expiry;
    }

    /**
     * @param date_of_expiry the date_of_expiry to set
     */
    public void setDate_of_expiry(String date_of_expiry) {
        this.date_of_expiry = date_of_expiry;
    }

    /**
     * @return the sendBackReason
     */
    public String getSendBackReason() {
        return sendBackReason;
    }

    /**
     * @param sendBackReason the sendBackReason to set
     */
    public void setSendBackReason(String sendBackReason) {
        this.sendBackReason = sendBackReason;
    }

    /**
     * @return the workEmailId
     */
    public String getWorkEmailId() {
        return workEmailId;
    }

    /**
     * @param workEmailId the workEmailId to set
     */
    public void setWorkEmailId(String workEmailId) {
        this.workEmailId = workEmailId;
    }

    /**
     * @return the contractemployeeId
     */
    public String getContractemployeeId() {
        return contractemployeeId;
    }

    /**
     * @param contractemployeeId the contractemployeeId to set
     */
    public void setContractemployeeId(String contractemployeeId) {
        this.contractemployeeId = contractemployeeId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userEmpId
     */
    public String getUserEmpId() {
        return userEmpId;
    }

    /**
     * @param userEmpId the userEmpId to set
     */
    public void setUserEmpId(String userEmpId) {
        this.userEmpId = userEmpId;
    }

    /**
     * @return the empId1
     */
    public String getEmpId1() {
        return empId1;
    }

    /**
     * @param empId1 the empId1 to set
     */
    public void setEmpId1(String empId1) {
        this.empId1 = empId1;
    }

    /**
     * @return the jfStatus
     */
    public String getJfStatus() {
        return jfStatus;
    }

    /**
     * @param jfStatus the jfStatus to set
     */
    public void setJfStatus(String jfStatus) {
        this.jfStatus = jfStatus;
    }

    /**
     * @return the dataVerifiedById
     */
    public String getDataVerifiedById() {
        return dataVerifiedById;
    }

    /**
     * @param dataVerifiedById the dataVerifiedById to set
     */
    public void setDataVerifiedById(String dataVerifiedById) {
        this.dataVerifiedById = dataVerifiedById;
    }

    /**
     * @return the dataApprovedById
     */
    public String getDataApprovedById() {
        return dataApprovedById;
    }

    /**
     * @param dataApprovedById the dataApprovedById to set
     */
    public void setDataApprovedById(String dataApprovedById) {
        this.dataApprovedById = dataApprovedById;
    }

    /**
     * @return the hrName
     */
    public String getHrName() {
        return hrName;
    }

    /**
     * @param hrName the hrName to set
     */
    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the educationalQualificationFileAttachmentCount
     */
    public String[] getEducationalQualificationFileAttachmentCount() {
        return educationalQualificationFileAttachmentCount;
    }

    /**
     * @param educationalQualificationFileAttachmentCount the
     * educationalQualificationFileAttachmentCount to set
     */
    public void setEducationalQualificationFileAttachmentCount(String[] educationalQualificationFileAttachmentCount) {
        this.educationalQualificationFileAttachmentCount = educationalQualificationFileAttachmentCount;
    }

    /**
     * @return the otherProofDetails
     */
    public MultipartFile[] getOtherProofDetails() {
        return otherProofDetails;
    }

    /**
     * @param otherProofDetails the otherProofDetails to set
     */
    public void setOtherProofDetails(MultipartFile[] otherProofDetails) {
        this.otherProofDetails = otherProofDetails;
    }

    /**
     * @return the fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * @return the fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * @return the referenceName
     */
    public String getReferenceName() {
        return referenceName;
    }

    /**
     * @param referenceName the referenceName to set
     */
    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    /**
     * @return the referenceId
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * @param referenceId the referenceId to set
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * @return the moduleName
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName the moduleName to set
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return the xYearOfPassing
     */
    public String getxYearOfPassing() {
        return xYearOfPassing;
    }

    /**
     * @param xYearOfPassing the xYearOfPassing to set
     */
    public void setxYearOfPassing(String xYearOfPassing) {
        this.xYearOfPassing = xYearOfPassing;
    }

    /**
     * @return the xInstitution
     */
    public String getxInstitution() {
        return xInstitution;
    }

    /**
     * @param xInstitution the xInstitution to set
     */
    public void setxInstitution(String xInstitution) {
        this.xInstitution = xInstitution;
    }

    /**
     * @return the xPercentage
     */
    public String getxPercentage() {
        return xPercentage;
    }

    /**
     * @param xPercentage the xPercentage to set
     */
    public void setxPercentage(String xPercentage) {
        this.xPercentage = xPercentage;
    }

    /**
     * @return the p2Qualification
     */
    public String getP2Qualification() {
        return p2Qualification;
    }

    /**
     * @param p2Qualification the p2Qualification to set
     */
    public void setP2Qualification(String p2Qualification) {
        this.p2Qualification = p2Qualification;
    }

    /**
     * @return the p2YearOfPassing
     */
    public String getP2YearOfPassing() {
        return p2YearOfPassing;
    }

    /**
     * @param p2YearOfPassing the p2YearOfPassing to set
     */
    public void setP2YearOfPassing(String p2YearOfPassing) {
        this.p2YearOfPassing = p2YearOfPassing;
    }

    /**
     * @return the p2Institution
     */
    public String getP2Institution() {
        return p2Institution;
    }

    /**
     * @param p2Institution the p2Institution to set
     */
    public void setP2Institution(String p2Institution) {
        this.p2Institution = p2Institution;
    }

    /**
     * @return the p2Percentage
     */
    public String getP2Percentage() {
        return p2Percentage;
    }

    /**
     * @param p2Percentage the p2Percentage to set
     */
    public void setP2Percentage(String p2Percentage) {
        this.p2Percentage = p2Percentage;
    }

    /**
     * @return the gradQualification
     */
    public String getGradQualification() {
        return gradQualification;
    }

    /**
     * @param gradQualification the gradQualification to set
     */
    public void setGradQualification(String gradQualification) {
        this.gradQualification = gradQualification;
    }

    /**
     * @return the gradYearOfPassing
     */
    public String getGradYearOfPassing() {
        return gradYearOfPassing;
    }

    /**
     * @param gradYearOfPassing the gradYearOfPassing to set
     */
    public void setGradYearOfPassing(String gradYearOfPassing) {
        this.gradYearOfPassing = gradYearOfPassing;
    }

    /**
     * @return the gradInstitution
     */
    public String getGradInstitution() {
        return gradInstitution;
    }

    /**
     * @param gradInstitution the gradInstitution to set
     */
    public void setGradInstitution(String gradInstitution) {
        this.gradInstitution = gradInstitution;
    }

    /**
     * @return the gradPercentage
     */
    public String getGradPercentage() {
        return gradPercentage;
    }

    /**
     * @param gradPercentage the gradPercentage to set
     */
    public void setGradPercentage(String gradPercentage) {
        this.gradPercentage = gradPercentage;
    }

    /**
     * @return the pGradQualification
     */
    public String getpGradQualification() {
        return pGradQualification;
    }

    /**
     * @param pGradQualification the pGradQualification to set
     */
    public void setpGradQualification(String pGradQualification) {
        this.pGradQualification = pGradQualification;
    }

    /**
     * @return the pGradYearOfPassing
     */
    public String getpGradYearOfPassing() {
        return pGradYearOfPassing;
    }

    /**
     * @param pGradYearOfPassing the pGradYearOfPassing to set
     */
    public void setpGradYearOfPassing(String pGradYearOfPassing) {
        this.pGradYearOfPassing = pGradYearOfPassing;
    }

    /**
     * @return the pGradInstitution
     */
    public String getpGradInstitution() {
        return pGradInstitution;
    }

    /**
     * @param pGradInstitution the pGradInstitution to set
     */
    public void setpGradInstitution(String pGradInstitution) {
        this.pGradInstitution = pGradInstitution;
    }

    /**
     * @return the pGradPercentage
     */
    public String getpGradPercentage() {
        return pGradPercentage;
    }

    /**
     * @param pGradPercentage the pGradPercentage to set
     */
    public void setpGradPercentage(String pGradPercentage) {
        this.pGradPercentage = pGradPercentage;
    }

    /**
     * @return the educationalQualificationCount
     */
    public int getEducationalQualificationCount() {
        return educationalQualificationCount;
    }

    /**
     * @param educationalQualificationCount the educationalQualificationCount to
     * set
     */
    public void setEducationalQualificationCount(int educationalQualificationCount) {
        this.educationalQualificationCount = educationalQualificationCount;
    }

    /**
     * @param refFrmEarDesignation the refFrmEarDesignation to set
     */
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    //public String getIfsc() {
    //  return ifsc;
    //}
    //public void setIfsc(String ifsc) {
    //this.ifsc = ifsc;
    //}
    public String getUanNo() {
        return uanNo;
    }

    public void setUanNo(String uanNo) {
        this.uanNo = uanNo;
    }

    public MultipartFile getEmpUanProof() {
        return empUanProof;
    }

    public void setEmpUanProof(MultipartFile empUanProof) {
        this.empUanProof = empUanProof;
    }

    public String getEmpUanPfFileName() {
        return empUanPfFileName;
    }

    public void setEmpUanPfFileName(String empUanPfFileName) {
        this.empUanPfFileName = empUanPfFileName;
    }

    public String getEmpUanPfFileType() {
        return empUanPfFileType;
    }

    public void setEmpUanPfFileType(String empUanPfFileType) {
        this.empUanPfFileType = empUanPfFileType;
    }

    public String getEmpUanPfFileId() {
        return empUanPfFileId;
    }

    public void setEmpUanPfFileId(String empUanPfFileId) {
        this.empUanPfFileId = empUanPfFileId;
    }

    public MultipartFile getExp_attachment_1() {
        return exp_attachment_1;
    }

    public void setExp_attachment_1(MultipartFile exp_attachment_1) {
        this.exp_attachment_1 = exp_attachment_1;
    }

    public MultipartFile getExp_attachment_2() {
        return exp_attachment_2;
    }

    public void setExp_attachment_2(MultipartFile exp_attachment_2) {
        this.exp_attachment_2 = exp_attachment_2;
    }

    public MultipartFile getExp_attachment_3() {
        return exp_attachment_3;
    }

    public void setExp_attachment_3(MultipartFile exp_attachment_3) {
        this.exp_attachment_3 = exp_attachment_3;
    }

    public MultipartFile getExp_attachment_4() {
        return exp_attachment_4;
    }

    public void setExp_attachment_4(MultipartFile exp_attachment_4) {
        this.exp_attachment_4 = exp_attachment_4;
    }

    public MultipartFile getExp_attachment_5() {
        return exp_attachment_5;
    }

    public void setExp_attachment_5(MultipartFile exp_attachment_5) {
        this.exp_attachment_5 = exp_attachment_5;
    }

    public MultipartFile getExp_attachment_6() {
        return exp_attachment_6;
    }

    public void setExp_attachment_6(MultipartFile exp_attachment_6) {
        this.exp_attachment_6 = exp_attachment_6;
    }

    public MultipartFile getExp_attachment_7() {
        return exp_attachment_7;
    }

    public void setExp_attachment_7(MultipartFile exp_attachment_7) {
        this.exp_attachment_7 = exp_attachment_7;
    }

    public MultipartFile getExp_attachment_8() {
        return exp_attachment_8;
    }

    public void setExp_attachment_8(MultipartFile exp_attachment_8) {
        this.exp_attachment_8 = exp_attachment_8;
    }

    public MultipartFile getExp_attachment_9() {
        return exp_attachment_9;
    }

    public void setExp_attachment_9(MultipartFile exp_attachment_9) {
        this.exp_attachment_9 = exp_attachment_9;
    }

    public MultipartFile getExp_attachment_10() {
        return exp_attachment_10;
    }

    public void setExp_attachment_10(MultipartFile exp_attachment_10) {
        this.exp_attachment_10 = exp_attachment_10;
    }

    public String getExp_attachmentX() {
        return exp_attachmentX;
    }

    public void setExp_attachmentX(String exp_attachmentX) {
        this.exp_attachmentX = exp_attachmentX;
    }

    public String[] getExp_attachmentXY() {
        return exp_attachmentXY;
    }

    public void setExp_attachmentXY(String[] exp_attachmentXY) {
        this.exp_attachmentXY = exp_attachmentXY;
    }

    public MultipartFile getEmpAdharProof() {
        return empAdharProof;
    }

    public void setEmpAdharProof(MultipartFile empAdharProof) {
        this.empAdharProof = empAdharProof;
    }
}
