package cn.ikyou.infrastructure.request;

import lombok.Data;

@Data
public class LoginInfo {
	//用户名
	private String username;
	//密码
	private String password;
}
