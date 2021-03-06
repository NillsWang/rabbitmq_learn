package com.nilswang.rabbitmq.hello;

import com.nilswang.rabbitmq.utils.RabbitmqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import java.io.IOException;

/**
 * @author wangminze
 * @create 2020-08-22-15:59
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

        // 通道绑定对应的消息队列，若队列不存在则用来定义队列
        // 参数一：队列名称，如果队列不存在自动创建
        // 参数二：用来定义队列特性是否要持久化，true代表持久化，false代表不持久化
        // 参数三：是否独占队列，true代表独占队列，false代表不独占队列
        // 参数四：是否在消息完成后自动删除队列，true代表自动删除，false代表不自动删除
        // 参数五：额外附加参数
        channel.queueDeclare("hello1",false,false,false,null);

        // 发布消息
        // 参数一：交换机名称，如果直接发送到队列，则交换机名称为""
        // 参数二：目标队列名称
        // 参数三：传递消息的额外设置，属性参数
        // 参数四：消息的具体内容
        channel.basicPublish("","hello1",null,"hello rabbitmq".getBytes());

        RabbitmqUtils.closeConnection(connection,channel);

    }

}
