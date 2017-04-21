<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>
        All users
    </title>
</head>
<body>
<h1>All users</h1>
<table width="100%">
<c:forEach var="user" items="${users}">
    <tr>
        <td width="15%">
            ${user.id}
        </td>
        <td width="15%">
            ${user.name}
        </td>
        <td width="20%">
                ${user.firstName}
        </td>
        <td width="20%">
                ${user.lastName}
        </td>
        <td width="15%">
            <a href="/twitter/web/timeline/${user.name}">
                Timeline
            </a>
        </td>
        <td>
            <a href="/twitter/web/user/${user.name}/edit">
                Edit
            </a>
        </td>
    </tr>
</c:forEach>
</table>
<a href="create">Create new user</a> | <a href="/twitter/web/tweet/all">All tweets</a>
</body>
</html>