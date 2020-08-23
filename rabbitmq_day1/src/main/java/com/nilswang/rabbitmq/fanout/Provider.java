package com.nilswang.rabbitmq.fanout;

import com.nilswang.rabbitmq.utils.RabbitmqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

/**
 * @author wangminze
 * @create 2020-08-22-20:30
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

        // 将通道声明指定的交换机
        // 参数一：交换机的名称
        // 参数二：交换机类型 fanout 广播类型
        channel.exchangeDeclare("register","fanout");


        // 发布消息
        // 参数一exchange：交换机名称
        // 参数二routinqKey：队列名称
        // 参数三props：传递消息的额外设置
        // 参数四：消息的具体内容
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("register","",null,("fanout type message" + i).getBytes());
        }

        //释放资源
        RabbitmqUtils.closeConnection(connection,channel);

    }

}
