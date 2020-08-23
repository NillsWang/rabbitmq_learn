package com.nilswang.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangminze
 * @create 2020-08-23-8:10
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("hello")) // 持久化 非独占
public class HelloConsumer {

    @RabbitHandler
    public void receiveHello(String message){
        System.out.println("message = "+message);
    }

}
