/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.service;

import com.defiance.ideal.dto.EmployeeDto;
import com.defiance.ideal.dto.DetailsDto;
import com.defiance.ideal.dto.kraDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 14053
 */
public interface EmployeeService {
    public List getKraList(String employeeId, String kraId);
    public List getEmployeeKraDetails(String employeeId, String year, int quaterId);
    public List getIndividualKraDetails(String kraId);
    public String getMaxBandId();
    public String getAaExsting(EmployeeDto formData);
    public kraDto getAppraiseerDetails(EmployeeDto formData);
    public String insertIntoAaEligibleAssociates(kraDto appraisee_details);
    public String insertIntoAaKra(EmployeeDto kraDetails);
    public String insertIntoAaKraMaster(EmployeeDto kraDetails);
    public String insertIntoAaKraMapping(EmployeeDto kraDetails);
    public String getTotalWeightage(String kraId);
    public List getFinancialYearList(String employeeId);
    public List getQuarterList();
    public List getRmEmployeeList(EmployeeDto formData);
     public List getReportList(EmployeeDto formData);
     public List<kraDto> getStructureDetails(int parentId);
     public List<kraDto> getCmpStructData(String parentId);
     public List<kraDto> checkEmployeeKraDetails(String employeeId, String year, int quaterId);
    public List getRMFinancialYearList(String employeeId);
    public List getEmployeeKraForExcel(String employeeId, String year, String quaterId);
    public List getInstituteList();
    public List getUniversityList();
    public List getDegreeList();
    public List getCourseList();
    public List getCountryList();
    public List getVisaList();
    public List getBankList();
    public List getStreamList();
    public List getSkillList(String val);
    public List getStateList(String val,String region);
    public List getEmployeeSearchList(String val);
    public void updateCertification(EmployeeDto formData, String[] certificationId, String[] degree, String[] qualification, String[] year_of_passing, String[] institution, String[] percentage, String[] remarks,String[] university, int[] deletedTR);
    public void updateEmployeeKra(EmployeeDto formData, String[] certificationId, String[] qualification,String[] krauom,String[] kratarget,String[] percentage,  int[] deletedTR,String actionValue);
    public String getKraId(EmployeeDto formData);
    public void updateEmployeeKraByExcel(EmployeeDto formData);
    public void updateEmployeeKraDescByExcel(EmployeeDto formData);
    public String getEmpid(String empNumber);
    public String getBandid(String empNumber);
    public List<kraDto> getRMKraForExcel(kraDto filterData);
    public List<kraDto> getReportKraForExcel(kraDto filterData);
    
    public void updateRmRemarks(EmployeeDto formData, String[] certificationId, String[] qualification,String actionValue);
    
    public void updateKraAccept(String kraId);
    public List getEmployeeDetails(EmployeeDto formData,String action);
    public String getRecordCount(EmployeeDto formData,String action);
    public String getEmployeeName(String val);
    public String getQuarterName(String val);
    public int getMaxId();
    public String checkUnique(EmployeeDto formData,String value,String type,String requestId);

    public List getEmployeeResultCount(String val);

    public void updateLicence(EmployeeDto formData, String licence_number, String licence_date_issue, String licence_date_expire, String remarks);

    public void updateVoterDetails(EmployeeDto formData, String voter_id, String remarks);

    public void updateAdharDetails(EmployeeDto formData, String adhar_number, String remarks);

    public void updatePrevPfDetails(EmployeeDto formData);
    public void updateKycDetails(EmployeeDto formData);
    public void fileDownload(String fileName, String fileNameNew, String filePath, String fileType, HttpServletResponse response);

    public String getLicenseNumber(EmployeeDto formData);
    public List<EmployeeDto> getAssociatesList(EmployeeDto formData);
    public ArrayList getBands();
    public ArrayList getEmployeNumbers();
}

