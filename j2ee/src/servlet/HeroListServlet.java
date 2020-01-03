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
        response.setContentType("text/html; charset=UTF-8");
         
        int currentPage = Integer.parseInt(request.getParameter("current_page"));
        int pageSize = Integer.parseInt(request.getParameter("page_size"));
        List<Hero> heros = new HeroDAO().list(currentPage, pageSize);
 
        System.out.println(heros);
        
        JSONObject res = new JSONObject();
        res.put("msg", "新增成功");
        res.put("code", 1);
        res.put("list", JSONSerializer.toJSON(heros).toString());
        response.getWriter().print(res);
        
        /*StringBuffer sb = new StringBuffer();
        sb.append("<div style='width: 600px;margin: 50px auto;'><p><a href='/j2ee/addhero.html'>新增</a></p>");
        sb.append("<table align='center' border='1' cellspacing='0'>\r\n");
        sb.append("<tr><td>id</td><td>name</td><td>hp</td><td>damage</td><td>operations</td></tr>\r\n");
 
        String trFormat = "<tr><td>%d</td><td>%s</td><td>%f</td><td>%d</td><td><a href='edithero?id=%d'>编辑</a>&nbsp;&nbsp;<a href='deletehero?id=%d'>删除</a></td></tr>\r\n";
 
        for (Hero hero : heros) {
            String tr = String.format(trFormat, hero.getId(), hero.getName(), hero.getHp(), hero.getDamage(), hero.getId(), hero.getId());
            sb.append(tr);
        }
 
        sb.append("</table></div>");
 
        response.getWriter().write(sb.toString());*/
 
    }
}