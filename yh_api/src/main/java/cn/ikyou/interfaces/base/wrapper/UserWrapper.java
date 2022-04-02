package cn.ikyou.interfaces.base.wrapper;

import cn.ikyou.domain.usercenter.dto.MenuDTO;
import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.dto.UserDTO;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.interfaces.base.vo.organization.OrganizationTreeResVO;
import cn.ikyou.interfaces.base.vo.user.UserInfoVO;
import cn.ikyou.interfaces.base.vo.user.UserMenuResVO;
import cn.ikyou.interfaces.base.vo.user.UserRoleResVO;
import cn.ikyou.interfaces.base.vo.user.res.RoleResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserWrapper {

    /**
     * 转换用户信息
     * @param user
     * @return
     */
    public UserInfoVO userInfoToVO(UserDTO user) {
        UserInfoVO vo=new UserInfoVO();
        BeanUtils.copyProperties(user,vo);
        //生成角色
        List<UserRoleResVO> roles=new ArrayList<UserRoleResVO>();
        if(null!=user.getRoles()){
            for(RoleDTO role : user.getRoles()){
                UserRoleResVO roleVO=new UserRoleResVO();
                BeanUtils.copyProperties(role,roleVO);
                roles.add(roleVO);
            }
        }
        //生成菜单
        List<UserMenuResVO> menus=new ArrayList<>();
        if(null!=user.getMenus()){
            for(MenuDTO menu : user.getMenus()){
               if(menu.getParentId()==0 && menu.getAppId()==1){
                   UserMenuResVO menuVO=new UserMenuResVO();
                   BeanUtils.copyProperties(menu,menuVO);
                   //二级菜单
                   List<UserMenuResVO> menus1=new ArrayList<>();
                   for(MenuDTO menu1 : user.getMenus()){
                       if(menu1.getParentId().equals(menu.getMenuId()) && menu1.getAppId()==1){
                           UserMenuResVO menuVO1=new UserMenuResVO();
                           BeanUtils.copyProperties(menu1,menuVO1);
                           //按钮
                           List<UserMenuResVO> menus2=new ArrayList<>();
                           for(MenuDTO menu2 : user.getMenus()){
                               if(menu2.getParentId().equals(menu1.getMenuId()) && menu2.getAppId()==1){
                                   UserMenuResVO menuVO2=new UserMenuResVO();
                                   BeanUtils.copyProperties(menu2,menuVO2);
                                   menuVO2.setChildren(new ArrayList<>());
                                   menus2.add(menuVO2);
                               }
                           }
                           menuVO1.setChildren(menus2);
                           menus1.add(menuVO1);
                       }
                   }
                   menuVO.setChildren(menus1);
                   menus.add(menuVO);
               }
            }
        }
        vo.setMenus(menus);
        vo.setRoles(roles);
        return vo;
    }


    /**
     * list转换
     * @param list
     * @return
     */
    public List<UserInfoVO> pageVO(List<SysUser> list) {
        List<UserInfoVO> page=new ArrayList<UserInfoVO>();
        for(SysUser user : list){
            UserInfoVO userInfo=new UserInfoVO();
            BeanUtils.copyProperties(user,userInfo);
            page.add(userInfo);
        }
        return page;
    }

    /**
     * 角色转VO
     * @param list
     * @return
     */
    public List<RoleResVO> roleListVO(List<RoleDTO> list) {
        List<RoleResVO> result=new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(RoleDTO role : list){
                RoleResVO roleResVO=new RoleResVO();
                BeanUtils.copyProperties(role,roleResVO);
                result.add(roleResVO);
            }
        }
        return result;
    }


    public List<OrganizationTreeResVO> organizationVOTree(List<Organization> list) {
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
