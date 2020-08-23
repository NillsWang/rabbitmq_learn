package com.nilswang.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author wangminze
 * @create 2020-08-22-17:03
 */
public class RabbitmqUtils {

    // 创建连接mq的连接工程对象
    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        // 设置连接rabbitmq的主机
        connectionFactory.setHost("192.168.8.128");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        // 设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }

    public static Connection getConnection(){

        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection connection, Channel channel){
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
