package web.filter;

import domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if (uri.contains("/login.jsp") || uri.contains("/loginServlet")
                || uri.contains("/checkCodeServlet") || uri.contains(".css")
                || uri.contains(".js")
        ) {
            chain.doFilter(req, resp);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                request.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
