package com.nilswang.rabbitmq.hello;

import com.nilswang.rabbitmq.utils.RabbitmqUtils;
import com.rabbitmq.client.*;
import java.io.IOException;

/**
 * @author wangminze
 * @create 2020-08-22-16:24
 */
public class Consumer {

    public static void main(String[] args) {

        // 获取连接对象
        Connection connection = null;
        // 获取连接通道
        Channel channel = null;

        try {
            connection = RabbitmqUtils.getConnection();
            channel = connection.createChannel();
            // 通道绑定对应的消息队列
            // 参数一queue：队列名称，如果队列不存在自动创建
            // 参数二durable：用来定义队列特性是否要持久化，true代表持久化，false代表不持久化
            // 参数三exclusive：是否独占队列，true代表独占队列，false代表不独占队列
            // 参数四autoDelete：是否在消息完成后自动删除队列，true代表自动删除，false代表不自动删除
            // 参数五：额外附加参数
            channel.queueDeclare("hello",false,false,false,null);

            // 消费消息
            // 参数一：消费哪个队列的消息
            // 参数二：开启消息自动确认机制
            // 参数三：消费消息时的回调代码
            // 参数四：消息的具体内容
            channel.basicConsume("hello",true, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag
                        , Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(new String(body));

                    super.handleDelivery(consumerTag, envelope, properties, body);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            //RabbitmqUtils.closeConnection(connection,channel);
        }

    }

}
