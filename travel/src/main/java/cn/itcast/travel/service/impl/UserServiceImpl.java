package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(User registerUser) {
        boolean flag = true;
        User user = userDao.findByUsername(registerUser.getUsername());
        //1.判断是否已经存在同名用户
        if(user != null){
            flag = false;
        }else{
            //2.保存数据
            String code = UuidUtil.getUuid();
            registerUser.setCode(code); //生成用户专属码
            userDao.save(registerUser);

            //3.发送邮箱验证码
            String content = "<a href='http://localhost:8080/travel/activeUserServlet?code="+code+"'>点击验证</a>";
            MailUtils.sendMail(registerUser.getEmail(), content, "账号激活");
        }

        return flag;
    }

    /**
     * 邮箱激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //根据code查找用户，存在则修改user的status为Y
        User user = userDao.findByCode(code);
        if(user != null){
            userDao.updateStatus(user);
            return  true;
        }
        return false;
    }

    /**
     * 登录
     * @param loginUser
     * @return
     */
    @Override
    public User login(User loginUser) {
        return  userDao.findByUsernamePwd(loginUser);
    }
}
