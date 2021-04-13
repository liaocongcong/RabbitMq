package com.Lcc.topic;

import com.Lcc.utils.ConnRabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/29 17:29
 */
public class Customer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = ConnRabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机以及交换机类型
        channel.exchangeDeclare("topics","topic");
        //新建一个临时队列
        String queue = channel.queueDeclare().getQueue();

        //绑定队列和交换机  动态配置形式  route key
        channel.queueBind(queue,"topics","user.#");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });
    }
}