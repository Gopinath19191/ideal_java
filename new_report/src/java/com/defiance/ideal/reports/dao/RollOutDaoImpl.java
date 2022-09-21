/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.RollOutDTO;
import com.defiance.ideal.reports.util.CommonConfigurations;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 15261
 */
public class RollOutDaoImpl extends SqlMapClientDaoSupport implements RollOutDao {
   
    public List<RollOutDTO> getRollOutreport(RollOutDTO formData) {
        List<RollOutDTO> rollOutlist = null;

        try {
            rollOutlist = getSqlMapClientTemplate().queryForList("RollOutMap.rollOutList",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rollOutlist;
    }

    public List<RollOutDTO> getprojectList() {
        List<RollOutDTO> projectList = null;
        try {
            projectList = getSqlMapClientTemplate().queryForList("RollOutMap.getprojectList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public List<RollOutDTO> getSbuList(RollOutDTO sbuId){
        List<RollOutDTO> sbuList = null;
        try{
            //String sbuId = CommonConfigurations.SBU;
            System.out.println("sbu id from rollout:::"+ sbuId);
            sbuList = getSqlMapClientTemplate().queryForList("RollOutMap.getSbuList",sbuId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return sbuList;
    }
    public List<RollOutDTO> getPmNameList(){
        List<RollOutDTO> pmList = null;
        try{
            pmList = getSqlMapClientTemplate().queryForList("RollOutMap.getPmNameList");
        }catch(Exception e){
            e.printStackTrace();
        }
        return pmList;
    }
    public List<RollOutDTO> getsubSbuList(String sbuId){
        List<RollOutDTO> subSbulist = null;
        try{
            System.out.println("sbu Id === "+sbuId);
            subSbulist = getSqlMapClientTemplate().queryForList("RollOutMap.getSubSbuList",sbuId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return subSbulist;
    }
}
