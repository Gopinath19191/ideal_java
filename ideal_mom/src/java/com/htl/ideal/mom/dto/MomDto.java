/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.dto;

/**
 *
 * @author 16656
 */
public class MomDto {

    private String id;
    private String mom_title;
    private String mom_date;
    private String mom_venue;
    private String planned_start_time;
    private String planned_end_time;
    private String actual_start_time;
    private String actual_end_time;
    private String minuted_by;
    private String mom_status;
    private String deleted;
    private String mom_deleted[];
    private String mom_status_name;
    private String employee_no;
    private String employee_name;
    private String mom_deleted2;
    private String mom_id;
    private String employee_id;
    private String attendance_id;
    private String attendance_status;
    private String attendance_deleted;
    private String memPresent;
    private String memAbsent;
    private String present_attendance_id;
    private String absent_attendance_id;
    private String mom_present_attendance_id[];
    private String mom_absent_attendance_id[];
    private String mom_attendance_id[];
    private String members_present[];
    private String members_absent[];
    private String mom_attendance_deleted[];
    private String mom_absent_attendance_deleted[];
    private String myCheckBox_value;
    private String configValue;
    private String configKey;
    private String mailId;
    private String momTitleRefId;
    private String start_page;
    private String end_page;
    private String momVal;
    private String search_mom_name;
    private String plannedStartHr;
    private String plannedStartMin;
    private String plannedEndHr;
    private String plannedEndMin;
    private String actualStartHr;
    private String actualStartMin;
    private String actualEndHr;
    private String actualEndMin;
    private String plannedStartMeridiem;
    private String plannedEndMeridiem;
    private String actualStartMeridiem;
    private String actualEndMeridiem;
    private String mom_action_status;
    private String open_count;
    private String closed_count;
    private String progress_count;
    private String saved_count;
    private String hold_count;
    private String deferred_count;
     private String count;
     private String total_count;
     private String employee_idd;
     private String test[];
     private String mom_test;
     private String test2[];
private String mom_test2;
    public String[] getTest() {
        return test;
    }

    public String getMom_test() {
        return mom_test;
    }

    public void setMom_test(String mom_test) {
        this.mom_test = mom_test;
    }

    public String getMom_test2() {
        return mom_test2;
    }

    public void setMom_test2(String mom_test2) {
        this.mom_test2 = mom_test2;
    }

    public void setTest(String[] test) {
        this.test = test;
    }

    public String[] getTest2() {
        return test2;
    }

    public void setTest2(String[] test2) {
        this.test2 = test2;
    }

   
     
private String work_email_address;

    public String getWork_email_address() {
        return work_email_address;
    }

    public void setWork_email_address(String work_email_address) {
        this.work_email_address = work_email_address;
    }


   


    public String getEmployee_idd() {
        return employee_idd;
    }

    public void setEmployee_idd(String employee_idd) {
        this.employee_idd = employee_idd;
    }
     
     
    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }
     
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getOpen_count() {
        return open_count;
    }

    public void setOpen_count(String open_count) {
        this.open_count = open_count;
    }

    public String getClosed_count() {
        return closed_count;
    }

    public void setClosed_count(String closed_count) {
        this.closed_count = closed_count;
    }

    public String getProgress_count() {
        return progress_count;
    }

    public void setProgress_count(String progress_count) {
        this.progress_count = progress_count;
    }

    public String getSaved_count() {
        return saved_count;
    }

    public void setSaved_count(String saved_count) {
        this.saved_count = saved_count;
    }

    public String getHold_count() {
        return hold_count;
    }

    public void setHold_count(String hold_count) {
        this.hold_count = hold_count;
    }

    public String getDeferred_count() {
        return deferred_count;
    }

    public void setDeferred_count(String deferred_count) {
        this.deferred_count = deferred_count;
    }
    
    
    public String getMom_action_status() {
        return mom_action_status;
    }

    public void setMom_action_status(String mom_action_status) {
        this.mom_action_status = mom_action_status;
    }
    private String agenda_point;
    private String agenda_deleted;
    private String agenda_id;
    private String mom_agenda_points[];
    private String mom_agenda_id[];
    private String mom_agenda_deleted[];
    private String discussion_point;
    private String discussion_deleted;
    private String discussion_id;
    private String mom_discussion_points[];
    private String mom_discussion_id[];
    private String mom_discussion_deleted[];
    private String action_point_id;
    private String action_point_employee_id;
    private String action_point;
    private String tracking_comments;
    private String planned_close_date;
    private String actual_close_date;
    private String action_point_status;
    private String action_point_deleted;
    private String mom_action_point_id[];
    private String mom_action_point_employee_id[];
    private String mom_action_point[];
    private String mom_tracking_comments[];
    private String mom_planned_close_date[];
    private String mom_action_point_status[];
    private String mom_action_point_deleted[];
    private String mom_actual_close_date[];
    private String last_insertId;
    private String configuration_key;
    private String parent_id;
    private String previous_planned_close_date;
    private String mom_previous_planned_close_date[];
    private String mom_remarks[];
    private String action_id;
    private String ap_status_name;
    private String updated_by;
//    private String mom_updated_by[];
    private String remarks;
    private String action_mom_title;
     private String requestorName; 
       private String time; 

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
       

//    public String[] getMom_updated_by() {
//        return mom_updated_by;
//    }
//
//    public void setMom_updated_by(String[] mom_updated_by) {
//        this.mom_updated_by = mom_updated_by;
//    }

    public String getRequestorName() {
        return requestorName;
    }

    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

    
    public String getAction_mom_title() {
        return action_mom_title;
    }

    public String getMomTitleRefId() {
        return momTitleRefId;
    }

    public void setMomTitleRefId(String momTitleRefId) {
        this.momTitleRefId = momTitleRefId;
    }

    public void setAction_mom_title(String action_mom_title) {
        this.action_mom_title = action_mom_title;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStart_page() {
        return start_page;
    }

    public void setStart_page(String start_page) {
        this.start_page = start_page;
    }

    public String getEnd_page() {
        return end_page;
    }

    public void setEnd_page(String end_page) {
        this.end_page = end_page;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getMom_deleted2() {
        return mom_deleted2;
    }

    public void setMom_deleted2(String mom_deleted2) {
        this.mom_deleted2 = mom_deleted2;
    }

    public String getLast_insertId() {
        return last_insertId;
    }

    public void setLast_insertId(String last_insertId) {
        this.last_insertId = last_insertId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMom_title() {
        return mom_title;
    }

    public void setMom_title(String mom_title) {
        this.mom_title = mom_title;
    }

    public String getMom_date() {
        return mom_date;
    }

    public void setMom_date(String mom_date) {
        this.mom_date = mom_date;
    }

    public String getMom_venue() {
        return mom_venue;
    }

    public void setMom_venue(String mom_venue) {
        this.mom_venue = mom_venue;
    }

    public String getPlanned_start_time() {
        return planned_start_time;
    }

    public void setPlanned_start_time(String planned_start_time) {
        this.planned_start_time = planned_start_time;
    }

    public String getPlanned_end_time() {
        return planned_end_time;
    }

    public void setPlanned_end_time(String planned_end_time) {
        this.planned_end_time = planned_end_time;
    }

    public String getActual_start_time() {
        return actual_start_time;
    }

    public void setActual_start_time(String actual_start_time) {
        this.actual_start_time = actual_start_time;
    }

    public String getActual_end_time() {
        return actual_end_time;
    }

    public void setActual_end_time(String actual_end_time) {
        this.actual_end_time = actual_end_time;
    }

    public String getMinuted_by() {
        return minuted_by;
    }

    public void setMinuted_by(String minuted_by) {
        this.minuted_by = minuted_by;
    }

    public String getMom_status() {
        return mom_status;
    }

    public void setMom_status(String mom_status) {
        this.mom_status = mom_status;
    }

    public String getEmployee_no() {
        return employee_no;
    }

    public void setEmployee_no(String employee_no) {
        this.employee_no = employee_no;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getMom_id() {
        return mom_id;
    }

    public void setMom_id(String mom_id) {
        this.mom_id = mom_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getAttendance_status() {
        return attendance_status;
    }

    public void setAttendance_status(String attendance_status) {
        this.attendance_status = attendance_status;
    }

    public String[] getMembers_present() {
        return members_present;
    }

    public void setMembers_present(String[] members_present) {
        this.members_present = members_present;
    }

    public String[] getMembers_absent() {
        return members_absent;
    }

    public void setMembers_absent(String[] members_absent) {
        this.members_absent = members_absent;
    }

    public String getAgenda_point() {
        return agenda_point;
    }

    public void setAgenda_point(String agenda_point) {
        this.agenda_point = agenda_point;
    }

    public String getAgenda_deleted() {
        return agenda_deleted;
    }

    public void setAgenda_deleted(String agenda_deleted) {
        this.agenda_deleted = agenda_deleted;
    }

    public String getAgenda_id() {
        return agenda_id;
    }

    public void setAgenda_id(String agenda_id) {
        this.agenda_id = agenda_id;
    }

    public String[] getMom_agenda_points() {
        return mom_agenda_points;
    }

    public void setMom_agenda_points(String[] mom_agenda_points) {
        this.mom_agenda_points = mom_agenda_points;
    }

    public String[] getMom_agenda_id() {
        return mom_agenda_id;
    }

    public void setMom_agenda_id(String[] mom_agenda_id) {
        this.mom_agenda_id = mom_agenda_id;
    }

    public String[] getMom_agenda_deleted() {
        return mom_agenda_deleted;
    }

    public void setMom_agenda_deleted(String[] mom_agenda_deleted) {
        this.mom_agenda_deleted = mom_agenda_deleted;
    }

    public String getDiscussion_point() {
        return discussion_point;
    }

    public void setDiscussion_point(String discussion_point) {
        this.discussion_point = discussion_point;
    }

    public String getDiscussion_deleted() {
        return discussion_deleted;
    }

    public void setDiscussion_deleted(String discussion_deleted) {
        this.discussion_deleted = discussion_deleted;
    }

    public String getDiscussion_id() {
        return discussion_id;
    }

    public void setDiscussion_id(String discussion_id) {
        this.discussion_id = discussion_id;
    }

    public String[] getMom_discussion_points() {
        return mom_discussion_points;
    }

    public void setMom_discussion_points(String[] mom_discussion_points) {
        this.mom_discussion_points = mom_discussion_points;
    }

    public String[] getMom_discussion_id() {
        return mom_discussion_id;
    }

    public void setMom_discussion_id(String[] mom_discussion_id) {
        this.mom_discussion_id = mom_discussion_id;
    }

    public String[] getMom_discussion_deleted() {
        return mom_discussion_deleted;
    }

    public void setMom_discussion_deleted(String[] mom_discussion_deleted) {
        this.mom_discussion_deleted = mom_discussion_deleted;
    }

    public String getAction_point_id() {
        return action_point_id;
    }

    public void setAction_point_id(String action_point_id) {
        this.action_point_id = action_point_id;
    }

    public String getAction_point_employee_id() {
        return action_point_employee_id;
    }

    public void setAction_point_employee_id(String action_point_employee_id) {
        this.action_point_employee_id = action_point_employee_id;
    }

    public String getAction_point() {
        return action_point;
    }

    public void setAction_point(String action_point) {
        this.action_point = action_point;
    }

    public String getTracking_comments() {
        return tracking_comments;
    }

    public void setTracking_comments(String tracking_comments) {
        this.tracking_comments = tracking_comments;
    }

    public String getPlanned_close_date() {
        return planned_close_date;
    }

    public void setPlanned_close_date(String planned_close_date) {
        this.planned_close_date = planned_close_date;
    }

    public String getActual_close_date() {
        return actual_close_date;
    }

    public void setActual_close_date(String actual_close_date) {
        this.actual_close_date = actual_close_date;
    }

    public String getAction_point_status() {
        return action_point_status;
    }

    public void setAction_point_status(String action_point_status) {
        this.action_point_status = action_point_status;
    }

    public String getAction_point_deleted() {
        return action_point_deleted;
    }

    public void setAction_point_deleted(String action_point_deleted) {
        this.action_point_deleted = action_point_deleted;
    }

    public String[] getMom_action_point_id() {
        return mom_action_point_id;
    }

    public void setMom_action_point_id(String[] mom_action_point_id) {
        this.mom_action_point_id = mom_action_point_id;
    }

    public String[] getMom_action_point_employee_id() {
        return mom_action_point_employee_id;
    }

    public void setMom_action_point_employee_id(String[] mom_action_point_employee_id) {
        this.mom_action_point_employee_id = mom_action_point_employee_id;
    }

    public String[] getMom_action_point() {
        return mom_action_point;
    }

    public void setMom_action_point(String[] mom_action_point) {
        this.mom_action_point = mom_action_point;
    }

    public String[] getMom_tracking_comments() {
        return mom_tracking_comments;
    }

    public void setMom_tracking_comments(String[] mom_tracking_comments) {
        this.mom_tracking_comments = mom_tracking_comments;
    }

    public String[] getMom_planned_close_date() {
        return mom_planned_close_date;
    }

    public void setMom_planned_close_date(String[] mom_planned_close_date) {
        this.mom_planned_close_date = mom_planned_close_date;
    }

    public String[] getMom_action_point_status() {
        return mom_action_point_status;
    }

    public void setMom_action_point_status(String[] mom_action_point_status) {
        this.mom_action_point_status = mom_action_point_status;
    }

    public String[] getMom_action_point_deleted() {
        return mom_action_point_deleted;
    }

    public void setMom_action_point_deleted(String[] mom_action_point_deleted) {
        this.mom_action_point_deleted = mom_action_point_deleted;
    }

    public String getMemPresent() {
        return memPresent;
    }

    public void setMemPresent(String memPresent) {
        this.memPresent = memPresent;
    }

    public String getMemAbsent() {
        return memAbsent;
    }

    public void setMemAbsent(String memAbsent) {
        this.memAbsent = memAbsent;
    }

    public String getPlannedStartHr() {
        return plannedStartHr;
    }

    public void setPlannedStartHr(String plannedStartHr) {
        this.plannedStartHr = plannedStartHr;
    }

    public String getPlannedStartMin() {
        return plannedStartMin;
    }

    public void setPlannedStartMin(String plannedStartMin) {
        this.plannedStartMin = plannedStartMin;
    }

    public String getPlannedEndHr() {
        return plannedEndHr;
    }

    public void setPlannedEndHr(String plannedEndHr) {
        this.plannedEndHr = plannedEndHr;
    }

    public String getPlannedEndMin() {
        return plannedEndMin;
    }

    public void setPlannedEndMin(String plannedEndMin) {
        this.plannedEndMin = plannedEndMin;
    }

    public String getActualStartHr() {
        return actualStartHr;
    }

    public void setActualStartHr(String actualStartHr) {
        this.actualStartHr = actualStartHr;
    }

    public String getActualStartMin() {
        return actualStartMin;
    }

    public void setActualStartMin(String actualStartMin) {
        this.actualStartMin = actualStartMin;
    }

    public String getActualEndHr() {
        return actualEndHr;
    }

    public void setActualEndHr(String actualEndHr) {
        this.actualEndHr = actualEndHr;
    }

    public String getActualEndMin() {
        return actualEndMin;
    }

    public void setActualEndMin(String actualEndMin) {
        this.actualEndMin = actualEndMin;
    }

    public String getPlannedStartMeridiem() {
        return plannedStartMeridiem;
    }

    public void setPlannedStartMeridiem(String plannedStartMeridiem) {
        this.plannedStartMeridiem = plannedStartMeridiem;
    }

    public String getPlannedEndMeridiem() {
        return plannedEndMeridiem;
    }

    public void setPlannedEndMeridiem(String plannedEndMeridiem) {
        this.plannedEndMeridiem = plannedEndMeridiem;
    }

    public String getActualStartMeridiem() {
        return actualStartMeridiem;
    }

    public void setActualStartMeridiem(String actualStartMeridiem) {
        this.actualStartMeridiem = actualStartMeridiem;
    }

    public String getActualEndMeridiem() {
        return actualEndMeridiem;
    }

    public void setActualEndMeridiem(String actualEndMeridiem) {
        this.actualEndMeridiem = actualEndMeridiem;
    }

    public String getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(String attendance_id) {
        this.attendance_id = attendance_id;
    }

    public String[] getMom_attendance_id() {
        return mom_attendance_id;
    }

    public void setMom_attendance_id(String[] mom_attendance_id) {
        this.mom_attendance_id = mom_attendance_id;
    }

    public String getMom_status_name() {
        return mom_status_name;
    }

    public void setMom_status_name(String mom_status_name) {
        this.mom_status_name = mom_status_name;
    }

    public String getPresent_attendance_id() {
        return present_attendance_id;
    }

    public void setPresent_attendance_id(String present_attendance_id) {
        this.present_attendance_id = present_attendance_id;
    }

    public String getAbsent_attendance_id() {
        return absent_attendance_id;
    }

    public void setAbsent_attendance_id(String absent_attendance_id) {
        this.absent_attendance_id = absent_attendance_id;
    }

    public String[] getMom_present_attendance_id() {
        return mom_present_attendance_id;
    }

    public void setMom_present_attendance_id(String[] mom_present_attendance_id) {
        this.mom_present_attendance_id = mom_present_attendance_id;
    }

    public String[] getMom_absent_attendance_id() {
        return mom_absent_attendance_id;
    }

    public void setMom_absent_attendance_id(String[] mom_absent_attendance_id) {
        this.mom_absent_attendance_id = mom_absent_attendance_id;
    }

    public String getAttendance_deleted() {
        return attendance_deleted;
    }

    public void setAttendance_deleted(String attendance_deleted) {
        this.attendance_deleted = attendance_deleted;
    }

    public String[] getMom_attendance_deleted() {
        return mom_attendance_deleted;
    }

    public void setMom_attendance_deleted(String[] mom_attendance_deleted) {
        this.mom_attendance_deleted = mom_attendance_deleted;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String[] getMom_deleted() {
        return mom_deleted;
    }

    public void setMom_deleted(String[] mom_deleted) {
        this.mom_deleted = mom_deleted;
    }

    public String[] getMom_absent_attendance_deleted() {
        return mom_absent_attendance_deleted;
    }

    public void setMom_absent_attendance_deleted(String[] mom_absent_attendance_deleted) {
        this.mom_absent_attendance_deleted = mom_absent_attendance_deleted;
    }

    public String getMyCheckBox_value() {
        return myCheckBox_value;
    }

    public void setMyCheckBox_value(String myCheckBox_value) {
        this.myCheckBox_value = myCheckBox_value;
    }

    public String getConfiguration_key() {
        return configuration_key;
    }

    public void setConfiguration_key(String configuration_key) {
        this.configuration_key = configuration_key;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getPrevious_planned_close_date() {
        return previous_planned_close_date;
    }

    public void setPrevious_planned_close_date(String previous_planned_close_date) {
        this.previous_planned_close_date = previous_planned_close_date;
    }

    public String[] getMom_previous_planned_close_date() {
        return mom_previous_planned_close_date;
    }

    public void setMom_previous_planned_close_date(String[] mom_previous_planned_close_date) {
        this.mom_previous_planned_close_date = mom_previous_planned_close_date;
    }

    public String[] getMom_remarks() {
        return mom_remarks;
    }

    public void setMom_remarks(String[] mom_remarks) {
        this.mom_remarks = mom_remarks;
    }

    public String getAction_id() {
        return action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public String getAp_status_name() {
        return ap_status_name;
    }

    public void setAp_status_name(String ap_status_name) {
        this.ap_status_name = ap_status_name;
    }

    public String[] getMom_actual_close_date() {
        return mom_actual_close_date;
    }

    public void setMom_actual_close_date(String[] mom_actual_close_date) {
        this.mom_actual_close_date = mom_actual_close_date;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getSearch_mom_name() {
        return search_mom_name;
    }

    public void setSearch_mom_name(String search_mom_name) {
        this.search_mom_name = search_mom_name;
    }

    public String getMomVal() {
        return momVal;
    }

    public void setMomVal(String momVal) {
        this.momVal = momVal;
    }
}
