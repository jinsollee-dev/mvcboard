<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>파일첨부형 게시판 상세보기</h2>

<table>
    <tr>
        <td>번호</td>
        <td>${dto.idx}</td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>${dto.name}</td>
    </tr>
    <tr>
        <td>제목</td>
        <td>${dto.title}</td>
    </tr>
    <tr>
        <td>내용</td>
        <td>${dto.content}
            <c:if test="${not empty dto.ofile and isImg eq true}">
                <br><img src="/uploads/${dto.sfile}" style="max-width: 80%;">
            </c:if>

        </td>
    </tr>
    <tr>
        <td>작성일</td>
        <td>${dto.postdate}</td>
    </tr>
    <tr>
        <td>첨부파일</td>
        <td>
            <c:if test="${not empty dto.ofile}"> <!--ofile이 있다면-->
                ${dto.ofile}
                <a href="/mvcboard/download.do?ofile=${dtol.ofile}&sfile=${dto.sfile}&idx=${dto.idx}">[다운로드]</a>
            </c:if>
        </td>
    </tr>
    <tr>
        <td>조회수</td>
        <td>${dto.visitcount}</td>
    </tr>
    <tr>
        <td>다운로드 수</td>
        <td>${dto.downcount}</td>
    </tr>
    <tr>
        <td>댓글 수</td>
        <td>${dto.replycount}</td>
    </tr>
    <tr>
        <td colspan="2">
            <button type="button" onclick="location.href='/mvcboard/pass.do?mode=edit&idx=${dto.idx}'">
                수정하기
            </button>
            <button type="button" onclick="location.href='/mvcboard/pass.do?mode=delete&idx=${dto.idx}'">
                삭제하기
            </button>
            <button type="button" onclick="location.href='/mvcboard/list.do'">목록보기</button>
        </td>

    </tr>

</table>
</body>
</html>
