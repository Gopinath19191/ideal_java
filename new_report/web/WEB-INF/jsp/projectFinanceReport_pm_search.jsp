<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<% java.util.List list = (java.util.List) request.getAttribute("pmList");
   java.util.Iterator<com.defiance.ideal.reports.dto.ProjectFinanceReportDetails> itr = list.iterator();
   com.defiance.ideal.reports.dto.ProjectFinanceReportDetails td = null;     
   while(itr.hasNext()){
    td= itr.next();
    out.println(td.getEmployee_number() + "-" + td.getFirst_name() + " " + td.getLast_name());    
   } 
%>
