<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous">
    </script>
    <script>
        function replyInsert(){
            var data1={"bno":$("#bno").val(),
                "name":$("#name").val(),
                "content" :$("#content").val(),
                "pass":$("#pass").val()
            }
            $.ajax({
               type:"POST",
               url:"/mvcboard/reply.do",
                data:data1,
                dataType:"text",
                success:function (result){
                   if(result=='success'){
                       alert("댓글 입력성공")
                       location.href="/mvcboard/view.do?idx="+$("#bno").val()
                   }else{
                       alert("댓글 입력실패")
                   }
                }
            });
        }

        <!--ajax로 처리할때-->
        function reply_delete(idx, pass){
            var data1={"mode":"reply_del","idx":idx, "pass":pass};
            $.ajax({
                type:"POST",
                url:"/mvcboard/pass.do",
                data:data1,
                dataType:"text",
                success:function (result){
                    if(result=='success'){
                        alert("삭제 성공")
                        location.href="/mvcboard/view.do?idx="+$("#bno").val()
                    }else{
                        alert("삭제 실패")
                    }
                }
            });
        }
    </script>
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
                <a href="/mvcboard/download.do?ofile=${dto.ofile}&sfile=${dto.sfile}&idx=${dto.idx}">[다운로드]</a>
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


<h2>댓글영역</h2>
<h3>댓글작성</h3>
<h3>댓글 리스트</h3>

<form name="replyform">
    <input type="hidden" id="bno" value="${dto.idx}">
    <table>
        <tr>
            <td>작성자</td><td><input type="text" id="name" name="name"></td>
        </tr>
        <tr>
            <td>댓글내용</td>
            <td><textarea name="content" id="content" rows="3" cols="30"></textarea></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" id="pass" name="pass"></td>
        </tr>
        <tr>
            <td colspan="2"></td>
            <td><input type="button" id="replyBtn" onclick="replyInsert()"
                                            value="댓글작성"></td>
        </tr>
    </table>
</form>

<h3>댓글 리스트</h3>
<table>
    <c:forEach var="reply" items="${replyList}">
        <tr>
            <td>${reply.ridx}</td>
            <td>${reply.content}</td>
            <td>${reply.name}</td>
            <td>${reply.postdate}</td>
            <td><button type="button" onclick="location.href=reply_del(${reply.ridx}, ${reply.pass}}" value="삭제">삭제</button></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
