package com.company.project.mongo.dao;

import com.company.project.mongo.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Order getByOrderNo(String orderNo);

    Order getByOrderNoLike(String orderNo);

}
