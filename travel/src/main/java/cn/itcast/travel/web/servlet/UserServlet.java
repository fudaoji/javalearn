package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    //声明UserService
    UserService userService = new UserServiceImpl();
    /**
     * 退出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index.html");
    }
    /**
     * 获取用户信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info =  new ResultInfo();
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            info.setFlag(false);
        }else{
            info.setFlag(true);
            info.setData(user);
        }

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(info));
    }

    /**
     * 激活用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String code = request.getParameter("code");
        boolean flag = false;
        if(code != null){
            flag = userService.active(code);
        }
        String content = "<a href='"+request.getContextPath()+"/login.html'>验证成功，点击前往登录</a>";
        if(! flag){
            content = "邮箱验证失败";
        }
        response.getWriter().write(content);
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            sessionUser.setUid(u.getUid());
            request.getSession().setAttribute("user", sessionUser);
        }

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(info));
    }

    /**
     * 注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
