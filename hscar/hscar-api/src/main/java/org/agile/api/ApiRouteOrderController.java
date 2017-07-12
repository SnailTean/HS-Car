package org.agile.api;

import org.agile.service.api.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

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
}
