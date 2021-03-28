package com.Lcc.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/26 14:33
 */
public class ConnRabbitUtils {
    //定义提供连接对象的方法
    private  static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("192.168.2.101");
        //设置连接端口号
        connectionFactory.setPort(5672);
        //设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置连接虚拟主机密码和用户名
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和关闭来连接工具方法
    public static void close(Channel channel ,Connection conn){
        try {
            if (channel!=null){
                channel.close();
            }
            if (conn!=null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}