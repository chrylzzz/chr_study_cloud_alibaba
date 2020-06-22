package com.chryl.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.chryl.client.GoodsClient;
import com.chryl.po.ChrGoods;
import com.chryl.resource.MyTestResource;
import com.chryl.resource.handler.MyBlockHandler;
import com.chryl.resource.handler.MyFallbackHandler;
import com.chryl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Chr.yl on 2020/6/22.
 *
 * @author Chr.yl
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get/{id}")
    public ChrGoods getGoods(@PathVariable Integer id) {
        return orderService.get(id);
    }

    //测试资源
    @GetMapping("/get2/{id}")
    public ChrGoods getGoods2(@PathVariable Integer id) {
        //测试资源
//        return orderService.get3(id);
        //测试资源2
        return orderService.get4(String.valueOf(id));
    }

    //热点流控
    @GetMapping("/get3")
    public ChrGoods getGoods3(String name) {
        return orderService.get3(2);
    }


}
