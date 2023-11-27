<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function validateForm(form) {
            if(form.name.value==""){
                alert("작성자를 입력하세요");
                form.name.focus();
                return false;
            }
            if(form.title.value==""){
                alert("제목을 입력하세요");
                form.title.focus();
                return false;
            }
            if(form.content.value==""){
                alert("내용을 입력하세요");
                form.content.focus();
                return false;
            }
            if(form.pass.value==""){
                alert("비밀번호를 입력하세요");
                form.pass.focus();
                return false;
            }
        }
    </script>
</head>
<body>
<h2>파일첨부형 게시판 수정</h2>
<form name="writeFrm" method="post" enctype="multipart/form-data"
      action="/mvcboard/edit.do">
    <input type="hidden" name="idx" value="${dto.idx}">
    <input type="hidden" name="prevOfile" value="${dto.ofile}">
    <input type="hidden" name="prevSfile" value="${dto.sfile}">
    <table border="1" width="90%">
        <tr>
            <td>작성자</td>
            <td><input type="text" name="name" value="${dto.name}" style="width:150px;"></td>
        </tr>
        <tr>
            <td>제목</td>
            <td><input type="text" name="title" value="${dto.title}" style="width:150px;"></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea name="content" style="width: 90%;height: 100px;">${dto.content}</textarea> </td>
        </tr>
        <tr>
            <td>첨부파일</td>
            <td><input type="file" name="ofile" style="width:150px;">${dto.ofile}</td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="pass" value="${dto.pass}" style="width:150px;"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button type="submit">수정하기</button>
                <button type="reset">RESET</button>
                <button type="button" onclick="location.href='/mvcboard/list.do'">목록보기</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>