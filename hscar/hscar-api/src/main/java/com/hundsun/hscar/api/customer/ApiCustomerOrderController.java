package com.hundsun.hscar.api.customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hundsun.hscar.constant.OrderStatusEnum;
import com.hundsun.hscar.constant.OrderTypeEnum;
import com.hundsun.hscar.dto.CarOrderDto;
import com.hundsun.hscar.vo.CarOrderVo;
import org.agile.annotation.LoginUser;
import org.agile.common.ResultVo;
import org.agile.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hundsun.hscar.constant.UserTypeEnum;
import com.hundsun.hscar.dto.BaseOrderDto;
import com.hundsun.hscar.dto.OrderDto;
import com.hundsun.hscar.dto.WaitingOrderDto;
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
	@ApiImplicitParam(name = "carOrderVo", value = "订单详细实体order", required = true, dataType = "CarOrderVo")
	public ResultVo postOrder(@LoginUser UserEntity user,@RequestBody CarOrderVo carOrderVo) {
		WaitingOrderDto waitingOrderDto = orderService.getWaitingOrder(user.getUserId());
		if(CommonUtils.isNotEmpty(waitingOrderDto)){
			return ResultVo.error("存在未出行订单！");
		}
		CarOrderDto carOrderDto = tansferVoToCarOrderDto(carOrderVo);
		orderService.sendOrder(carOrderDto,user);
		return ResultVo.ok();
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
	 		
	 		WaitingOrderDto waitingOrder=orderService.getWaitingOrder(user.getUserId());
	        return ResultVo.ok().put("waitingOrder", waitingOrder);
	    }
	
	 	@ResponseBody
	    @RequestMapping(value = "sameWay")
		@ApiOperation(value = "获取待处理订单信息", notes = "根据Token查询待处理订单信息")
	    @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true)
	    public ResultVo sameWayOrders(@LoginUser UserEntity user) {
	 		
	 		List<WaitingOrderDto> sameWayOrders=orderService.getSameWayOrders(user.getUserId(),UserTypeEnum.PASSENGER.getValue());
	        return ResultVo.ok().put("sameWayOrders", sameWayOrders);
	    }
	 	@ResponseBody
	    @RequestMapping(value = "complete")
		@ApiOperation(value = "获取已完成订单信息", notes = "根据Token查询已完成订单信息")
	    @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true)
	    public ResultVo completeOrders(@LoginUser UserEntity user) {
	 		
	 		List<BaseOrderDto> completeOrders = orderService.getCompleteOrders(user.getUserId());
	 		return ResultVo.ok().put("completeOrders", completeOrders);
	    }

	private CarOrderDto tansferVoToCarOrderDto(CarOrderVo carOrderVo) {
		CarOrderDto carOrderDto = new CarOrderDto();
		carOrderDto.setNum(carOrderVo.getNum());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		try {
			date = sdf.parse(carOrderVo.getGoTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(Math.abs(System.currentTimeMillis() - date.getTime()) < 300000){
			carOrderDto.setOrderType(OrderTypeEnum.REALTIME.getValue());
		} else {
			carOrderDto.setOrderType(OrderTypeEnum.APPOINTMENT.getValue());
		}
		carOrderDto.setGoTime(date);
		carOrderDto.setDestination(carOrderVo.getDestination());
		carOrderDto.setDeparture(carOrderVo.getDeparture());
		carOrderDto.setPrice(carOrderVo.getPrice());
		carOrderDto.setDeparture(carOrderVo.getDeparture());
		carOrderDto.setOrderStatus(OrderStatusEnum.PUBLISHING.getValue());
		carOrderDto.setDepLongitude(carOrderVo.getDepLongitude());
		carOrderDto.setDepLatitude(carOrderVo.getDepLatitude());
		carOrderDto.setDesLongitude(carOrderVo.getDesLongitude());
		carOrderDto.setDesLatitude(carOrderVo.getDesLatitude());
		return carOrderDto;
	}

}
