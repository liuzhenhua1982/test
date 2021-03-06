package cn.ikyou.infrastructure.filter;


import cn.ikyou.ApplicationContextProvider;
import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.application.security.LoginAuthenticationToken;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.TokenCheckServiceException;
import cn.ikyou.infrastructure.execption.TokenInvalidException;
import cn.ikyou.infrastructure.execption.UnTokenException;
import cn.ikyou.infrastructure.util.JwtTokenUtils;
import cn.ikyou.infrastructure.util.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	private Logger log=LoggerFactory.getLogger(AuthorizationFilter.class);
	private RedisUtil redisUtil=(RedisUtil)ApplicationContextProvider.getBean(RedisUtil.class);
	private ObjectMapper objectMapper=(ObjectMapper) ApplicationContextProvider.getBean(ObjectMapper.class);
    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException {
    	Map<String,Object> result=new HashMap<String,Object>();
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        try {
        	if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
        		throw new UnTokenException();
        	}
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
            super.doFilterInternal(request, response, chain);
            return;
		}catch(UnTokenException ue) {
			result.put("status", HttpStatusCode.SUCCESS_CODE);
			result.put("msg","?????????token");
		}catch(TokenCheckServiceException ce) {
			result.put("status",HttpStatusCode.CODE_802);
			result.put("msg","token????????????");
		}catch(TokenInvalidException ie) {
			result.put("status",HttpStatusCode.CODE_802);
			result.put("msg","token??????");
		}catch(ExpiredJwtException ee) {
			result.put("status",HttpStatusCode.CODE_802);
			result.put("msg","token??????");
		}catch (Exception e) {
			result.put("status",HttpStatusCode.CODE_500);
			result.put("msg","???????????????????????????");
			result.put("error",e.getLocalizedMessage());
			log.error(ExceptionUtils.getMessage(e));
		}
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
        return;
    }

    // ?????????token????????????????????????????????????token
    private LoginAuthenticationToken getAuthentication(String tokenHeader) throws Exception{
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        //?????????????????????
		UserInfoDTO user=(UserInfoDTO)redisUtil.get(token);
        if(null==user) {
        	log.info("???????????????token");
        	throw new TokenInvalidException();
        }
        //??????????????????
        redisUtil.expire(token, 60*30);
        //??????????????????
		Set<SimpleGrantedAuthority> authoritiesSet = new HashSet<SimpleGrantedAuthority>();
		//
        return new LoginAuthenticationToken(user, authoritiesSet);
    }
    
    
}