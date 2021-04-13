package com.Lcc.topic;

import com.Lcc.utils.ConnRabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/29 17:16
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = ConnRabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机以及交换机类型  topic
        channel.exchangeDeclare("topics","topic");

        //发布消息
        String routekey = "user.save.bu";
        channel.basicPublish("topics",routekey,null,("这里是topic动态路由,routekey:["+routekey+"]").getBytes());

        ConnRabbitUtils.close(channel,connection);

    }
}