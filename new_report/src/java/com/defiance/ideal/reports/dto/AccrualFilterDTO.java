/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

import java.io.Serializable;

/**
 *
 * @author 14053
 */
public class AccrualFilterDTO implements Serializable {
    
    private String monthFilter;
    private String yearFilter;
    private String sbuFilter;
    private String statsFilter;
    
    private int sbuId;
    private String sbuName;
    

    public String getMonthFilter() {
        return monthFilter;
    }

    public void setMonthFilter(String monthFilter) {
        this.monthFilter = monthFilter;
    }

    public String getYearFilter() {
        return yearFilter;
    }

    public void setYearFilter(String yearFilter) {
        this.yearFilter = yearFilter;
    }

    public String getSbuFilter() {
        return sbuFilter;
    }

    public void setSbuFilter(String sbuFilter) {
        this.sbuFilter = sbuFilter;
    }

    public int getSbuId() {
        return sbuId;
    }

    public void setSbuId(int sbuId) {
        this.sbuId = sbuId;
    }

    public String getSbuName() {
        return sbuName;
    }

    public void setSbuName(String sbuName) {
        this.sbuName = sbuName;
    }

    public String getStatsFilter() {
        return statsFilter;
    }

    public void setStatsFilter(String statsFilter) {
        this.statsFilter = statsFilter;
    }

}
