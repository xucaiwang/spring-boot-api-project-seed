package com.company.project.solr.model;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Data
@SolrDocument(solrCoreName = "collection1")
public class SolrOrder {

    @Field
    private String id;

    @Field(value = "order_no")
    private String orderNo;

    @Field(value = "user_name")
    private String username;

    @Field(value = "nick_name")
    private String nickname;

    @Field(value = "order_price")
    private double totalPrice;

}
