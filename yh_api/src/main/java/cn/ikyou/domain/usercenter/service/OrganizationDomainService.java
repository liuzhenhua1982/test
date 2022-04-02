package cn.ikyou.domain.usercenter.service;

import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.entity.SysApi;
import cn.ikyou.interfaces.base.vo.organization.OrganizationAddReqVO;
import cn.ikyou.interfaces.base.vo.organization.OrganizationQueryVO;
import cn.ikyou.interfaces.base.vo.organization.OrganizationUpdateReqVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrganizationDomainService  {

    int add(OrganizationAddReqVO organizationAddReqVO);

    int update(OrganizationUpdateReqVO organizationUpdateReqVO);

    int delete(Integer id);

    Organization detail(Integer id);

    List<Organization> list(OrganizationQueryVO query);

    Organization getById(Integer orgId);
    List<Organization> orgList();
}
