/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 16221
 */
public class DomesticTravelDto implements Serializable {
    private String master_id;
    private String tp_reference_id;
    private String employee_id;
    private String employee_name;
    private String band_name;
    private String band_id;
    private String designation_id;
    private String designation_name;
    private String rm_id;
    private String pm_id;
    private String rm_name;
    private String pm_name;
    private String project_id;
    private String project_name;
    private String customer_id;
    private String customer_name;
    private String buh_id;
    private String buh_name;
    private String requested_date;
    private String status;
    private String previous_status;
    private String unit_id;
    private String sub_unit_id;
    private String unit_name;
    private String sub_unit_name;
    private String location_name;
    private String contact_number;
    private String travel_start_date;
    private String travel_end_date;
    private String employee_number;
    private String travel_billable;
    private String customer_others;
    private String project_others;
    private String client_reimbursable;
    private String settlemet_policy;
    private String country_id;
    private String travel_purpose;
    private String travel_term;
    private String guest_booking;
    private String guest_booking_name;
    private String config_key;
    private String config_value;
    private String rm_approved_date;
    private String rm_remarks;
    private String buh_approved_date;
    private String buh_remarks;
    private String treasury_id;
    private String treasury_name;
    private String treasury_approved_date;
    private String treasury_comments;
    private String admin_id;
    private String admin_name;
    private String admin_approved_date;
    private String admin_remarks;
    private String finance_id;
    private String finance_name;
    private String finance_approved_date;
    private String finance_remarks;
    private String status_name;
    private String cancel_remarks;
    private String mail_id;
    private String location_id;
    
    //Travel ticket
    private String[] ticket_booking_type;
    private String[] from_city;
    private String[] from_city_id;
    private String[] from_city_others;
    private String[] to_city;
    private String[] to_city_id;
    private String[] to_city_others;
    private String[] travel_date;
    private String[] travel_preference;
    private String[] travel_mode;
    private String[] ticket_remarks;
    private String[] ticket_deleted;
    private String[] ticket_id;
    private String[] travel_booking_status;
    
    private String ticket_book_config;
    private String ticket_book_type;
    private String travel_from_city;
    private String travel_from_city_id;
    private String travel_from_city_others;
    private String travel_to_city;
    private String travel_to_city_id;
    private String travel_to_city_others;
    private String travel_travel_date;
    private String travel_ticket_date;
    private String travel_travel_preference;
    private String travel_travel_mode;
    private String travel_ticket_remarks;
    private String travel_ticket_id;
    private String travel_ticketdeleted;
    private String booking_status;
    private String booking_status_name;
    private int ticket_count;

    //Hotel Hotel
    private String[] hotel_booking_type;
    private String[] city;
    private String[] city_id;
    private String[] city_others;
    private String[] from_date;
    private String[] to_date;
    private String[] location;
    private String[] hotel_remarks;
    private String[] hotel_deleted;
    private String[] hotel_id;
    private String[] hotel_booking_status;
    
    private String hotel_book_type;
    private String hotel_city;
    private String hotel_city_id;
    private String hotel_city_others;
    private String hotel_from_date;
    private String hotel_to_date;
    private String hotel_location;
    private String hotel_hotel_remarks;
    private String hoteldeleted;
    private String hotelid;
    private int hotel_count;

    //Conveyance
    private String[] pickup;
    private String[] dropto;
    private String[] start_date;
    private String[] time_hr;
    private String[] time_min;
    private String[] conveyance_remarks;
    private String[] conveyance_city;
    private String[] conveyance_city_id;
    private String[] conveyance_city_others;
    private String[] conveyancedeleted;
    private String[] conveyance_id;
    private String[] conveyance_booking_status;
    
    private String cab_pickup;
    private String cab_drop;
    private String cab_city_id;
    private String cab_city;
    private String cab_city_others;
    private String cab_date;
    private String cab_time_hrs;
    private String cab_time_min;
    private String cab_remarks;
    private String cab_deleted;
    private String cab_id;
    
    private String[] arr_advance_id;
    private String[] arr_advance_date;
    private String[] arr_advance_amount;
    private String[] arr_advance_remarks;
    private String[] arr_currency_id;
    private String[] arr_currency_name;
    private String[] arr_deposited_amount;
    private String[] arr_deposited_currency;
    private String[] arr_deposited_date;
    private String[] arr_depositor_remarks;
    private String[] arr_advance_deleted;
    
    private String advance_id;
    private String advance_date;
    private String advance_amount;
    private String advance_remarks;
    private String advance_deleted;
    private String currency_id;
    private String currency_name;
    private String deposited_amount;
    private String deposited_currency;
    private String deposited_date;
    private String depositor_remarks;
        
    private String admin_action;
    private String deviation;
    private String treasury_action;
    private String approver_type;
    private String list_type;
    private String file_id;
    private String file_name;
    
    public String getMaster_id() {
        return master_id;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public String getTp_reference_id() {
        return tp_reference_id;
    }

    public void setTp_reference_id(String tp_reference_id) {
        this.tp_reference_id = tp_reference_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getBand_name() {
        return band_name;
    }

    public void setBand_name(String band_name) {
        this.band_name = band_name;
    }

    public String getBand_id() {
        return band_id;
    }

    public void setBand_id(String band_id) {
        this.band_id = band_id;
    }

    public String getDesignation_id() {
        return designation_id;
    }

    public void setDesignation_id(String designation_id) {
        this.designation_id = designation_id;
    }

    public String getDesignation_name() {
        return designation_name;
    }

    public void setDesignation_name(String designation_name) {
        this.designation_name = designation_name;
    }

    public String getRm_id() {
        return rm_id;
    }

    public void setRm_id(String rm_id) {
        this.rm_id = rm_id;
    }

    public String getPm_id() {
        return pm_id;
    }

    public void setPm_id(String pm_id) {
        this.pm_id = pm_id;
    }

    public String getRm_name() {
        return rm_name;
    }

    public void setRm_name(String rm_name) {
        this.rm_name = rm_name;
    }

    public String getPm_name() {
        return pm_name;
    }

    public void setPm_name(String pm_name) {
        this.pm_name = pm_name;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getBuh_id() {
        return buh_id;
    }

    public void setBuh_id(String buh_id) {
        this.buh_id = buh_id;
    }

    public String getBuh_name() {
        return buh_name;
    }

    public void setBuh_name(String buh_name) {
        this.buh_name = buh_name;
    }

    public String getRequested_date() {
        return requested_date;
    }

    public void setRequested_date(String requested_date) {
        this.requested_date = requested_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getSub_unit_id() {
        return sub_unit_id;
    }

    public void setSub_unit_id(String sub_unit_id) {
        this.sub_unit_id = sub_unit_id;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getSub_unit_name() {
        return sub_unit_name;
    }

    public void setSub_unit_name(String sub_unit_name) {
        this.sub_unit_name = sub_unit_name;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getTravel_start_date() {
        return travel_start_date;
    }

    public void setTravel_start_date(String travel_start_date) {
        this.travel_start_date = travel_start_date;
    }

    public String getTravel_end_date() {
        return travel_end_date;
    }

    public void setTravel_end_date(String travel_end_date) {
        this.travel_end_date = travel_end_date;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getTravel_billable() {
        return travel_billable;
    }

    public void setTravel_billable(String travel_billable) {
        this.travel_billable = travel_billable;
    }

    public String getCustomer_others() {
        return customer_others;
    }

    public void setCustomer_others(String customer_others) {
        this.customer_others = customer_others;
    }

    public String getProject_others() {
        return project_others;
    }

    public void setProject_others(String project_others) {
        this.project_others = project_others;
    }

    public String getClient_reimbursable() {
        return client_reimbursable;
    }

    public void setClient_reimbursable(String client_reimbursable) {
        this.client_reimbursable = client_reimbursable;
    }

    public String getSettlemet_policy() {
        return settlemet_policy;
    }

    public void setSettlemet_policy(String settlemet_policy) {
        this.settlemet_policy = settlemet_policy;
    }

    public String[] getTicket_booking_type() {
        return ticket_booking_type;
    }

    public void setTicket_booking_type(String[] ticket_booking_type) {
        this.ticket_booking_type = ticket_booking_type;
    }

    public String[] getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String[] from_city) {
        this.from_city = from_city;
    }

    public String[] getFrom_city_id() {
        return from_city_id;
    }

    public void setFrom_city_id(String[] from_city_id) {
        this.from_city_id = from_city_id;
    }

    public String[] getFrom_city_others() {
        return from_city_others;
    }

    public void setFrom_city_others(String[] from_city_others) {
        this.from_city_others = from_city_others;
    }

    public String[] getTo_city() {
        return to_city;
    }

    public void setTo_city(String[] to_city) {
        this.to_city = to_city;
    }

    public String[] getTo_city_id() {
        return to_city_id;
    }

    public void setTo_city_id(String[] to_city_id) {
        this.to_city_id = to_city_id;
    }

    public String[] getTo_city_others() {
        return to_city_others;
    }

    public void setTo_city_others(String[] to_city_others) {
        this.to_city_others = to_city_others;
    }

    public String[] getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(String[] travel_date) {
        this.travel_date = travel_date;
    }

    public String[] getTravel_preference() {
        return travel_preference;
    }

    public void setTravel_preference(String[] travel_preference) {
        this.travel_preference = travel_preference;
    }

    public String[] getTravel_mode() {
        return travel_mode;
    }

    public void setTravel_mode(String[] travel_mode) {
        this.travel_mode = travel_mode;
    }

    public String[] getTicket_remarks() {
        return ticket_remarks;
    }

    public void setTicket_remarks(String[] ticket_remarks) {
        this.ticket_remarks = ticket_remarks;
    }

    public String[] getTicket_deleted() {
        return ticket_deleted;
    }

    public void setTicket_deleted(String[] ticket_deleted) {
        this.ticket_deleted = ticket_deleted;
    }

    public String[] getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String[] ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getTicket_book_type() {
        return ticket_book_type;
    }

    public void setTicket_book_type(String ticket_book_type) {
        this.ticket_book_type = ticket_book_type;
    }

    public String getTravel_from_city() {
        return travel_from_city;
    }

    public void setTravel_from_city(String travel_from_city) {
        this.travel_from_city = travel_from_city;
    }

    public String getTravel_from_city_id() {
        return travel_from_city_id;
    }

    public void setTravel_from_city_id(String travel_from_city_id) {
        this.travel_from_city_id = travel_from_city_id;
    }

    public String getTravel_from_city_others() {
        return travel_from_city_others;
    }

    public void setTravel_from_city_others(String travel_from_city_others) {
        this.travel_from_city_others = travel_from_city_others;
    }

    public String getTravel_to_city() {
        return travel_to_city;
    }

    public void setTravel_to_city(String travel_to_city) {
        this.travel_to_city = travel_to_city;
    }

    public String getTravel_to_city_id() {
        return travel_to_city_id;
    }

    public void setTravel_to_city_id(String travel_to_city_id) {
        this.travel_to_city_id = travel_to_city_id;
    }

    public String getTravel_to_city_others() {
        return travel_to_city_others;
    }

    public void setTravel_to_city_others(String travel_to_city_others) {
        this.travel_to_city_others = travel_to_city_others;
    }

    public String getTravel_travel_date() {
        return travel_travel_date;
    }

    public void setTravel_travel_date(String travel_travel_date) {
        this.travel_travel_date = travel_travel_date;
    }

    public String getTravel_ticket_date() {
        return travel_ticket_date;
    }

    public void setTravel_ticket_date(String travel_ticket_date) {
        this.travel_ticket_date = travel_ticket_date;
    }

    public String getTravel_travel_preference() {
        return travel_travel_preference;
    }

    public void setTravel_travel_preference(String travel_travel_preference) {
        this.travel_travel_preference = travel_travel_preference;
    }

    public String getTravel_travel_mode() {
        return travel_travel_mode;
    }

    public void setTravel_travel_mode(String travel_travel_mode) {
        this.travel_travel_mode = travel_travel_mode;
    }

    public String getTravel_ticket_remarks() {
        return travel_ticket_remarks;
    }

    public void setTravel_ticket_remarks(String travel_ticket_remarks) {
        this.travel_ticket_remarks = travel_ticket_remarks;
    }

    public String getTravel_ticket_id() {
        return travel_ticket_id;
    }

    public void setTravel_ticket_id(String travel_ticket_id) {
        this.travel_ticket_id = travel_ticket_id;
    }

    public String getTravel_ticketdeleted() {
        return travel_ticketdeleted;
    }

    public void setTravel_ticketdeleted(String travel_ticketdeleted) {
        this.travel_ticketdeleted = travel_ticketdeleted;
    }

    public int getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(int ticket_count) {
        this.ticket_count = ticket_count;
    }

    public String[] getHotel_booking_type() {
        return hotel_booking_type;
    }

    public void setHotel_booking_type(String[] hotel_booking_type) {
        this.hotel_booking_type = hotel_booking_type;
    }

    public String[] getCity() {
        return city;
    }

    public void setCity(String[] city) {
        this.city = city;
    }

    public String[] getCity_id() {
        return city_id;
    }

    public void setCity_id(String[] city_id) {
        this.city_id = city_id;
    }

    public String[] getCity_others() {
        return city_others;
    }

    public void setCity_others(String[] city_others) {
        this.city_others = city_others;
    }

    public String[] getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String[] from_date) {
        this.from_date = from_date;
    }

    public String[] getTo_date() {
        return to_date;
    }

    public void setTo_date(String[] to_date) {
        this.to_date = to_date;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public String[] getHotel_remarks() {
        return hotel_remarks;
    }

    public void setHotel_remarks(String[] hotel_remarks) {
        this.hotel_remarks = hotel_remarks;
    }

    public String[] getHotel_deleted() {
        return hotel_deleted;
    }

    public void setHotel_deleted(String[] hotel_deleted) {
        this.hotel_deleted = hotel_deleted;
    }

    public String[] getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String[] hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_book_type() {
        return hotel_book_type;
    }

    public void setHotel_book_type(String hotel_book_type) {
        this.hotel_book_type = hotel_book_type;
    }

    public String getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    public String getHotel_city_id() {
        return hotel_city_id;
    }

    public void setHotel_city_id(String hotel_city_id) {
        this.hotel_city_id = hotel_city_id;
    }

    public String getHotel_city_others() {
        return hotel_city_others;
    }

    public void setHotel_city_others(String hotel_city_others) {
        this.hotel_city_others = hotel_city_others;
    }

    public String getHotel_from_date() {
        return hotel_from_date;
    }

    public void setHotel_from_date(String hotel_from_date) {
        this.hotel_from_date = hotel_from_date;
    }

    public String getHotel_to_date() {
        return hotel_to_date;
    }

    public void setHotel_to_date(String hotel_to_date) {
        this.hotel_to_date = hotel_to_date;
    }

    public String getHotel_location() {
        return hotel_location;
    }

    public void setHotel_location(String hotel_location) {
        this.hotel_location = hotel_location;
    }

    public String getHotel_hotel_remarks() {
        return hotel_hotel_remarks;
    }

    public void setHotel_hotel_remarks(String hotel_hotel_remarks) {
        this.hotel_hotel_remarks = hotel_hotel_remarks;
    }

    public String getHoteldeleted() {
        return hoteldeleted;
    }

    public void setHoteldeleted(String hoteldeleted) {
        this.hoteldeleted = hoteldeleted;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public int getHotel_count() {
        return hotel_count;
    }

    public void setHotel_count(int hotel_count) {
        this.hotel_count = hotel_count;
    }

    public String[] getPickup() {
        return pickup;
    }

    public void setPickup(String[] pickup) {
        this.pickup = pickup;
    }

    public String[] getDropto() {
        return dropto;
    }

    public void setDropto(String[] dropto) {
        this.dropto = dropto;
    }

    public String[] getStart_date() {
        return start_date;
    }

    public void setStart_date(String[] start_date) {
        this.start_date = start_date;
    }

    public String[] getTime_hr() {
        return time_hr;
    }

    public void setTime_hr(String[] time_hr) {
        this.time_hr = time_hr;
    }

    public String[] getTime_min() {
        return time_min;
    }

    public void setTime_min(String[] time_min) {
        this.time_min = time_min;
    }

    public String[] getConveyance_remarks() {
        return conveyance_remarks;
    }

    public void setConveyance_remarks(String[] conveyance_remarks) {
        this.conveyance_remarks = conveyance_remarks;
    }

    public String[] getConveyance_city() {
        return conveyance_city;
    }

    public void setConveyance_city(String[] conveyance_city) {
        this.conveyance_city = conveyance_city;
    }

    public String[] getConveyance_city_id() {
        return conveyance_city_id;
    }

    public void setConveyance_city_id(String[] conveyance_city_id) {
        this.conveyance_city_id = conveyance_city_id;
    }

    public String[] getConveyance_city_others() {
        return conveyance_city_others;
    }

    public void setConveyance_city_others(String[] conveyance_city_others) {
        this.conveyance_city_others = conveyance_city_others;
    }

    public String[] getConveyancedeleted() {
        return conveyancedeleted;
    }

    public void setConveyancedeleted(String[] conveyancedeleted) {
        this.conveyancedeleted = conveyancedeleted;
    }

    public String[] getConveyance_id() {
        return conveyance_id;
    }

    public void setConveyance_id(String[] conveyance_id) {
        this.conveyance_id = conveyance_id;
    }

    public String getCab_pickup() {
        return cab_pickup;
    }

    public void setCab_pickup(String cab_pickup) {
        this.cab_pickup = cab_pickup;
    }

    public String getCab_drop() {
        return cab_drop;
    }

    public void setCab_drop(String cab_drop) {
        this.cab_drop = cab_drop;
    }

    public String getCab_city_id() {
        return cab_city_id;
    }

    public void setCab_city_id(String cab_city_id) {
        this.cab_city_id = cab_city_id;
    }

    public String getCab_city() {
        return cab_city;
    }

    public void setCab_city(String cab_city) {
        this.cab_city = cab_city;
    }

    public String getCab_city_others() {
        return cab_city_others;
    }

    public void setCab_city_others(String cab_city_others) {
        this.cab_city_others = cab_city_others;
    }

    public String getCab_date() {
        return cab_date;
    }

    public void setCab_date(String cab_date) {
        this.cab_date = cab_date;
    }

    public String getCab_time_hrs() {
        return cab_time_hrs;
    }

    public void setCab_time_hrs(String cab_time_hrs) {
        this.cab_time_hrs = cab_time_hrs;
    }

    public String getCab_time_min() {
        return cab_time_min;
    }

    public void setCab_time_min(String cab_time_min) {
        this.cab_time_min = cab_time_min;
    }

    public String getCab_remarks() {
        return cab_remarks;
    }

    public void setCab_remarks(String cab_remarks) {
        this.cab_remarks = cab_remarks;
    }

    public String getCab_deleted() {
        return cab_deleted;
    }

    public void setCab_deleted(String cab_deleted) {
        this.cab_deleted = cab_deleted;
    }

    public String getCab_id() {
        return cab_id;
    }

    public void setCab_id(String cab_id) {
        this.cab_id = cab_id;
    }

    public String getAdvance_date() {
        return advance_date;
    }

    public void setAdvance_date(String advance_date) {
        this.advance_date = advance_date;
    }

    public String getAdvance_amount() {
        return advance_amount;
    }

    public void setAdvance_amount(String advance_amount) {
        this.advance_amount = advance_amount;
    }

    public String getAdvance_remarks() {
        return advance_remarks;
    }

    public void setAdvance_remarks(String advance_remarks) {
        this.advance_remarks = advance_remarks;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getAdmin_action() {
        return admin_action;
    }

    public void setAdmin_action(String admin_action) {
        this.admin_action = admin_action;
    }

    public String getDeviation() {
        return deviation;
    }

    public void setDeviation(String deviation) {
        this.deviation = deviation;
    }

    public String getTreasury_action() {
        return treasury_action;
    }

    public void setTreasury_action(String treasury_action) {
        this.treasury_action = treasury_action;
    }

    public String getTravel_purpose() {
        return travel_purpose;
    }

    public void setTravel_purpose(String travel_purpose) {
        this.travel_purpose = travel_purpose;
    }

    public String getTravel_term() {
        return travel_term;
    }

    public void setTravel_term(String travel_term) {
        this.travel_term = travel_term;
    }

    public String getGuest_booking() {
        return guest_booking;
    }

    public void setGuest_booking(String guest_booking) {
        this.guest_booking = guest_booking;
    }

    public String getGuest_booking_name() {
        return guest_booking_name;
    }

    public void setGuest_booking_name(String guest_booking_name) {
        this.guest_booking_name = guest_booking_name;
    }

    public String getAdvance_id() {
        return advance_id;
    }

    public void setAdvance_id(String advance_id) {
        this.advance_id = advance_id;
    }

    public String getConfig_key() {
        return config_key;
    }

    public void setConfig_key(String config_key) {
        this.config_key = config_key;
    }

    public String getConfig_value() {
        return config_value;
    }

    public void setConfig_value(String config_value) {
        this.config_value = config_value;
    }

    public String getDeposited_amount() {
        return deposited_amount;
    }

    public void setDeposited_amount(String deposited_amount) {
        this.deposited_amount = deposited_amount;
    }

    public String getDeposited_currency() {
        return deposited_currency;
    }

    public void setDeposited_currency(String deposited_currency) {
        this.deposited_currency = deposited_currency;
    }

    public String getDeposited_date() {
        return deposited_date;
    }

    public void setDeposited_date(String deposited_date) {
        this.deposited_date = deposited_date;
    }

    public String getDepositor_remarks() {
        return depositor_remarks;
    }

    public void setDepositor_remarks(String depositor_remarks) {
        this.depositor_remarks = depositor_remarks;
    }
    public String getRm_approved_date() {
        return rm_approved_date;
    }

    public void setRm_approved_date(String rm_approved_date) {
        this.rm_approved_date = rm_approved_date;
    }

    public String getRm_remarks() {
        return rm_remarks;
    }

    public void setRm_remarks(String rm_remarks) {
        this.rm_remarks = rm_remarks;
    }

    public String getBuh_approved_date() {
        return buh_approved_date;
    }

    public void setBuh_approved_date(String buh_approved_date) {
        this.buh_approved_date = buh_approved_date;
    }

    public String getBuh_remarks() {
        return buh_remarks;
    }

    public void setBuh_remarks(String buh_remarks) {
        this.buh_remarks = buh_remarks;
    }

    public String getTreasury_id() {
        return treasury_id;
    }

    public void setTreasury_id(String treasury_id) {
        this.treasury_id = treasury_id;
    }

    public String getTreasury_name() {
        return treasury_name;
    }

    public void setTreasury_name(String treasury_name) {
        this.treasury_name = treasury_name;
    }

    public String getTreasury_approved_date() {
        return treasury_approved_date;
    }

    public void setTreasury_approved_date(String treasury_approved_date) {
        this.treasury_approved_date = treasury_approved_date;
    }

    public String getTreasury_comments() {
        return treasury_comments;
    }

    public void setTreasury_comments(String treasury_comments) {
        this.treasury_comments = treasury_comments;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_approved_date() {
        return admin_approved_date;
    }

    public void setAdmin_approved_date(String admin_approved_date) {
        this.admin_approved_date = admin_approved_date;
    }

    public String getAdmin_remarks() {
        return admin_remarks;
    }

    public void setAdmin_remarks(String admin_remarks) {
        this.admin_remarks = admin_remarks;
    }

    public String getFinance_id() {
        return finance_id;
    }

    public void setFinance_id(String finance_id) {
        this.finance_id = finance_id;
    }

    public String getFinance_name() {
        return finance_name;
    }

    public void setFinance_name(String finance_name) {
        this.finance_name = finance_name;
    }

    public String getFinance_approved_date() {
        return finance_approved_date;
    }

    public void setFinance_approved_date(String finance_approved_date) {
        this.finance_approved_date = finance_approved_date;
    }

    public String getFinance_remarks() {
        return finance_remarks;
    }

    public void setFinance_remarks(String finance_remarks) {
        this.finance_remarks = finance_remarks;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getApprover_type() {
        return approver_type;
    }

    public void setApprover_type(String approver_type) {
        this.approver_type = approver_type;
    }

    public String getList_type() {
        return list_type;
    }

    public void setList_type(String list_type) {
        this.list_type = list_type;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String[] getTravel_booking_status() {
        return travel_booking_status;
    }

    public void setTravel_booking_status(String[] travel_booking_status) {
        this.travel_booking_status = travel_booking_status;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public String[] getHotel_booking_status() {
        return hotel_booking_status;
    }

    public void setHotel_booking_status(String[] hotel_booking_status) {
        this.hotel_booking_status = hotel_booking_status;
    }

    public String[] getConveyance_booking_status() {
        return conveyance_booking_status;
    }

    public void setConveyance_booking_status(String[] conveyance_booking_status) {
        this.conveyance_booking_status = conveyance_booking_status;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getBooking_status_name() {
        return booking_status_name;
    }

    public void setBooking_status_name(String booking_status_name) {
        this.booking_status_name = booking_status_name;
    }

    public String getTicket_book_config() {
        return ticket_book_config;
    }

    public void setTicket_book_config(String ticket_book_config) {
        this.ticket_book_config = ticket_book_config;
    }

    public String[] getArr_advance_id() {
        return arr_advance_id;
    }

    public void setArr_advance_id(String[] arr_advance_id) {
        this.arr_advance_id = arr_advance_id;
    }

    public String[] getArr_advance_date() {
        return arr_advance_date;
    }

    public void setArr_advance_date(String[] arr_advance_date) {
        this.arr_advance_date = arr_advance_date;
    }

    public String[] getArr_advance_amount() {
        return arr_advance_amount;
    }

    public void setArr_advance_amount(String[] arr_advance_amount) {
        this.arr_advance_amount = arr_advance_amount;
    }

    public String[] getArr_advance_remarks() {
        return arr_advance_remarks;
    }

    public void setArr_advance_remarks(String[] arr_advance_remarks) {
        this.arr_advance_remarks = arr_advance_remarks;
    }

    public String[] getArr_currency_id() {
        return arr_currency_id;
    }

    public void setArr_currency_id(String[] arr_currency_id) {
        this.arr_currency_id = arr_currency_id;
    }

    public String[] getArr_currency_name() {
        return arr_currency_name;
    }

    public void setArr_currency_name(String[] arr_currency_name) {
        this.arr_currency_name = arr_currency_name;
    }

    public String[] getArr_deposited_amount() {
        return arr_deposited_amount;
    }

    public void setArr_deposited_amount(String[] arr_deposited_amount) {
        this.arr_deposited_amount = arr_deposited_amount;
    }

    public String[] getArr_deposited_currency() {
        return arr_deposited_currency;
    }

    public void setArr_deposited_currency(String[] arr_deposited_currency) {
        this.arr_deposited_currency = arr_deposited_currency;
    }

    public String[] getArr_deposited_date() {
        return arr_deposited_date;
    }

    public void setArr_deposited_date(String[] arr_deposited_date) {
        this.arr_deposited_date = arr_deposited_date;
    }

    public String[] getArr_depositor_remarks() {
        return arr_depositor_remarks;
    }

    public void setArr_depositor_remarks(String[] arr_depositor_remarks) {
        this.arr_depositor_remarks = arr_depositor_remarks;
    }

    public String[] getArr_advance_deleted() {
        return arr_advance_deleted;
    }

    public void setArr_advance_deleted(String[] arr_advance_deleted) {
        this.arr_advance_deleted = arr_advance_deleted;
    }

    public String getAdvance_deleted() {
        return advance_deleted;
    }

    public void setAdvance_deleted(String advance_deleted) {
        this.advance_deleted = advance_deleted;
    }

    public String getCancel_remarks() {
        return cancel_remarks;
    }

    public void setCancel_remarks(String cancel_remarks) {
        this.cancel_remarks = cancel_remarks;
    }

    public String getMail_id() {
        return mail_id;
    }

    public void setMail_id(String mail_id) {
        this.mail_id = mail_id;
    }

    public String getPrevious_status() {
        return previous_status;
    }

    public void setPrevious_status(String previous_status) {
        this.previous_status = previous_status;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }
    
}
