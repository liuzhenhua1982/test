package cn.ikyou.domain.usercenter.service.impl;

import cn.ikyou.domain.usercenter.dao.SysMenuMapper;
import cn.ikyou.domain.usercenter.dao.SysRoleMapper;
import cn.ikyou.domain.usercenter.dao.SysRoleMenuMapper;
import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.entity.SysMenu;
import cn.ikyou.domain.usercenter.entity.SysRole;
import cn.ikyou.domain.usercenter.entity.SysRoleMenu;
import cn.ikyou.domain.usercenter.service.SysRoleDomainService;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.base.vo.role.req.RoleListQueryReqVO;
import cn.ikyou.interfaces.base.vo.role.res.RoleAddResVO;
import cn.ikyou.interfaces.base.vo.role.res.RoleUpdateResVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SysRoleDomainServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleDomainService {

    private final SysRoleMenuMapper roleMenuMapper;
    private final SysMenuMapper menuMapper;

    @Override
    public List<RoleDTO> selectRoleByUserId(Integer userId) {
        return baseMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<SysMenu> menuTree() {
        QueryWrapper query = new QueryWrapper<>();
        query.orderByAsc("order_num");
        return menuMapper.selectList(query);
    }

    @Override
    @Transactional
    public int add(RoleAddResVO roleVO) {
        if(null==roleVO){
            throw new ServiceCheckException("角色信息为空");
        }
        if(StringUtils.isEmpty(roleVO.getRoleName())){
            throw new ServiceCheckException("角色名称为空");
        }
        if(StringUtils.isEmpty(roleVO.getRoleCode())){
            throw new ServiceCheckException("角色编码为空");
        }
        SysRole role=new SysRole();
        role.setCreateBy(UserInfoUtil.getCurrentUser().getUsername());
        role.setCreateTime(new Date());
        BeanUtils.copyProperties(roleVO,role);
        //更新角色信息
        int r = this.baseMapper.insert(role);
        if(r<=0){
            throw new ServiceCheckException("更新角色失败");
        }
        if(!CollectionUtils.isEmpty(roleVO.getMenus())){
            //生成新菜单
            for(Integer menuId : roleVO.getMenus()){
                if(null==menuId || 0==menuId){
                    continue;
                }
                SysRoleMenu roleMenu=new SysRoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(role.getRoleId());
                //保存
                r=roleMenuMapper.insert(roleMenu);
                if(r<=0){
                    throw new ServiceCheckException("保存权限失败");
                }
            }
        }
        return r;
    }

    @Override
    @Transactional
    public int update(RoleUpdateResVO roleVO) {
        if(null==roleVO){
            throw new ServiceCheckException("角色信息为空");
        }
        if(StringUtils.isEmpty(roleVO.getRoleName())){
            throw new ServiceCheckException("角色名称为空");
        }
        if(StringUtils.isEmpty(roleVO.getRoleCode())){
            throw new ServiceCheckException("角色编码为空");
        }
        SysRole role=this.baseMapper.selectById(roleVO.getRoleId());
        if(null==role){
            throw new ServiceCheckException("未发现角色信息");
        }
        BeanUtils.copyProperties(roleVO,role);
        role.setUpdateBy(UserInfoUtil.getCurrentUser().getUsername());
        role.setUpdateTime(new Date());
        //更新角色信息
        int r = this.baseMapper.updateById(role);
        if(r<=0){
            throw new ServiceCheckException("更新角色失败");
        }
        if(!CollectionUtils.isEmpty(roleVO.getMenus())){
            QueryWrapper query=new QueryWrapper();
            query.eq("role_id",role.getRoleId());
            //清理角色已经含有的菜单
            this.roleMenuMapper.delete(query);
            //生成新菜单
            for(Integer menuId : roleVO.getMenus()){
                if(null==menuId || 0==menuId){
                    continue;
                }
                SysRoleMenu roleMenu=new SysRoleMenu();
                roleMenu.setRoleId(role.getRoleId());
                roleMenu.setMenuId(menuId);
                //保存
                r=this.roleMenuMapper.insert(roleMenu);
                if(r<=0){
                    throw new ServiceCheckException("保存权限失败");
                }
            }
        }
        return r;
    }

    @Override
    @Transactional
    public int delete(Integer roleId) {
        QueryWrapper query=new QueryWrapper();
        query.eq("role_id",roleId);
        this.roleMenuMapper.delete(query);
        return this.baseMapper.deleteById(roleId);
    }

    @Override
    public IPage<SysRole> searchPage(RoleListQueryReqVO roleListResVO) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(roleListResVO.getRoleName())){
            queryWrapper.eq("role_name",roleListResVO.getRoleName());
        }
        //构建分页查询
        Page<SysRole> page = new Page<>(roleListResVO.getPage(), roleListResVO.getNum());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public RoleDTO details(Integer roleId) {
        SysRole role=baseMapper.selectById(roleId);
        if(null==role){
            throw new ServiceCheckException("未发现该角色");
        }
        RoleDTO roleDTO=new RoleDTO();
        BeanUtils.copyProperties(role,roleDTO);
        //查询菜单
        List<SysMenu> menus=roleMenuMapper.selectByRoleId(role.getRoleId());
        if(!CollectionUtils.isEmpty(menus)){
            roleDTO.setMenus(menus);
        }
        return roleDTO;
    }


}
