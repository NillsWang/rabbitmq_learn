package com.nilswang.rabbitmq.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangminze
 * @create 2020-08-23-8:33
 */
@Component
public class RouteConsumer {

    // 消费者2
    @RabbitListener(bindings = {
            @QueueBinding(
                    // 临时队列
                    value = @Queue,
                    // 指定绑定的交换机
                    exchange = @Exchange(value = "direct",type = "direct"),
                    // 指定key
                    key = {"info", "error", "warn"}
            )
    })
    public void receiveRoute2(String message){
        System.out.println("message2 = "+message);
    }

    // 消费者1
    @RabbitListener(bindings = {
            @QueueBinding(
                    // 临时队列
                    value = @Queue,
                    // 指定绑定的交换机
                    exchange = @Exchange(value = "direct",type = "direct"),
                    // 指定key
                    key = {"error"}
            )
    })
    public void receiveRoute1(String message){
        System.out.println("message1 = "+message);
    }

}
