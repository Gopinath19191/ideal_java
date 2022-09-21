/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.dto;
import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 14053
 */
public class EmployeeDto implements Serializable {
    private String userName;
    private String password;
    private String idealUserName;
    private String idealLoginTime;
    public String empId;
    private String groupId;
    private String userAccountId;
    private String moduleId;
    private String gCreate;
    private String gRead;
    private String gUpdate;
    private String gDelete;
    private String uCreate;
    private String uRead;
    private String uUpdate;
    private String uDelete;
    private String menuName;
    private String menuId;
    private String moduleName;
    private String employeeId;
    private String employeeName;
    //For Excel--Malar
    private String empFullName;
    private String sbuName;
    private String subSbuName;

    //Certification OR Education
    private String certificationId;
    public int deletedTR;
    private String qualification;
    private String krauom;
    private String kratarget;
    private String year_of_passing;
    private String institution;
    private String percentage;
    public String remarks;
    private String degree;
    public String employee;
    private String university;
    private String certificationIdX;
    private String qualificationX;
    private String krauomX;
    private String kratargetX;
    private String year_of_passingX;
    private String institutionX;
    private String percentageX;
    private String remarksX;
    private String degreeX;
    private String universityX;
    private String qualificationXY;
    private String institutionXY;
    private String universityXY;
    private String degreeXY;
    private String institutionName;
    private String qualificationName;
    private String universityName;
    private String actionValue;
    private String actionValueX;
    private String submittedDate;
    private String quarterId;
    public String year;
    public String kra_id;
    public String lastInsertId;
    public String rm_remarks;
    private String associateId;
private String submittedBy;
private String companyStructureId;
private String practiceGroup;
private String structureId;
private String practiceGroupSearch;
private String practice;
private String structureNameSearch;
private String bandId;

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    public String getStructureNameSearch() {
        return structureNameSearch;
    }

    public void setStructureNameSearch(String structureNameSearch) {
        this.structureNameSearch = structureNameSearch;
    }
    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }


    public String getPracticeGroupSearch() {
        return practiceGroupSearch;
    }

    public void setPracticeGroupSearch(String practiceGroupSearch) {
        this.practiceGroupSearch = practiceGroupSearch;
    }

    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }


    public String getPracticeGroup() {
        return practiceGroup;
    }

    public void setPracticeGroup(String practiceGroup) {
        this.practiceGroup = practiceGroup;
    }


    public String getCompanyStructureId() {
        return companyStructureId;
    }

    public void setCompanyStructureId(String companyStructureId) {
        this.companyStructureId = companyStructureId;
    }


    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }


    public String getAssociateId() {
        return associateId;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }
    
     public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
public String  file_name;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }


    public String getRm_remarks() {
        return rm_remarks;
    }

    public void setRm_remarks(String rm_remarks) {
        this.rm_remarks = rm_remarks;
    }
        
    public String getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(String lastInsertId) {
        this.lastInsertId = lastInsertId;
    }
    
    public String getKra_id() {
        return kra_id;
    }

    public void setKra_id(String kra_id) {
        this.kra_id = kra_id;
    }
        
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQuarterId() {
        return quarterId;
    }

    public void setQuarterId(String quarterId) {
        this.quarterId = quarterId;
    }


    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }
    
    
    //Work Experience

    //Passport
   

    //Visa
   

    //Skills
  

    //Address
   

    //Bank Details
    

    //Emergency Contacts
   
    private int start_page;
    private int end_page;
    private String recordCount;
    private String empName;
    private String uniqueId;
    private String uniqueValue;
    private String issueDate;

    //Count
    private String recordId;
    private String recordStructure;
    private String recordEmployee;
    
    //Excel Export
    public String excelbuttonName;
    public String licence_number;
    public String licence_date_issue;
    public String licence_date_expire;
    public String licence_remarks;
    public String voter_id;
    public String voter_remarks;
    public String adhar_number;
    public String adhar_remarks;
    public String proofType;
    public String prevPfDetails;
    public String alreadyPensionedMember;
    public String pfAlreadyWithdrawn;
    public String pfAccNo;
    public String uanNo;
    public String dateOfExit;
    public String internationalWorkerStatus;
    public String countryOfOrigin;
    public String passportNumber;
    public String countryOfIssue;
    public String passportValidFrom;
    public String passportValidUpto;
    public String pfId;
    public String empNumber;
    public String email;
    public String highestQualification;
    public String maritalStatus;
    public String speciallyAble;
    public String speciallyAbleCategory;
    public String adhar;
    public String nameAsAdhar;
    public MultipartFile file;
    public String filename;
    public String panNo;
    public String nameAsPan;
    public String drivingLicence;
    public String nameAsdrivingLicence;
    public String drivingLicenceExpiryDate;
    public String voterId;
    public String nameAsVoterId;
    public String rationId;
    public String nameAsRation;
    public String expireDate;
    public String proofNumber;
    public String nameAsProof;
    public String accessType;
public String fullFileName;

    public String getKrauom() {
        return krauom;
    }

    public void setKrauom(String krauom) {
        this.krauom = krauom;
    }

    public String getKratarget() {
        return kratarget;
    }

    public void setKratarget(String kratarget) {
        this.kratarget = kratarget;
    }

    public String getKrauomX() {
        return krauomX;
    }

    public void setKrauomX(String krauomX) {
        this.krauomX = krauomX;
    }

    public String getKratargetX() {
        return kratargetX;
    }

    public void setKratargetX(String kratargetX) {
        this.kratargetX = kratargetX;
    }



    public String getActionValueX() {
        return actionValueX;
    }

    public void setActionValueX(String actionValueX) {
        this.actionValueX = actionValueX;
    }



    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }



    public String getFullFileName() {
        return fullFileName;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    
    
  

    
    
    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    
    public String getNameAsProof() {
        return nameAsProof;
    }

    public void setNameAsProof(String nameAsProof) {
        this.nameAsProof = nameAsProof;
    }
    
    
    
    
    

    public String getProofNumber() {
        return proofNumber;
    }

    public void setProofNumber(String proofNumber) {
        this.proofNumber = proofNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getRationId() {
        return rationId;
    }

    public void setRationId(String rationId) {
        this.rationId = rationId;
    }

    public String getNameAsRation() {
        return nameAsRation;
    }

    public void setNameAsRation(String nameAsRation) {
        this.nameAsRation = nameAsRation;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getSpeciallyAble() {
        return speciallyAble;
    }

    public void setSpeciallyAble(String speciallyAble) {
        this.speciallyAble = speciallyAble;
    }

    public String getSpeciallyAbleCategory() {
        return speciallyAbleCategory;
    }

    public void setSpeciallyAbleCategory(String speciallyAbleCategory) {
        this.speciallyAbleCategory = speciallyAbleCategory;
    }

    public String getAdhar() {
        return adhar;
    }

    public void setAdhar(String adhar) {
        this.adhar = adhar;
    }

    public String getNameAsAdhar() {
        return nameAsAdhar;
    }

    public void setNameAsAdhar(String nameAsAdhar) {
        this.nameAsAdhar = nameAsAdhar;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getNameAsPan() {
        return nameAsPan;
    }

    public void setNameAsPan(String nameAsPan) {
        this.nameAsPan = nameAsPan;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    public String getNameAsdrivingLicence() {
        return nameAsdrivingLicence;
    }

    public void setNameAsdrivingLicence(String nameAsdrivingLicence) {
        this.nameAsdrivingLicence = nameAsdrivingLicence;
    }

    public String getDrivingLicenceExpiryDate() {
        return drivingLicenceExpiryDate;
    }

    public void setDrivingLicenceExpiryDate(String drivingLicenceExpiryDate) {
        this.drivingLicenceExpiryDate = drivingLicenceExpiryDate;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getNameAsVoterId() {
        return nameAsVoterId;
    }

    public void setNameAsVoterId(String nameAsVoterId) {
        this.nameAsVoterId = nameAsVoterId;
    }

    public String getPfId() {
        return pfId;
    }

    public void setPfId(String pfId) {
        this.pfId = pfId;
    }

    public String getPrevPfDetails() {
        return prevPfDetails;
    }

    public void setPrevPfDetails(String prevPfDetails) {
        this.prevPfDetails = prevPfDetails;
    }

    public String getAlreadyPensionedMember() {
        return alreadyPensionedMember;
    }

    public void setAlreadyPensionedMember(String alreadyPensionedMember) {
        this.alreadyPensionedMember = alreadyPensionedMember;
    }

    public String getPfAlreadyWithdrawn() {
        return pfAlreadyWithdrawn;
    }

    public void setPfAlreadyWithdrawn(String pfAlreadyWithdrawn) {
        this.pfAlreadyWithdrawn = pfAlreadyWithdrawn;
    }

    public String getPfAccNo() {
        return pfAccNo;
    }

    public void setPfAccNo(String pfAccNo) {
        this.pfAccNo = pfAccNo;
    }

    public String getUanNo() {
        return uanNo;
    }

    public void setUanNo(String uanNo) {
        this.uanNo = uanNo;
    }

    public String getDateOfExit() {
        return dateOfExit;
    }

    public void setDateOfExit(String dateOfExit) {
        this.dateOfExit = dateOfExit;
    }

    public String getInternationalWorkerStatus() {
        return internationalWorkerStatus;
    }

    public void setInternationalWorkerStatus(String internationalWorkerStatus) {
        this.internationalWorkerStatus = internationalWorkerStatus;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    public String getPassportValidFrom() {
        return passportValidFrom;
    }

    public void setPassportValidFrom(String passportValidFrom) {
        this.passportValidFrom = passportValidFrom;
    }

    public String getPassportValidUpto() {
        return passportValidUpto;
    }

    public void setPassportValidUpto(String passportValidUpto) {
        this.passportValidUpto = passportValidUpto;
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }

    public String getLicence_remarks() {
        return licence_remarks;
    }

    public void setLicence_remarks(String licence_remarks) {
        this.licence_remarks = licence_remarks;
    }

    public String getVoter_remarks() {
        return voter_remarks;
    }

    public void setVoter_remarks(String voter_remarks) {
        this.voter_remarks = voter_remarks;
    }

    public String getAdhar_remarks() {
        return adhar_remarks;
    }

    public void setAdhar_remarks(String adhar_remarks) {
        this.adhar_remarks = adhar_remarks;
    }


    public String getAdhar_number() {
        return adhar_number;
    }

    public void setAdhar_number(String adhar_number) {
        this.adhar_number = adhar_number;
    }


    public String getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(String voter_id) {
        this.voter_id = voter_id;
    }

   


    public String getLicence_number() {
        return licence_number;
    }

    public void setLicence_number(String licence_number) {
        this.licence_number = licence_number;
    }

    public String getLicence_date_issue() {
        return licence_date_issue;
    }

    public void setLicence_date_issue(String licence_date_issue) {
        this.licence_date_issue = licence_date_issue;
    }

    public String getLicence_date_expire() {
        return licence_date_expire;
    }

    public void setLicence_date_expire(String licence_date_expire) {
        this.licence_date_expire = licence_date_expire;
    }
    
    
    public String getRecordEmployee() {
        return recordEmployee;
    }

    public void setRecordEmployee(String recordEmployee) {
        this.recordEmployee = recordEmployee;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordStructure() {
        return recordStructure;
    }

    public void setRecordStructure(String recordStructure) {
        this.recordStructure = recordStructure;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUniqueValue() {
        return uniqueValue;
    }

    public void setUniqueValue(String uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    
    public int getEnd_page() {
        return end_page;
    }

    public void setEnd_page(int end_page) {
        this.end_page = end_page;
    }

    public int getStart_page() {
        return start_page;
    }

    public void setStart_page(int start_page) {
        this.start_page = start_page;
    }
    
  
    
    public String getCertificationIdX() {
        return certificationIdX;
    }

    public void setCertificationIdX(String certificationIdX) {
        this.certificationIdX = certificationIdX;
    }

    public String getDegreeX() {
        return degreeX;
    }

    public void setDegreeX(String degreeX) {
        this.degreeX = degreeX;
    }

    public String getInstitutionX() {
        return institutionX;
    }

    public void setInstitutionX(String institutionX) {
        this.institutionX = institutionX;
    }

    public String getPercentageX() {
        return percentageX;
    }

    public void setPercentageX(String percentageX) {
        this.percentageX = percentageX;
    }

    public String getQualificationX() {
        return qualificationX;
    }

    public void setQualificationX(String qualificationX) {
        this.qualificationX = qualificationX;
    }

    public String getRemarksX() {
        return remarksX;
    }

    public void setRemarksX(String remarksX) {
        this.remarksX = remarksX;
    }

    public String getUniversityX() {
        return universityX;
    }

    public void setUniversityX(String universityX) {
        this.universityX = universityX;
    }

    public String getYear_of_passingX() {
        return year_of_passingX;
    }

    public void setYear_of_passingX(String year_of_passingX) {
        this.year_of_passingX = year_of_passingX;
    }

    public String getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(String certificationId) {
        this.certificationId = certificationId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getDeletedTR() {
        return deletedTR;
    }

    public void setDeletedTR(int deletedTR) {
        this.deletedTR = deletedTR;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getYear_of_passing() {
        return year_of_passing;
    }

    public void setYear_of_passing(String year_of_passing) {
        this.year_of_passing = year_of_passing;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
        
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getgCreate() {
        return gCreate;
    }

    public void setgCreate(String gCreate) {
        this.gCreate = gCreate;
    }

    public String getgDelete() {
        return gDelete;
    }

    public void setgDelete(String gDelete) {
        this.gDelete = gDelete;
    }

    public String getgRead() {
        return gRead;
    }

    public void setgRead(String gRead) {
        this.gRead = gRead;
    }

    public String getgUpdate() {
        return gUpdate;
    }

    public void setgUpdate(String gUpdate) {
        this.gUpdate = gUpdate;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getIdealLoginTime() {
        return idealLoginTime;
    }

    public void setIdealLoginTime(String idealLoginTime) {
        this.idealLoginTime = idealLoginTime;
    }

    public String getIdealUserName() {
        return idealUserName;
    }

    public void setIdealUserName(String idealUserName) {
        this.idealUserName = idealUserName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getuCreate() {
        return uCreate;
    }

    public void setuCreate(String uCreate) {
        this.uCreate = uCreate;
    }

    public String getuDelete() {
        return uDelete;
    }

    public void setuDelete(String uDelete) {
        this.uDelete = uDelete;
    }

    public String getuRead() {
        return uRead;
    }

    public void setuRead(String uRead) {
        this.uRead = uRead;
    }

    public String getuUpdate() {
        return uUpdate;
    }

    public void setuUpdate(String uUpdate) {
        this.uUpdate = uUpdate;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpFullName() {
        return empFullName;
    }

    public void setEmpFullName(String empFullName) {
        this.empFullName = empFullName;
    }

    public String getExcelbuttonName() {
        return excelbuttonName;
    }

    public void setExcelbuttonName(String excelbuttonName) {
        this.excelbuttonName = excelbuttonName;
    }

    public String getSbuName() {
        return sbuName;
    }

    public void setSbuName(String sbuName) {
        this.sbuName = sbuName;
    }

    public String getSubSbuName() {
        return subSbuName;
    }

    public void setSubSbuName(String subSbuName) {
        this.subSbuName = subSbuName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * @return the issueDate
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * @param issueDate the issueDate to set
     */
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

}
