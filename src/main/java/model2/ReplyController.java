package model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/mvcboard/reply.do")
public class ReplyController extends HttpServlet {


    // 비밀번호 검증후 -> passcontroller에서 ridx 가지고 get으로 받음
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     int ridx=Integer.parseInt(req.getParameter("ridx"));
     MVCBoardDAO dao = new MVCBoardDAO();
     int result=dao.replyDelete(ridx); //삭제처리
     dao.close();
     PrintWriter out =resp.getWriter(); //ajax로 반환할 값 설정
     if(result==1){
         out.print("success");
     }else{
         out.print("fail");
     }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReplyDTO dto = new ReplyDTO();
        dto.setName(req.getParameter("name"));
        dto.setBno(Integer.parseInt(req.getParameter("bno")));
        dto.setContent(req.getParameter("content"));
        dto.setPass(req.getParameter("pass"));
        MVCBoardDAO dao=new MVCBoardDAO();
        int result=dao.insertReply(dto);
        dao.close();

        PrintWriter out = resp.getWriter();
        if(result==1){
            out.print("success");
        }else{
            out.println("fail");
        }

    }
}
