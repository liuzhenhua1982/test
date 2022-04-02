package cn.ikyou.application.security;

import cn.ikyou.application.dto.UserInfoDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class UserDetails extends User{
	
	private static final long serialVersionUID = 8845158093745171915L;
	
	private UserInfoDTO user=null;

	public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	
	public UserDetails(UserInfoDTO user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUsername(), user.getPassword(), authorities);
		this.user=user;
	}


	public UserInfoDTO getUser() {
		return user;
	}
	
}
