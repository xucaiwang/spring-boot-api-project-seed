package com.company.project.mongo.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractMongoService<T,ID extends Serializable> implements MongoService<T,ID> {

    @Autowired
    private MongoRepository<T,ID> mongoRepository;

    @Override
    public void save(T model) {
        mongoRepository.save(model);
    }

    @Override
    public void deleteById(ID id) {
        mongoRepository.delete(id);
    }

    @Override
    public void update(T model) {
        mongoRepository.save(model);
    }

    @Override
    public T findById(ID id) {
        return mongoRepository.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return mongoRepository.findAll();
    }
}
