package com.fisher.mybatis.demo.service;

import com.fisher.mybatis.demo.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fisher.mybatis.demo.entity.CategoryChildrenItem;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shefenfei
 * @since 2020-05-20
 */
public interface CategoryService extends IService<Category> {

    List<CategoryChildrenItem> getCategoryById(Long id);

}
