package cn.ikyou.interfaces.base.controller;

import cn.ikyou.domain.dictionary.entity.Dictionary;
import cn.ikyou.domain.dictionary.service.DictionaryDomainService;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.app.vo.yhorder.CarResVO;
import cn.ikyou.interfaces.base.vo.dictionary.DictionaryListResVO;
import cn.ikyou.interfaces.base.vo.dictionary.DictionaryMapResVO;
import cn.ikyou.interfaces.base.wrapper.DictionaryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "字典管理")
@RequestMapping("/api/pc/dictionary")
public class DictionaryController {

    private final DictionaryDomainService dictionaryDomainService;
    private final DictionaryWrapper dictionaryWrapper;


    @ResponseBody
    @ApiOperation("根据机构与类型获取字典")
    @GetMapping("/get-by-org-list")
    public Result<List<DictionaryListResVO>> byOrgList(@RequestParam(value="type",required=true) String type){
        Result<List<DictionaryListResVO>> result=Result.build();
        try{
            List<DictionaryListResVO> dictionaryListResVOS = dictionaryDomainService.findDictionaryByTypeList(type, UserInfoUtil.getCurrentUser().getOrgId());
            result.setData(dictionaryListResVOS);
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
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setMsg("获取成功");

        return result;
    }


    /**
     * 通过类型获取字典列表
     * @param type
     * @return
     */
    @ResponseBody
    @ApiOperation("根据类型获取字典")
    @GetMapping("/get")
    public Result<List<DictionaryListResVO>> list(@RequestParam(value="type",required=true) String type){
        Result<List<DictionaryListResVO>> result=Result.build();
        List<Dictionary> list=dictionaryDomainService.findDictionaryByType(type);
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setMsg("获取成功");
        result.setData(dictionaryWrapper.dictionarylistToVO(list));
        return result;
    }

    @ResponseBody
    @ApiOperation("根据上级ID获取字典")
    @GetMapping("/get-by-pid")
    public Result<List<DictionaryListResVO>> getByPid(@RequestParam(value="pid",required=true) Integer pid){
        Result<List<DictionaryListResVO>> result=Result.build();
        List<Dictionary> list=dictionaryDomainService.findDictionaryBypid(pid);
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setMsg("获取成功");
        result.setData(dictionaryWrapper.dictionarylistToVO(list));
        return result;
    }

    @ResponseBody
    @ApiOperation("根据类型获取所含有的字典集合")
    @GetMapping("/get-by-type-list")
    public Result<List<DictionaryMapResVO>> getByTypeList(@RequestParam(value="type",required=true) String type){
        Result<List<DictionaryMapResVO>> result=Result.build();
        List<Dictionary> list=dictionaryDomainService.findDictionaryByTypeList(type);
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setMsg("获取成功");
        result.setData(dictionaryWrapper.dictionaryMpToVO(list));
        return result;
    }

}
