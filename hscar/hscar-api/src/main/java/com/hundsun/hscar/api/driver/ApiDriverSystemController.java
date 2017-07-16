package com.hundsun.hscar.api.driver;

import java.util.Map;

import org.agile.annotation.IgnoreAuth;
import org.agile.annotation.LoginUser;
import org.agile.common.ResultVo;
import org.agile.common.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hundsun.hscar.entity.UserEntity;
import com.hundsun.hscar.service.api.ITokenService;
import com.hundsun.hscar.service.api.IUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 车主端系统接口
 *
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@RestController
@RequestMapping("/api/driver/system")
@Api(value = "api-driver-system-controller", description = "车主端系统接口", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiDriverSystemController {
	
	@Autowired
    private IUserService userService;
	
    @Autowired
    private ITokenService tokenService;
	
	/**
     * 注册
     */
    @IgnoreAuth
    @PostMapping("register")
    @ApiOperation(value = "车主注册", notes = "用于注册车主类用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", dataType="string", name = "mobile", value = "手机号", required = true),
    	@ApiImplicitParam(paramType = "query", dataType="string", name = "password", value = "密码", required = true)
    })
    public ResultVo register(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        userService.save(mobile, password);

        return ResultVo.ok();
    }
    
    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "车主登录", notes = "用于车主用户登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType="string", name = "mobile", value = "手机号", required = true),
        @ApiImplicitParam(paramType = "query", dataType="string", name = "password", value = "密码", required = true)
    })
    public ResultVo login(String mobile, String password) {
    	Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return ResultVo.ok(map);
    }

    /**
     * 获取用户信息
     */
//    @ResponseBody
//    @RequestMapping(value = "userInfo")
    @GetMapping("userInfo")
	@ApiOperation(value = "获取用户信息", notes = "根据Token获取用户信息")
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
}
