package model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/mvcboard/list.do")
public class BoardListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MVCBoardDAO dao = new MVCBoardDAO();
        Map<String, Object> map = new HashMap<>();

        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");

        if(searchWord != null){
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }else{
            searchField="";
            searchWord="";
        }

        int pageSize=3; //한 페이지에 출력할 글 개수
        int blockSize=3; //한 블럭에 해당하는 페이지 개수
        int pageNum=1;
        String pageTemp = req.getParameter("pageNum");
        if(pageTemp !=null && !pageTemp.equals("")){
            pageNum=Integer.parseInt(pageTemp);
        } //전달된 페이지 번호가 있으면 pageNum=pageTemp

        //oracle = start=(pageNum-1)*pageSize+1
        //orcale -> int end = pageNum*pageSize;
        int start=(pageNum-1)*pageSize;
        map.put("start", start);
        map.put("pageSize", pageSize);
        int totalcount = dao.selectCount(map);
        List<MVCBoardDTO> boardList=dao.selectList(map);
        dao.close();

        String pagingImg = BoardPage.pagingStr(totalcount, pageSize, blockSize, pageNum, searchField, searchWord,"/mvcboard/list.do");

        map.put("pageImg",pagingImg);
        map.put("pageSize",pageSize);
        map.put("pageNum",pageNum);
        req.setAttribute("map", map);
        req.setAttribute("totalcount", totalcount);
        req.setAttribute("boardList", boardList);
        req.getRequestDispatcher("/board/list.jsp").forward(req,resp);
    }
}
