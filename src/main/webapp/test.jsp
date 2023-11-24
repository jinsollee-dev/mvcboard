<%@ page import="common.MySQConPool" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>MySQL Connect Pool 테스트</h2>
<%
    MySQConPool mPool = new MySQConPool();
    mPool.close();
%>
</body>
</html>
