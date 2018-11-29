package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.version.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(description = "会话测试")
@RestController
@RequestMapping("/{apiVersion}/sessionTest")
public class SessionTestController {

    @ApiOperation(value="设置会话", notes="设置会话")
    @ApiImplicitParam(name = "apiVersion", value = "版本", required = true, paramType = "path", dataType = "string",defaultValue = "v1_0_0")
    @PostMapping("/set")
    @ApiVersion("1_0_0")
    public Result set(HttpServletRequest request,HttpSession session){
        session.setAttribute("request Url", request.getRequestURL());
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="获取会话", notes="获取会话")
    @ApiImplicitParam(name = "apiVersion", value = "版本", required = true, paramType = "path", dataType = "string",defaultValue = "v1_0_0")
    @GetMapping("/get")
    @ApiVersion("1_0_0")
    public Result get(HttpSession session){
        StringBuffer url = (StringBuffer) session.getAttribute("request Url");
        return ResultGenerator.genSuccessResult(url);
    }
}
