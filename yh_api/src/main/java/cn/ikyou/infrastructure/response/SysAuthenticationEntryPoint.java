package cn.ikyou.infrastructure.response;

import cn.ikyou.infrastructure.common.HttpStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 未登录时：返回状态码401
 *
 */
@Component
public class SysAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
    private ObjectMapper mapper;
	
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
    	Map<String,Object> resuult=new HashMap<String,Object>();
        response.setContentType("application/json;charset=utf-8");
        resuult.put("status", HttpStatusCode.SUCCESS_CODE);
        resuult.put("msg",e.getMessage());
        response.getWriter().write(mapper.writeValueAsString(resuult));
    }
}