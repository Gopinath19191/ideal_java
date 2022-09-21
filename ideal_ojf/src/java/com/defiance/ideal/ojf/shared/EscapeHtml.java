/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.ojf.shared;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author Administrator
 */
public class EscapeHtml {

    public EscapeHtml(Object pageClass){
        try {

            Class c = Class.forName(pageClass.getClass().getCanonicalName());

            Method[] method = c.getDeclaredMethods();

            for(int i=0;i<method.length;i++){
                if(method[i].getName().startsWith("g")){                    
                    Object obj = method[i].invoke(pageClass,null);
                    String methodName = method[i].getName();
                    methodName = methodName.substring(3,methodName.length());
                   if(obj!=null){
                       System.out.println("methodName = " + method[i].getReturnType().toString());
                      if(!(method[i].getReturnType().toString().equals("int")) && !method[i].getName().equals("getotherProof") && !(method[i].getReturnType().toString().equals("FormFile")) && !(method[i].getReturnType().toString().equals("List")) && !(method[i].getReturnType().toString().equals("void")) && !(method[i].getName().equals("getOtherProof")))
                      {//                          System.out.println("i = " + method[i].getName());
                            String s = obj.toString();
                            s = StringEscapeUtils.escapeHtml(s);
                            Class[] types = new Class[] {String.class};
                            Method setterMethod = c.getMethod("set"+methodName, types);
                            setterMethod.invoke(pageClass, s);
                      }

                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EscapeHtml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EscapeHtml(){

    }

    public void unEscapeHtml(Object pageClass){
         try {
            Class c = Class.forName(pageClass.getClass().getCanonicalName());

            Method[] method = c.getDeclaredMethods();
            for(int i=0;i<method.length;i++){
                if(method[i].getName().startsWith("g")){
                    Object obj = method[i].invoke(pageClass,null);
                    String methodName = method[i].getName();
                    methodName = methodName.substring(3,methodName.length());
                   if(obj!=null){
                      if(!(method[i].getReturnType().toString().equals("int"))){
                            String s = obj.toString();
                            s = StringEscapeUtils.unescapeHtml(s);
                            Class[] types = new Class[] {String.class};
                            Method setterMethod = c.getMethod("set"+methodName, types);
                            setterMethod.invoke(pageClass, s);
                      }

                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EscapeHtml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
