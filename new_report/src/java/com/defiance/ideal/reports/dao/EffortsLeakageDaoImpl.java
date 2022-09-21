/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.EffortsLeakageDataDTO;
import com.defiance.ideal.reports.dto.SearchDTO;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16221
 */
public class EffortsLeakageDaoImpl extends SqlMapClientDaoSupport implements EffortsLeakageDao{
    public List<EffortsLeakageDataDTO> getData(EffortsLeakageDataDTO filterData){
        List<EffortsLeakageDataDTO> dataList = null;
        try{
            dataList = getSqlMapClientTemplate().queryForList("EffortsLeakage.getData", filterData);
        }catch(Exception e){
        
        }
        return dataList;
    }
    public List<EffortsLeakageDataDTO>getProjectUnbilledDetails(EffortsLeakageDataDTO filterData){
        List<EffortsLeakageDataDTO> dataList = null;
        try{
            dataList = getSqlMapClientTemplate().queryForList("EffortsLeakage.getprojectUnbilledDetails", filterData);
        }catch(Exception e){
        
        }
        return dataList;
    }
    public List<EffortsLeakageDataDTO>getRevenueLeakageReport(SearchDTO filterData){
        List<EffortsLeakageDataDTO> dataList = null;
        try{
            dataList = getSqlMapClientTemplate().queryForList("EffortsLeakage.getRevenueLeakageReport", filterData);
        }catch(Exception e){
        }
        return dataList;
    }
    public List<SearchDTO> getCustomerList(){
        List<SearchDTO> dataList = null;
        try{
            dataList = getSqlMapClientTemplate().queryForList("EffortsLeakage.getCustomerList");
        }catch(Exception e){
        }
        return dataList;
    }
    public String getParent_id(){
        String dataList = null;
        try{
            dataList = (String)getSqlMapClientTemplate().queryForObject("EffortsLeakage.getParent_id");
        }catch(Exception e){
        }
        return dataList;
    }
    public List<SearchDTO> getUnit(){
        List<SearchDTO> dataList = null;
        try{
            dataList = getSqlMapClientTemplate().queryForList("EffortsLeakage.getUnit");
        }catch(Exception e){
        }
        return dataList;
    }
    public List<SearchDTO> getSubUnit(String parent_id){
        List<SearchDTO> dataList = null;
        try{
            dataList = getSqlMapClientTemplate().queryForList("EffortsLeakage.getSubUnit", parent_id);
        }catch(Exception e){
        }
        return dataList;
    }
}
