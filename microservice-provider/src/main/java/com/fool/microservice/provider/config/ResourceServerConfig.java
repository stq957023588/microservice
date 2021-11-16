package com.fool.microservice.provider.config;

import com.alibaba.fastjson.JSON;
import com.fool.microservice.provider.customize.CustomizeAccessDecisionManager;
import com.fool.microservice.provider.customize.CustomizeFilterInvocationSecurityMetadataSource;
import com.fool.microservice.provider.entity.DefaultResult;
import com.fool.microservice.provider.entity.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author fool
 * @date 2021/11/8 13:59
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;

    private final CustomizeAccessDecisionManager accessDecisionManager;

    public ResourceServerConfig(CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource, CustomizeAccessDecisionManager accessDecisionManager) {
        this.securityMetadataSource = securityMetadataSource;
        this.accessDecisionManager = accessDecisionManager;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        //决策管理器
                        o.setAccessDecisionManager(accessDecisionManager);
                        //安全元数据源
                        o.setSecurityMetadataSource(securityMetadataSource);
                        return o;
                    }
                })
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                //权限拒绝处理逻辑
                .accessDeniedHandler(accessDeniedHandler())
                //匿名用户访问无权限资源时的异常处理
                .authenticationEntryPoint(authenticationEntryPoint());
    }

    /**
     * 访问被拒
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            DefaultResult<?> result = new DefaultResult<>(Result.ACCESS_DENIED,"access denied",null);
            httpServletResponse.setContentType("text/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(result));

        };
    }

    /**
     * 未登录
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            DefaultResult<?> result = new DefaultResult<>(Result.AUTH_FAILED,"Full authentication is required to access this resource",null);
            httpServletResponse.setContentType("text/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(result));
        };
    }

}
