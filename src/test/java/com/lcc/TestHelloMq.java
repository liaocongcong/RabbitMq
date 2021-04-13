package com.lcc;

import com.Lcc.RabbitMqMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: liaocongcong
 * @Date: 2021/3/29 21:12
 */
@SpringBootTest(classes = RabbitMqMain.class)
@RunWith(SpringRunner.class)
public class TestHelloMq {

    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //fantOut 广播
    @Test
    public void testFantOut(){
        rabbitTemplate.convertAndSend("logs","","FantOut的模型发送消息");
    }

    //work
    @Test
    public void test2(){

        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","work 模型");
        }
    }

    //hello world
    @Test
    public void test(){

        rabbitTemplate.convertAndSend("hello","helloworld");
    }
}