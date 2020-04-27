package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    private String table = "tab_user";

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            //1.sql
            String sql = "select *  from " + this.table + " where username = ?";
            //2.exe sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){ };
        return user;
    }

    /**
     * 保存数据
     * @param user
     */
    @Override
    public void save(User user){
        //1.sql
        String sql = "insert into " + this.table + "(username,password,email,name,birthday,sex,telephone,code) "
                + "values(?,?,?,?,?,?,?,?)";
        //2.exe sql
        template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getCode()
        );
    }
}
