package com.company.project.configurer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfigurer {

    // 队列名称
    public final static String SPRING_BOOT_QUEUE = "spring-boot-queue";
    // 交换机名称
    public final static String SPRING_BOOT_EXCHANGE = "spring-boot-exchange";
    // 绑定的值
    public static final String SPRING_BOOT_BIND_KEY = "spring-boot-bind-key";

    @Bean
    public Queue orderQueue() {
        return new Queue("order");
    }

    // === 在RabbitMQ上创建queue,exchange,binding 方法一：通过@Bean实现 begin ===
    /**
     * 定义队列：
     * @return
     */
    @Bean
    public Queue queue1() {
        return new Queue(SPRING_BOOT_QUEUE+"-1",true);
    }

    /**
     * 定义交换机
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(SPRING_BOOT_EXCHANGE,true,false);
    }

    /**
     * 定义绑定
     * @param queue1
     * @param exchange
     * @return
     */
    @Bean
    public Binding binding(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(SPRING_BOOT_BIND_KEY );
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange",true,false);
    }

    @Bean
    Binding bindingExchangeB(Queue queue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }
}
