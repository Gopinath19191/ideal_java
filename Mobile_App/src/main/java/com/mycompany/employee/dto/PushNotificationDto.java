
package com.mycompany.employee.dto;

import java.io.Serializable;

/**
 *
 * @author 16363
 */
public class PushNotificationDto implements Serializable{
    private String userName;
    private String platform;
    private String register_key;
    private String deviceId;
    private String timesheetID;
    private String timesheetDate;

    public String getTimesheetID() {
        return timesheetID;
    }

    public void setTimesheetID(String timesheetID) {
        this.timesheetID = timesheetID;
    }

    public String getTimesheetDate() {
        return timesheetDate;
    }

    public void setTimesheetDate(String timesheetDate) {
        this.timesheetDate = timesheetDate;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRegister_key() {
        return register_key;
    }

    public void setRegister_key(String register_key) {
        this.register_key = register_key;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    
    
}
