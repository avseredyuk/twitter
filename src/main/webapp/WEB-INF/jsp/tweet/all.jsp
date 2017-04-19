<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1>Global feed</h1>
<table width="100%">
    <c:forEach var="tweet" items="${tweets}">
        <tr>
            <td colspan="3">
                <hr/>
                    ${tweet.text}
            </td>
        </tr>
        <tr>
            <td width="15%">
                <b>${tweet.user.name}</b>
            </td>
            <td width="60%">
                Likes: ${tweet.likesCount}
            </td>
            <td width="15%">
                <form method="post" action="delete">
                    <input type="hidden" name="id" value="${tweet.id}">
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>