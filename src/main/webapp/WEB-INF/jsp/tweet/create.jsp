<%--
  Created by IntelliJ IDEA.
  User: Anton_Serediuk
  Date: 4/19/2017
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create tweet</title>
</head>
<body>
<h1>Create tweet</h1>
<form method="POST" action="create">
    Name: <input type="text" name="username"/><br/>
    Text: <textarea name="text"></textarea><br/>
    Reply to tweet: <input type="text" name="replyId"/><br/>
    Retweet of: <input type="text" name="retweetId"/><br/>
    <input type="submit" value="Create"/>
</form>
</body>
</html>

