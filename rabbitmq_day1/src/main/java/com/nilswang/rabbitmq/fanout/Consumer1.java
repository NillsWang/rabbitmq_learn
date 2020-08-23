package com.nilswang.rabbitmq.fanout;

import com.nilswang.rabbitmq.utils.RabbitmqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author wangminze
 * @create 2020-08-22-20:40
 */
public class Consumer1 {

    public static void main(String[] args) {

        // 获取连接对象
        Connection connection;
        // 获取连接通道
        Channel channel;

        try {
            connection = RabbitmqUtils.getConnection();
            channel = connection.createChannel();

            // 通道绑定交换机
            channel.exchangeDeclare("register","fanout");

            //临时队列
            String queueName = channel.queueDeclare().getQueue();

            //绑定交换机和队列
            channel.queueBind(queueName,"register","");

            //消费消息
            channel.basicConsume(queueName,false, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag
                        , Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);

                    System.out.println("consumer-1"+new String(body));

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            //RabbitmqUtils.closeConnection(connection,channel);
        }

    }

}
