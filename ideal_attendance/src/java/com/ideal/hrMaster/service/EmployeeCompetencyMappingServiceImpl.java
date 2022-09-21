/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.service;

import com.ideal.hrMaster.dao.EmployeeCompetencyMappingDao;
import com.ideal.hrMaster.dto.EmployeeCompetencyMappingDto;
import java.util.List;

/**
 *
 * @author 8000460
 */
public class EmployeeCompetencyMappingServiceImpl implements EmployeeCompetencyMappingService {

    EmployeeCompetencyMappingDao dao;

    public EmployeeCompetencyMappingDao getDao() {
        return dao;
    }

    public void setDao(EmployeeCompetencyMappingDao dao) {
        this.dao = dao;
    }

    public EmployeeCompetencyMappingDto getEmployeeDetails(String employee_id) {
        EmployeeCompetencyMappingDto employee_details = null;
        try {
            employee_details = dao.getEmployeeDetails(employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee_details;
    }

    public List<EmployeeCompetencyMappingDto> getUnitList(int parent_id) {
        List<EmployeeCompetencyMappingDto> unit_detail = null;
        try {
            unit_detail = dao.getUnitList(parent_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unit_detail;
    }

    public List<EmployeeCompetencyMappingDto> getCompetencyList(int sub_sbu_id) {
        List<EmployeeCompetencyMappingDto> competency_detail = null;
        try {
            competency_detail = dao.getCompetencyList(sub_sbu_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return competency_detail;
    }

    public List<EmployeeCompetencyMappingDto> getSkillList(int competency_id) {
        List<EmployeeCompetencyMappingDto> skill_detail = null;
        try {
            skill_detail = dao.getSkillList(competency_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skill_detail;
    }

    public String saveCompetency(EmployeeCompetencyMappingDto dto) {
        String lastInsertedId = null;
        try {
            lastInsertedId = (String) dao.saveCompetency(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastInsertedId;
    }

    public List<EmployeeCompetencyMappingDto> getProficiencyList(int skill_id) {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = dao.getProficiencyList(skill_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public void saveSkill(EmployeeCompetencyMappingDto dto) {
        try {
            dao.saveSkill(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EmployeeCompetencyMappingDto> getEmployeeCompetencyMappingDetail(String employee_id) {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = dao.getEmployeeCompetencyMappingDetail(employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }

    public List<EmployeeCompetencyMappingDto> getEmployeeProficiencyList() {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = dao.getEmployeeProficiencyList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }

    public List<EmployeeCompetencyMappingDto> getEmployeeCompetencyList() {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = dao.getEmployeeCompetencyList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }

    public void updateStatus(EmployeeCompetencyMappingDto dto) {
        try {
            dao.updateStatus(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCompetencySkill(EmployeeCompetencyMappingDto dto) {
        try {
            dao.updateCompetencySkill(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EmployeeCompetencyMappingDto> getReporteeSkill(String manager_id) {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = dao.getReporteeSkill(manager_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }

    public EmployeeCompetencyMappingDto getExperience(String employee_id) {
        EmployeeCompetencyMappingDto dto = null;
        try {
            dto = dao.getExperience(employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public List<EmployeeCompetencyMappingDto> getjobsList() {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = dao.getjobsList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public List<EmployeeCompetencyMappingDto> getEmployeeMappingDetail(String employee_id) {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = dao.getEmployeeMappingDetail(employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public List<EmployeeCompetencyMappingDto> getJobDetail(String job_id){
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = dao.getJobDetail(job_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    
    public String login(String employee_number,String password){
        String message=null;
        try{
            message=dao.login(employee_number,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return message;
    }
}
