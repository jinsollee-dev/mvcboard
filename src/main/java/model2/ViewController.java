package model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MVCBoardDAO dao = new MVCBoardDAO();
        int idx=Integer.parseInt(req.getParameter("idx"));
        dao.updateVisitCount(idx);
        MVCBoardDTO dto=dao.selectView(idx);
        List<ReplyDTO> replyDTOList=dao.selectListReply(idx);
        dao.close();

        //파일을 가져오는데 확장자 분리해서 그림파일인 경우는 이미지 출력
        String ext=null;
        String fileName=dto.getSfile();
        if(fileName!=null){
            ext=fileName.substring(fileName.lastIndexOf(".")+1);
        }
        String[] mimeStr={"png","jpg","gif"};
        List<String> mimeList= Arrays.asList(mimeStr); //asList-> list로 변환
        boolean isImage =false;
        //ext가 mimeList안에 포함되어 있는가 포함되어있으면 true
        if(mimeList.contains(ext)){
            isImage=true;
        }

        req.setAttribute("replyList",replyDTOList);
        req.setAttribute("dto", dto);
        req.setAttribute("isImg", isImage);
        req.getRequestDispatcher("/board/view.jsp").forward(req, resp);

    }
}
