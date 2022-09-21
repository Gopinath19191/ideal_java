/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author 16047
 */
public class AttendanceChainedComparator implements Comparator<AttendanceDto>{
    private List<Comparator<AttendanceDto>> listComparators;
    @SafeVarargs
    public AttendanceChainedComparator(Comparator<AttendanceDto> attendanceDateComp, Comparator<AttendanceDto> attendanceEmpComp) {
        this.listComparators = Arrays.asList(attendanceDateComp,attendanceEmpComp);
    }

    @Override
    public int compare(AttendanceDto a1, AttendanceDto a2) {
        for (Comparator<AttendanceDto> comparator : listComparators) {
            int result = comparator.compare(a1, a2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
