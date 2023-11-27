
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function validateForm(form){
            if(form.pass.value==""){
                alert("비밀번호를 입력하세요")
                form.pass.focus();
                return false;
            }
        }
    </script>
</head>


<body>
<form name="passForm" method="post" action="/mvcboard/pass.do"
      onsubmit="return validateForm(this);">
    <input type="hidden" name="idx" value="${param.idx}">
    <input type="hidden" name="mode" value="${mode}">

    <table>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="pass"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button type="submit">검증하기</button>
                <button type="reset">RESET</button>
                <button type="button" onclick="location.href='/mvcboard/list.do'">목록보기</button>
            </td>
        </tr>
    </table>

</form>
</body>
</html>