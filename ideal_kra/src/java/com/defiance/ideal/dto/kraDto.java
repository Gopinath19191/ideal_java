/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dto;

import java.io.Serializable;

/**
 *
 * @author 16221
 */
public class kraDto implements Serializable{
    private String employeeId;
    private String empNumber;
    private String financial_year;
    private String quarter_id;
    private String quarter_name;
    private String rm_name;
    private String approved_by;
    private String submitted_on;
    private String approved_on;
    private String accepted_on;
    private String kra_description;
    private String kra_uom;
    private String kra_target;
    private String kra_weightage;
    private String deleted;
    private String status;
    private String kra_id;
    private String kra_achieved;
    private String rm_remarks;
    private String employee_name;
    private String employee_number;
    private String total_weightage;
    private String description_id;
    private String configValue;
    private String configKey;
    private String manager_name;
    private String manager_mail_id;
    private String employee_mail_id;
    private String excelbuttonName;
    private String id;
    private String name;
    private String structureName;
    private String structureNameSearch;
    private String appraisee_id;
    private String reviewer_id;
    private String normalizer_id;
    private String band_id;
    private String lastInsertId;

    public String getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(String lastInsertId) {
        this.lastInsertId = lastInsertId;
    }
    
    public String getBand_id() {
        return band_id;
    }

    public void setBand_id(String band_id) {
        this.band_id = band_id;
    }
    
    public String getAppraisee_id() {
        return appraisee_id;
    }

    public void setAppraisee_id(String appraisee_id) {
        this.appraisee_id = appraisee_id;
    }

    public String getReviewer_id() {
        return reviewer_id;
    }

    public void setReviewer_id(String reviewer_id) {
        this.reviewer_id = reviewer_id;
    }

    public String getNormalizer_id() {
        return normalizer_id;
    }

    public void setNormalizer_id(String normalizer_id) {
        this.normalizer_id = normalizer_id;
    }
    
    public String getExcelbuttonName() {
        return excelbuttonName;
    }

    public void setExcelbuttonName(String excelbuttonName) {
        this.excelbuttonName = excelbuttonName;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getStructureNameSearch() {
        return structureNameSearch;
    }

    public void setStructureNameSearch(String structureNameSearch) {
        this.structureNameSearch = structureNameSearch;
    }


    private String structureId;

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }


    private String companyStructureId;

    public String getCompanyStructureId() {
        return companyStructureId;
    }

    public void setCompanyStructureId(String companyStructureId) {
        this.companyStructureId = companyStructureId;
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


    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getManager_mail_id() {
        return manager_mail_id;
    }

    public void setManager_mail_id(String manager_mail_id) {
        this.manager_mail_id = manager_mail_id;
    }

    public String getEmployee_mail_id() {
        return employee_mail_id;
    }

    public void setEmployee_mail_id(String employee_mail_id) {
        this.employee_mail_id = employee_mail_id;
    }
        
    public String getDescription_id() {
        return description_id;
    }

    public void setDescription_id(String description_id) {
        this.description_id = description_id;
    }
        
    public String getTotal_weightage() {
        return total_weightage;
    }

    public void setTotal_weightage(String total_weightage) {
        this.total_weightage = total_weightage;
    }
            
    public String getEmployee_name() {
        return employee_name;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
        
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFinancial_year() {
        return financial_year;
    }

    public void setFinancial_year(String financial_year) {
        this.financial_year = financial_year;
    }

    public String getQuarter_id() {
        return quarter_id;
    }

    public void setQuarter_id(String quarter_id) {
        this.quarter_id = quarter_id;
    }

    public String getQuarter_name() {
        return quarter_name;
    }

    public void setQuarter_name(String quarter_name) {
        this.quarter_name = quarter_name;
    }

    public String getRm_name() {
        return rm_name;
    }

    public void setRm_name(String rm_name) {
        this.rm_name = rm_name;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public String getSubmitted_on() {
        return submitted_on;
    }

    public void setSubmitted_on(String submitted_on) {
        this.submitted_on = submitted_on;
    }

    public String getApproved_on() {
        return approved_on;
    }

    public void setApproved_on(String approved_on) {
        this.approved_on = approved_on;
    }

    public String getAccepted_on() {
        return accepted_on;
    }

    public void setAccepted_on(String accepted_on) {
        this.accepted_on = accepted_on;
    }

    public String getKra_description() {
        return kra_description;
    }

    public void setKra_description(String kra_description) {
        this.kra_description = kra_description;
    }

    public String getKra_uom() {
        return kra_uom;
    }

    public void setKra_uom(String kra_uom) {
        this.kra_uom = kra_uom;
    }

    public String getKra_target() {
        return kra_target;
    }

    public void setKra_target(String kra_target) {
        this.kra_target = kra_target;
    }

    public String getKra_weightage() {
        return kra_weightage;
    }

    public void setKra_weightage(String kra_weightage) {
        this.kra_weightage = kra_weightage;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKra_id() {
        return kra_id;
    }

    public void setKra_id(String kra_id) {
        this.kra_id = kra_id;
    }

    public String getKra_achieved() {
        return kra_achieved;
    }

    public void setKra_achieved(String kra_achieved) {
        this.kra_achieved = kra_achieved;
    }

    public String getRm_remarks() {
        return rm_remarks;
    }

    public void setRm_remarks(String rm_remarks) {
        this.rm_remarks = rm_remarks;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }
      
    
}
