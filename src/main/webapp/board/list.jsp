
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>파일 첨부형 게시판 목록 보기</h2>
<h4>검색 레코드 수 : ${totalcount}</h4>

<form method="get">
<table width="50%">
    <tr>
        <td align="center">
            <select name="searchField">
                <c:choose>
                    <c:when test="${map.searchField=='title'}">
                        <option value="">선택</option>
                        <option value="title" selected>제목</option>
                        <option value="content">내용</option>
                    </c:when>
                    <c:when test="${map.searchField=='content'}">
                        <option value="">선택</option>
                        <option value="title">제목</option>
                        <option value="content" selected>내용</option>
                    </c:when>
                    <c:otherwise>
                        <option value="" selected>선택</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </c:otherwise>
                </c:choose>
                <input type="text" name="searchWord" value="${map.searchWord}">
                <input type="submit" value="검색하기">
            </select>
        </td>
    </tr>
</table>
</form>

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

<table>
    <tr align="center">
        <td>${map.pageImg}</td>
        <td><button onclick="location.href='/mvcboard/write.do';">글쓰기</button> </td>
    </tr>

</table>

</body>
</html>
