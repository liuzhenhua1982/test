package cn.ikyou.interfaces.base.controller;

import cn.ikyou.application.index.IndexService;
import cn.ikyou.domain.usercenter.dto.UserDTO;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.infrastructure.util.JwtTokenUtils;
import cn.ikyou.infrastructure.util.RedisUtil;
import cn.ikyou.interfaces.base.vo.user.PasswordVO;
import cn.ikyou.interfaces.base.vo.user.UserInfoVO;
import cn.ikyou.interfaces.base.wrapper.UserWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(value = "首页", tags = "首页")
@AllArgsConstructor
@RestController
@RequestMapping("/api/pc")
public class IndexController {

	private final UserWrapper userWrapper;
	private final RedisUtil redisUtil;
	private final IndexService indexService;


	/**
	 * 获取用户信息
	 * @return
	 */
	@ResponseBody
	@ApiOperation("获取当前用户信息")
	@RequestMapping(value="/user-info",method=RequestMethod.GET)
	public Result<UserInfoVO> userInfo(){
		Result<UserInfoVO> result=Result.build();
		try {
			UserDTO user= indexService.userInfo();
			result.setData(userWrapper.userInfoToVO(user));
			result.setStatus(HttpStatusCode.SUCCESS_CODE);
		}catch(ServiceCheckException e) {
			result.setMsg(e.getMessage());
			result.setStatus(HttpStatusCode.FAILURE_CODE);
		}catch (Exception e) {
			result.setStatus(HttpStatusCode.CODE_500);
			result.setMsg("系统异常，请重试！");
			result.setError(e.getLocalizedMessage());
			log.error(e.getMessage());
		}
		return result;
	}


	/**
	 * 退出
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@ApiOperation("退出登录")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Result logout(HttpServletRequest request, HttpServletResponse response) {
		Result result = Result.build();
		log.info("系统退出");
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
			result.setStatus(HttpStatusCode.CODE_801);
			result.setMsg("请携带token");
			return result;
    	}
		String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        //删除缓存
        redisUtil.del(token);
        //清理session信息
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(null==auth) {
			result.setStatus(HttpStatusCode.SUCCESS_CODE);
			result.setMsg("退出成功");
			return result;
		}
		new SecurityContextLogoutHandler().logout(request, response, auth);
		result.setStatus(HttpStatusCode.SUCCESS_CODE);
		result.setMsg("退出成功");
		return result;
	}


	/**
	 * 更新密码
	 * @param password
	 * @return
	 */
	@ResponseBody
	@ApiOperation("修改密码")
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public Result password(@RequestBody PasswordVO password) {
		Result result=Result.build();
		try {
			int r=indexService.password(password);
			if(r==1) {
				result.setStatus(HttpStatusCode.SUCCESS_CODE);
				result.setMsg("更新成功");
			}else {
				result.setStatus(HttpStatusCode.FAILURE_CODE);
				result.setMsg("更新失败");
			}
		}catch(ServiceCheckException e) {
			result.setStatus(HttpStatusCode.FAILURE_CODE);
			result.setMsg(e.getMessage());
		}catch (Exception e) {
			result.setStatus(HttpStatusCode.CODE_500);
			result.setMsg("系统异常，请稍后重试！");
			result.setError(e.getMessage());
		}
		return result;
	}


}
