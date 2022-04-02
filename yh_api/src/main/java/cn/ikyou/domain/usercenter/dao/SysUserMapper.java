package cn.ikyou.domain.usercenter.dao;

import cn.ikyou.domain.usercenter.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


/**
 *  Mapper 接口
 *
 * @author 石聪辉
 * @since 2021-04-12
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser selectOneById(@Param("id") Integer id);
}
