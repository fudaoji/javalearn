package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

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
        //1.get post data
        Map<String, String[]> map = request.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //2. check login
        UserService userService = new UserServiceImpl();
        User u = userService.login(loginUser);

        ResultInfo info = new ResultInfo();
        info.setFlag(false);
        if(u == null){
            info.setErrorMsg("账号或密码错误");
        }else if(u != null && !"Y".equalsIgnoreCase(u.getStatus())){
            info.setErrorMsg("用户暂未激活");
        }else if(u != null && "Y".equalsIgnoreCase(u.getStatus())){
            info.setFlag(true);
            info.setErrorMsg("登录成功");
            //保存会话
            User sessionUser = new User();
            sessionUser.setUsername(u.getUsername());
            sessionUser.setName(u.getName());
            sessionUser.setEmail(u.getEmail());
            request.getSession().setAttribute("user", sessionUser);
        }

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(info));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
