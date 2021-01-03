package com.fisher.mybatis.demo.mapper;

import com.fisher.mybatis.demo.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fisher.mybatis.demo.entity.CategoryChildrenItem;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shefenfei
 * @since 2020-05-20
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryChildrenItem> categoryListById(Long id);

}
