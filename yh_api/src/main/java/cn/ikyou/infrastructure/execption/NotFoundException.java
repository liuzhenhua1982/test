package cn.ikyou.infrastructure.execption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ApiIgnore
@Controller
public class NotFoundException implements ErrorController {
 
	private Logger log= LoggerFactory.getLogger(NotFoundException.class);
	
    @Override
    public String getErrorPath() {
        return "/error";
    }
 
    @RequestMapping(value = {"/error"})
    @ResponseBody
    public Object error(HttpServletRequest request) {
    	String requestType = request.getHeader("X-Requested-With");
    	if("XMLHttpRequest".equals(requestType)){
    		log.info("ajax请求未发现");
    		Map<String, Object> body = new HashMap<>();
            body.put("msg", "not found");
            body.put("status", "1");
            body.put("code", "404");
            return body;
    	}else{
    		log.info("普通请求未发现,地址为:"+request.getServletPath());
    		return "未发现页面";
    	}
    	
    }
}