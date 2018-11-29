package com.company.project.mq;

import com.company.project.configurer.RabbitMQConfigurer;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = RabbitMQConfigurer.SPRING_BOOT_QUEUE+"-2",durable = "true"),
        exchange = @Exchange(value = RabbitMQConfigurer.SPRING_BOOT_EXCHANGE, type = ExchangeTypes.TOPIC,durable="true"),
        key = RabbitMQConfigurer.SPRING_BOOT_BIND_KEY)
)
public class ReceiveMsg2 {

    @RabbitHandler
    public void receive_2(String content) {
        System.out.println("[ReceiveMsg-2] receive msg: " + content);
    }

}
