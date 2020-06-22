package com.chryl.resource.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chryl.po.ChrGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 可以自定义BlockException处理的类,不用再@SentinelResource处单独定义方法,采用引用外部blockExceptionClass的方式统一处理
 * Created by Chr.yl on 2020/6/21.
 *
 * @author Chr.yl
 */
@Slf4j
@Component
public class MyBlockHandler {

    //注意必须是static
    public static ChrGoods show(Integer id, BlockException e) {

        //自定义异常处理逻辑
        log.info("触发了 myBlockHandlerMethod :{}", e);
        ChrGoods chrGoods = new ChrGoods();
        chrGoods.setGoodsId(-100);
        chrGoods.setGoodsName("容错商品: " + id);
        return chrGoods;
    }


    //注意必须是static
    public static ChrGoods show2(String id, BlockException e) {
        //自定义异常处理逻辑
        log.info("触发了 myBlockHandlerMethod :{}", e);
        ChrGoods chrGoods = new ChrGoods();
        chrGoods.setGoodsId(-100);
        chrGoods.setGoodsName("容错商品: " + id);
        return chrGoods;
    }
}
