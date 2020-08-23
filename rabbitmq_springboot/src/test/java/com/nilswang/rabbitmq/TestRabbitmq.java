package com.nilswang.rabbitmq;

import com.nilswang.rabbitmq.RabbitmqSpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangminze
 * @create 2020-08-23-8:03
 */
@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitmq {

    // 注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // topic 动态路由模式
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topic","user.save.add","topic message");
    }

    // route 路由模式
    @Test
    public void testRoute(){

        rabbitTemplate.convertAndSend("direct","error","direct message");

    }

    // fanout 广播模式
    @Test
    public void testFanout(){

        rabbitTemplate.convertAndSend("logs","","fanout message");

    }

    // work 模式
    @Test
    public void testWork(){

        for (int i = 0; i < 10 ; i++) {
            rabbitTemplate.convertAndSend("work","work massage"+i);
        }
    }

    // hello world
    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello","hello rabbitmq");
    }




}