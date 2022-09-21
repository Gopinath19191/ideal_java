/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.admin.db;

import com.defiance.ideal.qpd.admin.dto.AdminDTO;
import com.defiance.ideal.shared.DBConnectivity;
import org.apache.beehive.controls.api.bean.ControlExtension;
import org.apache.beehive.controls.system.jdbc.JdbcControl.SQL;

/**
 *
 * @author HARIHARAN.C
 */
@ControlExtension
public interface AdminDBCTL extends DBConnectivity{

 @SQL(statement="select id as userAccountId,username,password,group_id as groupId,employee_id as empId from users where username={userName} AND password={userPassword}")
 public AdminDTO AuthenticateUser(String userName,String userPassword) throws Exception;

 @SQL(statement="SELECT name AS structureName,id as csId FROM company_structures WHERE parent_id!=0")
 public AdminDTO[] getStructureDetails() throws Exception;

 @SQL(statement="SELECT ea.qpdId,emp.id as appraiseeId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName," +
 "emp.employee_number as appraiseeNumber,DATE_FORMAT(emp.joined_date,'%d-%m-%Y') as dateOfJoin,subbnd.id As bandId,subbnd.name as bandName,cs.name as departmentName," +
 "emp.structure_name as depId,appr.id as appraiserId,appr.employee_number as appraiserNumber,CONCAT(appr.first_name,' ',appr.last_name) " +
 " as appraiserName,rev.id as reviewerId,rev.employee_number as reviewerNumber,CONCAT(rev.first_name,' ',rev.last_name) as reviewerName,norm.id as normalizerId,norm.employee_number as normalizerNumber,CONCAT(norm.first_name,' ',norm.last_name) as normalizerName, " +
 " ea.submitStatus FROM employees as emp LEFT JOIN employees as appr ON (emp.manager=appr.id) " +
 " LEFT JOIN employees as rev ON (appr.manager=rev.id) " +
 " LEFT JOIN employees as norm ON (rev.manager=norm.id) " +
 " LEFT JOIN aa_eligible_associates as ea ON (emp.id = ea.appraiseeId AND ea.year={appraisalYear}) " +
 " LEFT JOIN bands as subbnd ON (subbnd.id = emp.band_id) " +
 " LEFT JOIN company_structures as cs ON (emp.structure_name = cs.id) WHERE ea.submitStatus IS NULL " +
 " {sql: dojCheck} {sql: whereCondition} " +
 " UNION SELECT ea.qpdId,emp.id as appraiseeId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.employee_number as appraiseeNumber,DATE_FORMAT(emp.joined_date,'%d-%m-%Y') as dateOfJoin,bnd.id As bandId,bnd.name as bandName,cs.name as departmentName,emp.structure_name as depId,appr.id as appraiserId,appr.employee_number as appraiserNumber,CONCAT(appr.first_name,' ',appr.last_name) as appraiserName,rev.id as reviewerId,rev.employee_number as reviewerNumber,CONCAT(rev.first_name,' ',rev.last_name) as reviewerName,norm.id as normalizerId,norm.employee_number as normalizerNumber,CONCAT(norm.first_name,' ',norm.last_name) as normalizerName,ea.submitStatus FROM aa_eligible_associates as ea LEFT JOIN employees as emp ON (emp.id = ea.appraiseeId AND ea.year={appraisalYear}) LEFT JOIN employees as appr ON (ea.appraiserId=appr.id) LEFT JOIN employees as rev ON (ea.reviewerId=rev.id) LEFT JOIN employees as norm ON (ea.normalizerId=norm.id) LEFT JOIN bands as bnd ON (bnd.id = ea.bandId) LEFT JOIN company_structures as cs ON (ea.departmentId = cs.id) WHERE ea.appraisalTriggered = 0 {sql: dojCheck} {sql: whereCondition2} ")
 public AdminDTO[] filterEmployeeData(String dojCheck,int appraisalYear,String whereCondition,String whereCondition2);

 @SQL(statement="Insert INTO aa_eligible_associates(qpdId,quarter,year,bandId,departmentId,appraiseeId,appraiserId,reviewerId,normalizerId,hrId,financeId,submitStatus) VALUES({qpdId},{quarter},{year},{bandId},{depId},{appraiseeId},{appraiserId},{reviewerId},{normalizerId},{hrId},{financeId},{submitStatus})" +
 " ON DUPLICATE KEY UPDATE qpdId={qpdId},quarter={quarter},year={year},bandId={bandId},departmentId={depId},appraiseeId={appraiseeId},appraiserId={appraiserId},reviewerId={reviewerId},normalizerId={normalizerId},hrId={hrId},financeId={financeId},submitStatus={submitStatus}")
 public void triggerAppraisal(int quarter,int year,int bandId,int depId,int appraiseeId,int appraiserId,int reviewerId,int normalizerId,int hrId,int financeId,int submitStatus,String qpdId) throws Exception;

 @SQL(statement="Insert INTO aa_eligible_associates(qpdId,quarter,year,bandId,departmentId,appraiseeId,appraiserId,reviewerId,normalizerId,hrId,financeId,submitStatus,appraisalTriggered) VALUES({qpdId},{quarter},{year},{bandId},{depId},{appraiseeId},{appraiserId},{reviewerId},{normalizerId},{hrId},{financeId},{submitStatus},{triggerStatus})" +
 " ON DUPLICATE KEY UPDATE qpdId={qpdId},quarter={quarter},year={year},bandId={bandId},departmentId={depId},appraiseeId={appraiseeId},appraiserId={appraiserId},reviewerId={reviewerId},normalizerId={normalizerId},hrId={hrId},financeId={financeId},submitStatus={submitStatus},appraisalTriggered={triggerStatus}")
 public void triggerAppraisal(int quarter,int year,int bandId,int depId,int appraiseeId,int appraiserId,int reviewerId,int normalizerId,int hrId,int financeId,int submitStatus,String qpdId,int triggerStatus) throws Exception;

 @SQL(statement="SELECT id as empIdAc,employee_number As employeeNumberAc,CONCAT(first_name,' ',last_name) as employeeNameAc FROM employees WHERE first_name LIKE \"%{sql: searchString}%\" OR last_name LIKE \"%{sql: searchString}%\" OR employee_number LIKE \"%{sql: searchString}%\"")
 public AdminDTO[] getEmployeeName(String searchString) throws Exception;

 @SQL(statement="SELECT qpdId,appraiseeId,DATE_FORMAT(emp.joined_date,'%d-%m-%Y') as dateOfJoin,bnd.id as bandId,bnd.name as bandName,ea.departmentId as depId,cs.name as departmentName,appraiserId,reviewerId,normalizerId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.employee_number as appraiseeNumber,appr.employee_number as appraiserNumber,CONCAT(appr.first_name,' ',appr.last_name) as appraiserName,rev.employee_number as reviewerNumber,CONCAT(rev.first_name,' ',rev.last_name) as reviewerName,norm.employee_number as normalizerNumber,CONCAT(norm.first_name,' ',norm.last_name) as normalizerName,ea.submitStatus " +
 " FROM aa_eligible_associates as ea " +
 " LEFT JOIN employees as emp ON (emp.id = ea.appraiseeId) " +
 " LEFT JOIN employees as appr ON (ea.appraiserId=appr.id) " +
 " LEFT JOIN employees as rev ON (ea.reviewerId=rev.id) " +
 " LEFT JOIN employees as norm ON (ea.normalizerId=norm.id) " +
 " LEFT JOIN bands as bnd ON (ea.bandId = bnd.id) " +
 " LEFT JOIN company_structures as cs ON (ea.departmentId = cs.id) " +
 " WHERE ea.year={appraisalYear} " +
 " {sql: dojCheck} AND ea.appraisalTriggered = {aprraisalTriggerStatus} {sql: whereCondition} ORDER BY emp.employee_number")
 public AdminDTO[] filterEmployeeDataChangeRequest(String dojCheck, int appraisalYear,int aprraisalTriggerStatus,String whereCondition);

 
 @SQL(statement="UPDATE aa_eligible_associates set quarter={quarter},year={year},bandId={bandId},departmentId={depId},appraiseeId={appraiseeId},appraiserId={appraiserId},reviewerId={reviewerId},normalizerId={normalizerId},hrId={hrId},financeId={financeId},submitStatus={submitStatus} WHERE qpdId={qpdId}")
 public void updateAppraisal(int quarter,int year,int bandId,int depId,int appraiseeId,int appraiserId,int reviewerId,int normalizerId,int hrId,int financeId,int submitStatus,int qpdId);

 /* Submit Status Reset Query removed for assoicate data updation - changed on request from Varun by Hariharan.C */
 @SQL(statement="UPDATE aa_eligible_associates set quarter={quarter},year={year},bandId={bandId},departmentId={depId},appraiseeId={appraiseeId},appraiserId={appraiserId},reviewerId={reviewerId},normalizerId={normalizerId},hrId={hrId},financeId={financeId} WHERE qpdId={qpdId}")
 public void updateAppraisal(int quarter,int year,int bandId,int depId,int appraiseeId,int appraiserId,int reviewerId,int normalizerId,int hrId,int financeId,int qpdId);

 @SQL(statement="Select name as bandName From bands where id = {bandId}")
 public AdminDTO getBandDetails(int bandId);

 @SQL(statement="Select name as departmentName From company_structures where id = {depId}")
 public AdminDTO getDepartmentDetails(int depId);

 @SQL(statement="Select name as masterBandName,id as masterBandId from bands where parent_id IS NULL")
 public AdminDTO[] getBandData();


// @SQL(statement="SELECT distinct(CONCAT(appraiser.first_name,' ',appraiser.last_name)) as appraiserName,appraiser.id as appraiserId " +
// "FROM employees AS emp LEFT JOIN employees as appraiser ON emp.manager = appraiser.id LEFT JOIN employees as reviewer " +
//         "ON appraiser.manager = reviewer.id LEFT JOIN employees as finalReviewer ON reviewer.manager = finalReviewer.id " +
//         "where appraiser.first_name is NOT NULL order by appraiser.first_name ASC")
 
 @SQL(statement="SELECT distinct(CONCAT(appraiser.first_name,' ',appraiser.last_name)) as appraiserName,appraiser.id as appraiserId " +
 "FROM employees AS emp LEFT JOIN employees as appraiser ON emp.manager = appraiser.id LEFT JOIN employees as reviewer " +
         "ON appraiser.manager = reviewer.id LEFT JOIN employees as finalReviewer ON reviewer.manager = finalReviewer.id " +
         "where appraiser.first_name is NOT NULL GROUP BY appraiser.id ORDER BY appraiser.first_name ASC")
 public AdminDTO[] getAppraiserDataBeforeTrigger();

// @SQL(statement="SELECT distinct(CONCAT(appraiser.first_name,' ',appraiser.last_name)) as appraiserName,appraiser.id as appraiserId " +
// "FROM aa_eligible_associates as qea LEFT JOIN employees as emp ON(qea.appraiseeId = emp.id) LEFT JOIN employees as appraiser " +
//         "ON(qea.appraiserId=appraiser.id) LEFT JOIN employees as reviewer ON(qea.reviewerId=reviewer.id) LEFT JOIN employees as normaliser " +
//         "ON(qea.normalizerId=normaliser.id) LEFT JOIN employees hr ON(qea.hrId=hr.id) LEFT JOIN employees finance ON(qea.financeid=finance.id)" +
//         " where appraiser.first_name is NOT null order by appraiser.first_name ASC")
 
 @SQL(statement="SELECT distinct(CONCAT(appraiser.first_name,' ',appraiser.last_name)) as appraiserName,appraiser.id as appraiserId " +
 "FROM aa_eligible_associates as qea LEFT JOIN employees as emp ON(qea.appraiseeId = emp.id) " +
 "LEFT JOIN employees as appraiser ON(qea.appraiserId=appraiser.id) WHERE appraiser.first_name is NOT null GROUP BY appraiser.id ORDER by appraiser.first_name ASC")
 public AdminDTO[] getAppraiserDataAfterTrigger();
 

}
