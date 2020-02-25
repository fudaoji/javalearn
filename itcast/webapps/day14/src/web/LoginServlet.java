package web;

import dao.UserDao;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().write("haha");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if("".equals(name) || "".equals(password)){
            resp.getWriter().write("请输入账号、密码");
        }else{
            User loginUser = new User();
            loginUser.setName(name);
            loginUser.setPassword(password);

            UserDao userDao = new UserDao();
            User user = userDao.login(loginUser);
            if(user != null){
                resp.getWriter().write("success");
                //req.setAttribute("user", user);
                //req.getRequestDispatcher("/day14/successLogin").forward(req, resp);
            }else{
                resp.getWriter().write("fail");
                //req.getRequestDispatcher("/day14/failLogin").forward(req, resp);
            }
        }
    }
}
