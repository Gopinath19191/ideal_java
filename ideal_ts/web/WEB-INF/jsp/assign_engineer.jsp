<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<% java.util.List lis = (java.util.List) request.getAttribute("empNameList");
   java.util.Iterator<com.ideal.admin.ticket.dto.TicAdminDataDTO> it = lis.iterator();
   com.ideal.admin.ticket.dto.TicAdminDataDTO dt = null;
   while(it.hasNext()){
    dt= (com.ideal.admin.ticket.dto.TicAdminDataDTO)it.next();
    out.println(dt.getEmpName());
   }
%>