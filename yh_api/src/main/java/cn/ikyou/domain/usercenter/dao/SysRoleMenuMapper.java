package cn.ikyou.domain.usercenter.dao;

import cn.ikyou.domain.usercenter.entity.SysMenu;
import cn.ikyou.domain.usercenter.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author 石聪辉
 * @since 2021-04-12
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<SysMenu> selectByRoleId(@Param("roleId") Integer roleId);

    List<SysMenu> selectByUserId(@Param("userId") Integer userId);
}
