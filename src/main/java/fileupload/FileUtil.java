package fileupload;

import com.google.protobuf.Empty;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    public static String uploadFile(HttpServletRequest req, String sDirectory)
            throws ServletException, IOException {
        Part part = req.getPart("ofile");
        String partHeader = part.getHeader("content-disposition");
        String[] phArr = partHeader.split("filename=");
        String originalFileName = phArr[1].trim().replace("\"", "");
        if (!originalFileName.isEmpty()) {
            part.write(sDirectory + File.separator + originalFileName);
        }
        return originalFileName;
    }

    public static String renameFile(String sDirectory, String filename) {
        String ext = filename.substring(filename.lastIndexOf(".")); //확장자 분리
        String sname = filename.substring(0, filename.lastIndexOf(".")); //확장자 분리
        String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
        //파일 중복되는 경우를 대비해 제목에 날짜정보 추가해서 새로운 이름 주기
        String newFileName = sname+"_"+now+ext;
        File oldFile = new File(sDirectory + File.separator + filename);
        File newFile = new File(sDirectory + File.separator + newFileName);
        oldFile.renameTo(newFile);

        return newFileName;

    }
}