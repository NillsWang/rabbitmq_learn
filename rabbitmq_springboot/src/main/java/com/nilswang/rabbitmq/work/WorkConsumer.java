package com.nilswang.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangminze
 * @create 2020-08-23-8:10
 */
@Component
public class WorkConsumer {

    // 消费者1
    // 默认公平消费
    @RabbitListener(queuesToDeclare = @Queue("work")) // 持久化 非独占
    public void receiveWork2(String message){
        System.out.println("message2 = "+message);
    }

    // 消费者2
    // 默认公平消费
    @RabbitListener(queuesToDeclare = @Queue("work")) // 持久化 非独占
    public void receiveWork1(String message){
        System.out.println("message1 = "+message);
    }
}
