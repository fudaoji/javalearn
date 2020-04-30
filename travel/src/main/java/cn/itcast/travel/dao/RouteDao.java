package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    /**
     * 获取总数
     * @param cid
     * @param searchKey
     * @return
     */
    public int findTotal(int cid, String searchKey);

    /**
     * 获取某类线路的翻页数据
     * @param cid
     * @param start
     * @param pageSize
     * @param searchKey
     * @return
     */
    public List<Route> findByPage(int cid, int start, int pageSize, String searchKey);

    Route findOne(int rid);
}
