package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    /**
     * 分页查询某分类的路线
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param searchKey
     * @return
     */
    public PageBean<Route> page(int cid, int currentPage, int pageSize, String searchKey);

    /**
     * get route by rid
     * @param rid
     * @return
     */
    Route findOne(int rid);
}
