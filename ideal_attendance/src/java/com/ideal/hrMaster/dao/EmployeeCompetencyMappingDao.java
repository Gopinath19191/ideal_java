/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dao;

import com.ideal.hrMaster.dto.EmployeeCompetencyMappingDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 8000460
 */
public interface EmployeeCompetencyMappingDao {
    public EmployeeCompetencyMappingDto getEmployeeDetails(String employee_id);
    public List<EmployeeCompetencyMappingDto> getUnitList(int parent_id);
    public List<EmployeeCompetencyMappingDto> getCompetencyList(int sub_sbu_id);
    public List<EmployeeCompetencyMappingDto> getSkillList(int competency_id);
    public String saveCompetency(EmployeeCompetencyMappingDto dto);
    public List<EmployeeCompetencyMappingDto> getProficiencyList(int skill_id);
    public void saveSkill(EmployeeCompetencyMappingDto dto);
    public List<EmployeeCompetencyMappingDto>getEmployeeCompetencyMappingDetail(String employee_id);
    public List<EmployeeCompetencyMappingDto>getEmployeeProficiencyList();
    public List<EmployeeCompetencyMappingDto>getEmployeeCompetencyList();
    public void updateStatus(EmployeeCompetencyMappingDto dto);
    public void updateCompetencySkill(EmployeeCompetencyMappingDto dto);
    public List<EmployeeCompetencyMappingDto>getReporteeSkill(String manager_id);
    public EmployeeCompetencyMappingDto getExperience(String employee_id);
    public List<EmployeeCompetencyMappingDto> getjobsList();
    public List<EmployeeCompetencyMappingDto> getEmployeeMappingDetail(String employee_id);
    public List<EmployeeCompetencyMappingDto> getJobDetail(String job_id);
    public String login(String employee_number,String password);
    
    
}
