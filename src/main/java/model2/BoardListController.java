package model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/mvcboard/list.do")
public class BoardListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MVCBoardDAO dao = new MVCBoardDAO();
        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");
        String pageTemp = req.getParameter("pageNum");

        int pageSize=10;
        int blockSize=5;
        int pageNum=1;
        if(pageTemp !=null){
            pageNum=Integer.parseInt(pageTemp);

        }

        int start=0;

        Map<String, Object> map = new HashMap<>();
        map.put("searchField", searchField);
        map.put("searchWord", searchWord);
        map.put("start", start);
        map.put("pageSize", pageSize);
        int totalcount = dao.selectCount(map);
        List<MVCBoardDTO> boardList=dao.selectList(map);
        req.setAttribute("totalcount", totalcount);
        req.setAttribute("boardList", boardList);
        req.getRequestDispatcher("/board/list.jsp").forward(req,resp);
    }
}
