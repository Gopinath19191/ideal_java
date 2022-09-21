/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.joiningForm.dao;
import com.defiance.ideal.ojf.dto.ApplicantDTO;
import com.defiance.ideal.ojf.joiningForm.dto.JoinerFormDTO;
import com.defiance.ideal.ojf.joiningForm.dto.JoinerNewDTO;
import com.defiance.ideal.ojf.joiningForm.dto.MasterDataDTO;
import com.defiance.ideal.ojf.joiningForm.dto.NationalityDTO;
import com.defiance.ideal.ojf.joiningForm.dto.NewDTO;
import com.defiance.ideal.ojf.joiningForm.dto.SourcehireDTO;
import com.defiance.ideal.ojf.shared.CommonConfigurations;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author 15858
 */
public interface JoinerDAO {
    public  List<JoinerFormDTO> getCandidateList(JoinerFormDTO formData);
    public void excelExport(JoinerFormDTO formData, HttpServletResponse response,List<JoinerFormDTO> list);
    public JoinerFormDTO checkTrackNumber(String trackNumber);
    public List<JoinerFormDTO> getJfEmpData(CommonConfigurations ccfg);
    public NationalityDTO getRegionData(int id);
    public List<NationalityDTO> getRegionDetails(int id);
    public List<NationalityDTO> getCityDetails(int id);
    public List<NationalityDTO> getMaritalStatuslist();
    public List<NationalityDTO> getNationalitylist();
    public List<NationalityDTO> getCountryList();
   public List<JoinerFormDTO> getJfPrevEmpData(String id);
    public List<JoinerFormDTO> getEducationData(int id);
    public List<JoinerFormDTO> getCertificationData(int id);
    public List<JoinerFormDTO> getSkillData(int id);
    public List<NationalityDTO> getInstitutionDetails();
    public List<NationalityDTO> getUniversityDetails();
    public List<NationalityDTO> getQualificationDetails();
    public List<NationalityDTO> getDegreeDetails();
    public List<NationalityDTO> getStreamDetails();
    public List<JoinerFormDTO> getJfEduQualifiProofData(String id);
    public void addOrUpdateEmployeeStepOne(JoinerFormDTO formData,JoinerDAO jfi);
    //new added
    public JoinerFormDTO getCandidateDetails(JoinerFormDTO formData);
    public JoinerFormDTO getPassportNumber(String id);
    public String getCcMailNames(String dataVerifiedMailCc);
     public String getsourcehirelisstbyname(String sorcename);
     public List<MasterDataDTO> getStructureDetails(int parentId);
     public List<MasterDataDTO> getEmployeeName(String searchString); 
     public List<MasterDataDTO> getEmployeeNamebyContract(String searchString);
     public String getCcMail(String empIds);
     public void addNewEmployeeDetail(JoinerFormDTO formData);
     public int getLastInsertId();
     public List<NationalityDTO> getSkillDetails(int id);
    

    public void addFileDb(String file_id, String fileName, String contentType, String referenceName, String jfId, String moduleName);
    public void fileDownload(String fileName, String filePath, String fileType, HttpServletResponse response);
    public List<MasterDataDTO> getBandDetails(String parentId);
     public List<MasterDataDTO> getDesignationDetails();
     public List<MasterDataDTO> getPracticeGroup(String structureId);
     public List<MasterDataDTO> getSubPracticeGroup(String practiceGroupId);
     public List<MasterDataDTO> getEmployeeDetailsFromId(String reportingManager);
     public List<SourcehireDTO> getsourcehirelisst(String sorceid);
     public String getportalidbyname(String portalname);
      public List<SourcehireDTO> getjobportallist(String portalid);
      public List<MasterDataDTO> getEmployeeNamebyhr(String searchString);
      public List<JoinerFormDTO> getJfFamilyMemberData(String id);
      public List<JoinerFormDTO> getJfVisaData(String id);
      public List<JoinerFormDTO> getPassportData(String id);
      public List<JoinerFormDTO> getEmergencyContacts(String id);
      public List<JoinerFormDTO> getLicenceDetails(String id);
      public List<NationalityDTO> getVisaList();
      public void addOrUpdateEmployeeStepTwo(NewDTO formData, JoinerDAO jfi);
      //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
      
      //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
      
      public JoinerFormDTO getJoinerMasterData(String id);
      public String getRRFRes(String id);
      public String getEmployeeType(String id);
      public List<JoinerFormDTO> getJfReferrerDTData(String id);
      public List<JoinerFormDTO> getJfReferrerPrevCompData(String id);
      public void addOrUpdateEmployeeStepThree(JoinerFormDTO formData,JoinerDAO jfi);
      
      //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
       public String getNewEmployeeNumber(int empId);
       public Integer getJoinerUserId(String jfId);
       public int addOrUpdateEmployeeFinalSubmit(HttpServletRequest requestObj, String employeeType, String groupId,JoinerFormDTO formData);
       public String getGroupId(JoinerFormDTO formData);
       public String checkWorkEmail(String workEmailId,String employeeCategory,String contractemployeeId);
       public void updateNewEmployeeMailDetail(JoinerFormDTO formData);
       public void updateNewEmployeeDetail(JoinerFormDTO formData);
      //////////////////////////////////
       
        public String getTrackData(String id);
      
     public JoinerFormDTO getJoinerReportData(JoinerFormDTO formData);
     public JoinerFormDTO getAuthorizerDetails(String val);
    public List<JoinerFormDTO> getJfEmpData(JoinerFormDTO formData);
     public List<JoinerFormDTO> getMediInsuranceData(String empId);
    
    // public List<JoinerFormDTO>  getJfReferrerDTData(String id);
    // public List<JoinerFormDTO> getJfReferrerPrevCompData(String id);
     
     
     public void revertRRF(String jf_id);
     
     public void addOrUpdateEmployeeAddress(JoinerFormDTO formData);
     
     public int getJfStatus(int id);
     
     public JoinerFormDTO getEmployeeAddressData(int empId);
     
     public int getEmpId(String employeeNumber);
     
     public void updateEmpAddressData(String jfId, int id);
     
     public int getJfEmpId(String id);
     
     public List<MasterDataDTO> getEmployeeNameOfHr(String searchString);
     public String getRrfId(String rrfRes);
     public JoinerFormDTO getSyncEmpDetails(String n);
       
       /////////////////////////////////

    public int getBankProofFileId(String jfId, String referenceName);

    public void updateFileDb(String fileName, String contentType, int id);
    
    public String getVendorName(String empref);
    
    public String getJobPortalName(String empref);
    public List getDocumentList(String id);
    public JoinerFormDTO getSigleApplicant(String id);
    // public ApplicantDTO getRrfDetails(ApplicantDTO reqDto);
}