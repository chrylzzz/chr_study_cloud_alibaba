package com.chryl.controller.mq;

import com.alibaba.fastjson.JSON;
import com.chryl.client.GoodsClient;
import com.chryl.po.ChrGoods;
import com.chryl.po.ChrOrder;
import com.chryl.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by Chr.yl on 2020/7/8.
 *
 * @author Chr.yl
 */

@Slf4j
@RestController
@RequestMapping("/rocket")
public class MqOrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    GoodsClient goodsClient;

    /**
     * 普通消息,发送mq ,用户微服务接收消息,发送短信Sms服务
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ChrOrder show(@PathVariable("id") Integer id) {
        log.info("查询商品信息");
        //获取商品信息
        ChrGoods goodsInfo = goodsClient.getGoodsInfo(id);
        ChrOrder chrOrder = new ChrOrder();
        if (goodsInfo == null) {
            chrOrder.setGoodsname("fail");
            return chrOrder;
        }
        log.info("商品信息为:{}", JSON.toJSONString(goodsInfo));
        chrOrder.setUid(1);
        chrOrder.setUsername("chryl");
        chrOrder.setGoodsname(goodsInfo.getGoodsName());
        chrOrder.setNumber(1);
        chrOrder.setGoodsid(goodsInfo.getGoodsId());
        chrOrder.setGoodsprice(new BigDecimal(goodsInfo.getGoodsPrice()));
        //下单
        orderService.createOrder(chrOrder);
        //下单成功,消息发送到mq(投递消息):根据topic和消息内容发送
        rocketMQTemplate.convertAndSend("order-topic", chrOrder);

        return chrOrder;
    }

    /**
     * 发送半事务消息,需要借助 db和rocket mq
     */



}
