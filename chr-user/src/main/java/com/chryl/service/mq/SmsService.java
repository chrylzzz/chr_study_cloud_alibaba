package com.chryl.service.mq;

import com.chryl.po.ChrOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * Created by Chr.yl on 2020/7/8.
 *
 * @author Chr.yl
 */
@Slf4j
@Service("shopSmsService")//SmsService对象名被alibaba使用了
@RocketMQMessageListener(
        consumerGroup = "user-group",//消费者组名
        topic = "order-topic"//,//消费的主题
//        consumeMode = ConsumeMode.ORDERLY,//消费模式,指定是否顺序消费: CONCURRENTLY(无序,同步消费,默认值) ;ORDERLY(顺序消费)
//        messageModel =  MessageModel.CLUSTERING//消息模式: BROADCASTING(广播模式) ,CLUSTERING(集群模式,默认值)
)
public class SmsService implements RocketMQListener<ChrOrder> {

    @Override
    public void onMessage(ChrOrder chrOrder) {

        System.out.println("收到了消息.." + chrOrder);
    }
}
