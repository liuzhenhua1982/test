package cn.ikyou.infrastructure.response;

import cn.ikyou.application.security.UserDetails;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.util.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 登录成功处理器：返回状态码200
 * @author 86181
 *
 */
@Component
public class SysAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RedisUtil redisUtil;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Map<String,Object> result=new HashMap<String,Object>();
        response.setContentType("application/json;charset=utf-8");
        result.put("status", HttpStatusCode.SUCCESS_CODE);
        result.put("msg","登录成功");
        //获取用户信息
        UserDetails user = (UserDetails)authentication.getPrincipal();
        //生成UUID
        String token=UUID.randomUUID().toString();
        //设置token
        result.put("token", token);
        //设置缓存
        redisUtil.set(token, user.getUser(), 60*30);
        //响应消息
        response.getWriter().write(objectMapper.writeValueAsString(result));

    }
}
