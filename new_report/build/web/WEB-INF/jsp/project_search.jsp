<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@page import="org.springframework.web.context.WebApplicationContext" %>

<% 
    java.util.List list = (java.util.List) request.getAttribute("projectDetailsList");
    java.util.Iterator<com.defiance.ideal.reports.dto.ProjectDetails> itr = list.iterator();
    com.defiance.ideal.reports.dto.ProjectDetails pd = null;
    //out.println("size- :" + list.size());
    int i=0;
    while(itr.hasNext()){
     pd= itr.next(); 
     out.println(pd.getPstring());
    }
%>
