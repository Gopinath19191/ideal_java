/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dto;

import java.io.Serializable;

/**
 *
 * @author 14583
 */
public class AgencyDTO implements Serializable {
    
    private String agencyId;
    private String agencyName;
    private String abtAgency;
    private String agencyAddress;
    private String agencyContactPerson;
    private String agencyEmail;
    private String agencyContactPhone;
    private String agencyCode;
    private String agencyCategory;
    private String agencyUrl;
    private String status;
    private String deleted;
    private String creatorId;

    public String getAgencyCategory() {
        return agencyCategory;
    }

    public void setAgencyCategory(String agencyCategory) {
        this.agencyCategory = agencyCategory;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyUrl() {
        return agencyUrl;
    }

    public void setAgencyUrl(String agencyUrl) {
        this.agencyUrl = agencyUrl;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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

    public String getAbtAgency() {
        return abtAgency;
    }

    public void setAbtAgency(String abtAgency) {
        this.abtAgency = abtAgency;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

    public String getAgencyContactPerson() {
        return agencyContactPerson;
    }

    public void setAgencyContactPerson(String agencyContactPerson) {
        this.agencyContactPerson = agencyContactPerson;
    }

    public String getAgencyContactPhone() {
        return agencyContactPhone;
    }

    public void setAgencyContactPhone(String agencyContactPhone) {
        this.agencyContactPhone = agencyContactPhone;
    }

    public String getAgencyEmail() {
        return agencyEmail;
    }

    public void setAgencyEmail(String agencyEmail) {
        this.agencyEmail = agencyEmail;
    }
    
}
