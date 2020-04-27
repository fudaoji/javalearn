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
            String content = "<a href='http://localhost:8080/travel/activeUser?code="+code+"'>点击验证</a>";
            MailUtils.sendMail(registerUser.getEmail(), content, "账号激活");
        }

        return flag;
    }
}
