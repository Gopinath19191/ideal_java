/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.PurchaseOrderReportDetails;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


/**
 * 
 * @author 15261
 */
public class PurchaseOrderReportDaoImpl extends SqlMapClientDaoSupport
        implements PurchaseOrderReportDao {

    public List<PurchaseOrderReportDetails> getPurchaseOrderReportDetails
            (PurchaseOrderReportDetails filterData) {
        List<PurchaseOrderReportDetails> poReportData = null;
        try {
            poReportData = getSqlMapClientTemplate().queryForList(
                    "ProjectReportMap.getPurchaseOrderReportDetails", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return poReportData;
    }

    public int getPurchaseOrderReportDetailsCount(PurchaseOrderReportDetails dto) {
        int recordCount = 0;
        try{
            recordCount = (Integer)getSqlMapClientTemplate().queryForObject("ProjectReportMap.getPurchaseOrderReportDetailsCount", dto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return recordCount;
    }
}
