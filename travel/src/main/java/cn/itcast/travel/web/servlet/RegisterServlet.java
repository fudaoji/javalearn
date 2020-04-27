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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = new ResultInfo();
        //序列化
        ObjectMapper mapper = new ObjectMapper();

        //校验验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");  //remove from session

        if(checkCode == null || !checkCode.equalsIgnoreCase(check)){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            String json = mapper.writeValueAsString(info);
            response.getWriter().write(json);
            return;
        }

        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装数据
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.service 处理数据
        UserService userService = new UserServiceImpl();
        boolean flag = userService.register(user);

        //4.响应请求
        if(flag){
            //success
            info.setFlag(true);
            info.setErrorMsg("注册成功");
        }else{
            //fail
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }

        String json = mapper.writeValueAsString(info);

        //response.setContentType("application/json; charset=utf-8"); //也可直接前端约束json传输
        response.getWriter().write(json);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
