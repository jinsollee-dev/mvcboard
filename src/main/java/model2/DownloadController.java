package model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import fileupload.FileUtil;
import java.io.IOException;

@WebServlet("/mvcboard/download.do")
public class DownloadController extends HttpServlet {

    //view.jsp에서 ofile, sfile 값 전달받음
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ofile=req.getParameter("ofile");
        String sfile=req.getParameter("sfile");
        int idx=Integer.parseInt(req.getParameter("idx")); //get으로 전달할때는 항상 String이므로 정수형으로 변환
        FileUtil.download(req, resp, "/uploads", sfile, ofile);
        MVCBoardDAO dao=new MVCBoardDAO();
        dao.downCountPlus(idx);
        dao.close();
    }
}
