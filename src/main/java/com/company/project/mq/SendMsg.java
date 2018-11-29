package com.company.project.mq;

import com.company.project.configurer.RabbitMQConfigurer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMsg {

    // 此接口的默认实现是RabbitTemplate，目前只有一个实现，
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息
     *
     * @param msgContent
     */
    public void send(String msgContent) {
        amqpTemplate.convertAndSend(RabbitMQConfigurer.SPRING_BOOT_EXCHANGE, RabbitMQConfigurer.SPRING_BOOT_BIND_KEY, msgContent);
    }

    public void sendAll(String msgContent) {
        amqpTemplate.convertAndSend("fanout-exchange","",msgContent);
    }
}
