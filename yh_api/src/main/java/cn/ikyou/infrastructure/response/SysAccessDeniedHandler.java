package cn.ikyou.infrastructure.response;

import cn.ikyou.infrastructure.common.HttpStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限不足处理器：返回状态码403
 *
 */
@Component
public class SysAccessDeniedHandler implements AccessDeniedHandler {

	@Autowired
    private ObjectMapper mapper;
	
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        Map<String,Object> resuult=new HashMap<String,Object>();
        response.setContentType("application/json;charset=utf-8");
        resuult.put("status", HttpStatusCode.CODE_403);
        resuult.put("msg",e.getMessage());
        response.getWriter().write(mapper.writeValueAsString(resuult));
    }

}