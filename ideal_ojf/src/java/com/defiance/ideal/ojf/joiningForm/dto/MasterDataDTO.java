/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.ojf.joiningForm.dto;



/**
 *
 * @author 14583
 */

public class MasterDataDTO implements java.io.Serializable {

    private String structureName;
    private String structureId;
    private String practiceGroupId;
    private String subPracticeGroupId;
    private String practiceGroup;
    private String subPracticeGroup;
    private String employeeName;
    private String employeeNumber;
    private String empId;
    private String bandName;
    private String desigName;
    private String bandId;
    private String desigId;

    private String configUniqueId;
    private String configKey;
    private String configValue;

    private String cmpLocationId;
    private String cmpLocationName;
    
    private String vendorId;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    private String vendorName;
   

    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
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

    public String getDesigId() {
        return desigId;
    }

    public void setDesigId(String desigId) {
        this.desigId = desigId;
    }

    public String getDesigName() {
        return desigName;
    }

    public void setDesigName(String desigName) {
        this.desigName = desigName;
    }

    public String getSubPracticeGroup() {
        return subPracticeGroup;
    }

    public void setSubPracticeGroup(String subPracticeGroup) {
        this.subPracticeGroup = subPracticeGroup;
    }

    public String getSubPracticeGroupId() {
        return subPracticeGroupId;
    }

    public void setSubPracticeGroupId(String subPracticeGroupId) {
        this.subPracticeGroupId = subPracticeGroupId;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigUniqueId() {
        return configUniqueId;
    }

    public void setConfigUniqueId(String configUniqueId) {
        this.configUniqueId = configUniqueId;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getCmpLocationId() {
        return cmpLocationId;
    }

    public void setCmpLocationId(String cmpLocationId) {
        this.cmpLocationId = cmpLocationId;
    }

    public String getCmpLocationName() {
        return cmpLocationName;
    }

    public void setCmpLocationName(String cmpLocationName) {
        this.cmpLocationName = cmpLocationName;
    }

}
