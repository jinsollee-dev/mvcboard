package model2;

import common.MySQConPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MVCBoardDAO extends MySQConPool {
    public MVCBoardDAO() {
        super();
    }

    public int selectCount(Map<String, Object> map) {
        int totalCount = 0;
        String sql = "select count(*) from mvcboard";
        if(map.get("searchWord")!=null && !map.get("searchWord").equals("")){
            sql += " where "+map.get("searchField")
                    +" like '%"+map.get("searchWord")+"%'";
        }

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return totalCount;

    }

    public List<MVCBoardDTO> selectList(Map<String, Object> map) {
        List<MVCBoardDTO> boards = new ArrayList<MVCBoardDTO>();
        String sql="select * from mvcboard";
        if(map.get("searchWord")!=null && !map.get("searchWord").equals("")){
            sql += " where "+map.get("searchField")
                    +" like '%"+map.get("searchWord")+"%'";
        }
        sql +=" order by idx desc limit ?,?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(map.get("start").toString()));
            pstmt.setInt(2, Integer.parseInt(map.get("pageSize").toString()));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MVCBoardDTO dto = new MVCBoardDTO();
                dto.setIdx(rs.getInt("idx"));
                dto.setName(rs.getString("name"));
                dto.setTitle(rs.getString("title"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setDowncount(rs.getInt("downcount"));
                dto.setOfile(rs.getString("ofile"));
                dto.setSfile(rs.getString("sfile"));
                dto.setVisitcount(rs.getInt("visitcount"));
                dto.setReplycount(rs.getInt("replycount"));
                boards.add(dto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return boards;
    }

    public int insertBoard(MVCBoardDTO dto) {
        int result = 0;
        String sql = "insert into mvcboard(title, content, name, pass, ofile, sfile) " +
                "values(?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setString(3, dto.getName());
            pstmt.setString(4, dto.getPass());
            pstmt.setString(5, dto.getOfile());
            pstmt.setString(6, dto.getSfile());
            result = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
