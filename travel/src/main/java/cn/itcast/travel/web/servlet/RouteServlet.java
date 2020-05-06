package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 收藏/取消收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String ridStr = request.getParameter("rid");
        int rid = 0;
        if(ridStr != null){
            rid = Integer.parseInt(ridStr);
        }
        ResultInfo resultInfo = new ResultInfo(false);
        User user = (User) request.getSession().getAttribute("user");
        if(user != null){
            //1. get favorite by uid and rid
            Favorite favorite = favoriteService.doFavorite(rid, user.getUid());
            //2. return favorite if it exists
            if(favorite != null){
                resultInfo.setFlag(true);
                resultInfo.setData(favorite);
            }
        }
        this.jsonReturn(resultInfo, response);
    }

    /**
     * 是否收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String ridStr = request.getParameter("rid");
        int rid = 0;
        if(ridStr != null){
            rid = Integer.parseInt(ridStr);
        }
        ResultInfo resultInfo = new ResultInfo(false);
        User user = (User) request.getSession().getAttribute("user");
        if(user != null){
            //1. get favorite by uid and rid
            Favorite favorite = favoriteService.isFavorite(rid, user.getUid());
            //2. return favorite if it exists
            if(favorite != null){
                resultInfo.setFlag(true);
                resultInfo.setData(favorite);
            }
        }
        this.jsonReturn(resultInfo, response);
    }

    /**
     * 获取单个路线详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rid = Integer.parseInt(request.getParameter("rid"));

        Route route = routeService.findOne(rid);
        this.jsonReturn(route, response);
    }

    /**
     * 路线列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String searchKey = request.getParameter("searchKey");
        if(searchKey == null){
            searchKey = "";
        }

        //2.参数处理
        int currentPage = 1;
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageSize = 5;
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        int cid = 0;
        if(cidStr != null && cidStr.length() > 0){
            cid = Integer.parseInt(cidStr);
        }

        PageBean<Route> pb = routeService.page(cid, currentPage, pageSize, searchKey);
        this.jsonReturn(pb, response);
    }

}
