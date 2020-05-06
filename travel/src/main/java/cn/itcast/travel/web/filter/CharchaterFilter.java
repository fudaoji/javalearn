package cn.itcast.travel.web.filter;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharchaterFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //2.获取请求方法
        String method = request.getMethod();

        //解决post请求中文乱码问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }
        //验证登录
        if(! this.checkLogin(request)){
            ObjectMapper mapper = new ObjectMapper();
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("expired");
            String json = mapper.writeValueAsString(resultInfo);
            response.setContentType("application/json; charset=utf-8"); //也可直接前端约束json传输
            response.getWriter().write(json);
            return;
        }
        //处理响应乱码
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);
    }

    /**
     * 验证是否登录
     * @param request
     * @return
     */
    private boolean checkLogin(HttpServletRequest request){
        boolean flag = true;

        String uri = request.getRequestURI();
        if (uri.contains("/route/addFavorite") || uri.contains("/user/myFavorite")) {
            User user = (User) request.getSession().getAttribute("user");
            if(user == null){
                flag = false;
            }
        }
        return flag;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
