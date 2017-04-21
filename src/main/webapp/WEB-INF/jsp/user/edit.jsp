<%--
  Created by IntelliJ IDEA.
  User: Anton_Serediuk
  Date: 4/21/2017
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<h1>Edit user</h1>
<form method="POST" action="edit">
    ID: <input type="text" name="id" value="${user.id}" readonly><br/>
    Name: <input type="text" name="name" value="${user.name}"><br/>
    First name: <input type="text" name="firstName" value="${user.firstName}"><br/>
    Last name: <input type="text" name="lastName" value="${user.lastName}"><br/>
    <input type="submit" value="Edit"/>
</form>

</body>
</html>
