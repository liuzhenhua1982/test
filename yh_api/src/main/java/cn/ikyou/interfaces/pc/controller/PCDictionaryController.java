package cn.ikyou.interfaces.pc.controller;

import cn.ikyou.application.dict.PCDictService;
import cn.ikyou.domain.business.entity.TWkOrders;
import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.PageResult;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.app.vo.yhorder.YhOrderQueryReqVO;
import cn.ikyou.interfaces.app.vo.yhorder.YhOrderResVO;
import cn.ikyou.interfaces.pc.vo.dict.*;
import cn.ikyou.interfaces.pc.wrapper.PCDictWrapper;
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
 * 开发时间：2022/1/24
 * 说明：
 */
@Slf4j
@RestController
@Api(tags = "字典维护")
@AllArgsConstructor
@RequestMapping("/api/pc/dictionary")
public class PCDictionaryController {

    private final PCDictService pcDictService;

    @ResponseBody
    @ApiOperation("字典列表")
    @RequestMapping(value = "/searchPage", method = RequestMethod.POST)
    public PageResult<PCDictResVO> searchPage(@RequestBody PCDictReqVO reqVO) {
        PageResult<PCDictResVO> result = PageResult.build();
        try{
            IPage<PCDictResVO> page= pcDictService.searchPage(reqVO);
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
    public Result add(@RequestBody PCDictAddReqVO addReqVO) {
        Result result = Result.build();
        try{
            boolean r = pcDictService.add(addReqVO);
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
            log.error(ExceptionUtils.getStackTrace(e));
        }catch (Exception e){
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError("添加失败");
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }
    @ResponseBody
    @ApiOperation("更新")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody PCDictUpdateReqVO updateReqVO) {
        Result result = Result.build();
        try{
            boolean r = pcDictService.update(updateReqVO);
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
            boolean r = pcDictService.del(id);
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
    public Result<PCDictDetailsResVO> details(@RequestParam(value="id",required=true) Integer id) {
        Result<PCDictDetailsResVO> result = Result.build();
        try{
            Dictionary details = pcDictService.details(id);
            PCDictDetailsResVO resVO = new PCDictDetailsResVO();
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
