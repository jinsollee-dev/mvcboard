package model2;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

import java.io.IOException;

@WebServlet("/mvcboard/edit.do")
@MultipartConfig(
        maxFileSize = 1024*1024*5,
        maxRequestSize = 1024*1024*10
)

public class UpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MVCBoardDAO dao = new MVCBoardDAO();
        int idx=Integer.parseInt(req.getParameter("idx"));

        MVCBoardDTO dto=dao.selectView(idx);
        dao.close();
        req.setAttribute("dto", dto);
        req.getRequestDispatcher("/board/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String saveDirectory=getServletContext().getRealPath("/uploads");
        String originalFileName="";

        try{
            originalFileName=FileUtil.uploadFile(req,saveDirectory);

        }catch (Exception ex){
            JSFunction.alertBack(resp, "파일업로드 오류");

        }
        int idx=Integer.parseInt(req.getParameter("idx"));
        String prevOfile=req.getParameter("prevOfile");
        String prevSfile=req.getParameter("prevSfile");

        String name=req.getParameter("name");
        String title=req.getParameter("title");
        String content=req.getParameter("content");

        HttpSession session=req.getSession();
        String pass=(String)session.getAttribute("pass");
        MVCBoardDTO dto=new MVCBoardDTO();
        dto.setIdx(idx);
        dto.setName(name);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setPass(pass);

        if(!originalFileName.equals("")){
            String savedFileName=FileUtil.renameFile(saveDirectory,originalFileName);
            dto.setOfile(originalFileName);
            dto.setSfile(savedFileName);
            FileUtil.deleteFile(req, "/uploads",prevSfile);
        }else{
            dto.setOfile(prevOfile);
            dto.setSfile(prevSfile);
        }
    MVCBoardDAO dao=new MVCBoardDAO();
        int result=dao.update(dto);
        dao.close();
        if(result==1){
            session.removeAttribute("pass");
            resp.sendRedirect("/mvcboard/view.do?idx="+idx);
        }else{
            JSFunction.alertLocation(resp,"데이터 업데이트 실패","/mvcboard/view.do?idx="+idx);
        }
    }
}
