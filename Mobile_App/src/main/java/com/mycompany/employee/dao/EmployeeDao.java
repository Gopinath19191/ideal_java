/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.employee.dao;

import com.mycompany.employee.dto.*;
import com.mycompany.employee.entitiy.ConfigurationValuesEntity;
import com.mycompany.employee.entitiy.NewsCategories;

import java.util.List;

/**
 * @author 16363
 */
public interface EmployeeDao {

    public int getLoginDetails(EmployeeDto regisDto) throws Exception;

    public List<EmployeeDto> getDeviceDetails(EmployeeDto regisDto) throws Exception;

    public int insertDeviceDetails(EmployeeDto regisDto) throws Exception;

    public int getEmployeeId(EmployeeDto employeeDto) throws Exception;

    //public void updateDeviceDetails(EmployeeDto regisDto) throws Exception; 

    public int getNotificationCount(EmployeeDto regisDto) throws Exception;

    public Object getDashBoardUnReadMessageCount(NewsAndUpdateDTO newsAndUpdateDTO) throws Exception;

    public List<EmployeeDto> getUserProfile(EmployeeDto regisDto) throws Exception;

    public void updateDeviceRegIdDetails(EmployeeDto regisDto) throws Exception;

    public void insertSessionDetails(EmployeeDto regisDto) throws Exception;

    public void updateSessionToken(EmployeeDto regisDto) throws Exception;

    public int checkSessionExists(EmployeeDto regisDto) throws Exception;

    public double getDeviceAppVersionAndroid(EmployeeDto regisDto) throws Exception;

    public double getDeviceAppVersionIOS(EmployeeDto regisDto) throws Exception;

    //    public List<EmployeeDto> isTokenValid(EmployeeDto regisDto) throws Exception;
    public int isTokenValid(EmployeeDto regisDto) throws Exception;

    public int isEmpValid(EmployeeDto regisDto) throws Exception;

    public void resettingPassword(EmployeeDto regisDto) throws Exception;

    public void deleteDeviceDtls(EmployeeDto regisDto) throws Exception;

    public void deleteToken(EmployeeDto regisDto) throws Exception;

    public List<TSNotificationListDto> getTimesheetNL(EmployeeDto registerDto) throws Exception;

    public List<TimeSheetDto> getTimesheetDetailsAndEntries(EmployeeDto registerDto) throws Exception;

    public List<CalendarDto> getCurrentCalendar(EmployeeDto employeeDto) throws Exception;

    public List<MasterDto> getMasterData(EmployeeDto regisDto) throws Exception;

    public List<MasterDto> getMasterProjects(EmployeeDto regisDto) throws Exception;

    public List<MasterDto> getMasterRoles(EmployeeDto regisDto) throws Exception;

    public List<MasterDto> getMasterDataRoles(EmployeeDto regisDto) throws Exception;

    public List<MasterDto> getRegularizationDtls() throws Exception;

    public List<MasterDto> getPhaseDtls(MasterDto registerDto) throws Exception;

    public List<MasterDto> getTaskDtls(MasterDto registerDto) throws Exception;

//    public List<MasterDto> getNonProjectDetails() throws Exception;

    public List<MasterDto> getNonProjectPhases() throws Exception;

    public List<MasterDto> getNonProjectTasks(MasterDto masDto) throws Exception;

    public void insertTimesheetEntries(EmployeeDto regisDto) throws Exception;

    public void deleteUpdate(EmployeeDto regisDto) throws Exception;

    public List<EmployeeDto> getEmpWfhDetails(EmployeeDto employeeDto) throws Exception;

    public List<EmployeeDto> getWfhPolicy() throws Exception;

    public List<EmployeeDto> getWfhExcList() throws Exception;

    public void insertTxnLog(LogsDto lDto) throws Exception;

    public void insertErrorLog(LogsDto lDto) throws Exception;

    public int isEntrySubmitted(EmployeeDto regisDto) throws Exception;

    public int isEntry(EmployeeDto regisDto) throws Exception;

    public String isEmpLeaveApplied(EmployeeDto regisDto) throws Exception;

    public List<EmployeeDto> getEmpWfhDetailsForReasons(EmployeeDto employeeDto) throws Exception;

    public int checkWFHEligibility(EmployeeDto regisDto) throws Exception;

    public List<PushNotificationDto> getDeviceRegKeyForPN(PushNotificationDto pnDto) throws Exception;

    public List<PushNotificationDto> getDeviceRegKeyForSentBackPN(PushNotificationDto pnDto) throws Exception;

    public List<EmployeeDto> getleaveDaysPN(EmployeeDto employeeDto) throws Exception;

    public List<PushNotificationDto> getltimesheetDatePN(PushNotificationDto pushDto) throws Exception;

    public int nodeviceFound(PushNotificationDto pushDto) throws Exception;

    public List<NewsCategories> getNewsCategoriesListWithCount(NewsAndUpdateDTO newsAndUpdateDTO) throws Exception;

    List<NewsFeedMessageDTO> getNewsFeedListWithUnReadCount(NewsAndUpdateDTO newsAndUpdateDTO) throws Exception;

    List<NewsFeedMessageDTO> getNewsFeedList(NewsAndUpdateDTO newsAndUpdateDTO) throws Exception;

    List<ConfigurationValuesEntity> getNewsFeedConfigurationDetails();

    int isFeedExistOrNot(NewsAndUpdateDTO newsAndUpdateDTO) throws Exception;

    void isFeedReadStatusChanged(NewsAndUpdateDTO newsAndUpdateDTO) throws Exception;

    int isEmployeeFeedHistoryExistsOrNot(NewsAndUpdateDTO newsAndUpdateDTO) throws Exception;

    void insertEmployeeFeedHistory(NewsAndUpdateDTO newsAndUpdateDTO) throws Exception;

    List<NewsFeedsDTO> getFeedSocialInteractionType(NewsAndUpdateDTO newsAndUpdateDTO);

    int isFeedSocialInteractionExits(NewsAndUpdateDTO newsAndUpdateDTO);

    void updateFeedSocialInteractionType(NewsAndUpdateDTO newsAndUpdateDTO);

    int insertFeedSocialInteractionType(NewsAndUpdateDTO newsAndUpdateDTO);

    List<SocialInteractionDTO> getInteractionCountInformation(NewsAndUpdateDTO newsAndUpdateDTO);
}
