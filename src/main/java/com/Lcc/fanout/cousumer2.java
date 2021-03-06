package com.Lcc.fanout;

import com.Lcc.utils.ConnRabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/28 16:25
 */
public class cousumer2 {
    public static void main(String[] args) throws IOException {
        //test  测试环境调试 提交本地开发 999  测hi是
        Connection connection = ConnRabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare("logs","fanout");

        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"logs","");

        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2："+new String(body));
            }
        });
    }
}