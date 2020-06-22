package com.chryl.resource.handler;

import com.chryl.po.ChrGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * --@SentinelResource可以自定义 fallback 处理的类,
 * 不用再每个方法进行定义,采用引用外部blockExceptionClass的方式统一处理
 * Created by Chr.yl on 2020/6/21.
 *
 * @author Chr.yl
 */
@Slf4j
@Component
public class MyFallbackHandler {
    public static ChrGoods show(Integer id, Throwable t) {

        //自定义异常处理逻辑
        log.info("触发了 fallback :{}", t);
        ChrGoods chrGoods = new ChrGoods();
        chrGoods.setGoodsId(-101);
        chrGoods.setGoodsName("容错商品: " + id);
        return chrGoods;
    }

    public static ChrGoods show2(String id, Throwable t) {
        //自定义异常处理逻辑
        log.info("触发了 fallback :{}", t);
        ChrGoods chrGoods = new ChrGoods();
        chrGoods.setGoodsId(-101);
        chrGoods.setGoodsName("容错商品: " + id);
        return chrGoods;
    }
}
