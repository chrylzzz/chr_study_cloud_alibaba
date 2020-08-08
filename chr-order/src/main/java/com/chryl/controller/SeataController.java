package com.chryl.controller;

import com.chryl.client.GoodsClient;
import com.chryl.po.ChrGoods;
import com.chryl.po.ChrOrder;
import com.chryl.po.ReturnResult;
import com.chryl.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by Chr.yl on 2020/8/8.
 *
 * @author Chr.yl
 */
@RestController("/seata")
public class SeataController {

    @Autowired
    GoodsClient goodsClient;

    @Autowired
    OrderService orderService;

    @GlobalTransactional
    @GetMapping("/reduce")
    public Object show() {
        //查询商品信息
        ChrGoods goodsInfo = goodsClient.getGoodsInfo(1);

        //创建订单
        ChrOrder chrOrder = new ChrOrder();
        chrOrder.setUid(1);
        chrOrder.setUsername("chryl");
        chrOrder.setGoodsname(goodsInfo.getGoodsName());
        chrOrder.setGoodsid(goodsInfo.getGoodsId());
        chrOrder.setNumber(1);
        chrOrder.setGoodsprice(new BigDecimal(goodsInfo.getGoodsPrice()));
        orderService.createOrder(chrOrder);

        //扣库存
        ReturnResult returnResult = goodsClient.reduceInventory(goodsInfo.getGoodsId(), 1);

        return returnResult;
    }
}
