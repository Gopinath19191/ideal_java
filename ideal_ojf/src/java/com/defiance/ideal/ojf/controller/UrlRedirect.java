/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.controller;

/**
 *
 * @author 15858
 */
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UrlRedirect extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, java.io.IOException {
        String contextPath= "http://www.java2s.com";
    response.sendRedirect(response.encodeRedirectURL(contextPath + "/maps"));
  }
  
  public static void main(String[] args) throws ServletException, IOException{
      HttpServletRequest request = null;
      HttpServletResponse response = null;
      UrlRedirect u = new UrlRedirect();
      u.doGet(request,response);
  }
}
 