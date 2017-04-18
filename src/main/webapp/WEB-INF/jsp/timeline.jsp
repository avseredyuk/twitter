<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<c:forEach var="tweet" items="${timeline.tweets}">
    ${tweet.text}<br/>
</c:forEach>


</body>
</html>