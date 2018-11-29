package com.company.project.mongo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value="order",description="参数对象order")
@Data
@Document(collection = "t_order")
@ToString
public class Order implements Serializable {

    @Id
    private String id;

    @ApiModelProperty(value="订单编号",name="orderNo",required = true,dataType = "string")
    @Field(value = "order_no")
    @NotBlank(message = "订单编号不能为空!")
    private String orderNo;

    @Field(value = "user_name")
    private String username;

    @ApiModelProperty(value="订单别称",name="nickname",required = true,dataType = "string")
    @Field(value = "nick_name")
    @NotBlank(message = "订单别称不能为空!")
    private String nickname;

    @ApiModelProperty(value="订单总价",name="totalPrice",required = true,dataType = "double")
    @Field(value = "total_price")
    @NotNull(message = "订单总价不能为空!")
    private double totalPrice;
}
