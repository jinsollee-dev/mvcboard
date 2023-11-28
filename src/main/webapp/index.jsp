<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="/mvcboard/list.do">게시판 목록보기</a>

<c:forEach var="i" begin="2" end="2" step="1">
    <c:forEach var="j" begin="1" end="5" step="1">
        ${i}*${j}=${i*j}&nbsp;&nbsp;
    </c:forEach>
</c:forEach>

</body>
</html>