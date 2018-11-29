package com.company.project.solr.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.io.Serializable;

public abstract class AbstractSolrService<T,ID extends Serializable> implements SolrService<T,ID> {

    @Autowired
    private SolrCrudRepository<T,ID> solrCrudRepository;

    @Override
    public void save(T model) {
        solrCrudRepository.save(model);
    }

    @Override
    public void deleteById(ID id) {
        solrCrudRepository.delete(id);
    }

    @Override
    public void update(T model) {
        solrCrudRepository.save(model);
    }

    @Override
    public T findById(ID id) {
        return solrCrudRepository.findOne(id);
    }

}
