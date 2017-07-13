package org.agile.api;

import org.agile.annotation.IgnoreAuth;
import org.agile.annotation.LoginUser;
import org.agile.common.ResultVo;
import org.agile.entity.UserEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * API测试接口
 *
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@RestController
@RequestMapping("/api/test")
@Api(value = "test", description = "测试接口", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiTestController {

    /**
     * 获取用户信息
     */
	@GetMapping("userInfo")
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true)
    public ResultVo userInfo(@LoginUser UserEntity user) {
        return ResultVo.ok().put("user", user);
    }

    /**
     * 忽略Token验证测试
     */
    @IgnoreAuth
    @GetMapping("notToken")
    @ApiOperation(value = "忽略Token验证测试")
    public ResultVo notToken() {
        return ResultVo.ok().put("message", "无需token也能访问。。。");
    }
    
    // @RequestBody只能有1个
  	// 使用了@RequestBody，不能在拦截器中，获得流中的数据，再json转换，拦截器中，也不清楚数据的类型，无法转换成java对象
    
    @ResponseBody
 	@RequestMapping(value = "list", method = RequestMethod.POST)
 	@ApiOperation(value = "获得用户列表", notes = "列表信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResultVo list(
 			@ApiParam(value = "分类ID", required = true) @RequestParam Long categoryId,
 			@ApiParam(value = "分类ID", required = true) @RequestParam Long categoryId2,
 			@ApiParam(value = "token", required = true) @RequestParam String token) {
 		return new ResultVo();
 	}
    
 	@ResponseBody
 	@RequestMapping(value = "add", method = RequestMethod.POST)
 	@ApiOperation(value = "添加用户", notes = "获取商品信息(用于数据同步)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResultVo add(@RequestBody UserEntity user) {
 		return new ResultVo();
 	}
 	
 	@ResponseBody
 	@RequestMapping(value = "update", method = RequestMethod.GET)
 	@ApiOperation(value = "update用户", notes = "获取商品信息(用于数据同步)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResultVo update(UserEntity user) {
 		return new ResultVo();
 	}
}
