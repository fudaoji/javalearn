package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;


public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    private String table = "tab_favorite";

    /**
     * 根据rid和uid获取数据
     *
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public Favorite getByUidCid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from " + this.table +" where rid=? and uid=? ";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        }catch (DataAccessException e){
            //e.printStackTrace();
        }
        return  favorite;
    }

    /**
     * 新增
     *
     * @param rid
     * @param uid
     */
    @Override
    public void add(int rid, int uid) {
        String sql = "insert into " + this.table + "(rid,date,uid) values(?,?,?)";
        template.update(sql, rid, new Date(), uid);
    }

    /**
     * 编辑
     *
     * @param favorite
     */
    @Override
    public void set(Favorite favorite) {
        String sql = "update " + this.table + " set status=? where rid=? and uid=?";
        template.update(sql, favorite.getStatus(), favorite.getRid(), favorite.getUid());
    }

    /**
     * 根据rid
     *
     * @param rid
     * @return
     */
    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from " + this.table + " where rid=?";
        return  template.queryForObject(sql, Integer.class, rid);
    }
}
