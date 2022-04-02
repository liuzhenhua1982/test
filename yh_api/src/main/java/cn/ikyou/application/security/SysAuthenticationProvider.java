package cn.ikyou.application.security;

import cn.ikyou.infrastructure.request.LoginInfo;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;


/**
 * 用户认证
 *
 */
@Component
public class SysAuthenticationProvider implements AuthenticationProvider {

	private Logger log=LoggerFactory.getLogger(SysAuthenticationProvider.class);
	@Autowired
	private SysUserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("获取用户信息");
		//获取登录信息
		LoginInfo loginInfo=(LoginInfo)authentication.getPrincipal();
		//获取用户
		UserDetails userInfo=null;
		try {
			userInfo = userDetailsService.loadUserByUsername(loginInfo);
		} catch (Exception e) {
			log.error(ExceptionUtils.getMessage(e));
			throw new BadCredentialsException("获取用户信息失败");
		}
		if (null==userInfo) {
		    throw new BadCredentialsException("用户不存在");
		}
		boolean flag = passwordEncoder.matches(loginInfo.getPassword(),userInfo.getPassword());
		if (!flag) {
		    throw new BadCredentialsException("密码不正确");
		}
		//添加权限信息
		Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
		//返回结果
		return new LoginAuthenticationToken(userInfo, authorities);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
