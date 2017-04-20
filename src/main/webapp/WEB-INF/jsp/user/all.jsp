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
        <td width="70%">
            ${user.name}
        </td>
        <td width="15%">
            <a href="/twitter/web/timeline/${user.name}">
                Timeline
            </a>
        </td>
    </tr>
</c:forEach>
</table>

</body>
</html>