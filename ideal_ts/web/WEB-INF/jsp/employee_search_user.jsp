<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<% java.util.List lis = (java.util.List) request.getAttribute("empNameList");
    java.util.Iterator<com.ideal.system.ticket.dto.TicSystemDataDTO> it = lis.iterator();
    com.ideal.system.ticket.dto.TicSystemDataDTO dt = null;
   while(it.hasNext()){
    dt= it.next();
    out.println(dt.getEmpName());
   }
%>