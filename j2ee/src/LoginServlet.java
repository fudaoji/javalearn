import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	/**
	 * 实例化，由于是单例，所以刷新多次也只执行一次
	 */
	public LoginServlet(){
        System.out.println("LoginServlet 构造方法 被调用");
    }
	
	/**
	 * 初始化，也只执行一次
	 */
	public void init(ServletConfig config) {
		//System.out.println("init of Login Servlet");
    }
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8");//响应支持中文
		//response.setContentType("text/html; charset=UTF-8"); //服务端设置内容编码
		request.setCharacterEncoding("UTF-8");  //接收中文参数
		
		response.getWriter().println(request.getMethod());
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		if("admin".equals(name) && "123456".equals(password)){
			request.getRequestDispatcher("success.html").forward(request, response); //服务器方式跳转
		}else{
			response.sendRedirect("fail.html");  //客户端方式跳转
		}

	}
	
	/**
	 * 销毁函数
	 */
	public void destroy() {
        System.out.println("destroy()");
    }
}
