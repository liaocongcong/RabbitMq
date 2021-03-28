package com.Lcc;

import com.Lcc.utils.ConnRabbitUtils;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/25 16:21
 */
public class Customer {

     @Test
     public void TestCustomer() throws IOException, TimeoutException {
        /* //创建连接工厂
         ConnectionFactory connectionFactory = new ConnectionFactory();
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
         Connection connection = connectionFactory.newConnection();*/
         //通过工具类获取
         Connection connection=  ConnRabbitUtils.getConnection();
         //获取连接通道
         Channel channel  = connection.createChannel();
         channel.queueDeclare("hello",false,false,false,null);

         channel.basicConsume("hello",true,new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope,AMQP.BasicProperties properties,byte[] body)throws IOException{
                System.out.println("new String(body) ="+new String(body));
            }
         });
         ////channel.close();
         //connection.close();
     }
}