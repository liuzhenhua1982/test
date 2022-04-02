package cn.ikyou.infrastructure.filter;

import cn.ikyou.application.security.LoginAuthenticationToken;
import cn.ikyou.infrastructure.request.LoginInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


public class LoginAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

	private Logger log = LoggerFactory.getLogger(LoginAuthenticationProcessingFilter.class);
	private ObjectMapper objectMapper=new ObjectMapper();

	private boolean postOnly = true;

	public LoginAuthenticationProcessingFilter() {
		super(new AntPathRequestMatcher("/api/v1/login", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		log.info("开始认证");

		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("认证仅支持POST请求");
		}

		LoginAuthenticationToken authRequest = null;

		log.info("请求方式为："+request.getContentType());

		try (InputStream is = request.getInputStream()) {
			LoginInfo loginInfo = objectMapper.readValue(is, LoginInfo.class);
			authRequest = new LoginAuthenticationToken(loginInfo);
		} catch (IOException e) {
			authRequest = new LoginAuthenticationToken(null);
			log.error(ExceptionUtils.getMessage(e));
		}

		setDetails(request, authRequest);
		return super.getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	protected void setDetails(HttpServletRequest request, LoginAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}
}