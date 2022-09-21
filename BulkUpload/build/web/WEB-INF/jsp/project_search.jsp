<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<% java.util.List lis = (java.util.List) request.getAttribute("projectList");
   java.util.Iterator<com.ideal.pc.ticket.dto.PcDataDTO> it = lis.iterator();
   com.ideal.pc.ticket.dto.PcDataDTO dt = null;
   while(it.hasNext()){
    dt= (com.ideal.pc.ticket.dto.PcDataDTO)it.next();
    out.println(dt.getProjectName());
   }
%>