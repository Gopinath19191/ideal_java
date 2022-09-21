/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dao;

import com.ideal.hrMaster.dto.EmployeeCompetencyMappingDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 8000460
 */
public class EmployeeCompetencyMappingDaoImpl extends SqlMapClientDaoSupport implements EmployeeCompetencyMappingDao {

    public EmployeeCompetencyMappingDto getEmployeeDetails(String employee_id) {
        EmployeeCompetencyMappingDto employee_detail = null;
        try {
            employee_detail = (EmployeeCompetencyMappingDto) getSqlMapClientTemplate().queryForObject("EmployeeCompetency.getEmployeeDetails", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee_detail;
    }

    public List<EmployeeCompetencyMappingDto> getUnitList(int parent_id) {
        List<EmployeeCompetencyMappingDto> unit_list = null;
        try {
            unit_list = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getUnitList", parent_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unit_list;
    }

    public List<EmployeeCompetencyMappingDto> getCompetencyList(int sub_sbu_id) {
        List<EmployeeCompetencyMappingDto> competency_list = null;
        try {
            competency_list = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getCompetencyList", sub_sbu_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return competency_list;
    }

    public List<EmployeeCompetencyMappingDto> getSkillList(int competency_id) {
        List<EmployeeCompetencyMappingDto> skill_list = null;
        try {
            skill_list = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getSkillList", competency_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skill_list;
    }

    public String saveCompetency(EmployeeCompetencyMappingDto dto) {
        String lastInsertedId = null;
        try {
            lastInsertedId = (String) getSqlMapClientTemplate().insert("EmployeeCompetency.insertCompetency", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastInsertedId;
    }

    public List<EmployeeCompetencyMappingDto> getProficiencyList(int skill_id) {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getProficiencyList", skill_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public void saveSkill(EmployeeCompetencyMappingDto dto) {
        try {
            getSqlMapClientTemplate().insert("EmployeeCompetency.insertSkill", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EmployeeCompetencyMappingDto> getEmployeeCompetencyMappingDetail(String employee_id) {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getEmployeeCompetencyMappingDetail", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public List<EmployeeCompetencyMappingDto> getEmployeeProficiencyList() {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getEmployeeProficiencyList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public List<EmployeeCompetencyMappingDto> getEmployeeCompetencyList() {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getEmployeeCompetencyList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;

    }

    public void updateStatus(EmployeeCompetencyMappingDto dto) {
        try {
            getSqlMapClientTemplate().insert("EmployeeCompetency.updateStatus", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCompetencySkill(EmployeeCompetencyMappingDto dto) {
        try {
            getSqlMapClientTemplate().insert("EmployeeCompetency.updateCompetencySkill", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EmployeeCompetencyMappingDto> getReporteeSkill(String manager_id) {
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
            dto = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getReporteeSkill", manager_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public EmployeeCompetencyMappingDto getExperience(String employee_id) {
        EmployeeCompetencyMappingDto dto = new EmployeeCompetencyMappingDto();
        String end_date;
        try {
            String experience = (String) getSqlMapClientTemplate().queryForObject("EmployeeCompetency.getEmployeeExperience", employee_id);
            String experience_all = (String) getSqlMapClientTemplate().queryForObject("EmployeeCompetency.getEmployeeExperienceAll", employee_id);
            String emp_joined_date = (String) getSqlMapClientTemplate().queryForObject("EmployeeCompetency.getEmployeeJoinedDate", employee_id);
           
            if (experience_all == null || experience_all =="" ) {
                end_date = emp_joined_date;
            } else {
                end_date = experience;
            }
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dto.setHtl_experience((String)getTotalYears(emp_joined_date, (String) formatter.format(date)));
            dto.setOverall_experience((String)getTotalYears(end_date, (String) formatter.format(date)));
            System.out.println(dto.getHtl_experience());
            System.out.println(dto.getOverall_experience());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public String getTotalYears(String start_date, String end_date) throws ParseException {
        String experience_text="";
        System.out.println("Start Date--->"+start_date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date st_date=formatter.parse(start_date);
        Date ed_date=formatter.parse(end_date);
        int diff_in_days =(int) Math.floor(Math.abs((st_date.getTime() - ed_date.getTime()))/ (1000 * 60 * 60 * 24));
        int no_years=(int)Math.floor(diff_in_days/365);
        int no_remaining_month=(int)Math.floor((diff_in_days - (no_years * 365)) / 30.5);
        if(no_years>1)
            experience_text=no_years+" Years ";
        else
            experience_text=no_years+" Year ";
        if(no_remaining_month>1)
            experience_text+=no_remaining_month+" Months ";
        else
            experience_text+=no_remaining_month+" Month ";
        
        return experience_text;
    }
    public List<EmployeeCompetencyMappingDto> getjobsList() {
       List<EmployeeCompetencyMappingDto> dto = null;
        try {
         dto = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getJobList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public List<EmployeeCompetencyMappingDto> getEmployeeMappingDetail(String employee_id){
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
         dto = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getEmployeeMappingDetail",employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public List<EmployeeCompetencyMappingDto> getJobDetail(String job_id){
        List<EmployeeCompetencyMappingDto> dto = null;
        try {
         dto = getSqlMapClientTemplate().queryForList("EmployeeCompetency.getJobDetail",job_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    
    public String login(String employee_number,String password){
        String message =null;
        try{
           EmployeeCompetencyMappingDto dto= (EmployeeCompetencyMappingDto) getSqlMapClientTemplate().queryForObject("EmployeeCompetency.login", employee_number,password);
           if(dto.getEmployee_id() !=null && dto.getEmployee_id() !=""){
               message="Valid User";
           }else{
               message="Invalid Username And Password";
           }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return message;
    }
}
