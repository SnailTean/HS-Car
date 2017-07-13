package org.agile.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableWebMvc
@EnableSwagger
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * 链式编程：来定制API样式 后续会加上分组信息
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*")
			.useDefaultResponseMessages(false)
//			.pathProvider(new GtPaths())
			.apiVersion("0.1").swaggerGroup("user");
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("恒生拼车API接口平台", 
			"提供详细的后台所有Restful接口", 
			"",
			"416848503@qq.com", 
			"张铭明", 
			"");
		return apiInfo;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	class GtPaths extends SwaggerPathProvider {
		@Override
		protected String applicationPath() {
			return "/restapi";
		}

		@Override
		protected String getDocumentationPath() {
			return "/restapi";
		}
	}

}