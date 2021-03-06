package web;

import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(request.getParameter("id"));

        if (id <= 0) {
            request.setAttribute("msg", "参数错误");
            request.getRequestDispatcher("update.jsp").forward(request, response);
            return;
        }

        UserService service = new UserServiceImpl();
        User user = service.findUser(id);
        if (user == null) {
            request.setAttribute("msg", "用户不存在");
            request.getRequestDispatcher("update.jsp").forward(request, response);
            return;
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
