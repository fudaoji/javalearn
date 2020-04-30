package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();
    /**
     * 获取所有
     * @return
     */
    @Override
    public List<Category> findAll() {
        List<Category> cs = new ArrayList<>();
        Jedis jedis = JedisUtil.getJedis();
        String cacheKey = "categoryList";
        System.out.println("准备访问jedis");
        //1、查询redis中的数据
        Set<Tuple> categoryList = jedis.zrangeWithScores(cacheKey, 0, -1);
        System.out.println("访问jedis结束");
        //2、redis中不存在则查询数据库，并把数据写入到redis
        if(categoryList == null || categoryList.size() == 0){
            cs = categoryDao.findAll();
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd(cacheKey, cs.get(i).getCid(), cs.get(i).getCname());
            }
        }else{
            //3.存在则推入cs中
            for (Tuple tuple : categoryList) {
                Category category = new Category();
                category.setCid((int) tuple.getScore());
                category.setCname(tuple.getElement());
                cs.add(category);
            }
        }
        return cs;
    }
}
