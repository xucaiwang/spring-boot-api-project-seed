package com.company.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


@ApiModel(value="userRegisiterVO",description="参数对象userRegisiterVO")
@Data
public class UserRegisiterVO {

    @ApiModelProperty(value="用户名",name="username",required = true,dataType = "string")
    @NotBlank(message = "用户名不能为空!")
    private String username;

    @ApiModelProperty(value="密码",name="password",required = true,dataType = "string")
    @NotBlank(message = "密码不能为空!")
    private String password;

    @ApiModelProperty(value="昵称",name="nickname",required = true,dataType = "string")
    @NotBlank(message = "昵称不能为空!")
    private String nickname;

    @ApiModelProperty(value="性别",name="sex",required = true,dataType = "int",notes = "1代表男，0代表女")
    @NotNull(message = "性别不能为空!")
    private Integer sex;

}
