package com.Lcc.direct;

import com.Lcc.utils.ConnRabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/29 15:05
 */
public class provider {
    public static void main(String[] args) throws IOException {
        Connection connection=  ConnRabbitUtils.getConnection();
        Channel channel  = connection.createChannel();

        //通过通道声明交换机  参数1 交换机名称  参数2 direct 路由模式
        channel.exchangeDeclare("logs_direct","direct");
        String routeingKey = "info";

        String routekey = "warning";
        channel.basicPublish("logs_direct",routekey,null,"途安一".getBytes());

        ConnRabbitUtils.close(channel,connection);
    }
}