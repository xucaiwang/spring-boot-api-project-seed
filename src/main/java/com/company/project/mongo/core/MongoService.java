package com.company.project.mongo.core;

import java.io.Serializable;
import java.util.List;

public interface MongoService<T,ID extends Serializable> {

    void save(T model);//持久化

    void deleteById(ID id);//通过主鍵刪除

    void update(T model);//更新

    T findById(ID id);//通过ID查找

    List<T> findAll();//获取所有
}
