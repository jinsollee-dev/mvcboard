package model2;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JSFunction;

import java.io.IOException;

@WebServlet("/mvcboard/write.do")
@MultipartConfig(
        maxFileSize = 1024*1024*5,
        maxRequestSize = 1024*1024*10
)
public class BoardInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/board/write.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 파일업로드 처리
        String saveDirectory=req.getServletContext().getRealPath("/uploads");
        String originalFileName="";
        try{
            originalFileName= FileUtil.uploadFile(req,saveDirectory);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        MVCBoardDTO dto = new MVCBoardDTO();
        dto.setName(req.getParameter("name"));
        dto.setTitle(req.getParameter("title"));
        dto.setContent(req.getParameter("content"));
        dto.setPass(req.getParameter("pass"));
        if(originalFileName!=""){
            dto.setOfile(originalFileName);
            String savedFileName =FileUtil.renameFile(saveDirectory, originalFileName);
            dto.setSfile(savedFileName);
        }
        MVCBoardDAO dao = new MVCBoardDAO();
        int result = dao.insertBoard(dto);
        if(result==1){
            resp.sendRedirect("/mvcboard/list.do");
        }else{
            JSFunction.alertLocation(resp,"글쓰기 실패","/mvcboard/writer.do");

        }
    }


    }

