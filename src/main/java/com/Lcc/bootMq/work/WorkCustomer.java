package com.Lcc.bootMq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/4/3 15:03
 */
@Component
public class WorkCustomer {

    //一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void resteat(String messages){
        System.out.println("message1 ："+messages);
    }


    //一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void resteats(String messages){
        System.out.println("message2 ："+messages);
    }

}