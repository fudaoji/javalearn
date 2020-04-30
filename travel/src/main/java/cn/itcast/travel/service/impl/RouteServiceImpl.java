package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.ArrayList;
import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDao routeDao = new RouteDaoImpl();
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
}
