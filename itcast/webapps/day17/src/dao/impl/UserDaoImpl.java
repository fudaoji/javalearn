package dao.impl;

import dao.UserDao;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * LOGIN
     *
     * @param loginUser
     * @return
     */
    @Override
    public User login(User loginUser) {
        String sql = "select * from user where username=? and password=?";
        try {
            return template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
<<<<<<< HEAD
     * 登录
     *
     * @param loginUser 表单数据
     * @return User
     */
    @Override
    public User login(User loginUser) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword()
            );
            return user;
=======
     * 查找总数
     *
     * @param condition
     * @return
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        //2.遍历map
        Set<String> keySet = condition.keySet();
        for (String key : keySet) {
            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                //有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");//？条件的值
            }
        }
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        //定义参数的集合
        List<Object> params = new ArrayList<Object>();

        //2.遍历map
        Set<String> keySet = condition.keySet();
        for (String key : keySet) {
            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                //有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();

        System.out.println(sql);
        System.out.println(params.toString());
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }

    /**
     * 新增管理员
     *
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        String sql = "insert into user (name,gender,age,address,qq,email) values(?,?,?,?,?,?)";
        try {
            return template.update(sql,
                    user.getName(),
                    user.getGender(),
                    user.getAge(),
                    user.getAddress(),
                    user.getQq(),
                    user.getEmail()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public User findOne(int id) {
        String sql = "select * from user where id=?";
        try {
            return template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class), id
            );
>>>>>>> 0a5cb22bc0b4e113362f2e3b8a910d07bbbbd3df
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
<<<<<<< HEAD
=======

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @Override
    public int updateOne(User user) {
        String sql = "update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        try {
            return template.update(sql,
                    user.getName(),
                    user.getGender(),
                    user.getAge(),
                    user.getAddress(),
                    user.getQq(),
                    user.getEmail(),
                    user.getId()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * delete user
     *
     * @param id
     * @return
     */
    @Override
    public int delete(int id) {
        String sql = "delete from user where id=?";
        try {
            return template.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
>>>>>>> 0a5cb22bc0b4e113362f2e3b8a910d07bbbbd3df
}
