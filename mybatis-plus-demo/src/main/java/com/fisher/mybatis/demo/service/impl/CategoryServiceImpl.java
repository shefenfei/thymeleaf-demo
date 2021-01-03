package com.fisher.mybatis.demo.service.impl;

import com.fisher.mybatis.demo.entity.Category;
import com.fisher.mybatis.demo.entity.CategoryChildrenItem;
import com.fisher.mybatis.demo.mapper.CategoryMapper;
import com.fisher.mybatis.demo.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shefenfei
 * @since 2020-05-20
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryChildrenItem> getCategoryById(Long id) {
        List<CategoryChildrenItem> childrenItems = categoryMapper.categoryListById(id);
        return childrenItems;
    }
}
