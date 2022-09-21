/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

/**
 *
 * @author 16113
 */
public class EmployeeBasicReportDTO {
    
    private String title;
    private String empNumber;
    private String empName;
    private String birthDate;
    private String joinDate;
    private String expYears;
    private String expMonths;
    private String address1;
    private String address2;
    private String city;
    private String sbu;
    private String subSbu;
    private String rmId;
    private String rmName;
    private String designation;
    private String status;
    private String billable;
    private String resigned;
    private String relieved;
    private String email;
    private String bandName;
    private String transferredDate;
    private String transferdCountryId;
    private String transferedCountry;
    private String transfercityId;
    private String transferCity;
    private String skillName;
    private String streamName;
    private String personalMail;
    private String finalAdhar;
//--Sub Practice Group    
    private String id;
    private String name;
    //--project Billed Hours
    private String sbuFilter;
    
    private String panNumber;
    
    private String parentSbu;
    private String parentSubSbu;
    private String parentManagerId;
    private String parentManagerName;
    private String sourceOfHire;
    private String joiningStartDate;
    private String joiningEndDate;
    private String exitStatus;

    private String gender;
    private String totalMonthExp;
    private String totalYearExp;
    private String contractExpiryDate;
    private String contractStartDate;
    private String currentLocation;
    
    private String unit_name;
    private String emp_billable;
    private String emp_dnb;
    private String emp_general;
    private String emp_sales;
    private String emp_total;
    private String subPractice;
    private String orgUnit;
    private String currentcountry;

    public String getCurrentcountry() {
        return currentcountry;
    }

    public void setCurrentcountry(String currentcountry) {
        this.currentcountry = currentcountry;
    }
    
    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }
    
    public String getSubPractice() {
        return subPractice;
    }

    public void setSubPractice(String subPractice) {
        this.subPractice = subPractice;
    }
    
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    
    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }
    

    public String getContractExpiryDate() {
        return contractExpiryDate;
    }

    public void setContractExpiryDate(String contractExpiryDate) {
        this.contractExpiryDate = contractExpiryDate;
    }
    

    public String getFinalAdhar() {
        return finalAdhar;
    }

    public void setFinalAdhar(String finalAdhar) {
        this.finalAdhar = finalAdhar;
    }

    
    
    public String getTotalMonthExp() {
        return totalMonthExp;
    }

    public void setTotalMonthExp(String totalMonthExp) {
        this.totalMonthExp = totalMonthExp;
    }

    public String getTotalYearExp() {
        return totalYearExp;
    }

    public void setTotalYearExp(String totalYearExp) {
        this.totalYearExp = totalYearExp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCurrentWrkLocation() {
        return currentWrkLocation;
    }

    public void setCurrentWrkLocation(String currentWrkLocation) {
        this.currentWrkLocation = currentWrkLocation;
    }
    private String currentWrkLocation;
    
    public String getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(String exitStatus) {
        this.exitStatus = exitStatus;
    }
    
    public String getJoiningStartDate() {
        return joiningStartDate;
    }

    public void setJoiningStartDate(String joiningStartDate) {
        this.joiningStartDate = joiningStartDate;
    }

    public String getJoiningEndDate() {
        return joiningEndDate;
    }

    public void setJoiningEndDate(String joiningEndDate) {
        this.joiningEndDate = joiningEndDate;
    }
    
    

    public String getSourceOfHire() {
        return sourceOfHire;
    }

    public void setSourceOfHire(String sourceOfHire) {
        this.sourceOfHire = sourceOfHire;
    }
    
    

    public String getParentSbu() {
        return parentSbu;
    }

    public void setParentSbu(String parentSbu) {
        this.parentSbu = parentSbu;
    }

    public String getParentSubSbu() {
        return parentSubSbu;
    }

    public void setParentSubSbu(String parentSubSbu) {
        this.parentSubSbu = parentSubSbu;
    }

    public String getParentManagerId() {
        return parentManagerId;
    }

    public void setParentManagerId(String parentManagerId) {
        this.parentManagerId = parentManagerId;
    }

    public String getParentManagerName() {
        return parentManagerName;
    }

    public void setParentManagerName(String parentManagerName) {
        this.parentManagerName = parentManagerName;
    }
    
    

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
    
    
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getBillable() {
        return billable;
    }

    public void setBillable(String billable) {
        this.billable = billable;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
          this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getExpMonths() {
        return expMonths;
    }

    public void setExpMonths(String expMonths) {
        this.expMonths = expMonths;
    }

    public String getExpYears() {
        return expYears;
    }

    public void setExpYears(String expYears) {
        this.expYears = expYears;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getRelieved() {
        return relieved;
    }

    public void setRelieved(String relieved) {
        this.relieved = relieved;
    }

    public String getResigned() {
        return resigned;
    }

    public void setResigned(String resigned) {
        this.resigned = resigned;
    }

    public String getRmId() {
        return rmId;
    }

    public void setRmId(String rmId) {
        this.rmId = rmId;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getSbu() {
        return sbu;
    }

    public void setSbu(String sbu) {
        this.sbu = sbu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSbuFilter() {
        return sbuFilter;
    }

    public void setSbuFilter(String sbuFilter) {
        this.sbuFilter = sbuFilter;
    }

    public String getTransferCity() {
        return transferCity;
    }

    public void setTransferCity(String transferCity) {
        this.transferCity = transferCity;
    }

    public String getTransfercityId() {
        return transfercityId;
    }

    public void setTransfercityId(String transfercityId) {
        this.transfercityId = transfercityId;
    }

    public String getTransferredDate() {
        return transferredDate;
    }

    public void setTransferredDate(String transferredDate) {
        this.transferredDate = transferredDate;
    }

    public String getTransferdCountryId() {
        return transferdCountryId;
    }

    public void setTransferdCountryId(String transferdCountryId) {
        this.transferdCountryId = transferdCountryId;
    }

    public String getTransferedCountry() {
        return transferedCountry;
    }

    public void setTransferedCountry(String transferedCountry) {
        this.transferedCountry = transferedCountry;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getPersonalMail() {
        return personalMail;
    }

    public void setPersonalMail(String personalMail) {
        this.personalMail = personalMail;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getEmp_billable() {
        return emp_billable;
    }

    public void setEmp_billable(String emp_billable) {
        this.emp_billable = emp_billable;
    }

    public String getEmp_dnb() {
        return emp_dnb;
    }

    public void setEmp_dnb(String emp_dnb) {
        this.emp_dnb = emp_dnb;
    }

    public String getEmp_general() {
        return emp_general;
    }

    public void setEmp_general(String emp_general) {
        this.emp_general = emp_general;
    }

    public String getEmp_sales() {
        return emp_sales;
    }

    public void setEmp_sales(String emp_sales) {
        this.emp_sales = emp_sales;
    }

    public String getEmp_total() {
        return emp_total;
    }

    public void setEmp_total(String emp_total) {
        this.emp_total = emp_total;
    }
}
