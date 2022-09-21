/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 14583
 */
public class CustomerMappingDTO implements Serializable {
    
    private int deleted;
    private String subRbu;
    private String rbuID;
    private String rbuName;
    private String customerListCheck;
    private String successMsg;
    private String customerMapId;
    private String customerId;
    private String customerCode;
    private String customerName;
    private String customerCodeName;
    private String bdmId;
    private String bdmEmpNumber;
    private String bdmEmpName;
    private String leaderId;
    private String leaderEmpNumber;
    private String leaderEmpName;
    private String region;
    private String regionId;
    private String regionName;
    private String mapperId;
    private String mapperEmpNumber;
    private String mapperEmpName;
    private Date mappedDate;

    public String getBdmEmpName() {
        return bdmEmpName;
    }

    public void setBdmEmpName(String bdmEmpName) {
        this.bdmEmpName = bdmEmpName;
    }

    public String getBdmEmpNumber() {
        return bdmEmpNumber;
    }

    public void setBdmEmpNumber(String bdmEmpNumber) {
        this.bdmEmpNumber = bdmEmpNumber;
    }

    public String getBdmId() {
        return bdmId;
    }

    public void setBdmId(String bdmId) {
        this.bdmId = bdmId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerMapId() {
        return customerMapId;
    }

    public void setCustomerMapId(String customerMapId) {
        this.customerMapId = customerMapId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLeaderEmpName() {
        return leaderEmpName;
    }

    public void setLeaderEmpName(String leaderEmpName) {
        this.leaderEmpName = leaderEmpName;
    }

    public String getLeaderEmpNumber() {
        return leaderEmpNumber;
    }

    public void setLeaderEmpNumber(String leaderEmpNumber) {
        this.leaderEmpNumber = leaderEmpNumber;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public Date getMappedDate() {
        return mappedDate;
    }

    public void setMappedDate(Date mappedDate) {
        this.mappedDate = mappedDate;
    }

    public String getMapperEmpName() {
        return mapperEmpName;
    }

    public void setMapperEmpName(String mapperEmpName) {
        this.mapperEmpName = mapperEmpName;
    }

    public String getMapperEmpNumber() {
        return mapperEmpNumber;
    }

    public void setMapperEmpNumber(String mapperEmpNumber) {
        this.mapperEmpNumber = mapperEmpNumber;
    }

    public String getMapperId() {
        return mapperId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCustomerCodeName() {
        return customerCodeName;
    }

    public void setCustomerCodeName(String customerCodeName) {
        this.customerCodeName = customerCodeName;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public String getCustomerListCheck() {
        return customerListCheck;
    }

    public void setCustomerListCheck(String customerListCheck) {
        this.customerListCheck = customerListCheck;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getSubRbu() {
        return subRbu;
    }

    public void setSubRbu(String subRbu) {
        this.subRbu = subRbu;
    }

    public String getRbuID() {
        return rbuID;
    }

    public void setRbuID(String rbuID) {
        this.rbuID = rbuID;
    }

    public String getRbuName() {
        return rbuName;
    }

    public void setRbuName(String rbuName) {
        this.rbuName = rbuName;
    }
    
}
