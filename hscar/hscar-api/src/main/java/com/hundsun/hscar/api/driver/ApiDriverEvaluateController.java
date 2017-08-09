package com.hundsun.hscar.api.driver;

import java.math.BigDecimal;

import org.agile.annotation.LoginUser;
import org.agile.common.ResultVo;
import org.agile.common.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hundsun.hscar.entity.DriverEntity;
import com.hundsun.hscar.entity.UserEntity;
import com.hundsun.hscar.service.api.IDriverEvaluateService;
import com.hundsun.hscar.service.api.IDriverService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 司机评价
 *
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-08-07
 */
@RestController
@RequestMapping("/api/driver/evaluate")
@Api(value = "api-driver-evaluate-controller", description = "司机评价接口", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiDriverEvaluateController {
	
	@Autowired
    private IDriverService driverService;
	
	@Autowired
	private IDriverEvaluateService driverEvaluateService;
	
	/**
     * 获取司机评分
     */
    @ResponseBody
    @RequestMapping(value = "driverRate")
	@ApiOperation(value = "获取司机评分", notes = "根据Token获取司机评分")
    @ApiImplicitParam(paramType = "header", dataType="string", name = "token", value = "令牌", required = true)
    public ResultVo driverRate(@LoginUser UserEntity user) {
    	Assert.isNull(user, "用户信息不能为空!");
    	DriverEntity driverEntity = driverService.queryObjectByUserId(user.getUserId());
    	Assert.isNull(driverEntity, "司机信息不能为空!");
    	BigDecimal rate = driverEvaluateService.getDriverRate(driverEntity.getDriverId());
    	if(rate == null) {
    		rate = new BigDecimal("0");
    	}
        return ResultVo.ok().put("rate", rate);
    }
}
