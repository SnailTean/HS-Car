package org.agile.api;

import org.agile.annotation.IgnoreAuth;
import org.agile.common.ResultVo;
import org.agile.common.validator.Assert;
import org.agile.entity.OrderEntity;
import org.agile.service.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 行程订单
 * 
 * @author Ryan
 * @email ryansunboy@gmail.com
 * @date 2017-07-12
 */
@RestController
@RequestMapping("/api")
@Api("订单接口")
public class ApiRouteOrderController {
	@Autowired
	private IOrderService orderService; 
    @PostMapping("order")
    @ApiOperation(value = "发送订单",notes="根据Order对象创建订单")
    @ApiImplicitParam(name = "order", value = "订单详细实体order", required = true, dataType = "OrderEntity")  
    @RequestMapping(value = "", method = RequestMethod.POST)
    public OrderEntity postOrder(@RequestBody OrderEntity order) {
    	orderService.sendOrder();
		return order;
      
    }
}
