package cn.ikyou.interfaces.base.controller;

import cn.ikyou.domain.usercenter.entity.SysApi;
import cn.ikyou.domain.usercenter.service.SysApiDomainService;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.PageResult;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.base.vo.api.ApiAddVO;
import cn.ikyou.interfaces.base.vo.api.ApiDetailVO;
import cn.ikyou.interfaces.base.vo.api.ApiQueryVO;
import cn.ikyou.interfaces.base.vo.api.ApiUpdateVO;
import cn.ikyou.interfaces.base.wrapper.ApiWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 菜单管理
 */
@Slf4j
@RestController
@Api(tags = "API管理")
@RequestMapping("/api/pc/api")
@AllArgsConstructor
public class ApiController {

    private final SysApiDomainService apiDomainService;
    private final ApiWrapper apiWrapper;

    @ResponseBody
    @ApiOperation("api详情")
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Result<ApiDetailVO>  detail(@RequestParam(value="id",required=true) Integer id){
        Result<ApiDetailVO> result=Result.build();
        SysApi api=apiDomainService.getById(id);
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setData(apiWrapper.entityVO(api));
        return result;
    }



    @ResponseBody
    @ApiOperation("新增API")
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Result add(@RequestBody ApiAddVO apiAddVO){
        Result result=Result.build();
        int r=apiDomainService.add(apiAddVO);
        if(r>0) {
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("新增成功");
        }else {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("新增成功");
        }
        return result;
    }


    @ResponseBody
    @ApiOperation("更新API")
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Result update(@RequestBody ApiUpdateVO apiUpdateVO){
        Result result=Result.build();
        int r=apiDomainService.updateApi(apiUpdateVO);
        if(r>0) {
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("更新成功");
        }else {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("更新失败");
        }
        return result;
    }



    @ResponseBody
    @ApiOperation("获取菜单列表")
    @RequestMapping(value="/page",method= RequestMethod.POST)
    public PageResult<ApiDetailVO> page(@RequestBody ApiQueryVO query){
        PageResult<ApiDetailVO> result=PageResult.build();
        try {
            //查询
            IPage<SysApi> page=apiDomainService.searchPage(query);
            result.setTotal(page.getTotal());
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setList(apiWrapper.listVO(page.getRecords()));
        }catch(ServiceCheckException e) {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg(e.getMessage());
        }catch (Exception e) {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("查询失败");
            result.setError(e.getMessage());
            log.error(e.getMessage());
        }
        return result;
    }


    @ResponseBody
    @ApiOperation("删除API")
    @RequestMapping(value="/del",method=RequestMethod.GET)
    public Result  delete(@RequestParam(value="id",required=true) Integer id){
        Result result=Result.build();
        try {
            if(apiDomainService.delete(id) == 1) {
                result.setStatus(HttpStatusCode.SUCCESS_CODE);
                result.setMsg("删除成功");
            }else {
                result.setStatus(HttpStatusCode.FAILURE_CODE);
                result.setMsg("删除失败");
            }
        }catch(ServiceCheckException e) {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("删除失败");
        }catch (Exception e) {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("系统异常，请重试！");
            result.setError(e.getLocalizedMessage());
            log.error(e.getMessage());
        }
        return result;
    }


}
