package com.mycompany.employee.controllers;

import com.google.gson.Gson;
import com.mycompany.employee.dao.EmployeeDao;
import com.mycompany.employee.dto.*;
import com.mycompany.employee.entitiy.ConfigurationValuesEntity;
import com.mycompany.employee.entitiy.NewsCategories;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 16363
 */
@Path("/employee")
public class EmployeeController extends MultiActionController {

	private static final String SUCCESS = "success";
	private static final String ERROR_CODE = "err_code";
	private static final String ERROR_MESSAGE = "err_message";
	private static final String INVALID_USERNAME = "Invalid Username";
	private static final String TOKEN_INVALID = "Token Invalid";
	private static final String SESSION_EXPIRED = "Session Expired";
	private static final String RESPONSE = "response";
	private static final String NEW_UPDATE_MODULE = "News_and_update";
	private static final String METHOD_TYPE_POST = "POST";

	private Gson gson = new Gson();
	private JSONParser parser = new JSONParser();
	@Autowired
	private static final Logger log = Logger.getLogger(EmployeeController.class.getName());

	@Path("/valid")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getLoginDetails(@Context ServletContext config, String details) throws Exception {

		HashMap<String, Object> result = new HashMap<>();
		List<EmployeeDto> deviceList;
		JSONObject inputJson;
		inputJson = (JSONObject) parser.parse(details);
		String userName = inputJson.get("id").toString();
		String password = inputJson.get("password").toString();
		String deviceId = inputJson.get("device_id").toString();
		String regId = inputJson.get("registration_id").toString();
		String platform = inputJson.get("platform").toString();
		Double version = 0.00;
		Double app_version = 0.00;
		app_version = Double.parseDouble(inputJson.get("version").toString());
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao registerDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		EmployeeDto regisDto = new EmployeeDto();
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(userName);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("getLoginDetails");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + userName + " " + " Password :" + password + " " + " DeviceId :" + deviceId + " "
				+ " RegId :" + regId + " " + " platform :" + platform);

		registerDao.insertTxnLog(lDto);
		regisDto.setUserName(userName);
		regisDto.setPassword(password);
		regisDto.setDeviceId(deviceId);
		regisDto.setRegId(regId);
		regisDto.setPlatform(platform);
		regisDto.setVersion(app_version);

		int resultCnt = registerDao.getLoginDetails(regisDto);
		if (resultCnt > 0) {
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			String token = bytes.toString();
			regisDto.setTokenNo(token);

			deviceList = registerDao.getDeviceDetails(regisDto);
			if (platform.equalsIgnoreCase("Android")) {
				version = registerDao.getDeviceAppVersionAndroid(regisDto);
			} else {
				version = registerDao.getDeviceAppVersionIOS(regisDto);
			}

			int sessionCheck = registerDao.checkSessionExists(regisDto);
			if (sessionCheck > 0) {
				registerDao.updateSessionToken(regisDto);
			} else {
				registerDao.insertSessionDetails(regisDto);
			}

			if (deviceList.isEmpty()) {
				registerDao.insertDeviceDetails(regisDto);
			} else {
				registerDao.updateDeviceRegIdDetails(regisDto);
			}

			result.put("authenticate", "true");
			result.put("token", token);
			result.put("app_version", version);
			result.put("success", "1");
			result.put("err_code", "0");
			result.put("err_message", "");

		} else {
			result.put("authenticate", "false");
			result.put("success", "0");
			result.put("err_code", "522");
			result.put("err_message", "Invalid username or password");
			lDto.setError_code("522");
			lDto.setError_description("Invalid Username");
			registerDao.insertErrorLog(lDto);
		}
		return gson.toJson(result);
	}

	@Path("/validLogin")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getLoginValid(@Context ServletContext config, String details) throws Exception {

		logger.error("This is error : ");
		HashMap<String, Object> result = new HashMap<>();
		List<EmployeeDto> deviceList;
		JSONObject inputJson;
		inputJson = (JSONObject) parser.parse(details);
		String userName = inputJson.get("id").toString();
		String password = inputJson.get("password").toString();
		String deviceId = inputJson.get("device_id").toString();
		String regId = inputJson.get("registration_id").toString();
		String platform = inputJson.get("platform").toString();
		Double version = 0.00;
		Double app_version = 0.00;
		app_version = Double.parseDouble(inputJson.get("version").toString());
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao registerDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		EmployeeDto regisDto = new EmployeeDto();
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(userName);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("getLoginDetails");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + userName + " " + " Password :" + password + " " + " DeviceId :" + deviceId + " "
				+ " RegId :" + regId + " " + " platform :" + platform);

		registerDao.insertTxnLog(lDto);
		regisDto.setUserName(userName);
		regisDto.setPassword(password);
		regisDto.setDeviceId(deviceId);
		regisDto.setRegId(regId);
		regisDto.setPlatform(platform);
		regisDto.setVersion(app_version);

		int resultCnt = registerDao.getLoginDetails(regisDto);
		if (resultCnt > 0) {
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			String token = bytes.toString();
			regisDto.setTokenNo(token);

			deviceList = registerDao.getDeviceDetails(regisDto);
			if (platform.equalsIgnoreCase("Android")) {
				version = registerDao.getDeviceAppVersionAndroid(regisDto);
			} else {
				version = registerDao.getDeviceAppVersionIOS(regisDto);
			}

			int sessionCheck = registerDao.checkSessionExists(regisDto);
			if (sessionCheck > 0) {
				registerDao.updateSessionToken(regisDto);
			} else {
				registerDao.insertSessionDetails(regisDto);
			}

			if (deviceList.isEmpty()) {
				registerDao.insertDeviceDetails(regisDto);
			} else {
				registerDao.updateDeviceRegIdDetails(regisDto);
			}

			result.put("authenticate", "true");
			result.put("token", token);
			result.put("app_version", version);
			result.put("employeeId", registerDao.getEmployeeId(regisDto));
			result.put("success", "1");
			result.put("err_code", "0");
			result.put("err_message", "");

		} else {
			result.put("authenticate", "false");
			result.put("success", "0");
			result.put("err_code", "522");
			result.put("err_message", "Invalid username or password");
			lDto.setError_code("522");
			lDto.setError_description("Invalid Username");
			registerDao.insertErrorLog(lDto);
		}
		return gson.toJson(result);
	}

	@Path("/notificationCount")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getNotificationCount(@Context ServletContext config, String details) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		JSONObject inputJson;
		int count;
		int sessionRes;
		inputJson = (JSONObject) parser.parse(details);
		String userName = inputJson.get("id").toString();
		String tokenNo = inputJson.get("tokenNo").toString();
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao registerDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		EmployeeDto regisDto = new EmployeeDto();
		regisDto.setUserName(userName);
		regisDto.setTokenNo(tokenNo);
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(userName);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("getNotificationCount");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + userName + " " + " TokenNo :" + tokenNo);
		registerDao.insertTxnLog(lDto);
		int resultCnt = registerDao.isEmpValid(regisDto);
		if (resultCnt > 0) {
			sessionRes = registerDao.isTokenValid(regisDto);
			if (sessionRes == -1) {
				result.put("success", "false");
				result.put("err_code", "523");
				result.put("err_message", "Token Invalid");
				lDto.setError_code("523");
				lDto.setError_description("Token Invalid");
				registerDao.insertErrorLog(lDto);
			} else {
				if (sessionRes == 0) {
					result.put("success", "false");
					result.put("err_code", "521");
					result.put("err_message", "Session expired");
					lDto.setError_code("521");
					lDto.setError_description("Session Expired");
					registerDao.insertErrorLog(lDto);
					registerDao.deleteToken(regisDto);
				} else {
					count = registerDao.getNotificationCount(regisDto);
					result.put("success", "true");
					result.put("err_code", "0");
					result.put("err_message", "");
					result.put("count", String.valueOf(count));
				}
			}
		} else {
			result.put("success", "false");
			result.put("err_code", "522");
			result.put("err_message", "Invalid username");
			lDto.setError_code("522");
			lDto.setError_description("Invalid Username");
			registerDao.insertErrorLog(lDto);
		}

		return gson.toJson(result);
	}

	public static String encodeImage(byte[] imageByteArray) throws UnsupportedEncodingException {

		return Base64.encodeBase64URLSafeString(imageByteArray);
	}

	@Path("/profile")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserProfile(@Context ServletContext config, String details) throws Exception {

		HashMap<String, Object> result = new HashMap<>();
		JSONObject inputJson;
		int sessionRes;
		List<EmployeeDto> resultList;
		inputJson = (JSONObject) parser.parse(details);
		String userName = inputJson.get("id").toString();
		String tokenNo = inputJson.get("tokenNo").toString();
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao registerDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		EmployeeDto regisDto = new EmployeeDto();
		String imageDataString = "";
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(userName);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("getUserProfile");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + userName + " " + " TokenNo :" + tokenNo);
		registerDao.insertTxnLog(lDto);
		regisDto.setUserName(userName);
		regisDto.setTokenNo(tokenNo);
		int resultCnt = registerDao.isEmpValid(regisDto);
		if (resultCnt > 0) {
			sessionRes = registerDao.isTokenValid(regisDto);
			if (sessionRes == -1) {
				result.put("success", "false");
				result.put("err_code", "523");
				result.put("err_message", "Token Invalid");
				lDto.setError_code("523");
				lDto.setError_description("Token Invalid");
				registerDao.insertErrorLog(lDto);
			} else {
				if (sessionRes == 0) {
					result.put("success", "false");
					result.put("err_code", "521");
					result.put("err_message", "Session expired");
					lDto.setError_code("521");
					lDto.setError_description("Session Expired");
					registerDao.insertErrorLog(lDto);
					registerDao.deleteToken(regisDto);
				} else {
					resultList = registerDao.getUserProfile(regisDto);
					if (!resultList.isEmpty()) {
						result.put("success", "true");
						result.put("err_code", "0");
						result.put("err_message", "");
						result.put("emp_id", userName);
						result.put("emp_name", resultList.get(0).getEmpName());
						result.put("gender", resultList.get(0).getGender());
						result.put("designation", resultList.get(0).getDesignation());
						result.put("band", resultList.get(0).getBand());
						result.put("department", resultList.get(0).getDepartment());
						result.put("employment_status", resultList.get(0).getEmploymentStatus());
						result.put("DOB", resultList.get(0).getDob());
						result.put("DOJ", resultList.get(0).getDoj());
						result.put("location", resultList.get(0).getLocation());
						result.put("photo", resultList.get(0).getEmployeePhoto());
						// Local
						// File file = new File("D:\\Ponraj\\Projects\\iDeal Mobile
						// App\\iDealProfileImages\\"+resultList.get(0).getEmployeePhoto());
						// Production
						File file = new File("C:\\wampn\\www\\iDeal\\" + "app\\webroot\\uploads\\employee_photos\\"
								+ resultList.get(0).getEmployeePhoto());

						// Pre-production
						// File file = new
						// File("C:\\wamp\\www\\idealuat\\app\\webroot\\uploads\\employee_photos\\"+resultList.get(0).getEmployeePhoto());
						// Beta
						// File file = new
						// File("C:\\wamp\\www\\idealbeta\\app\\webroot\\uploads\\employee_photos\\"+resultList.get(0).getEmployeePhoto());
						try {
							double fileSize = file.length();
							log.info("PhotoSize::::::::\" + fileSize");
							if (fileSize > 2000000) {
								BufferedImage originalImage = ImageIO.read(file);
								int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
										: originalImage.getType();

								ByteArrayOutputStream baos = new ByteArrayOutputStream();

								BufferedImage resizeImageJpg = resizeImage(originalImage, type);
								ImageIO.write(resizeImageJpg, "jpg", baos);

								baos.flush();
								byte[] imageInByte = baos.toByteArray();
								imageDataString = new String(Base64.encodeBase64(imageInByte), "UTF-8");
							} else {
								FileInputStream imageInFile = new FileInputStream(file);
								byte imageData[] = new byte[(int) file.length()];
								imageInFile.read(imageData);
								imageDataString = new String(Base64.encodeBase64(imageData), "UTF-8");
							}
						} catch (FileNotFoundException e) {
							log.error("Image not found" + e);
						} catch (IOException ioe) {
							log.error("Exception while reading the Image" + ioe);
						}
						result.put("image", imageDataString);
					} else {
						result.put("success", "false");
						result.put("err_code", "524");
						result.put("err_message", "No Profile created for the Employee");
						lDto.setError_code("524");
						lDto.setError_description("No profile created");
						registerDao.insertErrorLog(lDto);
					}
				}
			}
		} else {
			result.put("success", "false");
			result.put("err_code", "522");
			result.put("err_message", "Invalid Username");
			lDto.setError_code("522");
			lDto.setError_description("Invalid Username");
			registerDao.insertErrorLog(lDto);
		}
		return gson.toJson(result);
	}

	@Path("/getMasterData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getMasterData(@Context ServletContext config, String details) throws Exception {

		HashMap<String, Object> result = new HashMap<>();
		JSONObject inputJson;
		List<MasterDto> projectList;
		List<MasterDto> rolesList;
		List<EmployeeDto> wfhDetails;
		List<EmployeeDto> wfhExcList;
		List<EmployeeDto> wfhPolicy;
		int sessionRes;
		List<MasterDto> regularizationList;
		List<MasterDto> resultListPhases;
		List<MasterDto> resultListPhasesTask;
		List<MasterDto> nonProjectActivities;
		List<MasterDto> nonProjectActivitiesTasks;
		inputJson = (JSONObject) parser.parse(details);
		String userName = inputJson.get("id").toString();
		String tokenNo = inputJson.get("tokenNo").toString();
		String startDate = inputJson.get("start_date").toString();
		String endDate = inputJson.get("end_date").toString();
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(userName);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("getMasterData");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + userName + " " + " TokenNo :" + tokenNo + " " + "StartDate :" + startDate + " "
				+ " EndDate :" + endDate);

		employeeDao.insertTxnLog(lDto);
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setUserName(userName);
		employeeDto.setTokenNo(tokenNo);
		employeeDto.setStartDate(startDate);
		employeeDto.setEndDate(endDate);
		int resultCnt = employeeDao.isEmpValid(employeeDto);
		if (resultCnt > 0) {
			sessionRes = employeeDao.isTokenValid(employeeDto);
			if (sessionRes == -1) {
				result.put("success", "false");
				result.put("err_code", "523");
				result.put("err_message", "Token Invalid");
				lDto.setError_code("523");
				lDto.setError_description("Token Invalid");
				employeeDao.insertErrorLog(lDto);
			} else {
				if (sessionRes == 0) {
					result.put("success", "false");
					result.put("err_code", "521");
					result.put("err_message", "Session expired");
					lDto.setError_code("521");
					lDto.setError_description("Session expired");
					employeeDao.insertErrorLog(lDto);
					employeeDao.deleteToken(employeeDto);
				} else {
					projectList = employeeDao.getMasterProjects(employeeDto);
					nonProjectActivities = employeeDao.getNonProjectPhases();
					List<HashMap<String, Object>> list = new ArrayList<>();
					HashMap<String, Object> json = null;
					if (!projectList.isEmpty() || !nonProjectActivities.isEmpty()) {
						for (MasterDto obj : projectList) {
							json = new HashMap<>();
							json.put("project_id", obj.getProjectID());
							json.put("project_name", obj.getProjectName());
							List<HashMap<String, Object>> roles = new ArrayList<>();
							List<HashMap<String, Object>> phase = new ArrayList();
							HashMap<String, Object> phaseOthers = new HashMap<>();
							HashMap<String, Object> taskOthers = new HashMap<>();
							List<HashMap<String, Object>> taskOthersNB = new ArrayList();
							employeeDto.setProject_id(obj.getProjectID());
							rolesList = employeeDao.getMasterRoles(employeeDto);
							for (MasterDto subobj : rolesList) {
								HashMap<String, Object> tempobj = new HashMap<>();
								tempobj.put("roll_id", subobj.getRoleID());
								tempobj.put("roll_name", subobj.getRoleName());
								roles.add(tempobj);

								MasterDto mDto = new MasterDto();
								mDto.setProjectCode(subobj.getProjectCode());
								mDto.setStartDate(startDate);
								mDto.setEndDate(endDate);
								resultListPhases = employeeDao.getPhaseDtls(mDto);

								for (MasterDto phaseObj : resultListPhases) {
									HashMap<String, Object> tempobj2 = new HashMap<>();
									tempobj2.put("phase_id", phaseObj.getPhaseID());
									tempobj2.put("phase_name", phaseObj.getPhaseName());
									mDto.setPhasePrimarykey(phaseObj.getPhasePrimarykey());
									mDto.setStartDate(startDate);
									mDto.setEndDate(endDate);
									resultListPhasesTask = employeeDao.getTaskDtls(mDto);
									List<HashMap<String, Object>> task = new ArrayList<>();
									for (MasterDto taskObj : resultListPhasesTask) {
										HashMap<String, Object> tempobj1 = new HashMap<>();
										tempobj1.put("task_id", taskObj.getTaskID());
										tempobj1.put("task_name", taskObj.getTaskName());
										task.add(tempobj1);
									}
									tempobj2.put("tasks", task);
									phase.add(tempobj2);
									json.put("is_role_enabled", subobj.getIs_role_enabled());
									json.put("start_date", subobj.getStartDate());
									json.put("end_date", subobj.getEndDate());
								}
							}
							taskOthers.put("task_id", "Non billable activity");
							taskOthers.put("task_name", "Non billable activity");
							taskOthersNB.add(taskOthers);
							phaseOthers.put("phase_id", "Non billable activity");
							phaseOthers.put("phase_name", "Non billable activity");
							phaseOthers.put("tasks", taskOthersNB);
							phase.add(phaseOthers);
							json.put("role", roles);
							json.put("project_phases", phase);
							list.add(json);
							result.put("projects", list);

						}
						nonProjectActivities = employeeDao.getNonProjectPhases();
						if (!nonProjectActivities.isEmpty()) {
							List<HashMap<String, Object>> tempNpa = new ArrayList<>();
							List<HashMap<String, Object>> NProles = new ArrayList<>();
							HashMap<String, Object> nonProjObj = new HashMap<>();
							MasterDto masDto = new MasterDto();
							for (MasterDto mobj : nonProjectActivities) {
								HashMap<String, Object> NpaObj = new HashMap<>();
								NpaObj.put("phase_id", mobj.getNphaseId());
								NpaObj.put("phase_name", mobj.getNphaseName());
								masDto.setNphaseId(mobj.getNphaseId());
								List<HashMap<String, Object>> Nptlist = new ArrayList<>();
								nonProjectActivitiesTasks = employeeDao.getNonProjectTasks(masDto);
								for (MasterDto taskObj : nonProjectActivitiesTasks) {
									HashMap<String, Object> Nptobj = new HashMap<>();
									Nptobj.put("task_id", taskObj.getNtaskId());
									Nptobj.put("task_name", taskObj.getNtaskName());
									Nptlist.add(Nptobj);
								}
								NpaObj.put("tasks", Nptlist);
								tempNpa.add(NpaObj);
							}
							nonProjObj.put("project_phases", tempNpa);
							nonProjObj.put("project_id", "Non_Project_Activity");
							nonProjObj.put("project_name", "Non Project Activity");
							nonProjObj.put("start_date", "2008-01-01");
							nonProjObj.put("end_date", "9999-12-31");
							nonProjObj.put("is_role_enabled", "false");
							nonProjObj.put("role", NProles);
							list.add(nonProjObj);
							result.put("projects", list);
						}
						result.put("success", "true");
						result.put("err_code", "200");
						result.put("err_message", "");
					} else {
						result.put("success", "false");
						result.put("err_code", 525);
						result.put("err_message", "No Projects Available");
						lDto.setError_code("525");
						lDto.setError_description("No Projects Available");
						employeeDao.insertErrorLog(lDto);
					}

					int wfhEligibility = employeeDao.checkWFHEligibility(employeeDto);
					wfhDetails = employeeDao.getEmpWfhDetailsForReasons(employeeDto);
					wfhPolicy = employeeDao.getWfhPolicy();
					wfhExcList = employeeDao.getWfhExcList();
					int emp_month_count = wfhDetails.get(0).getMonth_count();
					int emp_year_count = wfhDetails.get(0).getYear_count();
					int policy_month_count = wfhPolicy.get(0).getWHF_Per_Month();
					int policy_year_count = wfhPolicy.get(0).getWHF_Per_Year();
					String excUserId = wfhExcList.get(0).getEmp_id();
					String is_allowed = "";

					regularizationList = employeeDao.getRegularizationDtls();
					if (!regularizationList.isEmpty()) {
						List<HashMap<String, Object>> tempRegularization = new ArrayList<>();
						for (MasterDto obj : regularizationList) {
							HashMap<String, Object> tempobj = new HashMap<>();
							if (obj.getParentId().equals("638") && obj.getConfigurationKey().equalsIgnoreCase("w")) {
								if (emp_month_count >= policy_month_count - 1 && emp_year_count >= policy_year_count - 1
										&& excUserId != null && !excUserId.equals(userName) || wfhEligibility == 0) {

									is_allowed = "0";
								}
							} else {
								is_allowed = "1";
							}
							tempobj.put("is_allowed", is_allowed);
							tempobj.put("reason_id", obj.getParentId());
							tempobj.put("reason_short_key", obj.getConfigurationKey());
							tempobj.put("reason_text", obj.getConfigurationValue());
							tempRegularization.add(tempobj);
						}
						result.put("reasons", tempRegularization);
					}
				}
			}
		}
		return gson.toJson(result);
	}

	@Path("/resetPassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String resetPassword(@Context ServletContext config, String details) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		JSONObject inputJson;
		inputJson = (JSONObject) parser.parse(details);
		String userName = inputJson.get("id").toString();
		String password = inputJson.get("old_password").toString();
		String newPassword = inputJson.get("new_password").toString();
		String confirmPassword = inputJson.get("confirm_password").toString();
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao registerDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		EmployeeDto regisDto = new EmployeeDto();
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(userName);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("resetPassword");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + userName + " " + " Password :" + password + " " + "NewPassword :" + newPassword
				+ " " + " ConfirmPassword :" + confirmPassword);

		registerDao.insertTxnLog(lDto);
		regisDto.setUserName(userName);
		regisDto.setPassword(password);
		regisDto.setNewPassword(newPassword);
		regisDto.setConfirmPassword(confirmPassword);
		int resultCnt = registerDao.getLoginDetails(regisDto);
		if (resultCnt > 0) {
			if (!newPassword.equals(confirmPassword)) {
				result.put("success", "false");
				result.put("err_code", "526");
				result.put("err_message", "new_password and confirm_password should be same");
				lDto.setError_code("526");
				lDto.setError_description("New password and confirm password should be same");
				registerDao.insertErrorLog(lDto);
			} else {
				registerDao.resettingPassword(regisDto);
				result.put("success", "true");
				result.put("err_code", "0");
				result.put("err_message", "");
				log.info("Username :" + userName + " reset password successfull");
			}
		} else {
			result.put("success", "false");
			result.put("err_code", "522");
			result.put("err_message", "Invalid username or password");
			lDto.setError_code("522");
			lDto.setError_description("Invalid username and password");
			registerDao.insertErrorLog(lDto);
		}
		return gson.toJson(result);
	}

	@Path("/logout")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(@Context ServletContext config, String details) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		JSONObject inputJson;
		List<EmployeeDto> deviceList;
		int sessionRes;
		inputJson = (JSONObject) parser.parse(details);
		String userName = inputJson.get("id").toString();
		String deviceId = inputJson.get("device_id").toString();
		String platform = inputJson.get("platform").toString();
		String token = inputJson.get("token").toString();
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao registerDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		EmployeeDto regisDto = new EmployeeDto();
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(userName);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("logout");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + userName + " " + " DeviceID :" + deviceId + " " + "Platform :" + platform + " "
				+ " Token :" + token);
		registerDao.insertTxnLog(lDto);
		regisDto.setUserName(userName);
		regisDto.setDeviceId(deviceId);
		regisDto.setPlatform(platform);
		regisDto.setTokenNo(token);

		int resultCnt = registerDao.isEmpValid(regisDto);
		if (resultCnt > 0) {
			deviceList = registerDao.getDeviceDetails(regisDto);
			if (!deviceList.isEmpty()) {
				registerDao.deleteDeviceDtls(regisDto);
				sessionRes = registerDao.isTokenValid(regisDto);
				if (sessionRes == -1) {
					result.put("success", "false");
					result.put("err_code", "523");
					result.put("err_message", "Token Invalid");
					lDto.setError_code("523");
					lDto.setError_description("Token Invalid");
					registerDao.insertErrorLog(lDto);
				} else {
					result.put("success", "true");
					result.put("err_code", "0");
					result.put("err_message", "");
					registerDao.deleteToken(regisDto);
				}
			} else {
				result.put("success", "false");
				result.put("err_code", "538");
				result.put("err_message", "Invalid deviceId");
				lDto.setError_code("538");
				lDto.setError_description("Invalid deviceId");
				registerDao.insertErrorLog(lDto);
			}
		} else {
			result.put("success", "false");
			result.put("err_code", "522");
			result.put("err_message", "Invalid username");
			lDto.setError_code("522");
			lDto.setError_description("Invalid username");
			registerDao.insertErrorLog(lDto);
		}
		return gson.toJson(result);
	}

	@Path("/tsEntries")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getTimesheetDetailsAndEntries(@Context ServletContext config, @NotNull String details)
			throws Exception {

		HashMap<String, Object> result = new HashMap<>();
		JSONObject inputJson;
		int sessionRes;
		List<TimeSheetDto> resultList;
		inputJson = (JSONObject) parser.parse(details);
		String empNumber = inputJson.get("id").toString();
		String fromDate = inputJson.get("start_date").toString();
		String toDate = inputJson.get("end_date").toString();
		String tokenNo = inputJson.get("tokenNo").toString();
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(empNumber);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("getTimesheetDetailsAndEntries");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + empNumber + " " + " FromDate :" + fromDate + " " + "ToDate :" + toDate + " "
				+ " Token :" + tokenNo);
		employeeDao.insertTxnLog(lDto);
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setUserName(empNumber);
		employeeDto.setTokenNo(tokenNo);
		employeeDto.setStartDate(fromDate);
		employeeDto.setEndDate(toDate);
		int resultCnt = employeeDao.isEmpValid(employeeDto);
		if (resultCnt > 0) {
			sessionRes = employeeDao.isTokenValid(employeeDto);
			if (sessionRes == -1) {
				result.put("success", "false");
				result.put("err_code", "523");
				result.put("err_message", "Token Invalid");
				lDto.setError_code("523");
				lDto.setError_description("Token Invalid");
				employeeDao.insertErrorLog(lDto);
			} else {
				if (sessionRes == 0) {
					result.put("success", "false");
					result.put("err_code", "521");
					result.put("err_message", "Session expired");
					lDto.setError_code("521");
					lDto.setError_description("Session expired");
					employeeDao.insertErrorLog(lDto);
					employeeDao.deleteToken(employeeDto);
				} else {
					resultList = employeeDao.getTimesheetDetailsAndEntries(employeeDto);
					List<HashMap<String, Object>> list = new ArrayList<>();
					if (resultList.isEmpty()) {
						result.put("success", "false");
						result.put("err_code", "527");
						result.put("err_message", "No timesheet entries available");
						lDto.setError_code("527");
						lDto.setError_description("No timesheet entries available");
						employeeDao.insertErrorLog(lDto);
					} else {
						HashMap<String, Object> json = null;
						String lastInserteddate = "";
						for (TimeSheetDto obj : resultList) {
							if (!lastInserteddate.equalsIgnoreCase(obj.getSelected_date())) {
								json = new HashMap<>();
								lastInserteddate = obj.getSelected_date();
								json.put("date", obj.getSelected_date());
								json.put("available_hours", obj.getAvailableHrs());
								json.put("attendance_hours", obj.getAttendance_hours());
								if (obj.getTotalHrs() == null) {
									json.put("timesheet_availability", "0");
								} else {
									json.put("timesheet_availability", "1");
								}
								json.put("timesheet_hours", obj.getTotalHrs());
								json.put("regularization_hours", obj.getRegularizationHrs());
								json.put("is_holiday", obj.getIs_holiday());
								List<HashMap<String, Object>> templist = new ArrayList<>();
								for (TimeSheetDto subobj : resultList) {
									HashMap<String, Object> tempobj = new HashMap<>();
									if ((subobj.getSelected_date().equalsIgnoreCase(json.get("date").toString()))
											&& subobj.getStatus() != null && subobj.getStatus() != "") {

										tempobj.put("timesheet_id", subobj.getTimesheet_id());
										tempobj.put("project_id", subobj.getProject_id());
										tempobj.put("project_name", subobj.getProject_name());
										tempobj.put("project_status", subobj.getProject_status());
										tempobj.put("timesheet_hours", subobj.getTimesheet_hours());
										tempobj.put("shift_id", subobj.getShift_id());
										tempobj.put("role_id", subobj.getRole_id());
										tempobj.put("phase_id", subobj.getPhase_id());
										tempobj.put("task_id", subobj.getTask_id());
										tempobj.put("is_regularized", subobj.getIs_regularized());
										tempobj.put("reasons", subobj.getReasons());
										tempobj.put("remark", subobj.getRemark());
										tempobj.put("status", subobj.getStatus());
										tempobj.put("reasonCheck", subobj.getReasonCheck());
										templist.add(tempobj);
									}
								}
								json.put("entries", templist);

								list.add(json);
								result.put("data", list);

								result.put("success", "true");
								result.put("err_code", "200");
								result.put("err_message", "");
							}
						}
					}
				}
			}
		} else {
			result.put("success", "false");
			result.put("err_code", "522");
			result.put("err_message", "Invalid Username");
			lDto.setError_code("522");
			lDto.setError_description("Invalid Username");
			employeeDao.insertErrorLog(lDto);
		}
		return gson.toJson(result);
	}

	@Path("/rejectedTsList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getRejectedTsNotificationList(@Context ServletContext config, @NotNull String details)
			throws Exception {

		HashMap<String, Object> rjList = new HashMap<>();
		JSONObject inputJson;
		inputJson = (JSONObject) parser.parse(details);
		int sessionRes;
		List<TSNotificationListDto> resultList;
		String empNumber = inputJson.get("id").toString();
		String tokenNo = inputJson.get("tokenNo").toString();
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao registerDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(empNumber);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("getRejectedTsNotificationList");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + empNumber + " " + " Token :" + tokenNo);
		registerDao.insertTxnLog(lDto);
		EmployeeDto registerDto = new EmployeeDto();
		registerDto.setUserName(empNumber);
		registerDto.setTokenNo(tokenNo);
		int resultCnt = registerDao.isEmpValid(registerDto);
		if (resultCnt > 0) {
			sessionRes = registerDao.isTokenValid(registerDto);
			if (sessionRes == -1) {
				rjList.put("success", "false");
				rjList.put("err_code", "523");
				rjList.put("err_message", "Token Invalid");
				lDto.setError_code("523");
				lDto.setError_description("Token Invalid");
				registerDao.insertErrorLog(lDto);
			} else {
				if (sessionRes == 0) {
					rjList.put("success", "false");
					rjList.put("err_code", "521");
					rjList.put("err_message", "Session expired");
					lDto.setError_code("521");
					lDto.setError_description("Session expired");
					registerDao.insertErrorLog(lDto);
					registerDao.deleteToken(registerDto);
				} else {
					resultList = registerDao.getTimesheetNL(registerDto);
					if (!resultList.isEmpty()) {
						rjList.put("success", "true");
						rjList.put("err_code", "200");
						rjList.put("err_message", "");

						for (int i = 0; i < resultList.size(); i++) {
							rjList.put("list", resultList);
						}
					} else {
						rjList.put("success", "false");
						rjList.put("err_code", "528");
						rjList.put("err_message", "No sent back entries available");
						lDto.setError_code("528");
						lDto.setError_description("No sent back entries available");
						registerDao.insertErrorLog(lDto);

					}
				}
			}
		} else {
			rjList.put("success", "false");
			rjList.put("err_code", "522");
			rjList.put("err_message", "Invalid Username");
			lDto.setError_code("522");
			lDto.setError_description("Invalid Username");
			registerDao.insertErrorLog(lDto);
		}
		return gson.toJson(rjList);
	}

	@Path("/calendar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getCurrentCalendar(@Context ServletContext config, String details) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		JSONObject inputJson = new JSONObject();
		inputJson = (JSONObject) parser.parse(details);
		int SessionRes;
		List<CalendarDto> resultList;
		String empNumber = inputJson.get("id").toString();
		String fromDate = inputJson.get("start_date").toString();
		String toDate = inputJson.get("end_date").toString();
		String tokenNo = inputJson.get("tokenNo").toString();
		final WebApplicationContext cxt = WebApplicationContextUtils.getRequiredWebApplicationContext(config);
		EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(empNumber);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("getCurrentCalendar");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + empNumber + " " + " FromDate :" + fromDate + " " + "ToDate :" + toDate + " "
				+ " Token :" + tokenNo);
		employeeDao.insertTxnLog(lDto);
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setUserName(empNumber);
		employeeDto.setTokenNo(tokenNo);
		employeeDto.setStartDate(fromDate);
		employeeDto.setEndDate(toDate);
		int resultCnt = employeeDao.isEmpValid(employeeDto);
		if (resultCnt > 0) {
			SessionRes = employeeDao.isTokenValid(employeeDto);
			if (SessionRes == -1) {
				result.put("success", "false");
				result.put("err_code", "523");
				result.put("err_message", "Token Invalid");
				lDto.setError_code("523");
				lDto.setError_description("Token Invalid");
				employeeDao.insertErrorLog(lDto);
			} else {
				if (SessionRes == 0) {
					result.put("success", "false");
					result.put("err_code", "521");
					result.put("err_message", "Session expired");
					lDto.setError_code("521");
					lDto.setError_description("Session Expired");
					employeeDao.insertErrorLog(lDto);
					employeeDao.deleteToken(employeeDto);
				} else {
					resultList = employeeDao.getCurrentCalendar(employeeDto);
					List<HashMap<String, Object>> list = new ArrayList<>();
					if (resultList.isEmpty()) {
						result.put("success", "false");
						result.put("err_code", "529");
						result.put("err_message", "No calendar available");
						lDto.setError_code("529");
						lDto.setError_description("No Calendar Available");
						employeeDao.insertErrorLog(lDto);
					} else {
						HashMap<String, Object> json = null;
						for (CalendarDto obj : resultList) {
							json = new HashMap<>();
							json.put("timesheet_date", obj.getTimesheet_date());
							json.put("available_hours", obj.getAvailable_hours());
							json.put("is_holiday", obj.getIs_holiday());
							json.put("is_weekend", obj.getIs_weekend());
							json.put("leave_status", obj.getLeave_status());
							json.put("entries_status", obj.getEntries_status());
							json.put("attendance_hours", obj.getAttendance_hours());
							json.put("regularization_hours", obj.getRegularizationHrs());
							list.add(json);
							result.put("calendar", list);
							result.put("success", "true");
							result.put("err_code", "200");
							result.put("err_message", "");
						}
					}
				}
			}

		} else {
			result.put("success", "false");
			result.put("err_code", "522");
			result.put("err_message", "Invalid Username");
			lDto.setError_code("522");
			lDto.setError_description("Invalid Username");
			employeeDao.insertErrorLog(lDto);
		}
		return gson.toJson(result);
	}

	@Path("/submitTimeSheetEntries")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject submitTimeSheetEntry(@Context ServletContext config, String jsonStr) throws Exception {

		JSONObject result = new JSONObject();
		int sessionRes;
		List<EmployeeDto> profile;
		List<EmployeeDto> wfhDetails;
		List<EmployeeDto> wfhExcList;
		List<EmployeeDto> wfhPolicy;
		JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);
		String userName = (String) jsonObject.get("id");
		String tokenNo = (String) jsonObject.get("tokenNo");
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(userName);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("submitTimeSheetEntry");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + userName + " " + " Token :" + tokenNo);
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setUserName(userName);
		employeeDto.setTokenNo(tokenNo);
		int resultCnt = employeeDao.isEmpValid(employeeDto);
		if (resultCnt > 0) {
			sessionRes = employeeDao.isTokenValid(employeeDto);
			if (sessionRes == -1) {
				result.put("success", "false");
				result.put("err_code", "523");
				result.put("err_message", "Token Invalid");
				lDto.setError_code("523");
				lDto.setError_description("Token Invalid");
				employeeDao.insertErrorLog(lDto);
			} else {
				if (sessionRes == 0) {
					result.put("success", "false");
					result.put("err_code", "521");
					result.put("err_message", "Session expired");
					lDto.setError_code("521");
					lDto.setError_description("Session Expired");
					employeeDao.insertErrorLog(lDto);
					employeeDao.deleteToken(employeeDto);
				} else {
					profile = employeeDao.getUserProfile(employeeDto);
					JSONArray slideContent = (JSONArray) jsonObject.get("dates");
					Iterator i = slideContent.iterator();
					String flag = "0";
					while (i.hasNext()) {
						JSONObject slide = (JSONObject) i.next();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date timesheetDate = sdf.parse((String) slide.get("date"));
						String timeSheetDateStr = sdf.format(sdf.parse(slide.get("date").toString()));
						employeeDto.setTimeSheetDateStr(timeSheetDateStr);
						employeeDto.setTimeSheetDate(timesheetDate);
						String availableHours = slide.get("available_hours").toString();
						employeeDto.setAvailable_hours(availableHours);
						String att_hours = slide.get("attendance_hours").toString();
						employeeDto.setAttendance_hours(att_hours);
						String timesheetHours = slide.get("timesheet_hours").toString();
						employeeDto.setTotalTimesheet_hours(timesheetHours);
						String regularizationHours = slide.get("regularization_hours").toString();
						employeeDto.setRegularization_hours(regularizationHours);
						String status = slide.get("status").toString();
						employeeDto.setStatus(status);
						String is_holiday = slide.get("is_holiday").toString();
						employeeDto.setIs_holiday(is_holiday);
						String is_HfLeave = slide.get("is_HfLeave").toString();
						employeeDto.setIs_HfLeave(is_HfLeave);
						String is_WeekEnd = slide.get("is_WeekEnd").toString();
						employeeDto.setIs_WeekEnd(is_WeekEnd);
						int resCount1 = employeeDao.isEntry(employeeDto);
						int resCount = employeeDao.isEntrySubmitted(employeeDto);
						String isEmpLeave = employeeDao.isEmpLeaveApplied(employeeDto);

						if (!(isEmpLeave.equalsIgnoreCase("FULL") && is_WeekEnd.equals("0")
								&& is_holiday.equals("0"))) {
							log.info("resCount" + resCount);
							if (resCount == 0 && resCount1 != 0) {
								result.put("success", "false");
								result.put("err_code", "530");
								result.put("err_message", "Submitted/Approved timesheet entries cannot be changed");
								lDto.setError_code("530");
								lDto.setError_description("Submitted/Approved timesheet entries cannot be changed");
								employeeDao.insertErrorLog(lDto);
								log.info("Username :" + userName
										+ " Submitted/Approved timesheet entries cannot be changed");
							} else {
								JSONArray slideContentEntries = (JSONArray) slide.get("entries");
								if (slideContentEntries.isEmpty()) {
									result.put("success", "false");
									result.put("err_code", "531");
									result.put("err_message", "No Entries available for the date - " + timesheetDate);
									lDto.setError_code("531");
									lDto.setError_description("No Entries available for " + timesheetDate);
									employeeDao.insertErrorLog(lDto);
								} else {
									Iterator it = slideContentEntries.iterator();
									Iterator it1 = slideContentEntries.iterator();
									int timeSheetHours = 0;
									int timeSheetMinutes = 0;
									String reasonsExistChk = "";
									String totalTimesheetHrs = "";
									String totTimesheetHours1 = "";
									String timesheet_hours1 = "";
									JSONArray slideContentEntries1 = (JSONArray) slide.get("entries");
									Iterator itr = slideContentEntries1.iterator();
									while (itr.hasNext()) {
										JSONObject slide2 = (JSONObject) itr.next();
										reasonsExistChk = reasonsExistChk + slide2.get("reasons").toString();
									}
									// while (it1.hasNext())
									for (int j = 0; j < slideContentEntries.size(); j++) {
										JSONObject obj = (JSONObject) slideContentEntries.get(j);
										timesheet_hours1 = (String) obj.get("timesheet_hours");
										String temp[] = timesheet_hours1.split(":");
										int hours = Integer.parseInt(temp[0]);
										int minutes = Integer.parseInt(temp[1]);
										timeSheetHours = timeSheetHours + hours;
										timeSheetMinutes = timeSheetMinutes + minutes;

										if (timeSheetMinutes >= 60) {
											timeSheetHours++;
											timeSheetMinutes = timeSheetMinutes % 60;
										}
										totalTimesheetHrs = timeSheetHours + "." + timeSheetMinutes;
									}

									while (it.hasNext()) {
										flag = "0";
										JSONObject slide1 = (JSONObject) it.next();
										String project_id = slide1.get("project_id").toString();
										employeeDto.setProject_id(project_id);
										String timesheet_hours = (String) slide1.get("timesheet_hours");
										employeeDto.setTimesheet_hours(timesheet_hours);
										String shift_id = slide1.get("shift_id").toString();
										employeeDto.setShift_id(shift_id);
										String role_id = (String) slide1.get("role_id");
										String phase_id = (String) slide1.get("phase_id");
										String task_id = (String) slide1.get("task_id");
										String timesheet_id = slide1.get("timesheet_id").toString();
										employeeDto.setRole_id((String) slide1.get("role_id"));
										employeeDto.setPhase_id((String) slide1.get("phase_id"));
										employeeDto.setTask_id((String) slide1.get("task_id"));
										String is_regularized = slide1.get("is_regularized").toString();
										employeeDto.setIs_regularized(is_regularized);
										String reasons = slide1.get("reasons").toString();
										employeeDto.setReasons(reasons);

										if (phase_id == null || phase_id == "" || phase_id.isEmpty() || task_id == null
												|| task_id == "" || task_id.isEmpty()) {

											LogsDto ElogDto = new LogsDto();
											ElogDto.setError_code("511");
											ElogDto.setError_description("NoPhaseIDorTaskID");
											ElogDto.setModule_name("Timesheet_entries");
											ElogDto.setFunction_name("submitTimeSheetEntry");
											ElogDto.setTxn_type_name("POST");
											ElogDto.setEmp_id(userName);
											ElogDto.setData("Username :" + userName + " " + " project_id :" + project_id
													+ " " + " timesheet_id :" + timesheet_id + " " + " timeSheetDate :"
													+ timeSheetDateStr + " " + " phase_id :" + phase_id + " "
													+ " task_id :" + task_id + " " + " role_id :" + role_id + " "
													+ " timesheet_hours :" + timesheet_hours + " " + " att_hours :"
													+ att_hours + " " + " shift_id :" + shift_id);

											employeeDao.insertErrorLog(ElogDto);
										}
										if (role_id == null || role_id == "" || role_id.isEmpty()) {

											LogsDto ElogDto = new LogsDto();
											ElogDto.setError_code("511");
											ElogDto.setError_description("NoRoleID");
											ElogDto.setModule_name("Timesheet_entries");
											ElogDto.setFunction_name("submitTimeSheetEntry");
											ElogDto.setTxn_type_name("POST");
											ElogDto.setEmp_id(userName);
											ElogDto.setData("Username :" + userName + " " + " project_id :" + project_id
													+ " " + " timesheet_id :" + timesheet_id + " " + " timeSheetDate :"
													+ timeSheetDateStr + " " + " role_id :" + role_id);

											employeeDao.insertErrorLog(ElogDto);
										}
										if (status.equalsIgnoreCase("m")
												&& Double.parseDouble(totalTimesheetHrs) > 23.59) {
											result.put("success", "false");
											result.put("err_code", "537");
											result.put("err_message",
													"The user cannot enter time greater than 23:59 hours for a Timesheet Entry.");
											lDto.setError_code("537");
											lDto.setError_description(
													"User cannot enter time greater than 23:59 hours for a timesheet entry");
											employeeDao.insertErrorLog(lDto);
											flag = "1";
											break;
										}

										if (slide1.get("reasons").toString().trim().equals("Work From Home")) {
											wfhDetails = employeeDao.getEmpWfhDetails(employeeDto);
											wfhPolicy = employeeDao.getWfhPolicy();
											wfhExcList = employeeDao.getWfhExcList();
											int emp_month_count = wfhDetails.get(0).getMonth_count();
											int emp_year_count = wfhDetails.get(0).getYear_count();
											int policy_month_count = wfhPolicy.get(0).getWHF_Per_Month();
											int policy_year_count = wfhPolicy.get(0).getWHF_Per_Year();
											String excUserId = wfhExcList.get(0).getEmp_id();

											if (emp_month_count >= policy_month_count - 1
													&& emp_year_count >= policy_year_count - 1 && excUserId != null
													&& !excUserId.equals(userName)) {
												result.put("success", "false");
												result.put("err_code", "533");
												result.put("err_message",
														"You are exceeding 'Work from home' policy limit for the Month/Year. Please refer policy document.");
												lDto.setError_code("533");
												lDto.setError_description("Exceeded WFH Policy");
												employeeDao.insertErrorLog(lDto);
												flag = "1";
												break;
											}
										}
										if (profile.get(0).getLocation().trim()
												.equals("SKCL Towers - Triton Square(HTL - India - Chennai)")
												|| profile.get(0).getLocation().trim().equals(
														"HINDUJA TECH LTD. GIGA SPACE,DELTA-1, 3rd Floor,(HTL - India - Pune)")) {
											if (status.equalsIgnoreCase("m") && att_hours.equals("00:00")
													&& reasonsExistChk.equals("")) {
												result.put("success", "false");
												result.put("err_code", "534");
												result.put("err_message", "Please select the regularization reason");
												lDto.setError_code("534");
												lDto.setError_description("Please select regularization reason");
												employeeDao.insertErrorLog(lDto);
												flag = "1";
												break;
											}
											if (status.equalsIgnoreCase("m") && !att_hours.equals("00:00")
													&& !att_hours.equals(timesheetHours)
													&& reasonsExistChk.equals("")) {
												result.put("success", "false");
												result.put("err_code", "534");
												result.put("err_message", "Please select the regularization reason");
												lDto.setError_code("534");
												lDto.setError_description("Please select regularization reason");
												employeeDao.insertErrorLog(lDto);
												flag = "1";
												break;
											}
											if (status.equalsIgnoreCase("m") && att_hours.equals(availableHours)
													&& !att_hours.equals(timesheetHours)
													&& reasonsExistChk.equals("")) {
												result.put("success", "false");
												result.put("err_code", "534");
												result.put("err_message", "Please select the regularization reason");
												lDto.setError_code("534");
												lDto.setError_description("Please select regularization reason");
												employeeDao.insertErrorLog(lDto);
												flag = "1";
												break;
											}
											if (status.equalsIgnoreCase("m") && !att_hours.equals("00:00")
													&& (reasonsExistChk.equals("")) && is_WeekEnd.equals("0")
													&& is_holiday.equals("0") && is_HfLeave.equals("0")) {
												DateFormat df = new java.text.SimpleDateFormat("hh:mm");
												Date available_hours = df
														.parse(slide.get("available_hours").toString());
												Date attendance_hours = df
														.parse(slide.get("attendance_hours").toString());
												long diff = attendance_hours.getTime() - available_hours.getTime();
												long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
												if (minutes < -1) {
													result.put("success", "false");
													result.put("err_code", "534");
													result.put("err_message",
															"Please select the regularization reason");
													lDto.setError_code("534");
													lDto.setError_description("Please select regularization reason");
													employeeDao.insertErrorLog(lDto);
													flag = "1";
													break;
												}
											}
											if (status.equalsIgnoreCase("m") && !att_hours.equals("00:00")
													&& (reasonsExistChk.equals(""))
													&& (is_WeekEnd.equals("1") || is_holiday.equals("1"))) {

												String attendance_hrs = slide.get("attendance_hours").toString()
														.replace(":", ".");
												String available_hrs = slide.get("available_hours").toString()
														.replace(":", ".");
												String timesheet_hrd = slide.get("timesheet_hours").toString()
														.replace(":", ".");
												if (Double.parseDouble(timesheet_hrd) > Double
														.parseDouble(attendance_hrs)) {
													result.put("success", "false");
													result.put("err_code", "534");
													result.put("err_message",
															"Please select the regularization reason");
													lDto.setError_code("534");
													lDto.setError_description("Please select regularization reason");
													employeeDao.insertErrorLog(lDto);
													flag = "1";
													break;
												}
												// if (Double.parseDouble(attendance_hrs) >
												// Double.parseDouble(available_hrs)) {
												// result.put("success", "false");
												// result.put("err_code", "534");
												// result.put("err_message", "Please select the regularization reason");
												// lDto.setError_code("534");
												// lDto.setError_description("Please select regularization reason");
												// employeeDao.insertErrorLog(lDto);
												// flag = "1";
												// break;
												// }
											}
										} else {
 

											if (status.equalsIgnoreCase("m") && att_hours.equals("00:00")) {
												if ((!timesheetHours.equals(availableHours))
														&& (reasonsExistChk.equals(""))) {
													result.put("success", "false");
													result.put("err_code", "534");
													result.put("err_message",
															"Please select the regularization reason");
													lDto.setError_code("534");
													lDto.setError_description("Please select regularization reason");
													employeeDao.insertErrorLog(lDto);
													flag = "1";
													break;
												}
											}
											if (status.equalsIgnoreCase("m") && att_hours.equals(availableHours)
													&& !att_hours.equals("00:00") && reasonsExistChk.equals("")) {
												DateFormat df = new java.text.SimpleDateFormat("hh:mm");
												Date available_hours = df
														.parse(slide.get("available_hours").toString());
												Date attendance_hours = df
														.parse(slide.get("attendance_hours").toString());
												long diff = attendance_hours.getTime() - available_hours.getTime();
												long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
												if (minutes < -1) {
													result.put("success", "false");
													result.put("err_code", "534");
													result.put("err_message",
															"Please select the regularization reason");
													lDto.setError_code("534");
													lDto.setError_description("Please select regularization reason");
													employeeDao.insertErrorLog(lDto);
													flag = "1";
													break;
												}
											}

											String attendance_hrs1 = slide.get("attendance_hours").toString()
													.replace(":", ".");
											String timesheet_hrd1 = slide.get("timesheet_hours").toString().replace(":",
													".");

											if (status.equalsIgnoreCase("m") && !attendance_hrs1.equals("00:00")
													&& !timesheet_hrd1.equals("00:00")
													&& !attendance_hrs1.equals(timesheet_hrd1)
													&& Double.parseDouble(timesheet_hrd1) > Double
															.parseDouble(attendance_hrs1)
													&& reasonsExistChk.equals("")) {

												result.put("success", "false");
												result.put("err_code", "534");
												result.put("err_message", "Please select the regularization reason");
												lDto.setError_code("534");
												lDto.setError_description("Please select regularization reason");
												employeeDao.insertErrorLog(lDto);
												flag = "1";
												break;

											}

											if (status.equalsIgnoreCase("m") && att_hours.equals(availableHours)
													&& !att_hours.equals("00:00") && reasonsExistChk.equals("")) {
												String attendance_hrs = slide.get("attendance_hours").toString()
														.replace(":", ".");
												String timesheet_hrd = slide.get("timesheet_hours").toString()
														.replace(":", ".");
 

												if (Double.parseDouble(timesheet_hrd) > Double
														.parseDouble(attendance_hrs)) {
													result.put("success", "false");
													result.put("err_code", "534");
													result.put("err_message",
															"Please select the regularization reason");
													lDto.setError_code("534");
													lDto.setError_description("Please select regularization reason");
													employeeDao.insertErrorLog(lDto);
													flag = "1";
													break;
												}
											}
										}
										if (!reasons.equals("") && slide1.get("remark").equals("")) {
											result.put("success", "false");
											result.put("err_code", "535");
											result.put("err_message",
													"Remarks should not be empty while selecting the Regularization reason.");
											lDto.setError_code("535");
											lDto.setError_description(
													"Remarks should not be empty while selecting the regularization reason");
											employeeDao.insertErrorLog(lDto);
											flag = "1";
											break;
										}
										if (project_id.equals("")) {
											result.put("success", "false");
											result.put("err_code", "540");
											result.put("err_message", "Please select project");
											lDto.setError_code("540");
											lDto.setError_description("Please select project");
											employeeDao.insertErrorLog(lDto);
											break;
										}
										employeeDto.setRemark((String) slide1.get("remark"));
										employeeDto.setStatus((String) slide1.get("status"));
										employeeDto.setTimesheetId(slide1.get("timesheet_id").toString());
										if ("00:00".equals(timesheet_hours) || "".equals(timesheet_hours)
												|| timesheet_hours.equals("0:0") || timesheet_hours.equals("0:00")
												|| timesheet_hours.equals("00:0") || timesheet_hours.equals("0")) {
											result.put("success", "false");
											result.put("err_code", "536");
											result.put("err_message", "Timesheet hours should not be empty.");
											lDto.setError_code("536");
											lDto.setError_description("Timesheet hours should not be empty");
											employeeDao.insertErrorLog(lDto);
											flag = "1";
											break;
										} else {
											// String temp[] = timesheet_hours.split(":");
											// int hours = Integer.parseInt(temp[0]);
											// int minutes = Integer.parseInt(temp[1]);
											// timeSheetHours = timeSheetHours + hours;
											// timeSheetMinutes = timeSheetMinutes + minutes;
											//
											// if (timeSheetMinutes >= 60) {
											// timeSheetHours ++;
											// timeSheetMinutes = timeSheetMinutes % 60;
											// }
											// String totTimesheetHours = timeSheetHours+"."+timeSheetMinutes;

											// if(Double.parseDouble(totTimesheetHours) > 23.59)
											// {
											// result.put("success", "false");
											// result.put("err_code", "537");
											// result.put("err_message", "The user cannot select a time greater than
											// 23:59 hours for a Timesheet Entry.");
											// lDto.setError_code("537");
											// lDto.setError_description("User cannot select a time greater than 23:59
											// hours for a timesheet entry");
											// employeeDao.insertErrorLog(lDto);
											// flag = "1";
											// break;
											// }
											employeeDao.insertTimesheetEntries(employeeDto);
											lDto.setData("Username :" + userName + " " + " Token :" + tokenNo + " "
													+ " project_id :" + project_id + " " + " timesheet_id :"
													+ timesheet_id + " " + " timeSheetDate :" + timeSheetDateStr + " "
													+ " phase_id :" + phase_id + " " + " task_id :" + task_id + " "
													+ " role_id :" + role_id + " " + " timesheet_hours :"
													+ timesheet_hours + " " + " att_hours :" + att_hours + " "
													+ " shift_id :" + shift_id + " " + " status :" + status + " "
													+ " availableHrs :" + availableHours + " " + " regularizationHrs :"
													+ regularizationHours + " " + "reason :" + reasons + " remarks :"
													+ slide1.get("remark"));
											employeeDao.insertTxnLog(lDto);
											result.put("success", "true");
											result.put("err_code", "0");
											result.put("err_message", "");
										}
									}
								}
								if (flag.equals("1")) {
									break;
								}
							}
						} else {
							result.put("success", "false");
							result.put("err_code", "539");
							result.put("err_message", "Leave request has been submitted for the date " + timesheetDate);
							lDto.setError_code("539");
							lDto.setError_description("Leave request has been submitted for the date " + timesheetDate);
							employeeDao.insertErrorLog(lDto);
							break;
						}
					}
				}
			}
		}
		return result;
	}

	@Path("/deleteTimeSheetEntry")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@Context ServletContext config, String details) throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		JSONObject inputJson;
		int sessionRes;
		inputJson = (JSONObject) parser.parse(details);
		String userName = inputJson.get("id").toString();
		String tokenNo = inputJson.get("tokenNo").toString();
		String timesheetId = inputJson.get("timeSheet_id").toString();

		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(userName);
		lDto.setModule_name("Timesheet_entries");
		lDto.setFunction_name("getCurrentCalendar");
		lDto.setTxn_type_name("POST");
		lDto.setData("Username :" + userName + " " + " TimesheetID :" + timesheetId + " " + " Token :" + tokenNo);
		employeeDao.insertTxnLog(lDto);
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setUserName(userName);
		employeeDto.setTokenNo(tokenNo);
		employeeDto.setTimesheetId(timesheetId);
		int resultCnt = employeeDao.isEmpValid(employeeDto);
		if (resultCnt > 0) {
			sessionRes = employeeDao.isTokenValid(employeeDto);
			if (sessionRes == -1) {
				result.put("success", "false");
				result.put("err_code", "523");
				result.put("err_message", "Token Invalid");
				lDto.setError_code("523");
				lDto.setError_description("Token Invalid");
				employeeDao.insertErrorLog(lDto);
			} else {
				if (sessionRes == 0) {
					result.put("success", "false");
					result.put("err_code", "521");
					result.put("err_message", "Session expired");
					lDto.setError_code("521");
					lDto.setError_description("Session Expired");
					employeeDao.insertErrorLog(lDto);
					employeeDao.deleteToken(employeeDto);
				} else {
					employeeDao.deleteUpdate(employeeDto);
					result.put("success", "true");
					result.put("err_code", "");
					result.put("err_message", "");
				}
			}
		} else {
			result.put("success", "false");
			result.put("err_code", "522");
			result.put("err_message", "Invalid Username");
			lDto.setError_code("522");
			lDto.setError_description("Invalid Username");
			employeeDao.insertErrorLog(lDto);
		}
		return gson.toJson(result);
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		int imageWidth = 250;
		int imageHeight = 400;
		BufferedImage resizedImage = new BufferedImage(imageWidth, imageHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
		g.dispose();

		return resizedImage;
	}

	@Path("/feedCategoriesList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String feedCategoriesList(@Context ServletContext config, NewsAndUpdateDTO newsAndUpdateDTO)
			throws Exception {
		logger.info("request Method name : feedCategoriesList \n" + "params : " + gson.toJson(newsAndUpdateDTO));
		HashMap<String, Object> result = loginSessionValidation(config, newsAndUpdateDTO.getEmployeeId(),
				newsAndUpdateDTO.getTokenNo(), NEW_UPDATE_MODULE, "feedCategoriesList", METHOD_TYPE_POST);
		if (result.isEmpty()) {
			final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
			EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
			List<NewsCategories> newsCategoriesList = employeeDao.getNewsCategoriesListWithCount(newsAndUpdateDTO);
			result.put(RESPONSE, newsCategoriesList);
			result.put(SUCCESS, "true");
			result.put(ERROR_CODE, "");
			result.put(ERROR_MESSAGE, "");
			return gson.toJson(result);
		}
		return gson.toJson(result);
	}

	@Path("/newsFeedList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String newsFeedList(@Context ServletContext config, NewsAndUpdateDTO newsAndUpdateDTO) throws Exception {
		logger.info("request Method name : newsFeedList \n" + "params : " + gson.toJson(newsAndUpdateDTO));
		HashMap<String, Object> result = loginSessionValidation(config, newsAndUpdateDTO.getEmployeeId(),
				newsAndUpdateDTO.getTokenNo(), NEW_UPDATE_MODULE, "newsFeedList", METHOD_TYPE_POST);
		if (result.isEmpty()) {
			final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
			EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
			List<NewsFeedMessageDTO> newsFeedsList = employeeDao.getNewsFeedListWithUnReadCount(newsAndUpdateDTO);
			List<ConfigurationValuesEntity> configurationValuesEntity = employeeDao.getNewsFeedConfigurationDetails();
			FeedMessageListDTO feedMessageListDTO = new FeedMessageListDTO();
			feedMessageListDTO.setNewsFeedMessageDTOList(newsFeedsList);
			feedMessageListDTO.setConfigurationValuesEntity(configurationValuesEntity);
			result.put(SUCCESS, "true");
			result.put(ERROR_CODE, "");
			result.put(ERROR_MESSAGE, "");
			result.put(RESPONSE, feedMessageListDTO);
		}
		return gson.toJson(result);
	}

	@Path("/newsFeedMessageList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String newsFeedMessageList(@Context ServletContext config, NewsAndUpdateDTO newsAndUpdateDTO)
			throws Exception {
		logger.info("request Method name : newsFeedMessageList \n" + "params : " + gson.toJson(newsAndUpdateDTO));
		HashMap<String, Object> result = loginSessionValidation(config, newsAndUpdateDTO.getEmployeeId(),
				newsAndUpdateDTO.getTokenNo(), NEW_UPDATE_MODULE, "newsFeedMessageList", METHOD_TYPE_POST);
		if (result.isEmpty()) {
			final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
			EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
			List<ConfigurationValuesEntity> configurationValuesEntityList = employeeDao
					.getNewsFeedConfigurationDetails();
			Optional<ConfigurationValuesEntity> configurationValuesEntityOptional = configurationValuesEntityList
					.stream().filter(configurationValuesEntity -> configurationValuesEntity.getConfigurationKey()
							.equalsIgnoreCase("pic_square_location"))
					.findFirst();
			Optional<ConfigurationValuesEntity> thumbnailLocationOptional = configurationValuesEntityList.stream()
					.filter(configurationValuesEntity -> configurationValuesEntity.getConfigurationKey()
							.equalsIgnoreCase("pic_thumbnail_location"))
					.findFirst();
			Optional<ConfigurationValuesEntity> fullImageLocationOptional = configurationValuesEntityList.stream()
					.filter(configurationValuesEntity -> configurationValuesEntity.getConfigurationKey()
							.equalsIgnoreCase("picture_location"))
					.findFirst();
			String pictureSquareLocation = configurationValuesEntityOptional.isPresent()
					? configurationValuesEntityOptional.get().getConfigurationValue()
					: "";
			String pictureLocation = fullImageLocationOptional.isPresent()
					? fullImageLocationOptional.get().getConfigurationValue()
					: "";
			String thumbnailLocation = thumbnailLocationOptional.isPresent()
					? thumbnailLocationOptional.get().getConfigurationValue()
					: "";
			newsAndUpdateDTO.setPictureLocation(pictureLocation);
			newsAndUpdateDTO.setPictureThumbnailLocation(thumbnailLocation);
			newsAndUpdateDTO.setPictureSquareLocation(pictureSquareLocation);
			List<NewsFeedMessageDTO> newsFeedsList = employeeDao.getNewsFeedList(newsAndUpdateDTO);
			FeedMessageListDTO feedMessageListDTO = new FeedMessageListDTO();
			feedMessageListDTO.setNewsFeedMessageDTOList(newsFeedsList);
			feedMessageListDTO.setConfigurationValuesEntity(configurationValuesEntityList);
			result.put(SUCCESS, "true");
			result.put(ERROR_CODE, "");
			result.put(ERROR_MESSAGE, "");
			result.put(RESPONSE, feedMessageListDTO);
		}
		return gson.toJson(result);
	}

	@Path("/makeFeedSocialInterAction")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String makeFeedSocialInterAction(@Context ServletContext config, NewsAndUpdateDTO newsAndUpdateDTO)
			throws Exception {
		logger.info("request Method name : makeFeedSocialInterAction \n" + "params : " + gson.toJson(newsAndUpdateDTO));
		HashMap<String, Object> result = new HashMap<>();
		if (newsAndUpdateDTO.getEmployeeId().isEmpty() || newsAndUpdateDTO.getEmpId() == 0) {
			result.put(SUCCESS, "false");
			result.put(ERROR_CODE, "");
			result.put(ERROR_MESSAGE, "Employee id or number should not empty");
			return gson.toJson(result);
		}
		result = loginSessionValidation(config, newsAndUpdateDTO.getEmployeeId(), newsAndUpdateDTO.getTokenNo(),
				NEW_UPDATE_MODULE, "makeFeedSocialInterAction", METHOD_TYPE_POST);
		if (result.isEmpty()) {
			final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
			EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
			List<NewsFeedsDTO> newsFeedsDTOList = employeeDao.getFeedSocialInteractionType(newsAndUpdateDTO);
			if (newsFeedsDTOList == null || newsFeedsDTOList.isEmpty()) {
				result.put(SUCCESS, "false");
				result.put(ERROR_CODE, "");
				result.put(ERROR_MESSAGE, "Feed not exists.");
			} else {
				newsAndUpdateDTO.setSectionId(newsFeedsDTOList.get(0).getSectionId());
				newsAndUpdateDTO.setInteractionType(newsFeedsDTOList.get(0).getInteractionType());
				int isEmployeeFeedHistoryExists = 0;
				isEmployeeFeedHistoryExists = employeeDao.isEmployeeFeedHistoryExistsOrNot(newsAndUpdateDTO);
				if (isEmployeeFeedHistoryExists == 0) {
					result.put(SUCCESS, "false");
					result.put(ERROR_CODE, "");
					result.put(ERROR_MESSAGE, "Feed still not visible by employee");
				} else {
					return gson.toJson(doSocialInteractionAction(employeeDao, newsAndUpdateDTO, result));
				}
			}
		}
		return gson.toJson(result);
	}

	private HashMap<String, Object> doSocialInteractionAction(EmployeeDao employeeDao,
			NewsAndUpdateDTO newsAndUpdateDTO, HashMap<String, Object> result) {
		int interactionId = employeeDao.isFeedSocialInteractionExits(newsAndUpdateDTO);
		if (interactionId > 0) {
			employeeDao.updateFeedSocialInteractionType(newsAndUpdateDTO);
		} else {
			employeeDao.insertFeedSocialInteractionType(newsAndUpdateDTO);
		}
		List<SocialInteractionDTO> socialInteractionDTOList = employeeDao
				.getInteractionCountInformation(newsAndUpdateDTO);
		SocialInteractionDetailsResponse socialInteractionDetailsResponse = new SocialInteractionDetailsResponse();
		if (socialInteractionDTOList != null) {
			socialInteractionDetailsResponse.setSocialInteractionCount(socialInteractionDTOList.size());
			socialInteractionDetailsResponse.setSocialInteractionDTOList(socialInteractionDTOList);
		} else {
			socialInteractionDetailsResponse.setSocialInteractionCount(0);
			socialInteractionDetailsResponse.setSocialInteractionDTOList(null);
		}
		result.put(SUCCESS, "true");
		result.put(ERROR_CODE, "");
		result.put(ERROR_MESSAGE, "");
		result.put(RESPONSE, socialInteractionDetailsResponse);
		return result;
	}

	@Path("/feedReadAction")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String feedReadUpdate(@Context ServletContext config, NewsAndUpdateDTO newsAndUpdateDTO) throws Exception {
		HashMap<String, Object> result = loginSessionValidation(config, newsAndUpdateDTO.getEmployeeId(),
				newsAndUpdateDTO.getTokenNo(), NEW_UPDATE_MODULE, "feedReadUpdate", METHOD_TYPE_POST);
		if (result.isEmpty()) {
			final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
			EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
			List<NewsFeedsDTO> newsFeedsDTOList = employeeDao.getFeedSocialInteractionType(newsAndUpdateDTO);
			if (newsFeedsDTOList == null || newsFeedsDTOList.isEmpty()) {
				result.put(SUCCESS, "false");
				result.put(ERROR_CODE, "");
				result.put(ERROR_MESSAGE, "Feed not exists.");
			} else {
				return gson.toJson(doFeedReadAction(employeeDao, newsFeedsDTOList, newsAndUpdateDTO, result));
			}
		}
		return gson.toJson(result);
	}

	private HashMap<String, Object> doFeedReadAction(EmployeeDao employeeDao, List<NewsFeedsDTO> newsFeedsDTOList,
			NewsAndUpdateDTO newsAndUpdateDTO, HashMap<String, Object> result) {
		newsAndUpdateDTO.setSectionId(newsFeedsDTOList.get(0).getSectionId());
		newsAndUpdateDTO.setPublisherId(newsFeedsDTOList.get(0).getPublisherId());
		try {
			int isEmployeeFeedHistoryExists = employeeDao.isEmployeeFeedHistoryExistsOrNot(newsAndUpdateDTO);
			if (isEmployeeFeedHistoryExists == 0) {
				employeeDao.insertEmployeeFeedHistory(newsAndUpdateDTO);
			}
			result.put(SUCCESS, "true");
			result.put(ERROR_CODE, "");
			result.put(ERROR_MESSAGE, "");
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	@Path("/dashBoardUnReadMessageCounts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getDashBoardUnReadMessageCounts(@Context ServletContext config,
			@NotNull NewsAndUpdateDTO newsAndUpdateDTO) throws Exception {

		HashMap<String, Object> result = loginSessionValidation(config, newsAndUpdateDTO.getEmployeeId(),
				newsAndUpdateDTO.getTokenNo(), NEW_UPDATE_MODULE, "dashBoardUnReadMessageCounts", METHOD_TYPE_POST);
		if (result.isEmpty()) {
			final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
			EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
			Object countObject = employeeDao.getDashBoardUnReadMessageCount(newsAndUpdateDTO);
			if (countObject != null) {
				result.put("countDetails", countObject);
				result.put(SUCCESS, "true");
				result.put(ERROR_CODE, "0");
				result.put(ERROR_MESSAGE, "");
			} else {
				result.put(SUCCESS, "false");
				result.put(ERROR_CODE, "");
				result.put(ERROR_MESSAGE, "count not exists");
			}
		}

		return gson.toJson(result);
	}

	private String getFileWithUtil(String fileName) {
		String result = "";
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			logger.error("This is error : " + e);
		}
		return result;
	}

	private HashMap<String, Object> loginSessionValidation(ServletContext config, String userName, String tokenNo,
			String moduleName, String functionName, String methodType) {
		HashMap<String, Object> result = new HashMap<>();
		final WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(config);
		EmployeeDao employeeDao = (EmployeeDao) cxt.getBean("EmployeeDao");
		LogsDto lDto = makeLogsDTO(moduleName, functionName, methodType, userName, tokenNo);
		try {
			EmployeeDto employeeDto = new EmployeeDto();
			employeeDto.setUserName(userName);
			employeeDto.setTokenNo(tokenNo);
			employeeDao.insertTxnLog(lDto);
			int resultCnt;
			resultCnt = employeeDao.isEmpValid(employeeDto);
			return makeResultMessage(resultCnt, employeeDao, lDto, employeeDto);
		} catch (Exception e) {
			logger.error("This is error : " + e);
		}
		return result;
	}

	private LogsDto makeLogsDTO(String moduleName, String functionName, String methodType, String userName,
			String tokenNo) {
		LogsDto lDto = new LogsDto();
		lDto.setEmp_id(userName);
		lDto.setModule_name(moduleName);
		lDto.setFunction_name(functionName);
		lDto.setTxn_type_name(methodType);
		lDto.setData("Username :" + userName + " " + " Token :" + tokenNo);
		return lDto;
	}

	private HashMap<String, Object> makeResultMessage(int resultCnt, EmployeeDao employeeDao, LogsDto lDto,
			EmployeeDto employeeDto) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			if (resultCnt <= 0) {
				result.put(SUCCESS, "false");
				result.put(ERROR_CODE, "522");
				result.put(ERROR_MESSAGE, INVALID_USERNAME);
				lDto.setError_code("522");
				lDto.setError_description(INVALID_USERNAME);
				employeeDao.insertErrorLog(lDto);
			} else {
				int sessionRes = employeeDao.isTokenValid(employeeDto);
				if (sessionRes == -1) {
					result.put(SUCCESS, "false");
					result.put(ERROR_CODE, "523");
					result.put(ERROR_MESSAGE, TOKEN_INVALID);
					lDto.setError_code("523");
					lDto.setError_description(TOKEN_INVALID);
					employeeDao.insertErrorLog(lDto);
				} else if (sessionRes == 0) {
					result.put(SUCCESS, "false");
					result.put(ERROR_CODE, "521");
					result.put(ERROR_MESSAGE, SESSION_EXPIRED);
					lDto.setError_code("521");
					lDto.setError_description(SESSION_EXPIRED);
					employeeDao.insertErrorLog(lDto);
					employeeDao.deleteToken(employeeDto);
				}
			}
		} catch (Exception e) {
			logger.error("This is error : " + e);
		}
		return result;
	}

}
