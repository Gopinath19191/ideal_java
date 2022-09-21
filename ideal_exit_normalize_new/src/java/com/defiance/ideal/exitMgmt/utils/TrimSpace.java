/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.exitMgmt.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Divya
 */
public class TrimSpace {

    public static void trimObject(final Object pageClass) {
        try {
            final Class cls = Class.forName(pageClass.getClass().getCanonicalName());
            final Method[] method = cls.getDeclaredMethods();
            for (int i = 0; i < method.length; i++) {
                if (method[i].getName().startsWith("g")) {
                    final Object obj = method[i].invoke(pageClass);
                    String methodName = method[i].getName();
                    methodName = methodName.substring(3, methodName.length());
                    if (obj != null) {
                        if (!(method[i].getReturnType().toString().equals("int"))) {
                            final String str = obj.toString().trim();
                            System.out.println("=== The string value is = "+str +" method name is = "+method[i]);
                            final Class[] types = new Class[]{String.class};
                            final Method setterMethod = cls.getMethod("set" + methodName, types);
                            setterMethod.invoke(pageClass, str);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *  replaceNull - Method to replace null objects in an DTO
     * @param pageClass - DTO object
     * @param intValue  - Value to be replaced when int is null
     * @param strValue  - Value to be replaced when String is null
     * @param flag      - boolean array for setting int and String value
     * @param fieldName - field name in array whose value has to replaced.
     */
    public static void replaceNull(final Object pageClass,int intValue,String strValue,boolean[] flag,String[] fieldName){
       try {
            final Class cls = Class.forName(pageClass.getClass().getCanonicalName());
            final Method[] method = cls.getDeclaredMethods();
            for (int i = 0; i < method.length; i++) {
                if (method[i].getName().startsWith("g")) {
                    if(CommonFunctions.isStringInArray(method[i].getName().replace("get",""), fieldName,true))
                    {
                    final Object obj = method[i].invoke(pageClass);
                    String methodName = method[i].getName();
                    methodName = methodName.substring(3, methodName.length());
                    if (obj == null) {
                        if ((method[i].getReturnType().toString().contains("int")) && flag[0]) {
                            final Class[] types = new Class[]{String.class};
                            final Method setterMethod = cls.getMethod("set" + methodName, types);
                            setterMethod.invoke(pageClass, intValue);
                        }
                        if ((method[i].getReturnType().toString().contains("String")) && flag[1]) {
                            final Class[] types = new Class[]{String.class};
                            final Method setterMethod = cls.getMethod("set" + methodName, types);
                            setterMethod.invoke(pageClass, strValue);
                        }

                    }
                }
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    /**
     *  replaceNull - Method to replace null objects in an DTO
     * @param pageClass - DTO object
     * @param intValue  - Value to be replaced when int in null
     * @param strValue  - Value to be replaced when String is null
     * @param flag      - boolean array for setting int and String value
     * @param fieldName - field name in array whose value has to replaced.
     */
    public static List getDuePendingValues(final Object pageClass, int intValue, String strValue, boolean[] flag, String[] fieldName, String[] displayName,String dueValue) {
        List formNoValue = new ArrayList();
        Map mapObj = new HashMap();
         for(int k=0;k <displayName.length;k++){
             mapObj.put(fieldName[k],displayName[k]);
         }
        try {
            final Class cls = Class.forName(pageClass.getClass().getCanonicalName());
            final Method[] method = cls.getDeclaredMethods();
            for (int i = 0; i < method.length; i++) {
                if (method[i].getName().startsWith("g")) {
                    if (CommonFunctions.isStringInArray(method[i].getName().replace("get", ""), fieldName, true)) {
                        String methodName = method[i].getName();
                        methodName = methodName.substring(3, methodName.length());
                        final Method getterMethod = cls.getMethod("get" + methodName);
                        if (getterMethod.invoke(pageClass).equals(dueValue)) {
//                            formNoValue.add(methodName);
//                            System.out.println(":::::"+mapObj.get(methodName));
                            if(!mapObj.get(methodName).equals("QPD completed")){
                                formNoValue.add(mapObj.get(methodName));
                            }
                        }
                    }
                }
            }
             System.out.println("For Checking ::::"+formNoValue.size());
//                for(int j=0;j<formNoValue.size();j++){
//                    System.out.println(":::::"+formNoValue.get(j).toString());
//                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formNoValue;
    }
}
