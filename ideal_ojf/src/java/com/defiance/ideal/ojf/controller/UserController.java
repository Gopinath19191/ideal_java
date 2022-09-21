/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.controller;

import com.defiance.ideal.ojf.dao.LoginDAO;
import com.defiance.ideal.ojf.dao.LoginDAOImpl;
import com.defiance.ideal.ojf.dto.ApplicantDTO;
import com.defiance.ideal.ojf.dto.LoginDTO;
import com.defiance.ideal.ojf.joiningForm.dao.JoinerDAO;
import com.defiance.ideal.ojf.joiningForm.dao.JoinerFormImpl;
import com.defiance.ideal.ojf.joiningForm.dao.MasterDaoImpl;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 15850
 */
public class UserController extends MultiActionController {

    public static String JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL;
    public static String ojfGroupMail;

    public ModelAndView initIt(HttpServletRequest requestObj) throws Exception {
        ModelAndView mv = null;
        // HttpServletRequest requestObj = this.getRequest();
        System.out.println("here it comes " + requestObj.getParameter("idltkn"));
        LoginDTO iDealToken = ((LoginDAOImpl) this.getBean("loginDao")).authenticateToken(requestObj.getParameter("idltkn"));

        if (iDealToken == null) {
            mv = new ModelAndView("unauthorisedaccess");// unauthorisedaccess.jsp
        } else {
            requestObj.setAttribute("authenUser", iDealToken.getUserName());
            mv = new ModelAndView("validate");// calling validate();
        }
        return mv;
    }

    // validate user with level user and usergroup;
    public ModelAndView validate(HttpServletRequest request, HttpServletResponse response, LoginDTO formData) throws Exception {
        //for token Authentication
        initIt(request);
        HttpSession session = request.getSession();

        String authenticatedFlag = null;
        String userName = formData.getUserName();
        String passward = formData.getUserPassword();
        String toEnc = formData.getUserPassword();
        MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
        mdEnc.reset();
        mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
        byte messageDigest[] = mdEnc.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xff & messageDigest[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
//          String userPassword = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
        String userPassword = sb.toString(); // Encrypted string
        formData.setUserPassword(userPassword);
        ModelAndView mv = null;
        LoginDTO lodindto = ((LoginDAOImpl) this.getBean("loginDao")).authenticateLoginDetails(formData);

        if (lodindto == null) {
            String errorMessage = "Invalid Login Details";
            request.setAttribute("invalid_login", errorMessage);
            mv = new ModelAndView("index");// index.jsp

            return mv;
            // return null;
        } else if (lodindto.getGroupId().equals(CommonConfigurations.JOINING_FORMALITY_NEWCANDIDATE_GROUPID)
                && (lodindto.getEmpStatus().equals("1") || lodindto.getEmpStatus().equals("2") || lodindto.getEmpStatus().equals("4"))) {

            session.setAttribute("trackNumber", passward);
            String groupId = lodindto.getGroupId();
            session.setAttribute("groupId", groupId);//my new code
            session.setAttribute("loginId", formData.getUserName());
            authenticatedFlag = "1";
            return mv = new ModelAndView("redirect:joineraddstepone.htm"); // newcandidate com/defiance/ideal/joiningForm/joinerDetails.do methode

        }
        if (lodindto.getGroupId().equals(CommonConfigurations.JOINING_FORMALITY_NEWCANDIDATE_GROUPID) && Integer.parseInt(lodindto.getEmpStatus()) > 2 && Integer.parseInt(lodindto.getEmpStatus()) != 4) {
            authenticatedFlag = "0";
            session.setAttribute("groupId", formData.getGroupId());//my new code
            session.setAttribute("loginId", formData.getUserName());
            return mv = new ModelAndView("newCandidateRelogin");//newCandidateRelogin.jsp;
            //com/defiance/ideal/joiningForm/joinerDetails.do
        }
        LoginDTO userAuthentication = ((LoginDAOImpl) this.getBean("loginDao")).authenticateUser(lodindto);//complete
        //working
        //user Authentication start
        if (userAuthentication == null) {
            LoginDTO groupAuthentication = ((LoginDAOImpl) this.getBean("loginDao")).authenticateGroup(formData);//complete

            if (groupAuthentication == null) {
                authenticatedFlag = "0";
            } else if ((groupAuthentication.getgCreate().trim().equals("1")) && (groupAuthentication.getgRead().trim().equals("1")) && (groupAuthentication.getgUpdate().trim().equals("1")) && (groupAuthentication.getgDelete().trim().equals("1"))) {
                System.out.println("setting Auth Flag to 1");
                authenticatedFlag = "1";
            } else {
                authenticatedFlag = "0";
            }
            System.out.println("authenticatedFlag after gAuth = " + authenticatedFlag);
        } //working            
        else if ((userAuthentication.getuCreate().trim().equals("1")) && (userAuthentication.getuRead().trim().equals("1")) && (userAuthentication.getuUpdate().trim().equals("1")) && (userAuthentication.getuDelete().trim().equals("1"))) {
            System.out.println("setting Auth Flag to 1");
            authenticatedFlag = "1";
        } else {
            authenticatedFlag = "0";
        }

        if (authenticatedFlag.equals("1")) {
            session.setAttribute("loginId", formData.getUserName());
            session.setAttribute("groupId", lodindto.getGroupId());
            session.setAttribute("employeeId", lodindto.getEmpId());
            session.setAttribute("joinerGroupId", CommonConfigurations.JOINING_FORMALITY_NEWCANDIDATE_GROUPID);
            if (mv == null) {
                mv = new ModelAndView("redirect:begin.htm?login_status=0");
            }


        } else {
            mv = new ModelAndView("unauthorisedaccess");// return unauthorisedaccess.jsp 
        }

        return mv;
    }// ###################end of validat();#######################

    public ModelAndView begin(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO joinerformDto) throws Exception {

        ModelAndView mv;
        HttpSession session = request.getSession();
        String successMessage = null;
        if (request.getParameter("successMsg") == null || request.getParameter("successMsg").equals("")) {
            successMessage = (String) session.getAttribute("successMsg");
        } else {
            successMessage = (String) request.getParameter("successMsg");
        }

        String errmsg = request.getParameter("errorEmployee");

        if (successMessage != null) {
            if (successMessage.equals("Mail Triggered Successfully")) {
                request.setAttribute("successMsg", "Mail Triggered Successfully");
            } else if (successMessage.equals("Data Saved Successfully")) {
                request.setAttribute("successMsg", "Data Saved Successfully");
            } else if (successMessage.equals("Data Verification Completed")) {
                request.setAttribute("successMsg", "Data Verification Completed");
            } else if (successMessage.equals("Joining Form send back to employee")) {
                request.setAttribute("successMsg", "Joining Form send back to employee");
            } else {
                request.setAttribute("successMsg", successMessage);
            }
            session.removeAttribute("successMsg");
        }
        String buttonType = request.getParameter("search");
        if(request.getParameter("login_status") != null){
            if(request.getParameter("login_status").equals("0")){
                joinerformDto.setStatusSearch("0");
            }
        }
        
        List<JoinerFormDTO> list = ((JoinerFormImpl) this.getBean("joinerDao")).getCandidateList(joinerformDto);//joining form data is joingDto;
        List<MasterDataDTO> levelOneStructure = ((MasterDaoImpl) this.getBean("MasterDao")).getStructureDetails(0);
        LinkedHashMap statusList = CommonFunctions.statusList();
        request.setAttribute("candidateList", list);
        request.setAttribute("levelOneStructure", levelOneStructure);
        request.setAttribute("statusList", statusList);
        request.setAttribute("filterData", joinerformDto);
        if (buttonType != null) {

            System.out.println("Exporttesting +++++++");
            if (buttonType.equalsIgnoreCase("Export")) {
                ((JoinerFormImpl) this.getBean("joinerDao")).excelExport(joinerformDto, response, list);

            }
        }
        String gid = (String) session.getAttribute("groupId");

        if (gid != null && gid.equals(CommonConfigurations.JOINING_FORMALITY_NEWCANDIDATE_GROUPID)) {
            mv = new ModelAndView("redirect:joinerAdd.htm");//  Mapped to joinerAdd method;
        } else {
            mv = new ModelAndView("jflist");
            mv.addObject("colour", "green");
        }
        return mv;

    }

    public ModelAndView joineraddstepone(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO joinerformDto) throws Exception {
        ModelAndView mv = null;

        HttpSession session = requestObj.getSession();
        String trackNumber = (String) requestObj.getParameter("trackNumber");
        if (trackNumber == null) {
            trackNumber = (String) session.getAttribute("trackNumber");
        }
        String printStatus = requestObj.getParameter("printStatus");
        JoinerFormDTO employeeData = ((JoinerFormImpl) this.getBean("joinerDao")).checkTrackNumber(trackNumber);
        LinkedHashMap bloodGroupList = CommonFunctions.bloodGroupList();
        requestObj.setAttribute("bloodGroupList", bloodGroupList);
        if (employeeData.getDataCount() > 0) {
            String id = employeeData.getJfId();
            CommonConfigurations.id = id;
            CommonConfigurations ccfg = new CommonConfigurations();//for testing only...
            List<JoinerFormDTO> joinerData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(ccfg);

            System.out.println("000000" + joinerData1.get(0).getBankFileName());
            System.out.println("" + joinerData1.get(0).getBankFileType());
            requestObj.setAttribute("joinerData1", joinerData1);
            // get 1 record
            int empId = 0;
            int jfStatus = ((JoinerFormImpl) this.getBean("joinerDao")).getJfStatus(Integer.parseInt(id));
            if (jfStatus == 6) {
                empId = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpId(id);
            } else {
                empId = Integer.parseInt(id);
            }
            JoinerFormDTO joinerAddressData = ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeAddressData(empId);
            Iterator itr = joinerData1.iterator();
            JoinerFormDTO joinerData = (JoinerFormDTO) itr.next();
            requestObj.setAttribute("joinerData", joinerData);
            requestObj.setAttribute("joinerAddressData", joinerAddressData);// setting to req obj
            if (joinerData.getBankCity() != null && !joinerData.getBankCity().equals("")) {
                NationalityDTO regionData = null;
                try {
                    regionData = ((JoinerFormImpl) this.getBean("joinerDao")).getRegionData(Integer.parseInt(joinerData.getBankCity()));
                } catch (NumberFormatException e) {
                    System.out.println("in side joineraddstepone++++++" + e);
                }
                requestObj.setAttribute("regionData", regionData);
                List<NationalityDTO> regionList = ((JoinerFormImpl) this.getBean("joinerDao")).getRegionDetails(Integer.parseInt(regionData.getBankCountry()));
                List<NationalityDTO> cityList = ((JoinerFormImpl) this.getBean("joinerDao")).getCityDetails(Integer.parseInt(regionData.getBankRegion()));
                requestObj.setAttribute("regionList", regionList);
                requestObj.setAttribute("cityList", cityList);
            }
            session.setAttribute("trackNumber", trackNumber);
            requestObj.setAttribute("employeeData", employeeData);
            List<NationalityDTO> maritalStatus = ((JoinerFormImpl) this.getBean("joinerDao")).getMaritalStatuslist();
            requestObj.setAttribute("maritalStatus", maritalStatus);
            requestObj.setAttribute("bankAccType", CommonConfigurations.bankAccType);
            requestObj.setAttribute("bankNames", CommonConfigurations.bankNames);

            requestObj.setAttribute("joinerGroupId", CommonConfigurations.JOINING_FORMALITY_NEWCANDIDATE_GROUPID);

            List<NationalityDTO> nationalityList = ((JoinerFormImpl) this.getBean("joinerDao")).getNationalitylist();
            requestObj.setAttribute("nationalityList", nationalityList);
            List<NationalityDTO> countryList = ((JoinerFormImpl) this.getBean("joinerDao")).getCountryList();
            requestObj.setAttribute("countryList", countryList);
            mv = new ModelAndView("joineraddstepone");
        }
        return mv;
    }

    public ModelAndView joinerFormAddOrUpdateStepTwo(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {
//        ModelAndView mv=null;

        String printStatus = request.getParameter("printStatus");
        System.out.println("printStatus==================" + printStatus);
        HttpSession sessionObj = request.getSession();
        String trackNumber = (String) sessionObj.getAttribute("trackNumber");
        System.out.println("trackNumber==================" + trackNumber);
        JoinerFormDTO employeeData = ((JoinerFormImpl) this.getBean("joinerDao")).checkTrackNumber(trackNumber);
        String id = employeeData.getJfId();
        formData.setJfId(id);
        int emp_id = 0;
        int jfStatus = ((JoinerFormImpl) this.getBean("joinerDao")).getJfStatus(Integer.parseInt(id));
        if (jfStatus == 6) {
            emp_id = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpId(id);
        } else {
            emp_id = Integer.parseInt(id);
        }
        formData.setEmp_id(emp_id);
        System.out.println("id==============@@@@@@@@@@@@" + id);
        int i = 0;
        try {
            i = Integer.parseInt(id.trim());
        } catch (NumberFormatException e) {
            System.out.println("in side joineraddstepTwo++++++" + e);
        }

        List<NationalityDTO> stream = ((JoinerFormImpl) this.getBean("joinerDao")).getStreamDetails();
        request.setAttribute("stream", stream);
        List<JoinerFormDTO> skillDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getSkillData(i);
        request.setAttribute("skillDetails", skillDetails);

        List<NationalityDTO> qualification = ((JoinerFormImpl) this.getBean("joinerDao")).getQualificationDetails();
        request.setAttribute("qualification", qualification);
        List<NationalityDTO> degree = ((JoinerFormImpl) this.getBean("joinerDao")).getDegreeDetails();
        Iterator itr = degree.iterator();
        while (itr.hasNext()) {
            NationalityDTO degree1 = (NationalityDTO) itr.next();

        }
        request.setAttribute("degree", degree);
        List<NationalityDTO> institution = ((JoinerFormImpl) this.getBean("joinerDao")).getInstitutionDetails();
        request.setAttribute("institution", institution);
        List<NationalityDTO> university = ((JoinerFormImpl) this.getBean("joinerDao")).getUniversityDetails();
        request.setAttribute("university", university);

        List<JoinerFormDTO> certificationDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getCertificationData(Integer.parseInt(id));
        request.setAttribute("certificationDetails", certificationDetails);

        CommonConfigurations ccfg = (CommonConfigurations) this.getBean("CommonConfigurations");
        CommonConfigurations.id = id;
        List<JoinerFormDTO> joinerData = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(ccfg);
        List<JoinerFormDTO> prevEmpDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getJfPrevEmpData(id);// only one comp details as issue in jsp
        List<JoinerFormDTO> expProofDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getJfExpProofData(id);
        request.setAttribute("expProofDetails", expProofDetails);
        //JoinerFormDTO prevEmpDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getJfPrevEmpData(id);
        List<JoinerFormDTO> educationDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getEducationData(Integer.parseInt(id));

        List<JoinerFormDTO> eduQaulifiProofDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEduQualifiProofData(id);
        request.setAttribute("eduQaulifiProofDetails", eduQaulifiProofDetails);
        //****************************** 

        Iterator it = eduQaulifiProofDetails.iterator();
        while (itr.hasNext()) {
            JoinerFormDTO eduQaulifiProofDetails1 = (JoinerFormDTO) itr.next();
            //System.out.println(" eduQaulifiProofDetails1.getFiledata()==================="+eduQaulifiProofDetails1.getFileData());
        }

        Iterator itr1 = educationDetails.iterator();

        while (itr1.hasNext()) {
            JoinerFormDTO educationDetails1 = (JoinerFormDTO) itr1.next();
            if (isIntNumber(educationDetails1.getInstitutionX())) {
                educationDetails1.setInstituteName("0");
            } else {
                educationDetails1.setInstituteName("1");
            }

            if (isIntNumber(educationDetails1.getUniversityX())) {
                educationDetails1.setUniversityName("0");
            } else {
                educationDetails1.setUniversityName("1");
            }
            if (isIntNumber(educationDetails1.getQualificationX())) {
                educationDetails1.setQualificationName("0");
            } else {
                educationDetails1.setQualificationName("1");
            }

        }//end of itarator
        request.setAttribute("educationDetails", educationDetails);
        request.setAttribute("joinerData", joinerData);
        request.setAttribute("employeeData", employeeData);
        request.setAttribute("prevEmpDetail", prevEmpDetails);
        request.setAttribute("printStatus", printStatus);
        request.setAttribute("joinerDob", formData.getDateOfBirth());
        if (request.getParameter("backButton") == null) {
            JoinerDAO jfi = ((JoinerDAO) this.getBean("joinerDao"));
            ((JoinerFormImpl) this.getBean("joinerDao")).addOrUpdateEmployeeAddress(formData);
            ((JoinerFormImpl) this.getBean("joinerDao")).addOrUpdateEmployeeStepOne(formData, jfi);// implemented 
        }
        return new ModelAndView("joineraddsteptwo");
    }

    public ModelAndView joinerFormAddOrUpdateStepThree(HttpServletRequest request, HttpServletResponse response, NewDTO formData) throws Exception {

        HttpSession sessionObj = request.getSession();
        String trackNumber = (String) sessionObj.getAttribute("trackNumber");
        String printStatus = request.getParameter("printStatus");
        JoinerFormDTO employeeData = ((JoinerFormImpl) this.getBean("joinerDao")).checkTrackNumber(trackNumber);
        String id = employeeData.getJfId();
        CommonConfigurations ccfg = new CommonConfigurations();
        ccfg.id = id;
        List<JoinerFormDTO> joinerData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(ccfg);
        JoinerFormDTO joinerData = null;
        Iterator itr = joinerData1.iterator();
        while (itr.hasNext()) {
            joinerData = (JoinerFormDTO) itr.next();
        }
        List<JoinerFormDTO> familyMemberDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getJfFamilyMemberData(id);


        Iterator it = familyMemberDetails.iterator();
        while (it.hasNext()) {
            JoinerFormDTO familydetails1 = (JoinerFormDTO) it.next();
        }


        List<JoinerFormDTO> visaDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getJfVisaData(id);
        List<JoinerFormDTO> passportDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getPassportData(id);
        List<JoinerFormDTO> emergencyDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getEmergencyContacts(id);

        List<NationalityDTO> countryList = ((JoinerFormImpl) this.getBean("joinerDao")).getCountryList();
        request.setAttribute("countryList", countryList);
        List<NationalityDTO> visaList = ((JoinerFormImpl) this.getBean("joinerDao")).getVisaList();
        request.setAttribute("visaList", visaList);

        request.setAttribute("joinerData", joinerData);
        request.setAttribute("employeeData", employeeData);
        request.setAttribute("familyMemberDetails", familyMemberDetails);
        request.setAttribute("visaDetails", visaDetails);
        request.setAttribute("passportDetails", passportDetails);
        request.setAttribute("emergencyDetails", emergencyDetails);
        request.setAttribute("relationShips", CommonConfigurations.relationShips);
        request.setAttribute("printStatus", printStatus);
        if (request.getParameter("backButton") == null) {
            JoinerDAO jfi = ((JoinerDAO) this.getBean("joinerDao"));
            ((JoinerFormImpl) this.getBean("joinerDao")).addOrUpdateEmployeeStepTwo(formData, jfi);
        }
        ModelAndView mv = new ModelAndView("joineraddstepthree");
//    fwd.setRedirect(true);
        return mv;
    }

//     public ModelAndView joinerFormAddOrUpdateStepFour(HttpServletRequest request, HttpServletResponse response, JoinerNewDTO formData) throws Exception {
//         System.out.println("joinerFormAddOrUpdateStepFour in controlle enterd =========================");
//         // storing step Three Details ...................
//         return new ModelAndView("joineraddstepfour");
//         
//     }
    public ModelAndView joinerFormAddOrUpdateStepFour(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {
        HttpSession sessionObj = requestObj.getSession();

        String empId = (String) sessionObj.getAttribute("employeeId");
        List<MasterDataDTO> dataApprovedByList = null;
        String trackNumber = (String) sessionObj.getAttribute("trackNumber");
        String printStatus = requestObj.getParameter("printStatus");
        JoinerFormDTO employeeData = ((JoinerFormImpl) this.getBean("joinerDao")).checkTrackNumber(trackNumber);

        String id = employeeData.getJfId();
        CommonConfigurations ccfg = new CommonConfigurations();
        ccfg.id = id;
        List<JoinerFormDTO> joinerData1 = (List<JoinerFormDTO>) ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(ccfg);
        Iterator itr = joinerData1.iterator();

        JoinerFormDTO joinerData = (JoinerFormDTO) itr.next();

        if (joinerData.joinerEmpId != null) {
            List<JoinerFormDTO> pendingDocuments = (List<JoinerFormDTO>) ((JoinerFormImpl) this.getBean("joinerDao")).getPendingDocuments(id);
            JoinerFormDTO joinerMasterData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerMasterData(joinerData.joinerEmpId);
            JoinerFormDTO joinerMasterDataBillable = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerMasterDataBillable(joinerData.getJfId());


            if (joinerMasterDataBillable == null) {
                List<MasterDataDTO> empBillableList = ((MasterDaoImpl) this.getBean("MasterDao")).getMasterDataList(CommonConfigurations.empBillableConfigId);
                requestObj.setAttribute("empBillableList", empBillableList);
            } else {
                requestObj.setAttribute("joinerMasterDataBillable", joinerMasterDataBillable.getMasterBillable());
            }
            requestObj.setAttribute("joinerMasterData", joinerMasterData);
            requestObj.setAttribute("pendingDocuments", pendingDocuments);
        }

        if (joinerData.rrfRes != null) {
            String rrfRes = ((JoinerFormImpl) this.getBean("joinerDao")).getRRFRes(joinerData.rrfRes);
            requestObj.setAttribute("rrfRes", rrfRes);
        } else {
            requestObj.setAttribute("rrfRes", "");
        }

        String employeeType = ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeType(id);
        String dataApprovedBy = null;
        if (joinerData.getDataApprovedBy() == null || joinerData.getDataApprovedBy().equals("")) {
            dataApprovedBy = "";
        } else {
            dataApprovedBy = ((JoinerFormImpl) this.getBean("joinerDao")).getCcMailNames(joinerData.getDataApprovedBy());
        }
//List<MasterDataDTO> levelOneStructure = ((MasterDaoImpl) this.getBean("MasterDao"))
        List<JoinerFormDTO> referrerDTDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getJfReferrerDTData(id);
        List<JoinerFormDTO> referrerPrevCompDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getJfReferrerPrevCompData(id);
        List<MasterDataDTO> employementTypeList = ((MasterDaoImpl) this.getBean("MasterDao")).getMasterDataList(CommonConfigurations.employementTypeConfigId);
        List<MasterDataDTO> empBillableList = ((MasterDaoImpl) this.getBean("MasterDao")).getMasterDataList(CommonConfigurations.empBillableConfigId);
        List<MasterDataDTO> cmpLocationList = ((MasterDaoImpl) this.getBean("MasterDao")).getCmpLocationList();
        dataApprovedByList = ((MasterDaoImpl) this.getBean("MasterDao")).getEmployeeDetailsFromId(empId);
        List<JoinerFormDTO> documentList = ((JoinerFormImpl) this.getBean("joinerDao")).getDocumentList(id);
        if (joinerData.getDataVerifiedMailCc() != null) {
            if (!joinerData.getDataVerifiedMailCc().isEmpty()) {
                String dataVerifiedMailCC = ((JoinerFormImpl) this.getBean("joinerDao")).getCcMailNames(joinerData.getDataVerifiedMailCc());
                requestObj.setAttribute("dataVerifiedMailCC", dataVerifiedMailCC);
            }
        }

        requestObj.setAttribute("joinerData", joinerData);
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("referrerDTDetails", referrerDTDetails);
        requestObj.setAttribute("referrerPrevCompDetails", referrerPrevCompDetails);
        requestObj.setAttribute("printStatus", printStatus);
        requestObj.setAttribute("employeeType", employeeType);
        requestObj.setAttribute("dataApprovedByList", dataApprovedByList);
        requestObj.setAttribute("dataApprovedBy", dataApprovedBy);
        requestObj.setAttribute("employementTypeList", employementTypeList);
        requestObj.setAttribute("empBillableList", empBillableList);
        requestObj.setAttribute("cmpLocationList", cmpLocationList);
        requestObj.setAttribute("documentList", documentList);
        System.out.println("Back Button Four = " + requestObj.getParameter("backButton"));
        if (requestObj.getParameter("backButton") == null) {
            JoinerDAO jfi = ((JoinerDAO) this.getBean("joinerDao"));
            ((JoinerFormImpl) this.getBean("joinerDao")).addOrUpdateEmployeeStepThree(formData, jfi);

        }
        return new ModelAndView("joineraddstepfour");

    }

//   public ModelAndView finalSubmit(HttpServletRequest request, HttpServletResponse response, JoinerNewDTO formData) throws Exception {
//       System.out.println("finalSubmit in controller enterd ========================="); 
//       // storing step four Details and forwarding to appropriate Jsp
//       return new ModelAndView("success");
//       
//   }  
    public ModelAndView finalSubmit(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside finalSubmit");
        SendMail mailObj = null;
        try {
            mailObj = new SendMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = null;
        HttpSession session = requestObj.getSession();
        Properties configFile = new Properties();
        InputStream input = null;

        input = new FileInputStream("D:\\propertyfiles\\ojfconfiguration.properties");
        configFile.load(input);
        ojfGroupMail = configFile.getProperty("ojfGroupMail");
        JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL = configFile.getProperty("JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL");


        String groupId = (String) session.getAttribute("groupId");
        String mailCC = "";
        String userEmpId = (String) session.getAttribute("employeeId");
        String employeeType = requestObj.getParameter("employeeType");
        String buttonType = formData.getButtonType();

        if (requestObj.getParameter("as_values_dataVerifiedMailCc") == null ? "" != null : !requestObj.getParameter("as_values_dataVerifiedMailCc").equals("")) {
            int mailCcLength = requestObj.getParameter("as_values_dataVerifiedMailCc").length();
            formData.setDataVerifiedMailCc(requestObj.getParameter("as_values_dataVerifiedMailCc").substring(0, (mailCcLength - 1)));
            formData.setDataVerifiedBy(userEmpId);
        }
        if (requestObj.getParameter("as_values_dataApprovedBy") != null) {
            if (requestObj.getParameter("as_values_dataApprovedBy") == null ? "" != null : !requestObj.getParameter("as_values_dataApprovedBy").equals("")) {
                int dataApprovedByLength = requestObj.getParameter("as_values_dataApprovedBy").length();
                formData.setDataApprovedBy(requestObj.getParameter("as_values_dataApprovedBy").substring(0, (dataApprovedByLength - 1)));
                formData.setCheckJoinerWorkEmail("1");
            }
        }

        int empId = ((JoinerFormImpl) this.getBean("joinerDao")).addOrUpdateEmployeeFinalSubmit(requestObj, employeeType, groupId, formData);//
        Integer userId = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerUserId(formData.getJfId());
        String employeeNumber = ((JoinerFormImpl) this.getBean("joinerDao")).getNewEmployeeNumber(empId);
        int jfStatus = ((JoinerFormImpl) this.getBean("joinerDao")).getJfStatus(Integer.parseInt(formData.getJfId()));
        if (jfStatus == 6) {
            int id = ((JoinerFormImpl) this.getBean("joinerDao")).getEmpId(employeeNumber);
            ((JoinerFormImpl) this.getBean("joinerDao")).updateEmpAddressData(formData.getJfId(), id);
        }

        formData.setEmpId1(formData.getJfId());
        String joinerMasterEmpId = String.valueOf(empId);
        // String joinerMasterEmpId=String.valueOf(formData.getEmpId());
        JoinerFormDTO joinerMasterData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerMasterData(joinerMasterEmpId);
        JoinerFormDTO joinerDto = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);

        if (buttonType.equalsIgnoreCase("Add Employee to iDeal")) {

            Calendar calendar = Calendar.getInstance();
            String currentTimestamp = String.valueOf(calendar.getTimeInMillis());

            formData.setTrackNumber(currentTimestamp.toString());

            MailMessages mailMessageObj = new MailMessages();
            long password = calendar.getTimeInMillis();
            String username = formData.getPersonalEmailId1();
            formData.setUserName(username);
            formData.setPassword(password);

            String ojfHr = ((JoinerFormImpl) this.getBean("joinerDao")).getOjfHrName(formData.getOjfHrName());

            System.out.println("joinerDto.getFirstName()" + joinerDto.getFirstName());
            System.out.println("Details*****" + joinerDto.getLastName() + joinerDto.getPersonalEmailId1() + joinerDto.getPracticeGroup() + joinerDto.getReportingManager() + joinerDto.getJoinerEmpId()
                    + joinerDto.getJoinedDate() + joinerDto.getBandName() + joinerDto.getSubBandName() + joinerDto.getDesigName() + joinerDto.getJfStatus()
                    + joinerDto.getEmployeeLocation() + joinerDto.getEmployeeCategory() + joinerDto.getSubPracticeGroup() + joinerDto.getWorkEmail() + joinerDto.getOjfHrName() + ojfHr);

            System.out.println(" before updateNewEmployeeMailDetail(formData) executing************** ");
            ((JoinerFormImpl) this.getBean("joinerDao")).updateNewEmployeeMailDetail(formData);
            System.out.println("after updateNewEmployeeMailDetail(formData) executing************** ");

            String mailSubject = mailMessageObj.getOjfSubject("NewJoinee");

            String mailTo = "";

            String mailMessage = mailMessageObj.getOjfMessage(requestObj, "NewJoinee", employeeNumber + "#-#"
                    + joinerDto.getFirstName() + " " + joinerDto.getLastName() + "#-#" + joinerDto.getDesigName() + "#-#" + joinerDto.getSubBandName() + "#-# "
                    + joinerDto.getPracticeGroup() + "#-#" + joinerDto.getSubPracticeGroup() + "#-#" + joinerMasterData.getEmpStatus() + "#-#" + joinerMasterData.getEmpBillable() + "#-#" + joinerDto.getReportingManager() + "#-#"
                    + joinerDto.getEmployeeLocation() + "#-#" + joinerDto.getJoinedDate() + "#-#" + joinerDto.getPersonalEmailId1() + "#-#" + joinerDto.getWorkEmail() + "#-#" + ojfHr);
            SendMailTLS mailObject = new SendMailTLS();
            System.out.println("rm mail&&&&&&&" + joinerDto.getRmMail());
            System.out.println("test %%%%%  config  " + ojfGroupMail);
            System.out.println("test from config%%%%%%%%%%%  " + JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL);
            String groupMail = ojfGroupMail;
            System.out.println("groupMail" + groupMail);

            List<JoinerFormDTO> dataVerifiedMailOjf = ((JoinerFormImpl) this.getBean("joinerDao")).getOjfCcMail(formData.getEmployeeCcEmail());
            requestObj.setAttribute("dataVerifiedMailOjf", dataVerifiedMailOjf);

            String dlCcList = ((JoinerFormImpl) this.getBean("joinerDao")).getOjfdlCcList();
            String dlCcStructure = ((JoinerFormImpl) this.getBean("joinerDao")).getDlCcStructure(joinerDto.getPracticeGroupIdd());

            // old mailCC = mailCC + ',' + groupMail;
            System.out.println("joinerDto.getPracticeGroupIdd()" + joinerDto.getPracticeGroupIdd());
            if ("2".equals(joinerDto.getPracticeGroupIdd()) || "5".equals(joinerDto.getPracticeGroupIdd())) {
                System.out.println("inside structure####");
                if (dlCcStructure != null) {
                    mailCC = dlCcList + ',' + dlCcStructure;
                } else {
                    mailCC = dlCcList;
                }
            } else {
                mailCC = dlCcList;
            }

            List<JoinerFormDTO> mailToOjf = ((JoinerFormImpl) this.getBean("joinerDao")).getOjfToMail(formData.getEmployeeToEmail());
            requestObj.setAttribute("mailToOjf", mailToOjf);

            String dlToList = ((JoinerFormImpl) this.getBean("joinerDao")).getOjfdlToList();

            mailTo = dlToList + ',' + joinerDto.getRmMail() + ',' + joinerDto.getPersonalEmailId1();
            System.out.println("mailCC==" + mailCC);
            System.out.println("mailTo*****" + mailTo);
            System.out.println("Reporting Manager Mail****+" + joinerDto.getRmMail());
            try {
                mailObject.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
                ((JoinerFormImpl) this.getBean("joinerDao")).updateMailSuccessStatus(formData.getJfId());
            } catch (Exception e) {
                ((JoinerFormImpl) this.getBean("joinerDao")).updateMailFailureStatus(formData.getJfId());
                //sout and update mail fail status
            }

            System.out.println("mail sent successfully========in add ideal");
            session.setAttribute("successMsg", "Mail Triggered Successfully======");

            session.setAttribute("successMsg", "Employee Added to iDeal.Employee Number is " + employeeNumber);

            if (response.isCommitted()) {
                System.out.println("TRUE");
            } else {
                System.out.println("FALSE");
            }
            String redirecturl = JOINING_FORMALITY_IDEAL_ACCOUNT_CREATION_URL + empId + "/" + userId;
            System.out.println(" " + redirecturl);
            response.sendRedirect(redirecturl);

        }
        if (buttonType.equalsIgnoreCase("Save") && groupId.equalsIgnoreCase(CommonConfigurations.JOINING_FORMALITY_NEWCANDIDATE_GROUPID)) {
            mv = new ModelAndView("redirect:logout.htm");
        }
        if (buttonType.equalsIgnoreCase("Submit") && groupId.equalsIgnoreCase(CommonConfigurations.JOINING_FORMALITY_NEWCANDIDATE_GROUPID)) {
            mv = new ModelAndView("success");
        }
        if (buttonType.trim().equalsIgnoreCase("Data Verified") && (groupId.equalsIgnoreCase("5") || groupId.equalsIgnoreCase("24"))) {
            session.setAttribute("successMsg", "Data Verification Completed");
            mv = new ModelAndView("redirect:begin.htm");
        }
        if (buttonType.trim().equalsIgnoreCase("Send back to Employee") && (groupId.equalsIgnoreCase("5") || groupId.equalsIgnoreCase("24"))) {
            session.setAttribute("successMsg", "Joining Form send back to employee");
            mv = new ModelAndView("redirect:begin.htm");
        }
        // Forward fwd = new Forward("joinercomplete");
        //    fwd.setRedirect(true);

        return mv;


    }

    public ModelAndView triggerJoiningMail(HttpServletRequest request, HttpServletResponse response,JoinerFormDTO formData) throws Exception {

        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String ojf_id = request.getParameter("jfId");
        JoinerFormDTO joinerDto = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerMailData(ojf_id);
        MailMessages mailMessageObj = new MailMessages();
        String mailCC = "";
        String ojfHr = ((JoinerFormImpl) this.getBean("joinerDao")).getOjfHrName(ojf_id);

        System.out.println("joinerDto.getFirstName()" + joinerDto.getFirstName());
        System.out.println("Details*****" + joinerDto.getLastName() + joinerDto.getPersonalEmailId1() + joinerDto.getPracticeGroup() + joinerDto.getReportingManager() + joinerDto.getJoinerEmpId()
                + joinerDto.getJoinedDate() + joinerDto.getBandName() + joinerDto.getSubBandName() + joinerDto.getDesigName() + joinerDto.getJfStatus()
                + joinerDto.getEmployeeLocation() + joinerDto.getEmployeeCategory() + joinerDto.getSubPracticeGroup() + joinerDto.getWorkEmail() + joinerDto.getOjfHrName() + ojfHr);

        String mailSubject = mailMessageObj.getOjfSubject("NewJoinee");

        String mailTo = "";

        String mailMessage = mailMessageObj.getOjfMessage(request, "NewJoinee", joinerDto.getJoinerEmpId() + "#-#"
                + joinerDto.getFirstName() + " " + joinerDto.getLastName() + "#-#" + joinerDto.getDesigName() + "#-#" + joinerDto.getSubBandName() + "#-# "
                + joinerDto.getPracticeGroup() + "#-#" + joinerDto.getSubPracticeGroup() + "#-#" + joinerDto.getEmpStatus() + "#-#" + joinerDto.getEmpBillable() + "#-#" + joinerDto.getReportingManager() + "#-#"
                + joinerDto.getEmployeeLocation() + "#-#" + joinerDto.getJoinedDate() + "#-#" + joinerDto.getPersonalEmailId1() + "#-#" + joinerDto.getWorkEmail() + "#-#" + ojfHr);
        SendMailTLS mailObject = new SendMailTLS();

        String dlCcList = ((JoinerFormImpl) this.getBean("joinerDao")).getOjfdlCcList();
        String dlCcStructure = ((JoinerFormImpl) this.getBean("joinerDao")).getDlCcStructure(joinerDto.getPracticeGroupIdd());
        if ("2".equals(joinerDto.getPracticeGroupIdd()) || "5".equals(joinerDto.getPracticeGroupIdd())) {
            System.out.println("inside structure####");
            if (dlCcStructure != null) {
                mailCC = dlCcList + ',' + dlCcStructure;
            } else {
                mailCC = dlCcList;
            }
        } else {
            mailCC = dlCcList;
        }

        String dlToList = ((JoinerFormImpl) this.getBean("joinerDao")).getOjfdlToList();

        mailTo = dlToList + ',' + joinerDto.getRmMail() + ',' + joinerDto.getPersonalEmailId1();
        try {
            mailObject.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            ((JoinerFormImpl) this.getBean("joinerDao")).updateMailSuccessStatus(ojf_id);
            session.setAttribute("successMsg", "Joiner Mail triggered Successfully!!!");
        } catch (Exception e) {
            //sout and update mail fail status
            ((JoinerFormImpl) this.getBean("joinerDao")).updateMailFailureStatus(ojf_id);
            session.setAttribute("successMsg", "Joiner Mail Not Triggered");
        }
        mv = new ModelAndView("redirect:begin.htm");
        return mv;
    }
    public ModelAndView newEmployeeAdd(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO joinerformDto) throws Exception {
        String jfId = joinerformDto.getJfId();
        String buttonStatus = request.getParameter("buttonStatus");
        String canStatus = request.getParameter("canStatus");
        Properties prop = new Properties();
        prop.load(new FileInputStream(CommonConfigurations.propertyPath));
        List<MasterDataDTO> levelOneStructure = ((JoinerFormImpl) this.getBean("joinerDao")).getStructureDetails(0);
        List<MasterDataDTO> bandList = ((JoinerFormImpl) this.getBean("joinerDao")).getBandDetails("0");
        List<MasterDataDTO> designationList = ((JoinerFormImpl) this.getBean("joinerDao")).getDesignationDetails();
        List<MasterDataDTO> vendorList = ((MasterDaoImpl) this.getBean("MasterDao")).getvendorDetaillist();
        List<MasterDataDTO> reportingManagerObj = null;
        List<MasterDataDTO> emprefObj = null;
        List<MasterDataDTO> mailCcListObj = null;
        String emprefname = null;
        String contractEmployeeName = null;
        List<MasterDataDTO> contractEmployeeObj = null;
        LinkedHashMap mapObj = CommonFunctions.getEmployeeType();
        JoinerFormDTO candidateDetails = null;


        if (jfId != null) {
            candidateDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getCandidateDetails(joinerformDto);
            String rrfName = ((JoinerFormImpl) this.getBean("joinerDao")).getRrfId((String) candidateDetails.getRrfRes());
            JoinerFormDTO passportArr = ((JoinerFormImpl) this.getBean("joinerDao")).getPassportNumber(jfId);
//            System.out.println("Passport Details $*$*$*$*$*$*$*$*$"+passportArr.getPassportNumberX());
            List<MasterDataDTO> practiceGroupList = ((JoinerFormImpl) this.getBean("joinerDao")).getPracticeGroup(candidateDetails.getStructureId());
            List<MasterDataDTO> subPracticeGroupList = ((JoinerFormImpl) this.getBean("joinerDao")).getSubPracticeGroup(candidateDetails.getPracticeGroupIdd());
            Iterator it = subPracticeGroupList.iterator();
            while (it.hasNext()) {
                MasterDataDTO dto = (MasterDataDTO) it.next();
            }
            List<MasterDataDTO> subBandList = ((JoinerFormImpl) this.getBean("joinerDao")).getBandDetails(candidateDetails.getBandId());
            reportingManagerObj = ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeDetailsFromId(candidateDetails.getReportingManager());
            if (candidateDetails.getEmpref() == null || candidateDetails.getEmpref().equalsIgnoreCase("Enter Name") || candidateDetails.getEmpref().equals("")) {
                System.out.println("if");
            } else {
                System.out.println("else");
                if (candidateDetails.getSourceofhire().equals("r")) {
                    emprefname = ((JoinerFormImpl) this.getBean("joinerDao")).getCcMailNames(candidateDetails.getEmpref());
                    emprefObj = ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeDetailsFromId(candidateDetails.getEmpref());
                } else if (candidateDetails.getSourceofhire().equals("v")) {
                    emprefname = ((JoinerFormImpl) this.getBean("joinerDao")).getVendorName(candidateDetails.getEmpref());
                } else if (candidateDetails.getSourceofhire().equals("j")) {
                    emprefname = ((JoinerFormImpl) this.getBean("joinerDao")).getJobPortalName(candidateDetails.getEmpref());
                } else {
                }
            }

            if ("e".equals(candidateDetails.getEmployeeCategory())) {
                contractEmployeeName = ((JoinerFormImpl) this.getBean("joinerDao")).getCcMailNames(candidateDetails.getContract_employee_id());
                contractEmployeeObj = ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeDetailsFromId(candidateDetails.getContract_employee_id());
            }
            System.out.println("$$$$$$$$$$$$$$$$:" + candidateDetails.getMailCc());
            if (candidateDetails.getMailCc() != null && !candidateDetails.getMailCc().equals("")) {
                mailCcListObj = ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeDetailsFromId(candidateDetails.getMailCc());
            }
            System.out.println("Reporting Manager ^*^*^*^*^*^**^*^*^ " + candidateDetails.getReportingManager());
            String reportingManagerName = ((JoinerFormImpl) this.getBean("joinerDao")).getCcMailNames(candidateDetails.getReportingManager());
            String hrMailCCNames = null;
            if (candidateDetails.getMailCc() != null && !candidateDetails.getMailCc().equals("")) {
                hrMailCCNames = ((JoinerFormImpl) this.getBean("joinerDao")).getCcMailNames(candidateDetails.getMailCc());
            } else {
                hrMailCCNames = "";
            }
            request.setAttribute("contractEmployeeName", contractEmployeeName);
            request.setAttribute("contractEmployeeObj", contractEmployeeObj);
            request.setAttribute("hrMailCCNames", hrMailCCNames);
            request.setAttribute("reportingManagerName", reportingManagerName);
            request.setAttribute("emprefname", emprefname);
            request.setAttribute("candidateDetails", candidateDetails);
            request.setAttribute("rrfName", rrfName);
            request.setAttribute("practiceGroupList", practiceGroupList);
            request.setAttribute("subPracticeGroupList", subPracticeGroupList);
            request.setAttribute("subBandList", subBandList);
            request.setAttribute("passportArr", passportArr);
        }

        request.setAttribute("levelOneStructure", levelOneStructure);
        /*Load SOURCE OF HIRE*/
        String sourceid = ((JoinerFormImpl) this.getBean("joinerDao")).getsourcehirelisstbyname("source_of_hire");
        List<SourcehireDTO> sourceofhirelist = ((JoinerFormImpl) this.getBean("joinerDao")).getsourcehirelisst(sourceid);
        request.setAttribute("sourceofhirelist", sourceofhirelist);
        /*Ends*/

        /*Load Job portal*/
        String portalid = ((JoinerFormImpl) this.getBean("joinerDao")).getportalidbyname("job_portal");
        List<SourcehireDTO> portallistlist = ((JoinerFormImpl) this.getBean("joinerDao")).getjobportallist(portalid);
        request.setAttribute("portallistlist", portallistlist);
        /*Ends*/

        request.setAttribute("bandList", bandList);
        request.setAttribute("designationList", designationList);
        request.setAttribute("employeeTypeList", mapObj);
        request.setAttribute("vendorList", vendorList);
        request.setAttribute("reportingManagerObj", reportingManagerObj);
        request.setAttribute("emprefObj", emprefObj);
        request.setAttribute("mailCcList", mailCcListObj);
        request.setAttribute("submitButton", prop.getProperty("submitButton"));
        request.setAttribute("saveButton", prop.getProperty("saveButton"));
        request.setAttribute("triggerMail", prop.getProperty("triggerMail"));
        request.setAttribute("cancelButton", prop.getProperty("cancelButton"));
        if (buttonStatus != null) {
            request.setAttribute("buttonStatus", buttonStatus);
            request.setAttribute("canStatus", canStatus);
        }

        ModelAndView mv = new ModelAndView("newemployeeadd");
        return mv;

    }

    public ModelAndView getCompanyStructureX(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {
        // String selectedStructure = request.getParameter("structureId");

        String selectedStructure = formData.getStructureId();
        System.out.println("selectedStructure = " + selectedStructure);
        List< MasterDataDTO> structureList = new ArrayList();
        if (selectedStructure != null) {
            structureList = ((JoinerFormImpl) this.getBean("joinerDao")).getStructureDetails(Integer.parseInt(selectedStructure));
        }
        request.setAttribute("getDataFor", "CompanyStructureName");
        Iterator itr = structureList.iterator();
        while (itr.hasNext()) {
            MasterDataDTO structureList1 = (MasterDataDTO) itr.next();
            response.getOutputStream().write(structureList1.getStructureId().getBytes());
            response.getOutputStream().write(",".getBytes());
            response.getOutputStream().write(structureList1.getStructureName().getBytes());
            response.getOutputStream().write(":".getBytes());
        }
return null;

    }

    public ModelAndView getCompanyStructure(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        String selectedStructure = request.getParameter("structureId");
        System.out.println("selectedStructure = " + selectedStructure);
        request.setAttribute("structureList", ((JoinerFormImpl) this.getBean("joinerDao")).getStructureDetails(Integer.parseInt(selectedStructure)));
        request.setAttribute("getDataFor", "CompanyStructureName");
        return new ModelAndView("ajax");
        // return null;

    }

    public ModelAndView getSubDetails(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {
        System.out.println("inside getSubDetails++++");
        String selectedBandId = formData.getStructureId();
        // String selectedBandId1 = request.getParameter("structureId");
        System.out.println("selectedBandId = " + selectedBandId);
        List<MasterDataDTO> subBandList = ((MasterDaoImpl) this.getBean("MasterDao")).getBandDetails(selectedBandId);
        System.out.println("subBandList ============= " + subBandList);
        request.setAttribute("subBandList", subBandList);
        request.setAttribute("getDataFor", "subBandDetails");
        return new ModelAndView("ajax");





    }

    public ModelAndView getEmployeeName(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {
        //formData.getQ();
        String searchString = request.getParameter("q");
        System.out.println("queryString Typed = " + searchString);
        request.setAttribute("employeeName", ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeName(searchString));
        request.setAttribute("getDataFor", "employeeName");
        return new ModelAndView("ajax");
    }

    public ModelAndView getEmployeeNameOfHr(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {
        //formData.getQ();
        String searchString = request.getParameter("q");
        System.out.println("queryString Typed = " + searchString);
        request.setAttribute("employeeName", ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeNameOfHr(searchString));
        request.setAttribute("getDataFor", "employeeName");
        return new ModelAndView("ajax");
    }

    public ModelAndView getEmployeeNamebyContract(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        String searchString = request.getParameter("q");
        System.out.println("asdfasdf=======>" + request.getParameter("asdf"));
        System.out.println("queryString Typed = " + searchString);
        request.setAttribute("employeeName", ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeNamebyContract(searchString));
        request.setAttribute("getDataFor", "employeeName");

        return new ModelAndView("ajax");
    }

    public ModelAndView loadRRF(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {
        String val;
        String jfId;
        val = formData.getPracticeGroup();
        //formData.setPracticeGroup(val);
        jfId = formData.getJfId();
        System.out.println("val++++++++++++" + val);
        System.out.println("jfId++++++++++++" + jfId);
        System.out.println("formData++++++++" + formData);
        List<JoinerFormDTO> rrfData = ((JoinerFormImpl) this.getBean("joinerDao")).getRRFDetails(formData);
        System.out.println("rrfData==============================" + rrfData);
        Iterator itr = rrfData.iterator();
        while (itr.hasNext()) {
            JoinerFormDTO rrfData1 = (JoinerFormDTO) itr.next();
            response.getOutputStream().write(rrfData1.getRrfId().getBytes());
            response.getOutputStream().write(",".getBytes());
            response.getOutputStream().write(rrfData1.getRrfName().getBytes());
            response.getOutputStream().write(":".getBytes());
        }

        return null;

    }

    public ModelAndView addOrUpdateEmployee(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("addOrUpdateEmployee() executing =******************* ");
        HttpSession session = request.getSession();
        String buttonName = formData.getButtonName();
        String userEmpId = (String) session.getAttribute("employeeId");
        formData.setUserEmpId(userEmpId);
        String appAddProperty = formData.getStatus();
        String mailCC = null;
        ModelAndView mv = null;
        if (appAddProperty == null || appAddProperty.equals("")) {
            //try================
            try {
                if (request.getParameter("as_values_rmName") == null ? "" != null : !request.getParameter("as_values_rmName").equals("")) {
                    int rmLength = request.getParameter("as_values_rmName").length();
                    formData.setReportingManager(request.getParameter("as_values_rmName").substring(0, (rmLength - 1)));
                }

                if (buttonName.equals("Trigger Mail")) {
                    if (request.getParameter("as_values_mailCc") == null ? "" != null : !request.getParameter("as_values_mailCc").equals("")) {
                        int mailCcLength = request.getParameter("as_values_mailCc").length();
                        formData.setMailCc(request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1)));
                        System.out.println("MIAL CC +++++++ " + request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1)));
                        mailCC = ((JoinerFormImpl) this.getBean("joinerDao")).getCcMail(request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1)));
                        System.out.println("mailCC(hr) " + mailCC);
                        //formData.setMailCc(mailCC); 
                    }
////                requestObj.setAttribute("successMsg", "Mail Triggered Successfully");
                    session.setAttribute("successMsg", "Mail Triggered Successfully");
                } else {
////                requestObj.setAttribute("successMsg", "Data Saved Successfully");
                    session.setAttribute("successMsg", "Data Saved Successfully");
                }
                this.addOrUpdateEmployee(formData, request, mailCC);//strat.....step1
            } catch (Exception e) {
                System.out.println("------Error in Add Or Update addOrUpdateEmployee JF Controller --------");
                e.printStackTrace();
            }
            mv = new ModelAndView("redirect:begin.htm");
        } else {
            String rrf_id = formData.getRrf_id();
            System.out.println("rrf_id ============" + rrf_id);
            //formData.setRrf_id("1234");          
            SimpleDateFormat sdf = new SimpleDateFormat(CommonConfigurations.CandidateRefDateFormat);
            Calendar calendar = Calendar.getInstance();
            String currentTimestamp = String.valueOf(calendar.getTimeInMillis());
            String currentMonYear = sdf.format(calendar.getTime());
            formData.setTrackNumber(currentTimestamp.toString());

            //formData.setEmpContract("0");
            formData.setAppStatus("C");
            formData.setRefnumber(currentMonYear);

            if (request.getParameter("as_values_empref") == null ? "" != null : !request.getParameter("as_values_empref").equals("")) {
                int emLength = request.getParameter("as_values_empref").length();
                System.out.println("$$$$$$:" + request.getParameter("as_values_empref").substring(0, (emLength - 1)));
                formData.setEmpref(request.getParameter("as_values_empref").substring(0, (emLength - 1)));
            } else {
                formData.setEmpref("0");
            }
            System.out.println(request.getParameter("as_values_empContract"));
            if (request.getParameter("as_values_empContract") == null ? "" != null : !request.getParameter("as_values_empContract").equals("")) {
                int epLength = 0;
                if (request.getParameter("as_values_empContract") != null) {
                    epLength = request.getParameter("as_values_empContract").length();
                }
                //System.out.println("$$$$$$:"+request.getParameter("as_values_empContract").substring(0, (epLength - 1)));
                if (request.getParameter("as_values_empContract") != null) {
                    formData.setEmpContract(request.getParameter("as_values_empContract").substring(0, (epLength - 1)));
                } else {
                    System.out.println(request.getParameter("emp_contract"));
                    formData.setEmpContract(request.getParameter("emp_contract"));
                }
            } else {
                formData.setEmpContract("0");
            }
            try {
                if (buttonName.equals("Trigger Mail")) {
                    if (request.getParameter("as_values_mailCc") == null ? "" != null : !request.getParameter("as_values_mailCc").equals("")) {
                        int mailCcLength = request.getParameter("as_values_mailCc").length();
                        formData.setMailCc(request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1)));
                        System.out.println("MIAL CC +++++++ " + request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1)));
                        mailCC = ((JoinerFormImpl) this.getBean("joinerDao")).getCcMail(request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1)));
                        System.out.println("mailCC(hr) " + mailCC);
                        //formData.setMailCc(mailCC); 
                    }
////                requestObj.setAttribute("successMsg", "Mail Triggered Successfully");
                    session.setAttribute("successMsg", "Mail Triggered Successfully");
                } else {
////                requestObj.setAttribute("successMsg", "Data Saved Successfully");
                    session.setAttribute("successMsg", "Data Saved Successfully");
                }
                this.addOrUpdateEmployee(formData, request, mailCC);//strat.....step1
            } catch (Exception e) {
                System.out.println("------Error in Add Or Update addOrUpdateEmployee JF Controller --------");
                e.printStackTrace();
            }

            mv = new ModelAndView("redirect:begin.htm");
        }
//      mv.addObject("rrfId", formData.getRrf_id());
        return mv;

    }

    public void addOrUpdateEmployee(JoinerFormDTO formData, HttpServletRequest request, String mailCC) {
        //boi logic
        SendMail mailObj = null;
        try {
            mailObj = new SendMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MailMessages mailMessageObj = new MailMessages();
        String refId = formData.getJfId();
        String rrf_id = formData.getRrf_id();
        System.out.println("rrf_id ============" + rrf_id);
//        formData.setRrf(formData.getRrf_id());
//        formData.setRrf_id(rrf_id); 
        Calendar calendar = Calendar.getInstance();
        String currentTimestamp = String.valueOf(calendar.getTimeInMillis());
        formData.setTrackNumber(currentTimestamp.toString());
        if (refId == null ? "" == null : refId.equals("")) {
            try {
                CommonFunctions.TrimSpace(formData);
                this.addNewEmployeeDetail(formData);// step2
            } catch (Exception ex) {
                //  Logger.getLogger(JoiningFormBOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Update JF Page = " + rrf_id);
            try {
                if (formData.getButtonName().trim().equals("Trigger Mail")) {
                    Calendar cal = Calendar.getInstance();
                    long password = cal.getTimeInMillis();
                    String username = formData.getPersonalEmailId1();
                    formData.setUserName(username);
                    formData.setPassword(password);

                    System.out.println(" before updateNewEmployeeMailDetail(formData) executing************** ");
                    ((JoinerFormImpl) this.getBean("joinerDao")).updateNewEmployeeMailDetail(formData);
                    System.out.println("after updateNewEmployeeMailDetail(formData) executing************** ");
                    String mailSubject = mailMessageObj.getSubject("NewJoinee");
////                    String mailMessage = mailMessageObj.getMessage(requestObj, "NewJoinee", formData.getFirstName() + " " + formData.getLastName() + "#-#" + currentTimestamp.toString() + "#-#" + formData.getPersonalEmailId1() + "#-#" + currentTimestamp.toString());
                    String mailMessage = mailMessageObj.getMessage(request, "NewJoinee", formData.getFirstName() + " " + formData.getLastName() + "#-#" + currentTimestamp.toString() + "#-#" + formData.getPersonalEmailId1() + "#-#" + password);
                    //mailObj.smtpMail(formData.getMailTo(), mailSubject, mailMessage, formData.getMailCc());
                    SendMailTLS mailObject = new SendMailTLS();
                    //SendMail mailObject=new SendMail();
                    //mailObject.smtpMail(formData.getMailTo(), mailSubject, mailMessage, formData.getMailCc());
                    mailObject.smtpMail(formData.getMailTo(), mailSubject, mailMessage, mailCC);

                } else {
                    ((JoinerFormImpl) this.getBean("joinerDao")).updateNewEmployeeDetail(formData);
                }
            } catch (Exception ex) {
                System.out.println("controlle Trigger mail Exception" + ex);
                ((JoinerFormImpl) this.getBean("joinerDao")).updateMailFailure(formData);
            }
//     



        }
    }

    public void addNewEmployeeDetail(JoinerFormDTO formData) {
        try {
            formData.setDateOfBirth(CommonFunctions.changeDateFormatToDB(formData.getDateOfBirth()));
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(CommonConfigurations.CandidateRefDateFormat);
            String currentMonYear = sdf.format(cal.getTime());
            formData.setRefnumber(currentMonYear);

            String source_reference = "";
            if (!formData.getEmpref().equals("") && !formData.getEmpref().equalsIgnoreCase("Enter Name Here")) {
                source_reference = formData.getEmpref();
            } else if (!formData.getVendorname().equals("")) {
                source_reference = formData.getVendorname();
            } else if (!formData.getJobporname().equals("")) {
                source_reference = formData.getJobporname();
            }
            formData.setEmpref(source_reference);

            if (formData.getContract_type().equals("n")) {
                formData.setEmpContract("0");
            }
            String appAddProperty = formData.getStatus();
            if (appAddProperty == null || appAddProperty.equals("")) {
                ((JoinerFormImpl) this.getBean("joinerDao")).addNewEmployeeDetail(formData);
                int jf_id = ((JoinerFormImpl) this.getBean("joinerDao")).getLastInsertId();
                System.out.println("jfId @@@@@@@@@@@@@@@@ " + jf_id);
                String jf_idstr = jf_id + "";
                formData.setJfId(jf_idstr);
                ((JoinerFormImpl) this.getBean("joinerDao")).addOrUpdatePassportNumber(formData);

            } else {
                CommonFunctions.TrimSpace(formData);
                ((JoinerFormImpl) this.getBean("joinerDao")).addNewEmployeeDetail(formData);
                int jf_id = ((JoinerFormImpl) this.getBean("joinerDao")).getLastInsertId();
                System.out.println("jfId @@@@@@@@@@@@@@@@ " + jf_id);
                String jf_idstr = jf_id + "";
                formData.setJfId(jf_idstr);
                ((JoinerFormImpl) this.getBean("joinerDao")).addOrUpdatePassportNumber(formData);
                ((JoinerFormImpl) this.getBean("joinerDao")).updateApplicantStatus(formData);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getLocalizedMessage();
            //Logger.getLogger(JoiningFormDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loadState(HttpServletRequest requestObj, HttpServletResponse responseObj) throws Exception {
        int val;
        ModelAndView mv = null;
        List<NationalityDTO> regionList;
        val = Integer.parseInt((String) requestObj.getParameter("country_id"));
        if (requestObj.getParameter("region").equals("region")) {
            regionList = ((JoinerFormImpl) this.getBean("loadState")).getRegionDetails(val);
        } else {
            regionList = ((JoinerFormImpl) this.getBean("loadState")).getCityDetails(val);
        }

        for (int i = 0; i < regionList.size(); i++) {
            responseObj.getOutputStream().write(regionList.get(i).getRegionId().getBytes());
            responseObj.getOutputStream().write(",".getBytes());
            responseObj.getOutputStream().write(regionList.get(i).getRegionName().getBytes());
            responseObj.getOutputStream().write(":".getBytes());
        }
        //while (itr.hasNext()) {
        //requestObj.setAttribute("regionList", regionList);
        //

    }

    public void fileDownload(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) {
        String fileName = requestObj.getParameter("fileName");
        String fileType = requestObj.getParameter("fileType");
        String filePath = CommonConfigurations.fileUploadPath;
        ((JoinerFormImpl) this.getBean("fileDownload")).fileDownload(fileName, filePath, fileType, response);
    }

    public void changeskill(HttpServletRequest requestObj, HttpServletResponse responseObj) throws Exception {
        int streamid = 0;
        String s = requestObj.getParameter("selectedstream");
        System.out.println("s=================" + s);
        if (!s.equals("undefined") && !s.equals("")) {
            streamid = Integer.parseInt(s);
        }
        // streamid = Integer.parseInt((String) requestObj.getParameter("selectedstream"));
        System.out.println("streamid======" + streamid);
        List<NationalityDTO> skillList = ((JoinerFormImpl) this.getBean("skilldetails")).getSkillDetails(streamid);

        for (int i = 0; i < skillList.size(); i++) {
            responseObj.getOutputStream().write(skillList.get(0).getSkillId().getBytes());
            responseObj.getOutputStream().write(",".getBytes());
            responseObj.getOutputStream().write(skillList.get(0).getSkillName().getBytes());
            responseObj.getOutputStream().write(":".getBytes());
        }

    }

    public ModelAndView getEmployeeNamebyhr1(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String searchString = request.getParameter("q");
        System.out.println("queryString Typed = " + searchString);
        request.setAttribute("employeeName", ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeNamebyhr(searchString));

        System.out.println("hi=================================" + ((JoinerFormImpl) this.getBean("joinerDao")).getEmployeeNamebyhr(searchString));
        request.setAttribute("getDataFor", "employeeName");
        return new ModelAndView("ajax");

        // return null;

    }

    //new 
//   public void updateNewEmployeeMailDetail(String refId, JoinerFormDTO formData, long password, String groupId,String empId) {
//        try {
//            formData.setDateOfBirth(CommonFunctions.changeDateFormatToDB(formData.getDateOfBirth()));
//
//            String source_reference = "";
//            if(!formData.getEmpref().equals("") && !formData.getEmpref().equalsIgnoreCase("Enter Name Here"))
//                source_reference = formData.getEmpref();
//            else if(!formData.getVendorname().equals(""))
//                source_reference = formData.getVendorname();
//            else if(!formData.getJobporname().equals(""))
//                source_reference = formData.getJobporname();
//            formData.setEmpref(source_reference);
//            
//            dbCTRL.updateNewEmployeeMailDetail(formData.getJfId(), formData, password,empId,CommonConfigurations.JF_INITIATED);
//            dbCTRL.addNewCandidate(formData.getPersonalEmailId1(), password, refId, groupId);
//        } catch (Exception e) {
//            e.getLocalizedMessage();
//        }
//    }
//   
//    public void updateNewEmployeeDetail(String refId, JoinerFormDTO formData) {
//        try {
//            formData.setDateOfBirth(CommonFunctions.changeDateFormatToDB(formData.getDateOfBirth()));
//
//            String source_reference = "";
//
//            if(!formData.getEmpref().equals("") && !formData.getEmpref().equalsIgnoreCase("Enter Name Here"))
//                source_reference = formData.getEmpref();
//            else if(!formData.getVendorname().equals(""))
//                source_reference = formData.getVendorname();
//            else if(!formData.getJobporname().equals(""))
//                source_reference = formData.getJobporname();
//            formData.setEmpref(source_reference);
//
//            if(formData.getContract_type().equals("n")) {
//                formData.setEmpContract("");
//            }
//            
//            dbCTRL.updateNewEmployeeDetail(formData.getJfId(), formData);
//            dbCTRL.addOrUpdatePassportNumber(formData.getPassport_id(),formData.getJfId(),formData.getPassport_number());
//        } catch (Exception e) {
//            e.getLocalizedMessage();
//        }
//    }
    //new
    public ModelAndView checkWorkEmailAddress(HttpServletRequest requestObj, HttpServletResponse response) throws Exception {
        //HttpServletRequest requestObj = getRequest();
        String workEmailId = requestObj.getParameter("workEmailId");
        String employeeCategory = requestObj.getParameter("employeeCategory");
        String contractemployeeId = requestObj.getParameter("contractemployeeId");
        String checkWorkEmail = ((JoinerFormImpl) this.getBean("joinerDao")).checkWorkEmail(workEmailId, employeeCategory, contractemployeeId);
        requestObj.setAttribute("getDataFor", "workEmailId");
        requestObj.setAttribute("checkWorkEmail", checkWorkEmail);
        return new ModelAndView("ajax");
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    public ModelAndView printAllPages(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {
        System.out.println("inside printAllPages()++++++++++++++++++");
        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        System.out.println("empId = " + empId);
        formData.setEmpId1(empId);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        int currentYear = cal.get(Calendar.YEAR);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        System.out.println("joinerReportData++++++++++++" + joinerReportData);
        String dob = joinerReportData.getDateOfBirth();
        System.out.println("dob++++++++++++" + dob);


        if (dob != null) {
            String dateOfBirth[] = dob.split("-");
            int age = currentYear - Integer.parseInt(dateOfBirth[2]);
            requestObj.setAttribute("day", dateOfBirth[0]);
            requestObj.setAttribute("month", dateOfBirth[1]);
            requestObj.setAttribute("year", dateOfBirth[2]);
            requestObj.setAttribute("age", age);
        }


        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();

        List<JoinerFormDTO> previousEmpData = ((JoinerFormImpl) this.getBean("joinerDao")).getJfPrevEmpData(empId);
        List<JoinerFormDTO> techSkillsData = ((JoinerFormImpl) this.getBean("joinerDao")).getSkillData(Integer.parseInt(empId));
        List<JoinerFormDTO> familyMemberData = ((JoinerFormImpl) this.getBean("joinerDao")).getJfFamilyMemberData(empId);
        List<JoinerFormDTO> referenceData = ((JoinerFormImpl) this.getBean("joinerDao")).getJfReferrerDTData(empId);
        List<JoinerFormDTO> prevCompData = ((JoinerFormImpl) this.getBean("joinerDao")).getJfReferrerPrevCompData(empId);
        List<JoinerFormDTO> mediInsuranceData = ((JoinerFormImpl) this.getBean("joinerDao")).getMediInsuranceData(empId);
        List<JoinerFormDTO> educationData = ((JoinerFormImpl) this.getBean("joinerDao")).getEducationData(Integer.parseInt(empId));



        // START - New Code
        String authorizer = "";

        if (joinerReportData.getJfStatus() == null ? "5" == null : joinerReportData.getJfStatus().equals("5")) {
            authorizer = joinerReportData.getDataVerifiedById();
        }
        if (joinerReportData.getJfStatus() == null ? "6" == null : joinerReportData.getJfStatus().equals("6")) {
            authorizer = joinerReportData.getDataApprovedById();
        }

        if (authorizer != null) {//
            JoinerFormDTO authorizerData = ((JoinerFormImpl) this.getBean("joinerDao")).getAuthorizerDetails(authorizer);
            requestObj.setAttribute("authorizerData", authorizerData);
        }
        // END - New Code

        Iterator it = educationData.iterator();
        while (it.hasNext()) {
            JoinerFormDTO tt = (JoinerFormDTO) it.next();
            if (isIntNumber(tt.getInstitutionX())) {
                tt.setInstitutionX(tt.getInstituteName());
            } else {
                tt.setInstitutionX(tt.getInstitutionX());
            }
            if (isIntNumber(tt.getUniversityX())) {
                tt.setUniversityX(tt.getUniversityName());
            } else {
                tt.setUniversityX(tt.getUniversityX());
            }
            if (isIntNumber(tt.getQualificationX())) {
                tt.setQualificationX(tt.getQualificationName());
            } else {
                tt.setQualificationX(tt.getQualificationX());
            }
        }
        List<JoinerFormDTO> passportData = ((JoinerFormImpl) this.getBean("joinerDao")).getPassportData(empId);
        List<JoinerFormDTO> emergencyData = ((JoinerFormImpl) this.getBean("joinerDao")).getEmergencyContacts(empId);
        List<JoinerFormDTO> nomineeData = ((JoinerFormImpl) this.getBean("joinerDao")).getnomineeData(empId);
        this.copy_photo(employeeData.getEmpFileName());
        this.copy_photo(employeeData.getEmpSignatureFileName());
        requestObj.setAttribute("nomineeData", nomineeData);
        requestObj.setAttribute("emergencyData", emergencyData);
        requestObj.setAttribute("passportData", passportData);
        requestObj.setAttribute("educationData", educationData);
        requestObj.setAttribute("mediInsuranceData", mediInsuranceData);
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("bankName", CommonConfigurations.bankNames);
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("previousEmpData", previousEmpData);
        requestObj.setAttribute("techSkillsData", techSkillsData);
        requestObj.setAttribute("familyMemberData", familyMemberData);
        requestObj.setAttribute("referenceData", referenceData);
        requestObj.setAttribute("prevCompData", prevCompData);
        List<NationalityDTO> maritalStatus = ((JoinerFormImpl) this.getBean("joinerDao")).getMaritalStatuslist();
        requestObj.setAttribute("maritalStatus", maritalStatus);
        requestObj.setAttribute("firstName", joinerReportData.getFirstName().toUpperCase());
        requestObj.setAttribute("middleName", joinerReportData.getMiddleName().toUpperCase());
        requestObj.setAttribute("lastName", joinerReportData.getLastName().toUpperCase());
        requestObj.setAttribute("relationShips", CommonConfigurations.relationShips);
//        List<NationalityDTO> nationalityList = ((JoinerFormImpl) this.getBean("joinerDao")).getNationalitylist();
//        requestObj.setAttribute("nationalityList", nationalityList);
        requestObj.setAttribute("empId", empId);
        return new ModelAndView("allInOnePage");
    }

    public ModelAndView revertRRF(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        ModelAndView fwd = null;
        System.out.println("in side revertRRF()++++++++++++++++++");

        HttpSession session = request.getSession();
        System.out.println("Parameter==>" + request.getParameter("jfId"));
        String jf_id = request.getParameter("jfId");
        ((JoinerFormImpl) this.getBean("joinerDao")).revertRRF(jf_id);
        String successMessage = "RRF reverted successfully";
        session.setAttribute("successMsg", successMessage);
        fwd = new ModelAndView("redirect:begin.htm");
        return fwd;
    }

    public ModelAndView joinerReportPrint(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {
        System.out.println("inside joinerReportPrint()++++++++++++++++++");
        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        requestObj.setAttribute("joinerReportData", joinerReportData);

        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        // START - New Code
        String authorizer = "";

        if (joinerReportData.getJfStatus() == null ? "5" == null : joinerReportData.getJfStatus().equals("5")) {
            authorizer = joinerReportData.getDataVerifiedById();
        }
        if (joinerReportData.getJfStatus() == null ? "6" == null : joinerReportData.getJfStatus().equals("6")) {
            authorizer = joinerReportData.getDataApprovedById();
        }

        if (authorizer != null) {
            JoinerFormDTO authorizerData = ((JoinerFormImpl) this.getBean("joinerDao")).getAuthorizerDetails(authorizer);
            requestObj.setAttribute("authorizerData", authorizerData);
        }
        // END - New Code


        return new ModelAndView("joiningReport");
    }

    public ModelAndView empDetailsPageOne(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside empDetailsPageOne()++++++++++++++++++");
        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        String dob = joinerReportData.getDateOfBirth();
        if (dob != null) {
            String dateOfBirth[] = dob.split("-");
            requestObj.setAttribute("day", dateOfBirth[0]);
            requestObj.setAttribute("month", dateOfBirth[1]);
            requestObj.setAttribute("year", dateOfBirth[2]);
        }
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("bankName", CommonConfigurations.bankNames);
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        List<NationalityDTO> maritalStatus = ((JoinerFormImpl) this.getBean("joinerDao")).getMaritalStatuslist();
        requestObj.setAttribute("maritalStatus", maritalStatus);
//        List<NationalityDTO> nationalityList = ((JoinerFormImpl) this.getBean("joinerDao")).getNationalitylist();
//        requestObj.setAttribute("nationalityList", nationalityList);
        return new ModelAndView("empDetailsPageOne");
    }

    public ModelAndView empDetailsPageTwo(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside empDetailsPageTwo()++++++++++++++++++");


        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        List<JoinerFormDTO> previousEmpData = ((JoinerFormImpl) this.getBean("joinerDao")).getJfPrevEmpData(empId);
        List<JoinerFormDTO> techSkillsData = ((JoinerFormImpl) this.getBean("joinerDao")).getSkillData(Integer.parseInt(empId));
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();

        List<JoinerFormDTO> educationData = ((JoinerFormImpl) this.getBean("joinerDao")).getEducationData(Integer.parseInt(empId));
        System.out.println("educationData+++++++++++++++++" + educationData);
        Iterator it = educationData.iterator();
        while (it.hasNext()) {
            JoinerFormDTO tt = (JoinerFormDTO) it.next();
            if (isIntNumber(tt.getInstitutionX())) {
                tt.setInstitutionX(tt.getInstituteName());
            } else {
                tt.setInstitutionX(tt.getInstitutionX());
            }
            if (isIntNumber(tt.getUniversityX())) {
                tt.setUniversityX(tt.getUniversityName());
            } else {
                tt.setUniversityX(tt.getUniversityX());
            }
            if (isIntNumber(tt.getQualificationX())) {
                tt.setQualificationX(tt.getQualificationName());
            } else {
                tt.setQualificationX(tt.getQualificationX());
            }
        }
        requestObj.setAttribute("educationData", educationData);
        requestObj.setAttribute("previousEmpData", previousEmpData);
        requestObj.setAttribute("techSkillsData", techSkillsData);
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        return new ModelAndView("empDetailsPageTwo");
    }

    public ModelAndView empDetailsPageThree(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside empDetailsPageThree()++++++++++++++++++");
        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        String cancelPrint = requestObj.getParameter("cancelPrint");
        List<JoinerFormDTO> familyMemberData = ((JoinerFormImpl) this.getBean("joinerDao")).getJfFamilyMemberData(empId);
        requestObj.setAttribute("familyMemberData", familyMemberData);
        requestObj.setAttribute("relationShips", CommonConfigurations.relationShips);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        return new ModelAndView("empDetailsPageThree");
    }

    public ModelAndView empDetailsPageFour(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside empDetailsPageFour()++++++++++++++++++");
        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        List<JoinerFormDTO> referenceData = ((JoinerFormImpl) this.getBean("joinerDao")).getJfReferrerDTData(empId);
        List<JoinerFormDTO> prevCompData = ((JoinerFormImpl) this.getBean("joinerDao")).getJfReferrerPrevCompData(empId);
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        List<JoinerFormDTO> passportData = ((JoinerFormImpl) this.getBean("joinerDao")).getPassportData(empId);
        List<JoinerFormDTO> emergencyData = ((JoinerFormImpl) this.getBean("joinerDao")).getEmergencyContacts(empId);
        requestObj.setAttribute("passportData", passportData);
        requestObj.setAttribute("emergencyData", emergencyData);
        requestObj.setAttribute("referenceData", referenceData);
        requestObj.setAttribute("prevCompData", prevCompData);
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        // START - New Code
        String authorizer = "";

        if (joinerReportData.getJfStatus() == null ? "5" == null : joinerReportData.getJfStatus().equals("5")) {
            authorizer = joinerReportData.getDataVerifiedById();
        }
        if (joinerReportData.getJfStatus() == null ? "6" == null : joinerReportData.getJfStatus().equals("6")) {
            authorizer = joinerReportData.getDataApprovedById();
        }

        if (authorizer != null) {
            JoinerFormDTO authorizerData = ((JoinerFormImpl) this.getBean("joinerDao")).getAuthorizerDetails(authorizer);
            requestObj.setAttribute("authorizerData", authorizerData);
        }
        return new ModelAndView("empDetailsPageFour");
    }

    public ModelAndView groupMedical(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside groupMedical()++++++++++++++++++");
        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        List<JoinerFormDTO> mediInsuranceData = ((JoinerFormImpl) this.getBean("joinerDao")).getMediInsuranceData(empId);
        List<JoinerFormDTO> nomineeData = ((JoinerFormImpl) this.getBean("joinerDao")).getnomineeData(empId);
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("mediInsuranceData", mediInsuranceData);
        requestObj.setAttribute("nomineeData", nomineeData);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        requestObj.setAttribute("relationShips", CommonConfigurations.relationShips);
        // START - New Code
        String authorizer = "";

        if (joinerReportData.getJfStatus() == null ? "5" == null : joinerReportData.getJfStatus().equals("5")) {
            authorizer = joinerReportData.getDataVerifiedById();
        }
        if (joinerReportData.getJfStatus() == null ? "6" == null : joinerReportData.getJfStatus().equals("6")) {
            authorizer = joinerReportData.getDataApprovedById();
        }

        if (authorizer != null) {
            JoinerFormDTO authorizerData = ((JoinerFormImpl) this.getBean("joinerDao")).getAuthorizerDetails(authorizer);
            requestObj.setAttribute("authorizerData", authorizerData);
        }
        return new ModelAndView("groupMedical");
    }

    public ModelAndView lifeInsurance(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside lifeInsurance()++++++++++++++++++");
        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        int currentYear = cal.get(Calendar.YEAR);
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> nomineeData = ((JoinerFormImpl) this.getBean("joinerDao")).getnomineeData(empId);
        String dob = joinerReportData.dateOfBirth;
        if (dob != null) {
            String[] dobSeparator = dob.split("-");
            int age = currentYear - Integer.parseInt(dobSeparator[2]);
            requestObj.setAttribute("age", age);
        }
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("nomineeData", nomineeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        requestObj.setAttribute("relationShips", CommonConfigurations.relationShips);
        return new ModelAndView("lifeInsurance");
    }

    public ModelAndView paymentOfGratuityOne(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside paymentOfGratuityOne()++++++++++++++++++");
        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        List<JoinerFormDTO> nomineeData = ((JoinerFormImpl) this.getBean("joinerDao")).getnomineeData(empId);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        requestObj.setAttribute("nomineeData", nomineeData);
        List<NationalityDTO> maritalStatus = ((JoinerFormImpl) this.getBean("joinerDao")).getMaritalStatuslist();
        requestObj.setAttribute("maritalStatus", maritalStatus);
        requestObj.setAttribute("relationShips", CommonConfigurations.relationShips);
        return new ModelAndView("paymentOfGratuityOne");
    }

    public ModelAndView paymentOfGratuityTwo(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside paymentOfGratuityTwo()++++++++++++++++++");
        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        return new ModelAndView("paymentOfGratuityTwo");
    }

    public ModelAndView payrollDetails(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside payrollDetails()++++++++++++++++++");
        ModelAndView mv;
        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        List<JoinerFormDTO> emergencyData = ((JoinerFormImpl) this.getBean("joinerDao")).getEmergencyContacts(empId);
        requestObj.setAttribute("emergencyData", emergencyData);
        String dob = joinerReportData.getDateOfBirth();
        if (dob != null) {
            String dateOfBirth[] = dob.split("-");
            requestObj.setAttribute("day", dateOfBirth[0]);
            requestObj.setAttribute("month", dateOfBirth[1]);
            requestObj.setAttribute("year", dateOfBirth[2]);
        }
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        requestObj.setAttribute("bankName", CommonConfigurations.bankNames);
//        List<NationalityDTO> nationalityList = ((JoinerFormImpl) this.getBean("joinerDao")).getNationalitylist();
//        requestObj.setAttribute("nationalityList", nationalityList);
        return new ModelAndView("payrollDetails");
    }

    public ModelAndView empIdCard(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside empIdCard()++++++++++++++++++");
        ModelAndView mv;
        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        String dob = joinerReportData.getDateOfBirth();
        if (dob != null) {
            String dateOfBirth[] = dob.split("-");
            requestObj.setAttribute("day", dateOfBirth[0]);
            requestObj.setAttribute("month", dateOfBirth[1]);
            requestObj.setAttribute("year", dateOfBirth[2]);
        }
        List<JoinerFormDTO> emergencyData = ((JoinerFormImpl) this.getBean("joinerDao")).getEmergencyContacts(empId);
        requestObj.setAttribute("emergencyData", emergencyData);
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("firstName", joinerReportData.getFirstName().toUpperCase());
        requestObj.setAttribute("middleName", joinerReportData.getMiddleName().toUpperCase());
        requestObj.setAttribute("lastName", joinerReportData.getLastName().toUpperCase());
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("bankName", CommonConfigurations.bankNames);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        return new ModelAndView("empIdCard");
    }

    public ModelAndView requestForEmail(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside requestForEmail()++++++++++++++++++");
        ModelAndView mv;
        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        String dob = joinerReportData.getDateOfBirth();
        if (dob != null) {
            String dateOfBirth[] = dob.split("-");
            requestObj.setAttribute("day", dateOfBirth[0]);
            requestObj.setAttribute("month", dateOfBirth[1]);
            requestObj.setAttribute("year", dateOfBirth[2]);
        }
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("firstName", joinerReportData.getFirstName().toUpperCase());
        requestObj.setAttribute("middleName", joinerReportData.getMiddleName().toUpperCase());
        requestObj.setAttribute("lastName", joinerReportData.getLastName().toUpperCase());
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("bankName", CommonConfigurations.bankNames);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        return new ModelAndView("requestForEmail");
    }

    public ModelAndView defianceWorkDetails(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {


        System.out.println("inside defianceWorkDetails()++++++++++++++++++");
        ModelAndView mv;
        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        String dob = joinerReportData.getDateOfBirth();
        if (dob != null) {
            String dateOfBirth[] = dob.split("-");
            requestObj.setAttribute("day", dateOfBirth[0]);
            requestObj.setAttribute("month", dateOfBirth[1]);
            requestObj.setAttribute("year", dateOfBirth[2]);
        }
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("firstName", joinerReportData.getFirstName().toUpperCase());
        requestObj.setAttribute("middleName", joinerReportData.getMiddleName().toUpperCase());
        requestObj.setAttribute("lastName", joinerReportData.getLastName().toUpperCase());
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("bankName", CommonConfigurations.bankNames);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        return new ModelAndView("defianceWorkDetails");
    }

    public ModelAndView personalUndertaking(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside personalUndertaking()++++++++++++++++++");
        ModelAndView mv;

        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        int currentYear = cal.get(Calendar.YEAR);
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        String dob = joinerReportData.getDateOfBirth();
        if (dob != null) {
            String dateOfBirth[] = dob.split("-");
            int age = currentYear - Integer.parseInt(dateOfBirth[2]);
            requestObj.setAttribute("day", dateOfBirth[0]);
            requestObj.setAttribute("month", dateOfBirth[1]);
            requestObj.setAttribute("year", dateOfBirth[2]);
            requestObj.setAttribute("age", age);
        }
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("firstName", joinerReportData.getFirstName().toUpperCase());
        requestObj.setAttribute("middleName", joinerReportData.getMiddleName().toUpperCase());
        requestObj.setAttribute("lastName", joinerReportData.getLastName().toUpperCase());
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("bankName", CommonConfigurations.bankNames);
        requestObj.setAttribute("cancelPrint", cancelPrint);
        return new ModelAndView("personalUndertaking");
    }

    public ModelAndView pfNominationForm(HttpServletRequest requestObj, HttpServletResponse response, JoinerFormDTO formData) throws Exception {

        System.out.println("inside pfNominationForm()++++++++++++++++++");
        ModelAndView mv;
        String empId = requestObj.getParameter("empId");
        formData.setEmpId1(empId);
        String cancelPrint = requestObj.getParameter("cancelPrint");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(CommonConfigurations.DATE_FORMAT_NOW);
        String currentDate = date.format(cal.getTime());
        JoinerFormDTO joinerReportData = ((JoinerFormImpl) this.getBean("joinerDao")).getJoinerReportData(formData);
        List<JoinerFormDTO> employeeData1 = ((JoinerFormImpl) this.getBean("joinerDao")).getJfEmpData(formData);
        JoinerFormDTO employeeData = employeeData1.iterator().next();
        String dob = joinerReportData.getDateOfBirth();
        if (dob != null) {
            String dateOfBirth[] = dob.split("-");
            requestObj.setAttribute("day", dateOfBirth[0]);
            requestObj.setAttribute("month", dateOfBirth[1]);
            requestObj.setAttribute("year", dateOfBirth[2]);
        }
        requestObj.setAttribute("joinerReportData", joinerReportData);
        requestObj.setAttribute("firstName", joinerReportData.getFirstName().toUpperCase());
        requestObj.setAttribute("middleName", joinerReportData.getMiddleName().toUpperCase());
        requestObj.setAttribute("lastName", joinerReportData.getLastName().toUpperCase());
        requestObj.setAttribute("employeeData", employeeData);
        requestObj.setAttribute("currentDate", currentDate);
        requestObj.setAttribute("bankName", CommonConfigurations.bankNames);
        requestObj.setAttribute("cancelPrint", cancelPrint);


        return new ModelAndView("pfNominationForm");
    }

    public ModelAndView ojfStart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String successMessage = null;
        if (request.getParameter("successMsg") == null || request.getParameter("successMsg").equals("")) {
            successMessage = (String) session.getAttribute("successMsg");
        } else {
            successMessage = (String) request.getParameter("successMsg");
        }
        request.setAttribute("successMsg", successMessage);
        mv = new ModelAndView("redirect:begin.htm");
        return mv;
    }

    public ModelAndView SyncStatusCheck(HttpServletRequest request, HttpServletResponse response, JoinerFormDTO formData) throws Exception {
        ModelAndView mv = null;
        String flag = "2";
        JoinerFormDTO syncEmpDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getSyncEmpDetails("N");
        if (syncEmpDetails != null) {
            //response.sendRedirect("http://localhost/iDeal/employees/employeesList/"+syncEmpDetails.getEmployeeId());
            response.sendRedirect("begin.htm?successMsg=employee " + syncEmpDetails.getEmployeeNumber() + " is not yet synced in SAPBYD&errorEmployee=" + flag);
        } else {
            finalSubmit(request, response, formData);
        }
        return mv;
    }

    public ModelAndView editApplicantInOjf(HttpServletRequest request, HttpServletResponse response) throws Exception {

        JoinerFormDTO candidateDetails = new JoinerFormDTO();
        String buttonStatus = request.getParameter("buttonStatus");
        String canStatus = request.getParameter("canStatus");
        //String rrfId = request.getParameter("rrfId");
        String appId = request.getParameter("appId");
        //String split[] = rrfId.split("/");
        //String split1 = split[2].split("-")[0];
        //String split2 = split[2].split("-")[1];

        System.out.println("*************************** APP ID " + appId + " canStatus " + canStatus);
        Properties prop = new Properties();
        prop.load(new FileInputStream(CommonConfigurations.propertyPath));
        List<MasterDataDTO> levelOneStructure = ((JoinerFormImpl) this.getBean("joinerDao")).getStructureDetails(0);
        List<MasterDataDTO> bandList = ((JoinerFormImpl) this.getBean("joinerDao")).getBandDetails("0");
        System.out.println("bandList-----------------------------------" + bandList);
        List<MasterDataDTO> designationList = ((JoinerFormImpl) this.getBean("joinerDao")).getDesignationDetails();
        List<MasterDataDTO> vendorList = ((MasterDaoImpl) this.getBean("MasterDao")).getvendorDetaillist();
        List<MasterDataDTO> reportingManagerObj = null;
        List<MasterDataDTO> emprefObj = null;
        List<MasterDataDTO> mailCcListObj = null;
        LinkedHashMap mapObj = CommonFunctions.getEmployeeType();
        ApplicantDTO reqDto = new ApplicantDTO();
        ///reqDto.setReqId(split1);
        //reqDto.setChildReqId(split2);
        request.setAttribute("levelOneStructure", levelOneStructure);
        candidateDetails = ((JoinerFormImpl) this.getBean("joinerDao")).getSigleApplicant(appId);
        //ApplicantDTO rrfDto = ((JoinerFormImpl)this.getBean("joinerDao")).getRrfDetails(reqDto);
        JoinerFormDTO passportArr = new JoinerFormDTO();
        String rrfName = null;
        String reportingManagerName = candidateDetails.getRmName();
        candidateDetails.setJfId(null);
//          passportArr.setPassportNumberX(candidateDetails.getPassportNumberX());               


        /*Load SOURCE OF HIRE*/
        String sourceid = ((JoinerFormImpl) this.getBean("joinerDao")).getsourcehirelisstbyname("source_of_hire");
        List<SourcehireDTO> sourceofhirelist = ((JoinerFormImpl) this.getBean("joinerDao")).getsourcehirelisst(sourceid);
        request.setAttribute("sourceofhirelist", sourceofhirelist);
        /*Ends*/

        /*Load Job portal*/
        String portalid = ((JoinerFormImpl) this.getBean("joinerDao")).getportalidbyname("job_portal");
        List<SourcehireDTO> portallistlist = ((JoinerFormImpl) this.getBean("joinerDao")).getjobportallist(portalid);
        request.setAttribute("portallistlist", portallistlist);
        /*Ends*/
        List<MasterDataDTO> subBandList = ((JoinerFormImpl) this.getBean("joinerDao")).getBandDetails(candidateDetails.getBandId());
        List<MasterDataDTO> practiceGroupList = ((JoinerFormImpl) this.getBean("joinerDao")).getPracticeGroup(candidateDetails.getStructureId());
        List<MasterDataDTO> subPracticeGroupList = ((JoinerFormImpl) this.getBean("joinerDao")).getSubPracticeGroup(candidateDetails.getPracticeGroupIdd());

        request.setAttribute("passportArr", passportArr);
        request.setAttribute("subBandList", subBandList);
        request.setAttribute("practiceGroupList", practiceGroupList);
        request.setAttribute("subPracticeGroupList", subPracticeGroupList);
        request.setAttribute("candidateDetails", candidateDetails);
        request.setAttribute("bandList", bandList);
        request.setAttribute("designationList", designationList);
        request.setAttribute("employeeTypeList", mapObj);
        request.setAttribute("vendorList", vendorList);
        request.setAttribute("reportingManagerObj", reportingManagerObj);
        request.setAttribute("emprefObj", emprefObj);
        request.setAttribute("mailCcList", mailCcListObj);
        request.setAttribute("submitButton", prop.getProperty("submitButton"));
        request.setAttribute("saveButton", prop.getProperty("saveButton"));
        request.setAttribute("triggerMail", prop.getProperty("triggerMail"));
        request.setAttribute("cancelButton", prop.getProperty("cancelButton"));
        request.setAttribute("rrfName", rrfName);
        request.setAttribute("reportingManagerName", reportingManagerName);
        if (buttonStatus != null) {
            request.setAttribute("buttonStatus", buttonStatus);
            request.setAttribute("canStatus", canStatus);
        } else {
            request.setAttribute("buttonStatus", "applicationpage");
        }

        ModelAndView mv = new ModelAndView("newemployeeadd");
        return mv;

    }

//   public ModelAndView editApplicantInOjf1(HttpServletRequest request, HttpServletResponse response) throws Exception{
//       
//       JoinerFormDTO candidateDetails = new JoinerFormDTO();
//        String buttonStatus = request.getParameter("buttonStatus");
//        String canStatus = request.getParameter("canStatus");
//         String rrfId = request.getParameter("rrfId");
//        String appId = request.getParameter("appId");       
//                String split[] = rrfId.split("/");
//                String split1 = split[2].split("-")[0];
//                String split2 = split[2].split("-")[1];
//        
//        System.out.println("*************************** rrf id: "+rrfId +" APP ID "+appId+ " canStatus "+canStatus);
//        Properties prop = new Properties();
//        prop.load(new FileInputStream(CommonConfigurations.propertyPath));
//       List<MasterDataDTO> levelOneStructure = ((JoinerFormImpl)this.getBean("joinerDao")).getStructureDetails(0);
//       List<MasterDataDTO> bandList = ((JoinerFormImpl)this.getBean("joinerDao")).getBandDetails("0");
//       System.out.println("bandList-----------------------------------"+bandList);
//       List<MasterDataDTO> designationList = ((JoinerFormImpl)this.getBean("joinerDao")).getDesignationDetails();
//       List<MasterDataDTO> vendorList = ((MasterDaoImpl)this.getBean("MasterDao")).getvendorDetaillist();
//        List<MasterDataDTO> reportingManagerObj = null;
//        List<MasterDataDTO> emprefObj = null;
//        List<MasterDataDTO> mailCcListObj = null;        
//        LinkedHashMap mapObj = CommonFunctions.getEmployeeType();
//        ApplicantDTO reqDto = new ApplicantDTO();
//        reqDto.setReqId(split1);
//         reqDto.setChildReqId(split2);
//        request.setAttribute("levelOneStructure", levelOneStructure);
//        ApplicantDTO appDto = ((JoinerFormImpl)this.getBean("joinerDao")).getSigleApplicant(appId);
//         ApplicantDTO rrfDto = ((JoinerFormImpl)this.getBean("joinerDao")).getRrfDetails(reqDto);
//         JoinerFormDTO passportArr = new JoinerFormDTO();
//          String rrfName =null;
//          String reportingManagerName = null;
//         if(appDto != null && rrfDto != null)
//         {
//             candidateDetails.setJfId(null);
//            candidateDetails.setFirstName(appDto.getFirstName());
//            candidateDetails.setMiddleName(appDto.getMiddleName());
//            candidateDetails.setLastName(appDto.getLastName());
//            candidateDetails.setPanNo(appDto.getPanNo());
//            candidateDetails.setPersonalEmailId1(appDto.getPersonalEmail());
//            candidateDetails.setPersonalEmailId2(appDto.getAlternateEmail());
//            candidateDetails.setSourceofhire(appDto.getSourceOfHire().substring(0, 1).toLowerCase());
//            candidateDetails.setDesigName(appDto.getDesignation());
//            candidateDetails.setPassport_id(appDto.getPassport());
//            candidateDetails.setDateOfBirth(appDto.getDateofBirth());
//            candidateDetails.setBand(rrfDto.getBand());
//            candidateDetails.setReportingManager(rrfDto.getReportingManagerId());
//            reportingManagerName = rrfDto.getRmFname()+" "+rrfDto.getRmMname()+" "+rrfDto.getRmLname();
//            candidateDetails.setBandId(rrfDto.getBandId());
//            candidateDetails.setStructureId(rrfDto.getStructureId());
//            candidateDetails.setStructureName(rrfDto.getCompanyStructure());
//            candidateDetails.setPracticeGroup(rrfDto.getPracticeGroup());
//            candidateDetails.setPracticeGroupId(rrfDto.getPracticeGroupId());
//            candidateDetails.setSubPracticeGroup(rrfDto.getSubPracticeGroup());
//            candidateDetails.setSubPracticeGroupId(rrfDto.getSubPracticeGroupId());
//            candidateDetails.setSubBand(rrfDto.getSubBand());
//             candidateDetails.setSubBandId(rrfDto.getSubBandId());            
//            candidateDetails.setRrfRes(appDto.getRrfId());
//            candidateDetails.setRrf_id(rrfDto.getRrf_id());
//            candidateDetails.setEmployeeType(rrfDto.getEmployeType());
//            candidateDetails.setAppId(appDto.getId()+"");
//            passportArr.setPassportNumberX(appDto.getPassport());
//            candidateDetails.setAppStatus(appDto.getAppStatus());
//            rrfName =rrfDto.getRrfName();
//            //request.setAttribute("statusCheck", appDto.getAppStatus());
//            
//         }
//        /*Load SOURCE OF HIRE*/
//        String sourceid=((JoinerFormImpl)this.getBean("joinerDao")).getsourcehirelisstbyname("source_of_hire");
//        List<SourcehireDTO> sourceofhirelist = ((JoinerFormImpl)this.getBean("joinerDao")).getsourcehirelisst(sourceid);
//        request.setAttribute("sourceofhirelist", sourceofhirelist);
//        /*Ends*/
//        
//         /*Load Job portal*/
//        String portalid=((JoinerFormImpl)this.getBean("joinerDao")).getportalidbyname("job_portal");
//        List<SourcehireDTO> portallistlist = ((JoinerFormImpl)this.getBean("joinerDao")).getjobportallist(portalid);
//        request.setAttribute("portallistlist", portallistlist);
//        /*Ends*/
//        List<MasterDataDTO> subBandList = ((JoinerFormImpl)this.getBean("joinerDao")).getBandDetails(candidateDetails.getBandId());
//	List<MasterDataDTO> practiceGroupList = ((JoinerFormImpl)this.getBean("joinerDao")).getPracticeGroup(candidateDetails.getStructureId());
//        List<MasterDataDTO> subPracticeGroupList = ((JoinerFormImpl)this.getBean("joinerDao")).getSubPracticeGroup(candidateDetails.getPracticeGroupId());
//        
//	request.setAttribute("passportArr", passportArr);
//	request.setAttribute("subBandList", subBandList);
//	request.setAttribute("practiceGroupList", practiceGroupList);
//        request.setAttribute("subPracticeGroupList", subPracticeGroupList);
//        request.setAttribute("candidateDetails", candidateDetails);
//        request.setAttribute("bandList", bandList);
//        request.setAttribute("designationList", designationList);
//        request.setAttribute("employeeTypeList", mapObj);
//        request.setAttribute("vendorList", vendorList);
//        request.setAttribute("reportingManagerObj", reportingManagerObj);
//         request.setAttribute("emprefObj", emprefObj);
//        request.setAttribute("mailCcList", mailCcListObj);
//        request.setAttribute("submitButton", prop.getProperty("submitButton"));
//        request.setAttribute("saveButton", prop.getProperty("saveButton"));
//        request.setAttribute("triggerMail", prop.getProperty("triggerMail"));
//        request.setAttribute("cancelButton", prop.getProperty("cancelButton"));
//        request.setAttribute("rrfName", rrfName);
//        request.setAttribute("reportingManagerName", reportingManagerName);
//        if (buttonStatus != null) {
//            request.setAttribute("buttonStatus", buttonStatus);
//            request.setAttribute("canStatus", canStatus);
//        }
//       
//      ModelAndView mv = new ModelAndView("newemployeeadd");      
//        return mv;
//      
//  }
//   public ModelAndView applicantList(HttpServletRequest request, HttpServletResponse response) throws Exception{
//        ModelAndView mv = new ModelAndView("applicantList");
//         List<ApplicantDTO> applicantDto = ((JoinerFormImpl)this.getBean("joinerDao")).getApplicantData();
//         mv.addObject("applicantList",applicantDto);
//       return mv;
//   }
    ////////////////////////////////////////////////////////////////////////////////
    public ModelAndView applicantsList(HttpServletRequest requestObj, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        //    HttpServletRequest requestObj = this.getRequest();
        List<JoinerFormDTO> applicantsList = ((JoinerFormImpl) this.getBean("joinerDao")).getApplicantsList();
        System.out.println("applicant list ++++++++++++++++" + applicantsList);
        requestObj.setAttribute("applicantsList", applicantsList);
        return new ModelAndView("applicantList");
    }

    public boolean isIntNumber(String num) {
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public Object getBean(String beanName) {
        Object o = null;
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            return ctx.getBean(beanName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
    /**
     * @return the loginDao
     */
    public static void copy_photo(String file_name) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream("D:\\OJFProofUpload\\" + file_name );
            os = new FileOutputStream("C:\\wamp\\www\\app\\webroot\\uploads\\ojf_files\\" + file_name);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (Exception ex) {
            System.out.println("Unable to copy file:" + ex.getMessage());
        } finally {
            try {
                is.close();
                os.close();
            } catch (Exception ex) {
            }
        }
    }
}