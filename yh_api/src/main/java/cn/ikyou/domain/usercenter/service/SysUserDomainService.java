package cn.ikyou.domain.usercenter.service;


import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.entity.SysUser;
import cn.ikyou.interfaces.base.vo.user.req.UserAddReqVO;
import cn.ikyou.interfaces.base.vo.user.req.UserQueryVO;
import cn.ikyou.interfaces.base.vo.user.req.UserUpdateReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysUserDomainService extends IService<SysUser> {

    List<RoleDTO> roleList();

    int add(UserAddReqVO userInfo);

    int update(UserUpdateReqVO userInfo);

    int delete(int userid);

    IPage<SysUser> searchPage(UserQueryVO queryVO);
    SysUser selectOneById(Integer id);

}
