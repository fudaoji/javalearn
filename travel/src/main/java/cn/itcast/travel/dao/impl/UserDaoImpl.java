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

    /**
     * 根据code查找user
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            //1.sql
            String sql = "select *  from " + this.table + " where code = ?";
            //2.exe sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (Exception e){ };
        return user;
    }

    /**
     * update status of user
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        //1.sql
        String sql = "update " + this.table + " set status=? where uid=" + user.getUid();
        //2.exe sql
        template.update(sql, "Y");
    }

    /**
     * 根据用户名和密码获取user
     * @param loginUser
     * @return
     */
    @Override
    public User findByUsernamePwd(User loginUser) {
        User user = null;
        try {
            //1.sql
            String sql = "select *  from " + this.table + " where username = ? and password = ?";
            //2.exe sql
            user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword()
            );
        }catch (Exception e){ };
        return user;
    }
}
