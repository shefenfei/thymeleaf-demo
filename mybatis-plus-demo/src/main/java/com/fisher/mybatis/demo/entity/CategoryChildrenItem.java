package com.fisher.mybatis.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class CategoryChildrenItem extends Category{
    List<Category> children;
}
