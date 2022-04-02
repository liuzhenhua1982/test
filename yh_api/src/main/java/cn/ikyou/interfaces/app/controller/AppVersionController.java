package cn.ikyou.interfaces.app.controller;

import cn.ikyou.domain.common.service.TAppVersionDomainService;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.app.vo.version.AppVersionReqVO;
import cn.ikyou.interfaces.app.vo.version.AppVersionResVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/20
 * 说明：
 */
@Slf4j
@RestController
@RequestMapping("/api/app/version")
@AllArgsConstructor
@Api(tags = "APP版本控制")
public class AppVersionController {

    private final TAppVersionDomainService appVersionDomainService;

    @ResponseBody
    @ApiOperation("获取是否有更新")
    @RequestMapping(value="/get",method= RequestMethod.POST)
    public Result<AppVersionResVO> get(@RequestBody AppVersionReqVO apiAddVO){
        Result<AppVersionResVO>  result=Result.build();
        AppVersionResVO appVersionResVO=appVersionDomainService.get(apiAddVO);
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setData(appVersionResVO);
        result.setMsg("获取成功");
        return result;
    }


}
