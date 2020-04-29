package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * 旅游路线分类service
 */
public interface CategoryService {
    public List<Category> findAll();
}
