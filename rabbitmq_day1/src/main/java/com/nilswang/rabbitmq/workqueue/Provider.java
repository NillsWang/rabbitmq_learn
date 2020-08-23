package com.nilswang.rabbitmq.workqueue;

import com.nilswang.rabbitmq.utils.RabbitmqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wangminze
 * @create 2020-08-22-18:28
 */
public class Provider {

    /**
     * 生产消息
     */
    @Test
    public void testSendMessage() throws IOException{

        // 获取连接对象
        Connection connection = RabbitmqUtils.getConnection();

        // 获取连接通道
        Channel channel = connection.createChannel();

        // 通道绑定对应的消息队列
        // 参数一queue：队列名称，如果队列不存在自动创建
        // 参数二durable：用来定义队列特性是否要持久化，true代表持久化，false代表不持久化
        // 参数三exclusive：是否独占队列，true代表独占队列，false代表不独占队列
        // 参数四autoDelete：是否在消息完成后自动删除队列，true代表自动删除，false代表不自动删除
        // 参数五：额外附加参数
        channel.queueDeclare("work",false,false,false,null);

        // 发布消息
        // 参数一exchange：交换机名称
        // 参数二routinqKey：队列名称
        // 参数三props：传递消息的额外设置
        // 参数四：消息的具体内容
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("","work",null,("hello work queue" + i).getBytes());
        }

        RabbitmqUtils.closeConnection(connection,channel);

    }

}
