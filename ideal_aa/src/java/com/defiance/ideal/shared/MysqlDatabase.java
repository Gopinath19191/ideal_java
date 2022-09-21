/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.shared;

import com.defiance.ideal.login.dto.LoginFormDTO;
import com.defiance.ideal.qpd.managers.dto.ManagerDTO;
import org.apache.beehive.controls.api.bean.ControlExtension;

/**
 *
 * @author Admin
 */
@ControlExtension
public interface MysqlDatabase extends DBConnectivity {

    @SQL(statement = "select id as userAccountId,username,password,group_id as groupId,employee_id as empId from users where username={userName} AND password={userPassword}")
    public LoginFormDTO AuthenticateUser(String userName, String userPassword) throws Exception;

    @SQL(statement = "SELECT _create as uCreate,_read as uRead,_update as uUpdate,_delete as uDelete  FROM aros_acos as arcos,aros as aro WHERE arcos.aro_id = aro.id AND aro.model LIKE '%USER%' "
    + "AND aro.foreign_key = {empId} AND arcos.aco_id = {moduleId}")
    public LoginFormDTO authenticateUser(String empId, String moduleId);

    @SQL(statement = "SELECT _create as gCreate,_read as gRead,_update as gUpdate,_delete as gDelete  FROM aros_acos as arcos,aros as aro WHERE arcos.aro_id = aro.id AND aro.model LIKE '%GROUP%' "
    + "AND aro.foreign_key = {groupId} AND arcos.aco_id = {moduleId}")
    public LoginFormDTO authenticateGroup(String groupId, String moduleId);

    @SQL(statement = "SELECT emp.employee_number as employeeCode,emp.first_name as firstName,emp.last_name as lastName,emp.middle_name as middleName,emp.work_email_address as emailId,emp.appraiser,appraiser.work_email_address as appraiserEmailId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserNameMail,reviewer.work_email_address as reviewerEmailId,CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerNameMail,"
    + "normaliser.work_email_address as normaliserEmailId,CONCAT(normaliser.first_name,' ',normaliser.last_name) as normaliserNameMail,hr.work_email_address as hrEmailId,CONCAT(hr.first_name,' ',hr.last_name) as hrNameMail,finance.work_email_address as financeEmailId,CONCAT(finance.first_name,' ',finance.last_name) as financeNameMail"
    + " FROM employees emp LEFT JOIN aa_eligible_associates as qea ON(qea.appraiseeId = emp.id) LEFT JOIN employees appraiser ON(qea.appraiserId=appraiser.id) LEFT JOIN employees reviewer ON(qea.reviewerId=reviewer.id)LEFT JOIN employees normaliser ON(qea.normalizerId=normaliser.id) LEFT JOIN employees hr ON(qea.hrId=hr.id) LEFT JOIN employees finance ON(qea.financeid=finance.id) Where appraiseeId = {employeeId} and year = {year} ")
    public SendMailDTO getEmployeeEmailFromId(int employeeId, int year) throws Exception;

    @SQL(statement = "SELECT appraiserId,reviewerId,normalizerId FROM aa_eligible_associates WHERE appraiseeId={appraiseeId} AND year={appraisalYear}")
    public ManagerDTO getAppraiserReviewerId(int appraiseeId, int appraisalYear) throws Exception;

    @SQL(statement = "SELECT emp.employee_number as employeeCode,emp.first_name as firstName,emp.last_name as lastName,emp.middle_name as middleName,emp.work_email_address as emailId FROM employees emp Where id = {employeeId} ")
    public SendMailDTO getHrEmailFromId(int employeeId, int year) throws Exception;

//    @SQL(statement="SELECT emp.employee_number as employeeCode,emp.first_name as firstName,emp.last_name as lastName,emp.middle_name as middleName,emp.work_email_address as emailId,emp.appraiser,appraiser.work_email_address as appraiserEmailId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserNameMail,reviewer.work_email_address as reviewerEmailId,CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerNameMail," +
//    "normaliser.work_email_address as normaliserEmailId,CONCAT(normaliser.first_name,' ',normaliser.last_name) as normaliserNameMail,hr.work_email_address as hrEmailId,CONCAT(hr.first_name,' ',hr.last_name) as hrNameMail,finance.work_email_address as financeEmailId,CONCAT(finance.first_name,' ',finance.last_name) as financeNameMail" +
//    " FROM employees emp LEFT JOIN qpd_eligible_associates as qea ON(qea.appraiseeId = emp.id) LEFT JOIN employees appraiser ON(qea.appraiserId=appraiser.id) LEFT JOIN employees reviewer ON(qea.reviewerId=reviewer.id)LEFT JOIN employees normaliser ON(qea.normalizerId=normaliser.id) LEFT JOIN employees hr ON(qea.hrId=hr.id) LEFT JOIN employees finance ON(qea.financeid=finance.id) Where appraiseeId = {employeeId} ")
//    public SendMailDTO getEmployeeEmailFromIdQPDTable(int employeeId) throws Exception;
    @SQL(statement = "INSERT into file_uploads(file_name,file_type,reference_name,ref_id,module_name) values ({fileName},{contentType},{referenceName},{jfId},{moduleName})")
    public void addFileDb(String fileName, String contentType, String referenceName, int jfId, String moduleName);

    @SQL(statement = "SELECT id as fileId,file_name as fileName,file_type as fileType,reference_name as referenceName,ref_id as referenceId,module_name as moduleName from file_uploads WHERE ref_id={referenceId} AND module_name={moduleName}")
    public SendMailDTO[] getFilesList(int referenceId, String moduleName);

    @SQL(statement = "SELECT id as fileId,file_name as fileName,file_type as fileType,reference_name as referenceName,ref_id as referenceId,module_name as moduleName from file_uploads as fu LEFT JOIN aa_eligible_associates as qea ON (qea.qpdId = fu.ref_id) WHERE fu.ref_id={referenceId} AND fu.module_name={moduleName} AND qea.year = {appraisalYear} ")
    public SendMailDTO[] getFilesList(int referenceId, String moduleName, int appraisalYear);

    @SQL(statement = "SELECT MAX(year) as appraisalMaxYear FROM aa_eligible_associates")
    public SendMailDTO getLastAppraisalYear();

    public SendMailDTO getLastAppraisalQuarter();

    @SQL(statement = "DELETE FROM file_uploads WHERE id={fileId}")
    public void removeFileDb(int fileId);

    @SQL(statement = "SELECT count({sql: referenceName}) as userCount from aa_eligible_associates where year={currentYear} AND {sql: referenceName} = {employeeId} AND appraisalTriggered={triggeredStatus}")
    public LoginFormDTO menuAuthentication(String referenceName, int currentYear, int employeeId, int triggeredStatus);

    @SQL(statement = "Select work_email_address as emailId,CONCAT(first_name,' ',last_name) AS fullName FROM employees  where id={employeeId}")
    public SendMailDTO getEmailFromId(int employeeId) throws Exception;

    @SQL(statement = "Update aa_eligible_associates SET managerSurvey = {surveyStatus} WHERE year={currentYear} AND appraiseeId = {employeeId}")
    public void updateManagerSurvey(int currentYear, int employeeId, int surveyStatus);

    @SQL(statement = "Select managerSurvey from aa_eligible_associates WHERE  year={currentYear} AND appraiseeId = {employeeId}")
    public LoginFormDTO managerSurvey(int currentYear, int employeeId);

    @SQL(statement = "Select username FROM users WHERE password={userToken} AND employee_id={empId}")
    public String userNameString(String userToken, String empId);
}
