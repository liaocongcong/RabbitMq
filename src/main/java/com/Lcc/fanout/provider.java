package com.Lcc.fanout;

import com.Lcc.utils.ConnRabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/28 16:13
 */
public class provider {
    public static void main(String[] args) throws IOException {
        Connection connection=  ConnRabbitUtils.getConnection();
        Channel channel  = connection.createChannel();
        //声明指定交换机  参数1 交换机名称  参数2 交换机类型
        channel.exchangeDeclare("logs","fanout");

        channel.basicPublish("logs","",null,"test famout messages".getBytes());

        ConnRabbitUtils.close(channel,connection);
    }
}