package com.company.project.mq;

import com.company.project.configurer.RabbitMQConfigurer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMQConfigurer.SPRING_BOOT_QUEUE+"-1")
public class ReceiveMsg1 {

    /**
     * 获取信息:
     *  queue也可以支持RabbitMQ中对队列的模糊匹配
     * @param content
     */
    @RabbitHandler
    public void receive_1(String content) {
        System.out.println("[ReceiveMsg-1] receive msg: " + content);
    }
}
