package com.company.project.solr.service;


import com.company.project.solr.core.SolrService;
import com.company.project.solr.model.SolrOrder;

public interface SolrOrderService extends SolrService<SolrOrder,String> {

    SolrOrder getByOrderNo(String orderNo);
}
