package com.company.project.mongo.service;

import com.company.project.mongo.core.MongoService;
import com.company.project.mongo.model.Order;

public interface OrderService extends MongoService<Order,String> {

    Order getByOrderNo(String orderNo);

    Order getByOrderNoLike(String orderNo);

    void removeByOrderNo(String orderNo);

}
