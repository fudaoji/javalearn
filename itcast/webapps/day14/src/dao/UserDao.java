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
     * 登陆操作
     * @param  loginUser 登陆数据
     * @return User
     */
    public User login(User loginUser){
        try{
            String sql = "select *  from user where name = ? and password = ? ";
            //run sql
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getName(), loginUser.getPassword()
            );
            return  user;
        }catch (Exception e){
            //e.printStackTrace();
            return null;
        }
    }
}
