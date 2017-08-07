package com.hundsun.hscar.api.customer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.agile.annotation.IgnoreAuth;
import org.agile.annotation.LoginUser;
import org.agile.common.ResultVo;
import org.agile.common.exception.RRException;
import org.agile.common.validator.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.hundsun.hscar.entity.UserEntity;
import com.hundsun.hscar.service.api.IDriverService;
import com.hundsun.hscar.service.api.ITokenService;
import com.hundsun.hscar.service.api.IUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 乘客端系统接口
 *
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@RestController
@RequestMapping("/api/customer/system")
@Api(value = "api-customer-system-controller", description = "乘客端系统接口", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiCustomerSystemController {
	
	@Autowired
    private IUserService userService;
	
	@Autowired
    private IDriverService driverService;
	
    @Autowired
    private ITokenService tokenService;
	
	/**
     * 乘客注册
     */
    @IgnoreAuth
    @PostMapping("register")
    @ApiOperation(value = "乘客注册", notes = "用于注册乘客类用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", dataType="string", name = "mobile", value = "手机号", required = true),
    	@ApiImplicitParam(paramType = "query", dataType="string", name = "password", value = "密码", required = true)
    })
    public ResultVo register(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        userService.register(mobile, password);
        
		return login(mobile, password);
//		return ResultVo.ok();
    }
    
    /**
     * 乘客登录
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "乘客登录", notes = "用于乘客用户登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
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
     * 乘客带验证码登录
     */
    @IgnoreAuth
    @PostMapping("login_with_captcha")
    @ApiOperation(value = "乘客带验证码登录", notes = "用于乘客用户带验证码登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType="string", name = "mobile", value = "手机号", required = true),
        @ApiImplicitParam(paramType = "query", dataType="string", name = "password", value = "密码", required = true),
        @ApiImplicitParam(paramType = "query", dataType="string", name = "captcha", value = "验证码", required = true)
    })
    public ResultVo login_with_captcha(String mobile, String password, String captcha, HttpServletRequest request) {
    	String kaptcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return ResultVo.error("验证码不正确");
		}

        return login(mobile, password);
    }
    
    /**
     * 乘客端注册司机用户
     */
    @PostMapping("register_driver_by_customer")
    @ApiOperation(value = "乘客端注册司机用户", notes = "通过乘客用户，注册司机用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "令牌", required = true),
        @ApiImplicitParam(paramType = "query", dataType="string", name = "driverLicenseNumber", value = "驾照号码", required = true),
        @ApiImplicitParam(paramType = "query", dataType="string", name = "plateNumber", value = "车牌号", required = true)
    })
    public ResultVo register_driver_by_customer(@LoginUser UserEntity user, String driverLicenseNumber, String plateNumber) {
    	Assert.isNull(user, "用户不能为空");
        Assert.isBlank(driverLicenseNumber, "驾照号码不能为空");
        Assert.isBlank(plateNumber, "车牌号不能为空");
        driverService.register(user.getUserId(), driverLicenseNumber, plateNumber);
        return ResultVo.ok("注册司机成功!");
    }

    /**
     * 获取乘客用户信息
     */
    @ResponseBody
    @RequestMapping(value = "userInfo")
	@ApiOperation(value = "获取乘客用户信息", notes = "根据Token获取乘客用户信息")
    @ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "令牌", required = true)
    public ResultVo userInfo(@LoginUser UserEntity user) {
        return ResultVo.ok().put("user", user);
    }
    
    /**
	 * 更新乘客用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "updateUser")
	@ApiOperation(value = "更新乘客用户信息", notes = "更新乘客用户信息")
	@ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "令牌", required = true)
	public ResultVo updateUser(@RequestBody UserEntity user) {
		userService.update(user);
		return ResultVo.ok();
	}
	
	/**
	 * 修改用户密码
	 */
	@ResponseBody
	@RequestMapping(value = "updatePassword")
	@ApiOperation(value = "修改用户密码", notes = "修改用户密码")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "令牌", required = true),
		@ApiImplicitParam(paramType = "query", dataType="string", name = "oldPassword", value = "旧密码", required = true),
    	@ApiImplicitParam(paramType = "query", dataType="string", name = "newPassword", value = "新密码", required = true)
    })
	public ResultVo updatePassword(@LoginUser UserEntity user,String oldPassword, String newPassword) {
		Assert.isNull(user, "用户不能为空!");
		Assert.isNull(oldPassword, "旧密码不能为空!");
		Assert.isNull(newPassword, "新密码不能为空!");
		if(!user.getPassword().equals(DigestUtils.sha256Hex(oldPassword))) {
			throw new RRException("旧密码校验不通过!");
		}
		user.setPassword(newPassword);
		userService.update(user);
		return ResultVo.ok();
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
