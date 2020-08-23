package com.nilswang.rabbitmq.direct;

import com.nilswang.rabbitmq.utils.RabbitmqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author wangminze
 * @create 2020-08-22-20:40
 */
public class Consumer2 {

    public static void main(String[] args) {

        // 获取连接对象
        Connection connection;
        // 获取连接通道
        Channel channel;

        try {
            connection = RabbitmqUtils.getConnection();
            channel = connection.createChannel();

            // 通道绑定交换机
            channel.exchangeDeclare("direct","direct");

            //临时队列
            String queueName = channel.queueDeclare().getQueue();

            //绑定交换机和队列
            channel.queueBind(queueName,"direct","info");
            channel.queueBind(queueName,"direct","error");
            channel.queueBind(queueName,"direct","warning");

            //消费消息
            channel.basicConsume(queueName,true, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag
                        , Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);

                    System.out.println("consumer-2"+new String(body));

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            //RabbitmqUtils.closeConnection(connection,channel);
        }

    }

}
