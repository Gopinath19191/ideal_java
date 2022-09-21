<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<% java.util.List list = (java.util.List) request.getAttribute("projectDetailsList");
   java.util.Iterator<com.defiance.ideal.reports.dto.ProjectFinanceReportDetails> itr = list.iterator();
   com.defiance.ideal.reports.dto.ProjectFinanceReportDetails td = null;
   //out.println("size- :" + list.size());   
   while(itr.hasNext()){
    td= itr.next();
    out.println(td.getProject_code() + " - " + td.getProject_name()); 
   }
%>
