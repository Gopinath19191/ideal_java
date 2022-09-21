/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.dto;

import java.util.List;

/**
 *  
 * @author 14583
 */
public class LoginDTO {

   public String userType;
   public String assignedUnitId;
   public String type;
   public String moduleId;
   
   public String empId;
   public String empNumber;
   public String empName;
   public String bandName;
   public String bandId;
   public String companyStructure;
   public Integer companyStructureId;
   public String practiceGroup;
   public String practiceGroupId;
   public String subPracGroupId;
   public String subPracticeGroup;
   public String groupId;
   
   public String configKey;
   public String configValue;
   public Integer empCurrLocId;
   public Integer empCntryId;
   public Integer empRegId;
   public Integer empCityId;
   public String country;
   public String region;
   public String city;
   public String domain;
   public String skill;
   public String stream;
   public String customer;
   public String currency;

   // For Group Authentication
   public String gCreate;
   public String gRead;
   public String gUpdate;
   public String gDelete;

   // For User Authentication
   public String uCreate;
   public String uRead;
   public String uUpdate;
   public String uDelete;

   public String foreignKey;
   public String parentId;

    List moduleName;

    public String id;
    public String name;
    
    private String status;

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyStructure() {
        return companyStructure;
    }

    public void setCompanyStructure(String companyStructure) {
        this.companyStructure = companyStructure;
    }

    public Integer getCompanyStructureId() {
        return companyStructureId;
    }

    public void setCompanyStructureId(Integer companyStructureId) {
        this.companyStructureId = companyStructureId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getEmpCityId() {
        return empCityId;
    }

    public void setEmpCityId(Integer empCityId) {
        this.empCityId = empCityId;
    }

    public Integer getEmpCntryId() {
        return empCntryId;
    }

    public void setEmpCntryId(Integer empCntryId) {
        this.empCntryId = empCntryId;
    }

    public Integer getEmpCurrLocId() {
        return empCurrLocId;
    }

    public void setEmpCurrLocId(Integer empCurrLocId) {
        this.empCurrLocId = empCurrLocId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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

    public Integer getEmpRegId() {
        return empRegId;
    }

    public void setEmpRegId(Integer empRegId) {
        this.empRegId = empRegId;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public List getModuleName() {
        return moduleName;
    }

    public void setModuleName(List moduleName) {
        this.moduleName = moduleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPracticeGroup() {
        return practiceGroup;
    }

    public void setPracticeGroup(String practiceGroup) {
        this.practiceGroup = practiceGroup;
    }

    public String getPracticeGroupId() {
        return practiceGroupId;
    }

    public void setPracticeGroupId(String practiceGroupId) {
        this.practiceGroupId = practiceGroupId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getSubPracGroupId() {
        return subPracGroupId;
    }

    public void setSubPracGroupId(String subPracGroupId) {
        this.subPracGroupId = subPracGroupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedUnitId() {
        return assignedUnitId;
    }

    public void setAssignedUnitId(String assignedUnitId) {
        this.assignedUnitId = assignedUnitId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}