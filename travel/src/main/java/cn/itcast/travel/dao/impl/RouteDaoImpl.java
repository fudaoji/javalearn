package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    private String table = "tab_route";
    /**
     * 获取总数
     *
     * @param cid
     * @param searchKey
     * @return
     */
    @Override
    public int findTotal(int cid, String searchKey) {
        String sql = "select count(*) from "+table+" where 1=1 ";
        List params = new ArrayList();
        if(cid > 0){
            sql += " and cid=? ";
            params.add(cid);
        }
        if(searchKey.length() > 0){
            sql += " and rname like ? ";
            params.add("%"+searchKey+"%");
        }
        return  template.queryForObject(sql, Integer.class, params.toArray());
    }

    /**
     * 获取某类线路的翻页数据
     *
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String searchKey) {
        List<Route> list = new ArrayList<Route>();
        try {
            String sql = "select * from "+table+" where 1=1 ";
            List params = new ArrayList();
            if(cid > 0){
                sql += " and cid=? ";
                params.add(cid);
            }
            if(searchKey.length() > 0){
                sql += " and rname like ? ";
                params.add("%"+searchKey+"%");
            }
            sql += " limit ?,? ";

            params.add(start);
            params.add(pageSize);

            list = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        }catch (Exception e){ }
        return list;
    }
}
