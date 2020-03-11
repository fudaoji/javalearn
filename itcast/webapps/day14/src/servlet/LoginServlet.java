package servlet;

import dao.UserDao;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; character='utf-8'");
        Boolean flag = false;
        User user = null;
        User loginUser = new User();

        //method1
        /*String username = request.getParameter("username");
        String password = request.getParameter("password");
        if ("".equals(username) || "".equals(password)) {
            request.setAttribute("errMsg", "请填写账号和密码");
        } else {
            loginUser.setUsername(username);
            loginUser.setPassword(password);
        }*/

        //method2
        Map<String, String[]> map = request.getParameterMap();
        //使用BeanUtils封装，如果字段多了，这个方式会更简洁，但是要求字段名称和Bean类的属性要对应
        try {
            BeanUtils.populate(loginUser, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserDao userDao = new UserDao();
        user = userDao.login(loginUser);
        if (user != null) {
            flag = true;
            request.setAttribute("user", user);
        } else {
            request.setAttribute("errMsg", "账号或密码错误");
        }
        if (flag) {
            request.getRequestDispatcher("/successLogin").forward(request, response);
        } else {
            request.getRequestDispatcher("/failLogin").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
