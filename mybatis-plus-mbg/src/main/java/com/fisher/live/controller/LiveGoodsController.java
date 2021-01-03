package com.fisher.live.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fisher.live.entity.LiveGoods;
import com.fisher.live.service.LiveGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shefenfei
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/live-goods")
public class LiveGoodsController {

    @Autowired
    private LiveGoodsService liveGoodsService;

    @GetMapping("/test")
    public String test() {
        liveGoodsService.save(null);
        liveGoodsService.getById(1);
        Page<LiveGoods> page = new Page<>(1, 10);
        QueryWrapper<LiveGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("spuCode");
        liveGoodsService.page(page);
        liveGoodsService.list();
        return "";
    }
}
