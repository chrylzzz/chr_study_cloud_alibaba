package com.chryl.resource;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chryl.resource.handler.MyBlockHandler;
import com.chryl.resource.handler.MyFallbackHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by Chr.yl on 2020/6/22.
 *
 * @author Chr.yl
 */
@Slf4j
@Component
public class MyTestResource {


    //表示sentinel资源,可以注入到具体的接口里,让接口携带资源,然后设置资源的访问规则即可
    @SentinelResource(
            value = "message",
            blockHandler = "myBlockHandlerMethod",//自定义一个blockHandler方法,处理BlockException,注意,参数和返回值必须和定义所在的方法一样
            fallback = "myFallbackMethod"//,//自定义处理Throwable异常
    )
    public String message() {
        return "this is my message";
    }

    //注意必须是static
    public String myBlockHandlerMethod(BlockException e) {

        //自定义异常处理逻辑
        log.info("触发了 myBlockHandlerMethod :{}", e);
        return "触发了 myBlockHandlerMethod";
    }

    public static String myFallbackMethod(Throwable t) {

        //自定义异常处理逻辑
        log.info("触发了 fallback :{}", t);
        return "触发了 fallback";
    }
}
