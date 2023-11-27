package utils;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

import java.io.PrintWriter;

public class JSFunction {

    public static void alertLocation(String msg, String url, JspWriter out){
        try{
            String script = "<script>"
                    +"  alert('"+msg+"');"
                    +"   location.href='"+url+"';"
                    +"</script>";
            out.println(script);
        }catch (Exception ex){  }

    }

    public static void alertBack(String msg, JspWriter out){
        try{
            String script = "<script>"
                    +"  alert('"+msg+"');"
                    +"  history.back();"
                    +"</script>";
            out.println(script);
        }catch (Exception ex){  }

    }


    public static void alertLocation(HttpServletResponse resp, String msg, String url){
        try{
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            String script = "<script>"
                    +"  alert('"+msg+"');"
                    +"   location.href='"+url+"';"
                    +"</script>";
            out.println(script);
        }catch (Exception ex){  }

    }

    public static void alertBack(HttpServletResponse resp, String msg){
        try{
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            String script = "<script>"
                    +"  alert('"+msg+"');"
                    +"  history.back();"
                    +"</script>";
            out.println(script);
        }catch (Exception ex){  }

    }

}
