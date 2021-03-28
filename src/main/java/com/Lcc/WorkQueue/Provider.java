package com.Lcc.WorkQueue;

import com.Lcc.utils.ConnRabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/25 15:41
 */
public class Provider {

    public static void main(String[] args) throws IOException {

        //获取连接对象
        Connection connection = ConnRabbitUtils.getConnection();

        //获取通道对象
        Channel channel = connection.createChannel();

        //通过通道说明队列
        channel.queueDeclare("work",true,false,false,null);
        for (int i = 1; i < 30; i++) {
            //生产消息
            channel.basicPublish("","work",null,(i+"  hello work queue").getBytes());
        }
        //关闭资源
        ConnRabbitUtils.close(channel,connection);
    }
 }