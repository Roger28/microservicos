package com.rwork.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore jwtTokenStore;

	private static final String[] PUBLIC = { "/hr-oauth/oauth/token" };

	private static final String[] OPERATOR = { "/hr-worker/**" };

	private static final String[] ADMIN = { "/hr-payroll/**", "/hr-user/**" };

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(this.jwtTokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(ResourceServerConfig.PUBLIC).permitAll()
				.antMatchers(HttpMethod.GET, ResourceServerConfig.OPERATOR).hasAnyRole("OPERATOR", "ADMIN").antMatchers(ResourceServerConfig.ADMIN)
				.hasRole("ADMIN").anyRequest().authenticated();
	}

}
