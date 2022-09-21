/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

/**
 *
 * @author 14517
 */
public class FullReportDTO {
    private String projectcode;
     private String[] status;
     private String[] ownership;
     private String sbu;
     private String billable;
     private String subSbu;
//      private String pricing;

//    public String getPricing() {
//        return pricing;
//    }
//
//    public void setPricing(String pricing) {
//        this.pricing = pricing;
//    }

    public String getSbu() {
        return sbu;
    }

    public void setSbu(String sbu) {
        this.sbu = sbu;
    }

    public String[] getOwnership() {
        return ownership;
    }

    public void setOwnership(String[] ownership) {
        this.ownership = ownership;
    }

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

      public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBillable() {
        return billable;
    }

    public void setBillable(String billable) {
        this.billable = billable;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }

}
