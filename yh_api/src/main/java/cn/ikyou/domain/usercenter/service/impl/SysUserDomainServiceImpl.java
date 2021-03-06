package cn.ikyou.domain.usercenter.service.impl;

import cn.ikyou.domain.usercenter.dao.SysRoleMapper;
import cn.ikyou.domain.usercenter.dao.SysUserMapper;
import cn.ikyou.domain.usercenter.dao.SysUserRoleMapper;
import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.entity.SysRole;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.domain.usercenter.entity.SysUserRole;
import cn.ikyou.domain.usercenter.service.SysUserDomainService;
import cn.ikyou.infrastructure.execption.ServiceCheckException;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import cn.ikyou.interfaces.base.vo.user.req.UserAddReqVO;
import cn.ikyou.interfaces.base.vo.user.req.UserQueryVO;
import cn.ikyou.interfaces.base.vo.user.req.UserUpdateReqVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SysUserDomainServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserDomainService {

    private final SysRoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;
    private final SysUserRoleMapper userRoleMapper;
    private final SysUserMapper sysUserMapper;

    @Override
    public List<RoleDTO> roleList() {
        QueryWrapper query=new QueryWrapper();
        List<SysRole> listRole = roleMapper.selectList(query);
        if(CollectionUtils.isEmpty(listRole)){
            return null;
        }
        List<RoleDTO> list=new ArrayList<>();
        for(SysRole role : listRole){
            RoleDTO roleDTO=new RoleDTO();
            BeanUtils.copyProperties(role,roleDTO);
            list.add(roleDTO);
        }
        return list;
    }

    @Override
    @Transactional
    public int add(UserAddReqVO userInfo) {
        if(null==userInfo){
            throw new ServiceCheckException("????????????????????????");
        }
        if(StringUtils.isEmpty(userInfo.getPassword())){
            throw new ServiceCheckException("????????????");
        }
        if(StringUtils.isEmpty(userInfo.getPassword1())){
            throw new ServiceCheckException("??????????????????");
        }
        if(!userInfo.getPassword().equals(userInfo.getPassword1())){
            throw new ServiceCheckException("?????????????????????");
        }
        SysUser user=new SysUser();
        BeanUtils.copyProperties(userInfo,user);
        user.setCreateBy(UserInfoUtil.getCurrentUser().getUsername());
        user.setCreateTime(new Date());
        //???????????????
        user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        //????????????
        int r=this.baseMapper.insert(user);
        if(r<=0){
            throw new ServiceCheckException("??????????????????");
        }
        //??????????????????
        if(null!=userInfo.getRoles()){
            for(Integer roleId : userInfo.getRoles()){
                //??????????????????
                SysUserRole userRole=new SysUserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(user.getUserId());
                //????????????
                r=this.userRoleMapper.insert(userRole);
                if(r<=0){
                    throw new ServiceCheckException("??????????????????");
                }
            }
        }
        return r;
    }

    @Override
    @Transactional
    public int update(UserUpdateReqVO userInfo) {
        SysUser user=this.baseMapper.selectById(userInfo.getUserId());
        if(null==user){
            throw new ServiceCheckException("?????????????????????");
        }
        //??????????????????
        String tempPassword=user.getPassword();
        //????????????
        BeanUtils.copyProperties(userInfo,user);
        user.setUpdateBy(UserInfoUtil.getCurrentUser().getUsername());
        user.setUpdateTime(new Date());

        if(!StringUtils.isEmpty(userInfo.getPassword()) && !StringUtils.isEmpty(userInfo.getPassword1())){
            if(!userInfo.getPassword().equals(userInfo.getPassword1())){
                throw new ServiceCheckException("?????????????????????");
            }
            user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        }else{
            user.setPassword(tempPassword);
        }

        //????????????
        int r=this.baseMapper.updateById(user);
        if(r<=0){
            throw new ServiceCheckException("??????????????????");
        }
        //??????????????????
        if(null!=userInfo.getRoles()){
            QueryWrapper query=new QueryWrapper();
            query.eq("user_id",user.getUserId());
            //?????????????????????????????????
            this.userRoleMapper.delete(query);
            //???????????????
            for(Integer roleId : userInfo.getRoles()){
                //??????????????????
                SysUserRole userRole=new SysUserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(user.getUserId());
                //????????????
                r=this.userRoleMapper.insert(userRole);
                if(r<=0){
                    throw new ServiceCheckException("??????????????????");
                }
            }
        }
        return r;
    }

    @Override
    public int delete(int userid) {
        QueryWrapper query=new QueryWrapper();
        query.eq("user_id",userid);
        this.userRoleMapper.delete(query);
        return this.baseMapper.deleteById(userid);
    }


    @Override
    public IPage<SysUser> searchPage(UserQueryVO queryVO) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(queryVO.getUsername())){
            queryWrapper.eq("username",queryVO.getUsername());
        }
        if(null!=queryVO.getOrgId() && 0!=queryVO.getOrgId()){
            queryWrapper.eq("org_id",queryVO.getOrgId());
        }
        //??????????????????
        Page<SysUser> page = new Page<>(queryVO.getPage(), queryVO.getNum());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public SysUser selectOneById(Integer id) {
        return sysUserMapper.selectOneById(id);
    }


}
