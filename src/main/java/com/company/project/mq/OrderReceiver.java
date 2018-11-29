package com.company.project.mq;

import com.company.project.mongo.model.Order;
import com.company.project.solr.model.SolrOrder;
import com.company.project.solr.service.SolrOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "order")
@Slf4j
public class OrderReceiver {

    @Autowired
    private SolrOrderService solrOrderService;

    @RabbitHandler
    public void process(Order order) {
        log.info("接收到的消息:"+order.toString());
        SolrOrder solrOrder = new SolrOrder();
        solrOrder.setId(order.getId());
        solrOrder.setOrderNo(order.getOrderNo());
        solrOrder.setNickname(order.getNickname());
        solrOrder.setUsername(order.getUsername());
        solrOrder.setTotalPrice(order.getTotalPrice());
        solrOrderService.save(solrOrder);
    }

}
