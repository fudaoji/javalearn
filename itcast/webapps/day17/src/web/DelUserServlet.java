package web;

import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delUserServlet")
public class DelUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        String id = request.getParameter("id");
        System.out.println(request.getParameter("id"));

        UserService service = new UserServiceImpl();

        int res = service.delUser(id);
        if (res > 0) {
            response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");
        } else {
            response.getWriter().write("<h2>操作失败</h2>");
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
