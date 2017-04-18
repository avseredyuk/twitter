<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<c:forEach var="user" items="${users}">
    ${user.name}<br/>
</c:forEach>


</body>
</html>