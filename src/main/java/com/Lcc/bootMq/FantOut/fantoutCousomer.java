package com.Lcc.bootMq.FantOut;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/4/3 15:24
 */
@Component
public class fantoutCousomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建临时队列
                    exchange = @Exchange(value = "logs",type = "fanout")//绑定交换机
            )
    })
    public void receivel(String messages){
        System.out.println("message1 :"+messages);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建临时队列
                    exchange = @Exchange(value = "logs",type = "fanout")//绑定交换机
            )
    })
    public void receivel2(String messages){
        System.out.println("message2 :"+messages);
    }
}