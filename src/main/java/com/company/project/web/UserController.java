package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.service.UserService;
import com.company.project.version.ApiVersion;
import com.company.project.vo.UserModifyVO;
import com.company.project.vo.UserRegisiterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(description = "用户管理")
@RestController
@RequestMapping("/{apiVersion}/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="用户注册", notes="手机号、密码都是必填项")
    @ApiImplicitParam(name = "apiVersion", value = "版本", required = true, paramType = "path", dataType = "string",defaultValue = "v1_0_0")
    @PostMapping("/regisiter")
    @ApiVersion("1_0_0")
    public Result regisiter(@RequestBody @Valid @ApiParam(name="userVO",value="传入json格式",required=true) UserRegisiterVO userVO){
        userService.regisiter(userVO);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="获取用户个人信息", notes="获取用户个人信息")
    @ApiImplicitParam(name = "apiVersion", value = "版本", required = true, paramType = "path", dataType = "string",defaultValue = "v1_0_0")
    @GetMapping("/info")
    @ApiVersion("1_0_0")
    public Result info(Authentication authentication){
        return ResultGenerator.genSuccessResult(userService.findByUsername(authentication.getName()));
    }

    @ApiOperation(value="更新用户个人信息", notes="更新用户个人信息")
    @ApiImplicitParam(name = "apiVersion", value = "版本", required = true, paramType = "path", dataType = "string",defaultValue = "v1_0_0")
    @PutMapping("/modify")
    @ApiVersion("1_0_0")
    public Result modify(Authentication authentication,@RequestBody  @ApiParam(name="参数对象userModifyVO",value="传入json格式",required=true) UserModifyVO userModifyVO){
        userService.modify(authentication.getName(),userModifyVO);
        return ResultGenerator.genSuccessResult();
    }

}
