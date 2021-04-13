package com.Lcc.bootMq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/29 21:41
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value="hello"))
public class HelloCustomer {

    @RabbitHandler
    public void receiver(String messages){
        System.out.println("messages = "+messages);
    }
}