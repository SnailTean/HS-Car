package com.hundsun.hscar.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@Controller
public class SysPageController {

	/**
	 * 手机端-顾客页面
	 */
	@RequestMapping("hscar/app/customer/{url}.html")
	public String customer_page(@PathVariable("url") String url) {
		return "hscar/app/customer/" + url + ".html";
	}

	/**
	 * 手机端-司机页面
	 */
	@RequestMapping("hscar/app/driver/{url}.html")
	public String driver_page(@PathVariable("url") String url) {
		return "hscar/app/driver/" + url + ".html";
	}

}
