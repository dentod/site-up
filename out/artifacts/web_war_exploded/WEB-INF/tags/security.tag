<%@tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@attribute name="allowRoles" type="java.lang.String" %>
<%if(request.isUserInRole(allowRoles)){%>
<jsp:doBody/>
<%}%>