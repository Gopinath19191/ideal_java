/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.service;

import com.defiance.ideal.dao.EmployeeDao;
import com.defiance.ideal.dto.DetailsDto;
import com.defiance.ideal.dto.EmployeeDto;
import com.defiance.ideal.dto.kraDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author 14053
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao dao;
    
    public EmployeeDao getDao() {
        return dao;
    }

    public void setDao(EmployeeDao dao) {
        this.dao = dao;
    }

    public List<DetailsDto> getInstituteList(){
        List dataDto = dao.getInstituteList();
        return dataDto;
    }
    
    public List<kraDto> getKraList(String employeeId, String kraId){
        List getKraDetails = dao.getKraList(employeeId,kraId);
        return getKraDetails;
    }
    
     public List<kraDto> checkEmployeeKraDetails(String employeeId, String year, int quarterId){
        return dao.checkEmployeeKraDetails(employeeId, year,quarterId );
         
    }       
    public List<kraDto> getEmployeeKraDetails(String employeeId, String year, int quarterId){
        List getKraDetails = dao.getEmployeeKraDetails(employeeId, year,quarterId );
        return getKraDetails;
    }
    
    public List<kraDto> getIndividualKraDetails(String kraId){
        List getKraDetails = dao.getIndividualKraDetails(kraId);
        return getKraDetails;
    }
    public String getMaxBandId(){
        String maxBand = dao.getMaxBandId();
        return maxBand;
    }
    public String getAaExsting(EmployeeDto formData){
        String count = dao.getAaExsting(formData);
        return count;
    }
    public kraDto getAppraiseerDetails(EmployeeDto formData){
        kraDto appraiseer_details = dao.getAppraiseerDetails(formData);
        return appraiseer_details;
    }
    public String insertIntoAaEligibleAssociates(kraDto appraisee_details){
        String last_id = dao.insertIntoAaEligibleAssociates(appraisee_details);
        return last_id;
    }
    public String insertIntoAaKra(EmployeeDto kraDetails){
        String lastId = dao.insertIntoAaKra(kraDetails);
        return lastId;
    }
    public String insertIntoAaKraMaster(EmployeeDto kraDetails){
        String lastId = dao.insertIntoAaKraMaster(kraDetails);
        return lastId;
    }
    public String insertIntoAaKraMapping(EmployeeDto kraDetails){
        String lastId = dao.insertIntoAaKraMapping(kraDetails);
        return lastId;
    }
    public String getTotalWeightage(String kraId){
        String totalWeightage = dao.getTotalWeightage(kraId);
        return totalWeightage;
    }
    public List<kraDto> getFinancialYearList(String employeeId){
        List financialYearList = dao.getFinancialYearList(employeeId);
        return financialYearList;
    }
    public List<kraDto> getQuarterList(){
        List quarterList = dao.getQuarterList();
        return quarterList;
    }
    public List<kraDto> getRmEmployeeList(EmployeeDto formData){
        List RmEmployeeList = dao.getRmEmployeeList(formData);
        return RmEmployeeList;
    }
    public List<kraDto> getStructureDetails(int parentId){
        List RmEmployeeList = dao.getStructureDetails(parentId);
        return RmEmployeeList;
    }
    public List<kraDto> getReportList(EmployeeDto formData){
        List RmEmployeeList = dao.getReportList(formData);
        return RmEmployeeList;
    }
    public List<kraDto> getCmpStructData(String parentId) {
        List<kraDto> cmpStructList = null;
        try {
            cmpStructList = dao.getCmpStructData(parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmpStructList;
    }
    public ArrayList getBands() {
        return dao.getBands();
    }
    public List<kraDto> getRMFinancialYearList(String employeeId){
        List financialYearList = dao.getRMFinancialYearList(employeeId);
        return financialYearList;
    }
    public List<kraDto> getEmployeeKraForExcel(String employeeId, String year, String quaterId){
        List financialYearList = dao.getEmployeeKraForExcel(employeeId,year,quaterId);
        return financialYearList;
    }
    
    public List<DetailsDto> getUniversityList(){
        List dataDto = dao.getUniversityList();
        return dataDto;
    }

    public List<DetailsDto> getDegreeList(){
        List dataDto = dao.getDegreeList();
        return dataDto;
    }

    public List<DetailsDto> getCourseList(){
        List dataDto = dao.getCourseList();
        return dataDto;
    }

    public List<DetailsDto> getCountryList(){
        List dataDto = dao.getCountryList();
        return dataDto;
    }

    public List<DetailsDto> getVisaList(){
        List dataDto = dao.getVisaList();
        return dataDto;
    }

    public List getBankList() {
        List dataDto = dao.getBankList();
        return dataDto;
    }

    public List getStreamList(){
        List dataDto = dao.getStreamList();
        return dataDto;
    }

    public List getSkillList(String val){
       List dataDto = dao.getSkillList(val);
        return dataDto;
    }

    public List getStateList(String val,String region){
        List dataDto = dao.getStateList(val,region);
        return dataDto;
    }

    public List getEmployeeSearchList(String val) {
        List dataDto = dao.getEmployeeSearchList(val);
        return dataDto;
    }

    public void updateCertification(EmployeeDto formData,String[] certificationId,String[] degree,String[] qualification,String[] year_of_passing,String[] institution,String[] percentage,String[] remarks,String[] university,int[] deletedTR) {
        dao.updateCertification(formData,certificationId,degree,qualification,year_of_passing,institution,percentage,remarks,university,deletedTR);
    }
    public void updateEmployeeKra(EmployeeDto formData,String[] certificationId,String[] qualification,String[] krauom,String[] kratarget,String[] percentage,int[] deletedTR,String actionValue) {
        dao.updateEmployeeKra(formData,certificationId,qualification,krauom,kratarget,percentage,deletedTR,actionValue);
    }
    public String getEmpid(String empNumber) {
        return dao.getEmpid(empNumber);
    }
    public String getKraId(EmployeeDto formData) {
        return dao.getKraId(formData);
    }
    public String getBandid(String empNumber) {
        return dao.getBandid(empNumber);
    }
     public void updateEmployeeKraByExcel(EmployeeDto formData) {
        dao.updateEmployeeKraByExcel(formData);
    }
     public void updateEmployeeKraDescByExcel(EmployeeDto formData) {
        dao.updateEmployeeKraDescByExcel(formData);
    }
    public void updateRmRemarks(EmployeeDto formData, String[] certificationId, String[] qualification,String actionValue){
        dao.updateRmRemarks(formData, certificationId, qualification,actionValue);
    }
    public void updateKraAccept(String kraId){
        dao.updateKraAccept(kraId);
    }
    public List getEmployeeDetails(EmployeeDto formData,String action) {
        List dataDto = dao.getEmployeeDetails(formData,action);
        return dataDto;
    }

    public String getRecordCount(EmployeeDto formData,String action) {
        String dataDto = dao.getRecordCount(formData,action);
        return dataDto;
    }

    public String getEmployeeName(String val) {
        String dataDto = dao.getEmployeeName(val);
        return dataDto;
    }
     public String getQuarterName(String val) {
        String dataDto = dao.getQuarterName(val);
        return dataDto;
    }
public int getMaxId( ) {
        int dataDto = dao.getMaxId();
        return dataDto;
    }

    public String checkUnique(EmployeeDto formData,String value,String type,String requestId){
        String dataDto = dao.checkUnique(formData,value,type,requestId);
        return dataDto;
    }

    

    public List getEmployeeResultCount(String val) {
         List dataDto = dao.getEmployeeResultCount(val);
        return dataDto;
    }

    @Override
    public void updateLicence(EmployeeDto formData, String licence_number, String licence_date_issue, String licence_date_expire, String remarks) {
        dao.updateLicence(formData, licence_number, licence_date_issue, licence_date_expire, remarks);
    }

    @Override
    public void updateVoterDetails(EmployeeDto formData, String voter_id, String remarks) {
        dao.updateVoterDetails(formData, voter_id, remarks);
    }

    @Override
    public void updateAdharDetails(EmployeeDto formData, String adhar_number, String remarks) {
        dao.updateAdharDetails(formData, adhar_number, remarks);
    }
    
	@Override
    public void updatePrevPfDetails(EmployeeDto formData) {
        dao.updatePrevPfDetails(formData);
    }
     @Override
    public void updateKycDetails(EmployeeDto formData) {
        dao.updateKycDetails(formData);
    }
      @Override
public void fileDownload(String fileName, String fileNameNew, String filePath, String fileType, HttpServletResponse response){
    dao.fileDownload(fileName, fileNameNew, filePath, fileType, response);
}

    @Override
    public String getLicenseNumber(EmployeeDto formData) {
        String license_number = "";
        try{
            license_number = dao.getLicenseNumber(formData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return license_number;
    }
    
    @Override
     public List<EmployeeDto> getAssociatesList(EmployeeDto formData){
        List<EmployeeDto> associatesList = null;
        try {           
            associatesList = dao.getAssociatesList(formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return associatesList; 
         
     }
     @Override
     public List<kraDto> getRMKraForExcel(kraDto filterData){
        List<kraDto> rmKraListForExcel = dao.getRMKraForExcel(filterData);
        return rmKraListForExcel;
    }
     public List<kraDto> getReportKraForExcel(kraDto filterData){
        List<kraDto> rmKraListForExcel = dao.getReportKraForExcel(filterData);
        return rmKraListForExcel;
    }
     public ArrayList getEmployeNumbers() {
        return dao.getEmployeNumbers();
    }
}
