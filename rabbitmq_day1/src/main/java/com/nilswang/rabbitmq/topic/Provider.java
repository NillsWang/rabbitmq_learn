package com.nilswang.rabbitmq.topic;

import com.nilswang.rabbitmq.utils.RabbitmqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

/**
 * @author wangminze
 * @create 2020-08-22-21:23
 */
public class Provider {

    /**
     * 生产消息
     */
    @Test
    public void testSendMessage() throws IOException {

        // 获取连接对象
        Connection connection = RabbitmqUtils.getConnection();

        // 获取连接通道
        Channel channel = connection.createChannel();

        // 通过通道声明交换机
        // 参数一：交换机的名称
        // 参数二：交换机类型 direct 路由模式
        channel.exchangeDeclare("topic", "topic");

        // 路由key
        String routingKey = "user.add.add";

        // 发布消息
        channel.basicPublish("topic", routingKey, null, ("topic routingKey:" + routingKey).getBytes());

        // 关闭资源
        RabbitmqUtils.closeConnection(connection, channel);

    }

}
