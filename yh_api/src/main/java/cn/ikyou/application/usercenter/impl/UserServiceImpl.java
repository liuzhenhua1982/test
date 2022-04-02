package cn.ikyou.application.usercenter.impl;

import cn.ikyou.application.usercenter.UserService;
import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.dto.UserDTO;
import cn.ikyou.domain.usercenter.entity.Organization;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.domain.usercenter.service.OrganizationDomainService;
import cn.ikyou.domain.usercenter.service.SysMenuDomainService;
import cn.ikyou.domain.usercenter.service.SysRoleDomainService;
import cn.ikyou.domain.usercenter.service.SysUserDomainService;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.interfaces.base.vo.user.req.UserAddReqVO;
import cn.ikyou.interfaces.base.vo.user.req.UserQueryVO;
import cn.ikyou.interfaces.base.vo.user.req.UserUpdateReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final SysUserDomainService userDomainService;
    private final SysMenuDomainService sysMenuDomainService;
    private final SysRoleDomainService sysRoleDomainService;
    private final OrganizationDomainService organizationDomainService;

    @Override
    public UserDTO detail(Integer userid) {
        SysUser user=userDomainService.getById(userid);
        if(null==user){
            throw new ServiceCheckException("用户不存在！");
        }
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        //
        Organization organization=organizationDomainService.getById(user.getOrgId());
        if(null!=organization){
            userDTO.setOrgName(organization.getOrgName());
        }
        //获取角色
        List<RoleDTO> roles=sysRoleDomainService.selectRoleByUserId(userDTO.getUserId());
        //
        if(!CollectionUtils.isEmpty(roles)){
            userDTO.setRoles(roles);
            //角色ID
            List<Integer> roleIds=new ArrayList<>();
            for(RoleDTO roleDTO : roles){
                roleIds.add(roleDTO.getRoleId());
            }
            userDTO.setMenus(sysMenuDomainService.selectMenuByRoleIds(roleIds));
        }
        return userDTO;
    }

    @Override
    public List<RoleDTO> roleList() {
        return userDomainService.roleList();
    }

    @Override
    public List<Organization> organiaztionTree() {
        return organizationDomainService.list(null);
    }

    @Override
    public int add(UserAddReqVO userInfo) {
        return userDomainService.add(userInfo);
    }

    @Override
    public int update(UserUpdateReqVO userInfo) {
        return userDomainService.update(userInfo);
    }

    @Override
    public int delete(int userid) {
        return userDomainService.delete(userid);
    }

    @Override
    public IPage<SysUser> searchPage(UserQueryVO query) {
        return userDomainService.searchPage(query);
    }



}
