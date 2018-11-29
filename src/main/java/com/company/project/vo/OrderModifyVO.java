package com.company.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(value="orderModifyVO",description="参数对象orderModifyVO")
@Data
public class OrderModifyVO {

    @ApiModelProperty(value="订单别称",name="nickname",required = true,dataType = "string")
    @NotBlank(message = "订单别称不能为空!")
    private String nickname;
}
