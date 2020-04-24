package web;

import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1. 验证码核验
        String checkCode = request.getParameter("verifycode");
        String code = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if(! code.equalsIgnoreCase(checkCode)){
            request.setAttribute("login_msg", "验证码错误");
            request.getRequestDispatcher( "/login.jsp").forward(request, response);
            return;
        }
        //2. 组成user
        Map<String, String[]> map = request.getParameterMap();
        //4.封装User对象
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.登录判断
        UserService userService = new UserServiceImpl();
        User user = userService.login(loginUser);
        if(user == null){
            request.setAttribute("login_msg", "账号或密码错误");
            request.getRequestDispatcher( "/login.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("非法请求");
    }
}
