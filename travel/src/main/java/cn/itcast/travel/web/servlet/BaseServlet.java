package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Servlet基类，对比PHP Controller 的基类
 */
public class BaseServlet extends HttpServlet {
    /**
     * 请求分发
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.get uri
        String uri = req.getRequestURI();
        //2.get request method
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        //3.get method and invoke
        try {
            //利用反射机制,当前上下文的this表示调用此service方法的类
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
