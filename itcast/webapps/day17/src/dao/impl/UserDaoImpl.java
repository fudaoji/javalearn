package dao.impl;

import dao.UserDao;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtil;

import java.util.List;

/**
 * 用户数据访问
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    /**
     * 查询数据库获取所有学生
     * @return
     */
    @Override
    public List<User> findAll() {
        try {
            String sql = "select * from user";
            return template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
