package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;

import java.util.ArrayList;
import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDao routeDao = new RouteDaoImpl();
    RouteImgDao routeImgDao = new RouteImgDaoImpl();
    SellerDao sellerDao = new SellerDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 分页查询某分类的路线
     *
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param searchKey
     * @return
     */
    @Override
    public PageBean<Route> page(int cid, int currentPage, int pageSize, String searchKey) {
        PageBean<Route> pageBean = new PageBean<Route>(currentPage, pageSize);
        int total = routeDao.findTotal(cid, searchKey);
        System.out.println("总数量:"+total);
        List<Route> list = new ArrayList<Route>();
        if(total > 0){
            int start = (currentPage - 1) * pageSize;
            list = routeDao.findByPage(cid, start, pageSize, searchKey);
        }
        pageBean.setList(list);
        int totalPage = (int) Math.ceil(total / pageSize);
        pageBean.setTotalCount(total);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    /**
     * get route by cid
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        //1.get route basic info
        Route route = routeDao.findOne(rid);
        //2.get img list from route_img by rid
        List<RouteImg> routeImgs = routeImgDao.findByRid(rid);
        if(routeImgs.size() > 0){
            route.setRouteImgList(routeImgs);
        }
        //3.get seller info from seller by sid
        Seller seller = sellerDao.findOne(route.getSid());
        if(seller != null){
            route.setSeller(seller);
        }
        // 4.get category info from category by cid
        Category category = categoryDao.findOne(route.getCid());
        if(category != null){
            route.setCategory(category);
        }
        return route;
    }
}
