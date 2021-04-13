package com.Lcc.direct;

import com.Lcc.utils.ConnRabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/28 16:25
 */
public class cousumer3 {
    public static void main(String[] args) throws IOException {
        Connection connection = ConnRabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare("logs","fanout");

        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"logs","");

        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3："+new String(body));
            }
        });
    }
}