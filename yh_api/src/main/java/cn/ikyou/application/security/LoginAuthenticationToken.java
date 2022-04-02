package cn.ikyou.application.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginAuthenticationToken extends AbstractAuthenticationToken{
	
	private static final long serialVersionUID = 8486725296911711500L;
	
	private final Object principal;
	
	public LoginAuthenticationToken(Object principal) {
		super(null);
		this.principal=principal;
		setAuthenticated(false);
	}
	
	public LoginAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal=principal;
		super.setAuthenticated(true);
	}
 
	
	@Override
	public void setAuthenticated(boolean authenticated) {
		if(authenticated) {
			throw new IllegalArgumentException("不能设置token");
		}
		super.setAuthenticated(authenticated);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}
 

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
	}

}
