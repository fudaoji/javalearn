package dao;

import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtil;

/**
 * User 操作类
 */
public class UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    /**
     * 登录
     *
     * @param loginUser 表单数据
     * @return User
     */
    public User login(User loginUser) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword()
            );
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
