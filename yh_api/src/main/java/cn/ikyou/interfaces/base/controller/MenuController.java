package cn.ikyou.interfaces.base.controller;

import cn.ikyou.domain.usercenter.entity.SysMenu;
import cn.ikyou.domain.usercenter.service.SysMenuDomainService;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.PageResult;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.base.vo.menu.MenuAddReqVO;
import cn.ikyou.interfaces.base.vo.menu.MenuApiAddVO;
import cn.ikyou.interfaces.base.vo.menu.MenuVO;
import cn.ikyou.interfaces.base.vo.menu.req.MenuQueryVO;
import cn.ikyou.interfaces.base.wrapper.MenuWrapper;
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
@Api(tags = "菜单管理")
@RequestMapping("/api/pc/menu")
@AllArgsConstructor
public class MenuController {

    private final SysMenuDomainService menuDomainService;
    private final MenuWrapper menuWrapper;


    @ResponseBody
    @ApiOperation("菜单关联API")
    @RequestMapping(value="/menu-api",method=RequestMethod.POST)
    public Result menuApi(@RequestBody MenuApiAddVO menuApiAddVO){
        Result result=Result.build();
        int r=menuDomainService.menuApi(menuApiAddVO);
        if(r>0) {
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("新增成功");
        }else {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("新增失败");
        }
        return result;
    }


    /**
     * 菜单详情
     * @param menuId
     * @return
     */
    @ResponseBody
    @ApiOperation("菜单详情")
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Result<MenuVO>  detail(@RequestParam(value="menuId",required=true) Integer menuId){
        Result<MenuVO> result=Result.build();
        SysMenu menu=menuDomainService.detail(menuId);
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setData(menuWrapper.entityVO(menu));
        return result;
    }


    /**
     * 新增菜单
     * @param menuVO
     * @return
     */
    @ResponseBody
    @ApiOperation("新增菜单")
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Result add(@RequestBody MenuAddReqVO menuVO){
        Result result=Result.build();
        int r=menuDomainService.add(menuVO);
        if(r>0) {
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("新增成功");
        }else {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("新增成功");
        }
        return result;
    }

    /**
     * 菜单更新
     * @param menuVO
     * @return
     */
    @ResponseBody
    @ApiOperation("菜单更新")
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Result update(@RequestBody MenuVO menuVO){
        Result result=Result.build();
        int r=menuDomainService.update(menuVO);
        if(r>0) {
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("更新成功");
        }else {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("更新失败");
        }
        return result;
    }


    /**
     * 获取菜单列表
     * @param query
     * @return
     */
    @ResponseBody
    @ApiOperation("获取菜单列表")
    @RequestMapping(value="/page",method= RequestMethod.POST)
    public PageResult<MenuVO> list(@RequestBody MenuQueryVO query){
        PageResult<MenuVO> result=PageResult.build();
        try {
            //查询
            IPage<SysMenu> page=menuDomainService.searchPage(query);
            result.setTotal(page.getTotal());
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setList(menuWrapper.listVO(page.getRecords()));
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

    /**
     * 删除菜单
     * @return
     */
    @ResponseBody
    @ApiOperation("删除菜单")
    @RequestMapping(value="/del",method=RequestMethod.GET)
    public Result  delete(@RequestParam(value="menuId",required=true) Integer menuId){
        Result result=Result.build();
        try {
            if(menuDomainService.delete(menuId) == 1) {
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
