package cn.ikyou.interfaces.pc.controller;

import cn.ikyou.application.common.AppVersionService;
import cn.ikyou.domain.business.entity.TCars;
import cn.ikyou.domain.common.entity.TAppVersion;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.PageResult;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.pc.vo.app.PCAppAddReqVO;
import cn.ikyou.interfaces.pc.vo.app.PCAppReqVO;
import cn.ikyou.interfaces.pc.vo.app.PCAppResVO;
import cn.ikyou.interfaces.pc.vo.car.PCCarsReqVO;
import cn.ikyou.interfaces.pc.vo.car.PCCarsResVO;
import cn.ikyou.interfaces.pc.wrapper.PCAppWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/20
 * 说明：
 */
@Slf4j
@RestController
@Api(tags = "APP版本维护")
@AllArgsConstructor
@RequestMapping("/api/pc/app-version")
public class PCAppVersionController {

    private final AppVersionService appVersionService;
    private final PCAppWrapper pcAppWrapper;
    @ResponseBody
    @ApiOperation("APP列表")
    @RequestMapping(value = "/searchPage", method = RequestMethod.POST)
    public PageResult<PCAppResVO> searchPage(@RequestBody PCAppReqVO reqVO) {
        PageResult<PCAppResVO> result = PageResult.build();
        try{
            IPage<TAppVersion> page= appVersionService.searchPage(reqVO);
            result.setList(pcAppWrapper.appToVO(page.getRecords()));
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setTotal(page.getTotal());
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
    @ResponseBody
    @ApiOperation("保存发布")
    @PostMapping("/save")
    public Result<String> save(@RequestBody PCAppAddReqVO reqVO){
        Result<String> result=new Result<>();
        try{
            boolean r = appVersionService.save(reqVO);
            if(!r){
                result.setStatus(HttpStatusCode.FAILURE_CODE);
                result.setError("添加失败");
            }else{
                result.setStatus(HttpStatusCode.SUCCESS_CODE);
                result.setMsg("添加成功");
            }

        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("添加失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }
}
