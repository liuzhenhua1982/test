package cn.ikyou.infrastructure.config;


import cn.ikyou.application.security.RoleAccessDecisionManager;
import cn.ikyou.application.security.SysAuthenticationProvider;
import cn.ikyou.infrastructure.filter.AuthorizationFilter;
import cn.ikyou.infrastructure.filter.LoginAuthenticationProcessingFilter;
import cn.ikyou.infrastructure.response.SysAccessDeniedHandler;
import cn.ikyou.infrastructure.response.SysAuthenticationEntryPoint;
import cn.ikyou.infrastructure.response.SysAuthenticationFailureHandler;
import cn.ikyou.infrastructure.response.SysAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RoleAccessDecisionManager roleAccessDecisionManager;
	@Autowired
	private SysAccessDeniedHandler accessDeniedHanler;
	@Autowired
	private SysAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private SysAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private SysAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private SysAuthenticationProvider authenticationProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(14);
    }
 
	@Bean
	public LoginAuthenticationProcessingFilter loginAbstractAuthenticationProcessingFilter() throws Exception {
		LoginAuthenticationProcessingFilter filter = new LoginAuthenticationProcessingFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		//登录认证成功
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		//登录认证失败
		filter.setAuthenticationFailureHandler(authenticationFailureHandler);
		//
		return filter;
	}

	
    @Override
    public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/im-server/**");
		web.ignoring(). antMatchers("/doc.html")
				.antMatchers("/webjars/**")
				.antMatchers("/v2/**")
				.antMatchers("/api/file/get/**")
				.antMatchers("/swagger-resources/**");
    }
	
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//用户认证
    	auth.authenticationProvider(authenticationProvider);
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		//关闭csrf
		http.csrf().disable();
		//禁止缓存
		http.headers().cacheControl();
		//添加认证过滤器
		http.addFilterAt(loginAbstractAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
		//解析token
		http.addFilter(new AuthorizationFilter(authenticationManager()));
		//未登录返回JSON
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		//权限不足
        http.exceptionHandling().accessDeniedHandler(accessDeniedHanler);
		//不使用session
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O o) {
				o.setAccessDecisionManager(roleAccessDecisionManager);
				return o;
			}
		}).anyRequest().authenticated().and().formLogin().permitAll();
    }
}
