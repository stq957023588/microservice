package com.fool.microservice.provider.config;

import com.fool.microservice.provider.customize.CustomizeAccessDecisionManager;
import com.fool.microservice.provider.customize.CustomizeFilterInvocationSecurityMetadataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
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
                .anyRequest().authenticated();

        // http.csrf().disable()
        //         .authorizeRequests()
        //         .antMatchers("/auth-test").hasAnyAuthority("!ADMIN")
        //         .antMatchers("/auth-test2").hasAnyAuthority("ADMIN")
        //         .antMatchers("/**").permitAll()
        //         .anyRequest().authenticated();
    }
}
