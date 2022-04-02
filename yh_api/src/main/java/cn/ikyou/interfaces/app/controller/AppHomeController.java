package cn.ikyou.interfaces.app.controller;

import cn.ikyou.application.dto.UserInfoDTO;
import cn.ikyou.application.index.IndexService;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.infrastructure.util.JwtTokenUtils;
import cn.ikyou.infrastructure.util.RedisUtil;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.app.vo.AppUserInfoResVO;
import cn.ikyou.interfaces.app.vo.WeatherResVO;
import cn.ikyou.interfaces.app.wrapper.AppHomeWrapper;
import cn.ikyou.interfaces.base.vo.user.PasswordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@Api(tags = "首页")
@AllArgsConstructor
@RequestMapping("/api/app")
public class AppHomeController {

    private final AppHomeWrapper appHomeWrapper;
    private final RedisUtil redisUtil;
    private final IndexService indexService;


    @ResponseBody
    @ApiOperation("天气接口")
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public Result<WeatherResVO> weather(@RequestParam(value="city",required=true) String city) {
        Result<WeatherResVO> result= Result.build();
        try{
            WeatherResVO resVO = indexService.weather(city);
            result.setData(resVO);
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("获取成功");
        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("获取失败");
            log.error(ExceptionUtils.getMessage(e));
        }


        return result;
    }




    /**
     * 获取用户信息
     * @return
     */
    @ResponseBody
    @ApiOperation("获取当前用户信息")
    @RequestMapping(value="/user-info",method= RequestMethod.GET)
    public Result<AppUserInfoResVO> userInfo(){
        Result<AppUserInfoResVO> result= Result.build();
        UserInfoDTO user= UserInfoUtil.getCurrentUser();
        String city=indexService.cityByOrgId(user.getOrgId());
        AppUserInfoResVO appUserInfoResVO=appHomeWrapper.userInfoToVO(user);
        appUserInfoResVO.setCity(city);
        result.setData(appUserInfoResVO);
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        return result;
    }


    /**
     * 退出
     * @param request
     * @param response
     * @param modelMap
     * @return
     */
    @ResponseBody
    @ApiOperation("退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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
        Result result= Result.build();
        int r=indexService.password(password);
        if(r==1) {
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("更新成功");
        }else {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("更新失败");
        }
        return result;
    }



}
