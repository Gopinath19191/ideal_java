/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.exitMgmt.utils;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.apache.commons.lang.StringEscapeUtils;

public class EscapeHtml {

    public static void escapeHtml(final Object pageClass) {
        try {
            final Class cls = Class.forName(pageClass.getClass().getCanonicalName());
            final Method[] method = cls.getDeclaredMethods();
            for (int i = 0; i < method.length; i++) {
                if (method[i].getName().startsWith("g")) {
                    final Object obj = method[i].invoke(pageClass);
                    String methodName = method[i].getName();
                    methodName = methodName.substring(3, methodName.length());
                    if ((obj != null) && (!(method[i].getReturnType().toString().equals("int")))) {
                        String str = obj.toString();
                        str = StringEscapeUtils.escapeHtml(str);
                        final Class[] types = new Class[]{String.class};
                        final Method setterMethod = cls.getMethod("set" + methodName, types);
                        setterMethod.invoke(pageClass, str);
                    }
                }
            }
        } catch (Exception e) {
            //Logger.getLogger(TrimSpace.class.getName()).error(e);
        }
    }

    public static void unEscapeHtml(final Object pageClass) {
        try {
            final Class cls = Class.forName(pageClass.getClass().getCanonicalName());
            final Method[] method = cls.getDeclaredMethods();
            for (int i = 0; i < method.length; i++) {
                if (method[i].getName().startsWith("g")) {
                    final Object obj = method[i].invoke(pageClass);
                    String methodName = method[i].getName();
                    methodName = methodName.substring(3, methodName.length());
                    if ((obj != null) && (!(method[i].getReturnType().toString().equals("int")))) {
                        String str = obj.toString();
                        str = StringEscapeUtils.unescapeHtml(str);
                        final Class[] types = new Class[]{String.class};
                        final Method setterMethod = cls.getMethod("set" + methodName, types);
                        setterMethod.invoke(pageClass, str);
                    }
                }
            }
        } catch (Exception e) {
            //Logger.getLogger(EscapeHtml.class.getName()).error(e);
        }
    }
}
