<%--
  Created by IntelliJ IDEA.
  User: Anton_Serediuk
  Date: 4/14/2017
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>
<h1>Create user</h1>
<form method="POST" action="create">
    ID: <input type="text" name="id" value="${user.id}"><br/>
    Name: <input type="text" name="name"><br/>
    First name: <input type="text" name="firstName"><br/>
    Last name: <input type="text" name="lastName"><br/>
    <input type="submit" value="Edit"/>
</form>
</body>
</html>
