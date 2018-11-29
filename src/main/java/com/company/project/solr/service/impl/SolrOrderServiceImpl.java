package com.company.project.solr.service.impl;

import com.company.project.solr.core.AbstractSolrService;
import com.company.project.solr.dao.SolrOrderRepository;
import com.company.project.solr.model.SolrOrder;
import com.company.project.solr.service.SolrOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolrOrderServiceImpl extends AbstractSolrService<SolrOrder,String> implements SolrOrderService {

    @Autowired
    private SolrOrderRepository solrOrderRepository;

    @Override
    public SolrOrder getByOrderNo(String orderNo) {
        return solrOrderRepository.getByOrderNo(orderNo);
    }
}
