<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<% java.util.List list = (java.util.List) request.getAttribute("refNoList");
   java.util.Iterator<com.ideal.system.ticket.dto.TicSystemDataDTO> itr = list.iterator();
   com.ideal.system.ticket.dto.TicSystemDataDTO dto = null;
   while(itr.hasNext()){
    dto= itr.next();
    out.println(dto.getRefId());
   }
%>