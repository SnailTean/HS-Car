package com.hundsun.hscar.api.customer;

import java.util.List;

import org.agile.annotation.LoginUser;
import org.agile.common.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hundsun.hscar.dto.OrderDto;
import com.hundsun.hscar.entity.RouteDetailEntity;
import com.hundsun.hscar.entity.UserEntity;
import com.hundsun.hscar.service.api.IOrderService;
import com.hundsun.hscar.vo.OrderVo;
import com.hundsun.hscar.vo.RouteDetailVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 顾客订单
 * 
 * @author Ryan
 * @email ryansunboy@gmail.com
 * @date 2017-07-12
 */
@RestController
@RequestMapping("/api/customer/orders")
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
	
	 	@ResponseBody
	    @RequestMapping(value = "orders")
		@ApiOperation(value = "获取订单信息", notes = "根据Token和订单类型查询订单信息")
	 	  @ApiImplicitParams({
	 		    @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
	 	        @ApiImplicitParam(paramType = "query", dataType="string", name = "orderType", value = "订单类型", required = true),
	 	    })
	    public ResultVo userInfo(@LoginUser UserEntity user,String orderType) {
	 		
	 		OrderDto order=null;//orderService.getOrderBytype(user.getUserId(),orderType);
	        return ResultVo.ok().put("order", order);
	    }
	 	
	 	@ResponseBody
	    @RequestMapping(value = "waiting")
		@ApiOperation(value = "获取待处理订单信息", notes = "根据Token查询待处理订单信息")
	    @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true)
	    public ResultVo waitingOrders(@LoginUser UserEntity user) {
	 		
	 		List<OrderDto> waitingOrders=orderService.getWaitingOrders(user.getUserId());
	        return ResultVo.ok().put("waitingOrders", waitingOrders);
	    }
	
	 	@ResponseBody
	    @RequestMapping(value = "sameWay")
		@ApiOperation(value = "获取待处理订单信息", notes = "根据Token查询待处理订单信息")
	    @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true)
	    public ResultVo sameWayOrders(@LoginUser UserEntity user) {
	 		
	 		List<OrderDto> sameWayOrders=orderService.getSameWayOrders(user.getUserId());
	        return ResultVo.ok().put("sameWayOrders", sameWayOrders);
	    }
	
	
}
