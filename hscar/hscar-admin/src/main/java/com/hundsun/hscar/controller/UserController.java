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

import com.hundsun.hscar.entity.UserEntity;
import com.hundsun.hscar.service.api.IUserService;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.ResultVo;


/**
 * 用户信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-26
 */
@Controller
@RequestMapping("/hscar/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("hscar:user:list")
	public ResultVo list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<UserEntity> userList = userService.queryList(map);
		int total = userService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(userList, total, limit, page);
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("hscar:user:info")
	public ResultVo info(@PathVariable("userId") Long userId){
		UserEntity user = userService.queryObjectById(userId);
		
		return ResultVo.ok().put("user", user);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("hscar:user:save")
	public ResultVo save(@RequestBody UserEntity user){
		userService.save(user);
		
		return ResultVo.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("hscar:user:update")
	public ResultVo update(@RequestBody UserEntity user){
		userService.update(user);
		
		return ResultVo.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("hscar:user:delete")
	public ResultVo delete(@RequestBody Long[] userIds){
		userService.deleteBatch(userIds);
		
		return ResultVo.ok();
	}
	
}
