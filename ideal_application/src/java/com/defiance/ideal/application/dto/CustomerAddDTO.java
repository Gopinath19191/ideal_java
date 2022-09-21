/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 14058
 */
public class CustomerAddDTO implements Serializable {

    private String lastInsertId;
    private String salesManId;
    private String employeeNumber;
    private String employeeName;
    private String customerName;
    private String customerGroup;
    private String customerGroupName;
    private String SBU;
    private String RBU;
    private String currencyId;
    private String currencyCode;
    private String currency;
    private String customerURL;
    private String status;
    private String prevStatus;
    private String salesPerson;
    private String salesPersonId;
    private String customerCategory;
    private String aboutCustomer;
    private String remarks;
    private String customerAddress;
    private String city;
    private String region;
    private String country;
    private String salesPersonName;
    private Map <String, List> changedData;
    private Map <String, List> newlyAddedData;
    private Map <String, List> newlyAddedbilling;
    private Map <String, List> newlyAddedwork;
    private Map <String, List> newlyAddedfinance;
    private String[] customerDivisionList;
    private String customerDivisionName;
    private String[] customerDivisionListName;
    private String divisionId;
    private String fileinsertId;
    private int parentId;
    private String tanNumber;
    private String tableName;
    private String tableId;
    private String changedBy;
    private String changedFrom;
    private String changedTo;
    private String fieldChanged;
    private String customerCodelike;
    private String customerCalName;
    private String invoiceTo;
    private String preInvoiceTo;
    private String invoiceValue;

    public void setInvoiceValue(String invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public String getInvoiceValue() {
        return invoiceValue;
    }

    public void setPreInvoiceTo(String preInvoiceTo) {
        this.preInvoiceTo = preInvoiceTo;
    }

    public String getPreInvoiceTo() {
        return preInvoiceTo;
    }

    public String getInvoiceTo() {
        return invoiceTo;
    }   

    public void setInvoiceTo(String invoiceTo) {
        this.invoiceTo = invoiceTo;
    }

    public String getCustomerCalName() {
        return customerCalName;
    }

    public void setCustomerCalName(String customerCalName) {
        this.customerCalName = customerCalName;
    }

    public String getCustomerCodelike() {
        return customerCodelike;
    }

    public void setCustomerCodelike(String customerCodelike) {
        this.customerCodelike = customerCodelike;
    }
    
    

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public String getChangedFrom() {
        return changedFrom;
    }

    public void setChangedFrom(String changedFrom) {
        this.changedFrom = changedFrom;
    }

    public String getChangedTo() {
        return changedTo;
    }

    public void setChangedTo(String changedTo) {
        this.changedTo = changedTo;
    }

    public String getFieldChanged() {
        return fieldChanged;
    }

    public void setFieldChanged(String fieldChanged) {
        this.fieldChanged = fieldChanged;
    }
    
    public String getTanNumber() {
        return tanNumber;
    }

    public void setTanNumber(String tanNumber) {
        this.tanNumber = tanNumber;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getFileinsertId() {
        return fileinsertId;
    }

    public void setFileinsertId(String fileinsertId) {
        this.fileinsertId = fileinsertId;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String[] getCustomerDivisionListName() {
        return customerDivisionListName;
    }

    public void setCustomerDivisionListName(String[] customerDivisionListName) {
        this.customerDivisionListName = customerDivisionListName;
    }

    public String getCustomerDivisionName() {
        return customerDivisionName;
    }

    public void setCustomerDivisionName(String customerDivisionName) {
        this.customerDivisionName = customerDivisionName;
    }

    public String[] getCustomerDivisionList() {
        return customerDivisionList;
    }

    public void setCustomerDivisionList(String[] customerDivisionList) {
        this.customerDivisionList = customerDivisionList;
    }
    

    public Map<String, List> getNewlyAddedbilling() {
        return newlyAddedbilling;
    }

    public void setNewlyAddedbilling(Map<String, List> newlyAddedbilling) {
        this.newlyAddedbilling = newlyAddedbilling;
    }

    public Map<String, List> getNewlyAddedwork() {
        return newlyAddedwork;
    }

    public void setNewlyAddedwork(Map<String, List> newlyAddedwork) {
        this.newlyAddedwork = newlyAddedwork;
    }

    public Map<String, List> getNewlyAddedfinance() {
        return newlyAddedfinance;
    }

    public void setNewlyAddedfinance(Map<String, List> newlyAddedfinance) {
        this.newlyAddedfinance = newlyAddedfinance;
    }

    public Map<String, List> getNewlyAddedData() {
        return newlyAddedData;
    }

    public void setNewlyAddedData(Map<String, List> newlyAddedData) {
        this.newlyAddedData = newlyAddedData;
    }

    public Map<String, List> getChangedData() {
        return changedData;
    }

    public void setChangedData(Map<String, List> changedData) {
        this.changedData = changedData;
    }
    
    
    public String getSalesPersonName() {
        return salesPersonName;
    }

    public void setSalesPersonName(String salesPersonName) {
        this.salesPersonName = salesPersonName;
    }
    
    
    



    private String termsOfPayment;
    private String businessLeaderName;
    private String salesPersonContactNo;
    private String bdmName;
    private String stcNo;
    private String dataAreaId;
    private String customerID;
    private String custID;
    private Integer rbuID;
    private String rbuName;
    private Integer sbuID;
    private String sbuName;
    private String customerSaveButton;
    private String customerSubmitButton;
    private String customerApproveButton;
    private String customerRejectButton;
    private String cityID;
    private String cityName;
    private String regionID;
    private String regionName;
    private String countryID;
    private String countryName;
    private String regionFilter;
    private String countryFilter;
    

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }
    private String RBUFilter;
    private String invoiceID;
    private String invoiceCode;
    private String customerInvoiceCode;
    private String empWorkMail;
    private String empID;
    private String customerCode;
    private String legalInvoiceCodeId;
    private String customerMaxId;
    private String countryHidId;
    private String configKey;
    private String configValue;
    private String subRBU;
    private String sbuId;
    private String salesPersonRefId;
    private String contactType;
    private String addressType;
    private String customerStatus;
    private String id;
    private String contactPerson;
    private String contactDesignation;
    private String contactPhone;
    private String contactEmail;
    private String customercontactType;
    private String customerId;
    private String pincode;
    private String isCompanyLocation;
    private String iscompanyLocationId;

    public String getIscompanyLocationId() {
        return iscompanyLocationId;
    }

    public void setIscompanyLocationId(String iscompanyLocationId) {
        this.iscompanyLocationId = iscompanyLocationId;
    }
    

    public String getIsCompanyLocation() {
        return isCompanyLocation;
    }

    public void setIsCompanyLocation(String isCompanyLocation) {
        this.isCompanyLocation = isCompanyLocation;
    }
    private String customerGroupId;

    public String getContactDesignation() {
        return contactDesignation;
    }

    public void setContactDesignation(String contactDesignation) {
        this.contactDesignation = contactDesignation;
    }
    

    public String getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(String customerGroupId) {
        this.customerGroupId = customerGroupId;
    }
    
    

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCustomerId() {
        return customerId;


    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;


    }
    
    
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;


    }

    public void setFileName(String fileName) {
        this.fileName = fileName;


    }



    public String getFileType() {
        return fileType;


    }

    public void setFileType(String fileType) {
        this.fileType = fileType;


    }

    public String getReferenceName() {
        return referenceName;


    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;


    }

    public int getReferenceId() {
        return referenceId;


    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;


    }

    public String getModuleName() {
        return moduleName;


    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;


    }
    
    private MultipartFile file;
    private int fileId;
    private String fileName;
    private String fileType;
    private String referenceName;
    private int referenceId;
    private String moduleName;
    private String attchedFileName;
    private String lastFileInsertId;

    public String getLastFileInsertId() {
        return lastFileInsertId;


    }

    public void setLastFileInsertId(String lastFileInsertId) {
        this.lastFileInsertId = lastFileInsertId;


    }

    public String getAttchedFileName() {
        return attchedFileName;


    }

    public void setAttchedFileName(String attchedFileName) {
        this.attchedFileName = attchedFileName;
    }
    
    
 
    public String getCustomercontactType() {
        return customercontactType;


    }

    public void setCustomercontactType(String customercontactType) {
        this.customercontactType = customercontactType;
    }
    
    public String getId() {
        return id;


    }

    public String getContactPerson() {
        return contactPerson;


    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;


    }

    public String getContactPhone() {
        return contactPhone;


    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;


    }

    public String getContactEmail() {
        return contactEmail;


    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;


    }

    public void setId(String id) {
        this.id = id;




    }
    
     public String getCustomerStatus() {
        return customerStatus;


    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;


    }

    public String getContactType() {
        return contactType;



    }

    public void setContactType(String contactType) {
        this.contactType = contactType;


    }

    public String getAddressType() {
        return addressType;


    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;


    }
    
 
    public String getCityName() {
        return cityName;


    }

    public void setCityName(String cityName) {
        this.cityName = cityName;


    }

    public String getRegionID() {
        return regionID;


    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;


    }

    public String getRegionName() {
        return regionName;



    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;


    }

    public String getCountryID() {
        return countryID;


    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;


    }

    public String getCountryName() {
        return countryName;


    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;


    }

    public String getRegionFilter() {
        return regionFilter;


    }

    public void setRegionFilter(String regionFilter) {
        this.regionFilter = regionFilter;


    }

    public String getCountryFilter() {
        return countryFilter;
    }

    public void setCountryFilter(String countryFilter) {
        this.countryFilter = countryFilter;
    }

    public String getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(String lastInsertId) {
        this.lastInsertId = lastInsertId;
    }

    public String getSalesManId() {
        return salesManId;
    }

    public void setSalesManId(String salesManId) {
        this.salesManId = salesManId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public String getSBU() {
        return SBU;
    }

    public void setSBU(String SBU) {
        this.SBU = SBU;
    }

    public String getRBU() {
        return RBU;
    }

    public void setRBU(String RBU) {
        this.RBU = RBU;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrevStatus() {
        return prevStatus;
    }

    public void setPrevStatus(String prevStatus) {
        this.prevStatus = prevStatus;
    }

    public String getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(String salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    public String getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;


    }

    public void setCountry(String country) {
        this.country = country;


    }

    public String getTermsOfPayment() {
        return termsOfPayment;


    }

    public void setTermsOfPayment(String termsOfPayment) {
        this.termsOfPayment = termsOfPayment;


    }

    public String getBusinessLeaderName() {
        return businessLeaderName;


    }

    public void setBusinessLeaderName(String businessLeaderName) {
        this.businessLeaderName = businessLeaderName;


    }

    public String getBdmName() {
        return bdmName;


    }

    public void setBdmName(String bdmName) {
        this.bdmName = bdmName;


    }

    public String getStcNo() {
        return stcNo;


    }

    public void setStcNo(String stcNo) {
        this.stcNo = stcNo;


    }

    public String getDataAreaId() {
        return dataAreaId;


    }

    public void setDataAreaId(String dataAreaId) {
        this.dataAreaId = dataAreaId;


    }

    public String getCustID() {
        return custID;


    }

    public void setCustID(String custID) {
        this.custID = custID;


    }

    public Integer getRbuID() {
        return rbuID;
    }

    public void setRbuID(Integer rbuID) {
        this.rbuID = rbuID;
    }

    public String getRbuName() {
        return rbuName;
    }

    public void setRbuName(String rbuName) {
        this.rbuName = rbuName;
    }

    public Integer getSbuID() {
        return sbuID;
    }

    public void setSbuID(Integer sbuID) {
        this.sbuID = sbuID;
    }

    public String getSbuName() {
        return sbuName;
    }

    public void setSbuName(String sbuName) {
        this.sbuName = sbuName;
    }

    public String getCustomerSaveButton() {
        return customerSaveButton;
    }

    public void setCustomerSaveButton(String customerSaveButton) {
        this.customerSaveButton = customerSaveButton;
    }

    public String getCustomerSubmitButton() {
        return customerSubmitButton;
    }

    public void setCustomerSubmitButton(String customerSubmitButton) {
        this.customerSubmitButton = customerSubmitButton;
    }

    public String getCustomerApproveButton() {
        return customerApproveButton;
    }

    public void setCustomerApproveButton(String customerApproveButton) {
        this.customerApproveButton = customerApproveButton;
    }

    public String getCustomerRejectButton() {
        return customerRejectButton;
    }

    public void setCustomerRejectButton(String customerRejectButton) {
        this.customerRejectButton = customerRejectButton;


    }

    public String getRBUFilter() {
        return RBUFilter;


    }

    public void setRBUFilter(String RBUFilter) {
        this.RBUFilter = RBUFilter;


    }

    public String getInvoiceID() {
        return invoiceID;


    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceCode() {
        return invoiceCode;


    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;


    }

    public String getCustomerInvoiceCode() {
        return customerInvoiceCode;


    }

    public void setCustomerInvoiceCode(String customerInvoiceCode) {
        this.customerInvoiceCode = customerInvoiceCode;


    }

    public String getEmpWorkMail() {
        return empWorkMail;


    }

    public void setEmpWorkMail(String empWorkMail) {
        this.empWorkMail = empWorkMail;


    }

    public String getEmpID() {
        return empID;


    }

    public void setEmpID(String empID) {
        this.empID = empID;


    }

    public String getCustomerCode() {
        return customerCode;


    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;


    }

    public String getLegalInvoiceCodeId() {
        return legalInvoiceCodeId;


    }

    public void setLegalInvoiceCodeId(String legalInvoiceCodeId) {
        this.legalInvoiceCodeId = legalInvoiceCodeId;


    }

    public String getCustomerMaxId() {
        return customerMaxId;


    }

    public void setCustomerMaxId(String customerMaxId) {
        this.customerMaxId = customerMaxId;


    }

    public String getCountryHidId() {
        return countryHidId;


    }

    public void setCountryHidId(String countryHidId) {
        this.countryHidId = countryHidId;


    }

    public String getConfigKey() {
        return configKey;


    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;


    }

    public String getConfigValue() {
        return configValue;


    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;


    }

    public String getSubRBU() {
        return subRBU;


    }

    public void setSubRBU(String subRBU) {
        this.subRBU = subRBU;


    }

    public String getSbuId() {
        return sbuId;


    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;


    }

    public String getSalesPersonRefId() {
        return salesPersonRefId;


    }

    public void setSalesPersonRefId(String salesPersonRefId) {
        this.salesPersonRefId = salesPersonRefId;


    }

    
    public String getCustomerID() {
        return customerID;


    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;


    }
    
    private String customerDivision;
    
    private String attachmentType;
    private MultipartFile attachmentValue;

    public String getCustomerName() {
        return customerName;


    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;


    }

    public String getCustomerGroup() {
        return customerGroup;


    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;


    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    public String getAboutCustomer() {
        return aboutCustomer;
    }

    public void setAboutCustomer(String aboutCustomer) {
        this.aboutCustomer = aboutCustomer;
    }

    public String getCustomerURL() {
        return customerURL;
    }

    public void setCustomerURL(String customerURL) {
        this.customerURL = customerURL;
    }

    public String getCustomerDivision() {
        return customerDivision;
    }

    public void setCustomerDivision(String customerDivision) {
        this.customerDivision = customerDivision;
    }

    public String getSalesPersonContactNo() {
        return salesPersonContactNo;
    }

    public void setSalesPersonContactNo(String salesPersonContactNo) {
        this.salesPersonContactNo = salesPersonContactNo;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public MultipartFile getAttachmentValue() {
        return attachmentValue;
    }

    public void setAttachmentValue(MultipartFile attachmentValue) {
        this.attachmentValue = attachmentValue;
    }
    
    private String[] workingHoursPerDay;
    private String[] customerBillingAddressNew;
    private String[] customerBillingCountryNew;
    private String[] customerBillingRegionNew;
    private String[] customerBillingCityNew;
    private String[] customerBillingPincodeNew;
    private String[] customerBillingShortCodeNew;
    private String[] customerBillingGstCodeNew;
    private String[] customerBillingGstNumberNew;

    public String[] getCustomerBillingShortCodeNew() {
        return customerBillingShortCodeNew;
    }

    public void setCustomerBillingShortCodeNew(String[] customerBillingShortCodeNew) {
        this.customerBillingShortCodeNew = customerBillingShortCodeNew;
    }

    public String[] getCustomerWorkShortCodeNew() {
        return customerWorkShortCodeNew;
    }

    public void setCustomerWorkShortCodeNew(String[] customerWorkShortCodeNew) {
        this.customerWorkShortCodeNew = customerWorkShortCodeNew;
    }

    public String[] getCustomerBillingGstCodeNew() {
        return customerBillingGstCodeNew;
    }

    public void setCustomerBillingGstCodeNew(String[] customerBillingGstCodeNew) {
        this.customerBillingGstCodeNew = customerBillingGstCodeNew;
    }

    public String[] getCustomerBillingGstNumberNew() {
        return customerBillingGstNumberNew;
    }

    public void setCustomerBillingGstNumberNew(String[] customerBillingGstNumberNew) {
        this.customerBillingGstNumberNew = customerBillingGstNumberNew;
    }
        
    private String[] WorkingHoursPerDayNew;
	
	 //customer work location address New
    private String[] customerWorkAddressNew;
    private String[] customerWorkCountryNew;
    private String[] customerWorkRegionNew;
    private String[] customerWorkCityNew;
    private String[] customerWorkPincodeNew;
    private String[] customerWorkShortCodeNew;
    private String[] workLocationIscompanyLocationNew;
	
	
	// customer finanace contact details New
    private String[] customerFinanceContactPersonNew;
    private String[] customerFinanceContactPhoneNew;
    private String[] customerFinanceContactEmailNew;
    private String[] customerFinanceDesignationNew;
    
    private String[] customerDunningContactPersonNew;
    private String[] customerDunningContactPhoneNew;
    private String[] customerDunningContactEmailNew;
    private String[] customerDunningDesignationNew;
	
	//customer contact details New
    private String[] customerContactPersonNew;
    private String[] customerContactPhoneNew;
    private String[] customerContactEmailNew;
    private String[] customerDesignationNew;
    
            
    
    //customer contact details
    private String[] customerContactPerson;
    private String[] customerContactPhone;
    private String[] customerContactEmail;
    private String[] customerContactID;
    private String[] customerContactDesignation;
    
      // customer finanace contact details
    private String[] customerFinanceContactPerson;
    private String[] customerFinanceContactPhone;
    private String[] customerFinanceContactEmail;
    private String[] customerFinanceContactID;
    private String[] customerFinanceContactDesignation;
    
    private String[] customerDunningContactPerson;
    private String[] customerDunningContactPhone;
    private String[] customerDunningContactEmail;
    private String[] customerDunningContactID;
    private String[] customerDunningContactDesignation;

    public String[] getCustomerDunningContactPersonNew() {
        return customerDunningContactPersonNew;
    }

    public void setCustomerDunningContactPersonNew(String[] customerDunningContactPersonNew) {
        this.customerDunningContactPersonNew = customerDunningContactPersonNew;
    }

    public String[] getCustomerDunningContactPhoneNew() {
        return customerDunningContactPhoneNew;
    }

    public void setCustomerDunningContactPhoneNew(String[] customerDunningContactPhoneNew) {
        this.customerDunningContactPhoneNew = customerDunningContactPhoneNew;
    }

    public String[] getCustomerDunningContactEmailNew() {
        return customerDunningContactEmailNew;
    }

    public void setCustomerDunningContactEmailNew(String[] customerDunningContactEmailNew) {
        this.customerDunningContactEmailNew = customerDunningContactEmailNew;
    }

    public String[] getCustomerDunningDesignationNew() {
        return customerDunningDesignationNew;
    }

    public void setCustomerDunningDesignationNew(String[] customerDunningDesignationNew) {
        this.customerDunningDesignationNew = customerDunningDesignationNew;
    }

    public String[] getCustomerDunningContactPerson() {
        return customerDunningContactPerson;
    }

    public void setCustomerDunningContactPerson(String[] customerDunningContactPerson) {
        this.customerDunningContactPerson = customerDunningContactPerson;
    }

    public String[] getCustomerDunningContactPhone() {
        return customerDunningContactPhone;
    }

    public void setCustomerDunningContactPhone(String[] customerDunningContactPhone) {
        this.customerDunningContactPhone = customerDunningContactPhone;
    }

    public String[] getCustomerDunningContactEmail() {
        return customerDunningContactEmail;
    }

    public void setCustomerDunningContactEmail(String[] customerDunningContactEmail) {
        this.customerDunningContactEmail = customerDunningContactEmail;
    }

    public String[] getCustomerDunningContactID() {
        return customerDunningContactID;
    }

    public void setCustomerDunningContactID(String[] customerDunningContactID) {
        this.customerDunningContactID = customerDunningContactID;
    }

    public String[] getCustomerDunningContactDesignation() {
        return customerDunningContactDesignation;
    }

    public void setCustomerDunningContactDesignation(String[] customerDunningContactDesignation) {
        this.customerDunningContactDesignation = customerDunningContactDesignation;
    }

    public String[] getCustomerFinanceContactDesignation() {
        return customerFinanceContactDesignation;
    }

    public void setCustomerFinanceContactDesignation(String[] customerFinanceContactDesignation) {
        this.customerFinanceContactDesignation = customerFinanceContactDesignation;
    }
    
    
    
    //customer work location address
    private String[] customerWorkAddress;
    private String[] customerWorkShortCode;
    private String[] customerWorkCountry;
    private String[] customerWorkRegion;
    private String[] customerWorkCity;
    private String[] customerWorkPincode;
    private String[] customerWorkLocationID;
    private String[] WorkLocationIsCompanyLocation;
    private String[] isworklocationcompanylocation;

    public String[] getIsworklocationcompanylocation() {
        return isworklocationcompanylocation;
    }

    public void setIsworklocationcompanylocation(String[] isworklocationcompanylocation) {
        this.isworklocationcompanylocation = isworklocationcompanylocation;
    }
    
    
      // customer billing Address
    private String[] customerBillingAddress;
    private String[] customerBillingShortCode;
    private String[] customerBillingCountry;
    private String[] customerBillingRegion;
    private String[] customerBillingCity;
    private String[] customerBillingPincode;
    private String[] customerBillingID;
    private String[] customerBillingGstCode;
    private String[] customerBillingGstNumber;

    public String[] getWorkLocationIscompanyLocationNew() {
        return workLocationIscompanyLocationNew;
    }

    public void setWorkLocationIscompanyLocationNew(String[] workLocationIscompanyLocationNew) {
        this.workLocationIscompanyLocationNew = workLocationIscompanyLocationNew;
    }

    public String[] getWorkLocationIsCompanyLocation() {
        return WorkLocationIsCompanyLocation;
    }

    public void setWorkLocationIsCompanyLocation(String[] WorkLocatinIsCompanyLocation) {
        this.WorkLocationIsCompanyLocation = WorkLocatinIsCompanyLocation;
    }
    
    
    
    public String[] getCustomerWorkShortCode() {
        return customerWorkShortCode;
    }

    public void setCustomerWorkShortCode(String[] customerWorkShortCode) {
        this.customerWorkShortCode = customerWorkShortCode;
    }

    public String[] getCustomerBillingShortCode() {
        return customerBillingShortCode;
    }

    public void setCustomerBillingShortCode(String[] customerBillingShortCode) {
        this.customerBillingShortCode = customerBillingShortCode;
    }

    public String[] getCustomerBillingGstCode() {
        return customerBillingGstCode;
    }

    public void setCustomerBillingGstCode(String[] customerBillingGstCode) {
        this.customerBillingGstCode = customerBillingGstCode;
    }

    public String[] getCustomerBillingGstNumber() {
        return customerBillingGstNumber;
    }

    public void setCustomerBillingGstNumber(String[] customerBillingGstNumber) {
        this.customerBillingGstNumber = customerBillingGstNumber;
    }
    
    
    public String[] getCustomerContactDesignation() {
        return customerContactDesignation;
    }

    public void setCustomerContactDesignation(String[] customerContactDesignation) {
        this.customerContactDesignation = customerContactDesignation;
    }

        
    public String[] getCustomerFinanceDesignationNew() {
        return customerFinanceDesignationNew;
    }

    public void setCustomerFinanceDesignationNew(String[] customerFinanceDesignationNew) {
        this.customerFinanceDesignationNew = customerFinanceDesignationNew;
    }

    public String[] getCustomerDesignationNew() {
        return customerDesignationNew;
    }

    public void setCustomerDesignationNew(String[] customerDesignationNew) {
        this.customerDesignationNew = customerDesignationNew;
    }

    
    public String[] getWorkingHoursPerDayNew() {
        return WorkingHoursPerDayNew;
    }

    public void setWorkingHoursPerDayNew(String[] WorkingHoursPerDayNew) {
        this.WorkingHoursPerDayNew = WorkingHoursPerDayNew;
    }

    public String[] getCustomerFinanceContactPerson() {
        return customerFinanceContactPerson;
    }

    public void setCustomerFinanceContactPerson(String[] customerFinanceContactPerson) {
        this.customerFinanceContactPerson = customerFinanceContactPerson;
    }

    public String[] getCustomerFinanceContactPhone() {
        return customerFinanceContactPhone;
    }

    public void setCustomerFinanceContactPhone(String[] customerFinanceContactPhone) {
        this.customerFinanceContactPhone = customerFinanceContactPhone;
    }

    public String[] getCustomerFinanceContactEmail() {
        return customerFinanceContactEmail;
    }

    public void setCustomerFinanceContactEmail(String[] customerFinanceContactEmail) {
        this.customerFinanceContactEmail = customerFinanceContactEmail;
    }

    public String[] getCustomerFinanceContactID() {
        return customerFinanceContactID;
    }

    public void setCustomerFinanceContactID(String[] customerFinanceContactID) {
        this.customerFinanceContactID = customerFinanceContactID;
    }

    public String[] getCustomerWorkAddress() {
        return customerWorkAddress;
    }

    public void setCustomerWorkAddress(String[] customerWorkAddress) {
        this.customerWorkAddress = customerWorkAddress;
    }

    public String[] getCustomerWorkCountry() {
        return customerWorkCountry;
    }

    public void setCustomerWorkCountry(String[] customerWorkCountry) {
        this.customerWorkCountry = customerWorkCountry;
    }

    public String[] getCustomerWorkRegion() {
        return customerWorkRegion;
    }

    public void setCustomerWorkRegion(String[] customerWorkRegion) {
        this.customerWorkRegion = customerWorkRegion;
    }

    public String[] getCustomerWorkCity() {
        return customerWorkCity;
    }

    public void setCustomerWorkCity(String[] customerWorkCity) {
        this.customerWorkCity = customerWorkCity;
    }

    public String[] getCustomerWorkPincode() {
        return customerWorkPincode;
    }

    public void setCustomerWorkPincode(String[] customerWorkPincode) {
        this.customerWorkPincode = customerWorkPincode;
    }

    public String[] getCustomerWorkLocationID() {
        return customerWorkLocationID;
    }

    public void setCustomerWorkLocationID(String[] customerWorkLocationID) {
        this.customerWorkLocationID = customerWorkLocationID;
    }

    public String[] getCustomerBillingAddress() {
        return customerBillingAddress;
    }

    public void setCustomerBillingAddress(String[] customerBillingAddress) {
        this.customerBillingAddress = customerBillingAddress;
    }

    public String[] getCustomerBillingCountry() {
        return customerBillingCountry;
    }

    public void setCustomerBillingCountry(String[] customerBillingCountry) {
        this.customerBillingCountry = customerBillingCountry;
    }

    public String[] getCustomerBillingRegion() {
        return customerBillingRegion;
    }

    public void setCustomerBillingRegion(String[] customerBillingRegion) {
        this.customerBillingRegion = customerBillingRegion;
    }

    public String[] getCustomerBillingCity() {
        return customerBillingCity;
    }

    public void setCustomerBillingCity(String[] customerBillingCity) {
        this.customerBillingCity = customerBillingCity;
    }

    public String[] getCustomerBillingPincode() {
        return customerBillingPincode;
    }

    public void setCustomerBillingPincode(String[] customerBillingPincode) {
        this.customerBillingPincode = customerBillingPincode;
    }

    public String[] getCustomerBillingID() {
        return customerBillingID;
    }

    public void setCustomerBillingID(String[] customerBillingID) {
        this.customerBillingID = customerBillingID;
    }
    

    public String[] getCustomerContactPerson() {
        return customerContactPerson;
    }

    public void setCustomerContactPerson(String[] customerContactPerson) {
        this.customerContactPerson = customerContactPerson;
    }

    public String[] getCustomerContactPhone() {
        return customerContactPhone;
    }

    public void setCustomerContactPhone(String[] customerContactPhone) {
        this.customerContactPhone = customerContactPhone;
    }

    public String[] getCustomerContactEmail() {
        return customerContactEmail;
    }

    public void setCustomerContactEmail(String[] customerContactEmail) {
        this.customerContactEmail = customerContactEmail;
    }

    public String[] getCustomerContactID() {
        return customerContactID;
    }

    public void setCustomerContactID(String[] customerContactID) {
        this.customerContactID = customerContactID;
    }
    
    
    

    public String[] getWorkingHoursPerDay() {
        return workingHoursPerDay;
    }

    public void setWorkingHoursPerDay(String[] workingHoursPerDay) {
        this.workingHoursPerDay = workingHoursPerDay;
    }

    public String[] getCustomerBillingAddressNew() {
        return customerBillingAddressNew;
    }

    public void setCustomerBillingAddressNew(String[] customerBillingAddressNew) {
        this.customerBillingAddressNew = customerBillingAddressNew;
    }

    public String[] getCustomerBillingCountryNew() {
        return customerBillingCountryNew;
    }

    public void setCustomerBillingCountryNew(String[] customerBillingCountryNew) {
        this.customerBillingCountryNew = customerBillingCountryNew;
    }

    public String[] getCustomerBillingRegionNew() {
        return customerBillingRegionNew;
    }

    public void setCustomerBillingRegionNew(String[] customerBillingRegionNew) {
        this.customerBillingRegionNew = customerBillingRegionNew;
    }

    public String[] getCustomerBillingCityNew() {
        return customerBillingCityNew;
    }

    public void setCustomerBillingCityNew(String[] customerBillingCityNew) {
        this.customerBillingCityNew = customerBillingCityNew;
    }

    public String[] getCustomerBillingPincodeNew() {
        return customerBillingPincodeNew;
    }

    public void setCustomerBillingPincodeNew(String[] customerBillingPincodeNew) {
        this.customerBillingPincodeNew = customerBillingPincodeNew;
    }

    public String[] getCustomerWorkAddressNew() {
        return customerWorkAddressNew;
    }

    public void setCustomerWorkAddressNew(String[] customerWorkAddressNew) {
        this.customerWorkAddressNew = customerWorkAddressNew;
    }

    public String[] getCustomerWorkCountryNew() {
        return customerWorkCountryNew;
    }

    public void setCustomerWorkCountryNew(String[] customerWorkCountryNew) {
        this.customerWorkCountryNew = customerWorkCountryNew;
    }

    public String[] getCustomerWorkRegionNew() {
        return customerWorkRegionNew;
    }

    public void setCustomerWorkRegionNew(String[] customerWorkRegionNew) {
        this.customerWorkRegionNew = customerWorkRegionNew;
    }

    public String[] getCustomerWorkCityNew() {
        return customerWorkCityNew;
    }

    public void setCustomerWorkCityNew(String[] customerWorkCityNew) {
        this.customerWorkCityNew = customerWorkCityNew;
    }

    public String[] getCustomerWorkPincodeNew() {
        return customerWorkPincodeNew;
    }

    public void setCustomerWorkPincodeNew(String[] customerWorkPincodeNew) {
        this.customerWorkPincodeNew = customerWorkPincodeNew;
    }

    public String[] getCustomerFinanceContactPersonNew() {
        return customerFinanceContactPersonNew;
    }

    public void setCustomerFinanceContactPersonNew(String[] customerFinanceContactPersonNew) {
        this.customerFinanceContactPersonNew = customerFinanceContactPersonNew;
    }

    public String[] getCustomerFinanceContactPhoneNew() {
        return customerFinanceContactPhoneNew;
    }

    public void setCustomerFinanceContactPhoneNew(String[] customerFinanceContactPhoneNew) {
        this.customerFinanceContactPhoneNew = customerFinanceContactPhoneNew;
    }

    public String[] getCustomerFinanceContactEmailNew() {
        return customerFinanceContactEmailNew;
    }

    public void setCustomerFinanceContactEmailNew(String[] customerFinanceContactEmailNew) {
        this.customerFinanceContactEmailNew = customerFinanceContactEmailNew;
    }

    public String[] getCustomerContactPersonNew() {
        return customerContactPersonNew;
    }

    public void setCustomerContactPersonNew(String[] customerContactPersonNew) {
        this.customerContactPersonNew = customerContactPersonNew;
    }

    public String[] getCustomerContactPhoneNew() {
        return customerContactPhoneNew;
    }

    public void setCustomerContactPhoneNew(String[] customerContactPhoneNew) {
        this.customerContactPhoneNew = customerContactPhoneNew;
    }

    public String[] getCustomerContactEmailNew() {
        return customerContactEmailNew;
    }

    public void setCustomerContactEmailNew(String[] customerContactEmailNew) {
        this.customerContactEmailNew = customerContactEmailNew;
    }
   
   
    private String billingAddress;
    private String billingCountry;
    private String billingRegion;
    private String billingCity;
    private String billingPincode;
    private String gstCode;
    private String gstNumber;
    private String financeContactPerson;
    private String financeContactPhone;
    private String financeEmail;
    private String workLocationAddress;
    private String workLocationCountry;
    private String workLocationRegion;
    private String workLocationCity;
    private String workLocationPincode;
    private String workLocationWorkingHours;
    private String businessContactPerson;
    private String businessContactPhone;
    private String businessEmail;
    private String queryString;
    private String action;
    private String addressShortCode;
    private String workLocationIscmpyLocation;

    public String getWorkLocationIscmpyLocation() {
        return workLocationIscmpyLocation;
    }

    public void setWorkLocationIscmpyLocation(String workLocationIscmpyLocation) {
        this.workLocationIscmpyLocation = workLocationIscmpyLocation;
    }
    
    public String getAddressShortCode() {
        return addressShortCode;
    }

    public void setAddressShortCode(String addressShortCode) {
        this.addressShortCode = addressShortCode;
    }
    
    

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    
    
    
    
    

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
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

    public String getGstCode() {
        return gstCode;
    }

    public void setGstCode(String gstCode) {
        this.gstCode = gstCode;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
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

    public String getWorkLocationWorkingHours() {
        return workLocationWorkingHours;
    }

    public void setWorkLocationWorkingHours(String workLocationWorkingHours) {
        this.workLocationWorkingHours = workLocationWorkingHours;
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

    @Override
    public String toString() {
        return "CustomerAddDTO{" + "customerContactPerson=" + customerContactPerson + ", customerContactPhone=" + customerContactPhone + ", customerContactEmail=" + customerContactEmail + ", customerContactID=" + customerContactID + '}';
    }
    
}
