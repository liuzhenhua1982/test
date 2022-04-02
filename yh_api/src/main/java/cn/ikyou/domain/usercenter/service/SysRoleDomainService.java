package cn.ikyou.domain.usercenter.service;

import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.entity.SysMenu;
import cn.ikyou.domain.usercenter.entity.SysRole;
import cn.ikyou.interfaces.base.vo.role.req.RoleListQueryReqVO;
import cn.ikyou.interfaces.base.vo.role.res.RoleAddResVO;
import cn.ikyou.interfaces.base.vo.role.res.RoleUpdateResVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;


public interface SysRoleDomainService {

    List<SysMenu> menuTree();

    int add(RoleAddResVO roleVO);

    int update(RoleUpdateResVO roleVO);

    int delete(Integer roleId);

    IPage<SysRole> searchPage(RoleListQueryReqVO roleListResVO);

    RoleDTO details(Integer roleId);

    List<RoleDTO> selectRoleByUserId(Integer userId);

}
