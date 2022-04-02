package cn.ikyou.interfaces.base.wrapper;

import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.interfaces.base.vo.organization.OrganizationResVO;
import cn.ikyou.interfaces.base.vo.organization.OrganizationTreeResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizationWrapper {

	public OrganizationResVO entityVO(Organization organization) {
        OrganizationResVO vo = new OrganizationResVO();
        BeanUtils.copyProperties(organization, vo);
        return vo;
	}

    public List<OrganizationTreeResVO> entityVOTree(List<Organization> list) {
        List<OrganizationTreeResVO> tree=new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(Organization organization : list){
                if(organization.getPid()==0 || organization.getPid()==null){
                    OrganizationTreeResVO organizationRoot=new OrganizationTreeResVO();
                    BeanUtils.copyProperties(organization,organizationRoot);
                    children(organizationRoot,list);
                    tree.add(organizationRoot);
                }
            }
        }
	    return tree;
    }


    private void children(OrganizationTreeResVO organizationNode,List<Organization> list){
        if(!CollectionUtils.isEmpty(list) && organizationNode!=null){
            List<OrganizationTreeResVO> chidren=new ArrayList<>();
            for(Organization organization : list){
                if(organization.getPid().equals(organizationNode.getId())){
                    OrganizationTreeResVO currentOrganizationNode=new OrganizationTreeResVO();
                    BeanUtils.copyProperties(organization,currentOrganizationNode);
                    children(currentOrganizationNode,list);
                    chidren.add(currentOrganizationNode);
                }
            }
            organizationNode.setChildren(chidren);
        }
    }
}
