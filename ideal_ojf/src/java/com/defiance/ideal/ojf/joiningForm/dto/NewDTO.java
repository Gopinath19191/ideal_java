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
public class NewDTO {
      
    
    
    private int[] EDU_TR_deleted;
    private int EDU_TR_deletedX;
    private int[] CER_TR_deleted;
    private int CER_TR_deletedX;

    public int[] getCER_TR_deleted() {
        return CER_TR_deleted;
    }

    public void setCER_TR_deleted(int[] CER_TR_deleted) {
        this.CER_TR_deleted = CER_TR_deleted;
    }

    public int getCER_TR_deletedX() {
        return CER_TR_deletedX;
    }

    public void setCER_TR_deletedX(int CER_TR_deletedX) {
        this.CER_TR_deletedX = CER_TR_deletedX;
    }
    
     private String jfId;
    // private String[] educationalQualificationFileAttachmentStatus;
     private List formFiles = new ArrayList();
     private String[] certificationId;
     private String certificationIdX;
     private String[] jfPreEmpId;  //1=============
     private String[] educationId;
     private String[] namePrevEmp;
     private String[] nameAddPrevEmp;
     private String[] dateOfJoin;
     private String[] desigOnJoin;
     private String[] salaryOnJoin;
     private String[] dateOfRel;
     private String[] desigOnRel;
     private String[] salOnRel;
     private int[] EMP_TR_deleted;
     //2====
     private String[] skillId;
     private String[] skill_category;
     private String[] skill_category1;
     
     private String[] stream;
     private String skillIdX;
     private String[] skill;
     private String[] skill_type;
     private String[] skill_year; 
     private String[] skill_month;
     //3==============
     private String[] degree;
     private String[] qualification;
     private String[] qualification1;
     private String[] year_of_passing;
     private String[] institution;
     private String[] institution1;
     private String institutionX;
     private String[] university;
     private String[] university1;
     private String[] percentage;
     private String[] remarks;
     private String attachmentX;
     private String exp_attachmentX;
     private String[] attachmentXY;
     private String[] exp_attachmentXY;
     private String[] cert_attachmentXY;
     
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
     
      private MultipartFile[]  otherProofDetails;
     private String[] educationalQualificationFileAttachmentCount;
     private String[] educationalQualificationFileAttachmentStatus;
     private int educationalQualificationCount;
     private String[] hiddenMasterId; 
     //other proof variables
    
     
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
    
     private MultipartFile xProof;
    private MultipartFile p2Proof;
    private MultipartFile gradProof;
    private MultipartFile pGradProof;
    private MultipartFile canChequeLeaf;
    private MultipartFile dlProof;
    private MultipartFile empPhotoUpload;
    private MultipartFile empAddProof;
    private MultipartFile passportProof;
     
     
     //public int[] jfEduProofId;
     private String[] jfEduProofId;
     public int jfEduProofdb;
    public String otherProof;
    public int[] exp_year;
    public int[] exp_month;
    public List getOtherProof() {
        return this.formFiles;
    }
    public void setOtherProof(int iIndex, MultipartFile formFile){
       this.formFiles.add(formFile);
    }
    public int[] jfEduId;
    public String[] educationalQualificationStatus;
    public String[] otherQualification;
    public String[] otherYearOfPassing;
    public String[] otherInstitution;
    public String[] otherPercentage;
    public int[] jfSkillId;
    
    private String jfEduProofIdDb;
    private String fileName;
    private String fileType;
    private String fileId;
    private String referenceName;
    private String referenceId;
    private String moduleName;
    
    private String[] otherInstitute;
    private String[] otheruniversity;
    private String otherInstituteName;
    private String otherUniversityName;

    public String[] getOtherInstitute() {
        return otherInstitute;
    }

    public void setOtherInstitute(String[] otherInstitute) {
        this.otherInstitute = otherInstitute;
    }

    public String[] getOtheruniversity() {
        return otheruniversity;
    }

    public void setOtheruniversity(String[] otheruniversity) {
        this.otheruniversity = otheruniversity;
    }

    public String getOtherInstituteName() {
        return otherInstituteName;
    }

    public void setOtherInstituteName(String otherInstituteName) {
        this.otherInstituteName = otherInstituteName;
    }

    public String getOtherUniversityName() {
        return otherUniversityName;
    }

    public void setOtherUniversityName(String otherUniversityName) {
        this.otherUniversityName = otherUniversityName;
    }
       
    public List getFormFiles() {
        return formFiles;
    }

    public void setFormFiles(List formFiles) {
        this.formFiles = formFiles;
    }

    

    public int getJfEduProofdb() {
        return jfEduProofdb;
    }

    public void setJfEduProofdb(int jfEduProofdb) {
        this.jfEduProofdb = jfEduProofdb;
    }

  

    public int[] getJfEduId() {
        return jfEduId;
    }

    public void setJfEduId(int[] jfEduId) {
        this.jfEduId = jfEduId;
    }

    public String[] getEducationalQualificationStatus() {
        return educationalQualificationStatus;
    }

    public void setEducationalQualificationStatus(String[] educationalQualificationStatus) {
        this.educationalQualificationStatus = educationalQualificationStatus;
    }

    public String[] getOtherQualification() {
        return otherQualification;
    }

    public void setOtherQualification(String[] otherQualification) {
        this.otherQualification = otherQualification;
    }

    public String[] getOtherYearOfPassing() {
        return otherYearOfPassing;
    }

    public void setOtherYearOfPassing(String[] otherYearOfPassing) {
        this.otherYearOfPassing = otherYearOfPassing;
    }

    public String[] getOtherInstitution() {
        return otherInstitution;
    }

    public void setOtherInstitution(String[] otherInstitution) {
        this.otherInstitution = otherInstitution;
    }

    public String[] getOtherPercentage() {
        return otherPercentage;
    }

    public void setOtherPercentage(String[] otherPercentage) {
        this.otherPercentage = otherPercentage;
    }

    public int[] getJfSkillId() {
        return jfSkillId;
    }

    public void setJfSkillId(int[] jfSkillId) {
        this.jfSkillId = jfSkillId;
    }
   
    
     
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
      //4=======
      private String[] cert_qualification ;
      private String[] cert_year_of_passing;
      private String[] cert_institution;
      private String[] cert_percentage;
      private String[] cert_remarks;
     
    
          
          

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
     * @return the degree
     */
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
     * @return the educationalQualificationFileAttachmentStatus
     */
    public String[] getEducationalQualificationFileAttachmentStatus() {
        return educationalQualificationFileAttachmentStatus;
    }

    /**
     * @param educationalQualificationFileAttachmentStatus the educationalQualificationFileAttachmentStatus to set
     */
    public void setEducationalQualificationFileAttachmentStatus(String[] educationalQualificationFileAttachmentStatus) {
        this.educationalQualificationFileAttachmentStatus = educationalQualificationFileAttachmentStatus;
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
     * @return the educationalQualificationFileAttachmentCount
     */
    public String[] getEducationalQualificationFileAttachmentCount() {
        return educationalQualificationFileAttachmentCount;
    }

    /**
     * @param educationalQualificationFileAttachmentCount the educationalQualificationFileAttachmentCount to set
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
     * @return the jfEduProofIdDb
     */
    public String getJfEduProofIdDb() {
        return jfEduProofIdDb;
    }

    /**
     * @param jfEduProofIdDb the jfEduProofIdDb to set
     */
    public void setJfEduProofIdDb(String jfEduProofIdDb) {
        this.jfEduProofIdDb = jfEduProofIdDb;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
     * @return the educationalQualificationCount
     */
    public int getEducationalQualificationCount() {
        return educationalQualificationCount;
    }

    /**
     * @param educationalQualificationCount the educationalQualificationCount to set
     */
    public void setEducationalQualificationCount(int educationalQualificationCount) {
        this.educationalQualificationCount = educationalQualificationCount;
    }

    /**
     * @return the jfEduProofId
     */
    public String[] getJfEduProofId() {
        return jfEduProofId;
    }

    /**
     * @param jfEduProofId the jfEduProofId to set
     */
    public void setJfEduProofId(String[] jfEduProofId) {
        this.jfEduProofId = jfEduProofId;
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
     * @return the xProof
     */
    public MultipartFile getxProof() {
        return xProof;
    }

    /**
     * @param xProof the xProof to set
     */
    public void setxProof(MultipartFile xProof) {
        this.xProof = xProof;
    }

    /**
     * @return the p2Proof
     */
    public MultipartFile getP2Proof() {
        return p2Proof;
    }

    /**
     * @param p2Proof the p2Proof to set
     */
    public void setP2Proof(MultipartFile p2Proof) {
        this.p2Proof = p2Proof;
    }

    /**
     * @return the gradProof
     */
    public MultipartFile getGradProof() {
        return gradProof;
    }

    /**
     * @param gradProof the gradProof to set
     */
    public void setGradProof(MultipartFile gradProof) {
        this.gradProof = gradProof;
    }

    /**
     * @return the pGradProof
     */
    public MultipartFile getpGradProof() {
        return pGradProof;
    }

    /**
     * @param pGradProof the pGradProof to set
     */
    public void setpGradProof(MultipartFile pGradProof) {
        this.pGradProof = pGradProof;
    }

    /**
     * @return the canChequeLeaf
     */
    public MultipartFile getCanChequeLeaf() {
        return canChequeLeaf;
    }

    /**
     * @param canChequeLeaf the canChequeLeaf to set
     */
    public void setCanChequeLeaf(MultipartFile canChequeLeaf) {
        this.canChequeLeaf = canChequeLeaf;
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
     * @return the empPhotoUpload
     */
    public MultipartFile getEmpPhotoUpload() {
        return empPhotoUpload;
    }

    /**
     * @param empPhotoUpload the empPhotoUpload to set
     */
    public void setEmpPhotoUpload(MultipartFile empPhotoUpload) {
        this.empPhotoUpload = empPhotoUpload;
    }

    /**
     * @return the empAddProof
     */
    public MultipartFile getEmpAddProof() {
        return empAddProof;
    }

    /**
     * @param empAddProof the empAddProof to set
     */
    public void setEmpAddProof(MultipartFile empAddProof) {
        this.empAddProof = empAddProof;
    }

    /**
     * @return the passportProof
     */
    public MultipartFile getPassportProof() {
        return passportProof;
    }

    /**
     * @param passportProof the passportProof to set
     */
    public void setPassportProof(MultipartFile passportProof) {
        this.passportProof = passportProof;
    }

    /**
     * @return the hiddenMasterId
     */
    public String[] getHiddenMasterId() {
        return hiddenMasterId;
    }

    /**
     * @param hiddenMasterId the hiddenMasterId to set
     */
    public void setHiddenMasterId(String[] hiddenMasterId) {
        this.hiddenMasterId = hiddenMasterId;
    }

    public int[] getExp_year() {
        return exp_year;
    }

    public void setExp_year(int[] exp_year) {
        this.exp_year = exp_year;
    }

    public int[] getExp_month() {
        return exp_month;
    }

    public void setExp_month(int[] exp_month) {
        this.exp_month = exp_month;
    }

    

    
}
