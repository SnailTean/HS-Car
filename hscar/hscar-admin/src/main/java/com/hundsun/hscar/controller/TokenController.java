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

import com.hundsun.hscar.entity.TokenEntity;
import com.hundsun.hscar.service.api.ITokenService;
import org.agile.common.page.PageUtils;
import org.agile.common.page.Query;
import org.agile.common.ResultVo;


/**
 * 用户Token
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-26
 */
@Controller
@RequestMapping("/hscar/token")
public class TokenController {
	@Autowired
	private ITokenService tokenService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("hscar:token:list")
	public ResultVo list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TokenEntity> tokenList = tokenService.queryList(map);
		int total = tokenService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tokenList, total, limit, page);
		
		return ResultVo.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("hscar:token:info")
	public ResultVo info(@PathVariable("userId") Long userId){
		TokenEntity token = tokenService.queryObjectById(userId);
		
		return ResultVo.ok().put("token", token);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("hscar:token:save")
	public ResultVo save(@RequestBody TokenEntity token){
		tokenService.save(token);
		
		return ResultVo.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("hscar:token:update")
	public ResultVo update(@RequestBody TokenEntity token){
		tokenService.update(token);
		
		return ResultVo.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("hscar:token:delete")
	public ResultVo delete(@RequestBody Long[] userIds){
		tokenService.deleteBatch(userIds);
		
		return ResultVo.ok();
	}
	
}
