package com.Lcc.direct;

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
        Connection connection = ConnRabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("logs_direct","direct");

        String queue = channel.queueDeclare().getQueue();

        String exchangeName = "logs_direct";
        //channel.queueBind()
        //基于route key 绑定队列和交换机
        channel.queueBind(queue,exchangeName,"info");
        channel.queueBind(queue,exchangeName,"error");
        channel.queueBind(queue,exchangeName,"warning");

        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });
    }
}