package cn.ikyou.domain.usercenter.dao;

import cn.ikyou.domain.usercenter.dto.RoleDTO;
import cn.ikyou.domain.usercenter.entity.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<RoleDTO> selectRoleByUserId(@Param("userId") Integer userId);

}
