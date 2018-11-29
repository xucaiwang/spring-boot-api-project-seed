package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.mongo.model.Order;
import com.company.project.mongo.service.OrderService;
import com.company.project.version.ApiVersion;
import com.company.project.vo.OrderModifyVO;
import com.company.project.websocket.GlobalUserUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Api(description = "订单管理")
@RestController
@RequestMapping("/{apiVersion}/order")
@Slf4j
public class OrderController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @ApiOperation(value="保存订单", notes="保存订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "apiVersion", value = "版本", required = true, paramType = "path", dataType = "string",defaultValue = "v1_0_0"),
        @ApiImplicitParam(name = "order", value = "传入json格式", required = true, paramType = "body", dataType = "order")
    })
    @PostMapping("/save")
    @ApiVersion("1_0_0")
    public Result save(Authentication authentication,@RequestBody Order order) {
        order.setUsername(authentication.getName());
        orderService.save(order);
        amqpTemplate.convertAndSend("order",order);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="获取订单", notes="获取订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiVersion", value = "版本", required = true, paramType = "path", dataType = "string",defaultValue = "v1_0_0"),
            @ApiImplicitParam(name = "orderNo", value = "订单编号", required = true, paramType = "path", dataType = "string")
    })
    @GetMapping("/getByOrderNo/{orderNo}")
    @ApiVersion("1_0_0")
    public Result<Order> getByOrderNo(@PathVariable String orderNo) {
        Order order = orderService.getByOrderNo(orderNo);
        return ResultGenerator.genSuccessResult(order);
    }

    @ApiOperation(value="获取订单", notes="获取订单，模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiVersion", value = "版本", required = true, paramType = "path", dataType = "string",defaultValue = "v1_0_0"),
            @ApiImplicitParam(name = "orderNo", value = "订单编号", required = true, paramType = "path", dataType = "string")
    })
    @GetMapping("/getByOrderNoLike/{orderNo}")
    @ApiVersion("1_0_0")
    public Result getByOrderNoLike(@PathVariable String orderNo) {
        Order order = orderService.getByOrderNoLike(orderNo);
        return ResultGenerator.genSuccessResult(order);
    }

    @ApiOperation(value="删除订单", notes="删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiVersion", value = "版本", required = true, paramType = "path", dataType = "string",defaultValue = "v1_0_0"),
            @ApiImplicitParam(name = "orderNo", value = "订单编号", required = true, paramType = "path", dataType = "string")
    })
    @DeleteMapping("/removeByOrderNo/{orderNo}")
    @ApiVersion("1_0_0")
    public Result removeByOrderNo(@PathVariable String orderNo) {
        orderService.removeByOrderNo(orderNo);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="修改订单", notes="修改订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiVersion", value = "版本", required = true, paramType = "path", dataType = "string",defaultValue = "v1_0_0"),
            @ApiImplicitParam(name = "orderNo", value = "订单编号", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "orderModifyVO", value = "传入json格式", required = true, paramType = "body", dataType = "orderModifyVO")
    })
    @PutMapping("/update/{orderNo}")
    @ApiVersion("1_0_0")
    public Result update(@PathVariable String orderNo,@RequestBody OrderModifyVO orderModifyVO) {
//        Order order = new Order();
//        order.setOrderNo(orderNo);
//        order.setNickname(orderModifyVO.getNickname());
        Order order = orderService.getByOrderNo(orderNo);
        order.setNickname(orderModifyVO.getNickname());
        orderService.update(order);
        return ResultGenerator.genSuccessResult();
    }
}
