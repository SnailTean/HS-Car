package com.hundsun.hscar.api.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hundsun.hscar.dto.OrderDto;
import com.hundsun.hscar.entity.RouteDetailEntity;
import com.hundsun.hscar.service.api.IOrderService;
import com.hundsun.hscar.vo.OrderVo;
import com.hundsun.hscar.vo.RouteDetailVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 顾客订单
 * 
 * @author Ryan
 * @email ryansunboy@gmail.com
 * @date 2017-07-12
 */
@RestController
@RequestMapping("/api/customer/order")
@Api(value = "api-customer-order-controller", description = "顾客订单接口", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiCustomerOrderController {
	
	@Autowired
	private IOrderService orderService;

	@PostMapping("order")
	@ApiOperation(value = "发送订单", notes = "根据Order对象创建订单")
	@ApiImplicitParam(name = "order", value = "订单详细实体order", required = true, dataType = "OrderVo")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public OrderVo postOrder(@RequestBody OrderVo order) {
		RouteDetailVo routeDetailVo  = order.getRouteDetail();
		RouteDetailEntity routeEntity = new RouteDetailEntity();
		routeEntity.setDeparture(routeDetailVo.getDeparture());
		routeEntity.setDepCoordinate(routeDetailVo.getDep_coordinate());
		routeEntity.setDesCoordinate(routeDetailVo.getDes_coordinate());
		routeEntity.setDestination(routeDetailVo.getDestination());
		//routeEntity.setUserId(userId);
		OrderDto orderDto=new OrderDto();
		orderDto.setRouteDetail(routeEntity);
		orderDto.setOrderType(order.getOrderType());
		orderDto.setTime(order.getTime());
		orderService.sendOrder(orderDto);
		return order;
	}
	
}
