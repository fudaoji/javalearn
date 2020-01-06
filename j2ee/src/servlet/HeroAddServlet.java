package servlet;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import bean.Hero;
import dao.HeroDAO;
import net.sf.json.JSONObject;
 
public class HeroAddServlet extends HttpServlet {
 
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        
        //way1
        /*Hero hero = new Hero();
        hero.setName(request.getParameter("name"));
        hero.setHp(Float.parseFloat(request.getParameter("hp")));
        hero.setDamage(Integer.parseInt(request.getParameter("damage")));*/
        
        //way2
        JSONObject json = new JSONObject();
        json.put("name", request.getParameter("name"));
        json.put("hp", request.getParameter("hp"));
        json.put("damage", request.getParameter("damage"));
        Hero hero = (Hero)JSONObject.toBean(json, Hero.class); 
         
        System.out.println(json);
        new HeroDAO().add(hero);
        
        
        JSONObject res = new JSONObject();
        res.put("msg", "新增成功");
        res.put("code", 1);
        
        response.getWriter().print(res);
         
    }
}