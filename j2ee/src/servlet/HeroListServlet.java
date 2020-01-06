package servlet;
 
import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import bean.Hero;
import dao.HeroDAO;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
 
public class HeroListServlet extends HttpServlet {
 
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int currentPage = Integer.parseInt(request.getParameter("current_page"));
        int pageSize = Integer.parseInt(request.getParameter("page_size"));
        
        HeroDAO hd = new HeroDAO();
        
        long total = hd.getTotal();
        
        List<Hero> heros = hd.list(currentPage, pageSize);
 
        System.out.println(heros);
        
        JSONObject res = new JSONObject();
        res.put("msg", "success");
        res.put("code", 1);
        res.put("total", total);
        res.put("list", JSONSerializer.toJSON(heros).toString());
        response.getWriter().print(res);
 
    }
}