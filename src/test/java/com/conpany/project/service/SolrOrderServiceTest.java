package com.conpany.project.service;

import com.company.project.solr.model.SolrOrder;
import com.company.project.solr.service.SolrOrderService;
import com.conpany.project.Tester;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SolrOrderServiceTest extends Tester {

    @Autowired
    private SolrOrderService solrOrderService;

    @Test
    public void testSave(){
        SolrOrder order = new SolrOrder();
        order.setId("5bfe03eaaa7ecd2470db8b2e");
        order.setOrderNo("456789");
        order.setUsername("17875926618");
        order.setNickname("打底裤");
        order.setTotalPrice(158.8);
        solrOrderService.save(order);
    }

    @Test
    public void testGetByOrderNo(){
        SolrOrder order = solrOrderService.getByOrderNo("456789");
        Assert.assertNotNull(order);
        log.info(order.getId()+":"+order.getNickname());
    }
}
