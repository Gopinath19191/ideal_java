/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.joiningForm.dao;

import com.defiance.ideal.ojf.controller.SendMailTLS;
import com.defiance.ideal.ojf.dto.ApplicantDTO;
import com.defiance.ideal.ojf.dto.LoginDTO;
import com.defiance.ideal.ojf.joiningForm.dto.JoinerFormDTO;
import com.defiance.ideal.ojf.joiningForm.dto.JoinerNewDTO;
import com.defiance.ideal.ojf.joiningForm.dto.MasterDataDTO;
import com.defiance.ideal.ojf.joiningForm.dto.NationalityDTO;
import com.defiance.ideal.ojf.joiningForm.dto.NewDTO;
import com.defiance.ideal.ojf.joiningForm.dto.SourcehireDTO;
import com.defiance.ideal.ojf.shared.CommonConfigurations;
import com.defiance.ideal.ojf.shared.CommonFunctions;
import com.defiance.ideal.ojf.shared.MailMessages;
import com.defiance.ideal.ojf.shared.SendMail;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 15850
 */
public class JoinerFormImpl extends SqlMapClientDaoSupport implements JoinerDAO {

    public List<JoinerFormDTO> getCandidateList(JoinerFormDTO formData) {
        List<JoinerFormDTO> list = null;
        // JoinerFormDTO[] list = null;
        try {
            //formData.candidateRefNumberSearch,formData.candidateRefNumberSearch,formData.statusSearch,formData.practiceGroupSearch,formData.structureNameSearch,formData.candidateNameSearch,
            System.out.println("candidateRefNumberSearch first time--------------------" + formData.getCandidateRefNumberSearch());
            System.out.println("candidateRefNumberSearch--------------------" + formData.getStatusSearch());
            String candidateName = (formData.getCandidateNameSearch() == null) ? "%%" : "%" + formData.getCandidateNameSearch() + "%";
            String candidateRefNumber = (formData.getCandidateRefNumberSearch() == null) ? "%%" : "%" + formData.getCandidateRefNumberSearch() + "%";
            String companyStructure = (formData.getStructureNameSearch() == null) ? "%%" : "" + formData.getStructureNameSearch() + "%";
            String practiceGroup = (formData.getPracticeGroupSearch() == null) ? "%%" : "" + formData.getPracticeGroupSearch() + "%";
            String status = (formData.getStatusSearch() == null) ? "%%" : "%" + formData.getStatusSearch() + "%";

            formData.setCandidateNameSearch(candidateName);
            formData.setCandidateRefNumberSearch(candidateRefNumber);
            formData.setStructureNameSearch(companyStructure);
            formData.setPracticeGroupSearch(practiceGroup);
            formData.setStatusSearch(status);
            //for testing

            System.out.println("formData in daoimpl==============" + formData);

            list = (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJoinerFormDTO",
                    formData);
            // list = dbCTRL.getCandidateList(formData,CommonConfigurations.CandidateRefPrefix);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }//end of getCandidateList();

    public void excelExport(JoinerFormDTO formData, HttpServletResponse response, List<JoinerFormDTO> listData) {
        try {
            System.out.println("In Excel export:::: = " + formData.getCandidateNameSearch() + "--" + "---" + formData.getCandidateRefNumberSearch() + "--" + formData.getPracticeGroupSearch() + "--" + formData.getStatusSearch());
            String candidateName = (formData.getCandidateNameSearch() == null) ? "%%" : "%" + formData.getCandidateNameSearch() + "%";
            String candidateRefNumber = (formData.getCandidateRefNumberSearch() == null) ? "%%" : "%" + formData.getCandidateRefNumberSearch() + "%";
            String companyStructure = (formData.getStructureNameSearch() == null) ? "%%" : "" + formData.getStructureNameSearch() + "%";
            String practiceGroup = (formData.getPracticeGroupSearch() == null) ? "%%" : "" + formData.getPracticeGroupSearch() + "%";
            String status = (formData.getStatusSearch() == null) ? "%%" : "%" + formData.getStatusSearch() + "%";
            formData.setCandidateNameSearch(candidateName);
            formData.setCandidateRefNumberSearch(candidateRefNumber);
            formData.setStructureNameSearch(companyStructure);
            formData.setPracticeGroupSearch(practiceGroup);
            formData.setStatusSearch(status);
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + "Excel Export.xls" + "\"");
            HSSFWorkbook hssfworkbook = new HSSFWorkbook();
            HSSFSheet sheet = hssfworkbook.createSheet("new sheet");
            HSSFCellStyle cs = hssfworkbook.createCellStyle();
            HSSFDataFormat df = hssfworkbook.createDataFormat();
            cs.setDataFormat(df.getFormat("#,##0.0"));
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("Candidate Ref No");
            rowhead.createCell((short) 1).setCellValue("Candidate Name");
            rowhead.createCell((short) 2).setCellValue("Company Structure");
            rowhead.createCell((short) 3).setCellValue("Practice Group");
            rowhead.createCell((short) 4).setCellValue("Email Id");
            rowhead.createCell((short) 5).setCellValue("Joining Formalities Status");

            String candidateStatus = "";
            int i = 0;
            Iterator itr = listData.iterator();

            while (itr.hasNext()) {
                i++;
                JoinerFormDTO list = (JoinerFormDTO) itr.next();



                HSSFRow row = sheet.createRow((short) i + 1);
                row.createCell((short) 0).setCellValue(list.getRefnumber());
                row.createCell((short) 1).setCellValue(list.getFirstName());
                row.createCell((short) 2).setCellValue(list.getStructureName());
                row.createCell((short) 3).setCellValue(list.getPracticeGroup());
                row.createCell((short) 4).setCellValue(list.getPersonalEmailId1());
                if (list.getStatus().equals("0")) {
                    candidateStatus = "Trigger Mail To Candidate";
                } else if (list.getStatus().equals("1") || list.getStatus().equals("2")) {
                    candidateStatus = "Joining Formalities Initiated";
                } else if (list.getStatus().equals("3")) {
                    candidateStatus = "Joining Formalities Completed check details";
                } else if (list.getStatus().equals("4")) {
                    candidateStatus = "Send Back to employee";
                } else if (list.getStatus().equals("5")) {
                    candidateStatus = "Data Verified";
                } else if (list.getStatus().equals("6")) {
                    candidateStatus = "JF Data Added to iDeal";
                }
                row.createCell((short) 5).setCellValue(candidateStatus);
                //row.setRowStyle(cs);// add jar
            }
            hssfworkbook.write(out);
            out.close();
            out.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }// excel() end ;

    public JoinerFormDTO checkTrackNumber(String trackNumber) {

        JoinerFormDTO masterDataBeans = null;
        try {
            masterDataBeans = (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getTrackNumber",
                    trackNumber);
            // masterDataBeans = dbCTRL.checkTrackNumber(trackNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return masterDataBeans;



    }//end of checkTrackNo

    public List<JoinerFormDTO> getJfEmpData(CommonConfigurations ccfg) {

        List<JoinerFormDTO> joinerFormDto = (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfEmpData",
                ccfg);

        Iterator i = joinerFormDto.iterator();
        while (i.hasNext()) {
            JoinerFormDTO jfd = (JoinerFormDTO) i.next();
            System.out.println("bank file name " + jfd.getBankFileName());
        }

        return joinerFormDto;

    }

    public NationalityDTO getRegionData(int id) {

        NationalityDTO NationalityDTO = (NationalityDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getRegionData",
                id);

        return NationalityDTO;

    }

    public List<NationalityDTO> getRegionDetails(int id) {

        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getRegionDetails", id);

        //return null;

    }

    public List<NationalityDTO> getCityDetails(int id) {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getCityDetails", id);

    }

    public List<NationalityDTO> getMaritalStatuslist() {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getMaritalStatuslist");

    }

    public List<NationalityDTO> getNationalitylist() {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getNationalitylist");

    }

    public List<NationalityDTO> getCountryList() {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getCountryList");

    }

    public List<JoinerFormDTO> getJfPrevEmpData(String id) {


        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfPrevEmpData", id);

    }

    public List<JoinerFormDTO> getEducationData(int id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getEducationData", id);

    }

    public List<JoinerFormDTO> getCertificationData(int id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getCertificationData", id);

    }

    public List<JoinerFormDTO> getSkillData(int id) {
        List<JoinerFormDTO> li = null;
        try {
            li = (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getSkillData", id);
        } catch (Exception e) {

            System.out.println("Exception=================== in getskill impl" + e);
        }
        return li;
    }

    public List<NationalityDTO> getInstitutionDetails() {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getInstitutionDetails");
    }

    public List<NationalityDTO> getUniversityDetails() {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getUniversityDetails");
    }

    public List<NationalityDTO> getQualificationDetails() {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getQualificationDetails");
    }

    public List<NationalityDTO> getDegreeDetails() {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getDegreeDetails");
    }

    public List<NationalityDTO> getStreamDetails() {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getStreamDetails");
    }

    public List<JoinerFormDTO> getJfEduQualifiProofData(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfEduQualifiProofData", id);
    }
    
    public List<JoinerFormDTO> getJfExpProofData(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfExpProofData", id);
    }

    public void addOrUpdateEmployeeStepOne(JoinerFormDTO formData, JoinerDAO jfi) {
        //stepone story.................
        CommonFunctions fileUploadObj = new CommonFunctions();
        System.out.println("=================  " + formData.getCanChequeLeafYes());

        /**
         * ***********************************not getting "canchequeleaf"
         * value***************************************************************
         */
        String moduleName = CommonConfigurations.JF_MODULE_CODE;
        try {
            if (formData.getCanChequeLeaf() != null) {
                if (formData.getCanChequeLeafYes() == null) {
                    formData.setCanChequeLeafYes(formData.getCanChequeLeaf());
                }
                if (formData.getCanChequeLeafYes() != null) {
                    if ("".equals(formData.getCanChequeLeafYes().getOriginalFilename())) {
                        formData.setCanChequeLeafYes(formData.getCanChequeLeaf());
                    }
                }
                if (!"".equals(formData.getCanChequeLeafYes().getOriginalFilename())) {
                    fileUploadObj.fileUpload(formData.getCanChequeLeafYes(), formData.getJfId(), CommonConfigurations.JF_BANK_PROOFNAME, moduleName, jfi, "");
                }
            }
            if (formData.getEmpPhotoUpload() != null) {
                if (!formData.getEmpPhotoUpload().isEmpty()) {
                    fileUploadObj.fileUpload(formData.getEmpPhotoUpload(), formData.getJfId(), CommonConfigurations.JF_JOINERPHOTO_PROOFNAME, moduleName, jfi, "");
                }
            }
            if (formData.getEmpSignatureFile() != null) {
                if (!formData.getEmpSignatureFile().isEmpty()) {
                    fileUploadObj.fileUpload(formData.getEmpSignatureFile(), formData.getJfId(), CommonConfigurations.JF_JOINERSIGNATURE_NAME, moduleName, jfi, "");
                }
            }
            if (formData.getEmpAddProof() != null) {
                fileUploadObj.fileUpload(formData.getEmpAddProof(), formData.getJfId(), CommonConfigurations.JF_JOINERADDRESS_PROOFNAME, moduleName, jfi, "");
            }
            if (formData.getEmpAdharProof() != null) {
                fileUploadObj.fileUpload(formData.getEmpAdharProof(), formData.getJfId(), CommonConfigurations.JF_JOINERADHAR_PROOFNAME, moduleName, jfi, "");
            }
            if (formData.getEmpUanProof() != null) {
                if (!formData.getEmpUanProof().isEmpty()) {
                    fileUploadObj.fileUpload(formData.getEmpUanProof(), formData.getJfId(), CommonConfigurations.JF_JOINERUAN_PROOFNAME, moduleName, jfi, "");
                }
            }
            formData.setDateOfMarriage(CommonFunctions.changeDateFormatToDB(formData.getDateOfMarriage()));
            formData.setDateOfBirth(CommonFunctions.changeDateFormatToDB(formData.getDateOfBirth()));
            if (formData.getBankAccType().equals("1") && formData.getCanChequeLeaf() != null) {
                //dbCTRL.deleteBankProof(formData.getJfId(), CommonConfigurations.JF_BANK_PROOFNAME, CommonConfigurations.JF_MODULE_CODE);
                HashMap map = new HashMap();
                map.put("jfId", formData.getJfId());
                map.put("fileProofType", CommonConfigurations.JF_BANK_PROOFNAME);
                map.put("moduleName", CommonConfigurations.JF_MODULE_CODE);
                getSqlMapClientTemplate().delete("LoginMap.bankproof", map);
            }

            getSqlMapClientTemplate().insert("LoginMap.addOrUpdateEmployeeStepOne", formData);
            getSqlMapClientTemplate().update("LoginMap.addOrUpdateEmployeeStepOne1", formData);
//            dbCTRL.updateNewEmployeeDetailByCandidate(formData.getJfId(), formData);
//            dbCTRL.addOrUpdateEmployeeStepOne(formData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public JoinerFormDTO getCandidateDetails(JoinerFormDTO formData) {
        return (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getCandidateDetails", formData);


    }

    public JoinerFormDTO getPassportNumber(String id) {
        return (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getPassportNumber", id);
    }

    public String getCcMailNames(String dataVerifiedMailCc) {
        String checkexistsvenname = (String) getSqlMapClientTemplate().queryForObject("LoginMap.getCcMailNames",
                dataVerifiedMailCc);
        return checkexistsvenname;

    }

    public String getsourcehirelisstbyname(String sorcename) {

        String checkexistsvenname = (String) getSqlMapClientTemplate().queryForObject("LoginMap.getsourcehirelisstbyname",
                sorcename);
        return checkexistsvenname;
    }

    public List<MasterDataDTO> getStructureDetails(int parentId) {
        List<MasterDataDTO> dtoList = null;

        dtoList = (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getLevelOneStructure", parentId);

        Iterator it = dtoList.iterator();
        while (it.hasNext()) {
            MasterDataDTO dto = (MasterDataDTO) it.next();
            System.out.println("muniiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" + dto.getStructureName());
        }
        return dtoList;
    }

    public List<MasterDataDTO> getEmployeeName(String searchString) {
        return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getEmployeeName", searchString);
    }

    public List<MasterDataDTO> getEmployeeNameOfHr(String searchString) {
        HashMap map = new HashMap();
        map.put("searchString", searchString);
        map.put("structureName", 13);
        return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getEmployeeNameOfHr", map);
    }

    public List<MasterDataDTO> getEmployeeNamebyContract(String searchString) {
        return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getEmployeeNamebyContract", searchString);
    }

    public List<JoinerFormDTO> getRRFDetails(JoinerFormDTO formData) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getRRFDetails", formData);
    }

    public List<JoinerFormDTO> getOjfCcMail(String mc) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getOjfCcMail", mc);
    }

    //getOjfToMail
    public List<JoinerFormDTO> getOjfToMail(String mc) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getOjfToMail", mc);
    }
    //getOjfHrName

    public String getOjfHrName(String hr) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getOjfHrName", hr);
    }
//getOJFDL list CC

    public String getOjfdlCcList() {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getOjfdlCcList");
    }
    // get ojf dl list To
    public String getOjfdlToList() {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getOjfdlToList");
    }
    public void updateMailSuccessStatus( String ojf_id){
        getSqlMapClientTemplate().update("LoginMap.updateMailSuccessStatus", ojf_id);
    }
    public void updateMailFailureStatus( String ojf_id){
        getSqlMapClientTemplate().update("LoginMap.updateMailFailureStatus", ojf_id);
    }
    //get structure cc dl getDlCcStructure
    
    public String getDlCcStructure(String hr) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getDlCcStructure", hr);
    }
    public String getCcMail(String empIds) {

        System.out.println("EMPIDS =========== " + empIds);

        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getCcMail", empIds);
    }

    public void addNewEmployeeDetail(JoinerFormDTO formData) {
        try {

            if (formData.getRrf_id().equals("")) {
                formData.setRrf_id(null);
            }
            int status = Integer.parseInt(formData.getStatus());
            formData.setStatusToInt(status);
        } catch (Exception e) {
            System.out.println("cannot parse============" + e);
        }
        System.out.println("contract type ++++++++ " + formData.getContract_type());
        getSqlMapClientTemplate().insert("LoginMap.addNewEmployeeDetail", formData);
        getSqlMapClientTemplate().update("LoginMap.updateRrfEmpId", formData);
    }

    public int getLastInsertId() {
        return (Integer) getSqlMapClientTemplate().queryForObject("LoginMap.getLastInsertId");

    }

    public int getLastId() {
        return (Integer) getSqlMapClientTemplate().queryForObject("LoginMap.getLastId");

    }

    public void addOrUpdatePassportNumber(JoinerFormDTO formData) {
        Integer passportId = (Integer) getSqlMapClientTemplate().queryForObject("LoginMap.getPassportId", formData.getPassport_id());
        if (passportId == null) {
            getSqlMapClientTemplate().insert("LoginMap.addPassportNumber", formData);
        } else {
            getSqlMapClientTemplate().update("LoginMap.UpdatePassportNumber", formData);
        }
    }    
           
    public void addFileDb(String file_id, String fileName, String contentType, String referenceName, String jfId, String moduleName) {
        HashMap map = new HashMap();
        map.put("id", file_id);
        map.put("fileName", fileName);
        map.put("contentType", contentType);
        map.put("referenceName", referenceName);
        map.put("jfId", jfId);
        map.put("moduleName", moduleName);
        getSqlMapClientTemplate().insert("LoginMap.addFileDb", map);
    }

    public void fileDownload(String fileName, String filePath, String fileType, HttpServletResponse response) {
        try {
            System.out.println("FileTyep:::" + fileType + "Name:::" + fileName + "Path::::" + filePath);
            response.setContentType(fileType);
            response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");

            File file = new File(filePath + "\\" + fileName);
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
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NationalityDTO> getSkillDetails(int streamid) {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getSkillDetails", streamid);

    }

    public List<MasterDataDTO> getBandDetails(String parentId) {
        //List<MasterDataDTO> 
        if (parentId.equals("0")) {
            return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getBandDetails");
            //selectedValue = selectedValue + " and parent_id IS NULL ";
            //selectedValue="NULL";

        } else if (!parentId.equals("0")) {
            //selectedValue = selectedValue + " and parent_id=" + parentId +" ";
            //selectedValue= parentId;
            return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getBandDetails1", parentId);
        }
        return null;


    }

    public List<MasterDataDTO> getDesignationDetails() {
        return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getDesignationDetails");

    }

    public List<MasterDataDTO> getPracticeGroup(String structureId) {
        return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getPracticeGroup", structureId);
    }

    public List<MasterDataDTO> getSubPracticeGroup(String practiceGroupId) {
        return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getSubPracticeGroup", practiceGroupId);
    }

    public List<MasterDataDTO> getEmployeeDetailsFromId(String reportingManager) {
        return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getEmployeeDetailsFromId", reportingManager);
    }

    public List<SourcehireDTO> getsourcehirelisst(String sorceid) {
        return (List<SourcehireDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getsourcehirelisst", sorceid);
    }

    public String getportalidbyname(String portalname) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getportalidbyname", portalname);
    }

    public List<SourcehireDTO> getjobportallist(String portalid) {
        return (List<SourcehireDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getjobportallist", portalid);
    }

    public List<MasterDataDTO> getEmployeeNamebyhr(String searchString) {
        return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getEmployeeNamebyhr", searchString);
    }

    public List<JoinerFormDTO> getJfFamilyMemberData(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfFamilyMemberData", id);

    }

    public List<JoinerFormDTO> getJfVisaData(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfVisaData", id);

    }

    public List<JoinerFormDTO> getPassportData(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getPassportData", id);

    }

    public List<JoinerFormDTO> getEmergencyContacts(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getEmergencyContacts", id);
    }

    public List<JoinerFormDTO> getLicenceDetails(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getLicenceDetails", id);
    }

    public List<JoinerFormDTO> getPrevPfDetails(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getPrevPfDetails", id);
    }

    public List<JoinerFormDTO> getAdharDetails(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getAdharDetails", id);
    }

    public List<NationalityDTO> getVisaList() {
        return (List<NationalityDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getVisaList");
    }

//      public void addOrUpdateEmployeeStepTwo(NewDTO formData, JoinerDAO jfi){
//         // step two ..........
//          System.out.println("addOrUpdateEmployeeStepTwo==== in impl=================");
//           CommonFunctions.TrimSpace(formData);
//        CommonFunctions fileUploadObj = new CommonFunctions();
//        try {
//            // New code by Satheesh Somasan
//            /*List attchementArr = (List) formData.getAttachment();
//            for (int k = 0; k < attchementArr.size(); k++) {
//                FormFile fileccc = (FormFile) attchementArr.get(k);
//                System.out.println("@@@@@@@@@@@"+fileccc.getFileName());
//            }*/
//            
//            for (int i = 0; i < formData.getEducationId().length; i++) {
//               if ( ( formData.getEDU_TR_deleted()[i] == 0 && (!formData.getDegree()[i].equals("") ) ) || (formData.getEDU_TR_deleted()[i] == 1 && !formData.getEducationId()[i].equals("") ) ) {
//                    String fileName = "";
//                    MultipartFile fileData;
//                     System.out.println("somasan"+formData.getAttachmentXY()[i]);
//                    if (i == 0 )
//                        fileData = formData.getAttachment_1();
//                    else if(i == 1)
//                        fileData = formData.getAttachment_2();
//                    else if(i == 2)
//                        fileData = formData.getAttachment_3();
//                    else if(i == 3)
//                        fileData = formData.getAttachment_4();
//                    else if(i == 4)
//                        fileData = formData.getAttachment_5();
//                    else if(i == 5)
//                        fileData = formData.getAttachment_6();
//                    else if(i == 6)
//                        fileData = formData.getAttachment_7();
//                    else if(i == 7)
//                        fileData = formData.getAttachment_8();
//                    else if(i == 8)
//                        fileData = formData.getAttachment_9();
//                    else
//                        fileData = formData.getAttachment_10();
//
//                    if(!fileData.getName().equals("") && fileData!=null ) {
//                        System.out.println("Satheesh"+fileData.getName());
//                        if (fileData.getSize() != 0) {
//                            fileName = formData.getJfId()+"~~"+formData.getDegree()[i]+"~~"+fileData.getName();
//                            String filePath=CommonConfigurations.fileUploadPath;
//                            if (!fileName.equals("")) {
//                                File fileToCreate = new File(filePath, fileName);
//                                if (!fileToCreate.exists()) {
//                                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
//                                    fileOutStream.write(fileData.getBytes());
//                                    fileOutStream.flush();
//                                    fileOutStream.close();
//                                }
//                            }
//                        }
//                    } else {
//                        fileName = formData.getAttachmentXY()[i];
//                        System.out.println("Satheesh Somasan"+i);
//                    }System.out.println(" before addOrUpdateEducation==== in impl=================");
//                    if(formData.getDegree()[i].equals("10") || formData.getDegree()[i].equals("12")) {
//                   //     addOrUpdateEducation(formData.getEducationIdX(), formData.getJfId(), formData.getDegreeX(), formData.getQualification1(), formData.getYear_of_passing()[i] ,formData.getInstitution1()[i], formData.getUniversity1()[i], formData.getPercentage()[i], formData.getRemarks()[i],fileName,0);
//                    } else {
//                 //       addOrUpdateEducation(formData.getEducationId()[i], formData.getJfId(), formData.getDegree()[i], formData.getQualification()[i], formData.getYear_of_passing()[i] ,formData.getInstitution()[i], formData.getUniversity()[i], formData.getPercentage()[i], formData.getRemarks()[i],fileName,0);
//                    }System.out.println(" after addOrUpdateEducation==== in impl=================");
//                }
//            }
//            for (int i = 0; i < formData.getCertificationId().length; i++) {
//                if ( (  (!formData.getCert_qualification()[i].equals("") || !formData.getCert_year_of_passing()[i].equals("") || !formData.getCert_institution()[i].equals("") || !formData.getCert_percentage()[i].equals("") ) ) || ( !formData.getCertificationId()[i].equals("") ) ) {
//                    String fileName = "";
//                    MultipartFile fileData;
//                    if (i == 0 )
//                        fileData = formData.getCert_attachment_1();
//                    else if(i == 1)
//                        fileData = formData.getCert_attachment_2();
//                    else if(i == 2)
//                        fileData = formData.getCert_attachment_3();
//                    else if(i == 3)
//                        fileData = formData.getCert_attachment_4();
//                    else if(i == 4)
//                        fileData = formData.getCert_attachment_5();
//                    else if(i == 5)
//                        fileData = formData.getCert_attachment_6();
//                    else if(i == 6)
//                        fileData = formData.getCert_attachment_7();
//                    else if(i == 7)
//                        fileData = formData.getCert_attachment_8();
//                    else if(i == 8)
//                        fileData = formData.getCert_attachment_9();
//                    else
//                        fileData = formData.getCert_attachment_10();
//
//                    if(!fileData.getName().equals("") && fileData!=null ) {
//                        System.out.println("Satheesh"+fileData.getName());
//                        if (fileData.getSize() != 0) {
//                            fileName = formData.getJfId()+"~~certification~~"+fileData.getName();
//                            String filePath=CommonConfigurations.fileUploadPath;
//                            if (!fileName.equals("")) {
//                                File fileToCreate = new File(filePath, fileName);
//                                if (!fileToCreate.exists()) {
//                                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
//                                    fileOutStream.write(fileData.getBytes());
//                                    fileOutStream.flush();
//                                    fileOutStream.close();
//                                }
//                            }
//                        }
//                    } else {
//                        fileName = formData.getCert_attachmentXY()[i];
//                    }
//                    addOrUpdateEducation(formData.getCertificationId()[i], formData.getJfId(),"c" , formData.getCert_qualification()[i], formData.getCert_year_of_passing()[i] ,formData.getCert_institution()[i], " " , formData.getCert_percentage()[i], formData.getCert_remarks()[i],fileName,0);
//                }
//            }
//            for (int i = 0; i < formData.getSkillId().length; i++) {
//                if ( ( (!formData.getSkill_category()[i].equals("") || !formData.getStream()[i].equals("") || !formData.getSkill()[i].equals("") || !formData.getSkill_type()[i].equals("") || !formData.getSkill_year()[i].equals("") ) ) || ( !formData.getSkillId()[i].equals("") ) ) {
//                   addOrUpdateSkill(formData.getSkillId()[i], formData.getJfId(),formData.getSkill_category()[i], formData.getStream()[i] ,formData.getSkill()[i], formData.getSkill_type()[i], formData.getSkill_year()[i], formData.getSkill_month()[i],0);
//                }
//            }
    // End of New code
//            for (int i = 0; i < formData.getJfPreEmpId().length; i++) {
//                if ( (  (!formData.getNamePrevEmp()[i].equals("") || !formData.getNameAddPrevEmp()[i].equals("") || !formData.getDateOfJoin()[i].equals("") || !formData.getDesigOnJoin()[i].equals("") || !formData.getSalaryOnJoin()[i].equals("") || !formData.getDesigOnRel()[i].equals("") || !formData.getSalOnRel()[i].equals("") ) ) || ( !formData.getJfPreEmpId()[i].equals("") ) ) {
//                   addOrUpdatePreviousEmployer(formData.getJfPreEmpId()[i], formData.getJfId(), formData.getNamePrevEmp()[i], formData.getNameAddPrevEmp()[i], CommonFunctions.changeDateFormatToDB(formData.getDateOfJoin()[i]), formData.getDesigOnJoin()[i], formData.getSalaryOnJoin()[i], CommonFunctions.changeDateFormatToDB(formData.getDateOfRel()[i]), formData.getDesigOnRel()[i], formData.getSalOnRel()[i], 0);
//                }
//            }
//            List myFiles = (List) formData.getOtherProof();
//            for (int k = 0; k < myFiles.size(); k++) {
//                System.out.println("Satheesh Test");
//                if (formData.educationalQualificationFileAttachmentStatus[k].equals("undeleted")) {
//                    MultipartFile fileData = (MultipartFile) myFiles.get(k);
//                    if (fileData.getSize() == 0) {
//                    } else {
//                        System.out.println("Satheesh Test");
//                    
//                        fileUploadObj.fileUpload(fileData, formData.getJfId(), CommonConfigurations.JF_OTHERS_PROOFNAME + k, CommonConfigurations.JF_MODULE_CODE, jfi,"");
//                    }
//                } 
//            }
//        } catch (Exception ex) {
//            System.out.println("exMessage" + ex.getMessage());
//            ex.printStackTrace();
//          //  Logger.getLogger(JoiningFormDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      }
//       public void addOrUpdateEmployeeStepTwo(NewDTO formData, JoinerDAO jfi){
//           System.out.println("addOrUpdateEmployeeStepTwo in impl=================== enterd ");
//           CommonFunctions.TrimSpace(formData);
//            CommonFunctions fileUploadObj = new CommonFunctions();  
//           //  String fileName = "";
//           
//            
//            try{
//                //******************* Previous Employer Details****************
//                for (int i = 0; i < formData.getNamePrevEmp().length; i++) {
//                    System.out.println("before addOrUpdatePreviousEmployer() in impl=====================");
//               if ( (  (!formData.getNamePrevEmp()[i].equals("") || !formData.getNameAddPrevEmp()[i].equals("") || !formData.getDateOfJoin()[i].equals("") || !formData.getDesigOnJoin()[i].equals("") || !formData.getSalaryOnJoin()[i].equals("") || !formData.getDesigOnRel()[i].equals("") || !formData.getSalOnRel()[i].equals("") ) )  ) 
//                  addOrUpdatePreviousEmployer(formData.getJfId(), formData.getNamePrevEmp()[i], formData.getNameAddPrevEmp()[i], CommonFunctions.changeDateFormatToDB(formData.getDateOfJoin()[i]), formData.getDesigOnJoin()[i], formData.getSalaryOnJoin()[i], CommonFunctions.changeDateFormatToDB(formData.getDateOfRel()[i]), formData.getDesigOnRel()[i], formData.getSalOnRel()[i], 0);
//                System.out.println("after addOrUpdatePreviousEmployer() in impl=====================");
//          }//******************* Previous Employer Details****************
//              
//                
//                
//            //***************Technical skills*********************** 
//                 for (int i = 0; i < formData.getSkill_category().length; i++) {
//               if ( ( (!formData.getSkill_category()[i].equals("") || !formData.getStream()[i].equals("") || !formData.getSkill()[i].equals("") || !formData.getSkill_type()[i].equals("") || !formData.getSkill_year()[i].equals("") ) ) ) 
//                     System.out.println("before addOrUpdateSkill() in impl=====================");
//                   addOrUpdateSkill(formData.getJfId(),formData.getSkill_category()[i], formData.getStream()[i] ,formData.getSkill()[i], formData.getSkill_type()[i], formData.getSkill_year()[i], formData.getSkill_month()[i],0);
//                   System.out.println("aftre addOrUpdateSkill() in impl=====================");
//               //}
//            }
//             //***************Technical skills***********************    
//                
//                
//                 System.out.println(" before addOrUpdateEducation==== in impl=================");
//                  for (int i = 0; i < formData.getDegree().length; i++) {
//                 if(formData.getDegree()[i].equals("10") || formData.getDegree()[i].equals("12")) {
//                       addOrUpdateEducation(formData.getJfId(), formData.getDegree()[i], formData.getQualification()[i], formData.getYear_of_passing()[i] ,formData.getInstitution()[i], formData.getUniversity()[i], formData.getPercentage()[i], formData.getRemarks()[i],"abc",0);
//                   } else {
//                   //  addOrUpdateEducation(formData.getEducationId()[i], formData.getJfId(), formData.getDegree()[i], formData.getQualification()[i], formData.getYear_of_passing()[i] ,formData.getInstitution()[i], formData.getUniversity()[i], formData.getPercentage()[i], formData.getRemarks()[i],fileName);
//                    }
//                  }
//                 System.out.println(" after addOrUpdateEducation==== in impl=================");
//                 
//                 
//            }catch(Exception e){
//                System.out.println("Execption========="+e);}
//       }
    //new code==============================
    public void addOrUpdateEmployeeStepTwo(NewDTO formData, JoinerDAO jfi) {
        //        step two ..........
        System.out.println("addOrUpdateEmployeeStepTwo==== in impl=================");
        CommonFunctions.TrimSpace(formData);
        CommonFunctions fileUploadObj = new CommonFunctions();
        try {
//            System.out.println("formData.getEducationId()++++++++++++++++++++++++++" + formData.getEducationId());
//            System.out.println(" formData.getEducationId().length++++++++++++++++++++++++++" + formData.getEducationId().length);
//            // New code by Satheesh Somasan
//            /*List attchementArr = (List) formData.getAttachment();
//            for (int k = 0; k < attchementArr.size(); k++) {
//                FormFile fileccc = (FormFile) attchementArr.get(k);
//                System.out.println("@@@@@@@@@@@"+fileccc.getFileName());
//            }*/
//            
            //formData.setEducationId(null);
            System.out.println("formData.getEducationId() " + formData.getEducationId());
            for (int i = 0; i < formData.getEducationId().length; i++) {
                if (!formData.getDegree()[i].equals("") || !formData.getEducationId()[i].equals("")) {
                    System.out.println("formData.getEducationId()*************************" + formData.getEducationId());
                    String fileName = "";
                    MultipartFile fileData;
//                     System.out.println("somasan"+formData.getAttachmentXY()[i]);
                    if (i == 0) {
                        fileData = formData.getAttachment_1();
                    } else if (i == 1) {
                        fileData = formData.getAttachment_2();
                    } else if (i == 2) {
                        fileData = formData.getAttachment_3();
                    } else if (i == 3) {
                        fileData = formData.getAttachment_4();
                    } else if (i == 4) {
                        fileData = formData.getAttachment_5();
                    } else if (i == 5) {
                        fileData = formData.getAttachment_6();
                    } else if (i == 6) {
                        fileData = formData.getAttachment_7();
                    } else if (i == 7) {
                        fileData = formData.getAttachment_8();
                    } else if (i == 8) {
                        fileData = formData.getAttachment_9();
                    } else {
                        fileData = formData.getAttachment_10();
                    }

                    if (!fileData.getOriginalFilename().equals("") && fileData != null) {
                        System.out.println("Satheesh" + fileData.getOriginalFilename());
                        fileUploadObj.fileUpload(fileData, formData.getJfId(), CommonConfigurations.JF_Education + i, CommonConfigurations.JF_MODULE_CODE, jfi, "");
                        //if (fileData.getSize() != 0) {
                        fileName = formData.getJfId() + "~~" + formData.getDegree()[i] + "~~" + fileData.getOriginalFilename();
                        System.out.println("fileName ++++++++++++++++++" + fileName);
                        if (!fileName.equals("")) {
                            String filePath = CommonConfigurations.fileUploadPath;

                            // if (!fileName.equals("")) {
                            File fileToCreate = new File(filePath, fileName);
                            if (!fileToCreate.exists()) {
                                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                                fileOutStream.write(fileData.getBytes());
                                fileOutStream.flush();
                                fileOutStream.close();
                            }
                            //}

                        }
                    } else {
                        fileName = formData.getAttachmentXY()[i];
                        System.out.println("Satheesh Somasan" + i);
                    }
                    System.out.println(" before addOrUpdateEducation==== in impl=================");
                    if (formData.getDegree()[i].equals("10") || formData.getDegree()[i].equals("12")) {
                        System.out.println("if =============");
                        addOrUpdateEducation(formData.getEducationId()[i], formData.getJfId(), formData.getDegree()[i], formData.getQualification1()[i], formData.getYear_of_passing()[i], formData.getInstitution1()[i], formData.getUniversity1()[i], formData.getPercentage()[i], formData.getRemarks()[i], fileName, formData.getEDU_TR_deleted()[i], "", "");                        
                    } else {
                        System.out.println("esle==================");
                        addOrUpdateEducation(formData.getEducationId()[i], formData.getJfId(), formData.getDegree()[i], formData.getQualification()[i], formData.getYear_of_passing()[i], formData.getInstitution()[i],  formData.getUniversity()[i], formData.getPercentage()[i], formData.getRemarks()[i], fileName, formData.getEDU_TR_deleted()[i], formData.getOtherInstitute()[i], formData.getOtheruniversity()[i]);
                    }
                    System.out.println(" after addOrUpdateEducation==== in impl=================");
                    //file add to DB
                }
            }

            for (int i = 0; i < formData.getCertificationId().length; i++) {
                if (((!formData.getCert_qualification()[i].equals("") || !formData.getCert_year_of_passing()[i].equals("") || !formData.getCert_institution()[i].equals("") || !formData.getCert_percentage()[i].equals(""))) || (!formData.getCertificationId()[i].equals(""))) {
                    String fileName = "";
                    MultipartFile fileData;
                    if (i == 0) {
                        fileData = formData.getCert_attachment_1();
                    } else if (i == 1) {
                        fileData = formData.getCert_attachment_2();
                    } else if (i == 2) {
                        fileData = formData.getCert_attachment_3();
                    } else if (i == 3) {
                        fileData = formData.getCert_attachment_4();
                    } else if (i == 4) {
                        fileData = formData.getCert_attachment_5();
                    } else if (i == 5) {
                        fileData = formData.getCert_attachment_6();
                    } else if (i == 6) {
                        fileData = formData.getCert_attachment_7();
                    } else if (i == 7) {
                        fileData = formData.getCert_attachment_8();
                    } else if (i == 8) {
                        fileData = formData.getCert_attachment_9();
                    } else {
                        fileData = formData.getCert_attachment_10();
                    }

                    if (!fileData.getOriginalFilename().equals("") && fileData != null) {
                        System.out.println("Satheesh" + fileData.getOriginalFilename());
                        fileUploadObj.fileUpload(fileData, formData.getJfId(), CommonConfigurations.JF_Certification + i, CommonConfigurations.JF_MODULE_CODE, jfi, "");
                        // if (fileData.getSize() != 0) {

                        fileName = formData.getJfId() + "~~certification~~" + fileData.getOriginalFilename();
                        if (!fileName.equals("")) {
                            String filePath = CommonConfigurations.fileUploadPath;
                            //if (!fileName.equals("")) {
                            File fileToCreate = new File(filePath, fileName);
                            if (!fileToCreate.exists()) {
                                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                                fileOutStream.write(fileData.getBytes());
                                fileOutStream.flush();
                                fileOutStream.close();
                            }
                            //}
                        }
                    } else {
                        fileName = formData.getCert_attachmentXY()[i];
                    }
                    System.out.println(" before certification stroting method =================");
                    addOrUpdateEducation1(formData.getCertificationId()[i], formData.getJfId(), "c", formData.getCert_qualification()[i], formData.getCert_year_of_passing()[i], formData.getCert_institution()[i], " ", formData.getCert_percentage()[i], formData.getCert_remarks()[i], fileName, formData.getCER_TR_deleted()[i]);
                    System.out.println(" after certification stroting method =================");
                }
            }
            
            
            

            for (int i = 0; i < formData.getSkillId().length; i++) {
                if (((!formData.getSkill_category()[i].equals("") || !formData.getStream()[i].equals("") || !formData.getSkill()[i].equals("") || !formData.getSkill_type()[i].equals("") || !formData.getSkill_year()[i].equals(""))) || (!formData.getSkillId()[i].equals(""))) {
                    addOrUpdateSkill(formData.getSkillId()[i], formData.getJfId(), formData.getSkill_category()[i], formData.getStream()[i], formData.getSkill()[i], formData.getSkill_type()[i], formData.getSkill_year()[i], formData.getSkill_month()[i], 0);
                }
            }
            // End of New code

            for (int i = 0; i < formData.getJfPreEmpId().length; i++) {
                System.out.println("formData.getJfPreEmpId().length==============" + formData.getJfPreEmpId().length);
                if ((formData.getEMP_TR_deleted()[i] == 0 && (!formData.getNamePrevEmp()[i].equals("") || !formData.getNameAddPrevEmp()[i].equals("") || !formData.getDateOfJoin()[i].equals("") || !formData.getDesigOnJoin()[i].equals("") || !formData.getSalaryOnJoin()[i].equals("") || !formData.getDesigOnRel()[i].equals("") || !formData.getSalOnRel()[i].equals(""))) || (formData.getEMP_TR_deleted()[i] == 1 && !formData.getJfPreEmpId()[i].equals(""))) {
                    //addOrUpdatePreviousEmployer(formData.getJfPreEmpId()[i], formData.getJfId(), formData.getNamePrevEmp()[i], formData.getNameAddPrevEmp()[i], CommonFunctions.changeDateFormatToDB(formData.getDateOfJoin()[i]), formData.getDesigOnJoin()[i], formData.getSalaryOnJoin()[i], CommonFunctions.changeDateFormatToDB(formData.getDateOfRel()[i]), formData.getDesigOnRel()[i], formData.getSalOnRel()[i], 0);
                    //file attachment start
                    String fileName = "";
                    MultipartFile fileData;
                    if (i == 0) {
                        fileData = formData.getExp_attachment_1();
                    } else if (i == 1) {
                        fileData = formData.getExp_attachment_2();
                    } else if (i == 2) {
                        fileData = formData.getExp_attachment_3();
                    } else if (i == 3) {
                        fileData = formData.getExp_attachment_4();
                    } else if (i == 4) {
                        fileData = formData.getExp_attachment_5();
                    } else if (i == 5) {
                        fileData = formData.getExp_attachment_6();
                    } else if (i == 6) {
                        fileData = formData.getExp_attachment_7();
                    } else if (i == 7) {
                        fileData = formData.getExp_attachment_8();
                    } else if (i == 8) {
                        fileData = formData.getExp_attachment_9();
                    } else {
                        fileData = formData.getExp_attachment_10();
                    }

                    if (!fileData.getOriginalFilename().equals("") && fileData != null) {
                        System.out.println("Satheesh" + fileData.getOriginalFilename());
                        fileUploadObj.fileUpload(fileData, formData.getJfId(), CommonConfigurations.JF_Experience + i, CommonConfigurations.JF_MODULE_CODE, jfi, "");
                        // if (fileData.getSize() != 0) {

                        fileName = formData.getJfId() + "~~experience~~" + fileData.getOriginalFilename();
                        if (!fileName.equals("")) {
                            String filePath = CommonConfigurations.fileUploadPath;
                            //if (!fileName.equals("")) {
                            File fileToCreate = new File(filePath, fileName);
                            if (!fileToCreate.exists()) {
                                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                                fileOutStream.write(fileData.getBytes());
                                fileOutStream.flush();
                                fileOutStream.close();
                            }
                            //}
                        }
                    } else {
                        fileName = formData.getExp_attachmentXY()[i];
                    }
                    //end
                    List<String> dateofjoinings = getDateOfJoining(formData.getJfId());
                    List<String> companynames = getCompanyNames(formData.getJfId());
                    List<String> salarysOnJoining = getsalarysOnJoining(formData.getJfId());


                    if (!dateofjoinings.contains(CommonFunctions.changeDateFormatToDB(formData.getDateOfJoin()[i]))) {

                        addOrUpdatePreviousEmployer(formData.getJfPreEmpId()[i], formData.getJfId(), formData.getNamePrevEmp()[i], formData.getNameAddPrevEmp()[i], CommonFunctions.changeDateFormatToDB(formData.getDateOfJoin()[i]), formData.getDesigOnJoin()[i], formData.getSalaryOnJoin()[i], CommonFunctions.changeDateFormatToDB(formData.getDateOfRel()[i]), formData.getDesigOnRel()[i], formData.getSalOnRel()[i], 0, formData.getExp_year()[i],fileName, formData.getExp_month()[i]);
                    } else {
                        UpdatePreviousEmployer(formData.getJfPreEmpId()[i], formData.getJfId(), formData.getNamePrevEmp()[i], formData.getNameAddPrevEmp()[i], CommonFunctions.changeDateFormatToDB(formData.getDateOfJoin()[i]), formData.getDesigOnJoin()[i], formData.getSalaryOnJoin()[i], CommonFunctions.changeDateFormatToDB(formData.getDateOfRel()[i]), formData.getDesigOnRel()[i], formData.getSalOnRel()[i], formData.getEMP_TR_deleted()[i], formData.getExp_year()[i],fileName, formData.getExp_month()[i]);
                    }
                }
            }

            System.out.println("educationalQualificationFileAttachmentCount" + formData.getEducationalQualificationFileAttachmentCount().length);
            System.out.println("file===============" + formData.getOtherProof0());
            System.out.println("file===============" + formData.getOtherProof1());


            for (int i = 0; i <= formData.getEducationalQualificationFileAttachmentCount().length; i++) {

                String fileName = "";
                MultipartFile fileData = null;
                if (i == 0) {
                    fileData = formData.getOtherProof0();
                } else if (i == 1) {
                    fileData = formData.getOtherProof1();
                } else if (i == 2) {
                    fileData = formData.getOtherProof2();
                } else if (i == 3) {
                    fileData = formData.getOtherProof3();
                } else if (i == 4) {
                    fileData = formData.getOtherProof4();
                } else if (i == 5) {
                    fileData = formData.getOtherProof5();
                } else if (i == 6) {
                    fileData = formData.getOtherProof6();
                } else if (i == 7) {
                    fileData = formData.getOtherProof7();
                } else if (i == 8) {
                    fileData = formData.getOtherProof8();
                } else if (i == 9) {
                    fileData = formData.getOtherProof9();
                } else if (i == 10) {
                    fileData = formData.getOtherProof0();
                }
                if (fileData != null) {
                    if (!fileData.getOriginalFilename().equals("") && fileData != null) {
                        System.out.println("Satheesh" + fileData.getOriginalFilename());
                        fileUploadObj.fileUpload(fileData, formData.getJfId(), CommonConfigurations.JF_OTHERS_PROOFNAME + i, CommonConfigurations.JF_MODULE_CODE, jfi, "");
                        if (fileData.getSize() != 0) {

                            fileName = formData.getJfId() + "~~otherProof~~" + fileData.getOriginalFilename();
                            String filePath = CommonConfigurations.fileUploadPath;
                            if (!fileName.equals("")) {
                                File fileToCreate = new File(filePath, fileName);
                                if (!fileToCreate.exists()) {
                                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                                    fileOutStream.write(fileData.getBytes());
                                    fileOutStream.flush();
                                    fileOutStream.close();
                                }
                            }
                        }
                    }
                }
            }








            // other proof Attachment


        } catch (Exception ex) {
            System.out.println("exMessage" + ex);
            ex.printStackTrace();
//          //  Logger.getLogger(JoiningFormDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addOrUpdateEducation(String educationId, String jfId, String degree, String qualification1, String year_of_passing, String institution1, String university1, String percentage, String remarks, String fileName, int EDU_TR_deleted, String otherInstitute, String otheruniversity) {
        System.out.println(" befor addOrUpdateEducation () in impl");

        JoinerFormDTO formData1 = new JoinerFormDTO();
        System.out.println("educationId=======================" + educationId);
        if (educationId.equals("")) {
            System.out.println("educationId if+++++++++++++++");
            //formData1.setEducationIdX("1");
        }

        // else{formData1.setEducationIdX(educationId);}

        formData1.setEducationIdX(educationId);

        formData1.setJfId(jfId);
        formData1.setDegreeX(degree);
        formData1.setQualificationX(qualification1);
        formData1.setYear_of_passingX(year_of_passing);
        formData1.setInstitutionX(institution1);
        formData1.setOtherInstitute(otherInstitute);
        formData1.setOtheruniversity(otheruniversity);
        formData1.setUniversityX(university1);
        formData1.setPercentageX(percentage);
        formData1.setRemarksX(remarks);
//         if(!fileName.equals("")){
//         formData1.setFileName(fileName);
//         getSqlMapClientTemplate().insert("LoginMap.addOrUpdateEducation", formData1);
//         }
        formData1.setEDU_TR_deletedX(EDU_TR_deleted);
//         else{ 
//         getSqlMapClientTemplate().insert("LoginMap.addOrUpdateEducationWithouFileName", formData1);
//         }
//        System.out.println("other university  " +OtherUniversity );
//       
//        System.out.println("other institute  " +OtherInstitute);
        formData1.setFileName(fileName);
        if (!fileName.equals("")) {
            if (educationId.equals("") || educationId == null) {
                getSqlMapClientTemplate().insert("LoginMap.addOrUpdateEducation", formData1);
            } else {
                getSqlMapClientTemplate().update("LoginMap.updateEducation", formData1);
            }
        }
        System.out.println(" After addOrUpdateEducation () in impl");
    }

    public void addOrUpdateEducation1(String educationId, String jfId, String degree, String qualification1, String year_of_passing, String institution1, String university1, String percentage, String remarks, String fileName, int EDU_TR_deleted) {
        System.out.println(" befor addOrUpdateEducation () in impl");
        JoinerFormDTO formData1 = new JoinerFormDTO();
        System.out.println("educationId=======================" + educationId);
        if (educationId.equals("")) {
            formData1.setCertificationIdX("2");
        } else {
            formData1.setCertificationIdX(educationId);
        }

        formData1.setJfId(jfId);
        formData1.setDegreeX(degree);
        formData1.setQualificationX(qualification1);
        formData1.setYear_of_passingX(year_of_passing);
        formData1.setInstitutionX(institution1);
        formData1.setUniversityX(university1);
        formData1.setPercentageX(percentage);
        formData1.setRemarksX(remarks);
        formData1.setCER_TR_deletedX(EDU_TR_deleted);

        if (!fileName.equals("")) {
            formData1.setFileName(fileName);
            if (educationId.equals("") || educationId == null) {
                getSqlMapClientTemplate().insert("LoginMap.addOrUpdateEducation1", formData1);
            } else {
                getSqlMapClientTemplate().update("LoginMap.updateEducation1", formData1);
            }
        }
        //else formData1.setFileName(fileName);
        // formData1.setFileName(fileName);}
        //  formData1.setEDU_TR_deletedX(EDU_TR_deleted);
//        else 
//         getSqlMapClientTemplate().insert("LoginMap.addOrUpdateEducation123", formData1);}
//         System.out.println(" After addOrUpdateEducation () in impl");
    }

    public void addOrUpdateSkill(String skillId, String jfId, String skill_category, String stream, String skill, String skill_type, String skill_year, String skill_month, int SK_TR_deleted) {
        System.out.println(" Before addOrUpdateSkill() in impl******");
        JoinerFormDTO formData1 = new JoinerFormDTO();
//        if (skillId.equals("")) {
//            skillId = "1";
//        }
        formData1.setSkillIdX(skillId);
        formData1.setJfId(jfId);
        formData1.setSkill_categoryX(skill_category);
        formData1.setStreamX(stream);
        formData1.setSkillX(skill);
        formData1.setSkill_typeX(skill_type);
        formData1.setSkill_yearX(skill_year);
        formData1.setSkill_monthX(skill_month);
        //    formData1.setSK_TR_deletedX(SK_TR_deleted);
        if (skillId == null || "".equals(skillId) || "0".equals(skillId)) {
            System.out.println("inside addSkill **********");
            getSqlMapClientTemplate().insert("LoginMap.addSkill", formData1);
        } else {
            System.out.println("outside addSkill ********** " + skillId);
            getSqlMapClientTemplate().insert("LoginMap.updateSkill", formData1);
        }
        System.out.println(" After addOrUpdateSkill() in impl******");
    }

    public void addOrUpdatePreviousEmployer(String jfPreEmpId, String jfId, String namePrevEmp, String nameAddPrevEmp, String dateOfJoin, String desigOnJoin, String salaryOnJoin, String dateOfRel, String desigOnRel, String salOnRel, int EMP_TR_deleted, int exp_year, String fileName, int exp_month) {
        System.out.println(" Before addOrUpdatePreviousEmployer () in impl");
        JoinerFormDTO formData1 = new JoinerFormDTO();
//        int experience = 0;
//        experience = (exp_year*12)+exp_month;
        if (!jfPreEmpId.equals("")) {
            formData1.setJfPreEmpIdX(jfPreEmpId);
        }

        formData1.setJfId(jfId);
        formData1.setNamePrevEmpDb(namePrevEmp);
        formData1.setNameAddPrevEmpX(nameAddPrevEmp);
        formData1.setDateOfJoinX(dateOfJoin);
        formData1.setDesigOnJoinX(desigOnJoin);
        formData1.setSalaryOnJoinX(salaryOnJoin);
        formData1.setDateOfRelX(dateOfRel);
        formData1.setDesigOnRelX(desigOnRel);
        formData1.setSalOnRelX(salOnRel);
        formData1.setEMP_TR_deletedX(EMP_TR_deleted);
        formData1.setExpYear(exp_year);
        formData1.setExpMonth(exp_month);
        //formData1.setExperience(experience);
        //pojo=QueryforList("abc"dateof joining)

        formData1.setFileName(fileName);
        getSqlMapClientTemplate().insert("LoginMap.addOrUpdatePreviousEmployer", formData1);
        System.out.println(" After addOrUpdatePreviousEmployer () in impl");
    }

    public void UpdatePreviousEmployer(String jfPreEmpId, String jfId, String namePrevEmp, String nameAddPrevEmp, String dateOfJoin, String desigOnJoin, String salaryOnJoin, String dateOfRel, String desigOnRel, String salOnRel, int EMP_TR_deleted, int exp_year, String fileName, int exp_month) {
        System.out.println(" Before UpdatePreviousEmployer () in impl");
        JoinerFormDTO formData1 = new JoinerFormDTO();
//        int experience = 0;
//        experience = (exp_year*12)+exp_month;

        formData1.setJfId(jfId);
        formData1.setNamePrevEmpDb(namePrevEmp);
        formData1.setNameAddPrevEmpX(nameAddPrevEmp);
        formData1.setDateOfJoinX(dateOfJoin);
        formData1.setDesigOnJoinX(desigOnJoin);
        formData1.setSalaryOnJoinX(salaryOnJoin);
        formData1.setDateOfRelX(dateOfRel);
        formData1.setDesigOnRelX(desigOnRel);
        formData1.setSalOnRelX(salOnRel);
        formData1.setEMP_TR_deletedX(EMP_TR_deleted);
        formData1.setExpYear(exp_year);
        formData1.setExpMonth(exp_month);
        //formData1.setExperience(experience);
        //pojo=QueryforList("abc"dateof joining)

        if (jfPreEmpId != null && !jfPreEmpId.equals("")) {
            formData1.setJfPreEmpIdX(jfPreEmpId);
        }

//        
//            String id=(String) getSqlMapClientTemplate().queryForObject("LoginMap.getPreviousEmployerId", formData1);  
//            formData1.setJfPreEmpIdX(id); 


        formData1.setFileName(fileName);
        getSqlMapClientTemplate().update("LoginMap.UpdatePreviousEmployer", formData1);
        System.out.println(" After UpdatePreviousEmployer () in impl");
    }

    public JoinerFormDTO getJoinerMasterData(String id) {
        System.out.println("in joinerImpl*****" + id);
        return (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getJoinerMasterData", id);

    }

    public JoinerFormDTO getJoinerMasterDataBillable(String id) {
        System.out.println("JFFFFID " + id);
        return (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getJoinerMasterDataBillable", id);
    }

    public String getRRFRes(String id) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getRRFRes", id);

    }

    public String getEmployeeType(String id) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getEmployeeType", id);
    }

    public List<JoinerFormDTO> getJfReferrerDTData(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfReferrerDTData", id);
    }

    public List<JoinerFormDTO> getJfReferrerPrevCompData(String id) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfReferrerPrevCompData", id);
    }
// step3 story

    public void addOrUpdateEmployeeStepThree(JoinerFormDTO formData, JoinerDAO jfi) {
        System.out.println(" Started addOrUpdateEmployeeStepThree() in IMPL======================");
        CommonFunctions.TrimSpace(formData);
        try {

            CommonFunctions fileUploadObj = new CommonFunctions();
            if (formData.getDlProof() != null) {
                fileUploadObj.fileUpload(formData.getDlProof(), formData.getJfId(), CommonConfigurations.JF_DL_PROOFNAME, CommonConfigurations.JF_MODULE_CODE, jfi, formData.getDlProofID());
            }
            formData.setDlDateOfIssue(CommonFunctions.changeDateFormatToDB(formData.getDlDateOfIssue()));
            formData.setDlDateOfExpiry(CommonFunctions.changeDateFormatToDB(formData.getDlDateOfExpiry()));
            System.out.println(" Before addOrUpdateEmployeeLicence() in IMPL)======================");
            addOrUpdateEmployeeLicence(formData);
            System.out.println(" After addOrUpdateEmployeeLicence() in IMPL)======================");

            for (int i = 0; i < formData.getJfFamId().length; i++) {
                if ((formData.getDEP_TR_deleted()[i] == 0 && (!formData.getNameOfRelation()[i].equals("") || !formData.getRelationShip()[i].equals("") || !formData.getDobRelation()[i].equals(""))) || (formData.getDEP_TR_deleted()[i] == 1 && !formData.getJfFamId()[i].equals(""))) {
                    System.out.println(" Before addOrUpdateFamilyDetails() in IMPL)======================");
                    addOrUpdateFamilyDetails(formData.getJfFamId()[i], formData.getJfId(), formData.getNameOfRelation()[i], formData.getRelationShip()[i], CommonFunctions.changeDateFormatToDB(formData.getDobRelation()[i]), formData.getOccupationOfRel()[i], formData.getMedicalInsurance()[i], formData.getLifeInsurance()[i], formData.getDEP_TR_deleted()[i]);
                    System.out.println(" After addOrUpdateFamilyDetails() in IMPL)======================");
                }
            }

            for (int i = 0; i < formData.getPassportId().length; i++) {
                if ((formData.getPASS_TR_deleted()[i] == 0 && (!formData.getPassportNumber()[i].equals("") || !formData.getPassDateOfIssue()[i].equals("") || !formData.getPassDateOfExpiry()[i].equals("") || !formData.getPassPlaceOfIssue()[i].equals(""))) || (formData.getPASS_TR_deleted()[i] == 1 && !formData.getPassportId()[i].equals(""))) {
                    String fileName = "";
                    MultipartFile fileData;
                    System.out.println("somasan" + formData.getPassAttachmentXY()[i]);
                    if (i == 0) {
                        fileData = formData.getPassAttachment_1();
                    } else if (i == 1) {
                        fileData = formData.getPassAttachment_2();
                    } else if (i == 2) {
                        fileData = formData.getPassAttachment_3();
                    } else if (i == 3) {
                        fileData = formData.getPassAttachment_4();
                    } else if (i == 4) {
                        fileData = formData.getPassAttachment_5();
                    } else if (i == 5) {
                        fileData = formData.getPassAttachment_6();
                    } else if (i == 6) {
                        fileData = formData.getPassAttachment_7();
                    } else if (i == 7) {
                        fileData = formData.getPassAttachment_8();
                    } else if (i == 8) {
                        fileData = formData.getPassAttachment_9();
                    } else {
                        fileData = formData.getPassAttachment_10();
                    }

                    if (!fileData.getOriginalFilename().equals("") && fileData != null) {
                        System.out.println("Satheesh" + fileData.getOriginalFilename());
                        System.out.println("fileData size" + fileData.getSize());
                        //if (fileData.getSize() != 0) {
                        fileName = formData.getJfId() + "~~passport~~" + fileData.getOriginalFilename();
                        if (!fileName.equals("")) {
                            String filePath = CommonConfigurations.fileUploadPath;
                            //if (!fileName.equals("")) {
                            File fileToCreate = new File(filePath, fileName);
                            if (!fileToCreate.exists()) {
                                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                                fileOutStream.write(fileData.getBytes());
                                fileOutStream.flush();
                                fileOutStream.close();
                            }
                            //}
                        }
                    } else {
                        fileName = formData.getPassAttachmentXY()[i];
                        System.out.println("Satheesh Somasan" + i);
                    }
                    System.out.println(" Before addOrUpdatePassport() in IMPL)======================");

                    //  List<Integer> passportIds=getPassportIds(formData.getJfId());


                    System.out.println("deleted ___________" + formData.getPASS_TR_deleted()[i]);
                    addOrUpdatePassport(formData.getPassportId()[i], formData.getJfId(), formData.getPassportNumber()[i], CommonFunctions.changeDateFormatToDB(formData.getPassDateOfIssue()[i]), CommonFunctions.changeDateFormatToDB(formData.getPassDateOfExpiry()[i]), formData.getPassPlaceOfIssue()[i], fileName, formData.getPASS_TR_deleted()[i]);

                    System.out.println(" After addOrUpdatePassport() in IMPL)======================");
                }
            }

            for (int k = 0; k < formData.getJfVisaId().length; k++) {
                if ((formData.getVISA_TR_deleted()[k] == 0 && (!formData.getVisaCountry()[k].equals("") || !formData.getVisaType()[k].equals("") || !formData.getVisaDateOfIssue()[k].equals("") || !formData.getVisaDateOfExpiry()[k].equals("") || !formData.getReferenceNumber()[k].equals(""))) || (formData.getVISA_TR_deleted()[k] == 1 && !formData.getJfVisaId()[k].equals(""))) {
                    System.out.println(" Before addOrUpdateVisaDetails() in IMPL)======================");
                    addOrUpdateVisaDetails(formData.getJfVisaId()[k], formData.getJfId(), formData.getVisaCountry()[k], formData.getVisaType()[k], CommonFunctions.changeDateFormatToDB(formData.getVisaDateOfIssue()[k]), CommonFunctions.changeDateFormatToDB(formData.getVisaDateOfExpiry()[k]), formData.getReferenceNumber()[k], formData.getPlaceOfIssue()[k], formData.getEntries()[k], formData.getVISA_TR_deleted()[k]);
                    System.out.println(" After addOrUpdateVisaDetails() in IMPL)======================");
                }
            }

            for (int k = 0; k < formData.getEmergencyId().length; k++) {
                if ((formData.getEMERGENCY_TR_deleted()[k] == 0 && (!formData.getName()[k].equals("") || !formData.getRelationShip()[k].equals("") || !formData.getMobile_number()[k].equals(""))) || (formData.getEMERGENCY_TR_deleted()[k] == 1 && !formData.getEmergencyId()[k].equals(""))) {
                    System.out.println(" Before addOrUpdateEmergencyContacts() in IMPL)======================");
                    addOrUpdateEmergencyContacts(formData.getEmergencyId()[k], formData.getJfId(), formData.getName()[k], formData.getRelationship()[k], formData.getMobile_number()[k], formData.getHome_phone_number()[k], formData.getWork_phone_number()[k], formData.getEMERGENCY_TR_deleted()[k]);
                    System.out.println(" After addOrUpdateEmergencyContacts() in IMPL)======================");
                }
            }

        } catch (Exception ex) {
            System.out.println("addOrUpdateEmployeeStepThree----------" + ex);
            //Logger.getLogger(JoiningFormDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addOrUpdateEmployeeLicence(JoinerFormDTO formData) {

        getSqlMapClientTemplate().insert("LoginMap.addOrUpdateEmployeeLicence", formData);
    }

    public void addOrUpdateFamilyDetails(String id, String jf_Id, String name, String relationship, String date_birth, String occupation, int medicalInsurance, int nominee, int deleted) {
        JoinerFormDTO formData1 = new JoinerFormDTO();
        if (!id.equals("")) {
            formData1.setJfFamIdDb(Integer.parseInt(id));
        }
        //formData1.setJfFamIdDb(1);
        formData1.setJfId(jf_Id);
        formData1.setNameOfRelationDb(name);
        formData1.setRelationShipDb(relationship);
        formData1.setDobRelationDb(date_birth);
        formData1.setOccupationOfRelDb(occupation);
        formData1.setMedicalInsuranceDb(medicalInsurance);
        formData1.setLifeInsuranceDb(nominee);
        formData1.setEDU_TR_deletedX(deleted);

        getSqlMapClientTemplate().insert("LoginMap.addOrUpdateFamilyDetails", formData1);
    }

    public List<Integer> getPassportIds(String jfId) {
        return (List<Integer>) getSqlMapClientTemplate().queryForList("LoginMap.getPassportIds", jfId);

        //return null;

    }

    public void addOrUpdatePassport(String passportId, String jf_id, String passport_number,
            String passport_date_issue, String passport_date_expire, String passport_place_issue, String attachment, int deleted) {

        JoinerFormDTO formData1 = new JoinerFormDTO();
        System.out.println("PPPPPPPPPPasspoerId" + passportId);
        formData1.setPassportIdX(passportId);
        formData1.setJfId(jf_id);
        formData1.setPassportNumberX(passport_number);
        formData1.setPassDateOfIssueX(passport_date_issue);
        formData1.setPassDateOfExpiryX(passport_date_expire);
        formData1.setPassPlaceOfIssueX(passport_place_issue);

        System.out.println("attachment " + attachment);
        if (!attachment.equals("")) {
            formData1.setFileName(attachment);
            formData1.setEDU_TR_deletedX(deleted);
            System.out.println("deleted " + formData1.getEDU_TR_deletedX());
            if (passportId == null || passportId.equals("")) {
                getSqlMapClientTemplate().insert("LoginMap.addOrUpdatePassport", formData1);
            } else {
                getSqlMapClientTemplate().update("LoginMap.UpdatePassport", formData1);
            }
        }
    }

//  public void UpdatePassport(String passportId, String jf_id, String passport_number,
//            String passport_date_issue, String passport_date_expire, String passport_place_issue, String attachment, int deleted){
//   
//      JoinerFormDTO formData1=new JoinerFormDTO();
//      formData1.setPassportIdX(passportId);
//      formData1.setJfId(jf_id);
//      formData1.setPassportNumberX(passport_number);
//      formData1.setPassDateOfIssueX(passport_date_issue);
//      formData1.setPassDateOfExpiryX(passport_date_expire);
//      formData1.setPassPlaceOfIssueX(passport_place_issue);
//      if(!attachment.equals("")){
//      formData1.setFileName(attachment);
//      formData1.setEDU_TR_deletedX(deleted);} 
//   
//      getSqlMapClientTemplate().insert("LoginMap.UpdatePassport", formData1);
//  }
//  
    public void addOrUpdateVisaDetails(String id, String jf_Id, String country, String type, String dateOfIssue, String dateOfExpiry, String referenceNumber, String placeOfIssue, String entries, int deleted) {

        JoinerFormDTO formData1 = new JoinerFormDTO();
        if (!id.equals("")) {
            formData1.setJfVisaIdDb(id);
        }
        //formData1.setJfVisaIdDb("1");
        formData1.setJfId(jf_Id);
        formData1.setVisaCountryDb(country);
        formData1.setVisaTypeDb(type);
        formData1.setVisaDateOfIssueDb(dateOfIssue);
        formData1.setVisaDateOfExpiryDb(dateOfExpiry);
        formData1.setReferenceNumberDb(referenceNumber);
        formData1.setPlaceOfIssueDb(placeOfIssue);
        formData1.setEntriesDb(entries);
        formData1.setVISA_TR_deletedX(deleted);

        getSqlMapClientTemplate().insert("LoginMap.addOrUpdateVisaDetails", formData1);
    }
    //formData.getEmergencyId()[k], formData.getJfId(), formData.getName()[k], formData.getRelationship()[k],
    //formData.getMobile_number()[k], formData.getHome_phone_number()[k],formData.getWork_phone_number()[k],
    //formData.getEMERGENCY_TR_deleted() 

    public void addOrUpdateEmergencyContacts(String emergencyId, String jf_id, String name,
            String relationship, String mobile_number, String home_phone_number, String work_phone_number, int deleted) {
        JoinerFormDTO formData1 = new JoinerFormDTO();
        if (!emergencyId.equals("")) {
            formData1.setEmergencyIdX(emergencyId);
        }
        formData1.setJfId(jf_id);
        formData1.setNameX(name);
        formData1.setRelationShipDb(relationship);
        formData1.setMobile_numberX(mobile_number);
        formData1.setHome_phone_numberX(home_phone_number);
        formData1.setWork_phone_numberX(work_phone_number);
        formData1.setEMERGENCY_TR_deletedX(deleted);

        getSqlMapClientTemplate().insert("LoginMap.addOrUpdateEmergencyContacts", formData1);
    }

    public Integer getJoinerUserId(String jfId) {
        return (Integer) getSqlMapClientTemplate().queryForObject("LoginMap.getJoinerUserId", jfId);



    }

    public String getNewEmployeeNumber(int empId) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getNewEmployeeNumber1", empId);

    }

    public int addOrUpdateEmployeeFinalSubmit(HttpServletRequest requestObj, String employeeType, String groupId, JoinerFormDTO formData) {
        int emp_id = 0;
        String bankName = null;
        CommonFunctions.TrimSpace(formData);
//        HttpServletRequest requestObj = null;
        try {
            //Add Employee Details to Ideal
            System.out.println(":GROUP ID :" + groupId);
            MailMessages mailMessageObj = new MailMessages();
            SendMailTLS mailObj = new SendMailTLS();
            Calendar cal = Calendar.getInstance();
            JoinerFormDTO valuesFromJoiner = getjoinerDetails(formData.getJfId(), CommonConfigurations.JF_MODULE_CODE,
                    CommonConfigurations.JF_BANK_PROOFNAME, CommonConfigurations.JF_DL_PROOFNAME, CommonConfigurations.JF_JOINERPHOTO_PROOFNAME,
                    CommonConfigurations.JF_JOINERADDRESS_PROOFNAME, CommonConfigurations.JF_JOINERADHAR_PROOFNAME,CommonConfigurations.JF_PASSPORT_PROOFNAME);

            String buttonType = formData.getButtonType();
            for (int l = 0; l < formData.getJfPerCompId().length; l++) {
                if (formData.getPersonsAtCompanyStatus()[l].equals("undeleted")) {
                    if ((formData.getNameOfPersonAtCom()[l] == null || formData.getNameOfPersonAtCom()[l].equals("")) && (formData.getCompanyAndDep()[l] == null || formData.getCompanyAndDep()[l].equals("")) && (formData.getPacRelation()[l] == null || formData.getPacRelation()[l].equals(""))) {
                    } else {
                        addOrUpdatePersonsknownCompany(formData.getJfPerCompId()[l], formData.getJfId(), formData.getNameOfPersonAtCom()[l], formData.getCompanyAndDep()[l], formData.getPacRelation()[l], formData.getREL_TR_deleted()[l]);
                    }
                } else {
                    addOrUpdatePersonsknownCompany(formData.getJfPerCompId()[l], formData.getJfId(), formData.getNameOfPersonAtCom()[l], formData.getCompanyAndDep()[l], formData.getPacRelation()[l], formData.getREL_TR_deleted()[l]);
                }
            }
            for (int m = 0; m < formData.getJfRefEarCompId().length; m++) {
                if (formData.getRefFrmEarlierCompanyStatus()[m].equals("undeleted")) {
                    if ((formData.getRefFrmEarName()[m] == null || formData.getRefFrmEarName()[m].equals("")) && (formData.getRefFrmEarCmpName()[m] == null || formData.getRefFrmEarCmpName()[m].equals("")) && (formData.getRefFrmEarAddressAndTel()[m] == null || formData.getRefFrmEarAddressAndTel()[m].equals("")) && (formData.getRefFrmEarDesignation()[m] == null || formData.getRefFrmEarDesignation()[m].equals("")) && (formData.getRefFrmEarNoOfYearsKnown()[m] == null || formData.getRefFrmEarNoOfYearsKnown()[m].equals(""))) {
                    } else {
                        System.out.println("undeleted " + formData.getPRF_TR_deleted()[m]);
                        addOrUpdatePersonsknownEarlierCompany(formData.getJfRefEarCompId()[m], formData.getJfId(), formData.getRefFrmEarName()[m], formData.getRefFrmEarCmpName()[m], formData.getRefFrmEarAddressAndTel()[m], formData.getRefFrmEarDesignation()[m], formData.getRefFrmEarNoOfYearsKnown()[m], formData.getRefFrmEarEmailId()[m], formData.getPRF_TR_deleted()[m]);
                    }
                } else {
                    System.out.println("deleted " + formData.getPRF_TR_deleted()[m]);
                    addOrUpdatePersonsknownEarlierCompany(formData.getJfRefEarCompId()[m], formData.getJfId(), formData.getRefFrmEarName()[m], formData.getRefFrmEarCmpName()[m], formData.getRefFrmEarAddressAndTel()[m], formData.getRefFrmEarDesignation()[m], formData.getRefFrmEarNoOfYearsKnown()[m], formData.getRefFrmEarEmailId()[m], formData.getPRF_TR_deleted()[m]);
                }
            }
            if (buttonType.equalsIgnoreCase("Add Employee to iDeal")) {
                List<JoinerFormDTO> familyDetails = getJfFamilyMemberData(formData.getJfId());
                List<JoinerFormDTO> visaDetails = getJfVisaData(formData.getJfId());
                List<JoinerFormDTO> educationDetails = getEducationData(Integer.parseInt(formData.getJfId()));
                List<JoinerFormDTO> certificationDetails = getCertificationData(Integer.parseInt(formData.getJfId()));
                List<JoinerFormDTO> skillDetails = getSkillData(Integer.parseInt(formData.getJfId()));
                List<JoinerFormDTO> experienceDetails = getJfPrevEmpData(formData.getJfId());
                List<JoinerFormDTO> passportDetails = getPassportData(formData.getJfId());
                List<JoinerFormDTO> emergencyDetails = getEmergencyContacts(formData.getJfId());
                List<JoinerFormDTO> licenceDetails = getLicenceDetails(formData.getJfId());
                List<JoinerFormDTO> prevPfDetails = getPrevPfDetails(formData.getJfId());
                List<JoinerFormDTO> adharDetails = getAdharDetails(formData.getJfId());
                String dateOfBirth = CommonFunctions.changeDateFormatToDB(valuesFromJoiner.dateOfBirth);
                String joinedDate = CommonFunctions.changeDateFormatToDB(valuesFromJoiner.getDoj());
                String joinerDoj = CommonFunctions.changeDateFormatToDB(formData.getJoinerDoj());
                formData.setJoinerDoj(joinerDoj);
                String employeeNumberType = "";
                if (employeeType.equals("p")) {
                    employeeNumberType = " employee_number like \"1%\" ";
                } else if (employeeType.equals("d")) {
                    employeeNumberType = " employee_number like \"800%\" ";
                }
                if (employeeType.equals("v")) {
                    employeeNumberType = " employee_number like \"900%\" ";
                }
                if (employeeType.equals("g")) {
                    employeeNumberType = " employee_number like \"E00%\" ";
                }
                if (employeeType.equals("c")) {
                    employeeNumberType = " employee_number like \"E900%\" ";
                }
                String employeeNumber = getEmployeeNumber(employeeNumberType);
                String empNumber = null;
                if (employeeNumber == null && valuesFromJoiner.employeeType.equals("a")) { // For AL New Series
                    empNumber = "AL00001";
                } else if (valuesFromJoiner.employeeType.equals("g") || valuesFromJoiner.employeeType.equals("c")) { // For Permanent GmbH and Contract GmbH
                    int dummyEmpNumber = Integer.parseInt(employeeNumber.split("E")[1]) + 1;
                    String digitLength = "%05d";
                    String empNoWithAlpha = "E" + String.format(digitLength, dummyEmpNumber);
                    empNumber = empNoWithAlpha;
                } else if (valuesFromJoiner.employeeType.equals("a")) { // For AL employees
                    int dummyEmpNumber = Integer.parseInt(employeeNumber.split("AL")[1]) + 1;
                    String digitLength = "%05d";
                    String empNoWithAlpha = "AL" + String.format(digitLength, dummyEmpNumber);
                    empNumber = empNoWithAlpha;
                } else {
                    int empNum = Integer.parseInt(employeeNumber) + 1;
                    empNumber = Integer.toString(empNum);
                }
                valuesFromJoiner.setEmployeementType(formData.getEmployeementType());
                valuesFromJoiner.setJoiningLocation(formData.getJoiningLocation());
                valuesFromJoiner.setCurrentLocation(formData.getCurrentLocation());
                valuesFromJoiner.setEmpBillable(formData.getEmpBillable());
                String[] docs = {};
                String ojfDocumentDetails = "";
                if (formData.getDocs() == null) {
                    formData.setDocs(docs);
                }
                if (valuesFromJoiner.getEmpCategory().equals("e") && !valuesFromJoiner.getJoinerEmpId().equals("")) {
                    updateDetailsToIdealEmployees(valuesFromJoiner, dateOfBirth, empNumber, joinerDoj, formData.getJoinerWorkEmail());

                    emp_id = Integer.parseInt(valuesFromJoiner.joinerEmpId);
                } else {
                    for (int i = 0; i < formData.getDocs().length; i++) {
                        formData.setDocumentId(formData.getDocs()[i]);
                        if(i == 0){
                            ojfDocumentDetails = getOjfDocumentDetails(formData);
                        }else{
                            ojfDocumentDetails = ojfDocumentDetails+", "+getOjfDocumentDetails(formData);
                        }
                        //insertOjfDocumentDetails(formData);
                    }
                    String employee_remarks = "";
                    if(ojfDocumentDetails != null && !"".equals(ojfDocumentDetails)){
                        employee_remarks = ojfDocumentDetails+" are yet to be submitted.";
                     }
                    System.out.println("Inside esle====================");
                    addDetailsToIdealEmployees(valuesFromJoiner, dateOfBirth, empNumber, joinerDoj, formData.getJoinerWorkEmail(), employee_remarks);
                    emp_id = getLastId();
                    System.out.println("emp_id==================" + emp_id);
                }

                for (int i = 0; i < formData.getDocs().length; i++) {
                    formData.setDocumentId(formData.getDocs()[i]);
                    insertOjfDocumentDetails(formData);
                }
                System.out.println("DocumentList +++++ " + formData.getDocs().length);
                if (formData.getDocs().length == 0) {
                    formData.setDocumentSubmitionStatus("Y");
                } else {
                    formData.setDocumentSubmitionStatus("N");
                }
                updateJoinerDetails(formData, emp_id);

                //@@@@@@@@@@@@@@@@@@@@@@ not yet started ########################


                //*****************@@@@@@@@@@@@@@@@******************************

                setEmployeeStatus(formData.getJfId(), CommonConfigurations.JF_ADDED_TO_IDEAL);// have to implement

                //RRF Addition

                String rrf_id = fetchRRF(formData);
                if (rrf_id != null && !rrf_id.equals("") && !rrf_id.equals("0")) {
                    updateRRF(rrf_id, emp_id);
                }
                //dbCTRL.updateRrf_in_Ojf(formData);
                System.out.println("rrf_id--->" + rrf_id);

                deleteEmployeeBankDetails(emp_id);
                deleteEmployeeAddress(emp_id);
                deleteEmployeeVisa(emp_id);
                deleteEmployeePassport(emp_id);
                deleteEmployeeSkills(emp_id);
                deleteEmployeeWorkExperience(emp_id);
                deleteEmployeeEducation(emp_id);
                deleteEmployeeLeaveDetails(emp_id);
                deleteEmployeeLeaveRequests(emp_id);
                deleteEmployeeEmergencyContacts(emp_id);
                deleteLicenceDetails(emp_id);
                deleteAdharDetails(emp_id);
                deletePfDetails(emp_id);


                if (valuesFromJoiner.getBankAccType().equals("0")) {
                    for (int increment = 0; increment < CommonConfigurations.bankNames.length; increment++) {
                        if (Integer.parseInt(valuesFromJoiner.getBankName()) == increment) {
                            bankName = CommonConfigurations.bankNames[increment];
                        }
                    }
                    NationalityDTO regionData = getRegionData(Integer.parseInt(valuesFromJoiner.getBankCity()));
                    addDetailsToIdealBank(valuesFromJoiner, emp_id, bankName, regionData);
                }



//                for (int increment = 0; increment < familyDetails.length; increment++) {
//                    String relation = familyDetails[increment].relationShipDb;
//                    String nameOfRelation = familyDetails[increment].nameOfRelationDb;
//                    String dobRelation = CommonFunctions.changeDateFormatToDB(familyDetails[increment].dobRelationDb);
//                    String birthYear[] = dobRelation.split("-");
//                    int year = Integer.parseInt(birthYear[0]);
//                    int currentYear = cal.get(Calendar.YEAR);
//                    int age = currentYear - year;
//                    dbCTRL.addDetaislToIdealFamily(relation, nameOfRelation, dobRelation, emp_id, age);
//                }
                Iterator itr = familyDetails.iterator();
                while (itr.hasNext()) {
                    JoinerFormDTO familyDetails1 = (JoinerFormDTO) itr.next();
                    String relation = familyDetails1.getRelationShipDb();
                    String gender = null;
                    if (relation.equals("0") || relation.equals("3")) {
                        gender = "m";
                    } else if (relation.equals("1") || relation.equals("2") || relation.equals("4")) {
                        gender = "f";
                    }
                    System.out.println("Relation " + relation);
                    int len = CommonConfigurations.relationShips.length;
                    for (int i = 0; i < len; i++) {
                        if (relation.equals("" + i)) {
                            relation = CommonConfigurations.relationShips[i];
                        }
                    }
                    System.out.println("Relation " + relation);
                    String nameOfRelation = familyDetails1.getNameOfRelationDb();
                    String dobRelation = CommonFunctions.changeDateFormatToDB(familyDetails1.getDobRelationDb());
                    String birthYear[] = dobRelation.split("-");
                    int year = Integer.parseInt(birthYear[0]);
                    int currentYear = cal.get(Calendar.YEAR);
                    int age = currentYear - year;
                    addDetaislToIdealFamily(relation, nameOfRelation, dobRelation, emp_id, age, gender);
                }

//                addDetailsToIdealAddress(emp_id,valuesFromJoiner.getPresentAddress(),valuesFromJoiner.getPhoneNumberPresent(),"T");
//                addDetailsToIdealAddress(emp_id,valuesFromJoiner.getPermanentAddress(),valuesFromJoiner.getPhoneNumberPermanent(),"p");

//                for(int k=0;k<visaDetails.length;k++) {
//                    String issue_date = CommonFunctions.changeDateFormatToDB(visaDetails[k].visaDateOfIssueDb);
//                    String expiry_date = CommonFunctions.changeDateFormatToDB(visaDetails[k].visaDateOfExpiryDb);
//                    dbCTRL.addDetaislToIdealVisa(emp_id,visaDetails[k].visaCountryDb,visaDetails[k].visaTypeDb,issue_date,expiry_date,visaDetails[k].referenceNumberDb,visaDetails[k].placeOfIssueDb,visaDetails[k].entriesDb);
//                }
                Iterator visaitr = visaDetails.iterator();
                while (visaitr.hasNext()) {
                    JoinerFormDTO visaDetails1 = (JoinerFormDTO) visaitr.next();
                    String issue_date = CommonFunctions.changeDateFormatToDB(visaDetails1.getVisaDateOfIssueDb());
                    String expiry_date = CommonFunctions.changeDateFormatToDB(visaDetails1.getVisaDateOfExpiryDb());
                    addDetaislToIdealVisa(emp_id, visaDetails1.getVisaCountryDb(), visaDetails1.getVisaTypeDb(), issue_date, expiry_date, visaDetails1.getReferenceNumberDb(), visaDetails1.getPlaceOfIssueDb(), visaDetails1.getEntriesDb());
                }

//                for(int k=0;k<passportDetails.length;k++) {
//                    String issue_date = CommonFunctions.changeDateFormatToDB(passportDetails[k].passDateOfIssueX);
//                    String expiry_date = CommonFunctions.changeDateFormatToDB(passportDetails[k].passDateOfExpiryX);
//                    dbCTRL.addDetaislToIdealPassport(emp_id,passportDetails[k].passportNumberX,issue_date,expiry_date,passportDetails[k].passPlaceOfIssueX);
//                }
                Iterator passportitr = passportDetails.iterator();
                while (passportitr.hasNext()) {
                    JoinerFormDTO passportDetails1 = (JoinerFormDTO) passportitr.next();
                    String issue_date = CommonFunctions.changeDateFormatToDB(passportDetails1.getPassDateOfIssueX());
                    String expiry_date = CommonFunctions.changeDateFormatToDB(passportDetails1.getPassDateOfExpiryX());
                    addDetaislToIdealPassport(emp_id, passportDetails1.getPassportNumberX(), issue_date, expiry_date, passportDetails1.getPassPlaceOfIssueX());

                }

//                for(int k=0;k<educationDetails.length;k++) {
//                    dbCTRL.addDetaislToIdealEducation(emp_id,educationDetails[k].degreeX,educationDetails[k].qualificationX,educationDetails[k].institutionX,educationDetails[k].universityX,educationDetails[k].percentageX,educationDetails[k].year_of_passingX,educationDetails[k].remarksX);
//                }
                Iterator educationItr = educationDetails.iterator();
                while (educationItr.hasNext()) {
                    JoinerFormDTO educationDetails1 = (JoinerFormDTO) educationItr.next();
                    addDetaislToIdealEducation(emp_id, educationDetails1.getDegreeX(), educationDetails1.getQualificationX(), educationDetails1.getInstitutionX(), educationDetails1.getUniversityX(), educationDetails1.getPercentageX(), educationDetails1.getYear_of_passingX(), educationDetails1.getRemarksX());
                }

//                for(int k=0;k<certificationDetails.length;k++) {
//                    dbCTRL.addDetaislToIdealEducation(emp_id,"c",certificationDetails[k].cert_qualificationX,certificationDetails[k].cert_institutionX,"",certificationDetails[k].cert_percentageX,certificationDetails[k].cert_year_of_passingX,certificationDetails[k].cert_remarksX);
//                }
                Iterator certificationItr = certificationDetails.iterator();
                while (certificationItr.hasNext()) {
                    JoinerFormDTO certificationDetails1 = (JoinerFormDTO) certificationItr.next();
                    addDetaislToIdealEducation(emp_id, "c", certificationDetails1.getCert_qualificationX(), certificationDetails1.getCert_institutionX(), "", certificationDetails1.getCert_percentageX(), certificationDetails1.getCert_year_of_passingX(), certificationDetails1.getCert_remarksX());


                }

//                for(int k=0;k<skillDetails.length;k++) {
//                    dbCTRL.addDetaislToIdealSkill(emp_id,skillDetails[k].skill_categoryX,skillDetails[k].skillX,skillDetails[k].skill_yearX,skillDetails[k].skill_monthX,skillDetails[k].skill_typeX);
//                }
                Iterator skillItr = skillDetails.iterator();
                while (skillItr.hasNext()) {
                    JoinerFormDTO skillDetails1 = (JoinerFormDTO) skillItr.next();

                    addDetaislToIdealSkill(emp_id, skillDetails1.getSkill_categoryX(), skillDetails1.getSkillX(), skillDetails1.getSkill_yearX(), skillDetails1.getSkill_monthX(), skillDetails1.getSkill_typeX());
                }


//                for(int k=0;k<experienceDetails.length;k++) {
//                    String start_date = CommonFunctions.changeDateFormatToDB(experienceDetails[k].dateOfJoinDb);
//                    String end_date = CommonFunctions.changeDateFormatToDB(experienceDetails[k].dateOfRelDb);
//                    dbCTRL.addDetaislToIdealExperience(emp_id,experienceDetails[k].namePrevEmpDb,experienceDetails[k].nameAddPrevEmpDb,experienceDetails[k].desigOnRelDb,start_date,end_date);
//                }
//                
                Iterator expItr = experienceDetails.iterator();
                while (expItr.hasNext()) {
                    JoinerFormDTO experienceDetails1 = (JoinerFormDTO) expItr.next();
                    String start_date = CommonFunctions.changeDateFormatToDB(experienceDetails1.getDateOfJoinDb());
                    String end_date = CommonFunctions.changeDateFormatToDB(experienceDetails1.getDateOfRelDb());
                    addDetaislToIdealExperience(emp_id, experienceDetails1.getNamePrevEmpDb(), experienceDetails1.getNameAddPrevEmpDb(), experienceDetails1.getDesigOnRelDb(), start_date, end_date);

                }

//                for(int k=0;k<emergencyDetails.length;k++) {
//                    dbCTRL.addDetaislToIdealEmergency(emp_id,emergencyDetails[k].nameX,emergencyDetails[k].relationshipX,emergencyDetails[k].mobile_numberX,emergencyDetails[k].home_phone_numberX,emergencyDetails[k].work_phone_numberX);
//                }
                Iterator emergencyItr = emergencyDetails.iterator();
                while (emergencyItr.hasNext()) {
                    JoinerFormDTO emergencyDetails1 = (JoinerFormDTO) emergencyItr.next();
                    addDetaislToIdealEmergency(emp_id, emergencyDetails1.getNameX(), emergencyDetails1.getRelationshipX(), emergencyDetails1.getMobile_numberX(), emergencyDetails1.getHome_phone_numberX(), emergencyDetails1.getWork_phone_numberX());

                }

                Iterator licenceItr = licenceDetails.iterator();
                while (licenceItr.hasNext()) {
                    JoinerFormDTO licenceDetails1 = (JoinerFormDTO) licenceItr.next();
                    addDetaislToIdealIdProofs(emp_id, licenceDetails1.getDlNumber(), licenceDetails1.getDlDateOfIssue(), licenceDetails1.getDlDateOfExpiry(), "l");

                }

                Iterator prevPfItr = prevPfDetails.iterator();
                while (prevPfItr.hasNext()) {
                    JoinerFormDTO prevPfDetails1 = (JoinerFormDTO) prevPfItr.next();
                    addDetaislToIdealPrevPfDetails(emp_id, prevPfDetails1.getPreviousPfNumber(),prevPfDetails1.getUanNo());

                }

                Iterator adharItr = adharDetails.iterator();
                while (adharItr.hasNext()) {
                    JoinerFormDTO adharDetails1 = (JoinerFormDTO) adharItr.next();
                    addDetaislToIdealIdProofs(emp_id, adharDetails1.getAdharNo(), null, null, "a");

                }



            } else if (buttonType.equals("Save")) {
                setEmployeeStatus(formData.getJfId(), CommonConfigurations.JF_JOINER_SAVEDATA);
            } else if (buttonType.equals("Submit") && groupId.equalsIgnoreCase(CommonConfigurations.JOINING_FORMALITY_NEWCANDIDATE_GROUPID)) {
                setEmployeeStatus(formData.getJfId(), CommonConfigurations.JF_COMPLETED_CHECK_DETAILS);
                JoinerFormDTO joinerDetails = getCandidateDetails(formData);
                String mailMessage = mailMessageObj.getMailMessageForJoinerSubmit(requestObj, joinerDetails.getFirstName() + "#-#" + joinerDetails.getLastName());
                String mailTo = getCcMail(joinerDetails.getMailCc());
                String mailCC = joinerDetails.getPersonalEmailId1();
                try {
                    mailTo = mailTo +","+ CommonConfigurations.recruitment_team_dl;
                    System.out.println("Reference" + joinerDetails.getRefnumber());
                    System.out.println("To" + mailTo);
                    System.out.println("CC" + mailCC);
                    mailObj.smtpMail(mailTo, joinerDetails.getRefnumber(), mailMessage, mailCC);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (buttonType.equals("Data Verified") && (groupId.equals("5") || groupId.equals("24"))) {
                formData.setDoj(CommonFunctions.changeDateFormatToDB(formData.getDoj()));
                System.out.println(":JFFF ID:" + formData.getJfId());
                System.out.println(":dATA1:" + formData.getDataVerifiedMailCc());
                System.out.println(":dATA2:" + formData.getDoj());
                System.out.println(":dATA3:" + formData.getDataVerifiedBy());
                updateDataVerifiedDetails(formData);
                setEmployeeStatus(formData.getJfId(), CommonConfigurations.JF_DATA_VERIFIED_BY_HR);
            } else if (buttonType.equals("Send back to Employee") && (groupId.equals("5") || groupId.equals("24"))) {
                updateSendBackReason(formData);
                setEmployeeStatus(formData.getJfId(), CommonConfigurations.JF_SENDBACKTOJOINER);
                JoinerFormDTO joinerDetails = getCandidateDetails(formData);
                String mailMessage = mailMessageObj.getMailMessageForSendBack(requestObj, joinerDetails.getFirstName() + "#-#" + joinerDetails.getLastName() + "#-#" + joinerDetails.getSendBackReason() + "#-#" + joinerDetails.getPersonalEmailId1() + "#-#" + joinerDetails.getTrackNumber());
                // String mailCC = getCcMail(joinerDetails.getMailCc());
                String mailCC = getMailCcfromOJF(formData.getJfId());
                System.out.println("mailCC++++++++++++++++++++" + mailCC);
                try {
                    mailObj.smtpMail(joinerDetails.getPersonalEmailId1(), joinerDetails.getRefnumber(), mailMessage, mailCC);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            //Logger.getLogger(JoiningFormDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return emp_id;

    }

    public JoinerFormDTO getjoinerDetails(String id, String moduleName, String bankProofName, String dlProofName, String joinerPhotoProof, String joinerAddressProof, String joinerAdharProof,String passportProof) {
        JoinerFormDTO formData = new JoinerFormDTO();
        JoinerFormDTO valuesFromJoiner = null;
//   formData.getJfId(), CommonConfigurations.JF_MODULE_CODE,
//                    CommonConfigurations.JF_BANK_PROOFNAME, CommonConfigurations.JF_DL_PROOFNAME, CommonConfigurations.JF_JOINERPHOTO_PROOFNAME,
//                    CommonConfigurations.JF_JOINERADDRESS_PROOFNAME,CommonConfigurations.JF_PASSPORT_PROOFNAME           
//    
        System.out.println("JFID _-_-_-_-_-" + id);
        formData.setJfId(id);


        valuesFromJoiner = (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getjoinerDetails", formData);
        return valuesFromJoiner;
    }

    public void addOrUpdatePersonsknownCompany(int id, String jf_Id, String name, String company, String relationship, String deleted) {

        JoinerFormDTO formData = new JoinerFormDTO();
        //formData.getJfPerCompId()[l], formData.getJfId(), formData.getNameOfPersonAtCom()[l], formData.getCompanyAndDep()[l], formData.getPacRelation()[l], 0      
        formData.setJfPerCompIdDb(id);
        formData.setJfId(jf_Id);
        formData.setNameOfPersonAtComDb(name);
        formData.setCompanyAndDepDb(company);
        formData.setPacRelationDb(relationship);
        formData.setREL_TR_deletedX(deleted);
        if (!"".equals(name) || !"".equals(company) || !"".equals(relationship)) {
            getSqlMapClientTemplate().insert("LoginMap.addOrUpdatePersonsknownCompany", formData);
        }
    }

    public void addOrUpdatePersonsknownEarlierCompany(int id, String jf_Id, String name, String company_name, String address_telenumber, String designation, String number_years_known, String email_id, String deleted) {
        //formData.getJfRefEarCompId()[m], formData.getJfId(), formData.getRefFrmEarName()[m], formData.getRefFrmEarCmpName()[m], formData.getRefFrmEarAddressAndTel()[m], formData.getRefFrmEarDesignation()[m], formData.getRefFrmEarNoOfYearsKnown()[m], 0
        JoinerFormDTO formData = new JoinerFormDTO();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + id);
        //System.out.println("@@@@@@@@@@@@ incremented @@@@@@@@@@@@@@@@@@@"+ id++);
        // if()
        formData.setJfRefEarCompIdDb(id);
        formData.setJfId(jf_Id);
        formData.setRefFrmEarNameDb(name);
        formData.setRefFrmEarCmpNameDb(company_name);
        formData.setRefFrmEarAddressAndTelDb(address_telenumber);
        formData.setRefFrmEarDesignationDb(designation);
        formData.setRefFrmEarNoOfYearsKnownDb(number_years_known);
        formData.setRefFrmEarEmailIdDb(email_id);
        formData.setPRF_TR_deletedX(deleted);

        if (!"".equals(name) && !"".equals(company_name) && !"".equals(address_telenumber) && !"".equals(designation) && !"".equals(number_years_known)) {

            getSqlMapClientTemplate().insert("LoginMap.addOrUpdatePersonsknownEarlierCompany", formData);
        }
    }

    public String getEmployeeNumber(String employeeNumberType) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getEmployeeNumber", employeeNumberType);

    }

    public void updateDetailsToIdealEmployees(JoinerFormDTO newFormData, String dateOfBirth, String empNumber, String joinedDate, String workMailid) {

        //valuesFromJoiner, dateOfBirth, empNumber, joinerDoj, formData.getJoinerWorkEmail()  

        String OLD_FORMAT = "dd-MM-yyyy";
        String NEW_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = null;
        try {
            d = sdf.parse(dateOfBirth);
            sdf.applyPattern(NEW_FORMAT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        newFormData.setDateOfBirth(sdf.format(d));
        newFormData.setEmpNumber(empNumber);
        newFormData.setJoinedDate(joinedDate);
        newFormData.setWorkMailid(workMailid);

        getSqlMapClientTemplate().update("LoginMap.updateDetailsToIdealEmployees", newFormData);
    }

    public void addDetailsToIdealEmployees(JoinerFormDTO newFormData, String dateOfBirth, String empNumber, String joinedDate, String workMailid, String employee_remarks) {
        System.out.println(" inside addDetailsToIdealEmployees=========================");

        try {
            newFormData.setDateOfBirth(CommonFunctions.changeDateFormatToDB(dateOfBirth));
        } catch (Exception e) {
            System.out.println("Exception in date formate");
        }

        System.out.println("getDateOfBirth()===========" + newFormData.getDateOfBirth());
        newFormData.setEmpNumber(empNumber);
        newFormData.setJoinedDate(joinedDate);
        newFormData.setWorkMailid(workMailid);
        newFormData.setEmployeeRemarks(employee_remarks);
        System.out.println("Employee no=====================" + newFormData.getEmpNumber());
        System.out.println("Employee firstname=====================" + newFormData.getFirstName());
        System.out.println("Employee middle =====================" + newFormData.getMiddleName());
        System.out.println("Employee last=====================" + newFormData.getLastName());
        System.out.println("Employee no=====================" + newFormData.getPersonalEmailId1());
        System.out.println("Employee no=====================" + newFormData.getEmpFileName());

        System.out.println(" inside  before Query=========================");
        //getSqlMapClientTemplate().insert("LoginMap.addDetailsToIdealEmployees", newFormData); 
        // getSqlMapClientTemplate().insert("LoginMap.addDetailsToIdealEmployees", newFormData);
        try {
            newFormData.setNonappraiseestatus(0);
            getSqlMapClientTemplate().insert("LoginMap.addDetailsToIdealEmployees", newFormData);
        } catch (Exception e) {
            System.out.println("execption ========= raised" + e);
        }
        System.out.println(" inside  after Query=========================");
    }

    public void updateJoinerDetails(JoinerFormDTO formData, int empId) {
        formData.setEmpId(empId);
        getSqlMapClientTemplate().update("LoginMap.updateJoinerDetails", formData);

    }

    public void setEmployeeStatus(String jfId, int employeeStatus) {
        JoinerFormDTO formData = new JoinerFormDTO();
        formData.setJfId(jfId);
        formData.setEmployeeStatusX(employeeStatus);
        getSqlMapClientTemplate().update("LoginMap.setEmployeeStatus", formData);

    }

    public String fetchRRF(JoinerFormDTO formData) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.fetchRRF", formData);

    }

    public void updateRRF(String rrf_id, int empId) {
        HashMap map = new HashMap();
        map.put("rrf_id", rrf_id);
        map.put("empId", empId);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today = Calendar.getInstance().getTime();
        String closedDate = df.format(today);
        System.out.println("closedDate : "+closedDate);
        map.put("closedDate", closedDate);
        getSqlMapClientTemplate().update("LoginMap.updateRRF", map);
    }

    public void deleteEmployeeBankDetails(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteEmployeeBankDetails", empId);
    }

    public void deleteEmployeeAddress(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteEmployeeAddress", empId);
    }

    public void deleteEmployeeVisa(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteEmployeeVisa", empId);
    }

    public void deleteEmployeePassport(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteEmployeePassport", empId);
    }

    public void deleteEmployeeSkills(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteEmployeeSkills", empId);
    }

    public void deleteEmployeeWorkExperience(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteEmployeeWorkExperience", empId);
    }

    public void deleteEmployeeEducation(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteEmployeeEducation", empId);
    }

    public void deleteEmployeeLeaveDetails(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteEmployeeLeaveDetails", empId);
    }

    public void deleteEmployeeLeaveRequests(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteEmployeeLeaveRequests", empId);
    }

    public void deleteEmployeeEmergencyContacts(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteEmployeeEmergencyContacts", empId);
    }

    public void deleteLicenceDetails(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteLicenceDetails", empId);
    }

    public void deleteAdharDetails(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deleteAdharDetails", empId);
    }

    public void deletePfDetails(int empId) {
        getSqlMapClientTemplate().delete("LoginMap.deletePfDetails", empId);
    }

    public void addDetailsToIdealBank(JoinerFormDTO formdata, int empId, String bankName, NationalityDTO regionData) {
        formdata.setEmpId(empId);
        formdata.setBankName(bankName);
        formdata.setBankCountry(regionData.getBankCountry());
        formdata.setBankRegion(regionData.getBankRegion());

        getSqlMapClientTemplate().insert("LoginMap.addDetailsToIdealBank", formdata);
    }

    public void addDetaislToIdealFamily(String relation, String nameOfRelation, String dobRelation, int empId, int age, String gender) {
        JoinerFormDTO formdata = new JoinerFormDTO();
        formdata.setRelationShipDb(relation);
        formdata.setNameOfRelationDb(nameOfRelation);
        formdata.setDobRelationDb(dobRelation);
        formdata.setEmpId(empId);
        formdata.setAge(age);
        formdata.setGender(gender);
        getSqlMapClientTemplate().insert("LoginMap.addDetaislToIdealFamily", formdata);
    }

    public void addDetailsToIdealAddress(int empId, String address, String phone, String type) {
        JoinerFormDTO formdata = new JoinerFormDTO();
        formdata.setEmpId(empId);
        formdata.setAddress(address);
        formdata.setPhone(phone);
        formdata.setType(type);
        getSqlMapClientTemplate().insert("LoginMap.addDetailsToIdealAddress", formdata);

    }

    public void addDetaislToIdealVisa(int employee_id, String country_id, String visa_type, String date_of_issue, String date_of_expiry, String reference_number, String place_of_issue, String visa_visit) {
        //  emp_id,visaDetails1.getVisaCountryDb(),visaDetails1.getVisaTypeDb(),issue_date,expiry_date,visaDetails1.getReferenceNumberDb(),visaDetails1.getPlaceOfIssueDb(),visaDetails1.getEntriesDb()
        JoinerFormDTO formdata = new JoinerFormDTO();
        formdata.setEmpId(employee_id);

        formdata.setVisaCountryDb(country_id);
        formdata.setVisaTypeDb(visa_type);
        formdata.setDate_of_issue(date_of_issue);
        formdata.setDate_of_expiry(date_of_expiry);
        formdata.setReferenceNumberDb(reference_number);
        formdata.setPlaceOfIssueDb(place_of_issue);
        formdata.setEntriesDb(visa_visit);
        getSqlMapClientTemplate().insert("LoginMap.addDetaislToIdealVisa", formdata);
    }

    public void addDetaislToIdealPassport(int employee_id, String passport_number, String passport_date_issue, String passport_date_expire, String passport_place_issue) {
        //emp_id,passportDetails1.getPassportNumberX(),issue_date,expiry_date,passportDetails1.getPassPlaceOfIssueX()
        JoinerFormDTO formdata = new JoinerFormDTO();
        formdata.setEmpId(employee_id);
        formdata.setPassportNumberX(passport_number);
        formdata.setDate_of_issue(passport_date_issue);
        formdata.setDate_of_expiry(passport_date_expire);
        formdata.setPassPlaceOfIssueX(passport_place_issue);
        getSqlMapClientTemplate().insert("LoginMap.addDetaislToIdealPassport", formdata);
    }

    public void addDetaislToIdealEducation(int employee_id, String degree, String course, String institute, String university, String percentage, String year_of_passing, String remarks) {
        //emp_id,educationDetails1.getDegreeX(),educationDetails1.getQualificationX(),educationDetails1.getInstitutionX(),educationDetails1.getUniversityX(),educationDetails1.getPercentageX(),educationDetails1.getYear_of_passingX(),educationDetails1.getRemarksX()            
        JoinerFormDTO formdata = new JoinerFormDTO();
        formdata.setEmpId(employee_id);
        formdata.setDegreeX(degree);
        formdata.setQualificationX(course);
        formdata.setInstitutionX(institute);
        formdata.setUniversityX(university);
        formdata.setPercentageX(percentage);
        formdata.setYear_of_passingX(year_of_passing);
        formdata.setRemarksX(remarks);
        System.out.println("Educational Details " + formdata.getEmpId() + " " + formdata.getDegreeX() + " " + formdata.getQualificationX() + " " + formdata.getInstitutionX() + " " + formdata.getUniversityX() + " " + formdata.getPercentageX() + " " + formdata.getYear_of_passingX() + " " + formdata.getRemarksX());
        getSqlMapClientTemplate().insert("LoginMap.addDetaislToIdealEducation", formdata);

    }

    public void addDetaislToIdealSkill(int employee_id, String skill_category, String skill_id, String years_worked, String months_worked, String skill_type) {
        JoinerFormDTO formdata = new JoinerFormDTO();
        //emp_id,skillDetails1.getSkill_categoryX(),skillDetails1.getSkillX(),skillDetails1.getSkill_yearX(),skillDetails1.getSkill_monthX(),skillDetails1.getSkill_typeX()           
        formdata.setEmpId(employee_id);
        formdata.setSkill_categoryX(skill_category);
        formdata.setSkillX(skill_id);
        formdata.setSkill_yearX(years_worked);
        formdata.setSkill_monthX(months_worked);
        formdata.setSkill_typeX(skill_type);
        getSqlMapClientTemplate().insert("LoginMap.addDetaislToIdealSkill", formdata);
    }

    public void addDetaislToIdealExperience(int employee_id, String company_name, String company_address, String job_title, String start_date, String end_date) {
        //emp_id,experienceDetails1.getNamePrevEmpDb(),experienceDetails1.getNameAddPrevEmpDb(),experienceDetails1.getDesigOnRelDb(),start_date,end_date
        JoinerFormDTO formdata = new JoinerFormDTO();
        formdata.setEmpId(employee_id);
        formdata.setNamePrevEmpDb(company_name);
        formdata.setNameAddPrevEmpDb(company_address);
        formdata.setDesigOnRelDb(job_title);
        formdata.setDateOfJoinDb(start_date);
        formdata.setDateOfRelDb(end_date);

        getSqlMapClientTemplate().insert("LoginMap.addDetaislToIdealExperience", formdata);
    }

    public void addDetaislToIdealEmergency(int empId, String name, String relationship, String mobile_number, String home_phone_number, String work_phone_number) {
        JoinerFormDTO formdata = new JoinerFormDTO();
//emp_id,emergencyDetails1.getNameX(),emergencyDetails1.getRelationshipX(),emergencyDetails1.getMobile_numberX(),emergencyDetails1.getHome_phone_numberX(),emergencyDetails1.getWork_phone_numberX()            
        formdata.setEmpId(empId);
        formdata.setNameX(name);
        formdata.setRelationshipX(relationship);
        formdata.setMobile_numberX(mobile_number);
        formdata.setHome_phone_numberX(home_phone_number);
        formdata.setWork_phone_numberX(work_phone_number);

        getSqlMapClientTemplate().insert("LoginMap.addDetaislToIdealEmergency", formdata);
    }

    public void addDetaislToIdealIdProofs(int empId, String proofNumber, String proofDateOfIssue, String proofDateOfExpiry, String proofType) {
        HashMap map = new HashMap();
        map.put("empId", empId);
        map.put("proofNumber", proofNumber);
        map.put("proofDateOfIssue", proofDateOfIssue);
        map.put("proofDateOfExpiry", proofDateOfExpiry);
        map.put("proofType", proofType);
        getSqlMapClientTemplate().insert("LoginMap.addDetaislToIdealIdProofs", map);
    }

    public void addDetaislToIdealPrevPfDetails(int empId, String prevPfNumber, String uanNo) {
        HashMap map = new HashMap();
        map.put("empId", empId);
        map.put("prevPfNumber", prevPfNumber);
        map.put("uanNo", uanNo);
        getSqlMapClientTemplate().insert("LoginMap.addDetaislToIdealPrevPfDetails", map);
    }

    public void updateDataVerifiedDetails(JoinerFormDTO formData) {
        getSqlMapClientTemplate().update("LoginMap.updateDataVerifiedDetails", formData);

    }

    public void updateSendBackReason(JoinerFormDTO formData) {
        getSqlMapClientTemplate().update("LoginMap.updateSendBackReason", formData);
    }

    public String getGroupId(JoinerFormDTO formData) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getGroupId", formData);

    }

    public String checkWorkEmail(String workEmailId, String employeeCategory, String contractemployeeId) {
        String checkWorkEmail = null;
        try {
            if (employeeCategory.equals("e") && !contractemployeeId.equals("")) {
                checkWorkEmail = checkWorkEmailContract(workEmailId, contractemployeeId);
            } else {
                checkWorkEmail = checkWorkEmail(workEmailId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkWorkEmail;



    }

    public String checkWorkEmailContract(String workEmailId, String contractemployeeId) {
        JoinerFormDTO formData = new JoinerFormDTO();
        formData.setWorkEmailId(workEmailId);
        formData.setContractEmployeeId(contractemployeeId);

        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.checkWorkEmailContract", formData);

    }

    public String checkWorkEmail(String workEmailId) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.checkWorkEmail", workEmailId);
    }

    public void updateNewEmployeeMailDetail(JoinerFormDTO formData) {
        try {
            System.out.println("updateNewEmployeeMailDetail1 in DaoImpl **************************");
            formData.setDateOfBirth(CommonFunctions.changeDateFormatToDB(formData.getDateOfBirth()));

            String source_reference = "";
            if (!formData.getEmpref().equals("") && !formData.getEmpref().equalsIgnoreCase("Enter Name Here")) {
                source_reference = formData.getEmpref();
            } else if (!formData.getVendorname().equals("")) {
                source_reference = formData.getVendorname();
            } else if (!formData.getJobporname().equals("")) {
                source_reference = formData.getJobporname();
            }

            formData.setEmpref(source_reference);
            System.out.println("befor updateNewEmployeeMailDetail1 in DaoImpl **************************");
            this.addNewCandidate123(formData);
            updateNewEmployeeMailDetail1(formData);
            System.out.println("after updateNewEmployeeMailDetail1 in DaoImpl **************************");

            //this.addNewCandidate1(formData);


        } catch (Exception e) {
            e.getLocalizedMessage();
        }

    }

    public void addNewCandidate123(JoinerFormDTO formData) {
        System.out.println("start*************");
        //getSqlMapClientTemplate().insert("LogingMap.addNewCandidate123",formData);
        try {
            getSqlMapClientTemplate().insert("LoginMap.insertEmployee", formData);
            System.out.println("end*************");
        } catch (Exception e) {
            System.out.println("execption*************" + e);
        }

    }

    public void updateNewEmployeeMailDetail1(JoinerFormDTO formData) {
        System.out.println("start update*************");
        try {
            String empContractnew = formData.getEmpContract();
            if (empContractnew == null) {
                empContractnew = "";
            }
            if (empContractnew.equals("")) {
                empContractnew = "0";
                formData.setEmpContract(empContractnew);
            }

            formData.setStatus("1");// changing status directly  only for testing ;
            System.out.println("MAIL CC " + formData.getMailCc());
            getSqlMapClientTemplate().update("LoginMap.updateNewEmployeeMailDetail1", formData);
            System.out.println("end upadte*************");
        } catch (Exception e) {
            System.out.println("Exception*************" + e);
        }

    }

    public void updateNewEmployeeDetail(JoinerFormDTO formData) {
        try {
            formData.setDateOfBirth(CommonFunctions.changeDateFormatToDB(formData.getDateOfBirth()));

            String source_reference = "";

            if (!formData.getEmpref().equals("") && !formData.getEmpref().equalsIgnoreCase("Enter Name Here")) {
                source_reference = formData.getEmpref();
            } else if (!formData.getVendorname().equals("")) {
                source_reference = formData.getVendorname();
            } else if (!formData.getJobporname().equals("")) {
                source_reference = formData.getJobporname();
            }
            formData.setEmpref(source_reference);

            System.out.println("EmployeeContract&&&&&&&&&&&" + formData.getEmpContract());

            if (formData.getContract_type().equals("n")) {
                formData.setEmpContract("");
            }

            this.updateNewEmployeeDetail1(formData);
            this.addOrUpdatePassportNumber1(formData);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }

    }

    public void updateNewEmployeeDetail1(JoinerFormDTO formData) {
        getSqlMapClientTemplate().update("LoginMap.updateNewEmployeeDetail1", formData);

    }
    
    public void updateMailFailure(JoinerFormDTO formData){
        getSqlMapClientTemplate().update("LoginMap.updateMailFailure", formData);
        getSqlMapClientTemplate().update("LoginMap.deleteLogin", formData);
    }

    public void addOrUpdatePassportNumber1(JoinerFormDTO formData) {
        getSqlMapClientTemplate().queryForObject("LoginMap.addOrUpdatePassportNumber1", formData);//not mapped in xml
    }

    public String getMailCcfromOJF(String jfid) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getMailCcfromOJF", jfid);
    }

    public List<String> getDateOfJoining(String jfid) {
        System.out.println("getDateOfJoining inside");
        return (List<String>) getSqlMapClientTemplate().queryForList("LoginMap.getDateOfJoining", jfid);
    }

    public List<String> getCompanyNames(String jfid) {

        return (List<String>) getSqlMapClientTemplate().queryForList("LoginMap.getCompanyNames", jfid);
    }

    public List<String> getsalarysOnJoining(String jfid) {

        return (List<String>) getSqlMapClientTemplate().queryForList("LoginMap.getsalarysOnJoining", jfid);
    }
    /////////////////////////

    public String getTrackData(String jfId) {
        return (String) getSqlMapClientTemplate().queryForObject("LoginMap.getTrackNumber", jfId);


    }

    public JoinerFormDTO getJoinerReportData(JoinerFormDTO formData) {
        return (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getJoinerReportData", formData);

    }
    public JoinerFormDTO getJoinerMailData(String ojf_id){
        return (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getJoinerMailData", ojf_id);
    }
    public JoinerFormDTO getAuthorizerDetails(String val) {
        return (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getAuthorizerDetails", val);
    }

    public List<JoinerFormDTO> getJfEmpData(JoinerFormDTO formData) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfEmpData1", formData);
    }

    public List<JoinerFormDTO> getMediInsuranceData(String empId) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getMediInsuranceData", empId);
    }
    public List<JoinerFormDTO> getnomineeData(String empId) {
        return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getnomineeData", empId);
    }

//   nomineeData   public List<JoinerFormDTO>  getJfReferrerDTData(String id){
//           return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfReferrerDTData",id); 
//           
//           }
//       public List<JoinerFormDTO> getJfReferrerPrevCompData(String id){
//           return (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getJfReferrerPrevCompData",id);
//           
//           }
    public void revertRRF(String jf_id) {
        String rrfNumber = (String) getSqlMapClientTemplate().queryForObject("LoginMap.getRrfNumber", jf_id);
        getSqlMapClientTemplate().update("LoginMap.revertEmpId", rrfNumber);
        getSqlMapClientTemplate().update("LoginMap.revertRRF", jf_id);


    }
    //////////////////////

    public void addOrUpdateEmployeeAddress(JoinerFormDTO formData) {
        try {
            Integer id = (Integer) getSqlMapClientTemplate().queryForObject("LoginMap.getAddressId", formData);
            String addressType = "p";
            formData.setAddress_type(addressType);
            if (id == null) {
                getSqlMapClientTemplate().insert("LoginMap.addEmployeeAddressDetails", formData);
            } else {
                getSqlMapClientTemplate().update("LoginMap.updateEmployeeAddressDetails", formData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getJfStatus(int id) {
        int jfStatus = 0;
        try {
            jfStatus = (Integer) getSqlMapClientTemplate().queryForObject("LoginMap.getJfStatus", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jfStatus;
    }

    public JoinerFormDTO getEmployeeAddressData(int empId) {
        JoinerFormDTO joinerAddressData = null;
        try {
            joinerAddressData = (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getEmployeeAddressData", empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joinerAddressData;
    }

    public int getEmpId(String employeeNumber) {
        Integer id = 0;
        try {
            id = (Integer) getSqlMapClientTemplate().queryForObject("LoginMap.getEmpId", employeeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (id == null) {
            id = 0;
        }
        return id;
    }

    public void updateEmpAddressData(String jfId, int id) {
        try {
            HashMap map = new HashMap();
            map.put("jfId", jfId);
            map.put("id", id);
            getSqlMapClientTemplate().update("LoginMap.updateEmpAddressData", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getJfEmpId(String id) {
        int JfEmpId = 0;
        int jfId = Integer.parseInt(id);
        try {
            JfEmpId = (Integer) getSqlMapClientTemplate().queryForObject("LoginMap.getJfEmpId", jfId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JfEmpId;
    }

    public String getRrfId(String rrfRes) {
        String rrfName = null;
        int rrf_no = 0;
        if (rrfRes != null) {
            rrf_no = Integer.parseInt(rrfRes);
        }
        try {
            rrfName = (String) getSqlMapClientTemplate().queryForObject("LoginMap.getRrfId", rrf_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rrfName;
    }

    public JoinerFormDTO getSyncEmpDetails(String n) {
        JoinerFormDTO syncEmpDetails = null;
        //  n = "N";
        System.out.println("n" + n);
        syncEmpDetails = (JoinerFormDTO) getSqlMapClientTemplate().queryForObject("LoginMap.getSyncEmpDetails", n);
        //System.out.println("sync details"+syncEmpDetails.getEmployeeName());
        return syncEmpDetails;
    }

    @Override
    public int getBankProofFileId(String jfId, String referenceName) {
        Integer id = null;
        try {
            HashMap map = new HashMap();
            map.put("jfId", jfId);
            map.put("referenceName", referenceName);
            id = (Integer) getSqlMapClientTemplate().queryForObject("LoginMap.getBankProofFileId", map);
            if (id == null) {
                id = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void updateFileDb(String fileName, String contentType, int id) {
        try {
            HashMap map = new HashMap();
            map.put("fileName", fileName);
            map.put("contentType", contentType);
            map.put("id", id);
            getSqlMapClientTemplate().update("LoginMap.updateFileDb", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getVendorName(String empref) {
        String empRefName = null;
        try {
            empRefName = (String) getSqlMapClientTemplate().queryForObject("LoginMap.getVendorName", empref);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empRefName;
    }

    public String getJobPortalName(String empref) {
        String empRefName = null;
        try {
            empRefName = (String) getSqlMapClientTemplate().queryForObject("LoginMap.getJobPortalName", empref);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empRefName;
    }

    public List<JoinerFormDTO> getDocumentList(String id) {
        List<JoinerFormDTO> documentList = null;
        documentList = getSqlMapClientTemplate().queryForList("LoginMap.getDocumentList", id);
        return documentList;
    }

    private void insertOjfDocumentDetails(JoinerFormDTO formData) {
        getSqlMapClientTemplate().update("LoginMap.insertOjfDocumentDetails", formData);
    }
    
    private String getOjfDocumentDetails(JoinerFormDTO formData){
        return (String)getSqlMapClientTemplate().queryForObject("LoginMap.getOjfDocumentDetails", formData);
    }

    public List<JoinerFormDTO> getPendingDocuments(String jfId) {
        List<JoinerFormDTO> pendingDocuments = getSqlMapClientTemplate().queryForList("LoginMap.getPendingDocuments", jfId);
        return pendingDocuments;
    }
    public void saveApplicantDetails(ApplicantDTO dto)
    {       
        getSqlMapClientTemplate().insert("LoginMap.insertApplicantDetails", dto);        
    }   
    private void addOrUpdateEducation(String string, String jfId, String string0, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String fileName, int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public JoinerFormDTO getSigleApplicant(String id)
    {       
         JoinerFormDTO appDto = null;
         appDto = (JoinerFormDTO)getSqlMapClientTemplate().queryForObject("LoginMap.getSingleApplicant", id);
         return appDto;
         
    }
//    public ApplicantDTO getRrfDetails(ApplicantDTO reqDto)
//    {       
//       ApplicantDTO appDto = null; 
//        appDto =  (ApplicantDTO)getSqlMapClientTemplate().queryForObject("LoginMap.getRrfDetails", reqDto);
//       return appDto;
//    }
    
     public void updateApplicantStatus(JoinerFormDTO formData) {
     
        getSqlMapClientTemplate().update("LoginMap.updateApplicantStatus", formData);
        
    }
    
     public List<JoinerFormDTO> getApplicantsList(){
         
       List<JoinerFormDTO>  list= (List<JoinerFormDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getApplicantData");
        return list;
     
     }
     
     
     
     
//    public int getLoginDetails(ApplicantDTO regisDto) throws Exception{        
//        int result = (Integer)getSqlMapClientTemplate().queryForObject("registerMap.getLoginDetails",regisDto);
//        return result;
//    }
    
//    public List<ApplicantDTO> getApplicantData() { 
//        
//        List<ApplicantDTO> applicantDto = (List<ApplicantDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getApplicantData");
//
//        Iterator i = applicantDto.iterator();
//        while(i.hasNext()) {
//            ApplicantDTO jfd = (ApplicantDTO) i.next();
//            System.out.println("first name " + jfd.getFirstName());
//        }
//
//        return applicantDto;
//
//    }
    
}