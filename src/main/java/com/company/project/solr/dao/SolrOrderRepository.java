package com.company.project.solr.dao;

import com.company.project.solr.model.SolrOrder;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolrOrderRepository extends SolrCrudRepository<SolrOrder,String> {

    SolrOrder getByOrderNo(String orderNo);

}
