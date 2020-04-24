import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class DemoFilter implements Filter {
    /**
     * 一般用于释放资源
     */
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println("doFilter: " + request.getContextPath());
        chain.doFilter(req, resp);
    }

    /**
     * 一般用于加载资源
     *
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init: " + config.getServletContext().getContextPath());
    }

}
