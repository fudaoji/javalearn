package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    private String table = "tab_category";

    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<Category>();
        try {
            //1.sql
            String sql = "select *  from " + table;
            //2.exe sql
            list = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        }catch (Exception e){ };
        return list;
    }
}
