/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.TeamAllocationReportDTO;
import com.defiance.ideal.reports.util.CommonConfigurations;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 15332
 */
public class TeamAllocationReportDaoImpl extends SqlMapClientDaoSupport implements TeamAllocationReportDao {
    
    public List<TeamAllocationReportDTO> getRollOutreport(TeamAllocationReportDTO formData) {
        List<TeamAllocationReportDTO> teamAllocationReportlist = null;
        try {
            teamAllocationReportlist = getSqlMapClientTemplate().queryForList("TeamAllocationReportMap.teamAllocationReportList",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamAllocationReportlist;
    }

    public List<TeamAllocationReportDTO> getprojectList(TeamAllocationReportDTO formData) {
        List<TeamAllocationReportDTO> projectList = null;
        try {
            projectList = getSqlMapClientTemplate().queryForList("TeamAllocationReportMap.getprojectList",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
     public List<TeamAllocationReportDTO> getcustomerList(TeamAllocationReportDTO formData) {
        List<TeamAllocationReportDTO> customerList = null;
        try {
            customerList = getSqlMapClientTemplate().queryForList("TeamAllocationReportMap.getcustomerList",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }
    public List<TeamAllocationReportDTO> getSbuList(TeamAllocationReportDTO formData){
        List<TeamAllocationReportDTO> sbuList = null;
        try{
            //String sbuId = CommonConfigurations.SBU;
            //System.out.println("sbu id from teamallocationreport:::"+ sbuId);
            sbuList = getSqlMapClientTemplate().queryForList("TeamAllocationReportMap.getSbuList", formData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return sbuList;
    }
    public List<TeamAllocationReportDTO> getPmNameList(){
        List<TeamAllocationReportDTO> pmList = null;
        try{
            pmList = getSqlMapClientTemplate().queryForList("TeamAllocationReportMap.getPmNameList");
        }catch(Exception e){
            e.printStackTrace();
        }
        return pmList;
    }
    public List<TeamAllocationReportDTO> getsubSbuList(String sbuId){
        List<TeamAllocationReportDTO> subSbulist = null;
        try{
            System.out.println("sbu Id === "+sbuId);
            subSbulist = getSqlMapClientTemplate().queryForList("TeamAllocationReportMap.getSubSbuList",sbuId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return subSbulist;
    }
    
}
