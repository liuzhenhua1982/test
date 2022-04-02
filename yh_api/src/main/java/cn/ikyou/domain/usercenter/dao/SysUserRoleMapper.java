package cn.ikyou.domain.usercenter.dao;

import cn.ikyou.domain.usercenter.entity.SysRole;
import cn.ikyou.domain.usercenter.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysRole> findByUserId(@Param("userId") Integer userId);
}
