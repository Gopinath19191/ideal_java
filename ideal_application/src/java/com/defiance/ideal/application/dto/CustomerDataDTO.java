/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.dto;

import java.io.Serializable;

/**
 *
 * @author 14053
 */
public class CustomerDataDTO implements Serializable {
    private String customerName;
    private String customerGroup;
    private String customerGroupName;
    private String SBU;
    private String RBU;
    private String currency;
    private String customerURL;
    private String status;
    private Integer customerID;
    private String custID;
    private String customerCode;
    private String salesPerson;
    private String customerContactPhone;
    private String customerEmail;
    private String customerContactPerson;
    private String termsOfPayment;
    private String businessLeaderName;
    private String salesPersonContactNo;
    private String bdmName;
    private String stcNo;
    private String customerAddress;
    private String customerCountry;
    private String subRBU;
    private String salesPersonRef;
    private String workLocationAddress;
    private String workLocationShortCode;
    private String workLocationCountry;
    private String workLocationRegion;
    private String workLocationCity;
    private String workLocationPincode;
    private String workingHrs;
    
    private String billingAddress;
    private String billingAddressShortCode;
    private String billingCountry;
    private String billingRegion;
    private String billingCity;
    private String billingGstCode;
    private String billingGstNumber;

    public String getBillingGstCode() {
        return billingGstCode;
    }

    public void setBillingGstCode(String billingGstCode) {
        this.billingGstCode = billingGstCode;
    }

    public String getBillingGstNumber() {
        return billingGstNumber;
    }

    public void setBillingGstNumber(String billingGstNumber) {
        this.billingGstNumber = billingGstNumber;
    }
    
    
    public String getWorkingHrs() {
        return workingHrs;
    }

    public void setWorkingHrs(String workingHrs) {
        this.workingHrs = workingHrs;
    }
    private String billingPincode;
	
    private String businessContactPerson;
    private String businessContactDesignation;

    public String getWorkLocationShortCode() {
        return workLocationShortCode;
    }

    public void setWorkLocationShortCode(String workLocationShortCode) {
        this.workLocationShortCode = workLocationShortCode;
    }

    public String getBillingAddressShortCode() {
        return billingAddressShortCode;
    }

    public void setBillingAddressShortCode(String billingAddressShortCode) {
        this.billingAddressShortCode = billingAddressShortCode;
    }

    public String getBusinessContactDesignation() {
        return businessContactDesignation;
    }

    public void setBusinessContactDesignation(String businessContactDesignation) {
        this.businessContactDesignation = businessContactDesignation;
    }

    public String getFinanceContactDesignation() {
        return financeContactDesignation;
    }

    public void setFinanceContactDesignation(String financeContactDesignation) {
        this.financeContactDesignation = financeContactDesignation;
    }
    private String businessContactPhone;
    private String businessEmail;
	
    private String financeContactPerson;
    private String financeContactDesignation;
    private String financeContactPhone;
    private String financeEmail;

    private String divisionCount;
    private String divisionName;
    private String deleted;
    private String deletedName;
    private String projectName;
    private String projectStatus;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
    
    

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getDeletedName() {
        return deletedName;
    }

    public void setDeletedName(String deletedName) {
        this.deletedName = deletedName;
    }
    
    

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    
    

    public String getDivisionCount() {
        return divisionCount;
    }

    public void setDivisionCount(String divisionCount) {
        this.divisionCount = divisionCount;
    }
    
    
    public String getWorkLocationAddress() {
        return workLocationAddress;
    }

    public void setWorkLocationAddress(String workLocationAddress) {
        this.workLocationAddress = workLocationAddress;
    }

    public String getWorkLocationCountry() {
        return workLocationCountry;
    }

    public void setWorkLocationCountry(String workLocationCountry) {
        this.workLocationCountry = workLocationCountry;
    }

    public String getWorkLocationRegion() {
        return workLocationRegion;
    }

    public void setWorkLocationRegion(String workLocationRegion) {
        this.workLocationRegion = workLocationRegion;
    }

    public String getWorkLocationCity() {
        return workLocationCity;
    }

    public void setWorkLocationCity(String workLocationCity) {
        this.workLocationCity = workLocationCity;
    }

    public String getWorkLocationPincode() {
        return workLocationPincode;
    }

    public void setWorkLocationPincode(String workLocationPincode) {
        this.workLocationPincode = workLocationPincode;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingRegion() {
        return billingRegion;
    }

    public void setBillingRegion(String billingRegion) {
        this.billingRegion = billingRegion;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPincode() {
        return billingPincode;
    }

    public void setBillingPincode(String billingPincode) {
        this.billingPincode = billingPincode;
    }

    public String getBusinessContactPerson() {
        return businessContactPerson;
    }

    public void setBusinessContactPerson(String businessContactPerson) {
        this.businessContactPerson = businessContactPerson;
    }

    public String getBusinessContactPhone() {
        return businessContactPhone;
    }

    public void setBusinessContactPhone(String businessContactPhone) {
        this.businessContactPhone = businessContactPhone;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getFinanceContactPerson() {
        return financeContactPerson;
    }

    public void setFinanceContactPerson(String financeContactPerson) {
        this.financeContactPerson = financeContactPerson;
    }

    public String getFinanceContactPhone() {
        return financeContactPhone;
    }

    public void setFinanceContactPhone(String financeContactPhone) {
        this.financeContactPhone = financeContactPhone;
    }

    public String getFinanceEmail() {
        return financeEmail;
    }

    public void setFinanceEmail(String financeEmail) {
        this.financeEmail = financeEmail;
    }

    



    public String getSalesPersonRef() {
        return salesPersonRef;
    }

    public void setSalesPersonRef(String salesPersonRef) {
        this.salesPersonRef = salesPersonRef;
    }

    
    
    public String getSubRBU() {
        return subRBU;
    }

    public void setSubRBU(String subRBU) {
        this.subRBU = subRBU;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getBdmName() {
        return bdmName;
    }

    public void setBdmName(String bdmName) {
        this.bdmName = bdmName;
    }

    public String getBusinessLeaderName() {
        return businessLeaderName;
    }

    public void setBusinessLeaderName(String businessLeaderName) {
        this.businessLeaderName = businessLeaderName;
    }

    public String getCustomerContactPerson() {
        return customerContactPerson;
    }

    public void setCustomerContactPerson(String customerContactPerson) {
        this.customerContactPerson = customerContactPerson;
    }

    public String getCustomerContactPhone() {
        return customerContactPhone;
    }

    public void setCustomerContactPhone(String customerContactPhone) {
        this.customerContactPhone = customerContactPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSalesPersonContactNo() {
        return salesPersonContactNo;
    }

    public void setSalesPersonContactNo(String salesPersonContactNo) {
        this.salesPersonContactNo = salesPersonContactNo;
    }

    public String getStcNo() {
        return stcNo;
    }

    public void setStcNo(String stcNo) {
        this.stcNo = stcNo;
    }

    public String getTermsOfPayment() {
        return termsOfPayment;
    }

    public void setTermsOfPayment(String termsOfPayment) {
        this.termsOfPayment = termsOfPayment;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private String customerStatus;
    private String customerId;


    public String getRBU() {
        return RBU;
    }

    public void setRBU(String RBU) {
        this.RBU = RBU;
    }

    public String getSBU() {
        return SBU;
    }

    public void setSBU(String SBU) {
        this.SBU = SBU;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getCustomerURL() {
        return customerURL;
    }

    public void setCustomerURL(String customerURL) {
        this.customerURL = customerURL;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

}
