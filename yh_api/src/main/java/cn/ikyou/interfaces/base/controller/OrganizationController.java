package cn.ikyou.interfaces.base.controller;

import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.service.OrganizationDomainService;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.response.Result;
import cn.ikyou.interfaces.base.vo.organization.*;
import cn.ikyou.interfaces.base.wrapper.OrganizationWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@Api(tags = "机构管理")
@AllArgsConstructor
@RequestMapping("/api/pc/organization")
public class OrganizationController {

	private final OrganizationDomainService organizationService;
	private final OrganizationWrapper organizationWrapper;


	/**
	 * 新增机构
	 * @return
	 */
	@ResponseBody
	@ApiOperation("新增机构")
	@RequestMapping(value="/add",method= RequestMethod.POST)
	public Result add(@RequestBody OrganizationAddReqVO organizationAddReqVO){
		Result result=Result.build();
		int r=organizationService.add(organizationAddReqVO);
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
	 * 更新机构
	 * @return
	 */
	@ResponseBody
	@ApiOperation("更新机构")
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Result  update(@RequestBody OrganizationUpdateReqVO organizationUpdateReqVO){
		Result result=Result.build();
		int r=organizationService.update(organizationUpdateReqVO);
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
	 * 删除机构
	 * @return
	 */
	@ResponseBody
	@ApiOperation("删除机构")
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public Result  delete(@RequestParam(value="id",required=true) Integer id){
		Result result=Result.build();
		if(organizationService.delete(id) == 1) {
			result.setStatus(HttpStatusCode.SUCCESS_CODE);
			result.setMsg( "删除成功");
		}else {
			result.setStatus(HttpStatusCode.FAILURE_CODE);
			result.setMsg( "删除失败");
		}
		return result;
	}


	/**
	 * 获取机构树
	 * @param query
	 * @return
	 */
	@ResponseBody
	@ApiOperation("查询机构树")
	@RequestMapping(value="/tree",method= RequestMethod.POST)
	public Result<List<OrganizationTreeResVO>> tree(@RequestBody OrganizationQueryVO query){
		Result<List<OrganizationTreeResVO>> result=Result.build();
		//查询
		List<Organization> list=organizationService.list(query);
		result.setStatus(HttpStatusCode.SUCCESS_CODE);
		result.setData(organizationWrapper.entityVOTree(list));
		return result;
	}


	/**
	 * 获取机构详情
	 * @param id
	 * @return
	 */
	@ResponseBody
	@ApiOperation("获取机构详情")
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public Result<OrganizationResVO>  detail(@RequestParam(value="id",required=true) Integer id){
		Result<OrganizationResVO> result=Result.build();
		Organization organization=organizationService.detail(id);
		result.setStatus(HttpStatusCode.SUCCESS_CODE);
		result.setMsg("获取成功");
		result.setData(organizationWrapper.entityVO(organization));
		return result;
	}

}
