package com.Lcc;

import com.Lcc.utils.ConnRabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/25 15:41
 */
public class Provider {

    //生产消息
    @Test
    public void testSendMessage() throws Exception{
       /* //创建连接mq的连接工厂对象
        ConnectionFactory connectionFactory  = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("10.88.80.122");
        //设置连接端口号
        connectionFactory.setPort(5672);
        //设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置连接虚拟主机密码和用户名
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        //获取连接对象
        Connection connection = connectionFactory.newConnection();
        //获取连接通道*/
        Connection connection=  ConnRabbitUtils.getConnection();
        Channel channel  = connection.createChannel();
        //通道绑定对应消息队列
        //参数1：队列名称 如果队列不存在自动创建
        //参数2：用来定义队列特性看是否需要持久化 true持久化队列  false不持久化
        //参数3 看是否独占队列
        //参数4 是否在消费完成后自动删除队列
        channel.queueDeclare("hello",false,false,false,null);
        //发布消息
        //1 交换机名称  2 队列名称  3 传递消息额外设置  4 消息的具体内容
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes(StandardCharsets.UTF_8));

        /*channel.close();
        connection.close();*/
        ConnRabbitUtils.close(channel,connection);
    }
}