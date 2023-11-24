
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>파일 첨부형 게시판 목록 보기</h2>
<h4>검색 레코드 수 : ${totalcount}</h4>


<table>
    <tr>
    <th>번호</th>
    <th>제목</th>
    <th>작성자</th>
    <th>작성일</th>
    <th>조회수</th>
    <th>다운수</th>
    <th>댓글수</th>
    </tr>

<c:forEach var="board" items="${boardList}">
    <tr>
    <td>${board.idx}</td>
    <td>${board.title}</td>
    <td>${board.name}</td>
    <td>${board.postdate}</td>
    <td>${board.visitcount}</td>
    <td>${board.downcount}</td>
    <td>${board.replycount}</td>
    </tr>
</c:forEach>


</table>

</body>
</html>
