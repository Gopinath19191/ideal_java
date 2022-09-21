/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SwipeDumpToAttendanceData;

import java.util.Date;

/**
 *
 * @author 15064
 */
public class AttendanceData {
    private long EmpId;
    private String EmployeeNumber;
    private String AttendanceDate;
    private String CheckinTime;
    private int InOut;
    private String Door;
    private String Remarks;
    private String Source;
    private int Location;

    public long getEmpId() {
        return EmpId;
    }

    public void setEmpId(long EmpId) {
        this.EmpId = EmpId;
    }

    public String getEmployeeNumber() {
        return EmployeeNumber;
    }

    public void setEmployeeNumber(String EmployeeNumber) {
        this.EmployeeNumber = EmployeeNumber;
    }

    public String getAttendanceDate() {
        return AttendanceDate;
    }

    public void setAttendanceDate(String AttendanceDate) {
        this.AttendanceDate = AttendanceDate;
    }

    public String getCheckinTime() {
        return CheckinTime;
    }

    public void setCheckinTime(String CheckinTime) {
        this.CheckinTime = CheckinTime;
    }

    public int getInOut() {
        return InOut;
    }

    public void setInOut(int InOut) {
        this.InOut = InOut;
    }

    public String getDoor() {
        return Door;
    }

    public void setDoor(String Door) {
        this.Door = Door;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public int getLocation() {
        return Location;
    }

    public void setLocation(int Location) {
        this.Location = Location;
    }   
}
