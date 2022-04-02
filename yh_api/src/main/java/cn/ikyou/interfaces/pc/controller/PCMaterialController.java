package cn.ikyou.interfaces.pc.controller;

import cn.ikyou.application.business.PCMeterialService;
import cn.ikyou.domain.business.entity.TCars;
import cn.ikyou.domain.business.entity.TMaterial;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.PageResult;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.pc.vo.car.*;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialAddReqVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialReqVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialResVO;
import cn.ikyou.interfaces.pc.vo.meterial.PCMeterialUpdateReqVO;
import cn.ikyou.interfaces.pc.wrapper.PCMaterialWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/20
 * 说明：
 */
@Slf4j
@RestController
@Api(tags = "物料基础信息维护")
@AllArgsConstructor
@RequestMapping("/api/pc/material")
public class PCMaterialController {

    private final PCMeterialService pcMeterialService;
    private final PCMaterialWrapper pcMaterialWrapper;
    @ResponseBody
    @ApiOperation("物料列表")
    @RequestMapping(value = "/searchPage", method = RequestMethod.POST)
    public PageResult<PCMeterialResVO> searchPage(@RequestBody PCMeterialReqVO reqVO) {
        PageResult<PCMeterialResVO> result = PageResult.build();
        try{
            IPage<PCMeterialResVO> page= pcMeterialService.searchPage(reqVO);
            result.setList(page.getRecords());
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
    @ApiOperation("添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody PCMeterialAddReqVO addReqVO) {
        Result result = Result.build();
        try{
            boolean r = pcMeterialService.add(addReqVO);
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
    @ResponseBody
    @ApiOperation("更新")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody PCMeterialUpdateReqVO updateReqVO) {
        Result result = Result.build();
        try{
            boolean r = pcMeterialService.update(updateReqVO);
            if(!r){
                result.setStatus(HttpStatusCode.FAILURE_CODE);
                result.setError("更新失败");
            }else{
                result.setStatus(HttpStatusCode.SUCCESS_CODE);
                result.setMsg("更新成功");
            }

        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("更新失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }
    @ResponseBody
    @ApiOperation("删除")
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public Result del(@RequestParam(value="id",required=true) Integer id) {
        Result result = Result.build();
        try{
            boolean r = pcMeterialService.del(id);
            if(!r){
                result.setStatus(HttpStatusCode.FAILURE_CODE);
                result.setError("删除失败");
            }else{
                result.setStatus(HttpStatusCode.SUCCESS_CODE);
                result.setMsg("删除成功");
            }

        }catch (ServiceCheckException e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError(e.getMessage());
            log.error(ExceptionUtils.getMessage(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("删除失败");
            log.error(ExceptionUtils.getMessage(e));
        }
        return result;
    }
    @ResponseBody
    @ApiOperation("详细")
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public Result<PCMeterialResVO> details(@RequestParam(value="id",required=true) Integer id) {
        Result<PCMeterialResVO> result = Result.build();
        try{
            TMaterial details = pcMeterialService.details(id);
            PCMeterialResVO resVO = new PCMeterialResVO();
            BeanUtils.copyProperties(details,resVO);
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
}
