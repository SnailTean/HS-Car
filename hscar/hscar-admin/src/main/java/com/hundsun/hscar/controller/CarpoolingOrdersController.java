package com.hundsun.hscar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.hundsun.hscar.entity.CarpoolingOrdersEntity;
import com.hundsun.hscar.service.api.ICarpoolingOrdersService;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.ResultVo;


/**
 * 订单表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
@Controller
@RequestMapping("/hundsun/hscar/carpoolingOrders")
public class CarpoolingOrdersController {
	@Autowired
	private ICarpoolingOrdersService carpoolingOrdersService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("hundsun:hscar:carpoolingOrders:list")
	public ResultVo list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CarpoolingOrdersEntity> carpoolingOrdersList = carpoolingOrdersService.queryList(map);
		int total = carpoolingOrdersService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(carpoolingOrdersList, total, limit, page);
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{orderId}")
	@RequiresPermissions("hundsun:hscar:carpoolingOrders:info")
	public ResultVo info(@PathVariable("orderId") Long orderId){
		CarpoolingOrdersEntity carpoolingOrders = carpoolingOrdersService.queryObjectById(orderId);
		
		return ResultVo.ok().put("carpoolingOrders", carpoolingOrders);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("hundsun:hscar:carpoolingOrders:save")
	public ResultVo save(@RequestBody CarpoolingOrdersEntity carpoolingOrders){
		carpoolingOrdersService.save(carpoolingOrders);
		
		return ResultVo.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("hundsun:hscar:carpoolingOrders:update")
	public ResultVo update(@RequestBody CarpoolingOrdersEntity carpoolingOrders){
		carpoolingOrdersService.update(carpoolingOrders);
		
		return ResultVo.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("hundsun:hscar:carpoolingOrders:delete")
	public ResultVo delete(@RequestBody Long[] orderIds){
		carpoolingOrdersService.deleteBatch(orderIds);
		
		return ResultVo.ok();
	}
	
}
