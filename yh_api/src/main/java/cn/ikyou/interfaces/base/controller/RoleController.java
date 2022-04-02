package cn.ikyou.interfaces.base.controller;

import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.entity.SysMenu;
import cn.ikyou.domain.usercenter.entity.SysRole;
import cn.ikyou.domain.usercenter.service.SysRoleDomainService;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.response.PageResult;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.base.vo.role.RoleVO;
import cn.ikyou.interfaces.base.vo.role.req.RoleListQueryReqVO;
import cn.ikyou.interfaces.base.vo.role.res.RoleAddResVO;
import cn.ikyou.interfaces.base.vo.role.res.RoleUpdateResVO;
import cn.ikyou.interfaces.base.vo.role.res.RootApp;
import cn.ikyou.interfaces.base.wrapper.RoleWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "角色管理")
@AllArgsConstructor
@RequestMapping("/api/pc/role")
public class RoleController{


    private final RoleWrapper roleWrapper;
    private final SysRoleDomainService roleService;


    /**
     * 菜单树
     * @return
     */
    @ResponseBody
    @ApiOperation("角色修改添加获取可用菜单列表")
    @RequestMapping(value="/menu-tree",method= RequestMethod.POST)
    public Result<List<RootApp>> menuTree(){
        Result<List<RootApp>> result=Result.build();
        List<SysMenu> list=roleService.menuTree();
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setMsg("获取成功");
        result.setData(roleWrapper.menuVOTree(list));
        return result;
    }


    /**
     * 新增角色
     * @return
     */
    @ResponseBody
    @ApiOperation("新增角色")
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public Result add(@RequestBody RoleAddResVO roleVO){
        Result result=Result.build();
        try {
            int r=roleService.add(roleVO);
            if(r == 1) {
                result.setStatus(HttpStatusCode.SUCCESS_CODE);
                result.setMsg("新增成功");
            }else {
                result.setStatus(HttpStatusCode.FAILURE_CODE);
                result.setMsg("新增失败");
            }
        }catch(ServiceCheckException e) {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg(e.getMessage());
        }catch (Exception e) {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("系统异常，请重试！");
            result.setError(e.getLocalizedMessage());
            log.error(e.getMessage());
        }
        return result;
    }


    /**
     * 更新角色
     * @return
     */
    @ResponseBody
    @ApiOperation("更新角色")
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Result  update(@RequestBody RoleUpdateResVO roleVO){
        Result result=Result.build();
        try {
            int r=roleService.update(roleVO);
            if(r > 0) {
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
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("系统异常，请重试！");
            result.setError(e.getLocalizedMessage());
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * 删除角色
     * @return
     */
    @ResponseBody
    @ApiOperation("通过ID删除角色")
    @RequestMapping(value="/del",method=RequestMethod.GET)
    public Result delete(@RequestParam(value="roleId",required=true) Integer roleId){
        Result result=Result.build();
        try {
            if(roleService.delete(roleId) == 1) {
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


    /**
     * 获取角色列表
     * @return
     */
    @ResponseBody
    @ApiOperation("查询角色列表")
    @RequestMapping(value="/list",method= RequestMethod.POST)
    public PageResult<RoleVO> list(@RequestBody RoleListQueryReqVO roleListResVO){
        PageResult<RoleVO> result=PageResult.build();
        try {
            //查询
            IPage<SysRole> obj=roleService.searchPage(roleListResVO);
            result.setTotal(obj.getTotal());
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setList(roleWrapper.pageVO(obj.getRecords()));
            result.setMsg("查询成功");
        }catch(ServiceCheckException e) {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError( e.getMessage());
        }catch (Exception e) {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setError( "查询失败");
            log.error(e.getMessage());
        }
        return result;
    }


    /**
     * 获取角色详情
     * @return
     */
    @ResponseBody
    @ApiOperation("查询角色详情")
    @RequestMapping(value="/details",method=RequestMethod.GET)
    public Result<RoleVO>  details(@RequestParam(value="roleId",required=true) Integer roleId){
        Result<RoleVO> result=Result.build();
        RoleDTO role=roleService.details(roleId);
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setMsg("获取成功");
        result.setData(roleWrapper.roleToVO(role));
        return result;
    }


}
