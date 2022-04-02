package cn.ikyou.interfaces.app.controller;

import cn.ikyou.application.common.AppLocationService;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.app.vo.location.LocationReqVO;
import cn.ikyou.interfaces.app.vo.location.LocationResVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/20
 * 说明：
 */
@Slf4j
@RestController
@RequestMapping("/api/app/location")
@AllArgsConstructor
@Api(tags = "定位数据")
public class AppLocationController {
    private final AppLocationService appLocationService;

    @ResponseBody
    @ApiOperation("获取桩号")
    @RequestMapping(value="/zh",method= RequestMethod.POST)
    public Result<LocationReqVO> get(@RequestBody LocationResVO locationResVO){
        Result<LocationReqVO>  result=Result.build();
        try{
            LocationReqVO res = appLocationService.getLocation(locationResVO);
            result.setData(res);
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


}
