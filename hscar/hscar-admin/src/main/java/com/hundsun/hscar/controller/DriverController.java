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

import com.hundsun.hscar.entity.DriverEntity;
import com.hundsun.hscar.service.api.IDriverService;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.ResultVo;


/**
 * 司机信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-26
 */
@Controller
@RequestMapping("/hscar/driver")
public class DriverController {
	@Autowired
	private IDriverService driverService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("hscar:driver:list")
	public ResultVo list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<DriverEntity> driverList = driverService.queryList(map);
		int total = driverService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(driverList, total, limit, page);
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{driverId}")
	@RequiresPermissions("hscar:driver:info")
	public ResultVo info(@PathVariable("driverId") Long driverId){
		DriverEntity driver = driverService.queryObjectById(driverId);
		
		return ResultVo.ok().put("driver", driver);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("hscar:driver:save")
	public ResultVo save(@RequestBody DriverEntity driver){
		driverService.save(driver);
		
		return ResultVo.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("hscar:driver:update")
	public ResultVo update(@RequestBody DriverEntity driver){
		driverService.update(driver);
		
		return ResultVo.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("hscar:driver:delete")
	public ResultVo delete(@RequestBody Long[] driverIds){
		driverService.deleteBatch(driverIds);
		
		return ResultVo.ok();
	}
	
}
