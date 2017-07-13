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

import com.hundsun.hscar.entity.RouteDetailEntity;
import com.hundsun.hscar.service.api.IRouteDetailService;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.ResultVo;


/**
 * 路线详情表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-13
 */
@Controller
@RequestMapping("/hundsun/hscar/routeDetail")
public class RouteDetailController {
	@Autowired
	private IRouteDetailService routeDetailService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("hundsun:hscar:routeDetail:list")
	public ResultVo list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<RouteDetailEntity> routeDetailList = routeDetailService.queryList(map);
		int total = routeDetailService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(routeDetailList, total, limit, page);
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{routeId}")
	@RequiresPermissions("hundsun:hscar:routeDetail:info")
	public ResultVo info(@PathVariable("routeId") Long routeId){
		RouteDetailEntity routeDetail = routeDetailService.queryObject(routeId);
		
		return ResultVo.ok().put("routeDetail", routeDetail);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("hundsun:hscar:routeDetail:save")
	public ResultVo save(@RequestBody RouteDetailEntity routeDetail){
		routeDetailService.save(routeDetail);
		
		return ResultVo.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("hundsun:hscar:routeDetail:update")
	public ResultVo update(@RequestBody RouteDetailEntity routeDetail){
		routeDetailService.update(routeDetail);
		
		return ResultVo.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("hundsun:hscar:routeDetail:delete")
	public ResultVo delete(@RequestBody Long[] routeIds){
		routeDetailService.deleteBatch(routeIds);
		
		return ResultVo.ok();
	}
	
}
