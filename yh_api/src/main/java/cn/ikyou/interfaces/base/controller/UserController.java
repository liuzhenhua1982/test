package cn.ikyou.interfaces.base.controller;

import cn.ikyou.application.usercenter.UserService;
import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.dto.UserDTO;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.response.PageResult;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.base.vo.organization.OrganizationTreeResVO;
import cn.ikyou.interfaces.base.vo.user.UserInfoVO;
import cn.ikyou.interfaces.base.vo.user.req.UserAddReqVO;
import cn.ikyou.interfaces.base.vo.user.req.UserQueryVO;
import cn.ikyou.interfaces.base.vo.user.req.UserUpdateReqVO;
import cn.ikyou.interfaces.base.vo.user.res.RoleResVO;
import cn.ikyou.interfaces.base.wrapper.UserWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@Api(tags = "用户管理")
@AllArgsConstructor
@RequestMapping("/api/pc/user")
public class UserController {

    private final UserWrapper pcUserWrapper;
    private final UserService userService;

    /**
     * 获取角色列表
     * @return
     */
    @ResponseBody
    @ApiOperation("可用角色列表")
    @GetMapping(value="/role-list")
    public Result<List<RoleResVO>> roleList(){
        Result<List<RoleResVO>> result=Result.build();
        List<RoleDTO> list=userService.roleList();
        result.setData(pcUserWrapper.roleListVO(list));
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setMsg("获取成功");
        return result;
    }

    /**
     * 机构树
     * @return
     */
    @ResponseBody
    @ApiOperation("机构树")
    @GetMapping(value="/organization-tree")
    public Result<List<OrganizationTreeResVO>> organiaztionTree(){
        Result<List<OrganizationTreeResVO>> result=Result.build();
        List<Organization> list=userService.organiaztionTree();
        result.setData(pcUserWrapper.organizationVOTree(list));
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setMsg("获取成功");
        return result;
    }

    /**
     * 新增用户
     * @return
     */
    @ResponseBody
    @ApiOperation("新增用户")
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Result add(@RequestBody UserAddReqVO userInfo){
        Result result=Result.build();
        int r=userService.add(userInfo);
        if(r == 1) {
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("新增成功");
        }else {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("新增失败");
        }
        return result;
    }


    /**
     * 更新用户信息
     * @return
     */
    @ResponseBody
    @ApiOperation("更新用户")
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Result  update(@RequestBody UserUpdateReqVO userInfo){
        Result result=Result.build();
        int r=userService.update(userInfo);
        if(r > 0) {
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg("更新成功");
        }else {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg("更新失败");
        }
        return result;
    }

    /**
     * 删除用户
     * @return
     */
    @ResponseBody
    @ApiOperation("删除用户")
    @RequestMapping(value="/del",method=RequestMethod.GET)
    public Result  delete(@RequestParam(value="userid",required=true) int userid){
        Result result=Result.build();
        if(userService.delete(userid) == 1) {
            result.setStatus(HttpStatusCode.SUCCESS_CODE);
            result.setMsg( "删除成功");
        }else {
            result.setStatus(HttpStatusCode.FAILURE_CODE);
            result.setMsg( "删除失败");
        }
        return result;
    }


    /**
     * 获取用户列表
     * @param query
     * @return
     */
    @ResponseBody
    @ApiOperation("查询用户列表")
    @RequestMapping(value="/page",method= RequestMethod.POST)
    public PageResult<UserInfoVO> list(@RequestBody UserQueryVO query){
        PageResult<UserInfoVO> result=PageResult.build();
        //查询
        IPage<SysUser> page=userService.searchPage(query);
        result.setTotal(page.getTotal());
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setList(pcUserWrapper.pageVO(page.getRecords()));
        return result;
    }


    /**
     * 获取用户详情
     * @param userid
     * @return
     */
    @ResponseBody
    @ApiOperation("查询用户详情")
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Result<UserInfoVO>  details(@RequestParam(value="userid",required=true) Integer userid){
        Result<UserInfoVO> result=Result.build();
        UserDTO user=userService.detail(userid);
        result.setStatus(HttpStatusCode.SUCCESS_CODE);
        result.setMsg("获取成功");
        result.setData(pcUserWrapper.userInfoToVO(user));
        return result;
    }


}
