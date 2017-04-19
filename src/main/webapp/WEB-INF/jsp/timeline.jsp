<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1>Login: ${user.name}</h1>
<c:forEach var="tweet" items="${timeline.tweets}">
    Likes: ${tweet.likesCount}<br/>
    ${tweet.text}<br/>
    <hr/>
</c:forEach>


</body>
</html>