/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.pc.ticket.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 16364
 */
public class PcDataDTO {
    private Integer countryId;
    private String country;//
    private String regionId;
    public String region;
    private Integer cityId;
    public String city;
    private Integer practiceGroup;
 private Integer subPracticeGroup;
 public Integer id;
public String name;
public String projectName;
public Integer pid;
private String empName;
private String firstname;
private String lastname;
private String consultant_empNumber;
private String consultantMail;
private String workLocationName;
private String workLocationId;
 private MultipartFile file;
private String attachment_file_path;
private String fullFileName;
private String shortcode;

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }


    public String getFullFileName() {
        return fullFileName;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }


    public String getAttachment_file_path() {
        return attachment_file_path;
    }

    public void setAttachment_file_path(String attachment_file_path) {
        this.attachment_file_path = attachment_file_path;
    }


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
 
 
    public String getWorkLocationName() {
        return workLocationName;
    }

    public void setWorkLocationName(String workLocationName) {
        this.workLocationName = workLocationName;
    }

    public String getWorkLocationId() {
        return workLocationId;
    }

    public void setWorkLocationId(String workLocationId) {
        this.workLocationId = workLocationId;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    public String getConsultantMail() {
        return consultantMail;
    }

    public void setConsultantMail(String consultantMail) {
        this.consultantMail = consultantMail;
    }


    public String getConsultant_empNumber() {
        return consultant_empNumber;
    }

    public void setConsultant_empNumber(String consultant_empNumber) {
        this.consultant_empNumber = consultant_empNumber;
    }


    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPracticeGroupName() {
        return practiceGroupName;
    }

    public void setPracticeGroupName(String practiceGroupName) {
        this.practiceGroupName = practiceGroupName;
    }

    public String getSubPracticeGroupName() {
        return subPracticeGroupName;
    }

    public void setSubPracticeGroupName(String subPracticeGroupName) {
        this.subPracticeGroupName = subPracticeGroupName;
    }



    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }



    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


private int start_page;
    private int end_page;
    private int page;
    private int recordCount;
   
   
    private String employee_idd;
    private String mailId;
private String consultantName;
private String company;
private String address;
private String contactname;
private String relationship;
private String mobile;
private String pmobile;
private String aadhar;
private String pan;
private String startdate;
private String enddate;
private String fstartdate;
private String fenddate;
private String mail;
private String pc_code;
private String consultant_empid;
private String rmId;
private String pcId;
private String created_date;
private String projectId;
private String countryName;
public String regionName;
public String cityName;
private String rmName;
private String practiceGroupName;
private String subPracticeGroupName;
private String empNumber;

    public String getFstartdate() {
        return fstartdate;
    }

    public void setFstartdate(String fstartdate) {
        this.fstartdate = fstartdate;
    }

    public String getFenddate() {
        return fenddate;
    }

    public void setFenddate(String fenddate) {
        this.fenddate = fenddate;
    }


    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }


    public String getPmobile() {
        return pmobile;
    }

    public void setPmobile(String pmobile) {
        this.pmobile = pmobile;
    }


    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }


    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getRmId() {
        return rmId;
    }

    public void setRmId(String rmId) {
        this.rmId = rmId;
    }

    
   
   


    public String getConsultant_empid() {
        return consultant_empid;
    }

    public void setConsultant_empid(String consultant_empid) {
        this.consultant_empid = consultant_empid;
    }


    public String getPc_code() {
        return pc_code;
    }

    public void setPc_code(String pc_code) {
        this.pc_code = pc_code;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public String getEmployee_idd() {
        return employee_idd;
    }

    public void setEmployee_idd(String employee_idd) {
        this.employee_idd = employee_idd;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
 
 

    public Integer getPracticeGroup() {
        return practiceGroup;
    }

    public void setPracticeGroup(Integer practiceGroup) {
        this.practiceGroup = practiceGroup;
    }

    public Integer getSubPracticeGroup() {
        return subPracticeGroup;
    }

    public void setSubPracticeGroup(Integer subPracticeGroup) {
        this.subPracticeGroup = subPracticeGroup;
    }
 
 
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
}
