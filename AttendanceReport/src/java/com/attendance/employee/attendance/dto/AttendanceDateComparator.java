/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 16047
 */
public class AttendanceDateComparator implements Comparator<AttendanceDto>{

    @Override
    public int compare(AttendanceDto a1, AttendanceDto a2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date1 = null;
        try {
            date1 = sdf.parse(a1.getAttendanceDate());
        } catch (ParseException ex) {
            Logger.getLogger(MyAttendanceComp.class.getName()).log(Level.SEVERE, null, ex);
        }
                Date date2 = null;
        try {
            date2 = sdf.parse(a2.getAttendanceDate());
        } catch (ParseException ex) {
            Logger.getLogger(MyAttendanceComp.class.getName()).log(Level.SEVERE, null, ex);
        }
//        if(date1.after(date2)){
//            return -1;
//        } else {
//            return 1;
//        }
        
         return date2.compareTo(date1); 
    }
    
}
