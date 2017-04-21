<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>
        All tweets
    </title>
</head>
<body>
<h1>Global feed</h1>
<a href="create">Create new tweet</a>
<table width="100%">
    <c:forEach var="tweet" items="${tweets}">
        <c:if test="${not empty tweet.replyTo}">
            <tr>
                <td colspan="4" bgcolor="#a9a9a9">
                    Is reply to Tweet #${tweet.replyTo.id}
                </td>
            </tr>
        </c:if>
        <c:if test="${not empty tweet.retweetOf}">
            <tr>
                <td colspan="4" bgcolor="#a9a9a9">
                    It's retweet
                </td>
            </tr>
            <c:set var="tweet" scope="page" value="${tweet.retweetOf}"/>
        </c:if>
        <tr>
            <td colspan="4" bgcolor="#ffebcd">
                    ${tweet.text}
            </td>
        </tr>
        <tr>
            <td width="15%" bgcolor="#f5f5dc">
                <b>${tweet.user.name}</b>
            </td>
            <td width="30%" bgcolor="#f0ffff">
                Likes: ${tweet.likesCount}
            </td>
            <td width="30%" bgcolor="#f0ffff">
                RT: ${tweet.retweetsCount}
            </td>
            <td width="15%">
                <form method="post" action="delete">
                    <input type="hidden" name="id" value="${tweet.id}">
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
        <tr><td></td></tr>
    </c:forEach>
</table>
</body>
</html>