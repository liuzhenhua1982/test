package cn.ikyou.domain.usercenter.service.impl;

import cn.ikyou.domain.usercenter.dao.OrganizationMapper;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.service.OrganizationDomainService;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.base.vo.organization.OrganizationAddReqVO;
import cn.ikyou.interfaces.base.vo.organization.OrganizationQueryVO;
import cn.ikyou.interfaces.base.vo.organization.OrganizationUpdateReqVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrganizationDomainServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationDomainService {

    private OrganizationMapper organizationMapper;

    @Override
    public Organization getById(Integer orgId) {
        return baseMapper.selectById(orgId);
    }

    @Override
    public List<Organization> orgList() {
        return organizationMapper.orgList();
    }


    @Override
    public int add(OrganizationAddReqVO organizationAddReqVO) {
        if(null==organizationAddReqVO){
            throw new ServiceCheckException("无效数据");
        }
        Organization organization=new Organization();
        BeanUtils.copyProperties(organizationAddReqVO,organization);
        organization.setCreateBy(UserInfoUtil.getCurrentUser().getUserId());
        organization.setCreateTime(new Date());
        return baseMapper.insert(organization);
    }

    @Override
    public int update(OrganizationUpdateReqVO organizationUpdateReqVO) {
        Organization organization=baseMapper.selectById(organizationUpdateReqVO.getId());
        if(null==organization){
            throw new ServiceCheckException("未发现记录");
        }
        BeanUtils.copyProperties(organizationUpdateReqVO,organization);
        organization.setUpdateBy(UserInfoUtil.getCurrentUser().getUserId());
        organization.setUpdateTime(new Date());
        return baseMapper.updateById(organization);
    }

    @Override
    public int delete(Integer id) {
        Organization organization=baseMapper.selectById(id);
        if(null==organization){
            throw new ServiceCheckException("未发现记录");
        }
        return baseMapper.deleteById(id);
    }

    @Override
    public Organization detail(Integer id) {
        Organization organization=baseMapper.selectById(id);
        if(null==organization){
            throw new ServiceCheckException("未发现记录");
        }
        return baseMapper.selectById(id);
    }

    @Override
    public List<Organization> list(OrganizationQueryVO query) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if(null!=query && null != query.getOrganizationName() && !"".equals(query.getOrganizationName())){
            queryWrapper.eq("org_name",query.getOrganizationName());
        }
        return baseMapper.selectList(queryWrapper);
    }

}
