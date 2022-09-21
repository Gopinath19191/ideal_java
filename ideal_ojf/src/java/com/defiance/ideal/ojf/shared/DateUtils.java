/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DEFINACE
 */
public class DateUtils {

    public static String formatDateEx(final String date) throws java.text.ParseException {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        final Date today = df.parse(date);
        df.format(today);
        System.out.println("Pinrt Daye::" + df.format(today));
        return df.format(today);
    }

    public static void main(String[] args) throws ParseException {
        DateUtils.formatDateEx("2010-06-17 17:57:53");
    }
}
