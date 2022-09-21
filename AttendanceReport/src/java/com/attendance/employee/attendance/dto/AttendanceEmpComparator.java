/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dto;

import java.util.Comparator;

/**
 *
 * @author 16047
 */
public class AttendanceEmpComparator implements Comparator<AttendanceDto>{

    @Override
    public int compare(AttendanceDto a1, AttendanceDto a2) {
       int empId1 = 0;
       int empId2 = 0;
       if(a1.getEmpId() != null)
           empId1 = Integer.parseInt(a1.getEmpId());
       if(a2.getEmpId() != null)
           empId2 = Integer.parseInt(a2.getEmpId());
//       if(empId1 < empId2){
//           return -1;
//       }else{
//           return 1;
//       }
       return empId2 - empId1;
    }
    
}
