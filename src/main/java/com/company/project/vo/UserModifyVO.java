package com.company.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="userVO",description="参数对象userVO")
@Data
public class UserModifyVO {

    @ApiModelProperty(value="昵称",name="nickname",dataType = "string")
    private String nickname;

    @ApiModelProperty(value="性别",name="sex",dataType = "int",notes = "1代表男，0代表女")
    private Integer sex;
}
