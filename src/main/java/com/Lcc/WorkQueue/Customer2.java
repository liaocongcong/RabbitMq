package com.Lcc.WorkQueue;

import com.Lcc.utils.ConnRabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/26 17:13
 */
public class Customer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = ConnRabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        //每次只消费一个消息
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2 "+new String(body));
                //手动确认  参数 1 手动确认消息标识  参数2 false 每次确认一下
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}