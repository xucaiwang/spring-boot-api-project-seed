package com.company.project.mongo.service.impl;

import com.company.project.mongo.core.AbstractMongoService;
import com.company.project.mongo.dao.OrderRepository;
import com.company.project.mongo.model.Order;
import com.company.project.mongo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends AbstractMongoService<Order,String> implements OrderService{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrderRepository orderRepository;

    public Order getByOrderNo(String orderNo) {
        return orderRepository.getByOrderNo(orderNo);
    }

    public Order getByOrderNoLike(String orderNo) {
        return orderRepository.getByOrderNoLike(orderNo);
    }

    public void removeByOrderNo(String orderNo) {
        Query q = new Query(new Criteria("orderNo").is(orderNo));
        mongoTemplate.remove(q,Order.class);
    }

//    public void update(Order order) {
//        Query q = new Query(new Criteria("orderNo").is(order.getOrderNo()));
//        Update update = new Update().set("nickname", order.getNickname());
//        mongoTemplate.updateMulti(q, update, Order.class);
//    }

}
