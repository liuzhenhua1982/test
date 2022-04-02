package cn.ikyou.infrastructure.response;

import cn.ikyou.infrastructure.common.HttpStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录失败处理器：返回状态码402
 */
@Component
public class SysAuthenticationFailureHandler implements AuthenticationFailureHandler{
	
	@Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException {
    	Map<String,Object> resuult=new HashMap<String,Object>();
        response.setContentType("application/json;charset=utf-8");
        resuult.put("status", HttpStatusCode.CODE_402);
        resuult.put("msg",e.getMessage());
        response.getWriter().write(mapper.writeValueAsString(resuult));
    }
}
