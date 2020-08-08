package com.chryl.client;

import com.chryl.client.fallback.GoodsClientFallbackFactory;
import com.chryl.po.ChrGoods;
import com.chryl.po.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 同时有seata 和 feign的fallback  ,seata 全局事务会不生效,不任务出现了一场
 * Created by Chr.yl on 2020/6/22.
 *
 * @author Chr.yl
 */
@FeignClient(value = "service-goods"
//        ,//如果同时设置了feign的容错类和sentinel的资源容错类,资源的被sentinel控制,接口的被feign控制,各司其职
//        fallbackFactory = GoodsClientFallbackFactory.class
)
public interface GoodsClient {

    @PostMapping("/goods/get/{id}")
    ChrGoods getGoodsInfo(@PathVariable("id") Integer id);


    //扣减库存
    @PostMapping("/goods/reduce")
    ReturnResult reduceInventory(@RequestParam("goodsId") Integer goodsId,
                                 @RequestParam("number") Integer number);
}
