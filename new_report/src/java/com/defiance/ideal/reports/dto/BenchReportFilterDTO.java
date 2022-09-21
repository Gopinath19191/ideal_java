/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

/**
 *
 * @author 14619
 */
public class BenchReportFilterDTO {

    private String bandFilter;
    private String sbuFilter;
    private String sbuId;
    private String sbuName;
    private String bandId;
    private String bandName;
    private String from_date;
    private String to_date;
    private String subSbu;

    public String getSbuId() {
        return sbuId;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }

    public String getSbuName() {
        return sbuName;
    }

    public void setSbuName(String sbuName) {
        this.sbuName = sbuName;
    }

    /**
     * @return the bandFilter
     */
    public String getBandFilter() {
        return bandFilter;
    }

    /**
     * @param bandFilter the bandFilter to set
     */
    public void setBandFilter(String bandFilter) {
        this.bandFilter = bandFilter;
    }

    /**
     * @return the sbuFilter
     */
    public String getSbuFilter() {
        return sbuFilter;
    }

    /**
     * @param sbuFilter the sbuFilter to set
     */
    public void setSbuFilter(String sbuFilter) {
        this.sbuFilter = sbuFilter;
    }

    /**
     * @return the bandId
     */
    public String getBandId() {
        return bandId;
    }

    /**
     * @param bandId the bandId to set
     */
    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    /**
     * @return the bandName
     */
    public String getBandName() {
        return bandName;
    }

    /**
     * @param bandName the bandName to set
     */
    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }
    
}
