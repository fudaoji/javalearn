import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Db;
import net.sf.json.JSONObject;
 
public class LoginServlet extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	
    	JSONObject res = new JSONObject();
    	
        String name = request.getParameter("name");
        String password = request.getParameter("password");
 
        Db db = new Db();
        
        if (db.count("select count(id) from user where name='"+name+"' and password='"+password+"'") > 0) {
            request.getSession().setAttribute("userName", name);
            res.put("code", 1);
            res.put("msg", "登录成功");
        } else {
        	res.put("code", 2003);
            res.put("msg", "账号或者密码错误");
        }
        response.getWriter().print(res);
    }
}