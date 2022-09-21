/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.ProjectCompletionDataDTO;
import com.defiance.ideal.reports.dto.ProjectCompletionFilterDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14517
 */
public class ProjectCompletionDaoImpl extends SqlMapClientDaoSupport implements ProjectCompletionDao{
     private String sbuId;

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }
      public List<ProjectCompletionDataDTO> fetchProjectCompletionReport(ProjectCompletionFilterDTO filterData){
          //System.out.println("Inside Dao");
             List<ProjectCompletionDataDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("ProjectCompletionMap.getCompletionList",filterData);
          //  System.out.println("template size"+template.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }
      public Map<String, String> getSbuList(){
           Map<String, String> sbuList = new LinkedHashMap<String, String>();
        try {
            sbuList = getSqlMapClientTemplate().queryForMap("ProjectCompletionMap.getSbuList",sbuId,"sbuId", "sbuName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbuList;
      }

}
