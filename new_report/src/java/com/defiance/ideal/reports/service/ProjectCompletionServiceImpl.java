/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.ProjectCompletionDao;
import com.defiance.ideal.reports.dto.ProjectCompletionDataDTO;
import com.defiance.ideal.reports.dto.ProjectCompletionFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public class ProjectCompletionServiceImpl implements ProjectCompletionService{
    public ProjectCompletionDao projectCompletionDao;

    public ProjectCompletionDao getProjectCompletionDao() {
        return projectCompletionDao;
    }

    public void setProjectCompletionDao(ProjectCompletionDao projectCompletionDao) {
        this.projectCompletionDao = projectCompletionDao;
    }
    public Map<String, String> getSbuList() {
        return projectCompletionDao.getSbuList();
    }
   public List<ProjectCompletionDataDTO> fetchProjectCompletionList(ProjectCompletionFilterDTO filterData){
       List<ProjectCompletionDataDTO> projectList=projectCompletionDao.fetchProjectCompletionReport(filterData);
       System.out.println("Inside service size"+projectList.size());
       return projectList;
       
   }

}
