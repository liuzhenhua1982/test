package cn.ikyou.domain.usercenter.dao;

import cn.ikyou.domain.usercenter.dto.MenuDTO;
import cn.ikyou.domain.usercenter.entity.SysMenu;
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
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<MenuDTO> selectMenuByRoleIds(@Param("roleIds") List<Integer> roleIds);

}
