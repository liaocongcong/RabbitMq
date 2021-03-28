package com.Lcc.WorkQueue;

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
public class Customer1 {

    public static void main(String[] args) throws IOException {

        Connection connection = ConnRabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1 "+new String(body));
            }
        });
    }
}