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

import com.hundsun.hscar.entity.FriendshipEntity;
import com.hundsun.hscar.service.api.IFriendshipService;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.ResultVo;


/**
 * 好友表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
@Controller
@RequestMapping("/hscar/friendship")
public class FriendshipController {
	@Autowired
	private IFriendshipService friendshipService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("hscar:friendship:list")
	public ResultVo list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<FriendshipEntity> friendshipList = friendshipService.queryList(map);
		int total = friendshipService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(friendshipList, total, limit, page);
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hscar:friendship:info")
	public ResultVo info(@PathVariable("id") Integer id){
		FriendshipEntity friendship = friendshipService.queryObjectById(id);
		
		return ResultVo.ok().put("friendship", friendship);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("hscar:friendship:save")
	public ResultVo save(@RequestBody FriendshipEntity friendship){
		friendshipService.save(friendship);
		
		return ResultVo.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("hscar:friendship:update")
	public ResultVo update(@RequestBody FriendshipEntity friendship){
		friendshipService.update(friendship);
		
		return ResultVo.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("hscar:friendship:delete")
	public ResultVo delete(@RequestBody Integer[] ids){
		friendshipService.deleteBatch(ids);
		
		return ResultVo.ok();
	}
	
}
