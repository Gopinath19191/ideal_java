/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

/**
 *
 * @author 8000168
 */
public class AnnualAppraisalFilterDTO {
	private String empId;
	private String empName;
	private String key;
	private String employee_search;
	private String employee_id;
    private String sbu;
	private String subSbu;
	private String appraisalYear;
	private String id;
	private String name;
	private int start_page;
	private int end_page;
	private String reviewer_id;
	private String normalizer_id;
	private String reviewer_search;
	private String normalizer_search;

	public String getReviewer_id() {
		return reviewer_id;
	}

	public void setReviewer_id(String reviewer_id) {
		this.reviewer_id = reviewer_id;
	}

	public String getNormalizer_id() {
		return normalizer_id;
	}

	public void setNormalizer_id(String normalizer_id) {
		this.normalizer_id = normalizer_id;
	}

	public String getReviewer_search() {
		return reviewer_search;
	}

	public void setReviewer_search(String reviewer_search) {
		this.reviewer_search = reviewer_search;
	}

	public String getNormalizer_search() {
		return normalizer_search;
	}

	public void setNormalizer_search(String normalizer_search) {
		this.normalizer_search = normalizer_search;
	}

	
	public int getStart_page() {
		return start_page;
	}

	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}

	public int getEnd_page() {
		return end_page;
	}

	public void setEnd_page(int end_page) {
		this.end_page = end_page;
	}
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSbu() {
		return sbu;
	}

	public void setSbu(String sbu) {
		this.sbu = sbu;
	}

	public String getSubSbu() {
		return subSbu;
	}

	public void setSubSbu(String subSbu) {
		this.subSbu = subSbu;
	}
	
	public String getEmployee_search() {
		return employee_search;
	}

	public void setEmployee_search(String employee_search) {
		this.employee_search = employee_search;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAppraisalYear() {
		return appraisalYear;
	}

	public void setAppraisalYear(String appraisalYear) {
		this.appraisalYear = appraisalYear;
	}

	
	
}
