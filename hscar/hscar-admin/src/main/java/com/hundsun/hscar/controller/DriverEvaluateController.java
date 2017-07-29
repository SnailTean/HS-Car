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

import com.hundsun.hscar.entity.DriverEvaluateEntity;
import com.hundsun.hscar.service.api.IDriverEvaluateService;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.ResultVo;


/**
 * 司机评价表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
@Controller
@RequestMapping("/hscar/driverEvaluate")
public class DriverEvaluateController {
	@Autowired
	private IDriverEvaluateService driverEvaluateService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("hscar:driverEvaluate:list")
	public ResultVo list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<DriverEvaluateEntity> driverEvaluateList = driverEvaluateService.queryList(map);
		int total = driverEvaluateService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(driverEvaluateList, total, limit, page);
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hscar:driverEvaluate:info")
	public ResultVo info(@PathVariable("id") Integer id){
		DriverEvaluateEntity driverEvaluate = driverEvaluateService.queryObjectById(id);
		
		return ResultVo.ok().put("driverEvaluate", driverEvaluate);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("hscar:driverEvaluate:save")
	public ResultVo save(@RequestBody DriverEvaluateEntity driverEvaluate){
		driverEvaluateService.save(driverEvaluate);
		
		return ResultVo.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("hscar:driverEvaluate:update")
	public ResultVo update(@RequestBody DriverEvaluateEntity driverEvaluate){
		driverEvaluateService.update(driverEvaluate);
		
		return ResultVo.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("hscar:driverEvaluate:delete")
	public ResultVo delete(@RequestBody Integer[] ids){
		driverEvaluateService.deleteBatch(ids);
		
		return ResultVo.ok();
	}
	
}
