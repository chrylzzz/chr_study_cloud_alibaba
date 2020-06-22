package com.chryl.client.fallback;

import com.chryl.client.GoodsClient;
import com.chryl.po.ChrGoods;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by Chr.yl on 2020/6/22.
 *
 * @author Chr.yl
 */
@Component
@Slf4j
public class GoodsClientFallbackFactory implements FallbackFactory<GoodsClient> {

    @Override
    public GoodsClient create(Throwable throwable) {
        return new GoodsClient() {
            @Override
            public ChrGoods getGoodsInfo(Integer id) {
                log.info("feign 容错 信息: {}", throwable);
                //容错逻辑
                ChrGoods chrGoods = new ChrGoods();
                chrGoods.setGoodsId(-100);
                chrGoods.setGoodsName("出错,feign进行容错");
                return chrGoods;
            }
        };
    }
}
